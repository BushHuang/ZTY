package com.tencent.matrix.trace.tracer;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import com.tencent.matrix.Matrix;
import com.tencent.matrix.report.Issue;
import com.tencent.matrix.trace.TracePlugin;
import com.tencent.matrix.trace.config.TraceConfig;
import com.tencent.matrix.trace.constants.Constants;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.matrix.trace.core.UIThreadMonitor;
import com.tencent.matrix.trace.items.MethodItem;
import com.tencent.matrix.trace.util.TraceDataUtils;
import com.tencent.matrix.trace.util.Utils;
import com.tencent.matrix.util.DeviceUtil;
import com.tencent.matrix.util.MatrixHandlerThread;
import com.tencent.matrix.util.MatrixLog;
import java.lang.Thread;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONException;
import org.json.JSONObject;

public class AnrTracer extends Tracer {
    private static final String TAG = "Matrix.AnrTracer";
    private Handler anrHandler;
    private volatile AnrHandleTask anrTask;
    private boolean isAnrTraceEnable;
    private final TraceConfig traceConfig;

    class AnrHandleTask implements Runnable {
        AppMethodBeat.IndexRecord beginRecord;
        long token;

        AnrHandleTask(AppMethodBeat.IndexRecord indexRecord, long j) {
            this.beginRecord = indexRecord;
            this.token = j;
        }

        private String printAnr(String str, int[] iArr, long[] jArr, Thread.State state, StringBuilder sb, boolean z, long j, String str2, String str3, long j2, long j3, long j4, long j5) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(String.format("-\n>>>>>>>>>>>>>>>>>>>>>>> maybe happens ANR(%s ms)! <<<<<<<<<<<<<<<<<<<<<<<\n", Long.valueOf(j5)));
            sb2.append("|* scene: ");
            sb2.append(str);
            sb2.append("\n");
            sb2.append("|* [ProcessStat]");
            sb2.append("\n");
            sb2.append("|*\t\tPriority: ");
            sb2.append(iArr[0]);
            sb2.append("\n");
            sb2.append("|*\t\tNice: ");
            sb2.append(iArr[1]);
            sb2.append("\n");
            sb2.append("|*\t\tForeground: ");
            sb2.append(z);
            sb2.append("\n");
            sb2.append("|* [Memory]");
            sb2.append("\n");
            sb2.append("|*\t\tDalvikHeap: ");
            sb2.append(jArr[0]);
            sb2.append("kb\n");
            sb2.append("|*\t\tNativeHeap: ");
            sb2.append(jArr[1]);
            sb2.append("kb\n");
            sb2.append("|*\t\tVmSize: ");
            sb2.append(jArr[2]);
            sb2.append("kb\n");
            sb2.append("|* [doFrame]");
            sb2.append("\n");
            sb2.append("|*\t\tinputCost: ");
            sb2.append(j2);
            sb2.append("\n");
            sb2.append("|*\t\tanimationCost: ");
            sb2.append(j3);
            sb2.append("\n");
            sb2.append("|*\t\ttraversalCost: ");
            sb2.append(j4);
            sb2.append("\n");
            sb2.append("|* [Thread]");
            sb2.append("\n");
            sb2.append("|*\t\tState: ");
            sb2.append(state);
            sb2.append("\n");
            sb2.append("|*\t\tStack: ");
            sb2.append(str3);
            sb2.append("|* [Trace]");
            sb2.append("\n");
            sb2.append("|*\t\tStackSize: ");
            sb2.append(j);
            sb2.append("\n");
            sb2.append("|*\t\tStackKey: ");
            sb2.append(str2);
            sb2.append("\n");
            if (AnrTracer.this.traceConfig.isDebug()) {
                sb2.append(sb.toString());
            }
            sb2.append("=========================================================================");
            return sb2.toString();
        }

        public AppMethodBeat.IndexRecord getBeginRecord() {
            return this.beginRecord;
        }

        @Override
        public void run() throws JSONException, NumberFormatException {
            long jUptimeMillis = SystemClock.uptimeMillis();
            boolean zIsForeground = AnrTracer.this.isForeground();
            int[] processPriority = Utils.getProcessPriority(Process.myPid());
            long[] jArrCopyData = AppMethodBeat.getInstance().copyData(this.beginRecord);
            this.beginRecord.release();
            String visibleScene = AppMethodBeat.getVisibleScene();
            long[] jArrDumpMemory = AnrTracer.this.dumpMemory();
            Thread.State state = Looper.getMainLooper().getThread().getState();
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            String stack = Utils.getStack(stackTrace, "|*\t\t", 12);
            UIThreadMonitor monitor = UIThreadMonitor.getMonitor();
            long queueCost = monitor.getQueueCost(0, this.token);
            long queueCost2 = monitor.getQueueCost(1, this.token);
            long queueCost3 = monitor.getQueueCost(2, this.token);
            LinkedList linkedList = new LinkedList();
            if (jArrCopyData.length > 0) {
                TraceDataUtils.structuredDataToStack(jArrCopyData, linkedList, true, jUptimeMillis);
                TraceDataUtils.trimStack(linkedList, 30, new TraceDataUtils.IStructuredDataFilter() {
                    @Override
                    public void fallback(List<MethodItem> list, int i) {
                        MatrixLog.w("Matrix.AnrTracer", "[fallback] size:%s targetSize:%s stack:%s", Integer.valueOf(i), 30, list);
                        ListIterator<MethodItem> listIterator = list.listIterator(Math.min(i, 30));
                        while (listIterator.hasNext()) {
                            listIterator.next();
                            listIterator.remove();
                        }
                    }

                    @Override
                    public int getFilterMaxCount() {
                        return 60;
                    }

                    @Override
                    public boolean isFilter(long j, int i) {
                        return j < ((long) (i * 5));
                    }
                });
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            long jMax = Math.max(5000L, TraceDataUtils.stackToString(linkedList, sb, sb2));
            String treeKey = TraceDataUtils.getTreeKey(linkedList, jMax);
            MatrixLog.w("Matrix.AnrTracer", "%s \npostTime:%s curTime:%s", printAnr(visibleScene, processPriority, jArrDumpMemory, state, sb2, zIsForeground, linkedList.size(), treeKey, stack, queueCost, queueCost2, queueCost3, jMax), Long.valueOf(this.token), Long.valueOf(jUptimeMillis));
            if (jMax >= 6000) {
                MatrixLog.w("Matrix.AnrTracer", "The checked anr task was not executed on time. The possible reason is that the current process has a low priority. just pass this report", new Object[0]);
                return;
            }
            try {
                TracePlugin tracePlugin = (TracePlugin) Matrix.with().getPluginByClass(TracePlugin.class);
                if (tracePlugin == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                DeviceUtil.getDeviceInfo(jSONObject, Matrix.with().getApplication());
                jSONObject.put("detail", Constants.Type.ANR);
                jSONObject.put("cost", jMax);
                jSONObject.put("stackKey", treeKey);
                jSONObject.put("scene", visibleScene);
                jSONObject.put("stack", sb.toString());
                jSONObject.put("threadStack", Utils.getStack(stackTrace));
                jSONObject.put("processPriority", processPriority[0]);
                jSONObject.put("processNice", processPriority[1]);
                jSONObject.put("isProcessForeground", zIsForeground);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("dalvik_heap", jArrDumpMemory[0]);
                jSONObject2.put("native_heap", jArrDumpMemory[1]);
                jSONObject2.put("vm_size", jArrDumpMemory[2]);
                jSONObject.put("memory", jSONObject2);
                Issue issue = new Issue();
                issue.setKey(this.token + "");
                issue.setTag("Trace_Anr");
                issue.setContent(jSONObject);
                tracePlugin.onDetectIssue(issue);
            } catch (JSONException e) {
                MatrixLog.e("Matrix.AnrTracer", "[JSONException error: %s", e);
            }
        }
    }

    public AnrTracer(TraceConfig traceConfig) {
        this.traceConfig = traceConfig;
        this.isAnrTraceEnable = traceConfig.isAnrTraceEnable();
    }

    private long[] dumpMemory() {
        return new long[]{DeviceUtil.getDalvikHeap(), DeviceUtil.getNativeHeap(), DeviceUtil.getVmSize()};
    }

    @Override
    public void dispatchBegin(long j, long j2, long j3) {
        super.dispatchBegin(j, j2, j3);
        this.anrTask = new AnrHandleTask(AppMethodBeat.getInstance().maskIndex("AnrTracer#dispatchBegin"), j3);
        if (this.traceConfig.isDevEnv()) {
            MatrixLog.v("Matrix.AnrTracer", "* [dispatchBegin] token:%s index:%s", Long.valueOf(j3), Integer.valueOf(this.anrTask.beginRecord.index));
        }
        this.anrHandler.postDelayed(this.anrTask, 5000 - (SystemClock.uptimeMillis() - j3));
    }

    @Override
    public void dispatchEnd(long j, long j2, long j3, long j4, long j5, boolean z) {
        super.dispatchEnd(j, j2, j3, j4, j5, z);
        if (this.traceConfig.isDevEnv()) {
            long j6 = j3 - j;
            long j7 = j4 - j2;
            MatrixLog.v("Matrix.AnrTracer", "[dispatchEnd] token:%s cost:%sms cpu:%sms usage:%s", Long.valueOf(j5), Long.valueOf(j6), Long.valueOf(j7), Utils.calculateCpuUsage(j7, j6));
        }
        if (this.anrTask != null) {
            this.anrTask.getBeginRecord().release();
            this.anrHandler.removeCallbacks(this.anrTask);
        }
    }

    @Override
    public void doFrame(String str, long j, long j2, long j3, long j4, long j5, long j6) {
        if (this.traceConfig.isDevEnv()) {
            MatrixLog.v("Matrix.AnrTracer", "--> [doFrame] activityName:%s frameCost:%sms [%s:%s:%s]ns", str, Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5), Long.valueOf(j6));
        }
    }

    @Override
    public void onAlive() {
        super.onAlive();
        if (this.isAnrTraceEnable) {
            UIThreadMonitor.getMonitor().addObserver(this);
            this.anrHandler = new Handler(MatrixHandlerThread.getDefaultHandler().getLooper());
        }
    }

    @Override
    public void onDead() {
        super.onDead();
        if (this.isAnrTraceEnable) {
            UIThreadMonitor.getMonitor().removeObserver(this);
            if (this.anrTask != null) {
                this.anrTask.getBeginRecord().release();
            }
            this.anrHandler.removeCallbacksAndMessages(null);
            this.anrHandler.getLooper().quit();
        }
    }
}

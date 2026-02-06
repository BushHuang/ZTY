package com.tencent.matrix.trace.tracer;

import android.os.Process;
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
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONException;
import org.json.JSONObject;

public class EvilMethodTracer extends Tracer {
    private static final String TAG = "Matrix.EvilMethodTracer";
    private final TraceConfig config;
    private long evilThresholdMs;
    private AppMethodBeat.IndexRecord indexRecord;
    private boolean isEvilMethodTraceEnable;
    private long[] queueTypeCosts = new long[3];

    private class AnalyseTask implements Runnable {
        long cost;
        long cpuCost;
        long[] data;
        long endMs;
        boolean isForeground;
        long[] queueCost;
        String scene;

        AnalyseTask(boolean z, String str, long[] jArr, long[] jArr2, long j, long j2, long j3) {
            this.isForeground = z;
            this.scene = str;
            this.cost = j2;
            this.cpuCost = j;
            this.data = jArr;
            this.queueCost = jArr2;
            this.endMs = j3;
        }

        private String printEvil(String str, int[] iArr, boolean z, StringBuilder sb, long j, String str2, String str3, long j2, long j3, long j4, long j5) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(String.format("-\n>>>>>>>>>>>>>>>>>>>>> maybe happens Jankiness!(%sms) <<<<<<<<<<<<<<<<<<<<<\n", Long.valueOf(j5)));
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
            sb2.append("|* [CPU]");
            sb2.append("\n");
            sb2.append("|*\t\tusage: ");
            sb2.append(str3);
            sb2.append("\n");
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
            sb2.append("|* [Trace]");
            sb2.append("\n");
            sb2.append("|*\t\tStackSize: ");
            sb2.append(j);
            sb2.append("\n");
            sb2.append("|*\t\tStackKey: ");
            sb2.append(str2);
            sb2.append("\n");
            if (EvilMethodTracer.this.config.isDebug()) {
                sb2.append(sb.toString());
            }
            sb2.append("=========================================================================");
            return sb2.toString();
        }

        void analyse() throws JSONException, NumberFormatException {
            int[] processPriority = Utils.getProcessPriority(Process.myPid());
            String strCalculateCpuUsage = Utils.calculateCpuUsage(this.cpuCost, this.cost);
            LinkedList linkedList = new LinkedList();
            long[] jArr = this.data;
            if (jArr.length > 0) {
                TraceDataUtils.structuredDataToStack(jArr, linkedList, true, this.endMs);
                TraceDataUtils.trimStack(linkedList, 30, new TraceDataUtils.IStructuredDataFilter() {
                    @Override
                    public void fallback(List<MethodItem> list, int i) {
                        MatrixLog.w("Matrix.EvilMethodTracer", "[fallback] size:%s targetSize:%s stack:%s", Integer.valueOf(i), 30, list);
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
            long jMax = Math.max(this.cost, TraceDataUtils.stackToString(linkedList, sb, sb2));
            String treeKey = TraceDataUtils.getTreeKey(linkedList, jMax);
            String str = this.scene;
            boolean z = this.isForeground;
            long size = linkedList.size();
            long[] jArr2 = this.queueCost;
            MatrixLog.w("Matrix.EvilMethodTracer", "%s", printEvil(str, processPriority, z, sb2, size, treeKey, strCalculateCpuUsage, jArr2[0], jArr2[1], jArr2[2], this.cost));
            try {
                TracePlugin tracePlugin = (TracePlugin) Matrix.with().getPluginByClass(TracePlugin.class);
                if (tracePlugin == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                DeviceUtil.getDeviceInfo(jSONObject, Matrix.with().getApplication());
                jSONObject.put("detail", Constants.Type.NORMAL);
                jSONObject.put("cost", jMax);
                jSONObject.put("usage", strCalculateCpuUsage);
                try {
                    jSONObject.put("scene", this.scene);
                    jSONObject.put("stack", sb.toString());
                    jSONObject.put("stackKey", treeKey);
                    Issue issue = new Issue();
                    issue.setTag("Trace_EvilMethod");
                    issue.setContent(jSONObject);
                    tracePlugin.onDetectIssue(issue);
                } catch (JSONException e) {
                    e = e;
                    MatrixLog.e("Matrix.EvilMethodTracer", "[JSONException error: %s", e);
                }
            } catch (JSONException e2) {
                e = e2;
            }
        }

        @Override
        public void run() throws JSONException, NumberFormatException {
            analyse();
        }
    }

    public EvilMethodTracer(TraceConfig traceConfig) {
        this.config = traceConfig;
        this.evilThresholdMs = traceConfig.getEvilThresholdMs();
        this.isEvilMethodTraceEnable = traceConfig.isEvilMethodTraceEnable();
    }

    @Override
    public void dispatchBegin(long j, long j2, long j3) {
        super.dispatchBegin(j, j2, j3);
        this.indexRecord = AppMethodBeat.getInstance().maskIndex("EvilMethodTracer#dispatchBegin");
    }

    @Override
    public void dispatchEnd(long j, long j2, long j3, long j4, long j5, boolean z) throws Throwable {
        long j6;
        String str;
        String str2;
        int i;
        char c;
        char c2;
        String str3;
        String str4;
        long j7;
        super.dispatchEnd(j, j2, j3, j4, j5, z);
        long jCurrentTimeMillis = this.config.isDevEnv() ? System.currentTimeMillis() : 0L;
        long j8 = j3 - j;
        try {
            if (j8 >= this.evilThresholdMs) {
                try {
                    if (StartupTracer.isStartupEnd()) {
                        long[] jArrCopyData = AppMethodBeat.getInstance().copyData(this.indexRecord);
                        long[] jArr = new long[3];
                        System.arraycopy(this.queueTypeCosts, 0, jArr, 0, 3);
                        str3 = "[dispatchEnd] token:%s cost:%sms cpu:%sms usage:%s innerCost:%s";
                        c2 = 0;
                        str4 = "Matrix.EvilMethodTracer";
                        i = 5;
                        c = 3;
                        j7 = j8;
                        try {
                            MatrixHandlerThread.getDefaultHandler().post(new AnalyseTask(isForeground(), AppMethodBeat.getVisibleScene(), jArrCopyData, jArr, j4 - j2, j8, j3));
                        } catch (Throwable th) {
                            th = th;
                            str2 = str4;
                            str = str3;
                            j6 = j7;
                            this.indexRecord.release();
                            if (this.config.isDevEnv()) {
                                long j9 = j4 - j2;
                                String strCalculateCpuUsage = Utils.calculateCpuUsage(j9, j6);
                                Object[] objArr = new Object[i];
                                objArr[c2] = Long.valueOf(j5);
                                objArr[1] = Long.valueOf(j6);
                                objArr[2] = Long.valueOf(j9);
                                objArr[c] = strCalculateCpuUsage;
                                objArr[4] = Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis);
                                MatrixLog.v(str2, str, objArr);
                            }
                            throw th;
                        }
                    } else {
                        j7 = j8;
                        str3 = "[dispatchEnd] token:%s cost:%sms cpu:%sms usage:%s innerCost:%s";
                        str4 = "Matrix.EvilMethodTracer";
                        i = 5;
                        c = 3;
                        c2 = 0;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    i = 5;
                    c = 3;
                    c2 = 0;
                    j6 = j8;
                    str = "[dispatchEnd] token:%s cost:%sms cpu:%sms usage:%s innerCost:%s";
                    str2 = "Matrix.EvilMethodTracer";
                }
            }
            this.indexRecord.release();
            if (this.config.isDevEnv()) {
                long j10 = j4 - j2;
                long j11 = j7;
                String strCalculateCpuUsage2 = Utils.calculateCpuUsage(j10, j11);
                Object[] objArr2 = new Object[i];
                objArr2[c2] = Long.valueOf(j5);
                objArr2[1] = Long.valueOf(j11);
                objArr2[2] = Long.valueOf(j10);
                objArr2[c] = strCalculateCpuUsage2;
                objArr2[4] = Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis);
                MatrixLog.v(str4, str3, objArr2);
            }
        } catch (Throwable th3) {
            th = th3;
            j6 = j8;
            str = "[dispatchEnd] token:%s cost:%sms cpu:%sms usage:%s innerCost:%s";
            str2 = "Matrix.EvilMethodTracer";
            i = 5;
            c = 3;
            c2 = 0;
        }
    }

    @Override
    public void doFrame(String str, long j, long j2, long j3, long j4, long j5, long j6) {
        long[] jArr = this.queueTypeCosts;
        jArr[0] = j4;
        jArr[1] = j5;
        jArr[2] = j6;
    }

    public void modifyEvilThresholdMs(long j) {
        this.evilThresholdMs = j;
    }

    @Override
    public void onAlive() {
        super.onAlive();
        if (this.isEvilMethodTraceEnable) {
            UIThreadMonitor.getMonitor().addObserver(this);
        }
    }

    @Override
    public void onDead() {
        super.onDead();
        if (this.isEvilMethodTraceEnable) {
            UIThreadMonitor.getMonitor().removeObserver(this);
        }
    }
}

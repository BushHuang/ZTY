package com.tencent.matrix.trace.tracer;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import com.tencent.matrix.Matrix;
import com.tencent.matrix.report.Issue;
import com.tencent.matrix.trace.TracePlugin;
import com.tencent.matrix.trace.config.TraceConfig;
import com.tencent.matrix.trace.constants.Constants;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.matrix.trace.hacker.ActivityThreadHacker;
import com.tencent.matrix.trace.items.MethodItem;
import com.tencent.matrix.trace.listeners.IAppMethodBeatListener;
import com.tencent.matrix.trace.util.TraceDataUtils;
import com.tencent.matrix.util.DeviceUtil;
import com.tencent.matrix.util.MatrixHandlerThread;
import com.tencent.matrix.util.MatrixLog;
import com.tencent.mrs.plugin.IDynamicConfig;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class StartupTracer extends Tracer implements IAppMethodBeatListener, Application.ActivityLifecycleCallbacks {
    private static final String TAG = "Matrix.StartupTracer";
    private static long coldCost;
    private int activeActivityCount;
    private long coldStartupThresholdMs;
    private final TraceConfig config;
    private long firstScreenCost = 0;
    private boolean hasShowSplashActivity;
    private boolean isStartupEnable;
    private boolean isWarmStartUp;
    private Set<String> splashActivities;
    private long warmStartupThresholdMs;

    private class AnalyseTask implements Runnable {
        long allCost;
        long applicationCost;
        long[] data;
        long firstScreenCost;
        boolean isWarmStartUp;
        int scene;

        AnalyseTask(long[] jArr, long j, long j2, long j3, boolean z, int i) {
            this.data = jArr;
            this.scene = i;
            this.applicationCost = j;
            this.firstScreenCost = j2;
            this.allCost = j3;
            this.isWarmStartUp = z;
        }

        private void report(long j, long j2, StringBuilder sb, String str, long j3, boolean z, int i) throws JSONException {
            TracePlugin tracePlugin = (TracePlugin) Matrix.with().getPluginByClass(TracePlugin.class);
            if (tracePlugin == null) {
                return;
            }
            if ((j3 <= StartupTracer.this.coldStartupThresholdMs || z) && (j3 <= StartupTracer.this.warmStartupThresholdMs || !z)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                DeviceUtil.getDeviceInfo(jSONObject, Matrix.with().getApplication());
                jSONObject.put("is_warm_start_up", z);
                jSONObject.put("application_create_scene", i);
                jSONObject.put("cost", j3);
                jSONObject.put("application_create", j);
                jSONObject.put("first_activity_create", j2);
                jSONObject.put("detail", Constants.Type.STARTUP);
                jSONObject.put("stack", sb.toString());
                jSONObject.put("stackKey", str);
                jSONObject.put("subType", z ? 2 : 1);
                Issue issue = new Issue();
                issue.setTag("Trace_StartUp");
                issue.setContent(jSONObject);
                tracePlugin.onDetectIssue(issue);
            } catch (JSONException e) {
                MatrixLog.e("Matrix.StartupTracer", "[JSONException error: %s", e);
            }
        }

        @Override
        public void run() throws JSONException {
            LinkedList linkedList = new LinkedList();
            long[] jArr = this.data;
            if (jArr.length > 0) {
                TraceDataUtils.structuredDataToStack(jArr, linkedList, false, -1L);
                TraceDataUtils.trimStack(linkedList, StartupTracer.this.config.get(IDynamicConfig.ExptEnum.clicfg_matrix_trace_start_up_method_stack_count, 30), new TraceDataUtils.IStructuredDataFilter() {
                    @Override
                    public void fallback(List<MethodItem> list, int i) {
                        MatrixLog.w("Matrix.StartupTracer", "[fallback] size:%s targetSize:%s stack:%s", Integer.valueOf(i), 30, list);
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
            long jMax = Math.max(this.allCost, TraceDataUtils.stackToString(linkedList, sb, sb2));
            String treeKey = TraceDataUtils.getTreeKey(linkedList, jMax);
            if ((this.allCost > StartupTracer.this.coldStartupThresholdMs && !this.isWarmStartUp) || (this.allCost > StartupTracer.this.warmStartupThresholdMs && this.isWarmStartUp)) {
                MatrixLog.w("Matrix.StartupTracer", "stackKey:%s \n%s", treeKey, sb2.toString());
            }
            report(this.applicationCost, this.firstScreenCost, sb, treeKey, jMax, this.isWarmStartUp, this.scene);
        }
    }

    public StartupTracer(TraceConfig traceConfig) {
        this.config = traceConfig;
        this.isStartupEnable = traceConfig.isStartupEnable();
        this.splashActivities = traceConfig.getSplashActivities();
        this.coldStartupThresholdMs = traceConfig.getColdStartupThresholdMs();
        this.warmStartupThresholdMs = traceConfig.getWarmStartupThresholdMs();
    }

    private void analyse(long j, long j2, long j3, boolean z) {
        MatrixLog.i("Matrix.StartupTracer", "[report] applicationCost:%s firstScreenCost:%s allCost:%s isWarmStartUp:%s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Boolean.valueOf(z));
        long[] jArrCopyData = new long[0];
        if (!z && j3 >= this.coldStartupThresholdMs) {
            jArrCopyData = AppMethodBeat.getInstance().copyData(ActivityThreadHacker.sApplicationCreateBeginMethodIndex);
            ActivityThreadHacker.sApplicationCreateBeginMethodIndex.release();
        } else if (z && j3 >= this.warmStartupThresholdMs) {
            jArrCopyData = AppMethodBeat.getInstance().copyData(ActivityThreadHacker.sLastLaunchActivityMethodIndex);
            ActivityThreadHacker.sLastLaunchActivityMethodIndex.release();
        }
        MatrixHandlerThread.getDefaultHandler().post(new AnalyseTask(jArrCopyData, j, j2, j3, z, ActivityThreadHacker.sApplicationCreateScene));
    }

    private boolean isColdStartup() {
        return coldCost == 0;
    }

    public static boolean isStartupEnd() {
        return coldCost > 0;
    }

    private boolean isWarmStartUp() {
        return this.isWarmStartUp;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.activeActivityCount == 0 && coldCost > 0) {
            this.isWarmStartUp = true;
        }
        this.activeActivityCount++;
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        this.activeActivityCount--;
    }

    @Override
    public void onActivityFocused(String str) {
        if (!isColdStartup()) {
            if (isWarmStartUp()) {
                this.isWarmStartUp = false;
                long jUptimeMillis = SystemClock.uptimeMillis() - ActivityThreadHacker.getLastLaunchActivityTime();
                if (jUptimeMillis > 0) {
                    analyse(ActivityThreadHacker.getApplicationCost(), this.firstScreenCost, jUptimeMillis, true);
                    return;
                }
                return;
            }
            return;
        }
        if (this.firstScreenCost == 0) {
            this.firstScreenCost = SystemClock.uptimeMillis() - ActivityThreadHacker.getEggBrokenTime();
        }
        if (this.hasShowSplashActivity) {
            coldCost = SystemClock.uptimeMillis() - ActivityThreadHacker.getEggBrokenTime();
        } else if (this.splashActivities.contains(str)) {
            this.hasShowSplashActivity = true;
        } else if (this.splashActivities.isEmpty()) {
            MatrixLog.i("Matrix.StartupTracer", "default splash activity[%s]", str);
            coldCost = this.firstScreenCost;
        } else {
            MatrixLog.w("Matrix.StartupTracer", "pass this activity[%s] at duration of start up! splashActivities=%s", str, this.splashActivities);
        }
        if (coldCost > 0) {
            analyse(ActivityThreadHacker.getApplicationCost(), this.firstScreenCost, coldCost, false);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    protected void onAlive() {
        super.onAlive();
        MatrixLog.i("Matrix.StartupTracer", "[onAlive] isStartupEnable:%s", Boolean.valueOf(this.isStartupEnable));
        if (this.isStartupEnable) {
            AppMethodBeat.getInstance().addListener(this);
            Matrix.with().getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    @Override
    protected void onDead() {
        super.onDead();
        if (this.isStartupEnable) {
            AppMethodBeat.getInstance().removeListener(this);
            Matrix.with().getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }
}

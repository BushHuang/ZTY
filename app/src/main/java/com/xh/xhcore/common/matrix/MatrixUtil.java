package com.xh.xhcore.common.matrix;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.FrameMetrics;
import android.view.Window;
import com.tencent.matrix.Matrix;
import com.tencent.matrix.trace.TracePlugin;
import com.tencent.matrix.trace.config.TraceConfig;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.matrix.util.MatrixLog;
import com.xh.xhcore.common.base.BaseActivityLifecycleCallbacks;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.matrix.listener.IssuePluginListener;
import java.util.concurrent.TimeUnit;

public class MatrixUtil {
    private static final String TAG = "MatrixUtil";

    public static void init(Application application) {
        APMConfig aPMConfig = XHAppConfigProxy.getInstance().getAPMConfig();
        if (!aPMConfig.isEnable()) {
            MatrixLog.e("MatrixUtil", "Matrix is disabled! just return", new Object[0]);
            return;
        }
        boolean zIsDebugAPM = aPMConfig.isDebugAPM();
        AppMethodBeat.isDev = zIsDebugAPM;
        MatrixLog.i("MatrixUtil", "Matrix init", new Object[0]);
        Matrix.Builder builder = new Matrix.Builder(application);
        builder.patchListener(new IssuePluginListener(application));
        TracePlugin tracePlugin = new TracePlugin(new TraceConfig.Builder().dynamicConfig(aPMConfig.getApmDynamicConfig()).enableFPS(true).enableEvilMethodTrace(true).enableAnrTrace(true).enableStartup(true).splashActivities(aPMConfig.getSplashActivities()).isDebug(zIsDebugAPM).isDevEnv(zIsDebugAPM).build());
        builder.plugin(tracePlugin);
        Matrix.init(builder.build());
        tracePlugin.start();
    }

    private static void listenFrameMetrics(Application application) {
        if (Build.VERSION.SDK_INT >= 24) {
            final Window.OnFrameMetricsAvailableListener onFrameMetricsAvailableListener = new Window.OnFrameMetricsAvailableListener() {
                private void logFrameMetrics(Window window, FrameMetrics frameMetrics, int i) {
                    LogUtils.d("context = " + window.getContext() + " metric = " + i + " frameMetrics = " + TimeUnit.NANOSECONDS.toMillis(frameMetrics.getMetric(i)) + "ms");
                }

                @Override
                public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
                    logFrameMetrics(window, frameMetrics, 0);
                    logFrameMetrics(window, frameMetrics, 1);
                    logFrameMetrics(window, frameMetrics, 2);
                    logFrameMetrics(window, frameMetrics, 3);
                    logFrameMetrics(window, frameMetrics, 4);
                    logFrameMetrics(window, frameMetrics, 5);
                    logFrameMetrics(window, frameMetrics, 6);
                    logFrameMetrics(window, frameMetrics, 7);
                    logFrameMetrics(window, frameMetrics, 8);
                    logFrameMetrics(window, frameMetrics, 9);
                    logFrameMetrics(window, frameMetrics, 10);
                    logFrameMetrics(window, frameMetrics, 11);
                    LogUtils.d("dropCountSinceLastInvocation = " + i);
                }
            };
            application.registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks() {
                @Override
                public void onActivityPaused(Activity activity) {
                    super.onActivityPaused(activity);
                    activity.getWindow().removeOnFrameMetricsAvailableListener(onFrameMetricsAvailableListener);
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    super.onActivityResumed(activity);
                    activity.getWindow().addOnFrameMetricsAvailableListener(onFrameMetricsAvailableListener, new Handler(Looper.getMainLooper()));
                }
            });
        }
    }
}

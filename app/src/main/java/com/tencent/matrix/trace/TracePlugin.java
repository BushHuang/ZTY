package com.tencent.matrix.trace;

import android.app.Application;
import android.os.Build;
import android.os.Looper;
import com.tencent.matrix.plugin.Plugin;
import com.tencent.matrix.plugin.PluginListener;
import com.tencent.matrix.trace.config.TraceConfig;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.matrix.trace.core.UIThreadMonitor;
import com.tencent.matrix.trace.tracer.AnrTracer;
import com.tencent.matrix.trace.tracer.EvilMethodTracer;
import com.tencent.matrix.trace.tracer.FrameTracer;
import com.tencent.matrix.trace.tracer.StartupTracer;
import com.tencent.matrix.util.MatrixHandlerThread;
import com.tencent.matrix.util.MatrixLog;

public class TracePlugin extends Plugin {
    private static final String TAG = "Matrix.TracePlugin";
    private AnrTracer anrTracer;
    private EvilMethodTracer evilMethodTracer;
    private FrameTracer frameTracer;
    private StartupTracer startupTracer;
    private final TraceConfig traceConfig;

    public TracePlugin(TraceConfig traceConfig) {
        this.traceConfig = traceConfig;
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public AnrTracer getAnrTracer() {
        return this.anrTracer;
    }

    public AppMethodBeat getAppMethodBeat() {
        return AppMethodBeat.getInstance();
    }

    public EvilMethodTracer getEvilMethodTracer() {
        return this.evilMethodTracer;
    }

    public FrameTracer getFrameTracer() {
        return this.frameTracer;
    }

    public StartupTracer getStartupTracer() {
        return this.startupTracer;
    }

    @Override
    public String getTag() {
        return "Trace";
    }

    public TraceConfig getTraceConfig() {
        return this.traceConfig;
    }

    public UIThreadMonitor getUIThreadMonitor() {
        if (UIThreadMonitor.getMonitor().isInit()) {
            return UIThreadMonitor.getMonitor();
        }
        return null;
    }

    @Override
    public void init(Application application, PluginListener pluginListener) {
        super.init(application, pluginListener);
        MatrixLog.i("Matrix.TracePlugin", "trace plugin init, trace config: %s", this.traceConfig.toString());
        if (Build.VERSION.SDK_INT < 16) {
            MatrixLog.e("Matrix.TracePlugin", "[FrameBeat] API is low Build.VERSION_CODES.JELLY_BEAN(16), TracePlugin is not supported", new Object[0]);
            unSupportPlugin();
        } else {
            this.anrTracer = new AnrTracer(this.traceConfig);
            this.frameTracer = new FrameTracer(this.traceConfig);
            this.evilMethodTracer = new EvilMethodTracer(this.traceConfig);
            this.startupTracer = new StartupTracer(this.traceConfig);
        }
    }

    @Override
    public void onForeground(boolean z) {
        super.onForeground(z);
        if (isSupported()) {
            FrameTracer frameTracer = this.frameTracer;
            if (frameTracer != null) {
                frameTracer.onForeground(z);
            }
            AnrTracer anrTracer = this.anrTracer;
            if (anrTracer != null) {
                anrTracer.onForeground(z);
            }
            EvilMethodTracer evilMethodTracer = this.evilMethodTracer;
            if (evilMethodTracer != null) {
                evilMethodTracer.onForeground(z);
            }
            StartupTracer startupTracer = this.startupTracer;
            if (startupTracer != null) {
                startupTracer.onForeground(z);
            }
        }
    }

    @Override
    public void start() {
        super.start();
        if (!isSupported()) {
            MatrixLog.w("Matrix.TracePlugin", "[start] Plugin is unSupported!", new Object[0]);
            return;
        }
        MatrixLog.w("Matrix.TracePlugin", "start!", new Object[0]);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!UIThreadMonitor.getMonitor().isInit()) {
                    try {
                        UIThreadMonitor.getMonitor().init(TracePlugin.this.traceConfig);
                    } catch (RuntimeException e) {
                        MatrixLog.e("Matrix.TracePlugin", "[start] RuntimeException:%s", e);
                        return;
                    }
                }
                AppMethodBeat.getInstance().onStart();
                UIThreadMonitor.getMonitor().onStart();
                TracePlugin.this.anrTracer.onStartTrace();
                TracePlugin.this.frameTracer.onStartTrace();
                TracePlugin.this.evilMethodTracer.onStartTrace();
                TracePlugin.this.startupTracer.onStartTrace();
            }
        };
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            MatrixLog.w("Matrix.TracePlugin", "start TracePlugin in Thread[%s] but not in mainThread!", Long.valueOf(Thread.currentThread().getId()));
            MatrixHandlerThread.getDefaultMainHandler().post(runnable);
        }
    }

    @Override
    public void stop() {
        super.stop();
        if (!isSupported()) {
            MatrixLog.w("Matrix.TracePlugin", "[stop] Plugin is unSupported!", new Object[0]);
            return;
        }
        MatrixLog.w("Matrix.TracePlugin", "stop!", new Object[0]);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AppMethodBeat.getInstance().onStop();
                UIThreadMonitor.getMonitor().onStop();
                TracePlugin.this.anrTracer.onCloseTrace();
                TracePlugin.this.frameTracer.onCloseTrace();
                TracePlugin.this.evilMethodTracer.onCloseTrace();
                TracePlugin.this.startupTracer.onCloseTrace();
            }
        };
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            MatrixLog.w("Matrix.TracePlugin", "stop TracePlugin in Thread[%s] but not in mainThread!", Long.valueOf(Thread.currentThread().getId()));
            MatrixHandlerThread.getDefaultMainHandler().post(runnable);
        }
    }
}

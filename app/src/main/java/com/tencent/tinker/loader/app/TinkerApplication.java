package com.tencent.tinker.loader.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import com.tencent.tinker.loader.AppInfoChangedBlocker;
import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.TinkerUncaughtHandler;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.lang.reflect.Constructor;

public abstract class TinkerApplication extends Application {
    private static final String INTENT_PATCH_EXCEPTION = "intent_patch_exception";
    private static final String TINKER_LOADER_METHOD = "tryLoad";
    private final String delegateClassName;
    private final String loaderClassName;
    private ClassLoader mCurrentClassLoader;
    private Handler mInlineFence;
    private final int tinkerFlags;
    private final boolean tinkerLoadVerifyFlag;
    private Intent tinkerResultIntent;
    private boolean useSafeMode;

    protected TinkerApplication(int i) {
        this(i, "com.tencent.tinker.entry.DefaultApplicationLike", TinkerLoader.class.getName(), false);
    }

    protected TinkerApplication(int i, String str) {
        this(i, str, TinkerLoader.class.getName(), false);
    }

    protected TinkerApplication(int i, String str, String str2, boolean z) {
        this.mCurrentClassLoader = null;
        this.mInlineFence = null;
        this.tinkerFlags = i;
        this.delegateClassName = str;
        this.loaderClassName = str2;
        this.tinkerLoadVerifyFlag = z;
    }

    private void bailLoaded() {
        try {
            if (this.tinkerResultIntent != null && ShareIntentUtil.getIntentReturnCode(this.tinkerResultIntent) == 0 && !AppInfoChangedBlocker.tryStart(this)) {
                throw new IllegalStateException("AppInfoChangedBlocker.tryStart return false.");
            }
        } catch (Throwable th) {
            throw new TinkerRuntimeException("Fail to do bail logic for load ensuring.", th);
        }
    }

    private Handler createInlineFence(Application application, int i, String str, boolean z, long j, long j2, Intent intent) {
        try {
            Object objNewInstance = Class.forName(str, false, this.mCurrentClassLoader).getConstructor(Application.class, Integer.TYPE, Boolean.TYPE, Long.TYPE, Long.TYPE, Intent.class).newInstance(application, Integer.valueOf(i), Boolean.valueOf(z), Long.valueOf(j), Long.valueOf(j2), intent);
            Constructor<?> constructor = Class.forName("com.tencent.tinker.entry.TinkerApplicationInlineFence", false, this.mCurrentClassLoader).getConstructor(Class.forName("com.tencent.tinker.entry.ApplicationLike", false, this.mCurrentClassLoader));
            constructor.setAccessible(true);
            return (Handler) constructor.newInstance(objNewInstance);
        } catch (Throwable th) {
            throw new TinkerRuntimeException("createInlineFence failed", th);
        }
    }

    private void loadTinker() {
        try {
            Class<?> cls = Class.forName(this.loaderClassName, false, TinkerApplication.class.getClassLoader());
            this.tinkerResultIntent = (Intent) cls.getMethod("tryLoad", TinkerApplication.class).invoke(cls.getConstructor(new Class[0]).newInstance(new Object[0]), this);
        } catch (Throwable th) {
            Intent intent = new Intent();
            this.tinkerResultIntent = intent;
            ShareIntentUtil.setIntentReturnCode(intent, -20);
            this.tinkerResultIntent.putExtra("intent_patch_exception", th);
        }
    }

    private void onBaseContextAttached(Context context) {
        try {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long jCurrentTimeMillis = System.currentTimeMillis();
            loadTinker();
            this.mCurrentClassLoader = context.getClassLoader();
            Handler handlerCreateInlineFence = createInlineFence(this, this.tinkerFlags, this.delegateClassName, this.tinkerLoadVerifyFlag, jElapsedRealtime, jCurrentTimeMillis, this.tinkerResultIntent);
            this.mInlineFence = handlerCreateInlineFence;
            TinkerInlineFenceAction.callOnBaseContextAttached(handlerCreateInlineFence, context);
            if (this.useSafeMode) {
                ShareTinkerInternals.setSafeModeCount(this, 0);
            }
        } catch (TinkerRuntimeException e) {
            throw e;
        } catch (Throwable th) {
            throw new TinkerRuntimeException(th.getMessage(), th);
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Thread.setDefaultUncaughtExceptionHandler(new TinkerUncaughtHandler(this));
        onBaseContextAttached(context);
    }

    @Override
    public AssetManager getAssets() {
        AssetManager assets = super.getAssets();
        Handler handler = this.mInlineFence;
        return handler == null ? assets : TinkerInlineFenceAction.callGetAssets(handler, assets);
    }

    @Override
    public Context getBaseContext() {
        Context baseContext = super.getBaseContext();
        Handler handler = this.mInlineFence;
        return handler == null ? baseContext : TinkerInlineFenceAction.callGetBaseContext(handler, baseContext);
    }

    @Override
    public ClassLoader getClassLoader() {
        ClassLoader classLoader = super.getClassLoader();
        Handler handler = this.mInlineFence;
        return handler == null ? classLoader : TinkerInlineFenceAction.callGetClassLoader(handler, classLoader);
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Handler handler = this.mInlineFence;
        return handler == null ? resources : TinkerInlineFenceAction.callGetResources(handler, resources);
    }

    @Override
    public Object getSystemService(String str) {
        Object systemService = super.getSystemService(str);
        Handler handler = this.mInlineFence;
        return handler == null ? systemService : TinkerInlineFenceAction.callGetSystemService(handler, str, systemService);
    }

    public int getTinkerFlags() {
        return this.tinkerFlags;
    }

    public boolean isTinkerLoadVerifyFlag() {
        return this.tinkerLoadVerifyFlag;
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Handler handler = this.mInlineFence;
        if (handler == null) {
            return;
        }
        TinkerInlineFenceAction.callOnConfigurationChanged(handler, configuration);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bailLoaded();
        Handler handler = this.mInlineFence;
        if (handler == null) {
            return;
        }
        TinkerInlineFenceAction.callOnCreate(handler);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Handler handler = this.mInlineFence;
        if (handler == null) {
            return;
        }
        TinkerInlineFenceAction.callOnLowMemory(handler);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Handler handler = this.mInlineFence;
        if (handler == null) {
            return;
        }
        TinkerInlineFenceAction.callOnTerminate(handler);
    }

    @Override
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Handler handler = this.mInlineFence;
        if (handler == null) {
            return;
        }
        TinkerInlineFenceAction.callOnTrimMemory(handler, i);
    }

    public void setUseSafeMode(boolean z) {
        this.useSafeMode = z;
    }
}

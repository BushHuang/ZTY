package com.xuehai.launcher.common.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.util.XHDeviceUtil;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.config.Permission;
import com.xuehai.launcher.common.crash.CrashLevelManager;
import com.xuehai.launcher.common.crash.MyUncaughtExceptionHandler;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.StartupStore;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.DisplayUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 -2\u00020\u00012\u00020\u0002:\u0001-B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0014J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0016J\u0010\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u0006\u0010\u001b\u001a\u00020\u0019J\u001c\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0012\u0010 \u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010!\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\"\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010#\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\b\u0010$\u001a\u0004\u0018\u00010\u001fH\u0016J\u0012\u0010%\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010&\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\tH\u0016J\u0006\u0010+\u001a\u00020\tJ\u0006\u0010,\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/xuehai/launcher/common/base/BaseApplication;", "Lcom/xuehai/launcher/common/base/XhApplication;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "activityCount", "", "mTopActivity", "Landroid/app/Activity;", "attachBaseContext", "", "base", "Landroid/content/Context;", "doOnMainProcess", "getDeviceId", "", "getDeviceProtectedStorageContext", "getResources", "Landroid/content/res/Resources;", "getTopActivity", "getVersion", "getVersionCode", "initStartupStore", "startupStore", "Lcom/xuehai/launcher/common/plugins/StartupStore;", "isDebug", "", "isMainProcess", "isProduction", "onActivityCreated", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "whenOnBackground", "whenOnTop", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseApplication extends XhApplication implements Application.ActivityLifecycleCallbacks {

    public static final Companion INSTANCE = new Companion(null);
    private static BaseApplication application;
    private int activityCount;
    private Activity mTopActivity;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/xuehai/launcher/common/base/BaseApplication$Companion;", "", "()V", "application", "Lcom/xuehai/launcher/common/base/BaseApplication;", "getInstance", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BaseApplication getInstance() {
            BaseApplication baseApplication = BaseApplication.application;
            if (baseApplication != null) {
                return baseApplication;
            }
            Intrinsics.throwUninitializedPropertyAccessException("application");
            return null;
        }
    }

    @JvmStatic
    public static final BaseApplication getInstance() {
        return INSTANCE.getInstance();
    }

    private final boolean isMainProcess() {
        Object systemService = getApplicationContext().getSystemService("activity");
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid()) {
                return Intrinsics.areEqual(runningAppProcessInfo.processName, getPackageName());
            }
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        Intrinsics.checkNotNullParameter(base, "base");
        super.attachBaseContext(base);
        application = this;
        LogManager.enableFileLooper = false;
    }

    protected void doOnMainProcess() {
    }

    @Override
    public final String getDeviceId() {
        String serialNumber = PolicyManager.getDevicePolicyProxy().getSerialNumber();
        MyLog.i("获取设备号", "getDeviceId 调用MDM getSerialNumber：" + serialNumber);
        String str = serialNumber;
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, "unknown")) {
            return serialNumber;
        }
        String uuid = XHDeviceUtil.getUUID(this);
        Intrinsics.checkNotNullExpressionValue(uuid, "getUUID(this)");
        MyLog.i("获取设备号", "getDeviceId MDM调用 getUUID：" + uuid);
        return TextUtils.isEmpty(uuid) ? "" : uuid;
    }

    public final Context getDeviceProtectedStorageContext() {
        if (Build.VERSION.SDK_INT < 24) {
            return INSTANCE.getInstance();
        }
        Context contextCreateDeviceProtectedStorageContext = INSTANCE.getInstance().createDeviceProtectedStorageContext();
        Intrinsics.checkNotNullExpressionValue(contextCreateDeviceProtectedStorageContext, "{\n            getInstanc…torageContext()\n        }");
        return contextCreateDeviceProtectedStorageContext;
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (!(resources.getConfiguration().fontScale == 1.0f)) {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        Intrinsics.checkNotNullExpressionValue(resources, "res");
        return resources;
    }

    public final Activity getMTopActivity() {
        return this.mTopActivity;
    }

    public String getVersion() {
        return "v1.21.06.20251212hwS";
    }

    public int getVersionCode() {
        return 1021006;
    }

    public final void initStartupStore(StartupStore startupStore) {
    }

    public final boolean isDebug() {
        return !isProduction();
    }

    public final boolean isProduction() {
        return true;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        this.mTopActivity = activity;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (this.activityCount == 0) {
            whenOnTop();
        }
        this.activityCount++;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        int i = this.activityCount - 1;
        this.activityCount = i;
        if (i == 0) {
            whenOnBackground();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        if (!(newConfig.fontScale == 1.0f)) {
            getResources();
        }
        DisplayUtil.init$default(this, 0, 2, null);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        try {
            super.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MyUncaughtExceptionHandler().init(this);
        CrashLevelManager.INSTANCE.initLevel();
        Permission.initPermission();
        DisplayUtil.init$default(this, 0, 2, null);
        registerActivityLifecycleCallbacks(this);
        if (isMainProcess()) {
            doOnMainProcess();
        }
    }

    public final void whenOnBackground() {
        MyLog.i("Debug[MDM]", "mdm退到后台");
        SessionData.INSTANCE.setOnTop(false);
    }

    public final void whenOnTop() {
        MyLog.i("Debug[MDM]", "mdm回到前台");
        SessionData.INSTANCE.setOnTop(true);
    }
}

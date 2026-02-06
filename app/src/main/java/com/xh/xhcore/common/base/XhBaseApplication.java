package com.xh.xhcore.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import com.xh.logutils.LogLevel;
import com.xh.logutils.LogManager;
import com.xh.logutils.UncaughtExceptionCallback;
import com.xh.xhcore.common.config.BaseXHAppConfig;
import com.xh.xhcore.common.config.WholeLinkEnvironment;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.helper.PlatformRunningCheckHelper;
import com.xh.xhcore.common.hotfix.TinkerPatchUtil;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.matrix.MatrixUtil;
import com.xh.xhcore.common.oom.OOMHelper;
import com.xh.xhcore.common.service.LogcatService;
import com.xh.xhcore.common.statistic.BuryManager;
import com.xh.xhcore.common.statistic.DataCollectionUtil;
import com.xh.xhcore.common.statistic.strategy.BuryStrategyManager;
import com.xh.xhcore.common.third.SystemBarTintManager;
import com.xh.xhcore.common.util.ActivityListAdapter;
import com.xh.xhcore.common.util.DensityUtil;
import com.xh.xhcore.common.util.MMKVUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHDeviceUtil;
import com.xh.xhcore.common.util.XhPermissionUtil;
import com.xh.xhcore.common.util.inner.ServerEnvironmentUtil;
import com.xh.xhcore.common.xcrash.XCrashUtil;
import com.xuehai.provider.core.CPVDManager;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.util.HashMap;

public abstract class XhBaseApplication extends Application {
    public static boolean isMicroServerSuccess = false;
    public static Context mContext;
    public static Handler mMainHandler;
    public static String sAppId;
    public static String sPackageName;

    @Deprecated
    public static String sSessionId;

    @Deprecated
    public static String sUUID;

    @Deprecated
    public static String sUserId;
    public static String sVersionName;
    protected boolean isUseMicroService;
    private Intent mLogcatServiceIntent;
    private Object memoryKilled;

    @Deprecated
    protected HashMap<String, String> mUrlRootMap = new HashMap<>();
    private boolean isInit = false;

    public static int[] getClassIds() {
        CPVDUser user = CPVDUserData.getUser(mContext);
        if (user != null) {
            return user.getClassIds();
        }
        return null;
    }

    public static String getSchoolId() {
        CPVDUser user = CPVDUserData.getUser(mContext);
        return user != null ? String.valueOf(user.getSchoolId()) : "";
    }

    public static int getUserType() {
        CPVDUser user = CPVDUserData.getUser(mContext);
        if (user != null) {
            return user.getUserType();
        }
        return -1;
    }

    public static String getXHSessionId() {
        try {
            CPVDUser user = CPVDUserData.getUser(mContext);
            return user != null ? user.getAccessToken() : sSessionId;
        } catch (Exception e) {
            e.printStackTrace();
            return sSessionId;
        }
    }

    public static String getXHUserId() {
        try {
            CPVDUser user = CPVDUserData.getUser(mContext);
            StringBuilder sb = new StringBuilder();
            sb.append(user != null ? Long.valueOf(user.getUserId()) : sUserId);
            sb.append("");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return sUserId + "";
        }
    }

    public static XhBaseApplication getXhBaseApplication() {
        return (XhBaseApplication) mContext;
    }

    private void initBaseRequestInfo() {
        sPackageName = XHAppUtil.getPackageName();
        sVersionName = XHAppUtil.getVersionName();
        sUUID = XHDeviceUtil.getUUID(this);
        sSessionId = getS();
        sUserId = getM();
        sAppId = getC();
    }

    private void initEnvironment() {
        if (isCPVDEnvironmentProduction()) {
            ServerEnvironmentUtil.initProductionEnvironment();
        } else {
            ServerEnvironmentUtil.initNonProductionEnvironment();
        }
    }

    private void initLog() {
        LogUtils.setDebug(getAppConfig().isAppBuildTypeDebug());
        LogUtils.addTagBlackList("networkErrorTag");
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        mContext = this;
        XHAppConfigProxy.getInstance().setConfig(getAppConfig());
        if (XHAppConfigProxy.getInstance().isInitXCrash()) {
            XCrashUtil.init(this);
        }
        LogManager.enableFileLooper = false;
    }

    protected abstract BaseXHAppConfig getAppConfig();

    @Deprecated
    public abstract String getC();

    public long getLogSize() {
        return 4194304L;
    }

    @Deprecated
    public abstract String getM();

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        DensityUtil.onConfigChangeImplDefault(resources.getDisplayMetrics());
        return resources;
    }

    @Deprecated
    public abstract String getS();

    public UncaughtExceptionCallback getUncaughtExceptionCallBack() {
        return null;
    }

    public HashMap<String, String> getUrlRootMap() {
        return this.mUrlRootMap;
    }

    public void initMemoryKilled() {
        this.memoryKilled = new Object();
    }

    @Deprecated
    public abstract void initRootUrlMap();

    public boolean initThree() {
        if (!XhPermissionUtil.hasPermissions(this, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")) {
            return false;
        }
        if (this.isInit) {
            return true;
        }
        this.isInit = true;
        LogUtils.i("初始化第三方库");
        if (getAppConfig().saveMicroServerByMMKV()) {
            MMKVUtil.initMMKV();
        }
        OOMHelper.create().init(this);
        return true;
    }

    public boolean isAppKilled() {
        return this.memoryKilled == null;
    }

    public boolean isCPVDEnvironmentProduction() {
        CPVDUser user = CPVDUserData.getUser(this);
        return user == null || user.getEnvironment() == 1;
    }

    @Deprecated
    public boolean isNeedInitLogManager() {
        return true;
    }

    public boolean isUseMicroService() {
        return this.isUseMicroService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(getClass().getSimpleName(), "onCreate is called");
        mMainHandler = new Handler(Looper.getMainLooper());
        MatrixUtil.init(this);
        TinkerPatchUtil.initTinkerPatch(this);
        CPVDManager.init(this, getAppConfig().getAppId());
        initEnvironment();
        initLog();
        LogManager.getInstance().init(this, getUncaughtExceptionCallBack());
        initBaseRequestInfo();
        DataCollectionUtil.listenerAppLifecycle(this, XHAppConfigProxy.getInstance().getIgnoreCheckAppLock());
        ActivityListAdapter.init(this);
        startLogcatService();
        SystemBarTintManager.init(this);
        DensityUtil.initAppDensity(this);
        if (XHAppConfigProxy.getInstance().getWholeLinkEnvironment() != WholeLinkEnvironment.NONE) {
            BuryManager.INSTANCE.getInstance().start();
        }
        BuryStrategyManager.getInstance().init(this, getAppConfig().getBuryConfig());
        registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks() {
            @Override
            public void onActivityResumed(Activity activity) {
                XhBaseApplication.this.initThree();
                PlatformRunningCheckHelper.INSTANCE.checkPlatformRunning(activity);
            }
        });
        initThree();
    }

    public void setUrlRootMap(HashMap<String, String> map) {
        this.mUrlRootMap = map;
    }

    public void setUseMicroService(boolean z) {
        this.isUseMicroService = z;
    }

    public void startLogcatService() {
        LogUtils.setLogLevel(LogLevel.LOG_LEVEL_VERBOSE);
        LogUtils.i("startLogcatService 检测日志打印服务");
        if (LogcatService.isRunning()) {
            LogUtils.i("startLogcatService 日志打印服务正在运行中");
        } else {
            LogUtils.i("startLogcatService 日志打印服务未在运行，启动该服务");
            stopLogcatService();
            try {
                Intent intent = new Intent(mContext, (Class<?>) LogcatService.class);
                this.mLogcatServiceIntent = intent;
                intent.putExtra("maxSize", getLogSize());
                startService(this.mLogcatServiceIntent);
                LogUtils.i("startLogcatService 启动日志打印服务结束");
            } catch (IllegalStateException e) {
                LogcatService.resetRunningState();
                e.printStackTrace();
                LogUtils.e("startLogcatService 日志打印服务启动失败", e);
            }
        }
        LogUtils.i("startLogcatService 检测日志打印服务结束");
    }

    public boolean stopLogcatService() {
        Intent intent = this.mLogcatServiceIntent;
        if (intent != null) {
            return stopService(intent);
        }
        return false;
    }
}

package com.xuehai.launcher;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Build;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.MultiDex;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.cache.ApplicationManager;
import com.xuehai.launcher.common.cache.CacheManager;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.network.NetworkManger;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.util.TraceHelper;
import com.xuehai.launcher.common.util.font.FontReceiver;
import com.xuehai.launcher.receiver.BusinessReceiver;
import com.xuehai.launcher.receiver.InputMethodReceiver;
import com.xuehai.launcher.receiver.MyMonitoringReceiver;
import com.xuehai.launcher.receiver.MyNetworkCallback;
import com.xuehai.launcher.receiver.PackageReceiver;
import com.xuehai.launcher.receiver.ScreenReceiver;
import com.xuehai.launcher.receiver.UsbReceiver;
import com.xuehai.launcher.receiver.WifiCompat;
import com.xuehai.provider.constants.AuthorityUris;
import com.xuehai.provider.log.ContentProviderLog;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.mdm.MdmErrorCheck;
import com.xuehai.system.mdm.MyMdmLogClient;
import com.xuehai.system.mdm.proxy.LicenseReceiver;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.log.ZLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\b\u0010\u0016\u001a\u00020\nH\u0016J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0011H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001a"}, d2 = {"Lcom/xuehai/launcher/App;", "Lcom/xuehai/launcher/common/base/BaseApplication;", "()V", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "getNetworkCallback", "()Landroid/net/ConnectivityManager$NetworkCallback;", "setNetworkCallback", "(Landroid/net/ConnectivityManager$NetworkCallback;)V", "attachBaseContext", "", "base", "Landroid/content/Context;", "doOnMainProcess", "getVersion", "", "getVersionCode", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "onLowMemory", "onTrimMemory", "level", "Companion", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class App extends BaseApplication {

    public static final Companion INSTANCE = new Companion(null);
    private ConnectivityManager.NetworkCallback networkCallback;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/xuehai/launcher/App$Companion;", "", "()V", "getInstance", "Lcom/xuehai/launcher/App;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final App getInstance() {
            return (App) BaseApplication.INSTANCE.getInstance();
        }
    }

    public App() {
        this.networkCallback = Build.VERSION.SDK_INT >= 21 ? new MyNetworkCallback() : (ConnectivityManager.NetworkCallback) null;
    }

    @Override
    protected void attachBaseContext(Context base) {
        Intrinsics.checkNotNullParameter(base, "base");
        AuthorityUris.setAuthorityUris("com.xuehai.provider.default");
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void doOnMainProcess() {
        super.doOnMainProcess();
        App app = this;
        LocalBroadcastManager.getInstance(app).registerReceiver(new LicenseReceiver(), LicenseReceiver.INSTANCE.createIntentFilter());
        new MyMonitoringReceiver().register(app);
        registerReceiver(new PackageReceiver(), PackageReceiver.createIntentFilter());
        registerReceiver(new UsbReceiver(), UsbReceiver.INSTANCE.createIntentFilter());
        registerReceiver(new BusinessReceiver(), BusinessReceiver.INSTANCE.createIntentFilter());
        registerReceiver(new InputMethodReceiver(), InputMethodReceiver.INSTANCE.createIntentFilter());
        registerReceiver(new ScreenReceiver(), ScreenReceiver.INSTANCE.createIntentFilter());
        FontReceiver.INSTANCE.register(app);
        WifiCompat.INSTANCE.listener(this.networkCallback);
        DeviceActiveManager.INSTANCE.init(app);
        MdmErrorCheck.INSTANCE.observerMdmError();
    }

    public final ConnectivityManager.NetworkCallback getNetworkCallback() {
        return this.networkCallback;
    }

    @Override
    public String getVersion() {
        return "v1.21.06.20251212hwS";
    }

    @Override
    public int getVersionCode() {
        return 1021006;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        ApplicationManager.INSTANCE.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        TraceHelper.beginSection("App onCreate");
        LogUtils.Companion companion = LogUtils.INSTANCE;
        String name = MyLog.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "MyLog::class.java.name");
        companion.registerLogClass(name);
        LogUtils.Companion companion2 = LogUtils.INSTANCE;
        String name2 = MdmLog.class.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "MdmLog::class.java.name");
        companion2.registerLogClass(name2);
        LogUtils.Companion companion3 = LogUtils.INSTANCE;
        String name3 = MyMdmLogClient.class.getName();
        Intrinsics.checkNotNullExpressionValue(name3, "MyMdmLogClient::class.java.name");
        companion3.registerLogClass(name3);
        LogUtils.Companion companion4 = LogUtils.INSTANCE;
        String name4 = ContentProviderLog.class.getName();
        Intrinsics.checkNotNullExpressionValue(name4, "ContentProviderLog::class.java.name");
        companion4.registerLogClass(name4);
        ZLog.closeAllLog();
        super.onCreate();
        PolicyManager.getDevicePolicyProxy().initEnvironment();
        MdmLog.INSTANCE.setLogClient(new MyMdmLogClient());
        NetworkManger.INSTANCE.refreshNetwork();
        TraceHelper.endSection("App onCreate");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        MyLog.d("Debug[MDM]", "onLowMemory");
        CacheManager.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        CacheManager.onTrimMemory(level);
    }

    public final void setNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }
}

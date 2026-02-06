package com.xuehai.launcher.wifi;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.lifecycle.MutableLiveData;
import com.xuehai.launcher.common.base.AbsAndroidViewModel;
import com.xuehai.launcher.common.base.AbsViewModel;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.plugins.rx.MyObserver;
import com.xuehai.launcher.common.util.AppInstallManager;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.launcher.common.util.SettingsHelper;
import com.xuehai.launcher.common.util.ZOptional;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import com.xuehai.launcher.util.WifiClientUtil;
import com.xuehai.system.mdm.proxy.ApplicationPolicyProxy;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.AppUtil;
import com.zaze.utils.FileUtil;
import com.zaze.utils.ZCommand;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0017H\u0002J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u0017H\u0002J\u0006\u0010\u0019\u001a\u00020\u0010J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\u0006\u0010\u001b\u001a\u00020\u0010J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0002J\u0006\u0010\u001e\u001a\u00020\u0010J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010 \u001a\u00020\u0010J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006#"}, d2 = {"Lcom/xuehai/launcher/wifi/WifiViewModel;", "Lcom/xuehai/launcher/common/base/AbsAndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "needAuth", "", "useXueHaiWifi", "wifiBtnRes", "Landroidx/lifecycle/MutableLiveData;", "", "getWifiBtnRes", "()Landroidx/lifecycle/MutableLiveData;", "wifiStepState", "getWifiStepState", "applyRuntimePermission", "", "packageName", "", "authenticationNetwork", "authenticationNetworkBySettings", "authenticationWifiManagerNetwork", "installWifiManager", "Lio/reactivex/Observable;", "installXhSettings", "loadDefault", "openWifiManagerSetting", "openWifiSetting", "openXhSetting", "openXhSettingWifi", "selectWifi", "setRuntimePermissionFix", "startSettings", "updateWifiStepState", "isSelected", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiViewModel extends AbsAndroidViewModel {
    private final boolean needAuth;
    private final boolean useXueHaiWifi;
    private final MutableLiveData<Integer> wifiBtnRes;
    private final MutableLiveData<Boolean> wifiStepState;

    public WifiViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.wifiStepState = new MutableLiveData<>();
        this.wifiBtnRes = new MutableLiveData<>();
        this.needAuth = ClientConfig.INSTANCE.isStudentClient() && DeviceModelUtil.INSTANCE.isCustomSettingDevice() && !DeviceModelUtil.INSTANCE.isUseXhSettingsDevice();
        this.useXueHaiWifi = ClientConfig.INSTANCE.isStudentClient() && !DeviceModelUtil.INSTANCE.isCustomSettingDevice() && DeviceModelUtil.INSTANCE.isUseXhSettingsDevice();
    }

    private final void applyRuntimePermission(String packageName) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = getApplication().getPackageManager().getPackageInfo(packageName, 4096);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "getApplication<Applicati…ISSIONS\n                )");
            ApplicationPolicyProxy applicationPolicyProxy = PolicyManager.getApplicationPolicyProxy();
            String[] strArr = packageInfo.requestedPermissions;
            Intrinsics.checkNotNullExpressionValue(strArr, "packageInfo.requestedPermissions");
            applicationPolicyProxy.applyRuntimePermissions(packageName, CollectionsKt.listOf(Arrays.copyOf(strArr, strArr.length)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void authenticationNetworkBySettings() {
        if (!SessionData.INSTANCE.isOnline()) {
            toastMessage("请先进行WiFi连接");
            return;
        }
        updateWifiStepState(false);
        Observable map = Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return WifiViewModel.m186authenticationNetworkBySettings$lambda16(this.f$0);
            }
        }).subscribeOn(ThreadPlugins.ioScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiViewModel.m187authenticationNetworkBySettings$lambda17(this.f$0, (ZOptional) obj);
            }
        });
        final Function1<Boolean, ObservableSource<? extends Object>> function1 = new Function1<Boolean, ObservableSource<? extends Object>>() {
            {
                super(1);
            }

            @Override
            public final ObservableSource<? extends Object> invoke(Boolean bool) {
                Intrinsics.checkNotNullParameter(bool, "isInstalled");
                return bool.booleanValue() ? WifiViewModel.this.installXhSettings() : Observable.just(bool);
            }
        };
        map.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiViewModel.m188authenticationNetworkBySettings$lambda18(function1, (Boolean) obj);
            }
        }).doFinally(new Action() {
            @Override
            public final void run() {
                WifiViewModel.m189authenticationNetworkBySettings$lambda20(this.f$0);
            }
        }).subscribe(new MyObserver(getCompositeDisposable()));
    }

    private static final ZOptional m186authenticationNetworkBySettings$lambda16(WifiViewModel wifiViewModel) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        return ZOptional.ofNullable(AppUtil.getPackageInfo$default(wifiViewModel.getApplication(), "com.xuehai.settings", 0, 4, (Object) null));
    }

    private static final Boolean m187authenticationNetworkBySettings$lambda17(WifiViewModel wifiViewModel, ZOptional zOptional) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        Intrinsics.checkNotNullParameter(zOptional, "it");
        boolean z = true;
        if (zOptional.isPresent()) {
            PackageInfo packageArchiveInfo = AppUtil.getPackageArchiveInfo(wifiViewModel.getApplication(), WifiClientUtil.INSTANCE.getLocalPathSettings());
            if (packageArchiveInfo == null || packageArchiveInfo.versionCode <= ((PackageInfo) zOptional.get()).versionCode) {
                z = false;
            } else {
                MyLog.w("Debug[MDM]", "已安装版本过低,需要更新!! 开始安装内置版本");
                PolicyManager.getApplicationPolicyProxy().uninstallApplication("com.xuehai.settings");
            }
        }
        return Boolean.valueOf(z);
    }

    private static final ObservableSource m188authenticationNetworkBySettings$lambda18(Function1 function1, Boolean bool) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        return (ObservableSource) function1.invoke(bool);
    }

    private static final void m189authenticationNetworkBySettings$lambda20(final WifiViewModel wifiViewModel) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.setRuntimePermissionFix("com.xuehai.settings");
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() throws PackageManager.NameNotFoundException {
                WifiViewModel.m190authenticationNetworkBySettings$lambda20$lambda19(this.f$0);
            }
        }, DeviceModelUtil.isHuaweiHEMDevice() ? 5000L : 1000L);
    }

    private static final void m190authenticationNetworkBySettings$lambda20$lambda19(WifiViewModel wifiViewModel) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.applyRuntimePermission("com.xuehai.settings");
        WifiClientUtil.INSTANCE.authenticationNetworkBySettings(wifiViewModel.getApplication());
        AbsViewModel.hideProgress$default(wifiViewModel, 0L, 1, null);
    }

    private final void authenticationWifiManagerNetwork() {
        if (!SessionData.INSTANCE.isOnline()) {
            toastMessage("请先进行WiFi连接");
            return;
        }
        updateWifiStepState(false);
        Observable map = Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return WifiViewModel.m191authenticationWifiManagerNetwork$lambda11(this.f$0);
            }
        }).subscribeOn(ThreadPlugins.ioScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiViewModel.m192authenticationWifiManagerNetwork$lambda12(this.f$0, (ZOptional) obj);
            }
        });
        final Function1<Boolean, ObservableSource<? extends Object>> function1 = new Function1<Boolean, ObservableSource<? extends Object>>() {
            {
                super(1);
            }

            @Override
            public final ObservableSource<? extends Object> invoke(Boolean bool) {
                Intrinsics.checkNotNullParameter(bool, "isInstalled");
                return bool.booleanValue() ? WifiViewModel.this.installWifiManager() : Observable.just(bool);
            }
        };
        map.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiViewModel.m193authenticationWifiManagerNetwork$lambda13(function1, (Boolean) obj);
            }
        }).doFinally(new Action() {
            @Override
            public final void run() {
                WifiViewModel.m194authenticationWifiManagerNetwork$lambda15(this.f$0);
            }
        }).subscribe(new MyObserver(getCompositeDisposable()));
    }

    private static final ZOptional m191authenticationWifiManagerNetwork$lambda11(WifiViewModel wifiViewModel) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        return ZOptional.ofNullable(AppUtil.getPackageInfo$default(wifiViewModel.getApplication(), "com.zhitongyun.wifimanager", 0, 4, (Object) null));
    }

    private static final Boolean m192authenticationWifiManagerNetwork$lambda12(WifiViewModel wifiViewModel, ZOptional zOptional) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        Intrinsics.checkNotNullParameter(zOptional, "it");
        boolean z = true;
        if (zOptional.isPresent()) {
            PackageInfo packageArchiveInfo = AppUtil.getPackageArchiveInfo(wifiViewModel.getApplication(), WifiClientUtil.INSTANCE.getLocalPath());
            if (packageArchiveInfo == null || packageArchiveInfo.versionCode <= ((PackageInfo) zOptional.get()).versionCode) {
                z = false;
            } else {
                MyLog.w("Debug[MDM]", "已安装版本过低,需要更新!! 开始安装内置版本");
                PolicyManager.getApplicationPolicyProxy().uninstallApplication("com.zhitongyun.wifimanager");
            }
        }
        return Boolean.valueOf(z);
    }

    private static final ObservableSource m193authenticationWifiManagerNetwork$lambda13(Function1 function1, Boolean bool) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        return (ObservableSource) function1.invoke(bool);
    }

    private static final void m194authenticationWifiManagerNetwork$lambda15(final WifiViewModel wifiViewModel) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.setRuntimePermissionFix("com.zhitongyun.wifimanager");
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() throws PackageManager.NameNotFoundException {
                WifiViewModel.m195authenticationWifiManagerNetwork$lambda15$lambda14(this.f$0);
            }
        }, DeviceModelUtil.isHuaweiHEMDevice() ? 5000L : 1000L);
    }

    private static final void m195authenticationWifiManagerNetwork$lambda15$lambda14(WifiViewModel wifiViewModel) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.applyRuntimePermission("com.zhitongyun.wifimanager");
        WifiClientUtil.INSTANCE.authenticationNetwork(wifiViewModel.getApplication());
        AbsViewModel.hideProgress$default(wifiViewModel, 0L, 1, null);
    }

    private final Observable<String> installWifiManager() {
        showProgress("正在更新网络连接...");
        Observable map = WifiClientUtil.INSTANCE.releaseWifiManagerApk(getApplication()).observeOn(ThreadPlugins.installScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiViewModel.m196installWifiManager$lambda9((String) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "WifiClientUtil.releaseWi…  localPath\n            }");
        return map;
    }

    private static final String m196installWifiManager$lambda9(final String str) {
        Intrinsics.checkNotNullParameter(str, "localPath");
        if (ZCommand.isRoot()) {
            MyLog.d("Debug[MDM]", "Root静默安装 " + str);
            AppUtil.installApkSilent(str);
        } else {
            MyLog.d("Debug[MDM]", "安装 " + str);
            AppInstallManager.INSTANCE.addToInstallPool(new AppInstallManager.AppUpdateTask("com.zhitongyun.wifimanager", str, new Function1<AppInstallManager.InstallError, Unit>() {
                {
                    super(1);
                }

                @Override
                public Unit invoke(AppInstallManager.InstallError installError) {
                    invoke2(installError);
                    return Unit.INSTANCE;
                }

                public final void invoke2(AppInstallManager.InstallError installError) {
                    Intrinsics.checkNotNullParameter(installError, "it");
                    if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.ApkFileError.INSTANCE)) {
                        FileUtil.deleteFile(new File(str));
                        return;
                    }
                    if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.InstalledError.INSTANCE) || Intrinsics.areEqual(installError, AppInstallManager.InstallError.DowngradeError.INSTANCE) || !Intrinsics.areEqual(installError, AppInstallManager.InstallError.ExecuteError.INSTANCE)) {
                        return;
                    }
                    ApplicationPolicyProxy applicationPolicyProxy = PolicyManager.getApplicationPolicyProxy();
                    String str2 = str;
                    Intrinsics.checkNotNullExpressionValue(str2, "localPath");
                    applicationPolicyProxy.installApplication(str2);
                }
            }));
        }
        return str;
    }

    private final Observable<String> installXhSettings() {
        showProgress("正在更新设置...");
        Observable map = WifiClientUtil.INSTANCE.releaseXhSettingsApk(getApplication()).observeOn(ThreadPlugins.installScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiViewModel.m197installXhSettings$lambda10((String) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "WifiClientUtil.releaseXh…  localPath\n            }");
        return map;
    }

    private static final String m197installXhSettings$lambda10(final String str) {
        Intrinsics.checkNotNullParameter(str, "localPath");
        if (ZCommand.isRoot()) {
            MyLog.d("Debug[MDM]", "Root静默安装 " + str);
            AppUtil.installApkSilent(str);
        } else {
            MyLog.d("Debug[MDM]", "安装 " + str);
            AppInstallManager.INSTANCE.addToInstallPool(new AppInstallManager.AppUpdateTask("com.xuehai.settings", str, new Function1<AppInstallManager.InstallError, Unit>() {
                {
                    super(1);
                }

                @Override
                public Unit invoke(AppInstallManager.InstallError installError) {
                    invoke2(installError);
                    return Unit.INSTANCE;
                }

                public final void invoke2(AppInstallManager.InstallError installError) {
                    Intrinsics.checkNotNullParameter(installError, "it");
                    if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.ApkFileError.INSTANCE)) {
                        FileUtil.deleteFile(new File(str));
                        return;
                    }
                    if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.InstalledError.INSTANCE) || Intrinsics.areEqual(installError, AppInstallManager.InstallError.DowngradeError.INSTANCE) || !Intrinsics.areEqual(installError, AppInstallManager.InstallError.ExecuteError.INSTANCE)) {
                        return;
                    }
                    ApplicationPolicyProxy applicationPolicyProxy = PolicyManager.getApplicationPolicyProxy();
                    String str2 = str;
                    Intrinsics.checkNotNullExpressionValue(str2, "localPath");
                    applicationPolicyProxy.installApplication(str2);
                }
            }));
        }
        return str;
    }

    private final void openWifiManagerSetting() throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        if (!this.useXueHaiWifi && !Intrinsics.areEqual(Build.MODEL, "SM-P355C")) {
            SettingsHelper.openWifiSettings(getApplication());
            return;
        }
        try {
            applicationInfo = getApplication().getPackageManager().getApplicationInfo("com.zhitongyun.wifimanager", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            MyLog.e("Error[MDM]", "网络连接未安装(com.zhitongyun.wifimanager)");
            applicationInfo = (ApplicationInfo) null;
        }
        if (applicationInfo == null) {
            installWifiManager().doFinally(new Action() {
                @Override
                public final void run() {
                    WifiViewModel.m204openWifiManagerSetting$lambda2(this.f$0);
                }
            }).subscribe(new MyObserver(getCompositeDisposable()));
            return;
        }
        applyRuntimePermission("com.zhitongyun.wifimanager");
        setRuntimePermissionFix("com.zhitongyun.wifimanager");
        ApplicationUtil.startApp$default(ApplicationUtil.INSTANCE, getApplication(), "com.zhitongyun.wifimanager", 0, 4, (Object) null);
    }

    private static final void m204openWifiManagerSetting$lambda2(final WifiViewModel wifiViewModel) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.setRuntimePermissionFix("com.zhitongyun.wifimanager");
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() throws PackageManager.NameNotFoundException {
                WifiViewModel.m205openWifiManagerSetting$lambda2$lambda1(this.f$0);
            }
        }, DeviceModelUtil.isHuaweiHEMDevice() ? 5000L : 1000L);
    }

    private static final void m205openWifiManagerSetting$lambda2$lambda1(WifiViewModel wifiViewModel) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.applyRuntimePermission("com.zhitongyun.wifimanager");
        ApplicationUtil.startApp$default(ApplicationUtil.INSTANCE, wifiViewModel.getApplication(), "com.zhitongyun.wifimanager", 0, 4, (Object) null);
        AbsViewModel.hideProgress$default(wifiViewModel, 0L, 1, null);
    }

    private final void openXhSetting() throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = getApplication().getPackageManager().getApplicationInfo("com.xuehai.settings", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            MyLog.e("Error[MDM]", "设置未安装(com.xuehai.settings)");
            applicationInfo = (ApplicationInfo) null;
        }
        if (applicationInfo == null) {
            installXhSettings().doFinally(new Action() {
                @Override
                public final void run() {
                    WifiViewModel.m206openXhSetting$lambda5(this.f$0);
                }
            }).subscribe(new MyObserver(getCompositeDisposable()));
            return;
        }
        applyRuntimePermission("com.xuehai.settings");
        setRuntimePermissionFix("com.xuehai.settings");
        ApplicationUtil.startApp$default(ApplicationUtil.INSTANCE, getApplication(), "com.xuehai.settings", 0, 4, (Object) null);
    }

    private static final void m206openXhSetting$lambda5(final WifiViewModel wifiViewModel) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.setRuntimePermissionFix("com.xuehai.settings");
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() throws PackageManager.NameNotFoundException {
                WifiViewModel.m207openXhSetting$lambda5$lambda4(this.f$0);
            }
        }, DeviceModelUtil.isHuaweiHEMDevice() ? 5000L : 1000L);
    }

    private static final void m207openXhSetting$lambda5$lambda4(WifiViewModel wifiViewModel) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.applyRuntimePermission("com.xuehai.settings");
        ApplicationUtil.startApp$default(ApplicationUtil.INSTANCE, wifiViewModel.getApplication(), "com.xuehai.settings", 0, 4, (Object) null);
        AbsViewModel.hideProgress$default(wifiViewModel, 0L, 1, null);
    }

    private final void openXhSettingWifi() throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        Unit unit = null;
        try {
            applicationInfo = getApplication().getPackageManager().getApplicationInfo("com.xuehai.settings", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            MyLog.e("Error[MDM]", "设置未安装(com.xuehai.settings)");
            applicationInfo = (ApplicationInfo) null;
        }
        if (applicationInfo != null) {
            applyRuntimePermission("com.xuehai.settings");
            setRuntimePermissionFix("com.xuehai.settings");
            if (!WifiClientUtil.INSTANCE.openWlanNetworkBySettings(getApplication())) {
                ApplicationUtil.startApp$default(ApplicationUtil.INSTANCE, getApplication(), "com.xuehai.settings", 0, 4, (Object) null);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            installXhSettings().doFinally(new Action() {
                @Override
                public final void run() {
                    WifiViewModel.m208openXhSettingWifi$lambda8(this.f$0);
                }
            }).subscribe(new MyObserver(getCompositeDisposable()));
        }
    }

    private static final void m208openXhSettingWifi$lambda8(final WifiViewModel wifiViewModel) {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.setRuntimePermissionFix("com.xuehai.settings");
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() throws PackageManager.NameNotFoundException {
                WifiViewModel.m209openXhSettingWifi$lambda8$lambda7(this.f$0);
            }
        }, DeviceModelUtil.isHuaweiHEMDevice() ? 5000L : 1000L);
    }

    private static final void m209openXhSettingWifi$lambda8$lambda7(WifiViewModel wifiViewModel) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(wifiViewModel, "this$0");
        wifiViewModel.applyRuntimePermission("com.xuehai.settings");
        if (!WifiClientUtil.INSTANCE.openWlanNetworkBySettings(wifiViewModel.getApplication())) {
            ApplicationUtil.startApp$default(ApplicationUtil.INSTANCE, wifiViewModel.getApplication(), "com.xuehai.settings", 0, 4, (Object) null);
        }
        AbsViewModel.hideProgress$default(wifiViewModel, 0L, 1, null);
    }

    private final void setRuntimePermissionFix(String packageName) {
        if (ClientConfig.INSTANCE.isStudentClient() && DeviceModelUtil.isHuaweiHEMDevice()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(packageName);
            PolicyManager.getApplicationPolicyProxy().addRuntimePermissionFixAppList(arrayList);
        }
    }

    private final void updateWifiStepState(boolean isSelected) {
        if (this.needAuth) {
            LiveDataExtKt.set(this.wifiStepState, Boolean.valueOf(isSelected));
            LiveDataExtKt.set(this.wifiBtnRes, Integer.valueOf(isSelected ? 2131558422 : 2131558421));
        }
    }

    public final void authenticationNetwork() {
        if (WifiClientUtil.isUseXhSettings()) {
            authenticationNetworkBySettings();
        } else {
            authenticationWifiManagerNetwork();
        }
    }

    public final MutableLiveData<Integer> getWifiBtnRes() {
        return this.wifiBtnRes;
    }

    public final MutableLiveData<Boolean> getWifiStepState() {
        return this.wifiStepState;
    }

    public final void loadDefault() {
        updateWifiStepState(false);
    }

    public final void openWifiSetting() throws PackageManager.NameNotFoundException {
        updateWifiStepState(false);
        if (WifiClientUtil.isUseXhSettings()) {
            openXhSettingWifi();
        } else {
            openWifiManagerSetting();
        }
    }

    public final void selectWifi() throws PackageManager.NameNotFoundException {
        if (!this.needAuth) {
            openWifiSetting();
            return;
        }
        Boolean value = this.wifiStepState.getValue();
        if (value == null) {
            value = false;
        }
        updateWifiStepState(!value.booleanValue());
    }

    public final void startSettings() throws PackageManager.NameNotFoundException {
        if (WifiClientUtil.isUseXhSettings()) {
            openXhSetting();
        }
    }
}

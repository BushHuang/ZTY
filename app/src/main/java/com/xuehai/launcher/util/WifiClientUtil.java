package com.xuehai.launcher.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.ActivityUtil;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.launcher.common.util.EncryptionUtil;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import com.zaze.utils.FileUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0007J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00192\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00192\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/xuehai/launcher/util/WifiClientUtil;", "", "()V", "SETTINGS", "", "SETTINGS_WLAN_ACTION", "SETTINGS_WLAN_AUTH_ACTION", "WIFI", "assetsApk", "assetsApkSettings", "localPath", "getLocalPath", "()Ljava/lang/String;", "localPathSettings", "getLocalPathSettings", "authenticationNetwork", "", "context", "Landroid/content/Context;", "authenticationNetworkBySettings", "checkLocalFile", "checkLocalFileSettings", "isUseXhSettings", "openWlanNetworkBySettings", "releaseWifiManagerApk", "Lio/reactivex/Observable;", "releaseXhSettingsApk", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WifiClientUtil {
    public static final String SETTINGS = "com.xuehai.settings";
    public static final String SETTINGS_WLAN_ACTION = "com.xuehai.settings.wlan";
    public static final String SETTINGS_WLAN_AUTH_ACTION = "com.xuehai.settings.wlan.auth";
    public static final String WIFI = "com.zhitongyun.wifimanager";
    private static final String assetsApk = "apk/wifimanager.apk";
    private static final String assetsApkSettings = "apk/xhsettings.apk";
    public static final WifiClientUtil INSTANCE = new WifiClientUtil();
    private static final String localPath = FilePath.getBakPath() + "/wifimanager.apk";
    private static final String localPathSettings = FilePath.getBakPath() + "/xhsettings.apk";

    private WifiClientUtil() {
    }

    private final boolean checkLocalFile(Context context) {
        MyLog.i("Debug[MDM]", "校验本地已拷贝的apk：" + localPath);
        File file = new File(localPath);
        if (!file.exists()) {
            MyLog.i("Debug[MDM]", "不存在已拷贝的apk：" + localPath);
            return false;
        }
        boolean zEquals = TextUtils.equals(EncryptionUtil.getMD5FromStream(context.getAssets().open("apk/wifimanager.apk")), EncryptionUtil.getMD5FromFile(file));
        if (!zEquals) {
            MyLog.i("Debug[MDM]", "md5不匹配,删除本地apk：" + localPath);
            FileUtil.deleteFile(file);
        }
        return zEquals;
    }

    private final boolean checkLocalFileSettings(Context context) {
        MyLog.i("Debug[MDM]", "校验本地已拷贝的apk：" + localPathSettings);
        File file = new File(localPathSettings);
        if (!file.exists()) {
            MyLog.i("Debug[MDM]", "不存在已拷贝的apk：" + localPathSettings);
            return false;
        }
        boolean zEquals = TextUtils.equals(EncryptionUtil.getMD5FromStream(context.getAssets().open("apk/xhsettings.apk")), EncryptionUtil.getMD5FromFile(file));
        if (!zEquals) {
            MyLog.i("Debug[MDM]", "md5不匹配,删除本地apk：" + localPathSettings);
            FileUtil.deleteFile(file);
        }
        return zEquals;
    }

    @JvmStatic
    public static final boolean isUseXhSettings() {
        if (!ClientConfig.INSTANCE.isStudentClient()) {
            return false;
        }
        if (DeviceModelUtil.isLenovoDevice() || DeviceModelUtil.isHuaweiHEMDevice()) {
            return true;
        }
        return DeviceModelUtil.INSTANCE.isUseXhSettingsDevice();
    }

    private static final void m173releaseWifiManagerApk$lambda2(Context context, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(observableEmitter, "it");
        observableEmitter.onNext(Boolean.valueOf(INSTANCE.checkLocalFile(context)));
        observableEmitter.onComplete();
    }

    private static final String m174releaseWifiManagerApk$lambda3(Context context, Boolean bool) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(bool, "enable");
        MyLog.i("Debug[MDM]", "从资源文件中释放apk：apk/wifimanager.apk");
        if (!bool.booleanValue()) {
            ApplicationUtil.INSTANCE.readAssetsApkToFile(context, "apk/wifimanager.apk", localPath);
        }
        return localPath;
    }

    private static final void m175releaseXhSettingsApk$lambda4(Context context, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(observableEmitter, "it");
        observableEmitter.onNext(Boolean.valueOf(INSTANCE.checkLocalFileSettings(context)));
        observableEmitter.onComplete();
    }

    private static final String m176releaseXhSettingsApk$lambda5(Context context, Boolean bool) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(bool, "enable");
        MyLog.i("Debug[MDM]", "从资源文件中释放apk：apk/xhsettings.apk");
        if (!bool.booleanValue()) {
            ApplicationUtil.INSTANCE.readAssetsApkToFile(context, "apk/xhsettings.apk", localPathSettings);
        }
        return localPathSettings;
    }

    public final boolean authenticationNetwork(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            MyLog.i("Debug[MDM]", "开始认证网络");
            ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, context, new Intent("android.intent.xh.wifimanager.auth"), 0, 4, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.e("Debug[MDM]", "打开认证网络界面失败!");
            return false;
        }
    }

    public final boolean authenticationNetworkBySettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            MyLog.i("Debug[MDM]", "开始认证网络");
            ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, context, new Intent("com.xuehai.settings.wlan.auth"), 0, 4, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.e("Debug[MDM]", "打开认证网络界面失败!");
            return false;
        }
    }

    public final String getLocalPath() {
        return localPath;
    }

    public final String getLocalPathSettings() {
        return localPathSettings;
    }

    public final boolean openWlanNetworkBySettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            MyLog.i("Debug[MDM]", "打开Wlan设置界面");
            ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, context, new Intent("com.xuehai.settings.wlan"), 0, 4, null);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            MyLog.e("Debug[MDM]", "打开Wlan设置界面失败!");
            return false;
        }
    }

    public final Observable<String> releaseWifiManagerApk(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Observable<String> map = Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                WifiClientUtil.m173releaseWifiManagerApk$lambda2(context, observableEmitter);
            }
        }).subscribeOn(ThreadPlugins.ioScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiClientUtil.m174releaseWifiManagerApk$lambda3(context, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "create<Boolean> {\n      …      localPath\n        }");
        return map;
    }

    public final Observable<String> releaseXhSettingsApk(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Observable<String> map = Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                WifiClientUtil.m175releaseXhSettingsApk$lambda4(context, observableEmitter);
            }
        }).subscribeOn(ThreadPlugins.ioScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return WifiClientUtil.m176releaseXhSettingsApk$lambda5(context, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "create<Boolean> {\n      …calPathSettings\n        }");
        return map;
    }
}

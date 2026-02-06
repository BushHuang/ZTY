package com.xuehai.launcher.common.util;

import android.app.Activity;
import android.content.Context;
import androidx.lifecycle.Observer;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.http.NetStore;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.network.NetworkManger;
import com.xuehai.launcher.common.network.NetworkType;
import com.xuehai.launcher.common.plugins.rx.MyObserver;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import com.xuehai.mdm.config.DeviceSupport;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.mdm.device.DeviceManager;
import com.xuehai.system.mdm.proxy.PolicyManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fJ\u0006\u0010\u0011\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/common/util/DeviceActiveManager;", "", "()V", "REQUEST_CODE", "", "activateSDK", "", "addDeviceAdmin", "", "context", "Landroid/app/Activity;", "autoActive", "Landroid/content/Context;", "init", "isAdminActive", "isAdminActiveInSystem", "isDeviceActivated", "isSDKActivated", "removeDeviceAdmin", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceActiveManager {
    public static final DeviceActiveManager INSTANCE = new DeviceActiveManager();
    public static final int REQUEST_CODE = 9;

    private DeviceActiveManager() {
    }

    private final void autoActive(Context context) {
        ApplicationUtil applicationUtil = ApplicationUtil.INSTANCE;
        String packageName = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        ApplicationUtil.startApp$default(applicationUtil, context, packageName, 0, 4, (Object) null);
    }

    private static final void m84init$lambda0(final Context context, NetworkType networkType) {
        Intrinsics.checkNotNullParameter(context, "$context");
        boolean z = false;
        if (networkType != null && !networkType.isEnable()) {
            z = true;
        }
        if (z || INSTANCE.isDeviceActivated(context)) {
            return;
        }
        MyLog.i("Network[MDM]", "网络变为可用，设备未激活, 自动校验系统版本!!");
        NetStore.INSTANCE.checkOSVersion().subscribe(new MyObserver<Boolean>() {
            {
                super(null, 1, null);
            }

            @Override
            public void onNext(Object obj) {
                onNext(((Boolean) obj).booleanValue());
            }

            public void onNext(boolean result) {
                super.onNext((DeviceActiveManager$init$1$1) Boolean.valueOf(result));
                if (result) {
                    DeviceActiveManager.INSTANCE.autoActive(context);
                }
            }
        });
    }

    public final void activateSDK() {
        PolicyManager.getLicensePolicyProxy().activateFreeLicense();
    }

    public final boolean addDeviceAdmin(Activity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceManager.INSTANCE.addDeviceAdmin(context, 9);
    }

    public final void init(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (ClientConfig.INSTANCE.isTeacherClient() || new DeviceSupport(context).isLenovoSupport()) {
            return;
        }
        NetworkManger.INSTANCE.getNetworkData().observeForever(new Observer() {
            @Override
            public final void onChanged(Object obj) {
                DeviceActiveManager.m84init$lambda0(context, (NetworkType) obj);
            }
        });
    }

    public final boolean isAdminActive(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!ApplicationUtil.isAppInstalled("com.android.settings")) {
            MdmLog.e("DeviceManager", "没有找到系统设置 : com.android.settings");
            return true;
        }
        if (new DeviceSupport(context).isDevicePolicyServiceSupport()) {
            return isAdminActiveInSystem(context);
        }
        MdmLog.i("DeviceManager", "无需激活设备管理器");
        return true;
    }

    public final boolean isAdminActiveInSystem(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceManager.INSTANCE.isAdminActiveInSystem(context);
    }

    public final boolean isDeviceActivated(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isAdminActive(context) && isSDKActivated();
    }

    public final boolean isSDKActivated() {
        return PolicyManager.getLicensePolicyProxy().isFreeLicenseActivated();
    }

    public final void removeDeviceAdmin(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PolicyManager.getLicensePolicyProxy().unActivateFreeLicense();
        PolicyManager.getLicensePolicyProxy().unActivatePayLicense();
        PolicyManager.getDevicePolicyProxy().setAdminRemovable(true);
        DeviceManager.removeDeviceAdmin(context);
    }
}

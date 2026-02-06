package com.xuehai.system.huawei_c5;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.huawei.android.app.admin.DeviceControlManager;
import com.huawei.android.app.admin.DevicePackageManager;
import com.xuehai.system.common.compat.DevicePolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import java.util.Collections;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xuehai/system/huawei_c5/DevicePolicyHWHEM;", "Lcom/xuehai/system/common/compat/DevicePolicyDefault;", "context", "Landroid/content/Context;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/Context;Landroid/content/ComponentName;)V", "getDeviceMacAddress", "", "getSerialNumber", "reboot", "", "setAdminRemovable", "", "removable", "shoutdown", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePolicyHWHEM extends DevicePolicyDefault {
    private static final String TAG = "DevicePolicyHWHEM";
    private final ComponentName componentName;

    public DevicePolicyHWHEM(Context context, ComponentName componentName) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.componentName = componentName;
    }

    @Override
    public String getDeviceMacAddress() {
        Object systemService = getContext().getApplicationContext().getSystemService("device_policy");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
        }
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) systemService;
        String str = null;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                String wifiMacAddress = devicePolicyManager.getWifiMacAddress(this.componentName);
                if (wifiMacAddress != null) {
                    String upperCase = wifiMacAddress.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    str = upperCase;
                }
            } catch (Throwable th) {
                MdmLog.e("DevicePolicyHWHEM", "获取华为设备MacAddress 发生异常！", th);
                str = (String) null;
            }
        } else {
            str = (String) null;
        }
        String str2 = str;
        return str2 == null || str2.length() == 0 ? super.getDeviceMacAddress() : str;
    }

    @Override
    public String getSerialNumber() {
        String serialNumber;
        if (ActivityCompat.checkSelfPermission(getContext(), "android.permission.READ_PHONE_STATE") != 0) {
            MdmLog.e("DevicePolicyHWHEM", "获取设备号，没有[READ_PHONE_STATE]电话权限");
            serialNumber = "";
        } else if (Build.VERSION.SDK_INT >= 26) {
            serialNumber = Build.getSerial();
            String str = serialNumber;
            if ((str == null || str.length() == 0) || TextUtils.equals(str, "unknown")) {
                serialNumber = Build.SERIAL;
            }
        } else {
            serialNumber = Build.SERIAL;
        }
        String str2 = serialNumber;
        if ((str2 == null || str2.length() == 0) || TextUtils.equals(str2, "unknown")) {
            serialNumber = super.getSerialNumber();
        }
        Intrinsics.checkNotNullExpressionValue(serialNumber, "strSerial");
        return serialNumber;
    }

    @Override
    public void reboot() {
        new DeviceControlManager().rebootDevice(this.componentName);
    }

    @Override
    public boolean setAdminRemovable(boolean removable) {
        DevicePackageManager devicePackageManager = new DevicePackageManager();
        if (removable) {
            devicePackageManager.removeDisabledDeactivateMdmPackages(this.componentName, Collections.singletonList(getContext().getPackageName()));
            return true;
        }
        devicePackageManager.addDisabledDeactivateMdmPackages(this.componentName, Collections.singletonList(getContext().getPackageName()));
        return true;
    }

    @Override
    public void shoutdown() {
        new DeviceControlManager().shutdownDevice(this.componentName);
    }
}

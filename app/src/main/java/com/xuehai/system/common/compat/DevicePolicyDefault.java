package com.xuehai.system.common.compat;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.policies.IDevicePolicy;
import com.xuehai.system.common.util.MdmUtil;
import java.io.File;
import java.net.SocketException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\bH\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\bH\u0016J \u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\bH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u001aH\u0016J\b\u0010 \u001a\u00020\u001aH\u0016J\b\u0010!\u001a\u00020\u0010H\u0016J\b\u0010\"\u001a\u00020\u0010H\u0016J\u0018\u0010#\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001aH\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0016J(\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\bH\u0016J\u0018\u00101\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*2\u0006\u0010,\u001a\u00020*H\u0016J\u0010\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\bH\u0016J\b\u00104\u001a\u00020\u0010H\u0016J\u0010\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\u001aH\u0016J\b\u00107\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u00068"}, d2 = {"Lcom/xuehai/system/common/compat/DevicePolicyDefault;", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "addDeviceAdmin", "", "activity", "Landroid/app/Activity;", "componentName", "Landroid/content/ComponentName;", "requestCode", "", "clearBootingAnimation", "", "clearShuttingDownAnimation", "clipSystemWifiDetail", "clip", "controlShowDeprecatedTarget", "show", "debug", "boolean", "enableAccessibility", "packageName", "", "packageClass", "enable", "getDeviceMacAddress", "getImei", "getSerialNumber", "getSimSerialNumber", "initEnvironment", "reboot", "removeDeviceAdmin", "runBugReport", "filePath", "setAdminRemovable", "removable", "setBootingAnimation", "animationFile", "Ljava/io/File;", "loopFile", "soundFile", "delay", "", "setDexDisable", "disable", "setShuttingDownAnimation", "setSpenPointImageState", "state", "shoutdown", "startTcpDump", "outPath", "stopTcpDump", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DevicePolicyDefault implements IDevicePolicy {
    private final Context context;

    public DevicePolicyDefault(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public boolean addDeviceAdmin(Activity activity, ComponentName componentName, int requestCode) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        Intent intent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
        intent.putExtra("android.app.extra.DEVICE_ADMIN", componentName);
        intent.putExtra("android.app.extra.ADD_EXPLANATION", "");
        activity.startActivityForResult(intent, requestCode);
        return true;
    }

    @Override
    public void clearBootingAnimation() {
    }

    @Override
    public void clearShuttingDownAnimation() {
    }

    @Override
    public void clipSystemWifiDetail(boolean clip) {
    }

    @Override
    public boolean controlShowDeprecatedTarget(boolean show) {
        return Build.VERSION.SDK_INT < 29;
    }

    @Override
    public void debug(boolean z) {
    }

    @Override
    public void enableAccessibility(String packageName, String packageClass, boolean enable) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(packageClass, "packageClass");
    }

    public final Context getContext() {
        return this.context;
    }

    @Override
    public String getDeviceMacAddress() throws SocketException {
        String wifiMacAddress = MdmUtil.INSTANCE.getWifiMacAddress();
        return wifiMacAddress == null ? "" : wifiMacAddress;
    }

    @Override
    public String getImei() {
        String imei = MdmUtil.INSTANCE.getImei(this.context);
        return imei == null ? "" : imei;
    }

    @Override
    public String getSerialNumber() {
        return MdmUtil.INSTANCE.getUUID(this.context);
    }

    @Override
    public String getSimSerialNumber() {
        return "";
    }

    @Override
    public void initEnvironment() {
    }

    @Override
    public void reboot() {
        throw new UnSupportException();
    }

    @Override
    public void removeDeviceAdmin(Context context, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        Object systemService = context.getSystemService("device_policy");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
        }
        ((DevicePolicyManager) systemService).removeActiveAdmin(componentName);
    }

    @Override
    public String runBugReport(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        throw new UnSupportException();
    }

    @Override
    public boolean setAdminRemovable(boolean removable) {
        throw new UnSupportException();
    }

    @Override
    public void setBootingAnimation(File animationFile, File loopFile, File soundFile, long delay) {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(loopFile, "loopFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
    }

    @Override
    public boolean setDexDisable(boolean disable) {
        throw new UnSupportException();
    }

    @Override
    public void setShuttingDownAnimation(File animationFile, File soundFile) {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
    }

    @Override
    public boolean setSpenPointImageState(boolean state) {
        throw new UnSupportException();
    }

    @Override
    public void shoutdown() {
        throw new UnSupportException();
    }

    @Override
    public boolean startTcpDump(String outPath) {
        Intrinsics.checkNotNullParameter(outPath, "outPath");
        throw new UnSupportException();
    }

    @Override
    public boolean stopTcpDump() {
        throw new UnSupportException();
    }
}

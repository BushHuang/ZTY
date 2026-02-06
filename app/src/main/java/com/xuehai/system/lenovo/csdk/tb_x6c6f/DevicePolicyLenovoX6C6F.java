package com.xuehai.system.lenovo.csdk.tb_x6c6f;

import android.app.Activity;
import android.app.csdk.CSDKManager;
import android.content.ComponentName;
import android.content.Context;
import com.xuehai.system.common.compat.DevicePolicyDefault;
import com.xuehai.system.lenovo.csdk.LenovoCSDK;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u000eH\u0016J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0016J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0016J(\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0006H\u0016J\u0018\u0010(\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u0010#\u001a\u00020!H\u0016J\u0010\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0016J\b\u0010+\u001a\u00020\u000eH\u0016J\u0010\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0015H\u0016J\b\u0010.\u001a\u00020\u0006H\u0016¨\u0006/"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb_x6c6f/DevicePolicyLenovoX6C6F;", "Lcom/xuehai/system/common/compat/DevicePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addDeviceAdmin", "", "activity", "Landroid/app/Activity;", "componentName", "Landroid/content/ComponentName;", "requestCode", "", "clearBootingAnimation", "", "clearShuttingDownAnimation", "csdkManager", "Landroid/app/csdk/CSDKManager;", "debug", "boolean", "getDeviceMacAddress", "", "getImei", "getSerialNumber", "initEnvironment", "reboot", "removeDeviceAdmin", "runBugReport", "filePath", "setAdminRemovable", "removable", "setBootingAnimation", "animationFile", "Ljava/io/File;", "loopFile", "soundFile", "delay", "", "setDexDisable", "disable", "setShuttingDownAnimation", "setSpenPointImageState", "state", "shoutdown", "startTcpDump", "outPath", "stopTcpDump", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePolicyLenovoX6C6F extends DevicePolicyDefault {
    public DevicePolicyLenovoX6C6F(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final CSDKManager csdkManager() {
        return LenovoCSDK.INSTANCE.getCSDKManager(getContext());
    }

    @Override
    public boolean addDeviceAdmin(Activity activity, ComponentName componentName, int requestCode) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        csdkManager().enableDeviceAdmin(componentName.flattenToString(), true);
        return true;
    }

    @Override
    public void clearBootingAnimation() {
        csdkManager().clearCustomBootAnim();
    }

    @Override
    public void clearShuttingDownAnimation() {
        csdkManager().clearCustomShutAnim();
    }

    @Override
    public void debug(boolean z) {
    }

    @Override
    public String getDeviceMacAddress() {
        String deviceInfo = csdkManager().getDeviceInfo(1);
        return deviceInfo == null ? "" : deviceInfo;
    }

    @Override
    public String getImei() {
        String deviceInfo = csdkManager().getDeviceInfo(4);
        return deviceInfo == null ? "" : deviceInfo;
    }

    @Override
    public String getSerialNumber() {
        String deviceInfo = csdkManager().getDeviceInfo(2);
        return deviceInfo == null ? "" : deviceInfo;
    }

    @Override
    public void initEnvironment() {
        csdkManager().enableWifiCaptiveV3(false);
    }

    @Override
    public void reboot() {
        csdkManager().rebootDevice();
    }

    @Override
    public void removeDeviceAdmin(Context context, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        csdkManager().enableDeviceAdmin(componentName.flattenToString(), false);
    }

    @Override
    public String runBugReport(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        return super.runBugReport(filePath);
    }

    @Override
    public boolean setAdminRemovable(boolean removable) {
        return true;
    }

    @Override
    public void setBootingAnimation(File animationFile, File loopFile, File soundFile, long delay) {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(loopFile, "loopFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
        csdkManager().setCustomBootAnim(animationFile.getAbsolutePath());
    }

    @Override
    public boolean setDexDisable(boolean disable) {
        csdkManager().disallowChangeWorkMode(disable);
        return true;
    }

    @Override
    public void setShuttingDownAnimation(File animationFile, File soundFile) {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
        csdkManager().setCustomShutAnim(animationFile.getAbsolutePath());
    }

    @Override
    public boolean setSpenPointImageState(boolean state) {
        return super.setSpenPointImageState(state);
    }

    @Override
    public void shoutdown() {
        csdkManager().shutdownDevice();
    }

    @Override
    public boolean startTcpDump(String outPath) {
        Intrinsics.checkNotNullParameter(outPath, "outPath");
        return super.startTcpDump(outPath);
    }

    @Override
    public boolean stopTcpDump() {
        return super.stopTcpDump();
    }
}

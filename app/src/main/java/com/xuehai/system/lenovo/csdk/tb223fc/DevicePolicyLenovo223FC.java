package com.xuehai.system.lenovo.csdk.tb223fc;

import android.app.Activity;
import android.app.csdk.CSDKManager;
import android.content.ComponentName;
import android.content.Context;
import com.xuehai.system.common.compat.DevicePolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.lenovo.csdk.LenovoCSDK;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000b\b\u0000\u0018\u0000 52\u00020\u0001:\u00015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J \u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0006H\u0016J\b\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\u0018\u0010 \u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0016J\u0010\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0016J(\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0016J\u0018\u0010.\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\u0006\u0010)\u001a\u00020'H\u0016J\u0010\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0016J\b\u00101\u001a\u00020\u000eH\u0016J\u0010\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0018H\u0016J\b\u00104\u001a\u00020\u0006H\u0016¨\u00066"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb223fc/DevicePolicyLenovo223FC;", "Lcom/xuehai/system/common/compat/DevicePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addDeviceAdmin", "", "activity", "Landroid/app/Activity;", "componentName", "Landroid/content/ComponentName;", "requestCode", "", "clearBootingAnimation", "", "clearShuttingDownAnimation", "clipSystemWifiDetail", "clip", "csdkManager", "Landroid/app/csdk/CSDKManager;", "debug", "boolean", "enableAccessibility", "packageName", "", "packageClass", "enable", "getDeviceMacAddress", "getImei", "getSerialNumber", "initEnvironment", "reboot", "removeDeviceAdmin", "runBugReport", "filePath", "setAdminRemovable", "removable", "setBootingAnimation", "animationFile", "Ljava/io/File;", "loopFile", "soundFile", "delay", "", "setDexDisable", "disable", "setShuttingDownAnimation", "setSpenPointImageState", "state", "shoutdown", "startTcpDump", "outPath", "stopTcpDump", "Companion", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePolicyLenovo223FC extends DevicePolicyDefault {
    private static final String TAG = "DevicePolicyLenovo223FC";

    public DevicePolicyLenovo223FC(Context context) {
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
    public void clipSystemWifiDetail(boolean clip) {
        csdkManager().clipSystemWifiDetail(clip);
    }

    @Override
    public void debug(boolean z) {
    }

    @Override
    public void enableAccessibility(String packageName, String packageClass, boolean enable) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(packageClass, "packageClass");
        csdkManager().enableAccessibility(packageName, packageClass, enable);
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
        MdmLog.w("DevicePolicyLenovo223FC", "联想未实现！ [runBugReport(" + filePath + ")]");
        return super.runBugReport(filePath);
    }

    @Override
    public boolean setAdminRemovable(boolean removable) {
        MdmLog.w("DevicePolicyLenovo223FC", "联想未实现！ [setAdminRemovable(" + removable + ")]");
        return super.setAdminRemovable(removable);
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
        MdmLog.w("DevicePolicyLenovo223FC", "联想未实现！ [setSpenPointImageState(" + state + ")] >>> true");
        return super.setSpenPointImageState(state);
    }

    @Override
    public void shoutdown() {
        csdkManager().shutdownDevice();
    }

    @Override
    public boolean startTcpDump(String outPath) {
        Intrinsics.checkNotNullParameter(outPath, "outPath");
        MdmLog.w("DevicePolicyLenovo223FC", "联想未实现！ [startTcpDump(" + outPath + ")]");
        return super.startTcpDump(outPath);
    }

    @Override
    public boolean stopTcpDump() {
        MdmLog.w("DevicePolicyLenovo223FC", "联想未实现！ [stopTcpDump()]");
        return super.stopTcpDump();
    }
}

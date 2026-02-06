package com.xuehai.system.common.policies;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import java.io.File;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0003H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H&J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0003H&J \u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0003H&J\b\u0010\u0018\u001a\u00020\u0015H&J\b\u0010\u0019\u001a\u00020\u0015H&J\b\u0010\u001a\u001a\u00020\u0015H&J\b\u0010\u001b\u001a\u00020\u0015H&J\b\u0010\u001c\u001a\u00020\u000bH&J\b\u0010\u001d\u001a\u00020\u000bH&J\u0018\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0015H&J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H&J(\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020+H&J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H&J\u0018\u0010.\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'2\u0006\u0010)\u001a\u00020'H&J\u0010\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0003H&J\b\u00101\u001a\u00020\u000bH&J\u0010\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u0015H&J\b\u00104\u001a\u00020\u0003H&¨\u00065"}, d2 = {"Lcom/xuehai/system/common/policies/IDevicePolicy;", "", "addDeviceAdmin", "", "activity", "Landroid/app/Activity;", "componentName", "Landroid/content/ComponentName;", "requestCode", "", "clearBootingAnimation", "", "clearShuttingDownAnimation", "clipSystemWifiDetail", "clip", "controlShowDeprecatedTarget", "show", "debug", "boolean", "enableAccessibility", "packageName", "", "packageClass", "enable", "getDeviceMacAddress", "getImei", "getSerialNumber", "getSimSerialNumber", "initEnvironment", "reboot", "removeDeviceAdmin", "context", "Landroid/content/Context;", "runBugReport", "filePath", "setAdminRemovable", "removable", "setBootingAnimation", "animationFile", "Ljava/io/File;", "loopFile", "soundFile", "delay", "", "setDexDisable", "disable", "setShuttingDownAnimation", "setSpenPointImageState", "state", "shoutdown", "startTcpDump", "outPath", "stopTcpDump", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IDevicePolicy {
    boolean addDeviceAdmin(Activity activity, ComponentName componentName, int requestCode);

    void clearBootingAnimation();

    void clearShuttingDownAnimation();

    void clipSystemWifiDetail(boolean clip);

    boolean controlShowDeprecatedTarget(boolean show);

    void debug(boolean z);

    void enableAccessibility(String packageName, String packageClass, boolean enable);

    String getDeviceMacAddress();

    String getImei();

    String getSerialNumber();

    String getSimSerialNumber();

    void initEnvironment();

    void reboot();

    void removeDeviceAdmin(Context context, ComponentName componentName);

    String runBugReport(String filePath);

    boolean setAdminRemovable(boolean removable);

    void setBootingAnimation(File animationFile, File loopFile, File soundFile, long delay);

    boolean setDexDisable(boolean disable);

    void setShuttingDownAnimation(File animationFile, File soundFile);

    boolean setSpenPointImageState(boolean state);

    void shoutdown();

    boolean startTcpDump(String outPath);

    boolean stopTcpDump();
}

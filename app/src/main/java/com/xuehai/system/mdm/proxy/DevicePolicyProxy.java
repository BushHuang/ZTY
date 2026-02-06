package com.xuehai.system.mdm.proxy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import com.xuehai.launcher.common.utils.KeepConfig;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.IDevicePolicy;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u0000 72\u00020\u0001:\u00017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J \u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0005H\u0016J\b\u0010\u001a\u001a\u00020\u0017H\u0016J\b\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0016J\b\u0010\u001e\u001a\u00020\rH\u0016J\b\u0010\u001f\u001a\u00020\rH\u0016J\u0018\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u0017H\u0016J\u0010\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0005H\u0016J(\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u0005H\u0016J\u0018\u00100\u001a\u00020\r2\u0006\u0010(\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0016J\u0010\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u0005H\u0016J\b\u00103\u001a\u00020\rH\u0016J\u0010\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u0017H\u0016J\b\u00106\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/xuehai/system/mdm/proxy/DevicePolicyProxy;", "Lcom/xuehai/system/common/policies/IDevicePolicy;", "policy", "(Lcom/xuehai/system/common/policies/IDevicePolicy;)V", "addDeviceAdmin", "", "activity", "Landroid/app/Activity;", "componentName", "Landroid/content/ComponentName;", "requestCode", "", "clearBootingAnimation", "", "clearShuttingDownAnimation", "clipSystemWifiDetail", "clip", "controlShowDeprecatedTarget", "show", "debug", "boolean", "enableAccessibility", "packageName", "", "packageClass", "enable", "getDeviceMacAddress", "getImei", "getSerialNumber", "getSimSerialNumber", "initEnvironment", "reboot", "removeDeviceAdmin", "context", "Landroid/content/Context;", "runBugReport", "filePath", "setAdminRemovable", "removable", "setBootingAnimation", "animationFile", "Ljava/io/File;", "loopFile", "soundFile", "delay", "", "setDexDisable", "disable", "setShuttingDownAnimation", "setSpenPointImageState", "state", "shoutdown", "startTcpDump", "outPath", "stopTcpDump", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePolicyProxy implements IDevicePolicy {
    public static final String TAG = "MDM:DevicePolicy";
    private static int serialNumberHitCount;
    private final IDevicePolicy policy;

    public static final Companion INSTANCE = new Companion(null);
    private static String serialNumberCache = "";

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/mdm/proxy/DevicePolicyProxy$Companion;", "", "()V", "TAG", "", "serialNumberCache", "getSerialNumberCache", "()Ljava/lang/String;", "setSerialNumberCache", "(Ljava/lang/String;)V", "serialNumberHitCount", "", "getSerialNumberHitCount", "()I", "setSerialNumberHitCount", "(I)V", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getSerialNumberCache() {
            return DevicePolicyProxy.serialNumberCache;
        }

        public final int getSerialNumberHitCount() {
            return DevicePolicyProxy.serialNumberHitCount;
        }

        public final void setSerialNumberCache(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            DevicePolicyProxy.serialNumberCache = str;
        }

        public final void setSerialNumberHitCount(int i) {
            DevicePolicyProxy.serialNumberHitCount = i;
        }
    }

    public DevicePolicyProxy(IDevicePolicy iDevicePolicy) {
        Intrinsics.checkNotNullParameter(iDevicePolicy, "policy");
        this.policy = iDevicePolicy;
    }

    @Override
    public boolean addDeviceAdmin(Activity activity, ComponentName componentName, int requestCode) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        try {
            boolean zAddDeviceAdmin = this.policy.addDeviceAdmin(activity, componentName, requestCode);
            MdmLog.log("MDM:DevicePolicy", "请求启动设备管理器" + componentName.toShortString(), Boolean.valueOf(zAddDeviceAdmin));
            return zAddDeviceAdmin;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "请求启动设备管理器" + componentName.toShortString() + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void clearBootingAnimation() {
        try {
            MdmLog.log$default("MDM:DevicePolicy", "清除开机动画", null, 4, null);
            this.policy.clearBootingAnimation();
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "清除开机动画 发生异常！", th);
        }
    }

    @Override
    public void clearShuttingDownAnimation() {
        try {
            MdmLog.log$default("MDM:DevicePolicy", "清除关机动画", null, 4, null);
            this.policy.clearShuttingDownAnimation();
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "清除关机动画 发生异常！", th);
        }
    }

    @Override
    public void clipSystemWifiDetail(boolean clip) {
        try {
            this.policy.clipSystemWifiDetail(clip);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "clipSystemWifiDetail(" + clip + ")！", th);
        }
    }

    @Override
    public boolean controlShowDeprecatedTarget(boolean show) {
        String str = show ? "显示低target应用提示" : "隐藏低target应用提示";
        try {
            boolean zControlShowDeprecatedTarget = this.policy.controlShowDeprecatedTarget(show);
            MdmLog.log("MDM:DevicePolicy", str + ": " + show, Boolean.valueOf(zControlShowDeprecatedTarget));
            return zControlShowDeprecatedTarget;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void debug(boolean z) {
        try {
            this.policy.debug(z);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "debug 发生异常！", th);
        }
    }

    @Override
    public void enableAccessibility(String packageName, String packageClass, boolean enable) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(packageClass, "packageClass");
        try {
            this.policy.enableAccessibility(packageName, packageClass, enable);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "enableAccessibility(" + packageName + ',' + packageClass + ',' + enable + ")！", th);
        }
    }

    @Override
    public String getDeviceMacAddress() {
        try {
            return this.policy.getDeviceMacAddress();
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "获取设备MacAddress 发生异常！", th);
            return "";
        }
    }

    @Override
    public String getImei() {
        try {
            return this.policy.getImei();
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "获取设备IMEI 发生异常！", th);
            return "";
        }
    }

    @Override
    public String getSerialNumber() {
        try {
            if ((serialNumberCache.length() == 0) || serialNumberHitCount < 10) {
                String serialNumber = this.policy.getSerialNumber();
                if (Intrinsics.areEqual(serialNumberCache, serialNumber)) {
                    serialNumberHitCount++;
                }
                if (serialNumberHitCount >= 10) {
                    if (serialNumberCache.length() > 0) {
                        KeepConfig.getInstance().saveDeviceId(serialNumberCache);
                    }
                }
                serialNumberCache = serialNumber;
            }
            MdmLog.log$default("MDM:DevicePolicy", "MDM 获取设备唯一编码: " + serialNumberCache + "; hit:" + serialNumberHitCount, null, 4, null);
            return serialNumberCache;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "MDM 获取设备唯一编码 发生异常！", th);
            String deviceId = KeepConfig.getInstance().getDeviceId();
            return deviceId == null ? "" : deviceId;
        }
    }

    @Override
    public String getSimSerialNumber() {
        try {
            return this.policy.getSimSerialNumber();
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "获取设备SimSerialNumber发生异常！", th);
            return "";
        }
    }

    @Override
    public void initEnvironment() {
        try {
            this.policy.initEnvironment();
            MdmLog.log$default("MDM:DevicePolicy", "初始化mdm环境", null, 4, null);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "初始化mdm环境 发生异常！", th);
        }
    }

    @Override
    public void reboot() {
        try {
            MdmLog.log$default("MDM:DevicePolicy", "开始重启", null, 4, null);
            this.policy.reboot();
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "重启 发生异常！", th);
        }
    }

    @Override
    public void removeDeviceAdmin(Context context, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        try {
            MdmLog.log$default("MDM:DevicePolicy", "请求解锁设备管理器" + componentName.toShortString(), null, 4, null);
            this.policy.removeDeviceAdmin(context, componentName);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "请求解锁设备管理器" + componentName.toShortString() + " 发生异常！", th);
        }
    }

    @Override
    public String runBugReport(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        try {
            MdmLog.log$default("MDM:DevicePolicy", "获取dumpstate log", null, 4, null);
            return this.policy.runBugReport(filePath);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "获取dumpstate log 发生异常！", th);
            return "";
        }
    }

    @Override
    public boolean setAdminRemovable(boolean removable) {
        String str = removable ? "允许移除设备管理器" : "禁止移除设备管理器";
        try {
            boolean adminRemovable = this.policy.setAdminRemovable(removable);
            MdmLog.log("MDM:DevicePolicy", str + ": " + removable, Boolean.valueOf(adminRemovable));
            return adminRemovable;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void setBootingAnimation(File animationFile, File loopFile, File soundFile, long delay) {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(loopFile, "loopFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
        try {
            MdmLog.log$default("MDM:DevicePolicy", "设置开机动画", null, 4, null);
            this.policy.setBootingAnimation(animationFile, loopFile, soundFile, delay);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "设置开机动画 发生异常！", th);
        }
    }

    @Override
    public boolean setDexDisable(boolean disable) {
        String str = disable ? "禁用DEX模式" : "启用DEX模式";
        try {
            boolean dexDisable = this.policy.setDexDisable(disable);
            MdmLog.log("MDM:DevicePolicy", str + ": " + disable, Boolean.valueOf(dexDisable));
            return dexDisable;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", str + " 发生异常！", th);
            return false;
        }
    }

    @Override
    public void setShuttingDownAnimation(File animationFile, File soundFile) {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
        try {
            MdmLog.log$default("MDM:DevicePolicy", "设置关机动画", null, 4, null);
            this.policy.setShuttingDownAnimation(animationFile, soundFile);
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "设置关机动画 发生异常！", th);
        }
    }

    @Override
    public boolean setSpenPointImageState(boolean state) {
        try {
            boolean spenPointImageState = this.policy.setSpenPointImageState(state);
            MdmLog.log("MDM:DevicePolicy", "设置手写笔圆点提示状态: " + state, Boolean.valueOf(spenPointImageState));
            return spenPointImageState;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "设置手写笔圆点提示状态 发生异常！", th);
            return false;
        }
    }

    @Override
    public void shoutdown() {
        try {
            MdmLog.log$default("MDM:DevicePolicy", "开始关机", null, 4, null);
            this.policy.shoutdown();
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "关机 发生异常！", th);
        }
    }

    @Override
    public boolean startTcpDump(String outPath) {
        Intrinsics.checkNotNullParameter(outPath, "outPath");
        try {
            boolean zStartTcpDump = this.policy.startTcpDump(outPath);
            MdmLog.log("MDM:DevicePolicy", "启动网络数据抓包: " + outPath, Boolean.valueOf(zStartTcpDump));
            return zStartTcpDump;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "启动网络数据抓包 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean stopTcpDump() {
        try {
            boolean zStopTcpDump = this.policy.stopTcpDump();
            MdmLog.log("MDM:DevicePolicy", "关闭网络数据抓包", Boolean.valueOf(zStopTcpDump));
            return zStopTcpDump;
        } catch (Throwable th) {
            MdmLog.e("MDM:DevicePolicy", "关闭网络数据抓包 发生异常！", th);
            return false;
        }
    }
}

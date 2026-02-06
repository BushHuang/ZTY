package com.xuehai.system.samsung.knox.v3.custom.p200;

import android.content.Context;
import com.b2b.rom.ISamsungDevice;
import com.xuehai.system.common.util.MdmUtil;
import com.xuehai.system.samsung.knox.v3.DevicePolicySamsungV3;
import com.xuehai.system.samsung.knox.v3.custom.SamsungCustomSDK;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016¨\u0006\u0015"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p200/DevicePolicySamsungP200;", "Lcom/xuehai/system/samsung/knox/v3/DevicePolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getSerialNumber", "", "initEnvironment", "", "reboot", "runBugReport", "filePath", "samsungDevice", "Lcom/b2b/rom/ISamsungDevice;", "setSpenPointImageState", "", "state", "shoutdown", "startTcpDump", "outPath", "stopTcpDump", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DevicePolicySamsungP200 extends DevicePolicySamsungV3 {
    public DevicePolicySamsungP200(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public String getSerialNumber() {
        String serialNumber = super.getSerialNumber();
        if (!(serialNumber.length() == 0) && !MdmUtil.INSTANCE.isAllCharsSame(serialNumber)) {
            return serialNumber;
        }
        try {
            String sn = samsungDevice().getSN();
            Intrinsics.checkNotNullExpressionValue(sn, "{\n                    sa…ce().sn\n                }");
            return sn;
        } catch (Exception unused) {
            return MdmUtil.INSTANCE.getUUID(getContext());
        }
    }

    @Override
    public void initEnvironment() {
        samsungDevice();
    }

    @Override
    public void reboot() {
        samsungDevice().reboot();
    }

    @Override
    public String runBugReport(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        String strRunBugReport = samsungDevice().runBugReport(filePath);
        Intrinsics.checkNotNullExpressionValue(strRunBugReport, "samsungDevice().runBugReport(filePath)");
        return strRunBugReport;
    }

    public final ISamsungDevice samsungDevice() {
        return SamsungCustomSDK.INSTANCE.samsungDevice(getContext());
    }

    @Override
    public boolean setSpenPointImageState(boolean state) {
        return samsungDevice().setSpenPointImageState(state);
    }

    @Override
    public void shoutdown() {
        samsungDevice().shutdown();
    }

    @Override
    public boolean startTcpDump(String outPath) {
        Intrinsics.checkNotNullParameter(outPath, "outPath");
        return samsungDevice().startTcpdump(outPath);
    }

    @Override
    public boolean stopTcpDump() {
        return samsungDevice().stopTcpdump();
    }
}

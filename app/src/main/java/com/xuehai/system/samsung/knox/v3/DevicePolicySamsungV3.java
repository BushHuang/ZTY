package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.samsung.android.knox.EnterpriseDeviceManager;
import com.xuehai.system.common.compat.DevicePolicyDefault;
import com.xuehai.system.common.util.MdmFileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J(\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0015H\u0016J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000fH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006 "}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/DevicePolicySamsungV3;", "Lcom/xuehai/system/common/compat/DevicePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "getPolicy$mdm_samsungknoxv3_release", "()Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "clearBootingAnimation", "", "clearShuttingDownAnimation", "getFD", "Landroid/os/ParcelFileDescriptor;", "file", "Ljava/io/File;", "getSerialNumber", "", "getSimSerialNumber", "reboot", "setAdminRemovable", "", "removable", "setBootingAnimation", "animationFile", "loopFile", "soundFile", "delay", "", "setDexDisable", "disable", "setShuttingDownAnimation", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DevicePolicySamsungV3 extends DevicePolicyDefault {
    private final SamsungKnoxV3 policy;

    public DevicePolicySamsungV3(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(context);
    }

    private final ParcelFileDescriptor getFD(File file) throws FileNotFoundException {
        file.setReadable(true, false);
        ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(file, 268435456);
        Intrinsics.checkNotNullExpressionValue(parcelFileDescriptorOpen, "open(file, ParcelFileDescriptor.MODE_READ_ONLY)");
        return parcelFileDescriptorOpen;
    }

    @Override
    public void clearBootingAnimation() {
        this.policy.systemManager().clearAnimation(0);
    }

    @Override
    public void clearShuttingDownAnimation() {
        this.policy.systemManager().clearAnimation(1);
    }

    public final SamsungKnoxV3 getPolicy() {
        return this.policy;
    }

    @Override
    public String getSerialNumber() {
        String serialNumber = this.policy.deviceInventory().getSerialNumber();
        return serialNumber == null ? super.getSerialNumber() : serialNumber;
    }

    @Override
    public String getSimSerialNumber() {
        try {
            String str = this.policy.deviceInventory().getLastSimChangeInfo().currentSimInfo.serialNumber;
            Intrinsics.checkNotNullExpressionValue(str, "policy.deviceInventory()…rrentSimInfo.serialNumber");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return super.getSimSerialNumber();
        }
    }

    @Override
    public void reboot() {
        this.policy.passwordPolicy().reboot("xuehai");
    }

    @Override
    public boolean setAdminRemovable(boolean removable) {
        return EnterpriseDeviceManager.getInstance(getContext()).setAdminRemovable(removable);
    }

    @Override
    public void setBootingAnimation(File animationFile, File loopFile, File soundFile, long delay) throws Throwable {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(loopFile, "loopFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
        File file = new File(getContext().getFilesDir().toString() + "/booting/animation.qmg");
        File file2 = new File(getContext().getFilesDir().toString() + "/booting/animation_loopsource.qmg");
        File file3 = new File(getContext().getFilesDir().toString() + "/booting/wav.ogg");
        MdmFileUtils.INSTANCE.writeFileToStream(animationFile, file);
        MdmFileUtils.INSTANCE.writeFileToStream(loopFile, file2);
        MdmFileUtils.INSTANCE.writeFileToStream(soundFile, file3);
        ParcelFileDescriptor fd = getFD(file);
        ParcelFileDescriptor fd2 = getFD(file2);
        ParcelFileDescriptor fd3 = getFD(file3);
        clearBootingAnimation();
        this.policy.systemManager().setBootingAnimation(fd, fd2, fd3, (int) delay);
    }

    @Override
    public boolean setDexDisable(boolean disable) {
        return EnterpriseDeviceManager.getInstance(getContext()).getDexManager().setDexDisabled(disable);
    }

    @Override
    public void setShuttingDownAnimation(File animationFile, File soundFile) throws Throwable {
        Intrinsics.checkNotNullParameter(animationFile, "animationFile");
        Intrinsics.checkNotNullParameter(soundFile, "soundFile");
        File file = new File(getContext().getFilesDir().toString() + "/shutting/animation.qmg");
        File file2 = new File(getContext().getFilesDir().toString() + "/shutting/wav.ogg");
        MdmFileUtils.INSTANCE.writeFileToStream(animationFile, file);
        MdmFileUtils.INSTANCE.writeFileToStream(soundFile, file2);
        ParcelFileDescriptor fd = getFD(file);
        ParcelFileDescriptor fd2 = getFD(file2);
        clearShuttingDownAnimation();
        this.policy.systemManager().setShuttingDownAnimation(fd, fd2);
    }
}

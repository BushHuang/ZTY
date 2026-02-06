package com.xuehai.system.samsung.knox.v3.custom.p620;

import android.content.Context;
import com.b2b.rom.SamsungDevice;
import com.xuehai.system.samsung.knox.v3.RestrictionPolicySamsungV3;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p620/RestrictionPolicySamsungP620;", "Lcom/xuehai/system/samsung/knox/v3/RestrictionPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "samsungDevice", "Lcom/b2b/rom/SamsungDevice;", "allowSystemAccountLogin", "", "allow", "disableHomeLongFunc", "", "setBixbyShortcutEnabled", "enable", "setCameraShortcutEnabled", "setInputMethod", "inputMethodClassName", "", "setKanbanEnabled", "setLockScreenItems", "setSwitchInputMethodEnabled", "setTaskBarEnabled", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RestrictionPolicySamsungP620 extends RestrictionPolicySamsungV3 {
    private final Context context;
    private final SamsungDevice samsungDevice;

    public RestrictionPolicySamsungP620(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        SamsungDevice samsungDevice = SamsungDevice.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(samsungDevice, "getInstance(context)");
        this.samsungDevice = samsungDevice;
    }

    @Override
    public boolean allowSystemAccountLogin(boolean allow) {
        return this.samsungDevice.disableSamsungAccount(!allow);
    }

    @Override
    public void disableHomeLongFunc() {
        this.samsungDevice.disableHomeLongFunc();
    }

    @Override
    public boolean setBixbyShortcutEnabled(boolean enable) {
        return this.samsungDevice.disableBixbyShorcut(!enable);
    }

    @Override
    public boolean setCameraShortcutEnabled(boolean enable) {
        return this.samsungDevice.disableCameraShorcut(!enable);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        Intrinsics.checkNotNullParameter(inputMethodClassName, "inputMethodClassName");
        return this.samsungDevice.setInputMethod(inputMethodClassName);
    }

    @Override
    public boolean setKanbanEnabled(boolean enable) {
        return this.samsungDevice.disableKanban(!enable);
    }

    @Override
    public boolean setLockScreenItems(boolean enable) {
        return this.samsungDevice.setLockScreenItems(enable);
    }

    @Override
    public boolean setSwitchInputMethodEnabled(boolean enable) {
        return this.samsungDevice.disableSwitchInputMethod(!enable);
    }

    @Override
    public boolean setTaskBarEnabled(boolean enable) {
        return this.samsungDevice.disableTaskBar(!enable);
    }
}

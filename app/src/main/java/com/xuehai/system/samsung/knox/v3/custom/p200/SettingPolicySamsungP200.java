package com.xuehai.system.samsung.knox.v3.custom.p200;

import android.content.Context;
import com.b2b.rom.ISamsungDevice;
import com.xuehai.system.samsung.knox.v3.SettingPolicySamsungV3;
import com.xuehai.system.samsung.knox.v3.custom.SamsungCustomSDK;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J,\u0010\u000e\u001a\u00020\n2\"\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n0\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n`\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p200/SettingPolicySamsungP200;", "Lcom/xuehai/system/samsung/knox/v3/SettingPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "openSoftUpdateView", "", "samsungDevice", "Lcom/b2b/rom/ISamsungDevice;", "setDevelopHiddenState", "", "state", "setDropMenuHiddenState", "setSettingsHiddenState", "setStatusBarButtonState", "buttonList", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "startSoftUpdate", "hour", "", "minute", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SettingPolicySamsungP200 extends SettingPolicySamsungV3 {
    private final Context context;

    public SettingPolicySamsungP200(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public void openSoftUpdateView() {
        samsungDevice().openSoftUpdateView();
    }

    public final ISamsungDevice samsungDevice() {
        return SamsungCustomSDK.INSTANCE.samsungDevice(this.context);
    }

    @Override
    public boolean setDevelopHiddenState(boolean state) {
        samsungDevice().setDevelopHiddenState(state);
        return super.setDevelopHiddenState(state);
    }

    @Override
    public boolean setDropMenuHiddenState(boolean state) {
        return samsungDevice().setDropMenuHiddenState(state);
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) {
        return samsungDevice().setSettingsHiddenState(state);
    }

    @Override
    public boolean setStatusBarButtonState(HashMap<String, Boolean> buttonList) {
        Intrinsics.checkNotNullParameter(buttonList, "buttonList");
        return samsungDevice().setStatusBarButtonState(buttonList);
    }

    @Override
    public void startSoftUpdate(int hour, int minute) {
        samsungDevice().startSoftUpdate(hour, minute);
    }
}

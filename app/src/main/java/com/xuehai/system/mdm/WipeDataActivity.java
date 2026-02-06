package com.xuehai.system.mdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.xh.common.lib.sdk.samsung.AdminReceiver;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.system.mdm.device.DeviceManager;
import com.xuehai.system.mdm.proxy.PolicyManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00142\u00020\u00012\u00020\u0002:\u0002\u0014\u0015B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0012\u0010\r\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0007H\u0014J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0002¨\u0006\u0016"}, d2 = {"Lcom/xuehai/system/mdm/WipeDataActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "Lcom/xh/common/lib/sdk/samsung/AdminReceiver$DeviceAdminListener;", "()V", "isFullScreen", "", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDeviceAdminStateChanged", "isEnable", "wipeData", "Companion", "WipeDataListener", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WipeDataActivity extends AbsActivity implements AdminReceiver.DeviceAdminListener {

    public static final Companion INSTANCE = new Companion(null);
    private static WipeDataListener wipeDataListener;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/xuehai/system/mdm/WipeDataActivity$Companion;", "", "()V", "wipeDataListener", "Lcom/xuehai/system/mdm/WipeDataActivity$WipeDataListener;", "getWipeDataListener", "()Lcom/xuehai/system/mdm/WipeDataActivity$WipeDataListener;", "setWipeDataListener", "(Lcom/xuehai/system/mdm/WipeDataActivity$WipeDataListener;)V", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WipeDataListener getWipeDataListener() {
            return WipeDataActivity.wipeDataListener;
        }

        public final void setWipeDataListener(WipeDataListener wipeDataListener) {
            WipeDataActivity.wipeDataListener = wipeDataListener;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/xuehai/system/mdm/WipeDataActivity$WipeDataListener;", "", "onResult", "", "success", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface WipeDataListener {
        void onResult(boolean success);
    }

    private final boolean wipeData() {
        MyLog.i("[MDM]", "wipeData");
        PolicyManager.getRestrictionPolicyProxy().allowFirmwareRecovery(true);
        PolicyManager.getRestrictionPolicyProxy().allowFactoryReset(true);
        return DeviceManager.INSTANCE.wipe(this);
    }

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        WipeDataListener wipeDataListener2;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9) {
            if (resultCode == -1) {
                WipeDataListener wipeDataListener3 = wipeDataListener;
                if (wipeDataListener3 != null) {
                    wipeDataListener3.onResult(wipeData());
                }
            } else if (resultCode == 0 && (wipeDataListener2 = wipeDataListener) != null) {
                wipeDataListener2.onResult(false);
            }
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(savedInstanceState);
        if (DeviceActiveManager.INSTANCE.isAdminActiveInSystem(this)) {
            WipeDataListener wipeDataListener2 = wipeDataListener;
            if (wipeDataListener2 != null) {
                wipeDataListener2.onResult(wipeData());
            }
            finish();
            return;
        }
        AdminReceiver.addListener(this);
        if (DeviceActiveManager.INSTANCE.addDeviceAdmin(this)) {
            return;
        }
        WipeDataListener wipeDataListener3 = wipeDataListener;
        if (wipeDataListener3 != null) {
            wipeDataListener3.onResult(false);
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AdminReceiver.removeListener(this);
        wipeDataListener = null;
    }

    @Override
    public void onDeviceAdminStateChanged(boolean isEnable) {
        if (isEnable) {
            WipeDataListener wipeDataListener2 = wipeDataListener;
            if (wipeDataListener2 != null) {
                wipeDataListener2.onResult(wipeData());
                return;
            }
            return;
        }
        WipeDataListener wipeDataListener3 = wipeDataListener;
        if (wipeDataListener3 != null) {
            wipeDataListener3.onResult(false);
        }
    }
}

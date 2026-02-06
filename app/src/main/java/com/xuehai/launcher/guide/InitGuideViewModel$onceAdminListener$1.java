package com.xuehai.launcher.guide;

import com.xh.common.lib.sdk.samsung.AdminReceiver;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.LauncherSPUtil;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/xuehai/launcher/guide/InitGuideViewModel$onceAdminListener$1", "Lcom/xh/common/lib/sdk/samsung/AdminReceiver$DeviceAdminListener;", "onDeviceAdminStateChanged", "", "isEnable", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InitGuideViewModel$onceAdminListener$1 implements AdminReceiver.DeviceAdminListener {
    final InitGuideViewModel this$0;

    InitGuideViewModel$onceAdminListener$1(InitGuideViewModel initGuideViewModel) {
        this.this$0 = initGuideViewModel;
    }

    @Override
    public void onDeviceAdminStateChanged(boolean isEnable) {
        if (isEnable) {
            LauncherSPUtil.put("reboot_count", 0);
            this.this$0.continueToGuide(true);
        } else {
            LauncherSPUtil.setUpdateTimeByKey("admin_result_key");
            MyLog.INSTANCE.e("[MDM]", "设备管理器激活失败", new SecurityException());
            InitGuideViewModel initGuideViewModel = this.this$0;
            InitGuideViewModel.showCurrentMessage$default(initGuideViewModel, initGuideViewModel.getString(2131689500), false, false, 6, null);
            LiveDataExtKt.set(this.this$0.getNeedRetryAction(), true);
        }
        AdminReceiver.removeListener(this);
    }
}

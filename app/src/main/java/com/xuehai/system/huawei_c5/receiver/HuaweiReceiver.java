package com.xuehai.system.huawei_c5.receiver;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/huawei_c5/receiver/HuaweiReceiver;", "", "()V", "ACTION_HUAWEI_FREE", "", "LICENSE_STATE", "LICENSE_STATE_DESC", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HuaweiReceiver {
    public static final String ACTION_HUAWEI_FREE = "com.xuehai.mdm.huawei.free";
    public static final HuaweiReceiver INSTANCE = new HuaweiReceiver();
    public static final String LICENSE_STATE = "license_state";
    public static final String LICENSE_STATE_DESC = "license_state_desc";

    private HuaweiReceiver() {
    }
}

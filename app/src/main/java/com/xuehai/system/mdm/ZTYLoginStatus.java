package com.xuehai.system.mdm;

import android.os.Environment;
import com.xh.xhcore.common.util.MMKVUtil;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/mdm/ZTYLoginStatus;", "", "()V", "ZTY_LOGIN_KEY", "", "getMMKVLocalCachePath", "isZTYLogin", "", "updateZTYLoginStatus", "", "isLogin", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ZTYLoginStatus {
    public static final ZTYLoginStatus INSTANCE = new ZTYLoginStatus();
    private static final String ZTY_LOGIN_KEY = "zty_login_status";

    private ZTYLoginStatus() {
    }

    private final String getMMKVLocalCachePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/xuehai/keep/zty_login";
    }

    public final boolean isZTYLogin() {
        return MMKVUtil.INSTANCE.getBoolean(getMMKVLocalCachePath(), "zty_login_status", false);
    }

    public final void updateZTYLoginStatus(boolean isLogin) {
        MMKVUtil.INSTANCE.put(getMMKVLocalCachePath(), "zty_login_status", Boolean.valueOf(isLogin));
    }
}

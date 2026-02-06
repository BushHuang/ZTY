package com.xuehai.system.mdm;

import android.os.Environment;
import com.xh.xhcore.common.util.MMKVUtil;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/mdm/ZTYCrashStatus;", "", "()V", "ZTY_CRASH_KEY", "", "getMMKVLocalCachePath", "isZTYCrash", "", "updateZTYCrashStatus", "", "isCrash", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ZTYCrashStatus {
    public static final ZTYCrashStatus INSTANCE = new ZTYCrashStatus();
    private static final String ZTY_CRASH_KEY = "zty_crash_status";

    private ZTYCrashStatus() {
    }

    private final String getMMKVLocalCachePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/xuehai/keep/zty_crash";
    }

    public final boolean isZTYCrash() {
        return MMKVUtil.INSTANCE.getBoolean(getMMKVLocalCachePath(), "zty_crash_status", false);
    }

    public final void updateZTYCrashStatus(boolean isCrash) {
        MMKVUtil.INSTANCE.put(getMMKVLocalCachePath(), "zty_crash_status", Boolean.valueOf(isCrash));
    }
}

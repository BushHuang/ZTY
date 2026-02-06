package com.xuehai.launcher.common.util;

import com.zaze.utils.DeviceUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/xuehai/launcher/common/util/CheckDataUtil;", "", "()V", "checkSdcardIsFull", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CheckDataUtil {
    public static final CheckDataUtil INSTANCE = new CheckDataUtil();

    private CheckDataUtil() {
    }

    @JvmStatic
    public static final boolean checkSdcardIsFull() {
        return DeviceUtil.getSdFreeSpace() < 52428800;
    }
}

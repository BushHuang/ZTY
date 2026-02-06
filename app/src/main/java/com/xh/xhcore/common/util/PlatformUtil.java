package com.xh.xhcore.common.util;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xuehai.platform.PlatformManager;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/util/PlatformUtil;", "", "()V", "notifyLogoutPlatform", "", "code", "", "msg", "", "(Ljava/lang/Integer;Ljava/lang/String;)V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlatformUtil {
    public static final PlatformUtil INSTANCE = new PlatformUtil();

    private PlatformUtil() {
    }

    public final void notifyLogoutPlatform(Integer code, String msg) {
        try {
            Result.Companion companion = Result.INSTANCE;
            PlatformManager.notifyPlatformLogout(XhBaseApplication.mContext, code == null ? -1 : code.intValue(), msg);
            Result.m228constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m228constructorimpl(ResultKt.createFailure(th));
        }
    }
}

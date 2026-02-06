package com.xh.xhcore.common.statistic;

import com.xh.xhcore.common.http.BaseMicroServer;
import com.xh.xhcore.common.statistic.connect.BuryNetworkType;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/statistic/BuryUtil;", "", "()V", "getNetworkTypeFromUrl", "", "url", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BuryUtil {
    public static final BuryUtil INSTANCE = new BuryUtil();

    private BuryUtil() {
    }

    public final int getNetworkTypeFromUrl(String url) {
        Object obj;
        Intrinsics.checkNotNullParameter(url, "url");
        Iterator<T> it = BaseMicroServer.INSTANCE.getInstance().getServerUrl("SB103013").getMicroServerNewDtoList().iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (StringsKt.contains$default((CharSequence) url, (CharSequence) ((BaseMicroServer.MicroServerEntityUnify) next).getDomainOrIp(), false, 2, (Object) null)) {
                obj = next;
                break;
            }
        }
        return ((BaseMicroServer.MicroServerEntityUnify) obj) != null ? BuryNetworkType.intranet.ordinal() : BuryNetworkType.internet.ordinal();
    }
}

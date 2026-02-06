package com.xh.xhcore.common.http.dns;

import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.ConstStatistics;
import com.xh.xhcore.common.statistic.DataCollectionUtil;
import com.xh.xhcore.common.util.JsonUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSBuryUtil;", "", "()V", "buryDNSEvent", "", "hostName", "", "buryType", "", "responseMsg", "isHostNameNeedHttpDNS", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DNSBuryUtil {
    public static final DNSBuryUtil INSTANCE = new DNSBuryUtil();

    private DNSBuryUtil() {
    }

    @JvmStatic
    public static final void buryDNSEvent(String hostName, int buryType, String responseMsg) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        Intrinsics.checkNotNullParameter(responseMsg, "responseMsg");
        if (isHostNameNeedHttpDNS(hostName) && DNSLookupTypeManager.getCurrentModel().getAllowBury()) {
            DataCollectionUtil.setBury(new BuryEvent(ConstStatistics.INSTANCE.getHTTPDNS_LOOKUP_TYPE(), ConstStatistics.INSTANCE.getHTTPDNS_LOOKUP_NAME(), JsonUtil.toJsonString(new HTTPDNSStatistics(hostName, buryType, responseMsg))));
        }
    }

    public static void buryDNSEvent$default(String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        buryDNSEvent(str, i, str2);
    }

    @JvmStatic
    public static final boolean isHostNameNeedHttpDNS(String hostName) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        return true;
    }
}

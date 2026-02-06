package com.xh.xhcore.common.http.dns;

import com.xh.xhcore.common.http.dns.interceptor.HttpDNSResult;
import com.xh.xhcore.common.http.dns.model.DNSStatusResultModel;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007J\b\u0010\u0014\u001a\u00020\fH\u0007J\u001f\u0010\u0015\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\nH\u0003¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\bH\u0007J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\fH\u0007J\u0018\u0010\u001c\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\fH\u0007J\u0010\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0010H\u0007J\u0012\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\u0012H\u0007J\u0012\u0010\"\u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\u0012H\u0007R\u0018\u0010\u0003\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u0006\u0012\u0004\b\u0005\u0010\u0002¨\u0006#"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSLookupTypeManager;", "", "()V", "sThreadLocal", "com/xh/xhcore/common/http/dns/DNSLookupTypeManager$sThreadLocal$1", "getSThreadLocal$annotations", "Lcom/xh/xhcore/common/http/dns/DNSLookupTypeManager$sThreadLocal$1;", "clear", "", "getCurrentModel", "Lcom/xh/xhcore/common/http/dns/DNSThreadLocalModel;", "getCurrentType", "", "hostName", "", "getFollowUpConditionMatch", "", "getHttpDNSResult", "Lcom/xh/xhcore/common/http/dns/interceptor/HttpDNSResult;", "getLocalDNSResult", "getNetworkRetryTimes", "haveStepForward", "previousModelType", "currentModel", "(ILcom/xh/xhcore/common/http/dns/DNSThreadLocalModel;)Ljava/lang/Integer;", "increaseNetworkRetryTimes", "needRetryForDNS", "Lcom/xh/xhcore/common/http/dns/model/DNSStatusResultModel;", "setCurrentType", "type", "setFollowUpConditionMatch", "followUpMatchCondition", "setHttpDNSResult", "dnsResult", "setLocalDNSResult", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DNSLookupTypeManager {
    public static final DNSLookupTypeManager INSTANCE = new DNSLookupTypeManager();
    private static final DNSLookupTypeManager$sThreadLocal$1 sThreadLocal = new ThreadLocal<DNSThreadLocalModel>() {
        @Override
        protected DNSThreadLocalModel initialValue() {
            return new DNSThreadLocalModel(null, false, false, null, null, 0, 63, null);
        }
    };

    private DNSLookupTypeManager() {
    }

    @JvmStatic
    public static final void clear() {
        sThreadLocal.set(new DNSThreadLocalModel(null, false, false, null, null, 0, 63, null));
    }

    @JvmStatic
    public static final DNSThreadLocalModel getCurrentModel() {
        DNSThreadLocalModel dNSThreadLocalModel = sThreadLocal.get();
        return dNSThreadLocalModel == null ? new DNSThreadLocalModel(null, false, false, null, null, 0, 63, null) : dNSThreadLocalModel;
    }

    @JvmStatic
    public static final int getCurrentType(String hostName) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        DNSThreadLocalModel dNSThreadLocalModel = sThreadLocal.get();
        if (dNSThreadLocalModel == null) {
            return -1;
        }
        return dNSThreadLocalModel.getCurrentType(hostName);
    }

    @JvmStatic
    public static final boolean getFollowUpConditionMatch() {
        return getCurrentModel().getFollowUpConditionMatch();
    }

    @JvmStatic
    public static final HttpDNSResult getHttpDNSResult() {
        return getCurrentModel().getHttpDNSResult();
    }

    @JvmStatic
    public static final HttpDNSResult getLocalDNSResult() {
        return getCurrentModel().getLocalDNSResult();
    }

    @JvmStatic
    public static final int getNetworkRetryTimes() {
        return getCurrentModel().getNetworkRetryTimes();
    }

    @JvmStatic
    private static void getSThreadLocal$annotations() {
    }

    @JvmStatic
    private static final Integer haveStepForward(int previousModelType, DNSThreadLocalModel currentModel) {
        Set<Map.Entry<String, Integer>> setEntrySet = currentModel.getMap().entrySet();
        Intrinsics.checkNotNullExpressionValue(setEntrySet, "currentModel.map.entries");
        if (setEntrySet.size() != 1) {
            return null;
        }
        Object objLast = CollectionsKt.last(setEntrySet);
        Intrinsics.checkNotNullExpressionValue(objLast, "entries.last()");
        Map.Entry entry = (Map.Entry) objLast;
        Object value = entry.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "currentLastEntry.value");
        if (previousModelType < ((Number) value).intValue()) {
            return (Integer) entry.getValue();
        }
        return null;
    }

    @JvmStatic
    public static final void increaseNetworkRetryTimes() {
        getCurrentModel().increaseNetworkRetryTimes();
    }

    @JvmStatic
    public static final DNSStatusResultModel needRetryForDNS(int previousModelType) {
        DNSStatusResultModel dNSStatusResultModel;
        Integer numHaveStepForward = haveStepForward(previousModelType, getCurrentModel());
        if (numHaveStepForward == null) {
            dNSStatusResultModel = null;
        } else {
            int iIntValue = numHaveStepForward.intValue();
            LogUtils.Companion.d$default(LogUtils.INSTANCE, DNSConstKt.getDNS_TAG(), Intrinsics.stringPlus("[needRetryForDNS] currentLastStepForwardType = ", Integer.valueOf(iIntValue)), null, 4, null);
            boolean z = true;
            if (iIntValue != 0 && iIntValue != 1) {
                z = false;
            }
            dNSStatusResultModel = new DNSStatusResultModel(z, iIntValue);
        }
        if (dNSStatusResultModel == null) {
            dNSStatusResultModel = new DNSStatusResultModel(false, 2);
        }
        LogUtils.Companion.d$default(LogUtils.INSTANCE, DNSConstKt.getDNS_TAG(), Intrinsics.stringPlus("[needRetryForDNS] result = ", dNSStatusResultModel), null, 4, null);
        return dNSStatusResultModel;
    }

    @JvmStatic
    public static final void setCurrentType(String hostName, int type) {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        LogUtils.Companion.d$default(LogUtils.INSTANCE, DNSConstKt.getDNS_TAG(), "[setCurrentType] hostName = " + hostName + " type = " + type, null, 4, null);
        DNSThreadLocalModel dNSThreadLocalModel = sThreadLocal.get();
        if (dNSThreadLocalModel == null) {
            return;
        }
        dNSThreadLocalModel.setCurrentType(hostName, type);
    }

    @JvmStatic
    public static final void setFollowUpConditionMatch(boolean followUpMatchCondition) {
        LogUtils.Companion.d$default(LogUtils.INSTANCE, DNSConstKt.getDNS_TAG(), Intrinsics.stringPlus("[setFollowUpConditionMatch] followUpMatchCondition = ", Boolean.valueOf(followUpMatchCondition)), null, 4, null);
        getCurrentModel().setFollowUpConditionMatch(followUpMatchCondition);
    }

    @JvmStatic
    public static final void setHttpDNSResult(HttpDNSResult dnsResult) {
        getCurrentModel().setHttpDNSResult(dnsResult);
    }

    @JvmStatic
    public static final void setLocalDNSResult(HttpDNSResult dnsResult) {
        getCurrentModel().setLocalDNSResult(dnsResult);
    }
}

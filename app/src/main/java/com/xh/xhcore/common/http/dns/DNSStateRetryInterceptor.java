package com.xh.xhcore.common.http.dns;

import com.xh.xhcore.common.http.dns.model.DNSStatusResultModel;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.RequestTagsUtil;
import com.xh.xhcore.common.http.strategy.xh.config.AllowUploadNetworkError;
import com.xh.xhcore.common.http.strategy.xh.config.msg.HttpDNSErrorMessage;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u0004\u0018\u00010\b2\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSStateRetryInterceptor;", "Lokhttp3/Interceptor;", "()V", "followUpConditionMatch", "", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "needRetryForDnsException", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DNSStateRetryInterceptor implements Interceptor {
    private final boolean followUpConditionMatch() {
        return DNSLookupTypeManager.getFollowUpConditionMatch();
    }

    private final Interceptor.Chain needRetryForDnsException(Exception e, Interceptor.Chain chain) {
        LogUtils.Companion.d$default(LogUtils.INSTANCE, DNSConstKt.getDNS_TAG(), Intrinsics.stringPlus("[needRetryForDnsException] e = ", e), null, 4, null);
        if (e instanceof ConnectException) {
            return chain;
        }
        if (e instanceof SocketTimeoutException) {
            return chain.withConnectTimeout(3000, TimeUnit.MILLISECONDS).withReadTimeout(3000, TimeUnit.MILLISECONDS).withWriteTimeout(3000, TimeUnit.MILLISECONDS);
        }
        return null;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Intrinsics.checkNotNullParameter(chain, "chain");
        DNSLookupTypeManager.clear();
        DNSThreadLocalModel currentModel = DNSLookupTypeManager.getCurrentModel();
        AllowUploadNetworkError allowUploadNetworkError = (AllowUploadNetworkError) chain.request().tag(AllowUploadNetworkError.class);
        currentModel.setAllowBury(allowUploadNetworkError == null ? true : allowUploadNetworkError.getAllow());
        Request request = chain.request();
        Interceptor.Chain chain2 = chain;
        String hostNameWhenRetry = "";
        int currentLastStepForwardType = -1;
        while (true) {
            try {
                Response responseProceed = chain2.proceed(request);
                if (hostNameWhenRetry.length() > 0) {
                    DNSBuryUtil.buryDNSEvent$default(hostNameWhenRetry, 6, null, 4, null);
                }
                Intrinsics.checkNotNullExpressionValue(responseProceed, "response");
                return responseProceed;
            } catch (Exception e) {
                if (followUpConditionMatch()) {
                    throw e;
                }
                DNSStatusResultModel dNSStatusResultModelNeedRetryForDNS = DNSLookupTypeManager.needRetryForDNS(currentLastStepForwardType);
                if (!dNSStatusResultModelNeedRetryForDNS.getNeedRetryForDns()) {
                    throw e;
                }
                currentLastStepForwardType = dNSStatusResultModelNeedRetryForDNS.getCurrentLastStepForwardType();
                Interceptor.Chain chainNeedRetryForDnsException = needRetryForDnsException(e, chain);
                if (chainNeedRetryForDnsException == null) {
                    throw e;
                }
                LogUtils.Companion.d$default(LogUtils.INSTANCE, DNSConstKt.getDNS_TAG(), Intrinsics.stringPlus("proceed dns retry request: ", request), null, 4, null);
                if (currentLastStepForwardType == 1) {
                    hostNameWhenRetry = DNSLookupTypeManager.getCurrentModel().getHostNameWhenRetry();
                    if (hostNameWhenRetry.length() > 0) {
                        JSONObject jSONObjectPut = new HttpDNSErrorMessage().put("localDNSResult", DNSLookupTypeManager.getLocalDNSResult()).put("httpDNSResult", DNSLookupTypeManager.getHttpDNSResult()).put("stack", LogUtils.INSTANCE.getStackTraceString(e));
                        if (jSONObjectPut == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.xh.xhcore.common.http.strategy.xh.config.msg.HttpDNSErrorMessage");
                        }
                        DNSBuryUtil.buryDNSEvent$default(hostNameWhenRetry, 5, null, 4, null);
                        RequestTagsUtil.putTag(chain, (Class<HttpDNSErrorMessage>) HttpDNSErrorMessage.class, (HttpDNSErrorMessage) jSONObjectPut);
                    } else {
                        continue;
                    }
                }
                chain2 = chainNeedRetryForDnsException;
            }
        }
    }
}

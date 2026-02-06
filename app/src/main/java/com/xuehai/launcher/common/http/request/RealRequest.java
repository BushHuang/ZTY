package com.xuehai.launcher.common.http.request;

import android.text.TextUtils;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.xh.config.AllowUploadNetworkError;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.constants.error.RestFulError;
import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.http.LRequestBody;
import com.xuehai.launcher.common.http.LResponse;
import com.xuehai.launcher.common.interceptor.Interceptor;
import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.JsonUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/http/request/RealRequest;", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "Lcom/xuehai/launcher/common/http/LRequest;", "Lcom/xuehai/launcher/common/http/LResponse;", "()V", "intercept", "chain", "Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RealRequest implements Interceptor<LRequest, LResponse> {
    @Override
    public LResponse intercept(Interceptor.Chain<LRequest, LResponse> chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        LRequest lRequestInput = chain.input();
        final LResponse lResponse = new LResponse(lRequestInput);
        if (!SessionData.INSTANCE.isOnline()) {
            lResponse.setCode(-1);
            lResponse.setResponseBody("当前处于离线状态");
            return lResponse;
        }
        if (TextUtils.isEmpty(lRequestInput.getUrl())) {
            lResponse.setCode(-1);
            lResponse.setResponseBody("请求的URL不可为空");
            return lResponse;
        }
        XHRequestOkHttpProxy xHRequestOkHttpProxy = (XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setUrl(lRequestInput.getUrl())).setIsAsync(false)).setMethod(lRequestInput.getMethod());
        LRequestBody body = lRequestInput.getBody();
        ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) xHRequestOkHttpProxy.setBodyData(body != null ? body.getContent() : null).setHeaders(lRequestInput.getHeader().headers)).tag((Class<? super Class>) AllowUploadNetworkError.class, (Class) new AllowUploadNetworkError(lRequestInput.getSaveError()))).setCallback(new XHRequestCallBack.HttpCallBack<String>() {
            @Override
            public void failed(int code, String msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                String businessErrorMsg = getBusinessErrorMsg();
                lResponse.setCode(code);
                RestFulError restFulError = (RestFulError) JsonUtil.parseJson(businessErrorMsg, RestFulError.class);
                if (restFulError == null) {
                    restFulError = new RestFulError();
                    restFulError.setCode(lResponse.getCode());
                    restFulError.setMsg(msg);
                    restFulError.setDescription(msg);
                }
                lResponse.setRestFulError(restFulError);
                LResponse lResponse2 = lResponse;
                String msg2 = restFulError.getMsg();
                if (msg2 != null) {
                    msg = msg2;
                }
                lResponse2.setResponseBody(msg);
            }

            @Override
            public void success(String jsonParam) {
                Intrinsics.checkNotNullParameter(jsonParam, "jsonParam");
                lResponse.setCode(200);
                LResponse lResponse2 = lResponse;
                if (!TextUtils.isEmpty(jsonParam) && !StringsKt.equals("null", jsonParam, true)) {
                    try {
                        jsonParam = new JSONObject(jsonParam).optString("httpResponse", jsonParam);
                    } catch (Throwable unused) {
                        MyLog.w("Http[MDM]", "没有包含 httpResponse 直接返回 jsonParam");
                    }
                }
                lResponse2.setResponseBody(jsonParam);
            }
        })).request();
        return lResponse;
    }
}

package com.xuehai.launcher.common.http.request;

import com.xuehai.launcher.common.constants.error.RestFulError;
import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.http.LResponse;
import com.xuehai.launcher.common.http.RequestHelper;
import com.xuehai.launcher.common.interceptor.Interceptor;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.widget.CustomToast;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/http/request/HttpLogInterceptor;", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "Lcom/xuehai/launcher/common/http/LRequest;", "Lcom/xuehai/launcher/common/http/LResponse;", "()V", "intercept", "chain", "Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HttpLogInterceptor implements Interceptor<LRequest, LResponse> {
    @Override
    public LResponse intercept(Interceptor.Chain<LRequest, LResponse> chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        LRequest lRequestInput = chain.input();
        lRequestInput.log();
        LResponse lResponseProcess = chain.process(lRequestInput);
        if (lResponseProcess.isSuccessful()) {
            MyLog.i("Http[MDM]", "请求成功(" + lRequestInput.getUrl() + ") >> " + lResponseProcess.getResponseBody());
            return lResponseProcess;
        }
        RestFulError restFulError = lResponseProcess.getRestFulError();
        if (restFulError == null) {
            restFulError = new RestFulError();
            restFulError.setCode(lResponseProcess.getCode());
            restFulError.setMsg(lResponseProcess.getResponseBody());
        }
        lResponseProcess.setRestFulError(restFulError);
        RequestHelper.INSTANCE.unifyErrorDispose(lResponseProcess);
        MyLog.w("Http[MDM]", "请求发生错误(" + lRequestInput.getUrl() + ") >> " + restFulError.getDescription() + ' ' + restFulError.getMsg() + '(' + restFulError.getCode() + ')');
        if (lRequestInput.getToast()) {
            CustomToast.showToast(restFulError.getMsg() + '\n' + lResponseProcess.getCode());
        }
        return lResponseProcess;
    }
}

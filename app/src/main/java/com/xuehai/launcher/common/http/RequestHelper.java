package com.xuehai.launcher.common.http;

import com.xh.xhcore.common.http.XHMicroServer;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.ZCallback;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\t¨\u0006\u0010"}, d2 = {"Lcom/xuehai/launcher/common/http/RequestHelper;", "", "()V", "reqMicroService", "", "callBack", "Lcom/zaze/utils/ZCallback;", "", "requestWithRestful", "Lcom/xuehai/launcher/common/http/LResponse;", "request", "Lcom/xuehai/launcher/common/http/LRequest;", "requestWithRestfulByRx", "Lio/reactivex/Observable;", "unifyErrorDispose", "response", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RequestHelper {
    public static final RequestHelper INSTANCE = new RequestHelper();

    private RequestHelper() {
    }

    public final void reqMicroService(final ZCallback<String> callBack) {
        XHMicroServer.getInstance().getMicroServiceIPListWithRESTFul(new XHRequestCallBack.HttpCallBack<String>() {
            @Override
            public void failed(int i, String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                ZCallback<String> zCallback = callBack;
                if (zCallback != null) {
                    zCallback.onError(i, s);
                }
                ZCallback<String> zCallback2 = callBack;
                if (zCallback2 != null) {
                    zCallback2.onCompleted();
                }
            }

            @Override
            public void success(String s) {
                ZCallback<String> zCallback = callBack;
                if (zCallback != null) {
                    zCallback.onNext(s);
                }
                ZCallback<String> zCallback2 = callBack;
                if (zCallback2 != null) {
                    zCallback2.onCompleted();
                }
            }
        });
    }

    public final LResponse requestWithRestful(LRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new LCall(request).execute();
    }

    public final Observable<LResponse> requestWithRestfulByRx(LRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return new LCall(request).executeByRx();
    }

    public final void unifyErrorDispose(LResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getCode() == 107000401) {
            MyLog.d("Http[MDM]", "token失效");
        }
    }
}

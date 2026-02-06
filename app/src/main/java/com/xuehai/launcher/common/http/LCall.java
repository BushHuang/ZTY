package com.xuehai.launcher.common.http;

import android.text.TextUtils;
import com.xuehai.launcher.common.constants.error.ErrorCode;
import com.xuehai.launcher.common.constants.error.RestFulError;
import com.xuehai.launcher.common.http.request.HttpLogInterceptor;
import com.xuehai.launcher.common.http.request.RealRequest;
import com.xuehai.launcher.common.interceptor.Interceptor;
import com.xuehai.launcher.common.interceptor.RealInterceptorChain;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.plugins.rx.MyObserver;
import com.zaze.utils.ZCallback;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\f\u001a\u00020\u00002\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007J\"\u0010\u000e\u001a\u00020\u00002\u001a\u0010\u0005\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u000fJ\u0006\u0010\u0010\u001a\u00020\bJ\u0016\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0017J\b\u0010\u0018\u001a\u00020\bH\u0016R6\u0010\u0005\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/xuehai/launcher/common/http/LCall;", "", "request", "Lcom/xuehai/launcher/common/http/LRequest;", "(Lcom/xuehai/launcher/common/http/LRequest;)V", "interceptors", "Ljava/util/ArrayList;", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "Lcom/xuehai/launcher/common/http/LResponse;", "Lkotlin/collections/ArrayList;", "getRequest", "()Lcom/xuehai/launcher/common/http/LRequest;", "addInterceptor", "interceptor", "addInterceptors", "", "execute", "executeByCallback", "", "callback", "Lcom/zaze/utils/ZCallback;", "", "executeByRx", "Lio/reactivex/Observable;", "getResponseWhitChain", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LCall {
    private final ArrayList<Interceptor<LRequest, LResponse>> interceptors;
    private final LRequest request;

    public LCall(LRequest lRequest) {
        Intrinsics.checkNotNullParameter(lRequest, "request");
        this.request = lRequest;
        this.interceptors = new ArrayList<>();
    }

    private static final LResponse m66executeByCallback$lambda3(ZCallback zCallback, LResponse lResponse) {
        Intrinsics.checkNotNullParameter(lResponse, "response");
        if (zCallback == null) {
            return lResponse;
        }
        if (lResponse.isSuccessful()) {
            zCallback.onNext(zCallback.preNext(lResponse.getResponseBody()));
            return lResponse;
        }
        RestFulError restFulError = lResponse.getRestFulError();
        if (restFulError == null || TextUtils.isEmpty(restFulError.getMsg())) {
            zCallback.onError(lResponse.getCode(), lResponse.getResponseBody());
        } else {
            zCallback.onError(lResponse.getCode(), restFulError.getMsg());
        }
        return lResponse;
    }

    private static final void m67executeByCallback$lambda4(ZCallback zCallback) {
        if (zCallback != null) {
            zCallback.onCompleted();
        }
    }

    private static final LCall m68executeByRx$lambda1(LCall lCall) {
        Intrinsics.checkNotNullParameter(lCall, "this$0");
        return lCall;
    }

    private static final LResponse m69executeByRx$lambda2(LCall lCall) {
        Intrinsics.checkNotNullParameter(lCall, "it");
        return lCall.execute();
    }

    public final LCall addInterceptor(Interceptor<LRequest, LResponse> interceptor) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        this.interceptors.add(interceptor);
        return this;
    }

    public final LCall addInterceptors(List<? extends Interceptor<LRequest, LResponse>> interceptors) {
        if (interceptors != null) {
            this.interceptors.addAll(interceptors);
        }
        return this;
    }

    public final LResponse execute() {
        MyLog.i("[MDM]", "execute : thread :" + Thread.currentThread().getName());
        return getResponseWhitChain();
    }

    public final int executeByCallback(final ZCallback<String> callback) {
        executeByRx().subscribeOn(ThreadPlugins.requestScheduler()).observeOn(ThreadPlugins.ioScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LCall.m66executeByCallback$lambda3(callback, (LResponse) obj);
            }
        }).doFinally(new Action() {
            @Override
            public final void run() {
                LCall.m67executeByCallback$lambda4(callback);
            }
        }).subscribe(new MyObserver<LResponse>() {
            {
                super(null, 1, null);
            }

            @Override
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                super.onError(e);
                ZCallback<String> zCallback = callback;
                if (zCallback != null) {
                    zCallback.onError(ErrorCode.ERROR_DEAL_DATA.getCode(), ErrorCode.ERROR_DEAL_DATA.getMsg());
                }
            }
        });
        return 0;
    }

    public final Observable<LResponse> executeByRx() {
        Observable<LResponse> observableObserveOn = Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return LCall.m68executeByRx$lambda1(this.f$0);
            }
        }).subscribeOn(ThreadPlugins.requestScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LCall.m69executeByRx$lambda2((LCall) obj);
            }
        }).observeOn(ThreadPlugins.ioScheduler());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "fromCallable {\n         …eadPlugins.ioScheduler())");
        return observableObserveOn;
    }

    public final LRequest getRequest() {
        return this.request;
    }

    public LResponse getResponseWhitChain() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.interceptors);
        arrayList.add(new HttpLogInterceptor());
        arrayList.add(new RealRequest());
        return (LResponse) new RealInterceptorChain(arrayList, this.request, 0, 4, null).process(this.request);
    }
}

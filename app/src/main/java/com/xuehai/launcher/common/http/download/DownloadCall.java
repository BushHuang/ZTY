package com.xuehai.launcher.common.http.download;

import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.interceptor.Interceptor;
import com.xuehai.launcher.common.interceptor.RealInterceptorChain;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\f\u001a\u00020\u00002\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007J\"\u0010\u000e\u001a\u00020\u00002\u001a\u0010\u0005\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u000fJ\u0006\u0010\u0010\u001a\u00020\bR6\u0010\u0005\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/xuehai/launcher/common/http/download/DownloadCall;", "", "request", "Lcom/xuehai/launcher/common/http/LRequest;", "(Lcom/xuehai/launcher/common/http/LRequest;)V", "interceptors", "Ljava/util/ArrayList;", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "", "Lkotlin/collections/ArrayList;", "getRequest", "()Lcom/xuehai/launcher/common/http/LRequest;", "addInterceptor", "interceptor", "addInterceptors", "", "execute", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadCall {
    private final ArrayList<Interceptor<LRequest, Unit>> interceptors;
    private final LRequest request;

    public DownloadCall(LRequest lRequest) {
        Intrinsics.checkNotNullParameter(lRequest, "request");
        this.request = lRequest;
        this.interceptors = new ArrayList<>();
    }

    public final DownloadCall addInterceptor(Interceptor<LRequest, Unit> interceptor) {
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        this.interceptors.add(interceptor);
        return this;
    }

    public final DownloadCall addInterceptors(List<? extends Interceptor<LRequest, Unit>> interceptors) {
        if (interceptors != null) {
            this.interceptors.addAll(interceptors);
        }
        return this;
    }

    public final void execute() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DownloadCheckInterceptor());
        arrayList.addAll(this.interceptors);
        arrayList.add(new OkHttpDownload());
        new RealInterceptorChain(arrayList, this.request, 0, 4, null).process(this.request);
    }

    public final LRequest getRequest() {
        return this.request;
    }
}

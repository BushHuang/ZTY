package com.xuehai.launcher.common.http.download;

import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.interceptor.Interceptor;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\t\u0018\u00010\bJ\b\u0010\n\u001a\u00020\u0004H\u0007¨\u0006\u000b"}, d2 = {"Lcom/xuehai/launcher/common/http/download/DownloadManager;", "", "()V", "download", "", "request", "Lcom/xuehai/launcher/common/http/LRequest;", "interceptors", "", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "stopAll", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadManager {
    public static final DownloadManager INSTANCE = new DownloadManager();

    private DownloadManager() {
    }

    public static void download$default(DownloadManager downloadManager, LRequest lRequest, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        downloadManager.download(lRequest, list);
    }

    @JvmStatic
    public static final synchronized void stopAll() {
        DownloadTaskManager.stopAll();
    }

    public final void download(LRequest request, List<? extends Interceptor<LRequest, Unit>> interceptors) {
        Intrinsics.checkNotNullParameter(request, "request");
        new DownloadCall(request).addInterceptors(interceptors).addInterceptor(new DownloadRetryInterceptor()).execute();
    }
}

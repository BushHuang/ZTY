package com.xuehai.launcher.common.http.download;

import com.xh.xhcore.common.http.strategy.okhttp.download.DownloadOkHttp;
import com.xh.xhcore.common.http.strategy.xh.download.XHDownloadOkHttpProxy;
import com.xuehai.launcher.common.http.DownloadCallback;
import com.xuehai.launcher.common.log.MyLog;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0006J\u0016\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J\b\u0010\u0015\u001a\u00020\u000bH\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00048\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002¨\u0006\u0017"}, d2 = {"Lcom/xuehai/launcher/common/http/download/DownloadTaskManager;", "", "()V", "URL_CALLBACK", "Ljava/util/HashMap;", "", "Lcom/xuehai/launcher/common/http/download/DownloadTaskManager$DownloadCallbackBus;", "URL_MAP", "Lcom/xh/xhcore/common/http/strategy/xh/download/XHDownloadOkHttpProxy;", "getURL_MAP$annotations", "addDownloadCallbackBus", "", "url", "callbackBus", "addDownloadTask", "proxy", "getDownloadCallbackBus", "isDownloading", "", "removeDownloadCallbackBus", "removeDownloadTask", "stopAll", "DownloadCallbackBus", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadTaskManager {
    public static final DownloadTaskManager INSTANCE = new DownloadTaskManager();
    private static final HashMap<String, XHDownloadOkHttpProxy> URL_MAP = new HashMap<>();
    private static final HashMap<String, DownloadCallbackBus> URL_CALLBACK = new HashMap<>();

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001J\b\u0010\n\u001a\u00020\bH\u0002J\u001a\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J \u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u001a\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0016R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0004j\b\u0012\u0004\u0012\u00020\u0001`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0004j\b\u0012\u0004\u0012\u00020\u0001`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/xuehai/launcher/common/http/download/DownloadTaskManager$DownloadCallbackBus;", "Lcom/xuehai/launcher/common/http/DownloadCallback;", "()V", "callbacks", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "newCallbacks", "add", "", "callback", "mergeCallbacks", "onFailure", "errorCode", "", "errorMessage", "", "onProgress", "fileTotalSize", "", "fileDownSize", "speed", "onStart", "total", "onSuccess", "message", "savePath", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static class DownloadCallbackBus extends DownloadCallback {
        private final HashSet<DownloadCallback> callbacks = new HashSet<>();
        private final HashSet<DownloadCallback> newCallbacks = new HashSet<>();

        private final void mergeCallbacks() {
            if (!this.newCallbacks.isEmpty()) {
                this.callbacks.addAll(this.newCallbacks);
                this.newCallbacks.clear();
            }
        }

        public final void add(DownloadCallback callback) {
            if (callback != null) {
                this.newCallbacks.add(callback);
            }
        }

        @Override
        public void onFailure(int errorCode, String errorMessage) {
            mergeCallbacks();
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((DownloadCallback) it.next()).onFailure(errorCode, errorMessage);
            }
            this.callbacks.clear();
        }

        @Override
        public void onProgress(double fileTotalSize, double fileDownSize, double speed) {
            mergeCallbacks();
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((DownloadCallback) it.next()).onProgress(fileTotalSize, fileDownSize, speed);
            }
        }

        @Override
        public void onStart(double total) {
            mergeCallbacks();
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((DownloadCallback) it.next()).onStart(total);
            }
        }

        @Override
        public void onSuccess(String message, String savePath) {
            Intrinsics.checkNotNullParameter(savePath, "savePath");
            mergeCallbacks();
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((DownloadCallback) it.next()).onSuccess(message, savePath);
            }
            this.callbacks.clear();
        }
    }

    private DownloadTaskManager() {
    }

    @JvmStatic
    private static void getURL_MAP$annotations() {
    }

    @JvmStatic
    public static final synchronized void stopAll() {
        for (XHDownloadOkHttpProxy xHDownloadOkHttpProxy : URL_MAP.values()) {
            try {
                MyLog.d("Download[MDM]", "stop download: " + ((DownloadOkHttp) xHDownloadOkHttpProxy.getBaseRequest()).getUrl());
                xHDownloadOkHttpProxy.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        URL_MAP.clear();
        URL_CALLBACK.clear();
    }

    public final void addDownloadCallbackBus(String url, DownloadCallbackBus callbackBus) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(callbackBus, "callbackBus");
        URL_CALLBACK.put(url, callbackBus);
    }

    public final void addDownloadTask(String url, XHDownloadOkHttpProxy proxy) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        MyLog.d("Download[MDM]", "addDownloadTask : " + url);
        URL_MAP.put(url, proxy);
    }

    public final DownloadCallbackBus getDownloadCallbackBus(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return URL_CALLBACK.get(url);
    }

    public final boolean isDownloading(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return URL_MAP.keySet().contains(url);
    }

    public final void removeDownloadCallbackBus(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        URL_CALLBACK.remove(url);
    }

    public final void removeDownloadTask(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        MyLog.d("Download[MDM]", "removeDownloadTask : " + url);
        URL_MAP.remove(url);
    }
}

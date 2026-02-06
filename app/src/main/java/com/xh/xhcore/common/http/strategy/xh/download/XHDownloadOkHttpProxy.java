package com.xh.xhcore.common.http.strategy.xh.download;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.xh.networkclient.bean.BatchFileParam;
import com.xh.networkclient.bean.UrlDTO;
import com.xh.networkclient.common.CommonUtils;
import com.xh.xhcore.common.config.ServerNetworkConfig;
import com.xh.xhcore.common.constant.ConstServerBaseUrlKt;
import com.xh.xhcore.common.constant.ConstServerUrlKt;
import com.xh.xhcore.common.helper.LogIntervalHelper;
import com.xh.xhcore.common.http.XHHttpUtil;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.archi.HttpUtil;
import com.xh.xhcore.common.http.archi.ThreadUtil;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.ProgressResponseBody;
import com.xh.xhcore.common.http.strategy.okhttp.download.BaseDownloadOkHttp;
import com.xh.xhcore.common.http.strategy.okhttp.download.DownloadOkHttp;
import com.xh.xhcore.common.http.strategy.okhttp.interceptors.ProgressResponseInterceptor;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.security.DownloadSecurityUtil;
import com.xh.xhcore.common.http.strategy.xh.security.MultiDownloadSecurityConfig;
import com.xh.xhcore.common.http.strategy.xh.security.SingleDownloadSecurityConfig;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadUtil;
import com.xh.xhcore.common.statistic.connect.speed.download.BaseDownloadSpeedManager;
import com.xh.xhcore.common.statistic.connect.times.download.BaseDownloadTimesManager;
import com.xh.xhcore.common.statistic.connect.traffic.download.BaseDownloadTrafficManager;
import com.xh.xhcore.common.upload.XHTask;
import com.xh.xhcore.common.util.StringUtil;
import com.xh.xhcore.common.util.XHEncodeUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.json.JSONException;

public class XHDownloadOkHttpProxy extends XHDownloadProxy<XHDownloadOkHttpProxy, DownloadOkHttp> {
    private int downloadType;
    private boolean isTempUrl;
    private MultiDownloadSecurityConfig multiDownloadSecurityConfig;
    private String originalUrl;
    private SingleDownloadSecurityConfig singleDownloadSecurityConfig;
    private String[] urls;

    private class XHDownloadCallbackInC extends XHBaseRequestProxy<XHDownloadOkHttpProxy, DownloadOkHttp, XHRequestCallBack.XHDownloadCallBack>.XHBaseRequestCallbackInC {
        private File file;

        private XHDownloadCallbackInC() {
            super();
            this.file = null;
        }

        private void downloadOnEvent() {
            getOkHttpNetworkErrorUploader().appendErrorMessage(getFailedThrowable());
            ThreadUtil.toMainThreadChecked(XHDownloadOkHttpProxy.this.isExchangeToMainThread(), XHDownloadOkHttpProxy.this.getCallback(), new Runnable() {
                @Override
                public void run() {
                    if (!XHDownloadCallbackInC.this.isBusinessSuccessful()) {
                        XHDownloadOkHttpProxy.this.getCallback().failedUploadInner(XHDownloadCallbackInC.this.getCode(), "", null, XHDownloadCallbackInC.this.getOkHttpNetworkErrorUploader());
                    } else if (XHDownloadOkHttpProxy.this.downloadType == 0) {
                        XHDownloadOkHttpProxy.this.getCallback().successInner(XHDownloadOkHttpProxy.this.getSavePath());
                    } else if (XHDownloadOkHttpProxy.this.downloadType == 1) {
                        XHDownloadOkHttpProxy.this.getCallback().successInner(XHDownloadCallbackInC.this.getResponse());
                    }
                }
            });
        }

        private void uploadNetworkErrorAndHttpDownloadAgain(String str) {
            uploadNetworkError();
            XHDownloadOkHttpProxy.this.httpDownloadAgain(str);
        }

        @Override
        protected void deleteTempFile() {
            File file;
            if (XHDownloadOkHttpProxy.this.isDownloadMultiFiles() && (file = this.file) != null && file.exists()) {
                this.file.delete();
            }
        }

        @Override
        public String getResponseBodyString() {
            return "download response body!";
        }

        @Override
        protected void onEvent() {
            if (isUserCancelNetwork() || XHDownloadOkHttpProxy.this.hasFailedBefore) {
                downloadOnEvent();
                return;
            }
            if (XHDownloadOkHttpProxy.this.isDownloadMultiFiles()) {
                if (getCode() == CommonUtils.baseCode || !XHDownloadOkHttpProxy.this.downloadAgainWhenFail) {
                    downloadOnEvent();
                    return;
                }
                LogUtils.i("批量文件下载失败重试");
                uploadNetworkErrorAndHttpDownloadAgain(XHDownloadUtil.DOWNLOAD_FILES_BASE_URL + XHUploadUtil.getAppKeyFromUrl(XHDownloadOkHttpProxy.this.originalUrl));
                return;
            }
            if (getCode() == CommonUtils.baseCode) {
                if (!XHDownloadUtil.isHtmlFile(XHDownloadOkHttpProxy.this.getSavePath()) || !XHDownloadOkHttpProxy.this.downloadAgainWhenFail) {
                    downloadOnEvent();
                    return;
                }
                uploadNetworkErrorAndHttpDownloadAgain(XHDownloadUtil.DOWNLOAD_BASE_URL + XHEncodeUtil.base64EncodeDefault2String(XHDownloadOkHttpProxy.this.originalUrl));
                return;
            }
            if (getCode() == 107000509) {
                downloadOnEvent();
                return;
            }
            if (!XHDownloadOkHttpProxy.this.downloadAgainWhenFail) {
                downloadOnEvent();
                return;
            }
            uploadNetworkErrorAndHttpDownloadAgain(XHDownloadUtil.DOWNLOAD_BASE_URL + XHEncodeUtil.base64EncodeDefault2String(XHDownloadOkHttpProxy.this.originalUrl));
        }

        void onProgress(final int i, final double d, final double d2, final String str) {
            if (d == 0.0d) {
                return;
            }
            ThreadUtil.toMainThreadChecked(XHDownloadOkHttpProxy.this.isExchangeToMainThread(), XHDownloadOkHttpProxy.this.getCallback(), new Runnable() {
                @Override
                public void run() {
                    XHDownloadOkHttpProxy.this.getCallback().onProgress(i, d, d2, str);
                }
            });
        }

        @Override
        protected void readResponseBodyContent(Response response) throws IOException {
            File file;
            if (!isResponseSuccessful() || XHDownloadOkHttpProxy.this.downloadType == 1) {
                return;
            }
            String savePath = XHDownloadOkHttpProxy.this.getSavePath();
            if (XHDownloadOkHttpProxy.this.isDownloadMultiFiles()) {
                savePath = StringUtil.removeLastSeparator(savePath) + "/" + XHDownloadOkHttpProxy.this.getId() + ".zip";
            }
            this.file = BaseDownloadOkHttp.saveDownloadFile(response, savePath);
            if (XHDownloadOkHttpProxy.this.isDownloadMultiFiles() && (file = this.file) != null && file.exists()) {
                HttpUtil.unzip(this.file.getPath(), this.file.getParent(), new XHMultiDownloadUnZipListener(XHDownloadOkHttpProxy.this.getCallback(), XHDownloadOkHttpProxy.this.urls));
            }
        }

        @Override
        protected void verifyFileMd5() {
            File file;
            if (XHDownloadOkHttpProxy.this.isDownloadMultiFiles()) {
                return;
            }
            String md5FromResponseHeader = getMd5FromResponseHeader();
            if (TextUtils.isEmpty(md5FromResponseHeader) || (file = this.file) == null || !file.exists()) {
                return;
            }
            String strReplace = md5FromResponseHeader.trim().replace("\"", "");
            if (strReplace.length() != 32) {
                LogUtils.d("ETag value length is not 32, do not verify");
                return;
            }
            String fileMD5ToString = XHFileUtil.getFileMD5ToString(this.file);
            if (TextUtils.isEmpty(fileMD5ToString)) {
                return;
            }
            String str = "fileMd5=" + fileMD5ToString.trim() + ", fileMd5Strlen=" + fileMD5ToString.length();
            String str2 = "responseHeaderMd5=" + strReplace.trim() + ", len=" + strReplace.length();
            LogUtils.v(str);
            LogUtils.v(str2);
            if (fileMD5ToString.equalsIgnoreCase(strReplace)) {
                return;
            }
            getOkHttpNetworkErrorUploader().appendErrorMessage(str).appendErrorMessage(str2);
            setCodeAndDescription(107002001);
        }
    }

    XHDownloadOkHttpProxy(DownloadOkHttp downloadOkHttp) {
        super(downloadOkHttp);
        this.downloadType = 0;
        this.originalUrl = "";
        this.isTempUrl = false;
        this.multiDownloadSecurityConfig = null;
        this.singleDownloadSecurityConfig = null;
    }

    private void httpDownloadAgain(String str) {
        LogUtils.w("下载失败，重新下载；url = " + str);
        this.hasFailedBefore = true;
        doDownload(str);
    }

    private void replaceFileServerEnvironment() {
        if (TextUtils.isEmpty(((DownloadOkHttp) this.baseRequest).getUrl())) {
            setUrl(ConstServerUrlKt.FILE_SERVER_DOWNLOAD_ZIP);
        } else {
            setUrl(((DownloadOkHttp) this.baseRequest).getUrl().replace("filesoss.yunzuoye.net", ConstServerBaseUrlKt.BASE_URL_FILE_SERVER_HOST).replace("http://", "https://"));
        }
    }

    @Override
    protected void applyServerNetworkConfig(ServerNetworkConfig serverNetworkConfig) {
        super.applyServerNetworkConfig(serverNetworkConfig);
        if (serverNetworkConfig.isSocketConnectionTimeoutUploadDownloadValid()) {
            ((DownloadOkHttp) this.baseRequest).setConnectTimeout(serverNetworkConfig.getSocketConnectTimeoutMillisUploadDownload());
        }
        if (serverNetworkConfig.isSocketWriteTimeoutUploadDownloadValid()) {
            ((DownloadOkHttp) this.baseRequest).setWriteTimeout(serverNetworkConfig.getSocketWriteTimeoutMillisUploadDownload());
        }
        if (serverNetworkConfig.isSocketReadTimeoutUploadDownloadValid()) {
            ((DownloadOkHttp) this.baseRequest).setReadTimeout(serverNetworkConfig.getSocketReadTimeoutMillisUploadDownload());
        }
    }

    protected void doDownload(String str) {
        LogUtils.d("[doDownload] newDownloadUrl = " + str);
        String json = null;
        final XHDownloadCallbackInC xHDownloadCallbackInC = new XHDownloadCallbackInC();
        refreshId();
        ((DownloadOkHttp) this.baseRequest).setNetworkInterceptor((Interceptor) new ProgressResponseInterceptor(new ProgressResponseBody.ProgressResponseListener() {
            LogIntervalHelper logIntervalHelper = new LogIntervalHelper();

            @Override
            public void downloadProgress(long j, long j2, boolean z, long j3) throws JSONException {
                int i = (int) ((j * 100) / j2);
                String speedJson = XHHttpUtil.toSpeedJson(j3);
                this.logIntervalHelper.logDOnceInterval(100L, "下载进度：" + i + "% 下载速度 = " + speedJson);
                xHDownloadCallbackInC.onProgress(XHDownloadOkHttpProxy.this.getId(), (double) j2, (double) j, speedJson);
                if (z) {
                    BaseDownloadSpeedManager.getInstance(((DownloadOkHttp) XHDownloadOkHttpProxy.this.baseRequest).getUrl()).addItem(Long.valueOf(j3));
                    BaseDownloadTrafficManager.getInstance(((DownloadOkHttp) XHDownloadOkHttpProxy.this.baseRequest).getUrl()).addItem(Long.valueOf(j2));
                    BaseDownloadTimesManager.getInstance(((DownloadOkHttp) XHDownloadOkHttpProxy.this.baseRequest).getUrl()).addItem(1);
                    LogUtils.d("下载完成, 下载进度：" + i + "% 下载速度 = " + speedJson);
                }
            }
        }));
        if (isDownloadMultiFiles()) {
            BatchFileParam batchFileParam = new BatchFileParam();
            ArrayList arrayList = new ArrayList();
            if (getUrls() != null) {
                for (int i = 0; i < getUrls().length; i++) {
                    arrayList.add(new UrlDTO(getUrls()[i]));
                }
                batchFileParam.setUrls(arrayList);
                json = new Gson().toJson(batchFileParam);
            }
            ((DownloadOkHttp) this.baseRequest).setMethod("POST");
            ((DownloadOkHttp) this.baseRequest).setRequestBody(json);
            DownloadSecurityUtil.secureNoExchangeTempUrl(this.baseRequest, this.multiDownloadSecurityConfig);
        } else if (this.hasFailedBefore) {
            DownloadSecurityUtil.secureNoExchangeTempUrl(this.baseRequest, this.singleDownloadSecurityConfig);
        }
        ((DownloadOkHttp) ((DownloadOkHttp) this.baseRequest).setUrl(str)).setCallback(xHDownloadCallbackInC);
        callProxyRequest();
    }

    @Override
    protected String getSavePath() {
        return ((DownloadOkHttp) this.baseRequest).getLocalPath();
    }

    public SingleDownloadSecurityConfig getSingleDownloadSecurityConfig() {
        return this.singleDownloadSecurityConfig;
    }

    public String[] getUrls() {
        return this.urls;
    }

    protected boolean isDownloadMultiFiles() {
        return this.urls != null;
    }

    @Override
    protected XHDownloadOkHttpProxy requestInner() {
        if (isDownloadMultiFiles()) {
            replaceFileServerEnvironment();
            this.urls = XHDownloadUtil.exchangeUrls(this.urls);
        } else {
            if (TextUtils.isEmpty(getUrl())) {
                getCallback().failedUploadInner(107001017, "", new Throwable(), getDownStreamOrOssNetworkErrorUploader());
                return this;
            }
            setUrl(XHDownloadUtil.exchangeUrl(getUrl()));
            if (!this.isTempUrl && DownloadSecurityUtil.secureExchangeTempUrl(this, this.singleDownloadSecurityConfig, getUrl())) {
                return this;
            }
        }
        this.originalUrl = getUrl();
        doDownload(getUrl());
        return this;
    }

    public XHDownloadOkHttpProxy setBatchDownloadUrl(String str) {
        return (XHDownloadOkHttpProxy) setUrl(str);
    }

    public XHDownloadOkHttpProxy setDownloadType(int i) {
        this.downloadType = i;
        return this;
    }

    public XHDownloadOkHttpProxy setIsTempUrl(boolean z) {
        this.isTempUrl = z;
        return this;
    }

    public XHDownloadOkHttpProxy setMultiDownloadSecurityConfigs(MultiDownloadSecurityConfig multiDownloadSecurityConfig) {
        this.multiDownloadSecurityConfig = multiDownloadSecurityConfig;
        return this;
    }

    @Override
    public XHDownloadOkHttpProxy setSavePath(String str) {
        ((DownloadOkHttp) this.baseRequest).setLocalPath(str);
        return this;
    }

    public XHDownloadOkHttpProxy setSingleDownloadSecurityConfig(SingleDownloadSecurityConfig singleDownloadSecurityConfig) {
        this.singleDownloadSecurityConfig = singleDownloadSecurityConfig;
        return this;
    }

    public XHDownloadOkHttpProxy setUrls(String[] strArr) {
        this.urls = strArr;
        return this;
    }

    @Override
    public int stop(XHTask xHTask) {
        cancel();
        return 0;
    }
}

package com.xh.xhcore.common.http;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.callback.BaseNetworkFailedHandlerCallback;
import com.xh.xhcore.common.http.strategy.xh.callback.uploader.BaseNetworkErrorUploader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import okhttp3.Response;

public interface XHRequestCallBack {

    public static abstract class HttpCallBack<T> extends BaseNetworkFailedHandlerCallback {
        Gson gson;

        @Deprecated
        private String httpMsg;
        private String httpResponse;
        private boolean treatAsSuccessWhenParsedResponseIsNull;

        public HttpCallBack() {
            this.gson = new Gson();
            this.treatAsSuccessWhenParsedResponseIsNull = true;
        }

        public HttpCallBack(boolean z) {
            this.gson = new Gson();
            this.treatAsSuccessWhenParsedResponseIsNull = true;
            this.treatAsSuccessWhenParsedResponseIsNull = z;
        }

        private boolean isTypeString(Type type) {
            return type != null && (type instanceof Class) && "java.lang.String".equals(((Class) type).getCanonicalName());
        }

        private void successInner(T t) {
            LogUtils.d("xhNetworkExecuteStateTag", "xhNetworkEnd success");
            success(t);
        }

        private Type tClass() {
            Type genericSuperclass = getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                return ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            }
            return null;
        }

        @Deprecated
        private void uploadNetworkErrorLog() {
            if (TextUtils.isEmpty(this.httpMsg)) {
                return;
            }
            LogManager.getInstance().uploadNetworkErrorLog(new LinkedHashMap<String, Object>() {
                {
                    put("httpMsg", HttpCallBack.this.httpMsg);
                }
            });
        }

        public boolean businessFailed(int i, String str) {
            return false;
        }

        @Deprecated
        public void failed(int i, String str, String str2) {
            this.httpMsg = str2;
            failed(i, str);
            uploadNetworkErrorLog();
        }

        @Deprecated
        public void failed(int i, String str, String str2, String str3) {
            this.httpMsg = str3;
            this.httpResponse = str2;
            failed(i, str);
            uploadNetworkErrorLog();
        }

        public void failedUploadInner(int i, String str, String str2, Throwable th, BaseNetworkErrorUploader baseNetworkErrorUploader) {
            this.httpResponse = str2;
            failedUploadInner(i, str, th, baseNetworkErrorUploader);
        }

        public String getBusinessErrorMsg() {
            return this.httpResponse;
        }

        @Deprecated
        public String getHttpMsg() {
            return this.httpMsg;
        }

        public T getTFromString(String str, Type type) throws JsonSyntaxException {
            if (!isTypeString(type)) {
                return type == null ? str : (T) this.gson.fromJson(str, type);
            }
            if (!XHAppConfigProxy.getInstance().enableResponseStringJsonCheck() || str == 0 || "".equals(str.trim())) {
                return str;
            }
            new JsonParser().parse(str);
            return str;
        }

        public abstract void success(T t);

        @Deprecated
        final void successParse(String str) {
            try {
                success(getTFromString(str, tClass()));
            } catch (Exception e) {
                e.printStackTrace();
                failed(-1, "网关错误:网络异常，请检查网络！");
            }
        }

        public final void successParse(String str, BaseNetworkErrorUploader baseNetworkErrorUploader) {
            try {
                T tFromString = getTFromString(str, tClass());
                if (tFromString != null || this.treatAsSuccessWhenParsedResponseIsNull) {
                    successInner(tFromString);
                } else {
                    failedUploadInner(107001205, "", str, new Throwable(), baseNetworkErrorUploader);
                }
            } catch (Exception e) {
                failedUploadInner(107001204, "", str, new Throwable(e), baseNetworkErrorUploader);
            }
        }
    }

    public static abstract class XHDownloadCallBack extends BaseNetworkFailedHandlerCallback {
        protected List<Integer> multiDownloadStatusCodeList = null;

        protected final boolean isSuccess(int i) {
            return i == 200;
        }

        public void onProgress(int i, double d, double d2, String str) {
        }

        public void onRecvDate(int i, byte[] bArr, int i2, String str) {
        }

        public void setMultiDownloadStatusCodeListInner(List<Integer> list) {
            this.multiDownloadStatusCodeList = list;
            LogUtils.d("multiDownloadStatusCodeList = " + list);
        }

        public abstract void success(String str);

        public void successInner(String str) {
            LogUtils.d("xhNetworkExecuteStateTag", "xhNetworkEnd success savePath = " + str + " multiDownloadStatusCodeList = " + this.multiDownloadStatusCodeList);
            List<Integer> list = this.multiDownloadStatusCodeList;
            if (list == null) {
                success(str);
                return;
            }
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                if (!isSuccess(it.next().intValue())) {
                    failed(107002004, XHErrorCodeUtil.getErrorMsgInfo(107002004));
                    return;
                }
            }
            success(str);
        }

        public void successInner(Response response) {
        }
    }

    public static abstract class XHUploadCallBack extends BaseNetworkFailedHandlerCallback {
        private String jsonParam;

        public String getJsonParam() {
            return this.jsonParam;
        }

        public void onProgress(int i, double d, double d2, String str) {
        }

        public void onRecvDate(int i, byte[] bArr, int i2, String str) {
        }

        public void setJsonParam(String str) {
            this.jsonParam = str;
        }

        public abstract void success(int i, String str);

        public final void successInner(int i, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("xhNetworkEnd success reqId = ");
            sb.append(i);
            sb.append(" filePath = ");
            sb.append(str != null ? str : "null");
            LogUtils.d("xhNetworkExecuteStateTag", sb.toString());
            success(i, str);
        }
    }
}

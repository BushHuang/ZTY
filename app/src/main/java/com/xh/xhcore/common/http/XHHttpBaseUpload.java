package com.xh.xhcore.common.http;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.xh.logutils.LogManager;
import com.xh.networkclient.HttpRequest;
import com.xh.networkclient.common.CommonUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.HttpReq;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.XHUploadFilesResBean;
import com.xh.xhcore.common.http.archi.ThreadUtil;
import com.xh.xhcore.common.http.strategy.HttpConst;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadProxy;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadUtil;
import com.xh.xhcore.common.http.strategy.xh.upload.aksk.AkSkUtil;
import com.xh.xhcore.common.http.strategy.xh.upload.aksk.OnAkSkCallback;
import com.xh.xhcore.common.oss.OssConfig;
import com.xh.xhcore.common.util.StringUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xh.xhcore.common.util.XHLog;
import com.xh.xhcore.common.util.XHNetworkUtil;
import java.io.File;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class XHHttpBaseUpload {
    private static final int FILE_UPLOAD_SUCCESS = 1000;
    private static int INT_MAX = Integer.MAX_VALUE;
    private static final String LARGE_FILE_INSTANTS_SERVER_PATH = "http://filesoss.yunzuoye.net/XHFileServer/file/upload/instant/";
    private static final String LARGE_FILE_SERVER_PATH = "http://filesoss.yunzuoye.net/XHFileServer/file/upload/largefile/";
    private static final String TAG = "XHHttpBaseUpload";

    class AnonymousClass1 implements OnAkSkCallback {
        final XHRequestCallBack.XHUploadCallBack val$callBack;
        final boolean val$exchangeToMainThread;
        final String val$jsonParam;
        final String val$localPath;
        final String val$url;

        AnonymousClass1(String str, String str2, String str3, boolean z, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
            this.val$url = str;
            this.val$localPath = str2;
            this.val$jsonParam = str3;
            this.val$exchangeToMainThread = z;
            this.val$callBack = xHUploadCallBack;
        }

        @Override
        public void failed(final int i, final String str) {
            boolean z = this.val$exchangeToMainThread;
            final XHRequestCallBack.XHUploadCallBack xHUploadCallBack = this.val$callBack;
            ThreadUtil.toMainThreadChecked(z, xHUploadCallBack, new Runnable() {
                @Override
                public final void run() {
                    xHUploadCallBack.failed(i, str);
                }
            });
        }

        @Override
        public void success(String str, String str2, String str3) {
            XHHttpBaseUpload.largeFileUpload(this.val$url, this.val$localPath, this.val$jsonParam, this.val$exchangeToMainThread, str, str2, str3, true, this.val$callBack);
        }
    }

    class AnonymousClass5 implements HttpReq.IRequestCallBack {
        final XHRequestCallBack.XHUploadCallBack val$callBack;
        final boolean val$exchangeToMainThread;
        final String val$localPath;
        final String val$requestJsonParam;
        final String val$url;

        AnonymousClass5(String str, String str2, String str3, boolean z, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
            this.val$url = str;
            this.val$localPath = str2;
            this.val$requestJsonParam = str3;
            this.val$exchangeToMainThread = z;
            this.val$callBack = xHUploadCallBack;
        }

        @Override
        public void onEvent(final int i, final int i2, final String str, final String str2) {
            XHLog.i("XHHttpBaseUpload", "id = [" + i + "], code = [" + i2 + "], description = [" + str + "], jsonParam = [" + str2 + "]");
            if (i2 != CommonUtils.baseCode) {
                XHHttpBaseUpload.exchangeUploadToOss(this.val$url, this.val$localPath, this.val$requestJsonParam, this.val$exchangeToMainThread, this.val$callBack);
                return;
            }
            XHRequestCallBack.XHUploadCallBack xHUploadCallBack = this.val$callBack;
            if (xHUploadCallBack == null) {
                return;
            }
            if (!this.val$exchangeToMainThread) {
                XHHttpBaseUpload.callbackResponse(i, i2, str, str2, xHUploadCallBack);
                return;
            }
            Handler handler = XhBaseApplication.mMainHandler;
            final XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = this.val$callBack;
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    XHHttpBaseUpload.callbackResponse(i, i2, str, str2, xHUploadCallBack2);
                }
            });
        }

        @Override
        public void onProgress(final int i, final double d, final double d2, final String str) {
            if (this.val$exchangeToMainThread) {
                XhBaseApplication.mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (AnonymousClass5.this.val$callBack instanceof XHRequestCallBack.XHUploadCallBack) {
                            AnonymousClass5.this.val$callBack.setJsonParam(str);
                            AnonymousClass5.this.val$callBack.onProgress(i, d, d2, str);
                        }
                    }
                });
                return;
            }
            XHRequestCallBack.XHUploadCallBack xHUploadCallBack = this.val$callBack;
            if (xHUploadCallBack instanceof XHRequestCallBack.XHUploadCallBack) {
                xHUploadCallBack.setJsonParam(str);
                this.val$callBack.onProgress(i, d, d2, str);
            }
        }

        @Override
        public void onRecvDate(final int i, final byte[] bArr, final int i2, final String str) {
            if (this.val$exchangeToMainThread) {
                XhBaseApplication.mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (AnonymousClass5.this.val$callBack instanceof XHRequestCallBack.XHUploadCallBack) {
                            AnonymousClass5.this.val$callBack.onRecvDate(i, bArr, i2, str);
                        }
                    }
                });
                return;
            }
            XHRequestCallBack.XHUploadCallBack xHUploadCallBack = this.val$callBack;
            if (xHUploadCallBack instanceof XHRequestCallBack.XHUploadCallBack) {
                xHUploadCallBack.onRecvDate(i, bArr, i2, str);
            }
        }
    }

    class AnonymousClass6 implements OnAkSkCallback {
        final XHRequestCallBack.XHUploadCallBack val$callBack;
        final boolean val$exchangeToMainThread;
        final String val$jsonParam;
        final String val$localPath;
        final String val$url;

        AnonymousClass6(String str, String str2, String str3, boolean z, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
            this.val$url = str;
            this.val$localPath = str2;
            this.val$jsonParam = str3;
            this.val$exchangeToMainThread = z;
            this.val$callBack = xHUploadCallBack;
        }

        @Override
        public void failed(final int i, final String str) {
            boolean z = this.val$exchangeToMainThread;
            final XHRequestCallBack.XHUploadCallBack xHUploadCallBack = this.val$callBack;
            ThreadUtil.toMainThreadChecked(z, xHUploadCallBack, new Runnable() {
                @Override
                public final void run() {
                    xHUploadCallBack.failed(i, str);
                }
            });
        }

        @Override
        public void success(String str, String str2, String str3) {
            XHHttpBaseUpload.exchangeUploadToOss(this.val$url, this.val$localPath, this.val$jsonParam, this.val$exchangeToMainThread, str, str2, str3, true, this.val$callBack);
        }
    }

    private static void backupToFileServer(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        String str5 = "http://filesoss.yunzuoye.net/XHFileServer/file/upload/largefile/" + str + "/" + str4 + "/" + getFileSuffixFromFilePath(str2);
        XHLog.d("XHHttpBaseUpload", "上传到文件服务器的文件备份到文件服务中的路径：" + str5);
        XHHttpBaseReq.requestWithObject(str5, HttpRequest.RequestMethod.POST, map, (Map<String, Object>) null, str3, (XHRequestCallBack.HttpCallBack) null);
    }

    private static void callFailedAndUploadLog(XHRequestCallBack.XHUploadCallBack xHUploadCallBack, final int i, final String str) {
        xHUploadCallBack.failed(i, str);
        LogManager.getInstance().uploadNetworkErrorLog(new LinkedHashMap<String, Object>() {
            {
                put("msg", i + " " + str);
            }
        });
    }

    private static void callbackResponse(int i, int i2, String str, String str2, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        if (i2 != CommonUtils.baseCode) {
            xHUploadCallBack.failed(i2, XHHttpBaseReq.getErrorMsg(i2, XHErrorCodeUtil.getErrorMsgInfo(i2, str)));
            return;
        }
        try {
            XHUploadResBean xHUploadResBean = (XHUploadResBean) new Gson().fromJson(new JsonParser().parse(str2).getAsJsonObject().get("httpResponse").getAsString(), XHUploadResBean.class);
            if (xHUploadResBean.status == 1000) {
                xHUploadCallBack.success(i, xHUploadResBean.uploadFileDTO.fileId);
            } else {
                xHUploadCallBack.failed(i2, XHHttpBaseReq.getErrorMsg(i2, "上传文件失败"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.getInstance().uploadNetworkErrorLog(new LinkedHashMap<String, Object>() {
                {
                    put("exception", e.getMessage());
                }
            });
            xHUploadCallBack.failed(i2, XHHttpBaseReq.getErrorMsg(i2, "服务器返回数据格式不正确！"));
        }
    }

    public static OSSAsyncTask exchangeUploadToOss(final String str, final String str2, final String str3, final boolean z, String str4, String str5, String str6, final boolean z2, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        String fileNameFromFilePath;
        LogUtils.i("XHHttpBaseUpload", "小文件上传失败后重新直传到阿里云");
        final String appKeyFromUrl = getAppKeyFromUrl(str);
        try {
            fileNameFromFilePath = getFileNameFromFilePath(str2);
        } catch (IllegalArgumentException e) {
            xHUploadCallBack.failed(-1, e.getMessage());
            fileNameFromFilePath = null;
        }
        String uploadPath = getUploadPath(str3, appKeyFromUrl);
        final String strReplace = UUID.randomUUID().toString().replace("-", "");
        PutObjectRequest putObjectRequestNewPutObjectRequest = OssConfig.newPutObjectRequest(OssConfig.getBucketName(), uploadPath + strReplace + "." + getFileSuffixFromFilePath(fileNameFromFilePath), str2);
        putObjectRequestNewPutObjectRequest.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest putObjectRequest, final long j, final long j2) {
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (xHUploadCallBack instanceof XHRequestCallBack.XHUploadCallBack) {
                                xHUploadCallBack.setJsonParam(null);
                                xHUploadCallBack.onProgress(0, j2, j, null);
                            }
                        }
                    });
                    return;
                }
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 instanceof XHRequestCallBack.XHUploadCallBack) {
                    xHUploadCallBack2.setJsonParam(null);
                    xHUploadCallBack.onProgress(0, j2, j, null);
                }
            }
        });
        int oSSTimeOut = getOSSTimeOut(str3);
        return new OssConfig().setConnectionTimeOut(oSSTimeOut).setSocketTimeOut(oSSTimeOut).getOss(XhBaseApplication.mContext, str4, str5, str6).asyncPutObject(putObjectRequestNewPutObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onFailure(PutObjectRequest putObjectRequest, final ClientException clientException, final ServiceException serviceException) {
                Log.d("PutObject", "onFailure");
                if (!z2 || !XHUploadUtil.isAliyunOssTokenError(serviceException)) {
                    XHHttpBaseUpload.extracted(clientException, serviceException, z, xHUploadCallBack);
                } else {
                    Log.d("exchangeUploadToOss", "重试一次oss的token");
                    AkSkUtil.reset(new OnAkSkCallback() {
                        @Override
                        public void failed(int i, String str7) {
                            XHHttpBaseUpload.extracted(clientException, serviceException, z, xHUploadCallBack);
                        }

                        @Override
                        public void success(String str7, String str8, String str9) {
                            XHHttpBaseUpload.exchangeUploadToOss(str, str2, str3, z, str7, str8, str9, false, xHUploadCallBack);
                        }
                    });
                }
            }

            @Override
            public void onSuccess(final PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", putObjectResult.getETag());
                Log.d("RequestId", putObjectResult.getRequestId());
                Log.d("StatusCode", putObjectResult.getStatusCode() + "");
                Log.d("ObjectKey", putObjectRequest.getObjectKey());
                XHHttpBaseUpload.backupToFileServer(appKeyFromUrl, str2, str3, strReplace);
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 == null) {
                    return;
                }
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            xHUploadCallBack.success(XHHttpBaseUpload.INT_MAX, "https://" + putObjectRequest.getBucketName() + ".oss-cn-hangzhou.aliyuncs.com/" + putObjectRequest.getObjectKey());
                        }
                    });
                    return;
                }
                xHUploadCallBack2.success(XHHttpBaseUpload.INT_MAX, "https://" + putObjectRequest.getBucketName() + ".oss-cn-hangzhou.aliyuncs.com/" + putObjectRequest.getObjectKey());
            }
        });
    }

    public static void exchangeUploadToOss(String str, String str2, String str3, boolean z, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        AkSkUtil.getAkSk(new AnonymousClass6(str, str2, str3, z, xHUploadCallBack));
    }

    private static int exchangeUploadToXueHai(String str, String str2, String str3, final boolean z, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        XHLog.i("XHHttpBaseUpload", "大文件上传失败之后重新上传到公司文件服务器");
        if (!XHNetworkUtil.isNetworkAvailable(XhBaseApplication.mContext)) {
            if (xHUploadCallBack == null) {
                return 0;
            }
            xHUploadCallBack.failed(-108000001, XHHttpBaseReq.getErrorMsg(-108000001, "没有网络连接，请连接网络！"));
            return 0;
        }
        final int iUpload = HttpReq.upload(str, str2, str3, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(final int i, final int i2, final String str4, final String str5) {
                XHLog.i("XHHttpBaseUpload", "id = [" + i + "], code = [" + i2 + "], description = [" + str4 + "], jsonParam = [" + str5 + "]");
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 == null) {
                    return;
                }
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            XHHttpBaseUpload.callbackResponse(i, i2, str4, str5, xHUploadCallBack);
                        }
                    });
                } else {
                    XHHttpBaseUpload.callbackResponse(i, i2, str4, str5, xHUploadCallBack2);
                }
            }

            @Override
            public void onProgress(final int i, final double d, final double d2, final String str4) {
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (xHUploadCallBack instanceof XHRequestCallBack.XHUploadCallBack) {
                                xHUploadCallBack.setJsonParam(str4);
                                xHUploadCallBack.onProgress(i, d, d2, str4);
                            }
                        }
                    });
                    return;
                }
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 instanceof XHRequestCallBack.XHUploadCallBack) {
                    xHUploadCallBack2.setJsonParam(str4);
                    xHUploadCallBack.onProgress(i, d, d2, str4);
                }
            }

            @Override
            public void onRecvDate(final int i, final byte[] bArr, final int i2, final String str4) {
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (xHUploadCallBack instanceof XHRequestCallBack.XHUploadCallBack) {
                                xHUploadCallBack.onRecvDate(i, bArr, i2, str4);
                            }
                        }
                    });
                    return;
                }
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 instanceof XHRequestCallBack.XHUploadCallBack) {
                    xHUploadCallBack2.onRecvDate(i, bArr, i2, str4);
                }
            }
        });
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    XHRequestCallBack.XHUploadCallBack xHUploadCallBack2;
                    int i = iUpload;
                    if (i > 0 || (xHUploadCallBack2 = xHUploadCallBack) == null) {
                        return;
                    }
                    xHUploadCallBack2.failed(i, XHHttpBaseReq.getErrorMsg(i, "请求下发失败"));
                }
            });
        } else if (iUpload <= 0 && xHUploadCallBack != null) {
            xHUploadCallBack.failed(iUpload, XHHttpBaseReq.getErrorMsg(iUpload, "请求下发失败"));
        }
        return iUpload;
    }

    private static void extracted(final ClientException clientException, final ServiceException serviceException, boolean z, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public final void run() {
                    XHHttpBaseUpload.failCallBack(clientException, serviceException, xHUploadCallBack);
                }
            });
        } else {
            failCallBack(clientException, serviceException, xHUploadCallBack);
        }
    }

    private static void failCallBack(ClientException clientException, ServiceException serviceException, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        if (clientException != null) {
            clientException.printStackTrace();
            callFailedAndUploadLog(xHUploadCallBack, 107003000, "客户端错误：" + clientException.getMessage());
        }
        if (serviceException != null) {
            Log.e("ServiceException", serviceException.toString());
            callFailedAndUploadLog(xHUploadCallBack, 107003000, "服务器错误：" + serviceException.getErrorCode() + ":" + serviceException.getMessage());
        }
    }

    private static int fileUpload(String str, String str2, String str3, boolean z, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        if (!XHNetworkUtil.isNetworkAvailable(XhBaseApplication.mContext)) {
            if (xHUploadCallBack == null) {
                return 0;
            }
            xHUploadCallBack.failed(-108000001, XHHttpBaseReq.getErrorMsg(-108000001, "没有网络连接，请连接网络！"));
            return 0;
        }
        final int iUpload = HttpReq.upload(str, str2, str3, new AnonymousClass5(str, str2, str3, z, xHUploadCallBack));
        if (iUpload <= 0) {
            exchangeUploadToOss(str, str2, str3, z, xHUploadCallBack);
        } else if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public final void run() {
                    XHHttpBaseUpload.lambda$fileUpload$2(iUpload, xHUploadCallBack);
                }
            });
        } else if (iUpload <= 0 && xHUploadCallBack != null) {
            xHUploadCallBack.failed(iUpload, XHHttpBaseReq.getErrorMsg(iUpload, "请求下发失败"));
        }
        return iUpload;
    }

    protected static String getAppKeyFromUrl(String str) {
        return StringUtil.getLastNonEmptySegment(str);
    }

    private static String getFileInstantUploadUrl(String str) {
        return "http://filesoss.yunzuoye.net/XHFileServer/file/upload/instant/" + str;
    }

    protected static String getFileNameFromFilePath(String str) throws IllegalArgumentException {
        String[] strArrSplit = str.split("/");
        if (strArrSplit.length <= 1) {
            throw new IllegalArgumentException("文件路径格式不正确");
        }
        String str2 = strArrSplit[strArrSplit.length - 1];
        if (str2.contains(".")) {
            return str2;
        }
        return str2 + ".xh";
    }

    protected static String getFileSuffixFromFilePath(String str) {
        if (str.endsWith("/") || !str.contains(".")) {
            return "xh";
        }
        String[] strArrSplit = str.split("\\.");
        return strArrSplit.length > 0 ? strArrSplit[strArrSplit.length - 1] : "xh";
    }

    private static int getOSSTimeOut(String str) {
        if (TextUtils.isEmpty(str)) {
            return 15000;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("timeout")) {
                return jSONObject.getInt("timeout") * 1000;
            }
            return 15000;
        } catch (Exception e) {
            e.printStackTrace();
            return 15000;
        }
    }

    private static String getOssErrorMsg(ClientException clientException) {
        return clientException.getCause() instanceof SocketTimeoutException ? "文件上传超时，请重试！" : clientException.getCause() instanceof UnknownHostException ? "网络不通，请检查网络！" : clientException.getCause() instanceof ConnectException ? "网络连接失败，请重试！" : clientException.getMessage();
    }

    private static String getUploadPath(String str, String str2) {
        StringBuilder sb;
        String strOptString = "";
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("headList");
                if (jSONObjectOptJSONObject != null) {
                    strOptString = jSONObjectOptJSONObject.optString("Folder");
                }
            } catch (Exception e) {
                LogUtils.i("get headList failed ", e);
            }
        }
        if (TextUtils.isEmpty(strOptString)) {
            sb = new StringBuilder();
            sb.append(str2);
        } else {
            sb = new StringBuilder();
            sb.append(str2);
            sb.append("/");
            sb.append(strOptString);
        }
        sb.append("/");
        return sb.toString();
    }

    public static int httpUpload(String str, String str2, String str3, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        return httpUpload(str, str2, str3, true, xHUploadCallBack);
    }

    public static int httpUpload(String str, String str2, String str3, boolean z, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        return XHAppConfigProxy.getInstance().getRequestStrategy() == HttpConst.RequestStrategy.STRATEGY_LIB_CURL ? XHFileUtil.getFileLength(str2) >= 16777216 ? largeFileUpload(str, str2, str3, z, xHUploadCallBack) : fileUpload(str, str2, str3, z, xHUploadCallBack) : ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) XHUploadProxy.createOkHttp().setUrl(str)).setLocalPath(str2).setRequestJsonParams(str3)).setExchangeToMainThread(z)).setCallback(xHUploadCallBack)).request()).getId();
    }

    public static int httpUploadFiles(final String str, final String[] strArr, final String str2, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        return XHAppConfigProxy.getInstance().getRequestStrategy() == HttpConst.RequestStrategy.STRATEGY_LIB_CURL ? HttpReq.upload(str, strArr, str2, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(int i, int i2, String str3, String str4) {
                XHLog.i("XHHttpBaseUpload", "id = [" + i + "], code = [" + i2 + "], description = [" + str3 + "], jsonParam = [" + str4 + "]");
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 == null) {
                    return;
                }
                xHUploadCallBack2.setJsonParam(str4);
                if (i2 != CommonUtils.baseCode) {
                    XHHttpBaseUpload.uploadFilesToOss(str, strArr, str2, xHUploadCallBack);
                    return;
                }
                try {
                    new JSONArray(new JSONObject(str4).getString("httpResponse"));
                    xHUploadCallBack.success(i, str4);
                } catch (Exception e) {
                    e.printStackTrace();
                    XHHttpBaseUpload.uploadFilesToOss(str, strArr, str2, xHUploadCallBack);
                }
            }

            @Override
            public void onProgress(int i, double d, double d2, String str3) {
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 != null) {
                    xHUploadCallBack2.onProgress(i, d, d2, str3);
                }
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str3) {
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 != null) {
                    xHUploadCallBack2.onRecvDate(i, bArr, i2, str3);
                }
            }
        }) : ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) XHUploadProxy.createOkHttp().setUrl(str)).setLocalPaths(strArr).setRequestJsonParams(str2)).setCallback(xHUploadCallBack)).setExchangeToMainThread(false)).request()).getId();
    }

    private static int internalUploadFilesToOss(String str, String[] strArr, String str2, String str3, String str4, String str5, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        String[] strArr2 = strArr;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final int length = strArr2.length;
        final SparseArray sparseArray = new SparseArray();
        final SparseArray sparseArray2 = new SparseArray();
        ArrayList arrayList = new ArrayList();
        int length2 = 0;
        for (int i = 0; i < length; i++) {
            length2 = (int) (length2 + new File(strArr2[i]).length());
            sparseArray.put(i, 0L);
        }
        int i2 = 0;
        while (i2 < length) {
            String str6 = strArr2[i2];
            final int i3 = i2;
            final int i4 = length2;
            final ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = arrayList;
            arrayList3.add(exchangeUploadToOss(str, str6, str2, false, str3, str4, str5, true, new XHRequestCallBack.XHUploadCallBack() {
                @Override
                public void failed(int i5, String str7) {
                    synchronized (sparseArray) {
                        if (!XHUploadUtil.hasFailedBefore(sparseArray)) {
                            xHUploadCallBack.failed(i5, str7);
                        }
                        sparseArray.put(i3, -1L);
                    }
                }

                @Override
                public void onProgress(int i5, double d, double d2, String str7) {
                    super.onProgress(i5, d, d2, str7);
                    synchronized (sparseArray) {
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                        sparseArray.put(i3, Long.valueOf((long) d2));
                        long jLongValue = 0;
                        for (int i6 = 0; i6 < length; i6++) {
                            Long l = (Long) sparseArray.get(i6);
                            if (l.longValue() < 0) {
                                Iterator it = arrayList2.iterator();
                                while (it.hasNext()) {
                                    ((OSSAsyncTask) it.next()).cancel();
                                }
                                return;
                            }
                            jLongValue += l.longValue();
                        }
                        long j = jCurrentTimeMillis2 > 0 ? (long) (jLongValue / (jCurrentTimeMillis2 / 1000.0f)) : 0L;
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("speed", XHHttpUtil.toByteUnit(j));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        xHUploadCallBack.onProgress(i5, i4, jLongValue, jSONObject.toString());
                    }
                }

                @Override
                public void success(int i5, String str7) {
                    synchronized (sparseArray2) {
                        XHUploadFilesResBean.UploadResBean uploadResBean = new XHUploadFilesResBean.UploadResBean();
                        uploadResBean.setUrl(str7);
                        sparseArray2.put(i3, uploadResBean);
                        if (sparseArray2.size() == length) {
                            XHUploadFilesResBean xHUploadFilesResBean = new XHUploadFilesResBean();
                            ArrayList arrayList4 = new ArrayList();
                            for (int i6 = 0; i6 < sparseArray2.size(); i6++) {
                                arrayList4.add((XHUploadFilesResBean.UploadResBean) sparseArray2.get(sparseArray2.keyAt(i6)));
                            }
                            Gson gson = new Gson();
                            xHUploadFilesResBean.setHttpResponse(gson.toJson(arrayList4));
                            xHUploadCallBack.success(i5, gson.toJson(xHUploadFilesResBean));
                        }
                    }
                }
            }));
            i2++;
            arrayList = arrayList3;
            length2 = i4;
            strArr2 = strArr;
        }
        return INT_MAX;
    }

    static void lambda$fileUpload$2(int i, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        if (i > 0 || xHUploadCallBack == null) {
            return;
        }
        xHUploadCallBack.failed(i, XHHttpBaseReq.getErrorMsg(i, "请求下发失败"));
    }

    static void lambda$largeFileUpload$0(XHRequestCallBack.XHUploadCallBack xHUploadCallBack, long j, long j2) {
        if (xHUploadCallBack instanceof XHRequestCallBack.XHUploadCallBack) {
            xHUploadCallBack.setJsonParam(null);
            xHUploadCallBack.onProgress(0, j, j2, null);
        }
    }

    static void lambda$largeFileUpload$1(boolean z, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack, PutObjectRequest putObjectRequest, final long j, final long j2) {
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public final void run() {
                    XHHttpBaseUpload.lambda$largeFileUpload$0(xHUploadCallBack, j2, j);
                }
            });
        } else if (xHUploadCallBack instanceof XHRequestCallBack.XHUploadCallBack) {
            xHUploadCallBack.setJsonParam(null);
            xHUploadCallBack.onProgress(0, j2, j, null);
        }
    }

    public static int largeFileUpload(String str, String str2, String str3, boolean z, XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        AkSkUtil.getAkSk(new AnonymousClass1(str, str2, str3, z, xHUploadCallBack));
        return 0;
    }

    private static void largeFileUpload(final String str, final String str2, final String str3, final boolean z, String str4, String str5, String str6, final boolean z2, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        String fileNameFromFilePath;
        final String appKeyFromUrl = getAppKeyFromUrl(str);
        try {
            fileNameFromFilePath = getFileNameFromFilePath(str2);
        } catch (IllegalArgumentException e) {
            xHUploadCallBack.failed(-1, e.getMessage());
            fileNameFromFilePath = null;
        }
        String uploadPath = getUploadPath(str3, appKeyFromUrl);
        final String strReplace = UUID.randomUUID().toString().replace("-", "");
        PutObjectRequest putObjectRequestNewPutObjectRequest = OssConfig.newPutObjectRequest(OssConfig.getBucketName(), uploadPath + strReplace + "." + getFileSuffixFromFilePath(fileNameFromFilePath), str2);
        putObjectRequestNewPutObjectRequest.setProgressCallback(new OSSProgressCallback() {
            @Override
            public final void onProgress(Object obj, long j, long j2) {
                XHHttpBaseUpload.lambda$largeFileUpload$1(z, xHUploadCallBack, (PutObjectRequest) obj, j, j2);
            }
        });
        int oSSTimeOut = getOSSTimeOut(str3);
        new OssConfig().setConnectionTimeOut(oSSTimeOut).setSocketTimeOut(oSSTimeOut).getOss(XhBaseApplication.mContext, str4, str5, str6).asyncPutObject(putObjectRequestNewPutObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onFailure(PutObjectRequest putObjectRequest, ClientException clientException, ServiceException serviceException) {
                Log.d("PutObject", "onFailure");
                if (z2 && XHUploadUtil.isAliyunOssTokenError(serviceException)) {
                    AkSkUtil.reset(new OnAkSkCallback() {
                        @Override
                        public void failed(int i, String str7) {
                            XHHttpBaseUpload.exchangeUploadToXueHai(XHHttpBaseUpload.getFileInstantUploadUrl(appKeyFromUrl), str2, str3, z, xHUploadCallBack);
                        }

                        @Override
                        public void success(String str7, String str8, String str9) {
                            XHHttpBaseUpload.largeFileUpload(str, str2, str3, z, str7, str8, str9, false, xHUploadCallBack);
                        }
                    });
                } else {
                    XHHttpBaseUpload.exchangeUploadToXueHai(XHHttpBaseUpload.getFileInstantUploadUrl(appKeyFromUrl), str2, str3, z, xHUploadCallBack);
                }
            }

            @Override
            public void onSuccess(final PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", putObjectResult.getETag());
                Log.d("RequestId", putObjectResult.getRequestId());
                Log.d("StatusCode", putObjectResult.getStatusCode() + "");
                Log.d("ObjectKey", putObjectRequest.getObjectKey());
                XHHttpBaseUpload.backupToFileServer(appKeyFromUrl, str2, str3, strReplace);
                XHRequestCallBack.XHUploadCallBack xHUploadCallBack2 = xHUploadCallBack;
                if (xHUploadCallBack2 == null) {
                    return;
                }
                if (z) {
                    XhBaseApplication.mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            xHUploadCallBack.success(XHHttpBaseUpload.INT_MAX, "https://" + putObjectRequest.getBucketName() + ".oss-cn-hangzhou.aliyuncs.com/" + putObjectRequest.getObjectKey());
                        }
                    });
                    return;
                }
                xHUploadCallBack2.success(XHHttpBaseUpload.INT_MAX, "https://" + putObjectRequest.getBucketName() + ".oss-cn-hangzhou.aliyuncs.com/" + putObjectRequest.getObjectKey());
            }
        });
        if (z2) {
            INT_MAX--;
        }
    }

    public static void uploadFilesToOss(final String str, final String[] strArr, final String str2, final XHRequestCallBack.XHUploadCallBack xHUploadCallBack) {
        AkSkUtil.getAkSk(new OnAkSkCallback() {
            @Override
            public void failed(int i, String str3) {
                xHUploadCallBack.failed(i, str3);
            }

            @Override
            public void success(String str3, String str4, String str5) {
                XHHttpBaseUpload.internalUploadFilesToOss(str, strArr, str2, str3, str4, str5, xHUploadCallBack);
            }
        });
    }
}

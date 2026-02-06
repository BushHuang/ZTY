package com.xh.xhcore.common.http.strategy.xh.upload;

import android.text.TextUtils;
import android.util.SparseArray;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.gson.Gson;
import com.xh.logutils.Log;
import com.xh.networkclient.bean.UploadFileRes;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.FileMD5;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.ServerNetworkConfig;
import com.xh.xhcore.common.constant.ConstServerBaseUrlKt;
import com.xh.xhcore.common.constant.ConstServerUrlKt;
import com.xh.xhcore.common.helper.LogIntervalHelper;
import com.xh.xhcore.common.http.XHHttpUtil;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.XHUploadFilesResBean;
import com.xh.xhcore.common.http.XHUploadResBean;
import com.xh.xhcore.common.http.archi.HttpUtil;
import com.xh.xhcore.common.http.archi.ThreadUtil;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.BaseProgressRequestListener;
import com.xh.xhcore.common.http.strategy.okhttp.upload.UploadOkHttp;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.security.UploadSecurityConfig;
import com.xh.xhcore.common.http.strategy.xh.security.UploadSecurityUtil;
import com.xh.xhcore.common.http.strategy.xh.upload.aksk.AkSkUtil;
import com.xh.xhcore.common.http.strategy.xh.upload.aksk.OnAkSkCallback;
import com.xh.xhcore.common.http.strategy.xh.upload.bean.UploadFileEntity;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.OnOsUploadCallback;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.OsDomainHelper;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.OsTypeUploadHelper;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.DomainObjectStorageConfig;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsResponse;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsUploadConfig;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsUploadFilesBean;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.exception.OsException;
import com.xh.xhcore.common.oss.OssConfig;
import com.xh.xhcore.common.statistic.XHEnvironment;
import com.xh.xhcore.common.statistic.connect.speed.upload.BaseUploadSpeedManager;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XHUploadOkHttpProxy extends XHUploadProxy<XHUploadOkHttpProxy, UploadOkHttp> {
    private static final String TAG = "XHUploadOkHttpProxy";
    private List<String> aliyunExtraPath;
    private boolean canResetAliyunToken;
    private String domain;
    protected boolean hasFailedPreviously;
    private String localPath;
    private List<UploadFileEntity> localPathFileEntities;
    private UploadFileEntity localPathFileEntity;
    private String[] localPaths;
    private List<UploadSecurityConfig> multiUploadSecurityConfigs;
    private UploadSecurityConfig singleUploadSecurityConfig;
    private String uuid;

    class AnonymousClass1 implements OnOsUploadCallback {
        AnonymousClass1() {
        }

        public void lambda$onError$2$XHUploadOkHttpProxy$1(OsException osException) {
            XHUploadOkHttpProxy.this.getCallback().failedUploadInner(osException.getErrorCode(), osException.getErrorMessage(), osException, XHUploadOkHttpProxy.this.getDownStreamOrOssNetworkErrorUploader());
        }

        public void lambda$onProgress$1$XHUploadOkHttpProxy$1(double d, double d2, String str) {
            XHUploadOkHttpProxy.this.getCallback().onProgress(XHUploadOkHttpProxy.this.getId(), d, d2, str);
        }

        public void lambda$onSuccess$0$XHUploadOkHttpProxy$1(OsResponse osResponse) {
            XHUploadOkHttpProxy.this.getCallback().successInner(XHUploadOkHttpProxy.this.getId(), osResponse.getUrl());
        }

        @Override
        public void onError(final OsException osException) {
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onError$2$XHUploadOkHttpProxy$1(osException);
                }
            });
        }

        @Override
        public void onProgress(final double d, final double d2, final String str) {
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onProgress$1$XHUploadOkHttpProxy$1(d2, d, str);
                }
            });
        }

        @Override
        public void onSuccess(final OsResponse osResponse) {
            LogUtils.e("XHUploadOkHttpProxy", "onSuccess=" + osResponse.toString());
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onSuccess$0$XHUploadOkHttpProxy$1(osResponse);
                }
            });
        }
    }

    class AnonymousClass2 implements OnOsUploadCallback {
        AnonymousClass2() {
        }

        public void lambda$onError$2$XHUploadOkHttpProxy$2(OsException osException) {
            XHUploadOkHttpProxy.this.getCallback().failedUploadInner(osException.getErrorCode(), osException.getErrorMessage(), osException, XHUploadOkHttpProxy.this.getDownStreamOrOssNetworkErrorUploader());
        }

        public void lambda$onProgress$1$XHUploadOkHttpProxy$2(double d, double d2, String str) {
            XHUploadOkHttpProxy.this.getCallback().onProgress(XHUploadOkHttpProxy.this.getId(), d, d2, str);
        }

        public void lambda$onSuccess$0$XHUploadOkHttpProxy$2(List list) {
            XHUploadOkHttpProxy.this.getCallback().successInner(XHUploadOkHttpProxy.this.getId(), JsonUtil.toJsonString(list));
        }

        @Override
        public void onError(final OsException osException) {
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onError$2$XHUploadOkHttpProxy$2(osException);
                }
            });
        }

        @Override
        public void onProgress(final double d, final double d2, final String str) {
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onProgress$1$XHUploadOkHttpProxy$2(d2, d, str);
                }
            });
        }

        @Override
        public void onSuccess(OsResponse osResponse) {
            final List<OsUploadFilesBean> uploadFilesBean = osResponse.getUploadFilesBean();
            LogUtils.e("XHUploadOkHttpProxy", "onSuccess=" + new Gson().toJson(osResponse));
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onSuccess$0$XHUploadOkHttpProxy$2(uploadFilesBean);
                }
            });
        }
    }

    class AnonymousClass4 implements OnAkSkCallback {
        AnonymousClass4() {
        }

        @Override
        public void failed(final int i, final String str) {
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$failed$0$XHUploadOkHttpProxy$4(i, str);
                }
            });
        }

        public void lambda$failed$0$XHUploadOkHttpProxy$4(int i, String str) {
            XHUploadOkHttpProxy.this.getCallback().failedInner(i, str);
        }

        @Override
        public void success(String str, String str2, String str3) {
            XHUploadOkHttpProxy.this.uploadUsingOss(str, str2, str3);
        }
    }

    class AnonymousClass5 implements OnAkSkCallback {
        AnonymousClass5() {
        }

        @Override
        public void failed(final int i, final String str) {
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$failed$0$XHUploadOkHttpProxy$5(i, str);
                }
            });
        }

        public void lambda$failed$0$XHUploadOkHttpProxy$5(int i, String str) {
            XHUploadOkHttpProxy.this.getCallback().failedInner(i, str);
        }

        @Override
        public void success(String str, String str2, String str3) {
            XHUploadOkHttpProxy.this.innerUploadFilesToOss(str, str2, str3);
        }
    }

    class AnonymousClass6 extends XHRequestCallBack.XHUploadCallBack {
        final int val$finalTotalFileSize;
        final List val$ossAsyncTasks;
        final long val$startUploadTime;
        final int val$uploadFileCount;
        final SparseArray val$uploadFileProgressList;
        final int val$uploadIndex;
        final SparseArray val$uploadSuccessList;

        AnonymousClass6(SparseArray sparseArray, int i, int i2, SparseArray sparseArray2, long j, List list, int i3) {
            this.val$uploadSuccessList = sparseArray;
            this.val$uploadIndex = i;
            this.val$uploadFileCount = i2;
            this.val$uploadFileProgressList = sparseArray2;
            this.val$startUploadTime = j;
            this.val$ossAsyncTasks = list;
            this.val$finalTotalFileSize = i3;
        }

        @Override
        public void failed(final int i, final String str) {
            synchronized (this.val$uploadFileProgressList) {
                if (!XHUploadUtil.hasFailedBefore(this.val$uploadFileProgressList)) {
                    XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                        @Override
                        public final void run() {
                            this.f$0.lambda$failed$1$XHUploadOkHttpProxy$6(i, str);
                        }
                    });
                }
                this.val$uploadFileProgressList.put(this.val$uploadIndex, -1L);
            }
        }

        public void lambda$failed$1$XHUploadOkHttpProxy$6(int i, String str) {
            XHUploadOkHttpProxy.this.getCallback().failedInner(i, str);
        }

        public void lambda$success$0$XHUploadOkHttpProxy$6(int i, List list) {
            XHUploadOkHttpProxy.this.getCallback().successInner(i, JsonUtil.toJsonString(list));
        }

        @Override
        public void onProgress(int i, double d, double d2, String str) {
            super.onProgress(i, d, d2, str);
            synchronized (this.val$uploadFileProgressList) {
                long jCurrentTimeMillis = System.currentTimeMillis() - this.val$startUploadTime;
                this.val$uploadFileProgressList.put(this.val$uploadIndex, Long.valueOf((long) d2));
                long jLongValue = 0;
                for (int i2 = 0; i2 < this.val$uploadFileCount; i2++) {
                    Long l = (Long) this.val$uploadFileProgressList.get(i2);
                    if (l.longValue() < 0) {
                        Iterator it = this.val$ossAsyncTasks.iterator();
                        while (it.hasNext()) {
                            ((OSSAsyncTask) it.next()).cancel();
                        }
                        return;
                    }
                    jLongValue += l.longValue();
                }
                long j = jCurrentTimeMillis > 0 ? (long) (jLongValue / (jCurrentTimeMillis / 1000.0f)) : 0L;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("speed", XHHttpUtil.toByteUnit(j));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                XHUploadOkHttpProxy.this.getCallback().onProgress(i, this.val$finalTotalFileSize, jLongValue, jSONObject.toString());
                if (jLongValue == this.val$finalTotalFileSize) {
                    BaseUploadSpeedManager.getInternetInstance().addItem(Long.valueOf(j));
                }
            }
        }

        @Override
        public void success(final int i, String str) {
            synchronized (this.val$uploadSuccessList) {
                XHUploadFilesResBean.UploadResBean uploadResBean = new XHUploadFilesResBean.UploadResBean();
                uploadResBean.setUrl(str);
                this.val$uploadSuccessList.put(this.val$uploadIndex, uploadResBean);
                if (this.val$uploadSuccessList.size() == this.val$uploadFileCount) {
                    final ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < this.val$uploadSuccessList.size(); i2++) {
                        arrayList.add((XHUploadFilesResBean.UploadResBean) this.val$uploadSuccessList.get(this.val$uploadSuccessList.keyAt(i2)));
                    }
                    XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                        @Override
                        public final void run() {
                            this.f$0.lambda$success$0$XHUploadOkHttpProxy$6(i, arrayList);
                        }
                    });
                }
            }
        }
    }

    class AnonymousClass7 implements OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
        final String val$finalAppKey;

        AnonymousClass7(String str) {
            this.val$finalAppKey = str;
        }

        public void lambda$onSuccess$0$XHUploadOkHttpProxy$7(PutObjectRequest putObjectRequest) {
            XHUploadOkHttpProxy.this.getCallback().successInner(XHUploadOkHttpProxy.this.getId(), UploadSecurityUtil.generateAliyunFileUrlPath(XHUploadOkHttpProxy.this.singleUploadSecurityConfig, putObjectRequest));
        }

        @Override
        public void onFailure(PutObjectRequest putObjectRequest, final ClientException clientException, final ServiceException serviceException) {
            LogUtils.d("XHUploadOkHttpProxy", "PutObject --> onFailure");
            if (!XHUploadOkHttpProxy.this.canResetAliyunToken || !XHUploadUtil.isAliyunOssTokenError(serviceException)) {
                XHUploadOkHttpProxy.this.extracted(clientException, serviceException, this.val$finalAppKey);
                return;
            }
            LogUtils.d("XHUploadOkHttpProxy", "uploadUsingOss --> 重试一次：请求AkSk及token");
            XHUploadOkHttpProxy.this.canResetAliyunToken = false;
            AkSkUtil.reset(new OnAkSkCallback() {
                @Override
                public void failed(int i, String str) {
                    LogUtils.d("XHUploadOkHttpProxy", "uploadUsingOss --> 重试依旧失败，继续执行原有的逻辑");
                    XHUploadOkHttpProxy.this.extracted(clientException, serviceException, AnonymousClass7.this.val$finalAppKey);
                }

                @Override
                public void success(String str, String str2, String str3) {
                    XHUploadOkHttpProxy.this.uploadUsingOss(str, str2, str3);
                }
            });
        }

        @Override
        public void onSuccess(final PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
            LogUtils.d("XHUploadOkHttpProxy", "PutObject--->UploadSuccess，ETag = " + putObjectResult.getETag() + "，RequestId = " + putObjectResult.getRequestId() + "，StatusCode = " + putObjectResult.getStatusCode() + "，ObjectKey = " + putObjectRequest.getObjectKey());
            String fileMd5 = XHUploadOkHttpProxy.this.getFileMd5();
            if (!TextUtils.isEmpty(fileMd5) && fileMd5.equalsIgnoreCase(putObjectResult.getETag())) {
                XHUploadOkHttpProxy.this.backupToFileServer(this.val$finalAppKey, XHUploadOkHttpProxy.this.getSingleFileLocalPath().getLocalPath());
                XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$onSuccess$0$XHUploadOkHttpProxy$7(putObjectRequest);
                    }
                });
            } else {
                onFailure(putObjectRequest, new ClientException("ETag verify failed, ETag = " + putObjectResult.getETag() + " fileMd5 = " + fileMd5), (ServiceException) null);
            }
        }
    }

    public class XHUploadCallbackInC extends XHBaseRequestProxy<XHUploadOkHttpProxy, UploadOkHttp, XHRequestCallBack.XHUploadCallBack>.XHBaseRequestCallbackInC {
        File multiFilesUploadZipFile;

        class AnonymousClass1 implements OnAkSkCallback {
            AnonymousClass1() {
            }

            @Override
            public void failed(final int i, final String str) {
                XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$failed$0$XHUploadOkHttpProxy$XHUploadCallbackInC$1(i, str);
                    }
                });
            }

            public void lambda$failed$0$XHUploadOkHttpProxy$XHUploadCallbackInC$1(int i, String str) {
                XHUploadOkHttpProxy.this.getCallback().failedInner(i, str);
            }

            @Override
            public void success(String str, String str2, String str3) {
                XHUploadOkHttpProxy.this.exchangeUploadToOss(str, str2, str3);
            }
        }

        public XHUploadCallbackInC() {
            super();
        }

        private void callbackResponse() {
            if (getCode() != CommonUtils.baseCode) {
                failedUploadInnerDefault();
                return;
            }
            try {
                XHUploadResBean xHUploadResBean = (XHUploadResBean) new Gson().fromJson(this.responseBodyString, XHUploadResBean.class);
                if (xHUploadResBean.status == 1000) {
                    XHUploadOkHttpProxy.this.getCallback().successInner(XHUploadOkHttpProxy.this.getId(), xHUploadResBean.uploadFileDTO.fileId);
                } else {
                    XHUploadOkHttpProxy.this.getCallback().failedUploadInner(107003100, getOkHttpNetworkErrorUploader());
                }
            } catch (Exception e) {
                XHUploadOkHttpProxy.this.getCallback().failedUploadInner(107003101, "", e, getOkHttpNetworkErrorUploader());
            }
        }

        private void internalUploadToOss() {
            AkSkUtil.getAkSk(new AnonymousClass1());
        }

        @Override
        protected void deleteTempFile() {
            File file = this.multiFilesUploadZipFile;
            if (file == null || !file.exists()) {
                return;
            }
            this.multiFilesUploadZipFile.delete();
        }

        @Override
        public String getResponseBodyString() {
            return this.responseBodyString;
        }

        public void lambda$onEvent$0$XHUploadOkHttpProxy$XHUploadCallbackInC() {
            XHUploadOkHttpProxy.this.getCallback().successInner(XHUploadOkHttpProxy.this.getId(), this.responseBodyString);
        }

        public void lambda$onProgress$1$XHUploadOkHttpProxy$XHUploadCallbackInC(String str, int i, double d, double d2) {
            XHUploadOkHttpProxy.this.getCallback().setJsonParam(str);
            XHUploadOkHttpProxy.this.getCallback().onProgress(i, d, d2, str);
        }

        @Override
        protected void onEvent() {
            if (!XHUploadOkHttpProxy.this.isUploadUsingMultiFile()) {
                if (getCode() == 107002502 || getCode() == CommonUtils.baseCode || XHUploadOkHttpProxy.this.hasFailedPreviously || isUserCancelNetwork()) {
                    XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                        @Override
                        public final void run() {
                            this.f$0.callbackResponse();
                        }
                    });
                    return;
                } else {
                    uploadNetworkError(new Throwable());
                    internalUploadToOss();
                    return;
                }
            }
            if (XHUploadOkHttpProxy.this.getCallback() == null) {
                return;
            }
            if (isUserCancelNetwork()) {
                XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.failedInnerDefault();
                    }
                });
                return;
            }
            XHUploadOkHttpProxy.this.getCallback().setJsonParam(this.responseBodyString);
            if (getCode() != CommonUtils.baseCode) {
                uploadNetworkError(new Throwable());
                XHUploadOkHttpProxy.this.uploadFilesToOss();
                return;
            }
            try {
                new JSONArray(this.responseBodyString);
                XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$onEvent$0$XHUploadOkHttpProxy$XHUploadCallbackInC();
                    }
                });
            } catch (Exception e) {
                uploadNetworkError(new Throwable(e));
                XHUploadOkHttpProxy.this.uploadFilesToOss();
            }
        }

        void onProgress(final int i, final double d, final double d2, final String str) {
            XHUploadOkHttpProxy.this.toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$onProgress$1$XHUploadOkHttpProxy$XHUploadCallbackInC(str, i, d, d2);
                }
            });
        }

        public XHUploadCallbackInC setMultiFilesUploadZipFile(File file) {
            this.multiFilesUploadZipFile = file;
            return this;
        }

        @Override
        protected void updateFileServerStatus() {
            if (!XHUploadOkHttpProxy.this.isUploadUsingMultiFile()) {
                try {
                    this.fileServerStatus = ((UploadFileRes) new Gson().fromJson(this.responseBodyString, UploadFileRes.class)).getStatus();
                } catch (Exception e) {
                    e.printStackTrace();
                    this.fileServerStatus = 2000;
                }
            }
            if (this.fileServerStatus == 2500) {
                setCodeAndDescription(107002502);
            } else if (this.fileServerStatus != 1000) {
                setCodeAndDescription(107002002);
            }
        }
    }

    XHUploadOkHttpProxy(UploadOkHttp uploadOkHttp) {
        super(uploadOkHttp);
        this.domain = "xh.oss.com";
        this.multiUploadSecurityConfigs = null;
        this.singleUploadSecurityConfig = null;
        this.aliyunExtraPath = new ArrayList();
        this.localPathFileEntities = new ArrayList();
        this.hasFailedPreviously = false;
        this.canResetAliyunToken = true;
    }

    private void backupToFileServer(String str) {
        ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setUrl(str)).addHeader("Folder", (Object) transformToExtraPathHeaderString())).setMethod("POST")).request();
    }

    private void backupToFileServer(String str, String str2) {
        String str3 = XHUploadUtil.LARGE_FILE_SERVER_PATH + str + "/" + getUploadFileName() + "/" + XHUploadUtil.getFileSuffixFromFilePath(str2);
        LogUtils.d("XHUploadOkHttpProxy", "上传到文件服务器的文件备份到文件服务中的路径：" + str3);
        backupToFileServer(str3);
    }

    private void callFailedAndUploadLog(int i, String str, Throwable th) {
        getCallback().failedUploadInner(i, str, th, getDownStreamOrOssNetworkErrorUploader());
    }

    private XHUploadOkHttpProxy exchangeUploadToXueHai(String str) {
        this.hasFailedPreviously = true;
        LogUtils.i("XHUploadOkHttpProxy", "大文件上传失败之后重新上传到公司文件服务器");
        return uploadToXueHai(str);
    }

    private void extracted(final ClientException clientException, final ServiceException serviceException, String str) {
        if (this.hasFailedPreviously) {
            toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$extracted$15$XHUploadOkHttpProxy(clientException, serviceException);
                }
            });
        } else {
            getDownStreamOrOssNetworkErrorUploader().appendErrorMessage(clientException).appendErrorMessage(serviceException).uploadNetworkError();
            exchangeUploadToXueHai(XHUploadUtil.getFileInstantUploadUrl(str));
        }
    }

    private void lambda$extracted$15$XHUploadOkHttpProxy(ClientException clientException, ServiceException serviceException) {
        if (clientException != null) {
            String str = "客户端错误：" + clientException.getMessage();
            if (clientException.isCanceledException().booleanValue()) {
                getCallback().failedInner(107003000, str);
                return;
            } else {
                getCallback().failedUploadInner(107003000, str, clientException, getDownStreamOrOssNetworkErrorUploader());
                return;
            }
        }
        if (serviceException == null) {
            callFailedAndUploadLog(107003006, "", null);
            return;
        }
        callFailedAndUploadLog(107003001, "服务器错误：" + serviceException.getErrorCode() + ":" + serviceException.getMessage(), serviceException);
    }

    private XHUploadOkHttpProxy fileUpload() {
        return uploadToXueHai(((UploadOkHttp) this.baseRequest).getUrl());
    }

    private String getFileMd5() {
        return FileMD5.getMD5(new File(getSingleFileLocalPath().getLocalPath()));
    }

    private String getUploadFileExtraPath(boolean z) {
        String strTransformUploadFileExtraPath = getSingleFileLocalPath().transformUploadFileExtraPath();
        if (strTransformUploadFileExtraPath == null || TextUtils.isEmpty(strTransformUploadFileExtraPath)) {
            return "";
        }
        if (!z || strTransformUploadFileExtraPath.endsWith(File.separator)) {
            return strTransformUploadFileExtraPath;
        }
        return strTransformUploadFileExtraPath + File.separator;
    }

    private String getUploadFileFullPath(String str, String str2) {
        return getUploadFilePath(str) + getUploadFileExtraPath(true) + getUploadFileName() + "." + XHUploadUtil.getFileSuffixFromFilePath(str2);
    }

    private String getUploadFileName() {
        if (TextUtils.isEmpty(this.uuid)) {
            this.uuid = UUID.randomUUID().toString().replace("-", "");
        }
        return this.uuid;
    }

    private String getUploadFilePath(String str) {
        String strTransformAliyunExtraPathToHeaderString = transformAliyunExtraPathToHeaderString();
        if (TextUtils.isEmpty(strTransformAliyunExtraPathToHeaderString)) {
            return str + File.separator;
        }
        return str + File.separator + strTransformAliyunExtraPathToHeaderString + File.separator;
    }

    private XHUploadOkHttpProxy httpUploadFiles() {
        return doUpload(((UploadOkHttp) this.baseRequest).getUrl());
    }

    private void innerUploadFilesToOss(String str, String str2, String str3) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<UploadFileEntity> multiFileLocalPaths = getMultiFileLocalPaths();
        int size = multiFileLocalPaths.size();
        SparseArray sparseArray = new SparseArray();
        SparseArray sparseArray2 = new SparseArray();
        ArrayList arrayList = new ArrayList();
        int length = 0;
        for (int i = 0; i < size; i++) {
            length = (int) (length + new File(multiFileLocalPaths.get(i).getLocalPath()).length());
            sparseArray.put(i, 0L);
        }
        int i2 = 0;
        while (i2 < size) {
            int i3 = length;
            long j = jCurrentTimeMillis;
            ArrayList arrayList2 = arrayList;
            arrayList2.add(((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) XHUploadProxy.createOkHttp().setUrl(((UploadOkHttp) this.baseRequest).getUrl())).setUploadLocalPath(multiFileLocalPaths.get(i2)).setRequestJsonParams(getRequestJsonParams())).setAliyunExtraPath(getAliyunExtraPath()).setSingleUploadSecurityConfig((UploadSecurityConfig) UploadSecurityUtil.getFromList(i2, this.multiUploadSecurityConfigs)).setCallback(new AnonymousClass6(sparseArray2, i2, size, sparseArray, jCurrentTimeMillis, arrayList, i3))).setExchangeToMainThread(this.exchangeToMainThread)).exchangeUploadToOss(str, str2, str3));
            i2++;
            arrayList = arrayList2;
            multiFileLocalPaths = multiFileLocalPaths;
            size = size;
            length = i3;
            jCurrentTimeMillis = j;
        }
    }

    private boolean isUploadPrivate(UploadSecurityConfig uploadSecurityConfig) {
        if (uploadSecurityConfig == null) {
            return false;
        }
        return uploadSecurityConfig.isPrivate();
    }

    private boolean isUploadUsingMultiFile() {
        return (getMultiFileLocalPaths().isEmpty() && this.localPaths == null) ? false : true;
    }

    private void largeFileUpload() {
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("当前线程");
        sb.append(ThreadUtil.isOnMainThread() ? "是主线程" : "是子线程");
        printStream.println(sb.toString());
        AkSkUtil.getAkSk(new AnonymousClass4());
    }

    private void onUploadByOsType(OsUploadConfig osUploadConfig) {
        if (isUploadUsingMultiFile()) {
            OsTypeUploadHelper.INSTANCE.uploadFiles(osUploadConfig, new AnonymousClass2());
        } else {
            OsTypeUploadHelper.INSTANCE.uploadFile(osUploadConfig, new AnonymousClass1());
        }
    }

    private void setLocalPathFileEntities(List<UploadFileEntity> list) {
        this.localPathFileEntities = list;
    }

    private void setLocalPathFileEntity(UploadFileEntity uploadFileEntity) {
        this.localPathFileEntity = uploadFileEntity;
    }

    private XHUploadOkHttpProxy setMultiUploadSecurityConfigs(List<UploadSecurityConfig> list) {
        this.multiUploadSecurityConfigs = list;
        return this;
    }

    private String transformToExtraPathHeaderString() {
        String strTransformAliyunExtraPathToHeaderString = transformAliyunExtraPathToHeaderString();
        String uploadFileExtraPath = getUploadFileExtraPath(false);
        if (!TextUtils.isEmpty(uploadFileExtraPath)) {
            if (!TextUtils.isEmpty(strTransformAliyunExtraPathToHeaderString) && !strTransformAliyunExtraPathToHeaderString.endsWith(File.separator)) {
                strTransformAliyunExtraPathToHeaderString = strTransformAliyunExtraPathToHeaderString + File.separator;
            }
            strTransformAliyunExtraPathToHeaderString = strTransformAliyunExtraPathToHeaderString + uploadFileExtraPath;
        }
        LogUtils.i("XHUploadOkHttpProxy", "添加到Header的目录字段，extraPathHeader = " + strTransformAliyunExtraPathToHeaderString);
        return strTransformAliyunExtraPathToHeaderString;
    }

    private void uploadByXhOss() {
        if (getAliyunExtraPath().size() == 0) {
            toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$uploadByXhOss$6$XHUploadOkHttpProxy();
                }
            });
        }
        initFileServerUrlInner();
        if (!isUploadUsingMultiFile()) {
            final String localPath = getSingleFileLocalPath().getLocalPath();
            if (TextUtils.isEmpty(localPath)) {
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$uploadByXhOss$7$XHUploadOkHttpProxy();
                    }
                });
            }
            if (!new File(localPath).exists()) {
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$uploadByXhOss$8$XHUploadOkHttpProxy(localPath);
                    }
                });
            }
            if (XHFileUtil.getFileLength(localPath) >= 16777216) {
                largeFileUpload();
                return;
            } else {
                fileUpload();
                return;
            }
        }
        List<UploadFileEntity> multiFileLocalPaths = getMultiFileLocalPaths();
        if (multiFileLocalPaths.size() == 0) {
            toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$uploadByXhOss$9$XHUploadOkHttpProxy();
                }
            });
        }
        Iterator<UploadFileEntity> it = multiFileLocalPaths.iterator();
        while (it.hasNext()) {
            String localPath2 = it.next().getLocalPath();
            if (TextUtils.isEmpty(localPath2)) {
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$uploadByXhOss$10$XHUploadOkHttpProxy();
                    }
                });
            }
            final File file = new File(localPath2);
            if (!file.exists()) {
                LogUtils.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!");
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$uploadByXhOss$11$XHUploadOkHttpProxy(file);
                    }
                });
            }
        }
        httpUploadFiles();
    }

    private XHUploadOkHttpProxy uploadToXueHai(String str) {
        return doUpload(str);
    }

    @Override
    protected void applyServerNetworkConfig(ServerNetworkConfig serverNetworkConfig) {
        super.applyServerNetworkConfig(serverNetworkConfig);
        if (serverNetworkConfig.isSocketConnectionTimeoutUploadDownloadValid()) {
            ((UploadOkHttp) this.baseRequest).setConnectTimeout(serverNetworkConfig.getSocketConnectTimeoutMillisUploadDownload());
        }
        if (serverNetworkConfig.isSocketWriteTimeoutUploadDownloadValid()) {
            ((UploadOkHttp) this.baseRequest).setWriteTimeout(serverNetworkConfig.getSocketWriteTimeoutMillisUploadDownload());
        }
        if (serverNetworkConfig.isSocketReadTimeoutUploadDownloadValid()) {
            ((UploadOkHttp) this.baseRequest).setReadTimeout(serverNetworkConfig.getSocketReadTimeoutMillisUploadDownload());
        }
    }

    protected XHUploadOkHttpProxy doUpload(String str) {
        refreshId();
        final XHUploadCallbackInC xHUploadCallbackInC = new XHUploadCallbackInC();
        ((UploadOkHttp) this.baseRequest).setProgressRequestListener(new BaseProgressRequestListener() {
            final LogIntervalHelper logIntervalHelper = new LogIntervalHelper();

            @Override
            public void uploadProgress(long j, long j2, long j3) throws JSONException {
                String speedJson = XHHttpUtil.toSpeedJson(j3);
                this.logIntervalHelper.logDOnceInterval(100L, "上传进度：" + ((int) ((j * 100) / j2)) + "% 上传速度 = " + speedJson);
                xHUploadCallbackInC.onProgress(XHUploadOkHttpProxy.this.getId(), (double) j2, (double) j, speedJson);
                if (j == j2) {
                    BaseUploadSpeedManager.getInstance(((UploadOkHttp) XHUploadOkHttpProxy.this.baseRequest).getUrl()).addItem(Long.valueOf(j3));
                    LogUtils.d("上传完成，上传速度 = " + speedJson);
                }
            }
        });
        if (isUploadUsingMultiFile()) {
            File fileZipUploadFiles = HttpUtil.zipUploadFiles(getMultiFileLocalPaths(), XHEnvironment.getUpLoadZipPath(), String.format("%d_%d.zip", Integer.valueOf(getId()), Long.valueOf(System.currentTimeMillis())));
            String md5 = FileMD5.getMD5(fileZipUploadFiles);
            UploadSecurityUtil.secure((UploadSecurityConfig) UploadSecurityUtil.getFromList(0, this.multiUploadSecurityConfigs), (UploadOkHttp) this.baseRequest);
            ((UploadOkHttp) ((UploadOkHttp) ((UploadOkHttp) ((UploadOkHttp) this.baseRequest).setUrl(str)).addHeader("XueHai-MD5", (Object) md5)).addHeader("Folder", (Object) transformAliyunExtraPathToHeaderString())).setFiles(fileZipUploadFiles).setCallback(xHUploadCallbackInC.setMultiFilesUploadZipFile(fileZipUploadFiles));
            callProxyRequest();
        } else {
            String fileMd5 = getFileMd5();
            if (str.contains("/file/upload")) {
                if (str.endsWith("/")) {
                    str = str + fileMd5;
                } else {
                    str = str + "/" + fileMd5;
                }
            }
            UploadSecurityUtil.secure(this.singleUploadSecurityConfig, (UploadOkHttp) this.baseRequest);
            ((UploadOkHttp) ((UploadOkHttp) ((UploadOkHttp) ((UploadOkHttp) this.baseRequest).setUrl(str)).setFile(getSingleFileLocalPath().getLocalPath()).addHeader("XueHai-MD5", (Object) fileMd5)).addHeader("Folder", (Object) transformToExtraPathHeaderString())).setCallback(xHUploadCallbackInC);
            callProxyRequest();
        }
        return this;
    }

    public OSSAsyncTask<PutObjectResult> exchangeUploadToOss(String str, String str2, String str3) {
        this.hasFailedPreviously = true;
        LogUtils.i("XHUploadOkHttpProxy", "小文件上传失败后重新直传到阿里云");
        return uploadUsingOss(str, str2, str3);
    }

    public List<String> getAliyunExtraPath() {
        return this.aliyunExtraPath;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getLocalPath() {
        UploadFileEntity uploadFileEntity = this.localPathFileEntity;
        return uploadFileEntity != null ? uploadFileEntity.getLocalPath() : this.localPath;
    }

    public List<UploadFileEntity> getLocalPathFileEntities() {
        return this.localPathFileEntities;
    }

    public UploadFileEntity getLocalPathFileEntity() {
        return this.localPathFileEntity;
    }

    public String[] getLocalPaths() {
        if (this.localPathFileEntities != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<UploadFileEntity> it = this.localPathFileEntities.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getLocalPath());
            }
            if (!arrayList.isEmpty()) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
        }
        return this.localPaths;
    }

    public List<UploadFileEntity> getMultiFileLocalPaths() {
        List<UploadFileEntity> list = this.localPathFileEntities;
        if (list != null && !list.isEmpty()) {
            return this.localPathFileEntities;
        }
        ArrayList arrayList = new ArrayList();
        String[] strArr = this.localPaths;
        if (strArr != null) {
            for (String str : strArr) {
                arrayList.add(new UploadFileEntity(str));
            }
            setLocalPathFileEntities(arrayList);
        }
        return arrayList;
    }

    public UploadFileEntity getSingleFileLocalPath() {
        UploadFileEntity uploadFileEntity = this.localPathFileEntity;
        if (uploadFileEntity != null) {
            return uploadFileEntity;
        }
        String str = this.localPath;
        if (str == null) {
            str = "";
        }
        UploadFileEntity uploadFileEntity2 = new UploadFileEntity(str);
        setLocalPathFileEntity(uploadFileEntity2);
        return uploadFileEntity2;
    }

    public XHUploadOkHttpProxy initFileServerUrlInner() {
        if (TextUtils.isEmpty(((UploadOkHttp) this.baseRequest).getUrl())) {
            if (isUploadUsingMultiFile()) {
                setUrl(ConstServerUrlKt.FILE_SERVER_UPLOAD_ZIP);
            } else {
                setUrl(ConstServerUrlKt.FILE_SERVER_UPLOAD);
            }
            return this;
        }
        String strReplace = ((UploadOkHttp) this.baseRequest).getUrl().replace("filesoss.yunzuoye.net", ConstServerBaseUrlKt.BASE_URL_FILE_SERVER_HOST);
        if (XhBaseApplication.getXhBaseApplication().isCPVDEnvironmentProduction()) {
            strReplace = strReplace.replace("http://", "https://");
        }
        setUrl(strReplace);
        return this;
    }

    public boolean isHasFailedPreviously() {
        return this.hasFailedPreviously;
    }

    public void lambda$requestInner$0$XHUploadOkHttpProxy(String str, String str2) {
        getCallback().failedInner(107005001, "上传类型错误，请联系学海老师；domain =" + str + ", osType =" + str2);
    }

    public void lambda$requestInner$1$XHUploadOkHttpProxy(String str) {
        getCallback().failedInner(107005002, "桶配置未找到，请联系学海老师；" + str);
    }

    public void lambda$requestInner$2$XHUploadOkHttpProxy(String str) {
        getCallback().failedInner(107005002, "公有桶配置未找到，请联系学海老师；" + str);
    }

    public void lambda$requestInner$3$XHUploadOkHttpProxy(String str) {
        getCallback().failedInner(107005002, "安全配置数量与文件数量不一致，请联系学海老师；" + str);
    }

    public void lambda$requestInner$4$XHUploadOkHttpProxy() {
        getCallback().failedInner(107005002, "上传domain配置未找到，请联系学海老师；" + this.domain);
    }

    public Unit lambda$requestInner$5$XHUploadOkHttpProxy(DomainObjectStorageConfig domainObjectStorageConfig) {
        LogUtils.i("XHUploadOkHttpProxy", "findDomainConfig = " + new Gson().toJson(domainObjectStorageConfig));
        if (domainObjectStorageConfig == null) {
            if (Objects.equals(this.domain, "xh.oss.com")) {
                uploadByXhOss();
                return null;
            }
            toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$requestInner$4$XHUploadOkHttpProxy();
                }
            });
            return null;
        }
        final String osType = domainObjectStorageConfig.getOsType();
        final String domain = domainObjectStorageConfig.getDomain();
        String endpoint = domainObjectStorageConfig.getEndpoint();
        List<String> buckets = domainObjectStorageConfig.getBuckets();
        List<String> publicBuckets = domainObjectStorageConfig.getPublicBuckets();
        if (Objects.equals(domain, "xh.oss.com")) {
            if (osType.equals("1")) {
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$requestInner$0$XHUploadOkHttpProxy(domain, osType);
                    }
                });
                return null;
            }
            if (!osType.equals("0")) {
                return null;
            }
            OssConfig.bucketNames = (String[]) publicBuckets.toArray(new String[publicBuckets.size()]);
            uploadByXhOss();
            return null;
        }
        if (!isUploadUsingMultiFile()) {
            if (!isUploadPrivate(this.singleUploadSecurityConfig)) {
                buckets = publicBuckets;
            }
            String strRandomBucket = domainObjectStorageConfig.randomBucket(buckets);
            if (strRandomBucket == null) {
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$requestInner$1$XHUploadOkHttpProxy(domain);
                    }
                });
                return null;
            }
            UploadFileEntity singleFileLocalPath = getSingleFileLocalPath();
            String localPath = singleFileLocalPath.getLocalPath();
            HashMap map = new HashMap();
            map.put(localPath, this.singleUploadSecurityConfig);
            onUploadByOsType(OsUploadConfig.singleUploadConfig(domain, endpoint, strRandomBucket, isUploadPrivate(this.singleUploadSecurityConfig), osType, this.aliyunExtraPath, singleFileLocalPath, map));
            return null;
        }
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        List<UploadFileEntity> localPathFileEntities = getLocalPathFileEntities();
        if (this.multiUploadSecurityConfigs == null) {
            String strRandomBucket2 = domainObjectStorageConfig.randomBucket(publicBuckets);
            if (strRandomBucket2 == null) {
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$requestInner$2$XHUploadOkHttpProxy(domain);
                    }
                });
                return null;
            }
            Iterator<UploadFileEntity> it = localPathFileEntities.iterator();
            while (it.hasNext()) {
                String localPath2 = it.next().getLocalPath();
                map2.put(localPath2, strRandomBucket2);
                map3.put(localPath2, false);
            }
        }
        List<UploadSecurityConfig> list = this.multiUploadSecurityConfigs;
        if (list != null) {
            if (list.size() != localPathFileEntities.size()) {
                toMainThreadChecked(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.lambda$requestInner$3$XHUploadOkHttpProxy(domain);
                    }
                });
                return null;
            }
            map2.clear();
            map3.clear();
            String strRandomBucket3 = domainObjectStorageConfig.randomBucket(publicBuckets);
            String strRandomBucket4 = domainObjectStorageConfig.randomBucket(buckets);
            int size = localPathFileEntities.size();
            for (int i = 0; i < size; i++) {
                String localPath3 = localPathFileEntities.get(i).getLocalPath();
                UploadSecurityConfig uploadSecurityConfig = (UploadSecurityConfig) UploadSecurityUtil.getFromList(i, this.multiUploadSecurityConfigs);
                boolean zIsUploadPrivate = isUploadPrivate(uploadSecurityConfig);
                map2.put(localPath3, zIsUploadPrivate ? strRandomBucket4 : strRandomBucket3);
                map3.put(localPath3, Boolean.valueOf(zIsUploadPrivate));
                map4.put(localPath3, uploadSecurityConfig);
            }
        }
        onUploadByOsType(OsUploadConfig.multiUploadConfig(domain, endpoint, map2, map3, osType, this.aliyunExtraPath, localPathFileEntities, map4));
        return null;
    }

    public void lambda$uploadByXhOss$10$XHUploadOkHttpProxy() {
        getCallback().failedUploadInner(107003004, getDownStreamOrOssNetworkErrorUploader());
    }

    public void lambda$uploadByXhOss$11$XHUploadOkHttpProxy(File file) {
        getCallback().failedUploadInner(107001014, "", new Throwable("上传文件不存在: " + file.getPath()), getDownStreamOrOssNetworkErrorUploader());
    }

    public void lambda$uploadByXhOss$6$XHUploadOkHttpProxy() {
        getCallback().failedUploadInner(107003002, getDownStreamOrOssNetworkErrorUploader());
    }

    public void lambda$uploadByXhOss$7$XHUploadOkHttpProxy() {
        getCallback().failedUploadInner(107003004, getDownStreamOrOssNetworkErrorUploader());
    }

    public void lambda$uploadByXhOss$8$XHUploadOkHttpProxy(String str) {
        getCallback().failedUploadInner(107001014, "", new Throwable("上传文件不存在: " + str), getDownStreamOrOssNetworkErrorUploader());
    }

    public void lambda$uploadByXhOss$9$XHUploadOkHttpProxy() {
        getCallback().failedUploadInner(107003005, getDownStreamOrOssNetworkErrorUploader());
    }

    public void lambda$uploadUsingOss$12$XHUploadOkHttpProxy(IllegalArgumentException illegalArgumentException) {
        getCallback().failedUploadInner(107003102, "", illegalArgumentException, getDownStreamOrOssNetworkErrorUploader());
    }

    public void lambda$uploadUsingOss$13$XHUploadOkHttpProxy(long j, long j2) {
        getCallback().setJsonParam(null);
        getCallback().onProgress(0, j, j2, null);
    }

    public void lambda$uploadUsingOss$14$XHUploadOkHttpProxy(PutObjectRequest putObjectRequest, final long j, final long j2) {
        toMainThreadChecked(new Runnable() {
            @Override
            public final void run() {
                this.f$0.lambda$uploadUsingOss$13$XHUploadOkHttpProxy(j2, j);
            }
        });
    }

    @Override
    protected XHUploadOkHttpProxy requestInner() {
        OsDomainHelper.findDomainConfig(this.domain, new Function1() {
            @Override
            public final Object invoke(Object obj) {
                return this.f$0.lambda$requestInner$5$XHUploadOkHttpProxy((DomainObjectStorageConfig) obj);
            }
        });
        return this;
    }

    public XHUploadOkHttpProxy setAliyunExtraPath(List<String> list) {
        this.aliyunExtraPath = list;
        return this;
    }

    public XHUploadOkHttpProxy setDomain(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.domain = str;
        }
        return this;
    }

    public XHUploadOkHttpProxy setLocalPath(String str) {
        this.localPath = str;
        setLocalPathFileEntity(new UploadFileEntity(str));
        return this;
    }

    public XHUploadOkHttpProxy setLocalPaths(String[] strArr) {
        this.localPaths = strArr;
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(new UploadFileEntity(str));
        }
        setLocalPathFileEntities(arrayList);
        return this;
    }

    public XHUploadOkHttpProxy setMultiUploadSecurityConfigs(UploadSecurityConfig uploadSecurityConfig, int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(uploadSecurityConfig);
        }
        return setMultiUploadSecurityConfigs(arrayList);
    }

    public XHUploadOkHttpProxy setSingleUploadSecurityConfig(UploadSecurityConfig uploadSecurityConfig) {
        this.singleUploadSecurityConfig = uploadSecurityConfig;
        return this;
    }

    public XHUploadOkHttpProxy setUploadLocalPath(UploadFileEntity uploadFileEntity) {
        this.localPath = uploadFileEntity.getLocalPath();
        setLocalPathFileEntity(uploadFileEntity);
        return this;
    }

    public XHUploadOkHttpProxy setUploadLocalPathList(List<UploadFileEntity> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<UploadFileEntity> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getLocalPath());
        }
        this.localPaths = (String[]) arrayList.toArray(new String[arrayList.size()]);
        setLocalPathFileEntities(list);
        return this;
    }

    protected String transformAliyunExtraPathToHeaderString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.aliyunExtraPath.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(File.separator);
        }
        return sb.length() > 0 ? sb.deleteCharAt(sb.length() - 1).toString() : "";
    }

    public void uploadFilesToOss() {
        AkSkUtil.getAkSk(new AnonymousClass5());
    }

    public OSSAsyncTask<PutObjectResult> uploadUsingOss(String str, String str2, String str3) {
        String fileNameFromFilePath;
        String appKeyFromUrl = XHUploadUtil.getAppKeyFromUrl(((UploadOkHttp) this.baseRequest).getUrl());
        String localPath = getSingleFileLocalPath().getLocalPath();
        try {
            fileNameFromFilePath = XHUploadUtil.getFileNameFromFilePath(localPath);
        } catch (IllegalArgumentException e) {
            toMainThreadChecked(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.lambda$uploadUsingOss$12$XHUploadOkHttpProxy(e);
                }
            });
            fileNameFromFilePath = null;
        }
        PutObjectRequest putObjectRequestNewPutObjectRequest = OssConfig.newPutObjectRequest(OssConfig.getBucketName(), getUploadFileFullPath(appKeyFromUrl, fileNameFromFilePath), localPath);
        UploadSecurityUtil.secure(this.singleUploadSecurityConfig, putObjectRequestNewPutObjectRequest);
        putObjectRequestNewPutObjectRequest.setProgressCallback(new OSSProgressCallback() {
            @Override
            public final void onProgress(Object obj, long j, long j2) {
                this.f$0.lambda$uploadUsingOss$14$XHUploadOkHttpProxy((PutObjectRequest) obj, j, j2);
            }
        });
        int oSSTimeOut = XHUploadUtil.getOSSTimeOut(getRequestJsonParams());
        return new OssConfig().setConnectionTimeOut(oSSTimeOut).setSocketTimeOut(oSSTimeOut).getOss(XhBaseApplication.mContext, str, str2, str3).asyncPutObject(putObjectRequestNewPutObjectRequest, new AnonymousClass7(appKeyFromUrl));
    }
}

package com.xh.xhcore.common.oss;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.security.UploadSecurityConfig;
import com.xh.xhcore.common.http.strategy.xh.security.UploadSecurityUtil;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.OnOsUploadCallback;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.OsTypeUploadHelper;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsResponse;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.exception.OsException;
import com.xh.xhcore.common.obs.ObsFileUploadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0086\u0001\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00150\u00142\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xh/xhcore/common/oss/OssUploadHelper;", "", "()V", "CONNECTION_TIMEOUT", "", "SOCKET_TIMEOUT", "TAG", "", "uploadOssFile", "", "path", "ak", "sk", "token", "endpoint", "bucket", "pathExt", "", "fileExtraPath", "fileSecurityMap", "", "", "fileSecurityConfigMap", "", "Lcom/xh/xhcore/common/http/strategy/xh/security/UploadSecurityConfig;", "callback", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/OnOsUploadCallback;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OssUploadHelper {
    private final String TAG = "OssUploadHelper";
    private final int CONNECTION_TIMEOUT = 60000;
    private final int SOCKET_TIMEOUT = 60000;

    private static final void m33uploadOssFile$lambda0(OnOsUploadCallback onOsUploadCallback, PutObjectRequest putObjectRequest, long j, long j2) {
        if (onOsUploadCallback == null) {
            return;
        }
        onOsUploadCallback.onProgress(j, j2, null);
    }

    public final void uploadOssFile(final String path, String ak, String sk, String token, final String endpoint, String bucket, List<String> pathExt, String fileExtraPath, final Map<String, Boolean> fileSecurityMap, Map<String, UploadSecurityConfig> fileSecurityConfigMap, final OnOsUploadCallback callback) {
        Intrinsics.checkNotNullParameter(ak, "ak");
        Intrinsics.checkNotNullParameter(sk, "sk");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(bucket, "bucket");
        Intrinsics.checkNotNullParameter(fileSecurityMap, "fileSecurityMap");
        String str = path;
        if ((str == null || str.length() == 0) || StringsKt.isBlank(StringsKt.trim((CharSequence) str).toString())) {
            OsException osExceptionFromOsException = OsException.INSTANCE.fromOsException(107005003, "OSS 文件路径不能为空");
            if (callback == null) {
                return;
            }
            callback.onError(osExceptionFromOsException);
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            OsException osExceptionFromOsException2 = OsException.INSTANCE.fromOsException(107005004, "OSS 文件不存在，请检查");
            if (callback == null) {
                return;
            }
            callback.onError(osExceptionFromOsException2);
            return;
        }
        String appId = XHAppConfigProxy.getInstance().getAppId();
        Intrinsics.checkNotNullExpressionValue(appId, "getInstance().appId");
        String name = file.getName();
        ObsFileUploadUtils obsFileUploadUtils = ObsFileUploadUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(name, "fileName");
        String uploadFileFullPath = obsFileUploadUtils.getUploadFileFullPath(appId, name, pathExt == null ? new ArrayList() : pathExt, fileExtraPath);
        UploadSecurityConfig uploadSecurityConfig = fileSecurityConfigMap == null ? null : fileSecurityConfigMap.get(path);
        final PutObjectRequest putObjectRequestNewPutObjectRequest = OssConfig.newPutObjectRequest(bucket, uploadFileFullPath, path);
        Intrinsics.checkNotNullExpressionValue(putObjectRequestNewPutObjectRequest, "newPutObjectRequest(bucket, objectKey, path)");
        UploadSecurityUtil.secure(uploadSecurityConfig, putObjectRequestNewPutObjectRequest);
        putObjectRequestNewPutObjectRequest.setProgressCallback(new OSSProgressCallback() {
            @Override
            public final void onProgress(Object obj, long j, long j2) {
                OssUploadHelper.m33uploadOssFile$lambda0(callback, (PutObjectRequest) obj, j, j2);
            }
        });
        new OssConfig().setConnectionTimeOut(this.CONNECTION_TIMEOUT).setSocketTimeOut(this.SOCKET_TIMEOUT).getOss(XhBaseApplication.mContext, ak, sk, token).asyncPutObject(putObjectRequestNewPutObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                int i;
                String strStringPlus;
                if (clientException != null) {
                    strStringPlus = Intrinsics.stringPlus("客户端错误：", clientException.getMessage());
                    i = 107003000;
                } else if (serviceException != null) {
                    strStringPlus = "服务器错误：" + ((Object) serviceException.getErrorCode()) + ':' + ((Object) serviceException.getMessage());
                    i = 107003001;
                } else {
                    i = 107003006;
                    strStringPlus = "阿里云直传失败";
                }
                OnOsUploadCallback onOsUploadCallback = callback;
                if (onOsUploadCallback == null) {
                    return;
                }
                onOsUploadCallback.onError(OsException.INSTANCE.fromOsException(i, strStringPlus));
            }

            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                String eTag;
                String upperCase;
                String upperCase2;
                if (request == null) {
                    request = putObjectRequestNewPutObjectRequest;
                }
                if (result == null || (eTag = result.getETag()) == null) {
                    upperCase = null;
                } else {
                    upperCase = eTag.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                }
                String objectKey = request.getObjectKey();
                String fileMd5 = OsTypeUploadHelper.INSTANCE.getFileMd5(path);
                if (fileMd5 == null) {
                    upperCase2 = null;
                } else {
                    upperCase2 = fileMd5.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                }
                Boolean bool = fileSecurityMap.get(path);
                boolean zBooleanValue = bool == null ? false : bool.booleanValue();
                String strGenerateAliyunFileUrlPath = UploadSecurityUtil.generateAliyunFileUrlPath(zBooleanValue, request, endpoint);
                StringBuilder sb = new StringBuilder();
                sb.append(" UploadSuccess，path = ");
                sb.append((Object) path);
                sb.append("，fileMd5 = ");
                sb.append((Object) upperCase2);
                sb.append("， ETag = ");
                sb.append((Object) upperCase);
                sb.append("，requestId = ");
                sb.append((Object) (result == null ? null : result.getRequestId()));
                sb.append("，statusCode = ");
                sb.append(result != null ? Integer.valueOf(result.getStatusCode()) : null);
                sb.append("，objectKey = ");
                sb.append((Object) objectKey);
                sb.append("，url = ");
                sb.append(strGenerateAliyunFileUrlPath);
                LogUtils.Companion.i$default(LogUtils.INSTANCE, this.TAG, sb.toString(), null, 4, null);
                OsResponse osResponse = OsResponse.INSTANCE.toOsResponse(path, strGenerateAliyunFileUrlPath, upperCase, Boolean.valueOf(zBooleanValue));
                OnOsUploadCallback onOsUploadCallback = callback;
                if (onOsUploadCallback == null) {
                    return;
                }
                onOsUploadCallback.onSuccess(osResponse);
            }
        });
    }
}

package com.xh.xhcore.common.obs;

import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.DefaultProgressStatus;
import com.obs.services.model.ProgressListener;
import com.obs.services.model.ProgressStatus;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.OnOsUploadCallback;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.OsTypeUploadHelper;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsResponse;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.exception.OsException;
import com.xh.xhcore.common.util.XHEncodeUtil;
import java.io.File;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010\u000eJp\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\t2\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\r0\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/xh/xhcore/common/obs/ObsUploadHelper;", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Ljava/util/concurrent/ExecutorService;)V", "CONNECTION_TIMEOUT", "", "SOCKET_TIMEOUT", "TAG", "", "getObsFileUrl", "objectUrl", "isPrivate", "", "(Ljava/lang/String;Ljava/lang/Boolean;)Ljava/lang/String;", "uploadObsFile", "", "path", "ak", "sk", "token", "endpoint", "bucket", "pathExt", "", "fileExtraPath", "fileSecurityMap", "", "callback", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/OnOsUploadCallback;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ObsUploadHelper {
    private final int CONNECTION_TIMEOUT;
    private final int SOCKET_TIMEOUT;
    private final String TAG;
    private final ExecutorService executorService;

    public ObsUploadHelper(ExecutorService executorService) {
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        this.executorService = executorService;
        this.TAG = "ObsUploadHelper";
        this.CONNECTION_TIMEOUT = 60000;
        this.SOCKET_TIMEOUT = 60000;
    }

    private final String getObsFileUrl(String objectUrl, Boolean isPrivate) {
        String strUrlDecode = XHEncodeUtil.urlDecode(objectUrl);
        if (Intrinsics.areEqual((Object) isPrivate, (Object) true)) {
            strUrlDecode = Intrinsics.stringPlus(strUrlDecode, "?!");
        }
        Intrinsics.checkNotNullExpressionValue(strUrlDecode, "resultUrl");
        return strUrlDecode;
    }

    private static final void m30uploadObsFile$lambda2(ObsUploadHelper obsUploadHelper, String str, String str2, String str3, String str4, String str5, String str6, List list, String str7, String str8, File file, Map map, String str9, final OnOsUploadCallback onOsUploadCallback) {
        String etag;
        String upperCase;
        String upperCase2;
        String objectUrl;
        Intrinsics.checkNotNullParameter(obsUploadHelper, "this$0");
        Intrinsics.checkNotNullParameter(str, "$endpoint");
        Intrinsics.checkNotNullParameter(str2, "$ak");
        Intrinsics.checkNotNullParameter(str3, "$sk");
        Intrinsics.checkNotNullParameter(str4, "$token");
        Intrinsics.checkNotNullParameter(str5, "$appId");
        Intrinsics.checkNotNullParameter(str8, "$bucket");
        Intrinsics.checkNotNullParameter(file, "$file");
        Intrinsics.checkNotNullParameter(map, "$fileSecurityMap");
        try {
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public final void uncaughtException(Thread thread, Throwable th) {
                    ObsUploadHelper.m31uploadObsFile$lambda2$lambda0(onOsUploadCallback, thread, th);
                }
            });
            ObsConfiguration obsConfiguration = new ObsConfiguration();
            obsConfiguration.setSocketTimeout(obsUploadHelper.SOCKET_TIMEOUT);
            obsConfiguration.setConnectionTimeout(obsUploadHelper.CONNECTION_TIMEOUT);
            obsConfiguration.setEndPoint(str);
            ObsClient obsClient = new ObsClient(str2, str3, str4, obsConfiguration);
            ObsFileUploadUtils obsFileUploadUtils = ObsFileUploadUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(str6, "fileName");
            PutObjectRequest putObjectRequest = new PutObjectRequest(str8, obsFileUploadUtils.getUploadFileFullPath(str5, str6, list == null ? new ArrayList() : list, str7), file);
            putObjectRequest.setProgressListener(new ProgressListener() {
                @Override
                public final void progressChanged(ProgressStatus progressStatus) throws JSONException {
                    ObsUploadHelper.m32uploadObsFile$lambda2$lambda1(onOsUploadCallback, progressStatus);
                }
            });
            PutObjectResult putObjectResultPutObject = obsClient.putObject(putObjectRequest);
            Boolean bool = (Boolean) map.get(str9);
            String str10 = "";
            if (putObjectResultPutObject != null && (objectUrl = putObjectResultPutObject.getObjectUrl()) != null) {
                str10 = objectUrl;
            }
            String obsFileUrl = obsUploadHelper.getObsFileUrl(str10, bool);
            String objectKey = null;
            if (putObjectResultPutObject == null || (etag = putObjectResultPutObject.getEtag()) == null) {
                upperCase = null;
            } else {
                upperCase = etag.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            }
            String fileMd5 = OsTypeUploadHelper.INSTANCE.getFileMd5(str9);
            if (fileMd5 == null) {
                upperCase2 = null;
            } else {
                upperCase2 = fileMd5.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(" UploadSuccess , path = ");
            sb.append((Object) str9);
            sb.append("，fileMd5 = ");
            sb.append((Object) upperCase2);
            sb.append("， ETag = ");
            sb.append((Object) upperCase);
            sb.append("，objectUrl = ");
            sb.append(str10);
            sb.append("，objectKey = ");
            if (putObjectResultPutObject != null) {
                objectKey = putObjectResultPutObject.getObjectKey();
            }
            sb.append((Object) objectKey);
            sb.append("，url = ");
            sb.append(obsFileUrl);
            LogUtils.Companion.i$default(LogUtils.INSTANCE, obsUploadHelper.TAG, sb.toString(), null, 4, null);
            OsResponse osResponse = OsResponse.INSTANCE.toOsResponse(str9, obsFileUrl, upperCase, bool);
            if (onOsUploadCallback == null) {
                return;
            }
            onOsUploadCallback.onSuccess(osResponse);
        } catch (ObsException e) {
            e.printStackTrace();
            if (onOsUploadCallback == null) {
                return;
            }
            onOsUploadCallback.onError(OsException.INSTANCE.fromOsException(e));
        }
    }

    private static final void m31uploadObsFile$lambda2$lambda0(OnOsUploadCallback onOsUploadCallback, Thread thread, Throwable th) {
        OsException.Companion companion = OsException.INSTANCE;
        String message = th.getMessage();
        if (message == null) {
            message = "OBS 未知错误";
        }
        OsException osExceptionFromOsException = companion.fromOsException(107005005, message);
        if (onOsUploadCallback == null) {
            return;
        }
        onOsUploadCallback.onError(osExceptionFromOsException);
    }

    private static final void m32uploadObsFile$lambda2$lambda1(OnOsUploadCallback onOsUploadCallback, ProgressStatus progressStatus) throws JSONException {
        if (progressStatus == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.obs.services.internal.DefaultProgressStatus");
        }
        double instantaneousSpeed = ((DefaultProgressStatus) progressStatus).getInstantaneousSpeed();
        double d = 1048576;
        Double.isNaN(d);
        float f = (float) (instantaneousSpeed / d);
        JSONObject jSONObject = new JSONObject();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        jSONObject.put("speed", Intrinsics.stringPlus(str, "MB/s"));
        if (onOsUploadCallback == null) {
            return;
        }
        onOsUploadCallback.onProgress(r11.getTransferredBytes(), r11.getTotalBytes(), jSONObject.toString());
    }

    public final void uploadObsFile(final String path, final String ak, final String sk, final String token, final String endpoint, final String bucket, final List<String> pathExt, final String fileExtraPath, final Map<String, Boolean> fileSecurityMap, final OnOsUploadCallback callback) {
        Intrinsics.checkNotNullParameter(ak, "ak");
        Intrinsics.checkNotNullParameter(sk, "sk");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(bucket, "bucket");
        Intrinsics.checkNotNullParameter(fileSecurityMap, "fileSecurityMap");
        String str = path;
        if ((str == null || str.length() == 0) || StringsKt.isBlank(StringsKt.trim((CharSequence) str).toString())) {
            OsException osExceptionFromOsException = OsException.INSTANCE.fromOsException(107005003, "OBS 文件路径不能为空");
            if (callback == null) {
                return;
            }
            callback.onError(osExceptionFromOsException);
            return;
        }
        final File file = new File(path);
        if (file.exists()) {
            final String appId = XHAppConfigProxy.getInstance().getAppId();
            Intrinsics.checkNotNullExpressionValue(appId, "getInstance().appId");
            final String name = file.getName();
            this.executorService.execute(new Runnable() {
                @Override
                public final void run() {
                    ObsUploadHelper.m30uploadObsFile$lambda2(this.f$0, endpoint, ak, sk, token, appId, name, pathExt, fileExtraPath, bucket, file, fileSecurityMap, path, callback);
                }
            });
            return;
        }
        OsException osExceptionFromOsException2 = OsException.INSTANCE.fromOsException(107005004, "OBS 文件不存在，请检查");
        if (callback == null) {
            return;
        }
        callback.onError(osExceptionFromOsException2);
    }
}

package com.xh.xhcore.common.http.strategy.xh.security;

import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.xh.xhcore.common.http.strategy.okhttp.upload.UploadOkHttp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J$\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007J/\u0010\f\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u0001H\r\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012J\u001a\u0010\u0013\u001a\u00020\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001a\u0010\u0013\u001a\u00020\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0007¨\u0006\u0017"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/UploadSecurityUtil;", "", "()V", "generateAliyunFileUrlPath", "", "uploadSecurityConfig", "Lcom/xh/xhcore/common/http/strategy/xh/security/UploadSecurityConfig;", "putObjectRequest", "Lcom/alibaba/sdk/android/oss/model/PutObjectRequest;", "endpoint", "isPrivate", "", "getFromList", "T", "index", "", "configList", "", "(ILjava/util/List;)Ljava/lang/Object;", "secure", "", "uploadOkHttp", "Lcom/xh/xhcore/common/http/strategy/okhttp/upload/UploadOkHttp;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UploadSecurityUtil {
    public static final UploadSecurityUtil INSTANCE = new UploadSecurityUtil();

    private UploadSecurityUtil() {
    }

    @JvmStatic
    public static final String generateAliyunFileUrlPath(UploadSecurityConfig uploadSecurityConfig, PutObjectRequest putObjectRequest) {
        Intrinsics.checkNotNullParameter(putObjectRequest, "putObjectRequest");
        return generateAliyunFileUrlPath(uploadSecurityConfig == null ? false : uploadSecurityConfig.isPrivate(), putObjectRequest, "oss-cn-hangzhou.aliyuncs.com");
    }

    @JvmStatic
    public static final String generateAliyunFileUrlPath(UploadSecurityConfig uploadSecurityConfig, PutObjectRequest putObjectRequest, String endpoint) {
        Intrinsics.checkNotNullParameter(putObjectRequest, "putObjectRequest");
        return generateAliyunFileUrlPath(uploadSecurityConfig == null ? false : uploadSecurityConfig.isPrivate(), putObjectRequest, endpoint);
    }

    @JvmStatic
    public static final String generateAliyunFileUrlPath(boolean isPrivate, PutObjectRequest putObjectRequest, String endpoint) {
        Intrinsics.checkNotNullParameter(putObjectRequest, "putObjectRequest");
        String str = endpoint;
        if (str == null || str.length() == 0) {
            endpoint = "oss-cn-hangzhou.aliyuncs.com";
        }
        String str2 = "https://" + ((Object) putObjectRequest.getBucketName()) + '.' + endpoint + '/' + ((Object) putObjectRequest.getObjectKey());
        return isPrivate ? Intrinsics.stringPlus(str2, "?!") : str2;
    }

    @JvmStatic
    public static final <T> T getFromList(int index, List<T> configList) {
        if (configList != null && index >= 0 && index <= configList.size() - 1) {
            return configList.get(index);
        }
        return null;
    }

    @JvmStatic
    public static final void secure(UploadSecurityConfig uploadSecurityConfig, PutObjectRequest putObjectRequest) {
        Intrinsics.checkNotNullParameter(putObjectRequest, "putObjectRequest");
        if (uploadSecurityConfig != null && uploadSecurityConfig.isPrivate()) {
            ObjectMetadata metadata = putObjectRequest.getMetadata();
            if (metadata == null) {
                metadata = new ObjectMetadata();
            }
            metadata.setHeader("x-oss-object-acl", "private");
            metadata.addUserMetadata(Intrinsics.stringPlus("x-oss-meta-", ConstSecurity.SECURITY_CONDITIONS_KEY), uploadSecurityConfig.getConditions());
            putObjectRequest.setMetadata(metadata);
        }
    }

    @JvmStatic
    public static final void secure(UploadSecurityConfig uploadSecurityConfig, UploadOkHttp uploadOkHttp) {
        Intrinsics.checkNotNullParameter(uploadOkHttp, "uploadOkHttp");
        if (uploadSecurityConfig != null && uploadSecurityConfig.isPrivate()) {
            ((UploadOkHttp) ((UploadOkHttp) uploadOkHttp.addHeader("Acl", "private")).addHeader(ConstSecurity.SECURITY_CONDITIONS_KEY, (Object) uploadSecurityConfig.getConditions())).addHeader(ConstSecurity.SECURITY_CONDITION_SIGN_KEY, (Object) ConstSecurity.INSTANCE.computeUploadSecuritySign("private", uploadSecurityConfig.getConditions()));
        }
    }
}

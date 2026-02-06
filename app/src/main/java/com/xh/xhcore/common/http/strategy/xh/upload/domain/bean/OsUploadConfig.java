package com.xh.xhcore.common.http.strategy.xh.upload.domain.bean;

import com.xh.xhcore.common.http.strategy.xh.security.UploadSecurityConfig;
import com.xh.xhcore.common.http.strategy.xh.upload.bean.UploadFileEntity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 *2\u00020\u0001:\u0001*B\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0006¢\u0006\u0002\u0010\u0010J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u0015\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0011\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bHÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\u000bHÆ\u0003J\u0017\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0006HÆ\u0003J\u008d\u0001\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00062\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u000b2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0006HÆ\u0001J\u0013\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017¨\u0006+"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadConfig;", "", "domain", "", "endpoint", "buckets", "", "fileSecurityMap", "", "osType", "extraPath", "", "uploadFileEntities", "Lcom/xh/xhcore/common/http/strategy/xh/upload/bean/UploadFileEntity;", "fileSecurityConfigMap", "Lcom/xh/xhcore/common/http/strategy/xh/security/UploadSecurityConfig;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/Map;)V", "getBuckets", "()Ljava/util/Map;", "getDomain", "()Ljava/lang/String;", "getEndpoint", "getExtraPath", "()Ljava/util/List;", "getFileSecurityConfigMap", "getFileSecurityMap", "getOsType", "getUploadFileEntities", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OsUploadConfig {

    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, String> buckets;
    private final String domain;
    private final String endpoint;
    private final List<String> extraPath;
    private final Map<String, UploadSecurityConfig> fileSecurityConfigMap;
    private final Map<String, Boolean> fileSecurityMap;
    private final String osType;
    private final List<UploadFileEntity> uploadFileEntities;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J~\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\f\u001a\u00020\u00062\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000e2\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0012\u0018\u00010\tH\u0007J`\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000e2\u0006\u0010\u0016\u001a\u00020\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0012\u0018\u00010\tH\u0007¨\u0006\u0017"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadConfig$Companion;", "", "()V", "multiUploadConfig", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadConfig;", "domain", "", "endpoint", "buckets", "", "fileSecurityMap", "", "osType", "extraPath", "", "uploadFileEntities", "Lcom/xh/xhcore/common/http/strategy/xh/upload/bean/UploadFileEntity;", "fileSecurityConfigMap", "Lcom/xh/xhcore/common/http/strategy/xh/security/UploadSecurityConfig;", "singleUploadConfig", "bucket", "isPrivate", "uploadFileEntity", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final OsUploadConfig multiUploadConfig(String domain, String endpoint, Map<String, String> buckets, Map<String, Boolean> fileSecurityMap, String osType, List<String> extraPath, List<UploadFileEntity> uploadFileEntities, Map<String, UploadSecurityConfig> fileSecurityConfigMap) {
            Intrinsics.checkNotNullParameter(domain, "domain");
            Intrinsics.checkNotNullParameter(endpoint, "endpoint");
            Intrinsics.checkNotNullParameter(buckets, "buckets");
            Intrinsics.checkNotNullParameter(fileSecurityMap, "fileSecurityMap");
            Intrinsics.checkNotNullParameter(osType, "osType");
            Intrinsics.checkNotNullParameter(uploadFileEntities, "uploadFileEntities");
            return new OsUploadConfig(domain, endpoint, buckets, fileSecurityMap, osType, extraPath, uploadFileEntities, fileSecurityConfigMap);
        }

        @JvmStatic
        public final OsUploadConfig singleUploadConfig(String domain, String endpoint, String bucket, boolean isPrivate, String osType, List<String> extraPath, UploadFileEntity uploadFileEntity, Map<String, UploadSecurityConfig> fileSecurityConfigMap) {
            Intrinsics.checkNotNullParameter(domain, "domain");
            Intrinsics.checkNotNullParameter(endpoint, "endpoint");
            Intrinsics.checkNotNullParameter(bucket, "bucket");
            Intrinsics.checkNotNullParameter(osType, "osType");
            Intrinsics.checkNotNullParameter(uploadFileEntity, "uploadFileEntity");
            String localPath = uploadFileEntity.getLocalPath();
            return new OsUploadConfig(domain, endpoint, MapsKt.mutableMapOf(new Pair(localPath, bucket)), MapsKt.mutableMapOf(new Pair(localPath, Boolean.valueOf(isPrivate))), osType, extraPath, CollectionsKt.listOf(uploadFileEntity), fileSecurityConfigMap);
        }
    }

    public OsUploadConfig(String str, String str2, Map<String, String> map, Map<String, Boolean> map2, String str3, List<String> list, List<UploadFileEntity> list2, Map<String, UploadSecurityConfig> map3) {
        Intrinsics.checkNotNullParameter(str, "domain");
        Intrinsics.checkNotNullParameter(str2, "endpoint");
        Intrinsics.checkNotNullParameter(map, "buckets");
        Intrinsics.checkNotNullParameter(map2, "fileSecurityMap");
        Intrinsics.checkNotNullParameter(str3, "osType");
        Intrinsics.checkNotNullParameter(list2, "uploadFileEntities");
        this.domain = str;
        this.endpoint = str2;
        this.buckets = map;
        this.fileSecurityMap = map2;
        this.osType = str3;
        this.extraPath = list;
        this.uploadFileEntities = list2;
        this.fileSecurityConfigMap = map3;
    }

    public OsUploadConfig(String str, String str2, Map map, Map map2, String str3, List list, List list2, Map map3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? new LinkedHashMap() : map, (i & 8) != 0 ? new LinkedHashMap() : map2, str3, (i & 32) != 0 ? null : list, list2, (i & 128) != 0 ? null : map3);
    }

    @JvmStatic
    public static final OsUploadConfig multiUploadConfig(String str, String str2, Map<String, String> map, Map<String, Boolean> map2, String str3, List<String> list, List<UploadFileEntity> list2, Map<String, UploadSecurityConfig> map3) {
        return INSTANCE.multiUploadConfig(str, str2, map, map2, str3, list, list2, map3);
    }

    @JvmStatic
    public static final OsUploadConfig singleUploadConfig(String str, String str2, String str3, boolean z, String str4, List<String> list, UploadFileEntity uploadFileEntity, Map<String, UploadSecurityConfig> map) {
        return INSTANCE.singleUploadConfig(str, str2, str3, z, str4, list, uploadFileEntity, map);
    }

    public final String getDomain() {
        return this.domain;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final Map<String, String> component3() {
        return this.buckets;
    }

    public final Map<String, Boolean> component4() {
        return this.fileSecurityMap;
    }

    public final String getOsType() {
        return this.osType;
    }

    public final List<String> component6() {
        return this.extraPath;
    }

    public final List<UploadFileEntity> component7() {
        return this.uploadFileEntities;
    }

    public final Map<String, UploadSecurityConfig> component8() {
        return this.fileSecurityConfigMap;
    }

    public final OsUploadConfig copy(String domain, String endpoint, Map<String, String> buckets, Map<String, Boolean> fileSecurityMap, String osType, List<String> extraPath, List<UploadFileEntity> uploadFileEntities, Map<String, UploadSecurityConfig> fileSecurityConfigMap) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(buckets, "buckets");
        Intrinsics.checkNotNullParameter(fileSecurityMap, "fileSecurityMap");
        Intrinsics.checkNotNullParameter(osType, "osType");
        Intrinsics.checkNotNullParameter(uploadFileEntities, "uploadFileEntities");
        return new OsUploadConfig(domain, endpoint, buckets, fileSecurityMap, osType, extraPath, uploadFileEntities, fileSecurityConfigMap);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OsUploadConfig)) {
            return false;
        }
        OsUploadConfig osUploadConfig = (OsUploadConfig) other;
        return Intrinsics.areEqual(this.domain, osUploadConfig.domain) && Intrinsics.areEqual(this.endpoint, osUploadConfig.endpoint) && Intrinsics.areEqual(this.buckets, osUploadConfig.buckets) && Intrinsics.areEqual(this.fileSecurityMap, osUploadConfig.fileSecurityMap) && Intrinsics.areEqual(this.osType, osUploadConfig.osType) && Intrinsics.areEqual(this.extraPath, osUploadConfig.extraPath) && Intrinsics.areEqual(this.uploadFileEntities, osUploadConfig.uploadFileEntities) && Intrinsics.areEqual(this.fileSecurityConfigMap, osUploadConfig.fileSecurityConfigMap);
    }

    public final Map<String, String> getBuckets() {
        return this.buckets;
    }

    public final String getDomain() {
        return this.domain;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final List<String> getExtraPath() {
        return this.extraPath;
    }

    public final Map<String, UploadSecurityConfig> getFileSecurityConfigMap() {
        return this.fileSecurityConfigMap;
    }

    public final Map<String, Boolean> getFileSecurityMap() {
        return this.fileSecurityMap;
    }

    public final String getOsType() {
        return this.osType;
    }

    public final List<UploadFileEntity> getUploadFileEntities() {
        return this.uploadFileEntities;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.domain.hashCode() * 31) + this.endpoint.hashCode()) * 31) + this.buckets.hashCode()) * 31) + this.fileSecurityMap.hashCode()) * 31) + this.osType.hashCode()) * 31;
        List<String> list = this.extraPath;
        int iHashCode2 = (((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.uploadFileEntities.hashCode()) * 31;
        Map<String, UploadSecurityConfig> map = this.fileSecurityConfigMap;
        return iHashCode2 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "OsUploadConfig(domain=" + this.domain + ", endpoint=" + this.endpoint + ", buckets=" + this.buckets + ", fileSecurityMap=" + this.fileSecurityMap + ", osType=" + this.osType + ", extraPath=" + this.extraPath + ", uploadFileEntities=" + this.uploadFileEntities + ", fileSecurityConfigMap=" + this.fileSecurityConfigMap + ')';
    }
}

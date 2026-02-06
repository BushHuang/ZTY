package com.xh.xhcore.common.http.strategy.xh.upload.domain.bean;

import com.google.gson.Gson;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J[\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\u0016\u0010+\u001a\u0004\u0018\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006J\b\u0010,\u001a\u00020\u0003H\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0011\"\u0004\b\u001d\u0010\u0013¨\u0006-"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/DomainObjectStorageConfig;", "", "domainFlag", "", "osType", "buckets", "", "publicBuckets", "endpoint", "domain", "regionId", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBuckets", "()Ljava/util/List;", "setBuckets", "(Ljava/util/List;)V", "getDomain", "()Ljava/lang/String;", "setDomain", "(Ljava/lang/String;)V", "getDomainFlag", "setDomainFlag", "getEndpoint", "setEndpoint", "getOsType", "setOsType", "getPublicBuckets", "setPublicBuckets", "getRegionId", "setRegionId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "randomBucket", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DomainObjectStorageConfig {
    private List<String> buckets;
    private String domain;
    private String domainFlag;
    private String endpoint;
    private String osType;
    private List<String> publicBuckets;
    private String regionId;

    public DomainObjectStorageConfig() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public DomainObjectStorageConfig(String str, String str2, List<String> list, List<String> list2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "domainFlag");
        Intrinsics.checkNotNullParameter(str2, "osType");
        Intrinsics.checkNotNullParameter(list, "buckets");
        Intrinsics.checkNotNullParameter(list2, "publicBuckets");
        Intrinsics.checkNotNullParameter(str3, "endpoint");
        Intrinsics.checkNotNullParameter(str4, "domain");
        Intrinsics.checkNotNullParameter(str5, "regionId");
        this.domainFlag = str;
        this.osType = str2;
        this.buckets = list;
        this.publicBuckets = list2;
        this.endpoint = str3;
        this.domain = str4;
        this.regionId = str5;
    }

    public DomainObjectStorageConfig(String str, String str2, List list, List list2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? CollectionsKt.emptyList() : list2, (i & 16) != 0 ? "" : str3, (i & 32) != 0 ? "" : str4, (i & 64) != 0 ? "" : str5);
    }

    public static DomainObjectStorageConfig copy$default(DomainObjectStorageConfig domainObjectStorageConfig, String str, String str2, List list, List list2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = domainObjectStorageConfig.domainFlag;
        }
        if ((i & 2) != 0) {
            str2 = domainObjectStorageConfig.osType;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            list = domainObjectStorageConfig.buckets;
        }
        List list3 = list;
        if ((i & 8) != 0) {
            list2 = domainObjectStorageConfig.publicBuckets;
        }
        List list4 = list2;
        if ((i & 16) != 0) {
            str3 = domainObjectStorageConfig.endpoint;
        }
        String str7 = str3;
        if ((i & 32) != 0) {
            str4 = domainObjectStorageConfig.domain;
        }
        String str8 = str4;
        if ((i & 64) != 0) {
            str5 = domainObjectStorageConfig.regionId;
        }
        return domainObjectStorageConfig.copy(str, str6, list3, list4, str7, str8, str5);
    }

    public final String getDomainFlag() {
        return this.domainFlag;
    }

    public final String getOsType() {
        return this.osType;
    }

    public final List<String> component3() {
        return this.buckets;
    }

    public final List<String> component4() {
        return this.publicBuckets;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final String getDomain() {
        return this.domain;
    }

    public final String getRegionId() {
        return this.regionId;
    }

    public final DomainObjectStorageConfig copy(String domainFlag, String osType, List<String> buckets, List<String> publicBuckets, String endpoint, String domain, String regionId) {
        Intrinsics.checkNotNullParameter(domainFlag, "domainFlag");
        Intrinsics.checkNotNullParameter(osType, "osType");
        Intrinsics.checkNotNullParameter(buckets, "buckets");
        Intrinsics.checkNotNullParameter(publicBuckets, "publicBuckets");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(domain, "domain");
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        return new DomainObjectStorageConfig(domainFlag, osType, buckets, publicBuckets, endpoint, domain, regionId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DomainObjectStorageConfig)) {
            return false;
        }
        DomainObjectStorageConfig domainObjectStorageConfig = (DomainObjectStorageConfig) other;
        return Intrinsics.areEqual(this.domainFlag, domainObjectStorageConfig.domainFlag) && Intrinsics.areEqual(this.osType, domainObjectStorageConfig.osType) && Intrinsics.areEqual(this.buckets, domainObjectStorageConfig.buckets) && Intrinsics.areEqual(this.publicBuckets, domainObjectStorageConfig.publicBuckets) && Intrinsics.areEqual(this.endpoint, domainObjectStorageConfig.endpoint) && Intrinsics.areEqual(this.domain, domainObjectStorageConfig.domain) && Intrinsics.areEqual(this.regionId, domainObjectStorageConfig.regionId);
    }

    public final List<String> getBuckets() {
        return this.buckets;
    }

    public final String getDomain() {
        return this.domain;
    }

    public final String getDomainFlag() {
        return this.domainFlag;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final String getOsType() {
        return this.osType;
    }

    public final List<String> getPublicBuckets() {
        return this.publicBuckets;
    }

    public final String getRegionId() {
        return this.regionId;
    }

    public int hashCode() {
        return (((((((((((this.domainFlag.hashCode() * 31) + this.osType.hashCode()) * 31) + this.buckets.hashCode()) * 31) + this.publicBuckets.hashCode()) * 31) + this.endpoint.hashCode()) * 31) + this.domain.hashCode()) * 31) + this.regionId.hashCode();
    }

    public final String randomBucket(List<String> buckets) {
        Intrinsics.checkNotNullParameter(buckets, "buckets");
        if (buckets.isEmpty()) {
            return null;
        }
        return buckets.get(Random.INSTANCE.nextInt(buckets.size()));
    }

    public final void setBuckets(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.buckets = list;
    }

    public final void setDomain(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.domain = str;
    }

    public final void setDomainFlag(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.domainFlag = str;
    }

    public final void setEndpoint(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.endpoint = str;
    }

    public final void setOsType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.osType = str;
    }

    public final void setPublicBuckets(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.publicBuckets = list;
    }

    public final void setRegionId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.regionId = str;
    }

    public String toString() {
        String json = new Gson().toJson(this);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(this)");
        return json;
    }
}

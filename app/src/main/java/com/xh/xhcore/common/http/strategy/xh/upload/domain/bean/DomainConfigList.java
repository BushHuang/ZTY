package com.xh.xhcore.common.http.strategy.xh.upload.domain.bean;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/DomainConfigList;", "", "domainObjectStorageConfigList", "", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/DomainObjectStorageConfig;", "(Ljava/util/List;)V", "getDomainObjectStorageConfigList", "()Ljava/util/List;", "setDomainObjectStorageConfigList", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DomainConfigList {

    public static final Companion INSTANCE = new Companion(null);
    private List<DomainObjectStorageConfig> domainObjectStorageConfigList;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/DomainConfigList$Companion;", "", "()V", "toDomainConfigList", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/DomainConfigList;", "result", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DomainConfigList toDomainConfigList(String result) throws JsonSyntaxException {
            Intrinsics.checkNotNullParameter(result, "result");
            Object objFromJson = new Gson().fromJson(result, (Class<Object>) DomainConfigList.class);
            Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(result, …inConfigList::class.java)");
            return (DomainConfigList) objFromJson;
        }
    }

    public DomainConfigList() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DomainConfigList(List<DomainObjectStorageConfig> list) {
        Intrinsics.checkNotNullParameter(list, "domainObjectStorageConfigList");
        this.domainObjectStorageConfigList = list;
    }

    public DomainConfigList(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public static DomainConfigList copy$default(DomainConfigList domainConfigList, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = domainConfigList.domainObjectStorageConfigList;
        }
        return domainConfigList.copy(list);
    }

    public final List<DomainObjectStorageConfig> component1() {
        return this.domainObjectStorageConfigList;
    }

    public final DomainConfigList copy(List<DomainObjectStorageConfig> domainObjectStorageConfigList) {
        Intrinsics.checkNotNullParameter(domainObjectStorageConfigList, "domainObjectStorageConfigList");
        return new DomainConfigList(domainObjectStorageConfigList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DomainConfigList) && Intrinsics.areEqual(this.domainObjectStorageConfigList, ((DomainConfigList) other).domainObjectStorageConfigList);
    }

    public final List<DomainObjectStorageConfig> getDomainObjectStorageConfigList() {
        return this.domainObjectStorageConfigList;
    }

    public int hashCode() {
        return this.domainObjectStorageConfigList.hashCode();
    }

    public final void setDomainObjectStorageConfigList(List<DomainObjectStorageConfig> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.domainObjectStorageConfigList = list;
    }

    public String toString() {
        return "DomainConfigList(domainObjectStorageConfigList=" + this.domainObjectStorageConfigList + ')';
    }
}

package com.xh.xhcore.common.http.strategy.xh.upload.domain.bean;

import com.google.gson.Gson;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u000eJL\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\b\u0010%\u001a\u00020\u0003H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\t\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\r¨\u0006'"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsResponse;", "", "path", "", "url", "eTag", "uploadFilesBean", "", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadFilesBean;", "isPrivate", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)V", "getETag", "()Ljava/lang/String;", "()Ljava/lang/Boolean;", "setPrivate", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getPath", "setPath", "(Ljava/lang/String;)V", "getUploadFilesBean", "()Ljava/util/List;", "setUploadFilesBean", "(Ljava/util/List;)V", "getUrl", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsResponse;", "equals", "other", "hashCode", "", "toString", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OsResponse {

    public static final Companion INSTANCE = new Companion(null);
    private final String eTag;
    private Boolean isPrivate;
    private String path;
    private List<OsUploadFilesBean> uploadFilesBean;
    private final String url;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsResponse$Companion;", "", "()V", "toOsResponse", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsResponse;", "path", "", "url", "eTag", "isPrivate", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsResponse;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static OsResponse toOsResponse$default(Companion companion, String str, String str2, String str3, Boolean bool, int i, Object obj) {
            if ((i & 8) != 0) {
                bool = true;
            }
            return companion.toOsResponse(str, str2, str3, bool);
        }

        @JvmStatic
        public final OsResponse toOsResponse(String path, String url, String eTag, Boolean isPrivate) {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(url, "url");
            return new OsResponse(path, url, eTag, null, isPrivate, 8, null);
        }
    }

    public OsResponse(String str, String str2, String str3, List<OsUploadFilesBean> list, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "path");
        Intrinsics.checkNotNullParameter(str2, "url");
        this.path = str;
        this.url = str2;
        this.eTag = str3;
        this.uploadFilesBean = list;
        this.isPrivate = bool;
    }

    public OsResponse(String str, String str2, String str3, List list, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : list, (i & 16) != 0 ? true : bool);
    }

    public static OsResponse copy$default(OsResponse osResponse, String str, String str2, String str3, List list, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = osResponse.path;
        }
        if ((i & 2) != 0) {
            str2 = osResponse.url;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = osResponse.eTag;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            list = osResponse.uploadFilesBean;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            bool = osResponse.isPrivate;
        }
        return osResponse.copy(str, str4, str5, list2, bool);
    }

    @JvmStatic
    public static final OsResponse toOsResponse(String str, String str2, String str3, Boolean bool) {
        return INSTANCE.toOsResponse(str, str2, str3, bool);
    }

    public final String getPath() {
        return this.path;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getETag() {
        return this.eTag;
    }

    public final List<OsUploadFilesBean> component4() {
        return this.uploadFilesBean;
    }

    public final Boolean getIsPrivate() {
        return this.isPrivate;
    }

    public final OsResponse copy(String path, String url, String eTag, List<OsUploadFilesBean> uploadFilesBean, Boolean isPrivate) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(url, "url");
        return new OsResponse(path, url, eTag, uploadFilesBean, isPrivate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OsResponse)) {
            return false;
        }
        OsResponse osResponse = (OsResponse) other;
        return Intrinsics.areEqual(this.path, osResponse.path) && Intrinsics.areEqual(this.url, osResponse.url) && Intrinsics.areEqual(this.eTag, osResponse.eTag) && Intrinsics.areEqual(this.uploadFilesBean, osResponse.uploadFilesBean) && Intrinsics.areEqual(this.isPrivate, osResponse.isPrivate);
    }

    public final String getETag() {
        return this.eTag;
    }

    public final String getPath() {
        return this.path;
    }

    public final List<OsUploadFilesBean> getUploadFilesBean() {
        return this.uploadFilesBean;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iHashCode = ((this.path.hashCode() * 31) + this.url.hashCode()) * 31;
        String str = this.eTag;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<OsUploadFilesBean> list = this.uploadFilesBean;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        Boolean bool = this.isPrivate;
        return iHashCode3 + (bool != null ? bool.hashCode() : 0);
    }

    public final Boolean isPrivate() {
        return this.isPrivate;
    }

    public final void setPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }

    public final void setPrivate(Boolean bool) {
        this.isPrivate = bool;
    }

    public final void setUploadFilesBean(List<OsUploadFilesBean> list) {
        this.uploadFilesBean = list;
    }

    public String toString() {
        String json = new Gson().toJson(this);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(this)");
        return json;
    }
}

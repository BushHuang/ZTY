package com.xh.xhcore.common.http.strategy.xh.upload.domain.bean;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\bJ0\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0005\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadFilesBean;", "", "url", "", "md5", "isPrivate", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "()Ljava/lang/Boolean;", "setPrivate", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", "getUrl", "setUrl", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadFilesBean;", "equals", "other", "hashCode", "", "toString", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OsUploadFilesBean {

    public static final Companion INSTANCE = new Companion(null);
    private Boolean isPrivate;
    private String md5;
    private String url;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\tJ)\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadFilesBean$Companion;", "", "()V", "toOsUploadFilesBean", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadFilesBean;", "url", "", "isPrivate", "", "(Ljava/lang/String;Ljava/lang/Boolean;)Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadFilesBean;", "md5", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadFilesBean;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final OsUploadFilesBean toOsUploadFilesBean(String url, Boolean isPrivate) {
            return toOsUploadFilesBean(url, isPrivate, "");
        }

        @JvmStatic
        public final OsUploadFilesBean toOsUploadFilesBean(String url, Boolean isPrivate, String md5) {
            Intrinsics.checkNotNullParameter(md5, "md5");
            return new OsUploadFilesBean(url, md5, isPrivate);
        }
    }

    public OsUploadFilesBean() {
        this(null, null, null, 7, null);
    }

    public OsUploadFilesBean(String str, String str2, Boolean bool) {
        Intrinsics.checkNotNullParameter(str2, "md5");
        this.url = str;
        this.md5 = str2;
        this.isPrivate = bool;
    }

    public OsUploadFilesBean(String str, String str2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? true : bool);
    }

    public static OsUploadFilesBean copy$default(OsUploadFilesBean osUploadFilesBean, String str, String str2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = osUploadFilesBean.url;
        }
        if ((i & 2) != 0) {
            str2 = osUploadFilesBean.md5;
        }
        if ((i & 4) != 0) {
            bool = osUploadFilesBean.isPrivate;
        }
        return osUploadFilesBean.copy(str, str2, bool);
    }

    @JvmStatic
    public static final OsUploadFilesBean toOsUploadFilesBean(String str, Boolean bool) {
        return INSTANCE.toOsUploadFilesBean(str, bool);
    }

    @JvmStatic
    public static final OsUploadFilesBean toOsUploadFilesBean(String str, Boolean bool, String str2) {
        return INSTANCE.toOsUploadFilesBean(str, bool, str2);
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final Boolean getIsPrivate() {
        return this.isPrivate;
    }

    public final OsUploadFilesBean copy(String url, String md5, Boolean isPrivate) {
        Intrinsics.checkNotNullParameter(md5, "md5");
        return new OsUploadFilesBean(url, md5, isPrivate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OsUploadFilesBean)) {
            return false;
        }
        OsUploadFilesBean osUploadFilesBean = (OsUploadFilesBean) other;
        return Intrinsics.areEqual(this.url, osUploadFilesBean.url) && Intrinsics.areEqual(this.md5, osUploadFilesBean.md5) && Intrinsics.areEqual(this.isPrivate, osUploadFilesBean.isPrivate);
    }

    public final String getMd5() {
        return this.md5;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        String str = this.url;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.md5.hashCode()) * 31;
        Boolean bool = this.isPrivate;
        return iHashCode + (bool != null ? bool.hashCode() : 0);
    }

    public final Boolean isPrivate() {
        return this.isPrivate;
    }

    public final void setMd5(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.md5 = str;
    }

    public final void setPrivate(Boolean bool) {
        this.isPrivate = bool;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "OsUploadFilesBean(url=" + ((Object) this.url) + ", md5=" + this.md5 + ", isPrivate=" + this.isPrivate + ')';
    }
}

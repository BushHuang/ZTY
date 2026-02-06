package com.xuehai.launcher.common.util.app;

import android.content.pm.PackageInfo;
import com.xuehai.launcher.common.base.BaseApplication;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b'\b\u0086\b\u0018\u0000 22\u00020\u0001:\u00012Bc\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010*\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010+\u001a\u00020\fHÆ\u0003Jl\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020\f2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u0006HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019¨\u00063"}, d2 = {"Lcom/xuehai/launcher/common/util/app/AppShortcut;", "", "name", "", "packageName", "versionCode", "", "versionName", "sourceDir", "uid", "flags", "isInstalled", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "getFlags", "()Ljava/lang/Integer;", "setFlags", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "()Z", "setInstalled", "(Z)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPackageName", "setPackageName", "getSourceDir", "setSourceDir", "getUid", "setUid", "getVersionCode", "setVersionCode", "getVersionName", "setVersionName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Z)Lcom/xuehai/launcher/common/util/app/AppShortcut;", "equals", "other", "hashCode", "toString", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppShortcut {

    public static final Companion INSTANCE = new Companion(null);
    private Integer flags;
    private boolean isInstalled;
    private String name;
    private String packageName;
    private String sourceDir;
    private Integer uid;
    private Integer versionCode;
    private String versionName;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/xuehai/launcher/common/util/app/AppShortcut$Companion;", "", "()V", "transform", "Lcom/xuehai/launcher/common/util/app/AppShortcut;", "packageInfo", "Landroid/content/pm/PackageInfo;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final AppShortcut transform(PackageInfo packageInfo) {
            if (packageInfo == null) {
                return (AppShortcut) null;
            }
            String string = BaseApplication.INSTANCE.getInstance().getPackageManager().getApplicationLabel(packageInfo.applicationInfo).toString();
            String str = packageInfo.applicationInfo.packageName;
            int i = packageInfo.versionCode;
            String str2 = packageInfo.versionName;
            String str3 = packageInfo.applicationInfo.sourceDir;
            int i2 = packageInfo.applicationInfo.flags;
            return new AppShortcut(string, str, Integer.valueOf(i), str2, str3, Integer.valueOf(packageInfo.applicationInfo.uid), Integer.valueOf(i2), packageInfo.applicationInfo != null);
        }
    }

    public AppShortcut() {
        this(null, null, null, null, null, null, null, false, 255, null);
    }

    public AppShortcut(String str, String str2, Integer num, String str3, String str4, Integer num2, Integer num3, boolean z) {
        this.name = str;
        this.packageName = str2;
        this.versionCode = num;
        this.versionName = str3;
        this.sourceDir = str4;
        this.uid = num2;
        this.flags = num3;
        this.isInstalled = z;
    }

    public AppShortcut(String str, String str2, Integer num, String str3, String str4, Integer num2, Integer num3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? 0 : num, (i & 8) != 0 ? null : str3, (i & 16) == 0 ? str4 : null, (i & 32) != 0 ? 0 : num2, (i & 64) != 0 ? 0 : num3, (i & 128) == 0 ? z : false);
    }

    @JvmStatic
    public static final AppShortcut transform(PackageInfo packageInfo) {
        return INSTANCE.transform(packageInfo);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Integer getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final String getSourceDir() {
        return this.sourceDir;
    }

    public final Integer getUid() {
        return this.uid;
    }

    public final Integer getFlags() {
        return this.flags;
    }

    public final boolean getIsInstalled() {
        return this.isInstalled;
    }

    public final AppShortcut copy(String name, String packageName, Integer versionCode, String versionName, String sourceDir, Integer uid, Integer flags, boolean isInstalled) {
        return new AppShortcut(name, packageName, versionCode, versionName, sourceDir, uid, flags, isInstalled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppShortcut)) {
            return false;
        }
        AppShortcut appShortcut = (AppShortcut) other;
        return Intrinsics.areEqual(this.name, appShortcut.name) && Intrinsics.areEqual(this.packageName, appShortcut.packageName) && Intrinsics.areEqual(this.versionCode, appShortcut.versionCode) && Intrinsics.areEqual(this.versionName, appShortcut.versionName) && Intrinsics.areEqual(this.sourceDir, appShortcut.sourceDir) && Intrinsics.areEqual(this.uid, appShortcut.uid) && Intrinsics.areEqual(this.flags, appShortcut.flags) && this.isInstalled == appShortcut.isInstalled;
    }

    public final Integer getFlags() {
        return this.flags;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getSourceDir() {
        return this.sourceDir;
    }

    public final Integer getUid() {
        return this.uid;
    }

    public final Integer getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.packageName;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.versionCode;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.versionName;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.sourceDir;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num2 = this.uid;
        int iHashCode6 = (iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.flags;
        int iHashCode7 = (iHashCode6 + (num3 != null ? num3.hashCode() : 0)) * 31;
        boolean z = this.isInstalled;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode7 + i;
    }

    public final boolean isInstalled() {
        return this.isInstalled;
    }

    public final void setFlags(Integer num) {
        this.flags = num;
    }

    public final void setInstalled(boolean z) {
        this.isInstalled = z;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setPackageName(String str) {
        this.packageName = str;
    }

    public final void setSourceDir(String str) {
        this.sourceDir = str;
    }

    public final void setUid(Integer num) {
        this.uid = num;
    }

    public final void setVersionCode(Integer num) {
        this.versionCode = num;
    }

    public final void setVersionName(String str) {
        this.versionName = str;
    }

    public String toString() {
        return "AppShortcut(name=" + this.name + ", packageName=" + this.packageName + ", versionCode=" + this.versionCode + ", versionName=" + this.versionName + ", sourceDir=" + this.sourceDir + ", uid=" + this.uid + ", flags=" + this.flags + ", isInstalled=" + this.isInstalled + ')';
    }
}

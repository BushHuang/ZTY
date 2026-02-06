package com.xuehai.mdm.config;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0016\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0016J\u0018\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001c"}, d2 = {"Lcom/xuehai/mdm/config/DeviceConfiguration;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getAppBlackList", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getAppWhiteList", "getForbiddenComponentNames", "", "Landroid/content/ComponentName;", "isAppInstalled", "", "packageName", "flag", "", "isDevicePolicyServiceSupport", "isOsVersionValid", "isSDKSupport", "isVersionCodeValid", "versionCode", "isVersionNameValid", "versionName", "Companion", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DeviceConfiguration {
    public static final String TAG = "DeviceConfiguration";
    private final Context context;

    public DeviceConfiguration(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public static boolean isAppInstalled$default(DeviceConfiguration deviceConfiguration, Context context, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isAppInstalled");
        }
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return deviceConfiguration.isAppInstalled(context, str, i);
    }

    public HashSet<String> getAppBlackList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        Resources resources = this.context.getResources();
        List<ApplicationInfo> installedApplications = this.context.getPackageManager().getInstalledApplications(0);
        Intrinsics.checkNotNullExpressionValue(installedApplications, "allApplications");
        ArrayList arrayList = new ArrayList();
        for (Object obj : installedApplications) {
            if ((((ApplicationInfo) obj).flags & 1) == 0) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            hashSet.add(((ApplicationInfo) it.next()).packageName);
        }
        HashSet<String> hashSet2 = hashSet;
        String[] stringArray = resources.getStringArray(R.array.un_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.un_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray);
        String[] stringArray2 = resources.getStringArray(R.array.android_un_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "resources.getStringArray…rray.android_un_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray2);
        return hashSet;
    }

    public HashSet<String> getAppWhiteList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        Resources resources = this.context.getResources();
        HashSet<String> hashSet2 = hashSet;
        String[] stringArray = resources.getStringArray(R.array.xuehai_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.xuehai_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray);
        String[] stringArray2 = resources.getStringArray(R.array.android_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "resources.getStringArray(R.array.android_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray2);
        hashSet.add(this.context.getPackageName());
        return hashSet;
    }

    public final Context getContext() {
        return this.context;
    }

    public List<ComponentName> getForbiddenComponentNames() {
        return new ArrayList();
    }

    public final boolean isAppInstalled(Context context, String packageName, int flag) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName == null ? context.getPackageName() : packageName, flag);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("DeviceConfiguration", "没有找到应用信息 : " + packageName);
            applicationInfo = (ApplicationInfo) null;
        }
        return applicationInfo != null;
    }

    public boolean isDevicePolicyServiceSupport() {
        return true;
    }

    public boolean isOsVersionValid() {
        return true;
    }

    public boolean isSDKSupport() {
        return false;
    }

    public boolean isVersionCodeValid(int versionCode) {
        return versionCode > 30000000;
    }

    public boolean isVersionNameValid(String versionName) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        return true;
    }
}

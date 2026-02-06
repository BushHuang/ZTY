package com.xuehai.mdm.config;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u0018\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016¨\u0006\u0010"}, d2 = {"Lcom/xuehai/mdm/config/LenovoConfiguration;", "Lcom/xuehai/mdm/config/DeviceConfiguration;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAppBlackList", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getAppWhiteList", "getForbiddenComponentNames", "", "Landroid/content/ComponentName;", "isDevicePolicyServiceSupport", "", "isSDKSupport", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LenovoConfiguration extends DeviceConfiguration {
    public LenovoConfiguration(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i("DeviceConfiguration", "加载Lenovo配置");
    }

    @Override
    public HashSet<String> getAppBlackList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppBlackList());
        HashSet<String> hashSet2 = hashSet;
        String[] stringArray = getContext().getResources().getStringArray(R.array.lenovo_un_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…array.lenovo_un_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray);
        String[] stringArray2 = getContext().getResources().getStringArray(R.array.lenovo_un_keep_app_x6c6f);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…lenovo_un_keep_app_x6c6f)");
        CollectionsKt.addAll(hashSet2, stringArray2);
        String[] stringArray3 = getContext().getResources().getStringArray(R.array.lenovo_un_keep_app_223fc);
        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…lenovo_un_keep_app_223fc)");
        CollectionsKt.addAll(hashSet2, stringArray3);
        return hashSet;
    }

    @Override
    public HashSet<String> getAppWhiteList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppWhiteList());
        HashSet<String> hashSet2 = hashSet;
        String[] stringArray = getContext().getResources().getStringArray(R.array.lenovo_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…(R.array.lenovo_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray);
        String[] stringArray2 = getContext().getResources().getStringArray(R.array.lenovo_keep_app_x6c6f);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…ay.lenovo_keep_app_x6c6f)");
        CollectionsKt.addAll(hashSet2, stringArray2);
        String[] stringArray3 = getContext().getResources().getStringArray(R.array.lenovo_keep_app_223fc);
        Intrinsics.checkNotNullExpressionValue(stringArray3, "context.resources.getStr…ay.lenovo_keep_app_223fc)");
        CollectionsKt.addAll(hashSet2, stringArray3);
        return hashSet;
    }

    @Override
    public List<ComponentName> getForbiddenComponentNames() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ComponentName("com.android.settings", "com.android.settings.applications.InstalledAppDetails"));
        return arrayList;
    }

    @Override
    public boolean isDevicePolicyServiceSupport() {
        return !Intrinsics.areEqual(Build.MODEL, "Lenovo TB-8604F");
    }

    @Override
    public boolean isSDKSupport() {
        return true;
    }
}

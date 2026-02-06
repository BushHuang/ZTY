package com.xuehai.mdm.config;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u0018\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016¨\u0006\u0010"}, d2 = {"Lcom/xuehai/mdm/config/SamsungConfiguration;", "Lcom/xuehai/mdm/config/DeviceConfiguration;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAppBlackList", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getAppWhiteList", "getForbiddenComponentNames", "", "Landroid/content/ComponentName;", "isDevicePolicyServiceSupport", "", "isSDKSupport", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SamsungConfiguration extends DeviceConfiguration {
    public SamsungConfiguration(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i("DeviceConfiguration", "加载Samsung旧版本knox配置");
    }

    @Override
    public HashSet<String> getAppBlackList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppBlackList());
        String[] stringArray = getContext().getResources().getStringArray(R.array.samsung_un_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.samsung_un_keep_app)");
        CollectionsKt.addAll(hashSet, stringArray);
        return hashSet;
    }

    @Override
    public HashSet<String> getAppWhiteList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppWhiteList());
        String[] stringArray = getContext().getResources().getStringArray(R.array.samsung_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…R.array.samsung_keep_app)");
        CollectionsKt.addAll(hashSet, stringArray);
        return hashSet;
    }

    @Override
    public List<ComponentName> getForbiddenComponentNames() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ComponentName("com.android.phone", "com.android.phone.callsettings.CallSettingsActivity"));
        arrayList.add(new ComponentName("com.android.phone", "com.android.phone.CallFeaturesSetting"));
        return arrayList;
    }

    @Override
    public boolean isDevicePolicyServiceSupport() {
        return DeviceConfiguration.isAppInstalled$default(this, getContext(), "com.android.settings", 0, 4, null);
    }

    @Override
    public boolean isSDKSupport() {
        return DeviceConfiguration.isAppInstalled$default(this, getContext(), "com.android.settings", 0, 4, null);
    }
}

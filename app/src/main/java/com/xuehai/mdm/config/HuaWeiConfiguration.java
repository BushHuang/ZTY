package com.xuehai.mdm.config;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u0018\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0007H\u0016¨\u0006\u000e"}, d2 = {"Lcom/xuehai/mdm/config/HuaWeiConfiguration;", "Lcom/xuehai/mdm/config/DeviceConfiguration;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAppBlackList", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getAppWhiteList", "isSDKSupport", "", "isVersionNameValid", "versionName", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HuaWeiConfiguration extends DeviceConfiguration {
    public HuaWeiConfiguration(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i("DeviceConfiguration", "加载华为配置");
    }

    @Override
    public HashSet<String> getAppBlackList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppBlackList());
        String[] stringArray = getContext().getResources().getStringArray(R.array.huawei_un_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…array.huawei_un_keep_app)");
        CollectionsKt.addAll(hashSet, stringArray);
        return hashSet;
    }

    @Override
    public HashSet<String> getAppWhiteList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppWhiteList());
        String[] stringArray = getContext().getResources().getStringArray(R.array.huawei_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…(R.array.huawei_keep_app)");
        CollectionsKt.addAll(hashSet, stringArray);
        return hashSet;
    }

    @Override
    public boolean isSDKSupport() {
        return true;
    }

    @Override
    public boolean isVersionNameValid(String versionName) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        return StringsKt.contains$default((CharSequence) versionName, (CharSequence) "hw", false, 2, (Object) null);
    }
}

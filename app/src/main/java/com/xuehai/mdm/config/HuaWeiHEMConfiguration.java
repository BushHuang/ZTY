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

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u0018\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016¨\u0006\u0011"}, d2 = {"Lcom/xuehai/mdm/config/HuaWeiHEMConfiguration;", "Lcom/xuehai/mdm/config/DeviceConfiguration;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAppBlackList", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getAppWhiteList", "getForbiddenComponentNames", "", "Landroid/content/ComponentName;", "isSDKSupport", "", "isVersionNameValid", "versionName", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HuaWeiHEMConfiguration extends DeviceConfiguration {
    public HuaWeiHEMConfiguration(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i("DeviceConfiguration", "加载华为配置");
    }

    @Override
    public HashSet<String> getAppBlackList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppBlackList());
        HashSet<String> hashSet2 = hashSet;
        String[] stringArray = getContext().getResources().getStringArray(R.array.huawei_un_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…array.huawei_un_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray);
        String[] stringArray2 = getContext().getResources().getStringArray(R.array.huawei_hem_un_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…y.huawei_hem_un_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray2);
        return hashSet;
    }

    @Override
    public HashSet<String> getAppWhiteList() throws Resources.NotFoundException {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(super.getAppWhiteList());
        HashSet<String> hashSet2 = hashSet;
        String[] stringArray = getContext().getResources().getStringArray(R.array.huawei_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…(R.array.huawei_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray);
        String[] stringArray2 = getContext().getResources().getStringArray(R.array.huawei_hem_keep_app);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…rray.huawei_hem_keep_app)");
        CollectionsKt.addAll(hashSet2, stringArray2);
        return hashSet;
    }

    @Override
    public List<ComponentName> getForbiddenComponentNames() {
        List<ComponentName> forbiddenComponentNames = super.getForbiddenComponentNames();
        ArrayList arrayList = new ArrayList();
        List<ComponentName> list = forbiddenComponentNames;
        if (!list.isEmpty()) {
            arrayList.addAll(list);
        }
        arrayList.add(new ComponentName("com.huawei.ohos.inputmethod", "com.huawei.keyboard.store.ui.storehome.StoreHomeActivity"));
        arrayList.add(new ComponentName("com.huawei.ohos.inputmethod", "com.huawei.ohos.inputmethod.ui.view.SettingActivity"));
        arrayList.add(new ComponentName("com.huawei.ohos.inputmethod", "com.appstore.view.activity.PrimaryActivity"));
        arrayList.add(new ComponentName("com.huawei.ohos.inputmethod", "com.appstore.view.activity.InputSettingsActivity"));
        arrayList.add(new ComponentName("com.huawei.photos", "com.huawei.gallery.recycle.app.RecycleAlbumActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.mainscreen.MainScreenActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.mainscreen.SettingActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appfeature.spacecleaner.SpaceCleanActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.power.ui.HwPowerManagerActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.securitycenter.permission.ui.activity.PermissionSettingActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.securitycenter.antivirus.ui.AntiVirusActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.securitycenter.applock.view.AppLockEntranceActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
        arrayList.add(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationAllChannelSettingsActivity"));
        return arrayList;
    }

    @Override
    public boolean isSDKSupport() {
        return true;
    }

    @Override
    public boolean isVersionNameValid(String versionName) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        return true;
    }
}

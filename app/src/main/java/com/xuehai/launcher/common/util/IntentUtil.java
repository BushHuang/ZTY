package com.xuehai.launcher.common.util;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.config.ClientConfig;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005J\u0006\u0010\u0010\u001a\u00020\fJ \u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u0014J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u0014¨\u0006\u0016"}, d2 = {"Lcom/xuehai/launcher/common/util/IntentUtil;", "", "()V", "getDefaultHome", "Lkotlin/Pair;", "", "getHomeCandidates", "", "getLaunchIntentForPackage", "Landroid/content/Intent;", "packageName", "isIntentEffective", "", "intent", "isLauncherDefault", "className", "isMyLauncherDefault", "query", "Landroid/content/pm/ResolveInfo;", "flag", "", "resolveActivity", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IntentUtil {
    public static final IntentUtil INSTANCE = new IntentUtil();

    private IntentUtil() {
    }

    private final Pair<String, String> getDefaultHome() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = BaseApplication.INSTANCE.getInstance().getPackageManager().resolveActivity(intent, 0);
        if ((resolveInfoResolveActivity != null ? resolveInfoResolveActivity.activityInfo : null) == null) {
            return new Pair<>("", "");
        }
        ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
        String str = activityInfo != null ? activityInfo.packageName : null;
        if (str == null) {
            str = "";
        }
        return Intrinsics.areEqual(str, "android") ? new Pair<>("", "") : new Pair<>(str, resolveInfoResolveActivity.activityInfo.name);
    }

    public static List query$default(IntentUtil intentUtil, Intent intent, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return intentUtil.query(intent, i);
    }

    public static ResolveInfo resolveActivity$default(IntentUtil intentUtil, Intent intent, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 65536;
        }
        return intentUtil.resolveActivity(intent, i);
    }

    public final List<Pair<String, String>> getHomeCandidates() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = BaseApplication.INSTANCE.getInstance().getPackageManager().queryIntentActivities(intent, 0);
        Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "activities");
        List<ResolveInfo> list = listQueryIntentActivities;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ResolveInfo resolveInfo : list) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            String str = activityInfo != null ? activityInfo.packageName : null;
            String str2 = "";
            if (str == null) {
                str = "";
            } else {
                Intrinsics.checkNotNullExpressionValue(str, "it.activityInfo?.packageName ?: \"\"");
            }
            ActivityInfo activityInfo2 = resolveInfo.activityInfo;
            String str3 = activityInfo2 != null ? activityInfo2.name : null;
            if (str3 != null) {
                Intrinsics.checkNotNullExpressionValue(str3, "it.activityInfo?.name ?: \"\"");
                str2 = str3;
            }
            arrayList.add(new Pair(str, str2));
        }
        return arrayList;
    }

    public final Intent getLaunchIntentForPackage(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return BaseApplication.INSTANCE.getInstance().getPackageManager().getLaunchIntentForPackage(packageName);
    }

    public final boolean isIntentEffective(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (query$default(this, intent, 0, 2, null) != null) {
            return !r4.isEmpty();
        }
        return false;
    }

    public final boolean isLauncherDefault(String packageName, String className) {
        ActivityInfo activityInfo;
        ActivityInfo activityInfo2;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        if (packageName.length() == 0) {
            if (className.length() == 0) {
                return false;
            }
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = BaseApplication.INSTANCE.getInstance().getPackageManager().resolveActivity(intent, 65536);
        String str = null;
        String str2 = (resolveInfoResolveActivity == null || (activityInfo2 = resolveInfoResolveActivity.activityInfo) == null) ? null : activityInfo2.packageName;
        if (str2 == null) {
            str2 = "";
        }
        if (resolveInfoResolveActivity != null && (activityInfo = resolveInfoResolveActivity.activityInfo) != null) {
            str = activityInfo.name;
        }
        return Intrinsics.areEqual(str2, packageName) && Intrinsics.areEqual(str != null ? str : "", className);
    }

    public final boolean isMyLauncherDefault() {
        return isLauncherDefault(ClientConfig.INSTANCE.getPackageName(), ClientConfig.INSTANCE.getLauncherActivity());
    }

    public final List<ResolveInfo> query(Intent intent, int flag) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return BaseApplication.INSTANCE.getInstance().getPackageManager().queryIntentActivities(intent, flag);
    }

    public final ResolveInfo resolveActivity(Intent intent, int flag) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return BaseApplication.INSTANCE.getInstance().getPackageManager().resolveActivity(intent, flag);
    }
}

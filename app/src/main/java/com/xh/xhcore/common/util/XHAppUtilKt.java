package com.xh.xhcore.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xh/xhcore/common/util/XHAppUtilKt;", "", "()V", "TAG", "", "getApplicationInfo", "Landroid/content/pm/ApplicationInfo;", "context", "Landroid/content/Context;", "packageName", "flag", "", "isInstalled", "", "queryMainIntentActivities", "", "Landroid/content/pm/ResolveInfo;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHAppUtilKt {
    public static final XHAppUtilKt INSTANCE = new XHAppUtilKt();
    private static final String TAG = "XHAppUtilKt";

    private XHAppUtilKt() {
    }

    @JvmStatic
    public static final ApplicationInfo getApplicationInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getApplicationInfo$default(context, null, 0, 6, null);
    }

    @JvmStatic
    public static final ApplicationInfo getApplicationInfo(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getApplicationInfo$default(context, str, 0, 4, null);
    }

    @JvmStatic
    public static final ApplicationInfo getApplicationInfo(Context context, String packageName, int flag) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = packageName;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            return context.getPackageManager().getApplicationInfo(packageName, flag);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtils.Companion.e$default(LogUtils.INSTANCE, "XHAppUtilKt", Intrinsics.stringPlus("没有找到应用信息 : ", packageName), null, 4, null);
            return (ApplicationInfo) null;
        }
    }

    public static ApplicationInfo getApplicationInfo$default(Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = context.getPackageName();
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return getApplicationInfo(context, str, i);
    }

    @JvmStatic
    public static final boolean isInstalled(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return getApplicationInfo$default(context, packageName, 0, 4, null) != null;
    }

    @JvmStatic
    public static final List<ResolveInfo> queryMainIntentActivities(Context context, String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageName);
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "context.packageManager.q…CH_DEFAULT_ONLY\n        )");
        return listQueryIntentActivities;
    }
}

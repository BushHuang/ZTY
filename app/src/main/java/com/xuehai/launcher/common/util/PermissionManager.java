package com.xuehai.launcher.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.system.mdm.proxy.ApplicationPolicyProxy;
import com.xuehai.system.mdm.proxy.PolicyManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005J\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016J\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019¢\u0006\u0002\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0005J\u0019\u0010 \u001a\u00020\u001e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019¢\u0006\u0002\u0010!R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR-\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\nj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\""}, d2 = {"Lcom/xuehai/launcher/common/util/PermissionManager;", "", "()V", "lastUpdateTimeMap", "", "", "", "getLastUpdateTimeMap", "()Ljava/util/Map;", "permissionMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getPermissionMap", "()Ljava/util/HashMap;", "applyRuntimePermissions", "", "context", "Landroid/content/Context;", "packageName", "getDeniedPermission", "", "neededPermissions", "", "getPermissionNameSet", "permissions", "", "([Ljava/lang/String;)Ljava/util/Collection;", "getPermissionNames", "([Ljava/lang/String;)Ljava/lang/String;", "hasPermission", "", "permission", "hasPermissions", "([Ljava/lang/String;)Z", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PermissionManager {
    public static final PermissionManager INSTANCE = new PermissionManager();
    private static final Map<String, Long> lastUpdateTimeMap;
    private static final HashMap<String, String> permissionMap;

    static {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> map2 = map;
        map2.put("android.permission.WRITE_EXTERNAL_STORAGE", "访问存储空间");
        map2.put("android.permission.READ_PHONE_STATE", "读取手机状态");
        map2.put("android.permission.ACCESS_FINE_LOCATION", "获取位置");
        permissionMap = map;
        lastUpdateTimeMap = new LinkedHashMap();
    }

    private PermissionManager() {
    }

    public final void applyRuntimePermissions(Context context, String packageName) {
        List<String> listEmptyList;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 4096);
            MyLog.d("Application[MDM]", "lastUpdateTimeMap[packageName] = " + lastUpdateTimeMap.get(packageName));
            MyLog.d("Application[MDM]", "packageInfo.lastUpdateTime = " + packageInfo.lastUpdateTime);
            Long l = lastUpdateTimeMap.get(packageName);
            long j = packageInfo.lastUpdateTime;
            if (l != null && l.longValue() == j) {
                MyLog.d("Application[MDM]", "应用[" + packageName + "]没有更新，不需要赋权");
                return;
            }
            lastUpdateTimeMap.put(packageName, Long.valueOf(packageInfo.lastUpdateTime));
            MyLog.d("Application[MDM]", "应用[" + packageName + "]可能已更新，准备自动赋权");
            ApplicationPolicyProxy applicationPolicyProxy = PolicyManager.getApplicationPolicyProxy();
            String[] strArr = packageInfo.requestedPermissions;
            if (strArr == null || (listEmptyList = ArraysKt.toList(strArr)) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            applicationPolicyProxy.applyRuntimePermissions(packageName, listEmptyList);
            MyLog.d("Application[MDM]", "应用[" + packageName + "]可能已更新，已完成自动赋权");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final Set<String> getDeniedPermission(Collection<String> neededPermissions) {
        Intrinsics.checkNotNullParameter(neededPermissions, "neededPermissions");
        if (neededPermissions.isEmpty()) {
            return SetsKt.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (String str : neededPermissions) {
            if (!INSTANCE.hasPermission(str)) {
                hashSet.add(str);
            }
        }
        if (!hashSet.isEmpty()) {
            MyLog.INSTANCE.v("[MDM]", "deniedPermissions: " + hashSet);
        }
        return hashSet;
    }

    public final Map<String, Long> getLastUpdateTimeMap() {
        return lastUpdateTimeMap;
    }

    public final HashMap<String, String> getPermissionMap() {
        return permissionMap;
    }

    public final Collection<String> getPermissionNameSet(String[] permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        HashSet hashSet = new HashSet();
        for (String str : permissions) {
            String str2 = permissionMap.get(str);
            String str3 = str2;
            if (!(str3 == null || str3.length() == 0)) {
                hashSet.add(str2);
            }
        }
        return hashSet;
    }

    public final String getPermissionNames(String[] permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object obj : getPermissionNameSet(permissions)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if (i > 0) {
                sb.append(',' + str);
            } else {
                sb.append(str);
            }
            i = i2;
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "rationaleBuilder.toString()");
        return string;
    }

    public final boolean hasPermission(String permission) {
        Object objM228constructorimpl;
        Intrinsics.checkNotNullParameter(permission, "permission");
        boolean z = true;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        BaseApplication baseApplicationCreateDeviceProtectedStorageContext = Build.VERSION.SDK_INT >= 24 ? BaseApplication.INSTANCE.getInstance().createDeviceProtectedStorageContext() : BaseApplication.INSTANCE.getInstance();
        try {
            Result.Companion companion = Result.INSTANCE;
            if (ContextCompat.checkSelfPermission(baseApplicationCreateDeviceProtectedStorageContext, permission) != 0) {
                z = false;
            }
            objM228constructorimpl = Result.m228constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM228constructorimpl = Result.m228constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m231exceptionOrNullimpl(objM228constructorimpl) != null) {
            objM228constructorimpl = false;
        }
        return ((Boolean) objM228constructorimpl).booleanValue();
    }

    public final boolean hasPermissions(String[] permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (!(permissions.length == 0) && Build.VERSION.SDK_INT >= 23) {
            for (String str : permissions) {
                if (!INSTANCE.hasPermission(str)) {
                    return false;
                }
            }
        }
        return true;
    }
}

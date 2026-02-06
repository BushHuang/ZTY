package com.xh.xhcore.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import com.xh.view.XHAlertDialog;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\b\"\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010\tJ+\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\"\u00020\u0006H\u0007¢\u0006\u0002\u0010\u000eJE\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019J)\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\"\u00020\u0006H\u0007¢\u0006\u0002\u0010\u001bJ1\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\"\u00020\u0006H\u0007¢\u0006\u0002\u0010\u001cJ\u001a\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u000bH\u0007J\"\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u000bH\u0007J\"\u0010 \u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/xh/xhcore/common/util/XhPermissionUtil;", "", "()V", "REQUEST_PERMISSION_GROUP_STORAGE_CODE", "", "getDescribe", "", "permissions", "", "([Ljava/lang/String;)Ljava/lang/String;", "hasPermissions", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;[Ljava/lang/String;)Z", "onRequestPermissionsResult", "", "activity", "Landroid/app/Activity;", "requestCode", "grantResults", "", "isStudentClient", "callback", "Lcom/xh/xhcore/common/util/OnRequestPermissionsCallback;", "(Landroid/app/Activity;I[Ljava/lang/String;[IZLcom/xh/xhcore/common/util/OnRequestPermissionsCallback;)V", "requestPermissions", "(Landroid/app/Activity;[Ljava/lang/String;)V", "(Landroid/app/Activity;I[Ljava/lang/String;)V", "showDialogByOther", "canClose", "msg", "showDialogByStudent", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XhPermissionUtil {
    public static final XhPermissionUtil INSTANCE = new XhPermissionUtil();
    public static final int REQUEST_PERMISSION_GROUP_STORAGE_CODE = 1001;

    private XhPermissionUtil() {
    }

    private final String getDescribe(String... permissions) {
        String str;
        if (!(!(permissions.length == 0)) || (str = permissions[0]) == null) {
            return null;
        }
        switch (str.hashCode()) {
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    return "存储";
                }
                return null;
            case -5573545:
                if (str.equals("android.permission.READ_PHONE_STATE")) {
                    return "电话";
                }
                return null;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    return "相机";
                }
                return null;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    return "存储";
                }
                return null;
            default:
                return null;
        }
    }

    @JvmStatic
    public static final boolean hasPermissions(Context context, String... permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (Build.VERSION.SDK_INT < 23) {
            LogUtils.Companion.d$default(LogUtils.INSTANCE, INSTANCE.getClass().getName(), "API 小于 23 的情况下，不用请求权限", null, 4, null);
            return true;
        }
        if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context".toString());
        }
        int length = permissions.length;
        int i = 0;
        while (i < length) {
            String str = permissions[i];
            i++;
            if (ActivityCompat.checkSelfPermission(context, str) == -1) {
                LogUtils.Companion companion = LogUtils.INSTANCE;
                String describe = INSTANCE.getDescribe(str);
                if (describe != null) {
                    str = describe;
                }
                companion.w(Intrinsics.stringPlus("缺少权限 ", str));
                return false;
            }
        }
        return true;
    }

    @JvmStatic
    public static final void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults, boolean isStudentClient, OnRequestPermissionsCallback callback) {
        String str;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (callback.isCurrRequest(requestCode)) {
            int length = permissions.length;
            boolean z = true;
            if (length == grantResults.length) {
                int i = 0;
                boolean z2 = true;
                while (i < length) {
                    int i2 = i + 1;
                    String str2 = permissions[i];
                    if (grantResults[i] == -1) {
                        Intrinsics.checkNotNull(str2);
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, str2)) {
                            if (callback.onNoTip()) {
                                if (isStudentClient) {
                                    showDialogByStudent$default(activity, callback, false, 4, null);
                                    return;
                                }
                                String describe = INSTANCE.getDescribe(str2);
                                String str3 = describe;
                                if (str3 != null && !StringsKt.isBlank(str3)) {
                                    z = false;
                                }
                                if (z) {
                                    str = "使用该功能需要权限，请前往【设置】>【应用程序管理】>【应用详情】开启。";
                                } else {
                                    str = "使用该功能需要：【" + ((Object) describe) + "】权限，请前往【设置】>【应用程序管理 >【应用详情】开启。";
                                }
                                showDialogByOther$default(activity, str, false, 4, null);
                                return;
                            }
                            return;
                        }
                        i = i2;
                        z2 = false;
                    } else {
                        i = i2;
                    }
                }
                z = z2;
            }
            if (z) {
                callback.onGranted();
            } else {
                callback.onRefused();
            }
        }
    }

    @JvmStatic
    public static final void requestPermissions(Activity activity, int requestCode, String... permissions) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    @JvmStatic
    public static final void requestPermissions(Activity activity, String... permissions) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        requestPermissions(activity, 1001, (String[]) Arrays.copyOf(permissions, permissions.length));
    }

    @JvmStatic
    public static final void showDialogByOther(final Activity activity, String msg, boolean canClose) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(msg, "msg");
        new XHAlertDialog(activity).showNoTitleDialog(msg, "取消", "确定", new XHAlertDialog.DialogActionListener() {
            @Override
            public void onNegativeAction(Dialog p0) {
                if (p0 == null) {
                    return;
                }
                p0.dismiss();
            }

            @Override
            public void onPositiveAction(Dialog p0) {
                if (p0 != null) {
                    p0.dismiss();
                }
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse(Intrinsics.stringPlus("package:", XHAppUtil.getPackageName())));
                activity.startActivity(intent);
            }

            @Override
            public void onReportAction(Dialog p0) {
            }
        }, canClose);
    }

    @JvmStatic
    public static final void showDialogByOther(Activity activity, boolean canClose) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        showDialogByOther(activity, "使用该功能需要权限，请前往【设置】>【应用程序管理】>【应用详情】开启。", canClose);
    }

    public static void showDialogByOther$default(Activity activity, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        showDialogByOther(activity, str, z);
    }

    public static void showDialogByOther$default(Activity activity, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        showDialogByOther(activity, z);
    }

    @JvmStatic
    public static final void showDialogByStudent(Activity activity, final OnRequestPermissionsCallback callback, boolean canClose) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(callback, "callback");
        new XHAlertDialog(activity).showNoTitleDialog("无该功能使用权限，需清除应用数据后，在使用该功能的权限弹窗中选择“允许”。\r\n\r\n特别提示：如应用有本地数据未上传，如作业、练习题等，请先保存应用数据。", "取消", "清除数据", new XHAlertDialog.DialogActionListener() {
            @Override
            public void onNegativeAction(Dialog p0) {
                if (p0 != null) {
                    p0.dismiss();
                }
                XHAppUtil.killAllProcess();
            }

            @Override
            public void onPositiveAction(Dialog p0) {
                if (p0 != null) {
                    p0.dismiss();
                }
                callback.onClearCacheData();
            }

            @Override
            public void onReportAction(Dialog p0) {
            }
        }, canClose);
    }

    public static void showDialogByStudent$default(Activity activity, OnRequestPermissionsCallback onRequestPermissionsCallback, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        showDialogByStudent(activity, onRequestPermissionsCallback, z);
    }
}

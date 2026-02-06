package com.xh.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.appcompat.app.AppCompatActivity;
import com.xh.view.XHAlertDialog;
import com.xh.xhcore.common.http.XHErrorCodeUtil;
import com.xh.xhcore.common.http.strategy.xh.XHBaseRequestProxy;
import com.xh.xhcore.common.statistic.user.report.dialog.UserReportDialogFragment;
import java.util.ArrayList;
import java.util.List;

public class TipDialogHelper {
    public static final String CHECK_NETWORK = "检查网络";
    public static final String OPEN_NETWORK = "打开网络";
    private static SparseArray<XHAlertDialog.DialogProperty> errorCodeMap;
    private static List<Integer> sHandleErrorCodes;
    private static List<Integer> sNetworkErrorCodes;
    private static List<Integer> sUploadErrorCodes;

    public interface OnDismissListener {
        void onNegativeDismiss();

        void onPositiveDismiss(boolean z);
    }

    static {
        ArrayList arrayList = new ArrayList();
        sUploadErrorCodes = arrayList;
        arrayList.add(107002001);
        sUploadErrorCodes.add(107002002);
        sUploadErrorCodes.add(107001012);
        ArrayList arrayList2 = new ArrayList();
        sNetworkErrorCodes = arrayList2;
        arrayList2.add(107000007);
        sNetworkErrorCodes.add(107000006);
        sNetworkErrorCodes.add(107000056);
        ArrayList arrayList3 = new ArrayList();
        sHandleErrorCodes = arrayList3;
        arrayList3.add(107000007);
        sHandleErrorCodes.add(107000006);
        sHandleErrorCodes.add(107000028);
        sHandleErrorCodes.add(107000056);
        sHandleErrorCodes.add(107001012);
        sHandleErrorCodes.add(107001015);
        sHandleErrorCodes.add(107004000);
        sHandleErrorCodes.add(107004001);
        sHandleErrorCodes.add(107002001);
        sHandleErrorCodes.add(107002002);
        sHandleErrorCodes.add(107003000);
        sHandleErrorCodes.add(107000403);
        sHandleErrorCodes.add(107000404);
        sHandleErrorCodes.add(107000405);
        sHandleErrorCodes.add(107000406);
        sHandleErrorCodes.add(107000408);
        sHandleErrorCodes.add(107000415);
        sHandleErrorCodes.add(107000500);
        sHandleErrorCodes.add(107000502);
        sHandleErrorCodes.add(107000503);
        sHandleErrorCodes.add(107000504);
        sHandleErrorCodes.add(-108000001);
        errorCodeMap = new SparseArray<>();
        buildProperty(new int[]{107000006, 107000028, 107000056, 107001012}, "网络连接异常，请使用网络检查工具检查网络状态", "网络异常", "取消", "检查网络", false);
        buildProperty(new int[]{107000007, 107001015}, "未找到网络，请检查网络连接是否正常，或更换网络", "网络不通", "取消", "检查网络", false);
        buildProperty(107002001, "文件下载不完整，请重新下载。若重试无效，请联系学海老师", "文件下载异常", "取消", "重新下载", true);
        buildProperty(107002002, "文件下载失败，请重新下载。若重试无效，请联系学海老师", "下载失败", "取消", "重新下载", true);
        buildProperty(107003000, "请联系学海老师", "异常错误", "", "我知道了", true);
        buildProperty(new int[]{107000403, 107000404, 107000405, 107000406, 107000408, 107000415}, "请稍后重试。若重试无效，请联系学海老师", "请求异常", "取消", "重试", true);
        buildProperty(new int[]{107000500, 107000502, 107000503, 107000504}, "请稍后重试。若重试无效，请联系学海老师", "服务器开小差了", "取消", "重试", true);
        buildProperty(-108000001, "网络连接未开启，请开启网络", "找不到网络", "取消", "打开网络", false);
        buildProperty(107001016, "请求url为空", "网络异常", "取消", "检查网络", true);
        buildProperty(107001200, "网络访问超时，请使用网络检查工具检查网络状态", "网络异常", "取消", "检查网络", false);
        buildProperty(107001201, "接口超时，ping服务器失败", "网络异常", "取消", "检查网络", true);
        buildProperty(107001202, "未知的网络错误，请使用网络检查工具检查网络状态", "网络异常", "取消", "检查网络", false);
        buildProperty(107001203, "网络访问超时，ping百度不通，请使用网络检查工具检查网络状态", "网络异常", "取消", "检查网络", false);
        buildProperty(107001204, "数据解析异常", "网络异常", "取消", "检查网络", true);
        buildProperty(107001205, "数据解析后为null", "网络异常", "取消", "检查网络", true);
        buildProperty(107001206, "微服务未获取到ip", "网络异常", "取消", "检查网络", true);
        buildProperty(107002001, "文件下载不完整，请重新下载。若重试无效，请联系学海老师", "网络异常", "取消", "检查网络", true);
        buildProperty(107002002, "文件下载失败，请重新下载。若重试无效，请联系学海老师", "网络异常", "取消", "检查网络", true);
        buildProperty(107003001, "阿里云上传失败，服务端错误", "网络异常", "取消", "检查网络", true);
        buildProperty(107003002, "阿里云路径信息为空", "网络异常", "取消", "检查网络", true);
        buildProperty(107003100, "上传响应解析状态失败", "网络异常", "取消", "检查网络", true);
        buildProperty(107003101, "服务器返回数据格式不正确", "网络异常", "取消", "检查网络", true);
        buildProperty(107003102, "文件路径格式不正确", "网络异常", "取消", "检查网络", true);
        buildProperty(107004000, "接口访问超时，请使用网络检查工具检查网络状态", "网络异常", "取消", "检查网络", true);
        buildProperty(107004001, "接口访问失败，请使用网络检查工具检查网络状态", "网络异常", "取消", "检查网络", true);
    }

    private TipDialogHelper() {
    }

    private static XHAlertDialog.DialogProperty buildProperty(int i, String str, String str2, String str3, String str4, boolean z) {
        XHAlertDialog.DialogProperty alertDialogProperty = getAlertDialogProperty(i, str, str2, str3, str4, z);
        errorCodeMap.put(i, alertDialogProperty);
        return alertDialogProperty;
    }

    private static void buildProperty(int[] iArr, String str, String str2, String str3, String str4, boolean z) {
        for (int i : iArr) {
            errorCodeMap.put(i, getAlertDialogProperty(i, str, str2, str3, str4, z));
        }
    }

    private static XHAlertDialog.DialogProperty getAlertDialogProperty(int i, String str, String str2, String str3, String str4, boolean z) {
        XHAlertDialog.DialogProperty dialogProperty = new XHAlertDialog.DialogProperty();
        dialogProperty.setErrorMsg("错误信息：" + i);
        dialogProperty.setTitle(str2);
        dialogProperty.setContent(str);
        dialogProperty.setNegativeLab(str3);
        dialogProperty.setPositiveLab(str4);
        dialogProperty.setReportVisible(z);
        return dialogProperty;
    }

    public static XHAlertDialog show(Activity activity, XHAlertDialog.DialogProperty dialogProperty, XHAlertDialog.DialogActionListener dialogActionListener) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        XHAlertDialog xHAlertDialog = new XHAlertDialog(activity);
        xHAlertDialog.show(dialogProperty, dialogActionListener);
        return xHAlertDialog;
    }

    public static XHAlertDialog showNoNegativeDialog(Activity activity, String str, String str2, String str3, XHAlertDialog.DialogActionListener dialogActionListener) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        XHAlertDialog xHAlertDialog = new XHAlertDialog(activity);
        xHAlertDialog.showNoNegativeDialog(str, str2, str3, dialogActionListener);
        return xHAlertDialog;
    }

    public static XHAlertDialog showNoTitleDialog(Activity activity, String str, String str2, String str3, XHAlertDialog.DialogActionListener dialogActionListener) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        XHAlertDialog xHAlertDialog = new XHAlertDialog(activity);
        xHAlertDialog.showNoTitleDialog(str, str2, str3, dialogActionListener);
        return xHAlertDialog;
    }

    public static boolean showTipDialog(Activity activity, int i, String str, String str2) {
        return showTipDialog(activity, i, str, str2, null);
    }

    public static boolean showTipDialog(Activity activity, int i, String str, String str2, OnDismissListener onDismissListener) {
        return showTipDialog(activity, i, str, str2, onDismissListener, null);
    }

    public static boolean showTipDialog(final Activity activity, final int i, String str, @Deprecated String str2, final OnDismissListener onDismissListener, final XHBaseRequestProxy xHBaseRequestProxy) {
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        XHAlertDialog.DialogProperty dialogProperty = errorCodeMap.get(i);
        if (dialogProperty == null) {
            return TipToast.showToast(activity, i);
        }
        final XHAlertDialog.DialogProperty dialogPropertyCopy = dialogProperty.copy();
        XHAlertDialog xHAlertDialog = new XHAlertDialog(activity);
        if (!TextUtils.isEmpty(str)) {
            dialogPropertyCopy.setContent(str + "，" + dialogPropertyCopy.getContent());
        }
        xHAlertDialog.show(dialogPropertyCopy, new XHAlertDialog.DialogActionListener() {
            @Override
            public void onNegativeAction(Dialog dialog) {
                dialog.dismiss();
                OnDismissListener onDismissListener2 = onDismissListener;
                if (onDismissListener2 != null) {
                    onDismissListener2.onNegativeDismiss();
                }
            }

            @Override
            public void onPositiveAction(Dialog dialog) {
                boolean z = true;
                if ("检查网络".equals(dialogPropertyCopy.positiveLab)) {
                    TipDialogHelper.startAssistApp(activity);
                } else if ("打开网络".equals(dialogPropertyCopy.positiveLab)) {
                    TipDialogHelper.startWifiManagerApp(activity);
                } else {
                    z = false;
                }
                dialog.dismiss();
                OnDismissListener onDismissListener2 = onDismissListener;
                if (onDismissListener2 != null) {
                    onDismissListener2.onPositiveDismiss(z);
                }
            }

            @Override
            public void onReportAction(Dialog dialog) {
                dialog.dismiss();
                Activity activity2 = activity;
                if (!(activity2 instanceof AppCompatActivity) || xHBaseRequestProxy == null) {
                    TipToast.showToast(activity, "上报成功", 0);
                } else {
                    int i2 = i;
                    UserReportDialogFragment.newInstanceNetworkError(i2, XHErrorCodeUtil.getErrorMsgInfo(i2), xHBaseRequestProxy.getClientTraceId(), xHBaseRequestProxy.getNetworkErrorDetail()).show(((AppCompatActivity) activity2).getSupportFragmentManager());
                }
            }
        });
        return true;
    }

    private static void startAssistApp(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.xh.assist");
        if (launchIntentForPackage == null) {
            TipToast.showToast(context, "没有安装网络检查工具", 0);
            return;
        }
        launchIntentForPackage.putExtras(new Bundle());
        launchIntentForPackage.addFlags(268435456);
        context.startActivity(launchIntentForPackage);
    }

    private static void startWifiManagerApp(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.zhitongyun.wifimanager");
        if (launchIntentForPackage == null) {
            TipToast.showToast(context, "没有安装Wifi管理工具", 0);
            return;
        }
        launchIntentForPackage.putExtras(new Bundle());
        launchIntentForPackage.addFlags(268435456);
        context.startActivity(launchIntentForPackage);
    }
}

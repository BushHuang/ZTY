package com.xh.xhcore.common.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import com.xh.view.UpdateDialog;
import com.xuehai.provider.core.db.CPVDApp;
import com.xuehai.provider.core.db.CPVDAppData;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import kotlin.Deprecated;

public class XHTipView {
    public static Dialog getTipUpdateDialog(Context context, DialogInterface.OnCancelListener onCancelListener) {
        CPVDUser user = CPVDUserData.getUser(context);
        if (user != null && user.getUserId() > 0) {
            String packageName = context.getPackageName();
            try {
                int i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
                CPVDApp appInfo = CPVDAppData.getAppInfo(context, user.getUserId(), packageName);
                if (appInfo != null && appInfo.getVersionCode() != i && appInfo.isForced()) {
                    UpdateDialog updateDialog = new UpdateDialog(context, onCancelListener);
                    updateDialog.setAppVersion(appInfo.getVersionName());
                    return updateDialog;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Deprecated(message = "使用getTipUpdateDialog，确保在对应Activity销毁前销毁dialog")
    public static boolean tipUpdateDialog(Context context, DialogInterface.OnCancelListener onCancelListener) {
        Dialog tipUpdateDialog = getTipUpdateDialog(context, onCancelListener);
        if (tipUpdateDialog == null) {
            return false;
        }
        tipUpdateDialog.show();
        return true;
    }
}

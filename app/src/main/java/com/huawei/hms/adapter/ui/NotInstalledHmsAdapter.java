package com.huawei.hms.adapter.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.NotInstalledHmsDialogHelper;

public class NotInstalledHmsAdapter implements IBridgeActivityDelegate {
    public static final Object c = new Object();
    public static boolean d;

    public Activity f212a;
    public Dialog b;

    public static class a implements DialogInterface.OnCancelListener {

        public final Activity f213a;

        public a(Activity activity) {
            this.f213a = activity;
        }

        @Override
        public void onCancel(DialogInterface dialogInterface) {
            HMSLog.i("NotInstalledHmsAdapter", "<Dialog onCancel>");
            SystemManager.getInstance().notifyUpdateResult(13);
            this.f213a.finish();
        }
    }

    public static class b implements DialogInterface.OnClickListener {

        public final Activity f214a;

        public b(Activity activity) {
            this.f214a = activity;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            HMSLog.i("NotInstalledHmsAdapter", "<Dialog onClick>");
            SystemManager.getInstance().notifyUpdateResult(30);
            this.f214a.finish();
        }
    }

    public static boolean getShowLock() {
        synchronized (c) {
            HMSLog.i("NotInstalledHmsAdapter", "<canShowDialog> sIsShowingDialog: " + d);
            if (d) {
                return false;
            }
            d = true;
            return true;
        }
    }

    public final void a(Activity activity) {
        Dialog dialog = this.b;
        if (dialog != null && dialog.isShowing()) {
            this.b.setOnCancelListener(null);
            this.b.cancel();
        }
        this.b = NotInstalledHmsDialogHelper.getDialogBuilder(activity).setPositiveButton(NotInstalledHmsDialogHelper.getConfirmResId(activity), new b(activity)).setOnCancelListener(new a(activity)).show();
    }

    @Override
    public int getRequestCode() {
        HMSLog.i("NotInstalledHmsAdapter", "<getRequestCode>");
        return 0;
    }

    @Override
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeActivityCreate>");
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("NotInstalledHmsAdapter", "<onBridgeActivityCreate> activity is null or finishing");
        } else {
            this.f212a = activity;
            a(activity);
        }
    }

    @Override
    public void onBridgeActivityDestroy() {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeActivityDestroy>");
        synchronized (c) {
            d = false;
        }
    }

    @Override
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeActivityResult>");
        return false;
    }

    @Override
    public void onBridgeConfigurationChanged() {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeConfigurationChanged>");
        Activity activity = this.f212a;
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("NotInstalledHmsAdapter", "<onBridgeConfigurationChanged> mActivity is null or finishing");
        } else {
            a(this.f212a);
        }
    }

    @Override
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.i("NotInstalledHmsAdapter", "<onKeyUp>");
    }
}

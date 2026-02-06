package com.xh.view.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class Util {
    public static boolean startApp(Activity activity, String str, String str2, Bundle bundle) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = activity.getPackageManager().getApplicationInfo(str2, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            Toast.makeText(activity, "应用(" + str + ")未安装!", 0).show();
            return false;
        }
        Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(str2);
        if (launchIntentForPackage != null) {
            if (bundle != null) {
                launchIntentForPackage.putExtras(bundle);
            }
            launchIntentForPackage.addFlags(270532608);
            activity.startActivity(launchIntentForPackage);
            return true;
        }
        Toast.makeText(activity, "应用(" + str + ")不可直接打开!", 0).show();
        return false;
    }
}

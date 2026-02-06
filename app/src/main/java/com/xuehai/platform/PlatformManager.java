package com.xuehai.platform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.xuehai.provider.constants.BroadcastAction;
import com.xuehai.provider.core.CPVDProcess;
import com.xuehai.provider.log.ContentProviderLog;
import com.xuehai.provider.utils.CPVDUtil;

public class PlatformManager {
    public static final String EXIT = "com.xh.launcher.exit";
    protected static final String LAUNCHER_STUDENT = "com.xh.zhitongyunstu";
    protected static final String LAUNCHER_TEACHER = "com.xh.zhitongyuntch";
    protected static final String LAUNCHER_TV = "com.xh.zhitongyuntv";
    public static final String LOGOUT_LAUNCHER = "com.xh.logout.launcher";
    protected static final String MDM_STUDENT = "com.xuehai.launcher";
    protected static final String MDM_TEACHER = "com.xuehai.response_launcher_teacher";
    public static final String TAG = "PlatformManager";
    private static volatile Boolean FLAG__LOGOUT_LAUNCHER = false;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Runnable notifyRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e("PlatformManager", "handle run");
            PlatformManager.resetLogoutFlag();
        }
    };

    public static void clearAppData(Context context, String str) {
        Intent intent = new Intent(BroadcastAction.clearAppData(str));
        intent.setPackage(str);
        CPVDProcess.sendBroadcast(context, intent);
    }

    public static void exit(Context context) {
        CPVDProcess.sendBroadcast(context, new Intent("com.xh.launcher.exit"));
    }

    public static void notifyPlatformLogout(Context context, int i, String str) {
        Log.e("PlatformManager", "notifyPlatformLogout:code=" + i + "msg=" + str);
        if (FLAG__LOGOUT_LAUNCHER.booleanValue()) {
            return;
        }
        FLAG__LOGOUT_LAUNCHER = true;
        Intent intent = new Intent("com.xh.logout.launcher");
        intent.putExtra("code", i);
        intent.putExtra("msg", str);
        CPVDProcess.sendBroadcast(context, intent);
        handler.removeCallbacks(notifyRunnable);
        handler.postDelayed(notifyRunnable, 3000L);
    }

    public static boolean openCloudBoxLogin(Context context) {
        try {
            Intent intent = new Intent();
            intent.putExtra("key_PACKAGE_NAME", context.getPackageName());
            intent.setPackage("com.xh.zhitongyuntv");
            intent.setAction("com.xuehai.cloudtvlogin");
            if (CPVDUtil.isActivityExist(context, intent)) {
                context.startActivity(intent);
                return true;
            }
            ContentProviderLog.e("CPVDProcess", "找不到课堂云盒登陆口!!");
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static void reSetSessionSuccess(Context context) {
        CPVDProcess.sendBroadcastCompat(context, new Intent("com.xuehai.cpvd.session.reset.success"));
    }

    public static void resetLogoutFlag() {
        Log.e("PlatformManager", "resetLogoutFlag");
        FLAG__LOGOUT_LAUNCHER = false;
    }

    public static void sessionFailed(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(268435456);
            intent.setClassName("com.xuehai.launcher", "com.xuehai.launcher.library.login.ui.impl.OutLoginActivity");
            intent.putExtra("key_PACKAGE_NAME", context.getPackageName());
            if (CPVDUtil.isActivityExist(context, intent)) {
                context.startActivity(intent);
                return;
            }
            intent.setClassName("com.xuehai.response_launcher_teacher", "com.xuehai.launcher.library.login.ui.impl.OutLoginActivity");
            if (CPVDUtil.isActivityExist(context, intent)) {
                context.startActivity(intent);
            } else {
                ContentProviderLog.e("CPVDProcess", "找不到智通云登陆窗口!!");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean showActivity(Activity activity, Intent intent) {
        try {
            if (!CPVDProcess.isIntentEffective(activity.getPackageManager(), intent)) {
                return false;
            }
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            ContentProviderLog.e("PlatformManager", "跳转页面发生异常 : " + intent.getAction(), e);
            return false;
        }
    }
}

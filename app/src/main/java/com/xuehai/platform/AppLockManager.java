package com.xuehai.platform;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.xuehai.provider.core.db.CPVDAppData;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import com.xuehai.provider.log.BusinessLog;
import com.xuehai.provider.utils.CPVDUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AppLockManager {
    private static final String TAG = "AppLockManager";
    private static final Set<String> WHITE_SET = new HashSet(Arrays.asList("com.xuehai.response_launcher_teacher", "com.xuehai.launcher", "com.xh.zhitongyunstu", "com.xh.zhitongyuntch", "com.xuehai.xuehailauncher", "com.xuehai.xuehailauncher_tea", "com.xuehai.xuehailauncher_tea", "com.zhitongyun.wifimanager", "com.xh.assist"));

    public static boolean isAppNeedUnlock(Application application) {
        String packageName = application.getPackageName();
        if (WHITE_SET.contains(packageName)) {
            return false;
        }
        if (CPVDUtil.isAppInstalled(application, "com.xh.zhitongyunstu")) {
            CPVDUser user = CPVDUserData.getUser(application);
            if (user == null) {
                BusinessLog.i("AppLockManager", "获取用户信息为空");
            } else {
                if (CPVDAppData.getGlobalAppLockedStatus(user.getUserId())) {
                    boolean appLockedStatus = CPVDAppData.getAppLockedStatus(user.getUserId(), packageName);
                    BusinessLog.i("AppLockManager", "应用是否需要解锁：" + appLockedStatus);
                    return appLockedStatus;
                }
                BusinessLog.i("AppLockManager", "全局应用锁状态为false");
            }
        } else {
            BusinessLog.i("AppLockManager", "智通云(学生端)未安装");
        }
        return false;
    }

    public static boolean showAppUnLockView(Activity activity) {
        BusinessLog.i("AppLockManager", "跳转解锁页");
        Intent intent = new Intent();
        intent.putExtra("android.intent.extra.PACKAGE_NAME", activity.getPackageName());
        intent.setPackage("com.xh.zhitongyunstu");
        intent.setAction("com.xuehai.action.unlock");
        return PlatformManager.showActivity(activity, intent);
    }
}

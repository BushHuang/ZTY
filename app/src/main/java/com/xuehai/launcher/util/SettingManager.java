package com.xuehai.launcher.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.system.common.appusage.AppUsageHelper;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/util/SettingManager;", "", "()V", "checkAppUsagePermission", "", "checkAppWriteSettingPermission", "getOpenWriteSettingIntent", "Landroid/content/Intent;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingManager {
    public static final SettingManager INSTANCE = new SettingManager();

    private SettingManager() {
    }

    public final boolean checkAppUsagePermission() {
        if (ClientConfig.INSTANCE.isStudentClient()) {
            return AppUsageHelper.INSTANCE.checkAppUsagePermission(App.INSTANCE.getInstance());
        }
        return true;
    }

    public final boolean checkAppWriteSettingPermission() {
        if (ClientConfig.INSTANCE.isStudentClient() && DeviceModelUtil.INSTANCE.isUseXhSettingsDevice() && Build.VERSION.SDK_INT >= 23) {
            return Settings.System.canWrite(App.INSTANCE.getInstance());
        }
        return true;
    }

    public final Intent getOpenWriteSettingIntent() {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + App.INSTANCE.getInstance().getPackageName()));
        return intent;
    }
}

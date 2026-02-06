package com.xuehai.launcher.common.util;

import android.content.Context;
import android.content.Intent;
import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.ZCommand;
import com.zaze.utils.date.DateUtil;
import java.util.Date;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r¨\u0006\u000e"}, d2 = {"Lcom/xuehai/launcher/common/util/SettingsHelper;", "", "()V", "openDateSettings", "", "context", "Landroid/content/Context;", "openSettings", "intent", "Landroid/content/Intent;", "openWifiSettings", "updateSystemTime", "dateTimeStr", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SettingsHelper {
    public static final SettingsHelper INSTANCE = new SettingsHelper();

    private SettingsHelper() {
    }

    @JvmStatic
    public static final void openWifiSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SettingsHelper settingsHelper = INSTANCE;
        Intent intent = new Intent();
        intent.setAction("android.settings.WIFI_SETTINGS");
        Unit unit = Unit.INSTANCE;
        settingsHelper.openSettings(context, intent);
    }

    public final void openDateSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent();
        intent.setAction("android.settings.DATE_SETTINGS");
        Unit unit = Unit.INSTANCE;
        openSettings(context, intent);
    }

    public final void openSettings(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        ActivityUtil activityUtil = ActivityUtil.INSTANCE;
        intent.addFlags(268435456);
        Unit unit = Unit.INSTANCE;
        ActivityUtil.startActivity$default(activityUtil, context, intent, 0, 4, null);
    }

    public final void updateSystemTime(String dateTimeStr) {
        Date dateStringToDate$default = DateUtil.stringToDate$default(dateTimeStr == null ? "" : dateTimeStr, "yyyy-MM-dd HH:mm:ss", null, 4, null);
        if (dateStringToDate$default == null) {
            MyLog.i("Http[MDM]", "未获取到服务器时间, 不处理");
            return;
        }
        if (Math.abs(System.currentTimeMillis() - dateStringToDate$default.getTime()) <= 240000) {
            MyLog.i("Http[MDM]", "当前时间和服务器时间匹配, 不处理");
            return;
        }
        MyLog.saveAppErrorLog("本地时间和服务器时间不匹配 服务器时间: " + dateTimeStr);
        if (!ZCommand.isRoot()) {
            MyLog.saveAppErrorLog("设备未Root, 不同步时间！");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("date -s ");
        TimeZone timeZone = TimeZone.getDefault();
        Intrinsics.checkNotNullExpressionValue(timeZone, "getDefault()");
        sb.append(DateUtil.dateToString(dateStringToDate$default, "yyyyMMdd.HHmmss", timeZone));
        if (ZCommand.execRootCmdForRes(sb.toString()).isSuccess()) {
            MyLog.i("Http[MDM]", "同步时间成功");
        } else {
            MyLog.i("Http[MDM]", "同步时间失败");
        }
    }
}

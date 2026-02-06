package com.xh.logutils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xh.logutils.issue.APMHandler;
import com.xh.logutils.issue.APPHandler;
import com.xh.logutils.issue.CrashHandler;
import com.xh.logutils.issue.NetworkErrorHandler;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.ErrorLogPath;
import com.xh.xhcore.common.statistic.error.ErrorSnapshotManager;
import com.xh.xhcore.common.util.ActivityListAdapter;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHDeviceUtil;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class CrashOrErrorStatisticsInfo {
    public static HashMap<String, String> extraUploadMessage = new HashMap<>();
    protected ErrorLogPath errorLogPath;
    protected String filePrefix;
    protected JSONObject issueInfoUser;

    private CrashOrErrorStatisticsInfo() {
        this.issueInfoUser = new JSONObject();
        this.errorLogPath = null;
        this.filePrefix = "";
    }

    protected CrashOrErrorStatisticsInfo(ErrorLogPath errorLogPath, JSONObject jSONObject) {
        this.issueInfoUser = new JSONObject();
        this.errorLogPath = null;
        this.filePrefix = "";
        this.filePrefix = errorLogPath.name();
        this.errorLogPath = errorLogPath;
        this.issueInfoUser = jSONObject;
        combineObject();
    }

    public static void appendToExtraUploadMessage(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = extraUploadMessage.get(str);
        if (str3 == null) {
            str3 = "";
            extraUploadMessage.put(str, "");
        }
        extraUploadMessage.put(str, str3 + ";" + str2);
    }

    private void combineObject() {
        try {
            for (Map.Entry<String, Object> entry : getCommonInfoMap().entrySet()) {
                if (!this.issueInfoUser.has(entry.getKey())) {
                    this.issueInfoUser.putOpt(entry.getKey(), wrapToJson(entry.getValue()));
                }
            }
        } catch (Throwable th) {
            LogUtils.e(th);
        }
    }

    public static Map<String, Object> getCommonInfoMap() {
        HashMap map = new HashMap();
        try {
            map.put("packageName", XhBaseApplication.mContext.getPackageName());
            map.put("crashHappenTime", System.currentTimeMillis() + "");
            map.put("xhcoreVersionName", "4.1.34");
            map.put("versionName", XHAppUtil.getVersionName());
            map.put("versionCode", String.valueOf(XHAppUtil.getVersionCode()));
            map.put("recentActivityListString", ActivityListAdapter.getRecentActivityListString());
            map.put("recentActivityStack", ActivityListAdapter.getActivityStackString());
            if (XHAppConfigProxy.getInstance().isObtainDeviceInfo()) {
                map.put("deviceMacAddress", XHDeviceUtil.getMacFromHardware());
            }
            map.put("totalMemory", ErrorSnapshotManager.getTotalMemoryString(XhBaseApplication.mContext));
            map.put("availableMemory", ErrorSnapshotManager.getAvailableMemoryString(XhBaseApplication.mContext));
            map.put("sdcardTotalSpaceSize", ErrorSnapshotManager.getSdcardTotalSpaceSizeString());
            map.put("sdcardAvailableSpaceSize", ErrorSnapshotManager.getSdcardAvailableSpaceSizeString());
            map.put("appId", XHAppConfigProxy.getInstance().getAppId());
            map.put("buildTimeMillisFormatted", XHAppConfigProxy.getInstance().getBuildTimeMillisFormatted());
            map.putAll(extraUploadMessage);
            CPVDUser user = CPVDUserData.getUser(XhBaseApplication.mContext);
            map.put("schoolId", user != null ? String.valueOf(user.getSchoolId()) : "null");
            Field[] declaredFields = Build.class.getDeclaredFields();
            HashMap map2 = new HashMap();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map2.put(field.getName(), field.get(null).toString());
            }
            map.put("buildClassInfo", map2);
            return map;
        } catch (Throwable th) {
            LogUtils.e(th);
            return map;
        }
    }

    static void handleAPM(JSONObject jSONObject) {
        new APMHandler(jSONObject).handle();
    }

    static void handleApp(JSONObject jSONObject) {
        new APPHandler(jSONObject).handle();
    }

    static void handleCrash(JSONObject jSONObject) {
        new CrashHandler(jSONObject).handle();
    }

    static void handleNetwork(JSONObject jSONObject) {
        new NetworkErrorHandler(jSONObject).handle();
    }

    @Deprecated
    public static CrashOrErrorStatisticsInfo obtain(Context context) {
        return new CrashOrErrorStatisticsInfo();
    }

    private Object wrapToJson(Object obj) {
        return Build.VERSION.SDK_INT >= 19 ? JSONObject.wrap(obj) : obj;
    }

    @Deprecated
    public String getCoreInfo() {
        return "";
    }

    @Deprecated
    public List<String> getCrashStatisticsInfo() {
        return new ArrayList();
    }

    protected void saveToFile(String str) throws IOException {
        ErrorLogPath errorLogPath;
        if (TextUtils.isEmpty(this.filePrefix) || (errorLogPath = this.errorLogPath) == null) {
            LogUtils.e("either filePrefix or errorLogPath is not configured");
        } else {
            LogManager.saveStrInfo2File(this.filePrefix, str, errorLogPath);
        }
    }

    @Deprecated
    public void saveToFile(String str, ErrorLogPath errorLogPath) throws IOException {
        this.filePrefix = str;
        this.errorLogPath = errorLogPath;
        saveToFile(toString());
    }

    @Deprecated
    public CrashOrErrorStatisticsInfo setCoreInfo(String str) {
        return this;
    }

    @Deprecated
    public CrashOrErrorStatisticsInfo setCrashStatisticsInfo(List<String> list) {
        return this;
    }

    public String toString() {
        return JsonUtil.JSONObjectToString(this.issueInfoUser);
    }
}

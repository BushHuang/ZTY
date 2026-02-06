package com.xh.logutils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import com.xh.view.ToastUtil;
import com.xh.xhcore.common.base.BaseActivityLifecycleCallbacks;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.http.archi.ThreadUtil;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.ConstStatistics;
import com.xh.xhcore.common.statistic.ErrorLogPath;
import com.xh.xhcore.common.util.ArrayUtil;
import com.xh.xhcore.common.util.CrashTimeUtils;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xh.xhcore.common.util.XHStringUtil;
import com.xh.xhcore.common.util.XhPermissionUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class LogManager implements Thread.UncaughtExceptionHandler {
    private static String packageName;
    private WeakReference<Activity> activity;
    UncaughtExceptionCallback callback;
    private Context mContext;
    private static final LogManager INSTANCE = new LogManager();
    static String NAME_SPLIT = "#";
    static String LOG_SUFFIX = ".log";
    static String TAG = "LogUtils";
    private static final DateFormat TIME_FORMATTER = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
    public static boolean enableFileLooper = true;
    boolean hasInit = false;
    private final ConcurrentHashMap<String, WeakReference<Activity>> mHashMap = new ConcurrentHashMap<>();
    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks = new BaseActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            android.util.Log.d("[onActivityCreated]", activity.getClass().getSimpleName());
            LogManager.this.mHashMap.put(activity.getClass().getSimpleName(), new WeakReference(activity));
            LogManager.this.activity = new WeakReference(activity);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            LogManager.this.mHashMap.remove(activity.getClass().getSimpleName());
        }
    };

    private LogManager() {
    }

    private Context getContext() {
        Context context = this.mContext;
        return context != null ? context : XhBaseApplication.mContext;
    }

    public static String getErrorMsg(Throwable th) throws IOException {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter((Writer) stringWriter, true);
        try {
            th.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
            return stringWriter.toString();
        } finally {
            printWriter.close();
            try {
                stringWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static LogManager getInstance() {
        return INSTANCE;
    }

    @Deprecated
    private void putLogType(JSONObject jSONObject, String str) throws JSONException {
        try {
            jSONObject.putOpt("logType", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static String saveStrInfo2File(String str, String str2, ErrorLogPath errorLogPath) throws IOException {
        if (!XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")) {
            return null;
        }
        try {
            String str3 = str + NAME_SPLIT + packageName + NAME_SPLIT + TIME_FORMATTER.format(new Date()) + NAME_SPLIT + LOG_SUFFIX;
            if (Environment.getExternalStorageState().equals("mounted")) {
                String str4 = errorLogPath.getPath() + "/";
                File file = new File(str4);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String str5 = str4 + str3;
                XHFileUtil.deleteFile(str5);
                FileOutputStream fileOutputStream = new FileOutputStream(str5);
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.close();
                LogUtils.d("write log success, path = " + str4 + " fileName = " + str3);
            }
            return str3;
        } catch (Exception unused) {
            android.util.Log.e(TAG, "an error occured while writing file...");
            return null;
        }
    }

    private void uploadCrashLog(JSONObject jSONObject) {
        CrashOrErrorStatisticsInfo.handleCrash(jSONObject);
    }

    private void uploadNetworkErrorLog(JSONObject jSONObject) {
        CrashOrErrorStatisticsInfo.handleNetwork(jSONObject);
    }

    public void finishAllActivities() {
        Iterator<Map.Entry<String, WeakReference<Activity>>> it = this.mHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Activity activity = it.next().getValue().get();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public void handleException(Throwable th) {
        if (th == null) {
            return;
        }
        uploadJavaCrashLog(th, "");
        showToastExiting();
    }

    public void init(Context context) {
        android.util.Log.e("XH_COMMON", "init()");
        if (this.hasInit) {
            android.util.Log.e("XH_COMMON", "hasInit");
            return;
        }
        this.hasInit = true;
        this.mContext = context;
        packageName = context.getPackageName();
        ((Application) getContext()).registerActivityLifecycleCallbacks(this.lifecycleCallbacks);
        if (enableFileLooper && !FireLooper.isSafe()) {
            FireLooper.install();
            FireLooper.setUncaughtExceptionHandler(this);
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SETLOGLEVEL_BROADCAST");
        context.registerReceiver(new SetLogLevelReceiver(), intentFilter);
    }

    public void init(Context context, UncaughtExceptionCallback uncaughtExceptionCallback) {
        android.util.Log.e("XH_COMMON", "init() with callback");
        if (uncaughtExceptionCallback == null) {
            android.util.Log.e("XH_COMMON", "callback is null");
        }
        this.callback = uncaughtExceptionCallback;
        init(context);
    }

    public void showToastExiting() {
        try {
            String appName = XHAppUtil.getAppName();
            Context context = this.mContext;
            Object[] objArr = new Object[1];
            if (XHStringUtil.isEmpty(appName)) {
                appName = "程序";
            }
            objArr[0] = appName;
            ToastUtil.showLong(context, String.format("很抱歉,%s出现异常,即将退出。", objArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] throwableToArray(Throwable th) {
        return LogUtils.getStackTraceString(th).split("\n\t|\n");
    }

    public List<String> throwableToList(Throwable th) {
        return ArrayUtil.arrayToList(throwableToArray(th));
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) throws InterruptedException {
        android.util.Log.e("XH_COMMON", "uncaughtException()");
        CrashTimeUtils.INSTANCE.writeCrashTime();
        UncaughtExceptionCallback uncaughtExceptionCallback = this.callback;
        if (uncaughtExceptionCallback != null) {
            uncaughtExceptionCallback.onCallback(thread, th);
        }
        handleException(th);
        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ThreadUtil.toMainThreadIfNeed(new Runnable() {
            @Override
            public void run() {
                XHAppUtil.killAllProcess();
            }
        });
    }

    public void uploadAPMLog(JSONObject jSONObject) {
        CrashOrErrorStatisticsInfo.handleAPM(jSONObject);
    }

    @Deprecated
    public void uploadCrashLog(final String str) {
        uploadCrashLog(new HashMap<String, Object>() {
            {
                put("backtrace", str);
            }
        });
    }

    public void uploadCrashLog(Map map) {
        uploadCrashLog(new JSONObject(map));
    }

    public void uploadJavaCrashLog(final Throwable th, String str) {
        HashMap<String, Object> map = new HashMap<String, Object>() {
            {
                put("Crash type", ConstStatistics.CrashType.java.getType());
                put("backtrace", LogManager.this.throwableToArray(th));
            }
        };
        if (!TextUtils.isEmpty(str)) {
            map.put("extraMessage", str);
        }
        uploadCrashLog(map);
    }

    @Deprecated
    public void uploadLog(String str) {
        uploadLog(LogConverter.convertLogInfoToMap(str, ErrorLogPath.appUpload.name()));
    }

    public void uploadLog(Map map) {
        uploadLog(new JSONObject(map));
    }

    public void uploadLog(JSONObject jSONObject) {
        CrashOrErrorStatisticsInfo.handleApp(jSONObject);
    }

    @Deprecated
    public void uploadNetworkErrorLog(String str) {
        uploadNetworkErrorLog(LogConverter.convertLogInfoToMap(str, ErrorLogPath.networkError.name()));
    }

    public void uploadNetworkErrorLog(Map map) {
        uploadNetworkErrorLog(new JSONObject(map));
    }
}

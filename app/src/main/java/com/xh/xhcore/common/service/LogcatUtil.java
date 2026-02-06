package com.xh.xhcore.common.service;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.XHEnvironment;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xh.xhcore.common.util.XhPermissionUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

class LogcatUtil {
    private static boolean isRunning = false;
    private static long runningTime;

    LogcatUtil() {
    }

    private static boolean filterLog(String str) {
        return str.isEmpty() || str.contains("SurfaceControl") || str.contains("ViewRootImpl") || str.contains("AppStorageService") || str.contains("XHStreamFetcher") || str.contains("ClearConfigLoader");
    }

    public static boolean isRunning() {
        if (isRunning && System.currentTimeMillis() - runningTime >= 300000) {
            LogUtils.i("重置logcat isRunning 的状态为false");
            isRunning = false;
        }
        return isRunning;
    }

    public static boolean startCatchLog(Context context, long j) {
        return startCatchLog(String.format("%s/catch#%s#.log", XHEnvironment.getExternalStorageDirectory() + "/xuehai/log", context.getPackageName()), j);
    }

    public static boolean startCatchLog(String str, long j) {
        return startCatchLogWithSize(str, j);
    }

    public static boolean startCatchLog(String str, String str2, long j) {
        LogUtils.i("LogcatUtil", "command : " + str);
        if (isRunning) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!XHAppConfigProxy.getInstance().isInside() && !XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return false;
        }
        isRunning = true;
        runningTime = System.currentTimeMillis();
        Process processExec = null;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            try {
                processExec = Runtime.getRuntime().exec("sh");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
                dataOutputStream.write(str.getBytes());
                dataOutputStream.writeBytes("\n");
                dataOutputStream.flush();
                while (isRunning) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        runningTime = System.currentTimeMillis();
                        if (new File(str2).length() > j) {
                            File file = new File(str2 + ".1");
                            if (file.exists()) {
                                try {
                                    file.delete();
                                } catch (Throwable unused) {
                                }
                            }
                            XHFileUtil.writeFileFromIS(file, (InputStream) new FileInputStream(str2), false);
                            try {
                                new File(str2).delete();
                            } catch (Throwable unused2) {
                            }
                        } else if (!filterLog(line)) {
                            XHFileUtil.writeFileFromString(str2, line + "\n", true);
                        }
                    }
                }
                isRunning = false;
            } catch (Exception e2) {
                e2.printStackTrace();
                isRunning = false;
                if (processExec != null) {
                    processExec.destroy();
                }
            }
            if (processExec != null) {
                processExec.destroy();
            }
            return false;
        } catch (Throwable th) {
            isRunning = false;
            if (processExec != null) {
                try {
                    processExec.destroy();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static boolean startCatchLogWithSize(String str, long j) {
        return startCatchLog("logcat -v time process |grep " + Process.myPid(), str, j);
    }

    public static void stopCatchLog() {
        isRunning = false;
    }
}

package com.zaze.utils.log;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.zaze.utils.FileUtil;
import com.zaze.utils.ZCommand;
import com.zaze.utils.ZStringUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogcatUtil {
    private static boolean isRunning = false;

    public static boolean isRunning() {
        return isRunning;
    }

    public static void startCatchLog(Context context) throws Throwable {
        try {
            startCatchLog(ZStringUtil.format("%s/catch#%s#.log", context.getExternalCacheDir().getAbsolutePath() + "/zaze/log", context.getPackageName()), 10485760L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startCatchLog(String str, long j) throws Throwable {
        startCatchLog("logcat -v time", str, j);
    }

    public static void startCatchLog(String str, String str2, long j) throws Throwable {
        BufferedReader bufferedReader;
        ZLog.i("Cmd[ZZ]", "command : " + str, new Object[0]);
        if (isRunning) {
            return;
        }
        isRunning = true;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Process process = null;
        try {
            try {
                Process processExec = Runtime.getRuntime().exec(ZCommand.isRoot() ? "su" : "sh");
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
                        dataOutputStream.write(str.getBytes());
                        dataOutputStream.writeBytes("\n");
                        dataOutputStream.flush();
                        File file = new File(str2);
                        while (isRunning) {
                            String line = bufferedReader.readLine();
                            if (line != null) {
                                isRunning = FileUtil.writeToFile(file, line + "\n", j);
                                SystemClock.sleep(300L);
                            }
                        }
                        isRunning = false;
                        if (processExec != null) {
                            processExec.destroy();
                        }
                        bufferedReader.close();
                    } catch (Exception e) {
                        e = e;
                        process = processExec;
                        try {
                            e.printStackTrace();
                            if (process != null) {
                                process.destroy();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (process != null) {
                                try {
                                    process.destroy();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                    throw th;
                                }
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        process = processExec;
                        if (process != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    bufferedReader = null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedReader = null;
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public static void stopCatchLog() {
        isRunning = false;
    }
}

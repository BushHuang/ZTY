package com.xh.xhcore.common.xcrash;

import android.content.Context;
import android.os.MessageQueue;
import android.text.TextUtils;
import android.util.Log;
import com.xh.logutils.CrashOrErrorStatisticsInfo;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.XHEnvironment;
import com.xh.xhcore.common.util.CrashTimeUtils;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.LooperUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONObject;
import xcrash.ICrashCallback;
import xcrash.TombstoneManager;
import xcrash.TombstoneParser;
import xcrash.XCrash;

public class XCrashUtil {
    private static final String TAG = "XCrashUtil";
    private static final boolean enableInstantUpload = true;
    private static boolean isFinishedUploadPreviousCrashLog = false;

    private static void appendCrashInfoToCrashFile(String str) {
        for (Map.Entry<String, Object> entry : CrashOrErrorStatisticsInfo.getCommonInfoMap().entrySet()) {
            TombstoneManager.appendSection(str, entry.getKey().toString(), entry.getValue().toString());
        }
    }

    public static void init(Context context) {
        ICrashCallback iCrashCallback = new ICrashCallback() {
            private void handleCrash(String str, String str2) {
                Log.e("TAG", "handleCrash invoked!!");
                StringBuilder sb = new StringBuilder();
                sb.append("log path: ");
                sb.append(str != null ? str : "(null)");
                sb.append(", emergency: ");
                sb.append(str2 != null ? str2 : "(null)");
                Log.d("XCrashUtil", sb.toString());
                if (XCrashUtil.isDiskExhausted(str2)) {
                    XCrashUtil.sendThenDeleteCrashLog(str, str2);
                } else {
                    boolean unused = XCrashUtil.isFinishedUploadPreviousCrashLog;
                    XCrashUtil.sendThenDeleteCrashLog(str, str2);
                }
            }

            @Override
            public void onCrash(String str, String str2) {
                Log.e("TAG", "onCrash invoked!!");
                CrashTimeUtils.INSTANCE.writeCrashTime();
                XHFileUtil.createOrExistsDir(str);
                handleCrash(str, str2);
                LogManager.getInstance().showToastExiting();
                XHAppUtil.finishActivityAndServiceBeforeKillProcess();
            }
        };
        ICrashCallback iCrashCallback2 = new ICrashCallback() {
            private void handleCrash(String str, String str2) {
                Log.e("TAG", "anrCallback handleCrash invoked!!");
                StringBuilder sb = new StringBuilder();
                sb.append("log path: ");
                sb.append(str != null ? str : "(null)");
                sb.append(", emergency: ");
                sb.append(str2 != null ? str2 : "(null)");
                Log.d("XCrashUtil", sb.toString());
                if (XCrashUtil.isDiskExhausted(str2)) {
                    XCrashUtil.sendThenDeleteCrashLog(str, str2);
                } else {
                    boolean unused = XCrashUtil.isFinishedUploadPreviousCrashLog;
                    XCrashUtil.sendThenDeleteCrashLog(str, str2);
                }
            }

            @Override
            public void onCrash(String str, String str2) {
                Log.e("TAG", "anrCallback onCrash invoked!!");
                handleCrash(str, str2);
                LogManager.getInstance().showToastExiting();
                XHAppUtil.finishActivityAndServiceBeforeKillProcess();
            }
        };
        Log.d("XCrashUtil", "xCrash SDK init: start");
        XCrash.init(context, new XCrash.InitParameters().disableJavaCrashHandler().setNativeRethrow(true).setNativeLogCountMax(10).setNativeDumpFds(false).setNativeDumpNetwork(false).setNativeDumpMap(false).setNativeDumpAllThreads(false).setNativeDumpAllThreadsWhiteList(new String[]{"^Signal Catcher$", "^Jit thread pool$", ".*(R|r)ender.*"}).setNativeDumpAllThreadsCountMax(4).setNativeCallback(iCrashCallback).setAnrRethrow(true).setAnrDumpFds(false).setAnrDumpNetwork(false).setAnrLogCountMax(10).setAnrCallback(iCrashCallback2).setPlaceholderCountMax(3).setPlaceholderSizeKb(128).setLogFileMaintainDelayMs(5000));
        Log.d("XCrashUtil", "xCrash SDK init: end");
        LooperUtil.addIdleHandlerToMain(new MessageQueue.IdleHandler() {
            @Override
            public final boolean queueIdle() {
                return XCrashUtil.lambda$init$1();
            }
        });
    }

    private static boolean isDiskExhausted(String str) {
        return str != null;
    }

    private static boolean isUploadCrashUncompleted(Future<?> future) {
        return future == null || !future.isDone();
    }

    static void lambda$init$0(File[] fileArr) {
        for (File file : fileArr) {
            sendThenDeleteCrashLog(file.getAbsolutePath(), null);
        }
        isFinishedUploadPreviousCrashLog = true;
    }

    static boolean lambda$init$1() {
        final File[] allTombstones = TombstoneManager.getAllTombstones();
        if (allTombstones.length >= 1) {
            new Thread(new Runnable() {
                @Override
                public final void run() {
                    XCrashUtil.lambda$init$0(allTombstones);
                }
            }, "sendCrashLog").start();
            return false;
        }
        Log.d("XCrashUtil", "no crash log remain to upload");
        isFinishedUploadPreviousCrashLog = true;
        return false;
    }

    private static void reduceInfoInTombstoneMap(Map<String, String> map) {
        int iIndexOf;
        String str = map.get("stack");
        if (TextUtils.isEmpty(str) || (iIndexOf = str.indexOf("........  ........")) == -1) {
            return;
        }
        map.put("stack", str.substring(0, iIndexOf));
    }

    private static void sendThenDeleteCrashLog(String str, String str2) {
        if (isDiskExhausted(str2)) {
            LogUtils.e("disk is exhausted , can only be upload by network call");
            return;
        }
        Map map = new HashMap();
        try {
            map = TombstoneParser.parse(str, str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reduceInfoInTombstoneMap(map);
        LogManager.getInstance().uploadCrashLog(map);
        TombstoneManager.deleteTombstone(str);
    }

    private static void testUploadCrashLog(Map map) throws Throwable {
        String strJSONObjectToString = JsonUtil.JSONObjectToString(new JSONObject(map));
        String str = XHEnvironment.getExternalStorageDirectory().toString() + "/xuehai/xhcoretest";
        File file = new File(str);
        File file2 = new File(str + "/testUploadCrashLog.txt");
        file.mkdirs();
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XHFileUtil.writeFileFromString(file2, strJSONObjectToString, false);
    }
}

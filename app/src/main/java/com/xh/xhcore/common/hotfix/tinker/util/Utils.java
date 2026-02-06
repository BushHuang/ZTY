package com.xh.xhcore.common.hotfix.tinker.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.StatFs;
import com.tencent.tinker.lib.util.TinkerLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Utils {
    public static final int ERROR_PATCH_CONDITION_NOT_SATISFIED = -24;
    public static final int ERROR_PATCH_CRASH_LIMIT = -23;
    public static final int ERROR_PATCH_GOOGLEPLAY_CHANNEL = -20;
    public static final int ERROR_PATCH_MEMORY_LIMIT = -22;
    public static final int ERROR_PATCH_ROM_SPACE = -21;
    public static final int MIN_MEMORY_HEAP_SIZE = 45;
    public static final String PLATFORM = "platform";
    private static final String TAG = "Tinker.Utils";
    private static boolean background = false;

    public static class ScreenState {

        public interface IOnScreenOff {
            void onScreenOff();
        }

        public ScreenState(Context context, final IOnScreenOff iOnScreenOff) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            context.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context2, Intent intent) {
                    IOnScreenOff iOnScreenOff2;
                    String action = intent == null ? "" : intent.getAction();
                    TinkerLog.i("Tinker.Utils", "ScreenReceiver action [%s] ", action);
                    if ("android.intent.action.SCREEN_OFF".equals(action) && (iOnScreenOff2 = iOnScreenOff) != null) {
                        iOnScreenOff2.onScreenOff();
                    }
                    context2.unregisterReceiver(this);
                }
            }, intentFilter);
        }
    }

    public static int checkForPatchRecover(long j, int i) {
        if (isGooglePlay()) {
            return -20;
        }
        if (i < 45) {
            return -22;
        }
        return !checkRomSpaceEnough(j) ? -21 : 0;
    }

    @Deprecated
    public static boolean checkRomSpaceEnough(long j) {
        long availableBlocks;
        long blockCount;
        StatFs statFs;
        try {
            statFs = new StatFs(Environment.getDataDirectory().getPath());
            availableBlocks = statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception unused) {
            availableBlocks = 0;
        }
        try {
            blockCount = statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Exception unused2) {
            blockCount = 0;
            return blockCount == 0 ? false : false;
        }
        if (blockCount == 0 && availableBlocks > j) {
            return true;
        }
    }

    public static String getExceptionCauseString(Throwable th) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        while (th.getCause() != null) {
            try {
                th = th.getCause();
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        th.printStackTrace(printStream);
        return toVisualString(byteArrayOutputStream.toString());
    }

    public static boolean isBackground() {
        return background;
    }

    public static boolean isGooglePlay() {
        return false;
    }

    public static boolean isXposedExists(Throwable th) {
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className != null && className.contains("de.robv.android.xposed.XposedBridge")) {
                return true;
            }
        }
        return false;
    }

    public static void setBackground(boolean z) {
        background = z;
    }

    private static String toVisualString(String str) {
        char[] charArray;
        boolean z;
        if (str == null || (charArray = str.toCharArray()) == null) {
            return null;
        }
        int i = 0;
        while (true) {
            if (i >= charArray.length) {
                z = false;
                break;
            }
            if (charArray[i] > 127) {
                charArray[i] = 0;
                z = true;
                break;
            }
            i++;
        }
        return z ? new String(charArray, 0, i) : str;
    }
}

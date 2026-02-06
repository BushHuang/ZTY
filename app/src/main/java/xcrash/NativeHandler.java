package xcrash;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.util.Map;

class NativeHandler {
    private static final NativeHandler instance = new NativeHandler();
    private ICrashCallback anrCallback;
    private boolean anrCheckProcessState;
    private boolean anrEnable;
    private ICrashCallback crashCallback;
    private boolean crashRethrow;
    private Context ctx;
    private long anrTimeoutMs = 15000;
    private boolean initNativeLibOk = false;

    private NativeHandler() {
    }

    private static void crashCallback(String str, String str2, boolean z, boolean z2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                String stacktraceByThreadName = getStacktraceByThreadName(z2, str3);
                if (!TextUtils.isEmpty(stacktraceByThreadName)) {
                    TombstoneManager.appendSection(str, "java stacktrace", stacktraceByThreadName);
                }
            }
            TombstoneManager.appendSection(str, "memory info", Util.getProcessMemoryInfo());
            TombstoneManager.appendSection(str, "foreground", ActivityMonitor.getInstance().isApplicationForeground() ? "yes" : "no");
        }
        ICrashCallback iCrashCallback = getInstance().crashCallback;
        if (iCrashCallback != null) {
            try {
                iCrashCallback.onCrash(str, str2);
            } catch (Exception e) {
                XCrash.getLogger().w("xcrash", "NativeHandler native crash callback.onCrash failed", e);
            }
        }
        if (getInstance().crashRethrow) {
            return;
        }
        ActivityMonitor.getInstance().finishAllActivities();
    }

    static NativeHandler getInstance() {
        return instance;
    }

    private static String getStacktraceByThreadName(boolean z, String str) {
        try {
            for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread key = entry.getKey();
                if ((z && key.getName().equals("main")) || (!z && key.getName().contains(str))) {
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement[] value = entry.getValue();
                    for (StackTraceElement stackTraceElement : value) {
                        sb.append("    at ");
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                    }
                    return sb.toString();
                }
            }
            return null;
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "NativeHandler getStacktraceByThreadName failed", e);
            return null;
        }
    }

    private static native int nativeInit(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z, boolean z2, int i2, int i3, int i4, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i5, String[] strArr, boolean z8, boolean z9, int i6, int i7, int i8, boolean z10, boolean z11);

    private static native void nativeNotifyJavaCrashed();

    private static native void nativeTestCrash(int i);

    private static void traceCallback(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        TombstoneManager.appendSection(str, "memory info", Util.getProcessMemoryInfo());
        TombstoneManager.appendSection(str, "foreground", ActivityMonitor.getInstance().isApplicationForeground() ? "yes" : "no");
        if (getInstance().anrCheckProcessState && !Util.checkProcessAnrState(getInstance().ctx, getInstance().anrTimeoutMs)) {
            FileManager.getInstance().recycleLogFile(new File(str));
            return;
        }
        if (FileManager.getInstance().maintainAnr()) {
            String str3 = str.substring(0, str.length() - 13) + ".anr.xcrash";
            File file = new File(str);
            if (!file.renameTo(new File(str3))) {
                FileManager.getInstance().recycleLogFile(file);
                return;
            }
            ICrashCallback iCrashCallback = getInstance().anrCallback;
            if (iCrashCallback != null) {
                try {
                    iCrashCallback.onCrash(str3, str2);
                } catch (Exception e) {
                    XCrash.getLogger().w("xcrash", "NativeHandler ANR callback.onCrash failed", e);
                }
            }
        }
    }

    int initialize(Context context, ILibLoader iLibLoader, String str, String str2, String str3, boolean z, boolean z2, int i, int i2, int i3, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i4, String[] strArr, ICrashCallback iCrashCallback, boolean z8, boolean z9, boolean z10, int i5, int i6, int i7, boolean z11, boolean z12, ICrashCallback iCrashCallback2) {
        if (iLibLoader == null) {
            try {
                System.loadLibrary("xcrash");
            } catch (Throwable th) {
                XCrash.getLogger().e("xcrash", "NativeHandler System.loadLibrary failed", th);
                return -2;
            }
        } else {
            try {
                iLibLoader.loadLibrary("xcrash");
            } catch (Throwable th2) {
                XCrash.getLogger().e("xcrash", "NativeHandler ILibLoader.loadLibrary failed", th2);
                return -2;
            }
        }
        this.ctx = context;
        this.crashRethrow = z2;
        this.crashCallback = iCrashCallback;
        this.anrEnable = z8;
        this.anrCheckProcessState = z10;
        this.anrCallback = iCrashCallback2;
        this.anrTimeoutMs = z9 ? 15000L : 30000L;
        try {
            if (nativeInit(Build.VERSION.SDK_INT, Build.VERSION.RELEASE, Util.getAbiList(), Build.MANUFACTURER, Build.BRAND, Build.MODEL, Build.FINGERPRINT, str, str2, context.getApplicationInfo().nativeLibraryDir, str3, z, z2, i, i2, i3, z3, z4, z5, z6, z7, i4, strArr, z8, z9, i5, i6, i7, z11, z12) != 0) {
                XCrash.getLogger().e("xcrash", "NativeHandler init failed");
                return -3;
            }
            this.initNativeLibOk = true;
            return 0;
        } catch (Throwable th3) {
            XCrash.getLogger().e("xcrash", "NativeHandler init failed", th3);
            return -3;
        }
    }

    void notifyJavaCrashed() {
        if (this.initNativeLibOk && this.anrEnable) {
            nativeNotifyJavaCrashed();
        }
    }

    void testNativeCrash(boolean z) {
        if (this.initNativeLibOk) {
            nativeTestCrash(z ? 1 : 0);
        }
    }
}

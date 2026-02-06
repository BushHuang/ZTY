package xcrash;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;

public final class XCrash {
    private static String appId = null;
    private static String appVersion = null;
    private static boolean initialized = false;
    private static String logDir;
    private static ILogger logger = new DefaultLogger();

    public static class InitParameters {
        String appVersion = null;
        String logDir = null;
        int logFileMaintainDelayMs = 5000;
        ILogger logger = null;
        ILibLoader libLoader = null;
        int placeholderCountMax = 0;
        int placeholderSizeKb = 128;
        boolean enableJavaCrashHandler = true;
        boolean javaRethrow = true;
        int javaLogCountMax = 10;
        int javaLogcatSystemLines = 50;
        int javaLogcatEventsLines = 50;
        int javaLogcatMainLines = 200;
        boolean javaDumpFds = true;
        boolean javaDumpNetworkInfo = true;
        boolean javaDumpAllThreads = true;
        int javaDumpAllThreadsCountMax = 0;
        String[] javaDumpAllThreadsWhiteList = null;
        ICrashCallback javaCallback = null;
        boolean enableNativeCrashHandler = true;
        boolean nativeRethrow = true;
        int nativeLogCountMax = 10;
        int nativeLogcatSystemLines = 50;
        int nativeLogcatEventsLines = 50;
        int nativeLogcatMainLines = 200;
        boolean nativeDumpElfHash = true;
        boolean nativeDumpMap = true;
        boolean nativeDumpFds = true;
        boolean nativeDumpNetworkInfo = true;
        boolean nativeDumpAllThreads = true;
        int nativeDumpAllThreadsCountMax = 0;
        String[] nativeDumpAllThreadsWhiteList = null;
        ICrashCallback nativeCallback = null;
        boolean enableAnrHandler = true;
        boolean anrRethrow = true;
        boolean anrCheckProcessState = true;
        int anrLogCountMax = 10;
        int anrLogcatSystemLines = 50;
        int anrLogcatEventsLines = 50;
        int anrLogcatMainLines = 200;
        boolean anrDumpFds = true;
        boolean anrDumpNetworkInfo = true;
        ICrashCallback anrCallback = null;

        public InitParameters disableAnrCrashHandler() {
            this.enableAnrHandler = false;
            return this;
        }

        public InitParameters disableJavaCrashHandler() {
            this.enableJavaCrashHandler = false;
            return this;
        }

        public InitParameters disableNativeCrashHandler() {
            this.enableNativeCrashHandler = false;
            return this;
        }

        public InitParameters enableAnrCrashHandler() {
            this.enableAnrHandler = true;
            return this;
        }

        public InitParameters enableJavaCrashHandler() {
            this.enableJavaCrashHandler = true;
            return this;
        }

        public InitParameters enableNativeCrashHandler() {
            this.enableNativeCrashHandler = true;
            return this;
        }

        public InitParameters setAnrCallback(ICrashCallback iCrashCallback) {
            this.anrCallback = iCrashCallback;
            return this;
        }

        public InitParameters setAnrCheckProcessState(boolean z) {
            this.anrCheckProcessState = z;
            return this;
        }

        public InitParameters setAnrDumpFds(boolean z) {
            this.anrDumpFds = z;
            return this;
        }

        public InitParameters setAnrDumpNetwork(boolean z) {
            this.anrDumpNetworkInfo = z;
            return this;
        }

        public InitParameters setAnrLogCountMax(int i) {
            if (i < 1) {
                i = 1;
            }
            this.anrLogCountMax = i;
            return this;
        }

        public InitParameters setAnrLogcatEventsLines(int i) {
            this.anrLogcatEventsLines = i;
            return this;
        }

        public InitParameters setAnrLogcatMainLines(int i) {
            this.anrLogcatMainLines = i;
            return this;
        }

        public InitParameters setAnrLogcatSystemLines(int i) {
            this.anrLogcatSystemLines = i;
            return this;
        }

        public InitParameters setAnrRethrow(boolean z) {
            this.anrRethrow = z;
            return this;
        }

        public InitParameters setAppVersion(String str) {
            this.appVersion = str;
            return this;
        }

        public InitParameters setJavaCallback(ICrashCallback iCrashCallback) {
            this.javaCallback = iCrashCallback;
            return this;
        }

        public InitParameters setJavaDumpAllThreads(boolean z) {
            this.javaDumpAllThreads = z;
            return this;
        }

        public InitParameters setJavaDumpAllThreadsCountMax(int i) {
            if (i < 0) {
                i = 0;
            }
            this.javaDumpAllThreadsCountMax = i;
            return this;
        }

        public InitParameters setJavaDumpAllThreadsWhiteList(String[] strArr) {
            this.javaDumpAllThreadsWhiteList = strArr;
            return this;
        }

        public InitParameters setJavaDumpFds(boolean z) {
            this.javaDumpFds = z;
            return this;
        }

        public InitParameters setJavaDumpNetworkInfo(boolean z) {
            this.javaDumpNetworkInfo = z;
            return this;
        }

        public InitParameters setJavaLogCountMax(int i) {
            if (i < 1) {
                i = 1;
            }
            this.javaLogCountMax = i;
            return this;
        }

        public InitParameters setJavaLogcatEventsLines(int i) {
            this.javaLogcatEventsLines = i;
            return this;
        }

        public InitParameters setJavaLogcatMainLines(int i) {
            this.javaLogcatMainLines = i;
            return this;
        }

        public InitParameters setJavaLogcatSystemLines(int i) {
            this.javaLogcatSystemLines = i;
            return this;
        }

        public InitParameters setJavaRethrow(boolean z) {
            this.javaRethrow = z;
            return this;
        }

        public InitParameters setLibLoader(ILibLoader iLibLoader) {
            this.libLoader = iLibLoader;
            return this;
        }

        public InitParameters setLogDir(String str) {
            this.logDir = str;
            return this;
        }

        public InitParameters setLogFileMaintainDelayMs(int i) {
            if (i < 0) {
                i = 0;
            }
            this.logFileMaintainDelayMs = i;
            return this;
        }

        public InitParameters setLogger(ILogger iLogger) {
            this.logger = iLogger;
            return this;
        }

        public InitParameters setNativeCallback(ICrashCallback iCrashCallback) {
            this.nativeCallback = iCrashCallback;
            return this;
        }

        public InitParameters setNativeDumpAllThreads(boolean z) {
            this.nativeDumpAllThreads = z;
            return this;
        }

        public InitParameters setNativeDumpAllThreadsCountMax(int i) {
            if (i < 0) {
                i = 0;
            }
            this.nativeDumpAllThreadsCountMax = i;
            return this;
        }

        public InitParameters setNativeDumpAllThreadsWhiteList(String[] strArr) {
            this.nativeDumpAllThreadsWhiteList = strArr;
            return this;
        }

        public InitParameters setNativeDumpElfHash(boolean z) {
            this.nativeDumpElfHash = z;
            return this;
        }

        public InitParameters setNativeDumpFds(boolean z) {
            this.nativeDumpFds = z;
            return this;
        }

        public InitParameters setNativeDumpMap(boolean z) {
            this.nativeDumpMap = z;
            return this;
        }

        public InitParameters setNativeDumpNetwork(boolean z) {
            this.nativeDumpNetworkInfo = z;
            return this;
        }

        public InitParameters setNativeLogCountMax(int i) {
            if (i < 1) {
                i = 1;
            }
            this.nativeLogCountMax = i;
            return this;
        }

        public InitParameters setNativeLogcatEventsLines(int i) {
            this.nativeLogcatEventsLines = i;
            return this;
        }

        public InitParameters setNativeLogcatMainLines(int i) {
            this.nativeLogcatMainLines = i;
            return this;
        }

        public InitParameters setNativeLogcatSystemLines(int i) {
            this.nativeLogcatSystemLines = i;
            return this;
        }

        public InitParameters setNativeRethrow(boolean z) {
            this.nativeRethrow = z;
            return this;
        }

        public InitParameters setPlaceholderCountMax(int i) {
            if (i < 0) {
                i = 0;
            }
            this.placeholderCountMax = i;
            return this;
        }

        public InitParameters setPlaceholderSizeKb(int i) {
            if (i < 0) {
                i = 0;
            }
            this.placeholderSizeKb = i;
            return this;
        }
    }

    private XCrash() {
    }

    static String getAppId() {
        return appId;
    }

    static String getAppVersion() {
        return appVersion;
    }

    static String getLogDir() {
        return logDir;
    }

    static ILogger getLogger() {
        return logger;
    }

    public static int init(Context context) {
        return init(context, null);
    }

    public static synchronized int init(Context context, InitParameters initParameters) {
        Context context2;
        InitParameters initParameters2;
        ICrashCallback iCrashCallback;
        int iInitialize;
        if (initialized) {
            return 0;
        }
        initialized = true;
        if (context == null) {
            return -1;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = context;
        }
        InitParameters initParameters3 = initParameters == null ? new InitParameters() : initParameters;
        if (initParameters3.logger != null) {
            logger = initParameters3.logger;
        }
        String packageName = applicationContext.getPackageName();
        appId = packageName;
        if (TextUtils.isEmpty(packageName)) {
            appId = "unknown";
        }
        if (TextUtils.isEmpty(initParameters3.appVersion)) {
            initParameters3.appVersion = Util.getAppVersion(applicationContext);
        }
        appVersion = initParameters3.appVersion;
        if (TextUtils.isEmpty(initParameters3.logDir)) {
            initParameters3.logDir = applicationContext.getFilesDir() + "/tombstones";
        }
        logDir = initParameters3.logDir;
        int iMyPid = Process.myPid();
        String processName = null;
        if (initParameters3.enableJavaCrashHandler || initParameters3.enableAnrHandler) {
            processName = Util.getProcessName(applicationContext, iMyPid);
            if (initParameters3.enableAnrHandler && (TextUtils.isEmpty(processName) || !processName.equals(packageName))) {
                initParameters3.enableAnrHandler = false;
            }
        }
        String str = processName;
        FileManager.getInstance().initialize(initParameters3.logDir, initParameters3.javaLogCountMax, initParameters3.nativeLogCountMax, initParameters3.anrLogCountMax, initParameters3.placeholderCountMax, initParameters3.placeholderSizeKb, initParameters3.logFileMaintainDelayMs);
        if ((initParameters3.enableJavaCrashHandler || initParameters3.enableNativeCrashHandler || initParameters3.enableAnrHandler) && (applicationContext instanceof Application)) {
            ActivityMonitor.getInstance().initialize((Application) applicationContext);
        }
        if (initParameters3.enableJavaCrashHandler) {
            context2 = applicationContext;
            JavaCrashHandler.getInstance().initialize(iMyPid, str, appId, initParameters3.appVersion, initParameters3.logDir, initParameters3.javaRethrow, initParameters3.javaLogcatSystemLines, initParameters3.javaLogcatEventsLines, initParameters3.javaLogcatMainLines, initParameters3.javaDumpFds, initParameters3.javaDumpNetworkInfo, initParameters3.javaDumpAllThreads, initParameters3.javaDumpAllThreadsCountMax, initParameters3.javaDumpAllThreadsWhiteList, initParameters3.javaCallback);
        } else {
            context2 = applicationContext;
        }
        if (!initParameters3.enableAnrHandler || Build.VERSION.SDK_INT >= 21) {
            initParameters2 = initParameters3;
        } else {
            initParameters2 = initParameters3;
            AnrHandler.getInstance().initialize(context2, iMyPid, str, appId, initParameters3.appVersion, initParameters3.logDir, initParameters3.anrCheckProcessState, initParameters3.anrLogcatSystemLines, initParameters3.anrLogcatEventsLines, initParameters3.anrLogcatMainLines, initParameters3.anrDumpFds, initParameters3.anrDumpNetworkInfo, initParameters3.anrCallback);
        }
        if (initParameters2.enableNativeCrashHandler || (initParameters2.enableAnrHandler && Build.VERSION.SDK_INT >= 21)) {
            NativeHandler nativeHandler = NativeHandler.getInstance();
            ILibLoader iLibLoader = initParameters2.libLoader;
            String str2 = appId;
            String str3 = initParameters2.appVersion;
            String str4 = initParameters2.logDir;
            boolean z = initParameters2.enableNativeCrashHandler;
            boolean z2 = initParameters2.nativeRethrow;
            int i = initParameters2.nativeLogcatSystemLines;
            int i2 = initParameters2.nativeLogcatEventsLines;
            int i3 = initParameters2.nativeLogcatMainLines;
            boolean z3 = initParameters2.nativeDumpElfHash;
            boolean z4 = initParameters2.nativeDumpMap;
            boolean z5 = initParameters2.nativeDumpFds;
            boolean z6 = initParameters2.nativeDumpNetworkInfo;
            boolean z7 = initParameters2.nativeDumpAllThreads;
            int i4 = initParameters2.nativeDumpAllThreadsCountMax;
            String[] strArr = initParameters2.nativeDumpAllThreadsWhiteList;
            ICrashCallback iCrashCallback2 = initParameters2.nativeCallback;
            if (initParameters2.enableAnrHandler) {
                iCrashCallback = iCrashCallback2;
                boolean z8 = Build.VERSION.SDK_INT >= 21;
                iInitialize = nativeHandler.initialize(context2, iLibLoader, str2, str3, str4, z, z2, i, i2, i3, z3, z4, z5, z6, z7, i4, strArr, iCrashCallback, z8, initParameters2.anrRethrow, initParameters2.anrCheckProcessState, initParameters2.anrLogcatSystemLines, initParameters2.anrLogcatEventsLines, initParameters2.anrLogcatMainLines, initParameters2.anrDumpFds, initParameters2.anrDumpNetworkInfo, initParameters2.anrCallback);
            } else {
                iCrashCallback = iCrashCallback2;
            }
            iInitialize = nativeHandler.initialize(context2, iLibLoader, str2, str3, str4, z, z2, i, i2, i3, z3, z4, z5, z6, z7, i4, strArr, iCrashCallback, z8, initParameters2.anrRethrow, initParameters2.anrCheckProcessState, initParameters2.anrLogcatSystemLines, initParameters2.anrLogcatEventsLines, initParameters2.anrLogcatMainLines, initParameters2.anrDumpFds, initParameters2.anrDumpNetworkInfo, initParameters2.anrCallback);
        } else {
            iInitialize = 0;
        }
        FileManager.getInstance().maintain();
        return iInitialize;
    }

    public static void testJavaCrash(boolean z) throws RuntimeException {
        if (!z) {
            throw new RuntimeException("test java exception");
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                throw new RuntimeException("test java exception");
            }
        };
        thread.setName("xcrash_test_java_thread");
        thread.start();
    }

    public static void testNativeCrash(boolean z) {
        NativeHandler.getInstance().testNativeCrash(z);
    }
}

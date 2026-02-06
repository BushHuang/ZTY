package xcrash;

import android.os.Process;
import android.text.TextUtils;
import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

class JavaCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final JavaCrashHandler instance = new JavaCrashHandler();
    private String appId;
    private String appVersion;
    private ICrashCallback callback;
    private boolean dumpAllThreads;
    private int dumpAllThreadsCountMax;
    private String[] dumpAllThreadsWhiteList;
    private boolean dumpFds;
    private boolean dumpNetworkInfo;
    private String logDir;
    private int logcatEventsLines;
    private int logcatMainLines;
    private int logcatSystemLines;
    private int pid;
    private String processName;
    private boolean rethrow;
    private final Date startTime = new Date();
    private Thread.UncaughtExceptionHandler defaultHandler = null;

    private JavaCrashHandler() {
    }

    private String getEmergency(Date date, Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return Util.getLogHeader(this.startTime, date, "java", this.appId, this.appVersion) + "pid: " + this.pid + ", tid: " + Process.myTid() + ", name: " + thread.getName() + "  >>> " + this.processName + " <<<\n\njava stacktrace:\n" + stringWriter.toString() + "\n";
    }

    static JavaCrashHandler getInstance() {
        return instance;
    }

    private String getOtherThreadsInfo(Thread thread) {
        ArrayList<Pattern> arrayList;
        if (this.dumpAllThreadsWhiteList != null) {
            arrayList = new ArrayList<>();
            for (String str : this.dumpAllThreadsWhiteList) {
                try {
                    arrayList.add(Pattern.compile(str));
                } catch (Exception e) {
                    XCrash.getLogger().w("xcrash", "JavaCrashHandler pattern compile failed", e);
                }
            }
        } else {
            arrayList = null;
        }
        StringBuilder sb = new StringBuilder();
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread key = entry.getKey();
            StackTraceElement[] value = entry.getValue();
            if (!key.getName().equals(thread.getName()) && (arrayList == null || matchThreadName(arrayList, key.getName()))) {
                i2++;
                int i4 = this.dumpAllThreadsCountMax;
                if (i4 <= 0 || i < i4) {
                    sb.append("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
                    sb.append("pid: ");
                    sb.append(this.pid);
                    sb.append(", tid: ");
                    sb.append(key.getId());
                    sb.append(", name: ");
                    sb.append(key.getName());
                    sb.append("  >>> ");
                    sb.append(this.processName);
                    sb.append(" <<<\n");
                    sb.append("\n");
                    sb.append("java stacktrace:\n");
                    for (StackTraceElement stackTraceElement : value) {
                        sb.append("    at ");
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                    }
                    sb.append("\n");
                    i++;
                } else {
                    i3++;
                }
            }
        }
        if (allStackTraces.size() > 1) {
            if (i == 0) {
                sb.append("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
            }
            sb.append("total JVM threads (exclude the crashed thread): ");
            sb.append(allStackTraces.size() - 1);
            sb.append("\n");
            if (arrayList != null) {
                sb.append("JVM threads matched whitelist: ");
                sb.append(i2);
                sb.append("\n");
            }
            if (this.dumpAllThreadsCountMax > 0) {
                sb.append("JVM threads ignored by max count limit: ");
                sb.append(i3);
                sb.append("\n");
            }
            sb.append("dumped JVM threads:");
            sb.append(i);
            sb.append("\n");
            sb.append("+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++\n");
        }
        return sb.toString();
    }

    private void handleException(Thread thread, Throwable th) throws Throwable {
        File fileCreateLogFile;
        String emergency;
        RandomAccessFile randomAccessFile;
        ICrashCallback iCrashCallback;
        Date date = new Date();
        NativeHandler.getInstance().notifyJavaCrashed();
        AnrHandler.getInstance().notifyJavaCrashed();
        RandomAccessFile randomAccessFile2 = null;
        String absolutePath = null;
        try {
            fileCreateLogFile = FileManager.getInstance().createLogFile(String.format(Locale.US, "%s/%s_%020d_%s__%s%s", this.logDir, "tombstone", Long.valueOf(this.startTime.getTime() * 1000), this.appVersion, this.processName, ".java.xcrash"));
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "JavaCrashHandler createLogFile failed", e);
            fileCreateLogFile = null;
        }
        try {
            emergency = getEmergency(date, thread, th);
        } catch (Exception e2) {
            XCrash.getLogger().e("xcrash", "JavaCrashHandler getEmergency failed", e2);
            emergency = null;
        }
        if (fileCreateLogFile != null) {
            try {
                randomAccessFile = new RandomAccessFile(fileCreateLogFile, "rws");
                if (emergency != null) {
                    try {
                        try {
                            randomAccessFile.write(emergency.getBytes("UTF-8"));
                        } catch (Exception e3) {
                            e = e3;
                            XCrash.getLogger().e("xcrash", "JavaCrashHandler write log file failed", e);
                            if (randomAccessFile != null) {
                            }
                            iCrashCallback = this.callback;
                            if (iCrashCallback != null) {
                            }
                        }
                        try {
                            if (this.logcatMainLines <= 0 || this.logcatSystemLines > 0 || this.logcatEventsLines > 0) {
                                randomAccessFile.write(Util.getLogcat(this.logcatMainLines, this.logcatSystemLines, this.logcatEventsLines).getBytes("UTF-8"));
                            }
                            if (this.dumpFds) {
                                randomAccessFile.write(Util.getFds().getBytes("UTF-8"));
                            }
                            if (this.dumpNetworkInfo) {
                                randomAccessFile.write(Util.getNetworkInfo().getBytes("UTF-8"));
                            }
                            randomAccessFile.write(Util.getMemoryInfo().getBytes("UTF-8"));
                            StringBuilder sb = new StringBuilder();
                            sb.append("foreground:\n");
                            sb.append(!ActivityMonitor.getInstance().isApplicationForeground() ? "yes" : "no");
                            sb.append("\n\n");
                            randomAccessFile.write(sb.toString().getBytes("UTF-8"));
                            if (this.dumpAllThreads) {
                                randomAccessFile.write(getOtherThreadsInfo(thread).getBytes("UTF-8"));
                            }
                            try {
                                randomAccessFile.close();
                            } catch (Exception unused) {
                            }
                            emergency = null;
                        } catch (Exception e4) {
                            e = e4;
                            emergency = null;
                            XCrash.getLogger().e("xcrash", "JavaCrashHandler write log file failed", e);
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (Exception unused2) {
                                }
                            }
                            iCrashCallback = this.callback;
                            if (iCrashCallback != null) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile2 = randomAccessFile;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Exception unused3) {
                            }
                        }
                        throw th;
                    }
                } else if (this.logcatMainLines <= 0) {
                    randomAccessFile.write(Util.getLogcat(this.logcatMainLines, this.logcatSystemLines, this.logcatEventsLines).getBytes("UTF-8"));
                    if (this.dumpFds) {
                    }
                    if (this.dumpNetworkInfo) {
                    }
                    randomAccessFile.write(Util.getMemoryInfo().getBytes("UTF-8"));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("foreground:\n");
                    sb2.append(!ActivityMonitor.getInstance().isApplicationForeground() ? "yes" : "no");
                    sb2.append("\n\n");
                    randomAccessFile.write(sb2.toString().getBytes("UTF-8"));
                    if (this.dumpAllThreads) {
                    }
                    randomAccessFile.close();
                    emergency = null;
                }
            } catch (Exception e5) {
                e = e5;
                randomAccessFile = null;
            } catch (Throwable th3) {
                th = th3;
                if (randomAccessFile2 != null) {
                }
                throw th;
            }
        }
        iCrashCallback = this.callback;
        if (iCrashCallback != null) {
            if (fileCreateLogFile != null) {
                try {
                    absolutePath = fileCreateLogFile.getAbsolutePath();
                } catch (Exception unused4) {
                    return;
                }
            }
            iCrashCallback.onCrash(absolutePath, emergency);
        }
    }

    private boolean matchThreadName(ArrayList<Pattern> arrayList, String str) {
        Iterator<Pattern> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    void initialize(int i, String str, String str2, String str3, String str4, boolean z, int i2, int i3, int i4, boolean z2, boolean z3, boolean z4, int i5, String[] strArr, ICrashCallback iCrashCallback) {
        this.pid = i;
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        this.processName = str;
        this.appId = str2;
        this.appVersion = str3;
        this.rethrow = z;
        this.logDir = str4;
        this.logcatSystemLines = i2;
        this.logcatEventsLines = i3;
        this.logcatMainLines = i4;
        this.dumpFds = z2;
        this.dumpNetworkInfo = z3;
        this.dumpAllThreads = z4;
        this.dumpAllThreadsCountMax = i5;
        this.dumpAllThreadsWhiteList = strArr;
        this.callback = iCrashCallback;
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        try {
            Thread.setDefaultUncaughtExceptionHandler(this);
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "JavaCrashHandler setDefaultUncaughtExceptionHandler failed", e);
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) throws Throwable {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultHandler;
        if (uncaughtExceptionHandler != null) {
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        try {
            handleException(thread, th);
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "JavaCrashHandler handleException failed", e);
        }
        if (!this.rethrow) {
            ActivityMonitor.getInstance().finishAllActivities();
            Process.killProcess(this.pid);
            System.exit(10);
        } else {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.defaultHandler;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
        }
    }
}

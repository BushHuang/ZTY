package xcrash;

import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AnrHandler {
    private static final AnrHandler instance = new AnrHandler();
    private String appId;
    private String appVersion;
    private ICrashCallback callback;
    private boolean checkProcessState;
    private Context ctx;
    private boolean dumpFds;
    private boolean dumpNetworkInfo;
    private String logDir;
    private int logcatEventsLines;
    private int logcatMainLines;
    private int logcatSystemLines;
    private int pid;
    private String processName;
    private final Date startTime = new Date();
    private final Pattern patPidTime = Pattern.compile("^-----\\spid\\s(\\d+)\\sat\\s(.*)\\s-----$");
    private final Pattern patProcessName = Pattern.compile("^Cmd\\sline:\\s+(.*)$");
    private final long anrTimeoutMs = 15000;
    private long lastTime = 0;
    private FileObserver fileObserver = null;

    private AnrHandler() {
    }

    private String getEmergency(Date date, String str) {
        return Util.getLogHeader(this.startTime, date, "anr", this.appId, this.appVersion) + "pid: " + this.pid + "  >>> " + this.processName + " <<<\n\n--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n" + str + "\n+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++\n\n";
    }

    static AnrHandler getInstance() {
        return instance;
    }

    private String getTrace(String str, long j) throws Throwable {
        BufferedReader bufferedReader;
        Date date;
        String strGroup;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
            boolean z = false;
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (!z && line.startsWith("----- pid ")) {
                        Matcher matcher = this.patPidTime.matcher(line);
                        if (matcher.find() && matcher.groupCount() == 2) {
                            String strGroup2 = matcher.group(1);
                            String strGroup3 = matcher.group(2);
                            if (strGroup2 != null && strGroup3 != null && this.pid == Integer.parseInt(strGroup2) && (date = simpleDateFormat.parse(strGroup3)) != null && Math.abs(date.getTime() - j) <= 15000) {
                                String line2 = bufferedReader.readLine();
                                if (line2 == null) {
                                    break;
                                }
                                Matcher matcher2 = this.patProcessName.matcher(line2);
                                if (matcher2.find() && matcher2.groupCount() == 1 && (strGroup = matcher2.group(1)) != null && strGroup.equals(this.processName)) {
                                    sb.append(line2);
                                    sb.append('\n');
                                    sb.append("Mode: Watching /data/anr/*\n");
                                    z = true;
                                }
                            }
                        }
                    } else if (!z) {
                        continue;
                    } else {
                        if (line.startsWith("----- end ")) {
                            break;
                        }
                        sb.append(line);
                        sb.append('\n');
                    }
                } catch (Exception unused) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused2) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            }
            String string = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception unused4) {
            }
            return string;
        } catch (Exception unused5) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void handleAnr(String str) throws Throwable {
        String emergency;
        File fileCreateLogFile;
        RandomAccessFile randomAccessFile;
        ICrashCallback iCrashCallback;
        Date date = new Date();
        if (date.getTime() - this.lastTime < 15000) {
            return;
        }
        if (!this.checkProcessState || Util.checkProcessAnrState(this.ctx, 15000L)) {
            String trace = getTrace(str, date.getTime());
            if (TextUtils.isEmpty(trace)) {
                return;
            }
            this.lastTime = date.getTime();
            if (FileManager.getInstance().maintainAnr()) {
                RandomAccessFile randomAccessFile2 = null;
                String absolutePath = null;
                try {
                    emergency = getEmergency(date, trace);
                } catch (Exception e) {
                    XCrash.getLogger().e("xcrash", "AnrHandler getEmergency failed", e);
                    emergency = null;
                }
                try {
                    fileCreateLogFile = FileManager.getInstance().createLogFile(String.format(Locale.US, "%s/%s_%020d_%s__%s%s", this.logDir, "tombstone", Long.valueOf(date.getTime() * 1000), this.appVersion, this.processName, ".anr.xcrash"));
                } catch (Exception e2) {
                    XCrash.getLogger().e("xcrash", "AnrHandler createLogFile failed", e2);
                    fileCreateLogFile = null;
                }
                if (fileCreateLogFile != null) {
                    try {
                        randomAccessFile = new RandomAccessFile(fileCreateLogFile, "rws");
                        if (emergency != null) {
                            try {
                                try {
                                    randomAccessFile.write(emergency.getBytes("UTF-8"));
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
                                        try {
                                            randomAccessFile.close();
                                        } catch (Exception unused) {
                                        }
                                        emergency = null;
                                    } catch (Exception e3) {
                                        e = e3;
                                        emergency = null;
                                        XCrash.getLogger().e("xcrash", "AnrHandler write log file failed", e);
                                        if (randomAccessFile != null) {
                                        }
                                        iCrashCallback = this.callback;
                                        if (iCrashCallback != null) {
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    randomAccessFile2 = randomAccessFile;
                                    if (randomAccessFile2 != null) {
                                        try {
                                            randomAccessFile2.close();
                                        } catch (Exception unused2) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Exception e4) {
                                e = e4;
                                XCrash.getLogger().e("xcrash", "AnrHandler write log file failed", e);
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Exception unused3) {
                                    }
                                }
                                iCrashCallback = this.callback;
                                if (iCrashCallback != null) {
                                }
                            }
                        } else if (this.logcatMainLines <= 0) {
                            randomAccessFile.write(Util.getLogcat(this.logcatMainLines, this.logcatSystemLines, this.logcatEventsLines).getBytes("UTF-8"));
                            if (this.dumpFds) {
                            }
                            if (this.dumpNetworkInfo) {
                            }
                            randomAccessFile.write(Util.getMemoryInfo().getBytes("UTF-8"));
                            randomAccessFile.close();
                            emergency = null;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        randomAccessFile = null;
                    } catch (Throwable th2) {
                        th = th2;
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
        }
    }

    void initialize(Context context, int i, String str, String str2, String str3, String str4, boolean z, int i2, int i3, int i4, boolean z2, boolean z3, ICrashCallback iCrashCallback) {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        this.ctx = context;
        this.pid = i;
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        this.processName = str;
        this.appId = str2;
        this.appVersion = str3;
        this.logDir = str4;
        this.checkProcessState = z;
        this.logcatSystemLines = i2;
        this.logcatEventsLines = i3;
        this.logcatMainLines = i4;
        this.dumpFds = z2;
        this.dumpNetworkInfo = z3;
        this.callback = iCrashCallback;
        FileObserver fileObserver = new FileObserver("/data/anr/", 8) {
            @Override
            public void onEvent(int i5, String str5) throws Throwable {
                if (str5 != null) {
                    try {
                        String str6 = "/data/anr/" + str5;
                        if (str6.contains("trace")) {
                            AnrHandler.this.handleAnr(str6);
                        }
                    } catch (Exception e) {
                        XCrash.getLogger().e("xcrash", "AnrHandler fileObserver onEvent failed", e);
                    }
                }
            }
        };
        this.fileObserver = fileObserver;
        try {
            fileObserver.startWatching();
        } catch (Exception e) {
            this.fileObserver = null;
            XCrash.getLogger().e("xcrash", "AnrHandler fileObserver startWatching failed", e);
        }
    }

    void notifyJavaCrashed() {
        FileObserver fileObserver = this.fileObserver;
        if (fileObserver != null) {
            try {
                try {
                    fileObserver.stopWatching();
                } catch (Exception e) {
                    XCrash.getLogger().e("xcrash", "AnrHandler fileObserver stopWatching failed", e);
                }
            } finally {
                this.fileObserver = null;
            }
        }
    }
}

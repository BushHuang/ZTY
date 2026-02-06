package xcrash;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.system.Os;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class Util {
    static final String TAG = "xcrash";
    static final String anrCrashType = "anr";
    static final String anrLogSuffix = ".anr.xcrash";
    static final String javaCrashType = "java";
    static final String javaLogSuffix = ".java.xcrash";
    static final String logPrefix = "tombstone";
    private static final String memInfoFmt = "%21s %8s\n";
    private static final String memInfoFmt2 = "%21s %8s %21s %8s\n";
    static final String nativeCrashType = "native";
    static final String nativeLogSuffix = ".native.xcrash";
    static final String sepHead = "*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***";
    static final String sepOtherThreads = "--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---";
    static final String sepOtherThreadsEnding = "+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++";
    private static final String[] suPathname = {"/data/local/su", "/data/local/bin/su", "/data/local/xbin/su", "/system/xbin/su", "/system/bin/su", "/system/bin/.ext/su", "/system/bin/failsafe/su", "/system/sd/xbin/su", "/system/usr/we-need-root/su", "/sbin/su", "/su/bin/su"};
    static final String timeFormatterStr = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    static final String traceLogSuffix = ".trace.xcrash";

    private Util() {
    }

    static boolean checkAndCreateDir(String str) {
        File file = new File(str);
        try {
            if (file.exists()) {
                return file.isDirectory();
            }
            file.mkdirs();
            return file.exists() && file.isDirectory();
        } catch (Exception unused) {
            return false;
        }
    }

    static boolean checkProcessAnrState(Context context, long j) throws InterruptedException {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        int iMyPid = Process.myPid();
        long j2 = j / 500;
        for (int i = 0; i < j2; i++) {
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.pid == iMyPid && processErrorStateInfo.condition == 2) {
                        return true;
                    }
                }
            }
            try {
                Thread.sleep(500L);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    static String getAbiList() {
        if (Build.VERSION.SDK_INT >= 21) {
            return TextUtils.join(",", Build.SUPPORTED_ABIS);
        }
        String str = Build.CPU_ABI;
        String str2 = Build.CPU_ABI2;
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str + "," + str2;
    }

    static String getAppVersion(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            str = null;
        }
        return TextUtils.isEmpty(str) ? "unknown" : str;
    }

    static String getFds() {
        StringBuilder sb = new StringBuilder("open files:\n");
        try {
            File[] fileArrListFiles = new File("/proc/self/fd").listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String str) {
                    return TextUtils.isDigitsOnly(str);
                }
            });
            if (fileArrListFiles != null) {
                int i = 0;
                for (File file : fileArrListFiles) {
                    String canonicalPath = null;
                    try {
                        canonicalPath = Build.VERSION.SDK_INT >= 21 ? Os.readlink(file.getAbsolutePath()) : file.getCanonicalPath();
                    } catch (Exception unused) {
                    }
                    sb.append("    fd ");
                    sb.append(file.getName());
                    sb.append(": ");
                    sb.append(TextUtils.isEmpty(canonicalPath) ? "???" : canonicalPath.trim());
                    sb.append('\n');
                    i++;
                    if (i > 1024) {
                        break;
                    }
                }
                if (fileArrListFiles.length > 1024) {
                    sb.append("    ......\n");
                }
                sb.append("    (number of FDs: ");
                sb.append(fileArrListFiles.length);
                sb.append(")\n");
            }
        } catch (Exception unused2) {
        }
        sb.append('\n');
        return sb.toString();
    }

    private static String getFileContent(String str) {
        return getFileContent(str, 0);
    }

    private static String getFileContent(String str, int i) throws Throwable {
        StringBuilder sb = new StringBuilder();
        ?? r1 = 0;
        r1 = 0;
        try {
            try {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
                    int i2 = 0;
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            String strTrim = line.trim();
                            if (strTrim.length() > 0) {
                                i2++;
                                if (i == 0 || i2 <= i) {
                                    sb.append("  ");
                                    sb.append(strTrim);
                                    sb.append("\n");
                                }
                            }
                        } catch (Exception e) {
                            e = e;
                            r1 = bufferedReader;
                            XCrash.getLogger().i("xcrash", "Util getInfo(" + str + ") failed", e);
                            if (r1 != 0) {
                                r1.close();
                                r1 = r1;
                            }
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            r1 = bufferedReader;
                            if (r1 != 0) {
                                try {
                                    r1.close();
                                } catch (Exception unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    if (i > 0 && i2 > i) {
                        sb.append("  ......\n");
                        sb.append("  (number of records: ");
                        sb.append(i2);
                        sb.append(")\n");
                    }
                    bufferedReader.close();
                    r1 = i2;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception unused2) {
            }
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static String getLogHeader(Date date, Date date2, String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        StringBuilder sb = new StringBuilder();
        sb.append("*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***\nTombstone maker: 'xCrash 2.4.9'\nCrash type: '");
        sb.append(str);
        sb.append("'\nStart time: '");
        sb.append(simpleDateFormat.format(date));
        sb.append("'\nCrash time: '");
        sb.append(simpleDateFormat.format(date2));
        sb.append("'\nApp ID: '");
        sb.append(str2);
        sb.append("'\nApp version: '");
        sb.append(str3);
        sb.append("'\nRooted: '");
        sb.append(isRoot() ? "Yes" : "No");
        sb.append("'\nAPI level: '");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("'\nOS version: '");
        sb.append(Build.VERSION.RELEASE);
        sb.append("'\nABI list: '");
        sb.append(getAbiList());
        sb.append("'\nManufacturer: '");
        sb.append(Build.MANUFACTURER);
        sb.append("'\nBrand: '");
        sb.append(Build.BRAND);
        sb.append("'\nModel: '");
        sb.append(Build.MODEL);
        sb.append("'\nBuild fingerprint: '");
        sb.append(Build.FINGERPRINT);
        sb.append("'\n");
        return sb.toString();
    }

    static String getLogcat(int i, int i2, int i3) throws Throwable {
        int iMyPid = Process.myPid();
        StringBuilder sb = new StringBuilder();
        sb.append("logcat:\n");
        if (i > 0) {
            getLogcatByBufferName(iMyPid, sb, "main", i, 'D');
        }
        if (i2 > 0) {
            getLogcatByBufferName(iMyPid, sb, "system", i2, 'W');
        }
        if (i3 > 0) {
            getLogcatByBufferName(iMyPid, sb, "events", i2, 'I');
        }
        sb.append("\n");
        return sb.toString();
    }

    private static void getLogcatByBufferName(int i, StringBuilder sb, String str, int i2, char c) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        Exception e;
        boolean z = Build.VERSION.SDK_INT >= 24;
        String string = Integer.toString(i);
        String str2 = " " + string + " ";
        ArrayList arrayList = new ArrayList();
        arrayList.add("/system/bin/logcat");
        arrayList.add("-b");
        arrayList.add(str);
        arrayList.add("-d");
        arrayList.add("-v");
        arrayList.add("threadtime");
        arrayList.add("-t");
        if (!z) {
            double d = i2;
            Double.isNaN(d);
            i2 = (int) (d * 1.2d);
        }
        arrayList.add(Integer.toString(i2));
        if (z) {
            arrayList.add("--pid");
            arrayList.add(string);
        }
        arrayList.add("*:" + c);
        Object[] array = arrayList.toArray();
        ?? r12 = "--------- tail end of log ";
        sb.append("--------- tail end of log ");
        sb.append(str);
        sb.append(" (");
        sb.append(TextUtils.join(" ", array));
        sb.append(")\n");
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new ProcessBuilder(new String[0]).command(arrayList).start().getInputStream()));
            } catch (Exception e2) {
                bufferedReader = null;
                e = e2;
            } catch (Throwable th2) {
                r12 = 0;
                th = th2;
                if (r12 != 0) {
                }
                throw th;
            }
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        if (z || line.contains(str2)) {
                            sb.append(line);
                            sb.append("\n");
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    XCrash.getLogger().w("xcrash", "Util run logcat command failed", e);
                    if (bufferedReader == null) {
                        return;
                    }
                    bufferedReader.close();
                    return;
                }
                try {
                    bufferedReader.close();
                    return;
                } catch (IOException unused) {
                    return;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (r12 != 0) {
                try {
                    r12.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    static String getMemoryInfo() {
        return "memory info:\n System Summary (From: /proc/meminfo)\n" + getFileContent("/proc/meminfo") + "-\n Process Status (From: /proc/PID/status)\n" + getFileContent("/proc/self/status") + "-\n Process Limits (From: /proc/PID/limits)\n" + getFileContent("/proc/self/limits") + "-\n" + getProcessMemoryInfo() + "\n";
    }

    static String getNetworkInfo() {
        if (Build.VERSION.SDK_INT >= 29) {
            return "network info:\nNot supported on Android Q (API level 29) and later.\n\n";
        }
        return "network info:\n TCP over IPv4 (From: /proc/PID/net/tcp)\n" + getFileContent("/proc/self/net/tcp", 1024) + "-\n TCP over IPv6 (From: /proc/PID/net/tcp6)\n" + getFileContent("/proc/self/net/tcp6", 1024) + "-\n UDP over IPv4 (From: /proc/PID/net/udp)\n" + getFileContent("/proc/self/net/udp", 1024) + "-\n UDP over IPv6 (From: /proc/PID/net/udp6)\n" + getFileContent("/proc/self/net/udp6", 1024) + "-\n ICMP in IPv4 (From: /proc/PID/net/icmp)\n" + getFileContent("/proc/self/net/icmp", 256) + "-\n ICMP in IPv6 (From: /proc/PID/net/icmp6)\n" + getFileContent("/proc/self/net/icmp6", 256) + "-\n UNIX domain (From: /proc/PID/net/unix)\n" + getFileContent("/proc/self/net/unix", 256) + "\n";
    }

    static String getProcessMemoryInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Process Summary (From: android.os.Debug.MemoryInfo)\n");
        sb.append(String.format(Locale.US, "%21s %8s\n", "", "Pss(KB)"));
        sb.append(String.format(Locale.US, "%21s %8s\n", "", "------"));
        try {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            if (Build.VERSION.SDK_INT >= 23) {
                sb.append(String.format(Locale.US, "%21s %8s\n", "Java Heap:", memoryInfo.getMemoryStat("summary.java-heap")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Native Heap:", memoryInfo.getMemoryStat("summary.native-heap")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Code:", memoryInfo.getMemoryStat("summary.code")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Stack:", memoryInfo.getMemoryStat("summary.stack")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Graphics:", memoryInfo.getMemoryStat("summary.graphics")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Private Other:", memoryInfo.getMemoryStat("summary.private-other")));
                sb.append(String.format(Locale.US, "%21s %8s\n", "System:", memoryInfo.getMemoryStat("summary.system")));
                sb.append(String.format(Locale.US, "%21s %8s %21s %8s\n", "TOTAL:", memoryInfo.getMemoryStat("summary.total-pss"), "TOTAL SWAP:", memoryInfo.getMemoryStat("summary.total-swap")));
            } else {
                sb.append(String.format(Locale.US, "%21s %8s\n", "Java Heap:", "~ " + memoryInfo.dalvikPrivateDirty));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Native Heap:", String.valueOf(memoryInfo.nativePrivateDirty)));
                sb.append(String.format(Locale.US, "%21s %8s\n", "Private Other:", "~ " + memoryInfo.otherPrivateDirty));
                if (Build.VERSION.SDK_INT >= 19) {
                    sb.append(String.format(Locale.US, "%21s %8s\n", "System:", String.valueOf((memoryInfo.getTotalPss() - memoryInfo.getTotalPrivateDirty()) - memoryInfo.getTotalPrivateClean())));
                } else {
                    sb.append(String.format(Locale.US, "%21s %8s\n", "System:", "~ " + (memoryInfo.getTotalPss() - memoryInfo.getTotalPrivateDirty())));
                }
                sb.append(String.format(Locale.US, "%21s %8s\n", "TOTAL:", String.valueOf(memoryInfo.getTotalPss())));
            }
        } catch (Exception e) {
            XCrash.getLogger().i("xcrash", "Util getProcessMemoryInfo failed", e);
        }
        return sb.toString();
    }

    static String getProcessName(Context context, int i) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == i && !TextUtils.isEmpty(runningAppProcessInfo.processName)) {
                        return runningAppProcessInfo.processName;
                    }
                }
            }
        } catch (Exception unused) {
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
        } catch (Exception unused2) {
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            String line = bufferedReader.readLine();
            if (!TextUtils.isEmpty(line)) {
                String strTrim = line.trim();
                if (!TextUtils.isEmpty(strTrim)) {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused3) {
                    }
                    return strTrim;
                }
            }
        } catch (Exception unused4) {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused5) {
                }
            }
            throw th;
        }
        try {
            bufferedReader.close();
        } catch (Exception unused6) {
        }
        return null;
    }

    static boolean isRoot() {
        try {
            for (String str : suPathname) {
                if (new File(str).exists()) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}

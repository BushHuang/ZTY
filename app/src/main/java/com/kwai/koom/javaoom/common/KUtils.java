package com.kwai.koom.javaoom.common;

import android.app.ActivityManager;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.kwai.koom.javaoom.common.KConstants;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class KUtils {
    static final boolean $assertionsDisabled = false;
    private static String sProcessName;
    private static long startupTime;

    public static class ProcessStatus {
        public long javaHeapByteSize;
        public long pssKbSize;
        public long rssKbSize;
        public int threadsCount;
        public long totalByteSize;
        public long vssKbSize;
    }

    public static void closeQuietly(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int computeGenerations(Class<?> cls) {
        int i = 1;
        while (cls != null && cls.getSuperclass() != Object.class) {
            cls = cls.getSuperclass();
            i++;
        }
        return i;
    }

    public static ProcessStatus getProcessMemoryUsage() throws Throwable {
        RandomAccessFile randomAccessFile;
        IOException e;
        Closeable closeable;
        ProcessStatus processStatus = new ProcessStatus();
        Closeable closeable2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile("/proc/self/status", "r");
                while (true) {
                    try {
                        String line = randomAccessFile.readLine();
                        if (line == null) {
                            break;
                        }
                        if (!TextUtils.isEmpty(line)) {
                            if (line.startsWith("VmSize") && line.contains("kB")) {
                                String[] strArrSplit = line.split("\\s+");
                                if (strArrSplit.length > 1) {
                                    processStatus.vssKbSize = Long.parseLong(strArrSplit[1]);
                                }
                            } else if (line.startsWith("VmRSS:") && line.contains("kB")) {
                                String[] strArrSplit2 = line.split("\\s+");
                                if (strArrSplit2.length > 1) {
                                    processStatus.rssKbSize = Long.parseLong(strArrSplit2[1]);
                                }
                            } else if (line.startsWith("Threads:")) {
                                String[] strArrSplit3 = line.split("\\s+");
                                if (strArrSplit3.length > 1) {
                                    processStatus.threadsCount = Integer.parseInt(strArrSplit3[1]);
                                }
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                        closeQuietly(randomAccessFile);
                        return processStatus;
                    }
                }
            } catch (IOException e3) {
                randomAccessFile = null;
                e = e3;
            } catch (Throwable th) {
                th = th;
                closeQuietly(closeable2);
                throw th;
            }
            closeQuietly(randomAccessFile);
            return processStatus;
        } catch (Throwable th2) {
            th = th2;
            closeable2 = closeable;
            closeQuietly(closeable2);
            throw th;
        }
    }

    public static String getProcessName() throws IOException {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (!TextUtils.isEmpty(sProcessName)) {
            return sProcessName;
        }
        try {
            int iMyPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) KGlobalConfig.getApplication().getSystemService("activity");
            if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
                Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ActivityManager.RunningAppProcessInfo next = it.next();
                    if (next.pid == iMyPid) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(sProcessName)) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/self/cmdline")));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        int i = bufferedReader.read();
                        if (i <= 0) {
                            break;
                        }
                        sb.append((char) i);
                    }
                    sProcessName = sb.toString();
                    bufferedReader.close();
                } finally {
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return sProcessName;
    }

    public static float getSpaceInGB(String str) {
        StatFs statFs = new StatFs(str);
        return ((statFs.getBlockSizeLong() * 1.0f) * statFs.getAvailableBlocks()) / KConstants.Bytes.GB;
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.CHINESE).format(new Date());
    }

    public static void startup() {
        startupTime = System.currentTimeMillis();
    }

    public static int usageSeconds() {
        return (int) ((System.currentTimeMillis() - startupTime) / 1000);
    }
}

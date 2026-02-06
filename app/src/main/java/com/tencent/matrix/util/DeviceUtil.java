package com.tencent.matrix.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceUtil {
    private static final String CPU_FILE_PATH_0 = "/sys/devices/system/cpu/";
    private static final String CPU_FILE_PATH_1 = "/sys/devices/system/cpu/possible";
    private static final String CPU_FILE_PATH_2 = "/sys/devices/system/cpu/present";
    private static final FileFilter CPU_FILTER = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    };
    private static final String DEVICE_CPU = "cpu_app";
    public static final String DEVICE_MACHINE = "machine";
    private static final String DEVICE_MEMORY = "mem";
    private static final String DEVICE_MEMORY_FREE = "mem_free";
    private static final int INVALID = 0;
    private static final long MB = 1048576;
    private static final String MEMORY_FILE_PATH = "/proc/meminfo";
    private static final String TAG = "Matrix.DeviceUtil";
    private static LEVEL sLevelCache;
    private static long sLowMemoryThresold;
    private static int sMemoryClass;
    private static long sTotalMemory;

    public enum LEVEL {
        BEST(5),
        HIGH(4),
        MIDDLE(3),
        LOW(2),
        BAD(1),
        UN_KNOW(-1);

        int value;

        LEVEL(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static String convertStreamToString(InputStream inputStream) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        bufferedReader2.close();
                        return sb.toString();
                    }
                    sb.append(line);
                    sb.append('\n');
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static double getAppCpuRate() throws Throwable {
        Throwable th;
        RandomAccessFile randomAccessFile;
        String str;
        long j;
        double d;
        ?? randomAccessFile2 = "close process reader %s";
        long jCurrentTimeMillis = System.currentTimeMillis();
        RandomAccessFile randomAccessFile3 = 0;
        randomAccessFile = null;
        RandomAccessFile randomAccessFile4 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile("/proc/stat", "r");
            } catch (Exception e) {
                e = e;
                randomAccessFile = null;
            } catch (Throwable th2) {
                th = th2;
                str = randomAccessFile2;
                if (randomAccessFile3 != 0) {
                }
            }
            try {
                try {
                    String[] strArrSplit = randomAccessFile.readLine().split(" ");
                    j = Long.parseLong(strArrSplit[2]) + Long.parseLong(strArrSplit[3]) + Long.parseLong(strArrSplit[4]) + Long.parseLong(strArrSplit[5]) + Long.parseLong(strArrSplit[6]) + Long.parseLong(strArrSplit[7]) + Long.parseLong(strArrSplit[8]);
                    try {
                        randomAccessFile.close();
                    } catch (Exception e2) {
                        MatrixLog.i("Matrix.DeviceUtil", "close process reader %s", e2.toString());
                    }
                } catch (Exception e3) {
                    e = e3;
                    MatrixLog.i("Matrix.DeviceUtil", "RandomAccessFile(Process Stat) reader fail, error: %s", e.toString());
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception e4) {
                            MatrixLog.i("Matrix.DeviceUtil", "close process reader %s", e4.toString());
                        }
                    }
                    j = 0;
                    randomAccessFile2 = new RandomAccessFile("/proc/" + getAppId() + "/stat", "r");
                    String[] strArrSplit2 = randomAccessFile2.readLine().split(" ");
                    long j2 = Long.parseLong(strArrSplit2[13]) + Long.parseLong(strArrSplit2[14]);
                    randomAccessFile2.close();
                    if (0 != j) {
                    }
                    MatrixLog.i("Matrix.DeviceUtil", "getAppCpuRate cost:" + (System.currentTimeMillis() - jCurrentTimeMillis) + ",rate:" + d, new Object[0]);
                    return d;
                }
                randomAccessFile2 = new RandomAccessFile("/proc/" + getAppId() + "/stat", "r");
                String[] strArrSplit22 = randomAccessFile2.readLine().split(" ");
                long j22 = Long.parseLong(strArrSplit22[13]) + Long.parseLong(strArrSplit22[14]);
                randomAccessFile2.close();
                if (0 != j) {
                    double d2 = j22;
                    double d3 = j;
                    Double.isNaN(d2);
                    Double.isNaN(d3);
                    d = (d2 / d3) * 100.0d;
                } else {
                    d = 0.0d;
                }
                MatrixLog.i("Matrix.DeviceUtil", "getAppCpuRate cost:" + (System.currentTimeMillis() - jCurrentTimeMillis) + ",rate:" + d, new Object[0]);
                return d;
            } catch (Throwable th3) {
                Throwable th4 = th3;
            }
        } catch (Throwable th5) {
            th = th5;
            randomAccessFile3 = "/stat";
            str = randomAccessFile2;
            if (randomAccessFile3 != 0) {
                throw th;
            }
            try {
                randomAccessFile3.close();
                throw th;
            } catch (Exception e5) {
                MatrixLog.i("Matrix.DeviceUtil", str, e5.toString());
                throw th;
            }
        }
    }

    private static int getAppId() {
        return Process.myPid();
    }

    public static Debug.MemoryInfo getAppMemory(Context context) {
        try {
            Debug.MemoryInfo[] processMemoryInfo = ((ActivityManager) context.getSystemService("activity")).getProcessMemoryInfo(new int[]{getAppId()});
            if (processMemoryInfo.length > 0) {
                return processMemoryInfo[0];
            }
            return null;
        } catch (Exception e) {
            MatrixLog.i("Matrix.DeviceUtil", "getProcessMemoryInfo fail, error: %s", e.toString());
            return null;
        }
    }

    public static long getAvailMemory(Context context) {
        return Runtime.getRuntime().freeMemory() / 1024;
    }

    private static int getCoresFromCPUFiles(String str) {
        File[] fileArrListFiles = new File(str).listFiles(CPU_FILTER);
        if (fileArrListFiles == null) {
            return 0;
        }
        return fileArrListFiles.length;
    }

    private static int getCoresFromFile(String str) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream2, "UTF-8"));
                    String line = bufferedReader.readLine();
                    bufferedReader.close();
                    if (line != null && line.matches("0-[\\d]+$")) {
                        int i = Integer.parseInt(line.substring(2)) + 1;
                        try {
                            fileInputStream2.close();
                        } catch (IOException e) {
                            MatrixLog.i("Matrix.DeviceUtil", "[getCoresFromFile] error! %s", e.toString());
                        }
                        return i;
                    }
                    try {
                        fileInputStream2.close();
                    } catch (IOException e2) {
                        MatrixLog.i("Matrix.DeviceUtil", "[getCoresFromFile] error! %s", e2.toString());
                    }
                    return 0;
                } catch (IOException e3) {
                    e = e3;
                    fileInputStream = fileInputStream2;
                    MatrixLog.i("Matrix.DeviceUtil", "[getCoresFromFile] error! %s", e.toString());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            MatrixLog.i("Matrix.DeviceUtil", "[getCoresFromFile] error! %s", e4.toString());
                        }
                    }
                    return 0;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            MatrixLog.i("Matrix.DeviceUtil", "[getCoresFromFile] error! %s", e5.toString());
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static long getDalvikHeap() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / 1024;
    }

    public static void getDeviceInfo(JSONObject jSONObject, Application application) throws JSONException {
        try {
            jSONObject.put("machine", getLevel(application));
            jSONObject.put("cpu_app", getAppCpuRate());
            jSONObject.put("mem", getTotalMemory(application));
            jSONObject.put("mem_free", getMemFree(application));
        } catch (JSONException e) {
            MatrixLog.e("Matrix.DeviceUtil", "[JSONException for stack, error: %s", e);
        }
    }

    public static LEVEL getLevel(Context context) throws Throwable {
        LEVEL level = sLevelCache;
        if (level != null) {
            return level;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long totalMemory = getTotalMemory(context);
        int numOfCores = getNumOfCores();
        MatrixLog.i("Matrix.DeviceUtil", "[getLevel] totalMemory:%s coresNum:%s", Long.valueOf(totalMemory), Integer.valueOf(numOfCores));
        if (totalMemory >= 4294967296L) {
            sLevelCache = LEVEL.BEST;
        } else if (totalMemory >= 3221225472L) {
            sLevelCache = LEVEL.HIGH;
        } else if (totalMemory >= 2147483648L) {
            if (numOfCores >= 4) {
                sLevelCache = LEVEL.HIGH;
            } else if (numOfCores >= 2) {
                sLevelCache = LEVEL.MIDDLE;
            } else if (numOfCores > 0) {
                sLevelCache = LEVEL.LOW;
            }
        } else if (totalMemory >= 1073741824) {
            if (numOfCores >= 4) {
                sLevelCache = LEVEL.MIDDLE;
            } else if (numOfCores >= 2 || numOfCores > 0) {
                sLevelCache = LEVEL.LOW;
            }
        } else if (0 > totalMemory || totalMemory >= 1073741824) {
            sLevelCache = LEVEL.UN_KNOW;
        } else {
            sLevelCache = LEVEL.BAD;
        }
        MatrixLog.i("Matrix.DeviceUtil", "getLevel, cost:" + (System.currentTimeMillis() - jCurrentTimeMillis) + ", level:" + sLevelCache, new Object[0]);
        return sLevelCache;
    }

    public static long getLowMemoryThresold(Context context) {
        long j = sLowMemoryThresold;
        if (0 != j) {
            return j;
        }
        getTotalMemory(context);
        return sLowMemoryThresold;
    }

    public static long getMemFree(Context context) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        Exception e;
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.availMem / 1024;
        }
        long j = 0;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/meminfo"), "UTF-8"));
        } catch (Exception e2) {
            bufferedReader = null;
            e = e2;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
            if (bufferedReader != null) {
            }
            throw th;
        }
        try {
            try {
                String line = bufferedReader.readLine();
                while (true) {
                    if (line != null) {
                        if ("MemAvailable:".equals(line.split("\\s+")[0])) {
                            break;
                        }
                        line = bufferedReader.readLine();
                    }
                }
                try {
                    bufferedReader.close();
                    break;
                } catch (Exception e3) {
                    MatrixLog.i("Matrix.DeviceUtil", "close reader %s", e3.toString());
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e4) {
                        MatrixLog.i("Matrix.DeviceUtil", "close reader %s", e4.toString());
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            MatrixLog.i("Matrix.DeviceUtil", "[getAvailMemory] error! %s", e.toString());
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e6) {
                    MatrixLog.i("Matrix.DeviceUtil", "close reader %s", e6.toString());
                }
            }
            return j / 1024;
        }
        return j / 1024;
    }

    public static int getMemoryClass(Context context) {
        int i = sMemoryClass;
        if (i != 0) {
            return i * 1024;
        }
        getTotalMemory(context);
        return sMemoryClass * 1024;
    }

    public static long getNativeHeap() {
        return Debug.getNativeHeapAllocatedSize() / 1024;
    }

    private static int getNumOfCores() throws Throwable {
        int coresFromFile;
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            coresFromFile = getCoresFromFile("/sys/devices/system/cpu/possible");
            if (coresFromFile == 0) {
                coresFromFile = getCoresFromFile("/sys/devices/system/cpu/present");
            }
            if (coresFromFile == 0) {
                coresFromFile = getCoresFromCPUFiles("/sys/devices/system/cpu/");
            }
        } catch (Exception unused) {
            coresFromFile = 0;
        }
        if (coresFromFile == 0) {
            return 1;
        }
        return coresFromFile;
    }

    public static String getStringFromFile(String str) throws Exception {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(new File(str));
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
        }
        try {
            String strConvertStreamToString = convertStreamToString(fileInputStream);
            fileInputStream.close();
            return strConvertStreamToString;
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static long getTotalMemory(Context context) {
        long j = sTotalMemory;
        if (0 != j) {
            return j;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Build.VERSION.SDK_INT < 16) {
            return 0L;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        activityManager.getMemoryInfo(memoryInfo);
        sTotalMemory = memoryInfo.totalMem;
        sLowMemoryThresold = memoryInfo.threshold;
        long jMaxMemory = Runtime.getRuntime().maxMemory();
        if (jMaxMemory == Long.MAX_VALUE) {
            sMemoryClass = activityManager.getMemoryClass();
        } else {
            sMemoryClass = (int) (jMaxMemory / 1048576);
        }
        MatrixLog.i("Matrix.DeviceUtil", "getTotalMemory cost:" + (System.currentTimeMillis() - jCurrentTimeMillis) + ", total_mem:" + sTotalMemory + ", LowMemoryThresold:" + sLowMemoryThresold + ", Memory Class:" + sMemoryClass, new Object[0]);
        return sTotalMemory;
    }

    public static long getVmSize() {
        try {
            String[] strArrSplit = getStringFromFile(String.format("/proc/%s/status", Integer.valueOf(getAppId()))).trim().split("\n");
            for (String str : strArrSplit) {
                if (str.startsWith("VmSize")) {
                    Matcher matcher = Pattern.compile("\\d+").matcher(str);
                    if (matcher.find()) {
                        return Long.parseLong(matcher.group());
                    }
                }
            }
            if (strArrSplit.length > 12) {
                Matcher matcher2 = Pattern.compile("\\d+").matcher(strArrSplit[12]);
                if (matcher2.find()) {
                    return Long.parseLong(matcher2.group());
                }
            }
        } catch (Exception unused) {
        }
        return -1L;
    }

    public static boolean isLowMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }
}

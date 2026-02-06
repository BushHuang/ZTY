package com.tencent.matrix.trace.util;

import com.tencent.matrix.util.DeviceUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String calculateCpuUsage(long j, long j2) {
        if (j <= 0) {
            return j2 > 1000 ? "0%" : "100%";
        }
        if (j >= j2) {
            return "100%";
        }
        return String.format("%.2f", Float.valueOf(((j * 1.0f) / j2) * 100.0f)) + "%";
    }

    public static String formatTime(long j) {
        return new SimpleDateFormat("[yy-MM-dd HH:mm:ss]").format(new Date(j));
    }

    public static int[] getProcessPriority(int i) throws NumberFormatException {
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        try {
            String[] strArrSplit = DeviceUtil.getStringFromFile(String.format("/proc/%s/stat", Integer.valueOf(i))).trim().split(" ");
            if (strArrSplit.length >= 19) {
                i2 = Integer.parseInt(strArrSplit[17].trim());
                i3 = Integer.parseInt(strArrSplit[18].trim());
            }
            return new int[]{i2, i3};
        } catch (Exception unused) {
            return new int[]{i2, Integer.MAX_VALUE};
        }
    }

    public static String getStack() {
        return getStack(new Throwable().getStackTrace());
    }

    public static String getStack(StackTraceElement[] stackTraceElementArr) {
        return getStack(stackTraceElementArr, "", -1);
    }

    public static String getStack(StackTraceElement[] stackTraceElementArr, String str, int i) {
        if (stackTraceElementArr == null || stackTraceElementArr.length < 3) {
            return "";
        }
        if (i < 0) {
            i = Integer.MAX_VALUE;
        }
        StringBuilder sb = new StringBuilder(" \n");
        for (int i2 = 3; i2 < stackTraceElementArr.length - 3 && i2 < i; i2++) {
            sb.append(str);
            sb.append("at ");
            sb.append(stackTraceElementArr[i2].getClassName());
            sb.append(":");
            sb.append(stackTraceElementArr[i2].getMethodName());
            sb.append("(" + stackTraceElementArr[i2].getLineNumber() + ")");
            sb.append("\n");
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }
}

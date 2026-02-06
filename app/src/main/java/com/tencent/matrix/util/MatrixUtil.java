package com.tencent.matrix.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class MatrixUtil {
    private static final String TAG = "Matrix.MatrixUtil";
    private static String processName;
    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final ThreadLocal<MessageDigest> MD5_DIGEST = new ThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Initialize MD5 failed.", e);
            }
        }
    };

    private MatrixUtil() {
    }

    private static void appendHexPair(byte b, StringBuffer stringBuffer) {
        char[] cArr = hexDigits;
        char c = cArr[(b & 240) >> 4];
        char c2 = cArr[b & 15];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }

    private static String bufferToHex(byte[] bArr) {
        return bufferToHex(bArr, 0, bArr.length);
    }

    private static String bufferToHex(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i2 + i;
        while (i < i3) {
            appendHexPair(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    public static void closeQuietly(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.w("Matrix.MatrixUtil", "Failed to close resource", e);
            }
        }
    }

    public static String convertStreamToString(InputStream inputStream) throws Throwable {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
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

    public static String formatTime(String str, long j) {
        return new SimpleDateFormat(str).format(new Date(j));
    }

    public static String getLatestStack(String str, int i) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String[] strArrSplit = str.split("\n");
        if (strArrSplit.length <= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(strArrSplit[i2]);
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }

    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    public static String getMD5String(byte[] bArr) {
        return bufferToHex(MD5_DIGEST.get().digest(bArr));
    }

    public static String getProcessName(Context context) throws Throwable {
        String str = processName;
        if (str != null) {
            return str;
        }
        String processNameInternal = getProcessNameInternal(context);
        processName = processNameInternal;
        return processNameInternal;
    }

    private static String getProcessNameInternal(Context context) throws Throwable {
        FileInputStream fileInputStream;
        int i;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager.RunningAppProcessInfo next;
        int iMyPid = Process.myPid();
        if (context == null || iMyPid <= 0) {
            return "";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        FileInputStream fileInputStream2 = null;
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            try {
                Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                while (it.hasNext()) {
                    next = it.next();
                    if (next.pid == iMyPid) {
                        break;
                    }
                }
            } catch (Exception e) {
                Log.e("Matrix.MatrixUtil", "getProcessNameInternal exception:" + e.getMessage());
            }
            next = null;
            if (next != null) {
                return next.processName;
            }
        }
        byte[] bArr = new byte[128];
        try {
            try {
                try {
                    fileInputStream = new FileInputStream("/proc/" + iMyPid + "/cmdline");
                    try {
                        i = fileInputStream.read(bArr);
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream2 = fileInputStream;
                        Log.e("Matrix.MatrixUtil", "getProcessNameInternal exception:" + e.getMessage());
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e3) {
                                Log.e("Matrix.MatrixUtil", e3.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Exception e5) {
            Log.e("Matrix.MatrixUtil", e5.getMessage());
        }
        if (i <= 0) {
            fileInputStream.close();
            return "";
        }
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            if (bArr[i2] <= 0) {
                i = i2;
                break;
            }
            i2++;
        }
        String str = new String(bArr, 0, i, Charset.forName("UTF-8"));
        try {
            fileInputStream.close();
        } catch (Exception e6) {
            Log.e("Matrix.MatrixUtil", e6.getMessage());
        }
        return str;
    }

    public static String getStringFromFile(String str) throws Throwable {
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

    public static boolean isInMainProcess(Context context) throws Throwable {
        String packageName = context.getPackageName();
        String processName2 = getProcessName(context);
        if (processName2 == null || processName2.length() == 0) {
            processName2 = "";
        }
        return packageName.equals(processName2);
    }

    public static boolean isInMainThread(long j) {
        return Looper.getMainLooper().getThread().getId() == j;
    }

    public static String printException(Exception exc) {
        StackTraceElement[] stackTrace = exc.getStackTrace();
        if (stackTrace == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(exc.toString());
        for (int i = 2; i < stackTrace.length; i++) {
            sb.append('[');
            sb.append(stackTrace[i].getClassName());
            sb.append(':');
            sb.append(stackTrace[i].getMethodName());
            sb.append("(" + stackTrace[i].getLineNumber() + ")]");
            sb.append("\n");
        }
        return sb.toString();
    }
}

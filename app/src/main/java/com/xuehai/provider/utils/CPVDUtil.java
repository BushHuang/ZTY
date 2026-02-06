package com.xuehai.provider.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.xuehai.provider.log.ContentProviderLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CPVDUtil {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static void createFile(String str) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatEnvironment(int i) {
        return i != 2 ? i != 3 ? i != 4 ? "production" : "preRelease" : "develop" : "test";
    }

    public static ApplicationInfo getApplicationInfo(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static byte[] getFileMD5(File file) throws Throwable {
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2;
        DigestInputStream digestInputStream3 = null;
        try {
            if (file == null) {
                return null;
            }
            try {
                digestInputStream2 = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
            } catch (IOException e) {
                e = e;
                digestInputStream2 = null;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                }
                return null;
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                digestInputStream2 = null;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (digestInputStream3 != null) {
                    try {
                        digestInputStream3.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
            try {
                while (digestInputStream2.read(new byte[262144]) > 0) {
                }
                byte[] bArrDigest = digestInputStream2.getMessageDigest().digest();
                try {
                    digestInputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return bArrDigest;
            } catch (IOException e5) {
                e = e5;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                    try {
                        digestInputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return null;
            } catch (NoSuchAlgorithmException e7) {
                e = e7;
                e.printStackTrace();
                if (digestInputStream2 != null) {
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            digestInputStream3 = digestInputStream;
        }
    }

    public static String getFileMD5ToString(File file) {
        return bytes2HexString(getFileMD5(file));
    }

    public static boolean isActivityExist(Context context, Intent intent) {
        return (context == null || intent == null || context.getPackageManager().queryIntentActivities(intent, 0).size() == 0) ? false : true;
    }

    public static boolean isAppInstalled(Context context, String str) {
        return getApplicationInfo(context, str) != null;
    }

    @Deprecated
    public static boolean isNeedLog() {
        return ContentProviderLog.LOG;
    }

    @Deprecated
    public static <T> String objToJson(T t) {
        return JsonUtil.objToJson(t);
    }

    @Deprecated
    public static <T> T parseJson(String str, Class<T> cls) {
        return (T) JsonUtil.parseJson(str, cls);
    }

    public static String parserString(String str) {
        return (str == null || str.isEmpty()) ? "" : str;
    }

    @Deprecated
    public static void setNeedLog(boolean z) {
        ContentProviderLog.LOG = z;
    }
}

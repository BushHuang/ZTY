package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BksUtil {

    private static final String f421a = "BksUtil";
    private static final String b = "com.huawei.hwid";
    private static final String c = "com.huawei.hwid";
    private static final String d = "com.huawei.hms";
    private static final String e = "com.huawei.hwid.tv";
    private static final String g = "files/hmsrootcas.bks";
    private static final String h = "4.0.2.300";
    private static final String i = "aegis";
    private static final String j = "hmsrootcas.bks";
    private static final long k = 604800000;
    private static final String l = "last_update_time";
    private static final String m = "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05";
    private static final String n = "3517262215D8D3008CBF888750B6418EDC4D562AC33ED6874E0D73ABA667BC3C";
    private static final String o = "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539";
    private static final String q = "";
    private static final String r = "bks_hash";
    private static final Uri f = Uri.parse("content://com.huawei.hwid");
    private static final String[] p = {"B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05", "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539"};

    private BksUtil() {
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        File file = new File(str);
        if (file.exists()) {
            g.e("BksUtil", "The directory  has already exists");
            return 1;
        }
        if (file.mkdirs()) {
            g.a("BksUtil", "create directory  success");
            return 0;
        }
        g.b("BksUtil", "create directory  failed");
        return -1;
    }

    private static String a(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext().getFilesDir() + File.separator + "aegis";
        }
        return context.getApplicationContext().getFilesDir() + File.separator + "aegis";
    }

    private static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static void a(InputStream inputStream, Context context) throws Throwable {
        if (inputStream == null || context == null) {
            return;
        }
        String strA = a(context);
        if (!new File(strA).exists()) {
            a(strA);
        }
        File file = new File(strA, "hmsrootcas.bks");
        if (file.exists()) {
            file.delete();
        }
        ?? r7 = 0;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                g.c("BksUtil", "write output stream ");
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                r7 = 2048;
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int i2 = inputStream.read(bArr, 0, 2048);
                        if (i2 == -1) {
                            break;
                        } else {
                            fileOutputStream2.write(bArr, 0, i2);
                        }
                    }
                    f.a((OutputStream) fileOutputStream2);
                } catch (IOException unused) {
                    fileOutputStream = fileOutputStream2;
                    g.b("BksUtil", " IOException");
                    f.a((OutputStream) fileOutputStream);
                    r7 = fileOutputStream;
                } catch (Throwable th) {
                    th = th;
                    r7 = fileOutputStream2;
                    f.a((OutputStream) r7);
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean a(int i2) {
        return i2 >= 40002300;
    }

    private static byte[] a(Context context, String str) {
        PackageInfo packageInfo;
        if (context == null || TextUtils.isEmpty(str)) {
            Log.e("BksUtil", "packageName is null or context is null");
            return new byte[0];
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null) {
                return packageInfo.signatures[0].toByteArray();
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("BksUtil", "PackageManager.NameNotFoundException : " + e2.getMessage());
        } catch (Exception e3) {
            Log.e("BksUtil", "get pm exception : " + e3.getMessage());
        }
        return new byte[0];
    }

    private static String b(Context context) {
        return a(context) + File.separator + "hmsrootcas.bks";
    }

    private static String b(byte[] bArr) throws NoSuchAlgorithmException {
        if (bArr == null) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            g.b("BksUtil", "inputstraem exception");
            return "";
        }
    }

    private static boolean b(Context context, String str) {
        return "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539".equalsIgnoreCase(c(a(context, str)));
    }

    private static boolean b(String str) throws NumberFormatException {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        g.c("BksUtil", "hms version code is : " + str);
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = "4.0.2.300".split("\\.");
        int length = strArrSplit.length;
        int length2 = strArrSplit2.length;
        int iMax = Math.max(length, length2);
        int i3 = 0;
        while (i3 < iMax) {
            if (i3 < length) {
                try {
                    i2 = Integer.parseInt(strArrSplit[i3]);
                } catch (Exception e2) {
                    g.b("BksUtil", " exception : " + e2.getMessage());
                    return i3 >= length2;
                }
            } else {
                i2 = 0;
            }
            int i4 = i3 < length2 ? Integer.parseInt(strArrSplit2[i3]) : 0;
            if (i2 < i4) {
                return false;
            }
            if (i2 > i4) {
                return true;
            }
            i3++;
        }
        return true;
    }

    private static String c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return a(MessageDigest.getInstance("SHA-256").digest(bArr));
        } catch (NoSuchAlgorithmException e2) {
            Log.e("BksUtil", "NoSuchAlgorithmException" + e2.getMessage());
            return "";
        }
    }

    private static boolean c(Context context) {
        return new File(a(context) + File.separator + "hmsrootcas.bks").exists();
    }

    private static boolean c(Context context, String str) {
        byte[] bArrA = a(context, str);
        for (String str2 : p) {
            if (str2.equalsIgnoreCase(c(bArrA))) {
                return true;
            }
        }
        return false;
    }

    public static synchronized InputStream getBksFromTss(Context context) {
        InputStream inputStreamOpenInputStream;
        g.c("BksUtil", "get bks from tss begin");
        if (context != null) {
            c.a(context);
        }
        Context contextA = c.a();
        ByteArrayInputStream byteArrayInputStream = null;
        if (contextA == null) {
            g.b("BksUtil", "context is null");
            return null;
        }
        if (!b(h.a("com.huawei.hwid")) && !b(h.a("com.huawei.hms"))) {
            g.b("BksUtil", "hms version code is too low : " + h.a("com.huawei.hwid"));
            return null;
        }
        if (!c(contextA, "com.huawei.hwid") && !b(contextA, "com.huawei.hms")) {
            g.b("BksUtil", "hms sign error");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStreamOpenInputStream = contextA.getContentResolver().openInputStream(Uri.withAppendedPath(f, "files/hmsrootcas.bks"));
            try {
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i2 = inputStreamOpenInputStream.read(bArr);
                        if (i2 <= -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i2);
                    }
                    byteArrayOutputStream.flush();
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    try {
                        String strA = i.a("bks_hash", "", contextA);
                        String strB = b(byteArrayOutputStream.toByteArray());
                        if (c(contextA) && strA.equals(strB)) {
                            g.c("BksUtil", "bks not update");
                        } else {
                            g.c("BksUtil", "update bks and sp");
                            a(byteArrayInputStream2, contextA);
                            i.b("bks_hash", strB, contextA);
                        }
                        f.a(inputStreamOpenInputStream);
                        f.a((OutputStream) byteArrayOutputStream);
                        f.a((InputStream) byteArrayInputStream2);
                    } catch (Exception unused) {
                        byteArrayInputStream = byteArrayInputStream2;
                        g.b("BksUtil", "Get bks from HMS_VERSION_CODE exception : No content provider");
                        f.a(inputStreamOpenInputStream);
                        f.a((OutputStream) byteArrayOutputStream);
                        f.a((InputStream) byteArrayInputStream);
                        return getFilesBksIS(contextA);
                    } catch (Throwable th) {
                        th = th;
                        byteArrayInputStream = byteArrayInputStream2;
                        f.a(inputStreamOpenInputStream);
                        f.a((OutputStream) byteArrayOutputStream);
                        f.a((InputStream) byteArrayInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStreamOpenInputStream = null;
        }
        return getFilesBksIS(contextA);
    }

    public static InputStream getFilesBksIS(Context context) {
        if (!c(context)) {
            return null;
        }
        g.c("BksUtil", "getFilesBksIS ");
        try {
            return new FileInputStream(b(context));
        } catch (FileNotFoundException unused) {
            g.b("BksUtil", "FileNotFoundExceptio: ");
            return null;
        }
    }
}

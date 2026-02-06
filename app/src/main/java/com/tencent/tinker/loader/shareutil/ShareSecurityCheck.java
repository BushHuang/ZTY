package com.tencent.tinker.loader.shareutil;

import android.content.Context;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ShareSecurityCheck {
    private static final String TAG = "Tinker.SecurityCheck";
    private static String mPublicKeyMd5;
    private final Context mContext;
    private final HashMap<String, String> metaContentMap = new HashMap<>();
    private final HashMap<String, String> packageProperties = new HashMap<>();

    public ShareSecurityCheck(Context context) {
        this.mContext = context;
        if (mPublicKeyMd5 == null) {
            init(this.mContext);
        }
    }

    private boolean check(File file, Certificate[] certificateArr) {
        if (certificateArr.length <= 0) {
            return false;
        }
        for (int length = certificateArr.length - 1; length >= 0; length--) {
            try {
            } catch (Exception e) {
                Log.e("Tinker.SecurityCheck", file.getAbsolutePath(), e);
            }
            if (mPublicKeyMd5.equals(SharePatchFileUtil.getMD5(certificateArr[length].getEncoded()))) {
                return true;
            }
        }
        return false;
    }

    private void init(Context context) {
        try {
            try {
                String md5 = SharePatchFileUtil.getMD5(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
                mPublicKeyMd5 = md5;
                if (md5 != null) {
                } else {
                    throw new TinkerRuntimeException("get public key md5 is null");
                }
            } catch (Exception e) {
                throw new TinkerRuntimeException("ShareSecurityCheck init public key fail", e);
            }
        } finally {
            SharePatchFileUtil.closeQuietly(null);
        }
    }

    public HashMap<String, String> getMetaContentMap() {
        return this.metaContentMap;
    }

    public HashMap<String, String> getPackagePropertiesIfPresent() {
        String[] strArrSplit;
        if (!this.packageProperties.isEmpty()) {
            return this.packageProperties;
        }
        String str = this.metaContentMap.get("assets/package_meta.txt");
        if (str == null) {
            return null;
        }
        for (String str2 : str.split("\n")) {
            if (str2 != null && str2.length() > 0 && !str2.startsWith("#") && (strArrSplit = str2.split("=", 2)) != null && strArrSplit.length >= 2) {
                this.packageProperties.put(strArrSplit[0].trim(), strArrSplit[1].trim());
            }
        }
        return this.packageProperties;
    }

    public boolean verifyPatchMetaSignature(File file) throws Throwable {
        Throwable th;
        Exception e;
        JarFile jarFile;
        if (!SharePatchFileUtil.isLegalFile(file)) {
            return false;
        }
        JarFile jarFile2 = null;
        try {
            try {
                jarFile = new JarFile(file);
            } catch (Exception e2) {
                e = e2;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                }
                throw th;
            }
            try {
                Enumeration<JarEntry> enumerationEntries = jarFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    JarEntry jarEntryNextElement = enumerationEntries.nextElement();
                    if (jarEntryNextElement != null) {
                        String name = jarEntryNextElement.getName();
                        if (!name.startsWith("META-INF/") && name.endsWith("meta.txt")) {
                            this.metaContentMap.put(name, SharePatchFileUtil.loadDigestes(jarFile, jarEntryNextElement));
                            Certificate[] certificates = jarEntryNextElement.getCertificates();
                            if (certificates == null || !check(file, certificates)) {
                                try {
                                    jarFile.close();
                                } catch (IOException e3) {
                                    Log.e("Tinker.SecurityCheck", file.getAbsolutePath(), e3);
                                }
                                return false;
                            }
                        }
                    }
                }
                try {
                    jarFile.close();
                } catch (IOException e4) {
                    Log.e("Tinker.SecurityCheck", file.getAbsolutePath(), e4);
                }
                return true;
            } catch (Exception e5) {
                e = e5;
                throw new TinkerRuntimeException(String.format("ShareSecurityCheck file %s, size %d verifyPatchMetaSignature fail", file.getAbsolutePath(), Long.valueOf(file.length())), e);
            }
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                try {
                    jarFile2.close();
                } catch (IOException e6) {
                    Log.e("Tinker.SecurityCheck", file.getAbsolutePath(), e6);
                }
            }
            throw th;
        }
    }
}

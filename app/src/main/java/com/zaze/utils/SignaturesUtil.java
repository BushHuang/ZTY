package com.zaze.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u0010\bJ\u001c\u0010\u0003\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007¨\u0006\u000b"}, d2 = {"Lcom/zaze/utils/SignaturesUtil;", "", "()V", "getSignatures", "", "Landroid/content/pm/Signature;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)[Landroid/content/pm/Signature;", "", "algorithm", "util_release"}, k = 1, mv = {1, 4, 1})
public final class SignaturesUtil {
    public static final SignaturesUtil INSTANCE = new SignaturesUtil();

    private SignaturesUtil() {
    }

    @JvmStatic
    public static final String getSignatures(Context context, String algorithm) throws NoSuchAlgorithmException {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Signature[] signatures = getSignatures(context);
        boolean z2 = true;
        if (signatures == null) {
            z = true;
        } else if (!(signatures.length == 0)) {
            z = false;
        }
        if (z) {
            return null;
        }
        String str = algorithm;
        if (str != null && str.length() != 0) {
            z2 = false;
        }
        if (z2) {
            return EncryptionUtil.byteArrayToHex(signatures[0].toByteArray());
        }
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        for (Signature signature : signatures) {
            messageDigest.update(signature.toByteArray());
        }
        return EncryptionUtil.byteArrayToHex(messageDigest.digest());
    }

    @JvmStatic
    private static final Signature[] getSignatures(Context context) {
        Signature[] apkContentsSigners;
        SigningInfo signingInfo;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                PackageInfo packageInfo = AppUtil.INSTANCE.getPackageInfo(context, 134217728);
                if (packageInfo == null || (signingInfo = packageInfo.signingInfo) == null) {
                    return null;
                }
                apkContentsSigners = signingInfo.getApkContentsSigners();
            } else {
                PackageInfo packageInfo2 = AppUtil.INSTANCE.getPackageInfo(context, 64);
                if (packageInfo2 == null) {
                    return null;
                }
                apkContentsSigners = packageInfo2.signatures;
            }
            return apkContentsSigners;
        } catch (Exception unused) {
            return null;
        }
    }
}

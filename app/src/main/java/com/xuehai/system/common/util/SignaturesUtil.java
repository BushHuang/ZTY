package com.xuehai.system.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u001d\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u000bJ\u001a\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0004H\u0007¨\u0006\r"}, d2 = {"Lcom/xuehai/system/common/util/SignaturesUtil;", "", "()V", "getPackageSignature", "", "context", "Landroid/content/Context;", "packageName", "getSignatures", "", "Landroid/content/pm/Signature;", "(Landroid/content/Context;)[Landroid/content/pm/Signature;", "algorithm", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SignaturesUtil {
    public static final SignaturesUtil INSTANCE = new SignaturesUtil();

    private SignaturesUtil() {
    }

    @JvmStatic
    public static final String getSignatures(Context context, String algorithm) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Signature[] signatures = getSignatures(context);
        if (signatures == null) {
            return (String) null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        for (Signature signature : signatures) {
            messageDigest.update(signature.toByteArray());
        }
        return EncryptionUtil.byteArrayToHex(messageDigest.digest());
    }

    @JvmStatic
    public static final Signature[] getSignatures(Context context) throws PackageManager.NameNotFoundException {
        SigningInfo signingInfo;
        Intrinsics.checkNotNullParameter(context, "context");
        Signature[] apkContentsSigners = null;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 134217728);
                if (packageInfo != null && (signingInfo = packageInfo.signingInfo) != null) {
                    apkContentsSigners = signingInfo.getApkContentsSigners();
                }
            } else {
                PackageInfo packageInfo2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
                if (packageInfo2 != null) {
                    apkContentsSigners = packageInfo2.signatures;
                }
            }
            return apkContentsSigners;
        } catch (Exception unused) {
            return apkContentsSigners;
        }
    }

    public final String getPackageSignature(Context context, String packageName) throws PackageManager.NameNotFoundException {
        Signature[] signingCertificateHistory;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        PackageManager packageManager = context.getPackageManager();
        if (Build.VERSION.SDK_INT >= 28) {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 134217728);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "packageManager.getPackag…GET_SIGNING_CERTIFICATES)");
            signingCertificateHistory = packageInfo.signingInfo.getSigningCertificateHistory();
            Intrinsics.checkNotNullExpressionValue(signingCertificateHistory, "packageInfo.signingInfo.signingCertificateHistory");
        } else {
            PackageInfo packageInfo2 = packageManager.getPackageInfo(packageName, 64);
            Intrinsics.checkNotNullExpressionValue(packageInfo2, "packageManager.getPackag…kageName, GET_SIGNATURES)");
            signingCertificateHistory = packageInfo2.signatures;
            Intrinsics.checkNotNullExpressionValue(signingCertificateHistory, "packageInfo.signatures");
        }
        if (!(signingCertificateHistory.length == 0)) {
            return signingCertificateHistory[0].toCharsString();
        }
        return null;
    }
}

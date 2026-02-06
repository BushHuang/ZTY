package kshark.internal;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0003H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0003H\u0000\u001a\u0014\u0010\t\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"UTF_8", "Ljava/nio/charset/Charset;", "createHash", "", "text", "algorithm", "createSHA1Hash", "getBytes", "", "lastSegment", "segmentingChar", "", "shark"}, k = 2, mv = {1, 1, 15})
public final class StringsKt {
    public static final Charset UTF_8;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(charsetForName, "Charset.forName(\"UTF-8\")");
        UTF_8 = charsetForName;
    }

    private static final String createHash(String str, String str2) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(getBytes(str));
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString(b & 255));
            }
            String string = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(string, "hexString.toString()");
            return string;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError("Unable to construct MessageDigest for " + str2);
        }
    }

    public static final String createSHA1Hash(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$createSHA1Hash");
        return createHash(str, "SHA-1");
    }

    public static final byte[] getBytes(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$getBytes");
        byte[] bytes = str.getBytes(UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    public static final String lastSegment(String str, char c) {
        Intrinsics.checkParameterIsNotNull(str, "$this$lastSegment");
        int iLastIndexOf$default = kotlin.text.StringsKt.lastIndexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        if (iLastIndexOf$default == -1) {
            return str;
        }
        String strSubstring = str.substring(iLastIndexOf$default + 1);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }
}

package com.xh.xhcore.common.util.traceid;

public final class HexCodec {
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    HexCodec() {
    }

    static boolean hexEqualsByte(CharSequence charSequence, int i, byte b) {
        return charSequence.charAt(i + 0) == HEX_DIGITS[(b >> 4) & 15] && charSequence.charAt(i + 1) == HEX_DIGITS[b & 15];
    }

    static NumberFormatException isntLowerHexLong(CharSequence charSequence) {
        throw new NumberFormatException(((Object) charSequence) + " should be a 1 to 32 character lower-hex string with no prefix");
    }

    public static long lenientLowerHexToUnsignedLong(CharSequence charSequence, int i, int i2) {
        int i3;
        long j = 0;
        while (i < i2) {
            int i4 = i + 1;
            char cCharAt = charSequence.charAt(i);
            long j2 = j << 4;
            if (cCharAt >= '0' && cCharAt <= '9') {
                i3 = cCharAt - '0';
            } else {
                if (cCharAt < 'a' || cCharAt > 'f') {
                    return 0L;
                }
                i3 = (cCharAt - 'a') + 10;
            }
            j = j2 | i3;
            i = i4;
        }
        return j;
    }

    static boolean lowerHexEqualsUnsignedLong(CharSequence charSequence, int i, long j) {
        if (hexEqualsByte(charSequence, i + 0, (byte) ((j >>> 56) & 255)) && hexEqualsByte(charSequence, i + 2, (byte) ((j >>> 48) & 255)) && hexEqualsByte(charSequence, i + 4, (byte) ((j >>> 40) & 255)) && hexEqualsByte(charSequence, i + 6, (byte) ((j >>> 32) & 255)) && hexEqualsByte(charSequence, i + 8, (byte) ((j >>> 24) & 255)) && hexEqualsByte(charSequence, i + 10, (byte) ((j >>> 16) & 255)) && hexEqualsByte(charSequence, i + 12, (byte) ((j >>> 8) & 255))) {
            return hexEqualsByte(charSequence, i + 14, (byte) (j & 255));
        }
        return false;
    }

    public static boolean lowerHexEqualsUnsignedLong(CharSequence charSequence, long j) {
        if (charSequence == null || charSequence.length() != 16) {
            return false;
        }
        return lowerHexEqualsUnsignedLong(charSequence, 0, j);
    }

    public static long lowerHexToUnsignedLong(CharSequence charSequence) {
        int length = charSequence.length();
        if (length < 1 || length > 32) {
            throw isntLowerHexLong(charSequence);
        }
        return lowerHexToUnsignedLong(charSequence, length > 16 ? length - 16 : 0);
    }

    public static long lowerHexToUnsignedLong(CharSequence charSequence, int i) {
        long jLenientLowerHexToUnsignedLong = lenientLowerHexToUnsignedLong(charSequence, i, Math.min(i + 16, charSequence.length()));
        if (jLenientLowerHexToUnsignedLong != 0) {
            return jLenientLowerHexToUnsignedLong;
        }
        throw isntLowerHexLong(charSequence);
    }

    public static String toLowerHex(long j) {
        char[] cArr = new char[16];
        writeHexLong(cArr, 0, j);
        return new String(cArr);
    }

    public static String toLowerHex(long j, long j2) {
        int i = 16;
        char[] cArr = new char[j != 0 ? 32 : 16];
        if (j != 0) {
            writeHexLong(cArr, 0, j);
        } else {
            i = 0;
        }
        writeHexLong(cArr, i, j2);
        return new String(cArr);
    }

    public static void writeHexByte(char[] cArr, int i, byte b) {
        char[] cArr2 = HEX_DIGITS;
        cArr[i + 0] = cArr2[(b >> 4) & 15];
        cArr[i + 1] = cArr2[b & 15];
    }

    public static void writeHexLong(char[] cArr, int i, long j) {
        writeHexByte(cArr, i + 0, (byte) ((j >>> 56) & 255));
        writeHexByte(cArr, i + 2, (byte) ((j >>> 48) & 255));
        writeHexByte(cArr, i + 4, (byte) ((j >>> 40) & 255));
        writeHexByte(cArr, i + 6, (byte) ((j >>> 32) & 255));
        writeHexByte(cArr, i + 8, (byte) ((j >>> 24) & 255));
        writeHexByte(cArr, i + 10, (byte) ((j >>> 16) & 255));
        writeHexByte(cArr, i + 12, (byte) ((j >>> 8) & 255));
        writeHexByte(cArr, i + 14, (byte) (j & 255));
    }
}

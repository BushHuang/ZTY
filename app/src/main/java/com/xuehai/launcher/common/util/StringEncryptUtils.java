package com.xuehai.launcher.common.util;

import android.text.TextUtils;
import android.util.Base64;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class StringEncryptUtils {
    private static final String COMPLEMENT_SYMBOL = "&";
    private static final int DECRYPTION_VALIDITY_PERIOD = 300000;
    private static final int OBFUSCATION_LENGTH = 96;
    private static final int ONE_GROUP_ELEMENTS = 3;
    private static final int TIMESTAMP_LENGTH = 13;

    private static int changeDec(char c) {
        return (c < 'A' || c > 'Z') ? (c < 'a' || c > 'z') ? c - '0' : (c - 'a') + 36 : (c - 'A') + 10;
    }

    public static String decrypt(String str) {
        int length = str.length();
        if (length < 98) {
            throw new IllegalArgumentException("illegal ciphers text");
        }
        int i = length - 1;
        String strSubstring = str.substring(0, i);
        if (!TextUtils.equals(String.valueOf(getCheckCode(strSubstring)), str.substring(i, length))) {
            throw new IllegalArgumentException("check code verification failed");
        }
        int iIndexOf = strSubstring.indexOf("&");
        int i2 = iIndexOf + 1;
        int i3 = i2 + 96;
        char[] cArr = new char[32];
        for (String str2 : stringToStringArray(strSubstring.substring(i2, i3), 3)) {
            char[] charArray = str2.toCharArray();
            if (!TextUtils.equals(String.valueOf(charArray[2]), "&")) {
                cArr[to10String(String.valueOf(charArray[0]))] = charArray[2];
            }
        }
        String str3 = new String(Base64.decode((strSubstring.substring(0, iIndexOf) + String.valueOf(cArr) + strSubstring.substring(i3)).trim(), 8));
        int length2 = str3.length();
        if (length2 < 13) {
            throw new IllegalArgumentException("decryption failed");
        }
        int i4 = length2 - 13;
        String strSubstring2 = str3.substring(0, i4);
        if (System.currentTimeMillis() - Long.parseLong(str3.substring(i4, length2)) <= 300000) {
            return strSubstring2;
        }
        throw new IllegalArgumentException("decryption timeout");
    }

    private static int getCheckCode(String str) {
        char[] charArray = str.trim().toCharArray();
        ArrayList arrayList = new ArrayList();
        int iIntValue = 0;
        if (charArray.length > 0) {
            for (char c : charArray) {
                String strValueOf = String.valueOf(c);
                if (strValueOf.matches("[0-9]")) {
                    arrayList.add(Integer.valueOf(strValueOf));
                } else {
                    arrayList.add(Integer.valueOf(changeDec(c)));
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            iIntValue += ((Integer) it.next()).intValue();
        }
        return 9 - (iIntValue % 10);
    }

    private static String[] stringToStringArray(String str, int i) {
        if (str == null || str.equals("") || i <= 0) {
            return null;
        }
        int length = ((str.length() + i) - 1) / i;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < length - 1) {
                strArr[i2] = str.substring(i2 * i, (i2 + 1) * i);
            } else {
                strArr[i2] = str.substring(i2 * i);
            }
        }
        return strArr;
    }

    private static int to10String(String str) {
        return Integer.parseInt(new BigInteger(str, 32).toString(10));
    }
}

package com.zaze.utils;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

public class ZStringUtil {
    public static String arrayToString(List<String> list, String str) {
        if (list == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                sb.append(list.get(i));
            } else {
                sb.append(str);
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }

    public static String arrayToString(int[] iArr, String str) {
        if (iArr == null) {
            return "";
        }
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                sb.append(iArr[i]);
            } else {
                sb.append(str);
                sb.append(iArr[i]);
            }
        }
        return sb.toString();
    }

    public static String arrayToString(String[] strArr, String str) {
        if (strArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                sb.append(strArr[i]);
            } else {
                sb.append(str);
                sb.append(strArr[i]);
            }
        }
        return sb.toString();
    }

    public static String bytesToString(byte[] bArr) {
        return bytesToString(bArr, "");
    }

    public static String bytesToString(byte[] bArr, String str) {
        return bytesToString(bArr, str, "UTF-8");
    }

    public static String bytesToString(byte[] bArr, String str, String str2) {
        if (bArr == null) {
            return str;
        }
        try {
            return new String(bArr, str2).trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new String(bArr).trim();
        }
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        return TextUtils.equals(charSequence, charSequence2);
    }

    public static String format(String str, Object... objArr) {
        if (objArr == null) {
            return str;
        }
        try {
            return String.format(Locale.getDefault(), str, objArr);
        } catch (Exception unused) {
            return str;
        }
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence);
    }

    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    public static int parseInt(String str, int i) {
        if (isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String parseString(String str) {
        return parseString(str, "");
    }

    public static String parseString(String str, String str2) {
        return str == null ? str2 : str;
    }

    public static byte[] string2Bytes(String str) {
        if (str != null) {
            return str.getBytes();
        }
        return null;
    }

    public static byte[] string2Bytes(String str, int i) {
        byte[] bArr = new byte[i];
        if (str != null) {
            byte[] bytes = str.getBytes();
            if (bytes.length <= i) {
                i = bytes.length;
            }
            System.arraycopy(bytes, 0, bArr, 0, i);
        }
        return bArr;
    }
}

package com.xh.xhcore.common.util;

import android.text.TextUtils;
import java.io.File;

public class StringUtil {
    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        return TextUtils.equals(charSequence, charSequence2);
    }

    public static String getLastNonEmptySegment(String str) {
        String strRemoveLastSeparator = removeLastSeparator(str);
        int iLastIndexOf = strRemoveLastSeparator.lastIndexOf(File.separator);
        return iLastIndexOf < 0 ? strRemoveLastSeparator : strRemoveLastSeparator.substring(iLastIndexOf + 1, strRemoveLastSeparator.length());
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

    public static String removeAllEndBackslash(String str) {
        if (!TextUtils.isEmpty(str)) {
            while (str.endsWith("/")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public static String removeAllStartBackslash(String str) {
        if (!TextUtils.isEmpty(str)) {
            while (str.startsWith("/")) {
                str = str.substring(1, str.length());
            }
        }
        return str;
    }

    public static String removeLastSeparator(String str) {
        return (TextUtils.isEmpty(str) || !str.endsWith("/")) ? str : str.substring(0, str.length() - 1);
    }
}

package com.xh.xhcore.common.util;

import android.os.Build;
import android.text.Html;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class XHEncodeUtil {
    private XHEncodeUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] base64Decode(String str) {
        return Base64.decode(str, 2);
    }

    public static byte[] base64Decode(byte[] bArr) {
        return Base64.decode(bArr, 2);
    }

    public static byte[] base64Encode(String str) {
        return base64Encode(str.getBytes());
    }

    public static byte[] base64Encode(byte[] bArr) {
        return Base64.encode(bArr, 2);
    }

    public static String base64Encode2String(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static String base64Encode2String(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static String base64EncodeDefault2String(String str) {
        return Base64.encodeToString(str.getBytes(), 0);
    }

    public static byte[] base64UrlSafeEncode(String str) {
        return Base64.encode(str.getBytes(), 8);
    }

    public static String htmlEncode(CharSequence charSequence) {
        int i;
        char cCharAt;
        if (Build.VERSION.SDK_INT >= 16) {
            return Html.escapeHtml(charSequence);
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        int length = charSequence.length();
        while (i2 < length) {
            char cCharAt2 = charSequence.charAt(i2);
            if (cCharAt2 == '<') {
                sb.append("&lt;");
            } else if (cCharAt2 == '>') {
                sb.append("&gt;");
            } else if (cCharAt2 == '&') {
                sb.append("&amp;");
            } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                if (cCharAt2 > '~' || cCharAt2 < ' ') {
                    sb.append("&#");
                    sb.append((int) cCharAt2);
                    sb.append(";");
                } else if (cCharAt2 == ' ') {
                    while (true) {
                        int i3 = i2 + 1;
                        if (i3 >= length || charSequence.charAt(i3) != ' ') {
                            break;
                        }
                        sb.append("&nbsp;");
                        i2 = i3;
                    }
                    sb.append(' ');
                } else {
                    sb.append(cCharAt2);
                }
            } else if (cCharAt2 < 56320 && (i = i2 + 1) < length && (cCharAt = charSequence.charAt(i)) >= 56320 && cCharAt <= 57343) {
                sb.append("&#");
                sb.append(65536 | ((cCharAt2 - 55296) << 10) | (cCharAt - 56320));
                sb.append(";");
                i2 = i;
            }
            i2++;
        }
        return sb.toString();
    }

    public static String urlDecode(String str) {
        return urlDecode(str, "UTF-8");
    }

    public static String urlDecode(String str, String str2) {
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static String urlEncode(String str) {
        return urlEncode(str, "UTF-8");
    }

    public static String urlEncode(String str, String str2) {
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }
}

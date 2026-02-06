package com.xh.xhcore.common.http.strategy.okhttp.interceptors;

import com.xh.xhcore.common.http.archi.MD5Util;

public class RequestSignUtil {
    private static final String AUTHORIZATION = "Authorization";
    private static final String HTTP_SIGNATURE_KEY = "sign";
    private static final String TIMESTAMP_KEY = "t";

    interface SignListener {
        void onSignatureBodyReady(String str);
    }

    public static String addRestfulVerifyData(String str, String str2, String str3, String str4, String str5, SignListener signListener) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(jCurrentTimeMillis);
        sb.append(str3);
        if (!needCombineBodyDataIntoUrl(str) && !isEmpty(str4) && str4.getBytes().length < 1048576) {
            sb.append(str4);
        }
        if (str5 != null) {
            sb.append("Authorization: " + str5);
        }
        String string = sb.toString();
        if (signListener != null) {
            signListener.onSignatureBodyReady(string);
        }
        String strEncode = MD5Util.encode(string);
        StringBuilder sb2 = new StringBuilder(str2);
        if (str2.contains("?")) {
            sb2.append("&");
        } else {
            sb2.append("?");
        }
        sb2.append("sign");
        sb2.append("=");
        sb2.append(strEncode);
        sb2.append("&");
        sb2.append("t");
        sb2.append("=");
        sb2.append(jCurrentTimeMillis);
        return sb2.toString();
    }

    public static String addRestfulVerifyDataV2(String str, String str2, String str3, String str4, String str5, String str6, SignListener signListener) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(jCurrentTimeMillis);
        sb.append(str4);
        if (!needCombineBodyDataIntoUrl(str) && !isEmpty(str5) && str5.getBytes().length < 1048576) {
            sb.append(str5);
        }
        if (str6 != null) {
            sb.append("Authorization: " + str6);
        }
        String string = sb.toString();
        if (signListener != null) {
            signListener.onSignatureBodyReady(string);
        }
        String strEncode = MD5Util.encode(string);
        StringBuilder sb2 = new StringBuilder(str2);
        if (str2.contains("?")) {
            sb2.append("&");
        } else {
            sb2.append("?");
        }
        sb2.append("sign");
        sb2.append("=");
        sb2.append(strEncode);
        sb2.append(str3);
        sb2.append("&");
        sb2.append("t");
        sb2.append("=");
        sb2.append(jCurrentTimeMillis);
        return sb2.toString();
    }

    private static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean needCombineBodyDataIntoUrl(String str) {
        return "GET".equals(str) || "DELETE".equals(str);
    }
}

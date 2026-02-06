package com.xuehai.provider.log;

public class ContentProviderLog {
    public static boolean LOG = true;
    private static final String TAG = "CPVD:";
    private static ContentProviderLogClient logClient = new ContentProviderLogClient();

    private static String buildTag(String str) {
        return "CPVD:" + str;
    }

    public static void d(String str, String str2) {
        if (LOG) {
            logClient.d(buildTag(str), str2);
        }
    }

    public static void e(String str, String str2) {
        if (LOG) {
            logClient.e(buildTag(str), str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (LOG) {
            logClient.e(buildTag(str), str2, th);
        }
    }

    public static void i(String str, String str2) {
        if (LOG) {
            logClient.i(buildTag(str), str2);
        }
    }

    public static void setLogClient(ContentProviderLogClient contentProviderLogClient) {
        logClient = contentProviderLogClient;
    }

    public static void v(String str, String str2) {
        if (LOG) {
            logClient.v(buildTag(str), str2);
        }
    }

    public static void w(String str, String str2) {
        if (LOG) {
            logClient.w(buildTag(str), str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (LOG) {
            logClient.w(buildTag(str), str2, th);
        }
    }
}

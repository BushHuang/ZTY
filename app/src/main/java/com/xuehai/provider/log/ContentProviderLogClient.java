package com.xuehai.provider.log;

import android.util.Log;

public class ContentProviderLogClient {
    public void d(String str, String str2) {
        Log.d(str, str2);
    }

    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    public void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public void i(String str, String str2) {
        Log.i(str, str2);
    }

    public void v(String str, String str2) {
        Log.v(str, str2);
    }

    public void w(String str, String str2) {
        Log.w(str, str2);
    }

    public void w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }
}

package com.xuehai.launcher.natives;

import android.content.Context;

public class SecretJni {
    static {
        System.loadLibrary("secret-lib");
    }

    public static SecretJni getInstance() {
        return new SecretJni();
    }

    public native boolean verifyDevelopToken(Context context, String str, String str2);

    public native boolean verifyQrToken(Context context, String str);
}

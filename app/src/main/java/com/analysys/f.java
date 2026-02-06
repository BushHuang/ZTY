package com.analysys;

import android.content.Context;
import android.content.SharedPreferences;

public class f {

    private static SharedPreferences.Editor f44a;
    private static SharedPreferences b;

    public SharedPreferences a(Context context) {
        if (b == null && context != null) {
            b = context.getSharedPreferences("fz.d", 0);
        }
        return b;
    }

    public SharedPreferences.Editor b(Context context) {
        a(context);
        SharedPreferences sharedPreferences = b;
        if (sharedPreferences != null) {
            f44a = sharedPreferences.edit();
        }
        return f44a;
    }
}

package com.huawei.hms.hem;

import android.util.Log;

public final class h {
    h() {
    }

    public static void a(i iVar, int i, String str) {
        if (iVar == null) {
            return;
        }
        a(iVar.a(), i, str);
    }

    private static void a(String str, int i, String str2) {
        if (str == null) {
            return;
        }
        if (i == 3) {
            Log.d(str2, str);
            return;
        }
        if (i == 4) {
            Log.i(str2, str);
            return;
        }
        if (i == 5) {
            Log.w(str2, str);
        } else if (i != 6) {
            Log.i(str2, str);
        } else {
            Log.e(str2, str);
        }
    }
}

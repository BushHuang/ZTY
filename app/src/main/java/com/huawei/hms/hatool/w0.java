package com.huawei.hms.hatool;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;

public class w0 {
    public static w0 c = new w0();

    public boolean f327a = false;
    public Context b = b.i();

    public static w0 b() {
        return c;
    }

    public boolean a() {
        boolean zIsUserUnlocked;
        if (!this.f327a) {
            Context context = this.b;
            if (context == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                UserManager userManager = (UserManager) context.getSystemService("user");
                if (userManager != null) {
                    zIsUserUnlocked = userManager.isUserUnlocked();
                } else {
                    this.f327a = false;
                }
            } else {
                zIsUserUnlocked = true;
            }
            this.f327a = zIsUserUnlocked;
        }
        return this.f327a;
    }
}

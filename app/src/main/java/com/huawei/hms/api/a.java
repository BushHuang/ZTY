package com.huawei.hms.api;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

class a {
    static final a b = new a();
    private static final Object c = new Object();

    List<Activity> f239a = new ArrayList(1);

    a() {
    }

    void a(Activity activity) {
        synchronized (c) {
            for (Activity activity2 : this.f239a) {
                if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                    activity2.finish();
                }
            }
            this.f239a.add(activity);
        }
    }

    void b(Activity activity) {
        synchronized (c) {
            this.f239a.remove(activity);
        }
    }
}

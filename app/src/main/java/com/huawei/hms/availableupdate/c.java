package com.huawei.hms.availableupdate;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

public class c {
    public static final c b = new c();
    public static final Object c = new Object();

    public final List<Activity> f243a = new ArrayList(1);

    public void a(Activity activity) {
        synchronized (c) {
            for (Activity activity2 : this.f243a) {
                if (activity2 != null && activity2 != activity && !activity2.isFinishing()) {
                    activity2.finish();
                }
            }
            this.f243a.add(activity);
        }
    }

    public void b(Activity activity) {
        synchronized (c) {
            this.f243a.remove(activity);
        }
    }
}

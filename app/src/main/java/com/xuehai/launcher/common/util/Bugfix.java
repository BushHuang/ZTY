package com.xuehai.launcher.common.util;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Bugfix {
    private static final String TAG = "Bugfix";
    private static final List<String> TEXT_HOVER_LEAK_DEVICES;

    static {
        ArrayList arrayList = new ArrayList();
        TEXT_HOVER_LEAK_DEVICES = arrayList;
        arrayList.add("SM-P355C");
        TEXT_HOVER_LEAK_DEVICES.add("SM-P350");
    }

    public static void clearTextHoverCache() {
        Log.d("Bugfix", "clearTextHoverCache=" + Build.MODEL);
        if (TEXT_HOVER_LEAK_DEVICES.contains(Build.MODEL)) {
            try {
                Field declaredField = Class.forName("android.widget.TextView").getDeclaredField("mLastHoveredView");
                declaredField.setAccessible(true);
                declaredField.set(null, null);
                declaredField.setAccessible(false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}

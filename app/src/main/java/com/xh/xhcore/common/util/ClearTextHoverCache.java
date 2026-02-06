package com.xh.xhcore.common.util;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClearTextHoverCache {
    private static final List<String> textHoverLeakDevices;

    static {
        ArrayList arrayList = new ArrayList();
        textHoverLeakDevices = arrayList;
        arrayList.add("SM-P355C");
        textHoverLeakDevices.add("SM-P350");
        textHoverLeakDevices.add("SM-P200");
    }

    public static void clearTextHoverCache() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Log.d("clearTextHoverCache", "clearTextHoverCache=" + Build.MODEL);
        if (textHoverLeakDevices.contains(Build.MODEL)) {
            try {
                Field declaredField = Class.forName("android.widget.TextView").getDeclaredField("mLastHoveredView");
                declaredField.setAccessible(true);
                declaredField.set(null, null);
                declaredField.setAccessible(false);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
            }
        }
    }
}

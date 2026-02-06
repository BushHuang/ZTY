package com.kwai.koom.javaoom.test.leaked;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class LeakMaker<T> {
    private static List<LeakMaker> leakMakerList = new ArrayList();
    List<T> uselessObjectList = new ArrayList();

    public static void makeLeak(Context context) {
        leakMakerList.add(new ActivityLeakMaker());
        leakMakerList.add(new StringLeakMaker());
        Iterator<LeakMaker> it = leakMakerList.iterator();
        while (it.hasNext()) {
            it.next().startLeak(context);
        }
    }

    abstract void startLeak(Context context);
}

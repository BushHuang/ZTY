package com.kwai.koom.javaoom.test.leaked;

import android.content.Context;

public class StringLeakMaker extends LeakMaker<String> {
    @Override
    void startLeak(Context context) {
        this.uselessObjectList.add(new String(new byte[262145]));
    }
}

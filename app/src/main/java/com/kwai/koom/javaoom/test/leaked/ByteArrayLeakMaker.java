package com.kwai.koom.javaoom.test.leaked;

import android.content.Context;

public class ByteArrayLeakMaker extends LeakMaker<ByteArrayHolder> {

    public static class ByteArrayHolder {
        private byte[] array = new byte[262145];
    }

    @Override
    public void startLeak(Context context) {
        this.uselessObjectList.add(new ByteArrayHolder());
    }
}

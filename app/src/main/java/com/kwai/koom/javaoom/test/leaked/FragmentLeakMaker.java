package com.kwai.koom.javaoom.test.leaked;

import android.app.Fragment;
import android.content.Context;

public class FragmentLeakMaker extends LeakMaker<Fragment> {
    @Override
    void startLeak(Context context) {
    }
}

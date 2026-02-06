package com.zaze.utils.cache;

import android.content.res.Configuration;

interface MemoryListener {
    void onConfigurationChanged(Configuration configuration);

    void onLowMemory();

    void onTrimMemory(int i);
}

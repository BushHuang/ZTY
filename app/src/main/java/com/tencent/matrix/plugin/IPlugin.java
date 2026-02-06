package com.tencent.matrix.plugin;

import android.app.Application;

public interface IPlugin {
    void destroy();

    Application getApplication();

    String getTag();

    void init(Application application, PluginListener pluginListener);

    void onForeground(boolean z);

    void start();

    void stop();
}

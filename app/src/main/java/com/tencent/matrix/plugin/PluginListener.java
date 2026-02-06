package com.tencent.matrix.plugin;

import com.tencent.matrix.report.Issue;

public interface PluginListener {
    void onDestroy(Plugin plugin);

    void onInit(Plugin plugin);

    void onReportIssue(Issue issue);

    void onStart(Plugin plugin);

    void onStop(Plugin plugin);
}

package com.tencent.matrix.plugin;

import android.content.Context;
import com.tencent.matrix.report.Issue;
import com.tencent.matrix.util.MatrixLog;

public class DefaultPluginListener implements PluginListener {
    private static final String TAG = "Matrix.DefaultPluginListener";
    private final Context context;

    public DefaultPluginListener(Context context) {
        this.context = context;
    }

    @Override
    public void onDestroy(Plugin plugin) {
        MatrixLog.i("Matrix.DefaultPluginListener", "%s plugin is destroyed", plugin.getTag());
    }

    @Override
    public void onInit(Plugin plugin) {
        MatrixLog.i("Matrix.DefaultPluginListener", "%s plugin is inited", plugin.getTag());
    }

    @Override
    public void onReportIssue(Issue issue) {
        Object[] objArr = new Object[1];
        Object obj = issue;
        if (issue == null) {
            obj = "";
        }
        objArr[0] = obj;
        MatrixLog.i("Matrix.DefaultPluginListener", "report issue content: %s", objArr);
    }

    @Override
    public void onStart(Plugin plugin) {
        MatrixLog.i("Matrix.DefaultPluginListener", "%s plugin is started", plugin.getTag());
    }

    @Override
    public void onStop(Plugin plugin) {
        MatrixLog.i("Matrix.DefaultPluginListener", "%s plugin is stopped", plugin.getTag());
    }
}

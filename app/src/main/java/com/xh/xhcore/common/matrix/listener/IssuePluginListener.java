package com.xh.xhcore.common.matrix.listener;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.tencent.matrix.plugin.DefaultPluginListener;
import com.tencent.matrix.report.Issue;
import com.tencent.matrix.util.MatrixLog;
import com.xh.logutils.LogManager;
import com.xh.xhcore.R;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.matrix.issue.IssueConverter;
import com.xh.xhcore.common.matrix.issue.IssueFilter;
import com.xh.xhcore.common.matrix.issue.IssuesListActivity;
import com.xh.xhcore.common.matrix.issue.IssuesMap;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import java.lang.ref.SoftReference;
import org.json.JSONException;
import org.json.JSONObject;

public class IssuePluginListener extends DefaultPluginListener {
    public static final String TAG = "Matrix.IssuePluginListener";
    public SoftReference<Context> softReference;

    public IssuePluginListener(Context context) {
        super(context);
        this.softReference = new SoftReference<>(context);
    }

    private void jumpToIssueActivity() {
        XhBaseApplication xhBaseApplication = XhBaseApplication.getXhBaseApplication();
        Notification.Builder when = new Notification.Builder(xhBaseApplication).setSmallIcon(R.drawable.xhcore_leak_canary_leak).setContentTitle("APM issue detected!").setContentText("Tab for more details").setAutoCancel(true).setContentIntent(IssuesListActivity.createPendingIntent(xhBaseApplication)).setWhen(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= 26) {
            when.setChannelId(XHAppUtil.getAppName());
        }
        ((NotificationManager) xhBaseApplication.getSystemService("notification")).notify(R.id.xhcore_notification_apm_issue, when.build());
    }

    @Override
    public void onReportIssue(Issue issue) throws JSONException {
        JSONObject content = issue.getContent();
        if (content != null) {
            Object objOpt = content.opt("stackKey");
            if (objOpt != null && objOpt.toString().equals("1048574|")) {
                MatrixLog.e("Matrix.IssuePluginListener", "dispatchMessage is the only stackKey, this may happened when system method slow, ignore", new Object[0]);
                return;
            } else {
                JsonUtil.stringValueToJSONArray(content, "threadStack");
                JsonUtil.stringValueToJSONArray(content, "stack");
            }
        }
        JSONObject jSONObjectConvert = IssueConverter.convert(issue);
        if (!XHAppConfigProxy.getInstance().isAppBuildTypeDebug()) {
            LogManager.getInstance().uploadAPMLog(jSONObjectConvert);
            return;
        }
        MatrixLog.e("Matrix.IssuePluginListener", JsonUtil.JSONObjectToString(jSONObjectConvert), new Object[0]);
        IssuesMap.put(IssueFilter.getCurrentFilter(), issue);
        jumpToIssueActivity();
    }
}

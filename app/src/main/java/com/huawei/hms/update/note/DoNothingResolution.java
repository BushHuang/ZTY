package com.huawei.hms.update.note;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.support.log.HMSLog;

public class DoNothingResolution implements IBridgeActivityDelegate {
    @Override
    public int getRequestCode() {
        HMSLog.i("DoNothingResolution", "<DoNothingResolution getRequestCode>");
        return 0;
    }

    @Override
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.i("DoNothingResolution", "<DoNothingResolution onBridgeActivityCreate>");
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("DoNothingResolution", "<Resolution onBridgeActivityCreate> activity is null or finishing");
        } else {
            activity.setResult(30);
            activity.finish();
        }
    }

    @Override
    public void onBridgeActivityDestroy() {
        HMSLog.i("DoNothingResolution", "<DoNothingResolution onBridgeActivityDestroy>");
    }

    @Override
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        HMSLog.i("DoNothingResolution", "<DoNothingResolution onBridgeActivityResult>");
        return false;
    }

    @Override
    public void onBridgeConfigurationChanged() {
        HMSLog.i("DoNothingResolution", "<DoNothingResolution onBridgeConfigurationChanged>");
    }

    @Override
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.i("DoNothingResolution", "<DoNothingResolution onKeyUp>");
    }
}

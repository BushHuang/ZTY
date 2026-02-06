package com.xh.open.agent;

import android.content.Intent;
import android.os.Bundle;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.ActivityUtil;

public class AgentActivity extends AbsActivity {
    private static final String KEY_REQUEST_CODE = "key_request_code";

    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        MyLog.i("[MDM]", "AgentActivity onActivityResult--requestCode: " + i + " | resultCode: " + i2 + "data : " + intent);
        super.onActivityResult(i, i2, intent);
        setResult(i2, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle bundle) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setRequestedOrientation(3);
        Intent intent = getIntent();
        if (intent == null) {
            MyLog.e("[MDM]", "-->onCreate--getIntent() is null finish and return");
            finish();
            return;
        }
        intent.setComponent(null);
        intent.setAction("com.xh.zhitongyun.action.agent_login");
        int intExtra = intent.getIntExtra("key_request_code", 0);
        MyLog.i("[MDM]", "AgentActivity--onCreate-- start activity, requestCode = " + intExtra);
        if (ActivityUtil.INSTANCE.startActivity(this, intent, intExtra)) {
            return;
        }
        setResult(0);
        finish();
    }
}

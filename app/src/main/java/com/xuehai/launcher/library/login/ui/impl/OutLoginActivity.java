package com.xuehai.launcher.library.login.ui.impl;

import android.content.Intent;
import android.os.Bundle;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.util.ActivityUtil;

public class OutLoginActivity extends AbsActivity {
    @Override
    protected void onCreate(Bundle bundle) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(bundle);
        Intent intent = new Intent("com.xh.zhitongyun.action.out_login");
        intent.setFlags(268435456);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ActivityUtil.INSTANCE.startActivity(this, intent, -1);
        finish();
    }
}

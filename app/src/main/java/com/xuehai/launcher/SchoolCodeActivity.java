package com.xuehai.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.xuehai.launcher.library.welcome.ui.impl.WelcomeActivity;

@Deprecated
public class SchoolCodeActivity extends Activity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.xuehai.launcher.compat.R.layout.activity_school_code);
        startActivity(new Intent(this, (Class<?>) WelcomeActivity.class));
        finish();
    }
}

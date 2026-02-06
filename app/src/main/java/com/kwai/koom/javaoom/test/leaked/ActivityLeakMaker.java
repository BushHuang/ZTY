package com.kwai.koom.javaoom.test.leaked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.List;

public class ActivityLeakMaker extends LeakMaker<Activity> {

    public static class LeakedActivity extends Activity {
        static List<Activity> uselessObjectList;

        public static void setUselessObjectList(List<Activity> list) {
            uselessObjectList = list;
        }

        @Override
        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            uselessObjectList.add(this);
            finish();
        }
    }

    @Override
    public void startLeak(Context context) {
        LeakedActivity.setUselessObjectList(this.uselessObjectList);
        context.startActivity(new Intent(context, (Class<?>) LeakedActivity.class));
    }
}

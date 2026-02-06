package com.analysys.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import java.lang.ref.WeakReference;

public class AnalysysUtil {
    private static WeakReference<Activity> mActivity;
    private static Context sContext;

    private static Application getApplication() {
        Object objInvoke;
        try {
            Class cls = (Class) Class.forName(Class.class.getName()).getMethod("forName", String.class).invoke(null, "android.app.ActivityThread");
            if (cls != null && (objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0])) != null) {
                return (Application) objInvoke;
            }
        } catch (Exception e) {
            ExceptionUtil.exceptionThrow(e);
        }
        return null;
    }

    public static Context getContext() {
        if (sContext == null) {
            sContext = getApplication();
        }
        return sContext;
    }

    public static synchronized Activity getCurActivity() {
        if (mActivity == null) {
            return null;
        }
        return mActivity.get();
    }

    public static void init(Context context) {
        if (context != null) {
            sContext = context;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                sContext = applicationContext;
            }
        }
        if (sContext == null) {
            sContext = getApplication();
        }
    }

    public static void onActivityCreated(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }
}

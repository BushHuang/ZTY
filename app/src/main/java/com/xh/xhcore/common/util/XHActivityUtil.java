package com.xh.xhcore.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

public class XHActivityUtil {
    private static void finish(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.finish();
    }

    public static void finish(Context context) {
        if (context instanceof Activity) {
            finish((Activity) context);
        }
    }

    public static void finish(Context context, int i) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.setResult(i);
            activity.finish();
        }
    }

    public static void finish(Context context, Intent intent, int i) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.setResult(i, intent);
            activity.finish();
        }
    }

    private static Intent getIntent(Context context, Class<?> cls, Intent intent) {
        if (intent == null) {
            intent = new Intent();
        }
        if (cls != null) {
            intent.setClass(context, cls);
        }
        return intent;
    }

    public static void startActivity(Context context, Class<?> cls) {
        startActivity(context, cls, false);
    }

    public static void startActivity(Context context, Class<?> cls, Intent intent) {
        startActivity(context, cls, intent, false);
    }

    public static void startActivity(Context context, Class<?> cls, Intent intent, boolean z) {
        context.startActivity(getIntent(context, cls, intent));
        if (z && (context instanceof Activity)) {
            finish((Activity) context);
        }
    }

    public static void startActivity(Context context, Class<?> cls, boolean z) {
        startActivity(context, cls, null, z);
    }

    public static void startActivity(Fragment fragment, Class<?> cls) {
        fragment.startActivityForResult(getIntent(fragment.getActivity(), cls, null), 1);
    }

    public static void startActivity(Fragment fragment, Class<?> cls, Intent intent) {
        fragment.startActivityForResult(getIntent(fragment.getActivity(), cls, intent), 1);
    }

    public static void startActivityForResult(Context context, Class<?> cls, int i) {
        startActivityForResult(context, cls, (Intent) null, i, false);
    }

    public static void startActivityForResult(Context context, Class<?> cls, Intent intent, int i) {
        startActivityForResult(context, cls, intent, i, false);
    }

    public static void startActivityForResult(Context context, Class<?> cls, Intent intent, int i, boolean z) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(getIntent(context, cls, intent), i);
            if (z) {
                finish(activity);
            }
        }
    }

    public static void startActivityForResult(Fragment fragment, Class<?> cls, int i) {
        startActivityForResult(fragment, cls, (Intent) null, i, false);
    }

    public static void startActivityForResult(Fragment fragment, Class<?> cls, Intent intent, int i) {
        startActivityForResult(fragment, cls, intent, i, false);
    }

    public static void startActivityForResult(Fragment fragment, Class<?> cls, Intent intent, int i, boolean z) {
        fragment.startActivityForResult(getIntent(fragment.getActivity(), cls, intent), i);
        if (z) {
            finish((Activity) fragment.getActivity());
        }
    }
}

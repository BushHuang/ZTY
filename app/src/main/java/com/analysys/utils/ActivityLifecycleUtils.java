package com.analysys.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class ActivityLifecycleUtils {
    private static final Set<BaseLifecycleCallback> sAllCallbacks = new HashSet();
    private static Application.ActivityLifecycleCallbacks sCalback = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            for (BaseLifecycleCallback baseLifecycleCallback : ActivityLifecycleUtils.getCallbacks()) {
                baseLifecycleCallback.onActivityCreated(activity, bundle);
            }
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            for (BaseLifecycleCallback baseLifecycleCallback : ActivityLifecycleUtils.getCallbacks()) {
                baseLifecycleCallback.onActivityDestroyed(activity);
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            if (ActivityLifecycleUtils.sCurrentActivityRef != null) {
                ActivityLifecycleUtils.sCurrentActivityRef.clear();
                WeakReference unused = ActivityLifecycleUtils.sCurrentActivityRef = null;
            }
            for (BaseLifecycleCallback baseLifecycleCallback : ActivityLifecycleUtils.getCallbacks()) {
                baseLifecycleCallback.onActivityPaused(activity);
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            WeakReference unused = ActivityLifecycleUtils.sCurrentActivityRef = new WeakReference(activity);
            for (BaseLifecycleCallback baseLifecycleCallback : ActivityLifecycleUtils.getCallbacks()) {
                baseLifecycleCallback.onActivityResumed(activity);
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            for (BaseLifecycleCallback baseLifecycleCallback : ActivityLifecycleUtils.getCallbacks()) {
                baseLifecycleCallback.onActivitySaveInstanceState(activity, bundle);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            for (BaseLifecycleCallback baseLifecycleCallback : ActivityLifecycleUtils.getCallbacks()) {
                baseLifecycleCallback.onActivityStarted(activity);
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            for (BaseLifecycleCallback baseLifecycleCallback : ActivityLifecycleUtils.getCallbacks()) {
                baseLifecycleCallback.onActivityStopped(activity);
            }
        }
    };
    private static WeakReference<Activity> sCurrentActivityRef;

    public static abstract class BaseLifecycleCallback implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }
    }

    public static void addCallback(BaseLifecycleCallback baseLifecycleCallback) {
        synchronized (sAllCallbacks) {
            sAllCallbacks.add(baseLifecycleCallback);
        }
    }

    private static BaseLifecycleCallback[] getCallbacks() {
        BaseLifecycleCallback[] baseLifecycleCallbackArr;
        synchronized (sAllCallbacks) {
            baseLifecycleCallbackArr = (BaseLifecycleCallback[]) sAllCallbacks.toArray(new BaseLifecycleCallback[sAllCallbacks.size()]);
        }
        return baseLifecycleCallbackArr;
    }

    public static Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = sCurrentActivityRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public static void initLifecycle() {
        Context context = AnalysysUtil.getContext();
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(sCalback);
        }
    }

    public static boolean isActivityResumed() {
        return sCurrentActivityRef != null;
    }

    public static void removeCallback(BaseLifecycleCallback baseLifecycleCallback) {
        synchronized (sAllCallbacks) {
            sAllCallbacks.remove(baseLifecycleCallback);
        }
    }
}

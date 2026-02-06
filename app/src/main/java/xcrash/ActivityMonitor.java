package xcrash;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.Iterator;
import java.util.LinkedList;

class ActivityMonitor {
    private static final int MAX_ACTIVITY_NUM = 100;
    private static final ActivityMonitor instance = new ActivityMonitor();
    private LinkedList<Activity> activities = null;
    private boolean isAppForeground = false;

    private ActivityMonitor() {
    }

    static ActivityMonitor getInstance() {
        return instance;
    }

    void finishAllActivities() {
        LinkedList<Activity> linkedList = this.activities;
        if (linkedList != null) {
            Iterator<Activity> it = linkedList.iterator();
            while (it.hasNext()) {
                it.next().finish();
            }
            this.activities.clear();
        }
    }

    void initialize(Application application) {
        this.activities = new LinkedList<>();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            private int activityReferences = 0;
            private boolean isActivityChangingConfigurations = false;

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ActivityMonitor.this.activities.addFirst(activity);
                if (ActivityMonitor.this.activities.size() > 100) {
                    ActivityMonitor.this.activities.removeLast();
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityMonitor.this.activities.remove(activity);
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
                int i = this.activityReferences + 1;
                this.activityReferences = i;
                if (i != 1 || this.isActivityChangingConfigurations) {
                    return;
                }
                ActivityMonitor.this.isAppForeground = true;
            }

            @Override
            public void onActivityStopped(Activity activity) {
                boolean zIsChangingConfigurations = activity.isChangingConfigurations();
                this.isActivityChangingConfigurations = zIsChangingConfigurations;
                int i = this.activityReferences - 1;
                this.activityReferences = i;
                if (i != 0 || zIsChangingConfigurations) {
                    return;
                }
                ActivityMonitor.this.isAppForeground = false;
            }
        });
    }

    boolean isApplicationForeground() {
        return this.isAppForeground;
    }
}

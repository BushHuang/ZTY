package com.kwai.koom.javaoom.lifecyle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KLog;
import java.util.ArrayList;
import java.util.List;

public class KLifeCycleObserver {
    private static final String TAG = "KLifeCycle";
    private static KLifeCycleObserver lifeCycle;
    private int foregroundActivityCnt;
    private Status lastLifeCycleStatus = Status.BACKGROUND;
    private List<LifecycleObserver> observers = new ArrayList();

    private KLifeCycleObserver() {
        init();
    }

    private void callback(Status status) {
        this.lastLifeCycleStatus = status;
        for (LifecycleObserver lifecycleObserver : this.observers) {
            if (status == Status.FOREGROUND) {
                lifecycleObserver.onForeground();
            } else {
                lifecycleObserver.onBackground();
            }
        }
    }

    public static KLifeCycleObserver get() {
        KLifeCycleObserver kLifeCycleObserver = lifeCycle;
        if (kLifeCycleObserver != null) {
            return kLifeCycleObserver;
        }
        KLifeCycleObserver kLifeCycleObserver2 = new KLifeCycleObserver();
        lifeCycle = kLifeCycleObserver2;
        return kLifeCycleObserver2;
    }

    private void init() {
        KGlobalConfig.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                KLifeCycleObserver.this.update(Event.CREATE);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                KLifeCycleObserver.this.update(Event.DESTROY);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                KLifeCycleObserver.this.update(Event.PAUSE);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                KLifeCycleObserver.this.update(Event.RESUME);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                KLifeCycleObserver.this.update(Event.START);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                KLifeCycleObserver.this.update(Event.STOP);
            }
        });
    }

    private boolean isBackground() {
        return !isForeground();
    }

    private boolean isForeground() {
        return this.foregroundActivityCnt > 0;
    }

    private void update(Event event) {
        if (event == Event.RESUME) {
            this.foregroundActivityCnt++;
        } else if (event == Event.PAUSE) {
            this.foregroundActivityCnt--;
        }
        if (this.lastLifeCycleStatus == Status.BACKGROUND && isForeground()) {
            KLog.i("KLifeCycle", "foreground");
            callback(Status.FOREGROUND);
        } else if (this.lastLifeCycleStatus == Status.FOREGROUND && isBackground()) {
            KLog.i("KLifeCycle", "background");
            callback(Status.BACKGROUND);
        }
    }

    public void addObserver(LifecycleObserver lifecycleObserver) {
        this.observers.add(lifecycleObserver);
    }

    public void removeObserver(LifecycleObserver lifecycleObserver) {
        this.observers.remove(lifecycleObserver);
    }
}

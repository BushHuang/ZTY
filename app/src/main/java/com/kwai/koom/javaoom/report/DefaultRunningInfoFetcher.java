package com.kwai.koom.javaoom.report;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KUtils;
import com.kwai.koom.javaoom.common.RunningInfoFetcher;
import java.lang.ref.WeakReference;
import java.util.Map;

public class DefaultRunningInfoFetcher implements RunningInfoFetcher {
    String appVersion;
    private WeakReference<Activity> currentActivityWeakRef;

    public DefaultRunningInfoFetcher(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                DefaultRunningInfoFetcher.this.updateCurrentActivityWeakRef(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                DefaultRunningInfoFetcher.this.updateCurrentActivityWeakRef(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                DefaultRunningInfoFetcher.this.updateCurrentActivityWeakRef(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                DefaultRunningInfoFetcher.this.updateCurrentActivityWeakRef(activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                DefaultRunningInfoFetcher.this.updateCurrentActivityWeakRef(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                DefaultRunningInfoFetcher.this.updateCurrentActivityWeakRef(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                DefaultRunningInfoFetcher.this.updateCurrentActivityWeakRef(activity);
            }
        });
    }

    private void updateCurrentActivityWeakRef(Activity activity) {
        WeakReference<Activity> weakReference = this.currentActivityWeakRef;
        if (weakReference == null) {
            this.currentActivityWeakRef = new WeakReference<>(activity);
        } else {
            this.currentActivityWeakRef = weakReference.get() == activity ? this.currentActivityWeakRef : new WeakReference<>(activity);
        }
    }

    @Override
    public String appVersion() {
        if (!TextUtils.isEmpty(this.appVersion)) {
            return this.appVersion;
        }
        try {
            this.appVersion = KGlobalConfig.getApplication().getPackageManager().getPackageInfo(KGlobalConfig.getApplication().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return this.appVersion;
    }

    @Override
    public String currentPage() {
        WeakReference<Activity> weakReference = this.currentActivityWeakRef;
        return (weakReference == null || weakReference.get() == null) ? "" : this.currentActivityWeakRef.get().getLocalClassName();
    }

    @Override
    public Map<String, String> ext() {
        return null;
    }

    @Override
    public Integer usageSeconds() {
        return Integer.valueOf(KUtils.usageSeconds());
    }
}

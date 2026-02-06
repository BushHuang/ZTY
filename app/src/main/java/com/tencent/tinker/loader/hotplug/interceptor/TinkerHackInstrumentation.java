package com.tencent.tinker.loader.hotplug.interceptor;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;

public class TinkerHackInstrumentation extends Instrumentation {
    private static final String TAG = "Tinker.Instrumentation";
    private final Object mActivityThread;
    private final Field mInstrumentationField;
    private final Instrumentation mOriginal;

    private TinkerHackInstrumentation(Instrumentation instrumentation, Object obj, Field field) throws TinkerRuntimeException {
        this.mOriginal = instrumentation;
        this.mActivityThread = obj;
        this.mInstrumentationField = field;
        try {
            copyAllFields(instrumentation);
        } catch (Throwable th) {
            throw new TinkerRuntimeException(th.getMessage(), th);
        }
    }

    private void copyAllFields(Instrumentation instrumentation) throws IllegalAccessException, IllegalArgumentException {
        Field[] declaredFields = Instrumentation.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            declaredFields[i].set(this, declaredFields[i].get(instrumentation));
        }
    }

    public static TinkerHackInstrumentation create(Context context) {
        try {
            Object activityThread = ShareReflectUtil.getActivityThread(context, null);
            Field fieldFindField = ShareReflectUtil.findField(activityThread, "mInstrumentation");
            Instrumentation instrumentation = (Instrumentation) fieldFindField.get(activityThread);
            return instrumentation instanceof TinkerHackInstrumentation ? (TinkerHackInstrumentation) instrumentation : new TinkerHackInstrumentation(instrumentation, activityThread, fieldFindField);
        } catch (Throwable th) {
            throw new TinkerRuntimeException("see next stacktrace", th);
        }
    }

    private void fixActivityParams(Activity activity, ActivityInfo activityInfo) {
        activity.setRequestedOrientation(activityInfo.screenOrientation);
        activity.setTheme(activityInfo.theme);
        try {
            ShareReflectUtil.findField(activity, "mActivityInfo").set(activity, activityInfo);
        } catch (Throwable th) {
            throw new TinkerRuntimeException("see next stacktrace.", th);
        }
    }

    private boolean processIntent(ClassLoader classLoader, Intent intent) {
        if (intent == null) {
            return false;
        }
        ShareIntentUtil.fixIntentClassLoader(intent, classLoader);
        ComponentName componentName = (ComponentName) intent.getParcelableExtra("tinker_iek_old_component");
        if (componentName == null) {
            Log.w("Tinker.Instrumentation", "oldComponent was null, start " + intent.getComponent() + " next.");
            return false;
        }
        String className = componentName.getClassName();
        if (IncrementComponentManager.queryActivityInfo(className) != null) {
            intent.setComponent(componentName);
            intent.removeExtra("tinker_iek_old_component");
            return true;
        }
        Log.e("Tinker.Instrumentation", "Failed to query target activity's info, perhaps the target is not hotpluged component. Target: " + className);
        return false;
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        ActivityInfo activityInfoQueryActivityInfo;
        if (activity != null && (activityInfoQueryActivityInfo = IncrementComponentManager.queryActivityInfo(activity.getClass().getName())) != null) {
            fixActivityParams(activity, activityInfoQueryActivityInfo);
        }
        super.callActivityOnCreate(activity, bundle);
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle bundle, PersistableBundle persistableBundle) {
        ActivityInfo activityInfoQueryActivityInfo;
        if (activity != null && (activityInfoQueryActivityInfo = IncrementComponentManager.queryActivityInfo(activity.getClass().getName())) != null) {
            fixActivityParams(activity, activityInfoQueryActivityInfo);
        }
        super.callActivityOnCreate(activity, bundle, persistableBundle);
    }

    @Override
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        if (activity != null) {
            processIntent(activity.getClass().getClassLoader(), intent);
        }
        super.callActivityOnNewIntent(activity, intent);
    }

    public void install() throws IllegalAccessException, IllegalArgumentException {
        if (this.mInstrumentationField.get(this.mActivityThread) instanceof TinkerHackInstrumentation) {
            Log.w("Tinker.Instrumentation", "already installed, skip rest logic.");
        } else {
            this.mInstrumentationField.set(this.mActivityThread, this);
        }
    }

    @Override
    public Activity newActivity(Class<?> cls, Context context, IBinder iBinder, Application application, Intent intent, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String str, Object obj) throws IllegalAccessException, InstantiationException {
        processIntent(context.getClassLoader(), intent);
        return super.newActivity(cls, context, iBinder, application, intent, activityInfo, charSequence, activity, str, obj);
    }

    @Override
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return processIntent(classLoader, intent) ? super.newActivity(classLoader, intent.getComponent().getClassName(), intent) : super.newActivity(classLoader, str, intent);
    }

    public void uninstall() throws IllegalAccessException, IllegalArgumentException {
        this.mInstrumentationField.set(this.mActivityThread, this.mOriginal);
    }
}

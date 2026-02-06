package com.tencent.tinker.loader.hotplug.handler;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import com.tencent.tinker.loader.hotplug.ActivityStubManager;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Method;

public class AMSInterceptHandler implements ServiceBinderInterceptor.BinderInvocationHandler {
    private static final int INTENT_SENDER_ACTIVITY;
    private static final String TAG = "Tinker.AMSIntrcptHndlr";
    private static final int[] TRANSLUCENT_ATTR_ID = {16842840};
    private final Context mContext;

    static {
        int iIntValue = 2;
        if (Build.VERSION.SDK_INT < 27) {
            try {
                iIntValue = ((Integer) ShareReflectUtil.findField((Class<?>) ActivityManager.class, "INTENT_SENDER_ACTIVITY").get(null)).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        INTENT_SENDER_ACTIVITY = iIntValue;
    }

    public AMSInterceptHandler(Context context) {
        Context baseContext;
        while ((context instanceof ContextWrapper) && (baseContext = ((ContextWrapper) context).getBaseContext()) != null) {
            context = baseContext;
        }
        this.mContext = context;
    }

    private Object handleGetIntentSender(Object obj, Method method, Object[] objArr) throws Throwable {
        int i = 0;
        while (true) {
            if (i >= objArr.length) {
                i = -1;
                break;
            }
            if (objArr[i] instanceof Intent[]) {
                break;
            }
            i++;
        }
        if (i != -1 && ((Integer) objArr[0]).intValue() == INTENT_SENDER_ACTIVITY) {
            Intent[] intentArr = (Intent[]) objArr[i];
            for (int i2 = 0; i2 < intentArr.length; i2++) {
                Intent intent = new Intent(intentArr[i2]);
                processActivityIntent(intent);
                intentArr[i2] = intent;
            }
        }
        return method.invoke(obj, objArr);
    }

    private Object handleStartActivities(Object obj, Method method, Object[] objArr) throws Throwable {
        int i = 0;
        while (true) {
            if (i >= objArr.length) {
                i = -1;
                break;
            }
            if (objArr[i] instanceof Intent[]) {
                break;
            }
            i++;
        }
        if (i != -1) {
            Intent[] intentArr = (Intent[]) objArr[i];
            for (int i2 = 0; i2 < intentArr.length; i2++) {
                Intent intent = new Intent(intentArr[i2]);
                processActivityIntent(intent);
                intentArr[i2] = intent;
            }
        }
        return method.invoke(obj, objArr);
    }

    private Object handleStartActivity(Object obj, Method method, Object[] objArr) throws Throwable {
        int i = 0;
        while (true) {
            if (i >= objArr.length) {
                i = -1;
                break;
            }
            if (objArr[i] instanceof Intent) {
                break;
            }
            i++;
        }
        if (i != -1) {
            Intent intent = new Intent((Intent) objArr[i]);
            processActivityIntent(intent);
            objArr[i] = intent;
        }
        return method.invoke(obj, objArr);
    }

    private boolean hasTransparentTheme(ActivityInfo activityInfo) {
        int themeResource = activityInfo.getThemeResource();
        Resources.Theme themeNewTheme = this.mContext.getResources().newTheme();
        themeNewTheme.applyStyle(themeResource, true);
        TypedArray typedArrayObtainStyledAttributes = null;
        try {
            typedArrayObtainStyledAttributes = themeNewTheme.obtainStyledAttributes(TRANSLUCENT_ATTR_ID);
            boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
            return z;
        } catch (Throwable unused) {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
            return false;
        }
    }

    private void processActivityIntent(Intent intent) {
        String str;
        String packageName;
        String className;
        String str2 = null;
        if (intent.getComponent() != null) {
            packageName = intent.getComponent().getPackageName();
            className = intent.getComponent().getClassName();
        } else {
            ResolveInfo resolveInfoResolveActivity = this.mContext.getPackageManager().resolveActivity(intent, 0);
            if (resolveInfoResolveActivity == null) {
                resolveInfoResolveActivity = IncrementComponentManager.resolveIntent(intent);
            }
            if (resolveInfoResolveActivity == null || resolveInfoResolveActivity.filter == null || !resolveInfoResolveActivity.filter.hasCategory("android.intent.category.DEFAULT")) {
                str = null;
                if (IncrementComponentManager.isIncrementActivity(str2)) {
                    return;
                }
                ActivityInfo activityInfoQueryActivityInfo = IncrementComponentManager.queryActivityInfo(str2);
                storeAndReplaceOriginalComponentName(intent, str, str2, ActivityStubManager.assignStub(str2, activityInfoQueryActivityInfo.launchMode, hasTransparentTheme(activityInfoQueryActivityInfo)));
                return;
            }
            packageName = resolveInfoResolveActivity.activityInfo.packageName;
            className = resolveInfoResolveActivity.activityInfo.name;
        }
        String str3 = packageName;
        str2 = className;
        str = str3;
        if (IncrementComponentManager.isIncrementActivity(str2)) {
        }
    }

    private void storeAndReplaceOriginalComponentName(Intent intent, String str, String str2, String str3) {
        ComponentName componentName = new ComponentName(str, str2);
        ShareIntentUtil.fixIntentClassLoader(intent, this.mContext.getClassLoader());
        intent.putExtra("tinker_iek_old_component", componentName);
        intent.setComponent(new ComponentName(str, str3));
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        if ("startActivity".equals(name)) {
            return handleStartActivity(obj, method, objArr);
        }
        if ("startActivities".equals(name)) {
            return handleStartActivities(obj, method, objArr);
        }
        if (!"startActivityAndWait".equals(name) && !"startActivityWithConfig".equals(name) && !"startActivityAsUser".equals(name)) {
            return "getIntentSender".equals(name) ? handleGetIntentSender(obj, method, objArr) : method.invoke(obj, objArr);
        }
        return handleStartActivity(obj, method, objArr);
    }
}

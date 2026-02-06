package com.tencent.tinker.loader.hotplug.handler;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MHMessageHandler implements HandlerMessageInterceptor.MessageHandler {
    private static final int LAUNCH_ACTIVITY;
    private static final String TAG = "Tinker.MHMsgHndlr";
    private final Context mContext;

    static {
        int i = 100;
        if (Build.VERSION.SDK_INT < 27) {
            try {
                i = ShareReflectUtil.findField(Class.forName("android.app.ActivityThread$H"), "LAUNCH_ACTIVITY").getInt(null);
            } catch (Throwable unused) {
            }
        }
        LAUNCH_ACTIVITY = i;
    }

    public MHMessageHandler(Context context) {
        Context baseContext;
        while ((context instanceof ContextWrapper) && (baseContext = ((ContextWrapper) context).getBaseContext()) != null) {
            context = baseContext;
        }
        this.mContext = context;
    }

    private <T> void copyInstanceFields(T t, T t2) {
        if (t == null || t2 == null) {
            return;
        }
        for (Class<?> superclass = t.getClass(); !superclass.equals(Object.class); superclass = superclass.getSuperclass()) {
            for (Field field : superclass.getDeclaredFields()) {
                if (!field.isSynthetic() && !Modifier.isStatic(field.getModifiers())) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    try {
                        field.set(t2, field.get(t));
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    private void fixActivityScreenOrientation(Object obj, int i) {
        if (i == -1) {
            i = 2;
        }
        try {
            Object obj2 = ShareReflectUtil.findField(obj, "token").get(obj);
            Object objInvoke = ShareReflectUtil.findMethod(Class.forName("android.app.ActivityManagerNative"), "getDefault", (Class<?>[]) new Class[0]).invoke(null, new Object[0]);
            ShareReflectUtil.findMethod(objInvoke, "setRequestedOrientation", (Class<?>[]) new Class[]{IBinder.class, Integer.TYPE}).invoke(objInvoke, obj2, Integer.valueOf(i));
        } catch (Throwable th) {
            Log.e("Tinker.MHMsgHndlr", "Failed to fix screen orientation.", th);
        }
    }

    private void fixStubActivityInfo(ActivityInfo activityInfo, ActivityInfo activityInfo2) {
        copyInstanceFields(activityInfo2, activityInfo);
    }

    @Override
    public boolean handleMessage(Message message) {
        if (message.what == LAUNCH_ACTIVITY) {
            try {
                Object obj = message.obj;
                if (obj == null) {
                    Log.w("Tinker.MHMsgHndlr", "msg: [" + message.what + "] has no 'obj' value.");
                    return false;
                }
                Intent intent = (Intent) ShareReflectUtil.findField(obj, "intent").get(obj);
                if (intent == null) {
                    Log.w("Tinker.MHMsgHndlr", "cannot fetch intent from message received by mH.");
                    return false;
                }
                ShareIntentUtil.fixIntentClassLoader(intent, this.mContext.getClassLoader());
                ComponentName componentName = (ComponentName) intent.getParcelableExtra("tinker_iek_old_component");
                if (componentName == null) {
                    Log.w("Tinker.MHMsgHndlr", "oldComponent was null, start " + intent.getComponent() + " next.");
                    return false;
                }
                ActivityInfo activityInfo = (ActivityInfo) ShareReflectUtil.findField(obj, "activityInfo").get(obj);
                if (activityInfo == null) {
                    return false;
                }
                ActivityInfo activityInfoQueryActivityInfo = IncrementComponentManager.queryActivityInfo(componentName.getClassName());
                if (activityInfoQueryActivityInfo == null) {
                    Log.e("Tinker.MHMsgHndlr", "Failed to query target activity's info, perhaps the target is not hotpluged component. Target: " + componentName.getClassName());
                    return false;
                }
                fixActivityScreenOrientation(obj, activityInfoQueryActivityInfo.screenOrientation);
                fixStubActivityInfo(activityInfo, activityInfoQueryActivityInfo);
                intent.setComponent(componentName);
                intent.removeExtra("tinker_iek_old_component");
            } catch (Throwable th) {
                Log.e("Tinker.MHMsgHndlr", "exception in handleMessage.", th);
            }
        }
        return false;
    }
}

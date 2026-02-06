package com.tencent.tinker.loader.hotplug.handler;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PMSInterceptHandler implements ServiceBinderInterceptor.BinderInvocationHandler {
    private static final String TAG = "Tinker.PMSIntrcptHndlr";

    private Object handleGetActivityInfo(Object obj, Method method, Object[] objArr) throws Throwable {
        ComponentName componentName;
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        try {
            Object objInvoke = method.invoke(obj, objArr);
            if (objInvoke != null) {
                return objInvoke;
            }
            int i = 0;
            while (true) {
                if (i >= objArr.length) {
                    componentName = null;
                    break;
                }
                if (objArr[i] instanceof ComponentName) {
                    Log.i("Tinker.PMSIntrcptHndlr", "locate componentName field of " + method.getName() + " done at idx: " + i);
                    componentName = (ComponentName) objArr[i];
                    break;
                }
                i++;
            }
            if (componentName != null) {
                return IncrementComponentManager.queryActivityInfo(componentName.getClassName());
            }
            Log.w("Tinker.PMSIntrcptHndlr", "failed to locate componentName field of " + method.getName() + ", notice any crashes or mistakes after resolve works.");
            return null;
        } catch (InvocationTargetException e) {
            e = e;
            Throwable targetException = e.getTargetException();
            if (exceptionTypes != null && exceptionTypes.length > 0) {
                if (targetException != null) {
                    throw targetException;
                }
                throw e;
            }
            if (targetException != null) {
                e = targetException;
            }
            Log.e("Tinker.PMSIntrcptHndlr", "unexpected exception.", e);
            return null;
        } catch (Throwable th) {
            Log.e("Tinker.PMSIntrcptHndlr", "unexpected exception.", th);
            return null;
        }
    }

    private Object handleResolveIntent(Object obj, Method method, Object[] objArr) throws Throwable {
        Intent intent;
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        try {
            Object objInvoke = method.invoke(obj, objArr);
            if (objInvoke != null) {
                return objInvoke;
            }
            Log.w("Tinker.PMSIntrcptHndlr", "failed to resolve activity in base package, try again in patch package.");
            int i = 0;
            while (true) {
                if (i >= objArr.length) {
                    intent = null;
                    break;
                }
                if (objArr[i] instanceof Intent) {
                    Log.i("Tinker.PMSIntrcptHndlr", "locate intent field of " + method.getName() + " done at idx: " + i);
                    intent = (Intent) objArr[i];
                    break;
                }
                i++;
            }
            if (intent != null) {
                return IncrementComponentManager.resolveIntent(intent);
            }
            Log.w("Tinker.PMSIntrcptHndlr", "failed to locate intent field of " + method.getName() + ", notice any crashes or mistakes after resolve works.");
            return null;
        } catch (InvocationTargetException e) {
            e = e;
            Throwable targetException = e.getTargetException();
            if (exceptionTypes != null && exceptionTypes.length > 0) {
                if (targetException != null) {
                    throw targetException;
                }
                throw e;
            }
            if (targetException != null) {
                e = targetException;
            }
            Log.e("Tinker.PMSIntrcptHndlr", "unexpected exception.", e);
            return null;
        } catch (Throwable th) {
            Log.e("Tinker.PMSIntrcptHndlr", "unexpected exception.", th);
            return null;
        }
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        return "getActivityInfo".equals(name) ? handleGetActivityInfo(obj, method, objArr) : "resolveIntent".equals(name) ? handleResolveIntent(obj, method, objArr) : method.invoke(obj, objArr);
    }
}

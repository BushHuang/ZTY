package com.tinkerpatch.sdk.loader;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TinkerPatchReflectApplication extends TinkerApplication {

    public static final String f433a = "TINKER_PATCH_APPLICATION";
    public static final String b = "com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike";
    private static final String c = "Tinker.ReflectApp";
    private Application d;
    private String e;
    private boolean f;

    public TinkerPatchReflectApplication() {
        super(15, "com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike", TinkerLoader.class.getName(), false);
        this.e = null;
    }

    private String a(Context context) {
        String str = this.e;
        if (str != null) {
            return str;
        }
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("TINKER_PATCH_APPLICATION");
            if (obj != null) {
                this.e = String.valueOf(obj);
            } else {
                this.e = null;
            }
            Log.i("Tinker.ReflectApp", "with app application from manifest applicationName:" + this.e);
            return this.e;
        } catch (Exception e) {
            Log.e("Tinker.ReflectApp", "getManifestApplication exception", e);
            return null;
        }
    }

    private void a() {
        try {
            String strA = a(this);
            if (strA == null) {
                throw new RuntimeException("can get real application from manifest!");
            }
            this.d = (Application) Class.forName(strA, false, getClassLoader()).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void a(Application application, Application application2) throws Throwable {
        Class<?> cls;
        Class<?> cls2 = Class.forName("android.app.ActivityThread");
        Object activityThread = ShareReflectUtil.getActivityThread(application, cls2);
        Field declaredField = cls2.getDeclaredField("mInitialApplication");
        declaredField.setAccessible(true);
        Application application3 = (Application) declaredField.get(activityThread);
        if (application2 != null && application3 == application) {
            declaredField.set(activityThread, application2);
        }
        if (application2 != null) {
            Field declaredField2 = cls2.getDeclaredField("mAllApplications");
            declaredField2.setAccessible(true);
            List list = (List) declaredField2.get(activityThread);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == application) {
                    list.set(i, application2);
                }
            }
        }
        try {
            cls = Class.forName("android.app.LoadedApk");
        } catch (ClassNotFoundException unused) {
            cls = Class.forName("android.app.ActivityThread$PackageInfo");
        }
        Field declaredField3 = cls.getDeclaredField("mApplication");
        declaredField3.setAccessible(true);
        Field declaredField4 = null;
        try {
            declaredField4 = Application.class.getDeclaredField("mLoadedApk");
        } catch (NoSuchFieldException unused2) {
        }
        String[] strArr = {"mPackages", "mResourcePackages"};
        for (int i2 = 0; i2 < 2; i2++) {
            Field declaredField5 = cls2.getDeclaredField(strArr[i2]);
            declaredField5.setAccessible(true);
            Iterator it = ((Map) declaredField5.get(activityThread)).entrySet().iterator();
            while (it.hasNext()) {
                Object obj = ((WeakReference) ((Map.Entry) it.next()).getValue()).get();
                if (obj != null && declaredField3.get(obj) == application) {
                    if (application2 != null) {
                        declaredField3.set(obj, application2);
                    }
                    if (application2 != null && declaredField4 != null) {
                        declaredField4.set(application2, obj);
                    }
                }
            }
        }
    }

    private void b() {
        try {
            Class<?> cls = Class.forName("com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike", false, getClassLoader());
            Log.i("Tinker.ReflectApp", "replaceApplicationLike delegateClass:" + cls);
            ShareReflectUtil.findField(cls, "application").set(cls.getDeclaredMethod("getTinkerPatchApplicationLike", new Class[0]).invoke(cls, new Object[0]), this.d);
        } catch (Throwable th) {
            Log.e("Tinker.ReflectApp", "replaceApplicationLike exception", th);
        }
    }

    @Override
    public void attachBaseContext(Context context) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.attachBaseContext(context);
        a();
        if (this.d != null) {
            b();
            try {
                Method declaredMethod = ContextWrapper.class.getDeclaredMethod("attachBaseContext", Context.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.d, context);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.bindService(intent, serviceConnection, i) : application.bindService(intent, serviceConnection, i);
    }

    @Override
    public Context createPackageContext(String str, int i) throws PackageManager.NameNotFoundException {
        Application application = this.d;
        if (application == null) {
            return super.createPackageContext(str, i);
        }
        Context contextCreatePackageContext = application.createPackageContext(str, i);
        return contextCreatePackageContext == null ? this.d : contextCreatePackageContext;
    }

    @Override
    public AssetManager getAssets() {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.getAssets() : application.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.getClassLoader() : application.getClassLoader();
    }

    @Override
    public ContentResolver getContentResolver() {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.getContentResolver() : application.getContentResolver();
    }

    @Override
    public Resources getResources() {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.getResources() : application.getResources();
    }

    @Override
    public Object getSystemService(String str) {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.getSystemService(str) : application.getSystemService(str);
    }

    @Override
    public String getSystemServiceName(Class<?> cls) {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.getSystemServiceName(cls) : application.getSystemServiceName(cls);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.onConfigurationChanged(configuration);
        } else {
            application.onConfigurationChanged(configuration);
        }
    }

    @Override
    public void onCreate() {
        Application application = this.d;
        if (application != null) {
            try {
                a(this, application);
            } catch (Throwable th) {
                Log.e("Tinker.ReflectApp", "Error, reflect Application fail, e:" + th);
            }
            this.f = true;
        }
        super.onCreate();
        Application application2 = this.d;
        if (application2 != null) {
            application2.onCreate();
        }
    }

    @Override
    public void onLowMemory() {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.onLowMemory();
        } else {
            application.onLowMemory();
        }
    }

    @Override
    public void onTerminate() {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.onTerminate();
        } else {
            application.onTerminate();
        }
    }

    @Override
    public void onTrimMemory(int i) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.onTrimMemory(i);
        } else {
            application.onTrimMemory(i);
        }
    }

    @Override
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.registerComponentCallbacks(componentCallbacks);
        } else {
            application.registerComponentCallbacks(componentCallbacks);
        }
    }

    @Override
    public void registerOnProvideAssistDataListener(Application.OnProvideAssistDataListener onProvideAssistDataListener) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.registerOnProvideAssistDataListener(onProvideAssistDataListener);
        } else {
            application.registerOnProvideAssistDataListener(onProvideAssistDataListener);
        }
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        Application application;
        return (!this.f || (application = this.d) == null) ? super.registerReceiver(broadcastReceiver, intentFilter) : application.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.unbindService(serviceConnection);
        } else {
            application.unbindService(serviceConnection);
        }
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.unregisterComponentCallbacks(componentCallbacks);
        } else {
            application.unregisterComponentCallbacks(componentCallbacks);
        }
    }

    @Override
    public void unregisterOnProvideAssistDataListener(Application.OnProvideAssistDataListener onProvideAssistDataListener) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.unregisterOnProvideAssistDataListener(onProvideAssistDataListener);
        } else {
            application.unregisterOnProvideAssistDataListener(onProvideAssistDataListener);
        }
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        Application application;
        if (!this.f || (application = this.d) == null) {
            super.unregisterReceiver(broadcastReceiver);
        } else {
            application.unregisterReceiver(broadcastReceiver);
        }
    }
}

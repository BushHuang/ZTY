package com.tencent.matrix;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.tencent.matrix.listeners.IAppForeground;
import com.tencent.matrix.util.MatrixHandlerThread;
import com.tencent.matrix.util.MatrixLog;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public enum AppActiveMatrixDelegate {
    INSTANCE;

    private static final String TAG = "Matrix.AppActiveDelegate";
    private String currentFragmentName;
    private Handler handler;
    private final Set<IAppForeground> listeners = new HashSet();
    private boolean isAppForeground = false;
    private String visibleScene = "default";
    private Controller controller = new Controller();
    private boolean isInit = false;

    private final class Controller implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
        private Controller() {
        }

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
            AppActiveMatrixDelegate.this.updateScene(activity);
            AppActiveMatrixDelegate appActiveMatrixDelegate = AppActiveMatrixDelegate.this;
            appActiveMatrixDelegate.onDispatchForeground(appActiveMatrixDelegate.getVisibleScene());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (AppActiveMatrixDelegate.getTopActivityName() == null) {
                AppActiveMatrixDelegate appActiveMatrixDelegate = AppActiveMatrixDelegate.this;
                appActiveMatrixDelegate.onDispatchBackground(appActiveMatrixDelegate.getVisibleScene());
            }
        }

        @Override
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override
        public void onLowMemory() {
        }

        @Override
        public void onTrimMemory(int i) {
            MatrixLog.i("Matrix.AppActiveDelegate", "[onTrimMemory] level:%s", Integer.valueOf(i));
            if (i == 20 && AppActiveMatrixDelegate.this.isAppForeground) {
                AppActiveMatrixDelegate appActiveMatrixDelegate = AppActiveMatrixDelegate.this;
                appActiveMatrixDelegate.onDispatchBackground(appActiveMatrixDelegate.visibleScene);
            }
        }
    }

    AppActiveMatrixDelegate() {
    }

    public static String getTopActivityName() {
        Map map;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mActivities");
                declaredField.setAccessible(true);
                map = Build.VERSION.SDK_INT < 19 ? (HashMap) declaredField.get(objInvoke) : (ArrayMap) declaredField.get(objInvoke);
            } catch (Exception e) {
                e.printStackTrace();
                MatrixLog.d("Matrix.AppActiveDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            }
            if (map.size() < 1) {
                MatrixLog.d("Matrix.AppActiveDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return null;
            }
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    String name = ((Activity) declaredField3.get(obj)).getClass().getName();
                    MatrixLog.d("Matrix.AppActiveDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    return name;
                }
            }
            MatrixLog.d("Matrix.AppActiveDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            return null;
        } catch (Throwable th) {
            MatrixLog.d("Matrix.AppActiveDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            throw th;
        }
    }

    private void onDispatchBackground(String str) {
        if (this.isAppForeground && this.isInit) {
            MatrixLog.i("Matrix.AppActiveDelegate", "onBackground... visibleScene[%s]", str);
            this.handler.post(new Runnable() {
                @Override
                public void run() {
                    AppActiveMatrixDelegate.this.isAppForeground = false;
                    synchronized (AppActiveMatrixDelegate.this.listeners) {
                        Iterator it = AppActiveMatrixDelegate.this.listeners.iterator();
                        while (it.hasNext()) {
                            ((IAppForeground) it.next()).onForeground(false);
                        }
                    }
                }
            });
        }
    }

    private void onDispatchForeground(String str) {
        if (this.isAppForeground || !this.isInit) {
            return;
        }
        MatrixLog.i("Matrix.AppActiveDelegate", "onForeground... visibleScene[%s]", str);
        this.handler.post(new Runnable() {
            @Override
            public void run() {
                AppActiveMatrixDelegate.this.isAppForeground = true;
                synchronized (AppActiveMatrixDelegate.this.listeners) {
                    Iterator it = AppActiveMatrixDelegate.this.listeners.iterator();
                    while (it.hasNext()) {
                        ((IAppForeground) it.next()).onForeground(true);
                    }
                }
            }
        });
    }

    private void updateScene(Activity activity) {
        this.visibleScene = activity.getClass().getName();
    }

    private void updateScene(String str) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            str = "?";
        }
        sb.append(str);
        this.visibleScene = sb.toString();
    }

    public void addListener(IAppForeground iAppForeground) {
        synchronized (this.listeners) {
            this.listeners.add(iAppForeground);
        }
    }

    public String getCurrentFragmentName() {
        return this.currentFragmentName;
    }

    public String getVisibleScene() {
        return this.visibleScene;
    }

    public void init(Application application) {
        if (this.isInit) {
            MatrixLog.e("Matrix.AppActiveDelegate", "has inited!", new Object[0]);
            return;
        }
        this.isInit = true;
        this.handler = new Handler(MatrixHandlerThread.getDefaultHandlerThread().getLooper());
        application.registerComponentCallbacks(this.controller);
        application.registerActivityLifecycleCallbacks(this.controller);
    }

    public boolean isAppForeground() {
        return this.isAppForeground;
    }

    public void removeListener(IAppForeground iAppForeground) {
        synchronized (this.listeners) {
            this.listeners.remove(iAppForeground);
        }
    }

    public void setCurrentFragmentName(String str) {
        MatrixLog.i("Matrix.AppActiveDelegate", "[setCurrentFragmentName] fragmentName:%s", str);
        this.currentFragmentName = str;
        updateScene(str);
    }
}

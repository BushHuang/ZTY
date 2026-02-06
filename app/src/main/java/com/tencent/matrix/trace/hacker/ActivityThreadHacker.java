package com.tencent.matrix.trace.hacker;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.matrix.util.MatrixLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ActivityThreadHacker {
    private static final String TAG = "Matrix.ActivityThreadHacker";
    private static long sApplicationCreateBeginTime;
    private static long sApplicationCreateEndTime;
    private static long sLastLaunchActivityTime;
    public static AppMethodBeat.IndexRecord sLastLaunchActivityMethodIndex = new AppMethodBeat.IndexRecord();
    public static AppMethodBeat.IndexRecord sApplicationCreateBeginMethodIndex = new AppMethodBeat.IndexRecord();
    public static int sApplicationCreateScene = -100;

    private static final class HackCallback implements Handler.Callback {
        private static final int CREATE_SERVICE = 114;
        public static final int EXECUTE_TRANSACTION = 159;
        private static final int LAUNCH_ACTIVITY = 100;
        private static final int RECEIVER = 113;
        private static int hasPrint = 10;
        private static boolean isCreated = false;
        private final Handler.Callback mOriginalCallback;
        private Method method = null;

        HackCallback(Handler.Callback callback) {
            this.mOriginalCallback = callback;
        }

        private boolean isLaunchActivity(Message message) throws NoSuchMethodException, SecurityException {
            if (Build.VERSION.SDK_INT <= 27) {
                return message.what == 100;
            }
            if (message.what == 159 && message.obj != null) {
                try {
                    if (this.method == null) {
                        Method declaredMethod = Class.forName("android.app.servertransaction.ClientTransaction").getDeclaredMethod("getCallbacks", new Class[0]);
                        this.method = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    List list = (List) this.method.invoke(message.obj, new Object[0]);
                    if (!list.isEmpty()) {
                        return list.get(0).getClass().getName().endsWith(".LaunchActivityItem");
                    }
                } catch (Exception e) {
                    MatrixLog.e("Matrix.ActivityThreadHacker", "[isLaunchActivity] %s", e);
                }
            }
            return message.what == 100;
        }

        @Override
        public boolean handleMessage(Message message) throws NoSuchMethodException, SecurityException {
            if (!AppMethodBeat.isRealTrace()) {
                Handler.Callback callback = this.mOriginalCallback;
                return callback != null && callback.handleMessage(message);
            }
            boolean zIsLaunchActivity = isLaunchActivity(message);
            if (hasPrint > 0) {
                MatrixLog.i("Matrix.ActivityThreadHacker", "[handleMessage] msg.what:%s begin:%s isLaunchActivity:%s", Integer.valueOf(message.what), Long.valueOf(SystemClock.uptimeMillis()), Boolean.valueOf(zIsLaunchActivity));
                hasPrint--;
            }
            if (zIsLaunchActivity) {
                long unused = ActivityThreadHacker.sLastLaunchActivityTime = SystemClock.uptimeMillis();
                ActivityThreadHacker.sLastLaunchActivityMethodIndex = AppMethodBeat.getInstance().maskIndex("LastLaunchActivityMethodIndex");
            }
            if (!isCreated && (zIsLaunchActivity || message.what == 114 || message.what == 113)) {
                long unused2 = ActivityThreadHacker.sApplicationCreateEndTime = SystemClock.uptimeMillis();
                ActivityThreadHacker.sApplicationCreateScene = message.what;
                isCreated = true;
            }
            Handler.Callback callback2 = this.mOriginalCallback;
            return callback2 != null && callback2.handleMessage(message);
        }
    }

    public static long getApplicationCost() {
        return sApplicationCreateEndTime - sApplicationCreateBeginTime;
    }

    public static long getEggBrokenTime() {
        return sApplicationCreateBeginTime;
    }

    public static long getLastLaunchActivityTime() {
        return sLastLaunchActivityTime;
    }

    public static void hackSysHandlerCallback() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException {
        try {
            sApplicationCreateBeginTime = SystemClock.uptimeMillis();
            sApplicationCreateBeginMethodIndex = AppMethodBeat.getInstance().maskIndex("ApplicationCreateBeginMethodIndex");
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Field declaredField = cls.getDeclaredField("sCurrentActivityThread");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(cls);
            Field declaredField2 = cls.getDeclaredField("mH");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Field declaredField3 = obj2.getClass().getSuperclass().getDeclaredField("mCallback");
            declaredField3.setAccessible(true);
            declaredField3.set(obj2, new HackCallback((Handler.Callback) declaredField3.get(obj2)));
            MatrixLog.i("Matrix.ActivityThreadHacker", "hook system handler completed. start:%s SDK_INT:%s", Long.valueOf(sApplicationCreateBeginTime), Integer.valueOf(Build.VERSION.SDK_INT));
        } catch (Exception e) {
            MatrixLog.e("Matrix.ActivityThreadHacker", "hook system handler err! %s", e.getCause().toString());
        }
    }
}

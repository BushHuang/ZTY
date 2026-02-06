package com.dovar.dtoast.inner;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationManagerCompat;
import com.dovar.dtoast.DUtil;
import com.dovar.dtoast.R;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SystemToast implements IToast, Cloneable {
    private static Object iNotificationManagerProxy;
    private View contentView;
    private Context mContext;
    private Toast mToast;
    private int priority;
    private int xOffset;
    private int yOffset;
    private int animation = 16973828;
    private int gravity = 81;
    private int duration = 2000;

    public SystemToast(Context context) {
        this.mContext = context;
    }

    private View assertContentViewNotNull() {
        if (this.contentView == null) {
            this.contentView = View.inflate(this.mContext, R.layout.layout_toast, null);
        }
        return this.contentView;
    }

    public static void cancelAll() {
        SystemTN.instance().cancelAll();
    }

    private static Object getField(Object obj, String str) throws Exception {
        Field declaredField = obj.getClass().getDeclaredField(str);
        if (declaredField == null) {
            return null;
        }
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    private static void hookHandler(Toast toast) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (toast == null || Build.VERSION.SDK_INT >= 26) {
            return;
        }
        try {
            Field declaredField = Toast.class.getDeclaredField("mTN");
            declaredField.setAccessible(true);
            Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
            declaredField2.setAccessible(true);
            Object obj = declaredField.get(toast);
            declaredField2.set(obj, new SafelyHandlerWrapper((Handler) declaredField2.get(obj)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hookINotificationManager(Toast toast, Context context) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (toast == null || NotificationManagerCompat.from(context).areNotificationsEnabled() || DUtil.isWhiteList() || !isValid4HookINotificationManager() || iNotificationManagerProxy != null) {
            return;
        }
        try {
            Method declaredMethod = Toast.class.getDeclaredMethod("getService", new Class[0]);
            declaredMethod.setAccessible(true);
            final Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            iNotificationManagerProxy = Proxy.newProxyInstance(toast.getClass().getClassLoader(), new Class[]{Class.forName("android.app.INotificationManager")}, new InvocationHandler() {
                @Override
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    DUtil.log(method.getName());
                    if ("enqueueToast".equals(method.getName()) || "enqueueToastEx".equals(method.getName()) || "cancelToast".equals(method.getName())) {
                        objArr[0] = "android";
                    }
                    return method.invoke(objInvoke, objArr);
                }
            });
            Field declaredField = Toast.class.getDeclaredField("sService");
            declaredField.setAccessible(true);
            declaredField.set(toast, iNotificationManagerProxy);
        } catch (Exception e) {
            iNotificationManagerProxy = null;
            DUtil.log("hook INotificationManager error:" + e.getMessage());
        }
    }

    public static boolean isValid4HookINotificationManager() {
        return Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27;
    }

    private static void setupToastAnim(Toast toast, int i) {
        try {
            Object field = getField(toast, "mTN");
            if (field != null) {
                Object field2 = getField(field, "mParams");
                if (field2 instanceof WindowManager.LayoutParams) {
                    ((WindowManager.LayoutParams) field2).windowAnimations = i;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override
    public void cancel() {
        SystemTN.instance().cancelAll();
    }

    void cancelInternal() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
            this.mToast = null;
        }
    }

    public SystemToast m8clone() {
        SystemToast systemToast;
        CloneNotSupportedException e;
        try {
            systemToast = (SystemToast) super.clone();
            try {
                systemToast.mContext = this.mContext;
                systemToast.contentView = this.contentView;
                systemToast.duration = this.duration;
                systemToast.animation = this.animation;
                systemToast.gravity = this.gravity;
                systemToast.xOffset = this.xOffset;
                systemToast.yOffset = this.yOffset;
                systemToast.priority = this.priority;
            } catch (CloneNotSupportedException e2) {
                e = e2;
                e.printStackTrace();
                return systemToast;
            }
        } catch (CloneNotSupportedException e3) {
            systemToast = null;
            e = e3;
        }
        return systemToast;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getGravity() {
        return this.gravity;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public View getView() {
        return assertContentViewNotNull();
    }

    public int getXOffset() {
        return this.xOffset;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    @Override
    public SystemToast setAnimation(int i) {
        this.animation = i;
        return this;
    }

    @Override
    public SystemToast setDuration(int i) {
        this.duration = i;
        return this;
    }

    @Override
    public SystemToast setGravity(int i) {
        setGravity(i, 0, 0);
        return this;
    }

    @Override
    public SystemToast setGravity(int i, int i2, int i3) {
        this.gravity = i;
        this.xOffset = i2;
        this.yOffset = i3;
        return this;
    }

    @Override
    public SystemToast setPriority(int i) {
        this.priority = i;
        return this;
    }

    @Override
    public IToast setText(int i, String str) {
        TextView textView = (TextView) assertContentViewNotNull().findViewById(i);
        if (textView != null) {
            textView.setText(str);
        }
        return this;
    }

    @Override
    public SystemToast setView(View view) {
        if (view == null) {
            DUtil.log("contentView cannot be null!");
            return this;
        }
        this.contentView = view;
        return this;
    }

    @Override
    public void show() {
        assertContentViewNotNull();
        SystemTN.instance().add(this);
    }

    void showInternal() {
        if (this.mContext == null || this.contentView == null) {
            return;
        }
        Toast toast = new Toast(this.mContext);
        this.mToast = toast;
        toast.setView(this.contentView);
        this.mToast.setGravity(this.gravity, this.xOffset, this.yOffset);
        if (this.duration == 3500) {
            this.mToast.setDuration(1);
        } else {
            this.mToast.setDuration(0);
        }
        hookHandler(this.mToast);
        hookINotificationManager(this.mToast, this.mContext);
        setupToastAnim(this.mToast, this.animation);
        this.mToast.show();
    }

    @Override
    public void showLong() {
        setDuration(3500).show();
    }
}

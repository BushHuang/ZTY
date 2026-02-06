package com.analysys;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.analysys.process.AgentProcess;
import com.analysys.process.PathGeneral;
import com.analysys.utils.AThreadPool;
import com.analysys.utils.ActivityLifecycleUtils;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;

public class s {
    private static final Handler g = new Handler(Looper.getMainLooper());
    private static Runnable h = new Runnable() {
        @Override
        public void run() {
            try {
                Activity currentActivity = ActivityLifecycleUtils.getCurrentActivity();
                if (currentActivity != null) {
                    s.a().a(currentActivity.getWindow().getDecorView());
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
    };

    public ConcurrentHashMap<String, Object> f75a;
    private ConcurrentHashMap<String, Object> b;
    private float c;
    private float d;
    private float e;
    private float f;
    private HashSet<String> i;
    private HashSet<String> j;

    static class a {

        public static final s f77a = new s();
    }

    class b implements View.OnTouchListener {
        private View.OnTouchListener b;

        private b(View.OnTouchListener onTouchListener) {
            this.b = onTouchListener;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            try {
                if (motionEvent.getAction() == 0) {
                    try {
                        if (s.this.f(view)) {
                            s.this.a(view, motionEvent);
                        }
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
                boolean zA = s.this.a(Thread.currentThread().getStackTrace());
                if (this.b != null && !zA) {
                    return this.b.onTouch(view, motionEvent);
                }
                return false;
            } catch (Throwable th2) {
                ExceptionUtil.exceptionThrow(th2);
                return false;
            }
        }
    }

    private s() {
        this.f75a = null;
        this.b = null;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0.0f;
        this.i = new HashSet<>();
        this.j = new HashSet<>();
    }

    public static s a() {
        return a.f77a;
    }

    private void a(final View view, MotionEvent motionEvent) {
        if (c(view)) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            if (a(rawX, rawY)) {
                this.c = rawX;
                this.d = rawY;
                this.e = motionEvent.getX();
                this.f = motionEvent.getY();
                final long jCurrentTimeMillis = System.currentTimeMillis();
                AThreadPool.asyncLowPriorityExecutor(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (s.this.b == null) {
                                s.this.b = new ConcurrentHashMap();
                            } else {
                                s.this.b.clear();
                            }
                            if (s.this.a(PathGeneral.getInstance().general(view))) {
                                s.this.c();
                                s.this.d(view);
                                s.this.b.putAll(s.this.f75a);
                                AgentProcess.getInstance().pageTouchInfo(s.this.b, jCurrentTimeMillis);
                            }
                        } catch (Throwable th) {
                            ExceptionUtil.exceptionThrow(th);
                        }
                    }
                });
            }
        }
    }

    private boolean a(float f, float f2) {
        float f3 = this.c;
        if (f3 == 0.0f) {
            return true;
        }
        float f4 = this.d;
        return (f4 != 0.0f && f3 == f && f4 == f2) ? false : true;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str) || CommonUtils.isEmpty(new JSONArray(str))) {
            return false;
        }
        this.b.put("$element_path", str.replaceAll(" ", ""));
        return true;
    }

    private boolean a(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr.length <= 4) {
            return false;
        }
        return TextUtils.equals(stackTraceElementArr[2].getClassName() + "." + stackTraceElementArr[2].getMethodName(), stackTraceElementArr[3].getClassName() + "." + stackTraceElementArr[3].getMethodName());
    }

    public static void b() {
        g.removeCallbacks(h);
        g.postDelayed(h, 200L);
    }

    private void b(View view) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int visibility = view.getVisibility();
        if (visibility == 4 || visibility == 8 || !view.getGlobalVisibleRect(new Rect())) {
            return;
        }
        Method declaredMethod = Class.forName("android.view.View").getDeclaredMethod("getListenerInfo", new Class[0]);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        Object objInvoke = declaredMethod.invoke(view, new Object[0]);
        Field declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(objInvoke);
        if (obj instanceof b) {
            return;
        }
        declaredField.set(objInvoke, new b((View.OnTouchListener) obj));
    }

    private void c() {
        this.b.put("$click_x", Float.valueOf(this.c));
        this.b.put("$click_y", Float.valueOf(this.d));
        this.b.put("$element_x", Float.valueOf(this.e));
        this.b.put("$element_y", Float.valueOf(this.f));
    }

    private boolean c(View view) {
        while (view.getId() != 16908290) {
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                return false;
            }
            view = (View) parent;
        }
        return true;
    }

    private void d(View view) {
        this.b.put("$element_clickable", Integer.valueOf(((view instanceof ImageButton) || (view instanceof Button)) ? 1 : 0));
        this.b.put("$element_type", view.getClass().getName());
        String strE = e(view);
        if (TextUtils.isEmpty(strE)) {
            return;
        }
        this.b.put("$element_content", strE);
    }

    private boolean d() {
        return !this.j.isEmpty();
    }

    private String e(View view) {
        Class<?> classByName = AnsReflectUtils.getClassByName("androidx.appcompat.widget.SwitchCompat");
        if (classByName == null) {
            classByName = AnsReflectUtils.getClassByName("androidx.appcompat.widget.SwitchCompat");
        }
        CharSequence charSequenceValueOf = null;
        if (classByName != null && classByName.isInstance(view)) {
            charSequenceValueOf = (String) (((CompoundButton) view).isChecked() ? view.getClass().getMethod("getTextOn", new Class[0]) : view.getClass().getMethod("getTextOff", new Class[0])).invoke(view, new Object[0]);
        } else if (view instanceof CheckBox) {
            charSequenceValueOf = ((CheckBox) view).getText();
        } else if (view instanceof RadioButton) {
            charSequenceValueOf = ((RadioButton) view).getText();
        } else if (view instanceof ToggleButton) {
            ToggleButton toggleButton = (ToggleButton) view;
            charSequenceValueOf = toggleButton.isChecked() ? toggleButton.getTextOn() : toggleButton.getTextOff();
        } else if (view instanceof Button) {
            charSequenceValueOf = ((Button) view).getText();
        } else if (view instanceof CheckedTextView) {
            charSequenceValueOf = ((CheckedTextView) view).getText();
        } else if (view instanceof TextView) {
            charSequenceValueOf = ((TextView) view).getText();
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if (!TextUtils.isEmpty(imageView.getContentDescription())) {
                charSequenceValueOf = String.valueOf(imageView.getContentDescription());
            }
        }
        return !TextUtils.isEmpty(charSequenceValueOf) ? String.valueOf(charSequenceValueOf) : "";
    }

    private boolean f(View view) {
        try {
            if (AgentProcess.getInstance().getConfig().isAutoHeatMap() && !g(view)) {
                if (d()) {
                    return h(view);
                }
                return true;
            }
            return false;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return true;
        }
    }

    private boolean g(View view) {
        try {
            Context context = view.getContext();
            if (context instanceof Activity) {
                String name = context.getClass().getName();
                if (TextUtils.isEmpty(name)) {
                    return false;
                }
                return this.i.contains(name);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return false;
    }

    private boolean h(View view) {
        try {
            Context context = view.getContext();
            if (context instanceof Activity) {
                String name = context.getClass().getName();
                if (TextUtils.isEmpty(name)) {
                    return false;
                }
                return this.j.contains(name);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return false;
    }

    public void a(Activity activity) {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        this.f75a = concurrentHashMap;
        if (activity != null) {
            concurrentHashMap.put("$url", activity.getClass().getName());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.f75a.put("$page_width", Integer.valueOf(displayMetrics.widthPixels));
            this.f75a.put("$page_height", Integer.valueOf(displayMetrics.heightPixels));
            this.f75a.put("$screen_dpi", Integer.valueOf(displayMetrics.densityDpi));
            this.f75a.put("$screen_scale", Float.valueOf(160.0f / displayMetrics.densityDpi));
        }
    }

    public void a(View view) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!(view instanceof ViewGroup)) {
            b(view);
            return;
        }
        b(view);
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                a(viewGroup.getChildAt(i));
            } else {
                b(viewGroup.getChildAt(i));
            }
        }
    }

    public void a(List<String> list) {
        try {
            this.i.clear();
            if (list != null) {
                this.i.addAll(list);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void b(List<String> list) {
        try {
            this.j.clear();
            if (list != null) {
                this.j.addAll(list);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}

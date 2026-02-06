package com.analysys.visual;

import android.view.View;
import com.analysys.utils.ExceptionUtil;
import java.lang.reflect.Method;

public class al {

    private final String f104a;
    private final Object[] b;
    private final Class<?> c;
    private final Class<?> d;
    private final Method e;

    public al(Class<?> cls, String str, Object[] objArr, Class<?> cls2) throws NoSuchMethodException, SecurityException {
        this.f104a = str;
        this.b = objArr;
        this.c = cls2;
        Method methodB = b(cls);
        this.e = methodB;
        if (methodB != null) {
            this.d = methodB.getDeclaringClass();
            return;
        }
        throw new NoSuchMethodException("Method " + cls.getName() + "." + this.f104a + " not exists");
    }

    private static Class<?> a(Class<?> cls) {
        return cls == Byte.class ? Byte.TYPE : cls == Short.class ? Short.TYPE : cls == Integer.class ? Integer.TYPE : cls == Long.class ? Long.TYPE : cls == Float.class ? Float.TYPE : cls == Double.class ? Double.TYPE : cls == Boolean.class ? Boolean.TYPE : cls == Character.class ? Character.TYPE : cls;
    }

    private Method b(Class<?> cls) throws SecurityException {
        Class[] clsArr = new Class[this.b.length];
        int i = 0;
        while (true) {
            Object[] objArr = this.b;
            if (i >= objArr.length) {
                break;
            }
            clsArr[i] = objArr[i].getClass();
            i++;
        }
        for (Method method : cls.getMethods()) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (name.equals(this.f104a) && parameterTypes.length == this.b.length && a(this.c).isAssignableFrom(a(method.getReturnType()))) {
                boolean zIsAssignableFrom = true;
                for (int i2 = 0; i2 < parameterTypes.length && zIsAssignableFrom; i2++) {
                    zIsAssignableFrom = a(parameterTypes[i2]).isAssignableFrom(a((Class<?>) clsArr[i2]));
                }
                if (zIsAssignableFrom) {
                    return method;
                }
            }
        }
        return null;
    }

    public Object a(View view) {
        return a(view, this.b);
    }

    public Object a(View view, Object[] objArr) {
        if (!this.d.isAssignableFrom(view.getClass())) {
            return null;
        }
        try {
            return this.e.invoke(view, objArr);
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return null;
        }
    }

    public String toString() {
        return "[ViewMethodReflector " + this.f104a + "(" + this.b + ")]";
    }
}

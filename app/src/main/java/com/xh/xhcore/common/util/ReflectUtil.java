package com.xh.xhcore.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {
    private ReflectUtil() {
    }

    public static Object getField(Object obj, String str) throws Exception {
        Field fieldInner = getFieldInner(obj.getClass(), str);
        if (fieldInner != null) {
            fieldInner.setAccessible(true);
            return fieldInner.get(obj);
        }
        throw new NoSuchFieldException("className = " + obj.getClass() + " fieldName = " + str);
    }

    private static Field getFieldInner(Class<?> cls, String str) throws NoSuchFieldException {
        Field declaredField;
        Class<? super Object> superclass;
        try {
            declaredField = cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            declaredField = null;
        }
        return (declaredField != null || (superclass = cls.getSuperclass()) == null) ? declaredField : getFieldInner(superclass, str);
    }

    public static long getFieldLong(Object obj, String str) throws Exception {
        Field fieldInner = getFieldInner(obj.getClass(), str);
        if (fieldInner != null) {
            fieldInner.setAccessible(true);
            return fieldInner.getLong(obj);
        }
        throw new NoSuchFieldException("className = " + obj.getClass() + " fieldName = " + str);
    }

    public static Object invoke(Object obj, String str) throws Throwable {
        return obj.getClass().getMethod(str, (Class[]) null).invoke(obj, (Object[]) null);
    }

    public static Object invoke(Object obj, String str, Class<?> cls, Object obj2) throws Throwable {
        return obj.getClass().getMethod(str, cls).invoke(obj, obj2);
    }

    public static Object invoke(Object obj, String str, Class<?> cls, Object obj2, Class<?> cls2, Object obj3) throws Exception {
        return obj.getClass().getMethod(str, cls, cls2).invoke(obj, obj2, obj3);
    }

    public static Object invokeStatic(Object obj, String str) throws Throwable {
        return ((Class) obj).getMethod(str, (Class[]) null).invoke(obj, (Object[]) null);
    }

    public static <T> T newInstance(Class<T> cls, Class<?>[] clsArr, Object[] objArr) throws Throwable {
        return cls.getConstructor(clsArr).newInstance(objArr);
    }

    public static boolean respondsTo(Object obj, String str) throws SecurityException {
        for (Method method : obj.getClass().getMethods()) {
            if (method.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static void setField(Object obj, String str, Object obj2) throws Exception {
        Field fieldInner = getFieldInner(obj.getClass(), str);
        if (fieldInner != null) {
            fieldInner.setAccessible(true);
            fieldInner.set(obj, obj2);
            return;
        }
        throw new NoSuchFieldException("className = " + obj.getClass() + " fieldName = " + str);
    }
}

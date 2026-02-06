package com.tencent.matrix.util;

public class ReflectUtils {
    public static <T> T get(Class<?> cls, String str) throws Exception {
        return (T) new ReflectFiled(cls, str).get();
    }

    public static <T> T get(Class<?> cls, String str, Object obj) throws Exception {
        return (T) new ReflectFiled(cls, str).get(obj);
    }

    public static <T> T invoke(Class<?> cls, String str, Object obj, Object... objArr) throws Exception {
        return (T) new ReflectMethod(cls, str, new Class[0]).invoke(obj, objArr);
    }

    public static boolean set(Class<?> cls, String str, Object obj) throws Exception {
        return new ReflectFiled(cls, str).set(obj);
    }

    public static boolean set(Class<?> cls, String str, Object obj, Object obj2) throws Exception {
        return new ReflectFiled(cls, str).set(obj, obj2);
    }
}

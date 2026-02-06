package com.analysys.utils;

import android.text.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnsReflectUtils {
    private static Field findField(Class<?> cls, String str) {
        Field declaredField = null;
        while (cls != null && declaredField == null) {
            try {
                declaredField = cls.getDeclaredField(str);
            } catch (Throwable unused) {
            }
            if (cls == Object.class) {
                break;
            }
            if (declaredField == null) {
                cls = cls.getSuperclass();
            }
        }
        return declaredField;
    }

    public static Method findMethod(Class<?> cls, String str, Class[] clsArr) {
        Method declaredMethod = null;
        while (cls != null && declaredMethod == null) {
            try {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            } catch (Throwable unused) {
            }
            if (cls == Object.class) {
                break;
            }
            if (declaredMethod == null) {
                cls = cls.getSuperclass();
            }
        }
        return declaredMethod;
    }

    public static Class<?> getClassByName(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return null;
        }
    }

    private static Object getField(Class<?> cls, Object obj, String str) {
        if (cls == null) {
            if (obj == null) {
                return null;
            }
            cls = obj.getClass();
        }
        Field fieldFindField = findField(cls, str);
        if (fieldFindField == null) {
            return null;
        }
        try {
            fieldFindField.setAccessible(true);
            return fieldFindField.get(obj);
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return null;
        }
    }

    public static Object getField(Object obj, String str) {
        return getField(null, obj, str);
    }

    public static Object getStaticField(Class<?> cls, String str) {
        return getField(cls, null, str);
    }

    private static Object invokeMethod(Class<?> cls, Object obj, String str, Class[] clsArr, Object[] objArr) {
        if (cls == null) {
            if (obj == null) {
                return null;
            }
            cls = obj.getClass();
        }
        Method methodFindMethod = findMethod(cls, str, clsArr);
        if (methodFindMethod == null) {
            return null;
        }
        try {
            methodFindMethod.setAccessible(true);
            return methodFindMethod.invoke(obj, objArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object invokeMethod(Object obj, String str) {
        return invokeMethod(obj, str, null, null);
    }

    public static Object invokeMethod(Object obj, String str, Class[] clsArr, Object[] objArr) {
        return invokeMethod(null, obj, str, clsArr, objArr);
    }

    public static Object invokeMethodByName(Object obj, String str, Object[] objArr) {
        if (obj != null && !TextUtils.isEmpty(str)) {
            Class<?> superclass = obj.getClass();
            Method method = null;
            while (superclass != null && method == null) {
                try {
                    Method[] declaredMethods = superclass.getDeclaredMethods();
                    int length = declaredMethods.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Method method2 = declaredMethods[i];
                        if (str.equals(method2.getName())) {
                            method = method2;
                            break;
                        }
                        i++;
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionPrint(th);
                }
                if (superclass == Object.class) {
                    break;
                }
                if (method == null) {
                    superclass = superclass.getSuperclass();
                }
            }
            if (method == null) {
                return null;
            }
            try {
                method.setAccessible(true);
                return method.invoke(obj, objArr);
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Class[] clsArr, Object[] objArr) {
        return invokeMethod(cls, null, str, clsArr, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2) {
        try {
            return invokeStaticMethod(Class.forName(str), str2, (Class[]) null, (Object[]) null);
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return null;
        }
    }

    public static Object invokeStaticMethod(String str, String str2, Class cls, Object obj) {
        try {
            return invokeMethod(Class.forName(str), null, str2, new Class[]{cls}, new Object[]{obj});
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return null;
        }
    }

    public static boolean isSubClass(Class<?> cls, String str) {
        if (cls != null && str != null) {
            try {
                if (cls.isAssignableFrom(Class.forName(str))) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static boolean isSubClass(String[] strArr, Class<?> cls) {
        if (strArr != null && cls != null) {
            for (String str : strArr) {
                try {
                } catch (Throwable th) {
                    ExceptionUtil.exceptionPrint(th);
                }
                if (Class.forName(str).isAssignableFrom(cls)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean setField(Class cls, Object obj, String str, Object obj2) {
        if (cls == null) {
            if (obj == null) {
                return false;
            }
            cls = obj.getClass();
        }
        Field fieldFindField = findField(cls, str);
        if (fieldFindField == null) {
            return false;
        }
        try {
            fieldFindField.setAccessible(true);
            fieldFindField.set(obj, obj2);
            return true;
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return false;
        }
    }

    public static boolean setField(Object obj, String str, Object obj2) {
        return setField(null, obj, str, obj2);
    }

    public static void setStaticField(Class cls, String str, Object obj) {
        setField(cls, null, str, obj);
    }
}

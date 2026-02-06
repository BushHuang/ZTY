package com.zaze.utils;

import android.text.TextUtils;
import com.zaze.utils.log.ZLog;
import java.lang.reflect.Method;

public class ReflectUtil {
    public static boolean showLog = false;

    private static Class<?> dealPrimitive(Class<?> cls) {
        return Integer.class.equals(cls) ? Integer.TYPE : Boolean.class.equals(cls) ? Boolean.TYPE : Long.class.equals(cls) ? Long.TYPE : Short.class.equals(cls) ? Short.TYPE : Float.class.equals(cls) ? Float.TYPE : Double.class.equals(cls) ? Double.TYPE : Byte.class.equals(cls) ? Byte.TYPE : Character.class.equals(cls) ? Character.TYPE : cls;
    }

    private static Object execute(Class<?> cls, Object obj, String str, Object... objArr) throws Exception {
        if (showLog) {
            ZLog.d("Debug[ZZ]", "functionName : " + str, new Object[0]);
        }
        Class<?>[] clsArr = null;
        if (objArr != null && objArr.length > 0) {
            clsArr = new Class[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                clsArr[i] = objArr[i].getClass();
                clsArr[i] = dealPrimitive(clsArr[i]);
                if (clsArr[i].isPrimitive()) {
                    clsArr[i] = dealPrimitive(clsArr[i]);
                }
                if (showLog) {
                    ZLog.d("Debug[ZZ]", "clazz[" + i + "] " + clsArr[i], new Object[0]);
                }
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(obj, objArr);
    }

    public static Object executeMethod(Object obj, String str, Object... objArr) throws Exception {
        if (obj == null) {
            return null;
        }
        return execute(obj.getClass(), obj, str, objArr);
    }

    public static Object executeMethodByName(String str, String str2, Object... objArr) throws Exception {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return execute(Class.forName(str), null, str2, objArr);
    }
}

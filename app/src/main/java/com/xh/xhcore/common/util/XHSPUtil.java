package com.xh.xhcore.common.util;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class XHSPUtil {
    public static final String FILE_NAME = "share_data";

    private static class SharedPreferencesCompat {
        private static final Method APPLY_METHOD = findApplyMethod();

        private SharedPreferencesCompat() {
        }

        static void apply(SharedPreferences.Editor editor) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            try {
                if (APPLY_METHOD != null) {
                    APPLY_METHOD.invoke(editor, new Object[0]);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            editor.commit();
        }

        private static Method findApplyMethod() {
            try {
                return SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void clear() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SharedPreferences.Editor editorEdit = XhBaseApplication.mContext.getSharedPreferences("share_data", 0).edit();
        editorEdit.clear();
        SharedPreferencesCompat.apply(editorEdit);
    }

    public static boolean contains(String str) {
        return XhBaseApplication.mContext.getSharedPreferences("share_data", 0).contains(str);
    }

    public static <T> T get(String str, T t) {
        SharedPreferences sharedPreferences = XhBaseApplication.mContext.getSharedPreferences("share_data", 0);
        if (t instanceof String) {
            return (T) sharedPreferences.getString(str, (String) t);
        }
        if (t instanceof Integer) {
            return (T) new Integer(sharedPreferences.getInt(str, ((Integer) t).intValue()));
        }
        if (t instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) t).booleanValue()));
        }
        if (t instanceof Float) {
            return (T) Float.valueOf(sharedPreferences.getFloat(str, ((Float) t).floatValue()));
        }
        if (t instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(str, ((Long) t).longValue()));
        }
        return null;
    }

    public static Map<String, ?> getAll() {
        return XhBaseApplication.mContext.getSharedPreferences("share_data", 0).getAll();
    }

    public static boolean getBoolean(String str) {
        return XhBaseApplication.mContext.getSharedPreferences("share_data", 0).getBoolean(str, false);
    }

    public static float getFloat(String str) {
        return XhBaseApplication.mContext.getSharedPreferences("share_data", 0).getFloat(str, 0.0f);
    }

    public static int getInt(String str) {
        return XhBaseApplication.mContext.getSharedPreferences("share_data", 0).getInt(str, 0);
    }

    public static long getLong(String str) {
        return XhBaseApplication.mContext.getSharedPreferences("share_data", 0).getLong(str, 0L);
    }

    public static String getString(String str) {
        return TextUtils.isEmpty(str) ? "" : XhBaseApplication.mContext.getSharedPreferences("share_data", 0).getString(str, "");
    }

    public static void put(String str, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SharedPreferences.Editor editorEdit = XhBaseApplication.mContext.getSharedPreferences("share_data", 0).edit();
        if (obj instanceof String) {
            editorEdit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        } else {
            editorEdit.putString(str, obj.toString());
        }
        SharedPreferencesCompat.apply(editorEdit);
    }

    public static void remove(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SharedPreferences.Editor editorEdit = XhBaseApplication.mContext.getSharedPreferences("share_data", 0).edit();
        editorEdit.remove(str);
        SharedPreferencesCompat.apply(editorEdit);
    }
}

package com.analysys.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.analysys.e;

public class SharedUtil {
    public static boolean getBoolean(Context context, String str, boolean z) {
        try {
            String string = getString(context, str, "", "boolean");
            return !TextUtils.isEmpty(string) ? Boolean.parseBoolean(string) : z;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return z;
        }
    }

    public static float getFloat(Context context, String str, float f) {
        try {
            String string = getString(context, str, "", "float");
            return !TextUtils.isEmpty(string) ? CommonUtils.parseFloat(string, f) : f;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return f;
        }
    }

    public static int getInt(Context context, String str, int i) {
        try {
            String string = getString(context, str, "", "int");
            return !TextUtils.isEmpty(string) ? CommonUtils.parseInt(string, i) : i;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return i;
        }
    }

    public static long getLong(Context context, String str, long j) {
        try {
            String string = getString(context, str, "", "long");
            return !TextUtils.isEmpty(string) ? CommonUtils.parseLong(string, j) : j;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return j;
        }
    }

    public static String getString(Context context, String str, String str2) {
        return getString(context, str, str2, "string");
    }

    private static String getString(Context context, String str, String str2, String str3) {
        Cursor cursorQuery;
        String string = null;
        try {
            cursorQuery = context.getContentResolver().query(e.b(context), new String[]{str, str3}, str2, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() >= 1 && cursorQuery.moveToPosition(0)) {
                        string = cursorQuery.getString(0);
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        ExceptionUtil.exceptionThrow(th);
                        if (cursorQuery != null) {
                        }
                        if (string != null) {
                        }
                    } finally {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
        return string != null ? str2 : string;
    }

    public static void remove(Context context, String str) {
        try {
            Uri uriB = e.b(context);
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str);
            context.getContentResolver().update(uriB, contentValues, null, null);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static void setBoolean(Context context, String str, boolean z) {
        try {
            Uri uriB = e.b(context);
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str);
            contentValues.put("values", Boolean.valueOf(z));
            contentValues.put("type", "boolean");
            context.getContentResolver().insert(uriB, contentValues);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static void setFloat(Context context, String str, float f) {
        try {
            Uri uriB = e.b(context);
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str);
            contentValues.put("values", Float.valueOf(f));
            contentValues.put("type", "float");
            context.getContentResolver().insert(uriB, contentValues);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static void setInt(Context context, String str, int i) {
        ContentValues contentValues;
        Uri uriB;
        Uri uri = null;
        try {
            uriB = e.b(context);
            try {
                contentValues = new ContentValues();
            } catch (Throwable th) {
                th = th;
                contentValues = null;
            }
        } catch (Throwable th2) {
            th = th2;
            contentValues = null;
        }
        try {
            contentValues.put("key", str);
            contentValues.put("values", Integer.valueOf(i));
            contentValues.put("type", "int");
            context.getContentResolver().insert(uriB, contentValues);
        } catch (Throwable th3) {
            th = th3;
            uri = uriB;
            if (!th.getMessage().contains("IllegalArgumentException")) {
                ExceptionUtil.exceptionThrow(th);
                return;
            }
            if (uri == null || contentValues == null) {
                return;
            }
            try {
                context.getContentResolver().insert(uri, contentValues);
            } catch (Throwable th4) {
                ExceptionUtil.exceptionThrow(th4);
            }
        }
    }

    public static void setLong(Context context, String str, long j) {
        try {
            Uri uriB = e.b(context);
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str);
            contentValues.put("values", Long.valueOf(j));
            contentValues.put("type", "long");
            context.getContentResolver().insert(uriB, contentValues);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static void setString(Context context, String str, String str2) {
        try {
            Uri uriB = e.b(context);
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str);
            contentValues.put("values", str2);
            contentValues.put("type", "string");
            context.getContentResolver().insert(uriB, contentValues);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}

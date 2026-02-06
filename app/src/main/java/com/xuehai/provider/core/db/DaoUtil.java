package com.xuehai.provider.core.db;

import android.database.Cursor;

public class DaoUtil {
    protected static int getInt(Cursor cursor, String str) {
        return cursor.getInt(cursor.getColumnIndex(str));
    }

    protected static long getLong(Cursor cursor, String str) {
        return cursor.getLong(cursor.getColumnIndex(str));
    }

    protected static String getString(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndex(str));
    }
}

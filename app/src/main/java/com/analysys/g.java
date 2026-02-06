package com.analysys;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class g {

    private Context f45a;

    static class a {

        private static final g f46a = new g();
    }

    private g() {
    }

    public static g a(Context context) {
        a.f46a.b(context);
        return a.f46a;
    }

    private boolean a(Context context, List<Integer> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        String[] strArr = new String[list.size()];
        StringBuilder sb = new StringBuilder(" in (");
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = String.valueOf(list.get(i));
            if (i == list.size() - 1) {
                sb.append("?) ");
            } else {
                sb.append("?,");
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("b", (Integer) (-1));
        Uri uriA = e.a(this.f45a);
        ContentResolver contentResolver = context.getContentResolver();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("id");
        sb2.append(sb.toString());
        return contentResolver.update(uriA, contentValues, sb2.toString(), strArr) > 0;
    }

    private void b(Context context) {
        if (!CommonUtils.isEmpty(this.f45a) || CommonUtils.isEmpty(context)) {
            return;
        }
        this.f45a = context;
    }

    public JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        Cursor cursor = null;
        try {
            if (CommonUtils.isEmpty(this.f45a)) {
                return null;
            }
            Cursor cursorQuery = this.f45a.getContentResolver().query(e.a(this.f45a), new String[]{"a", "id", "c"}, null, null, "id asc  LIMIT 0,100");
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        ArrayList arrayList = new ArrayList();
                        do {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("a"));
                            arrayList.add(Integer.valueOf(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("id"))));
                            String strDbDecrypt = CommonUtils.dbDecrypt(string);
                            if (!CommonUtils.isEmpty(strDbDecrypt)) {
                                jSONArray.put(new JSONObject(strDbDecrypt));
                            }
                        } while (cursorQuery.moveToNext());
                        if (!a(this.f45a, arrayList)) {
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return null;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    try {
                        ExceptionUtil.exceptionThrow(th);
                        return jSONArray;
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return jSONArray;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void a(int i) {
        try {
            if (this.f45a == null) {
                return;
            }
            this.f45a.getContentResolver().delete(e.a(this.f45a), "id in(select id from  e_fz  order by id desc limit 0," + i + ")", null);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void a(String str, String str2) {
        if (CommonUtils.isEmpty(str) || this.f45a == null) {
            return;
        }
        String strDbEncrypt = CommonUtils.dbEncrypt(str);
        if (TextUtils.isEmpty(strDbEncrypt)) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("a", strDbEncrypt);
        contentValues.put("b", (Integer) 0);
        contentValues.put("c", str2);
        contentValues.put("d", Long.valueOf(System.currentTimeMillis()));
        try {
            this.f45a.getContentResolver().insert(e.a(this.f45a), contentValues);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public long b() {
        Cursor cursorQuery = null;
        long count = 0;
        try {
            if (this.f45a != null) {
                cursorQuery = this.f45a.getContentResolver().query(e.a(this.f45a), null, null, null, null);
                if (cursorQuery != null) {
                    count = cursorQuery.getCount();
                }
            }
        } catch (Throwable th) {
            try {
                ExceptionUtil.exceptionThrow(th);
                if (cursorQuery != null) {
                }
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }
        return count;
    }

    public void c() {
        try {
            if (this.f45a == null) {
                return;
            }
            this.f45a.getContentResolver().delete(e.a(this.f45a), "b=?", new String[]{String.valueOf(-1)});
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void d() {
        try {
            if (this.f45a == null) {
                return;
            }
            this.f45a.getContentResolver().delete(e.a(this.f45a), null, null);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}

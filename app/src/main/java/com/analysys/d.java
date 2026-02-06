package com.analysys;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class d {

    private c f42a;
    private SQLiteDatabase b;

    static class a {

        private static final d f43a = new d();
    }

    private d() {
    }

    public static d a(Context context) {
        a.f43a.d(context);
        return a.f43a;
    }

    private void c(Context context) {
        c cVar;
        if (this.f42a == null && context != null) {
            this.f42a = new c(context);
        }
        if (this.b != null || (cVar = this.f42a) == null) {
            return;
        }
        this.b = cVar.getWritableDatabase();
    }

    private void d(Context context) {
        c(context);
    }

    public void a() {
        this.f42a = null;
        SQLiteDatabase sQLiteDatabase = this.b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        this.b = null;
    }

    public SQLiteDatabase b(Context context) {
        c(context);
        return this.b;
    }
}

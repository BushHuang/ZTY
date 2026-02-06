package com.analysys;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class c extends SQLiteOpenHelper {
    c(Context context) {
        super(context, "analysys.data", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL("create table if not exists  e_fz  (id Integer Primary Key Autoincrement ,a text ,b int not null ,c varchar(50) not null ,d varchar(50) not null ,r_a text ,r_b text ,r_c text   )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}

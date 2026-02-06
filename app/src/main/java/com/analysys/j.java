package com.analysys;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.IBinder;

public class j extends MatrixCursor {

    static final String[] f61a = {"binder"};
    private Bundle b;

    public j(IBinder iBinder) {
        super(f61a);
        this.b = new Bundle();
        if (iBinder != null) {
            this.b.putParcelable("analysys_binder", new k(iBinder));
        }
    }

    public static final IBinder a(Cursor cursor) {
        Bundle extras = cursor.getExtras();
        extras.setClassLoader(k.class.getClassLoader());
        return ((k) extras.getParcelable("analysys_binder")).a();
    }

    @Override
    public Bundle getExtras() {
        return this.b;
    }
}

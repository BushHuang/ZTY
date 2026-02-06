package com.analysys.visual;

import android.view.View;

public class x extends u {
    private int b;

    public x(String str, String str2, String str3, ak akVar, Object obj, String str4) {
        super(str, str2, str3, akVar, obj, str4);
        this.b = -1;
    }

    @Override
    protected Object a(View view) {
        return Integer.valueOf(this.b);
    }

    public void a(int i) {
        this.b = i;
    }
}

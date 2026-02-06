package com.analysys.visual;

import android.text.TextUtils;

public class af {

    public final String f93a;
    public final Class<?> b;
    public final al c;

    public af(String str, Class<?> cls, al alVar) {
        this.f93a = str;
        this.b = cls;
        this.c = alVar;
    }

    public boolean equals(Object obj) {
        if (obj instanceof af) {
            return TextUtils.equals(this.f93a, ((af) obj).f93a);
        }
        return false;
    }

    public String toString() {
        return "[PageViewInfo " + this.f93a + "," + this.b + ", " + this.c + "]";
    }
}

package com.huawei.hms.hem;

import android.os.Process;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class i {

    private String f340a;
    private String b;
    private int c;
    private long d;
    private String e;
    private int f;
    private final StringBuilder g = new StringBuilder();

    i(String str, int i, String str2) {
        this.f340a = null;
        this.b = "HA";
        this.c = 0;
        this.d = 0L;
        this.f340a = str;
        this.c = i;
        if (str2 != null) {
            this.b = str2;
        }
        this.d = System.currentTimeMillis();
        this.e = Thread.currentThread().getName();
        this.f = Process.myPid();
    }

    private StringBuilder a(StringBuilder sb) {
        sb.append(' ');
        sb.append((CharSequence) this.g);
        return sb;
    }

    public final <T> i a(T t) {
        this.g.append(t);
        return this;
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        sb.append('[');
        sb.append(simpleDateFormat.format(Long.valueOf(this.d)));
        int i = this.c;
        String strValueOf = i != 3 ? i != 4 ? i != 5 ? i != 6 ? String.valueOf(i) : "E" : "W" : "I" : "D";
        sb.append(' ');
        sb.append(strValueOf);
        sb.append('/');
        sb.append(this.f340a);
        sb.append('/');
        sb.append(this.b);
        sb.append(' ');
        sb.append(this.f);
        sb.append(':');
        sb.append(this.e);
        sb.append(']');
        a(sb);
        return sb.toString();
    }
}

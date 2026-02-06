package com.analysys.visual;

import android.text.TextUtils;
import android.view.View;
import com.analysys.utils.ExceptionUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class u implements s {

    public final String f155a;
    private final String b;
    private final String c;
    private final ak d;
    private final Object e;
    private final String f;

    public u(String str, String str2, String str3, ak akVar, Object obj, String str4) {
        this.b = str;
        this.f155a = str2;
        this.c = str3;
        this.d = akVar;
        this.e = obj;
        this.f = str4;
    }

    Object a(View view) {
        return null;
    }

    @Override
    public String a() {
        return this.b;
    }

    @Override
    public boolean a(Object obj) {
        Object objB = b(obj);
        Object objB2 = b();
        if (objB != null && objB2 != null) {
            try {
                return ((objB instanceof String) && (objB2 instanceof CharSequence)) ? objB.equals(objB2) : "number".equals(this.c) ? ((Double) objB).doubleValue() == Double.parseDouble(objB2.toString()) : objB == objB2;
            } catch (Throwable th) {
                ExceptionUtil.exceptionPrint(th);
            }
        }
        return false;
    }

    public Object b() {
        return this.e;
    }

    public Object b(Object obj) {
        Object obj2;
        if (!TextUtils.isEmpty(this.f155a) && (obj2 = this.e) != null) {
            return c(obj2);
        }
        if (!(obj instanceof View) || TextUtils.isEmpty(this.b)) {
            return null;
        }
        View view = (View) obj;
        ak akVar = this.d;
        return akVar == null ? c(a(view)) : c(akVar.a(view));
    }

    public Object c(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!TextUtils.isEmpty(this.f) && (obj instanceof String)) {
            try {
                Matcher matcher = Pattern.compile(this.f).matcher(obj.toString());
                if (matcher.find()) {
                    obj = matcher.group();
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionPrint(th);
            }
        }
        String string = obj.toString();
        String str = this.c;
        char c = 65535;
        int iHashCode = str.hashCode();
        if (iHashCode != -1034364087) {
            if (iHashCode != -891985903) {
                if (iHashCode == 3029738 && str.equals("bool")) {
                    c = 2;
                }
            } else if (str.equals("string")) {
                c = 0;
            }
        } else if (str.equals("number")) {
            c = 1;
        }
        if (c == 0) {
            return string.length() > 1000 ? string.substring(0, 1000) : string;
        }
        if (c == 1) {
            try {
                return Double.valueOf(Double.parseDouble(string));
            } catch (Throwable th2) {
                ExceptionUtil.exceptionPrint(th2);
            }
        } else if (c == 2) {
            try {
                return Boolean.valueOf(Boolean.parseBoolean(string));
            } catch (Throwable th3) {
                ExceptionUtil.exceptionPrint(th3);
            }
        }
        return string;
    }
}

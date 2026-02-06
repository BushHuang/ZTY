package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import java.util.UUID;

public class z0 {
    public static z0 b;

    public Context f334a;

    public static class a extends a1 {

        public String f335a;
        public String b;

        public a(String str, String str2) {
            this.f335a = str;
            this.b = str2;
        }

        @Override
        public String a() {
            return com.huawei.hms.hatool.a.d(this.f335a, this.b);
        }

        @Override
        public String a(String str) {
            return SHA.sha256Encrypt(str);
        }

        @Override
        public String b() {
            return com.huawei.hms.hatool.a.g(this.f335a, this.b);
        }

        @Override
        public String c() {
            return com.huawei.hms.hatool.a.j(this.f335a, this.b);
        }

        @Override
        public int d() {
            return (com.huawei.hms.hatool.a.k(this.f335a, this.b) ? 4 : 0) | 0 | (com.huawei.hms.hatool.a.e(this.f335a, this.b) ? 2 : 0) | (com.huawei.hms.hatool.a.h(this.f335a, this.b) ? 1 : 0);
        }
    }

    public static z0 a() {
        z0 z0Var;
        synchronized (z0.class) {
            if (b == null) {
                b = new z0();
            }
            z0Var = b;
        }
        return z0Var;
    }

    public String a(String str, String str2) {
        return g.a(this.f334a, str, str2);
    }

    public String a(boolean z) {
        if (!z) {
            return "";
        }
        String strE = b.e();
        if (TextUtils.isEmpty(strE)) {
            strE = g0.a(this.f334a, "global_v2", "uuid", "");
            if (TextUtils.isEmpty(strE)) {
                strE = UUID.randomUUID().toString().replace("-", "");
                g0.b(this.f334a, "global_v2", "uuid", strE);
            }
            b.h(strE);
        }
        return strE;
    }

    public void a(Context context) {
        if (this.f334a == null) {
            this.f334a = context;
        }
    }

    public String b(String str, String str2) {
        return g.b(this.f334a, str, str2);
    }

    public x0 c(String str, String str2) {
        return new a(str, str2).a(this.f334a);
    }

    public String d(String str, String str2) {
        return c1.b(str, str2);
    }

    public Pair<String, String> e(String str, String str2) {
        if (!com.huawei.hms.hatool.a.f(str, str2)) {
            return new Pair<>("", "");
        }
        String strP = i.c().b().p();
        String strQ = i.c().b().q();
        if (!TextUtils.isEmpty(strP) && !TextUtils.isEmpty(strQ)) {
            return new Pair<>(strP, strQ);
        }
        Pair<String, String> pairE = b1.e(this.f334a);
        i.c().b().k((String) pairE.first);
        i.c().b().l((String) pairE.second);
        return pairE;
    }

    public String f(String str, String str2) {
        return c1.a(str, str2);
    }
}

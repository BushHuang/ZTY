package com.huawei.hms.hatool;

import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

public class x {
    public static x b = new x();

    public a f328a = new a();

    public class a {

        public String f329a;
        public String b;
        public long c = 0;

        public a() {
        }

        public void a(long j) {
            x.this.f328a.c = j;
        }

        public void a(String str) {
            x.this.f328a.b = str;
        }

        public void b(String str) {
            x.this.f328a.f329a = str;
        }
    }

    public static x d() {
        return b;
    }

    public String a() {
        return this.f328a.b;
    }

    public void a(String str, String str2) {
        long jB = b();
        String strB = r0.b(str, str2);
        if (strB == null || strB.isEmpty()) {
            y.e("WorkKeyHandler", "get rsa pubkey config error");
            return;
        }
        if (jB == 0) {
            jB = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - jB <= 43200000) {
            return;
        }
        String strGenerateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        String strA = e.a(strB, strGenerateSecureRandomStr);
        this.f328a.a(jB);
        this.f328a.b(strGenerateSecureRandomStr);
        this.f328a.a(strA);
    }

    public long b() {
        return this.f328a.c;
    }

    public String c() {
        return this.f328a.f329a;
    }
}

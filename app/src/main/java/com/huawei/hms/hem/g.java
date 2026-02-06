package com.huawei.hms.hem;

public final class g {
    private static h b = new h();
    private String d;

    private String f339a = "SDK.";
    private int c = 4;
    private boolean e = false;

    private boolean a(int i) {
        return this.e && i >= this.c;
    }

    private i b(int i, String str, String str2) {
        i iVar = new i(this.d, i, str);
        iVar.a((i) str2);
        return iVar;
    }

    public final void a(int i, String str, String str2) {
        if (a(i)) {
            String str3 = this.f339a + str;
            h.a(b(i, str3, str2), i, str3);
        }
    }
}

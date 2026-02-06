package com.analysys;

public class u {

    private static String f80a = null;
    private static String b = null;
    private static int c = 200;

    public static int a() {
        return c;
    }

    public static void a(int i) {
        c = i;
    }

    public static void a(int i, String str) {
        c = i;
        b = str;
    }

    public static void a(String str) {
        f80a = str;
    }

    public static String b() {
        return f80a;
    }

    public static void b(String str) {
        b = str;
    }

    public static String c() {
        return b;
    }

    public static void d() {
        a((String) null);
        b(null);
        a(200);
    }
}

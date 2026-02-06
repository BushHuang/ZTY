package com.analysys.visual;

public class ax extends Exception {

    private int f119a;

    public ax(int i) {
        this.f119a = i;
    }

    public ax(int i, String str) {
        super(str);
        this.f119a = i;
    }

    public ax(int i, Throwable th) {
        super(th);
        this.f119a = i;
    }

    public int a() {
        return this.f119a;
    }
}

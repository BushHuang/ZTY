package com.analysys.visual;

public class bs extends bv implements bq {

    private String f137a = "*";

    @Override
    public String a() {
        return this.f137a;
    }

    @Override
    public void a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        }
        this.f137a = str;
    }
}

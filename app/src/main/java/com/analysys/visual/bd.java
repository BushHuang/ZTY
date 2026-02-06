package com.analysys.visual;

public class bd implements be {
    @Override
    public String a() {
        return "";
    }

    @Override
    public void a(bl blVar) {
    }

    @Override
    public boolean a(String str) {
        return true;
    }

    @Override
    public String b() {
        return "";
    }

    @Override
    public void b(bl blVar) {
    }

    @Override
    public boolean b(String str) {
        return true;
    }

    @Override
    public be c() {
        return new bd();
    }

    @Override
    public void c(bl blVar) throws ay {
        if (blVar.b() || blVar.c() || blVar.d()) {
            throw new ay("bad rsv RSV1: " + blVar.b() + " RSV2: " + blVar.c() + " RSV3: " + blVar.d());
        }
    }

    @Override
    public void d() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

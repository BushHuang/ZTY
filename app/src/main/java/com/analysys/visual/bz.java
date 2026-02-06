package com.analysys.visual;

public class bz implements by {

    private final String f140a;

    public bz(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.f140a = str;
    }

    @Override
    public String a() {
        return this.f140a;
    }

    @Override
    public boolean a(String str) {
        for (String str2 : str.replaceAll(" ", "").split(",")) {
            if (this.f140a.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public by b() {
        return new bz(a());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f140a.equals(((bz) obj).f140a);
    }

    public int hashCode() {
        return this.f140a.hashCode();
    }

    @Override
    public String toString() {
        return a();
    }
}

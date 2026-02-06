package com.analysys.visual;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

public class bv implements br {

    private byte[] f139a;
    private TreeMap<String, String> b = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    @Override
    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    @Override
    public String b(String str) {
        String str2 = this.b.get(str);
        return str2 == null ? "" : str2;
    }

    @Override
    public Iterator<String> b() {
        return Collections.unmodifiableSet(this.b.keySet()).iterator();
    }

    @Override
    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    @Override
    public byte[] c() {
        return this.f139a;
    }
}

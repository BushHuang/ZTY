package com.analysys.visual;

import android.util.SparseArray;
import android.view.View;
import com.analysys.visual.q;
import java.util.HashMap;
import java.util.Map;

public class n extends k {
    private final SparseArray<Map<View, j>> q;

    public n(String str, int i) {
        super(str, i);
        this.q = new SparseArray<>();
    }

    @Override
    public View.AccessibilityDelegate a(int i, View view) {
        Map<View, j> map = this.q.get(i);
        if (map != null) {
            for (View view2 : map.keySet()) {
                if (view2 == view) {
                    j jVar = map.get(view);
                    if (jVar == null) {
                        return null;
                    }
                    Object objD = jVar.d(view2);
                    if (objD instanceof View.AccessibilityDelegate) {
                        return (View.AccessibilityDelegate) objD;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    protected void a(q.a aVar) {
        j jVar;
        if (aVar.c == null || aVar.c.isEmpty()) {
            return;
        }
        Map<View, j> map = this.q.get(aVar.f154a);
        if (map == null) {
            map = new HashMap<>();
            this.q.put(aVar.f154a, map);
        }
        map.clear();
        for (int i = 0; i < aVar.c.size(); i++) {
            View view = aVar.c.get(i);
            view.setTag(50331648, Integer.valueOf(aVar.f154a));
            Object objC = j.c(view);
            if (objC instanceof j) {
                jVar = (j) objC;
                jVar.a(this);
            } else {
                jVar = new j(view, this);
                c.a(view, this, jVar);
            }
            map.put(view, jVar);
        }
    }

    @Override
    public void b(q.a aVar) {
        Map<View, j> map = this.q.get(aVar.f154a);
        if (map == null) {
            return;
        }
        for (View view : map.keySet()) {
            view.setTag(50331648, null);
            c.b(view, this, map.get(view));
        }
        map.clear();
        this.q.remove(aVar.f154a);
    }
}

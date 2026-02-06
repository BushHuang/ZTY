package com.analysys.visual;

import android.view.View;
import android.view.ViewGroup;
import com.analysys.visual.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class q {

    public static class a {

        public final int f154a;
        public View b;
        public List<View> c;
        public View d;

        a(int i, List<View> list, View view) {
            this.f154a = i;
            this.c = list;
            this.b = view;
        }

        public void a() {
            this.b = null;
            this.d = null;
            List<View> list = this.c;
            if (list != null) {
                list.clear();
                this.c = null;
            }
        }
    }

    public static View a(View view, List<p.b> list, boolean z, boolean z2) {
        return new p().a(view, list, z, z2);
    }

    public static a a(View view, i iVar) {
        List<View> listA;
        return new a(view.hashCode(), a(view, iVar.g, false, false), (iVar.h == null || (listA = a(view, iVar.h, false, false)) == null || listA.isEmpty()) ? null : listA.get(0));
    }

    public static List<View> a(View view, e eVar, boolean z, boolean z2) {
        View viewA;
        if (eVar.f145a != null) {
            viewA = (eVar.h == 0 && eVar.f145a.isEmpty()) ? view : a(view, eVar.f145a, z, z2);
            if (viewA == null) {
                return null;
            }
        } else {
            viewA = null;
        }
        if (viewA == null) {
            if (eVar.d == null || eVar.d.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            a(view, eVar.d, arrayList);
            return arrayList;
        }
        if (eVar.d != null && !eVar.d.isEmpty()) {
            Iterator<s> it = eVar.d.iterator();
            while (it.hasNext()) {
                if (!it.next().a(viewA)) {
                    return null;
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(viewA);
        return arrayList2;
    }

    private static void a(View view, List<s> list, List<View> list2) {
        int i;
        boolean z;
        if (view == null) {
            return;
        }
        Iterator<s> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!it.next().a(view)) {
                    z = false;
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            list2.add(view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (i = 0; i < childCount; i++) {
                a(viewGroup.getChildAt(i), list, list2);
            }
        }
    }
}

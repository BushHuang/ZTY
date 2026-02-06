package com.analysys.visual;

import android.view.View;

public class c {
    public static void a(View view, g gVar, f fVar) {
        if (view == null || gVar == null || fVar == null) {
            return;
        }
        fVar.a(view);
        fVar.a(gVar);
    }

    public static void b(View view, g gVar, f fVar) {
        if (view == null || gVar == null || fVar == null) {
            return;
        }
        fVar.b(gVar);
        if (fVar.a().isEmpty()) {
            fVar.b(view);
        }
    }
}

package com.analysys.visual;

import android.view.View;
import com.analysys.utils.AnsReflectUtils;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class j extends View.AccessibilityDelegate implements f {

    private final View.AccessibilityDelegate f149a;
    private boolean b;
    private final Set<g> c = new CopyOnWriteArraySet();

    public j(View view, g gVar) {
        this.f149a = (View.AccessibilityDelegate) d(view);
        this.c.add(gVar);
    }

    public static Object c(View view) {
        return AnsReflectUtils.invokeMethod(view, "getAccessibilityDelegate");
    }

    @Override
    public Set<g> a() {
        return this.c;
    }

    @Override
    public void a(View view) {
        view.setAccessibilityDelegate(this);
    }

    @Override
    public void a(g gVar) {
        this.c.add(gVar);
    }

    @Override
    public void b(View view) {
        view.setAccessibilityDelegate(this.f149a);
    }

    @Override
    public void b(g gVar) {
        this.c.remove(gVar);
    }

    public Object d(View view) {
        return AnsReflectUtils.invokeMethod(view, "getAccessibilityDelegate");
    }

    @Override
    public void sendAccessibilityEvent(View view, int i) {
        if (this.b) {
            return;
        }
        this.b = true;
        Iterator<g> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().a(view, Integer.valueOf(i));
        }
        View.AccessibilityDelegate accessibilityDelegate = this.f149a;
        if (accessibilityDelegate != null) {
            accessibilityDelegate.sendAccessibilityEvent(view, i);
        }
        this.b = false;
    }
}

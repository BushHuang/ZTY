package com.analysys.visual;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.analysys.ui.RootView;
import com.analysys.ui.UniqueViewHelper;
import com.analysys.ui.WindowUIHelper;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.visual.p;
import com.analysys.visual.q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class i implements g {

    public int f146a;
    public String b;
    public String c;
    public final String d;
    public String e;
    public a f;
    public e g;
    public e h;
    public List<h> i;
    public List<s> j;
    public List<c> k;
    public List<b> l;
    public String m;
    protected Map<String, Object> n;
    int o;
    final Map<Integer, q.a> p = new ConcurrentHashMap();
    private long q;

    public class a {

        public boolean f147a;
        public boolean c;
        public boolean e;
        public boolean g;
        public final List<String> b = new ArrayList();
        public final List<String> d = new ArrayList();
        public final List<String> f = new ArrayList();
        public final List<String> h = new ArrayList();

        public a() {
        }
    }

    public class b extends c {
        public b() {
        }

        boolean a(View view) {
            if (this.c == null) {
                return true;
            }
            Iterator<s> it = this.c.iterator();
            while (it.hasNext()) {
                if (!it.next().a(view)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class c {
        public e b;
        public List<s> c;
        public String d;
    }

    i(String str) {
        this.d = str;
    }

    private void g() {
        this.n = null;
        q.a aVar = this.p.get(Integer.valueOf(this.o));
        if (aVar == null || aVar.d == null) {
            return;
        }
        HashMap map = new HashMap();
        List<s> list = this.j;
        if (list != null) {
            Iterator<s> it = list.iterator();
            while (it.hasNext()) {
                u uVar = (u) it.next();
                map.put(uVar.f155a, uVar.b(aVar.d));
            }
        }
        List<c> list2 = this.k;
        if (list2 != null) {
            Iterator<c> it2 = list2.iterator();
            while (it2.hasNext()) {
                Map<String, Object> mapB = b(it2.next());
                if (mapB != null) {
                    map.putAll(mapB);
                }
            }
        }
        if (com.analysys.visual.b.a().b()) {
            View view = aVar.d;
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            map.put("$pos_left", iArr[0] + "");
            map.put("$pos_top", iArr[1] + "");
            map.put("$pos_width", view.getWidth() + "");
            map.put("$pos_height", view.getHeight() + "");
        }
        this.n = map;
    }

    private boolean h() {
        View view;
        if (this.l == null) {
            return true;
        }
        q.a aVar = this.p.get(Integer.valueOf(this.o));
        if (aVar == null) {
            return false;
        }
        for (int i = 0; i < this.l.size(); i++) {
            b bVar = this.l.get(i);
            if (bVar != null) {
                if (bVar.b == this.g) {
                    view = aVar.d;
                } else {
                    List<View> listA = q.a(a(bVar), bVar.b, true, true);
                    view = (listA == null || listA.isEmpty()) ? null : listA.get(0);
                }
                if (view == null || !bVar.a(view)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected int a(View view) {
        if (view == null) {
            return -1;
        }
        Object tag = view.getTag(50331648);
        if (tag instanceof Integer) {
            return ((Integer) tag).intValue();
        }
        View rootView = view.getRootView();
        if (rootView == null) {
            return -1;
        }
        return rootView.hashCode();
    }

    protected View a(c cVar) {
        View view;
        if (cVar == null || cVar.b == null || cVar.b.h < 0) {
            q.a aVar = this.p.get(Integer.valueOf(this.o));
            if (aVar == null || aVar.d == null) {
                return null;
            }
            return aVar.d.getRootView();
        }
        q.a aVar2 = this.p.get(Integer.valueOf(this.o));
        if (aVar2 == null || (view = aVar2.d) == null) {
            return null;
        }
        for (int i = 0; i < cVar.b.h; i++) {
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
        return view;
    }

    public List<View> a(int i) {
        q.a aVar = this.p.get(Integer.valueOf(i));
        if (aVar != null) {
            return aVar.c;
        }
        return null;
    }

    void a(View view, boolean z) {
        d();
        if (view != null) {
            int iA = a(view);
            q.a aVar = this.p.get(Integer.valueOf(iA));
            if (aVar != null) {
                this.e = WindowUIHelper.getPageName(view);
                this.o = iA;
                aVar.d = view;
                a(z);
            }
        }
    }

    public final void a(RootView rootView) {
        if (c(rootView)) {
            q.a aVarA = q.a(rootView.view, this);
            if ((aVarA.c == null || aVarA.c.isEmpty()) && aVarA.b == null) {
                return;
            }
            this.p.put(Integer.valueOf(rootView.hashCode), aVarA);
            a(aVarA);
        }
    }

    protected abstract void a(q.a aVar);

    void a(boolean z) {
        if (z || !h()) {
            return;
        }
        g();
        Iterator<h> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public boolean a() {
        return (this instanceof o) || (this.g.f145a == null && this.g.d != null);
    }

    protected Map<String, Object> b(c cVar) {
        boolean z;
        View childAt;
        Object objInvokeMethod;
        Object field;
        int iIntValue;
        HashMap map = null;
        if (cVar.b.g != null) {
            List<View> listA = q.a(a(cVar), cVar.b, true, true);
            View view = (listA == null || listA.isEmpty()) ? null : listA.get(0);
            if (view == null || !UniqueViewHelper.isWebView(view.getClass())) {
                return null;
            }
            return am.a().b(view, cVar.d);
        }
        if (cVar.b.e != -1 && cVar.b.f != null) {
            List<View> listA2 = q.a(a(cVar), new e(cVar.b.f145a, cVar.b.f), true, true);
            View view2 = (listA2 == null || listA2.isEmpty()) ? null : listA2.get(0);
            if (view2 != null && (view2 instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int i = 0;
                while (true) {
                    if (i >= viewGroup.getChildCount() || (childAt = viewGroup.getChildAt(i)) == null) {
                        break;
                    }
                    if (viewGroup instanceof AdapterView) {
                        iIntValue = ((AdapterView) viewGroup).getPositionForView(childAt);
                    } else if (UniqueViewHelper.isExtendsFromUniqueClass(viewGroup.getClass().getName(), "unique.RecyclerView")) {
                        Object objInvokeMethod2 = AnsReflectUtils.invokeMethod(viewGroup, "getChildAdapterPosition", new Class[]{View.class}, new Object[]{childAt});
                        iIntValue = objInvokeMethod2 == null ? -1 : ((Integer) objInvokeMethod2).intValue();
                    } else if (UniqueViewHelper.isExtendsFromUniqueClass(viewGroup.getClass().getName(), "unique.ViewPager") && (objInvokeMethod = AnsReflectUtils.invokeMethod(viewGroup, "infoForChild", new Class[]{View.class}, new Object[]{childAt})) != null && (field = AnsReflectUtils.getField(objInvokeMethod, "position")) != null) {
                        iIntValue = ((Integer) field).intValue();
                    }
                    if (iIntValue == cVar.b.e) {
                        for (int i2 = 0; i2 < cVar.b.f145a.size(); i2++) {
                            p.b bVar = cVar.b.f145a.get(i2);
                            if (bVar.h == cVar.b.e) {
                                bVar.c = i;
                                z = true;
                                break;
                            }
                        }
                    } else {
                        i++;
                    }
                }
                z = false;
            }
            return null;
        }
        List<View> listA3 = q.a(a(cVar), cVar.b, true, true);
        View view3 = (listA3 == null || listA3.isEmpty()) ? null : listA3.get(0);
        if (view3 != null && cVar.c != null) {
            map = new HashMap();
            Iterator<s> it = cVar.c.iterator();
            while (it.hasNext()) {
                u uVar = (u) it.next();
                map.put(uVar.f155a, uVar.b(view3));
            }
        }
        return map;
    }

    public void b() {
        Iterator<Integer> it = this.p.keySet().iterator();
        while (it.hasNext()) {
            q.a aVar = this.p.get(Integer.valueOf(it.next().intValue()));
            b(aVar);
            aVar.a();
        }
        this.p.clear();
    }

    public final void b(RootView rootView) {
        q.a aVar = this.p.get(Integer.valueOf(rootView.hashCode));
        if (aVar != null) {
            b(aVar);
            aVar.a();
            this.p.remove(Integer.valueOf(rootView.hashCode));
        }
    }

    protected abstract void b(q.a aVar);

    boolean c() {
        return Math.abs(this.q - System.currentTimeMillis()) > 500;
    }

    public boolean c(RootView rootView) {
        if (!rootView.pageName.contains("(")) {
            if (this.f.f147a) {
                return true;
            }
            return this.f.b.contains(rootView.pageName);
        }
        if (rootView.pageName.indexOf(WindowUIHelper.getDialogName(null)) >= 0) {
            if (this.f.c) {
                return true;
            }
            return this.f.d.contains(rootView.pageName);
        }
        if (rootView.pageName.indexOf(WindowUIHelper.getFloatWindowName(null)) >= 0) {
            if (this.f.e) {
                return true;
            }
            return this.f.f.contains(rootView.pageName);
        }
        if (rootView.pageName.indexOf(WindowUIHelper.getPopupWindowName(null)) < 0) {
            return false;
        }
        if (this.f.g) {
            return true;
        }
        return this.f.h.contains(rootView.pageName);
    }

    void d() {
        this.q = System.currentTimeMillis();
    }

    public View e() {
        q.a aVar = this.p.get(Integer.valueOf(this.o));
        if (aVar != null) {
            return aVar.d;
        }
        return null;
    }

    public boolean equals(Object obj) {
        int i;
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        int i2 = this.f146a;
        if (i2 != -1 && (i = iVar.f146a) != -1) {
            return i2 == i;
        }
        if (TextUtils.equals(this.b, iVar.b)) {
            return true;
        }
        if (this.g.c || iVar.g.c) {
            return TextUtils.equals(this.g.b, iVar.g.b);
        }
        return false;
    }

    public Map<String, Object> f() {
        return this.n;
    }
}

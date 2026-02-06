package com.analysys.visual;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import com.analysys.ui.UniqueViewHelper;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.visual.i;
import com.analysys.visual.p;
import com.analysys.visual.q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class m extends k {
    private final SparseArray<LinkedHashMap<View, j>> q;

    public m(String str, int i) {
        super(str, i);
        this.q = new SparseArray<>();
    }

    private List<p.b> a(List<p.b> list, String str) {
        int size = list.size();
        do {
            size--;
            if (size < 0) {
                return null;
            }
        } while (!TextUtils.equals(list.get(size).b, str));
        if (size < list.size() - 1) {
            return list.subList(size + 1, list.size());
        }
        return null;
    }

    private void a(View view, List<s> list) {
        if (list == null || view == null) {
            return;
        }
        for (s sVar : list) {
            if (sVar instanceof x) {
                ((x) sVar).a(b(view));
            } else if (sVar instanceof w) {
                ((w) sVar).a(c(view));
            }
        }
    }

    private void a(ViewGroup viewGroup, int i, List<p.b> list, LinkedHashMap<View, j> linkedHashMap) {
        View viewA;
        j jVar;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != null && (viewA = q.a(childAt, list, true, true)) != null) {
                Object objC = j.c(viewA);
                if (objC instanceof j) {
                    jVar = (j) objC;
                    jVar.a(this);
                } else {
                    jVar = new j(viewA, this);
                    c.a(viewA, this, jVar);
                }
                linkedHashMap.put(viewA, jVar);
                viewA.setTag(50331648, Integer.valueOf(i));
            }
        }
    }

    private void a(LinkedHashMap<View, j> linkedHashMap) {
        j jVar;
        for (View view : linkedHashMap.keySet()) {
            if (view != null && (jVar = linkedHashMap.get(view)) != null) {
                c.b(view, this, jVar);
            }
        }
        linkedHashMap.clear();
    }

    private int b(View view) {
        Object field;
        q.a aVar = this.p.get(Integer.valueOf(this.o));
        if (aVar.b instanceof AdapterView) {
            while (true) {
                Object parent = view.getParent();
                if (parent != aVar.b) {
                    if (!(parent instanceof View)) {
                        break;
                    }
                    view = (View) parent;
                } else {
                    return ((AdapterView) aVar.b).getPositionForView(view);
                }
            }
        } else if (UniqueViewHelper.isExtendsFromUniqueClass(aVar.b.getClass().getName(), "unique.RecyclerView")) {
            while (true) {
                Object parent2 = view.getParent();
                if (parent2 != aVar.b) {
                    if (!(parent2 instanceof View)) {
                        break;
                    }
                    view = (View) parent2;
                } else {
                    Object objInvokeMethod = AnsReflectUtils.invokeMethod(aVar.b, "getChildAdapterPosition", new Class[]{View.class}, new Object[]{view});
                    if (objInvokeMethod == null) {
                        return -1;
                    }
                    return ((Integer) objInvokeMethod).intValue();
                }
            }
        } else if (UniqueViewHelper.isExtendsFromUniqueClass(aVar.b.getClass().getName(), "unique.ViewPager")) {
            while (true) {
                Object parent3 = view.getParent();
                if (parent3 != aVar.b) {
                    if (!(parent3 instanceof View)) {
                        break;
                    }
                    view = (View) parent3;
                } else {
                    Object objInvokeMethod2 = AnsReflectUtils.invokeMethod(parent3, "infoForChild", new Class[]{View.class}, new Object[]{view});
                    if (objInvokeMethod2 == null || (field = AnsReflectUtils.getField(objInvokeMethod2, "position")) == null) {
                        return -1;
                    }
                    return ((Integer) field).intValue();
                }
            }
        } else {
            LinkedHashMap<View, j> linkedHashMap = this.q.get(this.o);
            if (linkedHashMap != null) {
                Iterator<View> it = linkedHashMap.keySet().iterator();
                int i = -1;
                while (it.hasNext()) {
                    i++;
                    if (it.next() == view) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private int c(View view) {
        Object objInvokeMethod;
        Object field;
        q.a aVar = this.p.get(Integer.valueOf(this.o));
        if (!(aVar.b instanceof AdapterView)) {
            if (UniqueViewHelper.isExtendsFromUniqueClass(aVar.b.getClass().getName(), "unique.RecyclerView") && (objInvokeMethod = AnsReflectUtils.invokeMethod(aVar.b, "getLayoutManager")) != null) {
                while (true) {
                    Object parent = view.getParent();
                    if (parent != aVar.b) {
                        if (!(parent instanceof View)) {
                            break;
                        }
                        view = (View) parent;
                    } else {
                        Object objInvokeMethod2 = AnsReflectUtils.invokeMethod(objInvokeMethod, "getItemViewType", new Class[]{View.class}, new Object[]{view});
                        if (objInvokeMethod2 == null) {
                            return -1;
                        }
                        return ((Integer) objInvokeMethod2).intValue();
                    }
                }
            } else {
                return -1;
            }
        } else {
            while (true) {
                Object parent2 = view.getParent();
                if (parent2 != aVar.b) {
                    if (!(parent2 instanceof View)) {
                        break;
                    }
                    view = (View) parent2;
                } else {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (!(layoutParams instanceof AbsListView.LayoutParams) || (field = AnsReflectUtils.getField(layoutParams, "viewType")) == null) {
                        return -1;
                    }
                    return ((Integer) field).intValue();
                }
            }
        }
        return -1;
    }

    @Override
    public View.AccessibilityDelegate a(int i, View view) {
        LinkedHashMap<View, j> linkedHashMap = this.q.get(i);
        if (linkedHashMap != null) {
            for (View view2 : linkedHashMap.keySet()) {
                if (view2 == view) {
                    j jVar = linkedHashMap.get(view);
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
    public List<View> a(int i) {
        LinkedHashMap<View, j> linkedHashMap = this.q.get(i);
        if (linkedHashMap != null) {
            return new ArrayList(linkedHashMap.keySet());
        }
        return null;
    }

    @Override
    protected void a(q.a aVar) {
        if (aVar.b instanceof ViewGroup) {
            LinkedHashMap<View, j> linkedHashMap = this.q.get(aVar.f154a);
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap<>();
                this.q.put(aVar.f154a, linkedHashMap);
            }
            linkedHashMap.clear();
            List<p.b> listA = a(this.g.f145a, this.g.f);
            if (listA == null || listA.isEmpty()) {
                return;
            }
            a((ViewGroup) aVar.b, aVar.f154a, listA, linkedHashMap);
        }
    }

    @Override
    public void a(boolean z) {
        View viewE = e();
        a(viewE, this.j);
        if (this.k != null) {
            Iterator<i.c> it = this.k.iterator();
            while (it.hasNext()) {
                a(viewE, it.next().c);
            }
        }
        if (this.l != null) {
            Iterator<i.b> it2 = this.l.iterator();
            while (it2.hasNext()) {
                a(viewE, it2.next().c);
            }
        }
        super.a(z);
    }

    @Override
    public void b(q.a aVar) {
        LinkedHashMap<View, j> linkedHashMap;
        if (aVar.b == null || (linkedHashMap = this.q.get(aVar.f154a)) == null) {
            return;
        }
        a(linkedHashMap);
        this.q.remove(aVar.f154a);
    }
}

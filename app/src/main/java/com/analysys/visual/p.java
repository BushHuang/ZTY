package com.analysys.visual;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.List;
import org.json.JSONObject;

public class p {

    private final a f151a = new a();

    static class a {

        private final int[] f152a = new int[256];
        private int b = 0;

        a() {
        }

        int a(int i) {
            return this.f152a[i];
        }

        boolean a() {
            return this.f152a.length == this.b;
        }

        int b() {
            int i = this.b;
            this.b = i + 1;
            this.f152a[i] = 0;
            return i;
        }

        void b(int i) {
            int[] iArr = this.f152a;
            iArr[i] = iArr[i] + 1;
        }

        void c() {
            int i = this.b - 1;
            this.b = i;
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException(this.b);
            }
        }
    }

    public static class b {

        public int f153a;
        public final String b;
        public int c;
        final int d;
        final String e;
        final String f;
        final String g;
        public int h;

        public b(int i, String str, int i2, int i3, String str2, String str3, String str4, int i4) {
            this.f153a = i;
            this.b = str;
            this.c = i2;
            this.d = i3;
            this.e = str2;
            this.f = str3;
            this.g = str4;
            this.h = i4;
        }

        public JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                if (this.f153a == 1) {
                    jSONObject.put("prefix", "shortest");
                }
                if (this.b != null) {
                    jSONObject.put("view_class", this.b);
                }
                if (this.c > -1) {
                    jSONObject.put("index", this.c);
                }
                if (this.d > -1) {
                    jSONObject.put("id", this.d);
                }
                if (this.e != null) {
                    jSONObject.put("idName", this.e);
                }
                if (this.f != null) {
                    jSONObject.put("contentDescription", this.f);
                }
                if (this.g != null) {
                    jSONObject.put("tag", this.g);
                }
                return jSONObject;
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f153a == bVar.f153a && TextUtils.equals(this.b, bVar.b) && this.c == bVar.c && this.d == bVar.d && TextUtils.equals(this.e, bVar.e) && TextUtils.equals(this.f, bVar.f) && TextUtils.equals(this.g, bVar.g);
        }

        public String toString() {
            JSONObject jSONObjectA = a();
            return jSONObjectA != null ? jSONObjectA.toString() : "";
        }
    }

    p() {
    }

    private View a(View view, b bVar, List<b> list, boolean z) {
        if (list.isEmpty()) {
            return view;
        }
        View viewA = null;
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        int i = 2;
        int i2 = 1;
        if (this.f151a.a()) {
            ANSLog.w("analysys.visual", "index stack full2");
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        b bVar2 = list.get(0);
        List<b> listSubList = list.subList(1, list.size());
        int childCount = viewGroup.getChildCount();
        int iB = this.f151a.b();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = viewGroup.getChildAt(i3);
            if (bVar.f153a == i) {
                Class[] clsArr = new Class[i2];
                clsArr[0] = View.class;
                Object[] objArr = new Object[i2];
                objArr[0] = childAt;
                Object objInvokeMethod = AnsReflectUtils.invokeMethod(viewGroup, "infoForChild", clsArr, objArr);
                Object objInvokeMethod2 = AnsReflectUtils.invokeMethod(viewGroup, "getCurrentItem");
                boolean z2 = (objInvokeMethod == null || objInvokeMethod2 == null || AnsReflectUtils.getField(objInvokeMethod, "position") == objInvokeMethod2) ? false : true;
                if (!z2) {
                    View viewA2 = a(bVar2, childAt, iB, z);
                    if (viewA2 == null || (viewA = a(viewA2, bVar2, listSubList, z)) != null) {
                        i2 = 1;
                    } else {
                        i2 = 1;
                        ANSLog.v("analysys.visual", "find is null: " + i3 + ", " + viewA2 + ", " + bVar2 + ", " + listSubList + ", " + z);
                    }
                    if (bVar2.c >= 0 && this.f151a.a(iB) > bVar2.c) {
                        break;
                    }
                } else {
                    i2 = 1;
                }
                i3++;
                i = 2;
            }
        }
        this.f151a.c();
        return viewA;
    }

    private View a(b bVar, View view, int i, boolean z) {
        boolean zA;
        if (view == null) {
            return null;
        }
        int iA = this.f151a.a(i);
        if (TextUtils.isEmpty(bVar.b)) {
            zA = a(bVar, view);
            if (zA) {
                this.f151a.b(i);
            }
        } else if (a(view, bVar.b)) {
            this.f151a.b(i);
            zA = z ? true : a(bVar, view);
        } else {
            zA = false;
        }
        if (zA && (bVar.c == -1 || bVar.c == iA)) {
            return view;
        }
        if (bVar.f153a == 1 && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View viewA = a(bVar, viewGroup.getChildAt(i2), i, z);
                if (viewA != null) {
                    return viewA;
                }
            }
        }
        return null;
    }

    private boolean a(b bVar, View view) {
        if (-1 != bVar.d && view.getId() != bVar.d) {
            return false;
        }
        CharSequence contentDescription = view.getContentDescription();
        if (bVar.f != null && contentDescription != null && !bVar.f.contentEquals(contentDescription)) {
            return false;
        }
        String str = bVar.g;
        if (bVar.g != null) {
            return view.getTag() != null && str.equals(view.getTag().toString());
        }
        return true;
    }

    private static boolean a(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        for (Class<?> superclass = obj.getClass(); !TextUtils.equals(superclass.getName(), str); superclass = superclass.getSuperclass()) {
            if (superclass == Object.class) {
                return false;
            }
        }
        return true;
    }

    View a(View view, List<b> list, boolean z, boolean z2) {
        if (list.isEmpty()) {
            return view;
        }
        if (this.f151a.a()) {
            ANSLog.w("analysys.visual", "index stack full1");
            return null;
        }
        b bVar = list.get(0);
        List<b> listSubList = list.subList(1, list.size());
        int iB = this.f151a.b();
        if (!z2) {
            view = a(bVar, view, iB, z);
        }
        this.f151a.c();
        if (view != null) {
            return a(view, bVar, listSubList, z);
        }
        return null;
    }
}

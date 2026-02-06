package com.analysys.visual;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import com.analysys.process.SystemIds;
import com.analysys.ui.UniqueViewHelper;
import com.analysys.ui.WindowUIHelper;
import com.analysys.utils.ActivityLifecycleUtils;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.ExceptionUtil;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

class ae {
    private final List<af> b;
    private String e;
    private boolean f;
    private final Handler d = new Handler(Looper.getMainLooper());

    private final c f88a = new c();
    private final d c = new d(300);

    class a {

        boolean f89a;
        b b;

        public a(b bVar) {
            this.b = bVar;
        }

        public a a(String str) {
            if (this.f89a) {
                this.b.b.append(",");
            }
            StringBuilder sb = this.b.b;
            sb.append("\"");
            sb.append(str);
            sb.append("\":");
            this.f89a = false;
            return this;
        }

        public void a() {
            if (this.f89a) {
                this.b.b.append(",");
            }
            this.b.b.append("{");
            this.f89a = false;
        }

        public void a(int i) {
            if (this.f89a) {
                this.b.b.append(",");
            }
            this.b.b.append(i);
            this.f89a = true;
        }

        public void a(Object obj) {
            if (this.f89a) {
                this.b.b.append(",");
            }
            StringBuilder sb = this.b.b;
            if (obj == null) {
                obj = "null";
            }
            sb.append(obj);
            this.f89a = true;
        }

        public void b() {
            this.b.b.append("}");
            this.f89a = true;
        }

        public void b(String str) {
            if (this.f89a) {
                this.b.b.append(",");
            }
            if (str == null) {
                this.b.b.append("null");
            } else {
                this.b.b.append("\"");
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt == '\f') {
                        this.b.b.append("\\f");
                    } else if (cCharAt == '\r') {
                        this.b.b.append("\\r");
                    } else if (cCharAt == '\"' || cCharAt == '\\') {
                        this.b.b.append('\\');
                        this.b.b.append(cCharAt);
                    } else if (cCharAt != 8232 && cCharAt != 8233) {
                        switch (cCharAt) {
                            case '\b':
                                this.b.b.append("\\b");
                                break;
                            case '\t':
                                this.b.b.append("\\t");
                                break;
                            case '\n':
                                this.b.b.append("\\n");
                                break;
                            default:
                                if (cCharAt <= 31) {
                                    this.b.b.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                                    break;
                                } else {
                                    this.b.b.append(cCharAt);
                                    break;
                                }
                        }
                    } else {
                        this.b.b.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                    }
                }
                this.b.b.append("\"");
            }
            this.f89a = true;
        }

        public void c() {
        }

        public void d() {
            if (this.f89a) {
                this.b.b.append(",");
            }
            this.b.b.append("[");
            this.f89a = false;
        }

        public void e() {
            this.b.b.append("]");
            this.f89a = true;
        }

        public void f() {
            if (this.f89a) {
                this.b.b.append(",");
            }
            this.b.b.append("null");
            this.f89a = true;
        }
    }

    class b {

        OutputStream f90a;
        StringBuilder b = new StringBuilder();

        public b(OutputStream outputStream) {
            this.f90a = outputStream;
        }

        public void a() {
            try {
                this.f90a.write(this.b.toString().getBytes());
                this.b = new StringBuilder();
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }

        public void a(String str) {
            this.b.append(str);
        }
    }

    class c implements Callable<List<WindowUIHelper.PageRootInfo>> {
        private final Map<String, WindowUIHelper.PageViewBitmap> b = new HashMap();

        class a {

            View f92a;
            int b;
            Paint c;

            a(View view) {
                this.f92a = view;
                this.b = view.getLayerType();
                this.c = (Paint) AnsReflectUtils.getField(this.f92a, "mLayerPaint");
            }

            void a() {
                if (this.b != 1) {
                    this.f92a.setLayerType(1, null);
                }
            }

            void b() {
                if (this.b != this.f92a.getLayerType()) {
                    this.f92a.setLayerType(this.b, this.c);
                }
            }
        }

        c() {
        }

        private void a(ViewGroup viewGroup, List<a> list) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (UniqueViewHelper.isWebView(childAt.getClass())) {
                    list.add(new a(childAt));
                } else if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, list);
                }
            }
        }

        private void a(WindowUIHelper.PageRootInfo pageRootInfo) {
            Bitmap drawingCache;
            ArrayList arrayList;
            WindowUIHelper.PageViewBitmap pageViewBitmap;
            View view = pageRootInfo.rootView;
            Boolean boolValueOf = null;
            try {
                drawingCache = (Bitmap) AnsReflectUtils.invokeMethod(view, "createSnapshot", new Class[]{Bitmap.Config.class, Integer.TYPE, Boolean.TYPE}, new Object[]{Bitmap.Config.RGB_565, -1, false});
            } catch (Throwable unused) {
                drawingCache = null;
            }
            if (drawingCache == null) {
                try {
                    if (ae.this.f) {
                        arrayList = new ArrayList();
                        try {
                            a((ViewGroup) view, arrayList);
                            Iterator<a> it = arrayList.iterator();
                            while (it.hasNext()) {
                                it.next().a();
                            }
                        } catch (Throwable th) {
                            th = th;
                            ExceptionUtil.exceptionThrow(th);
                            pageViewBitmap = this.b.get(pageRootInfo.activityName);
                            if (pageViewBitmap == null) {
                            }
                            if (drawingCache != null) {
                            }
                            if (arrayList != null) {
                            }
                            if (boolValueOf != null) {
                                view.setDrawingCacheEnabled(false);
                            }
                            pageRootInfo.scale = f;
                            pageRootInfo.screenshot = pageViewBitmap;
                        }
                    } else {
                        arrayList = null;
                    }
                    boolValueOf = Boolean.valueOf(view.isDrawingCacheEnabled());
                    view.setDrawingCacheEnabled(true);
                    view.buildDrawingCache(true);
                    drawingCache = view.getDrawingCache();
                } catch (Throwable th2) {
                    th = th2;
                    arrayList = null;
                }
            } else {
                arrayList = null;
            }
            pageViewBitmap = this.b.get(pageRootInfo.activityName);
            if (pageViewBitmap == null) {
                pageViewBitmap = new WindowUIHelper.PageViewBitmap();
                this.b.put(pageRootInfo.activityName, pageViewBitmap);
            }
            if (drawingCache != null) {
                int density = drawingCache.getDensity();
                f = density != 0 ? 160.0f / density : 1.0f;
                int width = drawingCache.getWidth();
                int height = drawingCache.getHeight();
                double width2 = drawingCache.getWidth() * f;
                Double.isNaN(width2);
                int i = (int) (width2 + 0.5d);
                Bitmap bitmap = drawingCache;
                double height2 = drawingCache.getHeight() * f;
                Double.isNaN(height2);
                int i2 = (int) (height2 + 0.5d);
                if (width > 0 && height > 0 && i > 0 && i2 > 0) {
                    pageViewBitmap.recreate(i, i2, 160, bitmap);
                }
            }
            if (arrayList != null) {
                Iterator<a> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    it2.next().b();
                }
            }
            if (boolValueOf != null && !boolValueOf.booleanValue()) {
                view.setDrawingCacheEnabled(false);
            }
            pageRootInfo.scale = f;
            pageRootInfo.screenshot = pageViewBitmap;
        }

        @Override
        public List<WindowUIHelper.PageRootInfo> call() {
            ArrayList arrayList = new ArrayList();
            Activity currentActivity = ActivityLifecycleUtils.getCurrentActivity();
            if (currentActivity != null) {
                String name = currentActivity.getClass().getName();
                List<WindowUIHelper.PageRootInfo> topOpaqueActivityViews = WindowUIHelper.isTranslucentActivity(currentActivity) ? WindowUIHelper.getTopOpaqueActivityViews() : WindowUIHelper.getCurrentWindowViews(currentActivity, name);
                if (topOpaqueActivityViews == null || topOpaqueActivityViews.isEmpty()) {
                    arrayList.add(new WindowUIHelper.PageRootInfo(name, currentActivity.getWindow().getDecorView().getRootView()));
                } else {
                    arrayList.addAll(topOpaqueActivityViews);
                }
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    a((WindowUIHelper.PageRootInfo) arrayList.get(i));
                }
            }
            return arrayList;
        }
    }

    static class d extends LruCache<Class<?>, String> {
        d(int i) {
            super(i);
        }

        @Override
        protected String create(Class<?> cls) {
            return cls.getName();
        }
    }

    ae(List<af> list) {
        this.b = list;
    }

    private int a(View view) {
        Object objInvokeMethod;
        Object field;
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup)) {
            return -1;
        }
        if (UniqueViewHelper.isExtendsFromUniqueClass(parent.getClass().getName(), "unique.RecyclerView")) {
            Object objInvokeMethod2 = AnsReflectUtils.invokeMethod(parent, "getChildAdapterPosition", new Class[]{View.class}, new Object[]{view});
            if (objInvokeMethod2 == null) {
                return -1;
            }
            return ((Integer) objInvokeMethod2).intValue();
        }
        if (parent instanceof AdapterView) {
            return ((AdapterView) parent).getPositionForView(view);
        }
        if (!UniqueViewHelper.isExtendsFromUniqueClass(parent.getClass().getName(), "unique.ViewPager") || (objInvokeMethod = AnsReflectUtils.invokeMethod(parent, "infoForChild", new Class[]{View.class}, new Object[]{view})) == null || (field = AnsReflectUtils.getField(objInvokeMethod, "position")) == null) {
            return -1;
        }
        return ((Integer) field).intValue();
    }

    private void a(a aVar, View view) {
        Class<?> cls = view.getClass();
        boolean z = true;
        for (af afVar : this.b) {
            if (afVar.b.isAssignableFrom(cls) && afVar.c != null) {
                Object objA = afVar.c.a(view);
                if (z && !TextUtils.isEmpty(afVar.f93a)) {
                    String str = afVar.f93a;
                    char c2 = 65535;
                    int iHashCode = str.hashCode();
                    if (iHashCode != -1964681502) {
                        if (iHashCode != -1217487446) {
                            if (iHashCode == 92909918 && str.equals("alpha")) {
                                c2 = 1;
                            }
                        } else if (str.equals("hidden")) {
                            c2 = 2;
                        }
                    } else if (str.equals("clickable")) {
                        c2 = 0;
                    }
                    if (c2 == 0) {
                        boolean zBooleanValue = ((Boolean) objA).booleanValue();
                        if (!zBooleanValue || (view instanceof AbsListView) || (view instanceof AbsoluteLayout)) {
                            z = false;
                        }
                        if (!zBooleanValue) {
                            ViewParent parent = view.getParent();
                            if (parent instanceof AdapterView) {
                                boolean z2 = ((AdapterView) parent).getOnItemClickListener() != null;
                                objA = Boolean.valueOf(z2);
                                z = z2;
                            }
                        }
                    } else if (c2 != 1) {
                    }
                }
                if (objA != null) {
                    if (objA instanceof Number) {
                        aVar.a(afVar.f93a).a((Number) objA);
                    } else if (objA instanceof Boolean) {
                        aVar.a(afVar.f93a).a((Boolean) objA);
                    } else if (objA instanceof ColorStateList) {
                        aVar.a(afVar.f93a).a(Integer.valueOf(((ColorStateList) objA).getDefaultColor()));
                    } else if (objA instanceof Drawable) {
                        Drawable drawable = (Drawable) objA;
                        Rect bounds = drawable.getBounds();
                        aVar.a(afVar.f93a);
                        aVar.a();
                        aVar.a("classes");
                        aVar.d();
                        for (Class<?> superclass = drawable.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
                            aVar.b(superclass.getName());
                        }
                        aVar.e();
                        aVar.a("dimensions");
                        aVar.a();
                        aVar.a("left").a(bounds.left);
                        aVar.a("right").a(bounds.right);
                        aVar.a("top").a(bounds.top);
                        aVar.a("bottom").a(bounds.bottom);
                        aVar.b();
                        if (drawable instanceof ColorDrawable) {
                            aVar.a("color").a(((ColorDrawable) drawable).getColor());
                        }
                        aVar.b();
                    } else {
                        aVar.a(afVar.f93a).b(objA.toString());
                    }
                }
            }
        }
        aVar.a("processable").b(z ? "1" : "0");
    }

    private void a(b bVar, a aVar, View view) throws InterruptedException {
        aVar.d();
        b(bVar, aVar, view);
        aVar.e();
    }

    private void b(b bVar, a aVar, View view) throws InterruptedException {
        int id = view.getId();
        String strNameFromId = -1 != id ? SystemIds.getInstance().nameFromId(view.getResources(), id) : null;
        aVar.a();
        aVar.a("hashCode").a(view.hashCode());
        aVar.a("invisible").a(Boolean.valueOf(b(view)));
        aVar.a("id").a(id);
        if (strNameFromId == null) {
            aVar.a("mp_id_name").f();
        } else {
            aVar.a("mp_id_name").b(strNameFromId);
        }
        CharSequence contentDescription = view.getContentDescription();
        if (contentDescription == null) {
            aVar.a("contentDescription").f();
        } else {
            aVar.a("contentDescription").b(contentDescription.toString());
        }
        Object tag = view.getTag();
        if (tag == null) {
            aVar.a("tag").f();
        } else if (tag instanceof CharSequence) {
            aVar.a("tag").b(tag.toString());
        }
        int iA = a(view);
        if (iA != -1) {
            aVar.a("row").a(iA);
        }
        aVar.a("top").a(view.getTop());
        aVar.a("left").a(view.getLeft());
        aVar.a("width").a(view.getWidth());
        aVar.a("height").a(view.getHeight());
        aVar.a("scrollX").a(view.getScrollX());
        aVar.a("scrollY").a(view.getScrollY());
        aVar.a("visibility").a(view.getVisibility());
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        aVar.a("atop").a(iArr[1]);
        aVar.a("aleft").a(iArr[0]);
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        aVar.a("translationX").a(Float.valueOf(translationX));
        aVar.a("translationY").a(Float.valueOf(translationY));
        aVar.a("classes");
        aVar.d();
        Class<?> superclass = view.getClass();
        do {
            aVar.b(this.c.get(superclass));
            superclass = superclass.getSuperclass();
            if (superclass == Object.class) {
                break;
            }
        } while (superclass != null);
        aVar.e();
        a(aVar, view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            int[] rules = ((RelativeLayout.LayoutParams) layoutParams).getRules();
            aVar.a("layoutRules");
            aVar.d();
            for (int i : rules) {
                aVar.a(i);
            }
            aVar.e();
        }
        if (UniqueViewHelper.isWebView(view.getClass())) {
            this.f = true;
            String strA = am.a().a(view);
            if (strA != null) {
                aVar.a("h5_view").a((Object) strA);
            }
            aVar.a("subviews");
            aVar.d();
            aVar.e();
            aVar.b();
            return;
        }
        aVar.a("subviews");
        aVar.d();
        boolean z = view instanceof ViewGroup;
        if (z) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt != null) {
                    aVar.a(childAt.hashCode());
                }
            }
        }
        aVar.e();
        aVar.b();
        if (z) {
            ViewGroup viewGroup2 = (ViewGroup) view;
            int childCount2 = viewGroup2.getChildCount();
            for (int i3 = 0; i3 < childCount2; i3++) {
                View childAt2 = viewGroup2.getChildAt(i3);
                if (childAt2 != null) {
                    b(bVar, aVar, childAt2);
                }
            }
        }
    }

    private boolean b(View view) {
        int visibility = view.getVisibility();
        return visibility == 4 || visibility == 8 || !view.getGlobalVisibleRect(new Rect()) || !view.getLocalVisibleRect(new Rect());
    }

    List<WindowUIHelper.PageRootInfo> a() {
        FutureTask futureTask = new FutureTask(this.f88a);
        this.d.post(futureTask);
        try {
            Collections.emptyList();
            return (List) futureTask.get(5L, TimeUnit.SECONDS);
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return null;
        }
    }

    void a(OutputStream outputStream, List<WindowUIHelper.PageRootInfo> list) throws InterruptedException {
        this.f = false;
        b bVar = new b(outputStream);
        bVar.a("[");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                bVar.a(",");
            }
            WindowUIHelper.PageRootInfo pageRootInfo = list.get(i);
            bVar.a("{");
            bVar.a("\"activity\":");
            bVar.a(JSONObject.quote(pageRootInfo.activityName));
            bVar.a(",");
            bVar.a("\"scale\":");
            bVar.a(String.format("%s", Float.valueOf(pageRootInfo.scale)));
            bVar.a(",");
            bVar.a("\"serialized_objects\":");
            a aVar = new a(bVar);
            aVar.a();
            aVar.a("rootObject").a(pageRootInfo.rootView.hashCode());
            aVar.a("objects");
            a(bVar, aVar, pageRootInfo.rootView);
            aVar.b();
            aVar.c();
            bVar.a(",");
            bVar.a("\"screenshot\":");
            bVar.a();
            pageRootInfo.screenshot.writeBitmapJSON(Bitmap.CompressFormat.PNG, 100, outputStream);
            bVar.a("}");
        }
        bVar.a("]");
        bVar.a();
    }

    boolean a(List<WindowUIHelper.PageRootInfo> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<WindowUIHelper.PageRootInfo> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getSign());
        }
        String string = sb.toString();
        if (!TextUtils.isEmpty(string) && TextUtils.equals(this.e, string) && !am.a().b()) {
            return false;
        }
        this.e = string;
        return true;
    }
}

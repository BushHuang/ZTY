package com.analysys.visual;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import com.analysys.allgro.plugin.ASMProbeHelp;
import com.analysys.ui.RootView;
import com.analysys.ui.WindowUIHelper;
import com.analysys.utils.ANSLog;
import com.analysys.utils.ActivityLifecycleUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import com.analysys.visual.bind.VisualASMListener;
import com.analysys.visual.utils.VisualIpc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {

    private static final b f120a = new b();
    private boolean b;
    private boolean e;
    private Handler i;
    private HandlerThread j;
    private List<i> c = new ArrayList();
    private final List<RootView> d = new ArrayList();
    private final ActivityLifecycleUtils.BaseLifecycleCallback f = new ActivityLifecycleUtils.BaseLifecycleCallback() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            View viewFindViewById = activity.findViewById(16908290);
            if (viewFindViewById == null) {
                return;
            }
            RootView rootView = new RootView(viewFindViewById.getRootView(), WindowUIHelper.getActivityName(activity));
            ANSLog.i("analysys.visual", "activity onCreate " + rootView.pageName);
            b.this.c(rootView);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            View viewFindViewById = activity.findViewById(16908290);
            if (viewFindViewById == null) {
                return;
            }
            RootView rootView = new RootView(viewFindViewById.getRootView(), WindowUIHelper.getActivityName(activity));
            ANSLog.i("analysys.visual", "activity onDestroyed " + rootView.pageName);
            b.this.b(rootView);
        }
    };
    private final List<a> g = new ArrayList();
    private final Handler h = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            b.this.a(message);
        }
    };
    private List<i> k = new ArrayList();
    private JSONArray l = new JSONArray();

    class a implements Runnable {
        private final RootView b;
        private long c;

        a(RootView rootView, long j) {
            this.b = rootView;
            this.c = j;
        }

        boolean a() {
            return Math.abs(System.currentTimeMillis() - this.c) > 5000;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return ((a) obj).b.equals(this.b);
            }
            return false;
        }

        @Override
        public void run() {
            List listL = b.this.l();
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < listL.size()) {
                i iVar = (i) listL.get(i);
                try {
                    if (!(iVar instanceof o)) {
                        iVar.b(this.b);
                        iVar.a(this.b);
                    } else if (iVar.c(this.b)) {
                        jSONArray.put(new JSONObject(iVar.m));
                    }
                } catch (Throwable th) {
                    if (th.getClass().getName().contains("CalledFromWrongThreadException")) {
                        b.this.i();
                        i--;
                    } else {
                        ANSLog.i("analysys.visual", "-bind fail " + this.b.pageName + ", " + this + ", event list: " + listL.size());
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
                i++;
            }
            if (jSONArray.length() > 0) {
                am.a().a(this.b.view, jSONArray.toString());
            }
            b.this.g.remove(this);
        }
    }

    class C0011b extends ArrayList<View> {
        C0011b() {
        }

        @Override
        public View remove(int i) {
            RootView rootView;
            View view = get(i);
            if (view != null && (rootView = WindowUIHelper.getRootView(view)) != null) {
                ANSLog.i("analysys.visual", "window removed");
                b.this.b(rootView);
            }
            return (View) super.remove(i);
        }

        @Override
        public boolean add(View view) {
            RootView rootView;
            if (view != null && (rootView = WindowUIHelper.getRootView(view)) != null) {
                ANSLog.i("analysys.visual", "new window attached");
                b.this.c(rootView);
            }
            return super.add(view);
        }
    }

    private b() {
    }

    public static b a() {
        return f120a;
    }

    private void a(Message message) {
        if (message.what == 1) {
            if (!k()) {
                try {
                    h();
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
            this.i.sendMessageDelayed(this.i.obtainMessage(1), 500L);
        }
    }

    private void a(RootView rootView) {
        int iIndexOf = this.d.indexOf(rootView);
        if (iIndexOf < 0) {
            ANSLog.e("analysys.visual", "unBindRootView not exists");
            return;
        }
        RootView rootView2 = this.d.get(iIndexOf);
        ViewTreeObserver viewTreeObserver = rootView2.view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            if (Build.VERSION.SDK_INT >= 16) {
                viewTreeObserver.removeOnGlobalLayoutListener(rootView2.globalLayoutListener);
            }
            viewTreeObserver.removeOnScrollChangedListener(rootView2.scrollChangedListener);
        }
        int i = 0;
        while (true) {
            if (i >= this.g.size()) {
                i = -1;
                break;
            } else if (this.g.get(i).b.equals(rootView2)) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            this.i.removeCallbacks(this.g.get(i));
            this.g.remove(i);
        }
        List<i> listL = l();
        int i2 = 0;
        while (i2 < listL.size()) {
            i iVar = listL.get(i2);
            if (!(iVar instanceof o)) {
                try {
                    iVar.b(rootView2);
                } catch (Throwable th) {
                    if (th.getClass().getName().contains("CalledFromWrongThreadException")) {
                        i();
                        i2--;
                    } else {
                        ANSLog.i("analysys.visual", "-unbind fail " + rootView2.pageName + ", event list: " + listL.size());
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
            }
            i2++;
        }
        am.a().a(rootView2.hashCode);
        this.d.remove(rootView2);
    }

    private void a(String str, JSONArray jSONArray) {
        try {
            List<i> arrayList = new ArrayList<>();
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                i iVarA = d.a(str, jSONObject);
                if (iVarA != null) {
                    arrayList.add(iVarA);
                    jSONArray2.put(jSONObject);
                }
            }
            List<i> listL = l();
            if (listL.isEmpty()) {
                this.l = jSONArray2;
            } else {
                a(listL, arrayList, this.l, jSONArray2);
                arrayList = listL;
            }
            a(arrayList);
        } catch (Throwable th) {
            ANSLog.e("analysys.visual", "mergeEvents json error", th);
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private synchronized void a(List<i> list) {
        if (list == null) {
            return;
        }
        if (this.k.isEmpty() && list.isEmpty()) {
            return;
        }
        if (!this.e) {
            f();
            this.e = true;
        }
        final List<i> list2 = this.k;
        this.k = list;
        this.i.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Iterator it = b.this.g.iterator();
                    while (it.hasNext()) {
                        b.this.i.removeCallbacks((a) it.next());
                    }
                    b.this.g.clear();
                    boolean z = false;
                    for (i iVar : list2) {
                        if (!(iVar instanceof o)) {
                            try {
                                iVar.b();
                            } catch (Throwable th) {
                                ExceptionUtil.exceptionThrow(th);
                            }
                        } else if (!z) {
                            z = true;
                        }
                    }
                    if (z) {
                        am.a().c();
                    }
                    if (b.this.k.isEmpty()) {
                        return;
                    }
                    Iterator it2 = b.this.d.iterator();
                    while (it2.hasNext()) {
                        b.this.d((RootView) it2.next());
                    }
                } catch (Throwable th2) {
                    ExceptionUtil.exceptionThrow(th2);
                }
            }
        });
    }

    private void a(List<i> list, List<i> list2, JSONArray jSONArray, JSONArray jSONArray2) throws JSONException {
        for (int i = 0; i < list2.size(); i++) {
            i iVar = list2.get(i);
            JSONObject jSONObject = jSONArray2.getJSONObject(i);
            boolean z = true;
            int size = list.size() - 1;
            while (true) {
                if (size < 0) {
                    z = false;
                    break;
                } else if (!list.get(size).equals(iVar)) {
                    size--;
                } else if (iVar.c.equals("delete")) {
                    list.remove(size);
                    ((List) AnsReflectUtils.getField(jSONArray, "values")).remove(size);
                } else {
                    list.set(size, iVar);
                    jSONArray.put(size, jSONObject);
                }
            }
            if (!z && !iVar.c.equals("delete")) {
                list.add(iVar);
                jSONArray.put(jSONObject);
            }
        }
    }

    private void a(JSONArray jSONArray) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String strOptString = jSONObject.optString("event_id", "");
            String strOptString2 = jSONObject.optString("event_name", "");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("event_id", strOptString);
            jSONObject2.put("event_name", strOptString2);
            jSONArray2.put(jSONObject2);
        }
        ANSLog.i("analysys.visual", "Get visual config list success: " + jSONArray2.toString());
    }

    private void b(final RootView rootView) {
        this.i.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    b.this.a(rootView);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        }, 500L);
    }

    private void c(final RootView rootView) {
        this.i.post(new Runnable() {
            @Override
            public void run() {
                try {
                    b.this.d(rootView);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private void d(final RootView rootView) {
        if (!this.d.contains(rootView)) {
            this.d.add(rootView);
            ViewTreeObserver viewTreeObserver = rootView.view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                rootView.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (WindowUIHelper.isRootViewAlive(rootView.view)) {
                            b.this.c(rootView);
                        }
                    }
                };
                viewTreeObserver.addOnGlobalLayoutListener(rootView.globalLayoutListener);
                rootView.scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        b.this.c(rootView);
                    }
                };
                viewTreeObserver.addOnScrollChangedListener(rootView.scrollChangedListener);
            }
        }
        if (k()) {
            return;
        }
        int i = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= this.g.size()) {
                break;
            }
            if (this.g.get(i2).b.equals(rootView)) {
                i = i2;
                break;
            }
            i2++;
        }
        if (i < 0) {
            a aVar = new a(rootView, System.currentTimeMillis());
            this.g.add(aVar);
            this.i.postDelayed(aVar, 200L);
            return;
        }
        a aVar2 = this.g.get(i);
        if (!aVar2.a()) {
            this.i.removeCallbacks(aVar2);
            this.i.postDelayed(aVar2, 200L);
        } else {
            ANSLog.w("analysys.visual", "reBindRootView wait too long: " + rootView.pageName);
        }
    }

    private void d(String str) {
        if (TextUtils.isEmpty(str)) {
            ANSLog.i("analysys.visual", "refreshEvents empty");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                i iVarA = d.a("add", jSONArray.getJSONObject(i));
                if (iVarA != null) {
                    arrayList.add(iVarA);
                }
            }
            a(arrayList);
        } catch (Throwable th) {
            ANSLog.e("analysys.visual", "refreshEvents json error: " + str, th);
            a((String) null);
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private void f() {
        ANSLog.i("analysys.visual", "init visual bind");
        HandlerThread handlerThread = new HandlerThread("visual_bind");
        this.j = handlerThread;
        handlerThread.start();
        this.i = new Handler(this.j.getLooper()) {
            @Override
            public void handleMessage(Message message) {
                b.this.a(message);
            }
        };
        ASMProbeHelp.getInstance().registerHookObserver(new VisualASMListener());
        if (!g()) {
            ANSLog.i("analysys.visual", "init window manager hook failed");
            ActivityLifecycleUtils.addCallback(this.f);
            if (WindowUIHelper.getAllWindowViews() != null) {
                ANSLog.i("analysys.visual", "check window");
                this.i.sendMessageDelayed(this.i.obtainMessage(1), 500L);
            }
        }
        this.i.post(new Runnable() {
            @Override
            public void run() {
                try {
                    b.this.h();
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private boolean g() {
        List globalViews;
        Object windowManagerGlobal = WindowUIHelper.getWindowManagerGlobal();
        if (windowManagerGlobal == null || (globalViews = WindowUIHelper.getGlobalViews(false)) == null) {
            return false;
        }
        C0011b c0011b = new C0011b();
        c0011b.addAll(globalViews);
        return WindowUIHelper.setGlobalViews(windowManagerGlobal, c0011b);
    }

    private void h() {
        List<RootView> allWindowViews = WindowUIHelper.getAllWindowViews();
        if (allWindowViews == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int size = this.d.size() - 1; size >= 0; size--) {
            RootView rootView = this.d.get(size);
            if (!allWindowViews.contains(rootView)) {
                arrayList.add(rootView);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b((RootView) it.next());
        }
        for (RootView rootView2 : allWindowViews) {
            if (!this.d.contains(rootView2)) {
                c(rootView2);
            }
        }
    }

    private void i() {
        if (this.i != this.h) {
            this.j.quit();
            this.i = this.h;
            ANSLog.i("analysys.visual", "--change to main thread");
        }
    }

    private SharedPreferences j() {
        return AnalysysUtil.getContext().getSharedPreferences("viewcrawler.sp", 0);
    }

    private synchronized boolean k() {
        return this.k.isEmpty();
    }

    private synchronized List<i> l() {
        return new ArrayList(this.k);
    }

    public String a(Object obj, String str) {
        for (i iVar : l()) {
            if (iVar instanceof o) {
                return ((o) iVar).a(obj, str);
            }
        }
        return null;
    }

    public void a(View view, int i) {
        RootView rootView;
        List<View> listA;
        if (view == null || (rootView = WindowUIHelper.getRootView(view)) == null) {
            return;
        }
        int i2 = rootView.hashCode;
        for (i iVar : l()) {
            if (iVar instanceof k) {
                k kVar = (k) iVar;
                if (kVar.g() == i && (listA = iVar.a(i2)) != null) {
                    Iterator<View> it = listA.iterator();
                    while (it.hasNext()) {
                        if (it.next() == view) {
                            if (kVar.a(i2, view) instanceof j) {
                                return;
                            }
                            kVar.a(view, Integer.valueOf(i));
                            return;
                        }
                    }
                }
            }
        }
    }

    public void a(i iVar) {
        ANSLog.i("analysys.visual", "Report event: " + iVar.b + ", properties: " + iVar.f());
        VisualIpc.getInstance().reportVisualEvent(iVar.b, iVar.e, iVar.f());
    }

    public void a(Runnable runnable) {
        Handler handler = this.i;
        if (handler != null) {
            handler.postAtFrontOfQueue(runnable);
        }
    }

    public void a(String str) {
        try {
            j().edit().putString("viewcrawler.bindings", str).apply();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void a(String str, String str2, String str3) {
        ANSLog.i("analysys.visual", "Report event: " + str + ", properties: " + str2);
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            i iVar = null;
            Iterator<i> it = l().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                i next = it.next();
                if (str.equals(next.b)) {
                    if (next instanceof o) {
                        ((o) next).a(str2, str3);
                        iVar = next;
                    }
                }
            }
            if (iVar != null) {
                a(iVar);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void a(boolean z) {
        if (this.b == z) {
            return;
        }
        ANSLog.i("analysys.visual", "setEditing: " + z);
        this.b = z;
        a(this.c);
        if (this.b) {
            return;
        }
        c();
    }

    public boolean b() {
        return this.b;
    }

    public boolean b(String str) {
        HttpURLConnection httpURLConnection;
        String string;
        JSONObject jSONObject;
        try {
            URL url = new URL(str);
            if (str.startsWith("https")) {
                httpURLConnection = (HttpsURLConnection) url.openConnection();
                if (CommonUtils.getSSLSocketFactory(AnalysysUtil.getContext()) != null) {
                    ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(CommonUtils.getSSLSocketFactory(AnalysysUtil.getContext()));
                }
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            bufferedReader.close();
            string = sb.toString();
            jSONObject = new JSONObject(string);
        } catch (Throwable th) {
            ANSLog.e("analysys.visual", "Get visual config list failed: " + th.getMessage(), th);
            ExceptionUtil.exceptionPrint(th);
        }
        if (jSONObject.optInt("code") != 0) {
            ANSLog.e("analysys.visual", "Get visual config list failed: " + string);
            return false;
        }
        String string2 = jSONObject.getString("data");
        if (ANSLog.isShowLog) {
            a(jSONObject.getJSONArray("data"));
        }
        ANSLog.i("analysys.visual", "Get visual config list success: " + string2);
        a(string2);
        if (!this.b) {
            d(string2);
            VisualIpc.getInstance().reloadVisualEventLocal();
        }
        return true;
    }

    public void c() {
        if (this.b) {
            return;
        }
        try {
            String string = j().getString("viewcrawler.bindings", null);
            ANSLog.i("analysys.visual", "load config from local");
            d(string);
        } catch (Throwable th) {
            ANSLog.e("analysys.visual", "load config from local fail: " + th.getMessage(), th);
            ExceptionUtil.exceptionPrint(th);
        }
    }

    public void c(String str) {
        ANSLog.i("analysys.visual", "update events: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            a(jSONObject.optString("recordtype"), jSONObject.getJSONObject("payload").getJSONArray("events"));
        } catch (Throwable th) {
            ANSLog.e("analysys.visual", "updateEvents json error", th);
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public JSONArray d() {
        return this.l;
    }

    public String e() {
        List<i> listL = l();
        JSONArray jSONArray = new JSONArray();
        for (i iVar : listL) {
            if (iVar.a()) {
                try {
                    jSONArray.put(new JSONObject(iVar.m));
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        }
        return jSONArray.toString();
    }
}

package com.analysys.visual;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.analysys.hybrid.BaseWebViewInjector;
import com.analysys.hybrid.WebViewInjectManager;
import com.analysys.ui.UniqueViewHelper;
import com.analysys.utils.ExceptionUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class am extends BaseWebViewInjector {

    private static am f105a = new am();
    private Map<Integer, a> b = new ConcurrentHashMap();

    class a {
        private volatile String b;
        private volatile boolean c;
        private volatile String d;

        private a() {
            this.c = true;
        }

        void a() {
            this.b = null;
            this.d = null;
        }
    }

    private am() {
        WebViewInjectManager.getInstance().setInjector(this);
    }

    public static am a() {
        return f105a;
    }

    private String a(String str) {
        return "javascript:try{window.AnalysysAgent.onEventList(" + str + ")}catch(err){console.log(err.message)}";
    }

    private void a(Object obj, String str) {
        WebViewInjectManager.getInstance().loadUrl(obj, a(str));
    }

    @Override
    public void AnalysysAgentTrack(int i, String str, String str2, String str3) {
        b.a().a(str, str2, str3);
    }

    public String a(Object obj) throws InterruptedException {
        if (obj == null) {
            return null;
        }
        a aVar = this.b.get(Integer.valueOf(obj.hashCode()));
        if (aVar == null) {
            return null;
        }
        WebViewInjectManager.getInstance().loadUrl(obj, "javascript:try{window.AnalysysAgent.getVisualDomList(" + obj.hashCode() + ")}catch(err){console.log(err.message)}");
        try {
            Thread.sleep(100L);
        } catch (InterruptedException unused) {
        }
        return aVar.b;
    }

    public void a(int i) {
        WebViewInjectManager.getInstance().loadUrlInPage(a(""), i);
        WebViewInjectManager.getInstance().clearHybridInPage(i);
    }

    public void a(View view, String str) {
        if (view != null && UniqueViewHelper.isWebView(view.getClass())) {
            a((Object) view, str);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                a(viewGroup.getChildAt(i), str);
            }
        }
    }

    public Map<String, Object> b(View view, String str) throws InterruptedException {
        if (view == null) {
            return null;
        }
        a aVar = this.b.get(Integer.valueOf(view.hashCode()));
        if (aVar == null) {
            return null;
        }
        aVar.d = null;
        WebViewInjectManager.getInstance().loadUrl(view, "javascript:try{window.AnalysysAgent.getProperty(" + str + ")}catch(err){console.log(err.message)}");
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 40 || aVar.d != null) {
                break;
            }
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
            }
            i = i2;
        }
        String str2 = aVar.d;
        if (!TextUtils.isEmpty(str2)) {
            HashMap map = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(str2);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.get(next));
                }
                return map;
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return null;
    }

    public boolean b() {
        Iterator<a> it = this.b.values().iterator();
        while (it.hasNext()) {
            if (it.next().c) {
                return true;
            }
        }
        return false;
    }

    public void c() {
        WebViewInjectManager.getInstance().loadUrlAll(a(""));
    }

    @Override
    public void clearHybrid(int i) {
        a aVarRemove = this.b.remove(Integer.valueOf(i));
        if (aVarRemove != null) {
            aVarRemove.a();
        }
    }

    @Override
    public String getEventList(int i) {
        return b.a().e();
    }

    @Override
    public String getProperty(Object obj, String str) {
        String strA = obj != null ? b.a().a(obj, str) : null;
        return TextUtils.isEmpty(strA) ? "{}" : strA;
    }

    @Override
    public boolean isHybrid(int i) {
        return true;
    }

    @Override
    public void notifyInject(int i) {
        this.b.put(Integer.valueOf(i), new a());
    }

    @Override
    public void onProperty(int i, String str) {
        a aVar = this.b.get(Integer.valueOf(i));
        if (aVar == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            aVar.d = "";
        } else {
            aVar.d = str;
        }
    }

    @Override
    public void onVisualDomList(int i, String str) {
        a aVar = this.b.get(Integer.valueOf(i));
        if (aVar == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            aVar.c = true;
            aVar.b = "";
        } else {
            aVar.c = !str.equals(aVar.b);
            aVar.b = str;
        }
    }
}

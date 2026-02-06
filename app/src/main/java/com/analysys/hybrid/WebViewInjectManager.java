package com.analysys.hybrid;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import com.analysys.ui.UniqueViewHelper;
import com.analysys.ui.WindowUIHelper;
import com.analysys.utils.ActivityLifecycleUtils;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.ExceptionUtil;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebViewInjectManager {
    private static final String WEB_OBJ = "AnalysysAgentHybrid";
    private static String sInjectJsSdk;
    private static WebViewInjectManager sInstance = new WebViewInjectManager();
    private Map<Integer, a> mHybridObjects = new ConcurrentHashMap();
    private BaseWebViewInjector mInjector;

    public class a {
        private volatile boolean b;
        private Handler c;
        private Object d;
        private int e;
        private String f;

        a(Object obj) {
            this.d = obj;
            Object objInvokeMethod = AnsReflectUtils.invokeMethod(obj, "getWebViewLooper");
            objInvokeMethod = objInvokeMethod == null ? Looper.getMainLooper() : objInvokeMethod;
            if (obj instanceof View) {
                this.e = ((View) obj).getRootView().hashCode();
            }
            this.c = new Handler((Looper) objInvokeMethod);
            c();
        }

        private boolean a(Object obj, String str) {
            return WebViewInjectManager.this.callWebViewMethod(this.d, this.c, "addJavascriptInterface", new Class[]{Object.class, String.class}, new Object[]{obj, str});
        }

        private boolean a(String str) {
            return WebViewInjectManager.this.callWebViewMethod(this.d, this.c, "removeJavascriptInterface", new Class[]{String.class}, new Object[]{str});
        }

        private boolean b(String str) {
            return WebViewInjectManager.this.callWebViewMethod(this.d, this.c, "loadUrl", new Class[]{String.class}, new Object[]{str});
        }

        private void c() {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Object objInvokeMethod = AnsReflectUtils.invokeMethod(a.this.d, "getSettings");
                        if (objInvokeMethod != null) {
                            AnsReflectUtils.invokeMethod(objInvokeMethod, "setJavaScriptEnabled", new Class[]{Boolean.TYPE}, new Object[]{true});
                        }
                        a.this.a((Object) a.this, "AnalysysAgentHybrid");
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                    a.this.b = true;
                }
            };
            if (Thread.currentThread().getId() == this.c.getLooper().getThread().getId()) {
                runnable.run();
            } else {
                this.c.post(runnable);
            }
            if (TextUtils.isEmpty(WebViewInjectManager.sInjectJsSdk)) {
                return;
            }
            this.c.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        String str = (String) AnsReflectUtils.invokeMethod(a.this.d, "getUrl");
                        if (TextUtils.isEmpty(str) || !TextUtils.equals(str, a.this.f)) {
                            a.this.f = str;
                            if (!TextUtils.isEmpty(str)) {
                                a.this.b(WebViewInjectManager.sInjectJsSdk);
                            }
                        }
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                    if (a.this.c != null) {
                        a.this.c.postDelayed(this, 1000L);
                    }
                }
            }, 1000L);
        }

        @JavascriptInterface
        public void AnalysysAgentTrack(String str, String str2, String str3) {
            if (WebViewInjectManager.this.mInjector != null) {
                WebViewInjectManager.this.mInjector.AnalysysAgentTrack(this.d.hashCode(), str, str2, str3);
            }
        }

        int a() {
            return this.e;
        }

        @JavascriptInterface
        public void analysysHybridCallNative(String str) {
            HybridBridge.getInstance().execute(str, this.d);
        }

        void b() {
            if (WebViewInjectManager.this.mInjector != null) {
                WebViewInjectManager.this.mInjector.clearHybrid(this.d.hashCode());
            }
            a("AnalysysAgentHybrid");
            this.d = null;
            this.c = null;
        }

        @JavascriptInterface
        public String getEventList() {
            if (WebViewInjectManager.this.mInjector != null) {
                return WebViewInjectManager.this.mInjector.getEventList(this.d.hashCode());
            }
            return null;
        }

        @JavascriptInterface
        public String getProperty(String str) {
            if (WebViewInjectManager.this.mInjector != null) {
                return WebViewInjectManager.this.mInjector.getProperty(this.d, str);
            }
            return null;
        }

        @JavascriptInterface
        public boolean isHybrid() {
            if (WebViewInjectManager.this.mInjector != null) {
                return WebViewInjectManager.this.mInjector.isHybrid(this.d.hashCode());
            }
            return true;
        }

        @JavascriptInterface
        public void onProperty(String str) {
            if (WebViewInjectManager.this.mInjector != null) {
                WebViewInjectManager.this.mInjector.onProperty(this.d.hashCode(), str);
            }
        }

        @JavascriptInterface
        public void onVisualDomList(String str) {
            if (WebViewInjectManager.this.mInjector != null) {
                WebViewInjectManager.this.mInjector.onVisualDomList(this.d.hashCode(), str);
            }
        }
    }

    private WebViewInjectManager() {
    }

    private boolean callWebViewMethod(final Object obj, Handler handler, String str, Class[] clsArr, final Object[] objArr) {
        final Method methodFindMethod;
        if (handler == null || obj == null || (methodFindMethod = AnsReflectUtils.findMethod(obj.getClass(), str, clsArr)) == null) {
            return false;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    methodFindMethod.invoke(obj, objArr);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        };
        if (Thread.currentThread().getId() == handler.getLooper().getThread().getId()) {
            runnable.run();
            return true;
        }
        handler.post(runnable);
        return true;
    }

    public static WebViewInjectManager getInstance() {
        return sInstance;
    }

    public static void setInjectJsSdk(String str) {
        sInjectJsSdk = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ActivityLifecycleUtils.addCallback(new ActivityLifecycleUtils.BaseLifecycleCallback() {

            private Set<Integer> f49a = new HashSet();

            private void a(Activity activity) {
                try {
                    View viewFindViewById = activity.findViewById(16908290);
                    if (viewFindViewById == null) {
                        return;
                    }
                    final View rootView = viewFindViewById.getRootView();
                    ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                if (WindowUIHelper.isRootViewAlive(rootView)) {
                                    WebViewInjectManager.getInstance().injectWebViewInPage(rootView);
                                }
                            }
                        });
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                try {
                    int iHashCode = activity.getWindow().getDecorView().hashCode();
                    if (this.f49a.contains(Integer.valueOf(iHashCode))) {
                        this.f49a.remove(Integer.valueOf(iHashCode));
                        WebViewInjectManager.getInstance().clearHybridInPage(iHashCode);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                try {
                    int iHashCode = activity.getWindow().getDecorView().hashCode();
                    if (this.f49a.contains(Integer.valueOf(iHashCode))) {
                        return;
                    }
                    this.f49a.add(Integer.valueOf(iHashCode));
                    a(activity);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void clearHybrid(Object obj) {
        if (obj == null) {
            return;
        }
        a aVarRemove = this.mHybridObjects.remove(Integer.valueOf(obj.hashCode()));
        if (aVarRemove != null) {
            aVarRemove.b();
        }
    }

    public void clearHybridInPage(int i) {
        Iterator<Map.Entry<Integer, a>> it = this.mHybridObjects.entrySet().iterator();
        while (it.hasNext()) {
            a value = it.next().getValue();
            if (i == value.a()) {
                value.b();
                it.remove();
            }
        }
    }

    public synchronized a injectHybridObject(Object obj) {
        if (obj == null) {
            return null;
        }
        int iHashCode = obj.hashCode();
        if (this.mHybridObjects.containsKey(Integer.valueOf(iHashCode))) {
            return this.mHybridObjects.get(Integer.valueOf(iHashCode));
        }
        a aVar = new a(obj);
        this.mHybridObjects.put(Integer.valueOf(iHashCode), aVar);
        if (this.mInjector != null) {
            this.mInjector.notifyInject(iHashCode);
        }
        return aVar;
    }

    public void injectWebViewInPage(View view) {
        if (view != null && UniqueViewHelper.isWebView(view.getClass())) {
            injectHybridObject(view);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                injectWebViewInPage(viewGroup.getChildAt(i));
            }
        }
    }

    public void loadUrl(Object obj, String str) {
        if (obj == null) {
            return;
        }
        a aVar = this.mHybridObjects.get(Integer.valueOf(obj.hashCode()));
        if (aVar == null) {
            return;
        }
        aVar.b(str);
    }

    public void loadUrlAll(String str) {
        Iterator<Integer> it = this.mHybridObjects.keySet().iterator();
        while (it.hasNext()) {
            this.mHybridObjects.get(it.next()).b(str);
        }
    }

    public void loadUrlInPage(String str, int i) {
        Iterator<Map.Entry<Integer, a>> it = this.mHybridObjects.entrySet().iterator();
        while (it.hasNext()) {
            a value = it.next().getValue();
            if (i == value.a()) {
                value.b(str);
            }
        }
    }

    public void setInjector(BaseWebViewInjector baseWebViewInjector) {
        Iterator<Integer> it = this.mHybridObjects.keySet().iterator();
        while (it.hasNext()) {
            baseWebViewInjector.notifyInject(it.next().intValue());
        }
        this.mInjector = baseWebViewInjector;
    }
}

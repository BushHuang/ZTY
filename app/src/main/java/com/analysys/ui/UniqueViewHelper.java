package com.analysys.ui;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniqueViewHelper {
    public static final String UNIQUE_CLZ_CONTEXT_THEME_WRAPPER = "unique.ContextThemeWrapper";
    public static final String UNIQUE_CLZ_RECYCLER_VIEW = "unique.RecyclerView";
    public static final String UNIQUE_CLZ_VIEW_PAGER = "unique.ViewPager";
    public static final String UNIQUE_CLZ_WEB_VIEW = "unique.WebView";
    private static Map<String, List<String>> sUniqueClzMap = new HashMap();

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.android.internal.widget.ViewPager");
        arrayList.add("androidx.viewpager.widget.ViewPager");
        arrayList.add("androidx.viewpager.widget.ViewPager");
        sUniqueClzMap.put("unique.ViewPager", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("com.android.internal.widget.RecyclerView");
        arrayList2.add("androidx.recyclerview.widget.RecyclerView");
        arrayList2.add("androidx.recyclerview.widget.RecyclerView");
        sUniqueClzMap.put("unique.RecyclerView", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("android.webkit.WebView");
        arrayList3.add("com.tencent.smtt.sdk.WebView");
        sUniqueClzMap.put("unique.WebView", arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add("android.view.ContextThemeWrapper");
        arrayList4.add("androidx.appcompat.view.ContextThemeWrapper");
        sUniqueClzMap.put("unique.ContextThemeWrapper", arrayList4);
    }

    public static String getUniqueClzName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Set<String> setKeySet = sUniqueClzMap.keySet();
        String name = Object.class.getName();
        while (!TextUtils.equals(str, name)) {
            for (String str2 : setKeySet) {
                if (sUniqueClzMap.get(str2).contains(str)) {
                    return str2;
                }
            }
            try {
                str = Class.forName(str).getSuperclass().getName();
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static boolean isContextThemeWrapper(String str) {
        return isExtendsFromUniqueClass(str, "unique.ContextThemeWrapper");
    }

    public static boolean isExtendsFromUniqueClass(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        boolean zIsUniqueClassExactly = isUniqueClassExactly(str, str2);
        if (!zIsUniqueClassExactly) {
            try {
                Class<?> cls = Class.forName(str);
                while (cls != Object.class) {
                    cls = cls.getSuperclass();
                    if (cls != null && isUniqueClassExactly(cls.getName(), str2)) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return zIsUniqueClassExactly;
    }

    private static boolean isUniqueClassExactly(String str, String str2) {
        List<String> list = sUniqueClzMap.get(str2);
        if (list == null) {
            return false;
        }
        return list.contains(str);
    }

    public static boolean isWebView(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        return isWebView(cls.getName());
    }

    public static boolean isWebView(String str) {
        return isExtendsFromUniqueClass(str, "unique.WebView");
    }
}

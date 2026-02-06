package com.huawei.hms.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

public abstract class ResourceLoaderUtil {

    public static Context f381a;
    public static String b;

    public static int getAnimId(String str) {
        return f381a.getResources().getIdentifier(str, "anim", b);
    }

    public static int getColorId(String str) {
        return f381a.getResources().getIdentifier(str, "color", b);
    }

    public static Drawable getDrawable(String str) {
        return f381a.getResources().getDrawable(getDrawableId(str));
    }

    public static int getDrawableId(String str) {
        return f381a.getResources().getIdentifier(str, "drawable", b);
    }

    public static int getIdId(String str) {
        return f381a.getResources().getIdentifier(str, "id", b);
    }

    public static int getLayoutId(String str) {
        return f381a.getResources().getIdentifier(str, "layout", b);
    }

    public static String getString(String str) {
        return f381a.getResources().getString(getStringId(str));
    }

    public static String getString(String str, Object... objArr) {
        return f381a.getResources().getString(getStringId(str), objArr);
    }

    public static int getStringId(String str) {
        return f381a.getResources().getIdentifier(str, "string", b);
    }

    public static int getStyleId(String str) {
        return f381a.getResources().getIdentifier(str, "style", b);
    }

    public static Context getmContext() {
        return f381a;
    }

    public static void setmContext(Context context) {
        f381a = context;
        b = context.getPackageName();
    }
}

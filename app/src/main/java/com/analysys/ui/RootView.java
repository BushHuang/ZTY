package com.analysys.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;

public class RootView {
    public ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    public final int hashCode;
    public final String pageName;
    public ViewTreeObserver.OnScrollChangedListener scrollChangedListener;
    public final View view;

    public RootView(View view, String str) {
        this.view = view;
        this.hashCode = view.hashCode();
        this.pageName = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RootView)) {
            return false;
        }
        RootView rootView = (RootView) obj;
        return this.view == rootView.view && TextUtils.equals(this.pageName, rootView.pageName);
    }
}

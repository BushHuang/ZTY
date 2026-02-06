package com.ogaclejapan.smarttablayout;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;

final class Utils {
    private Utils() {
    }

    static int getEnd(View view) {
        return getEnd(view, false);
    }

    static int getEnd(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        return isLayoutRtl(view) ? z ? view.getLeft() + getPaddingEnd(view) : view.getLeft() : z ? view.getRight() - getPaddingEnd(view) : view.getRight();
    }

    static int getMarginEnd(View view) {
        if (view == null) {
            return 0;
        }
        return MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) view.getLayoutParams());
    }

    static int getMarginHorizontally(View view) {
        if (view == null) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginStart(marginLayoutParams) + MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
    }

    static int getMarginStart(View view) {
        if (view == null) {
            return 0;
        }
        return MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) view.getLayoutParams());
    }

    static int getMeasuredWidth(View view) {
        if (view == null) {
            return 0;
        }
        return view.getMeasuredWidth();
    }

    static int getPaddingEnd(View view) {
        if (view == null) {
            return 0;
        }
        return ViewCompat.getPaddingEnd(view);
    }

    static int getPaddingHorizontally(View view) {
        if (view == null) {
            return 0;
        }
        return view.getPaddingLeft() + view.getPaddingRight();
    }

    static int getPaddingStart(View view) {
        if (view == null) {
            return 0;
        }
        return ViewCompat.getPaddingStart(view);
    }

    static int getStart(View view) {
        return getStart(view, false);
    }

    static int getStart(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        return isLayoutRtl(view) ? z ? view.getRight() - getPaddingStart(view) : view.getRight() : z ? view.getLeft() + getPaddingStart(view) : view.getLeft();
    }

    static int getWidth(View view) {
        if (view == null) {
            return 0;
        }
        return view.getWidth();
    }

    static int getWidthWithMargin(View view) {
        return getWidth(view) + getMarginHorizontally(view);
    }

    static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }
}

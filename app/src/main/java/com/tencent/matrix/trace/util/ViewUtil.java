package com.tencent.matrix.trace.util;

import android.view.View;
import android.view.ViewGroup;

public class ViewUtil {

    public static class ViewInfo {
        public int mViewCount = 0;
        public int mViewDeep = 0;
        public String mActivityName = "";

        public String toString() {
            return "ViewCount:" + this.mViewCount + ",ViewDeep:" + this.mViewDeep + ",mActivityName:" + this.mActivityName;
        }
    }

    public static ViewInfo dumpViewInfo(View view) {
        ViewInfo viewInfo = new ViewInfo();
        traversalViewTree(viewInfo, 0, view);
        return viewInfo;
    }

    private static void traversalViewTree(ViewInfo viewInfo, int i, View view) {
        ViewGroup viewGroup;
        int childCount;
        if (view == null) {
            return;
        }
        int i2 = i + 1;
        if (i2 > viewInfo.mViewDeep) {
            viewInfo.mViewDeep = i2;
        }
        if ((view instanceof ViewGroup) && (childCount = (viewGroup = (ViewGroup) view).getChildCount()) > 0) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = viewGroup.getChildAt(i3);
                if (childAt == null || childAt.getVisibility() != 8) {
                    viewInfo.mViewCount++;
                    traversalViewTree(viewInfo, i2, childAt);
                }
            }
        }
    }
}

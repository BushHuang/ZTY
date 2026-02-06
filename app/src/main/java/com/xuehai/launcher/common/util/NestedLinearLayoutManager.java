package com.xuehai.launcher.common.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NestedLinearLayoutManager extends LinearLayoutManager {
    private int[] mMeasuredDimension;

    public NestedLinearLayoutManager(Context context) {
        super(context);
        this.mMeasuredDimension = new int[2];
    }

    public NestedLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.mMeasuredDimension = new int[2];
    }

    public NestedLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMeasuredDimension = new int[2];
    }

    private void measureRecyclerChild(RecyclerView.Recycler recycler, int i, int i2, int i3, int[] iArr) {
        try {
            View viewForPosition = recycler.getViewForPosition(i);
            recycler.bindViewToPosition(viewForPosition, i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewForPosition.getLayoutParams();
            viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
            iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            iArr[1] = viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
            recycler.recycleView(viewForPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < getItemCount(); i5++) {
            if (getOrientation() != 0) {
                measureRecyclerChild(recycler, i5, i, View.MeasureSpec.makeMeasureSpec(i5, 0), this.mMeasuredDimension);
                int[] iArr = this.mMeasuredDimension;
                i4 += iArr[1];
                if (i3 < iArr[0]) {
                    i3 = iArr[0];
                }
            } else {
                measureRecyclerChild(recycler, i5, View.MeasureSpec.makeMeasureSpec(i5, 0), i2, this.mMeasuredDimension);
                int[] iArr2 = this.mMeasuredDimension;
                i3 += iArr2[0];
                if (i4 < iArr2[1]) {
                    i4 = iArr2[1];
                }
            }
        }
        if (mode != 1073741824) {
            size = i3;
        }
        if (mode2 != 1073741824) {
            size2 = i4;
        }
        setMeasuredDimension(size, size2);
    }
}

package com.xuehai.launcher.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.xuehai.launcher.common.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J(\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J \u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\rJ\u0018\u0010\"\u001a\u00020\r2\b\b\u0001\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bJ.\u0010&\u001a\u00020\u00152\b\b\u0002\u0010'\u001a\u00020\b2\b\b\u0002\u0010(\u001a\u00020\b2\b\b\u0002\u0010)\u001a\u00020\b2\b\b\u0002\u0010*\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/xuehai/launcher/common/widget/NormalItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "bottomOffset", "", "leftOffset", "mBounds", "Landroid/graphics/Rect;", "mDivider", "Landroid/graphics/drawable/Drawable;", "mDividerHeight", "rect", "getRect", "()Landroid/graphics/Rect;", "rightOffset", "topOffset", "drawHorizontal", "", "canvas", "Landroid/graphics/Canvas;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "getItemOffsets", "outRect", "view", "Landroid/view/View;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "onDraw", "c", "setDrawable", "drawable", "color", "height", "setPadding", "left", "top", "right", "bottom", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NormalItemDecoration extends RecyclerView.ItemDecoration {
    private final String TAG;
    private int bottomOffset;
    private final Context context;
    private int leftOffset;
    private final Rect mBounds;
    private Drawable mDivider;
    private int mDividerHeight;
    private final Rect rect;
    private int rightOffset;
    private int topOffset;

    public NormalItemDecoration(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "NormalItemDecoration";
        this.mBounds = new Rect();
        this.mDivider = setDrawable(R.color.split_line, 1);
        this.rect = new Rect(0, 0, 0, 0);
    }

    private final void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        this.rect.set(this.leftOffset, this.topOffset, parent.getWidth() - this.rightOffset, parent.getHeight() - this.bottomOffset);
        if (parent.getClipToPadding()) {
            this.rect.left += parent.getPaddingLeft();
            this.rect.right -= parent.getPaddingRight();
            this.rect.top += parent.getPaddingTop();
            this.rect.bottom -= parent.getPaddingBottom();
        }
        canvas.clipRect(this.rect);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int iRound = this.mBounds.bottom + Math.round(childAt.getTranslationY());
            this.mDivider.setBounds(this.rect.left, iRound - this.mDividerHeight, this.rect.right, iRound);
            this.mDivider.draw(canvas);
        }
        canvas.restore();
    }

    public static void setPadding$default(NormalItemDecoration normalItemDecoration, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        normalItemDecoration.setPadding(i, i2, i3, i4);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        outRect.set(0, 0, 0, this.mDividerHeight);
    }

    public final Rect getRect() {
        return this.rect;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        if (parent.getLayoutManager() != null) {
            drawHorizontal(c, parent);
        }
    }

    public final Drawable setDrawable(int color, int height) {
        ColorDrawable colorDrawable = new ColorDrawable(this.context.getResources().getColor(color));
        this.mDivider = colorDrawable;
        this.mDividerHeight = height;
        return colorDrawable;
    }

    public final void setDrawable(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        this.mDivider = drawable;
        this.mDividerHeight = drawable.getIntrinsicHeight();
    }

    public final void setPadding(int left, int top, int right, int bottom) {
        this.leftOffset = left;
        this.topOffset = top;
        this.rightOffset = right;
        this.bottomOffset = bottom;
    }
}

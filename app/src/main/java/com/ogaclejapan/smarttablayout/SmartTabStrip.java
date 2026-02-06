package com.ogaclejapan.smarttablayout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

class SmartTabStrip extends LinearLayout {
    private static final int AUTO_WIDTH = -1;
    private static final byte DEFAULT_BOTTOM_BORDER_COLOR_ALPHA = 38;
    private static final int DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 2;
    private static final byte DEFAULT_DIVIDER_COLOR_ALPHA = 32;
    private static final float DEFAULT_DIVIDER_HEIGHT = 0.5f;
    private static final int DEFAULT_DIVIDER_THICKNESS_DIPS = 1;
    private static final boolean DEFAULT_DRAW_DECORATION_AFTER_TAB = false;
    private static final float DEFAULT_INDICATOR_CORNER_RADIUS = 0.0f;
    private static final int DEFAULT_INDICATOR_GRAVITY = 0;
    private static final boolean DEFAULT_INDICATOR_IN_CENTER = false;
    private static final boolean DEFAULT_INDICATOR_IN_FRONT = false;
    private static final boolean DEFAULT_INDICATOR_WITHOUT_PADDING = false;
    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = -13388315;
    private static final byte DEFAULT_TOP_BORDER_COLOR_ALPHA = 38;
    private static final int DEFAULT_TOP_BORDER_THICKNESS_DIPS = 0;
    private static final int GRAVITY_BOTTOM = 0;
    private static final int GRAVITY_CENTER = 2;
    private static final int GRAVITY_TOP = 1;
    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 8;
    private final Paint borderPaint;
    private final int bottomBorderColor;
    private final int bottomBorderThickness;
    private SmartTabLayout.TabColorizer customTabColorizer;
    private final SimpleTabColorizer defaultTabColorizer;
    private final float dividerHeight;
    private final Paint dividerPaint;
    private final int dividerThickness;
    private final boolean drawDecorationAfterTab;
    private SmartTabIndicationInterpolator indicationInterpolator;
    private final boolean indicatorAlwaysInCenter;
    private final float indicatorCornerRadius;
    private final int indicatorGravity;
    private final boolean indicatorInFront;
    private final Paint indicatorPaint;
    private final RectF indicatorRectF;
    private final int indicatorThickness;
    private final int indicatorWidth;
    private final boolean indicatorWithoutPadding;
    private int lastPosition;
    private int selectedPosition;
    private float selectionOffset;
    private final int topBorderColor;
    private final int topBorderThickness;

    private static class SimpleTabColorizer implements SmartTabLayout.TabColorizer {
        private int[] dividerColors;
        private int[] indicatorColors;

        private SimpleTabColorizer() {
        }

        @Override
        public final int getDividerColor(int i) {
            int[] iArr = this.dividerColors;
            return iArr[i % iArr.length];
        }

        @Override
        public final int getIndicatorColor(int i) {
            int[] iArr = this.indicatorColors;
            return iArr[i % iArr.length];
        }

        void setDividerColors(int... iArr) {
            this.dividerColors = iArr;
        }

        void setIndicatorColors(int... iArr) {
            this.indicatorColors = iArr;
        }
    }

    SmartTabStrip(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        int i;
        int[] intArray;
        int[] intArray2;
        super(context);
        this.indicatorRectF = new RectF();
        setWillNotDraw(false);
        float f = getResources().getDisplayMetrics().density;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842800, typedValue, true);
        int i2 = typedValue.data;
        float f2 = 0.0f * f;
        int colorAlpha = setColorAlpha(i2, (byte) 38);
        int i3 = (int) f2;
        int colorAlpha2 = setColorAlpha(i2, (byte) 38);
        int colorAlpha3 = setColorAlpha(i2, (byte) 32);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.stl_SmartTabLayout);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_indicatorAlwaysInCenter, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_indicatorWithoutPadding, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_indicatorInFront, false);
        int i4 = typedArrayObtainStyledAttributes.getInt(R.styleable.stl_SmartTabLayout_stl_indicatorInterpolation, 0);
        int i5 = typedArrayObtainStyledAttributes.getInt(R.styleable.stl_SmartTabLayout_stl_indicatorGravity, 0);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_indicatorColor, -13388315);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_indicatorColors, -1);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_indicatorThickness, (int) (8.0f * f));
        int layoutDimension = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.stl_SmartTabLayout_stl_indicatorWidth, -1);
        float dimension = typedArrayObtainStyledAttributes.getDimension(R.styleable.stl_SmartTabLayout_stl_indicatorCornerRadius, f2);
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_overlineColor, colorAlpha);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_overlineThickness, i3);
        int color3 = typedArrayObtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_underlineColor, colorAlpha2);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_underlineThickness, (int) (2.0f * f));
        int color4 = typedArrayObtainStyledAttributes.getColor(R.styleable.stl_SmartTabLayout_stl_dividerColor, colorAlpha3);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_dividerColors, -1);
        int dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_dividerThickness, (int) (f * 1.0f));
        boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_drawDecorationAfterTab, false);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId == -1) {
            i = 1;
            intArray = new int[]{color};
        } else {
            i = 1;
            intArray = getResources().getIntArray(resourceId);
        }
        if (resourceId2 == -1) {
            intArray2 = new int[i];
            intArray2[0] = color4;
        } else {
            intArray2 = getResources().getIntArray(resourceId2);
        }
        SimpleTabColorizer simpleTabColorizer = new SimpleTabColorizer();
        this.defaultTabColorizer = simpleTabColorizer;
        simpleTabColorizer.setIndicatorColors(intArray);
        this.defaultTabColorizer.setDividerColors(intArray2);
        this.topBorderThickness = dimensionPixelSize2;
        this.topBorderColor = color2;
        this.bottomBorderThickness = dimensionPixelSize3;
        this.bottomBorderColor = color3;
        this.borderPaint = new Paint(1);
        this.indicatorAlwaysInCenter = z;
        this.indicatorWithoutPadding = z2;
        this.indicatorInFront = z3;
        this.indicatorThickness = dimensionPixelSize;
        this.indicatorWidth = layoutDimension;
        this.indicatorPaint = new Paint(1);
        this.indicatorCornerRadius = dimension;
        this.indicatorGravity = i5;
        this.dividerHeight = 0.5f;
        Paint paint = new Paint(1);
        this.dividerPaint = paint;
        paint.setStrokeWidth(dimensionPixelSize4);
        this.dividerThickness = dimensionPixelSize4;
        this.drawDecorationAfterTab = z4;
        this.indicationInterpolator = SmartTabIndicationInterpolator.of(i4);
    }

    private static int blendColors(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.rgb((int) ((Color.red(i) * f) + (Color.red(i2) * f2)), (int) ((Color.green(i) * f) + (Color.green(i2) * f2)), (int) ((Color.blue(i) * f) + (Color.blue(i2) * f2)));
    }

    private void drawDecoration(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int height = getHeight();
        int width = getWidth();
        int childCount = getChildCount();
        SmartTabLayout.TabColorizer tabColorizer = getTabColorizer();
        boolean zIsLayoutRtl = Utils.isLayoutRtl(this);
        if (this.indicatorInFront) {
            drawOverline(canvas, 0, width);
            drawUnderline(canvas, 0, width, height);
        }
        if (childCount > 0) {
            View childAt = getChildAt(this.selectedPosition);
            int start = Utils.getStart(childAt, this.indicatorWithoutPadding);
            int end = Utils.getEnd(childAt, this.indicatorWithoutPadding);
            if (zIsLayoutRtl) {
                start = end;
                end = start;
            }
            int indicatorColor = tabColorizer.getIndicatorColor(this.selectedPosition);
            float f = this.indicatorThickness;
            if (this.selectionOffset <= 0.0f || this.selectedPosition >= getChildCount() - 1) {
                i = indicatorColor;
                int i6 = start;
                i2 = end;
                i3 = i6;
            } else {
                int indicatorColor2 = tabColorizer.getIndicatorColor(this.selectedPosition + 1);
                if (indicatorColor != indicatorColor2) {
                    indicatorColor = blendColors(indicatorColor2, indicatorColor, this.selectionOffset);
                }
                float leftEdge = this.indicationInterpolator.getLeftEdge(this.selectionOffset);
                float rightEdge = this.indicationInterpolator.getRightEdge(this.selectionOffset);
                float thickness = this.indicationInterpolator.getThickness(this.selectionOffset);
                View childAt2 = getChildAt(this.selectedPosition + 1);
                int start2 = Utils.getStart(childAt2, this.indicatorWithoutPadding);
                int end2 = Utils.getEnd(childAt2, this.indicatorWithoutPadding);
                if (zIsLayoutRtl) {
                    i4 = (int) ((end2 * rightEdge) + ((1.0f - rightEdge) * start));
                    i5 = (int) ((start2 * leftEdge) + ((1.0f - leftEdge) * end));
                } else {
                    i4 = (int) ((start2 * leftEdge) + ((1.0f - leftEdge) * start));
                    i5 = (int) ((end2 * rightEdge) + ((1.0f - rightEdge) * end));
                }
                f *= thickness;
                i2 = i5;
                i3 = i4;
                i = indicatorColor;
            }
            drawIndicator(canvas, i3, i2, height, f, i);
        }
        if (!this.indicatorInFront) {
            drawOverline(canvas, 0, width);
            drawUnderline(canvas, 0, getWidth(), height);
        }
        drawSeparator(canvas, height, childCount);
    }

    private void drawIndicator(Canvas canvas, int i, int i2, int i3, float f, int i4) {
        float f2;
        float f3;
        float f4;
        int i5 = this.indicatorThickness;
        if (i5 <= 0 || this.indicatorWidth == 0) {
            return;
        }
        int i6 = this.indicatorGravity;
        if (i6 == 1) {
            f2 = i5;
        } else {
            if (i6 != 2) {
                f3 = i3 - (i5 / 2.0f);
                float f5 = f / 2.0f;
                float f6 = f3 - f5;
                float f7 = f3 + f5;
                this.indicatorPaint.setColor(i4);
                if (this.indicatorWidth != -1) {
                    this.indicatorRectF.set(i, f6, i2, f7);
                } else {
                    float fAbs = (Math.abs(i - i2) - this.indicatorWidth) / 2.0f;
                    this.indicatorRectF.set(i + fAbs, f6, i2 - fAbs, f7);
                }
                f4 = this.indicatorCornerRadius;
                if (f4 <= 0.0f) {
                    canvas.drawRoundRect(this.indicatorRectF, f4, f4, this.indicatorPaint);
                    return;
                } else {
                    canvas.drawRect(this.indicatorRectF, this.indicatorPaint);
                    return;
                }
            }
            f2 = i3;
        }
        f3 = f2 / 2.0f;
        float f52 = f / 2.0f;
        float f62 = f3 - f52;
        float f72 = f3 + f52;
        this.indicatorPaint.setColor(i4);
        if (this.indicatorWidth != -1) {
        }
        f4 = this.indicatorCornerRadius;
        if (f4 <= 0.0f) {
        }
    }

    private void drawOverline(Canvas canvas, int i, int i2) {
        if (this.topBorderThickness <= 0) {
            return;
        }
        this.borderPaint.setColor(this.topBorderColor);
        canvas.drawRect(i, 0.0f, i2, this.topBorderThickness, this.borderPaint);
    }

    private void drawSeparator(Canvas canvas, int i, int i2) {
        if (this.dividerThickness <= 0) {
            return;
        }
        int iMin = (int) (Math.min(Math.max(0.0f, this.dividerHeight), 1.0f) * i);
        SmartTabLayout.TabColorizer tabColorizer = getTabColorizer();
        int i3 = (i - iMin) / 2;
        int i4 = iMin + i3;
        boolean zIsLayoutRtl = Utils.isLayoutRtl(this);
        for (int i5 = 0; i5 < i2 - 1; i5++) {
            View childAt = getChildAt(i5);
            int end = Utils.getEnd(childAt);
            int marginEnd = Utils.getMarginEnd(childAt);
            int i6 = zIsLayoutRtl ? end - marginEnd : end + marginEnd;
            this.dividerPaint.setColor(tabColorizer.getDividerColor(i5));
            float f = i6;
            canvas.drawLine(f, i3, f, i4, this.dividerPaint);
        }
    }

    private void drawUnderline(Canvas canvas, int i, int i2, int i3) {
        if (this.bottomBorderThickness <= 0) {
            return;
        }
        this.borderPaint.setColor(this.bottomBorderColor);
        canvas.drawRect(i, i3 - this.bottomBorderThickness, i2, i3, this.borderPaint);
    }

    private static int setColorAlpha(int i, byte b) {
        return Color.argb((int) b, Color.red(i), Color.green(i), Color.blue(i));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.drawDecorationAfterTab) {
            drawDecoration(canvas);
        }
    }

    SmartTabLayout.TabColorizer getTabColorizer() {
        SmartTabLayout.TabColorizer tabColorizer = this.customTabColorizer;
        return tabColorizer != null ? tabColorizer : this.defaultTabColorizer;
    }

    boolean isIndicatorAlwaysInCenter() {
        return this.indicatorAlwaysInCenter;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.drawDecorationAfterTab) {
            return;
        }
        drawDecoration(canvas);
    }

    void onViewPagerPageChanged(int i, float f) {
        this.selectedPosition = i;
        this.selectionOffset = f;
        if (f == 0.0f && this.lastPosition != i) {
            this.lastPosition = i;
        }
        invalidate();
    }

    void setCustomTabColorizer(SmartTabLayout.TabColorizer tabColorizer) {
        this.customTabColorizer = tabColorizer;
        invalidate();
    }

    void setDividerColors(int... iArr) {
        this.customTabColorizer = null;
        this.defaultTabColorizer.setDividerColors(iArr);
        invalidate();
    }

    void setIndicationInterpolator(SmartTabIndicationInterpolator smartTabIndicationInterpolator) {
        this.indicationInterpolator = smartTabIndicationInterpolator;
        invalidate();
    }

    void setSelectedIndicatorColors(int... iArr) {
        this.customTabColorizer = null;
        this.defaultTabColorizer.setIndicatorColors(iArr);
        invalidate();
    }
}

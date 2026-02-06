package com.ogaclejapan.smarttablayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SmartTabLayout extends HorizontalScrollView {
    private static final boolean DEFAULT_DISTRIBUTE_EVENLY = false;
    private static final boolean TAB_CLICKABLE = true;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final boolean TAB_VIEW_TEXT_ALL_CAPS = true;
    private static final int TAB_VIEW_TEXT_COLOR = -67108864;
    private static final int TAB_VIEW_TEXT_MIN_WIDTH = 0;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;
    private static final int TITLE_OFFSET_AUTO_CENTER = -1;
    private static final int TITLE_OFFSET_DIPS = 24;
    private boolean distributeEvenly;
    private InternalTabClickListener internalTabClickListener;
    private OnScrollChangeListener onScrollChangeListener;
    private OnTabClickListener onTabClickListener;
    private TabProvider tabProvider;
    protected final SmartTabStrip tabStrip;
    private int tabViewBackgroundResId;
    private boolean tabViewTextAllCaps;
    private ColorStateList tabViewTextColors;
    private int tabViewTextHorizontalPadding;
    private int tabViewTextMinWidth;
    private float tabViewTextSize;
    private int titleOffset;
    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener viewPagerPageChangeListener;

    private class InternalTabClickListener implements View.OnClickListener {
        private InternalTabClickListener() {
        }

        @Override
        public void onClick(View view) throws Resources.NotFoundException {
            for (int i = 0; i < SmartTabLayout.this.tabStrip.getChildCount(); i++) {
                if (view == SmartTabLayout.this.tabStrip.getChildAt(i)) {
                    if (SmartTabLayout.this.onTabClickListener != null) {
                        SmartTabLayout.this.onTabClickListener.onTabClicked(i);
                    }
                    SmartTabLayout.this.viewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int scrollState;

        private InternalViewPagerListener() {
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            this.scrollState = i;
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageScrollStateChanged(i);
            }
        }

        @Override
        public void onPageScrolled(int i, float f, int i2) {
            int childCount = SmartTabLayout.this.tabStrip.getChildCount();
            if (childCount == 0 || i < 0 || i >= childCount) {
                return;
            }
            SmartTabLayout.this.tabStrip.onViewPagerPageChanged(i, f);
            SmartTabLayout.this.scrollToTab(i, f);
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageScrolled(i, f, i2);
            }
        }

        @Override
        public void onPageSelected(int i) {
            if (this.scrollState == 0) {
                SmartTabLayout.this.tabStrip.onViewPagerPageChanged(i, 0.0f);
                SmartTabLayout.this.scrollToTab(i, 0.0f);
            }
            int childCount = SmartTabLayout.this.tabStrip.getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                SmartTabLayout.this.tabStrip.getChildAt(i2).setSelected(i == i2);
                i2++;
            }
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageSelected(i);
            }
        }
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(int i, int i2);
    }

    public interface OnTabClickListener {
        void onTabClicked(int i);
    }

    private static class SimpleTabProvider implements TabProvider {
        private final LayoutInflater inflater;
        private final int tabViewLayoutId;
        private final int tabViewTextViewId;

        private SimpleTabProvider(Context context, int i, int i2) {
            this.inflater = LayoutInflater.from(context);
            this.tabViewLayoutId = i;
            this.tabViewTextViewId = i2;
        }

        @Override
        public View createTabView(ViewGroup viewGroup, int i, PagerAdapter pagerAdapter) {
            int i2 = this.tabViewLayoutId;
            TextView textView = null;
            TextView textViewInflate = i2 != -1 ? this.inflater.inflate(i2, viewGroup, false) : null;
            int i3 = this.tabViewTextViewId;
            if (i3 != -1 && textViewInflate != null) {
                textView = (TextView) textViewInflate.findViewById(i3);
            }
            if (textView == null && TextView.class.isInstance(textViewInflate)) {
                textView = textViewInflate;
            }
            if (textView != null) {
                textView.setText(pagerAdapter.getPageTitle(i));
            }
            return textViewInflate;
        }
    }

    public interface TabColorizer {
        int getDividerColor(int i);

        int getIndicatorColor(int i);
    }

    public interface TabProvider {
        View createTabView(ViewGroup viewGroup, int i, PagerAdapter pagerAdapter);
    }

    public SmartTabLayout(Context context) {
        this(context, null);
    }

    public SmartTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmartTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setHorizontalScrollBarEnabled(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f = displayMetrics.density;
        float fApplyDimension = TypedValue.applyDimension(2, 12.0f, displayMetrics);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.stl_SmartTabLayout, i, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_defaultTabBackground, -1);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_defaultTabTextAllCaps, true);
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.stl_SmartTabLayout_stl_defaultTabTextColor);
        float dimension = typedArrayObtainStyledAttributes.getDimension(R.styleable.stl_SmartTabLayout_stl_defaultTabTextSize, fApplyDimension);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_defaultTabTextHorizontalPadding, (int) (16.0f * f));
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_defaultTabTextMinWidth, (int) (0.0f * f));
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_customTabTextLayoutId, -1);
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_customTabTextViewId, -1);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_distributeEvenly, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_clickable, true);
        int layoutDimension = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.stl_SmartTabLayout_stl_titleOffset, (int) (f * 24.0f));
        typedArrayObtainStyledAttributes.recycle();
        this.titleOffset = layoutDimension;
        this.tabViewBackgroundResId = resourceId;
        this.tabViewTextAllCaps = z;
        this.tabViewTextColors = colorStateList == null ? ColorStateList.valueOf(-67108864) : colorStateList;
        this.tabViewTextSize = dimension;
        this.tabViewTextHorizontalPadding = dimensionPixelSize;
        this.tabViewTextMinWidth = dimensionPixelSize2;
        this.internalTabClickListener = z3 ? new InternalTabClickListener() : null;
        this.distributeEvenly = z2;
        if (resourceId2 != -1) {
            setCustomTabView(resourceId2, resourceId3);
        }
        SmartTabStrip smartTabStrip = new SmartTabStrip(context, attributeSet);
        this.tabStrip = smartTabStrip;
        if (z2 && smartTabStrip.isIndicatorAlwaysInCenter()) {
            throw new UnsupportedOperationException("'distributeEvenly' and 'indicatorAlwaysInCenter' both use does not support");
        }
        setFillViewport(!this.tabStrip.isIndicatorAlwaysInCenter());
        addView(this.tabStrip, -1, -1);
    }

    private void populateTabStrip() {
        PagerAdapter adapter = this.viewPager.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            TabProvider tabProvider = this.tabProvider;
            View viewCreateDefaultTabView = tabProvider == null ? createDefaultTabView(adapter.getPageTitle(i)) : tabProvider.createTabView(this.tabStrip, i, adapter);
            if (viewCreateDefaultTabView == null) {
                throw new IllegalStateException("tabView is null.");
            }
            if (this.distributeEvenly) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewCreateDefaultTabView.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            }
            InternalTabClickListener internalTabClickListener = this.internalTabClickListener;
            if (internalTabClickListener != null) {
                viewCreateDefaultTabView.setOnClickListener(internalTabClickListener);
            }
            this.tabStrip.addView(viewCreateDefaultTabView);
            if (i == this.viewPager.getCurrentItem()) {
                viewCreateDefaultTabView.setSelected(true);
            }
        }
    }

    private void scrollToTab(int i, float f) {
        int width;
        int start;
        int i2;
        int childCount = this.tabStrip.getChildCount();
        if (childCount == 0 || i < 0 || i >= childCount) {
            return;
        }
        boolean zIsLayoutRtl = Utils.isLayoutRtl(this);
        View childAt = this.tabStrip.getChildAt(i);
        int width2 = (int) ((Utils.getWidth(childAt) + Utils.getMarginHorizontally(childAt)) * f);
        if (!this.tabStrip.isIndicatorAlwaysInCenter()) {
            if (this.titleOffset == -1) {
                if (0.0f < f && f < 1.0f) {
                    View childAt2 = this.tabStrip.getChildAt(i + 1);
                    width2 = Math.round(f * ((Utils.getWidth(childAt) / 2) + Utils.getMarginEnd(childAt) + (Utils.getWidth(childAt2) / 2) + Utils.getMarginStart(childAt2)));
                }
                width = zIsLayoutRtl ? (((-Utils.getWidthWithMargin(childAt)) / 2) + (getWidth() / 2)) - Utils.getPaddingStart(this) : ((Utils.getWidthWithMargin(childAt) / 2) - (getWidth() / 2)) + Utils.getPaddingStart(this);
            } else if (zIsLayoutRtl) {
                width = (i > 0 || f > 0.0f) ? this.titleOffset : 0;
            } else if (i > 0 || f > 0.0f) {
                width = -this.titleOffset;
            }
            int start2 = Utils.getStart(childAt);
            int marginStart = Utils.getMarginStart(childAt);
            scrollTo(width + (zIsLayoutRtl ? (((start2 + marginStart) - width2) - getWidth()) + Utils.getPaddingHorizontally(this) : (start2 - marginStart) + width2), 0);
            return;
        }
        if (0.0f < f && f < 1.0f) {
            View childAt3 = this.tabStrip.getChildAt(i + 1);
            width2 = Math.round(f * ((Utils.getWidth(childAt) / 2) + Utils.getMarginEnd(childAt) + (Utils.getWidth(childAt3) / 2) + Utils.getMarginStart(childAt3)));
        }
        View childAt4 = this.tabStrip.getChildAt(0);
        if (zIsLayoutRtl) {
            int width3 = Utils.getWidth(childAt4) + Utils.getMarginEnd(childAt4);
            int width4 = Utils.getWidth(childAt) + Utils.getMarginEnd(childAt);
            start = (Utils.getEnd(childAt) - Utils.getMarginEnd(childAt)) - width2;
            i2 = (width3 - width4) / 2;
        } else {
            int width5 = Utils.getWidth(childAt4) + Utils.getMarginStart(childAt4);
            int width6 = Utils.getWidth(childAt) + Utils.getMarginStart(childAt);
            start = (Utils.getStart(childAt) - Utils.getMarginStart(childAt)) + width2;
            i2 = (width5 - width6) / 2;
        }
        scrollTo(start - i2, 0);
    }

    protected TextView createDefaultTabView(CharSequence charSequence) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setText(charSequence);
        textView.setTextColor(this.tabViewTextColors);
        textView.setTextSize(0, this.tabViewTextSize);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        int i = this.tabViewBackgroundResId;
        if (i != -1) {
            textView.setBackgroundResource(i);
        } else if (Build.VERSION.SDK_INT >= 11) {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(16843534, typedValue, true);
            textView.setBackgroundResource(typedValue.resourceId);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            textView.setAllCaps(this.tabViewTextAllCaps);
        }
        int i2 = this.tabViewTextHorizontalPadding;
        textView.setPadding(i2, 0, i2, 0);
        int i3 = this.tabViewTextMinWidth;
        if (i3 > 0) {
            textView.setMinWidth(i3);
        }
        return textView;
    }

    public View getTabAt(int i) {
        return this.tabStrip.getChildAt(i);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ViewPager viewPager;
        super.onLayout(z, i, i2, i3, i4);
        if (!z || (viewPager = this.viewPager) == null) {
            return;
        }
        scrollToTab(viewPager.getCurrentItem(), 0.0f);
    }

    @Override
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        OnScrollChangeListener onScrollChangeListener = this.onScrollChangeListener;
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(i, i3);
        }
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!this.tabStrip.isIndicatorAlwaysInCenter() || this.tabStrip.getChildCount() <= 0) {
            return;
        }
        View childAt = this.tabStrip.getChildAt(0);
        View childAt2 = this.tabStrip.getChildAt(r5.getChildCount() - 1);
        int measuredWidth = ((i - Utils.getMeasuredWidth(childAt)) / 2) - Utils.getMarginStart(childAt);
        int measuredWidth2 = ((i - Utils.getMeasuredWidth(childAt2)) / 2) - Utils.getMarginEnd(childAt2);
        SmartTabStrip smartTabStrip = this.tabStrip;
        smartTabStrip.setMinimumWidth(smartTabStrip.getMeasuredWidth());
        ViewCompat.setPaddingRelative(this, measuredWidth, getPaddingTop(), measuredWidth2, getPaddingBottom());
        setClipToPadding(false);
    }

    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        this.tabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setCustomTabView(int i, int i2) {
        this.tabProvider = new SimpleTabProvider(getContext(), i, i2);
    }

    public void setCustomTabView(TabProvider tabProvider) {
        this.tabProvider = tabProvider;
    }

    public void setDefaultTabTextColor(int i) {
        this.tabViewTextColors = ColorStateList.valueOf(i);
    }

    public void setDefaultTabTextColor(ColorStateList colorStateList) {
        this.tabViewTextColors = colorStateList;
    }

    public void setDistributeEvenly(boolean z) {
        this.distributeEvenly = z;
    }

    public void setDividerColors(int... iArr) {
        this.tabStrip.setDividerColors(iArr);
    }

    public void setIndicationInterpolator(SmartTabIndicationInterpolator smartTabIndicationInterpolator) {
        this.tabStrip.setIndicationInterpolator(smartTabIndicationInterpolator);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.viewPagerPageChangeListener = onPageChangeListener;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    public void setSelectedIndicatorColors(int... iArr) {
        this.tabStrip.setSelectedIndicatorColors(iArr);
    }

    public void setViewPager(ViewPager viewPager) {
        this.tabStrip.removeAllViews();
        this.viewPager = viewPager;
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }
        viewPager.addOnPageChangeListener(new InternalViewPagerListener());
        populateTabStrip();
    }
}

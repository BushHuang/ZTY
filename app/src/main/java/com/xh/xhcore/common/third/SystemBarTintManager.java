package com.xh.xhcore.common.third;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

public class SystemBarTintManager {
    public static final int DEFAULT_TINT_COLOR = -1728053248;
    private static SystemBarConfig mConfig;
    private static String sNavBarOverride;
    private boolean mNavBarAvailable;
    private boolean mNavBarTintEnabled;
    private View mNavBarTintView;
    private boolean mStatusBarAvailable;
    private boolean mStatusBarTintEnabled;
    private View mStatusBarTintView;

    public static class SystemBarConfig {
        private static final boolean DEBUG = false;
        private static final String NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape";
        private static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
        private static final String NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width";
        private static final String SHOW_NAV_BAR_RES_NAME = "config_showNavigationBar";
        private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
        public static final String TAG = "SystemBarConfig";
        private final int mActionBarHeight;
        private final boolean mHasNavigationBar;
        private final boolean mInPortrait;
        private final int mNavigationBarHeight;
        private final int mNavigationBarWidth;
        private float mSmallestWidthDp;
        private final int mStatusBarHeight;
        private boolean mTranslucentNavBar;
        private boolean mTranslucentStatusBar;

        public SystemBarConfig(Context context) {
            Resources resources = context.getResources();
            this.mInPortrait = resources.getConfiguration().orientation == 1;
            this.mStatusBarHeight = getInternalDimensionSize(resources, "status_bar_height");
            this.mActionBarHeight = getActionBarHeight(context);
            this.mNavigationBarHeight = getNavigationBarHeight(context);
            this.mNavigationBarWidth = getNavigationBarWidth(context);
            this.mHasNavigationBar = this.mNavigationBarHeight > 0;
        }

        private int getActionBarHeight(Context context) {
            if (Build.VERSION.SDK_INT < 14) {
                return 0;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16843499, typedValue, true);
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }

        private int getInternalDimensionSize(Resources resources, String str) {
            int identifier = resources.getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        private int getNavigationBarHeight(Context context) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !hasNavBar(context)) {
                return 0;
            }
            return getInternalDimensionSize(resources, this.mInPortrait ? "navigation_bar_height" : "navigation_bar_height_landscape");
        }

        private int getNavigationBarWidth(Context context) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !hasNavBar(context)) {
                return 0;
            }
            return getInternalDimensionSize(resources, "navigation_bar_width");
        }

        private float getSmallestWidthDp(Activity activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 16) {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            }
            return Math.min(displayMetrics.widthPixels / displayMetrics.density, displayMetrics.heightPixels / displayMetrics.density);
        }

        private boolean hasNavBar(Context context) throws Resources.NotFoundException {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (identifier == 0) {
                return !ViewConfiguration.get(context).hasPermanentMenuKey();
            }
            boolean z = resources.getBoolean(identifier);
            if ("1".equals(SystemBarTintManager.sNavBarOverride)) {
                return false;
            }
            if ("0".equals(SystemBarTintManager.sNavBarOverride)) {
                return true;
            }
            return z;
        }

        public int getActionBarHeight() {
            return this.mActionBarHeight;
        }

        public int getNavigationBarHeight() {
            return this.mNavigationBarHeight;
        }

        public int getNavigationBarWidth() {
            return this.mNavigationBarWidth;
        }

        public int getPixelInsetBottom() {
            if (this.mTranslucentNavBar && isNavigationAtBottom()) {
                return this.mNavigationBarHeight;
            }
            return 0;
        }

        public int getPixelInsetRight() {
            if (!this.mTranslucentNavBar || isNavigationAtBottom()) {
                return 0;
            }
            return this.mNavigationBarWidth;
        }

        public int getPixelInsetTop(boolean z) {
            return (this.mTranslucentStatusBar ? this.mStatusBarHeight : 0) + (z ? this.mActionBarHeight : 0);
        }

        public int getStatusBarHeight() {
            return this.mStatusBarHeight;
        }

        public boolean hasNavigtionBar() {
            return this.mHasNavigationBar;
        }

        public void init(Activity activity, boolean z, boolean z2) {
            this.mSmallestWidthDp = getSmallestWidthDp(activity);
            this.mTranslucentStatusBar = z;
            this.mTranslucentNavBar = z2;
        }

        public boolean isNavigationAtBottom() {
            return this.mSmallestWidthDp >= 600.0f || this.mInPortrait;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
                declaredMethod.setAccessible(true);
                sNavBarOverride = (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable unused) {
                sNavBarOverride = null;
            }
        }
    }

    public SystemBarTintManager(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (Build.VERSION.SDK_INT >= 19) {
            TypedArray typedArrayObtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            try {
                this.mStatusBarAvailable = typedArrayObtainStyledAttributes.getBoolean(0, false);
                this.mNavBarAvailable = typedArrayObtainStyledAttributes.getBoolean(1, false);
                typedArrayObtainStyledAttributes.recycle();
                WindowManager.LayoutParams attributes = window.getAttributes();
                if ((67108864 & attributes.flags) != 0) {
                    this.mStatusBarAvailable = true;
                }
                if ((attributes.flags & 134217728) != 0) {
                    this.mNavBarAvailable = true;
                }
            } catch (Throwable th) {
                typedArrayObtainStyledAttributes.recycle();
                throw th;
            }
        }
        SystemBarConfig systemBarConfig = mConfig;
        if (systemBarConfig == null) {
            throw new RuntimeException("mConfig must init in Application");
        }
        systemBarConfig.init(activity, this.mStatusBarAvailable, this.mNavBarAvailable);
        if (!mConfig.hasNavigtionBar()) {
            this.mNavBarAvailable = false;
        }
        if (this.mStatusBarAvailable) {
            setupStatusBarView(activity, viewGroup);
        }
        if (this.mNavBarAvailable) {
            setupNavBarView(activity, viewGroup);
        }
    }

    public static void init(Context context) {
        mConfig = new SystemBarConfig(context);
    }

    private void setupNavBarView(Context context, ViewGroup viewGroup) {
        FrameLayout.LayoutParams layoutParams;
        this.mNavBarTintView = new View(context);
        if (mConfig.isNavigationAtBottom()) {
            layoutParams = new FrameLayout.LayoutParams(-1, mConfig.getNavigationBarHeight());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(mConfig.getNavigationBarWidth(), -1);
            layoutParams.gravity = 5;
        }
        this.mNavBarTintView.setLayoutParams(layoutParams);
        this.mNavBarTintView.setBackgroundColor(-1728053248);
        this.mNavBarTintView.setVisibility(8);
        viewGroup.addView(this.mNavBarTintView);
    }

    private void setupStatusBarView(Context context, ViewGroup viewGroup) {
        this.mStatusBarTintView = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, mConfig.getStatusBarHeight());
        layoutParams.gravity = 48;
        if (this.mNavBarAvailable && !mConfig.isNavigationAtBottom()) {
            layoutParams.rightMargin = mConfig.getNavigationBarWidth();
        }
        this.mStatusBarTintView.setLayoutParams(layoutParams);
        this.mStatusBarTintView.setBackgroundColor(-1728053248);
        this.mStatusBarTintView.setVisibility(8);
        viewGroup.addView(this.mStatusBarTintView);
    }

    public SystemBarConfig getConfig() {
        return mConfig;
    }

    public boolean isNavBarTintEnabled() {
        return this.mNavBarTintEnabled;
    }

    public boolean isStatusBarTintEnabled() {
        return this.mStatusBarTintEnabled;
    }

    public void setNavigationBarAlpha(float f) {
        if (!this.mNavBarAvailable || Build.VERSION.SDK_INT < 11) {
            return;
        }
        this.mNavBarTintView.setAlpha(f);
    }

    public void setNavigationBarTintColor(int i) {
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setBackgroundColor(i);
        }
    }

    public void setNavigationBarTintDrawable(Drawable drawable) {
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setBackgroundDrawable(drawable);
        }
    }

    public void setNavigationBarTintEnabled(boolean z) {
        this.mNavBarTintEnabled = z;
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setVisibility(z ? 0 : 8);
        }
    }

    public void setNavigationBarTintResource(int i) {
        if (this.mNavBarAvailable) {
            this.mNavBarTintView.setBackgroundResource(i);
        }
    }

    public void setStatusBarAlpha(float f) {
        if (!this.mStatusBarAvailable || Build.VERSION.SDK_INT < 11) {
            return;
        }
        this.mStatusBarTintView.setAlpha(f);
    }

    public void setStatusBarTintColor(int i) {
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setBackgroundColor(i);
        }
    }

    public void setStatusBarTintDrawable(Drawable drawable) {
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setBackgroundDrawable(drawable);
        }
    }

    public void setStatusBarTintEnabled(boolean z) {
        this.mStatusBarTintEnabled = z;
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setVisibility(z ? 0 : 8);
        }
    }

    public void setStatusBarTintResource(int i) {
        if (this.mStatusBarAvailable) {
            this.mStatusBarTintView.setBackgroundResource(i);
        }
    }

    public void setTintAlpha(float f) {
        setStatusBarAlpha(f);
        setNavigationBarAlpha(f);
    }

    public void setTintColor(int i) {
        setStatusBarTintColor(i);
        setNavigationBarTintColor(i);
    }

    public void setTintDrawable(Drawable drawable) {
        setStatusBarTintDrawable(drawable);
        setNavigationBarTintDrawable(drawable);
    }

    public void setTintResource(int i) {
        setStatusBarTintResource(i);
        setNavigationBarTintResource(i);
    }
}

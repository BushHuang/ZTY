package com.zaze.utils;

import android.app.Application;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import com.zaze.utils.log.ZLog;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\u001a\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\u0006\u0010\u0010\u001a\u00020\u0004J\b\u0010\u0011\u001a\u00020\nH\u0007J\b\u0010\u0012\u001a\u00020\u0007H\u0007J\b\u0010\u0013\u001a\u00020\u0007H\u0007J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\rH\u0007J\u001a\u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\u001a\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/zaze/utils/DisplayUtil;", "", "()V", "displayProfile", "Lcom/zaze/utils/DisplayUtil$DisplayProfile;", "matchedDisplayProfile", "dp2px", "", "dp", "metrics", "Landroid/util/DisplayMetrics;", "dpiFromPx", "px", "", "getDensityDpiName", "", "getDisplayProfile", "getMetrics", "getScreenHeightDp", "getScreenWidthDp", "init", "", "application", "Landroid/app/Application;", "baseWidthPixels", "pxFromDp", "pxFromSp", "sp", "replaceResource", "Landroid/content/res/Resources;", "resources", "DisplayProfile", "util_release"}, k = 1, mv = {1, 4, 1})
public final class DisplayUtil {
    public static final DisplayUtil INSTANCE = new DisplayUtil();
    private static DisplayProfile displayProfile;
    private static DisplayProfile matchedDisplayProfile;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010#\u001a\u00020$J\t\u0010%\u001a\u00020\nHÖ\u0001J\b\u0010&\u001a\u00020$H\u0016J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0003R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u0016R\u0011\u0010\u001a\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\bR\u0011\u0010\u001c\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\f¨\u0006*"}, d2 = {"Lcom/zaze/utils/DisplayUtil$DisplayProfile;", "", "metrics", "Landroid/util/DisplayMetrics;", "(Landroid/util/DisplayMetrics;)V", "density", "", "getDensity", "()F", "densityDpi", "", "getDensityDpi", "()I", "heightDp", "getHeightDp", "heightPixels", "getHeightPixels", "getMetrics", "()Landroid/util/DisplayMetrics;", "realHeightPixels", "getRealHeightPixels", "setRealHeightPixels", "(I)V", "realWidthPixels", "getRealWidthPixels", "setRealWidthPixels", "widthDp", "getWidthDp", "widthPixels", "getWidthPixels", "component1", "copy", "equals", "", "other", "getDensityDpiName", "", "hashCode", "toString", "updateRealMetrics", "", "realMetrics", "util_release"}, k = 1, mv = {1, 4, 1})
    public static final class DisplayProfile {
        private final float density;
        private final int densityDpi;
        private final float heightDp;
        private final int heightPixels;
        private final DisplayMetrics metrics;
        private int realHeightPixels;
        private int realWidthPixels;
        private final float widthDp;
        private final int widthPixels;

        public DisplayProfile(DisplayMetrics displayMetrics) {
            Intrinsics.checkNotNullParameter(displayMetrics, "metrics");
            this.metrics = displayMetrics;
            this.widthPixels = displayMetrics.widthPixels;
            this.heightPixels = this.metrics.heightPixels;
            this.density = this.metrics.density;
            this.densityDpi = this.metrics.densityDpi;
            this.widthDp = DisplayUtil.dpiFromPx(this.widthPixels, this.metrics);
            this.heightDp = DisplayUtil.dpiFromPx(this.heightPixels, this.metrics);
            this.realWidthPixels = this.widthPixels;
            this.realHeightPixels = this.heightPixels;
        }

        public static DisplayProfile copy$default(DisplayProfile displayProfile, DisplayMetrics displayMetrics, int i, Object obj) {
            if ((i & 1) != 0) {
                displayMetrics = displayProfile.metrics;
            }
            return displayProfile.copy(displayMetrics);
        }

        public final DisplayMetrics getMetrics() {
            return this.metrics;
        }

        public final DisplayProfile copy(DisplayMetrics metrics) {
            Intrinsics.checkNotNullParameter(metrics, "metrics");
            return new DisplayProfile(metrics);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof DisplayProfile) && Intrinsics.areEqual(this.metrics, ((DisplayProfile) other).metrics);
            }
            return true;
        }

        public final float getDensity() {
            return this.density;
        }

        public final int getDensityDpi() {
            return this.densityDpi;
        }

        public final String getDensityDpiName() {
            return DisplayUtil.getDensityDpiName(this.metrics);
        }

        public final float getHeightDp() {
            return this.heightDp;
        }

        public final int getHeightPixels() {
            return this.heightPixels;
        }

        public final DisplayMetrics getMetrics() {
            return this.metrics;
        }

        public final int getRealHeightPixels() {
            return this.realHeightPixels;
        }

        public final int getRealWidthPixels() {
            return this.realWidthPixels;
        }

        public final float getWidthDp() {
            return this.widthDp;
        }

        public final int getWidthPixels() {
            return this.widthPixels;
        }

        public int hashCode() {
            DisplayMetrics displayMetrics = this.metrics;
            if (displayMetrics != null) {
                return displayMetrics.hashCode();
            }
            return 0;
        }

        public final void setRealHeightPixels(int i) {
            this.realHeightPixels = i;
        }

        public final void setRealWidthPixels(int i) {
            this.realWidthPixels = i;
        }

        public String toString() {
            return "DisplayProfile(metrics=" + this.metrics + ", widthPixels=" + this.widthPixels + ", heightPixels=" + this.heightPixels + ", widthDp=" + this.widthDp + ", heightDp=" + this.heightDp + ", density=" + this.density + ", densityDpi=" + this.densityDpi + ", realWidthPixels=" + this.realWidthPixels + ", realHeightPixels=" + this.realHeightPixels + ')';
        }

        public final void updateRealMetrics(DisplayMetrics realMetrics) {
            Intrinsics.checkNotNullParameter(realMetrics, "realMetrics");
            this.realWidthPixels = realMetrics.widthPixels;
            this.realHeightPixels = realMetrics.heightPixels;
        }
    }

    private DisplayUtil() {
    }

    @Deprecated(message = "{@link DisplayUtil#pxFromDp(float dp)}")
    @JvmStatic
    public static final float dp2px(float f) {
        return dp2px$default(f, null, 2, null);
    }

    @Deprecated(message = "{@link DisplayUtil#pxFromDp(float dp)}")
    @JvmStatic
    public static final float dp2px(float dp, DisplayMetrics metrics) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        return dp * metrics.density;
    }

    public static float dp2px$default(float f, DisplayMetrics displayMetrics, int i, Object obj) {
        if ((i & 2) != 0) {
            displayMetrics = getMetrics();
        }
        return dp2px(f, displayMetrics);
    }

    @JvmStatic
    public static final float dpiFromPx(int i) {
        return dpiFromPx$default(i, null, 2, null);
    }

    @JvmStatic
    public static final float dpiFromPx(int px, DisplayMetrics metrics) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        return px / metrics.density;
    }

    public static float dpiFromPx$default(int i, DisplayMetrics displayMetrics, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            displayMetrics = getMetrics();
        }
        return dpiFromPx(i, displayMetrics);
    }

    @JvmStatic
    public static final String getDensityDpiName() {
        return getDensityDpiName$default(null, 1, null);
    }

    @JvmStatic
    public static final String getDensityDpiName(DisplayMetrics metrics) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        int i = metrics.densityDpi;
        return i != 120 ? i != 160 ? i != 213 ? i != 240 ? i != 320 ? i != 480 ? i != 640 ? "????dpi" : "xxxhdpi" : "xxhdpi" : "xhdpi" : "hdpi" : "tv" : "mdpi" : "ldpi";
    }

    public static String getDensityDpiName$default(DisplayMetrics displayMetrics, int i, Object obj) {
        if ((i & 1) != 0) {
            displayMetrics = getMetrics();
        }
        return getDensityDpiName(displayMetrics);
    }

    @JvmStatic
    public static final DisplayMetrics getMetrics() {
        return INSTANCE.getDisplayProfile().getMetrics();
    }

    @JvmStatic
    public static final float getScreenHeightDp() {
        return INSTANCE.getDisplayProfile().getHeightDp();
    }

    @JvmStatic
    public static final float getScreenWidthDp() {
        return INSTANCE.getDisplayProfile().getWidthDp();
    }

    @JvmStatic
    public static final void init(Application application) {
        init$default(application, 0, 2, null);
    }

    @JvmStatic
    public static final void init(Application application, int baseWidthPixels) {
        Intrinsics.checkNotNullParameter(application, "application");
        Resources resources = application.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "application.resources");
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "application.resources.displayMetrics");
        displayProfile = new DisplayProfile(displayMetrics);
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            Object systemService = application.getSystemService("window");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
            }
            ((WindowManager) systemService).getDefaultDisplay().getRealMetrics(displayMetrics2);
        } else {
            Resources resources2 = application.getResources();
            Intrinsics.checkNotNullExpressionValue(resources2, "application.resources");
            displayMetrics2.setTo(resources2.getDisplayMetrics());
        }
        DisplayProfile displayProfile2 = displayProfile;
        if (displayProfile2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("displayProfile");
        }
        displayProfile2.updateRealMetrics(displayMetrics2);
        if (baseWidthPixels > 0) {
            displayMetrics2.density = displayMetrics2.widthPixels / baseWidthPixels;
            displayMetrics2.scaledDensity = displayMetrics2.density;
            displayMetrics2.densityDpi = (int) (160 * displayMetrics2.density);
            matchedDisplayProfile = new DisplayProfile(displayMetrics2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("displayProfile : ");
        DisplayProfile displayProfile3 = displayProfile;
        if (displayProfile3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("displayProfile");
        }
        sb.append(displayProfile3);
        ZLog.i("Debug[ZZ]", sb.toString(), new Object[0]);
    }

    public static void init$default(Application application, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        init(application, i);
    }

    @JvmStatic
    public static final float pxFromDp(float f) {
        return pxFromDp$default(f, null, 2, null);
    }

    @JvmStatic
    public static final float pxFromDp(float dp, DisplayMetrics metrics) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        return TypedValue.applyDimension(1, dp, metrics);
    }

    public static float pxFromDp$default(float f, DisplayMetrics displayMetrics, int i, Object obj) {
        if ((i & 2) != 0) {
            displayMetrics = getMetrics();
        }
        return pxFromDp(f, displayMetrics);
    }

    @JvmStatic
    public static final float pxFromSp(float f) {
        return pxFromSp$default(f, null, 2, null);
    }

    @JvmStatic
    public static final float pxFromSp(float sp, DisplayMetrics metrics) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        return TypedValue.applyDimension(2, sp, metrics);
    }

    public static float pxFromSp$default(float f, DisplayMetrics displayMetrics, int i, Object obj) {
        if ((i & 2) != 0) {
            displayMetrics = getMetrics();
        }
        return pxFromSp(f, displayMetrics);
    }

    @JvmStatic
    public static final Resources replaceResource(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        DisplayProfile displayProfile2 = matchedDisplayProfile;
        if (displayProfile2 != null) {
            resources.getDisplayMetrics().setTo(displayProfile2.getMetrics());
        }
        return resources;
    }

    public final DisplayProfile getDisplayProfile() {
        DisplayProfile displayProfile2 = matchedDisplayProfile;
        if (displayProfile2 == null && (displayProfile2 = displayProfile) == null) {
            Intrinsics.throwUninitializedPropertyAccessException("displayProfile");
        }
        return displayProfile2;
    }
}

package com.xh.xhcore.common.adaptation;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.widget.TextView;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.DensityUtil;

public class TextViewAdaptationUtil {
    private static final String ADAPTATION_FONTFEATURESETTINGS = "kern=0";
    private static final int BASE_SCREEN_LONG_SIDE_LENGTH = 1024;
    private static final int BASE_SCREEN_SHORT_SIDE_LENGTH = 768;
    public static int TEXT_SIZE_PIXEL = 20;
    private static Typeface defaultTypeFace = null;
    private static int forceOrientation = 0;
    private static boolean isNewVersion = false;
    private static boolean isOrientationPortrait = true;
    private static Pair<Integer, Integer> portraitWidthHeight = new Pair<>(Integer.valueOf(getDisplayMetrics().widthPixels), Integer.valueOf(getDisplayMetrics().heightPixels));
    private static Pair<Integer, Integer> landscapeWidthHeight = new Pair<>(Integer.valueOf(getDisplayMetrics().widthPixels), Integer.valueOf(getDisplayMetrics().heightPixels));

    public static float computeCharacterLowerAWidth(float f) {
        Double.isNaN((((long) (((((((int) (f * 65536.0f)) / 1024) * 32) * 1114) / 65536.0f) * 1024.0f)) / 65536.0f) * 256.0f);
        float f2 = ((int) (r1 + 0.5d)) / 256.0f;
        LogUtils.d("字符:a的宽度为: (计算出的)" + f2);
        return f2;
    }

    public static void doTextPaintAdaptation(TextPaint textPaint) {
        if (getFullTypeface() != textPaint.getTypeface()) {
            textPaint.setTypeface(getFullTypeface());
        }
        int adaptationPaintFlags = getAdaptationPaintFlags(textPaint.getFlags());
        if (adaptationPaintFlags != textPaint.getFlags()) {
            textPaint.setFlags(adaptationPaintFlags);
        }
        textPaint.setHinting(0);
        if (Build.VERSION.SDK_INT < 21 || "kern=0".equals(textPaint.getFontFeatureSettings())) {
            return;
        }
        textPaint.setFontFeatureSettings("kern=0");
    }

    public static void doTextViewAdaptation(TextView textView) {
        textView.setTypeface(getFullTypeface());
        textView.setPaintFlags(getAdaptationPaintFlags(textView.getPaintFlags()));
        textView.getPaint().setHinting(0);
        if (Build.VERSION.SDK_INT >= 21) {
            textView.setFontFeatureSettings("kern=0");
        }
    }

    public static float dpSpToPx(float f) {
        return isNoteCreatedNewVersion() ? f * getAdaptationRadio() : DensityUtil.dp2px(f);
    }

    public static float dpSpToPxForcePrecision(float f) {
        return isNoteCreatedNewVersion() ? dpSpToPx(oddIntToUpperEven(f)) : DensityUtil.dp2px(f);
    }

    public static int getAdaptationPaintFlags(int i) {
        return i | 64 | 128;
    }

    public static float getAdaptationRadio() {
        return ((int) (getCurrentScreenRadio() * 2.0f)) / 2.0f;
    }

    public static float getAdaptationX(float f) {
        return dpSpToPx(f);
    }

    public static float getAdaptationY(float f) {
        return dpSpToPx(f);
    }

    private static int getBaseScreenTextLayoutOrientationLength() {
        return isOrientationPortrait ? 768 : 1024;
    }

    private static int getCurrentScreenLongSideLength() {
        Pair<Integer, Integer> widthHeight = getWidthHeight();
        return Math.max(((Integer) widthHeight.first).intValue(), ((Integer) widthHeight.second).intValue());
    }

    private static float getCurrentScreenRadio() {
        return getCurrentScreenTextLayoutOrientationLength() / getBaseScreenTextLayoutOrientationLength();
    }

    private static int getCurrentScreenShortSideLength() {
        Pair<Integer, Integer> widthHeight = getWidthHeight();
        return Math.min(((Integer) widthHeight.first).intValue(), ((Integer) widthHeight.second).intValue());
    }

    private static int getCurrentScreenTextLayoutOrientationLength() {
        return isOrientationPortrait ? getCurrentScreenShortSideLength() : getCurrentScreenLongSideLength();
    }

    private static DisplayMetrics getDisplayMetrics() {
        return XhBaseApplication.getXhBaseApplication().getResources().getDisplayMetrics();
    }

    private static DisplayMetrics getDisplayMetrics(Activity activity) {
        return activity.getResources().getDisplayMetrics();
    }

    public static Typeface getFullTypeface() {
        if (defaultTypeFace == null) {
            defaultTypeFace = Typeface.createFromAsset(XhBaseApplication.getXhBaseApplication().getAssets(), "DroidSansFallback.ttf");
        }
        return defaultTypeFace;
    }

    public static float getLineSpacingAdd(float f) {
        return dpSpToPxForcePrecision(f);
    }

    public static int getPadding(int i) {
        return (int) dpSpToPxForcePrecision(i);
    }

    public static int getRedundantSpace() {
        return (int) (getCurrentScreenTextLayoutOrientationLength() - dpSpToPx(getBaseScreenTextLayoutOrientationLength()));
    }

    public static int getRedundantSpaceLeft() {
        return getRedundantSpace() / 2;
    }

    public static int getRedundantSpaceRight() {
        return getRedundantSpace() / 2;
    }

    public static int getTextSizeInPixel() {
        return getTextSizeInPixel(TEXT_SIZE_PIXEL);
    }

    public static int getTextSizeInPixel(float f) {
        return (int) dpSpToPxForcePrecision(f);
    }

    @Deprecated
    public static int getTopOrBottomMargin(int i) {
        return (int) dpSpToPx(i);
    }

    private static Pair<Integer, Integer> getWidthHeight() {
        return isOrientationPortrait ? portraitWidthHeight : landscapeWidthHeight;
    }

    private static boolean isActivityOrientationPortrait(Activity activity) {
        return activity.getResources().getConfiguration().orientation != 2;
    }

    public static boolean isNoteCreatedNewVersion() {
        return isNewVersion;
    }

    public static int oddIntToUpperEven(float f) {
        int i = (int) f;
        return i % 2 == 1 ? i + 1 : i;
    }

    public static void onActivityCreate(Activity activity) {
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        Pair<Integer, Integer> pair = new Pair<>(Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels));
        if (isActivityOrientationPortrait(activity)) {
            portraitWidthHeight = pair;
        } else {
            landscapeWidthHeight = pair;
        }
    }

    public static void setForceOrientation(int i) {
        forceOrientation = i;
    }

    public static void setIsNewVersion(boolean z) {
        isNewVersion = z;
    }

    public static void setIsOrientationPortrait(Activity activity) {
        int i = forceOrientation;
        if (i == 1) {
            isOrientationPortrait = true;
        } else if (i == 2) {
            isOrientationPortrait = false;
        } else {
            isOrientationPortrait = isActivityOrientationPortrait(activity);
        }
    }

    public static int transformTextSizeToPx(int i, float f) {
        if (i == 0) {
            return (int) f;
        }
        if (i != 1 && i != 2) {
            return (int) f;
        }
        return getTextSizeInPixel(f);
    }

    @Deprecated
    public float getAdjustCoefficient(float f) {
        return 1.0f;
    }
}

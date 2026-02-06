package com.xh.xhcore.common.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.xh.xhcore.common.adaptation.TextViewAdaptationUtil;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.AdaptationAutoLinearUtil;
import com.xh.xhcore.common.config.DensityConfigBuilder;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.lang.reflect.Field;

public class DensityUtil {
    public static final float DEFAULT_HEIGHT = 1024.0f;
    public static final float DEFAULT_WIDTH = 768.0f;
    public static final int HEIGHT = 2;
    public static final int WIDTH = 1;
    private static int appDisplayMetricsHeight = 0;
    private static int appDisplayMetricsWidth = 0;
    private static float applicationAdaptationDensity = 0.0f;
    private static float applicationAdaptationScaledDensity = 0.0f;
    private static float applicationOriginalDensity = 0.0f;
    private static float applicationOriginalScaledDensity = 0.0f;
    private static int barHeight = 0;
    private static float densityScale = 1.0f;

    public static void applyAdaptationDensity(Activity activity) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        onActivityConfigChange(activity);
        onApplicationConfigChange(activity.getApplication());
    }

    public static int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, XhBaseApplication.mContext.getResources().getDisplayMetrics());
    }

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static float getApplicationOriginalDensity() {
        return applicationOriginalDensity;
    }

    @Deprecated
    public static DensityHelper getDensityHelper() {
        return new DensityHelper(applicationOriginalScaledDensity, applicationAdaptationScaledDensity);
    }

    public static float getDensityScale() {
        return densityScale;
    }

    private static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static float getTargetDensityByOrientation(int i) {
        float fMin;
        float fMin2;
        DensityConfigBuilder densityConfigBuilder = XHAppConfigProxy.getInstance().getDensityConfigBuilder();
        Float f = (Float) densityConfigBuilder.getDensityAdaptationBasicWidthHeight().first;
        Float f2 = (Float) densityConfigBuilder.getDensityAdaptationBasicWidthHeight().second;
        Float fValueOf = Float.valueOf(1.0f);
        if (i == 2) {
            if (f2.floatValue() == 0.0f) {
                f2 = fValueOf;
            }
            fMin = Math.max(appDisplayMetricsWidth, appDisplayMetricsHeight);
            fMin2 = Math.max(f.floatValue(), f2.floatValue());
        } else {
            if (f.floatValue() == 0.0f) {
                f = fValueOf;
            }
            fMin = Math.min(appDisplayMetricsWidth, appDisplayMetricsHeight);
            fMin2 = Math.min(f.floatValue(), f2.floatValue());
        }
        return fMin / fMin2;
    }

    private static void initAdaptationDensity(int i) {
        float targetDensityByOrientation = getTargetDensityByOrientation(i);
        if (isP200AndCustomDensityValid()) {
            targetDensityByOrientation = XHAppConfigProxy.getInstance().getDensityConfigBuilder().getCustomDensity();
        }
        float f = (applicationOriginalScaledDensity / applicationOriginalDensity) * targetDensityByOrientation;
        applicationAdaptationDensity = targetDensityByOrientation;
        applicationAdaptationScaledDensity = f;
    }

    public static void initAppDensity(Application application) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        appDisplayMetricsWidth = displayMetrics.widthPixels;
        appDisplayMetricsHeight = displayMetrics.heightPixels;
        barHeight = getStatusBarHeight(application);
        if (applicationOriginalDensity == 0.0f) {
            initOriginalDensity(application, displayMetrics);
        }
    }

    private static void initOriginalDensity(final Application application, DisplayMetrics displayMetrics) {
        DensityConfigBuilder densityConfigBuilder = XHAppConfigProxy.getInstance().getDensityConfigBuilder();
        applicationOriginalDensity = displayMetrics.density;
        if (densityConfigBuilder.isIsForbidFontSizeChangeBySystem()) {
            applicationOriginalScaledDensity = applicationOriginalDensity;
        } else {
            applicationOriginalScaledDensity = displayMetrics.scaledDensity;
        }
        application.registerComponentCallbacks(new ComponentCallbacks() {
            @Override
            public void onConfigurationChanged(Configuration configuration) {
                if (XHAppConfigProxy.getInstance().getDensityConfigBuilder().isIsForbidFontSizeChangeBySystem() || configuration == null || configuration.fontScale <= 0.0f) {
                    return;
                }
                float unused = DensityUtil.applicationOriginalScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
            }

            @Override
            public void onLowMemory() {
            }
        });
    }

    private static boolean isP200AndCustomDensityValid() {
        return Build.VERSION.INCREMENTAL.contains("P200") && XHAppConfigProxy.getInstance().getDensityConfigBuilder().getCustomDensity() != 0.0f;
    }

    public static void onActivityConfigChange(Activity activity) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (AdaptationAutoLinearUtil.isEnableAutoAdaptation(activity)) {
            onConfigChangeImpl(activity.getResources().getDisplayMetrics(), TextViewAdaptationUtil.getAdaptationRadio(), TextViewAdaptationUtil.getAdaptationRadio());
        } else {
            onConfigChangeImpl(activity.getResources().getDisplayMetrics(), applicationAdaptationDensity, applicationAdaptationScaledDensity);
        }
    }

    public static void onApplicationConfigChange(Application application) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        onConfigChangeImpl(application.getResources().getDisplayMetrics(), applicationAdaptationDensity, applicationAdaptationScaledDensity);
    }

    private static void onConfigChangeImpl(DisplayMetrics displayMetrics, float f, float f2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        float f3 = displayMetrics.density;
        float f4 = displayMetrics.scaledDensity;
        if (f > 0.0f) {
            if (f3 != f || (f2 > 0.0f && f4 != f2)) {
                if (XHAppConfigProxy.getInstance().isAppBuildTypeDebug()) {
                    LogUtils.d("currentDensity = " + f3 + " targetDensity = " + f + " currentScaledDensity = " + f4 + " targetScaledDensity = " + f2);
                }
                displayMetrics.density = f;
                displayMetrics.scaledDensity = f2;
                displayMetrics.densityDpi = (int) (f * 160.0f);
                setBitmapDefaultDensity(displayMetrics.densityDpi);
            }
        }
    }

    public static void onConfigChangeImplDefault(DisplayMetrics displayMetrics) {
        if (XHAppConfigProxy.getInstance().isDensityModeWidth()) {
            onConfigChangeImpl(displayMetrics, applicationAdaptationDensity, applicationAdaptationScaledDensity);
        }
    }

    public static float px2dp(float f) {
        return f / XhBaseApplication.mContext.getResources().getDisplayMetrics().density;
    }

    public static float px2sp(float f) {
        return f / XhBaseApplication.mContext.getResources().getDisplayMetrics().scaledDensity;
    }

    @Deprecated
    public static void resetAppOrientation(Activity activity) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        displayMetrics.density = applicationOriginalDensity;
        displayMetrics.scaledDensity = applicationOriginalScaledDensity;
        displayMetrics.densityDpi = (int) (applicationOriginalDensity * 160.0f);
        DisplayMetrics displayMetrics2 = activity.getApplication().getResources().getDisplayMetrics();
        displayMetrics2.density = applicationOriginalDensity;
        displayMetrics2.scaledDensity = applicationOriginalScaledDensity;
        displayMetrics2.densityDpi = (int) (applicationOriginalDensity * 160.0f);
        densityScale = 1.0f;
        setBitmapDefaultDensity(displayMetrics.densityDpi);
        applicationAdaptationDensity = 0.0f;
        applicationAdaptationScaledDensity = 0.0f;
    }

    private static void setAppOrientation(Activity activity, int i) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (applicationAdaptationDensity == 0.0f) {
            initAdaptationDensity(i);
            densityScale = applicationOriginalDensity / applicationAdaptationDensity;
        }
        applyAdaptationDensity(activity);
    }

    private static void setBitmapDefaultDensity(int i) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Field declaredField = Class.forName("android.graphics.Bitmap").getDeclaredField("sDefaultDensity");
            declaredField.setAccessible(true);
            declaredField.set(null, Integer.valueOf(i));
            declaredField.setAccessible(false);
        } catch (ClassNotFoundException | NoSuchFieldException unused) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setDefault(Activity activity) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        setAppOrientation(activity, 1);
    }

    @Deprecated
    public static void setOrientation(Activity activity, int i) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        setAppOrientation(activity, i);
    }

    public static int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, XhBaseApplication.mContext.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }
}

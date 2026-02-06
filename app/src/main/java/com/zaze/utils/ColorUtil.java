package com.zaze.utils;

import android.graphics.Color;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\b\b\u0001\u0010\u000b\u001a\u00020\u0007J\u0012\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0007J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0002J\u0012\u0010\u0010\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J\u001a\u0010\u0011\u001a\u00020\u000f2\b\b\u0001\u0010\t\u001a\u00020\u00042\b\b\u0001\u0010\n\u001a\u00020\u0004J\u0012\u0010\u0012\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J\u001c\u0010\u0013\u001a\u00020\u00042\b\b\u0001\u0010\t\u001a\u00020\u00042\b\b\u0001\u0010\n\u001a\u00020\u0004H\u0007J\u001c\u0010\u0014\u001a\u00020\u00042\b\b\u0001\u0010\u0015\u001a\u00020\u00042\b\b\u0001\u0010\u0016\u001a\u00020\u0004H\u0007J$\u0010\u0014\u001a\u00020\u00042\b\b\u0001\u0010\u0015\u001a\u00020\u00042\b\b\u0001\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0007J\u0012\u0010\u0018\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\b\b\u0001\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u001b\u001a\u00020\u001a2\b\b\u0001\u0010\u0005\u001a\u00020\u0004J\u0012\u0010\u001c\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007J\u001c\u0010\u001d\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u001e\u001a\u00020\u0007H\u0007J\u0010\u0010\u001f\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004J\u001c\u0010 \u001a\u00020\u00042\b\b\u0001\u0010!\u001a\u00020\u00042\b\b\u0001\u0010\"\u001a\u00020\u0007H\u0007¨\u0006#"}, d2 = {"Lcom/zaze/utils/ColorUtil;", "", "()V", "adjustAlpha", "", "color", "factor", "", "blendColors", "color1", "color2", "ratio", "darkenColor", "desaturateColor", "getColorDarkness", "", "getContrastColor", "getDifference", "getInverseColor", "getMixedColor", "getReadableText", "textColor", "backgroundColor", "difference", "invertColor", "isColorLight", "", "isColorSaturated", "lightenColor", "shiftColor", "by", "stripAlpha", "withAlpha", "baseColor", "alpha", "util_release"}, k = 1, mv = {1, 4, 1})
public final class ColorUtil {
    public static final ColorUtil INSTANCE = new ColorUtil();

    private ColorUtil() {
    }

    private final double getColorDarkness(int color) {
        if (color == -16777216) {
            return 1.0d;
        }
        if (color == -1 || color == 0) {
            return 0.0d;
        }
        double d = 1;
        double dRed = Color.red(color);
        Double.isNaN(dRed);
        double dGreen = Color.green(color);
        Double.isNaN(dGreen);
        double d2 = (dRed * 0.299d) + (dGreen * 0.587d);
        double dBlue = Color.blue(color);
        Double.isNaN(dBlue);
        double d3 = 255;
        Double.isNaN(d3);
        Double.isNaN(d);
        return d - ((d2 + (dBlue * 0.114d)) / d3);
    }

    public final int adjustAlpha(int color, float factor) {
        return Color.argb(Math.round(Color.alpha(color) * factor), Color.red(color), Color.green(color), Color.blue(color));
    }

    public final int blendColors(int color1, int color2, float ratio) {
        float f = 1.0f - ratio;
        return Color.argb((int) ((Color.alpha(color1) * f) + (Color.alpha(color2) * ratio)), (int) ((Color.red(color1) * f) + (Color.red(color2) * ratio)), (int) ((Color.green(color1) * f) + (Color.green(color2) * ratio)), (int) ((Color.blue(color1) * f) + (Color.blue(color2) * ratio)));
    }

    public final int darkenColor(int color) {
        return shiftColor(color, 0.9f);
    }

    public final int desaturateColor(int color, float ratio) {
        float[] fArr = new float[3];
        Color.colorToHSV(color, fArr);
        fArr[1] = ((fArr[1] / 1) * ratio) + ((1.0f - ratio) * 0.2f);
        return Color.HSVToColor(fArr);
    }

    public final int getContrastColor(int color) {
        double d = 1;
        double dRed = Color.red(color);
        Double.isNaN(dRed);
        double dGreen = Color.green(color);
        Double.isNaN(dGreen);
        double d2 = (dRed * 0.299d) + (dGreen * 0.587d);
        double dBlue = Color.blue(color);
        Double.isNaN(dBlue);
        double d3 = d2 + (dBlue * 0.114d);
        double d4 = 255;
        Double.isNaN(d4);
        Double.isNaN(d);
        return d - (d3 / d4) < 0.5d ? -16777216 : -1;
    }

    public final double getDifference(int color1, int color2) {
        double dRed = Color.red(color1) - Color.red(color2);
        Double.isNaN(dRed);
        double dAbs = Math.abs(dRed * 0.299d);
        double dGreen = Color.green(color1) - Color.green(color2);
        Double.isNaN(dGreen);
        double dAbs2 = dAbs + Math.abs(dGreen * 0.587d);
        double dBlue = Color.blue(color1) - Color.blue(color2);
        Double.isNaN(dBlue);
        return dAbs2 + Math.abs(dBlue * 0.114d);
    }

    public final int getInverseColor(int color) {
        return (16777215 - color) | (-1);
    }

    public final int getMixedColor(int color1, int color2) {
        return Color.rgb((Color.red(color1) + Color.red(color2)) / 2, (Color.green(color1) + Color.green(color2)) / 2, (Color.blue(color1) + Color.blue(color2)) / 2);
    }

    public final int getReadableText(int textColor, int backgroundColor) {
        return getReadableText(textColor, backgroundColor, 100);
    }

    public final int getReadableText(int textColor, int backgroundColor, int difference) {
        boolean zIsColorLight = isColorLight(backgroundColor);
        for (int i = 0; getDifference(textColor, backgroundColor) < difference && i < 100; i++) {
            textColor = getMixedColor(textColor, zIsColorLight ? -16777216 : -1);
        }
        return textColor;
    }

    public final int invertColor(int color) {
        return Color.argb(Color.alpha(color), 255 - Color.red(color), 255 - Color.green(color), 255 - Color.blue(color));
    }

    public final boolean isColorLight(int color) {
        double d = 1;
        double dRed = Color.red(color);
        Double.isNaN(dRed);
        double dGreen = Color.green(color);
        Double.isNaN(dGreen);
        double d2 = (dRed * 0.299d) + (dGreen * 0.587d);
        double dBlue = Color.blue(color);
        Double.isNaN(dBlue);
        double d3 = d2 + (dBlue * 0.114d);
        double d4 = 255;
        Double.isNaN(d4);
        Double.isNaN(d);
        return d - (d3 / d4) < 0.4d;
    }

    public final boolean isColorSaturated(int color) {
        double dRed = Color.red(color);
        Double.isNaN(dRed);
        double dGreen = Color.green(color);
        Double.isNaN(dGreen);
        double dBlue = Color.blue(color);
        Double.isNaN(dBlue);
        double dMax = Math.max(dRed * 0.299d, Math.max(dGreen * 0.587d, dBlue * 0.114d));
        double dRed2 = Color.red(color);
        Double.isNaN(dRed2);
        double dGreen2 = Color.green(color);
        Double.isNaN(dGreen2);
        double dBlue2 = Color.blue(color);
        Double.isNaN(dBlue2);
        return Math.abs(dMax - Math.min(dRed2 * 0.299d, Math.min(dGreen2 * 0.587d, dBlue2 * 0.114d))) > ((double) 20);
    }

    public final int lightenColor(int color) {
        return shiftColor(color, 1.1f);
    }

    public final int shiftColor(int color, float by) {
        if (by == 1.0f) {
            return color;
        }
        int iAlpha = Color.alpha(color);
        float[] fArr = new float[3];
        Color.colorToHSV(color, fArr);
        fArr[2] = fArr[2] * by;
        return (iAlpha << 24) + (16777215 & Color.HSVToColor(fArr));
    }

    public final int stripAlpha(int color) {
        return color | (-16777216);
    }

    public final int withAlpha(int baseColor, float alpha) {
        return (Math.min(255, Math.max(0, (int) (alpha * 255))) << 24) + (baseColor & 16777215);
    }
}

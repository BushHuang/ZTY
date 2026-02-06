package com.xh.xhcore.common.util;

@Deprecated
public class XHDensityUtil {
    private XHDensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int dp2px(float f) {
        return DensityUtil.dp2px(f);
    }

    public static float px2dp(float f) {
        return DensityUtil.px2dp(f);
    }

    public static float px2sp(float f) {
        return DensityUtil.px2sp(f);
    }

    public static int sp2px(float f) {
        return DensityUtil.sp2px(f);
    }
}

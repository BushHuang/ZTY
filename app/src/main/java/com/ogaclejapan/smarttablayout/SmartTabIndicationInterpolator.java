package com.ogaclejapan.smarttablayout;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public abstract class SmartTabIndicationInterpolator {
    static final int ID_LINEAR = 1;
    static final int ID_SMART = 0;
    public static final SmartTabIndicationInterpolator SMART = new SmartIndicationInterpolator();
    public static final SmartTabIndicationInterpolator LINEAR = new LinearIndicationInterpolator();

    public static class LinearIndicationInterpolator extends SmartTabIndicationInterpolator {
        @Override
        public float getLeftEdge(float f) {
            return f;
        }

        @Override
        public float getRightEdge(float f) {
            return f;
        }
    }

    public static class SmartIndicationInterpolator extends SmartTabIndicationInterpolator {
        private static final float DEFAULT_INDICATOR_INTERPOLATION_FACTOR = 3.0f;
        private final Interpolator leftEdgeInterpolator;
        private final Interpolator rightEdgeInterpolator;

        public SmartIndicationInterpolator() {
            this(3.0f);
        }

        public SmartIndicationInterpolator(float f) {
            this.leftEdgeInterpolator = new AccelerateInterpolator(f);
            this.rightEdgeInterpolator = new DecelerateInterpolator(f);
        }

        @Override
        public float getLeftEdge(float f) {
            return this.leftEdgeInterpolator.getInterpolation(f);
        }

        @Override
        public float getRightEdge(float f) {
            return this.rightEdgeInterpolator.getInterpolation(f);
        }

        @Override
        public float getThickness(float f) {
            return 1.0f / ((1.0f - getLeftEdge(f)) + getRightEdge(f));
        }
    }

    public static SmartTabIndicationInterpolator of(int i) {
        if (i == 0) {
            return SMART;
        }
        if (i == 1) {
            return LINEAR;
        }
        throw new IllegalArgumentException("Unknown id: " + i);
    }

    public abstract float getLeftEdge(float f);

    public abstract float getRightEdge(float f);

    public float getThickness(float f) {
        return 1.0f;
    }
}

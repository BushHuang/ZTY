package androidx.constraintlayout.motion.utils;

import java.util.Arrays;

public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    double[] mArea;
    int mType;
    float[] mPeriod = new float[0];
    double[] mPosition = new double[0];
    double PI2 = 6.283185307179586d;
    private boolean mNormalized = false;

    public void addPoint(double d, float f) {
        int length = this.mPeriod.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, length);
        this.mPeriod = Arrays.copyOf(this.mPeriod, length);
        this.mArea = new double[length];
        double[] dArr = this.mPosition;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.mPosition[iBinarySearch] = d;
        this.mPeriod[iBinarySearch] = f;
        this.mNormalized = false;
    }

    double getDP(double d) {
        if (d <= 0.0d) {
            d = 1.0E-5d;
        } else if (d >= 1.0d) {
            d = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.mPeriod;
        int i2 = i - 1;
        double d2 = fArr[i] - fArr[i2];
        double[] dArr = this.mPosition;
        double d3 = dArr[i] - dArr[i2];
        Double.isNaN(d2);
        double d4 = d2 / d3;
        double d5 = d * d4;
        double d6 = fArr[i2];
        double d7 = d4 * dArr[i2];
        Double.isNaN(d6);
        return (d6 - d7) + d5;
    }

    double getP(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.mPeriod;
        int i2 = i - 1;
        double d2 = fArr[i] - fArr[i2];
        double[] dArr = this.mPosition;
        double d3 = dArr[i] - dArr[i2];
        Double.isNaN(d2);
        double d4 = d2 / d3;
        double d5 = this.mArea[i2];
        double d6 = fArr[i2];
        double d7 = dArr[i2] * d4;
        Double.isNaN(d6);
        return d5 + ((d6 - d7) * (d - dArr[i2])) + ((d4 * ((d * d) - (dArr[i2] * dArr[i2]))) / 2.0d);
    }

    public double getSlope(double d) {
        double dp;
        double dSignum;
        double dp2;
        double dp3;
        double dSin;
        switch (this.mType) {
            case 1:
                return 0.0d;
            case 2:
                dp = getDP(d) * 4.0d;
                dSignum = Math.signum((((getP(d) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dp * dSignum;
            case 3:
                dp2 = getDP(d);
                return dp2 * 2.0d;
            case 4:
                dp2 = -getDP(d);
                return dp2 * 2.0d;
            case 5:
                dp3 = (-this.PI2) * getDP(d);
                dSin = Math.sin(this.PI2 * getP(d));
                return dp3 * dSin;
            case 6:
                dp = getDP(d) * 4.0d;
                dSignum = (((getP(d) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dp * dSignum;
            default:
                dp3 = this.PI2 * getDP(d);
                dSin = Math.cos(this.PI2 * getP(d));
                return dp3 * dSin;
        }
    }

    public double getValue(double d) {
        double dAbs;
        switch (this.mType) {
            case 1:
                return Math.signum(0.5d - (getP(d) % 1.0d));
            case 2:
                dAbs = Math.abs((((getP(d) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((getP(d) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((getP(d) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.PI2 * getP(d));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((getP(d) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.PI2 * getP(d));
        }
        return 1.0d - dAbs;
    }

    public void normalize() {
        double d = 0.0d;
        int i = 0;
        while (true) {
            float[] fArr = this.mPeriod;
            if (i >= fArr.length) {
                break;
            }
            double d2 = fArr[i];
            Double.isNaN(d2);
            d += d2;
            i++;
        }
        double d3 = 0.0d;
        int i2 = 1;
        while (true) {
            float[] fArr2 = this.mPeriod;
            if (i2 >= fArr2.length) {
                break;
            }
            int i3 = i2 - 1;
            float f = (fArr2[i3] + fArr2[i2]) / 2.0f;
            double[] dArr = this.mPosition;
            double d4 = dArr[i2] - dArr[i3];
            double d5 = f;
            Double.isNaN(d5);
            d3 += d4 * d5;
            i2++;
        }
        int i4 = 0;
        while (true) {
            float[] fArr3 = this.mPeriod;
            if (i4 >= fArr3.length) {
                break;
            }
            double d6 = fArr3[i4];
            Double.isNaN(d6);
            fArr3[i4] = (float) (d6 * (d / d3));
            i4++;
        }
        this.mArea[0] = 0.0d;
        int i5 = 1;
        while (true) {
            float[] fArr4 = this.mPeriod;
            if (i5 >= fArr4.length) {
                this.mNormalized = true;
                return;
            }
            int i6 = i5 - 1;
            float f2 = (fArr4[i6] + fArr4[i5]) / 2.0f;
            double[] dArr2 = this.mPosition;
            double d7 = dArr2[i5] - dArr2[i6];
            double[] dArr3 = this.mArea;
            double d8 = dArr3[i6];
            double d9 = f2;
            Double.isNaN(d9);
            dArr3[i5] = d8 + (d7 * d9);
            i5++;
        }
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.mPosition) + " period=" + Arrays.toString(this.mPeriod);
    }
}

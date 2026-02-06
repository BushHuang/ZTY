package androidx.constraintlayout.motion.utils;

import java.util.Arrays;

class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    Arc[] mArcs;
    private final double[] mTime;

    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];
        boolean linear;
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            this.linear = false;
            this.mVertical = i == 1;
            this.mTime1 = d;
            this.mTime2 = d2;
            this.mOneOverDeltaTime = 1.0d / (d2 - d);
            if (3 == i) {
                this.linear = true;
            }
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (!this.linear && Math.abs(d7) >= 0.001d && Math.abs(d8) >= 0.001d) {
                this.mLut = new double[101];
                double d9 = this.mVertical ? -1 : 1;
                Double.isNaN(d9);
                this.mEllipseA = d7 * d9;
                double d10 = this.mVertical ? 1 : -1;
                Double.isNaN(d10);
                this.mEllipseB = d8 * d10;
                this.mEllipseCenterX = this.mVertical ? d5 : d3;
                this.mEllipseCenterY = this.mVertical ? d4 : d6;
                buildTable(d3, d4, d5, d6);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                return;
            }
            this.linear = true;
            this.mX1 = d3;
            this.mX2 = d5;
            this.mY1 = d4;
            this.mY2 = d6;
            double dHypot = Math.hypot(d8, d7);
            this.mArcDistance = dHypot;
            this.mArcVelocity = dHypot * this.mOneOverDeltaTime;
            double d11 = this.mTime2;
            double d12 = this.mTime1;
            this.mEllipseCenterX = d7 / (d11 - d12);
            this.mEllipseCenterY = d8 / (d11 - d12);
        }

        private void buildTable(double d, double d2, double d3, double d4) {
            double dHypot;
            double d5 = d3 - d;
            double d6 = d2 - d4;
            int i = 0;
            double d7 = 0.0d;
            double d8 = 0.0d;
            double d9 = 0.0d;
            while (true) {
                if (i >= ourPercent.length) {
                    break;
                }
                double d10 = i;
                Double.isNaN(d10);
                double d11 = d7;
                double length = r15.length - 1;
                Double.isNaN(length);
                double radians = Math.toRadians((d10 * 90.0d) / length);
                double dSin = Math.sin(radians) * d5;
                double dCos = Math.cos(radians) * d6;
                if (i > 0) {
                    dHypot = d11 + Math.hypot(dSin - d8, dCos - d9);
                    ourPercent[i] = dHypot;
                } else {
                    dHypot = d11;
                }
                i++;
                d9 = dCos;
                d7 = dHypot;
                d8 = dSin;
            }
            double d12 = d7;
            this.mArcDistance = d12;
            int i2 = 0;
            while (true) {
                double[] dArr = ourPercent;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] / d12;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.mLut.length) {
                    return;
                }
                double d13 = i3;
                double length2 = r1.length - 1;
                Double.isNaN(d13);
                Double.isNaN(length2);
                double d14 = d13 / length2;
                int iBinarySearch = Arrays.binarySearch(ourPercent, d14);
                if (iBinarySearch >= 0) {
                    this.mLut[i3] = iBinarySearch / (ourPercent.length - 1);
                } else if (iBinarySearch == -1) {
                    this.mLut[i3] = 0.0d;
                } else {
                    int i4 = -iBinarySearch;
                    int i5 = i4 - 2;
                    double d15 = i5;
                    double[] dArr2 = ourPercent;
                    double d16 = (d14 - dArr2[i5]) / (dArr2[i4 - 1] - dArr2[i5]);
                    Double.isNaN(d15);
                    double length3 = dArr2.length - 1;
                    Double.isNaN(length3);
                    this.mLut[i3] = (d15 + d16) / length3;
                }
                i3++;
            }
        }

        double getDX() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d, (-this.mEllipseB) * this.mTmpSinAngle);
            if (this.mVertical) {
                d = -d;
            }
            return d * dHypot;
        }

        double getDY() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double d2 = (-this.mEllipseB) * this.mTmpSinAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d, d2);
            return this.mVertical ? (-d2) * dHypot : d2 * dHypot;
        }

        public double getLinearDX(double d) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d) {
            return this.mEllipseCenterY;
        }

        public double getLinearX(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mX1;
            return d3 + (d2 * (this.mX2 - d3));
        }

        public double getLinearY(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mY1;
            return d3 + (d2 * (this.mY2 - d3));
        }

        double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        double lookup(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = dArr.length - 1;
            Double.isNaN(length);
            double d2 = d * length;
            int i = (int) d2;
            double d3 = i;
            Double.isNaN(d3);
            return dArr[i] + ((d2 - d3) * (dArr[i + 1] - dArr[i]));
        }

        void setPoint(double d) {
            double dLookup = lookup((this.mVertical ? this.mTime2 - d : d - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(dLookup);
            this.mTmpCosAngle = Math.cos(dLookup);
        }
    }

    public ArcCurveFit(int[] iArr, double[] dArr, double[][] dArr2) {
        int i;
        this.mTime = dArr;
        this.mArcs = new Arc[dArr.length - 1];
        int i2 = 0;
        int i3 = 1;
        int i4 = 1;
        while (i2 < this.mArcs.length) {
            int i5 = iArr[i2];
            if (i5 == 0) {
                i = 3;
            } else if (i5 == 1) {
                i3 = 1;
                i = 1;
            } else if (i5 == 2) {
                i3 = 2;
                i = 2;
            } else if (i5 != 3) {
                i = i4;
            } else {
                i3 = i3 == 1 ? 2 : 1;
                i = i3;
            }
            int i6 = i2 + 1;
            this.mArcs[i2] = new Arc(i, dArr[i2], dArr[i6], dArr2[i2][0], dArr2[i2][1], dArr2[i6][0], dArr2[i6][1]);
            i4 = i;
            i2 = i6;
        }
    }

    @Override
    public double getPos(double d, int i) {
        int i2 = 0;
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            if (d > this.mArcs[r0.length - 1].mTime2) {
                d = this.mArcs[r5.length - 1].mTime2;
            }
        }
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i2 >= arcArr.length) {
                return Double.NaN;
            }
            if (d <= arcArr[i2].mTime2) {
                if (this.mArcs[i2].linear) {
                    return i == 0 ? this.mArcs[i2].getLinearX(d) : this.mArcs[i2].getLinearY(d);
                }
                this.mArcs[i2].setPoint(d);
                return i == 0 ? this.mArcs[i2].getX() : this.mArcs[i2].getY();
            }
            i2++;
        }
    }

    @Override
    public void getPos(double d, double[] dArr) {
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        }
        Arc[] arcArr = this.mArcs;
        if (d > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            d = arcArr2[arcArr2.length - 1].mTime2;
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d <= arcArr3[i].mTime2) {
                if (this.mArcs[i].linear) {
                    dArr[0] = this.mArcs[i].getLinearX(d);
                    dArr[1] = this.mArcs[i].getLinearY(d);
                    return;
                } else {
                    this.mArcs[i].setPoint(d);
                    dArr[0] = this.mArcs[i].getX();
                    dArr[1] = this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override
    public void getPos(double d, float[] fArr) {
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d <= arcArr3[i].mTime2) {
                if (this.mArcs[i].linear) {
                    fArr[0] = (float) this.mArcs[i].getLinearX(d);
                    fArr[1] = (float) this.mArcs[i].getLinearY(d);
                    return;
                } else {
                    this.mArcs[i].setPoint(d);
                    fArr[0] = (float) this.mArcs[i].getX();
                    fArr[1] = (float) this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override
    public double getSlope(double d, int i) {
        int i2 = 0;
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        }
        if (d > this.mArcs[r0.length - 1].mTime2) {
            d = this.mArcs[r5.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i2 >= arcArr.length) {
                return Double.NaN;
            }
            if (d <= arcArr[i2].mTime2) {
                if (this.mArcs[i2].linear) {
                    return i == 0 ? this.mArcs[i2].getLinearDX(d) : this.mArcs[i2].getLinearDY(d);
                }
                this.mArcs[i2].setPoint(d);
                return i == 0 ? this.mArcs[i2].getDX() : this.mArcs[i2].getDY();
            }
            i2++;
        }
    }

    @Override
    public void getSlope(double d, double[] dArr) {
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        } else {
            Arc[] arcArr = this.mArcs;
            if (d > arcArr[arcArr.length - 1].mTime2) {
                Arc[] arcArr2 = this.mArcs;
                d = arcArr2[arcArr2.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d <= arcArr3[i].mTime2) {
                if (this.mArcs[i].linear) {
                    dArr[0] = this.mArcs[i].getLinearDX(d);
                    dArr[1] = this.mArcs[i].getLinearDY(d);
                    return;
                } else {
                    this.mArcs[i].setPoint(d);
                    dArr[0] = this.mArcs[i].getDX();
                    dArr[1] = this.mArcs[i].getDY();
                    return;
                }
            }
            i++;
        }
    }

    @Override
    public double[] getTimePoints() {
        return this.mTime;
    }
}

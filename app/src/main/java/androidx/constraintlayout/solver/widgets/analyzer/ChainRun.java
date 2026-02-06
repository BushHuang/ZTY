package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.widgets = new ArrayList<>();
        this.orientation = i;
        build();
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
            }
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            if (this.orientation == 0) {
                next.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    @Override
    void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.widgets.get(0).widget;
        ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode target = getTarget(constraintAnchor, 0);
            int margin = constraintAnchor.getMargin();
            ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
            if (firstVisibleWidget != null) {
                margin = firstVisibleWidget.mLeft.getMargin();
            }
            if (target != null) {
                addTarget(this.start, target, margin);
            }
            DependencyNode target2 = getTarget(constraintAnchor2, 0);
            int margin2 = constraintAnchor2.getMargin();
            ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
            if (lastVisibleWidget != null) {
                margin2 = lastVisibleWidget.mRight.getMargin();
            }
            if (target2 != null) {
                addTarget(this.end, target2, -margin2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode target3 = getTarget(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
            if (firstVisibleWidget2 != null) {
                margin3 = firstVisibleWidget2.mTop.getMargin();
            }
            if (target3 != null) {
                addTarget(this.start, target3, margin3);
            }
            DependencyNode target4 = getTarget(constraintAnchor4, 1);
            int margin4 = constraintAnchor4.getMargin();
            ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
            if (lastVisibleWidget2 != null) {
                margin4 = lastVisibleWidget2.mBottom.getMargin();
            }
            if (target4 != null) {
                addTarget(this.end, target4, -margin4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }

    @Override
    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    @Override
    void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    @Override
    public long getWrapDimension() {
        int size = this.widgets.size();
        long wrapDimension = 0;
        for (int i = 0; i < size; i++) {
            wrapDimension = wrapDimension + r4.start.margin + this.widgets.get(i).getWrapDimension() + r4.end.margin;
        }
        return wrapDimension;
    }

    @Override
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    @Override
    boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        String string = sb.toString();
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            String str = string + "<";
            string = (str + it.next()) + "> ";
        }
        return string;
    }

    @Override
    public void update(Dependency dependency) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f;
        int i6;
        int i7;
        int i8;
        float f2;
        int i9;
        if (this.start.resolved && this.end.resolved) {
            ConstraintWidget parent = this.widget.getParent();
            boolean zIsRtl = (parent == null || !(parent instanceof ConstraintWidgetContainer)) ? false : ((ConstraintWidgetContainer) parent).isRtl();
            int i10 = this.end.value - this.start.value;
            int size = this.widgets.size();
            int i11 = 0;
            while (true) {
                i = -1;
                i2 = 8;
                if (i11 >= size) {
                    i11 = -1;
                    break;
                } else if (this.widgets.get(i11).widget.getVisibility() != 8) {
                    break;
                } else {
                    i11++;
                }
            }
            int i12 = size - 1;
            int i13 = i12;
            while (true) {
                if (i13 < 0) {
                    break;
                }
                if (this.widgets.get(i13).widget.getVisibility() != 8) {
                    i = i13;
                    break;
                }
                i13--;
            }
            int i14 = 0;
            while (i14 < 2) {
                int i15 = 0;
                i4 = 0;
                i5 = 0;
                int i16 = 0;
                f = 0.0f;
                while (i15 < size) {
                    WidgetRun widgetRun = this.widgets.get(i15);
                    if (widgetRun.widget.getVisibility() != i2) {
                        i16++;
                        if (i15 > 0 && i15 >= i11) {
                            i4 += widgetRun.start.margin;
                        }
                        int i17 = widgetRun.dimension.value;
                        boolean z = widgetRun.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (!z) {
                            if (widgetRun.matchConstraintsType == 1 && i14 == 0) {
                                i17 = widgetRun.dimension.wrapValue;
                                i5++;
                            } else if (widgetRun.dimension.resolved) {
                            }
                            z = true;
                        } else {
                            if (this.orientation == 0 && !widgetRun.widget.horizontalRun.dimension.resolved) {
                                return;
                            }
                            if (this.orientation == 1 && !widgetRun.widget.verticalRun.dimension.resolved) {
                                return;
                            }
                        }
                        if (z) {
                            i4 += i17;
                        } else {
                            i5++;
                            float f3 = widgetRun.widget.mWeight[this.orientation];
                            if (f3 >= 0.0f) {
                                f += f3;
                            }
                        }
                        if (i15 < i12 && i15 < i) {
                            i4 += -widgetRun.end.margin;
                        }
                    }
                    i15++;
                    i2 = 8;
                }
                if (i4 < i10 || i5 == 0) {
                    i3 = i16;
                    break;
                } else {
                    i14++;
                    i2 = 8;
                }
            }
            i3 = 0;
            i4 = 0;
            i5 = 0;
            f = 0.0f;
            int i18 = this.start.value;
            if (zIsRtl) {
                i18 = this.end.value;
            }
            if (i4 > i10) {
                i18 = zIsRtl ? i18 + ((int) (((i4 - i10) / 2.0f) + 0.5f)) : i18 - ((int) (((i4 - i10) / 2.0f) + 0.5f));
            }
            if (i5 > 0) {
                float f4 = i10 - i4;
                int i19 = (int) ((f4 / i5) + 0.5f);
                int i20 = 0;
                int i21 = 0;
                while (i20 < size) {
                    WidgetRun widgetRun2 = this.widgets.get(i20);
                    int i22 = i19;
                    if (widgetRun2.widget.getVisibility() == 8 || widgetRun2.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widgetRun2.dimension.resolved) {
                        i8 = i18;
                        f2 = f4;
                        i9 = i4;
                    } else {
                        int i23 = f > 0.0f ? (int) (((widgetRun2.widget.mWeight[this.orientation] * f4) / f) + 0.5f) : i22;
                        if (this.orientation == 0) {
                            int i24 = widgetRun2.widget.mMatchConstraintMaxWidth;
                            f2 = f4;
                            i9 = i4;
                            i8 = i18;
                            int iMax = Math.max(widgetRun2.widget.mMatchConstraintMinWidth, widgetRun2.matchConstraintsType == 1 ? Math.min(i23, widgetRun2.dimension.wrapValue) : i23);
                            if (i24 > 0) {
                                iMax = Math.min(i24, iMax);
                            }
                            if (iMax != i23) {
                                i21++;
                                i23 = iMax;
                            }
                        } else {
                            i8 = i18;
                            f2 = f4;
                            i9 = i4;
                            int i25 = widgetRun2.widget.mMatchConstraintMaxHeight;
                            int iMax2 = Math.max(widgetRun2.widget.mMatchConstraintMinHeight, widgetRun2.matchConstraintsType == 1 ? Math.min(i23, widgetRun2.dimension.wrapValue) : i23);
                            if (i25 > 0) {
                                iMax2 = Math.min(i25, iMax2);
                            }
                            if (iMax2 != i23) {
                                i21++;
                                i23 = iMax2;
                            }
                        }
                        widgetRun2.dimension.resolve(i23);
                    }
                    i20++;
                    i19 = i22;
                    f4 = f2;
                    i4 = i9;
                    i18 = i8;
                }
                i6 = i18;
                int i26 = i4;
                if (i21 > 0) {
                    i5 -= i21;
                    int i27 = 0;
                    for (int i28 = 0; i28 < size; i28++) {
                        WidgetRun widgetRun3 = this.widgets.get(i28);
                        if (widgetRun3.widget.getVisibility() != 8) {
                            if (i28 > 0 && i28 >= i11) {
                                i27 += widgetRun3.start.margin;
                            }
                            i27 += widgetRun3.dimension.value;
                            if (i28 < i12 && i28 < i) {
                                i27 += -widgetRun3.end.margin;
                            }
                        }
                    }
                    i4 = i27;
                } else {
                    i4 = i26;
                }
                i7 = 2;
                if (this.chainStyle == 2 && i21 == 0) {
                    this.chainStyle = 0;
                }
            } else {
                i6 = i18;
                i7 = 2;
            }
            if (i4 > i10) {
                this.chainStyle = i7;
            }
            if (i3 > 0 && i5 == 0 && i11 == i) {
                this.chainStyle = i7;
            }
            int i29 = this.chainStyle;
            if (i29 == 1) {
                int i30 = i3 > 1 ? (i10 - i4) / (i3 - 1) : i3 == 1 ? (i10 - i4) / 2 : 0;
                if (i5 > 0) {
                    i30 = 0;
                }
                int i31 = i6;
                for (int i32 = 0; i32 < size; i32++) {
                    WidgetRun widgetRun4 = this.widgets.get(zIsRtl ? size - (i32 + 1) : i32);
                    if (widgetRun4.widget.getVisibility() == 8) {
                        widgetRun4.start.resolve(i31);
                        widgetRun4.end.resolve(i31);
                    } else {
                        if (i32 > 0) {
                            i31 = zIsRtl ? i31 - i30 : i31 + i30;
                        }
                        if (i32 > 0 && i32 >= i11) {
                            i31 = zIsRtl ? i31 - widgetRun4.start.margin : i31 + widgetRun4.start.margin;
                        }
                        if (zIsRtl) {
                            widgetRun4.end.resolve(i31);
                        } else {
                            widgetRun4.start.resolve(i31);
                        }
                        int i33 = widgetRun4.dimension.value;
                        if (widgetRun4.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun4.matchConstraintsType == 1) {
                            i33 = widgetRun4.dimension.wrapValue;
                        }
                        i31 = zIsRtl ? i31 - i33 : i31 + i33;
                        if (zIsRtl) {
                            widgetRun4.start.resolve(i31);
                        } else {
                            widgetRun4.end.resolve(i31);
                        }
                        widgetRun4.resolved = true;
                        if (i32 < i12 && i32 < i) {
                            i31 = zIsRtl ? i31 - (-widgetRun4.end.margin) : i31 + (-widgetRun4.end.margin);
                        }
                    }
                }
                return;
            }
            if (i29 == 0) {
                int i34 = (i10 - i4) / (i3 + 1);
                if (i5 > 0) {
                    i34 = 0;
                }
                int i35 = i6;
                for (int i36 = 0; i36 < size; i36++) {
                    WidgetRun widgetRun5 = this.widgets.get(zIsRtl ? size - (i36 + 1) : i36);
                    if (widgetRun5.widget.getVisibility() == 8) {
                        widgetRun5.start.resolve(i35);
                        widgetRun5.end.resolve(i35);
                    } else {
                        int i37 = zIsRtl ? i35 - i34 : i35 + i34;
                        if (i36 > 0 && i36 >= i11) {
                            i37 = zIsRtl ? i37 - widgetRun5.start.margin : i37 + widgetRun5.start.margin;
                        }
                        if (zIsRtl) {
                            widgetRun5.end.resolve(i37);
                        } else {
                            widgetRun5.start.resolve(i37);
                        }
                        int iMin = widgetRun5.dimension.value;
                        if (widgetRun5.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun5.matchConstraintsType == 1) {
                            iMin = Math.min(iMin, widgetRun5.dimension.wrapValue);
                        }
                        i35 = zIsRtl ? i37 - iMin : i37 + iMin;
                        if (zIsRtl) {
                            widgetRun5.start.resolve(i35);
                        } else {
                            widgetRun5.end.resolve(i35);
                        }
                        if (i36 < i12 && i36 < i) {
                            i35 = zIsRtl ? i35 - (-widgetRun5.end.margin) : i35 + (-widgetRun5.end.margin);
                        }
                    }
                }
                return;
            }
            if (i29 == 2) {
                float horizontalBiasPercent = this.orientation == 0 ? this.widget.getHorizontalBiasPercent() : this.widget.getVerticalBiasPercent();
                if (zIsRtl) {
                    horizontalBiasPercent = 1.0f - horizontalBiasPercent;
                }
                int i38 = (int) (((i10 - i4) * horizontalBiasPercent) + 0.5f);
                if (i38 < 0 || i5 > 0) {
                    i38 = 0;
                }
                int i39 = zIsRtl ? i6 - i38 : i6 + i38;
                for (int i40 = 0; i40 < size; i40++) {
                    WidgetRun widgetRun6 = this.widgets.get(zIsRtl ? size - (i40 + 1) : i40);
                    if (widgetRun6.widget.getVisibility() == 8) {
                        widgetRun6.start.resolve(i39);
                        widgetRun6.end.resolve(i39);
                    } else {
                        if (i40 > 0 && i40 >= i11) {
                            i39 = zIsRtl ? i39 - widgetRun6.start.margin : i39 + widgetRun6.start.margin;
                        }
                        if (zIsRtl) {
                            widgetRun6.end.resolve(i39);
                        } else {
                            widgetRun6.start.resolve(i39);
                        }
                        int i41 = widgetRun6.dimension.value;
                        if (widgetRun6.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun6.matchConstraintsType == 1) {
                            i41 = widgetRun6.dimension.wrapValue;
                        }
                        i39 = zIsRtl ? i39 - i41 : i39 + i41;
                        if (zIsRtl) {
                            widgetRun6.start.resolve(i39);
                        } else {
                            widgetRun6.end.resolve(i39);
                        }
                        if (i40 < i12 && i40 < i) {
                            i39 = zIsRtl ? i39 - (-widgetRun6.end.margin) : i39 + (-widgetRun6.end.margin);
                        }
                    }
                }
            }
        }
    }
}

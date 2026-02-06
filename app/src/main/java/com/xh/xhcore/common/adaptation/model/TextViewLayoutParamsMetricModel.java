package com.xh.xhcore.common.adaptation.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/xh/xhcore/common/adaptation/model/TextViewLayoutParamsMetricModel;", "", "marginLeft", "", "marginTop", "marginRight", "marginBottom", "(IIII)V", "getMarginBottom", "()I", "getMarginLeft", "getMarginRight", "getMarginTop", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextViewLayoutParamsMetricModel {
    private final int marginBottom;
    private final int marginLeft;
    private final int marginRight;
    private final int marginTop;

    public TextViewLayoutParamsMetricModel() {
        this(0, 0, 0, 0, 15, null);
    }

    public TextViewLayoutParamsMetricModel(int i, int i2, int i3, int i4) {
        this.marginLeft = i;
        this.marginTop = i2;
        this.marginRight = i3;
        this.marginBottom = i4;
    }

    public TextViewLayoutParamsMetricModel(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    public static TextViewLayoutParamsMetricModel copy$default(TextViewLayoutParamsMetricModel textViewLayoutParamsMetricModel, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = textViewLayoutParamsMetricModel.marginLeft;
        }
        if ((i5 & 2) != 0) {
            i2 = textViewLayoutParamsMetricModel.marginTop;
        }
        if ((i5 & 4) != 0) {
            i3 = textViewLayoutParamsMetricModel.marginRight;
        }
        if ((i5 & 8) != 0) {
            i4 = textViewLayoutParamsMetricModel.marginBottom;
        }
        return textViewLayoutParamsMetricModel.copy(i, i2, i3, i4);
    }

    public final int getMarginLeft() {
        return this.marginLeft;
    }

    public final int getMarginTop() {
        return this.marginTop;
    }

    public final int getMarginRight() {
        return this.marginRight;
    }

    public final int getMarginBottom() {
        return this.marginBottom;
    }

    public final TextViewLayoutParamsMetricModel copy(int marginLeft, int marginTop, int marginRight, int marginBottom) {
        return new TextViewLayoutParamsMetricModel(marginLeft, marginTop, marginRight, marginBottom);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextViewLayoutParamsMetricModel)) {
            return false;
        }
        TextViewLayoutParamsMetricModel textViewLayoutParamsMetricModel = (TextViewLayoutParamsMetricModel) other;
        return this.marginLeft == textViewLayoutParamsMetricModel.marginLeft && this.marginTop == textViewLayoutParamsMetricModel.marginTop && this.marginRight == textViewLayoutParamsMetricModel.marginRight && this.marginBottom == textViewLayoutParamsMetricModel.marginBottom;
    }

    public final int getMarginBottom() {
        return this.marginBottom;
    }

    public final int getMarginLeft() {
        return this.marginLeft;
    }

    public final int getMarginRight() {
        return this.marginRight;
    }

    public final int getMarginTop() {
        return this.marginTop;
    }

    public int hashCode() {
        return (((((this.marginLeft * 31) + this.marginTop) * 31) + this.marginRight) * 31) + this.marginBottom;
    }

    public String toString() {
        return "TextViewLayoutParamsMetricModel(marginLeft=" + this.marginLeft + ", marginTop=" + this.marginTop + ", marginRight=" + this.marginRight + ", marginBottom=" + this.marginBottom + ')';
    }
}

package com.xh.xhcore.common.adaptation.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006#"}, d2 = {"Lcom/xh/xhcore/common/adaptation/model/TextViewMetricModel;", "", "paddingLeft", "", "paddingTop", "paddingRight", "paddingBottom", "lineSpacingAdd", "", "lineSpacingMult", "textSize", "(IIIIFFF)V", "getLineSpacingAdd", "()F", "getLineSpacingMult", "getPaddingBottom", "()I", "getPaddingLeft", "getPaddingRight", "getPaddingTop", "getTextSize", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextViewMetricModel {
    private final float lineSpacingAdd;
    private final float lineSpacingMult;
    private final int paddingBottom;
    private final int paddingLeft;
    private final int paddingRight;
    private final int paddingTop;
    private final float textSize;

    public TextViewMetricModel() {
        this(0, 0, 0, 0, 0.0f, 0.0f, 0.0f, 127, null);
    }

    public TextViewMetricModel(int i, int i2, int i3, int i4, float f, float f2, float f3) {
        this.paddingLeft = i;
        this.paddingTop = i2;
        this.paddingRight = i3;
        this.paddingBottom = i4;
        this.lineSpacingAdd = f;
        this.lineSpacingMult = f2;
        this.textSize = f3;
    }

    public TextViewMetricModel(int i, int i2, int i3, int i4, float f, float f2, float f3, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) == 0 ? i4 : 0, (i5 & 16) != 0 ? 0.0f : f, (i5 & 32) != 0 ? 1.0f : f2, (i5 & 64) != 0 ? 20.0f : f3);
    }

    public static TextViewMetricModel copy$default(TextViewMetricModel textViewMetricModel, int i, int i2, int i3, int i4, float f, float f2, float f3, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = textViewMetricModel.paddingLeft;
        }
        if ((i5 & 2) != 0) {
            i2 = textViewMetricModel.paddingTop;
        }
        int i6 = i2;
        if ((i5 & 4) != 0) {
            i3 = textViewMetricModel.paddingRight;
        }
        int i7 = i3;
        if ((i5 & 8) != 0) {
            i4 = textViewMetricModel.paddingBottom;
        }
        int i8 = i4;
        if ((i5 & 16) != 0) {
            f = textViewMetricModel.lineSpacingAdd;
        }
        float f4 = f;
        if ((i5 & 32) != 0) {
            f2 = textViewMetricModel.lineSpacingMult;
        }
        float f5 = f2;
        if ((i5 & 64) != 0) {
            f3 = textViewMetricModel.textSize;
        }
        return textViewMetricModel.copy(i, i6, i7, i8, f4, f5, f3);
    }

    public final int getPaddingLeft() {
        return this.paddingLeft;
    }

    public final int getPaddingTop() {
        return this.paddingTop;
    }

    public final int getPaddingRight() {
        return this.paddingRight;
    }

    public final int getPaddingBottom() {
        return this.paddingBottom;
    }

    public final float getLineSpacingAdd() {
        return this.lineSpacingAdd;
    }

    public final float getLineSpacingMult() {
        return this.lineSpacingMult;
    }

    public final float getTextSize() {
        return this.textSize;
    }

    public final TextViewMetricModel copy(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom, float lineSpacingAdd, float lineSpacingMult, float textSize) {
        return new TextViewMetricModel(paddingLeft, paddingTop, paddingRight, paddingBottom, lineSpacingAdd, lineSpacingMult, textSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextViewMetricModel)) {
            return false;
        }
        TextViewMetricModel textViewMetricModel = (TextViewMetricModel) other;
        return this.paddingLeft == textViewMetricModel.paddingLeft && this.paddingTop == textViewMetricModel.paddingTop && this.paddingRight == textViewMetricModel.paddingRight && this.paddingBottom == textViewMetricModel.paddingBottom && Intrinsics.areEqual((Object) Float.valueOf(this.lineSpacingAdd), (Object) Float.valueOf(textViewMetricModel.lineSpacingAdd)) && Intrinsics.areEqual((Object) Float.valueOf(this.lineSpacingMult), (Object) Float.valueOf(textViewMetricModel.lineSpacingMult)) && Intrinsics.areEqual((Object) Float.valueOf(this.textSize), (Object) Float.valueOf(textViewMetricModel.textSize));
    }

    public final float getLineSpacingAdd() {
        return this.lineSpacingAdd;
    }

    public final float getLineSpacingMult() {
        return this.lineSpacingMult;
    }

    public final int getPaddingBottom() {
        return this.paddingBottom;
    }

    public final int getPaddingLeft() {
        return this.paddingLeft;
    }

    public final int getPaddingRight() {
        return this.paddingRight;
    }

    public final int getPaddingTop() {
        return this.paddingTop;
    }

    public final float getTextSize() {
        return this.textSize;
    }

    public int hashCode() {
        return (((((((((((this.paddingLeft * 31) + this.paddingTop) * 31) + this.paddingRight) * 31) + this.paddingBottom) * 31) + Float.floatToIntBits(this.lineSpacingAdd)) * 31) + Float.floatToIntBits(this.lineSpacingMult)) * 31) + Float.floatToIntBits(this.textSize);
    }

    public String toString() {
        return "TextViewMetricModel(paddingLeft=" + this.paddingLeft + ", paddingTop=" + this.paddingTop + ", paddingRight=" + this.paddingRight + ", paddingBottom=" + this.paddingBottom + ", lineSpacingAdd=" + this.lineSpacingAdd + ", lineSpacingMult=" + this.lineSpacingMult + ", textSize=" + this.textSize + ')';
    }
}

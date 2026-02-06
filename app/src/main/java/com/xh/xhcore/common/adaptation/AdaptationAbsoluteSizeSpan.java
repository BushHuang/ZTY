package com.xh.xhcore.common.adaptation;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class AdaptationAbsoluteSizeSpan extends MetricAffectingSpan implements ParcelableSpan {
    private boolean mDip;
    private final float mSize;

    public AdaptationAbsoluteSizeSpan(float f) {
        this.mSize = f;
        this.mDip = false;
    }

    public AdaptationAbsoluteSizeSpan(float f, boolean z) {
        this.mSize = f;
        this.mDip = z;
    }

    public AdaptationAbsoluteSizeSpan(Parcel parcel) {
        this.mSize = parcel.readInt();
        this.mDip = parcel.readInt() != 0;
    }

    private void updateTextSize(TextPaint textPaint) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            if (this.mDip) {
                textPaint.setTextSize(TextViewAdaptationUtil.getTextSizeInPixel(this.mSize));
                return;
            } else {
                textPaint.setTextSize(this.mSize);
                return;
            }
        }
        if (this.mDip) {
            textPaint.setTextSize(this.mSize * textPaint.density);
        } else {
            textPaint.setTextSize(this.mSize);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean getDip() {
        return this.mDip;
    }

    public float getSize() {
        return this.mSize;
    }

    @Override
    public int getSpanTypeId() {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal() {
        return 16;
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        updateTextSize(textPaint);
    }

    @Override
    public void updateMeasureState(TextPaint textPaint) {
        updateTextSize(textPaint);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, float f) {
        parcel.writeFloat(this.mSize);
        parcel.writeInt(this.mDip ? 1 : 0);
    }
}

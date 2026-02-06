package com.xh.xhcore.common.adaptation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.xh.xhcore.common.adaptation.model.TextViewLayoutParamsMetricModel;

public class TextAdaptationLayoutParams extends FrameLayout.LayoutParams {
    private TextViewLayoutParamsMetricModel oldVersionTextViewLayoutParamsMetricModel;

    public TextAdaptationLayoutParams(int i, int i2) {
        super(i, i2);
        this.oldVersionTextViewLayoutParamsMetricModel = new TextViewLayoutParamsMetricModel();
    }

    public TextAdaptationLayoutParams(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.oldVersionTextViewLayoutParamsMetricModel = new TextViewLayoutParamsMetricModel();
    }

    @Override
    public void setMargins(int i, int i2, int i3, int i4) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            super.setMargins(TextViewAdaptationUtil.getRedundantSpaceLeft(), i2, TextViewAdaptationUtil.getRedundantSpaceRight(), i4);
        } else {
            super.setMargins(this.oldVersionTextViewLayoutParamsMetricModel.getMarginLeft(), this.oldVersionTextViewLayoutParamsMetricModel.getMarginTop(), this.oldVersionTextViewLayoutParamsMetricModel.getMarginRight(), this.oldVersionTextViewLayoutParamsMetricModel.getMarginBottom());
        }
    }

    public TextAdaptationLayoutParams setOldVersionTextViewLayoutParamsMetricModel(TextViewLayoutParamsMetricModel textViewLayoutParamsMetricModel) {
        this.oldVersionTextViewLayoutParamsMetricModel = textViewLayoutParamsMetricModel;
        return this;
    }
}

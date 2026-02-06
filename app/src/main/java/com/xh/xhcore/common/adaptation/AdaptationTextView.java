package com.xh.xhcore.common.adaptation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.xh.logutils.LogLevel;
import com.xh.xhcore.common.adaptation.model.TextViewMetricModel;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.XHLog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdaptationTextView extends AppCompatTextView {
    private static ExecutorService debugLogExecutorService = Executors.newSingleThreadExecutor();
    private boolean hasFixedLineSpacingAccordingToFontHeight;
    private TextViewMetricModel oldVersionTextViewMetricModel;

    public AdaptationTextView(Context context) {
        this(context, null);
    }

    public AdaptationTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AdaptationTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.oldVersionTextViewMetricModel = new TextViewMetricModel();
        this.hasFixedLineSpacingAccordingToFontHeight = false;
        init(context, attributeSet, i);
    }

    private int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, getContext().getResources().getDisplayMetrics());
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            TextViewAdaptationUtil.doTextViewAdaptation(this);
        }
    }

    private boolean isFontHeightOddOnBaseDevice() {
        float fontHeight = TextViewMeasureUtil.getFontHeight(this) / TextViewAdaptationUtil.getAdaptationRadio();
        return fontHeight != ((float) ((int) fontHeight)) || fontHeight % 2.0f == 1.0f;
    }

    private void printDebugAdaptationInfo() {
        try {
            debugLogExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    XHLog.setLogLevel(LogLevel.LOG_LEVEL_VERBOSE);
                    LogUtils.setDebug(false);
                    AdaptationTextView adaptationTextView = AdaptationTextView.this;
                    TextViewMeasureUtil.getWidthMethod1(adaptationTextView, adaptationTextView.getText().toString());
                    AdaptationTextView adaptationTextView2 = AdaptationTextView.this;
                    TextViewMeasureUtil.getTextViewHeight(adaptationTextView2, adaptationTextView2.getText().toString());
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion() && !this.hasFixedLineSpacingAccordingToFontHeight && isFontHeightOddOnBaseDevice()) {
            super.setLineSpacing(getLineSpacingExtra() + (TextViewAdaptationUtil.getAdaptationRadio() * 1.0f), getLineSpacingMultiplier());
            this.hasFixedLineSpacingAccordingToFontHeight = true;
        }
        super.onMeasure(i, i2);
    }

    @Override
    public void setLineSpacing(float f, float f2) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            super.setLineSpacing(TextViewAdaptationUtil.getLineSpacingAdd(f), 1.0f);
        } else {
            super.setLineSpacing(this.oldVersionTextViewMetricModel.getLineSpacingAdd(), this.oldVersionTextViewMetricModel.getLineSpacingMult());
        }
    }

    public AdaptationTextView setOldVersionTextViewMetricModel(TextViewMetricModel textViewMetricModel) {
        this.oldVersionTextViewMetricModel = textViewMetricModel;
        return this;
    }

    @Override
    public void setPadding(int i, int i2, int i3, int i4) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            super.setPadding(TextViewAdaptationUtil.getPadding(i), TextViewAdaptationUtil.getPadding(i2), TextViewAdaptationUtil.getPadding(i3), TextViewAdaptationUtil.getPadding(i4));
        } else {
            super.setPadding(this.oldVersionTextViewMetricModel.getPaddingLeft(), this.oldVersionTextViewMetricModel.getPaddingTop(), this.oldVersionTextViewMetricModel.getPaddingRight(), this.oldVersionTextViewMetricModel.getPaddingBottom());
        }
    }

    @Override
    public void setPaintFlags(int i) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            super.setPaintFlags(TextViewAdaptationUtil.getAdaptationPaintFlags(i));
        } else {
            super.setPaintFlags(i);
        }
    }

    @Override
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
    }

    @Override
    public void setTextSize(int i, float f) {
        if (!TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            super.setTextSize(0, this.oldVersionTextViewMetricModel.getTextSize());
        } else {
            super.setTextSize(0, TextViewAdaptationUtil.transformTextSizeToPx(i, f));
            this.hasFixedLineSpacingAccordingToFontHeight = false;
        }
    }

    @Override
    public void setTypeface(Typeface typeface) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            super.setTypeface(TextViewAdaptationUtil.getFullTypeface());
        } else {
            super.setTypeface(typeface);
        }
    }

    @Override
    public void setTypeface(Typeface typeface, int i) {
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            super.setTypeface(TextViewAdaptationUtil.getFullTypeface());
        } else {
            super.setTypeface(typeface, i);
        }
    }
}

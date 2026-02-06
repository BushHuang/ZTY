package com.xuehai.launcher.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001BB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\u0010\u00100\u001a\u00020\u00142\u0006\u00101\u001a\u00020\fH\u0002J\b\u00102\u001a\u00020$H\u0002J\u0006\u00103\u001a\u00020*J\u000e\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020\bJ\u0006\u00106\u001a\u000207J\u0010\u00108\u001a\u0002072\u0006\u00109\u001a\u00020:H\u0014J\u0018\u0010;\u001a\u0002072\u0006\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u00020\bH\u0014J\u0010\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020@H\u0016J\u000e\u0010A\u001a\u0002072\u0006\u00105\u001a\u00020\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u000e\u0010\u001c\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u0006C"}, d2 = {"Lcom/xuehai/launcher/common/widget/ValueSelectView;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "baseX", "", "baseY", "defaultAlpha", "fontOffsetCenter", "fontOffsetOther", "interval", "mOffset", "needGradual", "", "getNeedGradual", "()Z", "setNeedGradual", "(Z)V", "needLooper", "getNeedLooper", "setNeedLooper", "offsetCount", "onValueChangedListener", "Lcom/xuehai/launcher/common/widget/ValueSelectView$OnValueChangedListener;", "getOnValueChangedListener", "()Lcom/xuehai/launcher/common/widget/ValueSelectView$OnValueChangedListener;", "setOnValueChangedListener", "(Lcom/xuehai/launcher/common/widget/ValueSelectView$OnValueChangedListener;)V", "paintH", "Landroid/graphics/Paint;", "paintL", "selectedIndex", "touchY", "value", "", "", "values", "getValues", "()Ljava/util/List;", "setValues", "(Ljava/util/List;)V", "canMove", "offset", "createPaint", "getCenterValue", "getValue", "index", "next", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "setSelect", "OnValueChangedListener", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ValueSelectView extends AppCompatTextView {
    public Map<Integer, View> _$_findViewCache;
    private float baseX;
    private float baseY;
    private final int defaultAlpha;
    private final float fontOffsetCenter;
    private final float fontOffsetOther;
    private final float interval;
    private float mOffset;
    private boolean needGradual;
    private boolean needLooper;
    private int offsetCount;
    private OnValueChangedListener onValueChangedListener;
    private final Paint paintH;
    private final Paint paintL;
    private int selectedIndex;
    private float touchY;
    private List<String> values;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/widget/ValueSelectView$OnValueChangedListener;", "", "onValueChanged", "", "value", "", "index", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnValueChangedListener {
        void onValueChanged(String value, int index);
    }

    public ValueSelectView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.values = new ArrayList();
        this.offsetCount = 1;
        this.defaultAlpha = 150;
        Paint paintCreatePaint = createPaint();
        paintCreatePaint.setTextSize(46.0f);
        paintCreatePaint.setColor(Color.parseColor("#5B5B5B"));
        this.paintH = paintCreatePaint;
        this.fontOffsetCenter = Math.abs(paintCreatePaint.getFontMetrics().top + this.paintH.getFontMetrics().bottom);
        Paint paintCreatePaint2 = createPaint();
        paintCreatePaint2.setTextSize(30.0f);
        paintCreatePaint2.setARGB(255, 170, 170, 170);
        this.paintL = paintCreatePaint2;
        this.fontOffsetOther = Math.abs(paintCreatePaint2.getFontMetrics().top + this.paintL.getFontMetrics().bottom);
        this.interval = this.fontOffsetCenter + 16;
    }

    public ValueSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        this.values = new ArrayList();
        this.offsetCount = 1;
        this.defaultAlpha = 150;
        Paint paintCreatePaint = createPaint();
        paintCreatePaint.setTextSize(46.0f);
        paintCreatePaint.setColor(Color.parseColor("#5B5B5B"));
        this.paintH = paintCreatePaint;
        this.fontOffsetCenter = Math.abs(paintCreatePaint.getFontMetrics().top + this.paintH.getFontMetrics().bottom);
        Paint paintCreatePaint2 = createPaint();
        paintCreatePaint2.setTextSize(30.0f);
        paintCreatePaint2.setARGB(255, 170, 170, 170);
        this.paintL = paintCreatePaint2;
        this.fontOffsetOther = Math.abs(paintCreatePaint2.getFontMetrics().top + this.paintL.getFontMetrics().bottom);
        this.interval = this.fontOffsetCenter + 16;
    }

    public ValueSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        this.values = new ArrayList();
        this.offsetCount = 1;
        this.defaultAlpha = 150;
        Paint paintCreatePaint = createPaint();
        paintCreatePaint.setTextSize(46.0f);
        paintCreatePaint.setColor(Color.parseColor("#5B5B5B"));
        this.paintH = paintCreatePaint;
        this.fontOffsetCenter = Math.abs(paintCreatePaint.getFontMetrics().top + this.paintH.getFontMetrics().bottom);
        Paint paintCreatePaint2 = createPaint();
        paintCreatePaint2.setTextSize(30.0f);
        paintCreatePaint2.setARGB(255, 170, 170, 170);
        this.paintL = paintCreatePaint2;
        this.fontOffsetOther = Math.abs(paintCreatePaint2.getFontMetrics().top + this.paintL.getFontMetrics().bottom);
        this.interval = this.fontOffsetCenter + 16;
    }

    private final boolean canMove(float offset) {
        if (this.needLooper) {
            if (this.values.size() <= 1) {
                return false;
            }
        } else if (offset > 0.0f) {
            if (this.selectedIndex == 0) {
                return false;
            }
        } else if (offset >= 0.0f || this.selectedIndex == this.values.size() - 1) {
            return false;
        }
        return true;
    }

    private final Paint createPaint() {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(-16777216);
        paint.setDither(true);
        paint.setAntiAlias(true);
        return paint;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public final String getCenterValue() {
        return getValue(this.selectedIndex);
    }

    public final boolean getNeedGradual() {
        return this.needGradual;
    }

    public final boolean getNeedLooper() {
        return this.needLooper;
    }

    public final OnValueChangedListener getOnValueChangedListener() {
        return this.onValueChangedListener;
    }

    public final String getValue(int index) {
        return index < this.values.size() ? this.values.get(index) : "";
    }

    public final List<String> getValues() {
        return this.values;
    }

    public final void next() {
        this.selectedIndex++;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int iMax;
        int iMin;
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (this.selectedIndex >= this.values.size()) {
            return;
        }
        canvas.translate(0.0f, this.mOffset);
        canvas.drawText(this.values.get(this.selectedIndex), this.baseX, this.baseY + (this.fontOffsetCenter / 2), this.paintH);
        if (this.needGradual) {
            this.paintL.setAlpha(this.defaultAlpha);
        }
        float f = this.baseY;
        if (this.needLooper) {
            iMax = this.selectedIndex - Math.min(this.offsetCount, this.values.size());
            iMin = this.selectedIndex + this.offsetCount;
        } else {
            iMax = Math.max(this.selectedIndex - this.offsetCount, 0);
            iMin = Math.min(this.selectedIndex + this.offsetCount, this.values.size());
        }
        int i = this.selectedIndex - 1;
        if (iMax <= i) {
            while (true) {
                if (i >= 0) {
                    str2 = this.values.get(i);
                } else {
                    List<String> list = this.values;
                    str2 = list.get(list.size() + i);
                }
                f -= this.interval;
                if (this.needGradual) {
                    this.paintL.setAlpha(Math.max(0, r7.getAlpha() - 50));
                }
                if (f >= 0.0f) {
                    canvas.drawText(str2, this.baseX, f, this.paintL);
                }
                if (i == iMax) {
                    break;
                } else {
                    i--;
                }
            }
        }
        if (this.needGradual) {
            this.paintL.setAlpha(this.defaultAlpha);
        }
        float f2 = this.baseY;
        int i2 = this.selectedIndex;
        while (true) {
            i2++;
            if (i2 >= iMin) {
                return;
            }
            if (i2 >= this.values.size()) {
                List<String> list2 = this.values;
                str = list2.get(i2 % list2.size());
            } else {
                str = this.values.get(i2);
            }
            f2 += this.interval;
            if (this.needGradual) {
                this.paintL.setAlpha(Math.max(0, r5.getAlpha() - 50));
            }
            if (f2 <= getHeight()) {
                canvas.drawText(str, this.baseX, this.fontOffsetOther + f2, this.paintL);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        this.baseX = size * 0.5f;
        this.baseY = size2 * 0.5f;
        this.offsetCount = 5;
        setMeasuredDimension(size, size2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int action = event.getAction();
        if (action == 0) {
            this.touchY = event.getY();
        } else if (action != 2) {
            this.mOffset = 0.0f;
            invalidate();
        } else {
            float y = event.getY() - this.touchY;
            this.mOffset = y;
            if (canMove(y)) {
                float f = this.mOffset;
                float f2 = this.interval;
                float f3 = 2;
                if (f >= f2 / f3) {
                    this.touchY = event.getY();
                    this.mOffset = 0.0f;
                    int i = this.selectedIndex - 1;
                    this.selectedIndex = i;
                    if (i < 0) {
                        this.selectedIndex = i + this.values.size();
                    }
                    OnValueChangedListener onValueChangedListener = this.onValueChangedListener;
                    if (onValueChangedListener != null) {
                        onValueChangedListener.onValueChanged(this.values.get(this.selectedIndex), this.selectedIndex);
                    }
                } else if (f <= (-f2) / f3) {
                    this.touchY = event.getY();
                    this.mOffset = 0.0f;
                    int i2 = this.selectedIndex + 1;
                    this.selectedIndex = i2;
                    if (i2 >= this.values.size()) {
                        this.selectedIndex %= this.values.size();
                    }
                    OnValueChangedListener onValueChangedListener2 = this.onValueChangedListener;
                    if (onValueChangedListener2 != null) {
                        onValueChangedListener2.onValueChanged(this.values.get(this.selectedIndex), this.selectedIndex);
                    }
                }
                invalidate();
            }
        }
        return true;
    }

    public final void setNeedGradual(boolean z) {
        this.needGradual = z;
    }

    public final void setNeedLooper(boolean z) {
        this.needLooper = z;
    }

    public final void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.onValueChangedListener = onValueChangedListener;
    }

    public final void setSelect(int index) {
        this.selectedIndex = index;
        invalidate();
    }

    public final void setValues(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "value");
        this.values = list;
        this.selectedIndex = 0;
        invalidate();
    }
}

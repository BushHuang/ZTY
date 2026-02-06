package com.xuehai.launcher.guide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.xuehai.launcher.common.cache.ApplicationManager;
import com.zaze.utils.DisplayUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB+\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010H\u001a\u00020\u001dH\u0002J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0014J\u0018\u0010M\u001a\u00020J2\u0006\u0010N\u001a\u00020\t2\u0006\u0010O\u001a\u00020\tH\u0014J\u0016\u0010P\u001a\u00020J2\u000e\u0010Q\u001a\n\u0012\u0004\u0012\u00020@\u0018\u00010RJ\u0018\u0010S\u001a\u00020J2\u0006\u0010T\u001a\u00020\t2\u0006\u0010U\u001a\u00020@H\u0002R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\"\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\"\"\u0004\b)\u0010&R\u001a\u0010*\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\"\"\u0004\b,\u0010&R\u0011\u0010-\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0011\u0010/\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0015R\u000e\u00101\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00102\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0015R\u001a\u00104\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\"\"\u0004\b6\u0010&R\u001a\u00107\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\"\"\u0004\b9\u0010&R\u0011\u0010:\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\"R\u0011\u0010<\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u001fR!\u0010>\u001a\u0012\u0012\u0004\u0012\u00020@0?j\b\u0012\u0004\u0012\u00020@`A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0011\u0010D\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u001fR\u0011\u0010F\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0015¨\u0006V"}, d2 = {"Lcom/xuehai/launcher/guide/StepProgressBar;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "bitmap", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "blueColor", "getBlueColor", "()I", "cachePoint", "", "", "getCachePoint", "()[Ljava/lang/Float;", "[Ljava/lang/Float;", "circlePaint", "Landroid/graphics/Paint;", "getCirclePaint", "()Landroid/graphics/Paint;", "circleRadius", "getCircleRadius", "()F", "cx", "getCx", "setCx", "(F)V", "cy", "getCy", "setCy", "fontOffsetCenter", "getFontOffsetCenter", "setFontOffsetCenter", "fontSize", "getFontSize", "grayColor", "getGrayColor", "itemOffset", "lightBlueColor", "getLightBlueColor", "paddingLeft", "getPaddingLeft", "setPaddingLeft", "paddingRight", "getPaddingRight", "setPaddingRight", "spacing", "getSpacing", "statePaint", "getStatePaint", "stepItemList", "Ljava/util/ArrayList;", "Lcom/xuehai/launcher/guide/StepProgressItem;", "Lkotlin/collections/ArrayList;", "getStepItemList", "()Ljava/util/ArrayList;", "textPaint", "getTextPaint", "whiteColor", "getWhiteColor", "createPaint", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "resetStep", "steps", "", "updatePaintColor", "index", "item", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StepProgressBar extends View {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private Bitmap bitmap;
    private final int blueColor;
    private final Float[] cachePoint;
    private final Paint circlePaint;
    private final float circleRadius;
    private float cx;
    private float cy;
    private float fontOffsetCenter;
    private final float fontSize;
    private final int grayColor;
    private float itemOffset;
    private final int lightBlueColor;
    private float paddingLeft;
    private float paddingRight;
    private final float spacing;
    private final Paint statePaint;
    private final ArrayList<StepProgressItem> stepItemList;
    private final Paint textPaint;
    private final int whiteColor;

    public StepProgressBar(Context context) {
        super(context);
        this.grayColor = Color.parseColor("#CCCCCC");
        this.lightBlueColor = Color.parseColor("#A7C6D6");
        this.blueColor = Color.parseColor("#0B618C");
        this.whiteColor = Color.parseColor("#FFFFFFFF");
        this.stepItemList = new ArrayList<>();
        this.circleRadius = DisplayUtil.pxFromDp$default(16.0f, null, 2, null);
        this.spacing = DisplayUtil.pxFromDp$default(8.0f, null, 2, null);
        this.fontSize = DisplayUtil.pxFromDp$default(18.0f, null, 2, null);
        Paint paintCreatePaint = createPaint();
        paintCreatePaint.setTextSize(this.fontSize);
        paintCreatePaint.setTextAlign(Paint.Align.CENTER);
        this.statePaint = paintCreatePaint;
        Paint paintCreatePaint2 = createPaint();
        paintCreatePaint2.setTextSize(this.fontSize);
        paintCreatePaint2.setTextAlign(Paint.Align.CENTER);
        this.textPaint = paintCreatePaint2;
        Paint paintCreatePaint3 = createPaint();
        paintCreatePaint3.setStyle(Paint.Style.STROKE);
        paintCreatePaint3.setStrokeWidth(DisplayUtil.pxFromDp$default(2.0f, null, 2, null));
        paintCreatePaint3.setColor(Color.parseColor("#CCCCCC"));
        this.circlePaint = paintCreatePaint3;
        Float fValueOf = Float.valueOf(0.0f);
        this.cachePoint = new Float[]{fValueOf, fValueOf};
    }

    public StepProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.grayColor = Color.parseColor("#CCCCCC");
        this.lightBlueColor = Color.parseColor("#A7C6D6");
        this.blueColor = Color.parseColor("#0B618C");
        this.whiteColor = Color.parseColor("#FFFFFFFF");
        this.stepItemList = new ArrayList<>();
        this.circleRadius = DisplayUtil.pxFromDp$default(16.0f, null, 2, null);
        this.spacing = DisplayUtil.pxFromDp$default(8.0f, null, 2, null);
        this.fontSize = DisplayUtil.pxFromDp$default(18.0f, null, 2, null);
        Paint paintCreatePaint = createPaint();
        paintCreatePaint.setTextSize(this.fontSize);
        paintCreatePaint.setTextAlign(Paint.Align.CENTER);
        this.statePaint = paintCreatePaint;
        Paint paintCreatePaint2 = createPaint();
        paintCreatePaint2.setTextSize(this.fontSize);
        paintCreatePaint2.setTextAlign(Paint.Align.CENTER);
        this.textPaint = paintCreatePaint2;
        Paint paintCreatePaint3 = createPaint();
        paintCreatePaint3.setStyle(Paint.Style.STROKE);
        paintCreatePaint3.setStrokeWidth(DisplayUtil.pxFromDp$default(2.0f, null, 2, null));
        paintCreatePaint3.setColor(Color.parseColor("#CCCCCC"));
        this.circlePaint = paintCreatePaint3;
        Float fValueOf = Float.valueOf(0.0f);
        this.cachePoint = new Float[]{fValueOf, fValueOf};
    }

    public StepProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.grayColor = Color.parseColor("#CCCCCC");
        this.lightBlueColor = Color.parseColor("#A7C6D6");
        this.blueColor = Color.parseColor("#0B618C");
        this.whiteColor = Color.parseColor("#FFFFFFFF");
        this.stepItemList = new ArrayList<>();
        this.circleRadius = DisplayUtil.pxFromDp$default(16.0f, null, 2, null);
        this.spacing = DisplayUtil.pxFromDp$default(8.0f, null, 2, null);
        this.fontSize = DisplayUtil.pxFromDp$default(18.0f, null, 2, null);
        Paint paintCreatePaint = createPaint();
        paintCreatePaint.setTextSize(this.fontSize);
        paintCreatePaint.setTextAlign(Paint.Align.CENTER);
        this.statePaint = paintCreatePaint;
        Paint paintCreatePaint2 = createPaint();
        paintCreatePaint2.setTextSize(this.fontSize);
        paintCreatePaint2.setTextAlign(Paint.Align.CENTER);
        this.textPaint = paintCreatePaint2;
        Paint paintCreatePaint3 = createPaint();
        paintCreatePaint3.setStyle(Paint.Style.STROKE);
        paintCreatePaint3.setStrokeWidth(DisplayUtil.pxFromDp$default(2.0f, null, 2, null));
        paintCreatePaint3.setColor(Color.parseColor("#CCCCCC"));
        this.circlePaint = paintCreatePaint3;
        Float fValueOf = Float.valueOf(0.0f);
        this.cachePoint = new Float[]{fValueOf, fValueOf};
    }

    public StepProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.grayColor = Color.parseColor("#CCCCCC");
        this.lightBlueColor = Color.parseColor("#A7C6D6");
        this.blueColor = Color.parseColor("#0B618C");
        this.whiteColor = Color.parseColor("#FFFFFFFF");
        this.stepItemList = new ArrayList<>();
        this.circleRadius = DisplayUtil.pxFromDp$default(16.0f, null, 2, null);
        this.spacing = DisplayUtil.pxFromDp$default(8.0f, null, 2, null);
        this.fontSize = DisplayUtil.pxFromDp$default(18.0f, null, 2, null);
        Paint paintCreatePaint = createPaint();
        paintCreatePaint.setTextSize(this.fontSize);
        paintCreatePaint.setTextAlign(Paint.Align.CENTER);
        this.statePaint = paintCreatePaint;
        Paint paintCreatePaint2 = createPaint();
        paintCreatePaint2.setTextSize(this.fontSize);
        paintCreatePaint2.setTextAlign(Paint.Align.CENTER);
        this.textPaint = paintCreatePaint2;
        Paint paintCreatePaint3 = createPaint();
        paintCreatePaint3.setStyle(Paint.Style.STROKE);
        paintCreatePaint3.setStrokeWidth(DisplayUtil.pxFromDp$default(2.0f, null, 2, null));
        paintCreatePaint3.setColor(Color.parseColor("#CCCCCC"));
        this.circlePaint = paintCreatePaint3;
        Float fValueOf = Float.valueOf(0.0f);
        this.cachePoint = new Float[]{fValueOf, fValueOf};
    }

    private final Paint createPaint() {
        Paint paint = new Paint();
        paint.setColor(-16777216);
        paint.setDither(true);
        paint.setAntiAlias(true);
        return paint;
    }

    private final void updatePaintColor(int index, StepProgressItem item) {
        if (index == 0) {
            this.textPaint.setTextAlign(Paint.Align.LEFT);
        } else if (index == this.stepItemList.size() - 1) {
            this.textPaint.setTextAlign(Paint.Align.RIGHT);
        } else {
            this.textPaint.setTextAlign(Paint.Align.CENTER);
        }
        int state = item.getState();
        if (state == 0) {
            this.textPaint.setColor(this.grayColor);
            Paint paint = this.circlePaint;
            paint.setColor(this.grayColor);
            paint.setStyle(Paint.Style.STROKE);
            this.statePaint.setColor(this.grayColor);
            return;
        }
        if (state == 1) {
            this.textPaint.setColor(this.blueColor);
            Paint paint2 = this.circlePaint;
            paint2.setColor(this.lightBlueColor);
            paint2.setStyle(Paint.Style.FILL);
            this.statePaint.setColor(this.whiteColor);
            return;
        }
        if (state != 2) {
            this.textPaint.setColor(this.grayColor);
            this.circlePaint.setColor(this.grayColor);
            this.statePaint.setColor(this.grayColor);
        } else {
            this.textPaint.setColor(this.blueColor);
            Paint paint3 = this.circlePaint;
            paint3.setColor(this.lightBlueColor);
            paint3.setStyle(Paint.Style.FILL);
            this.statePaint.setColor(this.whiteColor);
        }
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

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final int getBlueColor() {
        return this.blueColor;
    }

    public final Float[] getCachePoint() {
        return this.cachePoint;
    }

    public final Paint getCirclePaint() {
        return this.circlePaint;
    }

    public final float getCircleRadius() {
        return this.circleRadius;
    }

    public final float getCx() {
        return this.cx;
    }

    public final float getCy() {
        return this.cy;
    }

    public final float getFontOffsetCenter() {
        return this.fontOffsetCenter;
    }

    public final float getFontSize() {
        return this.fontSize;
    }

    public final int getGrayColor() {
        return this.grayColor;
    }

    public final int getLightBlueColor() {
        return this.lightBlueColor;
    }

    @Override
    public final float getPaddingLeft() {
        return this.paddingLeft;
    }

    @Override
    public final float getPaddingRight() {
        return this.paddingRight;
    }

    public final float getSpacing() {
        return this.spacing;
    }

    public final Paint getStatePaint() {
        return this.statePaint;
    }

    public final ArrayList<StepProgressItem> getStepItemList() {
        return this.stepItemList;
    }

    public final Paint getTextPaint() {
        return this.textPaint;
    }

    public final int getWhiteColor() {
        return this.whiteColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (this.stepItemList.isEmpty()) {
            return;
        }
        int i = 0;
        for (Object obj : this.stepItemList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            StepProgressItem stepProgressItem = (StepProgressItem) obj;
            updatePaintColor(i, stepProgressItem);
            float f = i;
            float f2 = 2;
            canvas.drawText(stepProgressItem.getName(), this.itemOffset * f, ((this.circleRadius + this.circlePaint.getStrokeWidth()) * f2) + this.spacing + this.fontOffsetCenter, this.textPaint);
            this.cx = i == 0 ? this.paddingLeft + this.circleRadius + (f * this.itemOffset) : i == this.stepItemList.size() - 1 ? ((f * this.itemOffset) - this.paddingRight) - this.circleRadius : this.itemOffset * f;
            this.cy = this.circleRadius + (this.circlePaint.getStrokeWidth() / 2.0f);
            if (i > 0) {
                canvas.drawLine(this.circleRadius + this.cachePoint[0].floatValue(), this.cachePoint[1].floatValue(), this.cx - this.circleRadius, this.cy, this.circlePaint);
            }
            this.cachePoint[0] = Float.valueOf(this.cx);
            this.cachePoint[1] = Float.valueOf(this.cy);
            canvas.drawCircle(this.cx, this.cy, this.circleRadius, this.circlePaint);
            if (2 == stepProgressItem.getState()) {
                if (this.bitmap == null) {
                    this.bitmap = ApplicationManager.INSTANCE.makeDefaultIcon(getResources().getDrawable(2131230834), (int) (this.circleRadius * f2));
                }
                Bitmap bitmap = this.bitmap;
                if (bitmap != null) {
                    float f3 = this.cx;
                    float f4 = this.circleRadius;
                    canvas.drawBitmap(bitmap, f3 - f4, this.cy - f4, (Paint) null);
                }
            } else {
                canvas.drawText(String.valueOf(i2), this.cx, this.cy + this.fontOffsetCenter, this.statePaint);
            }
            i = i2;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        double d = this.spacing;
        double strokeWidth = this.circleRadius + this.circlePaint.getStrokeWidth();
        Double.isNaN(strokeWidth);
        Double.isNaN(d);
        double d2 = d + (strokeWidth * 2.0d);
        double d3 = this.textPaint.getFontMetrics().bottom - this.textPaint.getFontMetrics().top;
        Double.isNaN(d3);
        int iCeil = (int) Math.ceil(d2 + d3);
        if (!this.stepItemList.isEmpty()) {
            float f = 2;
            this.paddingLeft = RangesKt.coerceAtLeast((this.textPaint.measureText(((StepProgressItem) CollectionsKt.first((List) this.stepItemList)).getName()) / f) - this.circleRadius, 0.0f);
            this.paddingRight = RangesKt.coerceAtLeast((this.textPaint.measureText(((StepProgressItem) CollectionsKt.last((List) this.stepItemList)).getName()) / f) - this.circleRadius, 0.0f);
            this.itemOffset = (size * 1.0f) / RangesKt.coerceAtLeast(this.stepItemList.size() - 1, 1);
        }
        this.fontOffsetCenter = (-(this.textPaint.getFontMetrics().top + this.textPaint.getFontMetrics().bottom)) / 2;
        setMeasuredDimension(size, iCeil);
    }

    public final void resetStep(List<StepProgressItem> steps) {
        this.stepItemList.clear();
        if (steps != null) {
            this.stepItemList.addAll(steps);
        }
        requestLayout();
    }

    public final void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public final void setCx(float f) {
        this.cx = f;
    }

    public final void setCy(float f) {
        this.cy = f;
    }

    public final void setFontOffsetCenter(float f) {
        this.fontOffsetCenter = f;
    }

    public final void setPaddingLeft(float f) {
        this.paddingLeft = f;
    }

    public final void setPaddingRight(float f) {
        this.paddingRight = f;
    }
}

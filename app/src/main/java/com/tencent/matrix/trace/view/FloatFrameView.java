package com.tencent.matrix.trace.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.matrix.trace.R;
import java.util.Iterator;
import java.util.LinkedList;

public class FloatFrameView extends LinearLayout {
    public LineChartView chartView;
    public TextView fpsView;

    public static class LineChartView extends View {
        private static final int LINE_COUNT = 75;
        private int grayColor;
        private int greenColor;
        float height;
        private final Paint levelLinePaint;
        float lineContentWidth;
        float linePadding;
        float lineStrokeWidth;
        private final LinkedList<LineInfo> lines;
        private Path middlePath;
        private float[] middleTip;
        private int orangeColor;
        float padding;
        private final Paint paint;
        private int redColor;
        float textSize;
        private final Paint tipLinePaint;
        private final TextPaint tipPaint;
        private Path topPath;
        private float[] topTip;
        float width;

        class LineInfo {
            int color;
            int fps;
            private float[] linePoint = new float[4];

            LineInfo(int i) {
                this.fps = i;
                this.color = LineChartView.this.getColor(i);
                this.linePoint[0] = LineChartView.this.width;
                this.linePoint[2] = (((60 - i) * LineChartView.this.lineContentWidth) / 60.0f) + LineChartView.this.padding;
            }

            void draw(Canvas canvas, int i) {
                if (LineChartView.this.paint.getColor() != this.color) {
                    LineChartView.this.paint.setColor(this.color);
                }
                this.linePoint[1] = (i + 1) * LineChartView.this.linePadding;
                float[] fArr = this.linePoint;
                fArr[3] = fArr[1];
                canvas.drawLine(fArr[0], fArr[1], fArr[2], fArr[3], LineChartView.this.paint);
            }
        }

        public LineChartView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public LineChartView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.topPath = new Path();
            this.topTip = new float[2];
            this.middlePath = new Path();
            this.middleTip = new float[2];
            this.greenColor = getContext().getResources().getColor(17170453);
            this.orangeColor = getContext().getResources().getColor(17170457);
            this.redColor = getContext().getResources().getColor(17170455);
            this.grayColor = getContext().getResources().getColor(R.color.dark_text);
            this.padding = getContext().getResources().getDisplayMetrics().density * 10.0f;
            this.paint = new Paint();
            TextPaint textPaint = new TextPaint();
            this.tipPaint = textPaint;
            float f = getContext().getResources().getDisplayMetrics().density * 8.0f;
            this.textSize = f;
            textPaint.setTextSize(f);
            this.tipPaint.setStrokeWidth(3.0f);
            this.tipPaint.setColor(this.grayColor);
            TextPaint textPaint2 = new TextPaint();
            this.levelLinePaint = textPaint2;
            textPaint2.setStrokeWidth(2.0f);
            this.levelLinePaint.setStyle(Paint.Style.STROKE);
            Paint paint = new Paint(this.tipPaint);
            this.tipLinePaint = paint;
            paint.setStrokeWidth(2.0f);
            this.tipLinePaint.setColor(this.grayColor);
            this.tipLinePaint.setStyle(Paint.Style.STROKE);
            this.tipLinePaint.setPathEffect(new DashPathEffect(new float[]{6.0f, 6.0f}, 0.0f));
            this.lines = new LinkedList<>();
        }

        private int getColor(int i) {
            return i >= 50 ? this.greenColor : i >= 30 ? this.orangeColor : this.redColor;
        }

        public void addFps(int i) {
            LineInfo lineInfo = new LineInfo(i);
            synchronized (this.lines) {
                if (this.lines.size() >= 75) {
                    this.lines.removeLast();
                }
                this.lines.addFirst(lineInfo);
            }
            postInvalidate();
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            synchronized (this.lines) {
                Iterator<LineInfo> it = this.lines.iterator();
                int i = 0;
                int i2 = 1;
                while (it.hasNext()) {
                    LineInfo next = it.next();
                    i += next.fps;
                    next.draw(canvas, i2);
                    if (i2 % 25 == 0 || i2 == 0) {
                        Path path = new Path();
                        float f = next.linePoint[1];
                        path.moveTo(0.0f, f);
                        path.lineTo(getMeasuredHeight(), f);
                        canvas.drawPath(path, this.tipLinePaint);
                        this.tipPaint.setColor(this.grayColor);
                        canvas.drawText((i2 / 5) + "s", 0.0f, this.textSize + f, this.tipPaint);
                        if (i2 > 0) {
                            int i3 = i / i2;
                            this.tipPaint.setColor(getColor(i3));
                            canvas.drawText(i3 + " FPS", 0.0f, f - (this.textSize / 2.0f), this.tipPaint);
                        }
                    }
                    i2++;
                }
            }
            this.tipPaint.setColor(this.grayColor);
            this.levelLinePaint.setColor(this.greenColor);
            canvas.drawPath(this.topPath, this.levelLinePaint);
            float[] fArr = this.topTip;
            canvas.drawText("50", fArr[0] - (this.textSize / 2.0f), fArr[1], this.tipPaint);
            this.levelLinePaint.setColor(this.orangeColor);
            canvas.drawPath(this.middlePath, this.levelLinePaint);
            float[] fArr2 = this.middleTip;
            canvas.drawText("30  FPS", fArr2[0] - (this.textSize / 2.0f), fArr2[1], this.tipPaint);
        }

        @Override
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (z) {
                this.width = getMeasuredWidth();
                float measuredHeight = getMeasuredHeight();
                this.height = measuredHeight;
                float f = this.width;
                float f2 = this.padding;
                this.lineContentWidth = f - f2;
                float f3 = (measuredHeight - (f2 * 2.0f)) / 150.0f;
                this.lineStrokeWidth = f3;
                this.paint.setStrokeWidth(f3);
                this.linePadding = this.lineStrokeWidth * 2.0f;
                float f4 = this.lineContentWidth / 60.0f;
                float[] fArr = this.topTip;
                fArr[0] = (10.0f * f4) + this.padding;
                float f5 = this.height;
                fArr[1] = f5;
                this.topPath.moveTo(fArr[0], f5 - this.textSize);
                this.topPath.lineTo(this.topTip[0], 0.0f);
                float[] fArr2 = this.middleTip;
                fArr2[0] = (f4 * 30.0f) + this.padding;
                float f6 = this.height;
                fArr2[1] = f6;
                this.middlePath.moveTo(fArr2[0], f6 - this.textSize);
                this.middlePath.lineTo(this.middleTip[0], 0.0f);
            }
        }
    }

    public FloatFrameView(Context context) {
        super(context);
        initView(context);
    }

    public FloatFrameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        LayoutInflater.from(context).inflate(R.layout.float_frame_view, this);
        this.fpsView = (TextView) findViewById(R.id.fps_view);
        this.chartView = (LineChartView) findViewById(R.id.chart);
    }
}

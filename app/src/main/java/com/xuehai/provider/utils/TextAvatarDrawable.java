package com.xuehai.provider.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class TextAvatarDrawable extends Drawable {
    private long mId;
    private final Paint mPaint = new Paint(1);
    private String mText;

    public TextAvatarDrawable(String str, long j) {
        this.mText = CPVDAvatar.subLast2(str);
        this.mId = j;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        float fMin = Math.min(bounds.exactCenterX(), bounds.exactCenterY());
        this.mPaint.setColor(CPVDAvatar.COLOR_ARRAY[(int) (this.mId % 4)]);
        canvas.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), fMin, this.mPaint);
        this.mPaint.setColor(-1);
        this.mPaint.setTextSize(fMin * 0.74f);
        Rect rect = new Rect();
        Paint paint = this.mPaint;
        String str = this.mText;
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(this.mText, (0 - rect.left) + ((bounds.width() - rect.width()) / 2), (0 - rect.top) + ((bounds.height() - rect.height()) / 2), this.mPaint);
    }

    @Override
    public int getOpacity() {
        return -3;
    }

    @Override
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
        invalidateSelf();
    }

    public void setAvatarData(String str, long j) {
        this.mText = CPVDAvatar.subLast2(str);
        this.mId = j;
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }
}

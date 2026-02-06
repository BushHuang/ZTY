package com.zaze.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.media.ThumbnailUtils;
import android.view.View;
import com.zaze.utils.log.ZLog;
import java.io.ByteArrayOutputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007J\u001c\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J \u0010\r\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0007J\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\"\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u001d"}, d2 = {"Lcom/zaze/utils/BmpUtil;", "", "()V", "bitmap2Bytes", "", "bitmap", "Landroid/graphics/Bitmap;", "format", "Landroid/graphics/Bitmap$CompressFormat;", "bitmap2Drawable", "Landroid/graphics/drawable/Drawable;", "res", "Landroid/content/res/Resources;", "bytes2Bitmap", "bytes", "options", "Landroid/graphics/BitmapFactory$Options;", "bytes2Drawable", "drawable2Bitmap", "drawable", "bmpSize", "", "drawable2Bytes", "getViewBitmap", "width", "height", "view", "Landroid/view/View;", "toRoundBitmap", "util_release"}, k = 1, mv = {1, 4, 1})
public final class BmpUtil {
    public static final BmpUtil INSTANCE = new BmpUtil();

    private BmpUtil() {
    }

    @JvmStatic
    public static final byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(format, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] bitmap2Bytes$default(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            compressFormat = Bitmap.CompressFormat.PNG;
        }
        return bitmap2Bytes(bitmap, compressFormat);
    }

    @JvmStatic
    public static final Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(res, "res");
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(res, bitmap);
    }

    @JvmStatic
    public static final Bitmap bytes2Bitmap(byte[] bytes, BitmapFactory.Options options) {
        if (bytes != null) {
            if (!(bytes.length == 0)) {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            }
        }
        return null;
    }

    public static Bitmap bytes2Bitmap$default(byte[] bArr, BitmapFactory.Options options, int i, Object obj) {
        if ((i & 2) != 0) {
            options = (BitmapFactory.Options) null;
        }
        return bytes2Bitmap(bArr, options);
    }

    @JvmStatic
    public static final Drawable bytes2Drawable(Resources res, byte[] bytes) {
        Intrinsics.checkNotNullParameter(res, "res");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return bitmap2Drawable(res, bytes2Bitmap$default(bytes, null, 2, null));
    }

    @JvmStatic
    public static final Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            if (bitmap.getDensity() == 0) {
                bitmapDrawable.setTargetDensity(DisplayUtil.getMetrics());
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 1), Math.max(drawable.getIntrinsicHeight(), 1), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "bitmap");
        drawable.setBounds(0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
        drawable.draw(canvas);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    @JvmStatic
    public static final Bitmap drawable2Bitmap(Drawable drawable, int bmpSize) {
        int i;
        int i2;
        if (drawable == null) {
            return null;
        }
        if (bmpSize > 0 && bmpSize > 0) {
            if (drawable instanceof PaintDrawable) {
                PaintDrawable paintDrawable = (PaintDrawable) drawable;
                paintDrawable.setIntrinsicWidth(bmpSize);
                paintDrawable.setIntrinsicHeight(bmpSize);
            } else if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                if (bitmap.getDensity() == 0) {
                    bitmapDrawable.setTargetDensity(DisplayUtil.getMetrics());
                }
            }
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            i = bmpSize;
            i2 = i;
        } else {
            float f = (intrinsicWidth * 1.0f) / intrinsicHeight;
            if (intrinsicWidth > intrinsicHeight) {
                i2 = (int) (bmpSize / f);
                i = bmpSize;
            } else if (intrinsicHeight > intrinsicWidth) {
                i = (int) (bmpSize * f);
                i2 = bmpSize;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bmpSize, bmpSize, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        int i3 = (bmpSize - i) / 2;
        int i4 = (bmpSize - i2) / 2;
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(i3, i4, i + i3, i2 + i4);
        drawable.draw(canvas);
        drawable.setBounds(new Rect(drawable.getBounds()));
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    @JvmStatic
    public static final byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable), format);
    }

    @JvmStatic
    public static final Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        ZLog.i("Debug[ZZ]", "bmp: " + bitmap.getWidth() + 'x' + bitmap.getHeight() + " >> " + iMin + 'x' + iMin + ' ', new Object[0]);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint(1);
        float f = ((float) iMin) / 2.0f;
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(f, f, f, paint);
        paint.reset();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, new Matrix(), paint);
        return bitmapCreateBitmap;
    }

    public final Bitmap getViewBitmap(int width, int height, View view) {
        Bitmap bitmapExtractThumbnail = (Bitmap) null;
        if (view != null) {
            boolean zIsDrawingCacheEnabled = view.isDrawingCacheEnabled();
            if (!zIsDrawingCacheEnabled) {
                view.setDrawingCacheEnabled(true);
            }
            try {
                try {
                    if (view.getDrawingCache() != null) {
                        bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(view.getDrawingCache(), width, height);
                    }
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    if (!zIsDrawingCacheEnabled) {
                    }
                }
            } finally {
                if (!zIsDrawingCacheEnabled) {
                    view.setDrawingCacheEnabled(false);
                    view.destroyDrawingCache();
                }
            }
        }
        return bitmapExtractThumbnail == null ? Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565) : bitmapExtractThumbnail;
    }
}

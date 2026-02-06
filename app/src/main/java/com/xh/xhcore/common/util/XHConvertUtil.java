package com.xh.xhcore.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.xh.xhcore.common.util.XHConstUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class XHConvertUtil {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static class AnonymousClass1 {
        static final int[] $SwitchMap$com$xh$xhcore$common$util$XHConstUtil$MemoryUnit;

        static {
            int[] iArr = new int[XHConstUtil.MemoryUnit.values().length];
            $SwitchMap$com$xh$xhcore$common$util$XHConstUtil$MemoryUnit = iArr;
            try {
                iArr[XHConstUtil.MemoryUnit.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$xh$xhcore$common$util$XHConstUtil$MemoryUnit[XHConstUtil.MemoryUnit.KB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$xh$xhcore$common$util$XHConstUtil$MemoryUnit[XHConstUtil.MemoryUnit.MB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$xh$xhcore$common$util$XHConstUtil$MemoryUnit[XHConstUtil.MemoryUnit.GB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private XHConvertUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Drawable bitmap2Drawable(Resources resources, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(resources, bitmap);
    }

    public static byte[] bits2Bytes(String str) {
        int length = str.length() % 8;
        int length2 = str.length() / 8;
        if (length != 0) {
            while (length < 8) {
                str = "0" + str;
                length++;
            }
            length2++;
        }
        byte[] bArr = new byte[length2];
        for (int i = 0; i < length2; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i] = (byte) (bArr[i] << 1);
                bArr[i] = (byte) (bArr[i] | (str.charAt((i * 8) + i2) - '0'));
            }
        }
        return bArr;
    }

    public static String byte2FitMemorySize(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < 1024) {
            double d = j;
            Double.isNaN(d);
            return String.format("%.3fB", Double.valueOf(d + 5.0E-4d));
        }
        if (j < 1048576) {
            double d2 = j / 1024;
            Double.isNaN(d2);
            return String.format("%.3fKB", Double.valueOf(d2 + 5.0E-4d));
        }
        if (j < 1073741824) {
            double d3 = j / 1048576;
            Double.isNaN(d3);
            return String.format("%.3fMB", Double.valueOf(d3 + 5.0E-4d));
        }
        double d4 = j / 1073741824;
        Double.isNaN(d4);
        return String.format("%.3fGB", Double.valueOf(d4 + 5.0E-4d));
    }

    public static double byte2MemorySize(long j, XHConstUtil.MemoryUnit memoryUnit) {
        double d;
        double d2;
        if (j < 0) {
            return -1.0d;
        }
        int i = AnonymousClass1.$SwitchMap$com$xh$xhcore$common$util$XHConstUtil$MemoryUnit[memoryUnit.ordinal()];
        if (i == 2) {
            d = j;
            d2 = 1024.0d;
            Double.isNaN(d);
        } else if (i == 3) {
            d = j;
            d2 = 1048576.0d;
            Double.isNaN(d);
        } else {
            if (i != 4) {
                return j;
            }
            d = j;
            d2 = 1.073741824E9d;
            Double.isNaN(d);
        }
        return d / d2;
    }

    public static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static String bytes2Bits(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            for (int i = 7; i >= 0; i--) {
                sb.append(((b >> i) & 1) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    public static char[] bytes2Chars(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static Drawable bytes2Drawable(Resources resources, byte[] bArr) {
        if (resources == null) {
            return null;
        }
        return bitmap2Drawable(resources, bytes2Bitmap(bArr));
    }

    public static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static InputStream bytes2InputStream(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bArr);
    }

    public static OutputStream bytes2OutputStream(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException e) {
            e = e;
            byteArrayOutputStream = null;
        } catch (Throwable th) {
            th = th;
            XHCloseUtil.closeIO(byteArrayOutputStream2);
            throw th;
        }
        try {
            try {
                byteArrayOutputStream.write(bArr);
                XHCloseUtil.closeIO(byteArrayOutputStream);
                return byteArrayOutputStream;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                XHCloseUtil.closeIO(byteArrayOutputStream2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            XHCloseUtil.closeIO(byteArrayOutputStream);
            return null;
        }
    }

    public static byte[] chars2Bytes(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return null;
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        try {
            return ((BitmapDrawable) drawable).getBitmap();
        } catch (Throwable th) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            th.printStackTrace();
            return bitmapCreateBitmap;
        }
    }

    public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable), compressFormat);
    }

    private static int hex2Dec(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c < 'A' || c > 'F') {
            throw new IllegalArgumentException();
        }
        return (c - 'A') + 10;
    }

    public static byte[] hexString2Bytes(String str) {
        if (XHStringUtil.isSpace(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[length >> 1];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((hex2Dec(charArray[i]) << 4) | hex2Dec(charArray[i + 1]));
        }
        return bArr;
    }

    public static ByteArrayOutputStream input2OutputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStream.read(bArr, 0, 1024);
                    if (i == -1) {
                        XHCloseUtil.closeIO(inputStream);
                        return byteArrayOutputStream;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                }
            } catch (IOException e) {
                e.printStackTrace();
                XHCloseUtil.closeIO(inputStream);
                return null;
            }
        } catch (Throwable th) {
            XHCloseUtil.closeIO(inputStream);
            throw th;
        }
    }

    public static byte[] inputStream2Bytes(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return input2OutputStream(inputStream).toByteArray();
    }

    public static String inputStream2String(InputStream inputStream, String str) {
        if (inputStream != null && !XHStringUtil.isSpace(str)) {
            try {
                return new String(inputStream2Bytes(inputStream), str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static long memorySize2Byte(long j, XHConstUtil.MemoryUnit memoryUnit) {
        long j2;
        if (j < 0) {
            return -1L;
        }
        int i = AnonymousClass1.$SwitchMap$com$xh$xhcore$common$util$XHConstUtil$MemoryUnit[memoryUnit.ordinal()];
        if (i == 2) {
            j2 = 1024;
        } else if (i == 3) {
            j2 = 1048576;
        } else {
            if (i != 4) {
                return j;
            }
            j2 = 1073741824;
        }
        return j * j2;
    }

    public static byte[] outputStream2Bytes(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return ((ByteArrayOutputStream) outputStream).toByteArray();
    }

    public static String outputStream2String(OutputStream outputStream, String str) {
        if (outputStream != null && !XHStringUtil.isSpace(str)) {
            try {
                return new String(outputStream2Bytes(outputStream), str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static InputStream string2InputStream(String str, String str2) {
        if (str != null && !XHStringUtil.isSpace(str2)) {
            try {
                return new ByteArrayInputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static OutputStream string2OutputStream(String str, String str2) {
        if (str != null && !XHStringUtil.isSpace(str2)) {
            try {
                return bytes2OutputStream(str.getBytes(str2));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return bitmapCreateBitmap;
    }

    public ByteArrayInputStream output2InputStream(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
    }
}

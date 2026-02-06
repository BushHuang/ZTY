package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;

public final class JsonStringEncoder {
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected ByteArrayBuilder _bytes;
    protected final char[] _qbuf = {'\\', 0, '0', '0', 0, 0};
    protected TextBuffer _text;
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HB[i4 >> 4]);
                byteArrayBuilder.append(HB[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HB[i >> 4]);
            byteArrayBuilder.append(HB[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private static int _convert(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + 65536 + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }

    @Deprecated
    public static JsonStringEncoder getInstance() {
        return BufferRecyclers.getJsonStringEncoder();
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        int i2;
        ByteArrayBuilder byteArrayBuilder = this._bytes;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._bytes = byteArrayBuilder;
        }
        int length = str.length();
        byte[] bArrResetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = bArrResetAndGetFirstSegment.length;
        int i3 = 0;
        int i4 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int i5 = i3 + 1;
            int iCharAt = str.charAt(i3);
            while (iCharAt <= 127) {
                if (i4 >= length2) {
                    bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArrResetAndGetFirstSegment.length;
                    i4 = 0;
                }
                int i6 = i4 + 1;
                bArrResetAndGetFirstSegment[i4] = (byte) iCharAt;
                if (i5 >= length) {
                    i4 = i6;
                    break loop0;
                }
                int iCharAt2 = str.charAt(i5);
                i5++;
                iCharAt = iCharAt2;
                i4 = i6;
            }
            if (i4 >= length2) {
                bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                length2 = bArrResetAndGetFirstSegment.length;
                i4 = 0;
            }
            if (iCharAt < 2048) {
                i = i4 + 1;
                bArrResetAndGetFirstSegment[i4] = (byte) ((iCharAt >> 6) | 192);
            } else if (iCharAt < 55296 || iCharAt > 57343) {
                int i7 = i4 + 1;
                bArrResetAndGetFirstSegment[i4] = (byte) ((iCharAt >> 12) | 224);
                if (i7 >= length2) {
                    bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArrResetAndGetFirstSegment.length;
                    i7 = 0;
                }
                bArrResetAndGetFirstSegment[i7] = (byte) (((iCharAt >> 6) & 63) | 128);
                i = i7 + 1;
            } else {
                if (iCharAt > 56319) {
                    _illegal(iCharAt);
                }
                if (i5 >= length) {
                    _illegal(iCharAt);
                }
                int i8 = i5 + 1;
                int i_convert = _convert(iCharAt, str.charAt(i5));
                if (i_convert > 1114111) {
                    _illegal(i_convert);
                }
                int i9 = i4 + 1;
                bArrResetAndGetFirstSegment[i4] = (byte) ((i_convert >> 18) | 240);
                if (i9 >= length2) {
                    bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArrResetAndGetFirstSegment.length;
                    i9 = 0;
                }
                int i10 = i9 + 1;
                bArrResetAndGetFirstSegment[i9] = (byte) (((i_convert >> 12) & 63) | 128);
                if (i10 >= length2) {
                    bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArrResetAndGetFirstSegment.length;
                    i10 = 0;
                }
                int i11 = i10 + 1;
                bArrResetAndGetFirstSegment[i10] = (byte) (((i_convert >> 6) & 63) | 128);
                i2 = i_convert;
                i3 = i8;
                i = i11;
                if (i < length2) {
                    bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArrResetAndGetFirstSegment.length;
                    i = 0;
                }
                bArrResetAndGetFirstSegment[i] = (byte) ((i2 & 63) | 128);
                i4 = i + 1;
            }
            i2 = iCharAt;
            i3 = i5;
            if (i < length2) {
            }
            bArrResetAndGetFirstSegment[i] = (byte) ((i2 & 63) | 128);
            i4 = i + 1;
        }
        return this._bytes.completeAndCoalesce(i4);
    }

    public void quoteAsString(CharSequence charSequence, StringBuilder sb) {
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = charSequence.length();
        int i = 0;
        while (i < length2) {
            do {
                char cCharAt = charSequence.charAt(i);
                if (cCharAt >= length || iArr[cCharAt] == 0) {
                    sb.append(cCharAt);
                    i++;
                } else {
                    int i2 = i + 1;
                    char cCharAt2 = charSequence.charAt(i);
                    int i3 = iArr[cCharAt2];
                    sb.append(this._qbuf, 0, i3 < 0 ? _appendNumeric(cCharAt2, this._qbuf) : _appendNamed(i3, this._qbuf));
                    i = i2;
                }
            } while (i < length2);
            return;
        }
    }

    public char[] quoteAsString(String str) {
        int i;
        TextBuffer textBuffer = this._text;
        if (textBuffer == null) {
            textBuffer = new TextBuffer(null);
            this._text = textBuffer;
        }
        char[] cArrEmptyAndGetCurrentSegment = textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = str.length();
        int i2 = 0;
        int i3 = 0;
        loop0: while (true) {
            if (i2 >= length2) {
                break;
            }
            while (true) {
                char cCharAt = str.charAt(i2);
                if (cCharAt < length && iArr[cCharAt] != 0) {
                    break;
                }
                if (i3 >= cArrEmptyAndGetCurrentSegment.length) {
                    cArrEmptyAndGetCurrentSegment = textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                int i4 = i3 + 1;
                cArrEmptyAndGetCurrentSegment[i3] = cCharAt;
                i2++;
                if (i2 >= length2) {
                    i3 = i4;
                    break loop0;
                }
                i3 = i4;
            }
            i2 = i;
        }
        textBuffer.setCurrentLength(i3);
        return textBuffer.contentsAsArray();
    }

    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        int i3;
        ByteArrayBuilder byteArrayBuilder = this._bytes;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._bytes = byteArrayBuilder;
        }
        int length = str.length();
        byte[] bArrResetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int i4 = 0;
        int i_appendByte = 0;
        loop0: while (true) {
            if (i4 >= length) {
                break;
            }
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char cCharAt = str.charAt(i4);
                if (cCharAt > 127 || iArr[cCharAt] != 0) {
                    break;
                }
                if (i_appendByte >= bArrResetAndGetFirstSegment.length) {
                    bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                    i_appendByte = 0;
                }
                int i5 = i_appendByte + 1;
                bArrResetAndGetFirstSegment[i_appendByte] = (byte) cCharAt;
                i4++;
                if (i4 >= length) {
                    i_appendByte = i5;
                    break loop0;
                }
                i_appendByte = i5;
            }
            if (i_appendByte >= bArrResetAndGetFirstSegment.length) {
                bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                i_appendByte = 0;
            }
            int i6 = i4 + 1;
            char cCharAt2 = str.charAt(i4);
            if (cCharAt2 <= 127) {
                i_appendByte = _appendByte(cCharAt2, iArr[cCharAt2], byteArrayBuilder, i_appendByte);
                bArrResetAndGetFirstSegment = byteArrayBuilder.getCurrentSegment();
                i4 = i6;
            } else {
                if (cCharAt2 <= 2047) {
                    i2 = i_appendByte + 1;
                    bArrResetAndGetFirstSegment[i_appendByte] = (byte) ((cCharAt2 >> 6) | 192);
                    i = (cCharAt2 & '?') | 128;
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    int i7 = i_appendByte + 1;
                    bArrResetAndGetFirstSegment[i_appendByte] = (byte) ((cCharAt2 >> '\f') | 224);
                    if (i7 >= bArrResetAndGetFirstSegment.length) {
                        bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i7 = 0;
                    }
                    bArrResetAndGetFirstSegment[i7] = (byte) (((cCharAt2 >> 6) & 63) | 128);
                    i = (cCharAt2 & '?') | 128;
                    i2 = i7 + 1;
                } else {
                    if (cCharAt2 > 56319) {
                        _illegal(cCharAt2);
                    }
                    if (i6 >= length) {
                        _illegal(cCharAt2);
                    }
                    int i8 = i6 + 1;
                    int i_convert = _convert(cCharAt2, str.charAt(i6));
                    if (i_convert > 1114111) {
                        _illegal(i_convert);
                    }
                    int i9 = i_appendByte + 1;
                    bArrResetAndGetFirstSegment[i_appendByte] = (byte) ((i_convert >> 18) | 240);
                    if (i9 >= bArrResetAndGetFirstSegment.length) {
                        bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i9 = 0;
                    }
                    int i10 = i9 + 1;
                    bArrResetAndGetFirstSegment[i9] = (byte) (((i_convert >> 12) & 63) | 128);
                    if (i10 >= bArrResetAndGetFirstSegment.length) {
                        bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i10 = 0;
                    }
                    int i11 = i10 + 1;
                    bArrResetAndGetFirstSegment[i10] = (byte) (((i_convert >> 6) & 63) | 128);
                    i3 = (i_convert & 63) | 128;
                    i4 = i8;
                    i2 = i11;
                    if (i2 >= bArrResetAndGetFirstSegment.length) {
                        bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i2 = 0;
                    }
                    bArrResetAndGetFirstSegment[i2] = (byte) i3;
                    i_appendByte = i2 + 1;
                }
                i3 = i;
                i4 = i6;
                if (i2 >= bArrResetAndGetFirstSegment.length) {
                }
                bArrResetAndGetFirstSegment[i2] = (byte) i3;
                i_appendByte = i2 + 1;
            }
        }
        return this._bytes.completeAndCoalesce(i_appendByte);
    }
}

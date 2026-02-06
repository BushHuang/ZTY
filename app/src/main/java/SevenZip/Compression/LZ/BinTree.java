package SevenZip.Compression.LZ;

import java.io.IOException;

public class BinTree extends InWindow {
    private static final int[] CrcTable = new int[256];
    static final int kBT2HashSize = 65536;
    static final int kEmptyHashValue = 0;
    static final int kHash2Size = 1024;
    static final int kHash3Offset = 1024;
    static final int kHash3Size = 65536;
    static final int kMaxValForNormalize = 1073741823;
    static final int kStartMaxLen = 1;
    int _cyclicBufferPos;
    int[] _hash;
    int _hashMask;
    int _matchMaxLen;
    int[] _son;
    int _cyclicBufferSize = 0;
    int _cutValue = 255;
    int _hashSizeSum = 0;
    boolean HASH_ARRAY = true;
    int kNumHashDirectBytes = 0;
    int kMinMatchCheck = 4;
    int kFixHashSize = 66560;

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (i2 & 1) != 0 ? (i2 >>> 1) ^ (-306674912) : i2 >>> 1;
            }
            CrcTable[i] = i2;
        }
    }

    public boolean Create(int i, int i2, int i3, int i4) {
        if (i > 1073741567) {
            return false;
        }
        this._cutValue = (i3 >> 1) + 16;
        int i5 = i2 + i;
        super.Create(i5, i4 + i3, (((i5 + i3) + i4) / 2) + 256);
        this._matchMaxLen = i3;
        int i6 = i + 1;
        if (this._cyclicBufferSize != i6) {
            this._cyclicBufferSize = i6;
            this._son = new int[i6 * 2];
        }
        int i7 = 65536;
        if (this.HASH_ARRAY) {
            int i8 = i - 1;
            int i9 = i8 | (i8 >> 1);
            int i10 = i9 | (i9 >> 2);
            int i11 = i10 | (i10 >> 4);
            int i12 = ((i11 | (i11 >> 8)) >> 1) | 65535;
            if (i12 > 16777216) {
                i12 >>= 1;
            }
            this._hashMask = i12;
            i7 = this.kFixHashSize + i12 + 1;
        }
        if (i7 != this._hashSizeSum) {
            this._hashSizeSum = i7;
            this._hash = new int[i7];
        }
        return true;
    }

    public int GetMatches(int[] iArr) throws IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        byte b;
        byte[] bArr;
        int i9;
        if (this._pos + this._matchMaxLen <= this._streamPos) {
            i = this._matchMaxLen;
        } else {
            i = this._streamPos - this._pos;
            if (i < this.kMinMatchCheck) {
                MovePos();
                return 0;
            }
        }
        int i10 = this._pos > this._cyclicBufferSize ? this._pos - this._cyclicBufferSize : 0;
        int i11 = this._bufferOffset + this._pos;
        if (this.HASH_ARRAY) {
            int i12 = CrcTable[this._bufferBase[i11] & 255] ^ (this._bufferBase[i11 + 1] & 255);
            i3 = i12 & 1023;
            int i13 = i12 ^ ((this._bufferBase[i11 + 2] & 255) << 8);
            i4 = 65535 & i13;
            i2 = (i13 ^ (CrcTable[this._bufferBase[i11 + 3] & 255] << 5)) & this._hashMask;
        } else {
            i2 = (this._bufferBase[i11] & 255) ^ ((this._bufferBase[i11 + 1] & 255) << 8);
            i3 = 0;
            i4 = 0;
        }
        int[] iArr2 = this._hash;
        int i14 = iArr2[this.kFixHashSize + i2];
        if (this.HASH_ARRAY) {
            int i15 = iArr2[i3];
            int i16 = i4 + 1024;
            int i17 = iArr2[i16];
            iArr2[i3] = this._pos;
            this._hash[i16] = this._pos;
            int i18 = 2;
            if (i15 <= i10 || this._bufferBase[this._bufferOffset + i15] != this._bufferBase[i11]) {
                i18 = 0;
                i5 = 1;
            } else {
                iArr[0] = 2;
                iArr[1] = (this._pos - i15) - 1;
                i5 = 2;
            }
            if (i17 > i10 && this._bufferBase[this._bufferOffset + i17] == this._bufferBase[i11]) {
                if (i17 == i15) {
                    i18 -= 2;
                }
                int i19 = i18 + 1;
                iArr[i18] = 3;
                i18 = i19 + 1;
                iArr[i19] = (this._pos - i17) - 1;
                i15 = i17;
                i5 = 3;
            }
            i18 = (i18 != 0 && i15 == i14) ? i18 - 2 : 0;
            this._hash[this.kFixHashSize + i2] = this._pos;
            int i20 = this._cyclicBufferPos;
            int i21 = (i20 << 1) + 1;
            int i22 = i20 << 1;
            i6 = this.kNumHashDirectBytes;
            if (i6 != 0 && i14 > i10) {
                b = this._bufferBase[this._bufferOffset + i14 + this.kNumHashDirectBytes];
                bArr = this._bufferBase;
                i9 = this.kNumHashDirectBytes;
                if (b != bArr[i11 + i9]) {
                    int i23 = i18 + 1;
                    iArr[i18] = i9;
                    i18 = i23 + 1;
                    iArr[i23] = (this._pos - i14) - 1;
                    i5 = i9;
                }
            }
            int i24 = this._cutValue;
            int i25 = i6;
            while (i14 > i10) {
                int i26 = i24 - 1;
                if (i24 == 0) {
                    break;
                }
                int i27 = this._pos - i14;
                int i28 = this._cyclicBufferPos;
                int i29 = (i27 <= i28 ? i28 - i27 : (i28 - i27) + this._cyclicBufferSize) << 1;
                int i30 = this._bufferOffset + i14;
                int iMin = Math.min(i6, i25);
                int i31 = i10;
                if (this._bufferBase[i30 + iMin] == this._bufferBase[i11 + iMin]) {
                    while (true) {
                        i8 = iMin + 1;
                        if (i8 == i) {
                            i7 = i6;
                            break;
                        }
                        i7 = i6;
                        if (this._bufferBase[i30 + i8] != this._bufferBase[i11 + i8]) {
                            break;
                        }
                        iMin = i8;
                        i6 = i7;
                    }
                    if (i5 < i8) {
                        int i32 = i18 + 1;
                        iArr[i18] = i8;
                        i18 = i32 + 1;
                        iArr[i32] = i27 - 1;
                        if (i8 == i) {
                            int[] iArr3 = this._son;
                            iArr3[i22] = iArr3[i29];
                            iArr3[i21] = iArr3[i29 + 1];
                            break;
                        }
                        i5 = i8;
                        iMin = i5;
                    } else {
                        iMin = i8;
                    }
                } else {
                    i7 = i6;
                }
                if ((this._bufferBase[i30 + iMin] & 255) < (this._bufferBase[i11 + iMin] & 255)) {
                    int[] iArr4 = this._son;
                    iArr4[i22] = i14;
                    int i33 = i29 + 1;
                    i14 = iArr4[i33];
                    i25 = iMin;
                    i6 = i7;
                    i22 = i33;
                } else {
                    int[] iArr5 = this._son;
                    iArr5[i21] = i14;
                    i14 = iArr5[i29];
                    i6 = iMin;
                    i21 = i29;
                }
                i24 = i26;
                i10 = i31;
            }
            int[] iArr6 = this._son;
            iArr6[i22] = 0;
            iArr6[i21] = 0;
            MovePos();
            return i18;
        }
        i5 = 1;
        this._hash[this.kFixHashSize + i2] = this._pos;
        int i202 = this._cyclicBufferPos;
        int i212 = (i202 << 1) + 1;
        int i222 = i202 << 1;
        i6 = this.kNumHashDirectBytes;
        if (i6 != 0) {
            b = this._bufferBase[this._bufferOffset + i14 + this.kNumHashDirectBytes];
            bArr = this._bufferBase;
            i9 = this.kNumHashDirectBytes;
            if (b != bArr[i11 + i9]) {
            }
        }
        int i242 = this._cutValue;
        int i252 = i6;
        while (i14 > i10) {
        }
        int[] iArr62 = this._son;
        iArr62[i222] = 0;
        iArr62[i212] = 0;
        MovePos();
        return i18;
    }

    @Override
    public void Init() throws IOException {
        super.Init();
        for (int i = 0; i < this._hashSizeSum; i++) {
            this._hash[i] = 0;
        }
        this._cyclicBufferPos = 0;
        ReduceOffsets(-1);
    }

    @Override
    public void MovePos() throws IOException {
        int i = this._cyclicBufferPos + 1;
        this._cyclicBufferPos = i;
        if (i >= this._cyclicBufferSize) {
            this._cyclicBufferPos = 0;
        }
        super.MovePos();
        if (this._pos == 1073741823) {
            Normalize();
        }
    }

    void Normalize() {
        int i = this._pos;
        int i2 = this._cyclicBufferSize;
        int i3 = i - i2;
        NormalizeLinks(this._son, i2 * 2, i3);
        NormalizeLinks(this._hash, this._hashSizeSum, i3);
        ReduceOffsets(i3);
    }

    void NormalizeLinks(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            iArr[i3] = i4 <= i2 ? 0 : i4 - i2;
        }
    }

    public void SetCutValue(int i) {
        this._cutValue = i;
    }

    public void SetType(int i) {
        boolean z = i > 2;
        this.HASH_ARRAY = z;
        if (z) {
            this.kNumHashDirectBytes = 0;
            this.kMinMatchCheck = 4;
            this.kFixHashSize = 66560;
        } else {
            this.kNumHashDirectBytes = 2;
            this.kMinMatchCheck = 3;
            this.kFixHashSize = 0;
        }
    }

    public void Skip(int i) throws IOException {
        int i2;
        int i3;
        int i4 = i;
        do {
            if (this._pos + this._matchMaxLen <= this._streamPos) {
                i2 = this._matchMaxLen;
            } else {
                i2 = this._streamPos - this._pos;
                if (i2 < this.kMinMatchCheck) {
                    MovePos();
                }
                i4--;
            }
            int i5 = this._pos > this._cyclicBufferSize ? this._pos - this._cyclicBufferSize : 0;
            int i6 = this._bufferOffset + this._pos;
            if (this.HASH_ARRAY) {
                int i7 = CrcTable[this._bufferBase[i6] & 255] ^ (this._bufferBase[i6 + 1] & 255);
                this._hash[i7 & 1023] = this._pos;
                int i8 = i7 ^ ((this._bufferBase[i6 + 2] & 255) << 8);
                this._hash[(65535 & i8) + 1024] = this._pos;
                i3 = (i8 ^ (CrcTable[this._bufferBase[i6 + 3] & 255] << 5)) & this._hashMask;
            } else {
                i3 = (this._bufferBase[i6] & 255) ^ ((this._bufferBase[i6 + 1] & 255) << 8);
            }
            int[] iArr = this._hash;
            int i9 = this.kFixHashSize;
            int i10 = iArr[i9 + i3];
            iArr[i9 + i3] = this._pos;
            int i11 = this._cyclicBufferPos;
            int i12 = (i11 << 1) + 1;
            int i13 = i11 << 1;
            int i14 = this.kNumHashDirectBytes;
            int i15 = this._cutValue;
            int i16 = i10;
            int i17 = i14;
            while (i16 > i5) {
                int i18 = i15 - 1;
                if (i15 == 0) {
                    break;
                }
                int i19 = this._pos - i16;
                int i20 = this._cyclicBufferPos;
                int i21 = (i19 <= i20 ? i20 - i19 : (i20 - i19) + this._cyclicBufferSize) << 1;
                int i22 = this._bufferOffset + i16;
                int iMin = Math.min(i14, i17);
                if (this._bufferBase[i22 + iMin] == this._bufferBase[i6 + iMin]) {
                    do {
                        iMin++;
                        if (iMin == i2) {
                            break;
                        }
                    } while (this._bufferBase[i22 + iMin] == this._bufferBase[i6 + iMin]);
                    if (iMin == i2) {
                        int[] iArr2 = this._son;
                        iArr2[i13] = iArr2[i21];
                        iArr2[i12] = iArr2[i21 + 1];
                        break;
                    }
                }
                if ((this._bufferBase[i22 + iMin] & 255) < (this._bufferBase[i6 + iMin] & 255)) {
                    int[] iArr3 = this._son;
                    iArr3[i13] = i16;
                    int i23 = i21 + 1;
                    i16 = iArr3[i23];
                    i13 = i23;
                    i17 = iMin;
                } else {
                    int[] iArr4 = this._son;
                    iArr4[i12] = i16;
                    i16 = iArr4[i21];
                    i12 = i21;
                    i14 = iMin;
                }
                i15 = i18;
            }
            int[] iArr5 = this._son;
            iArr5[i13] = 0;
            iArr5[i12] = 0;
            MovePos();
            i4--;
        } while (i4 != 0);
    }
}

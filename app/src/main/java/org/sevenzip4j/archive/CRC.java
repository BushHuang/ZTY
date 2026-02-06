package org.sevenzip4j.archive;

public class CRC {
    public static int[] Table = new int[256];
    int _value = -1;

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (i2 & 1) != 0 ? (i2 >>> 1) ^ (-306674912) : i2 >>> 1;
            }
            Table[i] = i2;
        }
    }

    public static int CalculateDigest(byte[] bArr, int i) {
        CRC crc = new CRC();
        crc.Update(bArr, i);
        return crc.GetDigest();
    }

    public static boolean VerifyDigest(int i, byte[] bArr, int i2) {
        return CalculateDigest(bArr, i2) == i;
    }

    public int GetDigest() {
        return this._value ^ (-1);
    }

    public void Init() {
        this._value = -1;
    }

    public void Update(byte[] bArr) {
        for (byte b : bArr) {
            int[] iArr = Table;
            int i = this._value;
            this._value = iArr[(b ^ i) & 255] ^ (i >>> 8);
        }
    }

    public void Update(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int[] iArr = Table;
            int i3 = this._value;
            this._value = iArr[(bArr[i2] ^ i3) & 255] ^ (i3 >>> 8);
        }
    }

    public void Update(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int[] iArr = Table;
            int i4 = this._value;
            this._value = iArr[(bArr[i + i3] ^ i4) & 255] ^ (i4 >>> 8);
        }
    }

    public void UpdateByte(int i) {
        int[] iArr = Table;
        int i2 = this._value;
        this._value = iArr[(i ^ i2) & 255] ^ (i2 >>> 8);
    }

    public void UpdateUInt32(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            UpdateByte((i >> (i2 * 8)) & 255);
        }
    }

    public void UpdateUInt64(long j) {
        for (int i = 0; i < 8; i++) {
            UpdateByte(((int) (j >> (i * 8))) & 255);
        }
    }
}

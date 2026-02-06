package com.huawei.hms.hatool;

public class p0 {

    public byte[] f321a;
    public int b = 0;

    public p0(int i) {
        this.f321a = null;
        this.f321a = new byte[i];
    }

    public void a(byte[] bArr, int i) {
        if (i <= 0) {
            return;
        }
        byte[] bArr2 = this.f321a;
        int length = bArr2.length;
        int i2 = this.b;
        if (length - i2 >= i) {
            System.arraycopy(bArr, 0, bArr2, i2, i);
        } else {
            byte[] bArr3 = new byte[(bArr2.length + i) << 1];
            System.arraycopy(bArr2, 0, bArr3, 0, i2);
            System.arraycopy(bArr, 0, bArr3, this.b, i);
            this.f321a = bArr3;
        }
        this.b += i;
    }

    public byte[] a() {
        int i = this.b;
        if (i <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.f321a, 0, bArr, 0, i);
        return bArr;
    }

    public int b() {
        return this.b;
    }
}

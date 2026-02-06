package com.analysys.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class BytesParcelable implements Parcelable {
    public static final Parcelable.Creator<BytesParcelable> CREATOR = new Parcelable.Creator<BytesParcelable>() {
        @Override
        public BytesParcelable createFromParcel(Parcel parcel) {
            return new BytesParcelable(parcel);
        }

        @Override
        public BytesParcelable[] newArray(int i) {
            return new BytesParcelable[i];
        }
    };
    public byte[] data;
    public boolean finish;

    public BytesParcelable() {
    }

    protected BytesParcelable(Parcel parcel) {
        this.data = parcel.createByteArray();
        this.finish = parcel.readByte() != 0;
    }

    public BytesParcelable(byte[] bArr, boolean z) {
        this.data = bArr;
        this.finish = z;
    }

    public void appendData(BytesParcelable bytesParcelable) {
        byte[] bArr = bytesParcelable.data;
        if (bArr.length == 0) {
            return;
        }
        byte[] bArr2 = this.data;
        if (bArr2 == null || bArr2.length == 0) {
            this.data = bytesParcelable.data;
            return;
        }
        byte[] bArr3 = new byte[bArr2.length + bArr.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        byte[] bArr4 = bytesParcelable.data;
        System.arraycopy(bArr4, 0, bArr3, this.data.length, bArr4.length);
        this.data = bArr3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.data);
        parcel.writeByte(this.finish ? (byte) 1 : (byte) 0);
    }
}

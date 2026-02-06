package com.analysys;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR = new Parcelable.Creator<k>() {
        @Override
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        @Override
        public k[] newArray(int i) {
            return new k[i];
        }
    };

    private IBinder f62a;

    k(IBinder iBinder) {
        this.f62a = iBinder;
    }

    k(Parcel parcel) {
        this.f62a = parcel.readStrongBinder();
    }

    public IBinder a() {
        return this.f62a;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.f62a);
    }
}

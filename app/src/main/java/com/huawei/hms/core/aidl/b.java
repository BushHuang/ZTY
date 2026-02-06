package com.huawei.hms.core.aidl;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    public String f250a;
    public Bundle b;
    private int c;
    private Bundle d;

    static class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override
        public b createFromParcel(Parcel parcel) {
            return new b(parcel, (a) null);
        }

        @Override
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b() {
        this.b = null;
        this.c = 1;
        this.d = null;
    }

    private b(Parcel parcel) {
        this.b = null;
        this.c = 1;
        this.d = null;
        a(parcel);
    }

    b(Parcel parcel, a aVar) {
        this(parcel);
    }

    public b(String str, int i) {
        this.b = null;
        this.c = 1;
        this.d = null;
        this.f250a = str;
        this.c = i;
    }

    private static ClassLoader a(Class cls) {
        return cls.getClassLoader();
    }

    private void a(Parcel parcel) {
        this.c = parcel.readInt();
        this.f250a = parcel.readString();
        this.b = parcel.readBundle(a(Bundle.class));
        this.d = parcel.readBundle(a(Bundle.class));
    }

    public Bundle a() {
        return this.d;
    }

    public b a(Bundle bundle) {
        this.d = bundle;
        return this;
    }

    public int b() {
        return this.d == null ? 0 : 1;
    }

    public int c() {
        return this.c;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeString(this.f250a);
        parcel.writeBundle(this.b);
        parcel.writeBundle(this.d);
    }
}

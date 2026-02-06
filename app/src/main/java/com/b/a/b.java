package com.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

public abstract class b extends Binder implements a {

    private static final String f158a = "com.srcb.softupdate.ICustomif";
    private static int b = 1;
    private static int c = 2;
    private static int d = 3;
    private static int e = 4;
    private static int f = 5;
    private static int g = 6;
    private static int h = 7;
    private static int i = 8;
    private static int j = 9;
    private static int k = 10;
    private static int l = 11;
    private static int m = 12;

    public b() {
        attachInterface(this, "com.srcb.softupdate.ICustomif");
    }

    public static a a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.srcb.softupdate.ICustomif");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new c(iBinder) : (a) iInterfaceQueryLocalInterface;
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1598968902) {
            parcel2.writeString("com.srcb.softupdate.ICustomif");
            return true;
        }
        switch (i2) {
            case 1:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                List<String> listA = a(parcel.readLong(), parcel.readLong());
                parcel2.writeNoException();
                parcel2.writeStringList(listA);
                return true;
            case 2:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                String strA = a();
                parcel2.writeNoException();
                parcel2.writeString(strA);
                return true;
            case 3:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                String strA2 = a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strA2);
                return true;
            case 4:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                int iA = a(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(iA);
                return true;
            case 5:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                List<String> listB = b();
                parcel2.writeNoException();
                parcel2.writeStringList(listB);
                return true;
            case 6:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                String strB = b(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strB);
                return true;
            case 7:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                a(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 8:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                c();
                parcel2.writeNoException();
                return true;
            case 9:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 10:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                a(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 11:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                b(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 12:
                parcel.enforceInterface("com.srcb.softupdate.ICustomif");
                b(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i2, parcel, parcel2, i3);
        }
    }
}

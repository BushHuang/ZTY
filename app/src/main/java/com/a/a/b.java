package com.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

public abstract class b extends Binder implements a {
    private static int A = 26;
    private static int B = 27;
    private static int C = 28;
    private static int D = 29;
    private static int E = 30;
    private static int F = 31;
    private static int G = 32;
    private static int H = 33;

    private static final String f6a = "com.samsung.customxh.ICustomif";
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
    private static int n = 13;
    private static int o = 14;
    private static int p = 15;
    private static int q = 16;
    private static int r = 17;
    private static int s = 18;
    private static int t = 19;
    private static int u = 20;
    private static int v = 21;
    private static int w = 22;
    private static int x = 23;
    private static int y = 24;
    private static int z = 25;

    public b() {
        attachInterface(this, "com.samsung.customxh.ICustomif");
    }

    public static a a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.samsung.customxh.ICustomif");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new c(iBinder) : (a) iInterfaceQueryLocalInterface;
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
        if (i2 == 1598968902) {
            parcel2.writeString("com.samsung.customxh.ICustomif");
            return true;
        }
        switch (i2) {
            case 1:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zA = a(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zA ? 1 : 0);
                return true;
            case 2:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zB = b(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zB ? 1 : 0);
                return true;
            case 3:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zA2 = a(parcel.createStringArrayList());
                parcel2.writeNoException();
                parcel2.writeInt(zA2 ? 1 : 0);
                return true;
            case 4:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zB2 = b(parcel.createStringArrayList());
                parcel2.writeNoException();
                parcel2.writeInt(zB2 ? 1 : 0);
                return true;
            case 5:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zA3 = a();
                parcel2.writeNoException();
                parcel2.writeInt(zA3 ? 1 : 0);
                return true;
            case 6:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zA4 = a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zA4 ? 1 : 0);
                return true;
            case 7:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zC = c(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zC ? 1 : 0);
                return true;
            case 8:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zD = d(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zD ? 1 : 0);
                return true;
            case 9:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zE = e(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zE ? 1 : 0);
                return true;
            case 10:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zF = f(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zF ? 1 : 0);
                return true;
            case 11:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zG = g(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zG ? 1 : 0);
                return true;
            case 12:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zC2 = c(parcel.createStringArrayList());
                parcel2.writeNoException();
                parcel2.writeInt(zC2 ? 1 : 0);
                return true;
            case 13:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zD2 = d(parcel.createStringArrayList());
                parcel2.writeNoException();
                parcel2.writeInt(zD2 ? 1 : 0);
                return true;
            case 14:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                List<String> listB = b();
                parcel2.writeNoException();
                parcel2.writeStringList(listB);
                return true;
            case 15:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zE2 = e(parcel.createStringArrayList());
                parcel2.writeNoException();
                parcel2.writeInt(zE2 ? 1 : 0);
                return true;
            case 16:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zF2 = f(parcel.createStringArrayList());
                parcel2.writeNoException();
                parcel2.writeInt(zF2 ? 1 : 0);
                return true;
            case 17:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                List<String> listC = c();
                parcel2.writeNoException();
                parcel2.writeStringList(listC);
                return true;
            case 18:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zB3 = b(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zB3 ? 1 : 0);
                return true;
            case 19:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zD3 = d();
                parcel2.writeNoException();
                parcel2.writeInt(zD3 ? 1 : 0);
                return true;
            case 20:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zH = h(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(zH ? 1 : 0);
                return true;
            case 21:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                e();
                parcel2.writeNoException();
                return true;
            case 22:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                f();
                parcel2.writeNoException();
                return true;
            case 23:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                a(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 24:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                String strC = c(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strC);
                return true;
            case 25:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                g();
                parcel2.writeNoException();
                return true;
            case 26:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                boolean zA5 = a(parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
                parcel2.writeInt(zA5 ? 1 : 0);
                return true;
            case 27:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                String strH = h();
                parcel2.writeNoException();
                parcel2.writeString(strH);
                return true;
            case 28:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                i();
                parcel2.writeNoException();
                return true;
            case 29:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                String strJ = j();
                parcel2.writeNoException();
                parcel2.writeString(strJ);
                return true;
            case 30:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                i(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 31:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                j(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 32:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                k(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 33:
                parcel.enforceInterface("com.samsung.customxh.ICustomif");
                d(parcel.readString());
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i2, parcel, parcel2, i3);
        }
    }
}

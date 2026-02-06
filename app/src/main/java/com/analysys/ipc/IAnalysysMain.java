package com.analysys.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.Map;

public interface IAnalysysMain extends IInterface {

    public static abstract class a extends Binder implements IAnalysysMain {

        static class C0009a implements IAnalysysMain {

            private IBinder f57a;

            C0009a(IBinder iBinder) {
                this.f57a = iBinder;
            }

            @Override
            public IBinder asBinder() {
                return this.f57a;
            }

            @Override
            public void reportVisualEvent(String str, String str2, Map map) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysMain");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeMap(map);
                    this.f57a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setClientBinder(String str, IBinder iBinder) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysMain");
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iBinder);
                    this.f57a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.analysys.ipc.IAnalysysMain");
        }

        public static IAnalysysMain a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.analysys.ipc.IAnalysysMain");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAnalysysMain)) ? new C0009a(iBinder) : (IAnalysysMain) iInterfaceQueryLocalInterface;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.analysys.ipc.IAnalysysMain");
                setClientBinder(parcel.readString(), parcel.readStrongBinder());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.analysys.ipc.IAnalysysMain");
                return true;
            }
            parcel.enforceInterface("com.analysys.ipc.IAnalysysMain");
            reportVisualEvent(parcel.readString(), parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
            parcel2.writeNoException();
            return true;
        }
    }

    void reportVisualEvent(String str, String str2, Map map);

    void setClientBinder(String str, IBinder iBinder);
}

package com.tencent.tinker.lib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IForeService extends IInterface {

    public static abstract class Stub extends Binder implements IForeService {
        private static final String DESCRIPTOR = "com.tencent.tinker.lib.IForeService";
        static final int TRANSACTION_startme = 1;

        private static class Proxy implements IForeService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.tencent.tinker.lib.IForeService";
            }

            @Override
            public void startme() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.tencent.tinker.lib.IForeService");
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.tencent.tinker.lib.IForeService");
        }

        public static IForeService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.tencent.tinker.lib.IForeService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IForeService)) ? new Proxy(iBinder) : (IForeService) iInterfaceQueryLocalInterface;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.tencent.tinker.lib.IForeService");
                return true;
            }
            parcel.enforceInterface("com.tencent.tinker.lib.IForeService");
            startme();
            parcel2.writeNoException();
            return true;
        }
    }

    void startme() throws RemoteException;
}

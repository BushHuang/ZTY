package com.sec.enterprise.knox;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAttestation extends IInterface {

    public static abstract class Stub extends Binder implements IAttestation {
        private static final String DESCRIPTOR = "com.sec.enterprise.knox.IAttestation";
        static final int TRANSACTION_startAttestation_nonce = 1;

        private static class Proxy implements IAttestation {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.sec.enterprise.knox.IAttestation";
            }

            @Override
            public void startAttestation_nonce(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.sec.enterprise.knox.IAttestation");
                    parcelObtain.writeString(str);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.sec.enterprise.knox.IAttestation");
        }

        public static IAttestation asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.sec.enterprise.knox.IAttestation");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAttestation)) ? new Proxy(iBinder) : (IAttestation) iInterfaceQueryLocalInterface;
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
                parcel2.writeString("com.sec.enterprise.knox.IAttestation");
                return true;
            }
            parcel.enforceInterface("com.sec.enterprise.knox.IAttestation");
            startAttestation_nonce(parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void startAttestation_nonce(String str) throws RemoteException;
}

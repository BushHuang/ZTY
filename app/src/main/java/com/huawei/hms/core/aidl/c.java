package com.huawei.hms.core.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface c extends IInterface {

    public static abstract class a extends Binder implements c {

        private static class C0015a implements c {
            public static c b;

            private IBinder f251a;

            C0015a(IBinder iBinder) {
                this.f251a = iBinder;
            }

            @Override
            public IBinder asBinder() {
                return this.f251a;
            }
        }

        public a() {
            attachInterface(this, "com.huawei.hms.core.aidl.IAIDLCallback");
        }

        public static c asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.core.aidl.IAIDLCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) ? new C0015a(iBinder) : (c) iInterfaceQueryLocalInterface;
        }

        public static c getDefaultImpl() {
            return C0015a.b;
        }

        public static boolean setDefaultImpl(c cVar) {
            if (C0015a.b != null || cVar == null) {
                return false;
            }
            C0015a.b = cVar;
            return true;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.huawei.hms.core.aidl.IAIDLCallback");
                call(parcel.readInt() != 0 ? b.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.huawei.hms.core.aidl.IAIDLCallback");
            return true;
        }
    }

    void call(b bVar) throws RemoteException;
}

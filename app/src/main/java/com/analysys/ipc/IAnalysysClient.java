package com.analysys.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IAnalysysClient extends IInterface {

    public static abstract class a extends Binder implements IAnalysysClient {

        static class C0008a implements IAnalysysClient {

            private IBinder f56a;

            C0008a(IBinder iBinder) {
                this.f56a = iBinder;
            }

            @Override
            public IBinder asBinder() {
                return this.f56a;
            }

            @Override
            public void clearVisualSnapshot() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysClient");
                    this.f56a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public BytesParcelable getVisualSnapshotData(String str, boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysClient");
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.f56a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BytesParcelable.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public boolean isInFront() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysClient");
                    this.f56a.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void onVisualEditEvent(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysClient");
                    parcelObtain.writeString(str);
                    this.f56a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void reloadVisualEventLocal() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysClient");
                    this.f56a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public void setVisualEditing(boolean z) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.analysys.ipc.IAnalysysClient");
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.f56a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.analysys.ipc.IAnalysysClient");
        }

        public static IAnalysysClient a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.analysys.ipc.IAnalysysClient");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAnalysysClient)) ? new C0008a(iBinder) : (IAnalysysClient) iInterfaceQueryLocalInterface;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.analysys.ipc.IAnalysysClient");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.analysys.ipc.IAnalysysClient");
                    BytesParcelable visualSnapshotData = getVisualSnapshotData(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (visualSnapshotData != null) {
                        parcel2.writeInt(1);
                        visualSnapshotData.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("com.analysys.ipc.IAnalysysClient");
                    clearVisualSnapshot();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.analysys.ipc.IAnalysysClient");
                    onVisualEditEvent(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.analysys.ipc.IAnalysysClient");
                    setVisualEditing(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.analysys.ipc.IAnalysysClient");
                    reloadVisualEventLocal();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.analysys.ipc.IAnalysysClient");
                    boolean zIsInFront = isInFront();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInFront ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void clearVisualSnapshot();

    BytesParcelable getVisualSnapshotData(String str, boolean z);

    boolean isInFront();

    void onVisualEditEvent(String str);

    void reloadVisualEventLocal();

    void setVisualEditing(boolean z);
}

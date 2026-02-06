package cn.com.microtrust.firewall.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAFWService extends IInterface {

    public static class Default implements IAFWService {
        @Override
        public AFWRes addAppBlackRule(String str) throws RemoteException {
            return null;
        }

        @Override
        public AFWRes addAppWhiteRule(String str) throws RemoteException {
            return null;
        }

        @Override
        public AFWRes addBlackRule(String str) throws RemoteException {
            return null;
        }

        @Override
        public AFWRes addWhiteRule(String str) throws RemoteException {
            return null;
        }

        @Override
        public IBinder asBinder() {
            return null;
        }

        @Override
        public AFWRes clearAppRules() throws RemoteException {
            return null;
        }

        @Override
        public AFWRes clearIpHostRules() throws RemoteException {
            return null;
        }

        @Override
        public AFWRes clearRules() throws RemoteException {
            return null;
        }

        @Override
        public AFWRes getBlackRules() throws RemoteException {
            return null;
        }

        @Override
        public AFWRes getWhiteRules() throws RemoteException {
            return null;
        }

        @Override
        public AFWRes isDefalutAllowed() throws RemoteException {
            return null;
        }

        @Override
        public AFWRes isEnable() throws RemoteException {
            return null;
        }

        @Override
        public AFWRes setEnable(boolean z) throws RemoteException {
            return null;
        }

        @Override
        public AFWRes writeToFile() throws RemoteException {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAFWService {
        private static final String DESCRIPTOR = "cn.com.microtrust.firewall.aidl.IAFWService";
        static final int TRANSACTION_addAppBlackRule = 13;
        static final int TRANSACTION_addAppWhiteRule = 12;
        static final int TRANSACTION_addBlackRule = 6;
        static final int TRANSACTION_addWhiteRule = 4;
        static final int TRANSACTION_clearAppRules = 10;
        static final int TRANSACTION_clearIpHostRules = 8;
        static final int TRANSACTION_clearRules = 9;
        static final int TRANSACTION_getBlackRules = 7;
        static final int TRANSACTION_getWhiteRules = 5;
        static final int TRANSACTION_isDefalutAllowed = 3;
        static final int TRANSACTION_isEnable = 2;
        static final int TRANSACTION_setEnable = 1;
        static final int TRANSACTION_writeToFile = 11;

        private static class Proxy implements IAFWService {
            public static IAFWService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override
            public AFWRes addAppBlackRule(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAppBlackRule(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes addAppWhiteRule(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAppWhiteRule(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes addBlackRule(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addBlackRule(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes addWhiteRule(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addWhiteRule(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override
            public AFWRes clearAppRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearAppRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes clearIpHostRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearIpHostRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes clearRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes getBlackRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlackRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "cn.com.microtrust.firewall.aidl.IAFWService";
            }

            @Override
            public AFWRes getWhiteRules() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWhiteRules();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes isDefalutAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDefalutAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes isEnable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEnable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes setEnable(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnable(z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override
            public AFWRes writeToFile() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("cn.com.microtrust.firewall.aidl.IAFWService");
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().writeToFile();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AFWRes.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "cn.com.microtrust.firewall.aidl.IAFWService");
        }

        public static IAFWService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("cn.com.microtrust.firewall.aidl.IAFWService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAFWService)) ? new Proxy(iBinder) : (IAFWService) iInterfaceQueryLocalInterface;
        }

        public static IAFWService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IAFWService iAFWService) {
            if (Proxy.sDefaultImpl != null || iAFWService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAFWService;
            return true;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("cn.com.microtrust.firewall.aidl.IAFWService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes enable = setEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (enable != null) {
                        parcel2.writeInt(1);
                        enable.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResIsEnable = isEnable();
                    parcel2.writeNoException();
                    if (aFWResIsEnable != null) {
                        parcel2.writeInt(1);
                        aFWResIsEnable.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResIsDefalutAllowed = isDefalutAllowed();
                    parcel2.writeNoException();
                    if (aFWResIsDefalutAllowed != null) {
                        parcel2.writeInt(1);
                        aFWResIsDefalutAllowed.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResAddWhiteRule = addWhiteRule(parcel.readString());
                    parcel2.writeNoException();
                    if (aFWResAddWhiteRule != null) {
                        parcel2.writeInt(1);
                        aFWResAddWhiteRule.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes whiteRules = getWhiteRules();
                    parcel2.writeNoException();
                    if (whiteRules != null) {
                        parcel2.writeInt(1);
                        whiteRules.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResAddBlackRule = addBlackRule(parcel.readString());
                    parcel2.writeNoException();
                    if (aFWResAddBlackRule != null) {
                        parcel2.writeInt(1);
                        aFWResAddBlackRule.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes blackRules = getBlackRules();
                    parcel2.writeNoException();
                    if (blackRules != null) {
                        parcel2.writeInt(1);
                        blackRules.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResClearIpHostRules = clearIpHostRules();
                    parcel2.writeNoException();
                    if (aFWResClearIpHostRules != null) {
                        parcel2.writeInt(1);
                        aFWResClearIpHostRules.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResClearRules = clearRules();
                    parcel2.writeNoException();
                    if (aFWResClearRules != null) {
                        parcel2.writeInt(1);
                        aFWResClearRules.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResClearAppRules = clearAppRules();
                    parcel2.writeNoException();
                    if (aFWResClearAppRules != null) {
                        parcel2.writeInt(1);
                        aFWResClearAppRules.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResWriteToFile = writeToFile();
                    parcel2.writeNoException();
                    if (aFWResWriteToFile != null) {
                        parcel2.writeInt(1);
                        aFWResWriteToFile.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResAddAppWhiteRule = addAppWhiteRule(parcel.readString());
                    parcel2.writeNoException();
                    if (aFWResAddAppWhiteRule != null) {
                        parcel2.writeInt(1);
                        aFWResAddAppWhiteRule.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface("cn.com.microtrust.firewall.aidl.IAFWService");
                    AFWRes aFWResAddAppBlackRule = addAppBlackRule(parcel.readString());
                    parcel2.writeNoException();
                    if (aFWResAddAppBlackRule != null) {
                        parcel2.writeInt(1);
                        aFWResAddAppBlackRule.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    AFWRes addAppBlackRule(String str) throws RemoteException;

    AFWRes addAppWhiteRule(String str) throws RemoteException;

    AFWRes addBlackRule(String str) throws RemoteException;

    AFWRes addWhiteRule(String str) throws RemoteException;

    AFWRes clearAppRules() throws RemoteException;

    AFWRes clearIpHostRules() throws RemoteException;

    AFWRes clearRules() throws RemoteException;

    AFWRes getBlackRules() throws RemoteException;

    AFWRes getWhiteRules() throws RemoteException;

    AFWRes isDefalutAllowed() throws RemoteException;

    AFWRes isEnable() throws RemoteException;

    AFWRes setEnable(boolean z) throws RemoteException;

    AFWRes writeToFile() throws RemoteException;
}

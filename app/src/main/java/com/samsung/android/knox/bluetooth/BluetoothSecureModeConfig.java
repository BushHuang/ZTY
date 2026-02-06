package com.samsung.android.knox.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class BluetoothSecureModeConfig implements Parcelable {
    public static final Parcelable.Creator<BluetoothSecureModeConfig> CREATOR = new Parcelable.Creator<BluetoothSecureModeConfig>() {
        @Override
        public BluetoothSecureModeConfig createFromParcel(Parcel parcel) {
            return new BluetoothSecureModeConfig(parcel);
        }

        @Override
        public BluetoothSecureModeConfig[] newArray(int i) {
            return new BluetoothSecureModeConfig[i];
        }
    };
    public boolean a2dpEnable;
    public boolean ftpEnable;
    public boolean gattEnable;
    public boolean hdpEnable;
    public boolean hfpEnable;
    public boolean hidEnable;
    public boolean mapEnable;
    public boolean oppEnable;
    public boolean pairingMode;
    public boolean panEnable;
    public boolean pbapEnable;
    public boolean sapEnable;
    public boolean scanMode;
    public boolean whitelistEnable;

    public BluetoothSecureModeConfig() {
    }

    private BluetoothSecureModeConfig(Parcel parcel) {
        readFromParcel(parcel);
    }

    static BluetoothSecureModeConfig convertToNew(android.app.enterprise.BluetoothSecureModeConfig bluetoothSecureModeConfig) {
        if (bluetoothSecureModeConfig == null) {
            return null;
        }
        BluetoothSecureModeConfig bluetoothSecureModeConfig2 = new BluetoothSecureModeConfig();
        bluetoothSecureModeConfig2.a2dpEnable = bluetoothSecureModeConfig.a2dpEnable;
        bluetoothSecureModeConfig2.ftpEnable = bluetoothSecureModeConfig.ftpEnable;
        bluetoothSecureModeConfig2.gattEnable = bluetoothSecureModeConfig.gattEnable;
        bluetoothSecureModeConfig2.hdpEnable = bluetoothSecureModeConfig.hdpEnable;
        bluetoothSecureModeConfig2.hfpEnable = bluetoothSecureModeConfig.hfpEnable;
        bluetoothSecureModeConfig2.hidEnable = bluetoothSecureModeConfig.hidEnable;
        bluetoothSecureModeConfig2.mapEnable = bluetoothSecureModeConfig.mapEnable;
        bluetoothSecureModeConfig2.oppEnable = bluetoothSecureModeConfig.oppEnable;
        bluetoothSecureModeConfig2.pairingMode = bluetoothSecureModeConfig.pairingMode;
        bluetoothSecureModeConfig2.panEnable = bluetoothSecureModeConfig.panEnable;
        bluetoothSecureModeConfig2.pbapEnable = bluetoothSecureModeConfig.pbapEnable;
        bluetoothSecureModeConfig2.sapEnable = bluetoothSecureModeConfig.sapEnable;
        bluetoothSecureModeConfig2.scanMode = bluetoothSecureModeConfig.scanMode;
        bluetoothSecureModeConfig2.whitelistEnable = bluetoothSecureModeConfig.whitelistEnable;
        return bluetoothSecureModeConfig2;
    }

    static android.app.enterprise.BluetoothSecureModeConfig convertToOld(BluetoothSecureModeConfig bluetoothSecureModeConfig) {
        if (bluetoothSecureModeConfig == null) {
            return null;
        }
        android.app.enterprise.BluetoothSecureModeConfig bluetoothSecureModeConfig2 = new android.app.enterprise.BluetoothSecureModeConfig();
        bluetoothSecureModeConfig2.a2dpEnable = bluetoothSecureModeConfig.a2dpEnable;
        bluetoothSecureModeConfig2.ftpEnable = bluetoothSecureModeConfig.ftpEnable;
        bluetoothSecureModeConfig2.gattEnable = bluetoothSecureModeConfig.gattEnable;
        bluetoothSecureModeConfig2.hdpEnable = bluetoothSecureModeConfig.hdpEnable;
        bluetoothSecureModeConfig2.hfpEnable = bluetoothSecureModeConfig.hfpEnable;
        bluetoothSecureModeConfig2.hidEnable = bluetoothSecureModeConfig.hidEnable;
        bluetoothSecureModeConfig2.mapEnable = bluetoothSecureModeConfig.mapEnable;
        bluetoothSecureModeConfig2.oppEnable = bluetoothSecureModeConfig.oppEnable;
        bluetoothSecureModeConfig2.pairingMode = bluetoothSecureModeConfig.pairingMode;
        bluetoothSecureModeConfig2.panEnable = bluetoothSecureModeConfig.panEnable;
        bluetoothSecureModeConfig2.pbapEnable = bluetoothSecureModeConfig.pbapEnable;
        bluetoothSecureModeConfig2.sapEnable = bluetoothSecureModeConfig.sapEnable;
        bluetoothSecureModeConfig2.scanMode = bluetoothSecureModeConfig.scanMode;
        bluetoothSecureModeConfig2.whitelistEnable = bluetoothSecureModeConfig.whitelistEnable;
        return bluetoothSecureModeConfig2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.scanMode = parcel.readInt() != 0;
        this.pairingMode = parcel.readInt() != 0;
        this.hfpEnable = parcel.readInt() != 0;
        this.a2dpEnable = parcel.readInt() != 0;
        this.hidEnable = parcel.readInt() != 0;
        this.hdpEnable = parcel.readInt() != 0;
        this.panEnable = parcel.readInt() != 0;
        this.oppEnable = parcel.readInt() != 0;
        this.pbapEnable = parcel.readInt() != 0;
        this.mapEnable = parcel.readInt() != 0;
        this.ftpEnable = parcel.readInt() != 0;
        this.sapEnable = parcel.readInt() != 0;
        this.whitelistEnable = parcel.readInt() != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.scanMode ? 1 : 0);
        parcel.writeInt(this.pairingMode ? 1 : 0);
        parcel.writeInt(this.hfpEnable ? 1 : 0);
        parcel.writeInt(this.a2dpEnable ? 1 : 0);
        parcel.writeInt(this.hidEnable ? 1 : 0);
        parcel.writeInt(this.hdpEnable ? 1 : 0);
        parcel.writeInt(this.panEnable ? 1 : 0);
        parcel.writeInt(this.oppEnable ? 1 : 0);
        parcel.writeInt(this.pbapEnable ? 1 : 0);
        parcel.writeInt(this.mapEnable ? 1 : 0);
        parcel.writeInt(this.ftpEnable ? 1 : 0);
        parcel.writeInt(this.sapEnable ? 1 : 0);
        parcel.writeInt(this.whitelistEnable ? 1 : 0);
    }
}

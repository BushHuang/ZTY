package com.samsung.android.knox.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BluetoothSecureModeWhitelistConfig implements Parcelable {
    public static final Parcelable.Creator<BluetoothSecureModeWhitelistConfig> CREATOR = new Parcelable.Creator<BluetoothSecureModeWhitelistConfig>() {
        @Override
        public BluetoothSecureModeWhitelistConfig createFromParcel(Parcel parcel) {
            return new BluetoothSecureModeWhitelistConfig(parcel);
        }

        @Override
        public BluetoothSecureModeWhitelistConfig[] newArray(int i) {
            return new BluetoothSecureModeWhitelistConfig[i];
        }
    };
    public int cod;
    public String name;
    public String[] uuids;

    public BluetoothSecureModeWhitelistConfig() {
    }

    private BluetoothSecureModeWhitelistConfig(Parcel parcel) {
        readFromParcel(parcel);
    }

    public BluetoothSecureModeWhitelistConfig(String str, int i, String[] strArr) {
        this.name = str;
        this.cod = i;
        this.uuids = strArr;
    }

    static BluetoothSecureModeWhitelistConfig convertToNew(android.app.enterprise.BluetoothSecureModeWhitelistConfig bluetoothSecureModeWhitelistConfig) {
        if (bluetoothSecureModeWhitelistConfig == null) {
            return null;
        }
        return new BluetoothSecureModeWhitelistConfig(bluetoothSecureModeWhitelistConfig.name, bluetoothSecureModeWhitelistConfig.cod, bluetoothSecureModeWhitelistConfig.uuids);
    }

    static List<BluetoothSecureModeWhitelistConfig> convertToNewList(List<android.app.enterprise.BluetoothSecureModeWhitelistConfig> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<android.app.enterprise.BluetoothSecureModeWhitelistConfig> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static android.app.enterprise.BluetoothSecureModeWhitelistConfig convertToOld(BluetoothSecureModeWhitelistConfig bluetoothSecureModeWhitelistConfig) {
        if (bluetoothSecureModeWhitelistConfig == null) {
            return null;
        }
        return new android.app.enterprise.BluetoothSecureModeWhitelistConfig(bluetoothSecureModeWhitelistConfig.name, bluetoothSecureModeWhitelistConfig.cod, bluetoothSecureModeWhitelistConfig.uuids);
    }

    static List<android.app.enterprise.BluetoothSecureModeWhitelistConfig> convertToOldList(List<BluetoothSecureModeWhitelistConfig> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<BluetoothSecureModeWhitelistConfig> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToOld(it.next()));
        }
        return arrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.name = parcel.readString();
        this.cod = parcel.readInt();
        int i = parcel.readInt();
        if (i <= 0) {
            this.uuids = null;
            return;
        }
        String[] strArr = new String[i];
        this.uuids = strArr;
        parcel.readStringArray(strArr);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.name);
        parcel.writeInt(this.cod);
        String[] strArr = this.uuids;
        if (strArr == null || strArr.length <= 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(strArr.length);
            parcel.writeStringArray(this.uuids);
        }
    }
}

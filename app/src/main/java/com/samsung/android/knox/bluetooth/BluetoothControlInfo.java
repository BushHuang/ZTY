package com.samsung.android.knox.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.ControlInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BluetoothControlInfo extends ControlInfo {
    public static final Parcelable.Creator<BluetoothControlInfo> CREATOR = new Parcelable.Creator<BluetoothControlInfo>() {
        @Override
        public BluetoothControlInfo createFromParcel(Parcel parcel) {
            return new BluetoothControlInfo(parcel);
        }

        @Override
        public BluetoothControlInfo[] newArray(int i) {
            return new BluetoothControlInfo[i];
        }
    };

    public BluetoothControlInfo() {
    }

    private BluetoothControlInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    static BluetoothControlInfo convertToNew(android.app.enterprise.BluetoothControlInfo bluetoothControlInfo) {
        if (bluetoothControlInfo == null) {
            return null;
        }
        BluetoothControlInfo bluetoothControlInfo2 = new BluetoothControlInfo();
        bluetoothControlInfo2.adminPackageName = bluetoothControlInfo.adminPackageName;
        bluetoothControlInfo2.entries = new ArrayList(bluetoothControlInfo.entries);
        return bluetoothControlInfo2;
    }

    static List<BluetoothControlInfo> convertToNewList(List<android.app.enterprise.BluetoothControlInfo> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<android.app.enterprise.BluetoothControlInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }
}

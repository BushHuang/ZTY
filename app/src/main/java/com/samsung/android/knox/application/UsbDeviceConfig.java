package com.samsung.android.knox.application;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsbDeviceConfig implements Parcelable {
    public static final Parcelable.Creator<UsbDeviceConfig> CREATOR = new Parcelable.Creator<UsbDeviceConfig>() {
        @Override
        public UsbDeviceConfig createFromParcel(Parcel parcel) {
            return new UsbDeviceConfig(parcel);
        }

        @Override
        public UsbDeviceConfig[] newArray(int i) {
            return new UsbDeviceConfig[i];
        }
    };
    public int productId;
    public int vendorId;

    public UsbDeviceConfig() {
    }

    public UsbDeviceConfig(int i, int i2) {
        this.vendorId = i;
        this.productId = i2;
    }

    private UsbDeviceConfig(Parcel parcel) {
        readFromParcel(parcel);
    }

    static UsbDeviceConfig convertToNew(android.app.enterprise.UsbDeviceConfig usbDeviceConfig) {
        if (usbDeviceConfig == null) {
            return null;
        }
        UsbDeviceConfig usbDeviceConfig2 = new UsbDeviceConfig();
        usbDeviceConfig2.productId = usbDeviceConfig.productId;
        usbDeviceConfig2.vendorId = usbDeviceConfig.vendorId;
        return usbDeviceConfig2;
    }

    static List<UsbDeviceConfig> convertToNewList(List<android.app.enterprise.UsbDeviceConfig> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<android.app.enterprise.UsbDeviceConfig> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static android.app.enterprise.UsbDeviceConfig convertToOld(UsbDeviceConfig usbDeviceConfig) throws NoClassDefFoundError {
        if (usbDeviceConfig == null) {
            return null;
        }
        try {
            android.app.enterprise.UsbDeviceConfig usbDeviceConfig2 = new android.app.enterprise.UsbDeviceConfig();
            usbDeviceConfig2.vendorId = usbDeviceConfig.vendorId;
            usbDeviceConfig2.productId = usbDeviceConfig.productId;
            return usbDeviceConfig2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(UsbDeviceConfig.class, 12));
        }
    }

    public static List<android.app.enterprise.UsbDeviceConfig> convertToOldList(List<UsbDeviceConfig> list) throws NoClassDefFoundError {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<UsbDeviceConfig> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToOld(it.next()));
        }
        return arrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        UsbDeviceConfig usbDeviceConfig;
        int i;
        int i2;
        return obj != null && (obj instanceof UsbDeviceConfig) && (i = (usbDeviceConfig = (UsbDeviceConfig) obj).vendorId) > 0 && (i2 = usbDeviceConfig.productId) > 0 && this.vendorId == i && this.productId == i2;
    }

    public void readFromParcel(Parcel parcel) {
        this.vendorId = parcel.readInt();
        this.productId = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.vendorId);
        parcel.writeInt(this.productId);
    }
}

package com.samsung.android.knox;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class ControlInfo implements Parcelable {
    public static final Parcelable.Creator<ControlInfo> CREATOR = new Parcelable.Creator<ControlInfo>() {
        @Override
        public ControlInfo createFromParcel(Parcel parcel) {
            return new ControlInfo(parcel);
        }

        @Override
        public ControlInfo[] newArray(int i) {
            return new ControlInfo[i];
        }
    };
    public String adminPackageName;
    public List<String> entries;

    public ControlInfo() {
        this.adminPackageName = null;
        this.entries = null;
    }

    private ControlInfo(Parcel parcel) {
        this.adminPackageName = null;
        this.entries = null;
        readFromParcel(parcel);
    }

    public static ControlInfo convertToNew(android.app.enterprise.ControlInfo controlInfo) {
        if (controlInfo == null) {
            return null;
        }
        ControlInfo controlInfo2 = new ControlInfo();
        controlInfo2.adminPackageName = controlInfo.adminPackageName;
        controlInfo2.entries = controlInfo.entries;
        return controlInfo2;
    }

    public static android.app.enterprise.ControlInfo convertToOld(ControlInfo controlInfo) {
        if (controlInfo == null) {
            return null;
        }
        android.app.enterprise.ControlInfo controlInfo2 = new android.app.enterprise.ControlInfo();
        controlInfo2.adminPackageName = controlInfo.adminPackageName;
        controlInfo2.entries = controlInfo.entries;
        return controlInfo2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.adminPackageName = parcel.readString();
        this.entries = parcel.createStringArrayList();
    }

    public String toString() {
        return "adminPackageName: " + this.adminPackageName + " ,appControlType: " + this.entries;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.adminPackageName);
        parcel.writeStringList(this.entries);
    }
}

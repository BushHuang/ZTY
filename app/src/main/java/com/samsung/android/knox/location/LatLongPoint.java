package com.samsung.android.knox.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class LatLongPoint implements Serializable, Parcelable {
    public static final Parcelable.Creator<LatLongPoint> CREATOR = new Parcelable.Creator<LatLongPoint>() {
        @Override
        public LatLongPoint createFromParcel(Parcel parcel) {
            return new LatLongPoint(parcel);
        }

        @Override
        public LatLongPoint[] newArray(int i) {
            return new LatLongPoint[i];
        }
    };
    private static final long serialVersionUID = 1;
    public double latitude;
    public double longitude;

    public LatLongPoint(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    private LatLongPoint(Parcel parcel) {
        readFromParcel(parcel);
    }

    static LatLongPoint convertToNew(android.app.enterprise.geofencing.LatLongPoint latLongPoint) {
        if (latLongPoint == null) {
            return null;
        }
        return new LatLongPoint(latLongPoint.latitude, latLongPoint.longitude);
    }

    static android.app.enterprise.geofencing.LatLongPoint convertToOld(LatLongPoint latLongPoint) {
        if (latLongPoint == null) {
            return null;
        }
        return new android.app.enterprise.geofencing.LatLongPoint(latLongPoint.latitude, latLongPoint.longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }
}

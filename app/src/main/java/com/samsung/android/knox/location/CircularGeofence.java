package com.samsung.android.knox.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class CircularGeofence extends Geofence implements Parcelable, Serializable {
    private static final long serialVersionUID = 1;
    public LatLongPoint center;
    public double radius;

    CircularGeofence(Parcel parcel) {
        readFromParcel(parcel);
    }

    public CircularGeofence(LatLongPoint latLongPoint, double d) {
        this.type = 1;
        this.center = latLongPoint;
        this.radius = d;
    }

    static CircularGeofence convertToNew(android.app.enterprise.geofencing.CircularGeofence circularGeofence) {
        if (circularGeofence == null) {
            return null;
        }
        CircularGeofence circularGeofence2 = new CircularGeofence(circularGeofence.center != null ? LatLongPoint.convertToNew(circularGeofence.center) : null, circularGeofence.radius);
        circularGeofence2.id = circularGeofence.id;
        circularGeofence2.type = circularGeofence.type;
        return circularGeofence2;
    }

    static android.app.enterprise.geofencing.CircularGeofence convertToOld(CircularGeofence circularGeofence) {
        if (circularGeofence == null) {
            return null;
        }
        android.app.enterprise.geofencing.CircularGeofence circularGeofence2 = new android.app.enterprise.geofencing.CircularGeofence(LatLongPoint.convertToOld(circularGeofence.center), circularGeofence.radius);
        circularGeofence2.id = circularGeofence.id;
        circularGeofence2.type = circularGeofence.type;
        return circularGeofence2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.center = LatLongPoint.CREATOR.createFromParcel(parcel);
        this.radius = parcel.readDouble();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        this.center.writeToParcel(parcel, i);
        parcel.writeDouble(this.radius);
    }
}

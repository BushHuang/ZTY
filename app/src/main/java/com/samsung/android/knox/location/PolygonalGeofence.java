package com.samsung.android.knox.location;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolygonalGeofence extends Geofence implements Serializable, Parcelable {
    private static final long serialVersionUID = 1;
    public double graceDistance;
    public List<LatLongPoint> points;

    PolygonalGeofence(Parcel parcel) {
        this.points = new ArrayList();
        readFromParcel(parcel);
    }

    public PolygonalGeofence(List<LatLongPoint> list, double d) {
        this.type = 2;
        this.points = list;
        this.graceDistance = d;
    }

    static PolygonalGeofence convertToNew(android.app.enterprise.geofencing.PolygonalGeofence polygonalGeofence) {
        ArrayList arrayList = null;
        if (polygonalGeofence == null) {
            return null;
        }
        if (polygonalGeofence.points != null) {
            arrayList = new ArrayList();
            Iterator it = polygonalGeofence.points.iterator();
            while (it.hasNext()) {
                arrayList.add(LatLongPoint.convertToNew((android.app.enterprise.geofencing.LatLongPoint) it.next()));
            }
        }
        PolygonalGeofence polygonalGeofence2 = new PolygonalGeofence(arrayList, polygonalGeofence.graceDistance);
        polygonalGeofence2.id = polygonalGeofence.id;
        polygonalGeofence2.type = polygonalGeofence.type;
        return polygonalGeofence2;
    }

    static android.app.enterprise.geofencing.PolygonalGeofence convertToOld(PolygonalGeofence polygonalGeofence) {
        ArrayList arrayList = null;
        if (polygonalGeofence == null) {
            return null;
        }
        if (polygonalGeofence.points != null) {
            arrayList = new ArrayList();
            Iterator<LatLongPoint> it = polygonalGeofence.points.iterator();
            while (it.hasNext()) {
                arrayList.add(LatLongPoint.convertToOld(it.next()));
            }
        }
        android.app.enterprise.geofencing.PolygonalGeofence polygonalGeofence2 = new android.app.enterprise.geofencing.PolygonalGeofence(arrayList, polygonalGeofence.graceDistance);
        polygonalGeofence2.id = polygonalGeofence.id;
        polygonalGeofence2.type = polygonalGeofence.type;
        return polygonalGeofence2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            LatLongPoint latLongPointCreateFromParcel = LatLongPoint.CREATOR.createFromParcel(parcel);
            if (latLongPointCreateFromParcel == null) {
                throw new ParcelFormatException("Parcel format exception");
            }
            this.points.add(latLongPointCreateFromParcel);
        }
        this.graceDistance = parcel.readDouble();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.points.size());
        for (int i2 = 0; i2 < this.points.size(); i2++) {
            this.points.get(i2).writeToParcel(parcel, i);
        }
        parcel.writeDouble(this.graceDistance);
    }
}

package com.samsung.android.knox.location;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinearGeofence extends Geofence implements Serializable, Parcelable {
    private static final long serialVersionUID = 1;
    public List<LatLongPoint> optimizedPoints;
    public List<LatLongPoint> points;
    public double width;

    LinearGeofence(Parcel parcel) {
        this.points = new ArrayList();
        readFromParcel(parcel);
    }

    public LinearGeofence(List<LatLongPoint> list, double d) {
        this.type = 3;
        this.points = list;
        this.width = d;
    }

    static LinearGeofence convertToNew(android.app.enterprise.geofencing.LinearGeofence linearGeofence) {
        ArrayList arrayList;
        if (linearGeofence.points != null) {
            arrayList = new ArrayList();
            Iterator it = linearGeofence.points.iterator();
            while (it.hasNext()) {
                arrayList.add(LatLongPoint.convertToNew((android.app.enterprise.geofencing.LatLongPoint) it.next()));
            }
        } else {
            arrayList = null;
        }
        LinearGeofence linearGeofence2 = new LinearGeofence(arrayList, linearGeofence.width);
        linearGeofence2.id = linearGeofence.id;
        linearGeofence2.type = linearGeofence.type;
        return linearGeofence2;
    }

    static android.app.enterprise.geofencing.LinearGeofence convertToOld(LinearGeofence linearGeofence) {
        ArrayList arrayList = null;
        if (linearGeofence == null) {
            return null;
        }
        if (linearGeofence.points != null) {
            arrayList = new ArrayList();
            Iterator<LatLongPoint> it = linearGeofence.points.iterator();
            while (it.hasNext()) {
                arrayList.add(LatLongPoint.convertToOld(it.next()));
            }
        }
        android.app.enterprise.geofencing.LinearGeofence linearGeofence2 = new android.app.enterprise.geofencing.LinearGeofence(arrayList, linearGeofence.width);
        linearGeofence2.id = linearGeofence.id;
        linearGeofence2.type = linearGeofence.type;
        return linearGeofence2;
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
        this.width = parcel.readDouble();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.points.size());
        for (int i2 = 0; i2 < this.points.size(); i2++) {
            this.points.get(i2).writeToParcel(parcel, i);
        }
        parcel.writeDouble(this.width);
    }
}

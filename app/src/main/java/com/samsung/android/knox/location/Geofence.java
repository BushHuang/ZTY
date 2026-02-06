package com.samsung.android.knox.location;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Geofence implements Parcelable {
    public static final Parcelable.Creator<Geofence> CREATOR = new Parcelable.Creator<Geofence>() {
        @Override
        public Geofence createFromParcel(Parcel parcel) {
            int iDataPosition = parcel.dataPosition();
            int i = parcel.readInt();
            parcel.setDataPosition(iDataPosition);
            return GeofenceFactory.createGeofence(i, parcel);
        }

        @Override
        public Geofence[] newArray(int i) {
            return null;
        }
    };
    public int id;
    public int type;

    static List<Geofence> convertToNewList(List<android.app.enterprise.geofencing.Geofence> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<android.app.enterprise.geofencing.Geofence> it = list.iterator();
        while (it.hasNext()) {
            android.app.enterprise.geofencing.CircularGeofence circularGeofence = (android.app.enterprise.geofencing.Geofence) it.next();
            arrayList.add(((android.app.enterprise.geofencing.Geofence) circularGeofence).type == 1 ? CircularGeofence.convertToNew(circularGeofence) : ((android.app.enterprise.geofencing.Geofence) circularGeofence).type == 2 ? PolygonalGeofence.convertToNew((android.app.enterprise.geofencing.PolygonalGeofence) circularGeofence) : ((android.app.enterprise.geofencing.Geofence) circularGeofence).type == 3 ? LinearGeofence.convertToNew((android.app.enterprise.geofencing.LinearGeofence) circularGeofence) : null);
        }
        return arrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.type = parcel.readInt();
        this.id = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.id);
    }
}

package com.samsung.android.knox.location;

import java.util.List;

public class Geofencing {
    public static final String ACTION_DEVICE_INSIDE_GEOFENCE = "com.samsung.android.knox.intent.action.DEVICE_INSIDE_GEOFENCE";
    public static final String ACTION_DEVICE_LOCATION_UNAVAILABLE = "com.samsung.android.knox.intent.action.DEVICE_LOCATION_UNAVAILABLE";
    public static final String ACTION_DEVICE_OUTSIDE_GEOFENCE = "com.samsung.android.knox.intent.action.DEVICE_OUTSIDE_GEOFENCE";
    public static final int ERROR_GEOFENCING_FAILED = -1;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_UNKNOWN = -2000;
    public static final String EXTRA_ID = "com.samsung.android.knox.intent.extra.ID";
    public static final String EXTRA_USER_ID = "com.samsung.android.knox.intent.extra.USER_ID";
    public static final int TYPE_CIRCLE = 1;
    public static final int TYPE_LINEAR = 3;
    public static final int TYPE_POLYGON = 2;
    private android.app.enterprise.geofencing.Geofencing mGeofencing;

    public Geofencing(android.app.enterprise.geofencing.Geofencing geofencing) {
        this.mGeofencing = geofencing;
    }

    public int createGeofence(Geofence geofence) {
        if (geofence.type == 1) {
            return this.mGeofencing.createGeofence(CircularGeofence.convertToOld((CircularGeofence) geofence));
        }
        if (geofence.type == 2) {
            return this.mGeofencing.createGeofence(PolygonalGeofence.convertToOld((PolygonalGeofence) geofence));
        }
        if (geofence.type == 3) {
            return this.mGeofencing.createGeofence(LinearGeofence.convertToOld((LinearGeofence) geofence));
        }
        return -1;
    }

    public boolean destroyGeofence(int i) {
        return this.mGeofencing.destroyGeofence(i);
    }

    public List<Geofence> getGeofences() {
        return Geofence.convertToNewList(this.mGeofencing.getGeofences());
    }

    public float getMinDistanceParameter() {
        return this.mGeofencing.getMinDistanceParameter();
    }

    public long getMinTimeParameter() {
        return this.mGeofencing.getMinTimeParameter();
    }

    public List<Integer> isDeviceInsideGeofence() {
        return this.mGeofencing.isDeviceInsideGeofence();
    }

    public boolean isGeofencingEnabled() {
        return this.mGeofencing.isGeofencingEnabled();
    }

    public boolean setMinDistanceParameter(float f) {
        return this.mGeofencing.setMinDistanceParameter(f);
    }

    public boolean setMinTimeParameter(long j) {
        return this.mGeofencing.setMinTimeParameter(j);
    }

    public boolean startGeofencing() {
        return this.mGeofencing.startGeofencing();
    }

    public boolean stopGeofencing() {
        return this.mGeofencing.stopGeofencing();
    }
}

package com.samsung.android.knox.location;

import java.util.List;

public class LocationPolicy {
    private android.app.enterprise.LocationPolicy mLocationPolicy;

    public LocationPolicy(android.app.enterprise.LocationPolicy locationPolicy) {
        this.mLocationPolicy = locationPolicy;
    }

    public List<String> getAllLocationProviders() {
        return this.mLocationPolicy.getAllLocationProviders();
    }

    public boolean getLocationProviderState(String str) {
        return this.mLocationPolicy.getLocationProviderState(str);
    }

    public boolean isGPSOn() {
        return this.mLocationPolicy.isGPSOn();
    }

    public boolean isGPSStateChangeAllowed() {
        return this.mLocationPolicy.isGPSStateChangeAllowed();
    }

    public boolean setGPSStateChangeAllowed(boolean z) {
        return this.mLocationPolicy.setGPSStateChangeAllowed(z);
    }

    public boolean setLocationProviderState(String str, boolean z) {
        return this.mLocationPolicy.setLocationProviderState(str, z);
    }

    public boolean startGPS(boolean z) {
        return this.mLocationPolicy.startGPS(z);
    }
}

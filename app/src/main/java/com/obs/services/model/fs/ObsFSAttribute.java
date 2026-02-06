package com.obs.services.model.fs;

import com.obs.services.model.ObjectMetadata;

public class ObsFSAttribute extends ObjectMetadata {
    private int mode = -1;

    public int getMode() {
        return this.mode;
    }

    public void setMode(int i) {
        this.mode = i;
    }
}

package com.xh.xhcore.common.config;

public class TinkerConfig {
    private boolean tinkerEnable = false;
    private boolean patchRestartOnScreenOff = true;
    private boolean patchRollbackOnScreenOff = true;

    public boolean isPatchRestartOnScreenOff() {
        return this.patchRestartOnScreenOff;
    }

    public boolean isPatchRollbackOnScreenOff() {
        return this.patchRollbackOnScreenOff;
    }

    public boolean isTinkerEnable() {
        return this.tinkerEnable;
    }

    public TinkerConfig setPatchRestartOnScreenOff(boolean z) {
        this.patchRestartOnScreenOff = z;
        return this;
    }

    public TinkerConfig setPatchRollbackOnScreenOff(boolean z) {
        this.patchRollbackOnScreenOff = z;
        return this;
    }

    public TinkerConfig setTinkerEnable(boolean z) {
        this.tinkerEnable = z;
        return this;
    }
}

package com.xuehai.provider.core.dto;

public class CPVDControl {
    public static final int CONTROL_TYPE_NONE = -1;
    public static final int CONTROL_TYPE_TOB = 0;
    public static final int CONTROL_TYPE_TOC = 1;
    private int controlType;

    public CPVDControl() {
        this.controlType = 1;
    }

    public CPVDControl(int i) {
        this.controlType = 1;
        this.controlType = i;
    }

    public static boolean isToBControlType(int i) {
        return i == 0;
    }

    public static boolean isToCControlType(int i) {
        return i == 1;
    }

    public int getControlType() {
        return this.controlType;
    }

    public boolean isToBControlType() {
        return isToBControlType(this.controlType);
    }

    public boolean isToCControlType() {
        return isToCControlType(this.controlType);
    }

    public void setControlType(int i) {
        this.controlType = i;
    }
}

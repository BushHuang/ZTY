package com.xuehai.launcher.event;

public class AidlEvent {
    private int cmdType;

    public static class Type {
        public static final int ACTIVITE_ADMIN = 10001;
    }

    public AidlEvent(int i) {
        this.cmdType = i;
    }

    public int getCmdType() {
        return this.cmdType;
    }

    public void setCmdType(int i) {
        this.cmdType = i;
    }
}

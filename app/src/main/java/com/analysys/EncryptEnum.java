package com.analysys;

public enum EncryptEnum {
    EMPTY(0),
    AES(1),
    AES_CBC(2);

    private final int type;

    EncryptEnum(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}

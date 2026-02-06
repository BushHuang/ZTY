package com.huawei.hms.api;

import java.util.List;

public class ProtocolNegotiate {
    private static ProtocolNegotiate b = new ProtocolNegotiate();

    private int f236a = 1;

    public static ProtocolNegotiate getInstance() {
        return b;
    }

    public int getVersion() {
        return this.f236a;
    }

    public int negotiate(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            this.f236a = 1;
            return 1;
        }
        if (list.contains(2)) {
            this.f236a = 2;
        } else {
            this.f236a = list.get(list.size() - 1).intValue();
        }
        return this.f236a;
    }
}

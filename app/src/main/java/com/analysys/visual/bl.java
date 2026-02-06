package com.analysys.visual;

import java.nio.ByteBuffer;

public interface bl {

    public enum a {
        CONTINUOUS,
        TEXT,
        BINARY,
        PING,
        PONG,
        CLOSING
    }

    boolean b();

    boolean c();

    boolean d();

    boolean e();

    a f();

    ByteBuffer g();
}

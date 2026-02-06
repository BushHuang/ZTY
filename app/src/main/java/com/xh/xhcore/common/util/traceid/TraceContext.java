package com.xh.xhcore.common.util.traceid;

public class TraceContext {
    public static String traceIdString(long j, long j2) {
        if (j == 0) {
            char[] cArr = new char[16];
            HexCodec.writeHexLong(cArr, 0, j2);
            return new String(cArr);
        }
        char[] cArr2 = new char[32];
        HexCodec.writeHexLong(cArr2, 0, j);
        HexCodec.writeHexLong(cArr2, 16, j2);
        return new String(cArr2);
    }
}

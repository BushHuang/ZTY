package com.samsung.android.knox.sdp.core;

public class SdpEngineConstants {

    public interface Flags {
        public static final int SDP_MDFPP = 0;
        public static final int SDP_MINOR = 1;
    }

    public interface Type {
        public static final int SDP_ENGINE_ANDROID_DEFAULT = 1;
        public static final int SDP_ENGINE_CUSTOM = 2;
        public static final int SDP_ENGINE_INVALID = -1;
    }
}

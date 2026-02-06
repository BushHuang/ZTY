package com.obs.services.model;

@Deprecated
public final class ServerEncryption {
    public static final ServerEncryption OBS_KMS = new ServerEncryption("kms");
    private String serverEncryption;

    private ServerEncryption(String str) {
        this.serverEncryption = "";
        this.serverEncryption = str;
    }

    public static ServerEncryption parseServerEncryption(String str) {
        if (str == null || !str.equals(OBS_KMS.toString())) {
            return null;
        }
        return OBS_KMS;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ServerEncryption) && toString().equals(obj.toString());
    }

    public String getServerEncryption() {
        return this.serverEncryption;
    }

    public int hashCode() {
        return this.serverEncryption.hashCode();
    }

    public String toString() {
        return this.serverEncryption;
    }
}

package com.obs.services.model;

@Deprecated
public final class ServerAlgorithm {
    public static final ServerAlgorithm AES256 = new ServerAlgorithm("AES256");
    private String serverAlgorithm;

    private ServerAlgorithm(String str) {
        this.serverAlgorithm = "";
        this.serverAlgorithm = str;
    }

    public static ServerAlgorithm parseServerAlgorithm(String str) {
        if (str == null || !str.equals(AES256.toString())) {
            return null;
        }
        return AES256;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ServerAlgorithm) && toString().equals(obj.toString());
    }

    public String getServerAlgorithm() {
        return this.serverAlgorithm;
    }

    public int hashCode() {
        return this.serverAlgorithm.hashCode();
    }

    public String toString() {
        return this.serverAlgorithm;
    }
}

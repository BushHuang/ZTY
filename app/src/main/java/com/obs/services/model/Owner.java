package com.obs.services.model;

public class Owner {
    private String displayName;
    private String id;

    @Deprecated
    public String getDisplayName() {
        return this.displayName;
    }

    public String getId() {
        return this.id;
    }

    @Deprecated
    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String toString() {
        return "Owner [displayName=" + this.displayName + ", id=" + this.id + "]";
    }
}

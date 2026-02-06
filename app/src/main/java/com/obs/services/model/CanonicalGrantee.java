package com.obs.services.model;

public class CanonicalGrantee implements GranteeInterface {
    private String displayName;
    private String grantId;

    public CanonicalGrantee() {
    }

    public CanonicalGrantee(String str) {
        this.grantId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CanonicalGrantee canonicalGrantee = (CanonicalGrantee) obj;
        String str = this.grantId;
        if (str == null) {
            if (canonicalGrantee.grantId != null) {
                return false;
            }
        } else if (!str.equals(canonicalGrantee.grantId)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getIdentifier() {
        return this.grantId;
    }

    public int hashCode() {
        String str = this.grantId;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    @Deprecated
    public void setDisplayName(String str) {
        this.displayName = str;
    }

    @Override
    public void setIdentifier(String str) {
        this.grantId = str;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("CanonicalGrantee [id=");
        sb.append(this.grantId);
        if (this.displayName != null) {
            str = ", displayName=" + this.displayName;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }
}

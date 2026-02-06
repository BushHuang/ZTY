package com.obs.services.model;

public class GrantAndPermission {
    private boolean delivered;
    private GranteeInterface grantee;
    private Permission permission;

    public GrantAndPermission(GranteeInterface granteeInterface, Permission permission) {
        this.grantee = granteeInterface;
        this.permission = permission;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GrantAndPermission grantAndPermission = (GrantAndPermission) obj;
        if (this.delivered != grantAndPermission.delivered) {
            return false;
        }
        GranteeInterface granteeInterface = this.grantee;
        if (granteeInterface == null) {
            if (grantAndPermission.grantee != null) {
                return false;
            }
        } else if (!granteeInterface.equals(grantAndPermission.grantee)) {
            return false;
        }
        Permission permission = this.permission;
        if (permission == null) {
            if (grantAndPermission.permission != null) {
                return false;
            }
        } else if (!permission.equals(grantAndPermission.permission)) {
            return false;
        }
        return true;
    }

    public GranteeInterface getGrantee() {
        return this.grantee;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public int hashCode() {
        int i = ((this.delivered ? 1231 : 1237) + 31) * 31;
        GranteeInterface granteeInterface = this.grantee;
        int iHashCode = (i + (granteeInterface == null ? 0 : granteeInterface.hashCode())) * 31;
        Permission permission = this.permission;
        return iHashCode + (permission != null ? permission.hashCode() : 0);
    }

    public boolean isDelivered() {
        return this.delivered;
    }

    public void setDelivered(boolean z) {
        this.delivered = z;
    }

    public String toString() {
        return "GrantAndPermission [grantee=" + this.grantee + ", permission=" + this.permission + ", delivered=" + this.delivered + "]";
    }
}

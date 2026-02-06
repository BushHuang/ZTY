package com.obs.services.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccessControlList extends HeaderResponse {
    private boolean delivered;
    private Set<GrantAndPermission> grants;
    private Owner owner;
    public static final AccessControlList REST_CANNED_PRIVATE = new AccessControlList();
    public static final AccessControlList REST_CANNED_PUBLIC_READ = new AccessControlList();
    public static final AccessControlList REST_CANNED_PUBLIC_READ_WRITE = new AccessControlList();
    public static final AccessControlList REST_CANNED_PUBLIC_READ_DELIVERED = new AccessControlList();
    public static final AccessControlList REST_CANNED_PUBLIC_READ_WRITE_DELIVERED = new AccessControlList();

    @Deprecated
    public static final AccessControlList REST_CANNED_AUTHENTICATED_READ = new AccessControlList();

    @Deprecated
    public static final AccessControlList REST_CANNED_BUCKET_OWNER_READ = new AccessControlList();

    @Deprecated
    public static final AccessControlList REST_CANNED_BUCKET_OWNER_FULL_CONTROL = new AccessControlList();

    @Deprecated
    public static final AccessControlList REST_CANNED_LOG_DELIVERY_WRITE = new AccessControlList();

    public GrantAndPermission[] getGrantAndPermissions() {
        return (GrantAndPermission[]) getGrants().toArray(new GrantAndPermission[getGrants().size()]);
    }

    public Set<GrantAndPermission> getGrants() {
        if (this.grants == null) {
            this.grants = new HashSet();
        }
        return this.grants;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public List<Permission> getPermissionsForGrantee(GranteeInterface granteeInterface) {
        ArrayList arrayList = new ArrayList();
        for (GrantAndPermission grantAndPermission : getGrants()) {
            if (grantAndPermission.getGrantee().equals(granteeInterface)) {
                arrayList.add(grantAndPermission.getPermission());
            }
        }
        return arrayList;
    }

    public void grantAllPermissions(GrantAndPermission[] grantAndPermissionArr) {
        for (GrantAndPermission grantAndPermission : grantAndPermissionArr) {
            grantPermission(grantAndPermission.getGrantee(), grantAndPermission.getPermission());
        }
    }

    public GrantAndPermission grantPermission(GranteeInterface granteeInterface, Permission permission) {
        GrantAndPermission grantAndPermission = new GrantAndPermission(granteeInterface, permission);
        getGrants().add(grantAndPermission);
        return grantAndPermission;
    }

    public boolean isDelivered() {
        return this.delivered;
    }

    public void setDelivered(boolean z) {
        this.delivered = z;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (GrantAndPermission grantAndPermission : getGrantAndPermissions()) {
            sb.append(grantAndPermission.toString());
            sb.append(",");
        }
        sb.append("]");
        return "AccessControlList [owner=" + this.owner + ", grants=" + sb.toString() + "]";
    }
}

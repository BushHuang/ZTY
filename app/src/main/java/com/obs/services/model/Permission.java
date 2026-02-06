package com.obs.services.model;

public final class Permission {
    private String permissionString;
    public static final Permission PERMISSION_FULL_CONTROL = new Permission("FULL_CONTROL");
    public static final Permission PERMISSION_READ = new Permission("READ");
    public static final Permission PERMISSION_WRITE = new Permission("WRITE");
    public static final Permission PERMISSION_READ_ACP = new Permission("READ_ACP");
    public static final Permission PERMISSION_WRITE_ACP = new Permission("WRITE_ACP");

    @Deprecated
    public static final Permission PERMISSION_READ_OBJECT = new Permission("READ_OBJECT");

    @Deprecated
    public static final Permission PERMISSION_FULL_CONTROL_OBJECT = new Permission("FULL_CONTROL_OBJECT");

    private Permission(String str) {
        this.permissionString = "";
        this.permissionString = str;
    }

    public static Permission parsePermission(String str) {
        return str.equals(PERMISSION_FULL_CONTROL.toString()) ? PERMISSION_FULL_CONTROL : str.equals(PERMISSION_READ.toString()) ? PERMISSION_READ : str.equals(PERMISSION_WRITE.toString()) ? PERMISSION_WRITE : str.equals(PERMISSION_READ_ACP.toString()) ? PERMISSION_READ_ACP : str.equals(PERMISSION_WRITE_ACP.toString()) ? PERMISSION_WRITE_ACP : str.equals(PERMISSION_READ_OBJECT.toString()) ? PERMISSION_READ_OBJECT : str.equals(PERMISSION_FULL_CONTROL_OBJECT.toString()) ? PERMISSION_FULL_CONTROL_OBJECT : new Permission(str);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Permission) && toString().equals(obj.toString());
    }

    public String getPermissionString() {
        return this.permissionString;
    }

    public int hashCode() {
        return this.permissionString.hashCode();
    }

    public String toString() {
        return this.permissionString;
    }
}

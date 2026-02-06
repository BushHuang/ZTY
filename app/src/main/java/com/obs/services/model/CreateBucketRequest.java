package com.obs.services.model;

import com.obs.services.internal.utils.ServiceUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreateBucketRequest {
    private AccessControlList acl;
    private AvailableZoneEnum availableZone;
    private String bucketName;
    private String epid;
    private Map<String, String> extensionHeaderMap;
    private Map<ExtensionBucketPermissionEnum, Set<String>> extensionPermissionMap;
    private String location;
    private StorageClassEnum storageClass;

    public CreateBucketRequest() {
    }

    public CreateBucketRequest(String str) {
        this.bucketName = str;
    }

    public CreateBucketRequest(String str, String str2) {
        this.bucketName = str;
        this.location = str2;
    }

    private void setExtensionHeaderMap(Map<String, String> map) {
        this.extensionHeaderMap = map;
    }

    public AccessControlList getAcl() {
        return this.acl;
    }

    public Set<ExtensionBucketPermissionEnum> getAllGrantPermissions() {
        return getExtensionPermissionMap().keySet();
    }

    public AvailableZoneEnum getAvailableZone() {
        return this.availableZone;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public StorageClassEnum getBucketStorageClass() {
        return this.storageClass;
    }

    public Set<String> getDomainIdsByGrantPermission(ExtensionBucketPermissionEnum extensionBucketPermissionEnum) {
        Set<String> set = getExtensionPermissionMap().get(extensionBucketPermissionEnum);
        return set == null ? new HashSet() : set;
    }

    public String getEpid() {
        return this.epid;
    }

    public Map<String, String> getExtensionHeaderMap() {
        return this.extensionHeaderMap;
    }

    Map<ExtensionBucketPermissionEnum, Set<String>> getExtensionPermissionMap() {
        if (this.extensionPermissionMap == null) {
            this.extensionPermissionMap = new HashMap();
        }
        return this.extensionPermissionMap;
    }

    public Set<ExtensionBucketPermissionEnum> getGrantPermissionsByDomainId(String str) {
        HashSet hashSet = new HashSet();
        if (ServiceUtils.isValid(str)) {
            for (Map.Entry<ExtensionBucketPermissionEnum, Set<String>> entry : getExtensionPermissionMap().entrySet()) {
                if (entry.getValue().contains(str.trim())) {
                    hashSet.add(entry.getKey());
                }
            }
        }
        return hashSet;
    }

    public String getLocation() {
        return this.location;
    }

    public void grantExtensionPermission(String str, ExtensionBucketPermissionEnum extensionBucketPermissionEnum) {
        if (extensionBucketPermissionEnum == null || !ServiceUtils.isValid(str)) {
            return;
        }
        Set<String> hashSet = getExtensionPermissionMap().get(extensionBucketPermissionEnum);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            getExtensionPermissionMap().put(extensionBucketPermissionEnum, hashSet);
        }
        hashSet.add(str.trim());
    }

    public void setAcl(AccessControlList accessControlList) {
        this.acl = accessControlList;
    }

    public void setAvailableZone(AvailableZoneEnum availableZoneEnum) {
        this.availableZone = availableZoneEnum;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setBucketStorageClass(StorageClassEnum storageClassEnum) {
        this.storageClass = storageClassEnum;
    }

    public void setEpid(String str) {
        this.epid = str;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public String toString() {
        return "CreateBucketRequest [bucketName=" + this.bucketName + ", location=" + this.location + ", storageClass=" + this.storageClass + ", acl=" + this.acl + ", extensionPermissionMap=" + this.extensionPermissionMap + ", availableZone=" + this.availableZone + ",epid=" + this.epid + "]";
    }

    public void withdrawExtensionPermission(String str, ExtensionBucketPermissionEnum extensionBucketPermissionEnum) {
        if (extensionBucketPermissionEnum == null || !ServiceUtils.isValid(str)) {
            return;
        }
        String strTrim = str.trim();
        Set<String> set = getExtensionPermissionMap().get(extensionBucketPermissionEnum);
        if (set == null || !set.contains(strTrim)) {
            return;
        }
        set.remove(strTrim);
    }

    public void withdrawExtensionPermissions(String str) {
        if (ServiceUtils.isValid(str)) {
            String strTrim = str.trim();
            for (Map.Entry<ExtensionBucketPermissionEnum, Set<String>> entry : getExtensionPermissionMap().entrySet()) {
                if (entry.getValue().contains(strTrim)) {
                    entry.getValue().remove(strTrim);
                }
            }
        }
    }
}

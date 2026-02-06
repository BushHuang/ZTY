package com.obs.services.model;

import com.obs.services.internal.utils.ServiceUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PutObjectBasicRequest {
    protected AccessControlList acl;
    protected String bucketName;
    protected Map<ExtensionObjectPermissionEnum, Set<String>> extensionPermissionMap;
    protected String objectKey;
    protected SseCHeader sseCHeader;
    protected SseKmsHeader sseKmsHeader;
    protected String successRedirectLocation;

    public AccessControlList getAcl() {
        return this.acl;
    }

    public Set<ExtensionObjectPermissionEnum> getAllGrantPermissions() {
        return getExtensionPermissionMap().keySet();
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public Set<String> getDomainIdsByGrantPermission(ExtensionObjectPermissionEnum extensionObjectPermissionEnum) {
        Set<String> set = getExtensionPermissionMap().get(extensionObjectPermissionEnum);
        return set == null ? new HashSet() : set;
    }

    public Map<ExtensionObjectPermissionEnum, Set<String>> getExtensionPermissionMap() {
        if (this.extensionPermissionMap == null) {
            this.extensionPermissionMap = new HashMap();
        }
        return this.extensionPermissionMap;
    }

    public Set<ExtensionObjectPermissionEnum> getGrantPermissionsByDomainId(String str) {
        HashSet hashSet = new HashSet();
        if (ServiceUtils.isValid(str)) {
            String strTrim = str.trim();
            for (Map.Entry<ExtensionObjectPermissionEnum, Set<String>> entry : getExtensionPermissionMap().entrySet()) {
                if (entry.getValue().contains(strTrim)) {
                    hashSet.add(entry.getKey());
                }
            }
        }
        return hashSet;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public SseCHeader getSseCHeader() {
        return this.sseCHeader;
    }

    public SseKmsHeader getSseKmsHeader() {
        return this.sseKmsHeader;
    }

    public String getSuccessRedirectLocation() {
        return this.successRedirectLocation;
    }

    public void grantExtensionPermission(String str, ExtensionObjectPermissionEnum extensionObjectPermissionEnum) {
        if (extensionObjectPermissionEnum == null || !ServiceUtils.isValid(str)) {
            return;
        }
        Set<String> hashSet = getExtensionPermissionMap().get(extensionObjectPermissionEnum);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            getExtensionPermissionMap().put(extensionObjectPermissionEnum, hashSet);
        }
        hashSet.add(str.trim());
    }

    public void setAcl(AccessControlList accessControlList) {
        this.acl = accessControlList;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setExtensionPermissionMap(Map<ExtensionObjectPermissionEnum, Set<String>> map) {
        if (map == null) {
            return;
        }
        this.extensionPermissionMap = map;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setSseCHeader(SseCHeader sseCHeader) {
        this.sseCHeader = sseCHeader;
    }

    public void setSseKmsHeader(SseKmsHeader sseKmsHeader) {
        this.sseKmsHeader = sseKmsHeader;
    }

    public void setSuccessRedirectLocation(String str) {
        this.successRedirectLocation = str;
    }

    public void withdrawExtensionPermission(String str, ExtensionObjectPermissionEnum extensionObjectPermissionEnum) {
        if (extensionObjectPermissionEnum == null || !ServiceUtils.isValid(str)) {
            return;
        }
        String strTrim = str.trim();
        Set<String> set = getExtensionPermissionMap().get(extensionObjectPermissionEnum);
        if (set == null || !set.contains(strTrim)) {
            return;
        }
        set.remove(strTrim);
    }

    public void withdrawExtensionPermissions(String str) {
        if (ServiceUtils.isValid(str)) {
            for (Map.Entry<ExtensionObjectPermissionEnum, Set<String>> entry : getExtensionPermissionMap().entrySet()) {
                if (entry.getValue().contains(str.trim())) {
                    entry.getValue().remove(str);
                }
            }
        }
    }
}

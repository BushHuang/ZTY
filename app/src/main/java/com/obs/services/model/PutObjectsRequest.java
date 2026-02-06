package com.obs.services.model;

import com.obs.services.internal.utils.ServiceUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PutObjectsRequest extends AbstractBulkRequest {
    private AccessControlList acl;
    private long bigfileThreshold;
    private TaskCallback<PutObjectResult, PutObjectBasicRequest> callback;
    private long detailProgressInterval;
    private Map<ExtensionObjectPermissionEnum, Set<String>> extensionPermissionMap;
    private List<String> filePaths;
    private String folderPath;
    private UploadObjectsProgressListener listener;
    private long partSize;
    private String prefix;
    private SseCHeader sseCHeader;
    private SseKmsHeader sseKmsHeader;
    private String successRedirectLocation;
    private int taskNum;
    private long taskProgressInterval;

    public PutObjectsRequest(String str, String str2) {
        super(str);
        this.partSize = 5242880L;
        this.bigfileThreshold = 104857600L;
        this.taskNum = 1;
        this.taskProgressInterval = 512000L;
        this.detailProgressInterval = 102400L;
        this.folderPath = str2;
    }

    public PutObjectsRequest(String str, List<String> list) {
        super(str);
        this.partSize = 5242880L;
        this.bigfileThreshold = 104857600L;
        this.taskNum = 1;
        this.taskProgressInterval = 512000L;
        this.detailProgressInterval = 102400L;
        this.filePaths = list;
    }

    private String getFilePathsString() {
        List<String> list = this.filePaths;
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = this.filePaths.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            stringBuffer.append(", ");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 2);
    }

    public AccessControlList getAcl() {
        return this.acl;
    }

    public Set<ExtensionObjectPermissionEnum> getAllGrantPermissions() {
        return getExtensionPermissionMap().keySet();
    }

    public long getBigfileThreshold() {
        return this.bigfileThreshold;
    }

    public TaskCallback<PutObjectResult, PutObjectBasicRequest> getCallback() {
        return this.callback;
    }

    public long getDetailProgressInterval() {
        return this.detailProgressInterval;
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

    public List<String> getFilePaths() {
        return this.filePaths;
    }

    public String getFolderPath() {
        return this.folderPath;
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

    public long getPartSize() {
        return this.partSize;
    }

    public String getPrefix() {
        return this.prefix;
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

    public int getTaskNum() {
        return this.taskNum;
    }

    public long getTaskProgressInterval() {
        return this.taskProgressInterval;
    }

    public UploadObjectsProgressListener getUploadObjectsProgressListener() {
        return this.listener;
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

    public void setBigfileThreshold(long j) {
        if (j < 102400) {
            return;
        }
        if (j > 5368709120L) {
            this.bigfileThreshold = 5368709120L;
        } else {
            this.bigfileThreshold = j;
        }
    }

    public void setCallback(TaskCallback<PutObjectResult, PutObjectBasicRequest> taskCallback) {
        this.callback = taskCallback;
    }

    public void setDetailProgressInterval(long j) {
        this.detailProgressInterval = j;
    }

    public void setExtensionPermissionMap(Map<ExtensionObjectPermissionEnum, Set<String>> map) {
        if (map == null) {
            return;
        }
        this.extensionPermissionMap = map;
    }

    public void setPartSize(long j) {
        this.partSize = j;
    }

    public void setPrefix(String str) {
        if (str == null) {
            return;
        }
        if (str.endsWith("/")) {
            this.prefix = str;
            return;
        }
        this.prefix = str + "/";
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

    public void setTaskNum(int i) {
        if (i < 1) {
            this.taskNum = 1;
        } else if (i > 1000) {
            this.taskNum = 1000;
        } else {
            this.taskNum = i;
        }
    }

    public void setTaskProgressInterval(long j) {
        long j2 = this.detailProgressInterval;
        if (j < j2) {
            this.taskProgressInterval = j2;
        } else {
            this.taskProgressInterval = j;
        }
    }

    public void setUploadObjectsProgressListener(UploadObjectsProgressListener uploadObjectsProgressListener) {
        this.listener = uploadObjectsProgressListener;
    }

    public String toString() {
        return "PutObjectsRequest [bucketName=" + this.bucketName + ", folderPath=" + this.folderPath + ", listFilePath=" + getFilePathsString() + "]";
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

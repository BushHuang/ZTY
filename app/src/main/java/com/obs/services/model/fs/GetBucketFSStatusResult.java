package com.obs.services.model.fs;

import com.obs.services.model.AvailableZoneEnum;
import com.obs.services.model.BucketMetadataInfoResult;
import com.obs.services.model.StorageClassEnum;
import java.util.List;

public class GetBucketFSStatusResult extends BucketMetadataInfoResult {
    private FSStatusEnum status;

    public GetBucketFSStatusResult(String str, List<String> list, int i, List<String> list2, List<String> list3, StorageClassEnum storageClassEnum, String str2, String str3) {
        super(str, list, i, list2, list3, storageClassEnum, str2, str3);
    }

    public GetBucketFSStatusResult(String str, List<String> list, int i, List<String> list2, List<String> list3, StorageClassEnum storageClassEnum, String str2, String str3, FSStatusEnum fSStatusEnum) {
        this(str, list, i, list2, list3, storageClassEnum, str2, str3);
        this.status = fSStatusEnum;
    }

    public GetBucketFSStatusResult(String str, List<String> list, int i, List<String> list2, List<String> list3, StorageClassEnum storageClassEnum, String str2, String str3, FSStatusEnum fSStatusEnum, AvailableZoneEnum availableZoneEnum) {
        super(str, list, i, list2, list3, storageClassEnum, str2, str3, availableZoneEnum);
        this.status = fSStatusEnum;
    }

    public GetBucketFSStatusResult(String str, List<String> list, int i, List<String> list2, List<String> list3, StorageClassEnum storageClassEnum, String str2, String str3, FSStatusEnum fSStatusEnum, AvailableZoneEnum availableZoneEnum, String str4) {
        super(str, list, i, list2, list3, storageClassEnum, str2, str3, availableZoneEnum, str4);
        this.status = fSStatusEnum;
    }

    public FSStatusEnum getStatus() {
        return this.status;
    }
}

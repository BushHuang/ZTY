package com.obs.services.model.fs;

import com.obs.services.model.BucketMetadataInfoRequest;

public class GetBucketFSStatusRequest extends BucketMetadataInfoRequest {
    public GetBucketFSStatusRequest() {
    }

    public GetBucketFSStatusRequest(String str) {
        this.bucketName = str;
    }
}

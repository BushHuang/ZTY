package com.obs.services.model.fs;

import com.obs.services.model.CreateBucketRequest;

public class NewBucketRequest extends CreateBucketRequest {
    public NewBucketRequest() {
    }

    public NewBucketRequest(String str) {
        super(str);
    }

    public NewBucketRequest(String str, String str2) {
        super(str, str2);
    }
}

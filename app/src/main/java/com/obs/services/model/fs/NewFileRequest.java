package com.obs.services.model.fs;

import com.obs.services.model.PutObjectRequest;
import java.io.File;
import java.io.InputStream;

public class NewFileRequest extends PutObjectRequest {
    public NewFileRequest() {
    }

    public NewFileRequest(String str, String str2) {
        super(str, str2);
    }

    public NewFileRequest(String str, String str2, File file) {
        super(str, str2, file);
    }

    public NewFileRequest(String str, String str2, InputStream inputStream) {
        super(str, str2, inputStream);
    }
}

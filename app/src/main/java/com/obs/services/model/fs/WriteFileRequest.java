package com.obs.services.model.fs;

import com.obs.services.model.AppendObjectRequest;
import java.io.File;
import java.io.InputStream;

public class WriteFileRequest extends AppendObjectRequest {
    public WriteFileRequest() {
    }

    public WriteFileRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public WriteFileRequest(String str, String str2, File file) {
        this(str, str2);
        this.file = file;
    }

    public WriteFileRequest(String str, String str2, File file, long j) {
        this(str, str2, file);
        this.position = j;
    }

    public WriteFileRequest(String str, String str2, InputStream inputStream) {
        this(str, str2);
        this.input = inputStream;
    }

    public WriteFileRequest(String str, String str2, InputStream inputStream, long j) {
        this(str, str2, inputStream);
        this.position = j;
    }
}

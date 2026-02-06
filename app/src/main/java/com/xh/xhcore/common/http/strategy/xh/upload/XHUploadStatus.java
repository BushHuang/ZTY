package com.xh.xhcore.common.http.strategy.xh.upload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface XHUploadStatus {
    public static final int MULTI_FILE_ZIP = 3;
    public static final int UPLOAD_OSS_LARGE_FILE = 8;
    public static final int UPLOAD_OSS_MULTI_FILE = 9;
    public static final int UPLOAD_OSS_NORMAL_FILE = 7;
    public static final int UPLOAD_SERVER_LARGE_FILE = 5;
    public static final int UPLOAD_SERVER_MULTI_FILE = 6;
    public static final int UPLOAD_SERVER_NORMAL_FILE = 4;
    public static final int UPLOAD_START_LARGE_FILE = 1;
    public static final int UPLOAD_START_MULTI_FILE = 2;
    public static final int UPLOAD_START_NORMAL_FILE = 0;
}

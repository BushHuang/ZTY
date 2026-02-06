package com.xuehai.platform.file;

import android.content.Context;
import android.os.Environment;

public class FilePathManager {
    public static final String BASE_PATH = Environment.getExternalStorageDirectory() + "/xuehai";
    private String packageName;
    private long schoolId;
    private long userId;

    private FilePathManager(Context context, long j, long j2) {
        this.packageName = context.getPackageName();
        this.schoolId = j;
        this.userId = j2;
    }

    public static FilePathManager newInstance(Context context, long j, long j2) {
        return new FilePathManager(context, j, j2);
    }

    public String getAppCacheFileDir() {
        return getAppFileDir() + "/cache";
    }

    public String getAppDatabaseDir() {
        return String.format("%s/%s/databases/%s/%s", BASE_PATH, Long.valueOf(this.schoolId), this.packageName, Long.valueOf(this.userId));
    }

    public String getAppFileDir() {
        return String.format("%s/%s/filebases/%s", BASE_PATH, Long.valueOf(this.schoolId), this.packageName);
    }
}

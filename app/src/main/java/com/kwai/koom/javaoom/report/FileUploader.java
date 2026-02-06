package com.kwai.koom.javaoom.report;

import java.io.File;

public interface FileUploader {

    public final class CC {
        public static boolean $default$deleteWhenUploaded(FileUploader fileUploader) {
            return true;
        }
    }

    boolean deleteWhenUploaded();

    void upload(File file);
}

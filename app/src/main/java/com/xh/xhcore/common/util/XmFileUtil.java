package com.xh.xhcore.common.util;

import java.io.File;
import java.io.IOException;

public class XmFileUtil {
    public static boolean createFileNotExists(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        File parentFile = new File(str).getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            XHLog.w("", e.getMessage());
            return false;
        }
    }
}

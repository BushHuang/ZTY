package com.xh.networkclient.common;

import java.util.HashMap;

public enum Code {
	CODE_SUCCESS(0),
	CODE_FILE_NOT_COMPLETE(2001),
	CODE_FS_FAIL(2002),
	CODE_ADD_DOWNLOAD_TASK_FAIL(2003),
	CODE_BASEINFO_UPLOAD_SUCCESS(2004),
	CODE_ADD_UPLOAD_FILE_TASK_FAIL(2005),
	CODE_ADD_UPDATE_FILEURL_TASK_FAIL(2006),
	CODE_FILE_OTHER(3000);
	//4000+错误码段给老于用

    private final int value;

    private static HashMap<Integer, Code> valuesMap = new HashMap<Integer, Code>();

    static {
        for (Code e : values()) {
            valuesMap.put(e.getValue(), e);
        }
    }

    Code(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Code fromValue(int value) {
        return valuesMap.get(value);
    }
}
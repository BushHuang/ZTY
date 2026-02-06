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

    private static HashMap<Integer, Code> valuesMap = new HashMap<>();
    private final int value;

    static {
        for (Code code : valuesCustom()) {
            valuesMap.put(Integer.valueOf(code.getValue()), code);
        }
    }

    Code(int i) {
        this.value = i;
    }

    public static Code fromValue(int i) {
        return valuesMap.get(Integer.valueOf(i));
    }

    public static Code[] valuesCustom() {
        Code[] codeArrValuesCustom = values();
        int length = codeArrValuesCustom.length;
        Code[] codeArr = new Code[length];
        System.arraycopy(codeArrValuesCustom, 0, codeArr, 0, length);
        return codeArr;
    }

    public int getValue() {
        return this.value;
    }
}

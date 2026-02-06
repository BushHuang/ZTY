package com.huawei.hms.framework.common.grs;

import com.huawei.hms.framework.common.StringUtils;
import java.util.Locale;

public class GrsUtils {
    private static final int GRS_KEY_INDEX = 1;
    private static final int GRS_PATH_INDEX = 2;
    private static final String GRS_SCHEMA = "grs://";
    private static final int GRS_SERVICE_INDEX = 0;
    private static final int MAX_GRS_SPLIT = 3;
    private static final String SEPARATOR = "/";

    public static String fixResult(String[] strArr, String str) {
        if (strArr.length <= 2) {
            return str;
        }
        if (str.endsWith("/")) {
            return str + strArr[2];
        }
        return str + "/" + strArr[2];
    }

    public static boolean isGRSSchema(String str) {
        return str != null && str.startsWith("grs://");
    }

    public static String[] parseGRSSchema(String str) {
        String[] strArrSplit = StringUtils.substring(str, str.toLowerCase(Locale.ENGLISH).indexOf("grs://") + 6).split("/", 3);
        return strArrSplit.length == 1 ? new String[]{strArrSplit[0], "ROOT"} : strArrSplit;
    }

    public static String[] parseParams(String str) {
        if (str.endsWith("/")) {
            str = StringUtils.substring(str, str.indexOf("grs://"), str.length() - 1);
        }
        return parseGRSSchema(str);
    }
}

package com.xh.xhcore.common.http.strategy.xh.upload;

import android.text.TextUtils;
import android.util.SparseArray;
import com.alibaba.sdk.android.oss.ServiceException;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.constant.ConstServerBaseUrlKt;
import org.json.JSONObject;

public class XHUploadUtil {
    public static final String ALIYUN_UPLOAD_EXTRA_PATH_HEADER_KEY = "Folder";
    public static final int FILE_UPLOAD_SUCCESS = 1000;
    public static int INT_MAX = Integer.MAX_VALUE;
    public static final String LARGE_FILE_SERVER_PATH = ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/upload/largefile/";
    public static final String LARGE_FILE_INSTANTS_SERVER_PATH = ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/upload/instant/";

    public static String getAppKeyFromUrl(String str) {
        return XHAppConfigProxy.getInstance().getAppId();
    }

    public static String getFileInstantUploadUrl(String str) {
        return LARGE_FILE_INSTANTS_SERVER_PATH + str;
    }

    public static String getFileNameFromFilePath(String str) throws IllegalArgumentException {
        String[] strArrSplit = str.split("/");
        if (strArrSplit.length <= 1) {
            throw new IllegalArgumentException("文件路径格式不正确");
        }
        String str2 = strArrSplit[strArrSplit.length - 1];
        if (str2.contains(".")) {
            return str2;
        }
        return str2 + ".xh";
    }

    public static String getFileSuffixFromFilePath(String str) {
        if (str.endsWith("/") || !str.contains(".")) {
            return "xh";
        }
        String[] strArrSplit = str.split("\\.");
        return strArrSplit.length > 0 ? strArrSplit[strArrSplit.length - 1] : "xh";
    }

    public static int getOSSTimeOut(String str) {
        if (TextUtils.isEmpty(str)) {
            return 15000;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("timeout")) {
                return jSONObject.getInt("timeout") * 1000;
            }
            return 15000;
        } catch (Exception e) {
            e.printStackTrace();
            return 15000;
        }
    }

    public static int getTimeOut(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("timeout")) {
                return jSONObject.getInt("timeout");
            }
            return 15000;
        } catch (Exception e) {
            e.printStackTrace();
            return 15000;
        }
    }

    public static boolean hasFailedBefore(SparseArray<Long> sparseArray) {
        boolean z = false;
        for (int i = 0; i < sparseArray.size(); i++) {
            if (sparseArray.valueAt(i).longValue() == -1) {
                z = true;
            }
        }
        return z;
    }

    public static boolean isAliyunOssTokenError(ServiceException serviceException) {
        return serviceException != null && ("InvalidSecurityToken".equals(serviceException.getErrorCode()) || "SignatureDoesNotMatch".equals(serviceException.getErrorCode()) || "InvalidAccessKeyId".equals(serviceException.getErrorCode()) || "SecurityTokenExpired".equals(serviceException.getErrorCode()));
    }
}

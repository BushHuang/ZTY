package com.xh.xhcore.common.http.strategy.xh.download;

import android.text.TextUtils;
import com.xh.xhcore.common.constant.ConstServerBaseUrlKt;
import com.xh.xhcore.common.http.archi.HttpUtil;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.oss.OssConfig;
import com.xh.xhcore.common.util.XHFileUtil;
import java.io.File;

public class XHDownloadUtil {
    public static final String DOWNLOAD_BASE_URL = ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/stream?url=";
    public static final String DOWNLOAD_FILES_BASE_URL = ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/batch/raw/download/";

    public static String exchangeUrl(String str) {
        return exchangeUrlToHttps(exchangeUrlBucketName(str));
    }

    private static String exchangeUrlBucketName(String str) {
        String str2;
        if (str.contains("xuehaifile")) {
            LogUtils.d("是老的bucketName，可以直接下载：" + str);
        } else {
            int i = 0;
            while (true) {
                if (i >= OssConfig.bucketNames.length) {
                    str2 = "";
                    break;
                }
                if (!str.contains(OssConfig.bucketNames[i])) {
                    i++;
                } else {
                    if (!String.valueOf(str.charAt(str.indexOf(OssConfig.bucketNames[i]) + OssConfig.bucketNames[i].length())).equals("/")) {
                        LogUtils.d("已经是新的url，不需要调整：" + str);
                        return str;
                    }
                    str2 = OssConfig.bucketNames[i];
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                str = str.replace(str2 + "/", "").replace("://", "://" + str2 + ".");
            }
            LogUtils.d("调整之后的url：" + str);
        }
        return str;
    }

    static String exchangeUrlToHttps(String str) {
        return (TextUtils.isEmpty(str) || !str.contains("oss-cn-hangzhou.aliyuncs.com")) ? str : str.replaceFirst("http://", "https://");
    }

    public static String[] exchangeUrls(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = exchangeUrl(strArr[i]);
        }
        return strArr;
    }

    public static boolean isHtmlFile(String str) {
        if (XHFileUtil.getFileLength(str) > 10240 || !HttpUtil.containsHtml(XHFileUtil.readFile2String(new File(str), "UTF-8"))) {
            return false;
        }
        LogUtils.i("文件内容包含html，需要重新下载");
        return true;
    }
}

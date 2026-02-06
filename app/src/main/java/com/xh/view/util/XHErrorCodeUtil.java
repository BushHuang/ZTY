package com.xh.view.util;

import java.util.HashMap;

public class XHErrorCodeUtil {
    public static HashMap<Integer, String> CODE_DESCRIPTION_MAP = new HashMap<Integer, String>() {
        {
            put(107000006, "域名解析失败！");
            put(107000007, "网络不通，请检查网络！");
            put(107000028, "网络请求超时！请重试！");
            put(107000056, "网络不通，请检查网络！");
            put(107002002, "文件服务器错误！");
            put(107002001, "文件下载不完整！请重新下载！");
            put(107001012, "无法连接到服务器！");
            put(107001015, "无法连接到服务器！");
            put(107000401, "请重新登录！");
            put(107000403, "权限不足，拒绝访问！");
            put(107000404, "接口或资源不存在！");
            put(107000405, "请求类型错误！");
            put(107000406, "请求的内容不符合要求！");
            put(107000408, "请求服务器超时！");
            put(107000415, "服务器不支持返回该数据类型！");
            put(107000429, "您发送请求太频繁，请休息一会儿！");
            put(107000500, "服务器开小差了！");
            put(107000502, "不好了，服务器罢工了！");
            put(107000503, "该服务暂不可用！");
            put(107000504, "服务器繁忙，请稍后再试！");
            put(107004000, "网络请求超时！请重试！");
            put(107004001, "无法连接到服务器！");
        }
    };
    public static final int HTTP_CODE_107000006 = 107000006;
    public static final int HTTP_CODE_107000007 = 107000007;
    public static final int HTTP_CODE_107000028 = 107000028;
    public static final int HTTP_CODE_107000056 = 107000056;
    public static final int HTTP_CODE_107000400 = 107000400;
    public static final int HTTP_CODE_107000401 = 107000401;
    public static final int HTTP_CODE_107000403 = 107000403;
    public static final int HTTP_CODE_107000404 = 107000404;
    public static final int HTTP_CODE_107000405 = 107000405;
    public static final int HTTP_CODE_107000406 = 107000406;
    public static final int HTTP_CODE_107000408 = 107000408;
    public static final int HTTP_CODE_107000415 = 107000415;
    public static final int HTTP_CODE_107000429 = 107000429;
    public static final int HTTP_CODE_107000500 = 107000500;
    public static final int HTTP_CODE_107000502 = 107000502;
    public static final int HTTP_CODE_107000503 = 107000503;
    public static final int HTTP_CODE_107000504 = 107000504;
    public static final int HTTP_CODE_107001012 = 107001012;
    public static final int HTTP_CODE_107001015 = 107001015;
    public static final int HTTP_CODE_107002001 = 107002001;
    public static final int HTTP_CODE_107002002 = 107002002;
    public static final int HTTP_CODE_107004000 = 107004000;
    public static final int HTTP_CODE_107004001 = 107004001;
    public static final int HTTP_CODE_NO_NET = -108000001;

    public static String getErrorMsgInfo(int i, String str) {
        return CODE_DESCRIPTION_MAP.containsKey(Integer.valueOf(i)) ? CODE_DESCRIPTION_MAP.get(Integer.valueOf(i)) : str;
    }
}

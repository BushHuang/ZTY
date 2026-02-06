package com.xh.networkclient.common;

import com.xh.logutils.Log;
import com.xh.xhcore.common.util.XHLog;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CommonUtils {
    public static HashMap<Code, String> codeDes = null;
    private static int id;
    public static ConcurrentHashMap<Integer, Task> tasks;
    public static String fsUniqueId = "xhtest";
    public static String fsUploadUrl = String.valueOf("http://files.yunzuoye.net/XHFileServer/file") + "/upload/" + fsUniqueId;
    public static String fsHostPrefix = "http://files.yunzuoye.net/XHFileServer/file";
    public static String fsDownloadUrl = String.valueOf(fsHostPrefix) + "/download/" + fsUniqueId;
    public static int baseCode = 107000000;
    public static int fsSuccessCode = 1000;
    public static String jsonMd5Key = "md5";
    public static String jsonUserDataKey = "userData";

    static {
        HashMap<Code, String> map = new HashMap<>();
        codeDes = map;
        map.put(Code.CODE_SUCCESS, "Success");
        codeDes.put(Code.CODE_FILE_NOT_COMPLETE, "File is not complete");
        codeDes.put(Code.CODE_FS_FAIL, "File server return fail");
        codeDes.put(Code.CODE_ADD_DOWNLOAD_TASK_FAIL, "Add download task fail");
        codeDes.put(Code.CODE_BASEINFO_UPLOAD_SUCCESS, "Upload base info success");
        codeDes.put(Code.CODE_ADD_UPLOAD_FILE_TASK_FAIL, "Add upload file task fail");
        codeDes.put(Code.CODE_ADD_UPDATE_FILEURL_TASK_FAIL, "Add update file url task fail");
        id = 0;
        tasks = new ConcurrentHashMap<>();
    }

    public static void addTask(Integer num, Task task) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " id:" + num + "task:" + task, new Object[0]);
        tasks.put(num, task);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " out: id:" + num + "task:" + task, new Object[0]);
    }

    public static String getDesByCode(Code code) {
        return codeDes.get(code);
    }

    public static synchronized int getId() {
        int i = id + 1;
        id = i;
        if (i <= 0) {
            id = 1;
        }
        return id;
    }

    public static Task getTask(Integer num) {
        return tasks.get(num);
    }

    public static void removeTask(Integer num) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " id:" + num, new Object[0]);
        tasks.remove(num);
    }
}

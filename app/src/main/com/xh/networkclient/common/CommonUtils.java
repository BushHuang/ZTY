package com.xh.networkclient.common;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.xh.logutils.Log;
import com.xh.xhcore.common.util.XHLog;

public class CommonUtils {
	
	public static String fsHostPrefix = "http://files.yunzuoye.net/XHFileServer/file";
	public static String fsUniqueId = "xhtest";
	public static String fsUploadUrl = fsHostPrefix + "/upload/" + fsUniqueId;
	public static String fsDownloadUrl = fsHostPrefix + "/download/" + fsUniqueId;
//	public static String fsHostPrefix = "http://192.168.0.170:8080/XHFileServer/file/";
//	private static Properties properties = ProperUtil.getProperties();
//	public static String fsHostPrefix = properties.getProperty("fsHostPrefix");
	
	public static int baseCode = 107000000;
	public static int fsSuccessCode = 1000;
	public static String jsonMd5Key = "md5";
	public static String jsonUserDataKey = "userData";
	
	public static HashMap<Code, String> codeDes = new HashMap<Code, String>();
	
	static{
		codeDes.put(Code.CODE_SUCCESS, "Success");
		codeDes.put(Code.CODE_FILE_NOT_COMPLETE, "File is not complete");
		codeDes.put(Code.CODE_FS_FAIL, "File server return fail");
		codeDes.put(Code.CODE_ADD_DOWNLOAD_TASK_FAIL, "Add download task fail");
		codeDes.put(Code.CODE_BASEINFO_UPLOAD_SUCCESS, "Upload base info success");
		codeDes.put(Code.CODE_ADD_UPLOAD_FILE_TASK_FAIL, "Add upload file task fail");
		codeDes.put(Code.CODE_ADD_UPDATE_FILEURL_TASK_FAIL, "Add update file url task fail");
	}
	
	public static String getDesByCode(Code code){
		return codeDes.get(code);
	}

	private static int id = 0;
	public static synchronized int getId() {
		id++;
		if (id <= 0) {
			id = 1;
		}
		return id;
	}

	public static ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();

	public static void addTask(Integer id, Task task) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " id:" + id + "task:" + task);
		tasks.put(id, task);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " out: id:" + id + "task:" + task);
	}

	public static Task getTask(Integer id) {
		return tasks.get(id);
	}

	public static void removeTask(Integer id) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " id:" + id);
		tasks.remove(id);
	}
}

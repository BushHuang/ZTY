package com.xh.networkclient;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.xh.nativeUtils;
import com.xh.logutils.Log;
import com.xh.networkclient.bean.BatchFileParam;
import com.xh.networkclient.bean.FileRes;
import com.xh.networkclient.bean.UploadFileRes;
import com.xh.networkclient.bean.UrlDTO;
import com.xh.networkclient.common.Code;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.FileMD5;
import com.xh.networkclient.common.Task;
import com.xh.networkclient.common.TaskType;
import com.xh.xhcore.common.util.XHLog;

/**
 * http请求类,使用网络基础模块，请在应用的Manifest.xml中添加网络权限和如下receiver <uses-permission
 * android:name="android.permission.INTERNET" />
 * 
 * @author tinapengbin
 * 
 */
public class HttpRequest {

	/************* 提供单例 *************/

	private static final HttpRequest INSTANCE = new HttpRequest();

	public static final HttpRequest getInstance() {
		return INSTANCE;
	}

	private HttpRequest() {
	};

	private class AsynParam {
		public String updateUrl;
	}

	private HashMap<Integer, AsynParam> asynParams = new HashMap<Integer, AsynParam>();

	private int earlyBaseInfoSize = 1024 * 512;

	private String JSON_HTTP_RESPONSE_KEY = "httpResponse";
	private String JSON_POST_DATA_KEY = "postData";

	/**
	 * 普通http异步请求，默认get方法，若jsonParam中有postdata则底层自动改用post方法
	 * 
	 * @param url
	 *            ,http请求访问的url
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，可包括timeout，post方法的数据、用户数据，例子： {
	 *            "timeOut":3,//超时时间（秒）,若不传则默认10秒 "postData":"", //post方法的数据
	 *            "userData":"",//用户数据，会原封不动的传回给应用层
	 *            "contentType":"application/json; charset=UTF-8"
	 *            //httphead里面的Content-Type内容 }
	 * @param networkCallbacks
	 *            ,回调处理函数接口，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	public int request(String url, String jsonParam,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		return request(url, null, jsonParam, networkCallbacks);
	}
	
	/**
	 * 普通http异步请求，默认get方法，若jsonParam中有postdata则底层自动改用post方法
	 * 
	 * @param url
	 *            ,http请求访问的url
	 * @param jsonHeadLines
	 *            ,以json格式按需传入多个head行,例：{"userId":"25456465",
	 *            "Connection":"Keep-Alive"}
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，可包括timeout，post方法的数据、用户数据，例子： {
	 *            "timeOut":3,//超时时间（秒）,若不传则默认10秒 "postData":"", //post方法的数据
	 *            "userData":"",//用户数据，会原封不动的传回给应用层
	 *            "contentType":"application/json; charset=UTF-8"
	 *            //httphead里面的Content-Type内容 }
	 * @param networkCallbacks
	 *            ,回调处理函数接口，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	public int request(String url, String jsonHeadLines, String jsonParam,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);
		
		if(networkCallbacks == null){
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, " networkCallbacks is null");
		}
		
		JSONObject jsonParamObj = null;

		if (jsonParam == null) {
			jsonParamObj = new JSONObject();
		} else {
			try {
				jsonParamObj = new JSONObject(jsonParam);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			if (jsonHeadLines != null) {
				jsonParamObj.put("headList", jsonHeadLines);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* 获得唯一id */
		int id = CommonUtils.getId();

		/* 对于异步任务来说，将任务信息保存起来，以待任务结束后执行相应操作 */
		Task task = new Task(id, url, TaskType.TASK_HTTP_REQUEST,
				networkCallbacks);
		CommonUtils.addTask(id, task);
		
		String test = jsonParamObj.toString();
		int len = jsonParamObj.toString().getBytes().length;
		XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "len=" + len);

		/* 向native下发http请求任务 */
//		int ret = JNIRequest(id, url, jsonParamObj.toString());
		int ret = JNIRequest(id, url, jsonParamObj.toString().getBytes());
		if (ret != 0) {
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIRequest fail");
			CommonUtils.removeTask(id);
			id = 0;
		}

		return id;
	}

	/**
	 * 针对学海带KZ格式的请求接口
	 * 
	 * @param url
	 *            ,http请求访问的url
	 * @param K
	 *            ,请求参数K,为json字符串格式
	 * @param Z
	 *            ,请求参数K,没有则传null
	 * @param networkCallbacks
	 *            ,当请求成功时，onEvent函数的jsonParam即为服务器的响应数据
	 * @return
	 */
	public int requestWithKZ(String url, String K, int Z,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " K=" + K);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " Z=" + Z);

		return requestWithKZ(url, null, K, Z, null, networkCallbacks);
	}

	/**
	 * 针对学海带KZ格式的请求接口
	 * 
	 * @param url
	 *            ,http请求访问的url
	 * @param jsonHeadLines
	 *            ,以json格式按需传入多个head行,例：{"contentType":"application/json",
	 *            "Connection":"Keep-Alive"}
	 * @param K
	 *            ,请求参数K,为json字符串格式
	 * @param Z
	 *            ,请求参数K,没有则传null
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，可包括timeout，例子： {
	 *            "timeOut":3//超时时间（秒）,若不传则默认10秒 }
	 * @param networkCallbacks
	 *            ,当请求成功时，onEvent函数的jsonParam即为服务器的响应数据
	 * @return
	 */
	public int requestWithKZ(String url, String jsonHeadLines, String K, int Z,
			String jsonParam, NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url=" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonHeadLines=" + jsonHeadLines);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " K=" + K);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " Z=" + Z);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam=" + jsonParam);

		JSONObject jsonParamObj = null;

		if (jsonParam == null) {
			jsonParamObj = new JSONObject();
		} else {
			try {
				jsonParamObj = new JSONObject(jsonParam);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			if (jsonHeadLines != null) {
				jsonParamObj.put("headList", jsonHeadLines);
			} 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			JSONObject postData = new JSONObject();
			postData.put("K", K);
			postData.put("Z", Z);
			jsonParamObj.put("postData", postData.toString());

			jsonParamObj.put("contentType",
					"application/x-www-form-urlencoded; charset=UTF-8");
			jsonParamObj.put("isKZ", "1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request(url, jsonParamObj.toString(), networkCallbacks);
	}

	public enum RequestMethod {
		POST, // 增
		DELETE, // 删
		PUT, // 改
		PATCH, // 部分更改
		GET // 查
	}

	/**
	 * 针对学海RESTful格式的请求接口
	 * 
	 * @param url
	 *            ,http请求访问的url
	 * @param method
	 *            ,请求方法，
	 * @param jsonHeadLines
	 *            ,以json格式按需传入多个head行,例：{"contentType":"application/json",
	 *            "Connection":"Keep-Alive"}
	 * @param bodyData
	 *            ,请求body里面的数据，应为json格式
	 * @param networkCallbacks
	 *            ,当请求成功时，onEvent函数的jsonParam即为服务器的响应数据
	 * @return
	 */
	public int requestWithRESTful(String url, RequestMethod method,
			String jsonHeadLines, String bodyData,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url=" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " method=" + method);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonHeadLines=" + jsonHeadLines);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " bodyData=" + bodyData);

		return requestWithRESTful(url, method, jsonHeadLines, bodyData, null,
				networkCallbacks);
	}

	/**
	 * 针对学海RESTful格式的请求接口,可设置超时
	 * 
	 * @param url
	 *            ,http请求访问的url
	 * @param method
	 *            ,请求方法，
	 * @param jsonHeadLines
	 *            ,以json格式按需传入多个head行,例：{"contentType":"application/json",
	 *            "Connection":"Keep-Alive"}
	 * @param bodyData
	 *            ,请求body里面的数据，应为json格式
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，可包括timeout，例子： {
	 *            "timeOut":3//超时时间（秒）,若不传则默认10秒 }
	 * @param networkCallbacks
	 *            ,当请求成功时，onEvent函数的jsonParam即为服务器的响应数据
	 * @return
	 */
	public int requestWithRESTful(String url, RequestMethod method,
			String jsonHeadLines, String bodyData, String jsonParam,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url=" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " method=" + method);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonHeadLines=" + jsonHeadLines);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " bodyData=" + bodyData);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam=" + jsonParam);

		JSONObject jsonParamObj = null;

		if (jsonParam == null) {
			jsonParamObj = new JSONObject();
		} else {
			try {
				jsonParamObj = new JSONObject(jsonParam);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			jsonParamObj.put("requestMethod", method.toString());

			if (bodyData != null) {
				if (!method.equals(RequestMethod.DELETE)
						&& !method.equals(RequestMethod.GET)) {
					jsonParamObj.put("postData", bodyData);
				}
			}
			if (jsonHeadLines != null) {
				jsonParamObj.put("headList", jsonHeadLines);
			} else {
				JSONObject jsonHeadLinesTmp = new JSONObject();
				jsonHeadLinesTmp.put("contentType", "application/json");
				jsonParamObj.put("headList", jsonHeadLinesTmp.toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request(url, jsonParamObj.toString(), networkCallbacks);
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            ，下载地址
	 * 
	 * @param localPath
	 *            ，保存路径。包括目录名和文件名，且目录已存在。
	 * 
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，可包括用户数据，例子： {
	 *            "timeOut":600,//超时时间（秒）,若不传则默认5*60秒
	 *            "userData":""//用户数据，会原封不动的传回给应用层
	 *            "headList":""//自定义http头部，值为json格式 }
	 * @param networkCallbacks
	 *            ,回调处理函数，需应用层实现
	 * 
	 * @return 返回唯一标识，大于0为有效值
	 */
	public int download(String url, String localPath, String jsonParam,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + localPath);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		String md5 = null;

		if (url == null) {
			url = CommonUtils.fsDownloadUrl;// 此url来源于亚洲
		}

		/* 获得唯一id */
		int id = CommonUtils.getId();

		if (jsonParam != null) {
			try {
				JSONObject jSONObject = new JSONObject(jsonParam);
				if (jSONObject != null && jSONObject.has("md5")) {
					md5 = jSONObject.getString("md5");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/* 对于异步任务来说，将任务信息保存起来，以待任务结束后执行相应操作 */
		Task task = new Task(id, url, TaskType.TASK_HTTP_DOWNLOAD,
				networkCallbacks);
		task.localPath = localPath;
		task.isBatch = 0;
		// task.md5 = md5;
		CommonUtils.addTask(id, task);

		/* 向native下发下载任务 */
		int ret = JNIDownload(id, url, localPath, jsonParam);
		if (ret != 0) {
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIDownload fail");
			CommonUtils.removeTask(id);
			id = 0;
		}

		return id;
	}

	/**
	 * 批量下载文件；下载的文件名就是url里面的fileId
	 * 
	 * @param batchDownoadUrl
	 *            ，批量下载服务器的url，由亚洲提供。这里可以传null，若传null网络模块会赋默认值
	 * @param urls
	 *            ，下载的多个文件的url
	 * 
	 * @param downloadDir
	 *            ，下载目录,所有文件会下到此目录内
	 * 
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，可包括用户数据，例子： {
	 *            "timeOut":600,//超时时间（秒）,若不传则默认5*60秒
	 *            "userData":""//用户数据，会原封不动的传回给应用层
	 *            "headList":""//自定义http头部，值为json格式 }
	 * @param networkCallbacks
	 *            ,回调处理函数，需应用层实现
	 * 
	 * @return 返回唯一标识，大于0为有效值
	 */
	public int download(String batchDownloadUrl, String[] urls,
			String downloadDir, String jsonParam,
			NetworkCallback networkCallbacks) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " urls.length:" + urls.length);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " batchDownoadUrl:"
				+ batchDownloadUrl);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " downloadDir:" + downloadDir);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		if (batchDownloadUrl == null) {
			batchDownloadUrl = "http://files.yunzuoye.net/XHFileServer/file/batch/download/tmp";// 此url来源于亚洲
		}

		/* 获得唯一id */
		int id = CommonUtils.getId();

		/* 对于异步任务来说，将任务信息保存起来，以待任务结束后执行相应操作 */
		Task task = new Task(id, batchDownloadUrl, TaskType.TASK_HTTP_DOWNLOAD,
				networkCallbacks);
		// task.localPath = localPath;
		task.isBatch = 1;
		CommonUtils.addTask(id, task);

		String strUrls = null;

		/* c语言的md5算法结果与服务器上不同，因此采用服务器端的java md5算法 FileMD5 */

		BatchFileParam batchFileParam = new BatchFileParam();

		List<UrlDTO> uDTOList = new ArrayList<UrlDTO>();

		if (urls != null) {

			for (int i = 0; i < urls.length; i++) {
				uDTOList.add(new UrlDTO(urls[i]));
			}
			batchFileParam.setUrls(uDTOList);
			strUrls = new Gson().toJson(batchFileParam);
		}

		/* 将计算出来的md5结果传给native层，在native层要将md5放到head里面 */
		JSONObject jSONObject = null;

		try {
			if (jsonParam == null) {
				jSONObject = new JSONObject();
			} else {
				jSONObject = new JSONObject(jsonParam);
			}
			jSONObject.put("isBatch", "1");
			jSONObject.put("batchUrls", strUrls);
			jSONObject.put("contentType", "application/json");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!");
			return 0;
		}

		/* 向native下发下载任务 */
		int ret = JNIDownload(id, batchDownloadUrl, downloadDir,
				jSONObject.toString());
		if (ret != 0) {
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail");
			CommonUtils.removeTask(id);
			id = 0;
		}

		return id;
	}

	/**
	 * 上传文件
	 * 
	 * @param url
	 *            ，文件服务器地址
	 * @param localPath
	 *            ，要上传的文件全路径。
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，现识别出来可包括用户数据，例子： {
	 *            "timeOut":600,//超时时间（秒）,若不传则默认5*60秒
	 *            "userData":""//用户数据，会原封不动的传回给应用层
	 *            "headList":""//自定义http头部，值为json格式 }
	 * @param networkCallbacks
	 *            ,回调处理函数，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	public int upload(String url, String localPath, String jsonParam,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + localPath);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);
		String md5Str = null;

		if (url == null) {
			url = CommonUtils.fsUploadUrl;// 此url来源于亚洲
		}

		/* 获得唯一id */
		int id = CommonUtils.getId();

		/* 对于异步任务来说，将任务信息保存起来，以待任务结束后执行相应操作 */
		Task task = new Task(id, url, TaskType.TASK_HTTP_UPLOAD,
				networkCallbacks);
		task.localPath = localPath;
		task.isBatch = 0;
		CommonUtils.addTask(id, task);

		/* c语言的md5算法结果与服务器上不同，因此采用服务器端的java md5算法 FileMD5 */
		if (localPath != null) {
			File localFile = new File(localPath);
			if (!localFile.exists()) {
				XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!");
				CommonUtils.removeTask(id);
				return 0;
			}
			md5Str = FileMD5.getMD5(localFile);
		}

		if (url.contains("/file/upload")) {
			if (url.endsWith("/")) {
				url = url + md5Str;
			} else {
				url = url + "/" + md5Str;
			}
		}

		/* 将计算出来的md5结果传给native层，在native层要将md5放到head里面 */
		JSONObject jSONObject = null;

		try {
			if (jsonParam == null) {
				jSONObject = new JSONObject();
			} else {
				jSONObject = new JSONObject(jsonParam);
			}
			jSONObject.put(CommonUtils.jsonMd5Key, md5Str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!");
			return 0;
		}

		/* 向native下发下载任务 */
		int ret = JNIUpload(id, url, localPath, jSONObject.toString());
		if (ret != 0) {
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail");
			CommonUtils.removeTask(id);
			id = 0;
		}

		return id;
	}

	/**
	 * 批量上传文件
	 * 
	 * @param batchUploadUrl
	 *            ，文件服务器地址
	 * @param localPaths
	 *            ，要上传的多个文件的全路径。
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，现识别出来可包括用户数据，例子： {
	 *            "timeOut":600,//超时时间（秒）,若不传则默认5*60秒
	 *            "userData":""//用户数据，会原封不动的传回给应用层 }
	 * @param networkCallbacks
	 *            ,回调处理函数，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	private int uploadWithoutZip(String batchUploadUrl, String[] localPaths,
			String jsonParam, NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + batchUploadUrl);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.length:"
				+ localPaths.length);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.toString():"
				+ localPaths.toString());
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		if (batchUploadUrl == null) {
			batchUploadUrl = "http://files.yunzuoye.net/XHFileServer/file/batch/upload/tmp";// 此url来源于亚洲
		}

		/* 获得唯一id */
		int id = CommonUtils.getId();

		/* 对于异步任务来说，将任务信息保存起来，以待任务结束后执行相应操作 */
		Task task = new Task(id, batchUploadUrl, TaskType.TASK_HTTP_UPLOAD,
				networkCallbacks);
		// task.localPath = localPath;
		CommonUtils.addTask(id, task);

		// String[] md5s = new String[localPaths.length];
		List<String> md5List = new ArrayList<String>();
		String strLocalPaths = null;

		/* c语言的md5算法结果与服务器上不同，因此采用服务器端的java md5算法 FileMD5 */
		if (localPaths != null) {

			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < localPaths.length; i++) {
				jsonArray.put(localPaths[i]);
				File localFile = new File(localPaths[i]);
				if (!localFile.exists()) {
					XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!");
					CommonUtils.removeTask(id);
					return 0;
				}
				// md5s[i] = FileMD5.getMD5(localFile);
				md5List.add(FileMD5.getMD5(localFile));
			}
			strLocalPaths = jsonArray.toString();
		}

		/* 将计算出来的md5结果传给native层，在native层要将md5放到head里面 */
		JSONObject jSONObject = null;

		try {
			if (jsonParam == null) {
				jSONObject = new JSONObject();
			} else {
				jSONObject = new JSONObject(jsonParam);
			}
			// jSONObject.put(CommonUtils.jsonMd5Key, md5s);
			jSONObject.put(CommonUtils.jsonMd5Key, md5List);
			jSONObject.put("isBatch", "2");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!");
			return 0;
		}

		/* 向native下发下载任务 */
		int ret = JNIUpload(id, batchUploadUrl, strLocalPaths,
				jSONObject.toString());
		if (ret != 0) {
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail");
			CommonUtils.removeTask(id);
			id = 0;
		}

		return id;
	}

	/**
	 * 将字符串转成MD5值
	 * 
	 * @param string
	 *            需要转换的字符串
	 * @return 字符串的MD5值
	 */
	private static String stringToMD5(String string) {
		byte[] hash;

		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}

		return hex.toString();
	}

	/**
	 * 批量上传文件，先打包成一个文件再上传
	 * 
	 * @param batchUploadUrl
	 *            ，批量上传文件服务器地址
	 * @param localPaths
	 *            ，要上传的多个文件的全路径。
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，现识别出来可包括用户数据，例子： {
	 *            "timeOut":600,//超时时间（秒）,若不传则默认5*60秒 }
	 * @param networkCallbacks
	 *            ,回调处理函数，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	private int upload2(String batchUploadUrl, String[] localPaths,
			String jsonParam, NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + batchUploadUrl);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.length:"
				+ localPaths.length);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.toString():"
				+ localPaths.toString());
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		String strLocalPaths = null;

		if (batchUploadUrl == null) {
			batchUploadUrl = "http://files.yunzuoye.net/XHFileServer/file/zip/upload/tmp";// 此url来源于亚洲
		}

		/* 将多个url放到json格式中 */
		if (localPaths != null) {

			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < localPaths.length; i++) {
				jsonArray.put(localPaths[i]);
				File localFile = new File(localPaths[i]);
				if (!localFile.exists()) {
					XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!");
					return 0;
				}
			}
			strLocalPaths = jsonArray.toString();
		}

		String zipPath = "/sdcard/xuehai/" + stringToMD5(strLocalPaths)
				+ ".zip";
		// String zipPath = "/sdcard/1.zip";

		/* 将多个文件打包 */
		nativeUtils.JNIZipFiles(strLocalPaths, zipPath);

		/* 上传完要删除 */
		JSONObject jSONObject = null;

		try {
			if (jsonParam == null) {
				jSONObject = new JSONObject();
			} else {
				jSONObject = new JSONObject(jsonParam);
			}
			jSONObject.put(CommonUtils.jsonUserDataKey, "isDelete");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!");
			return 0;
		}

		return upload(batchUploadUrl, zipPath, jSONObject.toString(),
				networkCallbacks);
	}

	/**
	 * 批量上传文件
	 * 
	 * @param batchUploadUrl
	 *            ，文件服务器地址
	 * @param localPaths
	 *            ，要上传的多个文件的全路径。
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，现识别出来可包括用户数据，例子： {
	 *            "timeOut":600,//超时时间（秒）,若不传则默认5*60秒
	 *            "userData":""//用户数据，会原封不动的传回给应用层
	 *            "headList":""//自定义http头部，值为json格式 }
	 * @param networkCallbacks
	 *            ,回调处理函数，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	public int upload(String batchUploadUrl, String[] localPaths,
			String jsonParam, NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + batchUploadUrl);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.length:"
				+ localPaths.length);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.toString():"
				+ localPaths.toString());
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		if (batchUploadUrl == null) {
			batchUploadUrl = "http://files.yunzuoye.net/XHFileServer/file/zip/upload/tmp";// 此url来源于亚洲
		}

		/* 获得唯一id */
		int id = CommonUtils.getId();

		/* 对于异步任务来说，将任务信息保存起来，以待任务结束后执行相应操作 */
		Task task = new Task(id, batchUploadUrl, TaskType.TASK_HTTP_UPLOAD,
				networkCallbacks);
		task.isBatch = 1;
		CommonUtils.addTask(id, task);

		String strLocalPaths = null;

		/* c语言的md5算法结果与服务器上不同，因此采用服务器端的java md5算法 FileMD5 */
		if (localPaths != null) {

			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < localPaths.length; i++) {
				jsonArray.put(localPaths[i]);
				File localFile = new File(localPaths[i]);
				if (!localFile.exists()) {
					XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!");
					CommonUtils.removeTask(id);
					return 0;
				}
			}
			strLocalPaths = jsonArray.toString();
		}

		/* 将计算出来的md5结果传给native层，在native层要将md5放到head里面 */
		JSONObject jSONObject = null;

		try {
			if (jsonParam == null) {
				jSONObject = new JSONObject();
			} else {
				jSONObject = new JSONObject(jsonParam);
			}
			jSONObject.put("isBatch", "1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!");
			return 0;
		}

		/* 向native下发下载任务 */
		int ret = JNIUpload(id, batchUploadUrl, strLocalPaths,
				jSONObject.toString());
		if (ret != 0) {
			XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail");
			CommonUtils.removeTask(id);
			id = 0;
		}

		return id;
	}

	/**
	 * 上传文件及基本信息, 此接口已弃用
	 * 
	 * @param url
	 *            ，应用服务器地址
	 * @param localPath
	 *            ，要上传的文件全路径。
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，现识别出来可包括用户数据，例子： { "postData":"",
	 *            //post方法的数据,即baseinfo "userData":"", //用户数据，会原封不动的传回给应用层
	 *            "contentType":"application/json; charset=UTF-8"//http
	 *            head里面的Content-Type内容 }
	 * @param networkCallbacks
	 *            ,回调处理函数，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	private int uploadWithBaseInfo(final String url, final String localPath,
			final String jsonParam, final NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + url);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + localPath);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		final String fsUrl = CommonUtils.fsUploadUrl;
		final int returnId = CommonUtils.getId();

		/* 更新fileUrl或者上传基本信息+fileUrl，都走这个回调 */
		final NetworkCallback updateUrlCallback = new NetworkCallback() {

			@Override
			public void onEvent(int id, int code, String description,
					String jsonParam) {
				networkCallbacks
						.onEvent(returnId, code, description, jsonParam);
			}

			@Override
			public void onRecvDate(int id, byte[] data, int datalen,
					String jsonParam) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgress(int id, double total, double now,
					String jsonParam) {
				// TODO Auto-generated method stub

			}

		};

		/* 上传文件到文件服务器的回调，文件服务器会给一个fileUrl */
		final NetworkCallback uploadFileCallback = new NetworkCallback() {

			String fileUrl = null;

			@Override
			public void onEvent(int id, int code, String description,
					String jsonParamCbk) {
				if (code != CommonUtils.baseCode) {
					networkCallbacks.onEvent(returnId, code, description,
							jsonParamCbk);
					return;
				}

				/* 解析出fileUrl */
				if (jsonParamCbk != null) {
					try {
						JSONObject jSONObject = new JSONObject(jsonParamCbk);
						String responseStr = jSONObject
								.getString(JSON_HTTP_RESPONSE_KEY);
						Gson gson = new Gson();
						UploadFileRes fileRes = (UploadFileRes) gson.fromJson(
								responseStr, UploadFileRes.class);
						fileUrl = fileRes.getUploadFileDTO().getFileId();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				int updateUrlId = 0;
				AsynParam asynParam = asynParams.get(returnId);
				if (asynParam != null && asynParam.updateUrl != null) {
					JSONObject jSONObject = new JSONObject();
					try {
						jSONObject.put("fileUrl", fileUrl);
						String postData = jSONObject.toString();
						jSONObject = new JSONObject();
						jSONObject.put("postData", postData);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					updateUrlId = request(asynParam.updateUrl,
							jSONObject.toString(), updateUrlCallback);
				} else {
					JSONObject jSONObject = null;
					try {
						jSONObject = new JSONObject(jsonParam);
						String str = jSONObject.getString(JSON_POST_DATA_KEY);
						jSONObject = new JSONObject(str);
						jSONObject.put("fileUrl", fileUrl);
						str = jSONObject.toString();
						jSONObject = new JSONObject();
						jSONObject.put(JSON_POST_DATA_KEY, str);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					updateUrlId = request(url, jSONObject.toString(),
							updateUrlCallback);
				}

				if (updateUrlId <= 0) {
					networkCallbacks
							.onEvent(
									returnId,
									CommonUtils.baseCode
											+ Code.CODE_ADD_UPDATE_FILEURL_TASK_FAIL
													.getValue(),
									CommonUtils
											.getDesByCode(Code.CODE_ADD_UPDATE_FILEURL_TASK_FAIL),
									jsonParam);
				}
			}

			@Override
			public void onRecvDate(int id, byte[] data, int datalen,
					String jsonParam) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgress(int id, double total, double now,
					String jsonParam) {
				networkCallbacks.onProgress(returnId, total, now, jsonParam);
			}
		};

		/* 上传基本信息到应用服务器的回调，应用服务器会给一个更新url */
		NetworkCallback uploadBaseInfoCallback = new NetworkCallback() {

			@Override
			public void onEvent(int id, int code, String description,
					String jsonParamCbk) {
				if (code != CommonUtils.baseCode) {
					networkCallbacks.onEvent(returnId, code, description,
							jsonParamCbk);
					return;
				} else {
					networkCallbacks.onEvent(returnId, CommonUtils.baseCode
							+ Code.CODE_BASEINFO_UPLOAD_SUCCESS.getValue(),
							description, jsonParamCbk);
				}

				/* 解析出updateUrl */
				if (jsonParamCbk != null) {
					try {
						JSONObject jSONObject = new JSONObject(jsonParamCbk);
						String responseStr = jSONObject
								.getString(JSON_HTTP_RESPONSE_KEY);
						jSONObject = new JSONObject(responseStr);
						String updateUrl = jSONObject.getString("updateUrl");

						AsynParam asynParam = new AsynParam();
						asynParam.updateUrl = updateUrl;
						asynParams.put(returnId, asynParam);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// 上传文件到FS
				if (upload(fsUrl, localPath, jsonParam, uploadFileCallback) <= 0) {
					networkCallbacks
							.onEvent(
									returnId,
									CommonUtils.baseCode
											+ Code.CODE_ADD_UPLOAD_FILE_TASK_FAIL
													.getValue(),
									CommonUtils
											.getDesByCode(Code.CODE_ADD_UPLOAD_FILE_TASK_FAIL),
									jsonParam);
				}
			}

			@Override
			public void onRecvDate(int id, byte[] data, int datalen,
					String jsonParam) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgress(int id, double total, double now,
					String jsonParam) {
				// TODO Auto-generated method stub

			}

		};

		int id = 0;

		File f = new File(localPath);
		if (f.exists() && f.length() >= earlyBaseInfoSize) {
			/* 如果文件比较大，业务流程：1、上传基本信息到应用服务器；2、 上传文件到FS；3、将fileUrl更新到应用服务器 */
			id = request(url, jsonParam, uploadBaseInfoCallback);
		} else {
			/* 如果文件比较小，业务流程：1、 上传文件到FS；2、将基本信息和fileUrl上传到应用服务器 */
			id = upload(fsUrl, localPath, jsonParam, uploadFileCallback);
		}

		if (id <= 0) {
			return 0;
		}

		return returnId;
	};

	/**
	 * 根据fileId获得url, 此接口已弃用
	 * 
	 * @param fileId
	 *            ,上传文件成功后的fileId
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，现识别出来可包括用户数据，例子： {
	 *            "userData":""//用户数据，会原封不动的传回给应用层 }
	 * @param networkCallbacks
	 *            ,回调处理函数接口，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	private int getUrlByFileId(String fileId, String jsonParam,
			NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " fileId:" + fileId);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		String url = CommonUtils.fsDownloadUrl + "?fileId=" + fileId;

		return request(url, jsonParam, networkCallbacks);
	}

	/**
	 * 根据fileId下载文件,此接口已弃用
	 * 
	 * @param fileId
	 *            ,上传文件成功后的fileId
	 * @param localPath
	 *            ，保存路径。包括目录名和文件名，且目录已存在。
	 * @param jsonParam
	 *            ，可选参数，若没有则传null，现识别出来可包括用户数据，例子： {
	 *            "userData":""//用户数据，会原封不动的传回给应用层 }
	 * @param networkCallbacks
	 *            ,回调处理函数接口，需应用层实现
	 * @return 返回唯一标识，大于0为有效值
	 */
	private int downloadByFileId(String fileId, final String localPath,
			String jsonParam, final NetworkCallback networkCallbacks) {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " fileId:" + fileId);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + localPath);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + jsonParam);

		final int returnId = CommonUtils.getId();

		/* 根据fileId查询url */
		int getUrlId = getUrlByFileId(fileId, jsonParam, new NetworkCallback() {

			@Override
			public void onRecvDate(int id, byte[] data, int datalen,
					String jsonParam) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgress(int id, double total, double now,
					String jsonParam) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onEvent(final int id, int code, String description,
					String jsonParam) {

				if (code != CommonUtils.baseCode) {
					networkCallbacks.onEvent(returnId, code, description,
							jsonParam);
					return;
				}

				// String md5 = null;
				String url = null;

				/* 解析出url 和md5 */
				if (jsonParam != null) {
					try {
						JSONObject jSONObject = new JSONObject(jsonParam);
						// md5 = jSONObject.getString("md5");
						String str = jSONObject
								.getString(JSON_HTTP_RESPONSE_KEY);
						Gson gson = new Gson();
						FileRes fileRes = (FileRes) gson.fromJson(str,
								FileRes.class);
						url = fileRes.getFileDTO().getUrl()[0];
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				JSONObject jSONObject = null;

				try {
					if (jsonParam == null) {
						jSONObject = new JSONObject();
					} else {
						jSONObject = new JSONObject(jsonParam);
					}
					// jSONObject.put("md5", md5);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int downloadId = download(url, localPath,
						jSONObject.toString(), new NetworkCallback() {

							@Override
							public void onEvent(int id, int code,
									String description, String jsonParam) {
								networkCallbacks.onEvent(returnId, code,
										description, jsonParam);

							}

							@Override
							public void onRecvDate(int id, byte[] data,
									int datalen, String jsonParam) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onProgress(int id, double total,
									double now, String jsonParam) {
								networkCallbacks.onProgress(returnId, total,
										now, jsonParam);

							}

						});

				if (downloadId <= 0) {
					networkCallbacks.onEvent(
							returnId,
							Code.CODE_ADD_DOWNLOAD_TASK_FAIL.getValue(),
							CommonUtils
									.getDesByCode(Code.CODE_ADD_DOWNLOAD_TASK_FAIL),
							jsonParam);
				}

			}
		});

		if (getUrlId <= 0) {
			return 0;
		}

		return returnId;
	}

	/**
	 * 停止下载或上传类的相关任务，若要恢复，请调用download或upload再次下发，即可恢复下载
	 * 
	 * @param id
	 * @return
	 */
	public int stop(int id) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " id=" + id);
		JNIStop(id);
		CommonUtils.removeTask(id);
		return 0;
	}

	/**
	 * 将域名解析成ip
	 * 
	 * @param domain
	 *            ,要解析的域名
	 * @return 若失败，则返回null
	 */
	public String domainToIp(String domain) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " domain=" + domain);
		return JNIDomainToIp(domain);
	}

	/**
	 * 测试某ip和端口是否可连通
	 * 
	 * @param ip
	 *            ，要测试的ip
	 * @param port
	 *            , 要测试port
	 * @return
	 */
	public boolean testConnect(String ip, int port) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " ip=" + ip + ", port=" + port);
		int ret = JNITestConnect(ip, port);
		if (ret == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int setUpDownMaxThreads(int maxThreads) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " maxThreads=" + maxThreads);
		return JNISetUpDownMaxThreads(maxThreads);
	}

	/**
	 * 设置代理，格式如"192.168.0.126:8888",为调试抓包接口，一般不需要调用此接口
	 * 
	 * @param proxy
	 */
	public void setProxy(String proxy) {
		JNISetProxy(proxy);
		return;
	}

	/**
	 * jni接口，下发http请求的异步任务
	 * 
	 * @param id
	 *            ，应用层下发任务的唯一标识
	 * @param url
	 *            ，下载文件的url
	 * @param jsonParam
	 *            ，扩展参数
	 * @return，0表示成功，其它表示失败
	 */
//	private native int JNIRequest(int id, String url, String jsonParam);

	 private native int JNIRequest(int id, String url, byte[] data);

	private native int JNIDownload(int id, String url, String localPath,
			String jsonParam);

	private native int JNIUpload(int id, String url, String localPath,
			String jsonParam);

	private native int JNIStop(int id);

	private static native int JNISetProxy(String proxy);

	private static native String JNIDomainToIp(String domain);

	private static native int JNITestConnect(String ip, int port);
	private static native int JNISetUpDownMaxThreads(int maxThreads);

	private static String decryption(String str) {

		String result = "";

		String tag = "O";// .

		String TAG = "P";// :

		String[] val = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "0" };

		String[] v1 = new String[] { "Q", "W", "E", "R", "A", "S", "D", "F",
				"Z", "X" };

		if (null != str && str.length() > 0) {

			for (char c : str.toCharArray()) {

				if (tag.equals(String.valueOf(c))) {

					result += ".";

				} else if (TAG.equals(String.valueOf(c))) {

					result += ":";

				} else if (Arrays.asList(v1).contains(String.valueOf(c))) {

					result += val[Arrays.asList(v1).indexOf(String.valueOf(c))];

				}

			}

		}

		return result;
	}

	private static void checkProxy() {

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " enter ");
		File dir = new File("/mnt/sdcard/xhproxy");
		if (dir == null) {
			return;
		}
		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			String proxy = decryption(files[0].getName());
			JNISetProxy(proxy);
		}

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " out ");

		return;
	}

	static {
		System.loadLibrary("crypto");
		System.loadLibrary("ssl");
		System.loadLibrary("xh_curl");
		System.loadLibrary("xh_common");
		System.loadLibrary("xh_network_client");
		checkProxy();
	}
}

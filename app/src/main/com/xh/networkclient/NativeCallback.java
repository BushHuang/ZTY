package com.xh.networkclient;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;

import com.google.gson.Gson;
import com.xh.logutils.Log;
import com.xh.networkclient.bean.FileRes;
import com.xh.networkclient.bean.UploadFileRes;
import com.xh.networkclient.common.Code;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.FileMD5;
import com.xh.networkclient.common.Task;
import com.xh.networkclient.common.TaskType;
import com.xh.xhcore.common.util.XHLog;

public class NativeCallback {

	/**
	 * so库处理结果的回调函数，此函数会调用onEvent通知应用层
	 * 
	 * @param id
	 *            , 唯一标识
	 * @param code
	 *            ，错误码
	 * @param description
	 *            ，错误描述
	 * @param jsonParam
	 *            ,底层向应用层回传的数据 { userdata:"", //用户数据 httpResponse:""
	 *            //http服务端的响应数据，请应用层解析出httpResponse字符串值后，再解析成与服务器约定的bean类 }
	 */
	private void eventDeal(int id, int code, String description,
			byte[] bJsonParam) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "id:" + id);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "code:" + code);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "description:" + description);
		String jsonParam = null;

		try {
			if (null != bJsonParam) {
				jsonParam = new String(bJsonParam, "UTF-8");
			}
			XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "jsonParam:" + jsonParam);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int codeTmp = code;
		String desTmp = description;

		Task task = CommonUtils.getTask(id);
		if (task == null || task.callbacks == null) {
			Log.e(Log.XH_NETWORK_CLIENT_TAG, "id:" + id
					+ ", task or task.callbacks is null " + task);
			return;
		}

		if (code != 0) {
			long time = System.currentTimeMillis();
			XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onEvent callback ");
			task.callbacks.onEvent(id, codeTmp + CommonUtils.baseCode, desTmp,
					jsonParam);
			XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onEvent callback ");
			time = System.currentTimeMillis() - time;
			XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onEvent callback cost " + time
					+ "ms, " + "id = " + id);
			CommonUtils.removeTask(id);
			return;
		}

		int status = CommonUtils.fsSuccessCode;

		if (task.type == TaskType.TASK_HTTP_DOWNLOAD && jsonParam != null) {
			if (task.isBatch == 0) {
				String md5 = null;
				/* 若是从oss下载，在header中有md5码，已从中取出放入jsonParam中 */
				try {
					JSONObject jSONObject = new JSONObject(jsonParam);
					md5 = jSONObject.getString("md5");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/* 若是下载类任务，需要在此处进行md5验证 */
				if (md5 != null) {
					/* 用下载保存的文件重新计算md5值 */
					byte[] bMd5 = FileMD5.calcMD5(new File(task.localPath));
					if (bMd5 != null) {

						String md5Str = Base64.encodeToString(bMd5, 2);

						XHLog.v(Log.XH_NETWORK_CLIENT_TAG,
								"md5Str=" + md5Str.trim() + ", md5Strlen="
										+ md5Str.length());
						XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "md5=" + md5.trim()
								+ ", len=" + md5.length());

						/* 进行md5验证 */
						if (!md5Str.equals(md5)) {
							codeTmp = Code.CODE_FILE_NOT_COMPLETE.getValue();
							desTmp = CommonUtils
									.getDesByCode(Code.CODE_FILE_NOT_COMPLETE);
							Log.e(Log.XH_NETWORK_CLIENT_TAG,
									"download file md5 is not equal ");
						}
					}
				}
			}
		} else if (task.type == TaskType.TASK_HTTP_UPLOAD && jsonParam != null) {

			if (task.isBatch == 0) {
				try {
					JSONObject jSONObject = new JSONObject(jsonParam);
					String str = jSONObject.getString("httpResponse");
					Gson gson = new Gson();
					UploadFileRes fileRes = (UploadFileRes) gson.fromJson(str,
							UploadFileRes.class);
					status = fileRes.getStatus();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (task.type == TaskType.TASK_HTTP_REQUEST && jsonParam != null) {
			if (task.url != null && CommonUtils.fsHostPrefix != null
					&& task.url.indexOf(CommonUtils.fsHostPrefix) != -1) {
				try {
					JSONObject jSONObject = new JSONObject(jsonParam);
					String str = jSONObject.getString("httpResponse");
					Gson gson = new Gson();
					FileRes fileRes = (FileRes) gson.fromJson(str,
							FileRes.class);
					status = fileRes.getStatus();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/* 若文件服务器发生错误，转换错误码 */
		if (status != CommonUtils.fsSuccessCode) {
			codeTmp = Code.CODE_FS_FAIL.getValue();
			desTmp = CommonUtils.getDesByCode(Code.CODE_FS_FAIL);
			Log.e(Log.XH_NETWORK_CLIENT_TAG, "file server return fail ");
		}

		long time = System.currentTimeMillis();
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onEvent callback ");
		task.callbacks.onEvent(id, codeTmp + CommonUtils.baseCode, desTmp,
				jsonParam);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onEvent callback ");
		time = System.currentTimeMillis() - time;
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onEvent callback cost " + time
				+ "ms, " + "id = " + id);

		CommonUtils.removeTask(id);
	}

	/**
	 * so库异步回复数据处理回调函数，此函数中会调用onRecvDate通知应用层
	 * 
	 * @param id
	 *            ，唯一标识
	 * @param data
	 *            ，异步回复数据
	 * @param datalen
	 *            ，数据长度
	 * @param jsonParam
	 *            ，扩展参数
	 */
	private void recvDataDeal(int id, byte[] data, int datalen, String jsonParam) {
		Task task = CommonUtils.getTask(id);
		if (task == null || task.callbacks == null) {
			Log.e(Log.XH_NETWORK_CLIENT_TAG, "task or task.callbacks is null");
			return;
		}

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "id:" + id);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "datalen:" + datalen);

		long time = System.currentTimeMillis();
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onRecvDate callback ");
		task.callbacks.onRecvDate(id, data, datalen, jsonParam);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onRecvDate callback ");
		time = System.currentTimeMillis() - time;
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onRecvDate callback cost " + time
				+ "ms, " + "id = " + id);
	}

	/**
	 * so库异步处理进度的回调函数，此函数中会调用onProgress通知应用层
	 * 
	 * @param id
	 *            ，唯一标识
	 * @param total
	 *            ，下载或上传数据总长度
	 * @param now
	 *            ，已经下载或上传的数据长度
	 * @param jsonParam
	 *            ，扩展参数
	 */
	private void progressDeal(int id, double total, double now, String jsonParam) {
		Task task = CommonUtils.getTask(id);
		if (task == null || task.callbacks == null) {
			return;
		}

		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "id:" + id);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "total:" + total);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "now:" + now);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "jsonParam:" + jsonParam);

		long time = System.currentTimeMillis();
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onProgress callback ");
		task.callbacks.onProgress(id, total, now, jsonParam);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onProgress callback ");
		time = System.currentTimeMillis() - time;
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onProgress callback cost " + time
				+ "ms, " + "id = " + id);
	}
}

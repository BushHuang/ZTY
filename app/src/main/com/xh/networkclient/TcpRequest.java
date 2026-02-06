package com.xh.networkclient;

import com.xh.logutils.Log;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.Task;
import com.xh.networkclient.common.TaskType;
import com.xh.xhcore.common.util.XHLog;

/**
 * tpc请求类，向应用层提供的tcp请求的功。
 * 
 * @author tinapengbin
 * 
 */
public class TcpRequest {
	
	/*************提供单例*************/
	private static class Singleton {
		private static final TcpRequest INSTANCE = new TcpRequest();
	}

	public static final TcpRequest getInstance() {
		return Singleton.INSTANCE;
	}

	private TcpRequest() {
	};

	/**
	 * 客户端建立和服务器的连接
	 * 
	 * @param id
	 *            ，唯一标识
	 * @param ip
	 *            ，服务器ip
	 * @param port
	 *            ,服务器端口
	 * @param jsonParam
	 *            ，预留扩展参数，没有则传null
	 * @return，建立连接的id,大于0为有效值.
	 */
	public int connect(String ip, int port, String jsonParam, NetworkCallback networkCallbacks) {
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " ip:" + ip);
		XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " port:" + port);
		
		/* 获得唯一id */
		int id = CommonUtils.getId();
		
		Task task = new Task(id, null, TaskType.TASK_TCP_CONNECT, networkCallbacks);
		/* 对于异步任务来说，将请求任务的id与应用层传下来的回调函数对应关系保存起来，以便通知执行结果 */
		CommonUtils.addTask(id, task);
		
		/* 向native下发下载任务 */
		int ret = JNIConnect(id, ip, port, jsonParam);
		if (ret != 0) {
			Log.e(Log.XH_NETWORK_CLIENT_TAG, "JNIConnect fail");
			CommonUtils.removeTask(id);
			id = 0;
		}

		return id;
	}

	/**
	 * 通过tcp连接发送数据
	 * 
	 * @param id
	 *            ，唯一标识
	 * @param data
	 *            ，数据内容
	 * @param datalen
	 *            ，数据长度,注意：整个发包和收包的长度不允许超过10K
	 * @param jsonParam
	 *            ，预留扩展参数，没有则传null
	 * @return，0代表成功发送，其它代表失败
	 */
	public int send(int id, byte[] data, int dataLen, String jsonParam) {
		return JNISend(id, data, dataLen, jsonParam);
	}

	/**
	 * 断开和服务器的连接
	 * 
	 * @param id
	 *            ，唯一标识
	 * @return，0代表成功发送，其它代表失败
	 */
	public int disConnect(int id) {
		JNIDisConnect(id);
		CommonUtils.removeTask(id);
		return 0;
	}
	
	private native int JNIConnect(int id, String ip, int port, String jsonParam);
	private native int JNISend(int id, byte[] data, int dataLen, String jsonParam);
	private native int JNIDisConnect(int id);
	
	static {
		System.loadLibrary("xh_curl");
		System.loadLibrary("xh_common");
		System.loadLibrary("xh_network_client");
	}
}

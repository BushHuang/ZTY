package com.xh.networkclient;

import com.xh.networkclient.common.CommonUtils;

public interface NetworkCallback {
	
	final int NETWORK_SUCCESS = CommonUtils.baseCode;
	
	/**
	 * 当有错误发生时，网络模块回调此函数通知应用
	 * 
	 * @param id
	 *            ，tcp连接的唯一标识或http任务的唯一标识
	 * @param code
	 *            ，响应码。NETWORK_SUCCESS（107000000）代表成功，其它代表失败。jsonParam中存有服务端返回数。
	 * @param description  
	 *            ，code描述信息
	 * @param jsonParam
	 *            ,底层向应用层回传的数据
	 *             { 
	 *                 userData:"", //用户数据
	 *                 httpResponse:"" //http服务端的响应数据，请应用层解析出httpResponse字符串值后，再解析成与服务器约定的bean类
	 *             }
	 */	 
	public void onEvent(int id, int code, String description, String jsonParam);

	/**
	 * 底层tcp连接收到数据后，通过此回调函数将数据交给应用层。
	 * 
	 * @param id
	 *            ，tcp连接的唯一标识
	 * @param data
	 *            ，接收到的数，必须是一个完全的数据包。
	 * @param datalen
	 *            ，接收到的数据长度
	 * @param jsonParam
	 *            ，预留扩展参数，没有则传null
	 */
	public void onRecvDate(int id, byte[] data, int datalen, String jsonParam);

	/**
	 * 上传或下载的进度回调函数。
	 * 
	 * @param id
	 *            ，http任务的唯一标识
	 * @param total
	 *            ，总大小
	 * @param now
	 *            ，当前完成大小
	 * @param jsonParam
	 *            ，预留扩展参数，现在加入网速信息：{"speed":"0.00 B/s"}
	 */
	public void onProgress(int id, double total, double now, String jsonParam);
}

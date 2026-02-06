package com.xh.networkclient.common;

import com.xh.networkclient.NetworkCallback;

public class Task {

	public Task(int id, String url, TaskType type, NetworkCallback callbacks){
		this.id = id;
		this.url = url;
		this.type = type;
		this.callbacks = callbacks;
	}
    
	public int id;
	public String url;
	public TaskType type;
	public int isBatch;
	public NetworkCallback callbacks;
    
	public String localPath;
//	public String md5;
    
	public int code;
	public String description;
	public String jsonParam;
}

package com.xh.networkclient.common;

import com.xh.networkclient.NetworkCallback;

public class Task {
    public NetworkCallback callbacks;
    public int code;
    public String description;
    public int id;
    public int isBatch;
    public String jsonParam;
    public String localPath;
    public TaskType type;
    public String url;

    public Task(int i, String str, TaskType taskType, NetworkCallback networkCallback) {
        this.id = i;
        this.url = str;
        this.type = taskType;
        this.callbacks = networkCallback;
    }
}

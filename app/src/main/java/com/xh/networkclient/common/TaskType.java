package com.xh.networkclient.common;

public enum TaskType {
    TASK_HTTP_REQUEST,
    TASK_HTTP_DOWNLOAD,
    TASK_HTTP_UPLOAD,
    TASK_TCP_CONNECT;

    public static TaskType[] valuesCustom() {
        TaskType[] taskTypeArrValuesCustom = values();
        int length = taskTypeArrValuesCustom.length;
        TaskType[] taskTypeArr = new TaskType[length];
        System.arraycopy(taskTypeArrValuesCustom, 0, taskTypeArr, 0, length);
        return taskTypeArr;
    }
}

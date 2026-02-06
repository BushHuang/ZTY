package com.zaze.utils.task;

public abstract class TaskEntity implements Executor<TaskEntity> {
    private String jsonData;
    private String taskId;

    public TaskEntity() {
    }

    public TaskEntity(String str) {
        this.taskId = str;
    }

    public String getJsonData() {
        return this.jsonData;
    }

    public String getTaskId() {
        return this.taskId;
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError() {
    }

    @Override
    public void onStart() {
    }

    public TaskEntity setJsonData(String str) {
        this.jsonData = str;
        return this;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }
}

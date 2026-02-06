package com.zaze.utils.task;

public class ExecuteTask extends TaskEntity {
    private Executor<TaskEntity> executor;

    public ExecuteTask(TaskEntity taskEntity) {
        if (taskEntity != null) {
            setTaskId(taskEntity.getTaskId());
            setJsonData(taskEntity.getJsonData());
            this.executor = taskEntity;
        }
    }

    @Override
    public void onComplete() {
        Executor<TaskEntity> executor = this.executor;
        if (executor != null) {
            executor.onComplete();
        }
    }

    @Override
    public void onError() {
        Executor<TaskEntity> executor = this.executor;
        if (executor != null) {
            executor.onError();
        }
    }

    @Override
    public void onExecute(TaskEntity taskEntity) throws Exception {
        Executor<TaskEntity> executor = this.executor;
        if (executor != null) {
            executor.onExecute(taskEntity);
        }
    }

    @Override
    public void onStart() {
        Executor<TaskEntity> executor = this.executor;
        if (executor != null) {
            executor.onStart();
        }
    }
}

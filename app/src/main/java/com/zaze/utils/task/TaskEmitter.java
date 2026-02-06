package com.zaze.utils.task;

public class TaskEmitter implements Emitter<TaskEntity> {
    private Executor<TaskEntity> executor;

    public TaskEmitter() {
    }

    public TaskEmitter(Executor<TaskEntity> executor) {
        this.executor = executor;
    }

    @Override
    public void onComplete() {
        Executor<TaskEntity> executor = this.executor;
        if (executor != null) {
            executor.onComplete();
        }
    }

    @Override
    public void onError(Throwable th) {
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

    public void setExecutor(Executor<TaskEntity> executor) {
        this.executor = executor;
    }
}

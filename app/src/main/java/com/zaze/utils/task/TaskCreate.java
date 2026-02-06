package com.zaze.utils.task;

import com.zaze.utils.task.executor.TaskPool;

public class TaskCreate<T> extends TaskPusher<T> {
    public TaskCreate(String str) {
        super(str);
    }

    @Override
    protected void executeActual() {
        executeTask(getOrCreatePool(), false);
    }

    @Override
    public TaskAsync<T> executeOnAsync() {
        return new TaskAsync<>(this);
    }

    @Override
    public TaskAsyncAuto<T> executeOnAsyncAuto() {
        return new TaskAsyncAuto<>(this);
    }

    @Override
    public TaskAsyncMulti<T> executeOnAsyncMulti() {
        return new TaskAsyncMulti<>(this);
    }

    protected void executeTask(TaskPool taskPool, boolean z) {
        if (taskPool == null || taskPool.isEmpty()) {
            return;
        }
        if (z) {
            putPool(taskPool);
        }
        taskPool.executeTask();
    }
}

package com.zaze.utils.task;

public interface TaskSource<T> {
    void clearPoll();

    void execute();

    TaskAsync<T> executeOnAsync();

    TaskAsyncAuto<T> executeOnAsyncAuto();

    TaskAsyncMulti<T> executeOnAsyncMulti();

    boolean isEmpty();

    boolean isIdle();

    Task<T> pushTask(TaskEntity taskEntity, boolean z);

    Task<T> removeTask(String str);
}

package com.zaze.utils.task;

import com.zaze.utils.task.executor.TaskPool;
import java.util.Collection;
import java.util.Iterator;

public abstract class Task<T> implements TaskSource {
    public static final String DEFAULT_TAG = "$%#zaze_default_#%$";
    protected static boolean needLog;
    protected final String poolTag;

    public Task(String str) {
        this.poolTag = str;
    }

    public static void clear() {
        TaskPusher.clearAllPoll();
    }

    public static <T> Task<T> create() {
        return create("$%#zaze_default_#%$");
    }

    public static <T> Task<T> create(String str) {
        return new TaskCreate(str);
    }

    public static void setNeedLog(boolean z) {
        needLog = z;
        TaskPool.setNeedLog(z);
    }

    @Override
    public void execute() {
        executeActual();
    }

    protected abstract void executeActual();

    public Task<T> push(TaskEntity taskEntity) {
        return pushTask(taskEntity, false);
    }

    public Task<T> push(Collection<TaskEntity> collection) {
        Iterator<TaskEntity> it = collection.iterator();
        while (it.hasNext()) {
            push(it.next());
        }
        return this;
    }

    public Task<T> pushToHead(TaskEntity taskEntity) {
        return pushTask(taskEntity, true);
    }
}

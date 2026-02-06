package com.zaze.utils.task.executor;

import com.zaze.utils.log.ZLog;
import com.zaze.utils.task.ExecuteTask;
import com.zaze.utils.task.Executor;
import com.zaze.utils.task.TaskEmitter;
import com.zaze.utils.task.TaskEntity;

public abstract class TaskPool {
    static volatile boolean needLog = false;
    volatile boolean isStop = false;
    private final TaskEmitter emitter = new TaskEmitter();

    public static void setNeedLog(boolean z) {
        needLog = z;
    }

    public void clear() {
        if (needLog) {
            ZLog.i("Task[ZZ]", "清除该任务池所有任务!", new Object[0]);
        }
    }

    public abstract boolean executeTask();

    public TaskEmitter getEmitter(Executor<TaskEntity> executor) {
        this.emitter.setExecutor(executor);
        return this.emitter;
    }

    public abstract boolean isEmpty();

    public abstract boolean isIdle();

    abstract ExecuteTask pollTask();

    public abstract void pushTask(TaskEntity taskEntity, boolean z);

    public abstract void removeTask(String str);

    public void stop() {
        if (needLog) {
            ZLog.i("Task[ZZ]", "暂停该任务池所有的后续任务!", new Object[0]);
        }
        this.isStop = true;
    }
}

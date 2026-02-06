package com.zaze.utils.task;

import com.zaze.utils.log.ZLog;
import com.zaze.utils.task.executor.AsyncTaskPool;
import com.zaze.utils.task.executor.AutoTaskPool;
import com.zaze.utils.task.executor.TaskPool;

public class TaskAsync<T> extends TaskCreate<T> {
    TaskAsync(TaskCreate<T> taskCreate) {
        super(taskCreate.poolTag);
    }

    @Override
    protected void executeActual() {
        if (needLog) {
            ZLog.i("Task[ZZ]", "开始异步执行任务池(%s)内下一个任务！", this.poolTag);
        }
        TaskPool orCreatePool = getOrCreatePool();
        if (orCreatePool instanceof AutoTaskPool) {
            executeTask(orCreatePool, false);
            return;
        }
        if (needLog) {
            ZLog.i("Task[ZZ]", "转换为异步单任务执行模式(%s)", this.poolTag);
        }
        executeTask(AsyncTaskPool.newInstance(orCreatePool), true);
    }
}

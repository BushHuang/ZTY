package com.zaze.utils.task;

import com.zaze.utils.log.ZLog;
import com.zaze.utils.task.executor.AutoTaskPool;
import com.zaze.utils.task.executor.TaskPool;

public class TaskAsyncAuto<T> extends TaskCreate<T> {
    TaskAsyncAuto(TaskCreate<T> taskCreate) {
        super(taskCreate.poolTag);
    }

    @Override
    protected void executeActual() {
        if (needLog) {
            ZLog.i("Task[ZZ]", "开始异步自动依序执行任务池(%s)内所有任务！", this.poolTag);
        }
        TaskPool orCreatePool = getOrCreatePool();
        if (orCreatePool instanceof AutoTaskPool) {
            executeTask(orCreatePool, false);
            return;
        }
        if (needLog) {
            ZLog.i("Task[ZZ]", "转换为异步自动依序执行模式(%s)", this.poolTag);
        }
        executeTask(AutoTaskPool.newInstance(orCreatePool), true);
    }
}

package com.zaze.utils.task;

import com.zaze.utils.log.ZLog;
import com.zaze.utils.task.executor.MultiTaskPool;
import com.zaze.utils.task.executor.TaskPool;

public class TaskAsyncMulti<T> extends TaskCreate<T> {
    TaskAsyncMulti(TaskCreate<T> taskCreate) {
        super(taskCreate.poolTag);
    }

    @Override
    protected void executeActual() {
        if (needLog) {
            ZLog.i("Task[ZZ]", "开始批量执行任务池(%s)内任务！", this.poolTag);
        }
        executeTask(getOrCreatePool(), false);
    }

    @Override
    protected MultiTaskPool getOrCreatePool() {
        MultiTaskPool multiTaskPoolNewInstance;
        TaskPool orCreatePool = super.getOrCreatePool();
        boolean z = true;
        if (orCreatePool instanceof MultiTaskPool) {
            multiTaskPoolNewInstance = (MultiTaskPool) orCreatePool;
            z = false;
        } else {
            if (needLog) {
                ZLog.i("Task[ZZ]", "转换为异步多任务执行模式(%s)", this.poolTag);
            }
            multiTaskPoolNewInstance = MultiTaskPool.newInstance(orCreatePool);
        }
        if (z) {
            putPool(multiTaskPoolNewInstance);
        }
        return multiTaskPoolNewInstance;
    }

    public TaskAsyncMulti<T> notifyExecute() {
        getOrCreatePool().notifyExecute();
        return this;
    }

    public TaskAsyncMulti<T> setMax(int i) {
        getOrCreatePool().setMultiNum(i);
        return this;
    }

    public TaskAsyncMulti<T> setNotifyCount(int i) {
        getOrCreatePool().setNotifyCount(i);
        return this;
    }
}

package com.zaze.utils.task;

import android.text.TextUtils;
import com.zaze.utils.log.ZLog;
import com.zaze.utils.task.executor.SyncTaskPool;
import com.zaze.utils.task.executor.TaskPool;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public abstract class TaskPusher<T> extends Task<T> {
    private static final ConcurrentHashMap<String, TaskPool> POOL_MAP = new ConcurrentHashMap<>();

    public TaskPusher(String str) {
        super(str);
    }

    protected static void clearAllPoll() {
        Iterator<String> it = POOL_MAP.keySet().iterator();
        while (it.hasNext()) {
            TaskPool pool = getPool(it.next());
            if (pool != null) {
                pool.stop();
                pool.clear();
            }
        }
        POOL_MAP.clear();
    }

    public static TaskPool getPool(String str) {
        if (hasPool(str)) {
            if (needLog) {
                ZLog.i("Task[ZZ]", "提取任务池(%s)", str);
            }
            return POOL_MAP.get(str);
        }
        if (!needLog) {
            return null;
        }
        ZLog.e("Task[ZZ]", "任务池(%s)不存在", str);
        return null;
    }

    private static boolean hasPool(String str) {
        return !TextUtils.isEmpty(str) && POOL_MAP.containsKey(str);
    }

    @Override
    public void clearPoll() {
        TaskPool pool = getPool(this.poolTag);
        if (pool != null) {
            pool.clear();
        }
        POOL_MAP.remove(this.poolTag);
    }

    protected TaskPool getOrCreatePool() {
        TaskPool pool = getPool(this.poolTag);
        if (pool != null) {
            return pool;
        }
        if (needLog) {
            ZLog.i("Task[ZZ]", "创建任务池(%s)", this.poolTag);
        }
        SyncTaskPool syncTaskPool = new SyncTaskPool();
        putPool(syncTaskPool);
        return syncTaskPool;
    }

    @Override
    public boolean isEmpty() {
        TaskPool pool = getPool(this.poolTag);
        return pool == null || pool.isEmpty();
    }

    @Override
    public boolean isIdle() {
        TaskPool pool = getPool(this.poolTag);
        return pool == null || pool.isIdle();
    }

    @Override
    public Task<T> pushTask(TaskEntity taskEntity, boolean z) {
        if (taskEntity != null) {
            getOrCreatePool().pushTask(taskEntity, z);
        }
        return this;
    }

    protected void putPool(TaskPool taskPool) {
        if (needLog) {
            ZLog.i("Task[ZZ]", "更新任务池(%s)", this.poolTag);
        }
        POOL_MAP.put(this.poolTag, taskPool);
    }

    protected void removePool() {
        if (needLog) {
            ZLog.i("Task[ZZ]", "移除任务池(%s)!", this.poolTag);
        }
        POOL_MAP.remove(this.poolTag);
    }

    @Override
    public Task<T> removeTask(String str) {
        if (!TextUtils.isEmpty(str)) {
            getOrCreatePool().removeTask(str);
        }
        return this;
    }
}

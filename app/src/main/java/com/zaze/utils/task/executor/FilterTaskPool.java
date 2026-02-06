package com.zaze.utils.task.executor;

import com.zaze.utils.task.ExecuteTask;
import com.zaze.utils.task.TaskEntity;
import java.util.concurrent.ExecutorService;

public class FilterTaskPool extends TaskPool {
    ExecutorService executorService;
    TaskPool taskPool;

    public FilterTaskPool(TaskPool taskPool) {
        if (taskPool instanceof FilterTaskPool) {
            taskPool.stop();
            this.taskPool = ((FilterTaskPool) taskPool).getTaskPool();
        } else {
            this.taskPool = taskPool;
        }
        if (taskPool != null) {
            taskPool.isStop = false;
        }
        this.isStop = false;
    }

    @Override
    public void clear() {
        super.clear();
        TaskPool taskPool = this.taskPool;
        if (taskPool != null) {
            taskPool.clear();
        }
    }

    @Override
    public boolean executeTask() {
        TaskPool taskPool = this.taskPool;
        return taskPool != null && taskPool.executeTask();
    }

    public TaskPool getTaskPool() {
        return this.taskPool;
    }

    @Override
    public boolean isEmpty() {
        TaskPool taskPool = this.taskPool;
        return taskPool == null || taskPool.isEmpty();
    }

    @Override
    public boolean isIdle() {
        TaskPool taskPool = this.taskPool;
        return taskPool == null || taskPool.isIdle();
    }

    @Override
    public ExecuteTask pollTask() {
        TaskPool taskPool = this.taskPool;
        if (taskPool == null) {
            return null;
        }
        return taskPool.pollTask();
    }

    @Override
    public void pushTask(TaskEntity taskEntity, boolean z) {
        TaskPool taskPool = this.taskPool;
        if (taskPool != null) {
            taskPool.pushTask(taskEntity, z);
        }
    }

    @Override
    public void removeTask(String str) {
        TaskPool taskPool = this.taskPool;
        if (taskPool != null) {
            taskPool.removeTask(str);
        }
    }

    @Override
    public void stop() {
        super.stop();
        ExecutorService executorService = this.executorService;
        if (executorService != null) {
            executorService.shutdown();
        }
        TaskPool taskPool = this.taskPool;
        if (taskPool != null) {
            taskPool.stop();
        }
    }
}

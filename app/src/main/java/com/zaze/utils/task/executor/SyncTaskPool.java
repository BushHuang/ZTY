package com.zaze.utils.task.executor;

import com.zaze.utils.ZStringUtil;
import com.zaze.utils.log.ZLog;
import com.zaze.utils.task.ExecuteTask;
import com.zaze.utils.task.TaskEmitter;
import com.zaze.utils.task.TaskEntity;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public class SyncTaskPool extends TaskPool {
    private final ConcurrentLinkedQueue<String> taskIdQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, ExecuteTask> taskMap = new ConcurrentHashMap<>();
    private final ConcurrentSkipListSet<String> currentTaskSet = new ConcurrentSkipListSet<>();

    public SyncTaskPool() {
        this.isStop = false;
    }

    private boolean executeTask(ExecuteTask executeTask) {
        TaskEmitter emitter = getEmitter(executeTask);
        if (this.isStop) {
            if (needLog) {
                ZLog.i("Task[ZZ]", "该任务池已停止执行！", new Object[0]);
            }
            return false;
        }
        if (executeTask == null) {
            if (needLog) {
                ZLog.i("Task[ZZ]", "该任务池已经执行完毕！", new Object[0]);
            }
            return false;
        }
        if (needLog) {
            ZLog.i("Task[ZZ]", "执行任务(%s);剩余任务(%s)", executeTask.getTaskId(), Integer.valueOf(this.taskIdQueue.size()));
        }
        String taskId = executeTask.getTaskId();
        this.currentTaskSet.add(taskId);
        try {
            emitter.onExecute((TaskEmitter) executeTask);
        } catch (Exception e) {
            e.printStackTrace();
            emitter.onError(e);
        }
        this.currentTaskSet.remove(taskId);
        emitter.onComplete();
        return true;
    }

    @Override
    public void clear() {
        this.taskIdQueue.clear();
        this.taskMap.clear();
        this.currentTaskSet.clear();
    }

    @Override
    public boolean executeTask() {
        return executeTask(pollTask());
    }

    @Override
    public boolean isEmpty() {
        return this.taskIdQueue.isEmpty() || this.taskMap.isEmpty();
    }

    @Override
    public boolean isIdle() {
        return this.currentTaskSet.isEmpty() && isEmpty();
    }

    @Override
    public ExecuteTask pollTask() {
        if (!isEmpty()) {
            while (this.taskIdQueue.peek() != null) {
                String strPoll = this.taskIdQueue.poll();
                if (!ZStringUtil.isEmpty(strPoll) && this.taskMap.containsKey(strPoll)) {
                    ExecuteTask executeTask = this.taskMap.get(strPoll);
                    this.taskMap.remove(strPoll);
                    if (needLog) {
                        ZLog.i("Task[ZZ]", "提取执行 : 任务(%s)", strPoll);
                    }
                    return executeTask;
                }
            }
        }
        if (!needLog) {
            return null;
        }
        ZLog.i("Task[ZZ]", "没有需要执行的任务！", new Object[0]);
        return null;
    }

    @Override
    public void pushTask(TaskEntity taskEntity, boolean z) {
        if (taskEntity == null) {
            return;
        }
        String taskId = taskEntity.getTaskId();
        if (ZStringUtil.isEmpty(taskId)) {
            taskId = String.valueOf(taskEntity.hashCode());
            taskEntity.setTaskId(taskId);
        }
        if (this.currentTaskSet.contains(ZStringUtil.parseString(taskId))) {
            if (needLog) {
                ZLog.i("Task[ZZ]", "该任务正在执行中(%s)", taskId);
                return;
            }
            return;
        }
        this.taskMap.put(taskId, new ExecuteTask(taskEntity));
        if (!z) {
            if (this.taskIdQueue.contains(taskId)) {
                if (needLog) {
                    ZLog.w("Task[ZZ]", "已存在相同任务(%s), 更新为最新", taskId);
                    return;
                }
                return;
            } else {
                this.taskIdQueue.add(taskId);
                if (needLog) {
                    ZLog.i("Task[ZZ]", "添加任务(%s) : 成功", taskId);
                    return;
                }
                return;
            }
        }
        if (needLog) {
            ZLog.i("Task[ZZ]", "优先执行该任务(%s)", taskId);
        }
        removeTask(taskId);
        ArrayList arrayList = new ArrayList();
        arrayList.add(taskId);
        arrayList.addAll(this.taskIdQueue);
        this.taskIdQueue.clear();
        this.taskIdQueue.addAll(arrayList);
        if (needLog) {
            ZLog.i("Task[ZZ]", "任务置顶成功(%s)", taskId);
        }
    }

    @Override
    public void removeTask(String str) {
        if (this.taskIdQueue.contains(str)) {
            this.taskIdQueue.remove(str);
        }
    }

    @Override
    public void stop() {
        this.isStop = true;
    }
}

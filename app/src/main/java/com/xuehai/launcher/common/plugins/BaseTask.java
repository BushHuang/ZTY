package com.xuehai.launcher.common.plugins;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/common/plugins/BaseTask;", "Ljava/lang/Runnable;", "()V", "taskName", "", "getTaskName", "()Ljava/lang/String;", "setTaskName", "(Ljava/lang/String;)V", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseTask implements Runnable {
    private String taskName;

    public BaseTask() {
        String name = getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "this.javaClass.name");
        this.taskName = name;
    }

    public final String getTaskName() {
        return this.taskName;
    }

    public final void setTaskName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.taskName = str;
    }
}

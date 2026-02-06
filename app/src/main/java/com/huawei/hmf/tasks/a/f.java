package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

public final class f<TResult> implements ExecuteResult<TResult> {

    private OnFailureListener f188a;
    private Executor b;
    private final Object c = new Object();

    f(Executor executor, OnFailureListener onFailureListener) {
        this.f188a = onFailureListener;
        this.b = executor;
    }

    @Override
    public final void cancel() {
        synchronized (this.c) {
            this.f188a = null;
        }
    }

    @Override
    public final void onComplete(final Task<TResult> task) {
        if (task.isSuccessful() || task.isCanceled()) {
            return;
        }
        this.b.execute(new Runnable() {
            @Override
            public final void run() {
                synchronized (f.this.c) {
                    if (f.this.f188a != null) {
                        f.this.f188a.onFailure(task.getException());
                    }
                }
            }
        });
    }
}

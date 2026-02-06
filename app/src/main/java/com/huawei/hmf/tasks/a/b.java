package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

public final class b<TResult> implements ExecuteResult<TResult> {

    private OnCanceledListener f182a;
    private Executor b;
    private final Object c = new Object();

    b(Executor executor, OnCanceledListener onCanceledListener) {
        this.f182a = onCanceledListener;
        this.b = executor;
    }

    @Override
    public final void cancel() {
        synchronized (this.c) {
            this.f182a = null;
        }
    }

    @Override
    public final void onComplete(Task<TResult> task) {
        if (task.isCanceled()) {
            this.b.execute(new Runnable() {
                @Override
                public final void run() {
                    synchronized (b.this.c) {
                        if (b.this.f182a != null) {
                            b.this.f182a.onCanceled();
                        }
                    }
                }
            });
        }
    }
}

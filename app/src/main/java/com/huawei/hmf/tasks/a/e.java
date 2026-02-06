package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import java.util.concurrent.ExecutionException;

final class e<TResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TResult> {

    private final Object f187a = new Object();
    private final int b;
    private final i<Void> c;
    private int d;
    private Exception e;
    private boolean f;

    e(int i, i<Void> iVar) {
        this.b = i;
        this.c = iVar;
    }

    private void a() {
        if (this.d >= this.b) {
            if (this.e != null) {
                this.c.a(new ExecutionException("a task failed", this.e));
            } else if (this.f) {
                this.c.a();
            } else {
                this.c.a((i<Void>) null);
            }
        }
    }

    @Override
    public final void onCanceled() {
        synchronized (this.f187a) {
            this.d++;
            this.f = true;
            a();
        }
    }

    @Override
    public final void onFailure(Exception exc) {
        synchronized (this.f187a) {
            this.d++;
            this.e = exc;
            a();
        }
    }

    @Override
    public final void onSuccess(TResult tresult) {
        synchronized (this.f187a) {
            this.d++;
            a();
        }
    }
}

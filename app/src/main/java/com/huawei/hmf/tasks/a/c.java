package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.CancellationToken;
import java.util.ArrayList;
import java.util.List;

public final class c extends CancellationToken {

    public final List<Runnable> f184a = new ArrayList();
    public final Object b = new Object();
    public boolean c = false;

    @Override
    public final boolean isCancellationRequested() {
        return this.c;
    }

    @Override
    public final CancellationToken register(Runnable runnable) {
        synchronized (this.b) {
            if (this.c) {
                runnable.run();
            } else {
                this.f184a.add(runnable);
            }
        }
        return this;
    }
}

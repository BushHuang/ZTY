package com.xuehai.launcher.common.ext;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @Override
    public void observe(LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        super.observe(lifecycleOwner, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                if (SingleLiveEvent.this.mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @Override
    public void setValue(T t) {
        this.mPending.set(true);
        super.setValue(t);
    }
}

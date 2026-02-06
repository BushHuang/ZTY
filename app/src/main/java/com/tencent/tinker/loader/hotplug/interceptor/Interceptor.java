package com.tencent.tinker.loader.hotplug.interceptor;

import android.util.Log;

public abstract class Interceptor<T_TARGET> {
    private static final String TAG = "Tinker.Interceptor";
    private T_TARGET mTarget = null;
    private volatile boolean mInstalled = false;

    protected interface ITinkerHotplugProxy {
    }

    protected T_TARGET decorate(T_TARGET t_target) throws Throwable {
        return t_target;
    }

    protected abstract T_TARGET fetchTarget() throws Throwable;

    protected abstract void inject(T_TARGET t_target) throws Throwable;

    public synchronized void install() throws InterceptFailedException {
        try {
            T_TARGET t_targetFetchTarget = fetchTarget();
            this.mTarget = t_targetFetchTarget;
            T_TARGET t_targetDecorate = decorate(t_targetFetchTarget);
            if (t_targetDecorate != t_targetFetchTarget) {
                inject(t_targetDecorate);
            } else {
                Log.w("Tinker.Interceptor", "target: " + t_targetFetchTarget + " was already hooked.");
            }
            this.mInstalled = true;
        } catch (Throwable th) {
            this.mTarget = null;
            throw new InterceptFailedException(th);
        }
    }

    public synchronized void uninstall() throws InterceptFailedException {
        InterceptFailedException interceptFailedException;
        if (this.mInstalled) {
            try {
                inject(this.mTarget);
                this.mTarget = null;
                this.mInstalled = false;
            } finally {
            }
        }
    }
}

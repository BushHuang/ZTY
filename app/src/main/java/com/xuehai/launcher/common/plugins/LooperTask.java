package com.xuehai.launcher.common.plugins;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\nH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0014J\b\u0010\u0010\u001a\u00020\u0004H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\nH\u0014J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012J\u0006\u0010\u0016\u001a\u00020\u0004J\u0006\u0010\u0017\u001a\u00020\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/xuehai/launcher/common/plugins/LooperTask;", "Lcom/xuehai/launcher/common/plugins/BaseTask;", "()V", "executed", "", "getExecuted", "()Z", "setExecuted", "(Z)V", "<set-?>", "", "whenDo", "getWhenDo", "()J", "calculateNextDelay", "calculateNextTimeMills", "filter", "init", "", "initialDelay", "markExecuted", "next", "reachTime", "reset", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class LooperTask extends BaseTask {
    private boolean executed;
    private long whenDo;

    protected long calculateNextDelay() {
        return 300000L;
    }

    protected long calculateNextTimeMills() {
        return System.currentTimeMillis() + Math.max(calculateNextDelay(), 1000L);
    }

    public boolean filter() {
        return true;
    }

    public final boolean getExecuted() {
        return this.executed;
    }

    public final long getWhenDo() {
        return this.whenDo;
    }

    public final void init() {
        this.whenDo = System.currentTimeMillis() + initialDelay();
    }

    protected long initialDelay() {
        return calculateNextDelay();
    }

    public final void markExecuted() {
        this.executed = true;
    }

    public final void next() {
        this.whenDo = calculateNextTimeMills();
    }

    public final boolean reachTime() {
        return System.currentTimeMillis() >= this.whenDo;
    }

    public final void reset() {
        this.executed = false;
    }

    public final void setExecuted(boolean z) {
        this.executed = z;
    }
}

package com.xuehai.launcher.guide;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0003R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/xuehai/launcher/guide/StepProgressItem;", "", "step", "Lcom/xuehai/launcher/guide/InitStep;", "(Lcom/xuehai/launcher/guide/InitStep;)V", "", "name", "", "state", "(ILjava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getState", "()I", "setState", "(I)V", "getStep", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "updateState", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StepProgressItem {
    private final String name;
    private int state;
    private final int step;

    public StepProgressItem(int i, String str, int i2) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.step = i;
        this.name = str;
        this.state = i2;
    }

    public StepProgressItem(int i, String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i3 & 4) != 0 ? 0 : i2);
    }

    public StepProgressItem(InitStep initStep) {
        this(initStep.getStep(), initStep.getDesc(), 0, 4, null);
        Intrinsics.checkNotNullParameter(initStep, "step");
    }

    public static StepProgressItem copy$default(StepProgressItem stepProgressItem, int i, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = stepProgressItem.step;
        }
        if ((i3 & 2) != 0) {
            str = stepProgressItem.name;
        }
        if ((i3 & 4) != 0) {
            i2 = stepProgressItem.state;
        }
        return stepProgressItem.copy(i, str, i2);
    }

    public final int getStep() {
        return this.step;
    }

    public final String getName() {
        return this.name;
    }

    public final int getState() {
        return this.state;
    }

    public final StepProgressItem copy(int step, String name, int state) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new StepProgressItem(step, name, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StepProgressItem)) {
            return false;
        }
        StepProgressItem stepProgressItem = (StepProgressItem) other;
        return this.step == stepProgressItem.step && Intrinsics.areEqual(this.name, stepProgressItem.name) && this.state == stepProgressItem.state;
    }

    public final String getName() {
        return this.name;
    }

    public final int getState() {
        return this.state;
    }

    public final int getStep() {
        return this.step;
    }

    public int hashCode() {
        return (((this.step * 31) + this.name.hashCode()) * 31) + this.state;
    }

    public final void setState(int i) {
        this.state = i;
    }

    public String toString() {
        return "StepProgressItem(step=" + this.step + ", name=" + this.name + ", state=" + this.state + ')';
    }

    public final void updateState(InitStep step) {
        Intrinsics.checkNotNullParameter(step, "step");
        this.state = this.step < step.getStep() ? 2 : this.step == step.getStep() ? 1 : 0;
    }
}

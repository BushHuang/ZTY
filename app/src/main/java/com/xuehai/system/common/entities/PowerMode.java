package com.xuehai.system.common.entities;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/common/entities/PowerMode;", "", "code", "", "(I)V", "getCode", "()I", "SavingOff", "SavingOn", "Lcom/xuehai/system/common/entities/PowerMode$SavingOff;", "Lcom/xuehai/system/common/entities/PowerMode$SavingOn;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PowerMode {
    private final int code;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xuehai/system/common/entities/PowerMode$SavingOff;", "Lcom/xuehai/system/common/entities/PowerMode;", "()V", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SavingOff extends PowerMode {
        public static final SavingOff INSTANCE = new SavingOff();

        private SavingOff() {
            super(0, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xuehai/system/common/entities/PowerMode$SavingOn;", "Lcom/xuehai/system/common/entities/PowerMode;", "()V", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SavingOn extends PowerMode {
        public static final SavingOn INSTANCE = new SavingOn();

        private SavingOn() {
            super(1, null);
        }
    }

    private PowerMode(int i) {
        this.code = i;
    }

    public PowerMode(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getCode() {
        return this.code;
    }
}

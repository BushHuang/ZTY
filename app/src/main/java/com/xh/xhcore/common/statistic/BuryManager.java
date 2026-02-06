package com.xh.xhcore.common.statistic;

import com.xh.xhcore.common.http.strategy.LogUtils;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/statistic/BuryManager;", "", "()V", "commonBuryTimerHelper", "Lcom/xh/xhcore/common/statistic/CommonBuryTimerHelper;", "getCommonBuryTimerHelper", "()Lcom/xh/xhcore/common/statistic/CommonBuryTimerHelper;", "start", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BuryManager {

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<BuryManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<BuryManager>() {
        @Override
        public final BuryManager invoke() {
            return new BuryManager(null);
        }
    });
    private final CommonBuryTimerHelper commonBuryTimerHelper;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/statistic/BuryManager$Companion;", "", "()V", "instance", "Lcom/xh/xhcore/common/statistic/BuryManager;", "getInstance", "()Lcom/xh/xhcore/common/statistic/BuryManager;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BuryManager getInstance() {
            return (BuryManager) BuryManager.instance$delegate.getValue();
        }
    }

    private BuryManager() {
        this.commonBuryTimerHelper = new CommonBuryTimerHelper();
    }

    public BuryManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final CommonBuryTimerHelper getCommonBuryTimerHelper() {
        return this.commonBuryTimerHelper;
    }

    public final void start() {
        try {
            this.commonBuryTimerHelper.restart();
        } catch (NoSuchMethodError e) {
            LogUtils.INSTANCE.e(e.toString());
        }
    }
}

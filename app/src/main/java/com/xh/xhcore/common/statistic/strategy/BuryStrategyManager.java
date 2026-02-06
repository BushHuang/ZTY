package com.xh.xhcore.common.statistic.strategy;

import android.content.Context;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.statistic.BuryEvent;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\"\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u0010J\u0016\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004R\"\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/BuryStrategyManager;", "", "buryStrategy", "Lcom/xh/xhcore/common/statistic/strategy/IBuryStrategy;", "Lcom/xh/xhcore/common/statistic/strategy/IBuryConfig;", "(Lcom/xh/xhcore/common/statistic/strategy/IBuryStrategy;)V", "getBuryStrategy", "()Lcom/xh/xhcore/common/statistic/strategy/IBuryStrategy;", "setBuryStrategy", "bury", "", "buryEvent", "Lcom/xh/xhcore/common/statistic/BuryEvent;", "eventName", "", "properties", "", "init", "context", "Landroid/content/Context;", "buryConfig", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BuryStrategyManager {

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<BuryStrategyManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<BuryStrategyManager>() {
        @Override
        public final BuryStrategyManager invoke() {
            IBuryConfig buryConfig = XHAppConfigProxy.getInstance().getBuryConfig();
            return new BuryStrategyManager(buryConfig instanceof XHAnalysysBuryConfig ? new XHAnalysysBuryStrategy(new XHBuryStrategy(), new AnalysysBuryStrategy()) : buryConfig instanceof AnalysysBuryConfig ? new AnalysysBuryStrategy() : new XHBuryStrategy());
        }
    });
    private IBuryStrategy<? extends IBuryConfig> buryStrategy;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/BuryStrategyManager$Companion;", "", "()V", "instance", "Lcom/xh/xhcore/common/statistic/strategy/BuryStrategyManager;", "getInstance$annotations", "getInstance", "()Lcom/xh/xhcore/common/statistic/strategy/BuryStrategyManager;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final BuryStrategyManager getInstance() {
            return (BuryStrategyManager) BuryStrategyManager.instance$delegate.getValue();
        }
    }

    public BuryStrategyManager(IBuryStrategy<? extends IBuryConfig> iBuryStrategy) {
        Intrinsics.checkNotNullParameter(iBuryStrategy, "buryStrategy");
        this.buryStrategy = iBuryStrategy;
    }

    public static final BuryStrategyManager getInstance() {
        return INSTANCE.getInstance();
    }

    @Deprecated(message = "旧版本方法兼容")
    public final void bury(BuryEvent buryEvent) {
        this.buryStrategy.bury(buryEvent);
    }

    public final void bury(String eventName, Map<String, Object> properties) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.buryStrategy.bury(eventName, properties);
    }

    public final IBuryStrategy<? extends IBuryConfig> getBuryStrategy() {
        return this.buryStrategy;
    }

    public final void init(Context context, IBuryConfig buryConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(buryConfig, "buryConfig");
        this.buryStrategy.init(context, buryConfig);
    }

    public final void setBuryStrategy(IBuryStrategy<? extends IBuryConfig> iBuryStrategy) {
        Intrinsics.checkNotNullParameter(iBuryStrategy, "<set-?>");
        this.buryStrategy = iBuryStrategy;
    }
}

package com.xh.xhcore.common.statistic.strategy;

import android.content.Context;
import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.strategy.IBuryStrategy;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J$\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00140\u0013H\u0016J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0002H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/XHAnalysysBuryStrategy;", "Lcom/xh/xhcore/common/statistic/strategy/IBuryStrategy;", "Lcom/xh/xhcore/common/statistic/strategy/XHAnalysysBuryConfig;", "xhBuryStrategy", "Lcom/xh/xhcore/common/statistic/strategy/XHBuryStrategy;", "analysysBuryStrategy", "Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryStrategy;", "(Lcom/xh/xhcore/common/statistic/strategy/XHBuryStrategy;Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryStrategy;)V", "getAnalysysBuryStrategy", "()Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryStrategy;", "getXhBuryStrategy", "()Lcom/xh/xhcore/common/statistic/strategy/XHBuryStrategy;", "bury", "", "buryEvent", "Lcom/xh/xhcore/common/statistic/BuryEvent;", "eventName", "", "properties", "", "", "initInner", "context", "Landroid/content/Context;", "buryConfig", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHAnalysysBuryStrategy implements IBuryStrategy<XHAnalysysBuryConfig> {
    private final AnalysysBuryStrategy analysysBuryStrategy;
    private final XHBuryStrategy xhBuryStrategy;

    public XHAnalysysBuryStrategy(XHBuryStrategy xHBuryStrategy, AnalysysBuryStrategy analysysBuryStrategy) {
        Intrinsics.checkNotNullParameter(xHBuryStrategy, "xhBuryStrategy");
        Intrinsics.checkNotNullParameter(analysysBuryStrategy, "analysysBuryStrategy");
        this.xhBuryStrategy = xHBuryStrategy;
        this.analysysBuryStrategy = analysysBuryStrategy;
    }

    @Override
    public void bury(BuryEvent buryEvent) {
        this.xhBuryStrategy.bury(buryEvent);
        this.analysysBuryStrategy.bury(buryEvent);
    }

    @Override
    public void bury(String eventName, Map<String, Object> properties) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(properties, "properties");
        this.xhBuryStrategy.bury(eventName, properties);
        this.analysysBuryStrategy.bury(eventName, properties);
    }

    public final AnalysysBuryStrategy getAnalysysBuryStrategy() {
        return this.analysysBuryStrategy;
    }

    public final XHBuryStrategy getXhBuryStrategy() {
        return this.xhBuryStrategy;
    }

    @Override
    public void init(Context context, IBuryConfig iBuryConfig) {
        IBuryStrategy.DefaultImpls.init(this, context, iBuryConfig);
    }

    @Override
    public void initInner(Context context, XHAnalysysBuryConfig buryConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(buryConfig, "buryConfig");
        this.xhBuryStrategy.initInner(context, buryConfig.getXhBuryConfig());
        this.analysysBuryStrategy.initInner(context, buryConfig.getAnalysysBuryConfig());
    }
}

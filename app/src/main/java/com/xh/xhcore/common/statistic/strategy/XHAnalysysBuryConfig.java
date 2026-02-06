package com.xh.xhcore.common.statistic.strategy;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/XHAnalysysBuryConfig;", "Lcom/xh/xhcore/common/statistic/strategy/IBuryConfig;", "analysysBuryConfig", "Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryConfig;", "xhBuryConfig", "Lcom/xh/xhcore/common/statistic/strategy/XHBuryConfig;", "(Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryConfig;Lcom/xh/xhcore/common/statistic/strategy/XHBuryConfig;)V", "getAnalysysBuryConfig", "()Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryConfig;", "getXhBuryConfig", "()Lcom/xh/xhcore/common/statistic/strategy/XHBuryConfig;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHAnalysysBuryConfig implements IBuryConfig {
    private final AnalysysBuryConfig analysysBuryConfig;
    private final XHBuryConfig xhBuryConfig;

    public XHAnalysysBuryConfig(AnalysysBuryConfig analysysBuryConfig, XHBuryConfig xHBuryConfig) {
        Intrinsics.checkNotNullParameter(analysysBuryConfig, "analysysBuryConfig");
        Intrinsics.checkNotNullParameter(xHBuryConfig, "xhBuryConfig");
        this.analysysBuryConfig = analysysBuryConfig;
        this.xhBuryConfig = xHBuryConfig;
    }

    public XHAnalysysBuryConfig(AnalysysBuryConfig analysysBuryConfig, XHBuryConfig xHBuryConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(analysysBuryConfig, (i & 2) != 0 ? new XHBuryConfig() : xHBuryConfig);
    }

    public final AnalysysBuryConfig getAnalysysBuryConfig() {
        return this.analysysBuryConfig;
    }

    public final XHBuryConfig getXhBuryConfig() {
        return this.xhBuryConfig;
    }
}

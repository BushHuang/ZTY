package com.xh.xhcore.common.statistic.strategy;

import com.analysys.AnalysysConfig;
import com.analysys.process.AgentProcess;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\bJ\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryConfig;", "Lcom/xh/xhcore/common/statistic/strategy/IBuryConfig;", "()V", "config", "Lcom/analysys/AnalysysConfig;", "getConfig", "()Lcom/analysys/AnalysysConfig;", "mShakeConnectWebSocket", "", "getMShakeConnectWebSocket", "()Z", "setMShakeConnectWebSocket", "(Z)V", "enableShakeConnectWebSocket", "shakeConnectWebSocket", "setAutoHeatMap", "autoHeapMap", "setAutoTrackClick", "isTrackClick", "setAutoTrackFragmentPageView", "isTrackFragmentPageView", "setAutoTrackPageView", "autoTrackPageView", "setDebugMode", "debugMode", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AnalysysBuryConfig implements IBuryConfig {
    private final AnalysysConfig config = new AnalysysConfig();
    private boolean mShakeConnectWebSocket;

    public final AnalysysBuryConfig enableShakeConnectWebSocket(boolean shakeConnectWebSocket) {
        this.mShakeConnectWebSocket = shakeConnectWebSocket;
        return this;
    }

    public final AnalysysConfig getConfig() {
        return this.config;
    }

    public final boolean getMShakeConnectWebSocket() {
        return this.mShakeConnectWebSocket;
    }

    public final AnalysysBuryConfig setAutoHeatMap(boolean autoHeapMap) {
        this.config.setAutoHeatMap(autoHeapMap);
        return this;
    }

    public final AnalysysBuryConfig setAutoTrackClick(boolean isTrackClick) {
        this.config.setAutoTrackClick(isTrackClick);
        return this;
    }

    public final AnalysysBuryConfig setAutoTrackFragmentPageView(boolean isTrackFragmentPageView) {
        this.config.setAutoTrackFragmentPageView(isTrackFragmentPageView);
        return this;
    }

    public final AnalysysBuryConfig setAutoTrackPageView(boolean autoTrackPageView) {
        this.config.setAutoTrackPageView(autoTrackPageView);
        return this;
    }

    public final AnalysysBuryConfig setDebugMode(int debugMode) {
        AgentProcess.getInstance().setDebug(debugMode);
        return this;
    }

    public final void setMShakeConnectWebSocket(boolean z) {
        this.mShakeConnectWebSocket = z;
    }
}

package com.xh.xhcore.common.statistic.connect.traffic.download;

import com.xh.xhcore.common.statistic.connect.BuryNetworkType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/traffic/download/InternetDownloadTrafficManager;", "Lcom/xh/xhcore/common/statistic/connect/traffic/download/BaseDownloadTrafficManager;", "()V", "getNetworkType", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InternetDownloadTrafficManager extends BaseDownloadTrafficManager {

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<InternetDownloadTrafficManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<InternetDownloadTrafficManager>() {
        @Override
        public final InternetDownloadTrafficManager invoke() {
            return new InternetDownloadTrafficManager();
        }
    });

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/traffic/download/InternetDownloadTrafficManager$Companion;", "", "()V", "instance", "Lcom/xh/xhcore/common/statistic/connect/traffic/download/InternetDownloadTrafficManager;", "getInstance", "()Lcom/xh/xhcore/common/statistic/connect/traffic/download/InternetDownloadTrafficManager;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InternetDownloadTrafficManager getInstance() {
            return (InternetDownloadTrafficManager) InternetDownloadTrafficManager.instance$delegate.getValue();
        }
    }

    @Override
    public int getNetworkType() {
        return BuryNetworkType.internet.ordinal();
    }
}

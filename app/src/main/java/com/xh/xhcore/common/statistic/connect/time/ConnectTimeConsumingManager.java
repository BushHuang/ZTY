package com.xh.xhcore.common.statistic.connect.time;

import com.xh.xhcore.common.statistic.BaseBuryProcessManager;
import com.xh.xhcore.common.statistic.ConstStatistics;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0014¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/time/ConnectTimeConsumingManager;", "Lcom/xh/xhcore/common/statistic/BaseBuryProcessManager;", "", "()V", "getBuryDataModel", "", "getBuryName", "", "getBuryType", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConnectTimeConsumingManager extends BaseBuryProcessManager<Long> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<ConnectTimeConsumingManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ConnectTimeConsumingManager>() {
        @Override
        public final ConnectTimeConsumingManager invoke() {
            return new ConnectTimeConsumingManager(null);
        }
    });

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/time/ConnectTimeConsumingManager$Companion;", "", "()V", "instance", "Lcom/xh/xhcore/common/statistic/connect/time/ConnectTimeConsumingManager;", "getInstance", "()Lcom/xh/xhcore/common/statistic/connect/time/ConnectTimeConsumingManager;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConnectTimeConsumingManager getInstance() {
            return (ConnectTimeConsumingManager) ConnectTimeConsumingManager.instance$delegate.getValue();
        }
    }

    private ConnectTimeConsumingManager() {
    }

    public ConnectTimeConsumingManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override
    public Object getBuryDataModel() {
        Long l = (Long) CollectionsKt.minOrNull((Iterable) getBuryDataList());
        long jLongValue = l == null ? 0L : l.longValue();
        Long l2 = (Long) CollectionsKt.maxOrNull((Iterable) getBuryDataList());
        return new ConnectTimeConsumingModel(jLongValue, l2 != null ? l2.longValue() : 0L, (long) CollectionsKt.averageOfLong(getBuryDataList()));
    }

    @Override
    public String getBuryName() {
        return ConstStatistics.INSTANCE.getCONNECTION_TIME_CONSUMING_NAME();
    }

    @Override
    protected int getBuryType() {
        return ConstStatistics.INSTANCE.getCONNECTION_TIME_CONSUMING_TYPE();
    }
}

package com.xh.xhcore.common.statistic.connect.speed.upload;

import com.xh.xhcore.common.statistic.BaseBuryProcessManager;
import com.xh.xhcore.common.statistic.BuryUtil;
import com.xh.xhcore.common.statistic.ConstStatistics;
import com.xh.xhcore.common.statistic.connect.BuryNetworkType;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH&¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/speed/upload/BaseUploadSpeedManager;", "Lcom/xh/xhcore/common/statistic/BaseBuryProcessManager;", "", "()V", "getBuryDataModel", "", "getBuryName", "", "getBuryType", "", "getNetworkType", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseUploadSpeedManager extends BaseBuryProcessManager<Long> {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/speed/upload/BaseUploadSpeedManager$Companion;", "", "()V", "getInstance", "Lcom/xh/xhcore/common/statistic/connect/speed/upload/BaseUploadSpeedManager;", "url", "", "getInternetInstance", "Lcom/xh/xhcore/common/statistic/connect/speed/upload/InternetUploadSpeedManager;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BaseUploadSpeedManager getInstance(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return BuryNetworkType.internet.ordinal() == BuryUtil.INSTANCE.getNetworkTypeFromUrl(url) ? InternetUploadSpeedManager.INSTANCE.getInstance() : IntranetUploadSpeedManager.INSTANCE.getInstance();
        }

        @JvmStatic
        public final InternetUploadSpeedManager getInternetInstance() {
            return InternetUploadSpeedManager.INSTANCE.getInstance();
        }
    }

    @JvmStatic
    public static final BaseUploadSpeedManager getInstance(String str) {
        return INSTANCE.getInstance(str);
    }

    @JvmStatic
    public static final InternetUploadSpeedManager getInternetInstance() {
        return INSTANCE.getInternetInstance();
    }

    @Override
    public Object getBuryDataModel() {
        Long l = (Long) CollectionsKt.maxOrNull((Iterable) getBuryDataList());
        long jLongValue = l == null ? 0L : l.longValue();
        Long l2 = (Long) CollectionsKt.minOrNull((Iterable) getBuryDataList());
        return new UploadSpeedModel(jLongValue, l2 != null ? l2.longValue() : 0L, (long) CollectionsKt.averageOfLong(getBuryDataList()), getNetworkType());
    }

    @Override
    public String getBuryName() {
        return ConstStatistics.INSTANCE.getUPLOAD_SPEED_NAME();
    }

    @Override
    protected int getBuryType() {
        return ConstStatistics.INSTANCE.getUPLOAD_SPEED_TYPE();
    }

    public abstract int getNetworkType();
}

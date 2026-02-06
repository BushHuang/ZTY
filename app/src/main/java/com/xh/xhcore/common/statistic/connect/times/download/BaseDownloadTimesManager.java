package com.xh.xhcore.common.statistic.connect.times.download;

import com.xh.xhcore.common.statistic.BaseBuryProcessManager;
import com.xh.xhcore.common.statistic.BuryUtil;
import com.xh.xhcore.common.statistic.ConstStatistics;
import com.xh.xhcore.common.statistic.connect.BuryNetworkType;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0002H&¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/times/download/BaseDownloadTimesManager;", "Lcom/xh/xhcore/common/statistic/BaseBuryProcessManager;", "", "()V", "getBuryDataModel", "", "getBuryName", "", "getNetworkType", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseDownloadTimesManager extends BaseBuryProcessManager<Integer> {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/times/download/BaseDownloadTimesManager$Companion;", "", "()V", "getInstance", "Lcom/xh/xhcore/common/statistic/connect/times/download/BaseDownloadTimesManager;", "url", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BaseDownloadTimesManager getInstance(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return BuryNetworkType.internet.ordinal() == BuryUtil.INSTANCE.getNetworkTypeFromUrl(url) ? InternetDownloadTimesManager.INSTANCE.getInstance() : IntranetDownloadTimesManager.INSTANCE.getInstance();
        }
    }

    @JvmStatic
    public static final BaseDownloadTimesManager getInstance(String str) {
        return INSTANCE.getInstance(str);
    }

    @Override
    public Object getBuryDataModel() {
        return new DownloadTimesModel(getBuryDataList().size(), getNetworkType());
    }

    @Override
    public String getBuryName() {
        return ConstStatistics.INSTANCE.getDOWNLOAD_TIMES_NAME();
    }

    public abstract int getNetworkType();
}

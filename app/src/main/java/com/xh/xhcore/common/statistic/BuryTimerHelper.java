package com.xh.xhcore.common.statistic;

import com.xh.xhcore.common.helper.TimerHelper;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.connect.speed.download.InternetDownloadSpeedManager;
import com.xh.xhcore.common.statistic.connect.speed.download.IntranetDownloadSpeedManager;
import com.xh.xhcore.common.statistic.connect.speed.upload.InternetUploadSpeedManager;
import com.xh.xhcore.common.statistic.connect.speed.upload.IntranetUploadSpeedManager;
import com.xh.xhcore.common.statistic.connect.time.ConnectTimeConsumingManager;
import com.xh.xhcore.common.statistic.connect.times.download.InternetDownloadTimesManager;
import com.xh.xhcore.common.statistic.connect.times.download.IntranetDownloadTimesManager;
import com.xh.xhcore.common.statistic.connect.traffic.download.InternetDownloadTrafficManager;
import com.xh.xhcore.common.statistic.connect.traffic.download.IntranetDownloadTrafficManager;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/statistic/BuryTimerHelper;", "Lcom/xh/xhcore/common/helper/TimerHelper;", "()V", "currentSamplingCount", "", "getCurrentSamplingCount", "()I", "setCurrentSamplingCount", "(I)V", "statisticsTime", "", "clearItems", "", "computeAndAddOneBuryData", "doTimerAction", "getTimerDelay", "getTimerDuration", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BuryTimerHelper extends TimerHelper {

    public static final Companion INSTANCE = new Companion(null);
    private static final List<BaseBuryDataManager<?>> buryManagers = CollectionsKt.listOf((Object[]) new BaseBuryDataManager[]{ConnectTimeConsumingManager.INSTANCE.getInstance(), IntranetDownloadSpeedManager.INSTANCE.getInstance(), InternetDownloadSpeedManager.INSTANCE.getInstance(), IntranetUploadSpeedManager.INSTANCE.getInstance(), InternetUploadSpeedManager.INSTANCE.getInstance(), IntranetDownloadTrafficManager.INSTANCE.getInstance(), InternetDownloadTrafficManager.INSTANCE.getInstance(), IntranetDownloadTimesManager.INSTANCE.getInstance(), InternetDownloadTimesManager.INSTANCE.getInstance()});
    private int currentSamplingCount;
    private long statisticsTime;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/statistic/BuryTimerHelper$Companion;", "", "()V", "buryManagers", "", "Lcom/xh/xhcore/common/statistic/BaseBuryDataManager;", "getBuryManagers", "()Ljava/util/List;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<BaseBuryDataManager<?>> getBuryManagers() {
            return BuryTimerHelper.buryManagers;
        }
    }

    private final void clearItems() {
        LogUtils.INSTANCE.d("this = " + getClass().getSimpleName() + " clearItems");
        Iterator<BaseBuryDataManager<?>> it = buryManagers.iterator();
        while (it.hasNext()) {
            it.next().clearItems();
        }
    }

    private final void computeAndAddOneBuryData() {
        Iterator<BaseBuryDataManager<?>> it = buryManagers.iterator();
        while (it.hasNext()) {
            it.next().computeAndAddOneBuryData(this.statisticsTime);
        }
    }

    @Override
    public void doTimerAction() {
        if (this.currentSamplingCount % ConstStatistics.INSTANCE.getBURY_UPLOAD_CLIENT_TIMES() == 0) {
            if (this.currentSamplingCount != 0) {
                computeAndAddOneBuryData();
                this.statisticsTime += ConstStatistics.INSTANCE.getBURY_SAMPLING_PERIOD_UPLOAD();
            }
            clearItems();
        }
        this.currentSamplingCount++;
    }

    public final int getCurrentSamplingCount() {
        return this.currentSamplingCount;
    }

    @Override
    public long getTimerDelay() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long bury_sampling_period_upload = ConstStatistics.INSTANCE.getBURY_SAMPLING_PERIOD_UPLOAD() - ((jCurrentTimeMillis % ConstStatistics.INSTANCE.getONE_DAY()) % ConstStatistics.INSTANCE.getBURY_SAMPLING_PERIOD_UPLOAD());
        long bury_sampling_period_upload2 = (ConstStatistics.INSTANCE.getBURY_SAMPLING_PERIOD_UPLOAD() / 2) + bury_sampling_period_upload;
        this.statisticsTime = jCurrentTimeMillis + bury_sampling_period_upload + ConstStatistics.INSTANCE.getBURY_SAMPLING_PERIOD_UPLOAD();
        return bury_sampling_period_upload2;
    }

    @Override
    public long getTimerDuration() {
        return ConstStatistics.INSTANCE.getBURY_SAMPLING_PERIOD_CLIENT();
    }

    public final void setCurrentSamplingCount(int i) {
        this.currentSamplingCount = i;
    }
}

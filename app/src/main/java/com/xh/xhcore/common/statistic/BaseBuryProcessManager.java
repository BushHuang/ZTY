package com.xh.xhcore.common.statistic;

import com.xh.xhcore.common.util.JsonUtil;
import kotlin.Deprecated;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH\u0015¨\u0006\u000e"}, d2 = {"Lcom/xh/xhcore/common/statistic/BaseBuryProcessManager;", "T", "Lcom/xh/xhcore/common/statistic/BaseBuryDataManager;", "()V", "computeAndAddOneBuryData", "", "statisticsTime", "", "getBuryDataModel", "", "getBuryName", "", "getBuryType", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseBuryProcessManager<T> extends BaseBuryDataManager<T> {
    @Override
    public void computeAndAddOneBuryData(long statisticsTime) {
        super.computeAndAddOneBuryData(statisticsTime);
        if (getBuryDataList().size() > 0) {
            DataCollectionUtil.setBury(new BuryEvent(getBuryType(), getBuryName(), JsonUtil.toJsonString(getBuryDataModel()), statisticsTime));
        }
    }

    public abstract Object getBuryDataModel();

    public abstract String getBuryName();

    @Deprecated(message = "大数据已废弃type字段")
    protected int getBuryType() {
        return 0;
    }
}

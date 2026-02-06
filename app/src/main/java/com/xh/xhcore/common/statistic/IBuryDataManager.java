package com.xh.xhcore.common.statistic;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0004H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/statistic/IBuryDataManager;", "T", "", "addItem", "", "t", "(Ljava/lang/Object;)V", "clearItems", "computeAndAddOneBuryData", "statisticsTime", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IBuryDataManager<T> {
    void addItem(T t);

    void clearItems();

    void computeAndAddOneBuryData(long statisticsTime);
}

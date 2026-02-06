package com.xh.xhcore.common.statistic;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/xh/xhcore/common/statistic/BaseBuryDataManager;", "T", "Lcom/xh/xhcore/common/statistic/IBuryDataManager;", "()V", "buryDataList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getBuryDataList", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "addItem", "", "t", "(Ljava/lang/Object;)V", "clearItems", "computeAndAddOneBuryData", "statisticsTime", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseBuryDataManager<T> implements IBuryDataManager<T> {
    private final CopyOnWriteArrayList<T> buryDataList = new CopyOnWriteArrayList<>();

    @Override
    public void addItem(T t) {
        LogUtils.INSTANCE.d("this = " + getClass().getSimpleName() + " t = " + t);
        this.buryDataList.add(t);
    }

    @Override
    public void clearItems() {
        this.buryDataList.clear();
    }

    @Override
    public void computeAndAddOneBuryData(long statisticsTime) {
    }

    public final CopyOnWriteArrayList<T> getBuryDataList() {
        return this.buryDataList;
    }
}

package com.xuehai.launcher.common.adapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/xuehai/launcher/common/adapter/BaseFragmentPagerAdapter;", "V", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "list", "", "(Landroidx/fragment/app/FragmentManager;Ljava/util/Collection;)V", "dataList", "Ljava/util/ArrayList;", "getDataList", "()Ljava/util/ArrayList;", "getCount", "", "setDataList", "", "notify", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseFragmentPagerAdapter<V> extends FragmentStatePagerAdapter {
    private final ArrayList<V> dataList;

    public BaseFragmentPagerAdapter(FragmentManager fragmentManager, Collection<? extends V> collection) {
        super(fragmentManager);
        Intrinsics.checkNotNullParameter(fragmentManager, "fm");
        this.dataList = new ArrayList<>();
        setDataList(collection, false);
    }

    public static void setDataList$default(BaseFragmentPagerAdapter baseFragmentPagerAdapter, Collection collection, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setDataList");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        baseFragmentPagerAdapter.setDataList(collection, z);
    }

    @Override
    public int getCount() {
        return this.dataList.size();
    }

    public final ArrayList<V> getDataList() {
        return this.dataList;
    }

    public final void setDataList(Collection<? extends V> list, boolean notify) {
        this.dataList.clear();
        if (list != null) {
            this.dataList.addAll(list);
        }
        if (notify) {
            notifyDataSetChanged();
        }
    }
}

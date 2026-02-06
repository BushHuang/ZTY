package com.xuehai.launcher.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xuehai.launcher.R;
import com.xuehai.launcher.common.base.AbsFragment;
import com.xuehai.launcher.common.ext.FragmentExtKt;
import com.xuehai.launcher.common.ext.ViewModelFactoryKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u00142\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001a"}, d2 = {"Lcom/xuehai/launcher/other/OtherFragment;", "Lcom/xuehai/launcher/common/base/AbsFragment;", "()V", "adapter", "Lcom/xuehai/launcher/other/OtherAdapter;", "viewModel", "Lcom/xuehai/launcher/other/OtherViewModel;", "getViewModel", "()Lcom/xuehai/launcher/other/OtherViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "showOtherList", "list", "", "Lcom/xuehai/launcher/other/OtherBean;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OtherFragment extends AbsFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private OtherAdapter adapter;

    private final Lazy viewModel;

    public OtherFragment() {
        final OtherFragment otherFragment = this;
        this.viewModel = FragmentExtKt.createMyViewModelLazy(otherFragment, new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return otherFragment.getActivity();
            }
        }, Reflection.getOrCreateKotlinClass(OtherViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = otherFragment.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "this.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        });
    }

    private final OtherViewModel getViewModel() {
        return (OtherViewModel) this.viewModel.getValue();
    }

    private static final void m164onViewCreated$lambda1(OtherFragment otherFragment, DialogFragment dialogFragment) {
        Intrinsics.checkNotNullParameter(otherFragment, "this$0");
        FragmentManager fragmentManager = otherFragment.getFragmentManager();
        if (fragmentManager != null) {
            dialogFragment.show(fragmentManager, dialogFragment.getTag());
        }
    }

    private static final void m165onViewCreated$lambda2(OtherFragment otherFragment, List list) {
        Intrinsics.checkNotNullParameter(otherFragment, "this$0");
        otherFragment.showOtherList(list);
    }

    private final void showOtherList(List<OtherBean> list) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            FragmentActivity fragmentActivity = activity;
            this.adapter = new OtherAdapter(fragmentActivity, list);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.otherRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity));
            OtherAdapter otherAdapter = this.adapter;
            if (otherAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                otherAdapter = null;
            }
            recyclerView.setAdapter(otherAdapter);
        }
    }

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(2131492948, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getViewModel().getDialogFragment().observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                OtherFragment.m164onViewCreated$lambda1(this.f$0, (DialogFragment) obj);
            }
        });
        getViewModel().getListData().observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public final void onChanged(Object obj) {
                OtherFragment.m165onViewCreated$lambda2(this.f$0, (List) obj);
            }
        });
        getViewModel().load();
    }
}

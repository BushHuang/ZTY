package com.xuehai.launcher.other;

import android.view.View;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import com.xuehai.launcher.common.base.AbsViewModel;
import com.xuehai.launcher.common.ext.SingleLiveEvent;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import com.xuehai.launcher.util.ZtyClientUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/xuehai/launcher/other/OtherViewModel;", "Lcom/xuehai/launcher/common/base/AbsViewModel;", "()V", "dialogFragment", "Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "Landroidx/fragment/app/DialogFragment;", "getDialogFragment", "()Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "listData", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/xuehai/launcher/other/OtherBean;", "getListData", "()Landroidx/lifecycle/MutableLiveData;", "load", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OtherViewModel extends AbsViewModel {
    private final MutableLiveData<List<OtherBean>> listData = new MutableLiveData<>();
    private final SingleLiveEvent<DialogFragment> dialogFragment = new SingleLiveEvent<>();

    private static final void m166load$lambda0(OtherViewModel otherViewModel, View view) {
        Intrinsics.checkNotNullParameter(otherViewModel, "this$0");
        otherViewModel.dialogFragment.setValue(DialogProvider.Builder.negative$default(DialogProvider.Builder.message$default(new DialogProvider.Builder(), otherViewModel.getString(2131689514), 0, 2, null), null, null, 3, null).positive(otherViewModel.getString(2131689512), new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view2) {
                Intrinsics.checkNotNullParameter(view2, "it");
                ZtyClientUtil.clearData();
            }
        }).build());
    }

    private static final void m167load$lambda1(OtherViewModel otherViewModel, View view) {
        Intrinsics.checkNotNullParameter(otherViewModel, "this$0");
        otherViewModel.dialogFragment.setValue(new InputDialogFragment());
    }

    public final SingleLiveEvent<DialogFragment> getDialogFragment() {
        return this.dialogFragment;
    }

    public final MutableLiveData<List<OtherBean>> getListData() {
        return this.listData;
    }

    public final void load() {
        this.listData.setValue(CollectionsKt.arrayListOf(new OtherBean(getString(2131689513), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                OtherViewModel.m166load$lambda0(this.f$0, view);
            }
        }), new OtherBean(getString(2131689518), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                OtherViewModel.m167load$lambda1(this.f$0, view);
            }
        })));
    }
}

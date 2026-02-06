package com.xuehai.launcher.common.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.ext.SingleLiveEvent;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import io.reactivex.disposables.CompositeDisposable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010#\u001a\u00020\t2\b\b\u0001\u0010$\u001a\u00020%J)\u0010#\u001a\u00020\t2\b\b\u0001\u0010$\u001a\u00020%2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020(0'\"\u00020(¢\u0006\u0002\u0010)J\u0010\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020-J\b\u0010.\u001a\u00020\u0016H\u0016J\b\u0010/\u001a\u00020+H\u0014J\u0010\u00100\u001a\u00020+2\b\b\u0001\u00101\u001a\u00020%J\u000e\u00100\u001a\u00020+2\u0006\u00102\u001a\u00020\tJ\u000e\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020\tR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007R\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007¨\u00065"}, d2 = {"Lcom/xuehai/launcher/common/base/AbsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_finish", "Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "Ljava/lang/Void;", "get_finish$common_studentToBRelease", "()Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "_progress", "", "get_progress$common_studentToBRelease", "_showMessage", "get_showMessage$common_studentToBRelease", "_tipDialog", "Lcom/xuehai/launcher/common/widget/dialog/DialogProvider$Builder;", "get_tipDialog$common_studentToBRelease", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "dataLoading", "Landroidx/lifecycle/MutableLiveData;", "", "getDataLoading", "()Landroidx/lifecycle/MutableLiveData;", "dragLoading", "getDragLoading", "finish", "getFinish", "progress", "getProgress", "showMessage", "getShowMessage", "tipDialog", "getTipDialog", "getString", "stringRes", "", "args", "", "", "(I[Ljava/lang/Object;)Ljava/lang/String;", "hideProgress", "", "delay", "", "isLoading", "onCleared", "showProgress", "resId", "string", "toastMessage", "message", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class AbsViewModel extends ViewModel {
    private final SingleLiveEvent<Void> _finish;
    private final SingleLiveEvent<String> _progress;
    private final SingleLiveEvent<String> _showMessage;
    private final SingleLiveEvent<DialogProvider.Builder> _tipDialog;
    private final SingleLiveEvent<Void> finish;
    private final SingleLiveEvent<String> progress;
    private final SingleLiveEvent<String> showMessage;
    private final SingleLiveEvent<DialogProvider.Builder> tipDialog;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> dragLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> dataLoading = new MutableLiveData<>();

    public AbsViewModel() {
        SingleLiveEvent<String> singleLiveEvent = new SingleLiveEvent<>();
        this._showMessage = singleLiveEvent;
        this.showMessage = singleLiveEvent;
        SingleLiveEvent<String> singleLiveEvent2 = new SingleLiveEvent<>();
        this._progress = singleLiveEvent2;
        this.progress = singleLiveEvent2;
        SingleLiveEvent<Void> singleLiveEvent3 = new SingleLiveEvent<>();
        this._finish = singleLiveEvent3;
        this.finish = singleLiveEvent3;
        SingleLiveEvent<DialogProvider.Builder> singleLiveEvent4 = new SingleLiveEvent<>();
        this._tipDialog = singleLiveEvent4;
        this.tipDialog = singleLiveEvent4;
    }

    public static void hideProgress$default(AbsViewModel absViewModel, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hideProgress");
        }
        if ((i & 1) != 0) {
            j = 0;
        }
        absViewModel.hideProgress(j);
    }

    private static final void m53hideProgress$lambda0(AbsViewModel absViewModel) {
        Intrinsics.checkNotNullParameter(absViewModel, "this$0");
        absViewModel.progress.setValue(null);
    }

    public final CompositeDisposable getCompositeDisposable() {
        return this.compositeDisposable;
    }

    public final MutableLiveData<Boolean> getDataLoading() {
        return this.dataLoading;
    }

    public final MutableLiveData<Boolean> getDragLoading() {
        return this.dragLoading;
    }

    protected final SingleLiveEvent<Void> getFinish() {
        return this.finish;
    }

    protected final SingleLiveEvent<String> getProgress() {
        return this.progress;
    }

    protected final SingleLiveEvent<String> getShowMessage() {
        return this.showMessage;
    }

    public final String getString(int stringRes) {
        String string = BaseApplication.INSTANCE.getInstance().getString(stringRes);
        Intrinsics.checkNotNullExpressionValue(string, "BaseApplication.getInstance().getString(stringRes)");
        return string;
    }

    public final String getString(int stringRes, Object... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        String string = BaseApplication.INSTANCE.getInstance().getString(stringRes, Arrays.copyOf(args, args.length));
        Intrinsics.checkNotNullExpressionValue(string, "BaseApplication.getInsta…tString(stringRes, *args)");
        return string;
    }

    public final SingleLiveEvent<DialogProvider.Builder> getTipDialog() {
        return this.tipDialog;
    }

    public final SingleLiveEvent<Void> get_finish$common_studentToBRelease() {
        return this._finish;
    }

    public final SingleLiveEvent<String> get_progress$common_studentToBRelease() {
        return this._progress;
    }

    public final SingleLiveEvent<String> get_showMessage$common_studentToBRelease() {
        return this._showMessage;
    }

    public final SingleLiveEvent<DialogProvider.Builder> get_tipDialog$common_studentToBRelease() {
        return this._tipDialog;
    }

    public final void hideProgress(long delay) {
        if (delay > 0) {
            ThreadPlugins.runInUIThread(new Runnable() {
                @Override
                public final void run() {
                    AbsViewModel.m53hideProgress$lambda0(this.f$0);
                }
            }, delay);
        } else {
            LiveDataExtKt.action(this.progress);
        }
    }

    public boolean isLoading() {
        return Intrinsics.areEqual((Object) this.dataLoading.getValue(), (Object) true) || Intrinsics.areEqual((Object) this.dragLoading.getValue(), (Object) true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.dispose();
    }

    public final void showProgress(int resId) {
        showProgress(getString(resId));
    }

    public final void showProgress(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        LiveDataExtKt.set(this.progress, string);
    }

    public final void toastMessage(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        LiveDataExtKt.set(this.showMessage, message);
        MyLog.i("Debug[MDM]", message);
    }
}

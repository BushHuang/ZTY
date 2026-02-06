package com.xuehai.launcher.device;

import android.app.Application;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import com.xh.common.lib.sdk.samsung.AdminReceiver;
import com.xuehai.launcher.common.base.AbsAndroidViewModel;
import com.xuehai.launcher.common.base.AbsViewModel;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.plugins.rx.MyObserver;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.launcher.util.ZtyClientUtil;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.AppUtil;
import com.zaze.utils.ZCommand;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0006\u0010\u0016\u001a\u00020\u0013J\b\u0010\u0017\u001a\u00020\u0013H\u0014R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0010j\b\u0012\u0004\u0012\u00020\b`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xuehai/launcher/device/DeviceResetViewModel;", "Lcom/xuehai/launcher/common/base/AbsAndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "active", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/xuehai/launcher/device/DeviceResetBean;", "getActive", "()Landroidx/lifecycle/MutableLiveData;", "dataList", "getDataList", "deviceAdminListener", "Lcom/xh/common/lib/sdk/samsung/AdminReceiver$DeviceAdminListener;", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "add", "", "debugBean", "deleteSelf", "load", "onCleared", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceResetViewModel extends AbsAndroidViewModel {
    private final MutableLiveData<List<DeviceResetBean>> active;
    private final MutableLiveData<List<DeviceResetBean>> dataList;
    private final AdminReceiver.DeviceAdminListener deviceAdminListener;
    private final ArrayList<DeviceResetBean> list;

    public DeviceResetViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.list = new ArrayList<>();
        this.dataList = new MutableLiveData<>();
        this.active = new MutableLiveData<>();
        this.deviceAdminListener = new AdminReceiver.DeviceAdminListener() {
            @Override
            public final void onDeviceAdminStateChanged(boolean z) {
                DeviceResetViewModel.m98deviceAdminListener$lambda0(this.f$0, z);
            }
        };
    }

    private final void add(DeviceResetBean debugBean) {
        this.list.add(debugBean);
    }

    private final void deleteSelf() {
        String packageName = BaseApplication.INSTANCE.getInstance().getPackageName();
        if (ZCommand.isRoot()) {
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            AppUtil.unInstallApkSilent(packageName);
        } else {
            BaseApplication companion = BaseApplication.INSTANCE.getInstance();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            AppUtil.unInstall(companion, packageName);
        }
    }

    private static final void m98deviceAdminListener$lambda0(DeviceResetViewModel deviceResetViewModel, boolean z) {
        Intrinsics.checkNotNullParameter(deviceResetViewModel, "this$0");
        if (!z) {
            deviceResetViewModel.deleteSelf();
        }
        AbsViewModel.hideProgress$default(deviceResetViewModel, 0L, 1, null);
    }

    private static final void m102load$lambda3(final DeviceResetViewModel deviceResetViewModel, View view) {
        Intrinsics.checkNotNullParameter(deviceResetViewModel, "this$0");
        deviceResetViewModel.showProgress("正在还原请稍后...");
        Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return DeviceResetViewModel.m103load$lambda3$lambda1();
            }
        }).subscribeOn(ThreadPlugins.multiScheduler()).doFinally(new Action() {
            @Override
            public final void run() {
                DeviceResetViewModel.m104load$lambda3$lambda2(this.f$0);
            }
        }).subscribe(new MyObserver(deviceResetViewModel.getCompositeDisposable()));
    }

    private static final Unit m103load$lambda3$lambda1() {
        PolicyManager.INSTANCE.unLock();
        return Unit.INSTANCE;
    }

    private static final void m104load$lambda3$lambda2(DeviceResetViewModel deviceResetViewModel) {
        Intrinsics.checkNotNullParameter(deviceResetViewModel, "this$0");
        AbsViewModel.hideProgress$default(deviceResetViewModel, 0L, 1, null);
    }

    private static final void m105load$lambda5(final DeviceResetViewModel deviceResetViewModel, View view) {
        Intrinsics.checkNotNullParameter(deviceResetViewModel, "this$0");
        if (PolicyManager.INSTANCE.isLocked()) {
            deviceResetViewModel.toastMessage("请先一键还原, 再删除");
            return;
        }
        if (!DeviceActiveManager.INSTANCE.isAdminActiveInSystem(deviceResetViewModel.getApplication())) {
            MyLog.i("[MDM]", "卸载mdm");
            DeviceActiveManager.INSTANCE.removeDeviceAdmin(deviceResetViewModel.getApplication());
            deviceResetViewModel.deleteSelf();
            return;
        }
        deviceResetViewModel.showProgress("正在准备删除请稍后...");
        PolicyManager.getApplicationPolicyProxy().setApplicationUninstallationEnabled(ZtyClientUtil.INSTANCE.getMatchedZtyClient());
        PolicyManager.getApplicationPolicyProxy().uninstallApplication(ZtyClientUtil.INSTANCE.getMatchedZtyClient());
        AdminReceiver.addListener(deviceResetViewModel.deviceAdminListener);
        DeviceActiveManager.INSTANCE.removeDeviceAdmin(deviceResetViewModel.getApplication());
        if (DeviceModelUtil.isHuaweiHEMDevice()) {
            ThreadPlugins.runInUIThread(new Runnable() {
                @Override
                public final void run() {
                    DeviceResetViewModel.m106load$lambda5$lambda4(this.f$0);
                }
            }, 2000L);
        }
    }

    private static final void m106load$lambda5$lambda4(DeviceResetViewModel deviceResetViewModel) {
        Intrinsics.checkNotNullParameter(deviceResetViewModel, "this$0");
        deviceResetViewModel.deviceAdminListener.onDeviceAdminStateChanged(true);
    }

    public final MutableLiveData<List<DeviceResetBean>> getActive() {
        return this.active;
    }

    public final MutableLiveData<List<DeviceResetBean>> getDataList() {
        return this.dataList;
    }

    public final void load() {
        this.list.clear();
        add(new DeviceResetBean("一键还原", new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DeviceResetViewModel.m102load$lambda3(this.f$0, view);
            }
        }, 1, null, 8, null));
        add(new DeviceResetBean("删除" + getString(2131689506) + "（注: 请先一键还原, 再删除）", new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DeviceResetViewModel.m105load$lambda5(this.f$0, view);
            }
        }, 1, null, 8, null));
        LiveDataExtKt.set(this.dataList, this.list);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        AdminReceiver.removeListener(this.deviceAdminListener);
    }
}

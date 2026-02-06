package com.xuehai.launcher.device;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xuehai.launcher.R;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.ext.AppCompatActivityExtKt;
import com.xuehai.launcher.common.ext.MyViewModelLazy;
import com.xuehai.launcher.common.ext.ViewModelFactoryKt;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.widget.NormalItemDecoration;
import com.zaze.utils.DisplayUtil;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0018\u0010\u0010\u001a\u00020\f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/device/DeviceResetActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "()V", "adapter", "Lcom/xuehai/launcher/device/DeviceResetAdapter;", "viewModel", "Lcom/xuehai/launcher/device/DeviceResetViewModel;", "getViewModel", "()Lcom/xuehai/launcher/device/DeviceResetViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showList", "list", "", "Lcom/xuehai/launcher/device/DeviceResetBean;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceResetActivity extends AbsActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private DeviceResetAdapter adapter;

    private final Lazy viewModel;

    public DeviceResetActivity() {
        final DeviceResetActivity deviceResetActivity = this;
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        };
        this.viewModel = new MyViewModelLazy(new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return deviceResetActivity;
            }
        }, Reflection.getOrCreateKotlinClass(DeviceResetViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = deviceResetActivity.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, function0 == null ? new Function0<ViewModelProvider.Factory>() {
            {
                super(0);
            }

            @Override
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = deviceResetActivity.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        } : function0);
    }

    private final DeviceResetViewModel getViewModel() {
        return (DeviceResetViewModel) this.viewModel.getValue();
    }

    private static final void m95onCreate$lambda2$lambda0(DeviceResetActivity deviceResetActivity, List list) {
        Intrinsics.checkNotNullParameter(deviceResetActivity, "this$0");
        deviceResetActivity.showList(list);
    }

    private static final void m96onCreate$lambda2$lambda1(DeviceResetActivity deviceResetActivity, List list) {
        Intrinsics.checkNotNullParameter(deviceResetActivity, "this$0");
        DeviceActiveManager.INSTANCE.addDeviceAdmin(deviceResetActivity);
    }

    private static final void m97onCreate$lambda3(DeviceResetActivity deviceResetActivity, View view) {
        Intrinsics.checkNotNullParameter(deviceResetActivity, "this$0");
        deviceResetActivity.onBackPressed();
    }

    private final void showList(List<DeviceResetBean> list) {
        Unit unit;
        DeviceResetAdapter deviceResetAdapter = this.adapter;
        if (deviceResetAdapter != null) {
            deviceResetAdapter.setDataList(list);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            DeviceResetActivity deviceResetActivity = this;
            DeviceResetAdapter deviceResetAdapter2 = new DeviceResetAdapter(deviceResetActivity, list);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.developerRecyclerView);
            NormalItemDecoration normalItemDecoration = new NormalItemDecoration(deviceResetActivity);
            NormalItemDecoration.setPadding$default(normalItemDecoration, (int) DisplayUtil.pxFromDp$default(24.0f, null, 2, null), 0, 0, 0, 14, null);
            recyclerView.addItemDecoration(normalItemDecoration);
            recyclerView.setLayoutManager(new LinearLayoutManager(deviceResetActivity));
            recyclerView.setAdapter(deviceResetAdapter2);
            this.adapter = deviceResetAdapter2;
        }
    }

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        XHAppUtil.killAllProcess();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(savedInstanceState);
        setContentView(2131492914);
        DeviceResetViewModel viewModel = getViewModel();
        DeviceResetActivity deviceResetActivity = this;
        viewModel.getDataList().observe(deviceResetActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                DeviceResetActivity.m95onCreate$lambda2$lambda0(this.f$0, (List) obj);
            }
        });
        viewModel.getActive().observe(deviceResetActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                DeviceResetActivity.m96onCreate$lambda2$lambda1(this.f$0, (List) obj);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.developerToolbarTitle)).setText(getString(2131689518));
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(R.id.developerToolbar);
        Intrinsics.checkNotNullExpressionValue(toolbar, "developerToolbar");
        AppCompatActivityExtKt.setupActionBar(this, toolbar, new Function1<ActionBar, Unit>() {
            @Override
            public Unit invoke(ActionBar actionBar) {
                invoke2(actionBar);
                return Unit.INSTANCE;
            }

            public final void invoke2(ActionBar actionBar) {
                Intrinsics.checkNotNullParameter(actionBar, "$this$setupActionBar");
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        });
        ((Toolbar) _$_findCachedViewById(R.id.developerToolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DeviceResetActivity.m97onCreate$lambda3(this.f$0, view);
            }
        });
        getViewModel().load();
    }
}

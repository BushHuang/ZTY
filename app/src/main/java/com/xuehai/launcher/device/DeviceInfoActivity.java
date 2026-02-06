package com.xuehai.launcher.device;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.xuehai.launcher.R;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.ext.AppCompatActivityExtKt;
import com.xuehai.launcher.common.ext.MyViewModelLazy;
import com.xuehai.launcher.common.ext.ViewModelFactoryKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/xuehai/launcher/device/DeviceInfoActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "()V", "viewModel", "Lcom/xuehai/launcher/device/DeviceInfoViewModel;", "getViewModel", "()Lcom/xuehai/launcher/device/DeviceInfoViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getStatusBarColor", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceInfoActivity extends AbsActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    private final Lazy viewModel;

    public DeviceInfoActivity() {
        final DeviceInfoActivity deviceInfoActivity = this;
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
                return deviceInfoActivity;
            }
        }, Reflection.getOrCreateKotlinClass(DeviceInfoViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = deviceInfoActivity.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, function0 == null ? new Function0<ViewModelProvider.Factory>() {
            {
                super(0);
            }

            @Override
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = deviceInfoActivity.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        } : function0);
    }

    private final DeviceInfoViewModel getViewModel() {
        return (DeviceInfoViewModel) this.viewModel.getValue();
    }

    private static final void m89onCreate$lambda0(DeviceInfoActivity deviceInfoActivity, String str) {
        Intrinsics.checkNotNullParameter(deviceInfoActivity, "this$0");
        ((TextView) deviceInfoActivity._$_findCachedViewById(R.id.deviceIdTv)).setText(str);
    }

    private static final void m90onCreate$lambda1(DeviceInfoActivity deviceInfoActivity, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(deviceInfoActivity, "this$0");
        ((ImageView) deviceInfoActivity._$_findCachedViewById(R.id.deviceIdQrIv)).setImageBitmap(bitmap);
    }

    private static final void m91onCreate$lambda2(DeviceInfoActivity deviceInfoActivity, String str) {
        Intrinsics.checkNotNullParameter(deviceInfoActivity, "this$0");
        ((TextView) deviceInfoActivity._$_findCachedViewById(R.id.deviceMacAddressTv)).setText(str);
    }

    private static final void m92onCreate$lambda3(DeviceInfoActivity deviceInfoActivity, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(deviceInfoActivity, "this$0");
        ((ImageView) deviceInfoActivity._$_findCachedViewById(R.id.deviceMacAddressQrIv)).setImageBitmap(bitmap);
    }

    private static final void m93onCreate$lambda4(DeviceInfoActivity deviceInfoActivity, View view) {
        Intrinsics.checkNotNullParameter(deviceInfoActivity, "this$0");
        deviceInfoActivity.onBackPressed();
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
    protected int getStatusBarColor() {
        return 2131099817;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(savedInstanceState);
        setContentView(2131492913);
        DeviceInfoActivity deviceInfoActivity = this;
        getViewModel().getDeviceIdMsg().observe(deviceInfoActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                DeviceInfoActivity.m89onCreate$lambda0(this.f$0, (String) obj);
            }
        });
        getViewModel().getDeviceIdQrBmp().observe(deviceInfoActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                DeviceInfoActivity.m90onCreate$lambda1(this.f$0, (Bitmap) obj);
            }
        });
        getViewModel().getMacAddressMsg().observe(deviceInfoActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                DeviceInfoActivity.m91onCreate$lambda2(this.f$0, (String) obj);
            }
        });
        getViewModel().getMacAddressQrBmp().observe(deviceInfoActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                DeviceInfoActivity.m92onCreate$lambda3(this.f$0, (Bitmap) obj);
            }
        });
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(R.id.deviceToolbar);
        Intrinsics.checkNotNullExpressionValue(toolbar, "deviceToolbar");
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
        ((Toolbar) _$_findCachedViewById(R.id.deviceToolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DeviceInfoActivity.m93onCreate$lambda4(this.f$0, view);
            }
        });
        getViewModel().loadDeviceInfo();
    }
}

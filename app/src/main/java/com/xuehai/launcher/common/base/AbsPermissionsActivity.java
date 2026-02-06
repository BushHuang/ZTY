package com.xuehai.launcher.common.base;

import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.PermissionManager;
import com.xuehai.launcher.common.widget.dialog.CustomDialogFragment;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¢\u0006\u0002\u0010\u0007J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\u0012\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0016R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R:\u0010\n\u001a.\u0012*\u0012(\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00050\u0005 \f*\u0014\u0012\u000e\b\u0001\u0012\n \f*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000e0\u000e0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/xuehai/launcher/common/base/AbsPermissionsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "permissions", "", "", "getPermissions", "()[Ljava/lang/String;", "permissions$delegate", "Lkotlin/Lazy;", "permissionsRequest", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "startSettingRequest", "Landroid/content/Intent;", "afterPermissionGranted", "", "isNew", "", "autoSetupPermission", "beforePermissionGranted", "getPermissionsToRequest", "hasPermission", "onPermissionDenied", "onPostCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSomePermanentlyDenied", "setupPermission", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbsPermissionsActivity extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    private final Lazy permissions = LazyKt.lazy(new Function0<String[]>() {
        {
            super(0);
        }

        @Override
        public final String[] invoke() {
            return this.this$0.getPermissionsToRequest();
        }
    });
    private final ActivityResultLauncher<String[]> permissionsRequest;
    private final ActivityResultLauncher<Intent> startSettingRequest;

    public AbsPermissionsActivity() {
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                AbsPermissionsActivity.m51permissionsRequest$lambda1(this.f$0, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…}\n            }\n        }");
        this.permissionsRequest = activityResultLauncherRegisterForActivityResult;
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                AbsPermissionsActivity.m52startSettingRequest$lambda2(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResul…)\n            }\n        }");
        this.startSettingRequest = activityResultLauncherRegisterForActivityResult2;
    }

    private final String[] getPermissions() {
        return (String[]) this.permissions.getValue();
    }

    private final boolean hasPermission() {
        return PermissionManager.INSTANCE.hasPermissions(getPermissions());
    }

    private static final void m51permissionsRequest$lambda1(AbsPermissionsActivity absPermissionsActivity, Map map) {
        Intrinsics.checkNotNullParameter(absPermissionsActivity, "this$0");
        Intrinsics.checkNotNullExpressionValue(map, "it");
        boolean zBooleanValue = true;
        boolean z = false;
        for (Map.Entry entry : map.entrySet()) {
            MyLog.i("[MDM]", "onRequestPermissionsResult registerForActivityResult: " + ((String) entry.getKey()) + ": " + entry.getValue());
            if (zBooleanValue) {
                Object value = entry.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "result.value");
                zBooleanValue = ((Boolean) value).booleanValue();
            }
            if (!z && Intrinsics.areEqual(entry.getValue(), (Object) false) && !ActivityCompat.shouldShowRequestPermissionRationale(absPermissionsActivity, (String) entry.getKey())) {
                z = true;
            }
        }
        if (zBooleanValue) {
            absPermissionsActivity.afterPermissionGranted(true);
        } else if (z) {
            absPermissionsActivity.onSomePermanentlyDenied();
        } else {
            absPermissionsActivity.onPermissionDenied();
        }
    }

    private static final void m52startSettingRequest$lambda2(AbsPermissionsActivity absPermissionsActivity, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(absPermissionsActivity, "this$0");
        if (absPermissionsActivity.hasPermission()) {
            absPermissionsActivity.afterPermissionGranted(true);
        } else {
            absPermissionsActivity.setupPermission();
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public void afterPermissionGranted(boolean isNew) {
    }

    public boolean autoSetupPermission() {
        return true;
    }

    public void beforePermissionGranted() {
    }

    public String[] getPermissionsToRequest() {
        return new String[0];
    }

    public void onPermissionDenied() {
        setupPermission();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (autoSetupPermission()) {
            setupPermission();
        }
    }

    public void onSomePermanentlyDenied() {
        DialogProvider.Builder builderNegative = DialogProvider.Builder.message$default(new DialogProvider.Builder(), "如果没有「" + PermissionManager.INSTANCE.getPermissionNames(getPermissions()) + "」相关权限，此应用可能无法正常工作。", 0, 2, null).negative("取消", new Function1<View, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view) {
                Intrinsics.checkNotNullParameter(view, "it");
                this.this$0.finish();
            }
        });
        if (ClientConfig.INSTANCE.isStudentClient()) {
            builderNegative.positive("重置权限", new Function1<View, Unit>() {
                @Override
                public Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                public final void invoke2(View view) {
                    Intrinsics.checkNotNullParameter(view, "it");
                    Object systemService = BaseApplication.INSTANCE.getInstance().getSystemService("activity");
                    if (systemService == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
                    }
                    ActivityManager activityManager = (ActivityManager) systemService;
                    if (Build.VERSION.SDK_INT >= 19) {
                        try {
                            activityManager.clearApplicationUserData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            DialogProvider.Builder.positive$default(builderNegative, null, new Function1<View, Unit>() {
                {
                    super(1);
                }

                @Override
                public Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                public final void invoke2(View view) {
                    Intrinsics.checkNotNullParameter(view, "it");
                    MyLog.i("[MDM]", "openApplicationDetailsSetting");
                    Intent data = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", AbsPermissionsActivity.this.getPackageName(), null));
                    Intrinsics.checkNotNullExpressionValue(data, "Intent(Settings.ACTION_A…, null)\n                )");
                    AbsPermissionsActivity.this.startSettingRequest.launch(data);
                }
            }, 1, null);
        }
        CustomDialogFragment customDialogFragmentBuild = builderNegative.build();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        customDialogFragmentBuild.show(supportFragmentManager);
    }

    public void setupPermission() {
        if (hasPermission()) {
            afterPermissionGranted(false);
        } else {
            beforePermissionGranted();
            this.permissionsRequest.launch(getPermissions());
        }
    }
}

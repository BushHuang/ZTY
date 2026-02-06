package com.xuehai.launcher.common.base;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.util.DensityUtil;
import com.xuehai.launcher.common.R;
import com.xuehai.launcher.common.ext.AppCompatActivityExtKt;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.util.Bugfix;
import com.xuehai.launcher.common.widget.CustomToast;
import com.xuehai.launcher.common.widget.loading.LoadingDialog;
import com.xuehai.launcher.common.widget.loading.LoadingView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b&\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\fJ\b\u0010\u000e\u001a\u00020\fH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\fH\u0014J\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\b\u0001\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0017H\u0014J\b\u0010\u0019\u001a\u00020\u0017H\u0014J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001bH\u0014J\b\u0010\u001f\u001a\u00020\u001bH\u0014J\b\u0010 \u001a\u00020\u001bH\u0014J\b\u0010!\u001a\u00020\u001bH\u0014J\b\u0010\"\u001a\u00020\u001bH\u0014J\b\u0010#\u001a\u00020\u001bH\u0014J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u0017H\u0016J\u0015\u0010&\u001a\u00020\u001b2\b\u0010'\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010(J\u0012\u0010&\u001a\u00020\u001b2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0014J\u000e\u0010*\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\fJ\u0010\u0010*\u001a\u00020\u001b2\b\u0010+\u001a\u0004\u0018\u00010\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006-"}, d2 = {"Lcom/xuehai/launcher/common/base/AbsActivity;", "Lcom/xuehai/launcher/common/base/AbsPermissionsActivity;", "()V", "loadingDialog", "Lcom/xuehai/launcher/common/widget/loading/LoadingDialog;", "getLoadingDialog", "()Lcom/xuehai/launcher/common/widget/loading/LoadingDialog;", "loadingDialog$delegate", "Lkotlin/Lazy;", "createLoadingView", "Lcom/xuehai/launcher/common/widget/loading/LoadingView;", "getDimen", "", "resId", "getNavBarColor", "getResources", "Landroid/content/res/Resources;", "getStatusBarColor", "getStringArray", "", "", "(I)[Ljava/lang/String;", "isFullScreen", "", "isLayoutFullScreen", "isLightStatusBar", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onRestart", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "progress", "isShow", "(Ljava/lang/Boolean;)V", "message", "showToast", "content", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbsActivity extends AbsPermissionsActivity {

    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "LifeCycle[MDM]";
    private static boolean showLifeCycle;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    private final Lazy loadingDialog = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<LoadingDialog>() {
        {
            super(0);
        }

        @Override
        public final LoadingDialog invoke() {
            AbsActivity absActivity = this.this$0;
            return new LoadingDialog(absActivity, absActivity.createLoadingView());
        }
    });

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/xuehai/launcher/common/base/AbsActivity$Companion;", "", "()V", "TAG", "", "showLifeCycle", "", "getShowLifeCycle", "()Z", "setShowLifeCycle", "(Z)V", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getShowLifeCycle() {
            return AbsActivity.showLifeCycle;
        }

        public final void setShowLifeCycle(boolean z) {
            AbsActivity.showLifeCycle = z;
        }
    }

    private final LoadingDialog getLoadingDialog() {
        return (LoadingDialog) this.loadingDialog.getValue();
    }

    public static void progress$default(AbsActivity absActivity, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: progress");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        absActivity.progress(str);
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

    public LoadingView createLoadingView() {
        LoadingView loadingViewCreateHorizontalLoading = LoadingView.INSTANCE.createHorizontalLoading(this);
        loadingViewCreateHorizontalLoading.setTextColor(ContextCompat.getColor(loadingViewCreateHorizontalLoading.getContext(), R.color.white));
        return loadingViewCreateHorizontalLoading;
    }

    public final int getDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    protected int getNavBarColor() {
        return -1;
    }

    @Override
    public Resources getResources() {
        return BaseApplication.INSTANCE.getInstance().getResources();
    }

    protected int getStatusBarColor() {
        return R.color.white;
    }

    public final String[] getStringArray(int resId) throws Resources.NotFoundException {
        String[] stringArray = getResources().getStringArray(resId);
        Intrinsics.checkNotNullExpressionValue(stringArray, "this.resources.getStringArray(resId)");
        return stringArray;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected boolean isLayoutFullScreen() {
        return false;
    }

    protected boolean isLightStatusBar() {
        int color = ContextCompat.getColor(this, getStatusBarColor());
        double d = 1;
        double dRed = Color.red(color);
        Double.isNaN(dRed);
        double dGreen = Color.green(color);
        Double.isNaN(dGreen);
        double d2 = (dRed * 0.2126d) + (dGreen * 0.7152d);
        double dBlue = Color.blue(color);
        Double.isNaN(dBlue);
        double d3 = d2 + (dBlue * 0.0722d);
        double d4 = 255;
        Double.isNaN(d4);
        Double.isNaN(d);
        return d - (d3 / d4) < 0.3d || ((double) Color.alpha(color)) < 76.5d;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(savedInstanceState);
        if (showLifeCycle) {
            MyLog.INSTANCE.v(TAG, this + " onCreate");
        }
        if (XHAppConfigProxy.getInstance().isDensityModeWidth()) {
            DensityUtil.setDefault(this);
        }
        setRequestedOrientation(1);
        AppCompatActivityExtKt.setImmersion(this, (2 & 1) != 0 ? false : isFullScreen(), (2 & 2) != 0 ? false : false, (2 & 4) != 0 ? false : isLightStatusBar(), getStatusBarColor(), (2 & 16) != 0 ? -1 : getNavBarColor());
    }

    @Override
    protected void onDestroy() {
        getLoadingDialog().dismiss();
        Bugfix.clearTextHoverCache();
        super.onDestroy();
        if (showLifeCycle) {
            MyLog.INSTANCE.v(TAG, this + " onDestroy");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (showLifeCycle) {
            MyLog.INSTANCE.v(TAG, this + " onPause");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (showLifeCycle) {
            MyLog.INSTANCE.v(TAG, this + " onRestart");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (showLifeCycle) {
            MyLog.INSTANCE.v(TAG, this + " onResume");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (showLifeCycle) {
            MyLog.INSTANCE.v(TAG, this + " onStart");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (showLifeCycle) {
            MyLog.INSTANCE.v(TAG, this + " onStop");
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        AppCompatActivityExtKt.setImmersionOnWindowFocusChanged(this, isFullScreen(), isLayoutFullScreen(), hasFocus);
    }

    public final void progress(Boolean isShow) {
        progress((isShow == null || !isShow.booleanValue()) ? null : "加载中");
    }

    public final void progress(String message) {
        if (message == null) {
            getLoadingDialog().dismiss();
        } else {
            getLoadingDialog().setText(message).show();
        }
    }

    public final void showToast(int resId) {
        showToast(getString(resId));
    }

    public final void showToast(String content) {
        if (content != null) {
            CustomToast.showToast(content);
        }
    }
}

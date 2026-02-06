package com.xh.xhcore.common.base;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.xh.xhcore.common.adaptation.TextViewAdaptationUtil;
import com.xh.xhcore.common.base.XhBasePresenter;
import com.xh.xhcore.common.config.BaseXHActivityConfig;
import com.xh.xhcore.common.config.IXHActivityConfigFactory;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.ClearTextHoverCache;
import com.xh.xhcore.common.util.DensityUtil;
import com.xh.xhcore.common.util.XHLog;
import java.util.List;

public abstract class XhBaseActivity<V, T extends XhBasePresenter<V>> extends AppCompatActivity implements IBaseView, IXHActivityConfigFactory {
    protected Bundle mBundle;
    public T presenter;

    private void initDensityAdaptation() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (XHAppConfigProxy.getInstance().isDensityModeEnable()) {
            DensityUtil.setDefault(this);
        }
        if (TextViewAdaptationUtil.isNoteCreatedNewVersion()) {
            TextViewAdaptationUtil.onActivityCreate(this);
        }
    }

    private void restartApp() {
        resetMemory();
        Intent launchIntent = getLaunchIntent();
        if (launchIntent == null) {
            LogUtils.w("[restartApp] launch intent is null");
        } else {
            launchIntent.setFlags(268468224);
            startActivity(launchIntent);
        }
    }

    public void Lg(String str) {
        XHLog.d(getClass().getSimpleName(), str);
    }

    @Override
    public BaseXHActivityConfig getActivityConfig() {
        return new BaseXHActivityConfig();
    }

    protected Intent getLaunchIntent() {
        return getPackageManager().getLaunchIntentForPackage(getPackageName());
    }

    protected String getLauncherActivityName() {
        new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
        Intent launchIntent = getLaunchIntent();
        if (launchIntent == null) {
            LogUtils.w("[getLauncherActivityName] launch intent is null");
            return "";
        }
        List<ResolveInfo> listQueryIntentActivities = getPackageManager().queryIntentActivities(launchIntent, 0);
        return (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) ? "" : listQueryIntentActivities.get(0).activityInfo.name;
    }

    public abstract int getLayoutResId();

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        DensityUtil.onConfigChangeImplDefault(resources.getDisplayMetrics());
        return resources;
    }

    @Override
    public void hideProgress() {
    }

    public abstract T initPresenter();

    public abstract void initView();

    protected boolean isLauncherActivity() {
        return getClass().getCanonicalName().equals(getLauncherActivityName());
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (XHAppConfigProxy.getInstance().isDensityModeEnable()) {
            DensityUtil.applyAdaptationDensity(this);
        }
        super.onConfigurationChanged(configuration);
    }

    @Override
    protected void onCreate(Bundle bundle) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(bundle);
        this.mBundle = bundle;
        XhBaseApplication xhBaseApplication = (XhBaseApplication) getApplication();
        initDensityAdaptation();
        setXHContentView();
        T t = (T) initPresenter();
        this.presenter = t;
        t.attach(this);
        if (XHAppConfigProxy.getInstance().getAutoBindUnbindButterKnife()) {
            ButterKnife.bind(this);
        }
        if (XHAppConfigProxy.getInstance().isObtainDeviceInfo()) {
            if (isLauncherActivity()) {
                xhBaseApplication.initMemoryKilled();
            } else if (xhBaseApplication.isAppKilled()) {
                restartApp();
            }
        }
        initView();
    }

    @Override
    protected void onDestroy() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        T t = this.presenter;
        if (t != null) {
            t.detach();
        }
        if (XHAppConfigProxy.getInstance().getAutoBindUnbindButterKnife()) {
            ButterKnife.unbind(this);
        }
        ClearTextHoverCache.clearTextHoverCache();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void requestFailed(String... strArr) {
    }

    public void resetMemory() {
    }

    protected void setXHContentView() {
        setContentView(getLayoutResId());
    }

    @Override
    public void showProgress(String... strArr) {
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, 0).show();
    }
}

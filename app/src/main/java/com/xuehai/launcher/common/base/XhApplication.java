package com.xuehai.launcher.common.base;

import android.content.res.Configuration;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.BaseXHAppConfig;
import com.xh.xhcore.common.util.DensityUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/xuehai/launcher/common/base/XhApplication;", "Lcom/xh/xhcore/common/base/XhBaseApplication;", "()V", "getAppConfig", "Lcom/xh/xhcore/common/config/BaseXHAppConfig;", "getC", "", "getM", "getS", "initRootUrlMap", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class XhApplication extends XhBaseApplication {
    @Override
    protected BaseXHAppConfig getAppConfig() {
        return new XHAppConfig();
    }

    @Override
    public String getC() {
        return null;
    }

    @Override
    public String getM() {
        return null;
    }

    @Override
    public String getS() {
        return null;
    }

    @Override
    public void initRootUrlMap() {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        DensityUtil.onApplicationConfigChange(this);
        super.onConfigurationChanged(newConfig);
    }
}

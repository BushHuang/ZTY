package com.xuehai.launcher.common.base;

import com.xh.xhcore.common.config.BaseXHAppConfig;
import com.xh.xhcore.common.config.DensityAdaptationMode;
import com.xh.xhcore.common.config.DensityConfigBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/base/XHAppConfig;", "Lcom/xh/xhcore/common/config/BaseXHAppConfig;", "()V", "enableExtendDNS", "", "getAppId", "", "getAutoBindUnbindButterKnife", "getDensityConfigBuilder", "Lcom/xh/xhcore/common/config/DensityConfigBuilder;", "isAppBuildTypeDebug", "isCheckPlatformAutoKeepRunning", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHAppConfig extends BaseXHAppConfig {
    @Override
    public boolean enableExtendDNS() {
        return true;
    }

    @Override
    public String getAppId() {
        return "CA106002";
    }

    @Override
    public boolean getAutoBindUnbindButterKnife() {
        return false;
    }

    @Override
    public DensityConfigBuilder getDensityConfigBuilder() {
        DensityConfigBuilder customDensity = new DensityConfigBuilder().setDensityAdaptationMode(DensityAdaptationMode.ADAPTATION_MODE_WIDTH).setIsForbidFontSizeChangeBySystem(true).setCustomDensity(1.73f);
        Intrinsics.checkNotNullExpressionValue(customDensity, "DensityConfigBuilder()\n … .setCustomDensity(1.73f)");
        return customDensity;
    }

    @Override
    public boolean isAppBuildTypeDebug() {
        return false;
    }

    @Override
    public boolean isCheckPlatformAutoKeepRunning() {
        return false;
    }
}

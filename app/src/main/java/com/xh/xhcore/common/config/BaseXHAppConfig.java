package com.xh.xhcore.common.config;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.IXHAppConfig;
import com.xh.xhcore.common.http.strategy.HttpConst;
import com.xh.xhcore.common.matrix.APMConfig;
import com.xh.xhcore.common.statistic.strategy.IBuryConfig;
import com.xh.xhcore.common.statistic.strategy.XHBuryConfig;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseXHAppConfig implements IXHAppConfig {
    @Override
    public boolean enableDomainWhiteList() {
        return true;
    }

    @Override
    public boolean enableExtendDNS() {
        return false;
    }

    @Override
    public boolean enableLogoutWhenSignError() {
        return false;
    }

    @Override
    public boolean enableOOMTrace() {
        return false;
    }

    @Override
    public boolean enableResponseStringJsonCheck() {
        return true;
    }

    @Override
    public APMConfig getAPMConfig() {
        return new APMConfig();
    }

    @Override
    public int getAppRoleType() {
        return 3;
    }

    @Override
    public boolean getAutoBindUnbindButterKnife() {
        return true;
    }

    @Override
    public String getBuildTimeMillisFormatted() {
        return "defaultBuildTimeMillisFormatted";
    }

    @Override
    public IBuryConfig getBuryConfig() {
        return new XHBuryConfig();
    }

    @Override
    public DensityConfigBuilder getDensityConfigBuilder() {
        return new DensityConfigBuilder();
    }

    @Override
    public List<String> getDomainWhiteList() {
        return null;
    }

    @Override
    public Set<Class<?>> getIgnoreCheckAppLock() {
        return null;
    }

    @Override
    public Map<String, String> getMicroServerDefaultMap() {
        return XhBaseApplication.getXhBaseApplication().getUrlRootMap();
    }

    @Override
    public IXHAppConfig.MicroServerVersion getMicroServerVersion() {
        return IXHAppConfig.MicroServerVersion.VERSION2;
    }

    @Override
    public HttpConst.RequestStrategy getRequestStrategy() {
        return HttpConst.RequestStrategy.STRATEGY_OK_HTTP;
    }

    @Override
    public String getSignSource() {
        return "CA106004";
    }

    @Override
    public TinkerConfig getTinkerConfig() {
        return new TinkerConfig();
    }

    @Override
    public WholeLinkEnvironment getWholeLinkEnvironment() {
        return WholeLinkEnvironment.RELEASE;
    }

    @Override
    public boolean isCheckPlatformAutoKeepRunning() {
        return true;
    }

    @Override
    public boolean isInitXCrash() {
        return true;
    }

    @Override
    public boolean isInside() {
        return false;
    }

    @Override
    public boolean isObtainDeviceInfo() {
        return true;
    }

    @Override
    public boolean saveMicroServerByMMKV() {
        return true;
    }

    @Override
    public boolean useV2Sign() {
        return false;
    }
}

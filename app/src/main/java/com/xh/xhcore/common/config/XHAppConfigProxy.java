package com.xh.xhcore.common.config;

import com.xh.xhcore.common.config.IXHAppConfig;
import com.xh.xhcore.common.http.strategy.HttpConst;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.matrix.APMConfig;
import com.xh.xhcore.common.statistic.strategy.IBuryConfig;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XHAppConfigProxy implements IXHAppConfig {
    public static final String SERVER_TYPE_FILE_BOX = "SB103013";
    private static XHAppConfigProxy instance;
    private IXHAppConfig.MicroServerVersion microServerVersion;
    private BaseXHAppConfig realXHAppConfig;

    private XHAppConfigProxy() {
    }

    public static XHAppConfigProxy getInstance() {
        if (instance == null) {
            synchronized (XHAppConfigProxy.class) {
                if (instance == null) {
                    instance = new XHAppConfigProxy();
                }
            }
        }
        return instance;
    }

    private IXHAppConfig.MicroServerVersion getMicroServerVersionInner() {
        IXHAppConfig.MicroServerVersion microServerVersion = this.microServerVersion;
        return microServerVersion != null ? microServerVersion : this.realXHAppConfig.getMicroServerVersion();
    }

    @Override
    public boolean enableDomainWhiteList() {
        return this.realXHAppConfig.enableDomainWhiteList();
    }

    @Override
    public boolean enableExtendDNS() {
        return this.realXHAppConfig.enableExtendDNS();
    }

    @Override
    public boolean enableLogoutWhenSignError() {
        return this.realXHAppConfig.enableLogoutWhenSignError();
    }

    @Override
    public boolean enableOOMTrace() {
        return this.realXHAppConfig.enableOOMTrace();
    }

    @Override
    public boolean enableResponseStringJsonCheck() {
        return this.realXHAppConfig.enableResponseStringJsonCheck();
    }

    @Override
    public APMConfig getAPMConfig() {
        return this.realXHAppConfig.getAPMConfig();
    }

    @Override
    public String getAppId() {
        return this.realXHAppConfig.getAppId();
    }

    @Override
    public int getAppRoleType() {
        return this.realXHAppConfig.getAppRoleType();
    }

    @Override
    public boolean getAutoBindUnbindButterKnife() {
        return this.realXHAppConfig.getAutoBindUnbindButterKnife();
    }

    @Override
    public String getBuildTimeMillisFormatted() {
        return this.realXHAppConfig.getBuildTimeMillisFormatted();
    }

    @Override
    public IBuryConfig getBuryConfig() {
        return this.realXHAppConfig.getBuryConfig();
    }

    @Override
    public DensityConfigBuilder getDensityConfigBuilder() {
        return this.realXHAppConfig.getDensityConfigBuilder();
    }

    @Override
    public List<String> getDomainWhiteList() {
        return this.realXHAppConfig.getDomainWhiteList();
    }

    @Override
    public Set<Class<?>> getIgnoreCheckAppLock() {
        return this.realXHAppConfig.getIgnoreCheckAppLock();
    }

    @Override
    public Map<String, String> getMicroServerDefaultMap() {
        return this.realXHAppConfig.getMicroServerDefaultMap();
    }

    public String getMicroServerTypes() {
        Map<String, String> microServerDefaultMap = getMicroServerDefaultMap();
        StringBuilder sb = new StringBuilder();
        if (microServerDefaultMap == null || microServerDefaultMap.size() == 0) {
            LogUtils.e("serverTypesMap is empty");
            return "";
        }
        if (!microServerDefaultMap.containsKey("SB103013")) {
            microServerDefaultMap.put("SB103013", "");
        }
        Iterator<Map.Entry<String, String>> it = microServerDefaultMap.entrySet().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getKey());
            sb.append(",");
        }
        String strSubstring = sb.substring(0, sb.length() - 1);
        LogUtils.d("serverTypesString = " + strSubstring);
        return strSubstring;
    }

    @Override
    public IXHAppConfig.MicroServerVersion getMicroServerVersion() {
        IXHAppConfig.MicroServerVersion microServerVersionInner = getMicroServerVersionInner();
        return microServerVersionInner == IXHAppConfig.MicroServerVersion.VERSION1 ? IXHAppConfig.MicroServerVersion.VERSION2 : microServerVersionInner;
    }

    @Override
    public HttpConst.RequestStrategy getRequestStrategy() {
        return this.realXHAppConfig.getRequestStrategy();
    }

    @Override
    public String getSignSource() {
        return this.realXHAppConfig.getSignSource();
    }

    @Override
    public TinkerConfig getTinkerConfig() {
        return this.realXHAppConfig.getTinkerConfig();
    }

    @Override
    public WholeLinkEnvironment getWholeLinkEnvironment() {
        return this.realXHAppConfig.getWholeLinkEnvironment();
    }

    @Override
    public boolean isAppBuildTypeDebug() {
        return this.realXHAppConfig.isAppBuildTypeDebug();
    }

    @Override
    public boolean isCheckPlatformAutoKeepRunning() {
        return this.realXHAppConfig.isCheckPlatformAutoKeepRunning();
    }

    public boolean isDensityModeEnable() {
        return isDensityModeWidth();
    }

    public boolean isDensityModeWidth() {
        return getDensityConfigBuilder().getDensityAdaptationMode() == DensityAdaptationMode.ADAPTATION_MODE_WIDTH;
    }

    @Override
    public boolean isInitXCrash() {
        return this.realXHAppConfig.isInitXCrash();
    }

    @Override
    public boolean isInside() {
        return this.realXHAppConfig.isInside();
    }

    public boolean isMicroServerV3() {
        return getMicroServerVersion() == IXHAppConfig.MicroServerVersion.VERSION3;
    }

    @Override
    public boolean isObtainDeviceInfo() {
        return this.realXHAppConfig.isObtainDeviceInfo();
    }

    @Override
    public boolean saveMicroServerByMMKV() {
        return this.realXHAppConfig.saveMicroServerByMMKV();
    }

    public void setConfig(BaseXHAppConfig baseXHAppConfig) {
        this.realXHAppConfig = baseXHAppConfig;
    }

    public void setMicroServerVersion(IXHAppConfig.MicroServerVersion microServerVersion) {
        this.microServerVersion = microServerVersion;
    }

    @Override
    public boolean useV2Sign() {
        return this.realXHAppConfig.useV2Sign();
    }
}

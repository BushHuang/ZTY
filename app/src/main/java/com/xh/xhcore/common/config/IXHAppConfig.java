package com.xh.xhcore.common.config;

import com.xh.xhcore.common.http.strategy.HttpConst;
import com.xh.xhcore.common.matrix.APMConfig;
import com.xh.xhcore.common.statistic.strategy.IBuryConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IXHAppConfig {

    @Retention(RetentionPolicy.SOURCE)
    public @interface AppRoleType {
        public static final int ROLE_TYPE_GENNERAL = 3;
        public static final int ROLE_TYPE_STUDENT = 1;
        public static final int ROLE_TYPE_TEACHER = 2;
    }

    public enum MicroServerVersion {
        VERSION1,
        VERSION2,
        VERSION3
    }

    boolean enableDomainWhiteList();

    boolean enableExtendDNS();

    boolean enableLogoutWhenSignError();

    boolean enableOOMTrace();

    boolean enableResponseStringJsonCheck();

    APMConfig getAPMConfig();

    String getAppId();

    int getAppRoleType();

    boolean getAutoBindUnbindButterKnife();

    String getBuildTimeMillisFormatted();

    IBuryConfig getBuryConfig();

    DensityConfigBuilder getDensityConfigBuilder();

    List<String> getDomainWhiteList();

    Set<Class<?>> getIgnoreCheckAppLock();

    Map<String, String> getMicroServerDefaultMap();

    MicroServerVersion getMicroServerVersion();

    HttpConst.RequestStrategy getRequestStrategy();

    String getSignSource();

    TinkerConfig getTinkerConfig();

    WholeLinkEnvironment getWholeLinkEnvironment();

    boolean isAppBuildTypeDebug();

    boolean isCheckPlatformAutoKeepRunning();

    boolean isInitXCrash();

    boolean isInside();

    boolean isObtainDeviceInfo();

    boolean saveMicroServerByMMKV();

    boolean useV2Sign();
}

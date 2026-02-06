package com.xh.xhcore.common.statistic.strategy;

import android.content.Context;
import com.analysys.AnalysysAgent;
import com.analysys.AnalysysConfig;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.strategy.IBuryStrategy;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0015\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryStrategy;", "Lcom/xh/xhcore/common/statistic/strategy/IBuryStrategy;", "Lcom/xh/xhcore/common/statistic/strategy/AnalysysBuryConfig;", "()V", "APP_KEY_DEBUG", "", "APP_KEY_RELEASE", "CONFIG_URL", "SOCKET_URL", "UPLOAD_URL", "bury", "", "buryEvent", "Lcom/xh/xhcore/common/statistic/BuryEvent;", "eventName", "properties", "", "", "fixEventName", "fixEventName$xhcore_release", "initCommon", "context", "Landroid/content/Context;", "initInner", "buryConfig", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AnalysysBuryStrategy implements IBuryStrategy<AnalysysBuryConfig> {
    private final String APP_KEY_DEBUG = "45275ad56cc0cdbb";
    private final String APP_KEY_RELEASE = "3cf299fb3072e173";
    private final String UPLOAD_URL = "http://47.111.168.116:8089";
    private final String SOCKET_URL = "ws://47.111.168.116:9091";
    private final String CONFIG_URL = "http://47.111.168.116:8089";

    private final void initCommon(Context context) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        CPVDUser user = CPVDUserData.getUser(XhBaseApplication.mContext);
        if (user != null) {
            linkedHashMap.put("uid", Long.valueOf(user.getUserId()));
            String deviceNum = user.getDeviceNum();
            Intrinsics.checkNotNullExpressionValue(deviceNum, "user.deviceNum");
            linkedHashMap.put("uuid", deviceNum);
            linkedHashMap.put("schoolId", Long.valueOf(user.getSchoolId()));
        }
        String packageName = XHAppUtil.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName()");
        linkedHashMap.put("appPkg", packageName);
        String versionName = XHAppUtil.getVersionName();
        Intrinsics.checkNotNullExpressionValue(versionName, "getVersionName()");
        linkedHashMap.put("appVer", versionName);
        AnalysysAgent.registerSuperProperties(context, linkedHashMap);
    }

    @Override
    public void bury(BuryEvent buryEvent) {
        if (buryEvent != null) {
            HashMap map = new HashMap();
            String data = buryEvent.getData();
            if (data == null) {
                data = "";
            }
            map.put("data", data);
            String name = buryEvent.getName();
            Intrinsics.checkNotNullExpressionValue(name, "buryEvent.name");
            bury(name, map);
        }
    }

    @Override
    public void bury(String eventName, Map<String, Object> properties) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(properties, "properties");
        AnalysysAgent.track(XhBaseApplication.mContext, fixEventName$xhcore_release(eventName), properties);
    }

    public final String fixEventName$xhcore_release(String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        if (!(eventName.length() > 0)) {
            return eventName;
        }
        char cCharAt = eventName.charAt(0);
        return '0' <= cCharAt && cCharAt < ':' ? Intrinsics.stringPlus("EN_", eventName) : eventName;
    }

    @Override
    public void init(Context context, IBuryConfig iBuryConfig) {
        IBuryStrategy.DefaultImpls.init(this, context, iBuryConfig);
    }

    @Override
    public void initInner(Context context, AnalysysBuryConfig buryConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(buryConfig, "buryConfig");
        AnalysysConfig config = buryConfig.getConfig();
        if (XHAppConfigProxy.getInstance().isAppBuildTypeDebug()) {
            config.setAppKey(this.APP_KEY_DEBUG);
        } else {
            config.setAppKey(this.APP_KEY_RELEASE);
        }
        AnalysysAgent.init(context, config);
        AnalysysAgent.setUploadNetworkType(255);
        AnalysysAgent.setUploadURL(context, this.UPLOAD_URL);
        if (buryConfig.getMShakeConnectWebSocket()) {
            AnalysysAgent.setVisitorDebugURL(context, this.SOCKET_URL);
        }
        AnalysysAgent.setVisitorConfigURL(context, this.CONFIG_URL);
        initCommon(context);
    }
}

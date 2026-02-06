package com.xuehai.system.mdm.proxy;

import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.ILocationPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/mdm/proxy/LocationPolicyProxy;", "Lcom/xuehai/system/common/policies/ILocationPolicy;", "policy", "(Lcom/xuehai/system/common/policies/ILocationPolicy;)V", "allowLocationService", "", "enable", "isLocationServiceEnable", "startGPS", "start", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocationPolicyProxy implements ILocationPolicy {
    public static final String TAG = "MDM:LocationPolicy";
    private final ILocationPolicy policy;

    public LocationPolicyProxy(ILocationPolicy iLocationPolicy) {
        Intrinsics.checkNotNullParameter(iLocationPolicy, "policy");
        this.policy = iLocationPolicy;
    }

    @Override
    public boolean allowLocationService(boolean enable) {
        String str = enable ? "启用定位服务" : "禁用定位服务";
        try {
            boolean zAllowLocationService = this.policy.allowLocationService(enable);
            MdmLog.log("MDM:LocationPolicy", str, Boolean.valueOf(zAllowLocationService));
            return zAllowLocationService;
        } catch (Throwable th) {
            MdmLog.e("MDM:LocationPolicy", str + " 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean isLocationServiceEnable() {
        try {
            boolean zIsLocationServiceEnable = this.policy.isLocationServiceEnable();
            MdmLog.log("MDM:LocationPolicy", "获取定位服务是否启用", Boolean.valueOf(zIsLocationServiceEnable));
            return zIsLocationServiceEnable;
        } catch (Throwable th) {
            MdmLog.e("MDM:LocationPolicy", "获取定位服务是否启用 发生异常!", th);
            return false;
        }
    }

    @Override
    public boolean startGPS(boolean start) {
        String str = start ? "启动GPS定位" : "关闭GPS定位";
        try {
            boolean zStartGPS = this.policy.startGPS(start);
            MdmLog.log("MDM:LocationPolicy", str, Boolean.valueOf(zStartGPS));
            return zStartGPS;
        } catch (Throwable th) {
            MdmLog.e("MDM:LocationPolicy", str + " 发生异常!", th);
            return false;
        }
    }
}

package com.xuehai.system.mdm.proxy;

import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.policies.IFirewallPolicy;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0017\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J2\u0010\t\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\r\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\u000e\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\u000f\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0016\u0010\u0011\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016J\b\u0010\u0014\u001a\u00020\u0005H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/xuehai/system/mdm/proxy/FirewallPolicyProxy;", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "policy", "(Lcom/xuehai/system/common/policies/IFirewallPolicy;)V", "addAllowApps", "", "packageNames", "", "", "addAppDomainsRules", "allowDomains", "denyDomains", "addDenyApps", "addDomainsDenyRules", "addDomainsRules", "addIPDenyRules", "denyIPs", "addIPRules", "allowIPs", "cleanAppRules", "cleanDomainRules", "cleanIPRules", "getAllowApps", "getDenyApps", "getDomainsAllowRules", "getDomainsDenyRules", "getIPAllowRules", "getIPDenyRules", "setIptablesOption", "status", "Companion", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FirewallPolicyProxy implements IFirewallPolicy {
    public static final String TAG = "MDM:FirewallPolicy";
    private final IFirewallPolicy policy;

    public FirewallPolicyProxy(IFirewallPolicy iFirewallPolicy) {
        Intrinsics.checkNotNullParameter(iFirewallPolicy, "policy");
        this.policy = iFirewallPolicy;
    }

    @Override
    public boolean addAllowApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zAddAllowApps = this.policy.addAllowApps(packageNames);
            MdmLog.log("MDM:FirewallPolicy", "批量添加应用防火墙白名单(" + packageNames.size() + ')', Boolean.valueOf(zAddAllowApps));
            return zAddAllowApps;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "批量添加应用防火墙白名单(" + packageNames.size() + ") 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        try {
            boolean zAddAppDomainsRules = this.policy.addAppDomainsRules(packageNames, allowDomains, denyDomains);
            MdmLog.log("MDM:FirewallPolicy", "批量添加应用域名防火墙规则", Boolean.valueOf(zAddAppDomainsRules));
            return zAddAppDomainsRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "批量添加应用域名防火墙规则 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addDenyApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        try {
            boolean zAddDenyApps = this.policy.addDenyApps(packageNames);
            MdmLog.log("MDM:FirewallPolicy", "批量添加应用防火墙黑名单(" + packageNames.size() + ')', Boolean.valueOf(zAddDenyApps));
            return zAddDenyApps;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "批量添加应用防火墙黑名单(" + packageNames.size() + ") 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addDomainsDenyRules(List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        try {
            boolean zAddDomainsDenyRules = this.policy.addDomainsDenyRules(denyDomains);
            MdmLog.log("MDM:FirewallPolicy", "设置域名防火墙黑名单(" + denyDomains.size() + ')', Boolean.valueOf(zAddDomainsDenyRules));
            return zAddDomainsDenyRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "设置域名防火墙黑名单(" + denyDomains.size() + ") 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addDomainsRules(List<String> allowDomains) {
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        try {
            boolean zAddDomainsRules = this.policy.addDomainsRules(allowDomains);
            MdmLog.log("MDM:FirewallPolicy", "设置域名防火墙白名单", Boolean.valueOf(zAddDomainsRules));
            return zAddDomainsRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "设置域名防火墙白名单 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addIPDenyRules(List<String> denyIPs) {
        Intrinsics.checkNotNullParameter(denyIPs, "denyIPs");
        try {
            boolean zAddIPDenyRules = this.policy.addIPDenyRules(denyIPs);
            MdmLog.log("MDM:FirewallPolicy", "设置IP防火墙黑名单", Boolean.valueOf(zAddIPDenyRules));
            return zAddIPDenyRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "设置IP防火墙黑名单 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean addIPRules(List<String> allowIPs) {
        Intrinsics.checkNotNullParameter(allowIPs, "allowIPs");
        try {
            boolean zAddIPRules = this.policy.addIPRules(allowIPs);
            MdmLog.log("MDM:FirewallPolicy", "设置IP防火墙白名单", Boolean.valueOf(zAddIPRules));
            return zAddIPRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "设置IP防火墙白名单 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean cleanAppRules() {
        try {
            boolean zCleanAppRules = this.policy.cleanAppRules();
            MdmLog.log("MDM:FirewallPolicy", "清除所有防火墙规则", Boolean.valueOf(zCleanAppRules));
            return zCleanAppRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "清除所有防火墙规则 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean cleanDomainRules() {
        try {
            boolean zCleanDomainRules = this.policy.cleanDomainRules();
            MdmLog.log("MDM:FirewallPolicy", "清除域名防火墙规则", Boolean.valueOf(zCleanDomainRules));
            return zCleanDomainRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "清除域名防火墙规则 发生异常！", th);
            return false;
        }
    }

    @Override
    public boolean cleanIPRules() {
        try {
            boolean zCleanIPRules = this.policy.cleanIPRules();
            MdmLog.log("MDM:FirewallPolicy", "清除IP防火墙规则", Boolean.valueOf(zCleanIPRules));
            return zCleanIPRules;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "清除IP防火墙规则 发生异常！", th);
            return false;
        }
    }

    @Override
    public List<String> getAllowApps() {
        try {
            List<String> allowApps = this.policy.getAllowApps();
            MdmLog.log$default("MDM:FirewallPolicy", "获取应用防火墙白名单: " + allowApps, null, 4, null);
            return allowApps;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "获取应用防火墙白名单 发生异常！", th);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public List<String> getDenyApps() {
        try {
            List<String> denyApps = this.policy.getDenyApps();
            MdmLog.log$default("MDM:FirewallPolicy", "获取应用防火墙黑名单: " + denyApps, null, 4, null);
            return denyApps;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", "获取应用防火墙黑名单 发生异常！", th);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public List<String> getDomainsAllowRules() {
        try {
            List<String> domainsAllowRules = this.policy.getDomainsAllowRules();
            MdmLog.log$default("MDM:FirewallPolicy", "获取域名防火墙白名单：" + domainsAllowRules, null, 4, null);
            return domainsAllowRules;
        } catch (Exception e) {
            MdmLog.e("MDM:FirewallPolicy", "获取域名防火墙白名单 发生异常！", e);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public List<String> getDomainsDenyRules() {
        try {
            List<String> domainsDenyRules = this.policy.getDomainsDenyRules();
            MdmLog.log$default("MDM:FirewallPolicy", "获取域名防火墙黑名单：" + domainsDenyRules, null, 4, null);
            return domainsDenyRules;
        } catch (Exception e) {
            MdmLog.e("MDM:FirewallPolicy", "获取域名防火墙黑名单 发生异常！", e);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public List<String> getIPAllowRules() {
        try {
            List<String> iPAllowRules = this.policy.getIPAllowRules();
            MdmLog.log$default("MDM:FirewallPolicy", "获取IP防火墙白名单：" + iPAllowRules, null, 4, null);
            return iPAllowRules;
        } catch (Exception e) {
            MdmLog.e("MDM:FirewallPolicy", "获取IP防火墙白名单 发生异常！", e);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public List<String> getIPDenyRules() {
        try {
            List<String> iPDenyRules = this.policy.getIPDenyRules();
            MdmLog.log$default("MDM:FirewallPolicy", "获取IP防火墙黑名单：" + iPDenyRules, null, 4, null);
            return iPDenyRules;
        } catch (Exception e) {
            MdmLog.e("MDM:FirewallPolicy", "获取IP防火墙黑名单 发生异常！", e);
            return CollectionsKt.emptyList();
        }
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        String str = status ? "开启防火墙" : "关闭防火墙";
        try {
            boolean iptablesOption = this.policy.setIptablesOption(status);
            MdmLog.log("MDM:FirewallPolicy", str, Boolean.valueOf(iptablesOption));
            return iptablesOption;
        } catch (Throwable th) {
            MdmLog.e("MDM:FirewallPolicy", str + " 发生异常！", th);
            return false;
        }
    }
}

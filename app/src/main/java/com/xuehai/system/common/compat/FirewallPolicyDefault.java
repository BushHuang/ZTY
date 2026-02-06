package com.xuehai.system.common.compat;

import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.policies.IFirewallPolicy;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J2\u0010\b\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0016\u0010\u000b\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0016\u0010\f\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0016\u0010\r\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0016\u0010\u000e\u001a\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0016\u0010\u0010\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0016¨\u0006\u001d"}, d2 = {"Lcom/xuehai/system/common/compat/FirewallPolicyDefault;", "Lcom/xuehai/system/common/policies/IFirewallPolicy;", "()V", "addAllowApps", "", "packageNames", "", "", "addAppDomainsRules", "allowDomains", "denyDomains", "addDenyApps", "addDomainsDenyRules", "addDomainsRules", "addIPDenyRules", "denyIPs", "addIPRules", "allowIPs", "cleanAppRules", "cleanDomainRules", "cleanIPRules", "getAllowApps", "getDenyApps", "getDomainsAllowRules", "getDomainsDenyRules", "getIPAllowRules", "getIPDenyRules", "setIptablesOption", "status", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class FirewallPolicyDefault implements IFirewallPolicy {
    @Override
    public boolean addAllowApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        throw new UnSupportException();
    }

    @Override
    public boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        throw new UnSupportException();
    }

    @Override
    public boolean addDenyApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        throw new UnSupportException();
    }

    @Override
    public boolean addDomainsDenyRules(List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        throw new UnSupportException();
    }

    @Override
    public boolean addDomainsRules(List<String> allowDomains) {
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        throw new UnSupportException();
    }

    @Override
    public boolean addIPDenyRules(List<String> denyIPs) {
        Intrinsics.checkNotNullParameter(denyIPs, "denyIPs");
        throw new UnSupportException();
    }

    @Override
    public boolean addIPRules(List<String> allowIPs) {
        Intrinsics.checkNotNullParameter(allowIPs, "allowIPs");
        throw new UnSupportException();
    }

    @Override
    public boolean cleanAppRules() {
        throw new UnSupportException();
    }

    @Override
    public boolean cleanDomainRules() {
        throw new UnSupportException();
    }

    @Override
    public boolean cleanIPRules() {
        throw new UnSupportException();
    }

    @Override
    public List<String> getAllowApps() {
        return CollectionsKt.emptyList();
    }

    @Override
    public List<String> getDenyApps() {
        return CollectionsKt.emptyList();
    }

    @Override
    public List<String> getDomainsAllowRules() {
        throw new UnSupportException();
    }

    @Override
    public List<String> getDomainsDenyRules() {
        throw new UnSupportException();
    }

    @Override
    public List<String> getIPAllowRules() {
        throw new UnSupportException();
    }

    @Override
    public List<String> getIPDenyRules() {
        throw new UnSupportException();
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        return true;
    }
}

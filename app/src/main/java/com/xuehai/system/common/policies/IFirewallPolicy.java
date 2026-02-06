package com.xuehai.system.common.policies;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0016\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J2\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u0011\u001a\u00020\u0003H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003H&¨\u0006\u001c"}, d2 = {"Lcom/xuehai/system/common/policies/IFirewallPolicy;", "", "addAllowApps", "", "packageNames", "", "", "addAppDomainsRules", "allowDomains", "denyDomains", "addDenyApps", "addDomainsDenyRules", "addDomainsRules", "addIPDenyRules", "denyIPs", "addIPRules", "allowIPs", "cleanAppRules", "cleanDomainRules", "cleanIPRules", "getAllowApps", "getDenyApps", "getDomainsAllowRules", "getDomainsDenyRules", "getIPAllowRules", "getIPDenyRules", "setIptablesOption", "status", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IFirewallPolicy {
    boolean addAllowApps(List<String> packageNames);

    boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains);

    boolean addDenyApps(List<String> packageNames);

    boolean addDomainsDenyRules(List<String> denyDomains);

    boolean addDomainsRules(List<String> allowDomains);

    boolean addIPDenyRules(List<String> denyIPs);

    boolean addIPRules(List<String> allowIPs);

    boolean cleanAppRules();

    boolean cleanDomainRules();

    boolean cleanIPRules();

    List<String> getAllowApps();

    List<String> getDenyApps();

    List<String> getDomainsAllowRules();

    List<String> getDomainsDenyRules();

    List<String> getIPAllowRules();

    List<String> getIPDenyRules();

    boolean setIptablesOption(boolean status);
}

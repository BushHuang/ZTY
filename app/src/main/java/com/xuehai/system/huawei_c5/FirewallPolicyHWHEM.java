package com.xuehai.system.huawei_c5;

import android.content.ComponentName;
import android.text.TextUtils;
import com.huawei.android.app.admin.DeviceNetworkManager;
import com.xuehai.system.common.compat.FirewallPolicyDefault;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u001a\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J2\u0010\n\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\r\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u0011\u001a\u00020\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J&\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\b\u0010\u0017\u001a\u00020\u0006H\u0016J\b\u0010\u0018\u001a\u00020\u0006H\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0002J\u0010\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/xuehai/system/huawei_c5/FirewallPolicyHWHEM;", "Lcom/xuehai/system/common/compat/FirewallPolicyDefault;", "componentName", "Landroid/content/ComponentName;", "(Landroid/content/ComponentName;)V", "addAllowApps", "", "packageNames", "", "", "addAppDomainsRules", "allowDomains", "denyDomains", "addDomainsDenyRules", "addDomainsRules", "addIPDenyRules", "denyIPs", "addIPRules", "allowIPs", "addNetworkList", "isTrustList", "isDomainList", "addrList", "cleanAppRules", "cleanDomainRules", "cleanIPRules", "getAllowApps", "getDomainsAllowRules", "getDomainsDenyRules", "getIPAllowRules", "getIPDenyRules", "getNetworkList", "setIptablesOption", "status", "Companion", "mdm_huawei_c5_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FirewallPolicyHWHEM extends FirewallPolicyDefault {
    private static final String HUAWEI_HWOUC = "com.huawei.android.hwouc";
    private final ComponentName componentName;

    public FirewallPolicyHWHEM(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        this.componentName = componentName;
    }

    private final boolean addNetworkList(boolean isTrustList, boolean isDomainList, List<String> addrList) {
        ArrayList arrayList = new ArrayList();
        for (String strRemovePrefix : addrList) {
            if (StringsKt.startsWith$default(strRemovePrefix, "*.", false, 2, (Object) null)) {
                strRemovePrefix = StringsKt.removePrefix(strRemovePrefix, (CharSequence) "*.");
            }
            arrayList.add(strRemovePrefix);
        }
        DeviceNetworkManager deviceNetworkManager = new DeviceNetworkManager();
        deviceNetworkManager.removeNetworkList(this.componentName, !isTrustList, isDomainList, arrayList);
        return deviceNetworkManager.addNetworkList(this.componentName, isTrustList, isDomainList, arrayList);
    }

    private final List<String> getNetworkList(boolean isTrustList, boolean isDomainList) {
        List<String> networkList = new DeviceNetworkManager().getNetworkList(this.componentName, isTrustList, isDomainList);
        Intrinsics.checkNotNullExpressionValue(networkList, "DeviceNetworkManager().g…sTrustList, isDomainList)");
        return networkList;
    }

    @Override
    public boolean addAllowApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(packageNames);
        if (!arrayList.contains("com.huawei.android.hwouc")) {
            arrayList.add("com.huawei.android.hwouc");
        }
        return new DeviceNetworkManager().addNetworkAccessAppList(this.componentName, new ArrayList(arrayList), true);
    }

    @Override
    public boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        boolean zAddAllowApps = addAllowApps(packageNames);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : allowDomains) {
            if (!TextUtils.equals("*", str)) {
                linkedHashSet.add(str);
            }
        }
        if (!linkedHashSet.isEmpty()) {
            return addDomainsRules(CollectionsKt.toList(linkedHashSet)) && zAddAllowApps;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (String str2 : denyDomains) {
            if (!TextUtils.equals("*", str2)) {
                linkedHashSet2.add(str2);
            }
        }
        if (!(!linkedHashSet2.isEmpty())) {
            return zAddAllowApps;
        }
        if (!addDomainsDenyRules(CollectionsKt.toList(linkedHashSet2)) || !zAddAllowApps) {
        }
    }

    @Override
    public boolean addDomainsDenyRules(List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        return addNetworkList(false, true, denyDomains);
    }

    @Override
    public boolean addDomainsRules(List<String> allowDomains) {
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        return addNetworkList(true, true, allowDomains);
    }

    @Override
    public boolean addIPDenyRules(List<String> denyIPs) {
        Intrinsics.checkNotNullParameter(denyIPs, "denyIPs");
        return addNetworkList(false, false, new ArrayList(denyIPs));
    }

    @Override
    public boolean addIPRules(List<String> allowIPs) {
        Intrinsics.checkNotNullParameter(allowIPs, "allowIPs");
        return addNetworkList(true, false, new ArrayList(allowIPs));
    }

    @Override
    public boolean cleanAppRules() {
        DeviceNetworkManager deviceNetworkManager = new DeviceNetworkManager();
        deviceNetworkManager.clearNetworkAccessAppList(this.componentName);
        deviceNetworkManager.clearNetworkList(this.componentName);
        return true;
    }

    @Override
    public boolean cleanDomainRules() {
        DeviceNetworkManager deviceNetworkManager = new DeviceNetworkManager();
        deviceNetworkManager.removeNetworkList(this.componentName, true, true, new ArrayList(getDomainsAllowRules()));
        deviceNetworkManager.removeNetworkList(this.componentName, false, true, new ArrayList(getDomainsDenyRules()));
        return true;
    }

    @Override
    public boolean cleanIPRules() {
        DeviceNetworkManager deviceNetworkManager = new DeviceNetworkManager();
        deviceNetworkManager.removeNetworkList(this.componentName, true, false, new ArrayList(getIPAllowRules()));
        deviceNetworkManager.removeNetworkList(this.componentName, false, false, new ArrayList(getIPDenyRules()));
        return true;
    }

    @Override
    public List<String> getAllowApps() {
        ArrayList networkAccessAppList = new DeviceNetworkManager().getNetworkAccessAppList(this.componentName, true);
        Intrinsics.checkNotNullExpressionValue(networkAccessAppList, "DeviceNetworkManager().g…List(componentName, true)");
        return networkAccessAppList;
    }

    @Override
    public List<String> getDomainsAllowRules() {
        return getNetworkList(true, true);
    }

    @Override
    public List<String> getDomainsDenyRules() {
        return getNetworkList(false, true);
    }

    @Override
    public List<String> getIPAllowRules() {
        return getNetworkList(true, false);
    }

    @Override
    public List<String> getIPDenyRules() {
        return getNetworkList(false, false);
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        return true;
    }
}

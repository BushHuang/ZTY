package com.xuehai.system.samsung.knox.v3;

import android.content.Context;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.net.firewall.DomainFilterRule;
import com.samsung.android.knox.net.firewall.Firewall;
import com.samsung.android.knox.net.firewall.FirewallResponse;
import com.samsung.android.knox.net.firewall.FirewallRule;
import com.xuehai.system.common.compat.FirewallPolicyDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J2\u0010\u000e\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0016\u0010\u0011\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0016\u0010\u0012\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001d"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/FirewallPolicySamsungV3;", "Lcom/xuehai/system/common/compat/FirewallPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "getPolicy", "()Lcom/xuehai/system/samsung/knox/v3/SamsungKnoxV3;", "addAllowApps", "", "packageNames", "", "", "addAppDomainsRules", "allowDomains", "denyDomains", "addDenyApps", "addDomainsRules", "cleanAppRules", "cleanDomainRules", "createFirewallRule", "Lcom/samsung/android/knox/net/firewall/FirewallRule;", "ruleType", "Lcom/samsung/android/knox/net/firewall/FirewallRule$RuleType;", "packageName", "ip", "setIptablesOption", "status", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class FirewallPolicySamsungV3 extends FirewallPolicyDefault {
    private final SamsungKnoxV3 policy;

    public FirewallPolicySamsungV3(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = SamsungKnoxV3.INSTANCE.getInstance(context);
    }

    private final FirewallRule createFirewallRule(FirewallRule.RuleType ruleType, String packageName, String ip) {
        Unit unit;
        FirewallRule firewallRule = new FirewallRule(ruleType, Firewall.AddressType.IPV4);
        if (ip != null) {
            List listSplit$default = StringsKt.split$default((CharSequence) ip, new String[]{":"}, false, 0, 6, (Object) null);
            firewallRule.setIpAddress((String) listSplit$default.get(0));
            if (listSplit$default.size() == 2) {
                firewallRule.setPortNumber((String) listSplit$default.get(1));
            } else {
                firewallRule.setPortNumber("*");
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            firewallRule.setIpAddress("*");
            firewallRule.setPortNumber("*");
        }
        firewallRule.setPortLocation(Firewall.PortLocation.ALL);
        if (packageName != null) {
            firewallRule.setApplication(new AppIdentity(packageName, (String) null));
        }
        firewallRule.setNetworkInterface(Firewall.NetworkInterface.ALL_NETWORKS);
        return firewallRule;
    }

    static FirewallRule createFirewallRule$default(FirewallPolicySamsungV3 firewallPolicySamsungV3, FirewallRule.RuleType ruleType, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createFirewallRule");
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return firewallPolicySamsungV3.createFirewallRule(ruleType, str, str2);
    }

    @Override
    public boolean addAllowApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = packageNames.iterator();
        while (it.hasNext()) {
            arrayList.add(createFirewallRule$default(this, FirewallRule.RuleType.ALLOW, (String) it.next(), null, 4, null));
        }
        Firewall firewall = this.policy.firewall();
        Object[] array = arrayList.toArray(new FirewallRule[0]);
        if (array != null) {
            return FirewallResponse.Result.FAILED != firewall.addRules((FirewallRule[]) array)[0].getResult();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    @Override
    public boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = packageNames.iterator();
        while (it.hasNext()) {
            arrayList.add(new DomainFilterRule(new AppIdentity((String) it.next(), (String) null), denyDomains, allowDomains));
        }
        return FirewallResponse.Result.FAILED != this.policy.firewall().addDomainFilterRules(arrayList)[0].getResult();
    }

    @Override
    public boolean addDenyApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = packageNames.iterator();
        while (it.hasNext()) {
            arrayList.add(createFirewallRule$default(this, FirewallRule.RuleType.DENY, (String) it.next(), null, 4, null));
        }
        Firewall firewall = this.policy.firewall();
        Object[] array = arrayList.toArray(new FirewallRule[0]);
        if (array != null) {
            return FirewallResponse.Result.FAILED != firewall.addRules((FirewallRule[]) array)[0].getResult();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    @Override
    public boolean addDomainsRules(List<String> allowDomains) {
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DomainFilterRule(new AppIdentity("*", (String) null), Collections.singletonList("*"), allowDomains));
        return FirewallResponse.Result.FAILED != this.policy.firewall().addDomainFilterRules(arrayList)[0].getResult();
    }

    @Override
    public boolean cleanAppRules() {
        return FirewallResponse.Result.FAILED != this.policy.firewall().clearRules(15)[0].getResult();
    }

    @Override
    public boolean cleanDomainRules() {
        return FirewallResponse.Result.FAILED != this.policy.firewall().removeDomainFilterRules(DomainFilterRule.CLEAR_ALL)[0].getResult();
    }

    protected final SamsungKnoxV3 getPolicy() {
        return this.policy;
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        return this.policy.firewall().isFirewallEnabled() == status || this.policy.firewall().enableFirewall(status).getResult() != FirewallResponse.Result.FAILED;
    }
}

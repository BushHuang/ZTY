package com.xuehai.system.samsung.knox.v3.custom.p620;

import android.content.Context;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.net.firewall.DomainFilterRule;
import com.samsung.android.knox.net.firewall.FirewallResponse;
import com.samsung.android.knox.net.firewall.FirewallRule;
import com.xuehai.system.samsung.knox.v3.FirewallPolicySamsungV3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/samsung/knox/v3/custom/p620/FirewallPolicySamsungP620;", "Lcom/xuehai/system/samsung/knox/v3/FirewallPolicySamsungV3;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addDomainsDenyRules", "", "denyDomains", "", "", "getAllowApps", "getDenyApps", "getDomainsAllowRules", "getDomainsDenyRules", "mdm_samsungknoxv3_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FirewallPolicySamsungP620 extends FirewallPolicySamsungV3 {
    public FirewallPolicySamsungP620(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public boolean addDomainsDenyRules(List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DomainFilterRule(new AppIdentity("*", (String) null), denyDomains, Collections.singletonList("*")));
        return FirewallResponse.Result.FAILED != getPolicy().firewall().addDomainFilterRules(arrayList)[0].getResult();
    }

    @Override
    public List<String> getAllowApps() {
        FirewallRule[] rules = getPolicy().firewall().getRules(1, FirewallRule.Status.ENABLED);
        Intrinsics.checkNotNullExpressionValue(rules, "policy.firewall().getRul…ewallRule.Status.ENABLED)");
        FirewallRule[] firewallRuleArr = rules;
        ArrayList arrayList = new ArrayList(firewallRuleArr.length);
        for (FirewallRule firewallRule : firewallRuleArr) {
            arrayList.add(firewallRule.getApplication().getPackageName());
        }
        return arrayList;
    }

    @Override
    public List<String> getDenyApps() {
        FirewallRule[] rules = getPolicy().firewall().getRules(2, FirewallRule.Status.ENABLED);
        Intrinsics.checkNotNullExpressionValue(rules, "policy.firewall().getRul…ewallRule.Status.ENABLED)");
        FirewallRule[] firewallRuleArr = rules;
        ArrayList arrayList = new ArrayList(firewallRuleArr.length);
        for (FirewallRule firewallRule : firewallRuleArr) {
            arrayList.add(firewallRule.getApplication().getPackageName());
        }
        return arrayList;
    }

    @Override
    public List<String> getDomainsAllowRules() {
        String[] strArrListIptablesRules = getPolicy().firewall().listIptablesRules();
        Intrinsics.checkNotNullExpressionValue(strArrListIptablesRules, "policy.firewall().listIptablesRules()");
        return ArraysKt.toList(strArrListIptablesRules);
    }

    @Override
    public List<String> getDomainsDenyRules() {
        String[] strArrListIptablesRules = getPolicy().firewall().listIptablesRules();
        Intrinsics.checkNotNullExpressionValue(strArrListIptablesRules, "policy.firewall().listIptablesRules()");
        return ArraysKt.toList(strArrListIptablesRules);
    }
}

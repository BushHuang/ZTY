package com.samsung.android.knox.net.firewall;

import android.app.enterprise.FirewallPolicy;
import com.samsung.android.knox.SupportLibUtils;
import com.samsung.android.knox.net.firewall.FirewallResponse;
import com.samsung.android.knox.net.firewall.FirewallRule;
import com.sec.enterprise.firewall.FirewallRule;
import java.util.ArrayList;
import java.util.List;

public class Firewall {
    public static final String ACTION_BLOCKED_DOMAIN = "com.samsung.android.knox.intent.action.BLOCKED_DOMAIN";
    public static final String EXTRA_BLOCKED_DOMAIN_ISFOREGROUND = "com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_ISFOREGROUND";
    public static final String EXTRA_BLOCKED_DOMAIN_PACKAGENAME = "com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_PACKAGENAME";
    public static final String EXTRA_BLOCKED_DOMAIN_TIMESTAMP = "com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_TIMESTAMP";
    public static final String EXTRA_BLOCKED_DOMAIN_UID = "com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_UID";
    public static final String EXTRA_BLOCKED_DOMAIN_URL = "com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_URL";
    private static final int FIREWALL_ALLOW_RULE = 1;
    private static final int FIREWALL_DENY_RULE = 2;
    private static final int FIREWALL_REDIRECT_EXCEPTION_RULE = 8;
    private static final int FIREWALL_REDIRECT_RULE = 4;
    private com.sec.enterprise.firewall.Firewall mFirewall;
    private FirewallPolicy mFirewallPolicy;

    public enum AddressType {
        IPV4,
        IPV6
    }

    public enum Direction {
        INPUT,
        OUTPUT,
        ALL
    }

    public enum NetworkInterface {
        ALL_NETWORKS,
        WIFI_DATA_ONLY,
        MOBILE_DATA_ONLY
    }

    public enum PortLocation {
        REMOTE,
        LOCAL,
        ALL
    }

    public enum Protocol {
        TCP,
        UDP,
        ALL
    }

    public Firewall(FirewallPolicy firewallPolicy) {
        this.mFirewall = null;
        this.mFirewallPolicy = firewallPolicy;
    }

    public Firewall(com.sec.enterprise.firewall.Firewall firewall) {
        this.mFirewall = firewall;
        this.mFirewallPolicy = null;
    }

    public FirewallResponse[] addDomainFilterRules(List<DomainFilterRule> list) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall == null) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "addDomainFilterRules", new Class[]{List.class}, 19));
        }
        try {
            return FirewallResponse.convertToNewArray(firewall.addDomainFilterRules(DomainFilterRule.convertToOldList(list)));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError e2) {
            throw new NoSuchMethodError(e2.getMessage());
        }
    }

    public FirewallResponse[] addRules(FirewallRule[] firewallRuleArr) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall != null) {
            try {
                return FirewallResponse.convertToNewArray(firewall.addRules(FirewallRule.convertToOldArray(firewallRuleArr)));
            } catch (NoClassDefFoundError e) {
                throw new NoClassDefFoundError(e.getMessage());
            } catch (NoSuchMethodError e2) {
                throw new NoSuchMethodError(e2.getMessage());
            }
        }
        if (firewallRuleArr == null) {
            return null;
        }
        for (FirewallRule firewallRule : firewallRuleArr) {
            try {
                FirewallRuleValidator.usesUnsupportedParameters(firewallRule);
            } catch (NoSuchMethodError e3) {
                throw new NoSuchMethodError(e3.getMessage());
            }
        }
        FirewallResponse[] firewallResponseArr = new FirewallResponse[firewallRuleArr.length];
        for (int i = 0; i < firewallRuleArr.length; i++) {
            FirewallRule firewallRule2 = firewallRuleArr[i];
            FirewallResponse firewallResponseValidateFirewallRule = FirewallRuleValidator.validateFirewallRule(firewallRule2);
            if (firewallResponseValidateFirewallRule.getResult() == FirewallResponse.Result.SUCCESS) {
                firewallResponseValidateFirewallRule = this.mFirewallPolicy.addRules(FirewallRuleTranslator.translateToOld(firewallRule2)) ? FirewallResponse.addSuccess() : FirewallResponse.addFail();
            }
            firewallResponseArr[i] = firewallResponseValidateFirewallRule;
        }
        return firewallResponseArr;
    }

    public FirewallResponse[] clearRules(int i) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall != null) {
            return FirewallResponse.convertToNewArray(firewall.clearRules(i));
        }
        FirewallResponse[] firewallResponseArr = new FirewallResponse[4];
        if ((i & 1) == 1) {
            firewallResponseArr[0] = this.mFirewallPolicy.cleanIptablesAllowRules() ? FirewallResponse.clearSuccess() : FirewallResponse.clearFail();
        } else {
            firewallResponseArr[0] = FirewallResponse.clearNoChanges();
        }
        if ((i & 2) == 2) {
            firewallResponseArr[1] = this.mFirewallPolicy.cleanIptablesDenyRules() ? FirewallResponse.clearSuccess() : FirewallResponse.clearFail();
        } else {
            firewallResponseArr[1] = FirewallResponse.clearNoChanges();
        }
        if ((i & 4) == 4) {
            firewallResponseArr[2] = this.mFirewallPolicy.cleanIptablesRerouteRules() ? FirewallResponse.clearSuccess() : FirewallResponse.clearFail();
        } else {
            firewallResponseArr[2] = FirewallResponse.clearNoChanges();
        }
        if ((i & 8) == 8) {
            firewallResponseArr[3] = this.mFirewallPolicy.cleanIptablesRedirectExceptionsRules() ? FirewallResponse.clearSuccess() : FirewallResponse.clearFail();
        } else {
            firewallResponseArr[3] = FirewallResponse.clearNoChanges();
        }
        return firewallResponseArr;
    }

    public FirewallResponse enableDomainFilterReport(boolean z) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall == null) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "enableDomainFilterReport", new Class[]{Boolean.TYPE}, 19));
        }
        try {
            return FirewallResponse.convertToNew(firewall.enableDomainFilterReport(z));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "enableDomainFilterReport", new Class[]{Boolean.TYPE}, 19));
        }
    }

    public FirewallResponse enableFirewall(boolean z) {
        FirewallResponse firewallResponse;
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall != null) {
            return FirewallResponse.convertToNew(firewall.enableFirewall(z));
        }
        if (this.mFirewallPolicy.setIptablesOption(z)) {
            firewallResponse = new FirewallResponse(FirewallResponse.Result.SUCCESS, FirewallResponse.ErrorCode.NO_ERROR, z ? " successfully enabled.\n" : " successfully disabled.\n");
        } else {
            firewallResponse = new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.UNEXPECTED_ERROR, z ? " failed to enable. Error: " : " failed to disable. Error: ");
        }
        return firewallResponse;
    }

    public List<DomainFilterReport> getDomainFilterReport(List<String> list) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall == null) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "getDomainFilterReport", new Class[]{List.class}, 19));
        }
        try {
            return DomainFilterReport.convertToNewList(firewall.getDomainFilterReport(list));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "getDomainFilterReport", new Class[]{List.class}, 19));
        }
    }

    public List<DomainFilterRule> getDomainFilterRules(List<String> list) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall == null) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "getDomainFilterRules", new Class[]{List.class}, 19));
        }
        try {
            return DomainFilterRule.convertToNewList(firewall.getDomainFilterRules(list));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "getDomainFilterRules", new Class[]{List.class}, 19));
        }
    }

    public FirewallRule[] getRules(int i, FirewallRule.Status status) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall != null) {
            return FirewallRule.convertToNewArray(firewall.getRules(i, SupportLibUtils.convertEnumToEnum(status, FirewallRule.Status.class)));
        }
        FirewallRule.Status status2 = isFirewallEnabled() ? FirewallRule.Status.ENABLED : FirewallRule.Status.DISABLED;
        if (status != null && status != status2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if ((i & 1) == 1) {
            arrayList.addAll(FirewallRuleTranslator.translateToNewList(this.mFirewallPolicy.getIptablesAllowRules(), FirewallRule.RuleType.ALLOW, status2));
        }
        if ((i & 2) == 2) {
            arrayList.addAll(FirewallRuleTranslator.translateToNewList(this.mFirewallPolicy.getIptablesDenyRules(), FirewallRule.RuleType.DENY, status2));
        }
        if ((i & 4) == 4) {
            arrayList.addAll(FirewallRuleTranslator.translateToNewList(this.mFirewallPolicy.getIptablesRerouteRules(), FirewallRule.RuleType.REDIRECT, status2));
        }
        if ((i & 8) == 8) {
            arrayList.addAll(FirewallRuleTranslator.translateToNewList(this.mFirewallPolicy.getIptablesRedirectExceptionsRules(), FirewallRule.RuleType.REDIRECT_EXCEPTION, status2));
        }
        FirewallRule[] firewallRuleArr = new FirewallRule[arrayList.size()];
        arrayList.toArray(firewallRuleArr);
        return firewallRuleArr;
    }

    public boolean isDomainFilterReportEnabled() {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall == null) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "isDomainFilterReportEnabled", null, 19));
        }
        try {
            return firewall.isDomainFilterReportEnabled();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "isDomainFilterReportEnabled", null, 19));
        }
    }

    public boolean isFirewallEnabled() {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        return firewall != null ? firewall.isFirewallEnabled() : this.mFirewallPolicy.getIptablesOption();
    }

    public String[] listIptablesRules() {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall != null) {
            return firewall.listIptablesRules();
        }
        List listListIptablesRules = this.mFirewallPolicy.listIptablesRules();
        String[] strArr = new String[listListIptablesRules.size()];
        listListIptablesRules.toArray(strArr);
        return strArr;
    }

    public FirewallResponse[] removeDomainFilterRules(List<DomainFilterRule> list) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall == null) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(Firewall.class, "removeDomainFilterRules", new Class[]{List.class}, 19));
        }
        try {
            return FirewallResponse.convertToNewArray(firewall.removeDomainFilterRules(DomainFilterRule.convertToOldList(list)));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchMethodError e2) {
            throw new NoSuchMethodError(e2.getMessage());
        }
    }

    public FirewallResponse[] removeRules(FirewallRule[] firewallRuleArr) {
        com.sec.enterprise.firewall.Firewall firewall = this.mFirewall;
        if (firewall != null) {
            try {
                return FirewallResponse.convertToNewArray(firewall.removeRules(FirewallRule.convertToOldArray(firewallRuleArr)));
            } catch (NoClassDefFoundError e) {
                throw new NoClassDefFoundError(e.getMessage());
            } catch (NoSuchMethodError e2) {
                throw new NoSuchMethodError(e2.getMessage());
            }
        }
        for (FirewallRule firewallRule : firewallRuleArr) {
            try {
                FirewallRuleValidator.usesUnsupportedParameters(firewallRule);
            } catch (NoSuchMethodError e3) {
                throw new NoSuchMethodError(e3.getMessage());
            }
        }
        FirewallResponse[] firewallResponseArr = new FirewallResponse[firewallRuleArr.length];
        for (int i = 0; i < firewallRuleArr.length; i++) {
            FirewallRule firewallRule2 = firewallRuleArr[i];
            FirewallResponse firewallResponseValidateFirewallRule = FirewallRuleValidator.validateFirewallRule(firewallRule2);
            if (firewallResponseValidateFirewallRule.getResult() == FirewallResponse.Result.SUCCESS) {
                firewallResponseValidateFirewallRule = this.mFirewallPolicy.removeRules(FirewallRuleTranslator.translateToOld(firewallRule2)) ? FirewallResponse.removeSuccess() : FirewallResponse.removeFail();
            }
            firewallResponseArr[i] = firewallResponseValidateFirewallRule;
        }
        return firewallResponseArr;
    }
}

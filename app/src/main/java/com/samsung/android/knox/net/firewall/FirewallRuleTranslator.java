package com.samsung.android.knox.net.firewall;

import android.app.enterprise.FirewallAllowRule;
import android.app.enterprise.FirewallDenyRule;
import android.app.enterprise.FirewallExceptionRule;
import android.app.enterprise.FirewallRerouteRule;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.net.firewall.Firewall;
import com.samsung.android.knox.net.firewall.FirewallRule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class FirewallRuleTranslator {
    private static String[] networkInterfaceOptions = {"wifi", "data", "*"};
    private static String[] portLocationOptions = {"remote", "local", "*"};

    static class AnonymousClass1 {
        static final int[] $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType;

        static {
            int[] iArr = new int[FirewallRule.RuleType.values().length];
            $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType = iArr;
            try {
                iArr[FirewallRule.RuleType.ALLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[FirewallRule.RuleType.DENY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[FirewallRule.RuleType.REDIRECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[FirewallRule.RuleType.REDIRECT_EXCEPTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    FirewallRuleTranslator() {
    }

    private static String translateNetworkInterfaceToString(Firewall.NetworkInterface networkInterface) {
        if (networkInterface == null) {
            return null;
        }
        return networkInterface.equals(Firewall.NetworkInterface.WIFI_DATA_ONLY) ? networkInterfaceOptions[0] : networkInterface.equals(Firewall.NetworkInterface.MOBILE_DATA_ONLY) ? networkInterfaceOptions[1] : networkInterfaceOptions[2];
    }

    private static String translatePortLocationToString(Firewall.PortLocation portLocation) {
        return portLocation == null ? "" : portLocation.equals(Firewall.PortLocation.REMOTE) ? portLocationOptions[0] : portLocation.equals(Firewall.PortLocation.LOCAL) ? portLocationOptions[1] : portLocationOptions[2];
    }

    private static Firewall.NetworkInterface translateStringToNetworkInterface(String str) {
        return networkInterfaceOptions[0].equals(str) ? Firewall.NetworkInterface.WIFI_DATA_ONLY : networkInterfaceOptions[1].equals(str) ? Firewall.NetworkInterface.MOBILE_DATA_ONLY : Firewall.NetworkInterface.ALL_NETWORKS;
    }

    private static Firewall.PortLocation translateStringToPortLocation(String str) {
        return portLocationOptions[0].equals(str) ? Firewall.PortLocation.REMOTE : portLocationOptions[1].equals(str) ? Firewall.PortLocation.LOCAL : Firewall.PortLocation.ALL;
    }

    private static FirewallRule translateToNew(String str, FirewallRule.RuleType ruleType) {
        if (ruleType == FirewallRule.RuleType.ALLOW) {
            return translateToNewAllow(str);
        }
        if (ruleType == FirewallRule.RuleType.DENY) {
            return translateToNewDeny(str);
        }
        if (ruleType == FirewallRule.RuleType.REDIRECT) {
            return translateToNewRedirect(str);
        }
        if (ruleType == FirewallRule.RuleType.REDIRECT_EXCEPTION) {
            return translateToNewRedirectException(str);
        }
        return null;
    }

    private static FirewallRule translateToNewAllow(String str) {
        String str2;
        Firewall.NetworkInterface networkInterfaceTranslateStringToNetworkInterface;
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length < 2 || strArrSplit.length > 4) {
            return null;
        }
        Firewall.PortLocation portLocation = Firewall.PortLocation.ALL;
        Firewall.NetworkInterface networkInterfaceTranslateStringToNetworkInterface2 = Firewall.NetworkInterface.ALL_NETWORKS;
        int iLastIndexOf = strArrSplit[0].lastIndexOf(":");
        if (iLastIndexOf == -1) {
            return null;
        }
        String strSubstring = strArrSplit[0].substring(0, iLastIndexOf);
        String strSubstring2 = strArrSplit[0].substring(iLastIndexOf + 1);
        Firewall.PortLocation portLocationTranslateStringToPortLocation = translateStringToPortLocation(strArrSplit[1]);
        if (strArrSplit.length == 3) {
            networkInterfaceTranslateStringToNetworkInterface2 = translateStringToNetworkInterface(strArrSplit[2]);
        }
        if (strArrSplit.length > 3) {
            str2 = strArrSplit[2];
            networkInterfaceTranslateStringToNetworkInterface = translateStringToNetworkInterface(strArrSplit[3]);
        } else {
            Firewall.NetworkInterface networkInterface = networkInterfaceTranslateStringToNetworkInterface2;
            str2 = "*";
            networkInterfaceTranslateStringToNetworkInterface = networkInterface;
        }
        FirewallRule firewallRule = new FirewallRule(FirewallRule.RuleType.ALLOW, Firewall.AddressType.IPV4);
        AppIdentity appIdentity = new AppIdentity(str2, (String) null);
        firewallRule.setIpAddress(strSubstring);
        firewallRule.setPortNumber(strSubstring2);
        firewallRule.setPortLocation(portLocationTranslateStringToPortLocation);
        firewallRule.setApplication(appIdentity);
        firewallRule.setNetworkInterface(networkInterfaceTranslateStringToNetworkInterface);
        return firewallRule;
    }

    private static FirewallRule translateToNewDeny(String str) {
        String str2;
        Firewall.NetworkInterface networkInterfaceTranslateStringToNetworkInterface;
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length < 2 || strArrSplit.length > 4) {
            return null;
        }
        Firewall.PortLocation portLocation = Firewall.PortLocation.ALL;
        Firewall.NetworkInterface networkInterfaceTranslateStringToNetworkInterface2 = Firewall.NetworkInterface.ALL_NETWORKS;
        int iLastIndexOf = strArrSplit[0].lastIndexOf(":");
        if (iLastIndexOf == -1) {
            return null;
        }
        String strSubstring = strArrSplit[0].substring(0, iLastIndexOf);
        String strSubstring2 = strArrSplit[0].substring(iLastIndexOf + 1);
        Firewall.PortLocation portLocationTranslateStringToPortLocation = translateStringToPortLocation(strArrSplit[1]);
        if (strArrSplit.length == 3) {
            networkInterfaceTranslateStringToNetworkInterface2 = translateStringToNetworkInterface(strArrSplit[2]);
        }
        if (strArrSplit.length > 3) {
            str2 = strArrSplit[2];
            networkInterfaceTranslateStringToNetworkInterface = translateStringToNetworkInterface(strArrSplit[3]);
        } else {
            Firewall.NetworkInterface networkInterface = networkInterfaceTranslateStringToNetworkInterface2;
            str2 = "*";
            networkInterfaceTranslateStringToNetworkInterface = networkInterface;
        }
        FirewallRule firewallRule = new FirewallRule(FirewallRule.RuleType.DENY, Firewall.AddressType.IPV4);
        AppIdentity appIdentity = new AppIdentity(str2, (String) null);
        firewallRule.setIpAddress(strSubstring);
        firewallRule.setPortNumber(strSubstring2);
        firewallRule.setPortLocation(portLocationTranslateStringToPortLocation);
        firewallRule.setApplication(appIdentity);
        firewallRule.setNetworkInterface(networkInterfaceTranslateStringToNetworkInterface);
        return firewallRule;
    }

    static List<FirewallRule> translateToNewList(List<String> list, FirewallRule.RuleType ruleType, FirewallRule.Status status) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            FirewallRule firewallRuleTranslateToNew = translateToNew(it.next(), ruleType);
            firewallRuleTranslateToNew.setStatus(status);
            arrayList.add(firewallRuleTranslateToNew);
        }
        return arrayList;
    }

    private static FirewallRule translateToNewRedirect(String str) {
        String str2;
        Firewall.NetworkInterface networkInterfaceTranslateStringToNetworkInterface;
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length != 2 && strArrSplit.length != 4) {
            return null;
        }
        Firewall.NetworkInterface networkInterface = Firewall.NetworkInterface.ALL_NETWORKS;
        int iLastIndexOf = strArrSplit[0].lastIndexOf(":");
        if (iLastIndexOf == -1) {
            return null;
        }
        String strSubstring = strArrSplit[0].substring(0, iLastIndexOf);
        String strSubstring2 = strArrSplit[0].substring(iLastIndexOf + 1);
        int iLastIndexOf2 = strArrSplit[1].lastIndexOf(":");
        if (iLastIndexOf2 == -1) {
            return null;
        }
        String strSubstring3 = strArrSplit[1].substring(0, iLastIndexOf2);
        String strSubstring4 = strArrSplit[1].substring(iLastIndexOf2 + 1);
        if (strArrSplit.length == 4) {
            str2 = strArrSplit[2];
            networkInterfaceTranslateStringToNetworkInterface = translateStringToNetworkInterface(strArrSplit[3]);
        } else {
            str2 = "*";
            networkInterfaceTranslateStringToNetworkInterface = networkInterface;
        }
        FirewallRule firewallRule = new FirewallRule(FirewallRule.RuleType.REDIRECT, Firewall.AddressType.IPV4);
        AppIdentity appIdentity = new AppIdentity(str2, (String) null);
        firewallRule.setIpAddress(strSubstring);
        firewallRule.setPortNumber(strSubstring2);
        firewallRule.setTargetIpAddress(strSubstring3);
        firewallRule.setTargetPortNumber(strSubstring4);
        firewallRule.setApplication(appIdentity);
        firewallRule.setNetworkInterface(networkInterfaceTranslateStringToNetworkInterface);
        return firewallRule;
    }

    private static FirewallRule translateToNewRedirectException(String str) {
        int iLastIndexOf;
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length < 1 || strArrSplit.length > 2 || (iLastIndexOf = strArrSplit[0].lastIndexOf(":")) == -1) {
            return null;
        }
        String strSubstring = strArrSplit[0].substring(0, iLastIndexOf);
        String strSubstring2 = strArrSplit[0].substring(iLastIndexOf + 1);
        String str2 = strArrSplit.length == 2 ? strArrSplit[1] : "*";
        FirewallRule firewallRule = new FirewallRule(FirewallRule.RuleType.REDIRECT_EXCEPTION, Firewall.AddressType.IPV4);
        AppIdentity appIdentity = new AppIdentity(str2, (String) null);
        firewallRule.setIpAddress(strSubstring);
        firewallRule.setPortNumber(strSubstring2);
        firewallRule.setApplication(appIdentity);
        return firewallRule;
    }

    static Object translateToOld(FirewallRule firewallRule) {
        int i = AnonymousClass1.$SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[firewallRule.getRuleType().ordinal()];
        if (i == 1) {
            return translateToOldAllow(firewallRule);
        }
        if (i == 2) {
            return translateToOldDeny(firewallRule);
        }
        if (i == 3) {
            return translateToOldRedirect(firewallRule);
        }
        if (i != 4) {
            return null;
        }
        return translateToOldRedirectException(firewallRule);
    }

    private static FirewallAllowRule translateToOldAllow(FirewallRule firewallRule) {
        StringBuilder sb = new StringBuilder();
        sb.append(firewallRule.getIpAddress());
        sb.append(":");
        sb.append(firewallRule.getPortNumber());
        sb.append(";");
        sb.append(translatePortLocationToString(firewallRule.getPortLocation()));
        if (!firewallRule.getNetworkInterface().equals(Firewall.NetworkInterface.ALL_NETWORKS)) {
            sb.append(";");
            sb.append(translateNetworkInterfaceToString(firewallRule.getNetworkInterface()));
        }
        FirewallAllowRule firewallAllowRule = new FirewallAllowRule();
        firewallAllowRule.add(sb.toString());
        return firewallAllowRule;
    }

    private static FirewallDenyRule translateToOldDeny(FirewallRule firewallRule) {
        StringBuilder sb = new StringBuilder();
        sb.append(firewallRule.getIpAddress());
        sb.append(":");
        sb.append(firewallRule.getPortNumber());
        sb.append(";");
        sb.append(translatePortLocationToString(firewallRule.getPortLocation()));
        String packageName = firewallRule.getApplication().getPackageName();
        if ((packageName != null && !packageName.equals("*")) || !firewallRule.getNetworkInterface().equals(Firewall.NetworkInterface.ALL_NETWORKS)) {
            sb.append(";");
            sb.append(packageName);
            sb.append(";");
            sb.append(translateNetworkInterfaceToString(firewallRule.getNetworkInterface()));
        }
        FirewallDenyRule firewallDenyRule = new FirewallDenyRule();
        firewallDenyRule.add(sb.toString());
        return firewallDenyRule;
    }

    private static FirewallRerouteRule translateToOldRedirect(FirewallRule firewallRule) {
        StringBuilder sb = new StringBuilder();
        sb.append(firewallRule.getIpAddress());
        sb.append(":");
        sb.append(firewallRule.getPortNumber());
        sb.append(";");
        sb.append(firewallRule.getTargetIpAddress());
        sb.append(":");
        sb.append(firewallRule.getTargetPortNumber());
        String packageName = firewallRule.getApplication().getPackageName();
        if ((packageName != null && !packageName.equals("*")) || !firewallRule.getNetworkInterface().equals(Firewall.NetworkInterface.ALL_NETWORKS)) {
            sb.append(";");
            sb.append(packageName);
            sb.append(";");
            sb.append(translateNetworkInterfaceToString(firewallRule.getNetworkInterface()));
        }
        FirewallRerouteRule firewallRerouteRule = new FirewallRerouteRule();
        firewallRerouteRule.add(sb.toString());
        return firewallRerouteRule;
    }

    private static FirewallExceptionRule translateToOldRedirectException(FirewallRule firewallRule) {
        FirewallExceptionRule firewallExceptionRule = new FirewallExceptionRule();
        firewallExceptionRule.add(firewallRule.getIpAddress() + ":" + firewallRule.getPortNumber());
        return firewallExceptionRule;
    }
}

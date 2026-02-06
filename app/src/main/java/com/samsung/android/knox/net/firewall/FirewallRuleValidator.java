package com.samsung.android.knox.net.firewall;

import android.text.TextUtils;
import android.util.Patterns;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.SupportLibUtils;
import com.samsung.android.knox.net.firewall.Firewall;
import com.samsung.android.knox.net.firewall.FirewallResponse;
import com.samsung.android.knox.net.firewall.FirewallRule;

class FirewallRuleValidator {
    private static final String ADDRESS = "address";
    private static final String APP_IDENTITY = "app identity";
    private static final String DIRECTION = "direction";
    private static final String NETWORK_INTERFACE = "network interface";
    private static final String PARAMETERS = "Parameter(s): ";
    private static final String PORT_LOCATION = "port location";
    private static final String PORT_NUMBER = "port number";
    private static final String PROTOCOL = "protocol";
    private static final int SIZE_IPV4_ADDRESS = 4;
    private static final int SIZE_IPV6_ADDRESS = 16;
    private static final int SIZE_SHORT_INT = 2;
    private static final String SOURCE_ADDRESS = "source address";
    private static final String SOURCE_PORT_NUMBER = "source port number";
    private static final String TARGET_IP = "target IP";
    private static final String TARGET_PORT_NUMBER = "target port number";

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

    FirewallRuleValidator() {
    }

    private static long convertFromHexToInt(String str) {
        return Long.parseLong(str, 16);
    }

    private static String convertIpv6ToCompleteForm(String str) {
        if (str == null || !str.contains("::")) {
            return str;
        }
        String[] strArrSplit = str.split("::");
        int i = 0;
        if (strArrSplit.length != 1) {
            if (strArrSplit.length != 2) {
                return null;
            }
            String[] strArrSplit2 = strArrSplit[0].split(":");
            String[] strArrSplit3 = strArrSplit[1].split(":");
            int length = (8 - strArrSplit2.length) - strArrSplit3.length;
            StringBuilder sb = new StringBuilder();
            while (i < strArrSplit2.length) {
                sb.append(strArrSplit2[i] + ":");
                i++;
            }
            for (int length2 = strArrSplit2.length; length2 < strArrSplit2.length + length; length2++) {
                sb.append("0:");
            }
            for (int length3 = strArrSplit2.length + length; length3 < 8; length3++) {
                sb.append(strArrSplit3[(length3 - strArrSplit2.length) - length]);
                if (length3 != 7) {
                    sb.append(":");
                }
            }
            return sb.toString();
        }
        if (str.charAt(0) == ':') {
            String[] strArrSplit4 = strArrSplit[0].split(":");
            int length4 = 8 - strArrSplit4.length;
            StringBuilder sb2 = new StringBuilder();
            while (i < length4) {
                sb2.append("0:");
                i++;
            }
            for (int i2 = length4; i2 < 8; i2++) {
                sb2.append(strArrSplit4[i2 - length4]);
                if (i2 != 7) {
                    sb2.append(":");
                }
            }
            return sb2.toString();
        }
        String[] strArrSplit5 = strArrSplit[0].split(":");
        int length5 = 8 - strArrSplit5.length;
        StringBuilder sb3 = new StringBuilder();
        while (i < length5) {
            sb3.append(strArrSplit5[i] + ":");
            i++;
        }
        while (length5 < 8) {
            sb3.append("0");
            if (length5 != 7) {
                sb3.append(":");
            }
            length5++;
        }
        return sb3.toString();
    }

    private static boolean isIpv4MappedAddress(byte[] bArr) {
        if (bArr != null && bArr.length >= 16) {
            for (int i = 0; i < 10; i++) {
                if (bArr[i] != 0) {
                    return false;
                }
            }
            if (bArr[10] == -1 && bArr[11] == -1) {
                return true;
            }
        }
        return false;
    }

    private static byte[] translateIpv4MappedAddress(byte[] bArr) {
        if (!isIpv4MappedAddress(bArr)) {
            return null;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 12, bArr2, 0, 4);
        return bArr2;
    }

    private static byte[] translateIpv4TextualAddress(String str) throws NumberFormatException {
        if (str == null || str.length() == 0) {
            return null;
        }
        byte[] bArr = new byte[4];
        String[] strArrSplit = str.split("\\.", -1);
        try {
            int length = strArrSplit.length;
            int i = 0;
            try {
                if (length == 1) {
                    long j = Long.parseLong(strArrSplit[0]);
                    if (j >= 0 && j <= 4294967295L) {
                        bArr[0] = (byte) ((j >> 24) & 255);
                        bArr[1] = (byte) (((16777215 & j) >> 16) & 255);
                        bArr[2] = (byte) (((j & 65535) >> 8) & 255);
                        bArr[3] = (byte) (j & 255);
                    }
                    return null;
                }
                if (length == 2) {
                    long j2 = Integer.parseInt(strArrSplit[0]);
                    if (j2 < 0 || j2 > 255) {
                        return null;
                    }
                    bArr[0] = (byte) (j2 & 255);
                    long j3 = Integer.parseInt(strArrSplit[1]);
                    if (j3 >= 0 && j3 <= 16777215) {
                        bArr[1] = (byte) ((j3 >> 16) & 255);
                        bArr[2] = (byte) (((j3 & 65535) >> 8) & 255);
                        bArr[3] = (byte) (j3 & 255);
                    }
                    return null;
                }
                if (length != 3) {
                    if (length != 4) {
                        return null;
                    }
                    while (i < 4) {
                        long j4 = Integer.parseInt(strArrSplit[i]);
                        if (j4 >= 0 && j4 <= 255) {
                            bArr[i] = (byte) (j4 & 255);
                            i++;
                        }
                        return null;
                    }
                }
                while (i < 2) {
                    long j5 = Integer.parseInt(strArrSplit[i]);
                    if (j5 >= 0 && j5 <= 255) {
                        bArr[i] = (byte) (j5 & 255);
                        i++;
                    }
                    return null;
                }
                long j6 = Integer.parseInt(strArrSplit[2]);
                if (j6 >= 0 && j6 <= 65535) {
                    bArr[2] = (byte) ((j6 >> 8) & 255);
                    bArr[3] = (byte) (j6 & 255);
                }
                return null;
                return bArr;
                return bArr;
            } catch (NumberFormatException unused) {
                return null;
            }
        } catch (NumberFormatException unused2) {
            return null;
        }
    }

    static void usesUnsupportedParameters(FirewallRule firewallRule) throws NoSuchMethodError {
        if (firewallRule == null) {
            return;
        }
        FirewallRule.RuleType ruleType = firewallRule.getRuleType();
        if (firewallRule.getAddressType() != null && !firewallRule.getAddressType().equals(Firewall.AddressType.IPV4)) {
            throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(Firewall.AddressType.class, Firewall.AddressType.IPV6.name(), 17));
        }
        if (!TextUtils.isEmpty(firewallRule.getApplication().getSignature())) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(AppIdentity.class, "setSignature", new Class[]{String.class}, 19));
        }
        String packageName = firewallRule.getApplication().getPackageName();
        if (packageName != null && !packageName.trim().isEmpty() && !packageName.equals("*") && ruleType != null && !ruleType.equals(FirewallRule.RuleType.DENY) && !ruleType.equals(FirewallRule.RuleType.REDIRECT)) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(FirewallRule.class, "setPackageName", new Class[]{String.class}, 17));
        }
        if (ruleType != null && !ruleType.equals(FirewallRule.RuleType.REDIRECT) && !ruleType.equals(FirewallRule.RuleType.REDIRECT_EXCEPTION) && !firewallRule.getDirection().equals(Firewall.Direction.ALL)) {
            throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(Firewall.Direction.class, firewallRule.getDirection().name(), 17));
        }
        if (!firewallRule.getProtocol().equals(Firewall.Protocol.ALL)) {
            throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(Firewall.Protocol.class, firewallRule.getProtocol().name(), 17));
        }
    }

    static boolean validadeIpv4Range(String str) throws NumberFormatException {
        if (str != null && str.contains("-")) {
            String[] strArrSplit = str.split("-");
            if (strArrSplit.length == 2 && validateIpv4Address(strArrSplit[0]) && validateIpv4Address(strArrSplit[1])) {
                String[] strArrSplit2 = strArrSplit[0].split("\\.");
                String[] strArrSplit3 = strArrSplit[1].split("\\.");
                if (strArrSplit2 != null && strArrSplit2.length == 4 && strArrSplit3 != null && strArrSplit3.length == 4) {
                    for (int i = 0; i < 4; i++) {
                        try {
                            int i2 = Integer.parseInt(strArrSplit2[i]);
                            int i3 = Integer.parseInt(strArrSplit3[i]);
                            if (i2 > i3) {
                                return false;
                            }
                            if (i2 < i3) {
                                return true;
                            }
                        } catch (NumberFormatException unused) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    static boolean validadeIpv6Range(String str) {
        if (str != null && str.contains("-")) {
            String[] strArrSplit = str.split("-");
            if (strArrSplit.length == 2 && validateIpv6Address(strArrSplit[0]) && validateIpv6Address(strArrSplit[1])) {
                String[] strArrSplit2 = str.split("-");
                if (strArrSplit2[0].contains("::")) {
                    strArrSplit2[0] = convertIpv6ToCompleteForm(strArrSplit2[0]);
                }
                if (strArrSplit2[1].contains("::")) {
                    strArrSplit2[1] = convertIpv6ToCompleteForm(strArrSplit2[1]);
                }
                String[] strArrSplit3 = strArrSplit2[0].split(":");
                String[] strArrSplit4 = strArrSplit2[1].split(":");
                if (strArrSplit3 != null && strArrSplit3.length == 8 && strArrSplit4 != null && strArrSplit4.length == 8) {
                    for (int i = 0; i < 8; i++) {
                        long jConvertFromHexToInt = convertFromHexToInt(strArrSplit3[i]);
                        long jConvertFromHexToInt2 = convertFromHexToInt(strArrSplit4[i]);
                        if (jConvertFromHexToInt > jConvertFromHexToInt2) {
                            return false;
                        }
                        if (jConvertFromHexToInt < jConvertFromHexToInt2) {
                            return true;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    static boolean validadePortNumberRange(String str) {
        if (str != null && str.contains("-")) {
            String[] strArrSplit = str.split("-");
            if (strArrSplit.length == 2 && validatePortNumber(strArrSplit[0]) && validatePortNumber(strArrSplit[1])) {
                try {
                    return Integer.parseInt(strArrSplit[0]) <= Integer.parseInt(strArrSplit[1]);
                } catch (NumberFormatException unused) {
                }
            }
        }
        return false;
    }

    private static FirewallResponse validateAllowRule(FirewallRule firewallRule) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        if (firewallRule == null) {
            return new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.OPERATION_NOT_PERMITTED_ERROR, "Rule is null.");
        }
        Firewall.AddressType addressType = firewallRule.getAddressType();
        String ipAddress = firewallRule.getIpAddress();
        boolean z2 = false;
        if (addressType.equals(Firewall.AddressType.IPV4)) {
            if (!validadeIpv4Range(ipAddress) && !validateIpv4Address(ipAddress) && !"*".equals(ipAddress)) {
                sb.append("Parameter(s): address");
                z = false;
            }
            z = true;
        } else {
            if (!validadeIpv6Range(ipAddress) && !validateIpv6Address(ipAddress) && !"*".equals(ipAddress)) {
                sb.append("Parameter(s): address");
                z = false;
            }
            z = true;
        }
        if (!validatePortNumber(firewallRule.getPortNumber()) && !validadePortNumberRange(firewallRule.getPortNumber()) && !"*".equals(firewallRule.getPortNumber())) {
            if (z) {
                sb.append("Parameter(s): port number");
            } else {
                sb.append(", port number");
            }
            z = false;
        }
        if (firewallRule.getPortLocation() == null) {
            if (z) {
                sb.append("Parameter(s): port location");
            } else {
                sb.append(", port location");
            }
            z = false;
        }
        if (firewallRule.getApplication() == null || firewallRule.getApplication().getPackageName() == null || (!TextUtils.isEmpty(firewallRule.getApplication().getPackageName()) && !validatePackageName(firewallRule.getApplication().getPackageName()))) {
            if (z) {
                sb.append("Parameter(s): app identity");
            } else {
                sb.append(", app identity");
            }
            z = false;
        }
        if (firewallRule.getNetworkInterface() == null) {
            if (z) {
                sb.append("Parameter(s): network interface");
            } else {
                sb.append(", network interface");
            }
            z = false;
        }
        if (firewallRule.getProtocol() == null) {
            if (z) {
                sb.append("Parameter(s): protocol");
            } else {
                sb.append(", protocol");
            }
            z = false;
        }
        if (firewallRule.getDirection() != null) {
            z2 = z;
        } else if (z) {
            sb.append("Parameter(s): direction");
        } else {
            sb.append(", direction");
        }
        if (z2) {
            return new FirewallResponse(FirewallResponse.Result.SUCCESS, FirewallResponse.ErrorCode.NO_ERROR, "Parameters validated successfully");
        }
        sb.append(" is(are) invalid.");
        return new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.INVALID_PARAMETER_ERROR, sb.toString());
    }

    private static FirewallResponse validateDenyRule(FirewallRule firewallRule) {
        return validateAllowRule(firewallRule);
    }

    static FirewallResponse validateFirewallRule(FirewallRule firewallRule) {
        if (firewallRule == null) {
            return new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.OPERATION_NOT_PERMITTED_ERROR, "Rule is null.");
        }
        int i = AnonymousClass1.$SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[firewallRule.getRuleType().ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.UNEXPECTED_ERROR, "Failed to validate Rule.") : validateRedirectExceptionRule(firewallRule) : validateRedirectRule(firewallRule) : validateDenyRule(firewallRule) : validateAllowRule(firewallRule);
    }

    static boolean validateIpv4Address(String str) {
        if (translateIpv4TextualAddress(str) != null) {
            return Patterns.IP_ADDRESS.matcher(str).matches();
        }
        return false;
    }

    static boolean validateIpv6Address(String str) {
        int i;
        byte[] bArrTranslateIpv4TextualAddress;
        if (str != null && str.length() >= 2) {
            byte[] bArr = new byte[16];
            if (str.charAt(0) != ':') {
                i = 0;
            } else {
                if (str.charAt(1) != ':') {
                    return false;
                }
                i = 1;
            }
            int i2 = i;
            int i3 = 0;
            boolean z = false;
            int i4 = 0;
            int i5 = -1;
            while (true) {
                if (i >= str.length()) {
                    break;
                }
                int i6 = i + 1;
                char cCharAt = str.charAt(i);
                int iDigit = Character.digit(cCharAt, 16);
                if (iDigit != -1) {
                    i3 = (i3 << 4) | iDigit;
                    if (i3 > 65535) {
                        return false;
                    }
                    i = i6;
                    z = true;
                } else if (cCharAt == ':') {
                    if (z) {
                        if (i6 == str.length() || i4 + 2 > 16) {
                            return false;
                        }
                        int i7 = i4 + 1;
                        bArr[i4] = (byte) ((i3 >> 8) & 255);
                        i4 = i7 + 1;
                        bArr[i7] = (byte) (i3 & 255);
                        i = i6;
                        i2 = i;
                        i3 = 0;
                        z = false;
                    } else {
                        if (i5 != -1) {
                            return false;
                        }
                        i5 = i4;
                        i = i6;
                        i2 = i;
                    }
                } else {
                    if (cCharAt != '.' || i4 + 4 > 16) {
                        return false;
                    }
                    String strSubstring = str.substring(i2, str.length());
                    int i8 = 0;
                    int i9 = 0;
                    while (true) {
                        int iIndexOf = strSubstring.indexOf(46, i8);
                        if (iIndexOf == -1) {
                            break;
                        }
                        i9++;
                        i8 = iIndexOf + 1;
                    }
                    if (i9 != 3 || (bArrTranslateIpv4TextualAddress = translateIpv4TextualAddress(strSubstring)) == null) {
                        return false;
                    }
                    int i10 = 0;
                    while (i10 < 4) {
                        bArr[i4] = bArrTranslateIpv4TextualAddress[i10];
                        i10++;
                        i4++;
                    }
                    z = false;
                }
            }
        } else {
            return false;
        }
    }

    static boolean validatePackageName(String str) {
        if (str == null) {
            return false;
        }
        if ("*".equals(str)) {
            return true;
        }
        String[] strArrSplit = str.split("\\.");
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '.') {
                i++;
            }
        }
        if (i >= strArrSplit.length) {
            return false;
        }
        for (String str2 : strArrSplit) {
            if (!str2.matches("^[A-Za-z0-9_]+$") || str2.charAt(0) == '_' || (str2.charAt(0) >= '0' && str2.charAt(0) <= '9')) {
                return false;
            }
        }
        return true;
    }

    static boolean validatePortNumber(String str) throws NumberFormatException {
        int i;
        if (str == null) {
            return false;
        }
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            i = -1;
        }
        return i >= 0 && i <= 65535;
    }

    private static FirewallResponse validateRedirectExceptionRule(FirewallRule firewallRule) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        if (firewallRule == null) {
            return new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.OPERATION_NOT_PERMITTED_ERROR, "Rule is null.");
        }
        Firewall.AddressType addressType = firewallRule.getAddressType();
        String ipAddress = firewallRule.getIpAddress();
        boolean z2 = false;
        if (addressType.equals(Firewall.AddressType.IPV4)) {
            if (!validadeIpv4Range(ipAddress) && !validateIpv4Address(ipAddress) && !"*".equals(ipAddress)) {
                sb.append("Parameter(s): address");
                z = false;
            }
            z = true;
        } else {
            if (!validadeIpv6Range(ipAddress) && !validateIpv6Address(ipAddress) && !"*".equals(ipAddress)) {
                sb.append("Parameter(s): address");
                z = false;
            }
            z = true;
        }
        if (!validatePortNumber(firewallRule.getPortNumber()) && !validadePortNumberRange(firewallRule.getPortNumber()) && !"*".equals(firewallRule.getPortNumber())) {
            if (z) {
                sb.append("Parameter(s): port number");
            } else {
                sb.append(", port number");
            }
            z = false;
        }
        if (firewallRule.getApplication() == null || firewallRule.getApplication().getPackageName() == null || (!TextUtils.isEmpty(firewallRule.getApplication().getPackageName()) && !validatePackageName(firewallRule.getApplication().getPackageName()))) {
            if (z) {
                sb.append("Parameter(s): app identity");
            } else {
                sb.append(", app identity");
            }
            z = false;
        }
        if (firewallRule.getProtocol() != null) {
            z2 = z;
        } else if (z) {
            sb.append("Parameter(s): protocol");
        } else {
            sb.append(", protocol");
        }
        if (z2) {
            return new FirewallResponse(FirewallResponse.Result.SUCCESS, FirewallResponse.ErrorCode.NO_ERROR, "Parameters validated successfully");
        }
        sb.append(" is(are) invalid.");
        return new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.INVALID_PARAMETER_ERROR, sb.toString());
    }

    private static FirewallResponse validateRedirectRule(FirewallRule firewallRule) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        if (firewallRule == null) {
            return new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.OPERATION_NOT_PERMITTED_ERROR, "Rule is null.");
        }
        Firewall.AddressType addressType = firewallRule.getAddressType();
        String ipAddress = firewallRule.getIpAddress();
        boolean z2 = false;
        if (addressType.equals(Firewall.AddressType.IPV4)) {
            if (!validadeIpv4Range(ipAddress) && !validateIpv4Address(ipAddress) && !"*".equals(ipAddress)) {
                sb.append("Parameter(s): source address");
                z = false;
            }
            z = true;
        } else {
            if (!validadeIpv6Range(ipAddress) && !validateIpv6Address(ipAddress) && !"*".equals(ipAddress)) {
                sb.append("Parameter(s): source address");
                z = false;
            }
            z = true;
        }
        if (!validatePortNumber(firewallRule.getPortNumber()) && !validadePortNumberRange(firewallRule.getPortNumber()) && !"*".equals(firewallRule.getPortNumber())) {
            if (z) {
                sb.append("Parameter(s): source port number");
            } else {
                sb.append(", source port number");
            }
            z = false;
        }
        String targetIpAddress = firewallRule.getTargetIpAddress();
        if (addressType.equals(Firewall.AddressType.IPV4)) {
            if (!validateIpv4Address(targetIpAddress)) {
                sb.append("Parameter(s): target IP");
                z = false;
            }
        } else if (!validateIpv6Address(targetIpAddress)) {
            sb.append("Parameter(s): target IP");
            z = false;
        }
        if (!validatePortNumber(firewallRule.getTargetPortNumber()) || "*".equals(firewallRule.getTargetPortNumber())) {
            if (z) {
                sb.append("Parameter(s): target port number");
            } else {
                sb.append(", target port number");
            }
            z = false;
        }
        if (firewallRule.getApplication() == null || firewallRule.getApplication().getPackageName() == null || (!TextUtils.isEmpty(firewallRule.getApplication().getPackageName()) && !validatePackageName(firewallRule.getApplication().getPackageName()))) {
            if (z) {
                sb.append("Parameter(s): app identity");
            } else {
                sb.append(", app identity");
            }
            z = false;
        }
        if (firewallRule.getNetworkInterface() == null) {
            if (z) {
                sb.append("Parameter(s): network interface");
            } else {
                sb.append(", network interface");
            }
            z = false;
        }
        if (firewallRule.getProtocol() != null) {
            z2 = z;
        } else if (z) {
            sb.append("Parameter(s): protocol");
        } else {
            sb.append(", protocol");
        }
        if (z2) {
            return new FirewallResponse(FirewallResponse.Result.SUCCESS, FirewallResponse.ErrorCode.NO_ERROR, "Parameters validated successfully");
        }
        sb.append(" is(are) invalid.");
        return new FirewallResponse(FirewallResponse.Result.FAILED, FirewallResponse.ErrorCode.INVALID_PARAMETER_ERROR, sb.toString());
    }
}

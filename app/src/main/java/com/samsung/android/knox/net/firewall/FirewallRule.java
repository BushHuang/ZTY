package com.samsung.android.knox.net.firewall;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.SupportLibUtils;
import com.samsung.android.knox.net.firewall.Firewall;
import com.sec.enterprise.firewall.Firewall;
import com.sec.enterprise.firewall.FirewallRule;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class FirewallRule implements Parcelable {
    private static final String ADDRESS = "address";
    private static final String ADDRESS_TYPE = "address type";
    private static final String APP_IDENTITY = "app identity";
    public static final Parcelable.Creator<FirewallRule> CREATOR = new Parcelable.Creator<FirewallRule>() {
        @Override
        public FirewallRule createFromParcel(Parcel parcel) {
            return new FirewallRule(parcel);
        }

        @Override
        public FirewallRule[] newArray(int i) {
            return new FirewallRule[i];
        }
    };
    private static final String DIRECTION = "direction";
    private static final String IS_INVALID = " is invalid.";
    private static final String NETWORK_INTERFACE = "network interface";
    private static final String PARAMETER = "Parameter: ";
    private static final String PORT_LOCATION = "port location";
    private static final String PORT_NUMBER = "port number";
    private static final String PROTOCOL = "protocol";
    private static final String RULE_TYPE = "rule type";
    private static final String TARGET_IP = "target IP";
    private static final String TARGET_PORT_NUMBER = "target port number";
    private static final String UNSUPPORTED_METHOD = "This method is not supported for this RuleType: ";
    private String mAddress;
    private Firewall.AddressType mAddressType;
    private AppIdentity mAppIdentity;
    private Firewall.Direction mDirection;
    private int mId;
    private Firewall.NetworkInterface mNetworkInterface;
    private Firewall.PortLocation mPortLocation;
    private String mPortNumber;
    private Firewall.Protocol mProtocol;
    private RuleType mRuleType;
    private Status mStatus;
    private String mTargetIp;
    private String mTargetPortNumber;

    static class AnonymousClass2 {
        static final int[] $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType;

        static {
            int[] iArr = new int[RuleType.values().length];
            $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType = iArr;
            try {
                iArr[RuleType.ALLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[RuleType.DENY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[RuleType.REDIRECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[RuleType.REDIRECT_EXCEPTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum RuleType {
        DENY,
        ALLOW,
        REDIRECT,
        REDIRECT_EXCEPTION
    }

    public enum Status {
        DISABLED,
        ENABLED,
        PENDING
    }

    FirewallRule(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mRuleType = (RuleType) parcel.readSerializable();
        this.mStatus = (Status) parcel.readSerializable();
        this.mAddress = parcel.readString();
        this.mPortNumber = parcel.readString();
        this.mPortLocation = (Firewall.PortLocation) parcel.readSerializable();
        this.mAppIdentity = (AppIdentity) parcel.readParcelable(AppIdentity.class.getClassLoader());
        this.mNetworkInterface = (Firewall.NetworkInterface) parcel.readSerializable();
        this.mDirection = (Firewall.Direction) parcel.readSerializable();
        this.mProtocol = (Firewall.Protocol) parcel.readSerializable();
        this.mAddressType = (Firewall.AddressType) parcel.readSerializable();
        this.mTargetIp = parcel.readString();
        this.mTargetPortNumber = parcel.readString();
    }

    public FirewallRule(RuleType ruleType, Firewall.AddressType addressType) {
        if (ruleType == null) {
            throw new InvalidParameterException("Parameter: rule type is invalid.");
        }
        if (addressType == null) {
            throw new InvalidParameterException("Parameter: address type is invalid.");
        }
        this.mRuleType = ruleType;
        this.mStatus = Status.DISABLED;
        this.mAddressType = addressType;
        this.mAddress = "*";
        this.mPortNumber = "*";
        this.mAppIdentity = new AppIdentity("*", (String) null);
        this.mPortLocation = Firewall.PortLocation.ALL;
        this.mNetworkInterface = Firewall.NetworkInterface.ALL_NETWORKS;
        this.mDirection = Firewall.Direction.ALL;
        this.mProtocol = Firewall.Protocol.ALL;
        this.mTargetIp = null;
        this.mTargetPortNumber = null;
        this.mId = -1;
    }

    private static FirewallRule convertToNew(com.sec.enterprise.firewall.FirewallRule firewallRule) {
        AppIdentity appIdentity;
        if (firewallRule == null) {
            return null;
        }
        RuleType ruleType = (RuleType) SupportLibUtils.convertEnumToEnum(firewallRule.getRuleType(), RuleType.class);
        FirewallRule firewallRule2 = new FirewallRule(ruleType, (Firewall.AddressType) SupportLibUtils.convertEnumToEnum(firewallRule.getAddressType(), Firewall.AddressType.class));
        firewallRule2.setId(firewallRule.getId());
        firewallRule2.setIpAddress(firewallRule.getIpAddress());
        firewallRule2.setPortNumber(firewallRule.getPortNumber());
        if (RuleType.ALLOW.equals(ruleType) || RuleType.DENY.equals(ruleType)) {
            firewallRule2.setPortLocation((Firewall.PortLocation) SupportLibUtils.convertEnumToEnum(firewallRule.getPortLocation(), Firewall.PortLocation.class));
            firewallRule2.setDirection((Firewall.Direction) SupportLibUtils.convertEnumToEnum(firewallRule.getDirection(), Firewall.Direction.class));
        }
        try {
            appIdentity = AppIdentity.convertToNew(firewallRule.getApplication());
        } catch (NoSuchMethodError unused) {
            appIdentity = new AppIdentity();
            appIdentity.setPackageName(firewallRule.getPackageName());
        }
        firewallRule2.setApplication(appIdentity);
        firewallRule2.setNetworkInterface((Firewall.NetworkInterface) SupportLibUtils.convertEnumToEnum(firewallRule.getNetworkInterface(), Firewall.NetworkInterface.class));
        firewallRule2.setProtocol((Firewall.Protocol) SupportLibUtils.convertEnumToEnum(firewallRule.getProtocol(), Firewall.Protocol.class));
        if (RuleType.REDIRECT.equals(ruleType)) {
            firewallRule2.setTargetIpAddress(firewallRule.getTargetIpAddress());
            firewallRule2.setTargetPortNumber(firewallRule.getTargetPortNumber());
        }
        firewallRule2.setStatus((Status) SupportLibUtils.convertEnumToEnum(firewallRule.getStatus(), Status.class));
        return firewallRule2;
    }

    static FirewallRule[] convertToNewArray(com.sec.enterprise.firewall.FirewallRule[] firewallRuleArr) {
        if (firewallRuleArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.sec.enterprise.firewall.FirewallRule firewallRule : firewallRuleArr) {
            arrayList.add(convertToNew(firewallRule));
        }
        FirewallRule[] firewallRuleArr2 = new FirewallRule[arrayList.size()];
        arrayList.toArray(firewallRuleArr2);
        return firewallRuleArr2;
    }

    private static com.sec.enterprise.firewall.FirewallRule convertToOld(FirewallRule firewallRule) throws NoSuchMethodError, NoClassDefFoundError {
        if (firewallRule == null) {
            return null;
        }
        com.sec.enterprise.firewall.FirewallRule firewallRule2 = new com.sec.enterprise.firewall.FirewallRule(SupportLibUtils.convertEnumToEnum(firewallRule.getRuleType(), FirewallRule.RuleType.class), SupportLibUtils.convertEnumToEnum(firewallRule.getAddressType(), Firewall.AddressType.class));
        firewallRule2.setId(firewallRule.getId());
        firewallRule2.setIpAddress(firewallRule.getIpAddress());
        firewallRule2.setPortNumber(firewallRule.getPortNumber());
        if (RuleType.ALLOW.equals(firewallRule.getRuleType()) || RuleType.DENY.equals(firewallRule.getRuleType())) {
            firewallRule2.setPortLocation(SupportLibUtils.convertEnumToEnum(firewallRule.getPortLocation(), Firewall.PortLocation.class));
            firewallRule2.setDirection(SupportLibUtils.convertEnumToEnum(firewallRule.getDirection(), Firewall.Direction.class));
        }
        try {
            firewallRule2.setApplication(AppIdentity.convertToOld(firewallRule.getApplication()));
        } catch (NoSuchMethodError unused) {
            if (!TextUtils.isEmpty(firewallRule.getApplication().getSignature())) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(FirewallRule.class, "setApplication", new Class[]{AppIdentity.class}, 19));
            }
            firewallRule2.setPackageName(firewallRule.getApplication().getPackageName());
        }
        firewallRule2.setNetworkInterface(SupportLibUtils.convertEnumToEnum(firewallRule.getNetworkInterface(), Firewall.NetworkInterface.class));
        firewallRule2.setProtocol(SupportLibUtils.convertEnumToEnum(firewallRule.getProtocol(), Firewall.Protocol.class));
        if (RuleType.REDIRECT.equals(firewallRule.getRuleType())) {
            firewallRule2.setTargetIpAddress(firewallRule.getTargetIpAddress());
            firewallRule2.setTargetPortNumber(firewallRule.getTargetPortNumber());
        }
        firewallRule2.setStatus(SupportLibUtils.convertEnumToEnum(firewallRule.getStatus(), FirewallRule.Status.class));
        return firewallRule2;
    }

    static com.sec.enterprise.firewall.FirewallRule[] convertToOldArray(FirewallRule[] firewallRuleArr) throws NoSuchMethodError, NoClassDefFoundError {
        if (firewallRuleArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (FirewallRule firewallRule : firewallRuleArr) {
            arrayList.add(convertToOld(firewallRule));
        }
        com.sec.enterprise.firewall.FirewallRule[] firewallRuleArr2 = new com.sec.enterprise.firewall.FirewallRule[arrayList.size()];
        arrayList.toArray(firewallRuleArr2);
        return firewallRuleArr2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof FirewallRule)) {
            return false;
        }
        FirewallRule firewallRule = (FirewallRule) obj;
        boolean z2 = ((firewallRule.getAddressType() == null && getAddressType() == null) || (firewallRule.getAddressType() != null && firewallRule.getAddressType().equals(getAddressType()))) & true & ((firewallRule.getIpAddress() == null && getIpAddress() == null) || (firewallRule.getIpAddress() != null && firewallRule.getIpAddress().equals(getIpAddress()))) & ((firewallRule.getNetworkInterface() == null && getNetworkInterface() == null) || (firewallRule.getNetworkInterface() != null && firewallRule.getNetworkInterface().equals(getNetworkInterface()))) & ((firewallRule.getApplication() == null && getApplication() == null) || (firewallRule.getApplication() != null && getApplication() != null && firewallRule.getApplication().getPackageName() == null && getApplication().getPackageName() == null) || !(firewallRule.getApplication() == null || getApplication() == null || firewallRule.getApplication().getPackageName() == null || !firewallRule.getApplication().getPackageName().equals(getApplication().getPackageName()))) & ((firewallRule.getApplication() != null && getApplication() != null && firewallRule.getApplication().getSignature() == null && getApplication().getSignature() == null) || !(firewallRule.getApplication() == null || getApplication() == null || firewallRule.getApplication().getSignature() == null || !firewallRule.getApplication().getSignature().equals(getApplication().getSignature())));
        if ((RuleType.DENY.equals(firewallRule.getRuleType()) && RuleType.DENY.equals(getRuleType())) || (RuleType.ALLOW.equals(firewallRule.getRuleType()) && RuleType.ALLOW.equals(getRuleType()))) {
            z2 = z2 & ((firewallRule.getDirection() == null && getDirection() == null) || (firewallRule.getDirection() != null && firewallRule.getDirection().equals(getDirection()))) & ((firewallRule.getPortLocation() == null && getPortLocation() == null) || (firewallRule.getPortLocation() != null && firewallRule.getPortLocation().equals(getPortLocation())));
        }
        boolean z3 = z2 & ((firewallRule.getPortNumber() == null && getPortNumber() == null) || (firewallRule.getPortNumber() != null && firewallRule.getPortNumber().equals(getPortNumber()))) & ((firewallRule.getProtocol() == null && getProtocol() == null) || (firewallRule.getProtocol() != null && firewallRule.getProtocol().equals(getProtocol()))) & ((firewallRule.getRuleType() == null && getRuleType() == null) || (firewallRule.getRuleType() != null && firewallRule.getRuleType().equals(getRuleType()))) & ((firewallRule.getStatus() == null && getStatus() == null) || (firewallRule.getStatus() != null && firewallRule.getStatus().equals(getStatus())));
        if (!RuleType.REDIRECT.equals(firewallRule.getRuleType()) || !RuleType.REDIRECT.equals(getRuleType())) {
            return z3;
        }
        boolean z4 = z3 & ((firewallRule.getTargetIpAddress() == null && getTargetIpAddress() == null) || (firewallRule.getTargetIpAddress() != null && firewallRule.getTargetIpAddress().equals(getTargetIpAddress())));
        if ((firewallRule.getTargetPortNumber() == null && getTargetPortNumber() == null) || (firewallRule.getTargetPortNumber() != null && firewallRule.getTargetPortNumber().equals(getTargetPortNumber()))) {
            z = true;
        }
        return z4 & z;
    }

    public Firewall.AddressType getAddressType() {
        return this.mAddressType;
    }

    public AppIdentity getApplication() {
        return this.mAppIdentity;
    }

    public Firewall.Direction getDirection() {
        if (RuleType.ALLOW.equals(getRuleType()) || RuleType.DENY.equals(getRuleType())) {
            return this.mDirection;
        }
        throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
    }

    public int getId() {
        return this.mId;
    }

    public String getIpAddress() {
        return this.mAddress;
    }

    public Firewall.NetworkInterface getNetworkInterface() {
        return this.mNetworkInterface;
    }

    public Firewall.PortLocation getPortLocation() {
        if (RuleType.ALLOW.equals(getRuleType()) || RuleType.DENY.equals(getRuleType())) {
            return this.mPortLocation;
        }
        throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
    }

    public String getPortNumber() {
        return this.mPortNumber;
    }

    public Firewall.Protocol getProtocol() {
        return this.mProtocol;
    }

    public RuleType getRuleType() {
        return this.mRuleType;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public String getTargetIpAddress() {
        if (RuleType.REDIRECT.equals(getRuleType())) {
            return this.mTargetIp;
        }
        throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
    }

    public String getTargetPortNumber() {
        if (RuleType.REDIRECT.equals(getRuleType())) {
            return this.mTargetPortNumber;
        }
        throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
    }

    public int hashCode() {
        String str = this.mAddress;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        Firewall.AddressType addressType = this.mAddressType;
        int iHashCode2 = (iHashCode + (addressType == null ? 0 : addressType.hashCode())) * 31;
        Firewall.Direction direction = this.mDirection;
        int iHashCode3 = (((iHashCode2 + (direction == null ? 0 : direction.hashCode())) * 31) + this.mId) * 31;
        Firewall.NetworkInterface networkInterface = this.mNetworkInterface;
        int iHashCode4 = (iHashCode3 + (networkInterface == null ? 0 : networkInterface.hashCode())) * 31;
        AppIdentity appIdentity = this.mAppIdentity;
        int iHashCode5 = (iHashCode4 + (appIdentity == null ? 0 : appIdentity.hashCode())) * 31;
        Firewall.PortLocation portLocation = this.mPortLocation;
        int iHashCode6 = (iHashCode5 + (portLocation == null ? 0 : portLocation.hashCode())) * 31;
        String str2 = this.mPortNumber;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Firewall.Protocol protocol = this.mProtocol;
        int iHashCode8 = (iHashCode7 + (protocol == null ? 0 : protocol.hashCode())) * 31;
        RuleType ruleType = this.mRuleType;
        int iHashCode9 = (iHashCode8 + (ruleType == null ? 0 : ruleType.hashCode())) * 31;
        Status status = this.mStatus;
        int iHashCode10 = (iHashCode9 + (status == null ? 0 : status.hashCode())) * 31;
        String str3 = this.mTargetIp;
        int iHashCode11 = (iHashCode10 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.mTargetPortNumber;
        return iHashCode11 + (str4 != null ? str4.hashCode() : 0);
    }

    public void setApplication(AppIdentity appIdentity) {
        if (appIdentity == null || !FirewallRuleValidator.validatePackageName(appIdentity.getPackageName())) {
            throw new InvalidParameterException("Parameter: app identity is invalid.");
        }
        this.mAppIdentity = appIdentity;
    }

    public void setDirection(Firewall.Direction direction) {
        if (direction == null) {
            throw new InvalidParameterException("Parameter: direction is invalid.");
        }
        if (RuleType.ALLOW.equals(getRuleType()) || RuleType.DENY.equals(getRuleType())) {
            this.mDirection = direction;
            return;
        }
        throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setIpAddress(String str) {
        if (this.mAddressType.equals(Firewall.AddressType.IPV4)) {
            if (!FirewallRuleValidator.validadeIpv4Range(str) && !FirewallRuleValidator.validateIpv4Address(str) && !"*".equals(str)) {
                throw new InvalidParameterException("Parameter: address is invalid.");
            }
        } else if (this.mAddressType.equals(Firewall.AddressType.IPV6) && !FirewallRuleValidator.validadeIpv6Range(str) && !FirewallRuleValidator.validateIpv6Address(str) && !"*".equals(str)) {
            throw new InvalidParameterException("Parameter: address is invalid.");
        }
        this.mAddress = str;
    }

    public void setNetworkInterface(Firewall.NetworkInterface networkInterface) {
        if (networkInterface == null) {
            throw new InvalidParameterException("Parameter: network interface is invalid.");
        }
        this.mNetworkInterface = networkInterface;
    }

    public void setPortLocation(Firewall.PortLocation portLocation) {
        if (portLocation == null) {
            throw new InvalidParameterException("Parameter: port location is invalid.");
        }
        if (RuleType.ALLOW.equals(getRuleType()) || RuleType.DENY.equals(getRuleType())) {
            this.mPortLocation = portLocation;
            return;
        }
        throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
    }

    public void setPortNumber(String str) {
        if (!FirewallRuleValidator.validatePortNumber(str) && !FirewallRuleValidator.validadePortNumberRange(str) && !"*".equals(str)) {
            throw new InvalidParameterException("Parameter: port number is invalid.");
        }
        this.mPortNumber = str;
    }

    public void setProtocol(Firewall.Protocol protocol) {
        if (protocol == null) {
            throw new InvalidParameterException("Parameter: protocol is invalid.");
        }
        this.mProtocol = protocol;
    }

    public void setStatus(Status status) {
        this.mStatus = status;
    }

    public void setTargetIpAddress(String str) {
        if (!RuleType.REDIRECT.equals(getRuleType())) {
            throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
        }
        if (this.mAddressType.equals(Firewall.AddressType.IPV4)) {
            if (!FirewallRuleValidator.validateIpv4Address(str)) {
                throw new InvalidParameterException("Parameter: target IP is invalid.");
            }
        } else if (!FirewallRuleValidator.validateIpv6Address(str)) {
            throw new InvalidParameterException("Parameter: target IP is invalid.");
        }
        this.mTargetIp = str;
    }

    public void setTargetPortNumber(String str) {
        if (RuleType.REDIRECT.equals(getRuleType())) {
            if (!FirewallRuleValidator.validatePortNumber(str)) {
                throw new InvalidParameterException("Parameter: target port number is invalid.");
            }
            this.mTargetPortNumber = str;
        } else {
            throw new UnsupportedOperationException("This method is not supported for this RuleType: " + getRuleType().toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = AnonymousClass2.$SwitchMap$com$samsung$android$knox$net$firewall$FirewallRule$RuleType[this.mRuleType.ordinal()];
        if (i == 1 || i == 2) {
            sb.append("\nIP Address: " + getIpAddress());
            sb.append("\nPort Number: " + getPortNumber());
            sb.append("\nPort Location: " + getPortLocation());
            sb.append("\nPackage Name: " + getApplication().getPackageName());
            sb.append("\nSignature: " + getApplication().getSignature());
            sb.append("\nNetwork Interface: " + getNetworkInterface());
            sb.append("\nDirection: " + getDirection());
            sb.append("\nProtocol: " + getProtocol());
            sb.append("\nAddress Type: " + getAddressType() + "\n");
        } else if (i == 3) {
            sb.append("\nSource IP Address: " + getIpAddress());
            sb.append("\nSource Port Number: " + getPortNumber());
            sb.append("\nTarget IP Address: " + getTargetIpAddress());
            sb.append("\nTarget Port Number: " + getTargetPortNumber());
            sb.append("\nPackage Name: " + getApplication().getPackageName());
            sb.append("\nSignature: " + getApplication().getSignature());
            sb.append("\nNetwork Interface: " + getNetworkInterface());
            sb.append("\nProtocol: " + getProtocol() + "\n");
            sb.append("\nAddress Type: " + getAddressType() + "\n");
        } else if (i == 4) {
            sb.append("\nIP Address: " + getIpAddress());
            sb.append("\nPort Number: " + getPortNumber());
            sb.append("\nPackage Name: " + getApplication().getPackageName());
            sb.append("\nSignature: " + getApplication().getSignature());
            sb.append("\nProtocol: " + getProtocol() + "\n");
            sb.append("\nAddress Type: " + getAddressType() + "\n");
        }
        return sb.toString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeSerializable(this.mRuleType);
        parcel.writeSerializable(this.mStatus);
        parcel.writeString(this.mAddress);
        parcel.writeString(this.mPortNumber);
        parcel.writeSerializable(this.mPortLocation);
        parcel.writeParcelable(this.mAppIdentity, i);
        parcel.writeSerializable(this.mNetworkInterface);
        parcel.writeSerializable(this.mDirection);
        parcel.writeSerializable(this.mProtocol);
        parcel.writeSerializable(this.mAddressType);
        parcel.writeString(this.mTargetIp);
        parcel.writeString(this.mTargetPortNumber);
    }
}

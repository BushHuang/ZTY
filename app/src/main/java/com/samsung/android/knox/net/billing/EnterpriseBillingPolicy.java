package com.samsung.android.knox.net.billing;

import com.samsung.android.knox.SupportLibUtils;
import java.util.List;

public class EnterpriseBillingPolicy {
    private com.sec.enterprise.knox.billing.EnterpriseBillingPolicy mEnterpriseBillingPolicy;

    public EnterpriseBillingPolicy(com.sec.enterprise.knox.billing.EnterpriseBillingPolicy enterpriseBillingPolicy) {
        this.mEnterpriseBillingPolicy = enterpriseBillingPolicy;
    }

    public boolean activateProfile(String str, boolean z) {
        try {
            return this.mEnterpriseBillingPolicy.activateProfile(str, z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "activateProfile", new Class[]{String.class, Boolean.TYPE}, 13));
        }
    }

    public boolean allowRoaming(String str, boolean z) {
        try {
            return this.mEnterpriseBillingPolicy.allowRoaming(str, z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "allowRoaming", new Class[]{String.class, Boolean.TYPE}, 13));
        }
    }

    public boolean bindAppsToProfile(String str, List<String> list) {
        try {
            return this.mEnterpriseBillingPolicy.bindAppsToProfile(str, list);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "bindAppsToProfile", new Class[]{String.class, List.class}, 13));
        }
    }

    public boolean bindVpnToProfile(String str, String str2, String str3) {
        try {
            return this.mEnterpriseBillingPolicy.bindVpnToProfile(str, str2, str3);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "bindVpnToProfile", new Class[]{String.class, String.class, String.class}, 13));
        }
    }

    public boolean createProfile(EnterpriseBillingProfile enterpriseBillingProfile) {
        try {
            return this.mEnterpriseBillingPolicy.createProfile(EnterpriseBillingProfile.convertToOld(enterpriseBillingProfile));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public List<String> getAppsBoundToProfile(String str) {
        try {
            return this.mEnterpriseBillingPolicy.getAppsBoundToProfile(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "getAppsBoundToProfile", new Class[]{String.class}, 13));
        }
    }

    public List<String> getAvailableProfiles() {
        try {
            return this.mEnterpriseBillingPolicy.getAvailableProfiles();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "getAvailableProfiles", null, 13));
        }
    }

    public EnterpriseBillingProfile getBoundedProfile(String str) {
        try {
            return EnterpriseBillingProfile.convertToNew(this.mEnterpriseBillingPolicy.getBoundedProfile(str));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "getBoundedProfile", new Class[]{String.class}, 13));
        }
    }

    public EnterpriseBillingProfile getProfileDetails(String str) {
        try {
            return EnterpriseBillingProfile.convertToNew(this.mEnterpriseBillingPolicy.getProfileDetails(str));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "getProfileDetails", new Class[]{String.class}, 13));
        }
    }

    public boolean isProfileActive(String str) {
        try {
            return this.mEnterpriseBillingPolicy.isProfileActive(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "isProfileActive", new Class[]{String.class}, 13));
        }
    }

    public boolean isRoamingAllowed(String str) {
        try {
            return this.mEnterpriseBillingPolicy.isRoamingAllowed(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "isRoamingAllowed", new Class[]{String.class}, 13));
        }
    }

    public boolean removeProfile(String str) {
        try {
            return this.mEnterpriseBillingPolicy.removeProfile(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "removeProfile", new Class[]{String.class}, 13));
        }
    }

    public boolean unbindAppsFromProfile(String str, List<String> list) {
        try {
            return this.mEnterpriseBillingPolicy.unbindAppsFromProfile(str, list);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "unbindAppsFromProfile", new Class[]{String.class, List.class}, 13));
        }
    }

    public boolean unbindVpnFromProfile(String str, String str2) {
        try {
            return this.mEnterpriseBillingPolicy.unbindVpnFromProfile(str, str2);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseBillingPolicy.class, "unbindVpnFromProfile", new Class[]{String.class, String.class}, 13));
        }
    }

    public boolean updateProfile(EnterpriseBillingProfile enterpriseBillingProfile) {
        try {
            return this.mEnterpriseBillingPolicy.updateProfile(EnterpriseBillingProfile.convertToOld(enterpriseBillingProfile));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}

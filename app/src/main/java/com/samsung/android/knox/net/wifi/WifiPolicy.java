package com.samsung.android.knox.net.wifi;

import android.net.wifi.WifiConfiguration;
import com.samsung.android.knox.EnterpriseDeviceManager;
import java.util.Iterator;
import java.util.List;

public class WifiPolicy {
    private android.app.enterprise.WifiPolicy mWifiPolicy;

    public WifiPolicy(android.app.enterprise.WifiPolicy wifiPolicy) {
        this.mWifiPolicy = wifiPolicy;
    }

    public boolean activateWifiSsidRestriction(boolean z) {
        return this.mWifiPolicy.activateWifiSsidRestriction(z);
    }

    public boolean addBlockedNetwork(String str) {
        return this.mWifiPolicy.addBlockedNetwork(str);
    }

    public boolean addWifiSsidsToBlackList(List<String> list) {
        return this.mWifiPolicy.addWifiSsidsToBlackList(list);
    }

    public boolean addWifiSsidsToWhiteList(List<String> list) {
        return this.mWifiPolicy.addWifiSsidsToWhiteList(list);
    }

    public boolean addWifiSsidsToWhiteList(List<String> list, boolean z) {
        return this.mWifiPolicy.addWifiSsidsToWhiteList(list, z);
    }

    public boolean allowOpenWifiAp(boolean z) {
        return this.mWifiPolicy.allowOpenWifiAp(z);
    }

    public boolean allowWifiApSettingUserModification(boolean z) {
        return this.mWifiPolicy.allowWifiApSettingUserModification(z);
    }

    public boolean clearWifiSsidsFromBlackList() {
        return this.mWifiPolicy.clearWifiSsidsFromBlackList();
    }

    public boolean clearWifiSsidsFromList() {
        return this.mWifiPolicy.clearWifiSsidsFromList();
    }

    public boolean clearWifiSsidsFromWhiteList() {
        return this.mWifiPolicy.clearWifiSsidsFromWhiteList();
    }

    public boolean getAllowUserPolicyChanges() {
        return this.mWifiPolicy.getAllowUserPolicyChanges();
    }

    public boolean getAllowUserProfiles(boolean z) {
        return this.mWifiPolicy.getAllowUserProfiles(z);
    }

    public boolean getAutomaticConnectionToWifi() {
        return this.mWifiPolicy.getAutomaticConnectionToWifi();
    }

    public List<String> getBlockedNetworks() {
        return this.mWifiPolicy.getBlockedNetworks();
    }

    public int getMinimumRequiredSecurity() {
        return this.mWifiPolicy.getMinimumRequiredSecurity();
    }

    public List<String> getNetworkSSIDList() {
        return this.mWifiPolicy.getNetworkSSIDList();
    }

    public boolean getPasswordHidden() {
        return this.mWifiPolicy.getPasswordHidden();
    }

    public boolean getPromptCredentialsEnabled() {
        return this.mWifiPolicy.getPromptCredentialsEnabled();
    }

    public WifiConfiguration getWifiApSetting() {
        return this.mWifiPolicy.getWifiApSetting();
    }

    public WifiAdminProfile getWifiProfile(String str) {
        try {
            WifiAdminProfile wifiAdminProfileConvertToNew = WifiAdminProfile.convertToNew(this.mWifiPolicy.getWifiProfile(str));
            if (EnterpriseDeviceManager.getAPILevel() < 17 && wifiAdminProfileConvertToNew != null) {
                wifiAdminProfileConvertToNew.staticIpEnabled = this.mWifiPolicy.getNetworkDHCPEnabled(str);
                wifiAdminProfileConvertToNew.staticIp = this.mWifiPolicy.getNetworkIp(str);
                wifiAdminProfileConvertToNew.staticGateway = this.mWifiPolicy.getNetworkGateway(str);
                wifiAdminProfileConvertToNew.staticPrimaryDns = this.mWifiPolicy.getNetworkPrimaryDNS(str);
                wifiAdminProfileConvertToNew.staticSecondaryDns = this.mWifiPolicy.getNetworkSecondaryDNS(str);
                wifiAdminProfileConvertToNew.staticSubnetMask = this.mWifiPolicy.getNetworkSubnetMask(str);
                wifiAdminProfileConvertToNew.proxyState = this.mWifiPolicy.getNetworkProxyEnabled(str) ? 1 : 0;
                wifiAdminProfileConvertToNew.proxyHostname = this.mWifiPolicy.getNetworkProxyHostName(str);
                wifiAdminProfileConvertToNew.proxyPort = this.mWifiPolicy.getNetworkProxyPort(str);
                wifiAdminProfileConvertToNew.proxyBypassList = this.mWifiPolicy.getUrlsFromNetworkProxyBypassList(str);
            }
            return wifiAdminProfileConvertToNew;
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchFieldError e2) {
            throw new NoSuchFieldError(e2.getMessage());
        } catch (NoSuchMethodError e3) {
            throw new NoSuchMethodError(e3.getMessage());
        }
    }

    public List<WifiControlInfo> getWifiSsidsFromBlackLists() {
        return WifiControlInfo.convertToNewList(this.mWifiPolicy.getWifiSsidsFromBlackLists());
    }

    public List<WifiControlInfo> getWifiSsidsFromWhiteLists() {
        return WifiControlInfo.convertToNewList(this.mWifiPolicy.getWifiSsidsFromWhiteLists());
    }

    public boolean isNetworkBlocked(String str, boolean z) {
        return this.mWifiPolicy.isNetworkBlocked(str, z);
    }

    public boolean isOpenWifiApAllowed() {
        return this.mWifiPolicy.isOpenWifiApAllowed();
    }

    public boolean isWifiApSettingUserModificationAllowed() {
        return this.mWifiPolicy.isWifiApSettingUserModificationAllowed();
    }

    public boolean isWifiSsidRestrictionActive() {
        return this.mWifiPolicy.isWifiSsidRestrictionActive();
    }

    public boolean isWifiStateChangeAllowed() {
        return this.mWifiPolicy.isWifiStateChangeAllowed();
    }

    public boolean removeBlockedNetwork(String str) {
        return this.mWifiPolicy.removeBlockedNetwork(str);
    }

    public boolean removeNetworkConfiguration(String str) {
        return this.mWifiPolicy.removeNetworkConfiguration(str);
    }

    public boolean removeWifiSsidsFromBlackList(List<String> list) {
        return this.mWifiPolicy.removeWifiSsidsFromBlackList(list);
    }

    public boolean removeWifiSsidsFromWhiteList(List<String> list) {
        return this.mWifiPolicy.removeWifiSsidsFromWhiteList(list);
    }

    public boolean setAllowUserPolicyChanges(boolean z) {
        return this.mWifiPolicy.setAllowUserPolicyChanges(z);
    }

    public boolean setAllowUserProfiles(boolean z) {
        return this.mWifiPolicy.setAllowUserProfiles(z);
    }

    public boolean setAutomaticConnectionToWifi(boolean z) {
        return this.mWifiPolicy.setAutomaticConnectionToWifi(z);
    }

    public boolean setMinimumRequiredSecurity(int i) {
        return this.mWifiPolicy.setMinimumRequiredSecurity(i);
    }

    public boolean setPasswordHidden(boolean z) {
        return this.mWifiPolicy.setPasswordHidden(z);
    }

    public boolean setPromptCredentialsEnabled(boolean z) {
        return this.mWifiPolicy.setPromptCredentialsEnabled(z);
    }

    public boolean setWifiApSetting(String str, String str2, String str3) {
        return this.mWifiPolicy.setWifiApSetting(str, str2, str3);
    }

    public boolean setWifiProfile(WifiAdminProfile wifiAdminProfile) {
        try {
            boolean wifiProfile = this.mWifiPolicy.setWifiProfile(WifiAdminProfile.convertToOld(wifiAdminProfile));
            if (EnterpriseDeviceManager.getAPILevel() >= 17 || !wifiProfile || wifiAdminProfile == null) {
                return wifiProfile;
            }
            if (wifiAdminProfile.proxyBypassList != null) {
                this.mWifiPolicy.clearUrlsFromNetworkProxyBypassList(wifiAdminProfile.ssid);
                Iterator<String> it = wifiAdminProfile.proxyBypassList.iterator();
                while (it.hasNext()) {
                    wifiProfile |= this.mWifiPolicy.addUrlForNetworkProxyBypass(wifiAdminProfile.ssid, it.next());
                }
            }
            boolean networkDHCPEnabled = wifiProfile | this.mWifiPolicy.setNetworkDHCPEnabled(wifiAdminProfile.ssid, wifiAdminProfile.staticIpEnabled) | this.mWifiPolicy.setNetworkIp(wifiAdminProfile.ssid, wifiAdminProfile.staticIp) | this.mWifiPolicy.setNetworkGateway(wifiAdminProfile.ssid, wifiAdminProfile.staticGateway) | this.mWifiPolicy.setNetworkPrimaryDNS(wifiAdminProfile.ssid, wifiAdminProfile.staticPrimaryDns) | this.mWifiPolicy.setNetworkSecondaryDNS(wifiAdminProfile.ssid, wifiAdminProfile.staticSecondaryDns) | this.mWifiPolicy.setNetworkSubnetMask(wifiAdminProfile.ssid, wifiAdminProfile.staticSubnetMask);
            android.app.enterprise.WifiPolicy wifiPolicy = this.mWifiPolicy;
            String str = wifiAdminProfile.ssid;
            boolean z = true;
            if (wifiAdminProfile.proxyState != 1) {
                z = false;
            }
            return networkDHCPEnabled | wifiPolicy.setNetworkProxyEnabled(str, z) | this.mWifiPolicy.setNetworkProxyHostName(wifiAdminProfile.ssid, wifiAdminProfile.proxyHostname) | this.mWifiPolicy.setNetworkProxyPort(wifiAdminProfile.ssid, wifiAdminProfile.proxyPort);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (NoSuchFieldError e2) {
            throw new NoSuchFieldError(e2.getMessage());
        } catch (NoSuchMethodError e3) {
            throw new NoSuchMethodError(e3.getMessage());
        }
    }

    public boolean setWifiStateChangeAllowed(boolean z) {
        return this.mWifiPolicy.setWifiStateChangeAllowed(z);
    }
}

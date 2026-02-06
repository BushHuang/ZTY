package com.samsung.android.knox;

import android.app.Application;
import android.app.enterprise.EnterpriseDeviceManager;
import android.app.enterprise.FirewallPolicy;
import android.app.enterprise.MiscPolicy;
import android.app.enterprise.SecurityPolicy;
import android.app.enterprise.devicesettings.DeviceSettingsPolicy;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import com.samsung.android.knox.accounts.DeviceAccountPolicy;
import com.samsung.android.knox.accounts.EmailAccountPolicy;
import com.samsung.android.knox.accounts.EmailPolicy;
import com.samsung.android.knox.accounts.ExchangeAccountPolicy;
import com.samsung.android.knox.accounts.LDAPAccountPolicy;
import com.samsung.android.knox.application.ApplicationPolicy;
import com.samsung.android.knox.bluetooth.BluetoothPolicy;
import com.samsung.android.knox.bluetooth.BluetoothSecureModePolicy;
import com.samsung.android.knox.browser.BrowserPolicy;
import com.samsung.android.knox.container.BasePasswordPolicy;
import com.samsung.android.knox.datetime.DateTimePolicy;
import com.samsung.android.knox.deviceinfo.DeviceInventory;
import com.samsung.android.knox.devicesecurity.DeviceSecurityPolicy;
import com.samsung.android.knox.devicesecurity.PasswordPolicy;
import com.samsung.android.knox.display.Font;
import com.samsung.android.knox.keystore.CertificateProvisioning;
import com.samsung.android.knox.kiosk.KioskMode;
import com.samsung.android.knox.license.EnterpriseLicenseManager;
import com.samsung.android.knox.location.Geofencing;
import com.samsung.android.knox.location.LocationPolicy;
import com.samsung.android.knox.lockscreen.BootBanner;
import com.samsung.android.knox.lockscreen.LockscreenOverlay;
import com.samsung.android.knox.multiuser.MultiUserManager;
import com.samsung.android.knox.net.GlobalProxy;
import com.samsung.android.knox.net.apn.ApnSettingsPolicy;
import com.samsung.android.knox.net.firewall.Firewall;
import com.samsung.android.knox.net.vpn.VpnPolicy;
import com.samsung.android.knox.net.wifi.WifiPolicy;
import com.samsung.android.knox.nfc.NfcPolicy;
import com.samsung.android.knox.remotecontrol.RemoteInjection;
import com.samsung.android.knox.restriction.PhoneRestrictionPolicy;
import com.samsung.android.knox.restriction.RestrictionPolicy;
import com.samsung.android.knox.restriction.RoamingPolicy;
import com.samsung.android.knox.restriction.SPDControlPolicy;
import com.sec.enterprise.knox.AdvancedRestrictionPolicy;
import com.sec.enterprise.knox.EnterpriseKnoxManager;

public class EnterpriseDeviceManager {
    private static final int API_LEVEL_NOT_DEFINED = 0;
    private static final boolean DEBUG = true;
    private static final int KNOX_1_0 = 6;
    private static final int KNOX_1_0_1 = 7;
    private static final int KNOX_1_0_2 = 8;
    private static final int KNOX_1_1 = 9;
    private static final int KNOX_1_2 = 10;
    public static final int KNOX_2_0 = 11;
    public static final int KNOX_2_1 = 12;
    public static final int KNOX_2_2 = 13;
    public static final int KNOX_2_3 = 14;
    public static final int KNOX_2_4 = 15;
    public static final int KNOX_2_4_1 = 16;
    public static final int KNOX_2_5 = 17;
    public static final int KNOX_2_5_1 = 18;
    public static final int KNOX_2_6 = 19;
    public static final int KNOX_2_7 = 20;
    private static final int MDM_2_0 = 2;
    private static final int MDM_2_1 = 3;
    private static final int MDM_2_2 = 4;
    private static final int MDM_3_0 = 5;
    private static final String TAG = "EnterpriseDeviceManager-SupportLib";
    private static int mAPILevel;
    private static Context mContext;
    private static android.app.enterprise.EnterpriseDeviceManager mEdm;
    private static EnterpriseDeviceManager mInstance;
    private volatile ApnSettingsPolicy mApnSettingsPolicy;
    private volatile ApplicationPolicy mApplicationPolicy;
    private volatile BasePasswordPolicy mBasePasswordPolicy;
    private volatile BluetoothPolicy mBluetoothPolicy;
    private volatile BluetoothSecureModePolicy mBluetoothSecureModePolicy;
    private volatile BootBanner mBootBanner;
    private volatile BrowserPolicy mBrowserPolicy;
    private volatile CertificateProvisioning mCertificateProvisioning;
    private volatile DateTimePolicy mDateTimePolicy;
    private volatile DeviceAccountPolicy mDeviceAccountPolicy;
    private volatile DeviceInventory mDeviceInventory;
    private volatile DeviceSecurityPolicy mDeviceSecurityPolicy;
    private volatile EmailAccountPolicy mEmailAccountPolicy;
    private volatile EmailPolicy mEmailPolicy;
    private volatile EnterpriseLicenseManager mEnterpriseLicenseManager;
    private volatile ExchangeAccountPolicy mExchangeAccountPolicy;
    private volatile Firewall mFirewall;
    private volatile Font mFont;
    private volatile Geofencing mGeofencing;
    private volatile GlobalProxy mGlobalProxy;
    private volatile KioskMode mKioskMode;
    private volatile LDAPAccountPolicy mLDAPAccountPolicy;
    private volatile LocationPolicy mLocationPolicy;
    private volatile LockscreenOverlay mLockscreenOverlay;
    private volatile MultiUserManager mMultiUserManager;
    private volatile NfcPolicy mNfcPolicy;
    private volatile PasswordPolicy mPasswordPolicy;
    private volatile PhoneRestrictionPolicy mPhoneRestrictionPolicy;
    private volatile RemoteInjection mRemoteInjection;
    private volatile RestrictionPolicy mRestrictionPolicy;
    private volatile RoamingPolicy mRoamingPolicy;
    private volatile SPDControlPolicy mSPDControlPolicy;
    private volatile VpnPolicy mVpnPolicy;
    private volatile WifiPolicy mWifiPolicy;

    static class AnonymousClass1 {
        static final int[] $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion;
        static final int[] $SwitchMap$com$sec$enterprise$knox$EnterpriseKnoxManager$EnterpriseKnoxSdkVersion;

        static {
            int[] iArr = new int[EnterpriseDeviceManager.EnterpriseSdkVersion.values().length];
            $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion = iArr;
            try {
                iArr[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_2_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_2_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_4.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_4_0_1.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_4_1.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_1.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_2.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_3.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_4.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_4_1.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_5.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_5_1.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_6.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[EnterpriseDeviceManager.EnterpriseSdkVersion.ENTERPRISE_SDK_VERSION_5_7.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            int[] iArr2 = new int[EnterpriseKnoxManager.EnterpriseKnoxSdkVersion.values().length];
            $SwitchMap$com$sec$enterprise$knox$EnterpriseKnoxManager$EnterpriseKnoxSdkVersion = iArr2;
            try {
                iArr2[EnterpriseKnoxManager.EnterpriseKnoxSdkVersion.KNOX_ENTERPRISE_SDK_VERSION_1_0_1.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sec$enterprise$knox$EnterpriseKnoxManager$EnterpriseKnoxSdkVersion[EnterpriseKnoxManager.EnterpriseKnoxSdkVersion.KNOX_ENTERPRISE_SDK_VERSION_1_0_2.ordinal()] = 2;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sec$enterprise$knox$EnterpriseKnoxManager$EnterpriseKnoxSdkVersion[EnterpriseKnoxManager.EnterpriseKnoxSdkVersion.KNOX_ENTERPRISE_SDK_VERSION_1_1_0.ordinal()] = 3;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sec$enterprise$knox$EnterpriseKnoxManager$EnterpriseKnoxSdkVersion[EnterpriseKnoxManager.EnterpriseKnoxSdkVersion.KNOX_ENTERPRISE_SDK_VERSION_1_2_0.ordinal()] = 4;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    private EnterpriseDeviceManager(android.app.enterprise.EnterpriseDeviceManager enterpriseDeviceManager) {
        mEdm = enterpriseDeviceManager;
    }

    public static int getAPILevel() {
        int i = mAPILevel;
        if (i != 0) {
            return i;
        }
        android.app.enterprise.EnterpriseDeviceManager enterpriseDeviceManager = mEdm;
        if (enterpriseDeviceManager == null) {
            Context contextFromCurrentApplication = getContextFromCurrentApplication();
            enterpriseDeviceManager = contextFromCurrentApplication != null ? (android.app.enterprise.EnterpriseDeviceManager) contextFromCurrentApplication.getSystemService("enterprise_policy") : null;
        }
        if (enterpriseDeviceManager != null) {
            switch (AnonymousClass1.$SwitchMap$android$app$enterprise$EnterpriseDeviceManager$EnterpriseSdkVersion[enterpriseDeviceManager.getEnterpriseSdkVer().ordinal()]) {
                case 1:
                    mAPILevel = 2;
                    break;
                case 2:
                    mAPILevel = 3;
                    break;
                case 3:
                    mAPILevel = 4;
                    break;
                case 4:
                    mAPILevel = 5;
                    break;
                case 5:
                    mAPILevel = 6;
                    break;
                case 6:
                    com.sec.enterprise.knox.EnterpriseKnoxManager enterpriseKnoxManager = com.sec.enterprise.knox.EnterpriseKnoxManager.getInstance();
                    if (enterpriseKnoxManager != null) {
                        int i2 = AnonymousClass1.$SwitchMap$com$sec$enterprise$knox$EnterpriseKnoxManager$EnterpriseKnoxSdkVersion[enterpriseKnoxManager.getVersion().ordinal()];
                        if (i2 == 1) {
                            mAPILevel = 7;
                            break;
                        } else if (i2 == 2) {
                            mAPILevel = 8;
                            break;
                        }
                    }
                    break;
                case 7:
                    com.sec.enterprise.knox.EnterpriseKnoxManager enterpriseKnoxManager2 = com.sec.enterprise.knox.EnterpriseKnoxManager.getInstance();
                    if (enterpriseKnoxManager2 != null) {
                        int i3 = AnonymousClass1.$SwitchMap$com$sec$enterprise$knox$EnterpriseKnoxManager$EnterpriseKnoxSdkVersion[enterpriseKnoxManager2.getVersion().ordinal()];
                        if (i3 == 3) {
                            mAPILevel = 9;
                            break;
                        } else if (i3 == 4) {
                            mAPILevel = 10;
                            break;
                        }
                    }
                    break;
                case 8:
                    mAPILevel = 11;
                    break;
                case 9:
                    mAPILevel = 12;
                    break;
                case 10:
                    mAPILevel = 13;
                    break;
                case 11:
                    mAPILevel = 14;
                    break;
                case 12:
                    mAPILevel = 15;
                    break;
                case 13:
                    mAPILevel = 16;
                    break;
                case 14:
                    mAPILevel = 17;
                    break;
                case 15:
                    mAPILevel = 18;
                    break;
                case 16:
                    mAPILevel = 19;
                    break;
                case 17:
                    mAPILevel = 20;
                    break;
            }
        }
        return mAPILevel;
    }

    private static Context getContextFromCurrentApplication() {
        try {
            return ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0])).getApplicationContext();
        } catch (Exception unused) {
            Log.w("EnterpriseDeviceManager-SupportLib", "Could not call ActivityThread.currentApplication()");
            return null;
        }
    }

    public static EnterpriseDeviceManager getInstance(Context context) {
        android.app.enterprise.EnterpriseDeviceManager enterpriseDeviceManager;
        if (context == null) {
            return null;
        }
        EnterpriseDeviceManager enterpriseDeviceManager2 = mInstance;
        if (enterpriseDeviceManager2 == null) {
            synchronized (EnterpriseDeviceManager.class) {
                enterpriseDeviceManager2 = mInstance;
                if (enterpriseDeviceManager2 == null && (enterpriseDeviceManager = (android.app.enterprise.EnterpriseDeviceManager) context.getSystemService("enterprise_policy")) != null) {
                    enterpriseDeviceManager2 = new EnterpriseDeviceManager(enterpriseDeviceManager);
                    mInstance = enterpriseDeviceManager2;
                    mContext = context;
                }
            }
        }
        return enterpriseDeviceManager2;
    }

    public boolean getAdminRemovable() {
        return mEdm.getAdminRemovable();
    }

    public boolean getAdminRemovable(String str) {
        return mEdm.getAdminRemovable(str);
    }

    public ApnSettingsPolicy getApnSettingsPolicy() {
        android.app.enterprise.ApnSettingsPolicy apnSettingsPolicy;
        ApnSettingsPolicy apnSettingsPolicy2 = this.mApnSettingsPolicy;
        if (apnSettingsPolicy2 == null) {
            synchronized (this) {
                apnSettingsPolicy2 = this.mApnSettingsPolicy;
                if (apnSettingsPolicy2 == null && (apnSettingsPolicy = mEdm.getApnSettingsPolicy()) != null) {
                    apnSettingsPolicy2 = new ApnSettingsPolicy(apnSettingsPolicy);
                    this.mApnSettingsPolicy = apnSettingsPolicy2;
                }
            }
        }
        return apnSettingsPolicy2;
    }

    public ApplicationPolicy getApplicationPolicy() {
        ApplicationPolicy applicationPolicy = this.mApplicationPolicy;
        com.sec.enterprise.knox.EnterpriseKnoxManager enterpriseKnoxManager = com.sec.enterprise.knox.EnterpriseKnoxManager.getInstance();
        if (applicationPolicy == null) {
            synchronized (this) {
                applicationPolicy = this.mApplicationPolicy;
                if (applicationPolicy == null) {
                    android.app.enterprise.ApplicationPolicy applicationPolicy2 = mEdm.getApplicationPolicy();
                    if (getAPILevel() >= 11) {
                        AdvancedRestrictionPolicy advancedRestrictionPolicy = enterpriseKnoxManager.getAdvancedRestrictionPolicy(mContext);
                        if (applicationPolicy2 != null && advancedRestrictionPolicy != null) {
                            applicationPolicy = new ApplicationPolicy(applicationPolicy2, advancedRestrictionPolicy);
                            this.mApplicationPolicy = applicationPolicy;
                        }
                    } else if (applicationPolicy2 != null) {
                        applicationPolicy = new ApplicationPolicy(applicationPolicy2, null);
                        this.mApplicationPolicy = applicationPolicy;
                    }
                }
            }
        }
        return applicationPolicy;
    }

    public BluetoothPolicy getBluetoothPolicy() {
        BluetoothPolicy bluetoothPolicy = this.mBluetoothPolicy;
        if (bluetoothPolicy == null) {
            synchronized (this) {
                bluetoothPolicy = this.mBluetoothPolicy;
                if (bluetoothPolicy == null) {
                    android.app.enterprise.BluetoothPolicy bluetoothPolicy2 = mEdm.getBluetoothPolicy();
                    android.app.enterprise.RestrictionPolicy restrictionPolicy = mEdm.getRestrictionPolicy();
                    if (bluetoothPolicy2 != null && restrictionPolicy != null) {
                        bluetoothPolicy = new BluetoothPolicy(bluetoothPolicy2, restrictionPolicy);
                        this.mBluetoothPolicy = bluetoothPolicy;
                    }
                }
            }
        }
        return bluetoothPolicy;
    }

    public BluetoothSecureModePolicy getBluetoothSecureModePolicy() {
        android.app.enterprise.BluetoothSecureModePolicy bluetoothSecureModePolicy;
        BluetoothSecureModePolicy bluetoothSecureModePolicy2 = this.mBluetoothSecureModePolicy;
        if (bluetoothSecureModePolicy2 == null) {
            synchronized (this) {
                bluetoothSecureModePolicy2 = this.mBluetoothSecureModePolicy;
                if (bluetoothSecureModePolicy2 == null && (bluetoothSecureModePolicy = mEdm.getBluetoothSecureModePolicy()) != null) {
                    bluetoothSecureModePolicy2 = new BluetoothSecureModePolicy(bluetoothSecureModePolicy);
                    this.mBluetoothSecureModePolicy = bluetoothSecureModePolicy2;
                }
            }
        }
        return bluetoothSecureModePolicy2;
    }

    public BootBanner getBootBanner() {
        SecurityPolicy securityPolicy;
        BootBanner bootBanner = this.mBootBanner;
        if (bootBanner == null) {
            synchronized (this) {
                bootBanner = this.mBootBanner;
                if (bootBanner == null && (securityPolicy = mEdm.getSecurityPolicy()) != null) {
                    bootBanner = new BootBanner(securityPolicy);
                    this.mBootBanner = bootBanner;
                }
            }
        }
        return bootBanner;
    }

    public BrowserPolicy getBrowserPolicy() {
        BrowserPolicy browserPolicy = this.mBrowserPolicy;
        if (browserPolicy == null) {
            synchronized (this) {
                browserPolicy = this.mBrowserPolicy;
                if (browserPolicy == null) {
                    android.app.enterprise.BrowserPolicy browserPolicy2 = mEdm.getBrowserPolicy();
                    MiscPolicy miscPolicy = mEdm.getMiscPolicy();
                    if (browserPolicy2 != null && miscPolicy != null) {
                        browserPolicy = new BrowserPolicy(browserPolicy2, miscPolicy);
                        this.mBrowserPolicy = browserPolicy;
                    }
                }
            }
        }
        return browserPolicy;
    }

    public CertificateProvisioning getCertificateProvisioning() {
        SecurityPolicy securityPolicy;
        CertificateProvisioning certificateProvisioning = this.mCertificateProvisioning;
        if (certificateProvisioning == null) {
            synchronized (this) {
                certificateProvisioning = this.mCertificateProvisioning;
                if (certificateProvisioning == null && (securityPolicy = mEdm.getSecurityPolicy()) != null) {
                    certificateProvisioning = new CertificateProvisioning(securityPolicy);
                    this.mCertificateProvisioning = certificateProvisioning;
                }
            }
        }
        return certificateProvisioning;
    }

    public DateTimePolicy getDateTimePolicy() {
        android.app.enterprise.DateTimePolicy dateTimePolicy;
        DateTimePolicy dateTimePolicy2 = this.mDateTimePolicy;
        if (dateTimePolicy2 == null) {
            synchronized (this) {
                dateTimePolicy2 = this.mDateTimePolicy;
                if (dateTimePolicy2 == null && (dateTimePolicy = mEdm.getDateTimePolicy()) != null) {
                    dateTimePolicy2 = new DateTimePolicy(mContext, dateTimePolicy);
                    this.mDateTimePolicy = dateTimePolicy2;
                }
            }
        }
        return dateTimePolicy2;
    }

    public DeviceAccountPolicy getDeviceAccountPolicy() {
        DeviceAccountPolicy deviceAccountPolicy = this.mDeviceAccountPolicy;
        if (deviceAccountPolicy == null) {
            synchronized (this) {
                deviceAccountPolicy = this.mDeviceAccountPolicy;
                if (deviceAccountPolicy == null) {
                    android.app.enterprise.DeviceAccountPolicy deviceAccountPolicy2 = mEdm.getDeviceAccountPolicy();
                    SecurityPolicy securityPolicy = mEdm.getSecurityPolicy();
                    if (deviceAccountPolicy2 != null && securityPolicy != null) {
                        deviceAccountPolicy = new DeviceAccountPolicy(deviceAccountPolicy2, securityPolicy);
                        this.mDeviceAccountPolicy = deviceAccountPolicy;
                    }
                }
            }
        }
        return deviceAccountPolicy;
    }

    public DeviceInventory getDeviceInventory() {
        DeviceInventory deviceInventory = this.mDeviceInventory;
        if (deviceInventory == null) {
            synchronized (this) {
                deviceInventory = this.mDeviceInventory;
                if (deviceInventory == null) {
                    android.app.enterprise.DeviceInventory deviceInventory2 = mEdm.getDeviceInventory();
                    MiscPolicy miscPolicy = mEdm.getMiscPolicy();
                    if (deviceInventory2 != null && miscPolicy != null) {
                        deviceInventory = new DeviceInventory(deviceInventory2, miscPolicy);
                        this.mDeviceInventory = deviceInventory;
                    }
                }
            }
        }
        return deviceInventory;
    }

    public DeviceSecurityPolicy getDeviceSecurityPolicy() {
        DeviceSecurityPolicy deviceSecurityPolicy = this.mDeviceSecurityPolicy;
        if (deviceSecurityPolicy == null) {
            synchronized (this) {
                deviceSecurityPolicy = this.mDeviceSecurityPolicy;
                if (deviceSecurityPolicy == null) {
                    MiscPolicy miscPolicy = mEdm.getMiscPolicy();
                    SecurityPolicy securityPolicy = mEdm.getSecurityPolicy();
                    if (miscPolicy != null && securityPolicy != null) {
                        deviceSecurityPolicy = new DeviceSecurityPolicy(miscPolicy, securityPolicy);
                        this.mDeviceSecurityPolicy = deviceSecurityPolicy;
                    }
                }
            }
        }
        return deviceSecurityPolicy;
    }

    public EmailAccountPolicy getEmailAccountPolicy() {
        android.app.enterprise.EmailAccountPolicy emailAccountPolicy;
        EmailAccountPolicy emailAccountPolicy2 = this.mEmailAccountPolicy;
        if (emailAccountPolicy2 == null) {
            synchronized (this) {
                emailAccountPolicy2 = this.mEmailAccountPolicy;
                if (emailAccountPolicy2 == null && (emailAccountPolicy = mEdm.getEmailAccountPolicy()) != null) {
                    emailAccountPolicy2 = new EmailAccountPolicy(emailAccountPolicy);
                    this.mEmailAccountPolicy = emailAccountPolicy2;
                }
            }
        }
        return emailAccountPolicy2;
    }

    public EmailPolicy getEmailPolicy() {
        android.app.enterprise.EmailPolicy emailPolicy;
        EmailPolicy emailPolicy2 = this.mEmailPolicy;
        if (emailPolicy2 == null) {
            synchronized (this) {
                emailPolicy2 = this.mEmailPolicy;
                if (emailPolicy2 == null && (emailPolicy = mEdm.getEmailPolicy()) != null) {
                    emailPolicy2 = new EmailPolicy(emailPolicy);
                    this.mEmailPolicy = emailPolicy2;
                }
            }
        }
        return emailPolicy2;
    }

    public ExchangeAccountPolicy getExchangeAccountPolicy() {
        android.app.enterprise.ExchangeAccountPolicy exchangeAccountPolicy;
        ExchangeAccountPolicy exchangeAccountPolicy2 = this.mExchangeAccountPolicy;
        if (exchangeAccountPolicy2 == null) {
            synchronized (this) {
                exchangeAccountPolicy2 = this.mExchangeAccountPolicy;
                if (exchangeAccountPolicy2 == null && (exchangeAccountPolicy = mEdm.getExchangeAccountPolicy()) != null) {
                    exchangeAccountPolicy2 = new ExchangeAccountPolicy(exchangeAccountPolicy);
                    this.mExchangeAccountPolicy = exchangeAccountPolicy2;
                }
            }
        }
        return exchangeAccountPolicy2;
    }

    public Firewall getFirewall() {
        Firewall firewall = this.mFirewall;
        if (firewall == null) {
            synchronized (this) {
                firewall = this.mFirewall;
                if (firewall == null) {
                    if (getAPILevel() >= 17) {
                        com.sec.enterprise.firewall.Firewall firewall2 = mEdm.getFirewall();
                        if (firewall2 != null) {
                            firewall = new Firewall(firewall2);
                            this.mFirewall = firewall;
                        }
                    } else {
                        FirewallPolicy firewallPolicy = mEdm.getFirewallPolicy();
                        if (firewallPolicy != null) {
                            firewall = new Firewall(firewallPolicy);
                            this.mFirewall = firewall;
                        }
                    }
                }
            }
        }
        return firewall;
    }

    public Font getFont() {
        MiscPolicy miscPolicy;
        Font font = this.mFont;
        if (font == null) {
            synchronized (this) {
                font = this.mFont;
                if (font == null && (miscPolicy = mEdm.getMiscPolicy()) != null) {
                    font = new Font(miscPolicy);
                    this.mFont = font;
                }
            }
        }
        return font;
    }

    public Geofencing getGeofencing() {
        android.app.enterprise.geofencing.Geofencing geofencing;
        Geofencing geofencing2 = this.mGeofencing;
        if (geofencing2 == null) {
            synchronized (this) {
                geofencing2 = this.mGeofencing;
                if (geofencing2 == null && (geofencing = android.app.enterprise.geofencing.Geofencing.getInstance(mContext)) != null) {
                    geofencing2 = new Geofencing(geofencing);
                    this.mGeofencing = geofencing2;
                }
            }
        }
        return geofencing2;
    }

    public GlobalProxy getGlobalProxy() {
        DeviceSettingsPolicy deviceSettingsPolicy;
        GlobalProxy globalProxy = this.mGlobalProxy;
        if (globalProxy == null) {
            synchronized (this) {
                globalProxy = this.mGlobalProxy;
                if (globalProxy == null && (deviceSettingsPolicy = DeviceSettingsPolicy.getInstance(mContext)) != null) {
                    globalProxy = new GlobalProxy(deviceSettingsPolicy);
                    this.mGlobalProxy = globalProxy;
                }
            }
        }
        return globalProxy;
    }

    public KioskMode getKioskMode() {
        android.app.enterprise.kioskmode.KioskMode kioskMode;
        KioskMode kioskMode2 = this.mKioskMode;
        if (kioskMode2 == null) {
            synchronized (this) {
                kioskMode2 = this.mKioskMode;
                if (kioskMode2 == null && (kioskMode = android.app.enterprise.kioskmode.KioskMode.getInstance(mContext)) != null) {
                    kioskMode2 = new KioskMode(kioskMode);
                    this.mKioskMode = kioskMode2;
                }
            }
        }
        return kioskMode2;
    }

    public LDAPAccountPolicy getLDAPAccountPolicy() {
        android.app.enterprise.LDAPAccountPolicy lDAPAccountPolicy;
        LDAPAccountPolicy lDAPAccountPolicy2 = this.mLDAPAccountPolicy;
        if (lDAPAccountPolicy2 == null) {
            synchronized (this) {
                lDAPAccountPolicy2 = this.mLDAPAccountPolicy;
                if (lDAPAccountPolicy2 == null && (lDAPAccountPolicy = mEdm.getLDAPAccountPolicy()) != null) {
                    lDAPAccountPolicy2 = new LDAPAccountPolicy(lDAPAccountPolicy);
                    this.mLDAPAccountPolicy = lDAPAccountPolicy2;
                }
            }
        }
        return lDAPAccountPolicy2;
    }

    public LocationPolicy getLocationPolicy() {
        android.app.enterprise.LocationPolicy locationPolicy;
        LocationPolicy locationPolicy2 = this.mLocationPolicy;
        if (locationPolicy2 == null) {
            synchronized (this) {
                locationPolicy2 = this.mLocationPolicy;
                if (locationPolicy2 == null && (locationPolicy = mEdm.getLocationPolicy()) != null) {
                    locationPolicy2 = new LocationPolicy(locationPolicy);
                    this.mLocationPolicy = locationPolicy2;
                }
            }
        }
        return locationPolicy2;
    }

    public LockscreenOverlay getLockscreenOverlay() {
        LockscreenOverlay lockscreenOverlay = this.mLockscreenOverlay;
        if (lockscreenOverlay == null) {
            synchronized (this) {
                lockscreenOverlay = this.mLockscreenOverlay;
                if (lockscreenOverlay == null) {
                    android.app.enterprise.lso.LockscreenOverlay lockscreenOverlay2 = android.app.enterprise.lso.LockscreenOverlay.getInstance(mContext);
                    MiscPolicy miscPolicy = mEdm.getMiscPolicy();
                    if (lockscreenOverlay2 != null && miscPolicy != null) {
                        lockscreenOverlay = new LockscreenOverlay(lockscreenOverlay2, miscPolicy);
                        this.mLockscreenOverlay = lockscreenOverlay;
                    }
                }
            }
        }
        return lockscreenOverlay;
    }

    public MultiUserManager getMultiUserManager() {
        android.app.enterprise.multiuser.MultiUserManager multiUserManager;
        MultiUserManager multiUserManager2 = this.mMultiUserManager;
        if (multiUserManager2 == null) {
            synchronized (this) {
                multiUserManager2 = this.mMultiUserManager;
                if (multiUserManager2 == null && (multiUserManager = android.app.enterprise.multiuser.MultiUserManager.getInstance(mContext)) != null) {
                    multiUserManager2 = new MultiUserManager(multiUserManager);
                    this.mMultiUserManager = multiUserManager2;
                }
            }
        }
        return multiUserManager2;
    }

    public NfcPolicy getNfcPolicy() {
        DeviceSettingsPolicy deviceSettingsPolicy;
        NfcPolicy nfcPolicy = this.mNfcPolicy;
        if (nfcPolicy == null) {
            synchronized (this) {
                nfcPolicy = this.mNfcPolicy;
                if (nfcPolicy == null && (deviceSettingsPolicy = DeviceSettingsPolicy.getInstance(mContext)) != null) {
                    nfcPolicy = new NfcPolicy(deviceSettingsPolicy);
                    this.mNfcPolicy = nfcPolicy;
                }
            }
        }
        return nfcPolicy;
    }

    public PasswordPolicy getPasswordPolicy() {
        android.app.enterprise.PasswordPolicy passwordPolicy;
        PasswordPolicy passwordPolicy2 = this.mPasswordPolicy;
        if (passwordPolicy2 == null) {
            synchronized (this) {
                passwordPolicy2 = this.mPasswordPolicy;
                if (passwordPolicy2 == null && (passwordPolicy = mEdm.getPasswordPolicy()) != null) {
                    passwordPolicy2 = new PasswordPolicy(passwordPolicy);
                    this.mPasswordPolicy = passwordPolicy2;
                }
            }
        }
        return passwordPolicy2;
    }

    public PhoneRestrictionPolicy getPhoneRestrictionPolicy() {
        android.app.enterprise.PhoneRestrictionPolicy phoneRestrictionPolicy;
        PhoneRestrictionPolicy phoneRestrictionPolicy2 = this.mPhoneRestrictionPolicy;
        if (phoneRestrictionPolicy2 == null) {
            synchronized (this) {
                phoneRestrictionPolicy2 = this.mPhoneRestrictionPolicy;
                if (phoneRestrictionPolicy2 == null && (phoneRestrictionPolicy = mEdm.getPhoneRestrictionPolicy()) != null) {
                    phoneRestrictionPolicy2 = new PhoneRestrictionPolicy(phoneRestrictionPolicy);
                    this.mPhoneRestrictionPolicy = phoneRestrictionPolicy2;
                }
            }
        }
        return phoneRestrictionPolicy2;
    }

    public RemoteInjection getRemoteInjection() {
        android.app.enterprise.remotecontrol.RemoteInjection remoteInjection;
        RemoteInjection remoteInjection2 = this.mRemoteInjection;
        if (remoteInjection2 == null) {
            synchronized (this) {
                remoteInjection2 = this.mRemoteInjection;
                if (remoteInjection2 == null && (remoteInjection = android.app.enterprise.remotecontrol.RemoteInjection.getInstance()) != null) {
                    remoteInjection2 = new RemoteInjection(remoteInjection);
                    this.mRemoteInjection = remoteInjection2;
                }
            }
        }
        return remoteInjection2;
    }

    public RestrictionPolicy getRestrictionPolicy() {
        android.app.enterprise.RestrictionPolicy restrictionPolicy;
        RestrictionPolicy restrictionPolicy2 = this.mRestrictionPolicy;
        if (restrictionPolicy2 == null) {
            synchronized (this) {
                restrictionPolicy2 = this.mRestrictionPolicy;
                if (restrictionPolicy2 == null && (restrictionPolicy = mEdm.getRestrictionPolicy()) != null) {
                    restrictionPolicy2 = new RestrictionPolicy(restrictionPolicy);
                    this.mRestrictionPolicy = restrictionPolicy2;
                }
            }
        }
        return restrictionPolicy2;
    }

    public RoamingPolicy getRoamingPolicy() {
        android.app.enterprise.RoamingPolicy roamingPolicy;
        RoamingPolicy roamingPolicy2 = this.mRoamingPolicy;
        if (roamingPolicy2 == null) {
            synchronized (this) {
                roamingPolicy2 = this.mRoamingPolicy;
                if (roamingPolicy2 == null && (roamingPolicy = mEdm.getRoamingPolicy()) != null) {
                    roamingPolicy2 = new RoamingPolicy(roamingPolicy);
                    this.mRoamingPolicy = roamingPolicy2;
                }
            }
        }
        return roamingPolicy2;
    }

    public SPDControlPolicy getSPDControlPolicy() {
        SPDControlPolicy sPDControlPolicy = this.mSPDControlPolicy;
        if (sPDControlPolicy == null) {
            synchronized (this) {
                sPDControlPolicy = this.mSPDControlPolicy;
                if (sPDControlPolicy == null) {
                    try {
                        com.sec.enterprise.spd.SPDControlPolicy sPDControlPolicy2 = mEdm.getSPDControlPolicy();
                        if (sPDControlPolicy2 != null) {
                            sPDControlPolicy = new SPDControlPolicy(sPDControlPolicy2);
                            this.mSPDControlPolicy = sPDControlPolicy;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseDeviceManager.class, "getSPDControlPolicy", null, 19));
                    }
                }
            }
        }
        return sPDControlPolicy;
    }

    public VpnPolicy getVpnPolicy() {
        android.app.enterprise.VpnPolicy vpnPolicy;
        VpnPolicy vpnPolicy2 = this.mVpnPolicy;
        if (vpnPolicy2 == null) {
            synchronized (this) {
                vpnPolicy2 = this.mVpnPolicy;
                if (vpnPolicy2 == null && (vpnPolicy = mEdm.getVpnPolicy()) != null) {
                    vpnPolicy2 = new VpnPolicy(vpnPolicy);
                    this.mVpnPolicy = vpnPolicy2;
                }
            }
        }
        return vpnPolicy2;
    }

    public WifiPolicy getWifiPolicy() {
        android.app.enterprise.WifiPolicy wifiPolicy;
        WifiPolicy wifiPolicy2 = this.mWifiPolicy;
        if (wifiPolicy2 == null) {
            synchronized (this) {
                wifiPolicy2 = this.mWifiPolicy;
                if (wifiPolicy2 == null && (wifiPolicy = mEdm.getWifiPolicy()) != null) {
                    wifiPolicy2 = new WifiPolicy(wifiPolicy);
                    this.mWifiPolicy = wifiPolicy2;
                }
            }
        }
        return wifiPolicy2;
    }

    public boolean isAdminActive(ComponentName componentName) {
        return mEdm.isAdminActive(componentName);
    }

    public boolean setAdminRemovable(boolean z) {
        return mEdm.setAdminRemovable(z);
    }

    public boolean setAdminRemovable(boolean z, String str) {
        return mEdm.setAdminRemovable(z, str);
    }
}

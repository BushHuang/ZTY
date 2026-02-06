package com.samsung.android.knox.container;

import android.app.enterprise.FirewallPolicy;
import android.app.enterprise.MiscPolicy;
import android.app.enterprise.SecurityPolicy;
import android.content.Context;
import com.samsung.android.knox.SupportLibUtils;
import com.samsung.android.knox.accounts.DeviceAccountPolicy;
import com.samsung.android.knox.accounts.EmailAccountPolicy;
import com.samsung.android.knox.accounts.EmailPolicy;
import com.samsung.android.knox.accounts.ExchangeAccountPolicy;
import com.samsung.android.knox.accounts.LDAPAccountPolicy;
import com.samsung.android.knox.application.ApplicationPolicy;
import com.samsung.android.knox.browser.BrowserPolicy;
import com.samsung.android.knox.datetime.DateTimePolicy;
import com.samsung.android.knox.devicesecurity.PasswordPolicy;
import com.samsung.android.knox.dlp.DLPManagerPolicy;
import com.samsung.android.knox.keystore.CertificatePolicy;
import com.samsung.android.knox.keystore.CertificateProvisioning;
import com.samsung.android.knox.keystore.ClientCertificateManager;
import com.samsung.android.knox.keystore.EnterpriseCertEnrollPolicy;
import com.samsung.android.knox.kiosk.KioskMode;
import com.samsung.android.knox.location.Geofencing;
import com.samsung.android.knox.location.LocationPolicy;
import com.samsung.android.knox.lockscreen.BootBanner;
import com.samsung.android.knox.log.AuditLog;
import com.samsung.android.knox.net.billing.EnterpriseBillingPolicy;
import com.samsung.android.knox.net.firewall.Firewall;
import com.samsung.android.knox.net.wifi.WifiPolicy;
import com.samsung.android.knox.restriction.AdvancedRestrictionPolicy;
import com.samsung.android.knox.restriction.RestrictionPolicy;
import java.util.List;

public class KnoxContainerManager {
    public static final String ACTION_CONTAINER_ADMIN_LOCK = "com.samsung.android.knox.intent.action.CONTAINER_ADMIN_LOCK";
    public static final String ACTION_CONTAINER_CREATION_STATUS = "com.samsung.android.knox.intent.action.CONTAINER_CREATION_STATUS";
    public static final String ACTION_CONTAINER_REMOVED = "com.samsung.android.knox.intent.action.CONTAINER_REMOVED";
    public static final String ACTION_CONTAINER_STATE_CHANGED = "com.samsung.android.knox.intent.action.CONTAINER_STATE_CHANGED";
    public static final String CONTAINER_CREATION_REQUEST_ID = "requestId";
    public static final String CONTAINER_CREATION_STATUS_CODE = "code";
    public static final String CONTAINER_ID = "containerid";
    public static final String CONTAINER_NEW_STATE = "container_new_state";
    public static final String CONTAINER_OLD_STATE = "container_old_state";
    public static final String INTENT_BUNDLE = "intent";
    private volatile AdvancedRestrictionPolicy mAdvancedRestrictionPolicy;
    private volatile ApplicationPolicy mApplicationPolicy;
    private volatile AuditLog mAuditLogPolicy;
    private volatile BasePasswordPolicy mBasePasswordPolicy;
    private volatile BootBanner mBootBanner;
    private volatile BrowserPolicy mBrowserPolicy;
    private volatile CertificatePolicy mCertificatePolicy;
    private volatile CertificateProvisioning mCertificateProvisioning;
    private volatile ClientCertificateManager mClientCertificateManager;
    private volatile ContainerConfigurationPolicy mContainerConfigurationPolicy;
    private Context mContext;
    private volatile DLPManagerPolicy mDLPManagerPolicy;
    private volatile DateTimePolicy mDateTimePolicy;
    private volatile DeviceAccountPolicy mDeviceAccountPolicy;
    private volatile ExchangeAccountPolicy mEasAccountPolicy;
    private volatile EmailAccountPolicy mEmailAccountPolicy;
    private volatile EmailPolicy mEmailPolicy;
    private volatile EnterpriseBillingPolicy mEnterpriseBillingPolicy;
    private volatile EnterpriseCertEnrollPolicy mEnterpriseCertEnrollPolicy;
    private volatile Firewall mFirewall;
    private volatile Geofencing mGeofencing;
    private volatile KioskMode mKioskMode;
    private com.sec.enterprise.knox.container.KnoxContainerManager mKnoxContainerManager;
    private volatile LDAPAccountPolicy mLDAPAccountPolicy;
    private volatile LocationPolicy mLocationPolicy;
    private volatile PasswordPolicy mPasswordPolicy;
    private volatile RCPPolicy mRCPPolicy = null;
    private volatile RestrictionPolicy mRestrictionPolicy;
    private volatile WifiPolicy mWifiPolicy;

    public KnoxContainerManager(Context context, com.sec.enterprise.knox.container.KnoxContainerManager knoxContainerManager) {
        this.mContext = context;
        this.mKnoxContainerManager = knoxContainerManager;
    }

    public static boolean addConfigurationType(Context context, KnoxConfigurationType knoxConfigurationType) {
        return com.sec.enterprise.knox.container.KnoxContainerManager.addConfigurationType(context, KnoxConfigurationType.convertToOld(knoxConfigurationType));
    }

    public static int createContainer(CreationParams creationParams) {
        try {
            return com.sec.enterprise.knox.container.KnoxContainerManager.createContainer(CreationParams.convertToOld(creationParams));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static int createContainer(String str) {
        return com.sec.enterprise.knox.container.KnoxContainerManager.createContainer(str);
    }

    public static int createContainer(String str, String str2) {
        return com.sec.enterprise.knox.container.KnoxContainerManager.createContainer(str, str2);
    }

    public static void doSelfUninstall() {
        com.sec.enterprise.knox.container.KnoxContainerManager.doSelfUninstall();
    }

    public static KnoxConfigurationType getConfigurationType(int i) {
        return KnoxConfigurationType.convertToNew(com.sec.enterprise.knox.container.KnoxContainerManager.getConfigurationType(i));
    }

    public static KnoxConfigurationType getConfigurationTypeByName(String str) {
        return KnoxConfigurationType.convertToNew(com.sec.enterprise.knox.container.KnoxContainerManager.getConfigurationTypeByName(str));
    }

    public static List<KnoxConfigurationType> getConfigurationTypes() {
        return KnoxConfigurationType.convertToNewList(com.sec.enterprise.knox.container.KnoxContainerManager.getConfigurationTypes());
    }

    public static List<Integer> getContainers() {
        return com.sec.enterprise.knox.container.KnoxContainerManager.getContainers();
    }

    public static boolean removeConfigurationType(String str) {
        try {
            return com.sec.enterprise.knox.container.KnoxContainerManager.removeConfigurationType(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "removeConfigurationType", new Class[]{String.class}, 13));
        }
    }

    public static int removeContainer(int i) {
        return com.sec.enterprise.knox.container.KnoxContainerManager.removeContainer(i);
    }

    public AdvancedRestrictionPolicy getAdvancedRestrictionPolicy() {
        com.sec.enterprise.knox.AdvancedRestrictionPolicy advancedRestrictionPolicy;
        AdvancedRestrictionPolicy advancedRestrictionPolicy2 = this.mAdvancedRestrictionPolicy;
        if (advancedRestrictionPolicy2 == null) {
            synchronized (this) {
                advancedRestrictionPolicy2 = this.mAdvancedRestrictionPolicy;
                if (advancedRestrictionPolicy2 == null && (advancedRestrictionPolicy = this.mKnoxContainerManager.getAdvancedRestrictionPolicy()) != null) {
                    advancedRestrictionPolicy2 = new AdvancedRestrictionPolicy(advancedRestrictionPolicy);
                    this.mAdvancedRestrictionPolicy = advancedRestrictionPolicy2;
                }
            }
        }
        return advancedRestrictionPolicy2;
    }

    public ApplicationPolicy getApplicationPolicy() {
        ApplicationPolicy applicationPolicy = this.mApplicationPolicy;
        if (applicationPolicy == null) {
            synchronized (this) {
                applicationPolicy = this.mApplicationPolicy;
                if (applicationPolicy == null) {
                    android.app.enterprise.ApplicationPolicy applicationPolicy2 = this.mKnoxContainerManager.getApplicationPolicy();
                    com.sec.enterprise.knox.AdvancedRestrictionPolicy advancedRestrictionPolicy = this.mKnoxContainerManager.getAdvancedRestrictionPolicy();
                    if (applicationPolicy2 != null && advancedRestrictionPolicy != null) {
                        applicationPolicy = new ApplicationPolicy(applicationPolicy2, advancedRestrictionPolicy);
                        this.mApplicationPolicy = applicationPolicy;
                    }
                }
            }
        }
        return applicationPolicy;
    }

    public AuditLog getAuditLogPolicy() {
        if (this.mAuditLogPolicy == null) {
            synchronized (this) {
                if (this.mAuditLogPolicy == null) {
                    try {
                        com.sec.enterprise.knox.auditlog.AuditLog auditLogPolicy = this.mKnoxContainerManager.getAuditLogPolicy();
                        if (auditLogPolicy != null) {
                            this.mAuditLogPolicy = new AuditLog(auditLogPolicy);
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "getAuditLogPolicy", null, 19));
                    }
                }
            }
        }
        return this.mAuditLogPolicy;
    }

    public BasePasswordPolicy getBasePasswordPolicy() {
        android.app.enterprise.BasePasswordPolicy basePasswordPolicy;
        BasePasswordPolicy basePasswordPolicy2 = this.mBasePasswordPolicy;
        if (basePasswordPolicy2 == null) {
            synchronized (this) {
                basePasswordPolicy2 = this.mBasePasswordPolicy;
                if (basePasswordPolicy2 == null && (basePasswordPolicy = this.mKnoxContainerManager.getBasePasswordPolicy()) != null) {
                    basePasswordPolicy2 = new BasePasswordPolicy(basePasswordPolicy);
                    this.mBasePasswordPolicy = basePasswordPolicy2;
                }
            }
        }
        return basePasswordPolicy2;
    }

    public BootBanner getBootBanner() {
        SecurityPolicy securityPolicy;
        BootBanner bootBanner = this.mBootBanner;
        if (bootBanner == null) {
            synchronized (this) {
                bootBanner = this.mBootBanner;
                if (bootBanner == null && (securityPolicy = this.mKnoxContainerManager.getSecurityPolicy()) != null) {
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
                    android.app.enterprise.BrowserPolicy browserPolicy2 = this.mKnoxContainerManager.getBrowserPolicy();
                    MiscPolicy miscPolicy = this.mKnoxContainerManager.getMiscPolicy();
                    if (browserPolicy2 != null && miscPolicy != null) {
                        browserPolicy = new BrowserPolicy(browserPolicy2, miscPolicy);
                        this.mBrowserPolicy = browserPolicy;
                    }
                }
            }
        }
        return browserPolicy;
    }

    public CertificatePolicy getCertificatePolicy() {
        CertificatePolicy certificatePolicy = this.mCertificatePolicy;
        if (certificatePolicy == null) {
            synchronized (this) {
                certificatePolicy = this.mCertificatePolicy;
                if (certificatePolicy == null) {
                    try {
                        com.sec.enterprise.knox.certificate.CertificatePolicy certificatePolicy2 = this.mKnoxContainerManager.getCertificatePolicy();
                        if (certificatePolicy2 != null) {
                            certificatePolicy = new CertificatePolicy(certificatePolicy2);
                            this.mCertificatePolicy = certificatePolicy;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "getCertificatePolicy", null, 12));
                    }
                }
            }
        }
        return certificatePolicy;
    }

    public CertificateProvisioning getCertificateProvisioning() {
        SecurityPolicy securityPolicy;
        CertificateProvisioning certificateProvisioning = this.mCertificateProvisioning;
        if (certificateProvisioning == null) {
            synchronized (this) {
                certificateProvisioning = this.mCertificateProvisioning;
                if (certificateProvisioning == null && (securityPolicy = this.mKnoxContainerManager.getSecurityPolicy()) != null) {
                    certificateProvisioning = new CertificateProvisioning(securityPolicy);
                    this.mCertificateProvisioning = certificateProvisioning;
                }
            }
        }
        return certificateProvisioning;
    }

    public ClientCertificateManager getClientCertificateManagerPolicy() {
        ClientCertificateManager clientCertificateManager = this.mClientCertificateManager;
        if (clientCertificateManager == null) {
            synchronized (this) {
                clientCertificateManager = this.mClientCertificateManager;
                if (clientCertificateManager == null) {
                    try {
                        com.sec.enterprise.knox.ccm.ClientCertificateManager clientCertificateManagerPolicy = this.mKnoxContainerManager.getClientCertificateManagerPolicy();
                        if (clientCertificateManagerPolicy != null) {
                            clientCertificateManager = new ClientCertificateManager(clientCertificateManagerPolicy);
                            this.mClientCertificateManager = clientCertificateManager;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "getClientCertificateManagerPolicy", null, 12));
                    }
                }
            }
        }
        return clientCertificateManager;
    }

    public ContainerConfigurationPolicy getContainerConfigurationPolicy() {
        com.sec.enterprise.knox.container.ContainerConfigurationPolicy containerConfigurationPolicy;
        ContainerConfigurationPolicy containerConfigurationPolicy2 = this.mContainerConfigurationPolicy;
        if (containerConfigurationPolicy2 == null) {
            synchronized (this) {
                containerConfigurationPolicy2 = this.mContainerConfigurationPolicy;
                if (containerConfigurationPolicy2 == null && (containerConfigurationPolicy = this.mKnoxContainerManager.getContainerConfigurationPolicy()) != null) {
                    containerConfigurationPolicy2 = new ContainerConfigurationPolicy(containerConfigurationPolicy);
                    this.mContainerConfigurationPolicy = containerConfigurationPolicy2;
                }
            }
        }
        return containerConfigurationPolicy2;
    }

    public DLPManagerPolicy getDLPManagerPolicy() {
        DLPManagerPolicy dLPManagerPolicy = this.mDLPManagerPolicy;
        if (dLPManagerPolicy == null) {
            synchronized (this) {
                dLPManagerPolicy = this.mDLPManagerPolicy;
                if (dLPManagerPolicy == null) {
                    try {
                        com.sec.enterprise.knox.dlp.DLPManagerPolicy dLPManagerPolicy2 = this.mKnoxContainerManager.getDLPManagerPolicy();
                        if (dLPManagerPolicy2 != null) {
                            dLPManagerPolicy = new DLPManagerPolicy(dLPManagerPolicy2);
                            this.mDLPManagerPolicy = dLPManagerPolicy;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "getDLPManagerPolicy", null, 19));
                    }
                }
            }
        }
        return dLPManagerPolicy;
    }

    public DateTimePolicy getDateTimePolicy() {
        android.app.enterprise.DateTimePolicy dateTimePolicy;
        DateTimePolicy dateTimePolicy2 = this.mDateTimePolicy;
        if (dateTimePolicy2 == null) {
            synchronized (this) {
                dateTimePolicy2 = this.mDateTimePolicy;
                if (dateTimePolicy2 == null && (dateTimePolicy = this.mKnoxContainerManager.getDateTimePolicy()) != null) {
                    dateTimePolicy2 = new DateTimePolicy(this.mContext, dateTimePolicy);
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
                    android.app.enterprise.DeviceAccountPolicy deviceAccountPolicy2 = this.mKnoxContainerManager.getDeviceAccountPolicy();
                    SecurityPolicy securityPolicy = this.mKnoxContainerManager.getSecurityPolicy();
                    if (deviceAccountPolicy2 != null && securityPolicy != null) {
                        deviceAccountPolicy = new DeviceAccountPolicy(deviceAccountPolicy2, securityPolicy);
                        this.mDeviceAccountPolicy = deviceAccountPolicy;
                    }
                }
            }
        }
        return deviceAccountPolicy;
    }

    public EmailAccountPolicy getEmailAccountPolicy() {
        android.app.enterprise.EmailAccountPolicy emailAccountPolicy;
        EmailAccountPolicy emailAccountPolicy2 = this.mEmailAccountPolicy;
        if (emailAccountPolicy2 == null) {
            synchronized (this) {
                emailAccountPolicy2 = this.mEmailAccountPolicy;
                if (emailAccountPolicy2 == null && (emailAccountPolicy = this.mKnoxContainerManager.getEmailAccountPolicy()) != null) {
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
                if (emailPolicy2 == null && (emailPolicy = this.mKnoxContainerManager.getEmailPolicy()) != null) {
                    emailPolicy2 = new EmailPolicy(emailPolicy);
                    this.mEmailPolicy = emailPolicy2;
                }
            }
        }
        return emailPolicy2;
    }

    public EnterpriseBillingPolicy getEnterpriseBillingPolicy() {
        EnterpriseBillingPolicy enterpriseBillingPolicy = this.mEnterpriseBillingPolicy;
        if (enterpriseBillingPolicy == null) {
            synchronized (this) {
                enterpriseBillingPolicy = this.mEnterpriseBillingPolicy;
                if (enterpriseBillingPolicy == null) {
                    try {
                        com.sec.enterprise.knox.billing.EnterpriseBillingPolicy enterpriseBillingPolicy2 = this.mKnoxContainerManager.getEnterpriseBillingPolicy();
                        if (enterpriseBillingPolicy2 != null) {
                            enterpriseBillingPolicy = new EnterpriseBillingPolicy(enterpriseBillingPolicy2);
                            this.mEnterpriseBillingPolicy = enterpriseBillingPolicy;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "getEnterpriseBillingPolicy", null, 13));
                    }
                }
            }
        }
        return enterpriseBillingPolicy;
    }

    public EnterpriseCertEnrollPolicy getEnterpriseCertEnrollPolicy(String str) {
        EnterpriseCertEnrollPolicy enterpriseCertEnrollPolicy = this.mEnterpriseCertEnrollPolicy;
        if (enterpriseCertEnrollPolicy == null) {
            synchronized (this) {
                enterpriseCertEnrollPolicy = this.mEnterpriseCertEnrollPolicy;
                if (enterpriseCertEnrollPolicy == null) {
                    try {
                        com.sec.enterprise.knox.certenroll.EnterpriseCertEnrollPolicy enterpriseCertEnrollPolicy2 = this.mKnoxContainerManager.getEnterpriseCertEnrollPolicy(str);
                        if (enterpriseCertEnrollPolicy2 != null) {
                            enterpriseCertEnrollPolicy = new EnterpriseCertEnrollPolicy(enterpriseCertEnrollPolicy2);
                            this.mEnterpriseCertEnrollPolicy = enterpriseCertEnrollPolicy;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "getEnterpriseCertEnrollPolicy", null, 12));
                    }
                }
            }
        }
        return enterpriseCertEnrollPolicy;
    }

    public ExchangeAccountPolicy getExchangeAccountPolicy() {
        android.app.enterprise.ExchangeAccountPolicy exchangeAccountPolicy;
        ExchangeAccountPolicy exchangeAccountPolicy2 = this.mEasAccountPolicy;
        if (exchangeAccountPolicy2 == null) {
            synchronized (this) {
                exchangeAccountPolicy2 = this.mEasAccountPolicy;
                if (exchangeAccountPolicy2 == null && (exchangeAccountPolicy = this.mKnoxContainerManager.getExchangeAccountPolicy()) != null) {
                    exchangeAccountPolicy2 = new ExchangeAccountPolicy(exchangeAccountPolicy);
                    this.mEasAccountPolicy = exchangeAccountPolicy2;
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
                    try {
                        com.sec.enterprise.firewall.Firewall firewall2 = this.mKnoxContainerManager.getFirewall();
                        if (firewall2 != null) {
                            Firewall firewall3 = new Firewall(firewall2);
                            try {
                                this.mFirewall = firewall3;
                                firewall = firewall3;
                            } catch (NoSuchMethodError unused) {
                                firewall = firewall3;
                                FirewallPolicy firewallPolicy = this.mKnoxContainerManager.getFirewallPolicy();
                                if (firewallPolicy != null) {
                                    firewall = new Firewall(firewallPolicy);
                                    this.mFirewall = firewall;
                                }
                                return firewall;
                            }
                        }
                    } catch (NoSuchMethodError unused2) {
                    }
                }
            }
        }
        return firewall;
    }

    public Geofencing getGeofencing() {
        android.app.enterprise.geofencing.Geofencing geofencing;
        Geofencing geofencing2 = this.mGeofencing;
        if (geofencing2 == null) {
            synchronized (this) {
                geofencing2 = this.mGeofencing;
                if (geofencing2 == null && (geofencing = this.mKnoxContainerManager.getGeofencing()) != null) {
                    geofencing2 = new Geofencing(geofencing);
                    this.mGeofencing = geofencing2;
                }
            }
        }
        return geofencing2;
    }

    public KioskMode getKioskMode() {
        KioskMode kioskMode = this.mKioskMode;
        if (kioskMode == null) {
            synchronized (this) {
                kioskMode = this.mKioskMode;
                if (kioskMode == null) {
                    try {
                        android.app.enterprise.kioskmode.KioskMode kioskMode2 = this.mKnoxContainerManager.getKioskMode();
                        if (kioskMode2 != null) {
                            kioskMode = new KioskMode(kioskMode2);
                            this.mKioskMode = kioskMode;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxContainerManager.class, "getKioskMode", null, 12));
                    }
                }
            }
        }
        return kioskMode;
    }

    public LDAPAccountPolicy getLDAPAccountPolicy() {
        android.app.enterprise.LDAPAccountPolicy lDAPAccountPolicy;
        LDAPAccountPolicy lDAPAccountPolicy2 = this.mLDAPAccountPolicy;
        if (lDAPAccountPolicy2 == null) {
            synchronized (this) {
                lDAPAccountPolicy2 = this.mLDAPAccountPolicy;
                if (lDAPAccountPolicy2 == null && (lDAPAccountPolicy = this.mKnoxContainerManager.getLDAPAccountPolicy()) != null) {
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
                if (locationPolicy2 == null && (locationPolicy = this.mKnoxContainerManager.getLocationPolicy()) != null) {
                    locationPolicy2 = new LocationPolicy(locationPolicy);
                    this.mLocationPolicy = locationPolicy2;
                }
            }
        }
        return locationPolicy2;
    }

    public PasswordPolicy getPasswordPolicy() {
        android.app.enterprise.PasswordPolicy passwordPolicy;
        PasswordPolicy passwordPolicy2 = this.mPasswordPolicy;
        if (passwordPolicy2 == null) {
            synchronized (this) {
                passwordPolicy2 = this.mPasswordPolicy;
                if (passwordPolicy2 == null && (passwordPolicy = this.mKnoxContainerManager.getPasswordPolicy()) != null) {
                    passwordPolicy2 = new PasswordPolicy(passwordPolicy);
                    this.mPasswordPolicy = passwordPolicy2;
                }
            }
        }
        return passwordPolicy2;
    }

    public RCPPolicy getRCPPolicy() {
        com.sec.enterprise.knox.container.RCPPolicy rCPPolicy;
        RCPPolicy rCPPolicy2 = this.mRCPPolicy;
        if (rCPPolicy2 == null) {
            synchronized (this) {
                rCPPolicy2 = this.mRCPPolicy;
                if (rCPPolicy2 == null && (rCPPolicy = this.mKnoxContainerManager.getRCPPolicy()) != null) {
                    rCPPolicy2 = new RCPPolicy(rCPPolicy);
                    this.mRCPPolicy = rCPPolicy2;
                }
            }
        }
        return rCPPolicy2;
    }

    public RestrictionPolicy getRestrictionPolicy() {
        android.app.enterprise.RestrictionPolicy restrictionPolicy;
        RestrictionPolicy restrictionPolicy2 = this.mRestrictionPolicy;
        if (restrictionPolicy2 == null) {
            synchronized (this) {
                restrictionPolicy2 = this.mRestrictionPolicy;
                if (restrictionPolicy2 == null && (restrictionPolicy = this.mKnoxContainerManager.getRestrictionPolicy()) != null) {
                    restrictionPolicy2 = new RestrictionPolicy(restrictionPolicy);
                    this.mRestrictionPolicy = restrictionPolicy2;
                }
            }
        }
        return restrictionPolicy2;
    }

    public int getStatus() {
        return this.mKnoxContainerManager.getStatus();
    }

    public WifiPolicy getWifiPolicy() {
        android.app.enterprise.WifiPolicy wifiPolicy;
        WifiPolicy wifiPolicy2 = this.mWifiPolicy;
        if (wifiPolicy2 == null) {
            synchronized (this) {
                wifiPolicy2 = this.mWifiPolicy;
                if (wifiPolicy2 == null && (wifiPolicy = this.mKnoxContainerManager.getWifiPolicy()) != null) {
                    wifiPolicy2 = new WifiPolicy(wifiPolicy);
                    this.mWifiPolicy = wifiPolicy2;
                }
            }
        }
        return wifiPolicy2;
    }

    public boolean lock() {
        return this.mKnoxContainerManager.lock();
    }

    public boolean unlock() {
        return this.mKnoxContainerManager.unlock();
    }
}

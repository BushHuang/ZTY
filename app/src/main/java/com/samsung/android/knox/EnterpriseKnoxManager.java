package com.samsung.android.knox;

import android.content.Context;
import com.samsung.android.knox.container.KnoxContainerManager;
import com.samsung.android.knox.keystore.CertificatePolicy;
import com.samsung.android.knox.keystore.ClientCertificateManager;
import com.samsung.android.knox.keystore.EnterpriseCertEnrollPolicy;
import com.samsung.android.knox.keystore.TimaKeystore;
import com.samsung.android.knox.log.AuditLog;
import com.samsung.android.knox.net.billing.EnterpriseBillingPolicy;
import com.samsung.android.knox.net.vpn.GenericVpnPolicy;
import com.samsung.android.knox.restriction.AdvancedRestrictionPolicy;

public class EnterpriseKnoxManager {
    private static Context mContext;
    private static com.sec.enterprise.knox.EnterpriseKnoxManager mEkm;
    private static EnterpriseKnoxManager mInstance;
    private volatile AdvancedRestrictionPolicy mAdvancedRestrictionPolicy;
    private volatile AuditLog mAuditLogPolicy;
    private volatile CertificatePolicy mCertificatePolicy;
    private volatile ClientCertificateManager mClientCertificateManagerPolicy;
    private volatile EnterpriseBillingPolicy mEnterpriseBillingPolicy;
    private volatile TimaKeystore mTimaKeystorePolicy;

    private EnterpriseKnoxManager(com.sec.enterprise.knox.EnterpriseKnoxManager enterpriseKnoxManager) {
        mEkm = enterpriseKnoxManager;
    }

    public static EnterpriseKnoxManager getInstance(Context context) {
        com.sec.enterprise.knox.EnterpriseKnoxManager enterpriseKnoxManager;
        if (context == null) {
            return null;
        }
        EnterpriseKnoxManager enterpriseKnoxManager2 = mInstance;
        if (enterpriseKnoxManager2 == null) {
            synchronized (EnterpriseKnoxManager.class) {
                enterpriseKnoxManager2 = mInstance;
                if (enterpriseKnoxManager2 == null && (enterpriseKnoxManager = com.sec.enterprise.knox.EnterpriseKnoxManager.getInstance()) != null) {
                    enterpriseKnoxManager2 = new EnterpriseKnoxManager(enterpriseKnoxManager);
                    mInstance = enterpriseKnoxManager2;
                    mContext = context;
                }
            }
        }
        return enterpriseKnoxManager2;
    }

    public AdvancedRestrictionPolicy getAdvancedRestrictionPolicy() {
        com.sec.enterprise.knox.AdvancedRestrictionPolicy advancedRestrictionPolicy;
        AdvancedRestrictionPolicy advancedRestrictionPolicy2 = this.mAdvancedRestrictionPolicy;
        if (advancedRestrictionPolicy2 == null) {
            synchronized (this) {
                advancedRestrictionPolicy2 = this.mAdvancedRestrictionPolicy;
                if (advancedRestrictionPolicy2 == null && (advancedRestrictionPolicy = mEkm.getAdvancedRestrictionPolicy(mContext)) != null) {
                    advancedRestrictionPolicy2 = new AdvancedRestrictionPolicy(advancedRestrictionPolicy);
                    this.mAdvancedRestrictionPolicy = advancedRestrictionPolicy2;
                }
            }
        }
        return advancedRestrictionPolicy2;
    }

    public AuditLog getAuditLogPolicy() {
        com.sec.enterprise.knox.auditlog.AuditLog auditLog;
        AuditLog auditLog2 = this.mAuditLogPolicy;
        if (auditLog2 == null) {
            synchronized (this) {
                auditLog2 = this.mAuditLogPolicy;
                if (auditLog2 == null && (auditLog = com.sec.enterprise.knox.auditlog.AuditLog.getInstance(mContext)) != null) {
                    auditLog2 = new AuditLog(auditLog);
                    this.mAuditLogPolicy = auditLog2;
                }
            }
        }
        return auditLog2;
    }

    public CertificatePolicy getCertificatePolicy() {
        com.sec.enterprise.knox.certificate.CertificatePolicy certificatePolicy;
        CertificatePolicy certificatePolicy2 = this.mCertificatePolicy;
        if (certificatePolicy2 == null) {
            synchronized (this) {
                certificatePolicy2 = this.mCertificatePolicy;
                if (certificatePolicy2 == null && (certificatePolicy = com.sec.enterprise.knox.certificate.CertificatePolicy.getInstance(mContext)) != null) {
                    certificatePolicy2 = new CertificatePolicy(certificatePolicy);
                    this.mCertificatePolicy = certificatePolicy2;
                }
            }
        }
        return certificatePolicy2;
    }

    public ClientCertificateManager getClientCertificateManagerPolicy() {
        com.sec.enterprise.knox.ccm.ClientCertificateManager clientCertificateManagerPolicy;
        ClientCertificateManager clientCertificateManager = this.mClientCertificateManagerPolicy;
        if (clientCertificateManager == null) {
            synchronized (this) {
                clientCertificateManager = this.mClientCertificateManagerPolicy;
                if (clientCertificateManager == null && (clientCertificateManagerPolicy = mEkm.getClientCertificateManagerPolicy(mContext)) != null) {
                    clientCertificateManager = new ClientCertificateManager(clientCertificateManagerPolicy);
                    this.mClientCertificateManagerPolicy = clientCertificateManager;
                }
            }
        }
        return clientCertificateManager;
    }

    public EnterpriseBillingPolicy getEnterpriseBillingPolicy() {
        EnterpriseBillingPolicy enterpriseBillingPolicy = this.mEnterpriseBillingPolicy;
        if (enterpriseBillingPolicy == null) {
            synchronized (this) {
                enterpriseBillingPolicy = this.mEnterpriseBillingPolicy;
                if (enterpriseBillingPolicy == null) {
                    try {
                        com.sec.enterprise.knox.billing.EnterpriseBillingPolicy enterpriseBillingPolicy2 = mEkm.getEnterpriseBillingPolicy();
                        if (enterpriseBillingPolicy2 != null) {
                            enterpriseBillingPolicy = new EnterpriseBillingPolicy(enterpriseBillingPolicy2);
                            this.mEnterpriseBillingPolicy = enterpriseBillingPolicy;
                        }
                    } catch (NoSuchMethodError unused) {
                        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(EnterpriseKnoxManager.class, "getEnterpriseBillingPolicy", null, 13));
                    }
                }
            }
        }
        return enterpriseBillingPolicy;
    }

    public EnterpriseCertEnrollPolicy getEnterpriseCertEnrollPolicy(String str) {
        com.sec.enterprise.knox.certenroll.EnterpriseCertEnrollPolicy enterpriseCertEnrollPolicy = mEkm.getEnterpriseCertEnrollPolicy(mContext, str);
        if (enterpriseCertEnrollPolicy != null) {
            return new EnterpriseCertEnrollPolicy(enterpriseCertEnrollPolicy);
        }
        return null;
    }

    public GenericVpnPolicy getGenericVpnPolicy(String str, int i) {
        com.sec.enterprise.knox.GenericVpnPolicy genericVpnPolicy = mEkm.getGenericVpnPolicy(str, i);
        if (genericVpnPolicy != null) {
            return new GenericVpnPolicy(genericVpnPolicy);
        }
        return null;
    }

    public synchronized KnoxContainerManager getKnoxContainerManager(int i) {
        com.sec.enterprise.knox.container.KnoxContainerManager knoxContainerManager;
        knoxContainerManager = mEkm.getKnoxContainerManager(mContext, i);
        return knoxContainerManager != null ? new KnoxContainerManager(mContext, knoxContainerManager) : null;
    }

    public TimaKeystore getTimaKeystorePolicy() {
        com.sec.enterprise.knox.keystore.TimaKeystore timaKeystorePolicy;
        TimaKeystore timaKeystore = this.mTimaKeystorePolicy;
        if (timaKeystore == null) {
            synchronized (this) {
                timaKeystore = this.mTimaKeystorePolicy;
                if (timaKeystore == null && (timaKeystorePolicy = mEkm.getTimaKeystorePolicy(mContext)) != null) {
                    timaKeystore = new TimaKeystore(timaKeystorePolicy);
                    this.mTimaKeystorePolicy = timaKeystore;
                }
            }
        }
        return timaKeystore;
    }
}

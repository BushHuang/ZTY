package com.samsung.android.knox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.knox.deviceinfo.SimChangeInfo;

public class IntentConverterReceiver extends BroadcastReceiver {
    private static final String ACTION_CBA_INSTALL_STATUS_OLDEST = "android.intent.action.sec.CBA_INSTALL_STATUS";
    private static final String ACTION_SIM_CARD_CHANGED_OLDEST = "android.intent.action.sec.SIM_CARD_CHANGED";
    private static final int DEFAULT_VALUE = -1;

    private Intent convertActionApplicationFocusChangeIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.APPLICATION_FOCUS_CHANGE");
        intent2.putExtra("com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_COMPONENT_NAME", intent.getStringExtra("application_focus_component_name"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.APPLICATION_FOCUS_STATUS", intent.getStringExtra("application_focus_status"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("user_id", -1));
        return intent2;
    }

    private Intent convertActionAuditFullSize() {
        return new Intent("com.samsung.android.knox.intent.action.AUDIT_FULL_SIZE");
    }

    private Intent convertActionAuditMaximumSize() {
        return new Intent("com.samsung.android.knox.intent.action.AUDIT_MAXIMUM_SIZE");
    }

    private Intent convertActionBindResult(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.VPN_BIND_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.VPN_BIND_VENDOR", intent.getStringExtra("vpn_bind_vendor"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.VPN_BIND_CID", intent.getIntExtra("vpn_bind_cid", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.VPN_BIND_STATUS", intent.getBooleanExtra("vpn_bind_status", false));
        return intent2;
    }

    private Intent convertActionBlockedDomainIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.BLOCKED_DOMAIN");
        intent2.putExtra("com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_PACKAGENAME", intent.getStringExtra("com.sec.enterprise.intent.extra.BLOCKED_DOMAIN_PACKAGENAME"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_TIMESTAMP", intent.getStringExtra("com.sec.enterprise.intent.extra.BLOCKED_DOMAIN_TIMESTAMP"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_UID", intent.getStringExtra("com.sec.enterprise.intent.extra.BLOCKED_DOMAIN_UID"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_URL", intent.getStringExtra("com.sec.enterprise.intent.extra.BLOCKED_DOMAIN_URL"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.BLOCKED_DOMAIN_ISFOREGROUND", intent.getBooleanExtra("com.sec.enterprise.intent.extra.BLOCKED_DOMAIN_ISFOREGROUND", false));
        return intent2;
    }

    private Intent convertActionCertEnrollStatus(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.CEP_CERT_ENROLL_STATUS");
        intent2.putExtra("com.samsung.extra.knox.certenroll.STATUS", intent.getIntExtra("com.samsung.extra.knox.certenroll.STATUS", -3));
        intent2.putExtra("com.samsung.extra.knox.certenroll.ALIAS", intent.getStringExtra("com.samsung.extra.knox.certenroll.ALIAS"));
        intent2.putExtra("com.samsung.extra.knox.certenroll.TRANSACTION_ID", intent.getStringExtra("com.samsung.extra.knox.certenroll.TRANSACTION_ID"));
        intent2.putExtra("com.samsung.extra.knox.certenroll.REFERENCE_NUMBER", intent.getStringExtra("com.samsung.extra.knox.certenroll.REFERENCE_NUMBER"));
        intent2.putExtra("com.samsung.extra.knox.certenroll.CERT_HASH", intent.getStringExtra("com.samsung.extra.knox.certenroll.CERT_HASH"));
        intent2.putExtra("com.samsung.extra.knox.certenroll.SERVICE_USERID", intent.getIntExtra("com.samsung.extra.knox.certenroll.SERVICE_USERID", -1));
        intent2.putExtra("com.samsung.extra.knox.certenroll.SERVICE_PROTOCOL", intent.getStringExtra("com.samsung.extra.knox.certenroll.SERVICE_PROTOCOL"));
        return intent2;
    }

    private Intent convertActionCertificateRemovedIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.CERTIFICATE_REMOVED");
        intent2.putExtra("com.samsung.android.knox.intent.extra.CERTIFICATE_REMOVED_SUBJECT", intent.getStringExtra("com.samsung.edm.intent.extra.CERTIFICATE_REMOVED_SUBJECT"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("com.samsung.edm.intent.extra.USER_ID", -1));
        return intent2;
    }

    private Intent convertActionCriticalSize() {
        return new Intent("com.samsung.android.knox.intent.action.AUDIT_CRITICAL_SIZE");
    }

    private Intent convertActionDumpLogResult(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.DUMP_LOG_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.AUDIT_RESULT", intent.getIntExtra("mdm.intent.extra.audit.log.result", -1));
        return intent2;
    }

    private Intent convertActionEmailAccountAddResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.EMAIL_ACCOUNT_ADD_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.RESULT", intent.getIntExtra("result", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.INCOMING_PROTOCOL", intent.getStringExtra("protocol"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.EMAIL_ADDRESS", intent.getStringExtra("email_id"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.INCOMING_SERVER_ADDRESS", intent.getStringExtra("receive_host"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ACCOUNT_ID", intent.getLongExtra("account_id", -1L));
        return intent2;
    }

    private Intent convertActionEmailAccountDeleteResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.EMAIL_ACCOUNT_DELETE_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.RESULT", intent.getIntExtra("result", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.INCOMING_PROTOCOL", intent.getStringExtra("protocol"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.EMAIL_ADDRESS", intent.getStringExtra("email_id"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.INCOMING_SERVER_ADDRESS", intent.getStringExtra("receive_host"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ACCOUNT_ID", intent.getLongExtra("account_id", -1L));
        return intent2;
    }

    private Intent convertActionKnoxAttestationResult(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.KNOX_ATTESTATION_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.RESULT", intent.getIntExtra("com.sec.enterprise.knox.intent.extra.RESULT", -4));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ATTESTATION_DATA", intent.getByteArrayExtra("com.sec.enterprise.knox.intent.extra.ATTESTATION_DATA"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ERROR_MSG", intent.getStringExtra("com.sec.enterprise.knox.intent.extra.ERROR_MSG"));
        return intent2;
    }

    private Intent convertActionLogException(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.LOG_EXCEPTION");
        intent2.putExtra("com.samsung.android.knox.intent.extra.AUDIT_RESULT", intent.getStringExtra("mdm.intent.extra.audit.log.result"));
        return intent2;
    }

    private Intent convertActionNoUserActivity(Intent intent) {
        return new Intent("com.samsung.android.knox.intent.action.NO_USER_ACTIVITY");
    }

    private Intent convertActionServiceDisconnected(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.CEP_SERVICE_DISCONNECTED");
        intent2.putExtra("com.samsung.extra.knox.certenroll.SERVICE_USERID", intent.getIntExtra("com.samsung.extra.knox.certenroll.SERVICE_USERID", -1));
        intent2.putExtra("com.samsung.extra.knox.certenroll.SERVICE_PROTOCOL", intent.getStringExtra("com.samsung.extra.knox.certenroll.SERVICE_PROTOCOL"));
        return intent2;
    }

    private Intent convertActionUpdateFotaVersionIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.UPDATE_FOTA_VERSION_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.UPDATE_FOTA_VERSION_STATUS", intent.getIntExtra("com.sec.enterprise.intent.extra.UPDATE_FOTA_VERSION_STATUS", -1));
        return intent2;
    }

    private Intent convertActionUserActivity(Intent intent) {
        return new Intent("com.samsung.android.knox.intent.action.USER_ACTIVITY");
    }

    private Intent convertCbaInstallStatusIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.CBA_INSTALL_STATUS");
        intent2.putExtra("com.samsung.android.knox.intent.extra.STATUS", intent.getIntExtra("status", -1));
        return intent2;
    }

    private Intent convertCertificateFailureIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.CERTIFICATE_FAILURE");
        intent2.putExtra("com.samsung.android.knox.intent.extra.CERTIFICATE_FAILURE_MODULE", intent.getStringExtra("certificate_failure_module"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.CERTIFICATE_FAILURE_MESSAGE", intent.getStringExtra("certificate_failure_message"));
        if (EnterpriseDeviceManager.getAPILevel() >= 19) {
            intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("com.samsung.edm.intent.extra.USER_ID", -1));
        }
        return intent2;
    }

    private Intent convertContainerAdminLockIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.CONTAINER_ADMIN_LOCK");
        Bundle bundleExtra = intent.getBundleExtra("intent");
        Bundle bundle = new Bundle();
        bundle.putInt("containerid", bundleExtra.getInt("containerid"));
        intent2.putExtra("intent", bundle);
        return intent2;
    }

    private Intent convertContainerCreationStatusIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.CONTAINER_CREATION_STATUS");
        Bundle extras = intent.getExtras();
        Bundle bundle = new Bundle();
        bundle.putInt("code", extras.getInt("code"));
        bundle.putInt("requestId", extras.getInt("requestId"));
        intent2.putExtra("intent", bundle);
        return intent2;
    }

    private Intent convertContainerRemovedIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.CONTAINER_REMOVED");
        Bundle bundleExtra = intent.getBundleExtra("intent");
        Bundle bundle = new Bundle();
        bundle.putInt("containerid", bundleExtra.getInt("containerid"));
        intent2.putExtra("intent", bundle);
        return intent2;
    }

    private Intent convertContainerStateChangedIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.CONTAINER_STATE_CHANGED");
        Bundle bundleExtra = intent.getBundleExtra("intent");
        Bundle bundle = new Bundle();
        bundle.putInt("containerid", bundleExtra.getInt("containerid"));
        bundle.putInt("container_old_state", bundleExtra.getInt("container_old_state"));
        bundle.putInt("container_new_state", bundleExtra.getInt("container_new_state"));
        intent2.putExtra("intent", bundle);
        return intent2;
    }

    private Intent convertCreateLDAPAccountResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.LDAP_CREATE_ACCT_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.LDAP_RESULT", intent.getIntExtra("edm.intent.extra.ldap.result", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.LDAP_ACCT_ID", intent.getLongExtra("edm.intent.extra.ldap.acct.id", -1L));
        if (EnterpriseDeviceManager.getAPILevel() >= 12) {
            intent2.putExtra("com.samsung.android.knox.intent.extra.LDAP_USER_ID", intent.getIntExtra("edm.intent.extra.ldap.user.id", -1));
        }
        return intent2;
    }

    private Intent convertDeviceInsideGeofenceIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.DEVICE_INSIDE_GEOFENCE");
        intent2.putExtra("com.samsung.android.knox.intent.extra.ID", intent.getIntArrayExtra("edm.intent.extra.geofence.id"));
        if (EnterpriseDeviceManager.getAPILevel() >= 12) {
            intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("edm.intent.extra.geofence.user.id", -1));
        }
        return intent2;
    }

    private Intent convertDeviceLocationUnavailableIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.DEVICE_LOCATION_UNAVAILABLE");
        if (EnterpriseDeviceManager.getAPILevel() >= 12) {
            intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("edm.intent.extra.geofence.user.id", -1));
        }
        return intent2;
    }

    private Intent convertDeviceOutsideGeofenceIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.DEVICE_OUTSIDE_GEOFENCE");
        if (EnterpriseDeviceManager.getAPILevel() >= 12) {
            intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("edm.intent.extra.geofence.user.id", -1));
        }
        return intent2;
    }

    private Intent convertDisableKioskModeResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.DISABLE_KIOSK_MODE_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.KIOSK_RESULT", intent.getIntExtra("edm.intent.extra.kiosk.mode.result", -2000));
        return intent2;
    }

    private Intent convertEnableKioskModeResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.ENABLE_KIOSK_MODE_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.KIOSK_RESULT", intent.getIntExtra("edm.intent.extra.kiosk.mode.result", -1));
        return intent2;
    }

    private Intent convertEnforceSmimeAliasResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.ENFORCE_SMIME_ALIAS_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.ENFORCE_SMIME_ALIAS_TYPE", intent.getIntExtra("com.samsung.edm.intent.extra.ENFORCE_SMIME_ALIAS_TYPE", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ACCOUNT_ID", intent.getLongExtra("account_id", -1L));
        intent2.putExtra("com.samsung.android.knox.intent.extra.SMIME_RESULT", intent.getIntExtra("result", 0));
        return intent2;
    }

    private Intent convertExchangeAccountAddResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.EXCHANGE_ACCOUNT_ADD_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.STATUS", intent.getIntExtra("status", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.EMAIL_ADDRESS", intent.getStringExtra("email_id"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.SERVER_ADDRESS", intent.getStringExtra("server_host"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ACCOUNT_ID", intent.getLongExtra("account_id", -1L));
        return intent2;
    }

    private Intent convertExchangeAccountDeleteResultIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.EXCHANGE_ACCOUNT_DELETE_RESULT");
        intent2.putExtra("com.samsung.android.knox.intent.extra.STATUS", intent.getIntExtra("status", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.EMAIL_ADDRESS", intent.getStringExtra("email_id"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.SERVER_ADDRESS", intent.getStringExtra("server_host"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ACCOUNT_ID", intent.getLongExtra("account_id", -1L));
        return intent2;
    }

    private Intent convertKnoxLicenseStatusIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.KNOX_LICENSE_STATUS");
        intent2.putExtra("com.samsung.android.knox.intent.extra.KNOX_LICENSE_STATUS", intent.getStringExtra("edm.intent.extra.knox_license.status"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.KNOX_LICENSE_ERROR_CODE", intent.getIntExtra("edm.intent.extra.knox_license.errorcode", 102));
        intent2.putExtra("com.samsung.android.knox.intent.extra.KNOX_LICENSE_ACTIVATION_INITIATOR", intent.getIntExtra("edm.intent.extra.knox_license.activaton_initiator", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.KNOX_LICENSE_RESULT_TYPE", intent.getIntExtra("edm.intent.extra.knox_license.result_type", -1));
        return intent2;
    }

    private Intent convertLicenseStatusIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.LICENSE_STATUS");
        intent2.putExtra("com.samsung.android.knox.intent.extra.LICENSE_STATUS", intent.getStringExtra("edm.intent.extra.license.status"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.LICENSE_ERROR_CODE", intent.getIntExtra("edm.intent.extra.license.errorcode", 102));
        intent2.putExtra("com.samsung.android.knox.intent.extra.LICENSE_RESULT_TYPE", intent.getIntExtra("edm.intent.extra.license.result_type", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.LICENSE_PERM_GROUP", intent.getStringExtra("edm.intent.extra.license.perm_group"));
        if (EnterpriseDeviceManager.getAPILevel() >= 20) {
            intent2.putExtra("com.samsung.android.knox.intent.extra.LICENSE_ATTESTATION_STATUS", intent.getIntExtra("com.sec.enterprise.intent.extra.LICENSE_ATTESTATION_STATUS", 3));
        }
        return intent2;
    }

    private Intent convertNtpServerUnreachableIntent(Intent intent) {
        return new Intent("com.samsung.android.knox.intent.action.EVENT_NTP_SERVER_UNREACHABLE");
    }

    private Intent convertPreventApplicationStartIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.PREVENT_APPLICATION_START");
        intent2.putExtra("com.samsung.android.knox.intent.extra.APPLICATION_PACKAGE_NAME", intent.getStringExtra("application_package_name"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("user_id", -1));
        return intent2;
    }

    private Intent convertPreventApplicationStopIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.PREVENT_APPLICATION_STOP");
        intent2.putExtra("com.samsung.android.knox.intent.extra.APPLICATION_PACKAGE_NAME", intent.getStringExtra("application_package_name"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.USER_ID", intent.getIntExtra("user_id", -1));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ERROR_TYPE", intent.getStringExtra("error_type"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ERROR_REASON", intent.getStringExtra("error_reason"));
        intent2.putExtra("com.samsung.android.knox.intent.extra.ERROR_CLASS", intent.getStringExtra("error_class"));
        return intent2;
    }

    private Intent convertSimCardChangedIntent(Intent intent) {
        Intent intent2 = new Intent("com.samsung.android.knox.intent.action.SIM_CARD_CHANGED");
        intent2.putExtra("com.samsung.android.knox.intent.extra.SIM_CHANGE_INFO", SimChangeInfo.convertToNew(intent.getParcelableExtra("simChangeInfo")));
        return intent2;
    }

    private Intent convertUnexpectedKioskBehaviorIntent(Intent intent) {
        return new Intent("com.samsung.android.knox.intent.action.UNEXPECTED_KIOSK_BEHAVIOR");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentConvertActionLogException;
        String action = intent.getAction();
        if (EnterpriseDeviceManager.getAPILevel() > 20) {
            intentConvertActionLogException = null;
        } else if ("com.samsung.enterprise.container_state_changed".equals(action)) {
            intentConvertActionLogException = convertContainerStateChangedIntent(intent);
        } else if ("com.samsung.knox.container.creation.status".equals(action)) {
            intentConvertActionLogException = convertContainerCreationStatusIntent(intent);
        } else if ("com.samsung.knox.container.admin.locked".equals(action)) {
            intentConvertActionLogException = convertContainerAdminLockIntent(intent);
        } else if ("com.samsung.knox.container.removed".equals(action)) {
            intentConvertActionLogException = convertContainerRemovedIntent(intent);
        } else if ("edm.intent.application.action.prevent.start".equals(action)) {
            intentConvertActionLogException = convertPreventApplicationStartIntent(intent);
        } else if ("edm.intent.application.action.prevent.stop".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 14) {
                intentConvertActionLogException = convertPreventApplicationStopIntent(intent);
            }
        } else if ("edm.intent.action.ldap.createacct.result".equals(action)) {
            intentConvertActionLogException = convertCreateLDAPAccountResultIntent(intent);
        } else if ("edm.intent.action.device.inside".equals(action)) {
            intentConvertActionLogException = convertDeviceInsideGeofenceIntent(intent);
        } else if ("edm.intent.action.device.outside".equals(action)) {
            intentConvertActionLogException = convertDeviceOutsideGeofenceIntent(intent);
        } else if ("edm.intent.action.device.location.unavailable".equals(action)) {
            intentConvertActionLogException = convertDeviceLocationUnavailableIntent(intent);
        } else if ("com.samsung.edm.intent.action.CERTIFICATE_REMOVED".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 19) {
                intentConvertActionLogException = convertActionCertificateRemovedIntent(intent);
            }
        } else if ("edm.intent.certificate.action.certificate.failure".equals(action)) {
            intentConvertActionLogException = convertCertificateFailureIntent(intent);
        } else if ("com.samsung.edm.intent.action.APPLICATION_FOCUS_CHANGE".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 15) {
                intentConvertActionLogException = convertActionApplicationFocusChangeIntent(intent);
            }
        } else if ("edm.intent.action.EMAIL_ACCOUNT_ADD_RESULT".equals(action)) {
            intentConvertActionLogException = convertActionEmailAccountAddResultIntent(intent);
        } else if ("edm.intent.action.EMAIL_ACCOUNT_DELETE_RESULT".equals(action)) {
            intentConvertActionLogException = convertActionEmailAccountDeleteResultIntent(intent);
        } else if ("com.sec.enterprise.intent.action.BLOCKED_DOMAIN".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 20) {
                intentConvertActionLogException = convertActionBlockedDomainIntent(intent);
            }
        } else if ("com.sec.enterprise.intent.action.UPDATE_FOTA_VERSION_RESULT".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 20) {
                intentConvertActionLogException = convertActionUpdateFotaVersionIntent(intent);
            }
        } else if ("android.intent.action.sec.CBA_INSTALL_STATUS".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() < 15) {
                intentConvertActionLogException = convertCbaInstallStatusIntent(intent);
            }
        } else if ("com.samsung.edm.intent.action.EXCHANGE_CBA_INSTALL_STATUS".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 15) {
                intentConvertActionLogException = convertCbaInstallStatusIntent(intent);
            }
        } else if ("edm.intent.action.EXCHANGE_ACCOUNT_ADD_RESULT".equals(action)) {
            intentConvertActionLogException = convertExchangeAccountAddResultIntent(intent);
        } else if ("edm.intent.action.EXCHANGE_ACCOUNT_DELETE_RESULT".equals(action)) {
            intentConvertActionLogException = convertExchangeAccountDeleteResultIntent(intent);
        } else if ("com.samsung.edm.intent.action.ENFORCE_SMIME_ALIAS_RESULT".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 15) {
                intentConvertActionLogException = convertEnforceSmimeAliasResultIntent(intent);
            }
        } else if ("edm.intent.action.knox_license.status".equals(action)) {
            intentConvertActionLogException = convertKnoxLicenseStatusIntent(intent);
        } else if ("edm.intent.action.license.status".equals(action)) {
            intentConvertActionLogException = convertLicenseStatusIntent(intent);
        } else if ("com.samsung.edm.intent.event.NTP_SERVER_UNREACHABLE".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 17) {
                intentConvertActionLogException = convertNtpServerUnreachableIntent(intent);
            }
        } else if ("edm.intent.action.enable.kiosk.mode.result".equals(action)) {
            intentConvertActionLogException = convertEnableKioskModeResultIntent(intent);
        } else if ("edm.intent.action.disable.kiosk.mode.result".equals(action)) {
            intentConvertActionLogException = convertDisableKioskModeResultIntent(intent);
        } else if ("edm.intent.action.unexpected.kiosk.behavior".equals(action)) {
            intentConvertActionLogException = convertUnexpectedKioskBehaviorIntent(intent);
        } else if ("android.intent.action.sec.SIM_CARD_CHANGED".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() < 15) {
                intentConvertActionLogException = convertSimCardChangedIntent(intent);
            }
        } else if ("com.samsung.edm.intent.action.SIM_CARD_CHANGED".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 15) {
                intentConvertActionLogException = convertSimCardChangedIntent(intent);
            }
        } else if ("com.samsung.action.knox.certenroll.CEP_CERT_ENROLL_STATUS".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 12) {
                intentConvertActionLogException = convertActionCertEnrollStatus(intent);
            }
        } else if ("com.samsung.action.knox.certenroll.CEP_SERVICE_DISCONNECTED".equals(action)) {
            if (EnterpriseDeviceManager.getAPILevel() >= 12) {
                intentConvertActionLogException = convertActionServiceDisconnected(intent);
            }
        } else if ("com.sec.enterprise.knox.intent.action.KNOX_ATTESTATION_RESULT".equals(action)) {
            intentConvertActionLogException = convertActionKnoxAttestationResult(intent);
        } else if ("com.sec.action.NO_USER_ACTIVITY".equals(action)) {
            intentConvertActionLogException = convertActionNoUserActivity(intent);
        } else if ("com.sec.action.USER_ACTIVITY".equals(action)) {
            intentConvertActionLogException = convertActionUserActivity(intent);
        } else if ("com.samsung.android.mdm.VPN_BIND_RESULT".equals(action)) {
            intentConvertActionLogException = convertActionBindResult(intent);
        } else if ("mdm.intent.action.audit.log.critical.size".equals(action)) {
            intentConvertActionLogException = convertActionCriticalSize();
        } else if ("mdm.intent.action.audit.log.full.size".equals(action)) {
            intentConvertActionLogException = convertActionAuditFullSize();
        } else if ("mdm.intent.action.audit.log.maximum.size".equals(action)) {
            intentConvertActionLogException = convertActionAuditMaximumSize();
        } else if ("mdm.intent.action.dump.audit.log.result".equals(action)) {
            intentConvertActionLogException = convertActionDumpLogResult(intent);
        } else if ("mdm.intent.action.audit.log.exception".equals(action)) {
            intentConvertActionLogException = convertActionLogException(intent);
        }
        if (intentConvertActionLogException != null) {
            intentConvertActionLogException.setPackage(context.getPackageName());
            context.sendBroadcast(intentConvertActionLogException);
        }
    }
}

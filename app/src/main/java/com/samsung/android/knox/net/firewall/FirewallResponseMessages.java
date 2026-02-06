package com.samsung.android.knox.net.firewall;

class FirewallResponseMessages {
    static final String ADMINUID_IS_NOT_THE_OWNER = "This administrator can't execute this operation because he is not the owner.";
    static final String CLEAR_NOT_REQUESTED = "Clear was not requested for this RuleType.";
    static final String DEVICE_DOESNT_SUPPORT_IPV6 = "This device does not have IPv6 support for this type of rule.";
    static final String DISABLE_BEFORE_REMOVE = "Disable the rule before remove it.";
    static final String DNS_MISMATCH_DATABASE = "DNS(s) provided doesn't match DNS(s) in database.";
    static final String DOMAIN_REPORT_SUCCESSFULLY_DISABLED = "Domain Report successfully disabled.";
    static final String DOMAIN_REPORT_SUCCESSFULLY_ENABLED = "Domain Report successfully enabled.";
    static final String FAILED_ADD_DNS_DB = "Failed to add DNS(s) to database.";
    static final String FAILED_APPLY_DNS = "Error occurred applying DNS(s)";
    static final String FAILED_DNS_MDM_VERSION = "DNS(s) not yet supported.";
    static final String FAILED_REMOVE_DNS_DATABASE = "Failed to remove DNS(s) from database.";
    static final String FAILED_TO_ADD_RULE_IN_THE_DATABASE = "Failed to add/update rule in the database.";
    static final String FAILED_TO_CHANGE_REPORT_STATUS_ON_DATABASE = "Failed to change Domain Filter report status on database.";
    static final String FAILED_TO_CLEAR_RULES_FROM_DATABASE = "Failed to clear rules from database.";
    static final String FAILED_TO_DISABLE = " failed to disable. Error: ";
    static final String FAILED_TO_ENABLE = " failed to enable. Error: ";
    static final String FAILED_TO_ENABLE_FIREWALL_IN_THE_DATABASE = "Failed to enable Firewall in the database.";
    static final String FAILED_TO_ENABLE_RULE = "Failed to enable rule.";
    static final String FAILED_TO_REMOVE_RULE_FROM_THE_DATABASE = "Failed to remove/update rule from the database.";
    static final String FAIL_TO_DISABLE_FIREWALL_IN_THE_DATABASE = "Fail to disable Firewall in the database.";
    static final String FIREWALL_RULE_NOT_FOUND = "The specified FirewallRule was not found.";
    static final String FIREWALL_SUCCESSFULLY_DISABLED = "The firewall was successfully disabled.";
    static final String FIREWALL_SUCCESSFULLY_ENABLED = "The firewall was successfully enabled.";
    static final String INVALID_APP_IDENTITY = "Invalid AppIdentity object.";
    static final String INVALID_DNS = "Invalid DNS(s) provided";
    static final String INVALID_DOMAIN = "Invalid domain.";
    static final String INVALID_PACKAGE_NAME = "Invalid package name.";
    static final String IS_ARE_INVALID = " is(are) invalid.";
    static final String NO_PARAMETERS_ERROR = "No parameters provided.";
    static final String NO_RULE_SPECIFIED = "No rule was specified.";
    static final String NO_SIGNATURE_ALL_APPS = "There is no signature related to all applications.";
    static final String PACKAGE_NOT_INSTALLED = "The specified package name is not installed.";
    static final String PREVIOUS_SIGNATURE_DOES_NOT_MATCH = "Signature does not match with the previous added.";
    static final String RULES_ALREADY_CLEARED = "The rules are already cleared.";
    static final String RULES_SUCCESSFULLY_CLEARED = "Rules successfully cleared.";
    static final String RULETYPE_IS_NULL = "The specified package name is not installed.";
    static final String RULE_ALREADY_ENABLED = "The specified rule is already enabled.";
    static final String RULE_IS_ALREADY_IN_DATABASE = "The specified rule is already in the database.";
    static final String RULE_IS_NOT_IN_THE_DATABASE = "The rule is not in the database.";
    static final String RULE_IS_NULL = "Rule is null.";
    static final String RULE_SUCCESSFULLY_ADDED = "The rule(s) was successfully added/updated.";
    static final String RULE_SUCCESSFULLY_DISABLED = "The rule was successfully disabled.";
    static final String RULE_SUCCESSFULLY_ENABLED = "The rule was successfully enabled.";
    static final String RULE_SUCCESSFULLY_REMOVED = "The rule was successfuly removed/updated.";
    static final String RULE_WITH_ID = "Rule with Id = ";
    static final String SIGNATURE_DOES_NOT_MATCH = "Given signature does not match with the application.";
    static final String SUCCESSFULLY_DISABLED = " successfully disabled.\n";
    static final String SUCCESSFULLY_ENABLED = " successfully enabled.\n";
    static final String VALIDATION_FAILED = "Failed to validate Rule.";
    static final String VALIDATION_SUCCESS = "Parameters validated successfully";

    FirewallResponseMessages() {
    }
}

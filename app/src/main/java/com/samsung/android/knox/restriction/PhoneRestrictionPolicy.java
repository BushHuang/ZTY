package com.samsung.android.knox.restriction;

public class PhoneRestrictionPolicy {
    public static final int ERROR_SIM_PIN_ALREADY_LOCKED = 4;
    public static final int ERROR_SIM_PIN_ALREADY_LOCKED_BY_ADMIN = 11;
    public static final int ERROR_SIM_PIN_ALREADY_UNLOCKED = 5;
    public static final int ERROR_SIM_PIN_BLOCKED_BY_PUK = 6;
    public static final int ERROR_SIM_PIN_DATABASE = 10;
    public static final int ERROR_SIM_PIN_FAILED = 1;
    public static final int ERROR_SIM_PIN_ID_NOT_READY = 9;
    public static final int ERROR_SIM_PIN_INCORRECT_CODE = 3;
    public static final int ERROR_SIM_PIN_INVALID_CODE = 2;
    public static final int ERROR_SIM_PIN_MAX_RETRIES_EXCEEDED = 8;
    public static final int ERROR_SIM_PIN_NONE = 0;
    public static final int ERROR_SIM_PIN_OWNED_BY_OTHER_ADMIN = 12;
    public static final int ERROR_SIM_PIN_UNKNOWN = 100;
    public static final int LIMIT_NUMBER_OF_CALLS_BY_DAY = 0;
    public static final int LIMIT_NUMBER_OF_CALLS_BY_MONTH = 2;
    public static final int LIMIT_NUMBER_OF_CALLS_BY_WEEK = 1;
    public static final int LIMIT_NUMBER_OF_DATA_CALLS_BY_DAY = 0;
    public static final int LIMIT_NUMBER_OF_DATA_CALLS_BY_MONTH = 2;
    public static final int LIMIT_NUMBER_OF_DATA_CALLS_BY_WEEK = 1;
    public static final int LIMIT_NUMBER_OF_SMS_BY_DAY = 0;
    public static final int LIMIT_NUMBER_OF_SMS_BY_MONTH = 2;
    public static final int LIMIT_NUMBER_OF_SMS_BY_WEEK = 1;
    private android.app.enterprise.PhoneRestrictionPolicy mPhoneRestrictionPolicy;

    public PhoneRestrictionPolicy(android.app.enterprise.PhoneRestrictionPolicy phoneRestrictionPolicy) {
        this.mPhoneRestrictionPolicy = phoneRestrictionPolicy;
    }

    public boolean addIncomingCallExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.addIncomingCallExceptionPattern(str);
    }

    public boolean addIncomingCallRestriction(String str) {
        return this.mPhoneRestrictionPolicy.addIncomingCallRestriction(str);
    }

    public boolean addIncomingSmsExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.addIncomingSmsExceptionPattern(str);
    }

    public boolean addIncomingSmsRestriction(String str) {
        return this.mPhoneRestrictionPolicy.addIncomingSmsRestriction(str);
    }

    public boolean addOutgoingCallExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.addOutgoingCallExceptionPattern(str);
    }

    public boolean addOutgoingCallRestriction(String str) {
        return this.mPhoneRestrictionPolicy.addOutgoingCallRestriction(str);
    }

    public boolean addOutgoingSmsExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.addOutgoingSmsExceptionPattern(str);
    }

    public boolean addOutgoingSmsRestriction(String str) {
        return this.mPhoneRestrictionPolicy.addOutgoingSmsRestriction(str);
    }

    public boolean allowCallerIDDisplay(boolean z) {
        return this.mPhoneRestrictionPolicy.allowCallerIDDisplay(z);
    }

    public boolean allowCopyContactToSim(boolean z) {
        return this.mPhoneRestrictionPolicy.allowCopyContactToSim(z);
    }

    public boolean allowIncomingMms(boolean z) {
        return this.mPhoneRestrictionPolicy.allowIncomingMms(z);
    }

    public boolean allowIncomingSms(boolean z) {
        return this.mPhoneRestrictionPolicy.allowIncomingSms(z);
    }

    public boolean allowOutgoingMms(boolean z) {
        return this.mPhoneRestrictionPolicy.allowOutgoingMms(z);
    }

    public boolean allowOutgoingSms(boolean z) {
        return this.mPhoneRestrictionPolicy.allowOutgoingSms(z);
    }

    public boolean allowWapPush(boolean z) {
        return this.mPhoneRestrictionPolicy.allowWapPush(z);
    }

    public boolean blockMmsWithStorage(boolean z) {
        return this.mPhoneRestrictionPolicy.blockMmsWithStorage(z);
    }

    public boolean blockSmsWithStorage(boolean z) {
        return this.mPhoneRestrictionPolicy.blockSmsWithStorage(z);
    }

    public boolean canIncomingCall(String str) {
        return this.mPhoneRestrictionPolicy.canIncomingCall(str);
    }

    public boolean canIncomingSms(String str) {
        return this.mPhoneRestrictionPolicy.canIncomingSms(str);
    }

    public boolean canOutgoingCall(String str) {
        return this.mPhoneRestrictionPolicy.canOutgoingCall(str);
    }

    public boolean canOutgoingSms(String str) {
        return this.mPhoneRestrictionPolicy.canOutgoingSms(str);
    }

    public int changeSimPinCode(String str, String str2) {
        return this.mPhoneRestrictionPolicy.changeSimPinCode(str, str2);
    }

    public boolean clearStoredBlockedMms() {
        return this.mPhoneRestrictionPolicy.clearStoredBlockedMms();
    }

    public boolean clearStoredBlockedSms() {
        return this.mPhoneRestrictionPolicy.clearStoredBlockedSms();
    }

    public int disableSimPinLock(String str) {
        return this.mPhoneRestrictionPolicy.disableSimPinLock(str);
    }

    public boolean enableLimitNumberOfCalls(boolean z) {
        return this.mPhoneRestrictionPolicy.enableLimitNumberOfCalls(z);
    }

    public boolean enableLimitNumberOfSms(boolean z) {
        return this.mPhoneRestrictionPolicy.enableLimitNumberOfSms(z);
    }

    public int enableSimPinLock(String str) {
        return this.mPhoneRestrictionPolicy.enableSimPinLock(str);
    }

    public boolean getDataCallLimitEnabled() {
        return this.mPhoneRestrictionPolicy.getDataCallLimitEnabled();
    }

    public boolean getEmergencyCallOnly(boolean z) {
        return this.mPhoneRestrictionPolicy.getEmergencyCallOnly(z);
    }

    public String getIncomingCallExceptionPatterns() {
        return this.mPhoneRestrictionPolicy.getIncomingCallExceptionPatterns();
    }

    public String getIncomingCallRestriction(boolean z) {
        return this.mPhoneRestrictionPolicy.getIncomingCallRestriction(z);
    }

    public String getIncomingSmsExceptionPatterns() {
        return this.mPhoneRestrictionPolicy.getIncomingSmsExceptionPatterns();
    }

    public String getIncomingSmsRestriction(boolean z) {
        return this.mPhoneRestrictionPolicy.getIncomingSmsRestriction(z);
    }

    public long getLimitOfDataCalls(int i) {
        return this.mPhoneRestrictionPolicy.getLimitOfDataCalls(i);
    }

    public int getLimitOfIncomingCalls(int i) {
        return this.mPhoneRestrictionPolicy.getLimitOfIncomingCalls(i);
    }

    public int getLimitOfIncomingSms(int i) {
        return this.mPhoneRestrictionPolicy.getLimitOfIncomingSms(i);
    }

    public int getLimitOfOutgoingCalls(int i) {
        return this.mPhoneRestrictionPolicy.getLimitOfOutgoingCalls(i);
    }

    public int getLimitOfOutgoingSms(int i) {
        return this.mPhoneRestrictionPolicy.getLimitOfOutgoingSms(i);
    }

    public String getOutgoingCallExceptionPatterns() {
        return this.mPhoneRestrictionPolicy.getOutgoingCallExceptionPatterns();
    }

    public String getOutgoingCallRestriction(boolean z) {
        return this.mPhoneRestrictionPolicy.getOutgoingCallRestriction(z);
    }

    public String getOutgoingSmsExceptionPatterns() {
        return this.mPhoneRestrictionPolicy.getOutgoingSmsExceptionPatterns();
    }

    public String getOutgoingSmsRestriction(boolean z) {
        return this.mPhoneRestrictionPolicy.getOutgoingSmsRestriction(z);
    }

    public boolean isBlockMmsWithStorageEnabled() {
        return this.mPhoneRestrictionPolicy.isBlockMmsWithStorageEnabled();
    }

    public boolean isBlockSmsWithStorageEnabled() {
        return this.mPhoneRestrictionPolicy.isBlockSmsWithStorageEnabled();
    }

    public boolean isCallerIDDisplayAllowed() {
        return this.mPhoneRestrictionPolicy.isCallerIDDisplayAllowed();
    }

    public boolean isCopyContactToSimAllowed() {
        return this.mPhoneRestrictionPolicy.isCopyContactToSimAllowed();
    }

    public boolean isIncomingMmsAllowed() {
        return this.mPhoneRestrictionPolicy.isIncomingMmsAllowed();
    }

    public boolean isIncomingSmsAllowed() {
        return this.mPhoneRestrictionPolicy.isIncomingSmsAllowed();
    }

    public boolean isLimitNumberOfCallsEnabled() {
        return this.mPhoneRestrictionPolicy.isLimitNumberOfCallsEnabled();
    }

    public boolean isLimitNumberOfSmsEnabled() {
        return this.mPhoneRestrictionPolicy.isLimitNumberOfSmsEnabled();
    }

    public boolean isOutgoingMmsAllowed() {
        return this.mPhoneRestrictionPolicy.isOutgoingMmsAllowed();
    }

    public boolean isOutgoingSmsAllowed() {
        return this.mPhoneRestrictionPolicy.isOutgoingSmsAllowed();
    }

    public boolean isWapPushAllowed() {
        return this.mPhoneRestrictionPolicy.isWapPushAllowed();
    }

    public boolean removeIncomingCallExceptionPattern() {
        return this.mPhoneRestrictionPolicy.removeIncomingCallExceptionPattern();
    }

    public boolean removeIncomingCallRestriction() {
        return this.mPhoneRestrictionPolicy.removeIncomingCallRestriction();
    }

    public boolean removeIncomingSmsExceptionPattern() {
        return this.mPhoneRestrictionPolicy.removeIncomingSmsExceptionPattern();
    }

    public boolean removeIncomingSmsRestriction() {
        return this.mPhoneRestrictionPolicy.removeIncomingSmsRestriction();
    }

    public boolean removeOutgoingCallExceptionPattern() {
        return this.mPhoneRestrictionPolicy.removeOutgoingCallExceptionPattern();
    }

    public boolean removeOutgoingCallRestriction() {
        return this.mPhoneRestrictionPolicy.removeOutgoingCallRestriction();
    }

    public boolean removeOutgoingSmsExceptionPattern() {
        return this.mPhoneRestrictionPolicy.removeOutgoingSmsExceptionPattern();
    }

    public boolean removeOutgoingSmsRestriction() {
        return this.mPhoneRestrictionPolicy.removeOutgoingSmsRestriction();
    }

    public boolean resetCallsCount() {
        return this.mPhoneRestrictionPolicy.resetCallsCount();
    }

    public boolean resetDataCallLimitCounter() {
        return this.mPhoneRestrictionPolicy.resetDataCallLimitCounter();
    }

    public boolean resetSmsCount() {
        return this.mPhoneRestrictionPolicy.resetSmsCount();
    }

    public boolean setDataCallLimitEnabled(boolean z) {
        return this.mPhoneRestrictionPolicy.setDataCallLimitEnabled(z);
    }

    public boolean setEmergencyCallOnly(boolean z) {
        return this.mPhoneRestrictionPolicy.setEmergencyCallOnly(z);
    }

    public boolean setIncomingCallExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.setIncomingCallExceptionPattern(str);
    }

    public boolean setIncomingCallRestriction(String str) {
        return this.mPhoneRestrictionPolicy.setIncomingCallRestriction(str);
    }

    public boolean setIncomingSmsExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.setIncomingSmsExceptionPattern(str);
    }

    public boolean setIncomingSmsRestriction(String str) {
        return this.mPhoneRestrictionPolicy.setIncomingSmsRestriction(str);
    }

    public boolean setLimitOfDataCalls(long j, long j2, long j3) {
        return this.mPhoneRestrictionPolicy.setLimitOfDataCalls(j, j2, j3);
    }

    public boolean setLimitOfIncomingCalls(int i, int i2, int i3) {
        return this.mPhoneRestrictionPolicy.setLimitOfIncomingCalls(i, i2, i3);
    }

    public boolean setLimitOfIncomingSms(int i, int i2, int i3) {
        return this.mPhoneRestrictionPolicy.setLimitOfIncomingSms(i, i2, i3);
    }

    public boolean setLimitOfOutgoingCalls(int i, int i2, int i3) {
        return this.mPhoneRestrictionPolicy.setLimitOfOutgoingCalls(i, i2, i3);
    }

    public boolean setLimitOfOutgoingSms(int i, int i2, int i3) {
        return this.mPhoneRestrictionPolicy.setLimitOfOutgoingSms(i, i2, i3);
    }

    public boolean setOutgoingCallExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.setOutgoingCallExceptionPattern(str);
    }

    public boolean setOutgoingCallRestriction(String str) {
        return this.mPhoneRestrictionPolicy.setOutgoingCallRestriction(str);
    }

    public boolean setOutgoingSmsExceptionPattern(String str) {
        return this.mPhoneRestrictionPolicy.setOutgoingSmsExceptionPattern(str);
    }

    public boolean setOutgoingSmsRestriction(String str) {
        return this.mPhoneRestrictionPolicy.setOutgoingSmsRestriction(str);
    }
}

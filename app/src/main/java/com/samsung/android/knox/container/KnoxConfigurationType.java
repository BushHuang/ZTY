package com.samsung.android.knox.container;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KnoxConfigurationType implements Parcelable {
    public static final Parcelable.Creator<KnoxConfigurationType> CREATOR = new Parcelable.Creator<KnoxConfigurationType>() {
        @Override
        public KnoxConfigurationType createFromParcel(Parcel parcel) {
            return new KnoxConfigurationType(parcel);
        }

        @Override
        public KnoxConfigurationType[] newArray(int i) {
            Log.d("KnoxConfigurationType", "KnoxConfigurationType[] array to be created");
            return new KnoxConfigurationType[i];
        }
    };
    private static final String TAG = "KnoxConfigurationType";
    private static final int fingerprint = 1;
    private static final int iris = 2;
    private HashMap<String, List<Pair<String, String>>> mAllowChangeDataSettings;
    protected boolean mAllowMultiwindowMode;
    private boolean mAllowSwitch;
    protected boolean mAllowTaskManager;
    protected boolean mAllowUSBDebugging;
    protected List<String> mAppInstallationList;
    private AuthenticationConfig mAuthenticationConfig;
    protected int mBiometricAuthValue;
    protected String mCustomBadgeIcon;
    protected String mCustomHomeScreenWallpaper;
    protected String mCustomLockScreenWallpaper;
    protected String mCustomStatusIcon;
    protected String mCustomStatusLabel;
    protected boolean mEC;
    protected String mECBadge;
    protected String mECIcon;
    protected String mECName;
    protected List<String> mForbiddenStrings;
    protected List<String> mGoogleAppsList;
    protected boolean mIsBiometricAuthEnabled;
    private boolean mIsDefaultConfigType;
    protected int mKeyguardDisabledFeatures;
    private int mLayoutType;
    private boolean mManagedType;
    protected int mMaximumCharacterOccurences;
    protected int mMaximumCharacterSequenceLength;
    protected int mMaximumFailedPasswordsForWipe;
    protected int mMaximumNumericSequenceLength;
    protected int mMaximumTimeToLock;
    protected boolean mMultifactorAuthEnabled;
    protected String mName;
    protected String mNameIcon;
    protected int mPasswordMinimumLength;
    protected int mPasswordMinimumLetters;
    protected int mPasswordMinimumLowerCase;
    protected int mPasswordMinimumNonLetters;
    protected int mPasswordMinimumNumeric;
    protected int mPasswordMinimumSymbols;
    protected int mPasswordMinimumUpperCase;
    protected String mPasswordPattern;
    protected int mPasswordQuality;
    protected List<String> mProtectedList;
    private HashMap<String, List<Pair<String, String>>> mRCPDataSettings;
    private HashMap<String, List<Pair<String, String>>> mRCPNotifSettings;
    protected boolean mSimplePasswordEnabled;
    private String mVersion;

    public KnoxConfigurationType() {
        this.mPasswordMinimumNonLetters = 0;
        this.mPasswordMinimumLetters = 0;
        this.mPasswordMinimumNumeric = 0;
        this.mPasswordMinimumUpperCase = 0;
        this.mPasswordMinimumLowerCase = 0;
        this.mPasswordMinimumSymbols = 0;
        this.mPasswordQuality = 0;
        this.mMaximumFailedPasswordsForWipe = 0;
        this.mMaximumCharacterOccurences = 0;
        this.mMaximumCharacterSequenceLength = 0;
        this.mMaximumNumericSequenceLength = 0;
        this.mPasswordMinimumLength = 0;
        this.mMaximumTimeToLock = 0;
        this.mPasswordPattern = null;
        this.mName = null;
        this.mCustomHomeScreenWallpaper = null;
        this.mEC = false;
        this.mNameIcon = null;
        this.mECName = null;
        this.mECIcon = null;
        this.mECBadge = null;
        this.mCustomLockScreenWallpaper = null;
        this.mAppInstallationList = new ArrayList();
        this.mLayoutType = -1;
        this.mAllowSwitch = true;
        this.mAuthenticationConfig = new AuthenticationConfig();
        this.mForbiddenStrings = new ArrayList();
        this.mMultifactorAuthEnabled = false;
        this.mIsBiometricAuthEnabled = false;
        this.mBiometricAuthValue = -1;
        this.mKeyguardDisabledFeatures = -1;
        this.mRCPDataSettings = new HashMap<>();
        this.mAllowChangeDataSettings = new HashMap<>();
        this.mVersion = "custom";
        this.mManagedType = false;
        this.mSimplePasswordEnabled = true;
        this.mAllowMultiwindowMode = true;
        this.mAllowTaskManager = true;
        this.mAllowUSBDebugging = false;
        this.mIsDefaultConfigType = false;
        this.mCustomBadgeIcon = null;
        this.mCustomStatusLabel = null;
        this.mCustomStatusIcon = null;
        this.mProtectedList = new ArrayList();
        this.mGoogleAppsList = new ArrayList();
        this.mRCPNotifSettings = new HashMap<>();
    }

    public KnoxConfigurationType(Parcel parcel) {
        this.mPasswordMinimumNonLetters = 0;
        this.mPasswordMinimumLetters = 0;
        this.mPasswordMinimumNumeric = 0;
        this.mPasswordMinimumUpperCase = 0;
        this.mPasswordMinimumLowerCase = 0;
        this.mPasswordMinimumSymbols = 0;
        this.mPasswordQuality = 0;
        this.mMaximumFailedPasswordsForWipe = 0;
        this.mMaximumCharacterOccurences = 0;
        this.mMaximumCharacterSequenceLength = 0;
        this.mMaximumNumericSequenceLength = 0;
        this.mPasswordMinimumLength = 0;
        this.mMaximumTimeToLock = 0;
        String str = null;
        this.mPasswordPattern = null;
        this.mName = null;
        this.mCustomHomeScreenWallpaper = null;
        this.mEC = false;
        this.mNameIcon = null;
        this.mECName = null;
        this.mECIcon = null;
        this.mECBadge = null;
        this.mCustomLockScreenWallpaper = null;
        this.mAppInstallationList = new ArrayList();
        this.mLayoutType = -1;
        this.mAllowSwitch = true;
        this.mAuthenticationConfig = new AuthenticationConfig();
        this.mForbiddenStrings = new ArrayList();
        this.mMultifactorAuthEnabled = false;
        this.mIsBiometricAuthEnabled = false;
        this.mBiometricAuthValue = -1;
        this.mKeyguardDisabledFeatures = -1;
        this.mRCPDataSettings = new HashMap<>();
        this.mAllowChangeDataSettings = new HashMap<>();
        this.mVersion = "custom";
        this.mManagedType = false;
        this.mSimplePasswordEnabled = true;
        this.mAllowMultiwindowMode = true;
        this.mAllowTaskManager = true;
        this.mAllowUSBDebugging = false;
        this.mIsDefaultConfigType = false;
        this.mCustomBadgeIcon = null;
        this.mCustomStatusLabel = null;
        this.mCustomStatusIcon = null;
        this.mProtectedList = new ArrayList();
        this.mGoogleAppsList = new ArrayList();
        this.mRCPNotifSettings = new HashMap<>();
        this.mName = parcel.readString();
        String string = parcel.readString();
        if (string != null) {
            this.mVersion = string;
        } else {
            this.mVersion = "custom";
        }
        this.mPasswordMinimumNonLetters = parcel.readInt();
        this.mPasswordMinimumLetters = parcel.readInt();
        this.mPasswordMinimumNumeric = parcel.readInt();
        this.mPasswordMinimumUpperCase = parcel.readInt();
        this.mPasswordMinimumLowerCase = parcel.readInt();
        this.mPasswordMinimumSymbols = parcel.readInt();
        this.mPasswordQuality = parcel.readInt();
        this.mMaximumTimeToLock = parcel.readInt();
        this.mMaximumFailedPasswordsForWipe = parcel.readInt();
        this.mManagedType = parcel.readInt() == 1;
        String string2 = parcel.readString();
        this.mCustomBadgeIcon = (string2 == null || string2.isEmpty()) ? null : string2;
        String string3 = parcel.readString();
        this.mCustomHomeScreenWallpaper = (string3 == null || string3.isEmpty()) ? null : string3;
        this.mEC = parcel.readInt() == 1;
        Log.d("KnoxConfigurationType", "reading from parcel mEC " + this.mEC);
        String string4 = parcel.readString();
        this.mNameIcon = (string4 == null || string4.isEmpty()) ? null : string4;
        String string5 = parcel.readString();
        this.mECName = (string5 == null || string5.isEmpty()) ? null : string5;
        String string6 = parcel.readString();
        this.mECIcon = (string6 == null || string6.isEmpty()) ? null : string6;
        String string7 = parcel.readString();
        this.mECBadge = (string7 == null || string7.isEmpty()) ? null : string7;
        String string8 = parcel.readString();
        this.mCustomLockScreenWallpaper = (string8 == null || string8.isEmpty()) ? null : string8;
        String string9 = parcel.readString();
        this.mCustomStatusLabel = (string9 == null || string9.isEmpty()) ? null : string9;
        String string10 = parcel.readString();
        this.mCustomStatusIcon = (string10 == null || string10.isEmpty()) ? null : string10;
        parcel.readStringList(this.mAppInstallationList);
        parcel.readStringList(this.mForbiddenStrings);
        parcel.readStringList(this.mProtectedList);
        parcel.readStringList(this.mGoogleAppsList);
        this.mMaximumCharacterOccurences = parcel.readInt();
        this.mMaximumCharacterSequenceLength = parcel.readInt();
        this.mMaximumNumericSequenceLength = parcel.readInt();
        this.mPasswordMinimumLength = parcel.readInt();
        String string11 = parcel.readString();
        if (string11 != null && !string11.isEmpty()) {
            str = string11;
        }
        this.mPasswordPattern = str;
        this.mSimplePasswordEnabled = parcel.readInt() == 1;
        this.mMultifactorAuthEnabled = parcel.readInt() == 1;
        this.mAllowMultiwindowMode = parcel.readInt() == 1;
        this.mAllowTaskManager = parcel.readInt() == 1;
        this.mIsBiometricAuthEnabled = parcel.readInt() == 1;
        this.mBiometricAuthValue = parcel.readInt();
        this.mAllowUSBDebugging = parcel.readInt() == 1;
        this.mLayoutType = parcel.readInt();
        this.mAllowSwitch = parcel.readInt() == 1;
        this.mIsDefaultConfigType = parcel.readInt() == 1;
        deserializeRCPSettings(parcel, this.mRCPDataSettings);
        deserializeRCPSettings(parcel, this.mAllowChangeDataSettings);
        deserializeRCPSettings(parcel, this.mRCPNotifSettings);
        this.mAuthenticationConfig = (AuthenticationConfig) parcel.readParcelable(AuthenticationConfig.class.getClassLoader());
        this.mKeyguardDisabledFeatures = parcel.readInt();
    }

    static KnoxConfigurationType convertToNew(KnoxConfigurationType knoxConfigurationType, com.sec.enterprise.knox.container.KnoxConfigurationType knoxConfigurationType2) {
        if (knoxConfigurationType2 == null) {
            return null;
        }
        knoxConfigurationType.setName(knoxConfigurationType2.getName());
        knoxConfigurationType.setPasswordMinimumNonLetters(knoxConfigurationType2.getPasswordMinimumNonLetters());
        knoxConfigurationType.setPasswordMinimumLetters(knoxConfigurationType2.getPasswordMinimumLetters());
        knoxConfigurationType.setPasswordMinimumNumeric(knoxConfigurationType2.getPasswordMinimumNumeric());
        knoxConfigurationType.setPasswordMinimumUpperCase(knoxConfigurationType2.getPasswordMinimumUpperCase());
        knoxConfigurationType.setPasswordMinimumLowerCase(knoxConfigurationType2.getPasswordMinimumLowerCase());
        knoxConfigurationType.setPasswordMinimumSymbols(knoxConfigurationType2.getPasswordMinimumSymbols());
        knoxConfigurationType.setPasswordQuality(knoxConfigurationType2.getPasswordQuality());
        knoxConfigurationType.setMaximumTimeToLock(knoxConfigurationType2.getMaximumTimeToLock());
        knoxConfigurationType.setMaximumFailedPasswordsForWipe(knoxConfigurationType2.getMaximumFailedPasswordsForWipe());
        knoxConfigurationType.setCustomHomeScreenWallpaper(knoxConfigurationType2.getCustomHomeScreenWallpaper());
        try {
            knoxConfigurationType.setCustomizedContainerNameIcon(knoxConfigurationType2.getCustomizedContainerNameIcon());
            knoxConfigurationType.setCustomizedContainerEnabled(knoxConfigurationType2.isCustomizedContainerEnabled());
            knoxConfigurationType.setCustomizedContainerName(knoxConfigurationType2.getCustomizedContainerName());
            knoxConfigurationType.setCustomizedContainerIcon(knoxConfigurationType2.getCustomizedContainerIcon());
            knoxConfigurationType.setCustomizedContainerBadge(knoxConfigurationType2.getCustomizedContainerBadge());
        } catch (NoSuchMethodError unused) {
        }
        try {
            knoxConfigurationType.setCustomLockScreenWallpaper(knoxConfigurationType2.getCustomLockScreenWallpaper());
        } catch (NoSuchMethodError unused2) {
        }
        knoxConfigurationType.setAppInstallationList(knoxConfigurationType2.getAppInstallationList());
        knoxConfigurationType.setForbiddenStrings(knoxConfigurationType2.getForbiddenStrings());
        knoxConfigurationType.setMaximumCharacterOccurences(knoxConfigurationType2.getMaximumCharacterOccurences());
        knoxConfigurationType.setMaximumCharacterSequenceLength(knoxConfigurationType2.getMaximumCharacterSequenceLength());
        knoxConfigurationType.setMaximumNumericSequenceLength(knoxConfigurationType2.getMaximumNumericSequenceLength());
        knoxConfigurationType.setPasswordMinimumLength(knoxConfigurationType2.getPasswordMinimumLength());
        try {
            knoxConfigurationType.enforceMultifactorAuthentication(knoxConfigurationType2.isMultifactorAuthenticationEnforced());
        } catch (NoSuchMethodError unused3) {
        }
        try {
            knoxConfigurationType.setRequiredPasswordPattern(knoxConfigurationType2.getRequiredPwdPatternRestrictions());
        } catch (NoSuchMethodError unused4) {
        }
        try {
            knoxConfigurationType.setBiometricAuthenticationEnabled(1, knoxConfigurationType2.isBiometricAuthenticationEnabled(1));
            knoxConfigurationType.setBiometricAuthenticationEnabled(2, knoxConfigurationType2.isBiometricAuthenticationEnabled(2));
        } catch (NoSuchMethodError unused5) {
        }
        try {
            knoxConfigurationType.setContainerLayout(knoxConfigurationType2.getContainerLayout());
            knoxConfigurationType.allowLayoutSwitching(knoxConfigurationType2.isLayoutSwitchingAllowed());
            knoxConfigurationType.setDefaultConfigType(knoxConfigurationType2.isDefaultConfigType());
        } catch (NoSuchMethodError unused6) {
        }
        try {
            knoxConfigurationType.setKeyguardDisabledFeatures(knoxConfigurationType2.getKeyguardDisabledFeatures());
            knoxConfigurationType.setEnterpriseIdentityAuthentication(AuthenticationConfig.convertToNew(knoxConfigurationType2.getEnterpriseIdentityAuthentication()));
        } catch (NoSuchMethodError unused7) {
        }
        knoxConfigurationType.mRCPDataSettings = knoxConfigurationType.getDataSyncPolicy();
        knoxConfigurationType.mAllowChangeDataSettings = knoxConfigurationType.getAllowChangeDataSyncPolicy();
        knoxConfigurationType.setManagedType(knoxConfigurationType2.getManagedType());
        knoxConfigurationType.setCustomBadgeIcon(knoxConfigurationType2.getCustomBadgeIcon());
        knoxConfigurationType.setCustomStatusLabel(knoxConfigurationType2.getCustomStatusLabel());
        knoxConfigurationType.setCustomStatusIcon(knoxConfigurationType2.getCustomStatusIcon());
        knoxConfigurationType.setSimplePasswordEnabled(knoxConfigurationType2.getSimplePasswordEnabled());
        knoxConfigurationType.allowTaskManager(knoxConfigurationType2.isTaskManagerAllowed());
        knoxConfigurationType.allowMultiwindowMode(knoxConfigurationType2.isMultiwindowModeAllowed());
        knoxConfigurationType.mRCPNotifSettings = knoxConfigurationType.getNotificationSyncPolicy();
        try {
            knoxConfigurationType.setVersion(knoxConfigurationType2.getVersion());
        } catch (NoSuchMethodError unused8) {
        }
        try {
            knoxConfigurationType.setProtectedPackageList(knoxConfigurationType2.getProtectedPackageList());
        } catch (NoSuchMethodError unused9) {
        }
        try {
            knoxConfigurationType.setGoogleAppsList(knoxConfigurationType2.getGoogleAppsList());
        } catch (NoSuchMethodError unused10) {
        }
        try {
            knoxConfigurationType.allowUSBDebugging(knoxConfigurationType2.isUSBDebuggingAllowed());
        } catch (NoSuchMethodError unused11) {
        }
        return knoxConfigurationType;
    }

    static KnoxConfigurationType convertToNew(com.sec.enterprise.knox.container.KnoxConfigurationType knoxConfigurationType) {
        if (knoxConfigurationType == null) {
            return null;
        }
        if (knoxConfigurationType instanceof com.sec.enterprise.knox.container.LightweightConfigurationType) {
            return LightweightConfigurationType.convertToNew((com.sec.enterprise.knox.container.LightweightConfigurationType) knoxConfigurationType);
        }
        if (knoxConfigurationType instanceof com.sec.enterprise.knox.container.ContainerModeConfigurationType) {
            return ContainerModeConfigurationType.convertToNew((com.sec.enterprise.knox.container.ContainerModeConfigurationType) knoxConfigurationType);
        }
        KnoxConfigurationType knoxConfigurationType2 = new KnoxConfigurationType();
        convertToNew(knoxConfigurationType2, knoxConfigurationType);
        return knoxConfigurationType2;
    }

    static List<KnoxConfigurationType> convertToNewList(List<com.sec.enterprise.knox.container.KnoxConfigurationType> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<com.sec.enterprise.knox.container.KnoxConfigurationType> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static com.sec.enterprise.knox.container.KnoxConfigurationType convertToOld(KnoxConfigurationType knoxConfigurationType) throws NoSuchMethodError, NoClassDefFoundError {
        if (knoxConfigurationType == null) {
            return null;
        }
        if (knoxConfigurationType instanceof LightweightConfigurationType) {
            return LightweightConfigurationType.convertToOld((LightweightConfigurationType) knoxConfigurationType);
        }
        if (knoxConfigurationType instanceof ContainerModeConfigurationType) {
            return ContainerModeConfigurationType.convertToOld((ContainerModeConfigurationType) knoxConfigurationType);
        }
        com.sec.enterprise.knox.container.KnoxConfigurationType knoxConfigurationType2 = new com.sec.enterprise.knox.container.KnoxConfigurationType();
        convertToOld(knoxConfigurationType, knoxConfigurationType2);
        return knoxConfigurationType2;
    }

    static com.sec.enterprise.knox.container.KnoxConfigurationType convertToOld(KnoxConfigurationType knoxConfigurationType, com.sec.enterprise.knox.container.KnoxConfigurationType knoxConfigurationType2) throws NoSuchMethodError, NoClassDefFoundError {
        if (knoxConfigurationType == null) {
            return null;
        }
        knoxConfigurationType2.setName(knoxConfigurationType.getName());
        knoxConfigurationType2.setPasswordMinimumNonLetters(knoxConfigurationType.getPasswordMinimumNonLetters());
        knoxConfigurationType2.setPasswordMinimumLetters(knoxConfigurationType.getPasswordMinimumLetters());
        knoxConfigurationType2.setPasswordMinimumNumeric(knoxConfigurationType.getPasswordMinimumNumeric());
        knoxConfigurationType2.setPasswordMinimumUpperCase(knoxConfigurationType.getPasswordMinimumUpperCase());
        knoxConfigurationType2.setPasswordMinimumLowerCase(knoxConfigurationType.getPasswordMinimumLowerCase());
        knoxConfigurationType2.setPasswordMinimumSymbols(knoxConfigurationType.getPasswordMinimumSymbols());
        knoxConfigurationType2.setPasswordQuality(knoxConfigurationType.getPasswordQuality());
        knoxConfigurationType2.setMaximumTimeToLock(knoxConfigurationType.getMaximumTimeToLock());
        knoxConfigurationType2.setMaximumFailedPasswordsForWipe(knoxConfigurationType.getMaximumFailedPasswordsForWipe());
        knoxConfigurationType2.setCustomHomeScreenWallpaper(knoxConfigurationType.getCustomHomeScreenWallpaper());
        try {
            knoxConfigurationType2.setBiometricAuthenticationEnabled(1, knoxConfigurationType.isBiometricAuthenticationEnabled(1));
            knoxConfigurationType2.setBiometricAuthenticationEnabled(2, knoxConfigurationType.isBiometricAuthenticationEnabled(2));
        } catch (NoSuchMethodError unused) {
            if (knoxConfigurationType.isBiometricAuthenticationEnabled(1) || knoxConfigurationType.isBiometricAuthenticationEnabled(2)) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setBiometricAuthenticationEnabled", new Class[]{Integer.TYPE, Boolean.TYPE}, 14));
            }
        }
        try {
            knoxConfigurationType2.setCustomizedContainerNameIcon(knoxConfigurationType.getCustomizedContainerNameIcon());
        } catch (NoSuchMethodError unused2) {
            if (knoxConfigurationType.getCustomizedContainerNameIcon() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setCustomizedContainerNameIcon", new Class[]{String.class}, 20));
            }
        }
        try {
            knoxConfigurationType2.setCustomizedContainerEnabled(knoxConfigurationType.isCustomizedContainerEnabled());
        } catch (NoSuchMethodError unused3) {
            if (knoxConfigurationType.isCustomizedContainerEnabled()) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setCustomizedContainerEnabled", new Class[]{Boolean.TYPE}, 20));
            }
        }
        try {
            knoxConfigurationType2.setCustomizedContainerName(knoxConfigurationType.getCustomizedContainerName());
        } catch (NoSuchMethodError unused4) {
            if (knoxConfigurationType.getCustomizedContainerName() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setCustomizedContainerName", new Class[]{String.class}, 20));
            }
        }
        try {
            knoxConfigurationType2.setCustomizedContainerIcon(knoxConfigurationType.getCustomizedContainerIcon());
        } catch (NoSuchMethodError unused5) {
            if (knoxConfigurationType.getCustomizedContainerIcon() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setCustomizedContainerIcon", new Class[]{String.class}, 20));
            }
        }
        try {
            knoxConfigurationType2.setCustomizedContainerBadge(knoxConfigurationType.getCustomizedContainerBadge());
        } catch (NoSuchMethodError unused6) {
            if (knoxConfigurationType.getCustomizedContainerBadge() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setCustomizedContainerBadge", new Class[]{String.class}, 20));
            }
        }
        try {
            knoxConfigurationType2.setCustomLockScreenWallpaper(knoxConfigurationType.getCustomLockScreenWallpaper());
        } catch (NoSuchMethodError unused7) {
            if (knoxConfigurationType.getCustomLockScreenWallpaper() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setCustomLockScreenWallpaper", new Class[]{String.class}, 20));
            }
        }
        knoxConfigurationType2.setAppInstallationList(knoxConfigurationType.getAppInstallationList());
        knoxConfigurationType2.setForbiddenStrings(knoxConfigurationType.getForbiddenStrings());
        knoxConfigurationType2.setMaximumCharacterOccurences(knoxConfigurationType.getMaximumCharacterOccurences());
        knoxConfigurationType2.setMaximumCharacterSequenceLength(knoxConfigurationType.getMaximumCharacterSequenceLength());
        knoxConfigurationType2.setMaximumNumericSequenceLength(knoxConfigurationType.getMaximumNumericSequenceLength());
        knoxConfigurationType2.setPasswordMinimumLength(knoxConfigurationType.getPasswordMinimumLength());
        try {
            knoxConfigurationType2.enforceMultifactorAuthentication(knoxConfigurationType.isMultifactorAuthenticationEnforced());
        } catch (NoSuchMethodError unused8) {
            if (knoxConfigurationType.isMultifactorAuthenticationEnforced()) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "enforceMultifactorAuthentication", new Class[]{Boolean.TYPE}, 13));
            }
        }
        try {
            knoxConfigurationType2.setRequiredPasswordPattern(knoxConfigurationType.getRequiredPwdPatternRestrictions());
        } catch (NoSuchMethodError unused9) {
            if (knoxConfigurationType.getRequiredPwdPatternRestrictions() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setRequiredPasswordPattern", new Class[]{String.class}, 12));
            }
        }
        try {
            knoxConfigurationType2.setContainerLayout(knoxConfigurationType.getContainerLayout());
        } catch (NoSuchMethodError unused10) {
            if (knoxConfigurationType.getContainerLayout() != -1) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setContainerLayout", new Class[]{Integer.TYPE}, 17));
            }
        }
        try {
            knoxConfigurationType2.allowLayoutSwitching(knoxConfigurationType.isLayoutSwitchingAllowed());
        } catch (NoSuchMethodError unused11) {
            if (!knoxConfigurationType.isLayoutSwitchingAllowed()) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "allowLayoutSwitching", new Class[]{Boolean.TYPE}, 17));
            }
        }
        try {
            knoxConfigurationType2.setDefaultConfigType(knoxConfigurationType.isDefaultConfigType());
        } catch (NoSuchMethodError unused12) {
            if (knoxConfigurationType.isDefaultConfigType()) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setDefaultConfigType", new Class[]{Boolean.TYPE}, 17));
            }
        }
        try {
            knoxConfigurationType2.setEnterpriseIdentityAuthentication(AuthenticationConfig.convertToOld(knoxConfigurationType.getEnterpriseIdentityAuthentication()));
        } catch (NoSuchMethodError unused13) {
            if (!AuthenticationConfig.isDefaultObject(knoxConfigurationType.getEnterpriseIdentityAuthentication())) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setEnterpriseIdentityAuthentication", new Class[]{AuthenticationConfig.class}, 15));
            }
        }
        try {
            knoxConfigurationType2.setKeyguardDisabledFeatures(knoxConfigurationType.getKeyguardDisabledFeatures());
        } catch (NoSuchMethodError unused14) {
            if (knoxConfigurationType.getKeyguardDisabledFeatures() != -1) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setKeyguardDisabledFeatures", new Class[]{Integer.TYPE}, 15));
            }
        }
        List<String> list = knoxConfigurationType.mAppInstallationList;
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                List<Pair<String, String>> list2 = knoxConfigurationType.mRCPDataSettings.get(it.next());
                if (list2 != null) {
                    for (Pair<String, String> pair : list2) {
                        knoxConfigurationType2.setDataSyncPolicy(knoxConfigurationType.mAppInstallationList, (String) pair.first, (String) pair.second);
                    }
                }
            }
        }
        List<String> list3 = knoxConfigurationType.mAppInstallationList;
        if (list3 != null) {
            Iterator<String> it2 = list3.iterator();
            while (it2.hasNext()) {
                List<Pair<String, String>> list4 = knoxConfigurationType.mAllowChangeDataSettings.get(it2.next());
                if (list4 != null) {
                    for (Pair<String, String> pair2 : list4) {
                        knoxConfigurationType2.setAllowChangeDataSyncPolicy(knoxConfigurationType.mAppInstallationList, (String) pair2.first, Boolean.valueOf((String) pair2.second).booleanValue());
                    }
                }
            }
        }
        knoxConfigurationType2.setManagedType(knoxConfigurationType.getManagedType());
        knoxConfigurationType2.setCustomBadgeIcon(knoxConfigurationType.getCustomBadgeIcon());
        knoxConfigurationType2.setCustomStatusLabel(knoxConfigurationType.getCustomStatusLabel());
        knoxConfigurationType2.setCustomStatusIcon(knoxConfigurationType.getCustomStatusIcon());
        knoxConfigurationType2.setSimplePasswordEnabled(knoxConfigurationType.getSimplePasswordEnabled());
        knoxConfigurationType2.allowTaskManager(knoxConfigurationType.isTaskManagerAllowed());
        knoxConfigurationType2.allowMultiwindowMode(knoxConfigurationType.isMultiwindowModeAllowed());
        List<String> list5 = knoxConfigurationType.mAppInstallationList;
        if (list5 != null) {
            Iterator<String> it3 = list5.iterator();
            while (it3.hasNext()) {
                List<Pair<String, String>> list6 = knoxConfigurationType.mRCPNotifSettings.get(it3.next());
                if (list6 != null) {
                    for (Pair<String, String> pair3 : list6) {
                        knoxConfigurationType2.setDataSyncPolicy(knoxConfigurationType.mAppInstallationList, (String) pair3.first, (String) pair3.second);
                    }
                }
            }
        }
        try {
            knoxConfigurationType2.setVersion(knoxConfigurationType.getVersion());
        } catch (NoSuchMethodError unused15) {
            if (!knoxConfigurationType.getVersion().equals("custom")) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setVersion", new Class[]{String.class}, 12));
            }
        }
        try {
            knoxConfigurationType2.setProtectedPackageList(knoxConfigurationType.getProtectedPackageList());
        } catch (NoSuchMethodError unused16) {
            if (knoxConfigurationType.getProtectedPackageList() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setProtectedPackageList", new Class[]{List.class}, 12));
            }
        }
        try {
            knoxConfigurationType2.setGoogleAppsList(knoxConfigurationType.getGoogleAppsList());
        } catch (NoSuchMethodError unused17) {
            if (knoxConfigurationType.getProtectedPackageList() != null) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "setGoogleAppsList", new Class[]{List.class}, 12));
            }
        }
        try {
            knoxConfigurationType2.allowUSBDebugging(knoxConfigurationType.isUSBDebuggingAllowed());
        } catch (NoSuchMethodError unused18) {
            if (knoxConfigurationType.isUSBDebuggingAllowed()) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KnoxConfigurationType.class, "allowUSBDebugging", new Class[]{Boolean.TYPE}, 15));
            }
        }
        return knoxConfigurationType2;
    }

    private void deserializeRCPSettings(Parcel parcel, HashMap<String, List<Pair<String, String>>> map) {
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            String string = parcel.readString();
            ArrayList arrayList = new ArrayList();
            int i3 = parcel.readInt();
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add(new Pair<>(parcel.readString(), parcel.readString()));
            }
            map.put(string, arrayList);
        }
    }

    private List<String> getListFromSyncPolicy(String str, String str2, HashMap<String, List<Pair<String, String>>> map) {
        Set<String> setKeySet;
        ArrayList arrayList = null;
        if (map != null && str != null && !str.isEmpty() && str2 != null && !str2.isEmpty() && (setKeySet = map.keySet()) != null) {
            Pair pair = new Pair(str, str2);
            for (String str3 : setKeySet) {
                List<Pair<String, String>> list = map.get(str3);
                if (list != null) {
                    Iterator<Pair<String, String>> it = list.iterator();
                    while (it.hasNext()) {
                        if (pair.equals(it.next())) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(str3);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private String getRCPSyncPolicy(String str, String str2, HashMap<String, List<Pair<String, String>>> map) {
        List<Pair<String, String>> list;
        if (map != null && str != null && !str.isEmpty() && str2 != null && !str2.isEmpty() && (list = map.get(str)) != null) {
            for (Pair<String, String> pair : list) {
                if (((String) pair.first).equals(str2)) {
                    return (String) pair.second;
                }
            }
        }
        return null;
    }

    private boolean isDefaultConfigType() {
        return this.mIsDefaultConfigType;
    }

    private void serializeRCPSettings(Parcel parcel, HashMap<String, List<Pair<String, String>>> map) {
        Set<String> setKeySet = map.keySet();
        if (setKeySet == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(setKeySet.size());
        for (String str : setKeySet) {
            parcel.writeString(str);
            List<Pair<String, String>> list = map.get(str);
            if (list != null) {
                parcel.writeInt(list.size());
                for (Pair<String, String> pair : list) {
                    parcel.writeString((String) pair.first);
                    parcel.writeString((String) pair.second);
                }
            } else {
                parcel.writeInt(0);
            }
        }
    }

    private void setDefaultConfigType(boolean z) {
        this.mIsDefaultConfigType = z;
    }

    private void setRCPSyncPolicy(HashMap<String, List<Pair<String, String>>> map, HashMap<String, List<Pair<String, String>>> map2) {
        Set<String> setKeySet;
        if (map2 == null) {
            return;
        }
        map2.clear();
        if (map == null || (setKeySet = map.keySet()) == null) {
            return;
        }
        for (String str : setKeySet) {
            List<Pair<String, String>> list = map.get(str);
            if (list != null && !list.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (Pair<String, String> pair : list) {
                    arrayList.add(new Pair<>(pair.first, pair.second));
                }
                map2.put(str, arrayList);
            }
        }
    }

    private void setRCPSyncPolicy(List<String> list, String str, String str2, HashMap<String, List<Pair<String, String>>> map) {
        if (map == null || list == null || list.isEmpty() || str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return;
        }
        Pair<String, String> pair = new Pair<>(str, str2);
        Pair<String, String> pair2 = null;
        while (true) {
            boolean z = true;
            for (String str3 : list) {
                List<Pair<String, String>> arrayList = map.get(str3);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                } else {
                    for (Pair<String, String> pair3 : arrayList) {
                        if (pair3.equals(pair)) {
                            Log.d("KnoxConfigurationType", "Ignoring the duplicate entry: " + ((String) pair.first) + " " + ((String) pair.second));
                            z = false;
                        } else if (((String) pair3.first).equals(str)) {
                            Log.d("KnoxConfigurationType", "property found, remove and add it again: " + ((String) pair.first) + " " + ((String) pair.second));
                            pair2 = pair3;
                        }
                    }
                    if (pair2 != null) {
                        arrayList.remove(pair2);
                        pair2 = null;
                    }
                }
                if (z) {
                    arrayList.add(pair);
                    map.put(str3, arrayList);
                }
            }
            return;
        }
    }

    public void allowLayoutSwitching(boolean z) {
        this.mAllowSwitch = z;
    }

    public void allowMultiwindowMode(boolean z) {
        this.mAllowMultiwindowMode = z;
    }

    public void allowTaskManager(boolean z) {
        this.mAllowTaskManager = z;
    }

    public void allowUSBDebugging(boolean z) {
        this.mAllowUSBDebugging = z;
    }

    public KnoxConfigurationType clone(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("KnoxConfigurationType", "clone(): name is either null or empty, hence returning null");
            return null;
        }
        KnoxConfigurationType knoxConfigurationType = new KnoxConfigurationType();
        cloneConfiguration(knoxConfigurationType, str);
        return knoxConfigurationType;
    }

    protected void cloneConfiguration(KnoxConfigurationType knoxConfigurationType, String str) {
        knoxConfigurationType.setName(str);
        knoxConfigurationType.setPasswordMinimumNonLetters(this.mPasswordMinimumNonLetters);
        knoxConfigurationType.setPasswordMinimumLetters(this.mPasswordMinimumLetters);
        knoxConfigurationType.setPasswordMinimumNumeric(this.mPasswordMinimumNumeric);
        knoxConfigurationType.setPasswordMinimumUpperCase(this.mPasswordMinimumUpperCase);
        knoxConfigurationType.setPasswordMinimumLowerCase(this.mPasswordMinimumLowerCase);
        knoxConfigurationType.setPasswordMinimumSymbols(this.mPasswordMinimumSymbols);
        knoxConfigurationType.setPasswordQuality(this.mPasswordQuality);
        knoxConfigurationType.setMaximumFailedPasswordsForWipe(this.mMaximumFailedPasswordsForWipe);
        knoxConfigurationType.setManagedType(this.mManagedType);
        knoxConfigurationType.setCustomBadgeIcon(this.mCustomBadgeIcon);
        knoxConfigurationType.setCustomHomeScreenWallpaper(this.mCustomHomeScreenWallpaper);
        knoxConfigurationType.setCustomizedContainerNameIcon(this.mNameIcon);
        knoxConfigurationType.setCustomizedContainerEnabled(this.mEC);
        knoxConfigurationType.setCustomizedContainerName(this.mECName);
        knoxConfigurationType.setCustomizedContainerIcon(this.mECIcon);
        knoxConfigurationType.setCustomizedContainerBadge(this.mECBadge);
        knoxConfigurationType.setCustomLockScreenWallpaper(this.mCustomLockScreenWallpaper);
        knoxConfigurationType.setCustomStatusLabel(this.mCustomStatusLabel);
        knoxConfigurationType.setCustomStatusIcon(this.mCustomStatusIcon);
        knoxConfigurationType.setAppInstallationList(this.mAppInstallationList);
        knoxConfigurationType.setForbiddenStrings(this.mForbiddenStrings);
        knoxConfigurationType.setProtectedPackageList(this.mProtectedList);
        knoxConfigurationType.setGoogleAppsList(this.mGoogleAppsList);
        knoxConfigurationType.setMaximumCharacterOccurences(this.mMaximumCharacterOccurences);
        knoxConfigurationType.setMaximumCharacterSequenceLength(this.mMaximumCharacterSequenceLength);
        knoxConfigurationType.setMaximumNumericSequenceLength(this.mMaximumNumericSequenceLength);
        knoxConfigurationType.setPasswordMinimumLength(this.mPasswordMinimumLength);
        knoxConfigurationType.setSimplePasswordEnabled(this.mSimplePasswordEnabled);
        knoxConfigurationType.enforceMultifactorAuthentication(this.mMultifactorAuthEnabled);
        knoxConfigurationType.setRequiredPasswordPattern(this.mPasswordPattern);
        knoxConfigurationType.setRCPSyncPolicy(this.mRCPDataSettings, knoxConfigurationType.getDataSyncPolicy());
        knoxConfigurationType.setRCPSyncPolicy(this.mAllowChangeDataSettings, knoxConfigurationType.getAllowChangeDataSyncPolicy());
        knoxConfigurationType.setRCPSyncPolicy(this.mRCPNotifSettings, knoxConfigurationType.getNotificationSyncPolicy());
        knoxConfigurationType.setMaximumTimeToLock(this.mMaximumTimeToLock);
        knoxConfigurationType.setVersion(this.mVersion);
        knoxConfigurationType.allowMultiwindowMode(this.mAllowMultiwindowMode);
        knoxConfigurationType.allowTaskManager(this.mAllowTaskManager);
        knoxConfigurationType.setBiometricAuthenticationEnabled(this.mBiometricAuthValue, this.mIsBiometricAuthEnabled);
        knoxConfigurationType.allowUSBDebugging(this.mAllowUSBDebugging);
        knoxConfigurationType.setContainerLayout(this.mLayoutType);
        knoxConfigurationType.allowLayoutSwitching(this.mAllowSwitch);
        knoxConfigurationType.setDefaultConfigType(this.mIsDefaultConfigType);
        knoxConfigurationType.setEnterpriseIdentityAuthentication(this.mAuthenticationConfig);
        knoxConfigurationType.setKeyguardDisabledFeatures(this.mKeyguardDisabledFeatures);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void enforceMultifactorAuthentication(boolean z) {
        this.mMultifactorAuthEnabled = z;
    }

    public HashMap<String, List<Pair<String, String>>> getAllowChangeDataSyncPolicy() {
        return this.mAllowChangeDataSettings;
    }

    public boolean getAllowChangeDataSyncPolicy(String str, String str2) {
        return Boolean.parseBoolean(getRCPSyncPolicy(str, str2, this.mAllowChangeDataSettings));
    }

    public List<String> getAppInstallationList() {
        return this.mAppInstallationList;
    }

    public boolean getBiometricAuthenticationEnabledValue() {
        return this.mIsBiometricAuthEnabled;
    }

    public int getContainerLayout() {
        return this.mLayoutType;
    }

    public String getCustomBadgeIcon() {
        return this.mCustomBadgeIcon;
    }

    public String getCustomHomeScreenWallpaper() {
        return this.mCustomHomeScreenWallpaper;
    }

    public String getCustomLockScreenWallpaper() {
        return this.mCustomLockScreenWallpaper;
    }

    public String getCustomStatusIcon() {
        return this.mCustomStatusIcon;
    }

    public String getCustomStatusLabel() {
        return this.mCustomStatusLabel;
    }

    public String getCustomizedContainerBadge() {
        return this.mECBadge;
    }

    public String getCustomizedContainerIcon() {
        return this.mECIcon;
    }

    public String getCustomizedContainerName() {
        return this.mECName;
    }

    public String getCustomizedContainerNameIcon() {
        return this.mNameIcon;
    }

    public String getDataSyncPolicy(String str, String str2) {
        return getRCPSyncPolicy(str, str2, this.mRCPDataSettings);
    }

    public HashMap<String, List<Pair<String, String>>> getDataSyncPolicy() {
        return this.mRCPDataSettings;
    }

    public AuthenticationConfig getEnterpriseIdentityAuthentication() {
        return this.mAuthenticationConfig;
    }

    public List<String> getForbiddenStrings() {
        return this.mForbiddenStrings;
    }

    public List<String> getGoogleAppsList() {
        return this.mGoogleAppsList;
    }

    public int getKeyguardDisabledFeatures() {
        return this.mKeyguardDisabledFeatures;
    }

    public List<String> getListFromAllowChangeDataSyncPolicy(String str, boolean z) {
        return getListFromSyncPolicy(str, Boolean.toString(z), this.mAllowChangeDataSettings);
    }

    public List<String> getListFromDataSyncPolicy(String str, String str2) {
        return getListFromSyncPolicy(str, str2, this.mRCPDataSettings);
    }

    public boolean getManagedType() {
        return this.mManagedType;
    }

    public int getMaximumCharacterOccurences() {
        return this.mMaximumCharacterOccurences;
    }

    public int getMaximumCharacterSequenceLength() {
        return this.mMaximumCharacterSequenceLength;
    }

    public int getMaximumFailedPasswordsForWipe() {
        return this.mMaximumFailedPasswordsForWipe;
    }

    public int getMaximumNumericSequenceLength() {
        return this.mMaximumNumericSequenceLength;
    }

    public int getMaximumTimeToLock() {
        return this.mMaximumTimeToLock;
    }

    public String getName() {
        return this.mName;
    }

    public HashMap<String, List<Pair<String, String>>> getNotificationSyncPolicy() {
        return this.mRCPNotifSettings;
    }

    public int getPasswordMinimumLength() {
        return this.mPasswordMinimumLength;
    }

    public int getPasswordMinimumLetters() {
        return this.mPasswordMinimumLetters;
    }

    public int getPasswordMinimumLowerCase() {
        return this.mPasswordMinimumLowerCase;
    }

    public int getPasswordMinimumNonLetters() {
        return this.mPasswordMinimumNonLetters;
    }

    public int getPasswordMinimumNumeric() {
        return this.mPasswordMinimumNumeric;
    }

    public int getPasswordMinimumSymbols() {
        return this.mPasswordMinimumSymbols;
    }

    public int getPasswordMinimumUpperCase() {
        return this.mPasswordMinimumUpperCase;
    }

    public int getPasswordQuality() {
        return this.mPasswordQuality;
    }

    public List<String> getProtectedPackageList() {
        return this.mProtectedList;
    }

    public String getRequiredPwdPatternRestrictions() {
        return this.mPasswordPattern;
    }

    public boolean getSimplePasswordEnabled() {
        return this.mSimplePasswordEnabled;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public boolean isBiometricAuthenticationEnabled(int i) {
        int i2 = this.mBiometricAuthValue;
        if (i2 == -1 || (i2 & i) != i) {
            return false;
        }
        Log.d("KnoxConfigurationType", "isBiometricAuthenticationEnabled: return true (hasValue)");
        return true;
    }

    public boolean isCustomizedContainerEnabled() {
        return this.mEC;
    }

    public boolean isLayoutSwitchingAllowed() {
        return this.mAllowSwitch;
    }

    public boolean isMultifactorAuthenticationEnforced() {
        return this.mMultifactorAuthEnabled;
    }

    public boolean isMultiwindowModeAllowed() {
        return this.mAllowMultiwindowMode;
    }

    public boolean isTaskManagerAllowed() {
        return this.mAllowTaskManager;
    }

    public boolean isUSBDebuggingAllowed() {
        return this.mAllowUSBDebugging;
    }

    public void setAllowChangeDataSyncPolicy(List<String> list, String str, boolean z) {
        setRCPSyncPolicy(list, str, Boolean.toString(z), this.mAllowChangeDataSettings);
    }

    public void setAppInstallationList(List<String> list) {
        this.mAppInstallationList = list;
    }

    public void setBiometricAuthenticationEnabled(int i, boolean z) {
        if (i < 0) {
            return;
        }
        int i2 = this.mBiometricAuthValue;
        if (i2 <= 0) {
            i2 = 0;
        }
        int i3 = z ? i | i2 : (i ^ (-1)) & i2;
        this.mBiometricAuthValue = i3;
        if (i3 <= 0) {
            this.mIsBiometricAuthEnabled = false;
        } else {
            this.mIsBiometricAuthEnabled = true;
        }
        Log.d("KnoxConfigurationType", "setBiometricAuthenticationEnabled : bioAuth = " + this.mBiometricAuthValue + ", enabled : " + this.mIsBiometricAuthEnabled);
    }

    public void setContainerLayout(int i) {
        this.mLayoutType = i;
    }

    public void setCustomBadgeIcon(String str) {
        this.mCustomBadgeIcon = str;
    }

    public void setCustomHomeScreenWallpaper(String str) {
        this.mCustomHomeScreenWallpaper = str;
    }

    public void setCustomLockScreenWallpaper(String str) {
        this.mCustomLockScreenWallpaper = str;
    }

    public void setCustomStatusIcon(String str) {
        this.mCustomStatusIcon = str;
    }

    public void setCustomStatusLabel(String str) {
        this.mCustomStatusLabel = str;
    }

    public void setCustomizedContainerBadge(String str) {
        this.mECBadge = str;
    }

    public void setCustomizedContainerEnabled(boolean z) {
        this.mEC = z;
    }

    public void setCustomizedContainerIcon(String str) {
        this.mECIcon = str;
    }

    public void setCustomizedContainerName(String str) {
        this.mECName = str;
    }

    public void setCustomizedContainerNameIcon(String str) {
        this.mNameIcon = str;
    }

    public void setDataSyncPolicy(List<String> list, String str, String str2) {
        setRCPSyncPolicy(list, str, str2, this.mRCPDataSettings);
    }

    public void setEnterpriseIdentityAuthentication(AuthenticationConfig authenticationConfig) {
        this.mAuthenticationConfig = authenticationConfig;
    }

    public void setForbiddenStrings(List<String> list) {
        this.mForbiddenStrings = list;
    }

    public void setGoogleAppsList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mGoogleAppsList.clear();
        this.mGoogleAppsList.addAll(list);
    }

    public void setKeyguardDisabledFeatures(int i) {
        this.mKeyguardDisabledFeatures = i;
    }

    public void setManagedType(boolean z) {
        this.mManagedType = z;
    }

    public void setMaximumCharacterOccurences(int i) {
        this.mMaximumCharacterOccurences = i;
    }

    public void setMaximumCharacterSequenceLength(int i) {
        this.mMaximumCharacterSequenceLength = i;
    }

    public void setMaximumFailedPasswordsForWipe(int i) {
        this.mMaximumFailedPasswordsForWipe = i;
    }

    public void setMaximumNumericSequenceLength(int i) {
        this.mMaximumNumericSequenceLength = i;
    }

    public void setMaximumTimeToLock(int i) {
        this.mMaximumTimeToLock = i;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setNotificationSyncPolicy(List<String> list, String str, String str2) {
        setRCPSyncPolicy(list, str, str2, this.mRCPNotifSettings);
    }

    public void setPasswordMinimumLength(int i) {
        this.mPasswordMinimumLength = i;
    }

    public void setPasswordMinimumLetters(int i) {
        this.mPasswordMinimumLetters = i;
    }

    public void setPasswordMinimumLowerCase(int i) {
        this.mPasswordMinimumLowerCase = i;
    }

    public void setPasswordMinimumNonLetters(int i) {
        this.mPasswordMinimumNonLetters = i;
    }

    public void setPasswordMinimumNumeric(int i) {
        this.mPasswordMinimumNumeric = i;
    }

    public void setPasswordMinimumSymbols(int i) {
        this.mPasswordMinimumSymbols = i;
    }

    public void setPasswordMinimumUpperCase(int i) {
        this.mPasswordMinimumUpperCase = i;
    }

    public void setPasswordQuality(int i) {
        this.mPasswordQuality = i;
    }

    public void setProtectedPackageList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mProtectedList.clear();
        this.mProtectedList.addAll(list);
    }

    public void setRequiredPasswordPattern(String str) {
        this.mPasswordPattern = str;
    }

    public void setSimplePasswordEnabled(boolean z) {
        this.mSimplePasswordEnabled = z;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        String str = this.mName;
        if (str != null) {
            parcel.writeString(str);
        } else {
            parcel.writeString("custom");
        }
        String str2 = this.mVersion;
        if (str2 != null) {
            parcel.writeString(str2);
        } else {
            parcel.writeString("custom");
        }
        parcel.writeInt(this.mPasswordMinimumNonLetters);
        parcel.writeInt(this.mPasswordMinimumLetters);
        parcel.writeInt(this.mPasswordMinimumNumeric);
        parcel.writeInt(this.mPasswordMinimumUpperCase);
        parcel.writeInt(this.mPasswordMinimumLowerCase);
        parcel.writeInt(this.mPasswordMinimumSymbols);
        parcel.writeInt(this.mPasswordQuality);
        parcel.writeInt(this.mMaximumTimeToLock);
        parcel.writeInt(this.mMaximumFailedPasswordsForWipe);
        parcel.writeInt(this.mManagedType ? 1 : 0);
        String str3 = this.mCustomBadgeIcon;
        if (str3 != null) {
            parcel.writeString(str3);
        } else {
            parcel.writeString("");
        }
        String str4 = this.mCustomHomeScreenWallpaper;
        if (str4 != null) {
            parcel.writeString(str4);
        } else {
            parcel.writeString("");
        }
        Log.d("KnoxConfigurationType", "writing to parcel mEC " + this.mEC);
        parcel.writeInt(this.mEC ? 1 : 0);
        String str5 = this.mNameIcon;
        if (str5 != null) {
            parcel.writeString(str5);
        } else {
            parcel.writeString("");
        }
        String str6 = this.mECName;
        if (str6 != null) {
            parcel.writeString(str6);
        } else {
            parcel.writeString("");
        }
        String str7 = this.mECIcon;
        if (str7 != null) {
            parcel.writeString(str7);
        } else {
            parcel.writeString("");
        }
        String str8 = this.mECBadge;
        if (str8 != null) {
            parcel.writeString(str8);
        } else {
            parcel.writeString("");
        }
        String str9 = this.mCustomLockScreenWallpaper;
        if (str9 != null) {
            parcel.writeString(str9);
        } else {
            parcel.writeString("");
        }
        String str10 = this.mCustomStatusLabel;
        if (str10 != null) {
            parcel.writeString(str10);
        } else {
            parcel.writeString("");
        }
        String str11 = this.mCustomStatusIcon;
        if (str11 != null) {
            parcel.writeString(str11);
        } else {
            parcel.writeString("");
        }
        parcel.writeStringList(this.mAppInstallationList);
        parcel.writeStringList(this.mForbiddenStrings);
        parcel.writeStringList(this.mProtectedList);
        parcel.writeStringList(this.mGoogleAppsList);
        parcel.writeInt(this.mMaximumCharacterOccurences);
        parcel.writeInt(this.mMaximumCharacterSequenceLength);
        parcel.writeInt(this.mMaximumNumericSequenceLength);
        parcel.writeInt(this.mPasswordMinimumLength);
        String str12 = this.mPasswordPattern;
        if (str12 != null) {
            parcel.writeString(str12);
        } else {
            parcel.writeString("");
        }
        parcel.writeInt(this.mSimplePasswordEnabled ? 1 : 0);
        parcel.writeInt(this.mMultifactorAuthEnabled ? 1 : 0);
        parcel.writeInt(this.mAllowMultiwindowMode ? 1 : 0);
        parcel.writeInt(this.mAllowTaskManager ? 1 : 0);
        parcel.writeInt(this.mIsBiometricAuthEnabled ? 1 : 0);
        parcel.writeInt(this.mBiometricAuthValue);
        parcel.writeInt(this.mAllowUSBDebugging ? 1 : 0);
        parcel.writeInt(this.mLayoutType);
        parcel.writeInt(this.mAllowSwitch ? 1 : 0);
        parcel.writeInt(this.mIsDefaultConfigType ? 1 : 0);
        serializeRCPSettings(parcel, this.mRCPDataSettings);
        serializeRCPSettings(parcel, this.mAllowChangeDataSettings);
        serializeRCPSettings(parcel, this.mRCPNotifSettings);
        parcel.writeParcelable(this.mAuthenticationConfig, i);
        parcel.writeInt(this.mKeyguardDisabledFeatures);
    }
}

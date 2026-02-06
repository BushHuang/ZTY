package com.samsung.android.knox.container;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.samsung.android.knox.SupportLibUtils;

public class ContainerModeConfigurationType extends KnoxConfigurationType {
    public static final Parcelable.Creator<ContainerModeConfigurationType> CREATOR = new Parcelable.Creator<ContainerModeConfigurationType>() {
        @Override
        public ContainerModeConfigurationType createFromParcel(Parcel parcel) {
            return new ContainerModeConfigurationType(parcel);
        }

        @Override
        public ContainerModeConfigurationType[] newArray(int i) {
            Log.d("ContainerModeConfigurationType", "ContainerModeConfigurationType[] array to be created");
            return new ContainerModeConfigurationType[i];
        }
    };
    private static final String TAG = "ContainerModeConfigurationType";
    protected boolean mAllowClearAllNotification;
    protected boolean mAllowHomeKey;
    protected boolean mAllowSettingsChanges;
    protected boolean mAllowStatusBarExpansion;
    protected boolean mHideSystemBar;
    protected boolean mWipeRecentTasks;

    public ContainerModeConfigurationType() {
        this.mAllowSettingsChanges = true;
        this.mAllowStatusBarExpansion = true;
        this.mAllowHomeKey = true;
        this.mAllowClearAllNotification = false;
        this.mHideSystemBar = false;
        this.mWipeRecentTasks = false;
    }

    public ContainerModeConfigurationType(Parcel parcel) {
        super(parcel);
        this.mAllowSettingsChanges = true;
        this.mAllowStatusBarExpansion = true;
        this.mAllowHomeKey = true;
        this.mAllowClearAllNotification = false;
        this.mHideSystemBar = false;
        this.mWipeRecentTasks = false;
        this.mAllowSettingsChanges = parcel.readInt() == 1;
        this.mAllowStatusBarExpansion = parcel.readInt() == 1;
        this.mAllowHomeKey = parcel.readInt() == 1;
        this.mAllowClearAllNotification = parcel.readInt() == 1;
        this.mHideSystemBar = parcel.readInt() == 1;
        this.mWipeRecentTasks = parcel.readInt() == 1;
    }

    static ContainerModeConfigurationType convertToNew(com.sec.enterprise.knox.container.ContainerModeConfigurationType containerModeConfigurationType) {
        if (containerModeConfigurationType == null) {
            return null;
        }
        ContainerModeConfigurationType containerModeConfigurationType2 = new ContainerModeConfigurationType();
        KnoxConfigurationType.convertToNew(containerModeConfigurationType2, containerModeConfigurationType);
        containerModeConfigurationType2.setHideSystemBar(containerModeConfigurationType.isHideSystemBarEnabled());
        containerModeConfigurationType2.setWipeRecentTasks(containerModeConfigurationType.isWipeRecentTasksEnabled());
        containerModeConfigurationType2.allowClearAllNotification(containerModeConfigurationType.isClearAllNotificationAllowed());
        containerModeConfigurationType2.allowHomeKey(containerModeConfigurationType.isHomeKeyAllowed());
        containerModeConfigurationType2.allowSettingsChanges(containerModeConfigurationType.isSettingChangesAllowed());
        containerModeConfigurationType2.allowStatusBarExpansion(containerModeConfigurationType.isStatusBarExpansionAllowed());
        return containerModeConfigurationType2;
    }

    static com.sec.enterprise.knox.container.ContainerModeConfigurationType convertToOld(ContainerModeConfigurationType containerModeConfigurationType) throws NoSuchMethodError, NoClassDefFoundError {
        if (containerModeConfigurationType == null) {
            return null;
        }
        try {
            com.sec.enterprise.knox.container.ContainerModeConfigurationType containerModeConfigurationType2 = new com.sec.enterprise.knox.container.ContainerModeConfigurationType();
            KnoxConfigurationType.convertToOld(containerModeConfigurationType, containerModeConfigurationType2);
            containerModeConfigurationType2.setHideSystemBar(containerModeConfigurationType.isHideSystemBarEnabled());
            containerModeConfigurationType2.setWipeRecentTasks(containerModeConfigurationType.isWipeRecentTasksEnabled());
            containerModeConfigurationType2.allowClearAllNotification(containerModeConfigurationType.isClearAllNotificationAllowed());
            containerModeConfigurationType2.allowHomeKey(containerModeConfigurationType.isHomeKeyAllowed());
            containerModeConfigurationType2.allowSettingsChanges(containerModeConfigurationType.isSettingChangesAllowed());
            containerModeConfigurationType2.allowStatusBarExpansion(containerModeConfigurationType.isStatusBarExpansionAllowed());
            return containerModeConfigurationType2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(ContainerModeConfigurationType.class, 12));
        }
    }

    public void allowClearAllNotification(boolean z) {
        this.mAllowClearAllNotification = z;
    }

    public void allowHomeKey(boolean z) {
        this.mAllowHomeKey = z;
    }

    public void allowSettingsChanges(boolean z) {
        this.mAllowSettingsChanges = z;
    }

    public void allowStatusBarExpansion(boolean z) {
        this.mAllowStatusBarExpansion = z;
    }

    @Override
    public ContainerModeConfigurationType clone(String str) {
        if (str == null || str.isEmpty()) {
            Log.d("ContainerModeConfigurationType", "clone(): name is either null or empty, hence returning null");
            return null;
        }
        ContainerModeConfigurationType containerModeConfigurationType = new ContainerModeConfigurationType();
        cloneConfiguration(containerModeConfigurationType, str);
        containerModeConfigurationType.allowSettingsChanges(this.mAllowSettingsChanges);
        containerModeConfigurationType.allowStatusBarExpansion(this.mAllowStatusBarExpansion);
        containerModeConfigurationType.allowHomeKey(this.mAllowHomeKey);
        containerModeConfigurationType.allowClearAllNotification(this.mAllowClearAllNotification);
        containerModeConfigurationType.setHideSystemBar(this.mHideSystemBar);
        containerModeConfigurationType.setWipeRecentTasks(this.mWipeRecentTasks);
        return containerModeConfigurationType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean isClearAllNotificationAllowed() {
        return this.mAllowClearAllNotification;
    }

    public boolean isHideSystemBarEnabled() {
        return this.mHideSystemBar;
    }

    public boolean isHomeKeyAllowed() {
        return this.mAllowHomeKey;
    }

    public boolean isSettingChangesAllowed() {
        return this.mAllowSettingsChanges;
    }

    public boolean isStatusBarExpansionAllowed() {
        return this.mAllowStatusBarExpansion;
    }

    public boolean isWipeRecentTasksEnabled() {
        return this.mWipeRecentTasks;
    }

    public void setHideSystemBar(boolean z) {
        this.mHideSystemBar = z;
    }

    public void setWipeRecentTasks(boolean z) {
        this.mWipeRecentTasks = z;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mAllowSettingsChanges ? 1 : 0);
        parcel.writeInt(this.mAllowStatusBarExpansion ? 1 : 0);
        parcel.writeInt(this.mAllowHomeKey ? 1 : 0);
        parcel.writeInt(this.mAllowClearAllNotification ? 1 : 0);
        parcel.writeInt(this.mHideSystemBar ? 1 : 0);
        parcel.writeInt(this.mWipeRecentTasks ? 1 : 0);
    }
}

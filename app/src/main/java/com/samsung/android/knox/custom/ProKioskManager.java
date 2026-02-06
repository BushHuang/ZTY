package com.samsung.android.knox.custom;

import android.app.enterprise.knoxcustom.KnoxCustomManager;
import com.samsung.android.knox.SupportLibUtils;
import java.util.List;

public class ProKioskManager {
    private static KnoxCustomManager mKnoxCustomManager;
    private static android.app.enterprise.knoxcustom.ProKioskManager mProKioskManager;

    ProKioskManager(KnoxCustomManager knoxCustomManager) {
        mKnoxCustomManager = knoxCustomManager;
    }

    ProKioskManager(android.app.enterprise.knoxcustom.ProKioskManager proKioskManager) {
        mProKioskManager = proKioskManager;
    }

    public String getExitUI(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getExitUI(i) : mKnoxCustomManager.getSealedExitUI(i);
    }

    public boolean getHardKeyIntentState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.getHardKeyIntentState();
        }
        try {
            return mKnoxCustomManager.getSealedHardKeyIntentState();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getHardKeyIntentState", null, 15));
        }
    }

    public int getHideNotificationMessages() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.getHideNotificationMessages();
        }
        try {
            return mKnoxCustomManager.getSealedHideNotificationMessages();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getHideNotificationMessages", null, 14));
        }
    }

    public String getHomeActivity() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getHomeActivity() : mKnoxCustomManager.getSealedHomeActivity();
    }

    public boolean getInputMethodRestrictionState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getInputMethodRestrictionState() : mKnoxCustomManager.getSealedInputMethodRestrictionState();
    }

    public int getMultiWindowFixedState(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getMultiWindowFixedState(i) : mKnoxCustomManager.getSealedMultiWindowFixedState(i);
    }

    public List<PowerItem> getPowerDialogCustomItems() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return PowerItem.convertToNewList(proKioskManager.getPowerDialogCustomItems());
        }
        try {
            return PowerItem.convertToNewList(mKnoxCustomManager.getSealedPowerDialogCustomItems());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getPowerDialogCustomItems", null, 14));
        }
    }

    public boolean getPowerDialogCustomItemsState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.getPowerDialogCustomItemsState();
        }
        try {
            return mKnoxCustomManager.getSealedPowerDialogCustomItemsState();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getPowerDialogCustomItemsState", null, 14));
        }
    }

    public int getPowerDialogItems() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.getPowerDialogItems();
        }
        try {
            return mKnoxCustomManager.getSealedPowerDialogItems();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getPowerDialogItems", null, 14));
        }
    }

    public int getPowerDialogOptionMode() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getPowerDialogOptionMode() : mKnoxCustomManager.getSealedPowerDialogOptionMode();
    }

    public boolean getProKioskState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getProKioskState() : mKnoxCustomManager.getSealedState();
    }

    public String getProKioskString(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getProKioskString(i) : mKnoxCustomManager.getSealedModeString(i);
    }

    public int getSettingsEnabledItems() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.getSettingsEnabledItems();
        }
        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getSettingsEnabledItems", null, 17));
    }

    public boolean getStatusBarClockState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getStatusBarClockState() : mKnoxCustomManager.getSealedStatusBarClockState();
    }

    public boolean getStatusBarIconsState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getStatusBarIconsState() : mKnoxCustomManager.getSealedStatusBarIconsState();
    }

    public int getStatusBarMode() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getStatusBarMode() : mKnoxCustomManager.getSealedStatusBarMode();
    }

    public boolean getUsbMassStorageState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getUsbMassStorageState() : mKnoxCustomManager.getSealedUsbMassStorageState();
    }

    public String getUsbNetAddress(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getUsbNetAddress(i) : mKnoxCustomManager.getSealedUsbNetAddress(i);
    }

    public boolean getUsbNetState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.getUsbNetState() : mKnoxCustomManager.getSealedUsbNetState();
    }

    public boolean getVolumeKeyAppState() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.getVolumeKeyAppState();
        }
        try {
            return mKnoxCustomManager.getSealedVolumeKeyAppState();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getVolumeKeyAppState", null, 15));
        }
    }

    public List<String> getVolumeKeyAppsList() {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.getVolumeKeyAppsList();
        }
        try {
            return mKnoxCustomManager.getSealedVolumeKeyAppsList();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "getVolumeKeyAppsList", null, 15));
        }
    }

    public int setExitUI(String str, String str2) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setExitUI(str, str2) : mKnoxCustomManager.setSealedExitUI(str, str2);
    }

    public int setHardKeyIntentState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setHardKeyIntentState(z);
        }
        try {
            return mKnoxCustomManager.setSealedHardKeyIntentState(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setHardKeyIntentState", new Class[]{Boolean.TYPE}, 15));
        }
    }

    public int setHideNotificationMessages(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setHideNotificationMessages(i);
        }
        try {
            return mKnoxCustomManager.setSealedHideNotificationMessages(i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setHideNotificationMessages", new Class[]{Integer.TYPE}, 14));
        }
    }

    public int setHomeActivity(String str) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setHomeActivity(str) : mKnoxCustomManager.setSealedHomeActivity(str);
    }

    public int setInputMethodRestrictionState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setInputMethodRestrictionState(z) : mKnoxCustomManager.setSealedInputMethodRestrictionState(z);
    }

    public int setMultiWindowFixedState(int i, int i2) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setMultiWindowFixedState(i, i2) : mKnoxCustomManager.setSealedMultiWindowFixedState(i, i2);
    }

    public int setPassCode(String str, String str2) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setPassCode(str, str2) : mKnoxCustomManager.setSealedPassCode(str, str2);
    }

    public int setPowerDialogCustomItems(List<PowerItem> list) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setPowerDialogCustomItems(PowerItem.convertToOldList(list));
        }
        try {
            return mKnoxCustomManager.setSealedPowerDialogCustomItems(PowerItem.convertToOldList(list));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setPowerDialogCustomItems", new Class[]{List.class}, 14));
        }
    }

    public int setPowerDialogCustomItemsState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setPowerDialogCustomItemsState(z);
        }
        try {
            return mKnoxCustomManager.setSealedPowerDialogCustomItemsState(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setPowerDialogCustomItemsState", new Class[]{Boolean.TYPE}, 14));
        }
    }

    public int setPowerDialogItems(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setPowerDialogItems(i);
        }
        try {
            return mKnoxCustomManager.setSealedPowerDialogItems(i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setPowerDialogItems", new Class[]{Integer.TYPE}, 14));
        }
    }

    public int setPowerDialogOptionMode(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setPowerDialogOptionMode(i) : mKnoxCustomManager.setSealedPowerDialogOptionMode(i);
    }

    public int setProKioskState(boolean z, String str) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setProKioskState(z, str) : mKnoxCustomManager.setSealedState(z, str);
    }

    public int setProKioskString(int i, String str) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setProKioskString(i, str) : mKnoxCustomManager.setSealedModeString(i, str);
    }

    public int setSettingsEnabledItems(boolean z, int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setSettingsEnabledItems(z, i);
        }
        throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setSettingsEnabledItems", new Class[]{Boolean.TYPE, Integer.TYPE}, 17));
    }

    public int setStatusBarClockState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setStatusBarClockState(z) : mKnoxCustomManager.setSealedStatusBarClockState(z);
    }

    public int setStatusBarIconsState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setStatusBarIconsState(z) : mKnoxCustomManager.setSealedStatusBarIconsState(z);
    }

    public int setStatusBarMode(int i) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setStatusBarMode(i) : mKnoxCustomManager.setSealedStatusBarMode(i);
    }

    public int setUsbMassStorageState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setUsbMassStorageState(z) : mKnoxCustomManager.setSealedUsbMassStorageState(z);
    }

    public int setUsbNetAddresses(String str, String str2) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setUsbNetAddresses(str, str2) : mKnoxCustomManager.setSealedUsbNetAddresses(str, str2);
    }

    public int setUsbNetState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        return proKioskManager != null ? proKioskManager.setUsbNetState(z) : mKnoxCustomManager.setSealedUsbNetState(z);
    }

    public int setVolumeKeyAppState(boolean z) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setVolumeKeyAppState(z);
        }
        try {
            return mKnoxCustomManager.setSealedVolumeKeyAppState(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setVolumeKeyAppState", new Class[]{Boolean.TYPE}, 15));
        }
    }

    public int setVolumeKeyAppsList(List<String> list) {
        android.app.enterprise.knoxcustom.ProKioskManager proKioskManager = mProKioskManager;
        if (proKioskManager != null) {
            return proKioskManager.setVolumeKeyAppsList(list);
        }
        try {
            return mKnoxCustomManager.setSealedVolumeKeyAppsList(list);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(ProKioskManager.class, "setVolumeKeyAppsList", new Class[]{List.class}, 15));
        }
    }
}

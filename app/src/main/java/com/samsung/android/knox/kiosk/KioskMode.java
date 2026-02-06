package com.samsung.android.knox.kiosk;

import com.samsung.android.knox.SupportLibUtils;
import java.util.List;

public class KioskMode {
    public static final String ACTION_DISABLE_KIOSK_MODE_RESULT = "com.samsung.android.knox.intent.action.DISABLE_KIOSK_MODE_RESULT";
    public static final String ACTION_ENABLE_KIOSK_MODE_RESULT = "com.samsung.android.knox.intent.action.ENABLE_KIOSK_MODE_RESULT";
    public static final String ACTION_UNEXPECTED_KIOSK_BEHAVIOR = "com.samsung.android.knox.intent.action.UNEXPECTED_KIOSK_BEHAVIOR";
    public static final String EXTRA_KIOSK_RESULT = "com.samsung.android.knox.intent.extra.KIOSK_RESULT";
    private android.app.enterprise.kioskmode.KioskMode mKioskMode;

    public KioskMode(android.app.enterprise.kioskmode.KioskMode kioskMode) {
        this.mKioskMode = kioskMode;
    }

    public boolean allowAirCommandMode(boolean z) {
        try {
            return this.mKioskMode.allowAirCommandMode(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KioskMode.class, "allowAirCommandMode", new Class[]{Boolean.TYPE}, 13));
        }
    }

    public boolean allowAirViewMode(boolean z) {
        try {
            return this.mKioskMode.allowAirViewMode(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KioskMode.class, "allowAirViewMode", new Class[]{Boolean.TYPE}, 13));
        }
    }

    public boolean allowEdgeScreen(int i, boolean z) {
        try {
            return this.mKioskMode.allowEdgeScreen(i, z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KioskMode.class, "allowEdgeScreen", new Class[]{Integer.TYPE, Boolean.TYPE}, 17));
        }
    }

    public List<Integer> allowHardwareKeys(List<Integer> list, boolean z) {
        return this.mKioskMode.allowHardwareKeys(list, z);
    }

    public boolean allowMultiWindowMode(boolean z) {
        return this.mKioskMode.allowMultiWindowMode(z);
    }

    public boolean allowTaskManager(boolean z) {
        return this.mKioskMode.allowTaskManager(z);
    }

    public boolean clearAllNotifications() {
        return this.mKioskMode.clearAllNotifications();
    }

    public void disableKioskMode() {
        this.mKioskMode.disableKioskMode();
    }

    public void disableKioskMode(KioskSetting kioskSetting) {
        try {
            this.mKioskMode.disableKioskMode(KioskSetting.convertToOld(kioskSetting));
        } catch (NoSuchFieldError e) {
            throw new NoSuchFieldError(e.getMessage());
        }
    }

    public void enableKioskMode() {
        this.mKioskMode.enableKioskMode();
    }

    public void enableKioskMode(KioskSetting kioskSetting) {
        try {
            this.mKioskMode.enableKioskMode(KioskSetting.convertToOld(kioskSetting));
        } catch (NoSuchFieldError e) {
            throw new NoSuchFieldError(e.getMessage());
        }
    }

    public void enableKioskMode(String str) {
        this.mKioskMode.enableKioskMode(str);
    }

    public List<Integer> getAllBlockedHardwareKeys() {
        return this.mKioskMode.getAllBlockedHardwareKeys();
    }

    public int getBlockedEdgeScreen() {
        try {
            return this.mKioskMode.getBlockedEdgeScreen();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KioskMode.class, "getBlockedEdgeScreen", null, 17));
        }
    }

    public List<Integer> getHardwareKeyList() {
        return this.mKioskMode.getHardwareKeyList();
    }

    public String getKioskHomePackage() {
        return this.mKioskMode.getKioskHomePackage();
    }

    public boolean hideNavigationBar(boolean z) {
        return this.mKioskMode.hideNavigationBar(z);
    }

    public boolean hideStatusBar(boolean z) {
        return this.mKioskMode.hideStatusBar(z);
    }

    public boolean hideSystemBar(boolean z) {
        return this.mKioskMode.hideSystemBar(z);
    }

    public boolean isAirCommandModeAllowed() {
        try {
            return this.mKioskMode.isAirCommandModeAllowed();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KioskMode.class, "isAirCommandModeAllowed", null, 13));
        }
    }

    public boolean isAirViewModeAllowed() {
        try {
            return this.mKioskMode.isAirViewModeAllowed();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(KioskMode.class, "isAirViewModeAllowed", null, 13));
        }
    }

    public boolean isHardwareKeyAllowed(int i) {
        return this.mKioskMode.isHardwareKeyAllowed(i);
    }

    public boolean isKioskModeEnabled() {
        return this.mKioskMode.isKioskModeEnabled();
    }

    public boolean isMultiWindowModeAllowed() {
        return this.mKioskMode.isMultiWindowModeAllowed();
    }

    public boolean isNavigationBarHidden() {
        return this.mKioskMode.isNavigationBarHidden();
    }

    public boolean isStatusBarHidden() {
        return this.mKioskMode.isStatusBarHidden();
    }

    public boolean isSystemBarHidden() {
        return this.mKioskMode.isSystemBarHidden();
    }

    public boolean isTaskManagerAllowed() {
        return this.mKioskMode.isTaskManagerAllowed();
    }

    public boolean wipeRecentTasks() {
        return this.mKioskMode.wipeRecentTasks();
    }
}

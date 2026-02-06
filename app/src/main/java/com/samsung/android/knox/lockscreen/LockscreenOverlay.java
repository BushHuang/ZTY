package com.samsung.android.knox.lockscreen;

import android.app.enterprise.MiscPolicy;
import android.app.enterprise.lso.LockscreenOverlay;
import android.widget.ImageView;

public class LockscreenOverlay {
    private android.app.enterprise.lso.LockscreenOverlay mLockscreenOverlay;
    private MiscPolicy mMiscPolicy;

    public static class LSOEmergencyPhoneInfo {
        public int bottomPosition;
        public int gravity;
        public String icon;
        public String phoneNumber;
        public boolean showBackground;
        public boolean showDefaultText;
        public String text;
        public int topPosition;

        public LSOEmergencyPhoneInfo(String str) {
            init();
            this.phoneNumber = str;
        }

        public LSOEmergencyPhoneInfo(String str, int i, String str2, int i2) {
            init();
            this.phoneNumber = str;
            this.topPosition = i;
            this.icon = str2;
            this.bottomPosition = i2;
        }

        public LSOEmergencyPhoneInfo(String str, String str2) {
            init();
            this.phoneNumber = str;
            this.icon = str2;
        }

        static LSOEmergencyPhoneInfo convertToNew(LockscreenOverlay.LSOEmergencyPhoneInfo lSOEmergencyPhoneInfo) {
            if (lSOEmergencyPhoneInfo == null) {
                return null;
            }
            return new LSOEmergencyPhoneInfo(lSOEmergencyPhoneInfo.phoneNumber, lSOEmergencyPhoneInfo.topPosition, lSOEmergencyPhoneInfo.icon, lSOEmergencyPhoneInfo.bottomPosition);
        }

        static LockscreenOverlay.LSOEmergencyPhoneInfo convertToOld(LSOEmergencyPhoneInfo lSOEmergencyPhoneInfo) {
            if (lSOEmergencyPhoneInfo == null) {
                return null;
            }
            return new LockscreenOverlay.LSOEmergencyPhoneInfo(lSOEmergencyPhoneInfo.phoneNumber, lSOEmergencyPhoneInfo.topPosition, lSOEmergencyPhoneInfo.icon, lSOEmergencyPhoneInfo.bottomPosition);
        }

        private void init() {
            this.phoneNumber = null;
            this.topPosition = 0;
            this.bottomPosition = 100;
            this.icon = null;
            this.gravity = 17;
            this.showBackground = true;
            this.showDefaultText = false;
            this.text = null;
        }
    }

    public static class LSOImage {
        public int bottomPosition;
        public String filePath;
        public ImageView.ScaleType scaleType;
        public int topPosition;

        public LSOImage() {
            this.topPosition = 0;
            this.bottomPosition = 100;
            this.scaleType = ImageView.ScaleType.FIT_CENTER;
        }

        public LSOImage(int i, String str, int i2) {
            this.filePath = str;
            this.topPosition = i;
            this.bottomPosition = i2;
            this.scaleType = ImageView.ScaleType.FIT_CENTER;
        }

        public LSOImage(int i, String str, int i2, ImageView.ScaleType scaleType) {
            this.filePath = str;
            this.topPosition = i;
            this.bottomPosition = i2;
            this.scaleType = scaleType;
        }

        public LSOImage(String str) {
            this.topPosition = 0;
            this.bottomPosition = 100;
            this.filePath = str;
            this.scaleType = ImageView.ScaleType.FIT_CENTER;
        }

        static LockscreenOverlay.LSOImage convertToOld(LSOImage lSOImage) {
            if (lSOImage == null) {
                return null;
            }
            return new LockscreenOverlay.LSOImage(lSOImage.topPosition, lSOImage.filePath, lSOImage.bottomPosition, lSOImage.scaleType);
        }

        static LockscreenOverlay.LSOImage[] convertToOldList(LSOImage[] lSOImageArr) {
            if (lSOImageArr == null) {
                return null;
            }
            int length = lSOImageArr.length;
            LockscreenOverlay.LSOImage[] lSOImageArr2 = new LockscreenOverlay.LSOImage[length];
            for (int i = 0; i < length; i++) {
                lSOImageArr2[i] = convertToOld(lSOImageArr[i]);
            }
            return lSOImageArr2;
        }
    }

    public LockscreenOverlay(android.app.enterprise.lso.LockscreenOverlay lockscreenOverlay, MiscPolicy miscPolicy) {
        this.mLockscreenOverlay = lockscreenOverlay;
        this.mMiscPolicy = miscPolicy;
    }

    public boolean canConfigure() {
        return this.mLockscreenOverlay.canConfigure();
    }

    public boolean changeLockScreenString(String str) {
        return this.mMiscPolicy.changeLockScreenString(str);
    }

    public int configure(String str, String str2, String str3, String str4) {
        return this.mLockscreenOverlay.configure(str, str2, str3, str4);
    }

    public int configure(LSOImage[] lSOImageArr) {
        return this.mLockscreenOverlay.configure(LSOImage.convertToOldList(lSOImageArr));
    }

    public float getAlpha() {
        return this.mLockscreenOverlay.getAlpha();
    }

    public String getCurrentLockScreenString() {
        return this.mMiscPolicy.getCurrentLockScreenString();
    }

    public String getEmergencyPhone() {
        return this.mLockscreenOverlay.getEmergencyPhone();
    }

    public LSOEmergencyPhoneInfo getEmergencyPhoneInfo() {
        return LSOEmergencyPhoneInfo.convertToNew(this.mLockscreenOverlay.getEmergencyPhoneInfo());
    }

    public boolean isConfigured() {
        return this.mLockscreenOverlay.isConfigured();
    }

    public void removeEmergencyPhone() {
        this.mLockscreenOverlay.removeEmergencyPhone();
    }

    public void resetAll() {
        this.mLockscreenOverlay.resetAll();
    }

    public void resetOverlay() {
        this.mLockscreenOverlay.resetOverlay();
    }

    public void resetWallpaper() {
        this.mLockscreenOverlay.resetWallpaper();
    }

    public int setAlpha(float f) {
        return this.mLockscreenOverlay.setAlpha(f);
    }

    public int setEmergencyPhone(String str) {
        return this.mLockscreenOverlay.setEmergencyPhone(str);
    }

    public int setEmergencyPhoneInfo(LSOEmergencyPhoneInfo lSOEmergencyPhoneInfo) {
        return this.mLockscreenOverlay.setEmergencyPhoneInfo(LSOEmergencyPhoneInfo.convertToOld(lSOEmergencyPhoneInfo));
    }

    public int setWallpaper(String str) {
        return this.mLockscreenOverlay.setWallpaper(str);
    }
}

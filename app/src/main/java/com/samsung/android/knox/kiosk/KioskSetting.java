package com.samsung.android.knox.kiosk;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.List;

public class KioskSetting implements Parcelable {
    public static final Parcelable.Creator<KioskSetting> CREATOR = new Parcelable.Creator<KioskSetting>() {
        @Override
        public KioskSetting createFromParcel(Parcel parcel) {
            return new KioskSetting(parcel);
        }

        @Override
        public KioskSetting[] newArray(int i) {
            return new KioskSetting[i];
        }
    };
    public boolean airCommand;
    public boolean airView;
    public int blockedEdgeFunctions;
    public boolean clearAllNotifications;
    public List<Integer> hardwareKey;
    public boolean homeKey;
    public boolean multiWindow;
    public boolean navigationBar;
    public boolean settingsChanges;
    public boolean smartClip;
    public boolean statusBar;
    public boolean statusBarExpansion;
    public boolean systemBar;
    public boolean taskManager;
    public boolean wipeRecentTasks;

    public KioskSetting() {
    }

    public KioskSetting(Parcel parcel) {
        readFromParcel(parcel);
    }

    static android.app.enterprise.kioskmode.KioskSetting convertToOld(KioskSetting kioskSetting) throws NoSuchFieldError {
        if (kioskSetting == null) {
            return null;
        }
        android.app.enterprise.kioskmode.KioskSetting kioskSetting2 = new android.app.enterprise.kioskmode.KioskSetting();
        kioskSetting2.ClearAllNotifications = kioskSetting.clearAllNotifications;
        kioskSetting2.HardwareKey = kioskSetting.hardwareKey;
        kioskSetting2.HomeKey = kioskSetting.homeKey;
        kioskSetting2.MultiWindow = kioskSetting.multiWindow;
        kioskSetting2.NavigationBar = kioskSetting.navigationBar;
        kioskSetting2.SettingsChanges = kioskSetting.settingsChanges;
        kioskSetting2.StatusBar = kioskSetting.statusBar;
        kioskSetting2.StatusBarExpansion = kioskSetting.statusBarExpansion;
        kioskSetting2.SystemBar = kioskSetting.systemBar;
        kioskSetting2.TaskManager = kioskSetting.taskManager;
        kioskSetting2.WipeRecentTasks = kioskSetting.wipeRecentTasks;
        try {
            kioskSetting2.AirCommand = kioskSetting.airCommand;
        } catch (NoSuchFieldError unused) {
            if (kioskSetting.airCommand) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(KioskSetting.class, "AirCommand", 13));
            }
        }
        try {
            kioskSetting2.AirView = kioskSetting.airView;
        } catch (NoSuchFieldError unused2) {
            if (kioskSetting.airView) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(KioskSetting.class, "AirView", 13));
            }
        }
        try {
            kioskSetting2.BlockedEdgeFunctions = kioskSetting.blockedEdgeFunctions;
        } catch (NoSuchFieldError unused3) {
            if (kioskSetting.blockedEdgeFunctions != 0) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(KioskSetting.class, "BlockedEdgeFunctions", 17));
            }
        }
        try {
            kioskSetting2.SmartClip = kioskSetting.smartClip;
        } catch (NoSuchFieldError unused4) {
            if (kioskSetting.smartClip) {
                throw new NoSuchFieldError(SupportLibUtils.buildFieldErrorMsg(KioskSetting.class, "SmartClip", 13));
            }
        }
        return kioskSetting2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.settingsChanges = parcel.readInt() != 0;
        this.statusBarExpansion = parcel.readInt() != 0;
        this.homeKey = parcel.readInt() != 0;
        this.airCommand = parcel.readInt() != 0;
        this.airView = parcel.readInt() != 0;
        if (parcel.readInt() != 0) {
            int[] iArrCreateIntArray = parcel.createIntArray();
            this.hardwareKey = new ArrayList();
            for (int i : iArrCreateIntArray) {
                this.hardwareKey.add(new Integer(i));
            }
        }
        this.multiWindow = parcel.readInt() != 0;
        this.smartClip = parcel.readInt() != 0;
        this.taskManager = parcel.readInt() != 0;
        this.clearAllNotifications = parcel.readInt() != 0;
        this.navigationBar = parcel.readInt() != 0;
        this.statusBar = parcel.readInt() != 0;
        this.systemBar = parcel.readInt() != 0;
        this.wipeRecentTasks = parcel.readInt() != 0;
        this.blockedEdgeFunctions = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.settingsChanges ? 1 : 0);
        parcel.writeInt(this.statusBarExpansion ? 1 : 0);
        parcel.writeInt(this.homeKey ? 1 : 0);
        parcel.writeInt(this.airCommand ? 1 : 0);
        parcel.writeInt(this.airView ? 1 : 0);
        if (this.hardwareKey != null) {
            parcel.writeInt(1);
            int[] iArr = new int[this.hardwareKey.size()];
            for (int i2 = 0; i2 != this.hardwareKey.size(); i2++) {
                iArr[i2] = this.hardwareKey.get(i2).intValue();
            }
            parcel.writeIntArray(iArr);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.multiWindow ? 1 : 0);
        parcel.writeInt(this.smartClip ? 1 : 0);
        parcel.writeInt(this.taskManager ? 1 : 0);
        parcel.writeInt(this.clearAllNotifications ? 1 : 0);
        parcel.writeInt(this.navigationBar ? 1 : 0);
        parcel.writeInt(this.statusBar ? 1 : 0);
        parcel.writeInt(this.systemBar ? 1 : 0);
        parcel.writeInt(this.wipeRecentTasks ? 1 : 0);
        parcel.writeInt(this.blockedEdgeFunctions);
    }
}

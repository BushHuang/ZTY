package com.xuehai.mdm.config;

import android.os.Build;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010/\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020100j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000201`2J$\u00103\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020100j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000201`2H\u0002J$\u00104\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020100j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000201`2H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/xuehai/mdm/config/StatusBarButton;", "", "()V", "Add", "", "AirplaneMode", "AutoRotate", "BixbyRoutines", "BlueLightFilter", "Bluetooth", "ClearNotification", "DailyBoard", "DarkMode", "Device", "Dex", "Dnd", "DoNotDistub", "DolbyAtmos", "EyeComfortShield", "FocusMode", "HongbaoAccelerator", "KidsHome", "Location", "Media", "MobileData", "MobileHotspot", "MusicShare", "NavigationBar", "NightMode", "NotificationSettings", "Others", "PowerMode", "QSMore", "QSPower", "QSSearch", "QSSettings", "QuickShare", "RecordScreen", "RotationLock", "ScanQRCode", "ScreenCapture", "SecureFolder", "SmartView", "SoundMode", "Sync", "TabletVisibility", "Wifi", "getStatusBarButton", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "p200", "p615", "mdm_config_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StatusBarButton {
    public static final String Add = "Add";
    public static final String AirplaneMode = "AirplaneMode";
    public static final String AutoRotate = "AutoRotate";
    public static final String BixbyRoutines = "BixbyRoutines";
    public static final String BlueLightFilter = "BlueLightFilter";
    public static final String Bluetooth = "Bluetooth";
    public static final String ClearNotification = "ClearNotification";
    public static final String DailyBoard = "DailyBoard";
    public static final String DarkMode = "DarkMode";
    public static final String Device = "Device";
    public static final String Dex = "Dex";
    public static final String Dnd = "Dnd";
    public static final String DoNotDistub = "DoNotDistub";
    public static final String DolbyAtmos = "DolbyAtmos";
    public static final String EyeComfortShield = "EyeComfortShield";
    public static final String FocusMode = "FocusMode";
    public static final String HongbaoAccelerator = "HongbaoAccelerator";
    public static final StatusBarButton INSTANCE = new StatusBarButton();
    public static final String KidsHome = "KidsHome";
    public static final String Location = "Location";
    public static final String Media = "Media";
    public static final String MobileData = "MobileData";
    public static final String MobileHotspot = "MobileHotspot";
    public static final String MusicShare = "MusicShare";
    public static final String NavigationBar = "NavigationBar";
    public static final String NightMode = "NightMode";
    public static final String NotificationSettings = "NotificationSettings";
    public static final String Others = "Others";
    public static final String PowerMode = "PowerMode";
    public static final String QSMore = "QSMore";
    public static final String QSPower = "QSPower";
    public static final String QSSearch = "QSSearch";
    public static final String QSSettings = "QSSettings";
    public static final String QuickShare = "QuickShare";
    public static final String RecordScreen = "RecordScreen";
    public static final String RotationLock = "RotationLock";
    public static final String ScanQRCode = "ScanQRCode";
    public static final String ScreenCapture = "ScreenCapture";
    public static final String SecureFolder = "SecureFolder";
    public static final String SmartView = "SmartView";
    public static final String SoundMode = "SoundMode";
    public static final String Sync = "Sync";
    public static final String TabletVisibility = "TabletVisibility";
    public static final String Wifi = "Wifi";

    private StatusBarButton() {
    }

    private final HashMap<String, Boolean> p200() {
        return new HashMap<>();
    }

    private final HashMap<String, Boolean> p615() {
        HashMap<String, Boolean> map = new HashMap<>();
        HashMap<String, Boolean> map2 = map;
        map2.put("Wifi", true);
        map2.put("SoundMode", true);
        map2.put("Bluetooth", false);
        map2.put("AutoRotate", true);
        map2.put("AirplaneMode", true);
        map2.put("MobileData", true);
        map2.put("MobileHotspot", false);
        map2.put("PowerMode", false);
        map2.put("Location", false);
        map2.put("ScreenCapture", false);
        map2.put("RecordScreen", false);
        map2.put("Dex", false);
        map2.put("SmartView", false);
        map2.put("EyeComfortShield", true);
        map2.put("DarkMode", false);
        map2.put("DoNotDistub", false);
        map2.put("DolbyAtmos", false);
        map2.put("HongbaoAccelerator", false);
        map2.put("ScanQRCode", false);
        map2.put("Add", false);
        map2.put("MusicShare", false);
        map2.put("Sync", false);
        map2.put("BixbyRoutines", false);
        map2.put("QuickShare", false);
        map2.put("FocusMode", false);
        map2.put("KidsHome", false);
        map2.put("SecureFolder", false);
        map2.put("DailyBoard", false);
        map2.put("QSSearch", false);
        map2.put("QSPower", false);
        map2.put("QSSettings", true);
        map2.put("QSMore", false);
        map2.put("Device", false);
        map2.put("Media", false);
        map2.put("NotificationSettings", false);
        map2.put("ClearNotification", true);
        return map;
    }

    public final HashMap<String, Boolean> getStatusBarButton() {
        return Intrinsics.areEqual(Build.MODEL, "SM-P615C") ? p615() : new HashMap<>();
    }
}

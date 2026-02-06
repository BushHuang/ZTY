package com.xuehai.system.common.compat;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import com.xuehai.system.common.UnSupportException;
import com.xuehai.system.common.entities.PowerMode;
import com.xuehai.system.common.entities.RingerMode;
import com.xuehai.system.common.policies.ISettingPolicy;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0006H\u0016J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0016J+\u0010\u001c\u001a\u0002H\u001d\"\u0004\b\u0000\u0010\u001d2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u0002H\u001dH\u0016¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u0006H\u0016J\b\u0010\"\u001a\u00020\u0006H\u0016J\b\u0010#\u001a\u00020\u0006H\u0016J\b\u0010$\u001a\u00020\u000fH\u0016J \u0010%\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'H\u0016J\u0016\u0010(\u001a\u00020\u00062\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0016J\b\u0010*\u001a\u00020\u0006H\u0016J\u0016\u0010+\u001a\u00020\u00062\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0016J\u0010\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0016J\u0010\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0016J\u0010\u00100\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0016J\u0010\u00101\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0016J\u0010\u00102\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u000203H\u0016J\b\u00104\u001a\u00020\u0006H\u0016J\b\u00105\u001a\u00020\u0006H\u0016J\u0010\u00106\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0016J,\u00107\u001a\u00020\u00062\"\u00108\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000609j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0006`:H\u0016J \u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\b\u0010=\u001a\u00020\u000fH\u0016J\u0018\u0010>\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\b2\u0006\u0010@\u001a\u00020\bH\u0016J\u0010\u0010A\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0010\u0010B\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/xuehai/system/common/compat/SettingPolicyDefault;", "Lcom/xuehai/system/common/policies/ISettingPolicy;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "adjustStreamVolume", "", "streamType", "", "direction", "flags", "allowLockScreen", "allow", "allowSearchSettingsIndex", "controlAirplaneMode", "", "isOn", "controlEyeMode", "controlRingerMode", "mode", "Lcom/xuehai/system/common/entities/RingerMode;", "controlRotation", "isRotation", "controlSettingsTopLevelMenu", "keyList", "", "getAllowAccessibilityServicesList", "", "getSettingsSystemValue", "T", "key", "defaultObject", "(ILjava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "isBluetoothEnabled", "isEyeComfortTurnedOn", "isSearchSettingsEnable", "openSoftUpdateView", "putSettingsSystemValue", "value", "", "removeAllowAccessibilityServicesList", "pkgList", "removeLockScreen", "setAllowAccessibilityServicesList", "setBluetoothEnable", "enable", "setDevelopHiddenState", "state", "setDropMenuHiddenState", "setMobileDataState", "setPowerSavingMode", "Lcom/xuehai/system/common/entities/PowerMode;", "setSettingDisabled", "setSettingEnabled", "setSettingsHiddenState", "setStatusBarButtonState", "buttonList", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "setStreamVolume", "volume", "startSmartView", "startSoftUpdate", "hour", "minute", "turnOnConnectionAlwaysOn", "turnOnThreeKeyNavigation", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SettingPolicyDefault implements ISettingPolicy {
    private final Context context;

    public SettingPolicyDefault(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public boolean adjustStreamVolume(int streamType, int direction, int flags) {
        Object systemService = this.context.getSystemService("audio");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.media.AudioManager");
        }
        ((AudioManager) systemService).adjustStreamVolume(3, 1, 1);
        return true;
    }

    @Override
    public boolean allowLockScreen(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public boolean allowSearchSettingsIndex(boolean allow) {
        throw new UnSupportException();
    }

    @Override
    public void controlAirplaneMode(boolean isOn) {
    }

    @Override
    public void controlEyeMode(boolean isOn) {
    }

    @Override
    public void controlRingerMode(RingerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override
    public void controlRotation(boolean isRotation) {
    }

    @Override
    public void controlSettingsTopLevelMenu(String keyList) {
        Intrinsics.checkNotNullParameter(keyList, "keyList");
    }

    @Override
    public List<String> getAllowAccessibilityServicesList() {
        throw new UnSupportException();
    }

    @Override
    public <T> T getSettingsSystemValue(int mode, String key, T defaultObject) {
        Object objValueOf;
        Intrinsics.checkNotNullParameter(key, "key");
        if (defaultObject instanceof String) {
            objValueOf = mode != 0 ? mode != 1 ? mode != 2 ? Settings.System.getString(this.context.getContentResolver(), key) : Settings.Secure.getString(this.context.getContentResolver(), key) : Settings.Global.getString(this.context.getContentResolver(), key) : Settings.System.getString(this.context.getContentResolver(), key);
        } else if (defaultObject instanceof Integer) {
            objValueOf = mode != 0 ? mode != 1 ? mode != 2 ? Integer.valueOf(Settings.System.getInt(this.context.getContentResolver(), key)) : Integer.valueOf(Settings.Secure.getInt(this.context.getContentResolver(), key)) : Integer.valueOf(Settings.Global.getInt(this.context.getContentResolver(), key)) : Integer.valueOf(Settings.System.getInt(this.context.getContentResolver(), key));
        } else {
            if (defaultObject instanceof Boolean) {
                return (T) Boolean.valueOf((mode != 0 ? mode != 1 ? mode != 2 ? Settings.System.getInt(this.context.getContentResolver(), key) : Settings.Secure.getInt(this.context.getContentResolver(), key) : Settings.Global.getInt(this.context.getContentResolver(), key) : Settings.System.getInt(this.context.getContentResolver(), key)) != 0);
            }
            if (defaultObject instanceof Float) {
                objValueOf = mode != 0 ? mode != 1 ? mode != 2 ? Float.valueOf(Settings.System.getFloat(this.context.getContentResolver(), key)) : Float.valueOf(Settings.Secure.getFloat(this.context.getContentResolver(), key)) : Float.valueOf(Settings.Global.getFloat(this.context.getContentResolver(), key)) : Float.valueOf(Settings.System.getFloat(this.context.getContentResolver(), key));
            } else {
                if (!(defaultObject instanceof Long)) {
                    return defaultObject;
                }
                objValueOf = mode != 0 ? mode != 1 ? mode != 2 ? Long.valueOf(Settings.System.getLong(this.context.getContentResolver(), key)) : Long.valueOf(Settings.Secure.getLong(this.context.getContentResolver(), key)) : Long.valueOf(Settings.Global.getLong(this.context.getContentResolver(), key)) : Long.valueOf(Settings.System.getLong(this.context.getContentResolver(), key));
            }
        }
        return objValueOf;
    }

    @Override
    public boolean isBluetoothEnabled() {
        throw new UnSupportException();
    }

    @Override
    public boolean isEyeComfortTurnedOn() {
        return false;
    }

    @Override
    public boolean isSearchSettingsEnable() {
        throw new UnSupportException();
    }

    @Override
    public void openSoftUpdateView() {
        throw new UnSupportException();
    }

    @Override
    public boolean putSettingsSystemValue(int mode, String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof String) {
            if (mode == 0) {
                Settings.System.putString(this.context.getContentResolver(), key, (String) value);
            } else if (mode == 1) {
                Settings.Global.putString(this.context.getContentResolver(), key, (String) value);
            } else if (mode != 2) {
                Settings.System.putString(this.context.getContentResolver(), key, (String) value);
            } else {
                Settings.Secure.putString(this.context.getContentResolver(), key, (String) value);
            }
        } else if (value instanceof Integer) {
            if (mode == 0) {
                Settings.System.putInt(this.context.getContentResolver(), key, ((Number) value).intValue());
            } else if (mode == 1) {
                Settings.Global.putInt(this.context.getContentResolver(), key, ((Number) value).intValue());
            } else if (mode != 2) {
                Settings.System.putInt(this.context.getContentResolver(), key, ((Number) value).intValue());
            } else {
                Settings.Secure.putInt(this.context.getContentResolver(), key, ((Number) value).intValue());
            }
        } else if (value instanceof Float) {
            if (mode == 0) {
                Settings.System.putFloat(this.context.getContentResolver(), key, ((Number) value).floatValue());
            } else if (mode == 1) {
                Settings.Global.putFloat(this.context.getContentResolver(), key, ((Number) value).floatValue());
            } else if (mode != 2) {
                Settings.System.putFloat(this.context.getContentResolver(), key, ((Number) value).floatValue());
            } else {
                Settings.Secure.putFloat(this.context.getContentResolver(), key, ((Number) value).floatValue());
            }
        } else if (value instanceof Boolean) {
            boolean zBooleanValue = ((Boolean) value).booleanValue();
            if (mode == 0) {
                Settings.System.putInt(this.context.getContentResolver(), key, zBooleanValue ? 1 : 0);
            } else if (mode == 1) {
                Settings.Global.putInt(this.context.getContentResolver(), key, zBooleanValue ? 1 : 0);
            } else if (mode != 2) {
                Settings.System.putInt(this.context.getContentResolver(), key, zBooleanValue ? 1 : 0);
            } else {
                Settings.Secure.putInt(this.context.getContentResolver(), key, zBooleanValue ? 1 : 0);
            }
        } else if (value instanceof Long) {
            if (mode == 0) {
                Settings.System.putLong(this.context.getContentResolver(), key, ((Number) value).longValue());
            } else if (mode == 1) {
                Settings.Global.putLong(this.context.getContentResolver(), key, ((Number) value).longValue());
            } else if (mode != 2) {
                Settings.System.putLong(this.context.getContentResolver(), key, ((Number) value).longValue());
            } else {
                Settings.Secure.putLong(this.context.getContentResolver(), key, ((Number) value).longValue());
            }
        } else if (mode == 0) {
            Settings.System.putString(this.context.getContentResolver(), key, value.toString());
        } else if (mode == 1) {
            Settings.Global.putString(this.context.getContentResolver(), key, value.toString());
        } else if (mode != 2) {
            Settings.System.putString(this.context.getContentResolver(), key, value.toString());
        } else {
            Settings.Secure.putString(this.context.getContentResolver(), key, value.toString());
        }
        return true;
    }

    @Override
    public boolean removeAllowAccessibilityServicesList(List<String> pkgList) {
        Intrinsics.checkNotNullParameter(pkgList, "pkgList");
        throw new UnSupportException();
    }

    @Override
    public boolean removeLockScreen() {
        throw new UnSupportException();
    }

    @Override
    public boolean setAllowAccessibilityServicesList(List<String> pkgList) {
        Intrinsics.checkNotNullParameter(pkgList, "pkgList");
        throw new UnSupportException();
    }

    @Override
    public boolean setBluetoothEnable(boolean enable) {
        throw new UnSupportException();
    }

    @Override
    public boolean setDevelopHiddenState(boolean state) {
        throw new UnSupportException();
    }

    @Override
    public boolean setDropMenuHiddenState(boolean state) {
        throw new UnSupportException();
    }

    @Override
    public boolean setMobileDataState(boolean state) {
        return false;
    }

    @Override
    public void setPowerSavingMode(PowerMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override
    public boolean setSettingDisabled() {
        throw new UnSupportException();
    }

    @Override
    public boolean setSettingEnabled() {
        throw new UnSupportException();
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) {
        throw new UnSupportException();
    }

    @Override
    public boolean setStatusBarButtonState(HashMap<String, Boolean> buttonList) {
        Intrinsics.checkNotNullParameter(buttonList, "buttonList");
        return true;
    }

    @Override
    public boolean setStreamVolume(int volume, int streamType, int flags) {
        Object systemService = this.context.getSystemService("audio");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.media.AudioManager");
        }
        AudioManager audioManager = (AudioManager) systemService;
        int streamMaxVolume = audioManager.getStreamMaxVolume(streamType);
        if (volume < 0) {
            volume = 0;
        } else if (volume > streamMaxVolume) {
            volume = streamMaxVolume;
        }
        audioManager.setStreamVolume(streamType, volume, flags);
        return true;
    }

    @Override
    public void startSmartView() {
    }

    @Override
    public void startSoftUpdate(int hour, int minute) {
        throw new UnSupportException();
    }

    @Override
    public boolean turnOnConnectionAlwaysOn(boolean isOn) {
        return false;
    }

    @Override
    public boolean turnOnThreeKeyNavigation(boolean isOn) {
        return false;
    }
}

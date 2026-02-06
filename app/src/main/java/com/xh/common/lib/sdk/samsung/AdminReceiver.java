package com.xh.common.lib.sdk.samsung;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.mdm.device.DeviceManager;
import com.xuehai.system.mdm.proxy.PolicyPlugins;
import com.xuehai.system.mdm.proxy.RestrictionPolicyProxy;
import java.util.HashSet;
import java.util.Iterator;

public class AdminReceiver extends DeviceAdminReceiver {
    private static final HashSet<DeviceAdminListener> LISTENERS = new HashSet<>();
    private static final String TAG_SDK = "[MDM]SDK";

    public interface DeviceAdminListener {
        void onDeviceAdminStateChanged(boolean z);
    }

    public static void addListener(DeviceAdminListener deviceAdminListener) {
        if (deviceAdminListener != null) {
            MdmLog.i("[MDM]SDK", "addListener : " + deviceAdminListener.toString());
            LISTENERS.add(deviceAdminListener);
        }
    }

    public static void removeListener(DeviceAdminListener deviceAdminListener) {
        if (deviceAdminListener != null) {
            MdmLog.i("[MDM]SDK", "removeListener : " + deviceAdminListener.toString());
            LISTENERS.remove(deviceAdminListener);
        }
    }

    @Override
    public DevicePolicyManager getManager(Context context) {
        MdmLog.i("[MDM]SDK", "getManager");
        return super.getManager(context);
    }

    @Override
    public ComponentName getWho(Context context) {
        MdmLog.i("[MDM]SDK", "getWho");
        return super.getWho(context);
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        MdmLog.i("[MDM]SDK", "onDisableRequested");
        return null;
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        MdmLog.i("[MDM]SDK", "disabled " + intent.getAction());
        PolicyPlugins policyPlugins = new PolicyPlugins(context);
        RestrictionPolicyProxy restrictionPolicyProxy = policyPlugins.getRestrictionPolicyProxy();
        restrictionPolicyProxy.allowFirmwareRecovery(true);
        restrictionPolicyProxy.allowFactoryReset(true);
        restrictionPolicyProxy.setMTPEnabled(true);
        restrictionPolicyProxy.setUsbDebuggingEnabled(true);
        policyPlugins.getSettingPolicyProxy().setSettingEnabled();
        policyPlugins.getSettingPolicyProxy().setSettingsHiddenState(false);
        try {
            Iterator<DeviceAdminListener> it = LISTENERS.iterator();
            while (it.hasNext()) {
                it.next().onDeviceAdminStateChanged(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "enabled");
        MdmLog.i("[MDM]SDK", "enabled ");
        try {
            Iterator<DeviceAdminListener> it = LISTENERS.iterator();
            while (it.hasNext()) {
                it.next().onDeviceAdminStateChanged(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        MdmLog.i("[MDM]SDK", "password changed");
        DeviceManager.resetPassword(context);
    }

    @Override
    public void onPasswordExpiring(Context context, Intent intent) {
        super.onPasswordExpiring(context, intent);
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        MdmLog.i("[MDM]SDK", "password failed");
        DeviceManager.resetPassword(context);
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        MdmLog.i("[MDM]SDK", "password succeeded");
        DeviceManager.resetPassword(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        MdmLog.i("[MDM]SDK", "onReceive " + intent.getAction());
    }

    @Override
    public void onUserAdded(Context context, Intent intent, UserHandle userHandle) {
        super.onUserAdded(context, intent, userHandle);
        MdmLog.i("[MDM]SDK", "onUserAdded");
    }

    @Override
    public void onUserRemoved(Context context, Intent intent, UserHandle userHandle) {
        super.onUserRemoved(context, intent, userHandle);
        MdmLog.i("[MDM]SDK", "onUserRemoved");
    }

    @Override
    public void onUserStarted(Context context, Intent intent, UserHandle userHandle) {
        super.onUserStarted(context, intent, userHandle);
        MdmLog.i("[MDM]SDK", "onUserStarted");
    }

    @Override
    public void onUserStopped(Context context, Intent intent, UserHandle userHandle) {
        super.onUserStopped(context, intent, userHandle);
        MdmLog.i("[MDM]SDK", "onUserStopped");
    }

    void showToast(Context context, CharSequence charSequence) {
        MdmLog.i("[MDM]SDK", "xueHai Device Admin : " + ((Object) charSequence));
    }
}

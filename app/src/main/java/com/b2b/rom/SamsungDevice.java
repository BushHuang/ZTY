package com.b2b.rom;

import a.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.List;

public class SamsungDevice {
    public static final String LOG_TAG = SamsungDevice.class.getSimpleName();
    private static SamsungDevice mInstance;
    private a.a mService = null;
    String version = "1.0.3";
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(SamsungDevice.LOG_TAG, "SamsungDevice onServiceConnected ");
            SamsungDevice.this.mService = a.AbstractBinderC0000a.a(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            SamsungDevice.this.mService = null;
            Log.d(SamsungDevice.LOG_TAG, "Disconnected to SamsungDevice.");
        }
    };

    private SamsungDevice(Context context) {
        if (this.mService == null) {
            Intent intent = new Intent();
            intent.setClassName("com.samsung.customxh", "com.samsung.customxh.CustomDeviceService");
            Log.d(LOG_TAG, "SamsungDevice version = " + this.version);
            try {
                context.bindServiceAsUser(intent, this.mConnection, 1, UserHandle.CURRENT_OR_SELF);
            } catch (Exception unused) {
            }
        }
    }

    public static synchronized SamsungDevice getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SamsungDevice(context);
        }
        return mInstance;
    }

    public boolean addAppAutoRunningList(List<String> list) {
        Log.d(LOG_TAG, "addAppAutoRunningList");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.c(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean addPackagesToNotificationAllowList(List<String> list) {
        Log.d(LOG_TAG, "addPackagesToNotificationAllowList");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.a(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean clearPackagesFromNotificationAllowList() {
        Log.d(LOG_TAG, "removePackagesFromNotificationAllowList");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.a();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public void controlAirplaneMode(boolean z) {
        Log.d(LOG_TAG, "controlAirplaneMode");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.f(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlEyeMode(boolean z) {
        Log.d(LOG_TAG, "controlEyeMode");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.g(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlRingerMode(int i) {
        Log.d(LOG_TAG, "controlRingerMode");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlRotation(boolean z) {
        Log.d(LOG_TAG, "controlRotation");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.h(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlShowDeprecatedTarget(boolean z) {
        Log.d(LOG_TAG, "controlShowDeprecatedTarget");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.o(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean disableBixbyShorcut(boolean z) {
        Log.d(LOG_TAG, "disableBixbyShorcut");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.n(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean disableCameraShorcut(boolean z) {
        Log.d(LOG_TAG, "disableCameraShorcut");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.m(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public void disableHomeLongFunc() {
        Log.d(LOG_TAG, "networkReset");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.f();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean disableKanban(boolean z) {
        Log.d(LOG_TAG, "disableKanban");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.k(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean disableSamsungAccount(boolean z) {
        Log.d(LOG_TAG, "disableSamsungAccount");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.l(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean disableSwitchInputMethod(boolean z) {
        Log.d(LOG_TAG, "disableSwitchInputMethod");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.j(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean disableTaskBar(boolean z) {
        Log.d(LOG_TAG, "disableTaskBar disabled =" + z);
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.p(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public List<String> getAppAutoRunningList() {
        Log.d(LOG_TAG, "getAppAutoRunningList");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.d(LOG_TAG, " please bind service first ");
            return null;
        }
        try {
            return aVar.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getForegroundApp() {
        Log.d(LOG_TAG, "getForegroundApp");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.i();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return null;
    }

    public String getIMEI() {
        Log.d(LOG_TAG, "getIMEI");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.g();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return null;
    }

    public String getMacAddress() {
        Log.d(LOG_TAG, "getIMEI");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.h();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return null;
    }

    public List<String> getRunningTask() {
        Log.d(LOG_TAG, "getRunningTask");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.d(LOG_TAG, " please bind service first ");
            return null;
        }
        try {
            return aVar.j();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSN() {
        Log.d(LOG_TAG, "getSN");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.d();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return null;
    }

    public void networkReset() {
        Log.d(LOG_TAG, "networkReset");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.e();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openSoftUpdateView() {
        Log.d(LOG_TAG, "removeAppAutoRunningList");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.c();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean removeAppAutoRunningList(List<String> list) {
        Log.d(LOG_TAG, "removeAppAutoRunningList");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.d(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean removePackagesFromNotificationAllowList(List<String> list) {
        Log.d(LOG_TAG, "removePackagesFromNotificationAllowList");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.b(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public void setGPSState(boolean z) {
        Log.d(LOG_TAG, "setGPSState");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.c(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setHomeLongComponent(String str) {
        Log.d(LOG_TAG, "setHomeLongComponent");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.c(str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean setInputMethod(String str) {
        Log.d(LOG_TAG, "setInputMethod");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.a(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean setLockScreenItems(boolean z) {
        Log.d(LOG_TAG, "setLockScreenItems");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.i(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public boolean setNetworkSpeedState(boolean z) {
        Log.d(LOG_TAG, "setNetworkSpeedState");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.d(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public void setSettingsEnableStatus(boolean z) {
        Log.d(LOG_TAG, "setSettingsEnableStatus");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.e(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setUsageStatsPermission(String str) {
        Log.d(LOG_TAG, "setUsageStatsPermission");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.b(str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setVolumePanelSettingIconState(boolean z) {
        Log.d(LOG_TAG, "setVolumePanelSettingIconState");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.a(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean setWifiEnabled(boolean z) {
        Log.d(LOG_TAG, "setWifiEnabled");
        a.a aVar = this.mService;
        if (aVar != null) {
            try {
                return aVar.b(z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, " please bind service first ");
        }
        return false;
    }

    public void startSmartView() {
        Log.d(LOG_TAG, "startSmartView");
        a.a aVar = this.mService;
        if (aVar == null) {
            Log.e(LOG_TAG, " please bind service first ");
            return;
        }
        try {
            aVar.k();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

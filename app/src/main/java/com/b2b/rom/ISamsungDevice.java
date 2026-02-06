package com.b2b.rom;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import java.util.Map;

public class ISamsungDevice {

    public String f160a;
    private Context b;
    private com.a.a.a c;
    private com.b.a.a d;
    private a e;
    private b f;
    private String g;

    private ISamsungDevice() {
        this.f160a = "ISamsungDevice";
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = "3.0.9";
    }

    public ISamsungDevice(Context context) {
        this.f160a = "ISamsungDevice";
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = "3.0.9";
        this.b = context;
        new Intent();
        byte b = 0;
        this.e = new a(this, b);
        Intent intent = new Intent();
        intent.setClassName("com.samsung.customxh", "com.samsung.customxh.CustomDeviceService");
        this.b.bindService(intent, this.e, 1);
        this.f = new b(this, b);
        Intent intent2 = new Intent();
        intent2.setClassName("com.srcb.softupdate", "com.srcb.softupdate.CustomService");
        this.b.bindService(intent2, this.f, 1);
        Log.e(this.f160a, "bindService:V" + this.g);
    }

    public void Release() {
        a aVar = this.e;
        if (aVar != null) {
            this.b.unbindService(aVar);
            this.e = null;
        }
    }

    public boolean addAppAlwaysRunningList(List list) {
        Log.e(this.f160a, "addAppAlwaysRunningList:".concat(String.valueOf(list)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.e(list);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAppAutoRunningList(List list) {
        Log.e(this.f160a, "addAppAutoRunningList:".concat(String.valueOf(list)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.c(list);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPackagesToNotificationWhiteList(List list) {
        Log.e(this.f160a, "addPackagesToNotificationWhiteList:".concat(String.valueOf(list)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.a(list);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearPackagesFromNotificationWhiteList() {
        Log.e(this.f160a, "clearPackagesFromNotificationWhiteList");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void controlAirplaneMode(boolean z) {
        Log.e(this.f160a, "controlAirplaneMode:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return;
        }
        try {
            aVar.i(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlChangeHome(boolean z) {
        Log.e(this.f160a, "controlChangeHome:".concat(String.valueOf(z)));
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            return;
        }
        try {
            aVar.b(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlEyeMode(boolean z) {
        Log.e(this.f160a, "controlEyeMode:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return;
        }
        try {
            aVar.j(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlRingerMode(int i) {
        Log.e(this.f160a, "controlRingerMode:".concat(String.valueOf(i)));
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            return;
        }
        try {
            aVar.a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlRotation(boolean z) {
        Log.e(this.f160a, "controlRotation:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return;
        }
        try {
            aVar.k(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlSettingsTopLevelMenu(String str) {
        Log.e(this.f160a, "controlSettingsTopLevelMenu:".concat(String.valueOf(str)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return;
        }
        try {
            aVar.d(str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void controlShowDeprecatedTarget(boolean z) {
        Log.e(this.f160a, "controlShowDeprecatedTarget:".concat(String.valueOf(z)));
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            return;
        }
        try {
            aVar.a(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableHomeLongFunc() {
        Log.e(this.f160a, "disableHomeLongFunc");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return;
        }
        try {
            aVar.i();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List getAppAlwaysRunningList() {
        Log.e(this.f160a, "getAppAlwaysRunningList");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.c();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List getAppAutoRunningList() {
        Log.e(this.f160a, "getAppAutoRunningList");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List getAppUsage(long j, long j2) {
        Log.e(this.f160a, "getAppUsage");
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.a(j, j2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getForegroundApp() {
        Log.e(this.f160a, "getForegroundApp");
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.a();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIMEI() {
        Log.e(this.f160a, "getIMEI");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.j();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List getRunningTask() {
        Log.e(this.f160a, "getRunningTask");
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.b();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSN() {
        Log.e(this.f160a, "getSN");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return null;
        }
        try {
            return aVar.h();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void openSoftUpdateView() {
        Log.e(this.f160a, "openSoftUpdateView");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            Log.e(this.f160a, "remoteService is null");
            return;
        }
        try {
            aVar.g();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void reboot() {
        Log.e(this.f160a, "reboot");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            Log.e(this.f160a, "remoteService is null");
            return;
        }
        try {
            aVar.f();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean removeAppAlwaysRunningList(List list) {
        Log.e(this.f160a, "removeAppAlwaysRunningList:".concat(String.valueOf(list)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.f(list);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeAppAutoRunningList(List list) {
        Log.e(this.f160a, "removeAppAutoRunningList:".concat(String.valueOf(list)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.d(list);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removePackagesFromNotificationWhiteList(List list) {
        Log.e(this.f160a, "removePackagesFromNotificationWhiteList：".concat(String.valueOf(list)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.b(list);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String runBugReport(String str) {
        Log.e(this.f160a, "runBugReport:".concat(String.valueOf(str)));
        com.b.a.a aVar = this.d;
        String strB = null;
        if (aVar == null) {
            Log.e(this.f160a, "softupdateService is null");
            return null;
        }
        try {
            strB = aVar.b(str);
            Log.e(this.f160a, "runBugReport:".concat(String.valueOf(strB)));
            return strB;
        } catch (RemoteException e) {
            e.printStackTrace();
            return strB;
        }
    }

    public void setDefaultHome(String str, String str2) {
        Log.e(this.f160a, "setDefaultHome:".concat(String.valueOf(str)));
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            return;
        }
        try {
            aVar.b(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean setDevelopHiddenState(boolean z) {
        Log.e(this.f160a, "setDevelopHiddenState:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.h(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setDropMenuHiddenState(boolean z) {
        Log.e(this.f160a, "setDropMenuHiddenState：".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.b(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setGPSState(boolean z) {
        Log.e(this.f160a, "setGPSState:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.e(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setInputMethod(String str) {
        Log.e(this.f160a, "setInputMethod:".concat(String.valueOf(str)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.a(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setMTPEnabled(boolean z) {
        Log.e(this.f160a, "setMTPEnabled:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.d(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setNetworkSpeedState(boolean z) {
        Log.e(this.f160a, "setNetworkSpeedState:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.g(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setSettingsHiddenState(boolean z) {
        Log.e(this.f160a, "setSettingsHiddenState:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.a(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setSpenPointImageState(boolean z) {
        Log.e(this.f160a, "setSpenPointImageState:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.f(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setStatusBarButtonState(Map map) {
        Log.e(this.f160a, "setStatusBarButtonState:".concat(String.valueOf(map)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.a(map);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setUsageStatsPermission(String str) {
        Log.e(this.f160a, "setUsageStatsPermission:".concat(String.valueOf(str)));
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            Log.e(this.f160a, "setUsageStatsPermission:softupdateService == null");
            return;
        }
        try {
            aVar.a(str, "android:get_usage_stats");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean setUsbDebugEnabled(boolean z) {
        Log.e(this.f160a, "setUsbDebugEnabled:".concat(String.valueOf(z)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.c(z);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void shutdown() {
        Log.e(this.f160a, "shutdown");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            Log.e(this.f160a, "remoteService is null");
            return;
        }
        try {
            aVar.e();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startSmartView() {
        Log.e(this.f160a, "startSmartView");
        com.b.a.a aVar = this.d;
        if (aVar == null) {
            Log.e(this.f160a, "startSmartView:softupdateService == null");
            return;
        }
        try {
            aVar.c();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startSoftUpdate(int i, int i2) {
        Log.e(this.f160a, "startSoftUpdate:" + i + ":" + i2);
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            Log.e(this.f160a, "remoteService is null");
            return;
        }
        try {
            aVar.a(i, i2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean startTcpdump(String str) {
        Log.e(this.f160a, "startTcpdump:".concat(String.valueOf(str)));
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.b(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean stopTcpdump() {
        Log.e(this.f160a, "stopTcpdump");
        com.a.a.a aVar = this.c;
        if (aVar == null) {
            return false;
        }
        try {
            return aVar.d();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.analysys.ipc;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.analysys.e;
import com.analysys.ipc.IAnalysysMain;
import com.analysys.j;
import com.analysys.l;
import com.analysys.m;
import com.analysys.n;
import com.analysys.utils.ANSLog;
import com.analysys.utils.ActivityLifecycleUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IpcManager {
    private static final String ACTION_MAIN_BOOT = "action_main_boot";
    public static final String TAG = "analysys.ipc";
    private static IpcManager sInstance = new IpcManager();
    private Map<String, IAnalysysClient> mClientBinderMap;
    private l mClientProcessBinder;
    private BroadcastReceiver mMainProcessBootReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            IpcManager.this.queryMainBinderFromClient(context);
        }
    };
    private boolean mMainProcessBootReceiverRegistered;
    private PackageInfo mPackageInfo;
    private IAnalysysMain mainProcessBinder;

    private IpcManager() {
    }

    public static IpcManager getInstance() {
        return sInstance;
    }

    private void queryMainBinderFromClient(final Context context) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(e.d(context), null, null, null, null);
            if (cursorQuery != null) {
                if (this.mMainProcessBootReceiverRegistered) {
                    context.getApplicationContext().unregisterReceiver(this.mMainProcessBootReceiver);
                    this.mMainProcessBootReceiverRegistered = false;
                }
                IBinder iBinderA = j.a(cursorQuery);
                if (iBinderA != null) {
                    final String strA = m.a();
                    iBinderA.linkToDeath(new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {
                            IpcManager.this.mainProcessBinder = null;
                            ANSLog.w("analysys.ipc", "unlink to main process: " + strA);
                            context.getApplicationContext().registerReceiver(IpcManager.this.mMainProcessBootReceiver, new IntentFilter("action_main_boot"));
                            IpcManager.this.mMainProcessBootReceiverRegistered = true;
                        }
                    }, 0);
                    this.mainProcessBinder = IAnalysysMain.a.a(iBinderA);
                    ANSLog.i("analysys.ipc", "link to main process: " + strA);
                    this.mainProcessBinder.setClientBinder(strA, this.mClientProcessBinder);
                }
            }
            if (cursorQuery == null) {
                return;
            }
        } catch (Throwable th) {
            try {
                ExceptionUtil.exceptionThrow(th);
                if (cursorQuery == null) {
                    return;
                }
            } catch (Throwable th2) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
        try {
            cursorQuery.close();
        } catch (Throwable unused2) {
        }
    }

    private void setupProxy() {
        Object objInvokeStaticMethod = AnsReflectUtils.invokeStaticMethod("com.analysys.visual.utils.VisualIpc", "getInstance");
        if (objInvokeStaticMethod instanceof IIpcProxy) {
            IIpcProxy iIpcProxy = (IIpcProxy) objInvokeStaticMethod;
            if (CommonUtils.isMainProcess(AnalysysUtil.getContext())) {
                IAnalysysMain iAnalysysMain = this.mainProcessBinder;
                if (iAnalysysMain != null) {
                    ((n) iAnalysysMain).a(iIpcProxy);
                    return;
                }
                return;
            }
            l lVar = this.mClientProcessBinder;
            if (lVar != null) {
                lVar.a(iIpcProxy);
            }
        }
    }

    public void addClientBinder(final String str, IAnalysysClient iAnalysysClient) {
        Log.i("analysys.ipc", "add client binder " + str);
        if (TextUtils.isEmpty(str) || iAnalysysClient == null) {
            return;
        }
        if (this.mClientBinderMap == null) {
            this.mClientBinderMap = new ConcurrentHashMap();
        }
        this.mClientBinderMap.put(str, iAnalysysClient);
        ((n) this.mainProcessBinder).a(str);
        try {
            iAnalysysClient.asBinder().linkToDeath(new IBinder.DeathRecipient() {
                @Override
                public void binderDied() {
                    ANSLog.i("analysys.ipc", "remove client binder " + str);
                    IpcManager.this.mClientBinderMap.remove(str);
                }
            }, 0);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public List<IAnalysysClient> getAllClientBinder() {
        if (this.mClientBinderMap != null) {
            return new ArrayList(this.mClientBinderMap.values());
        }
        return null;
    }

    public IAnalysysClient getClientBinder(String str) {
        Map<String, IAnalysysClient> map = this.mClientBinderMap;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public IAnalysysClient getFrontClientBinder() {
        String frontProcessName;
        if (this.mClientBinderMap == null || (frontProcessName = getFrontProcessName()) == null) {
            return null;
        }
        return this.mClientBinderMap.get(frontProcessName);
    }

    public String getFrontProcessName() {
        try {
            Context context = AnalysysUtil.getContext();
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null) {
                return null;
            }
            ComponentName componentName = runningTasks.get(0).topActivity;
            if (componentName == null) {
                return null;
            }
            if (this.mPackageInfo == null) {
                this.mPackageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            }
            for (ActivityInfo activityInfo : this.mPackageInfo.activities) {
                if (TextUtils.equals(activityInfo.name, componentName.getClassName())) {
                    return activityInfo.processName;
                }
            }
            return null;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return null;
        }
    }

    public synchronized IAnalysysMain getMainBinder() {
        if (this.mainProcessBinder == null) {
            Context context = AnalysysUtil.getContext();
            if (CommonUtils.isMainProcess(context)) {
                this.mainProcessBinder = new n();
                setupProxy();
            } else {
                queryMainBinderFromClient(context);
            }
        }
        return this.mainProcessBinder;
    }

    public void init() {
        Context context = AnalysysUtil.getContext();
        if (context == null) {
            return;
        }
        if (CommonUtils.isMainProcess(context)) {
            context.sendBroadcast(new Intent("action_main_boot"));
            ANSLog.i("analysys.ipc", "main process init");
        } else {
            this.mClientProcessBinder = new l();
            setupProxy();
            queryMainBinderFromClient(context);
        }
    }

    public boolean isAppInFront() {
        if (ActivityLifecycleUtils.isActivityResumed()) {
            return true;
        }
        Map<String, IAnalysysClient> map = this.mClientBinderMap;
        if (map == null) {
            return false;
        }
        try {
            Iterator<IAnalysysClient> it = map.values().iterator();
            while (it.hasNext()) {
                if (it.next().isInFront()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return false;
        }
    }

    public boolean isCurrentProcessInFront() {
        return TextUtils.equals(m.a(), getFrontProcessName());
    }
}

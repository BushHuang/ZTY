package com.xuehai.system.mdm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.os.IBinder;
import android.util.Log;
import com.xuehai.system.mdm.MdmAidlInterface;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/xuehai/system/mdm/MdmServiceManager;", "", "()V", "MDM_STU", "", "MDM_TEA", "TAG", "mConnectionMap", "Ljava/util/WeakHashMap;", "Landroid/content/Context;", "Lcom/xuehai/system/mdm/MdmServiceManager$ServiceBinder;", "bindService", "Lcom/xuehai/system/mdm/MdmServiceManager$ServiceToken;", "context", "intent", "Landroid/content/Intent;", "conn", "Landroid/content/ServiceConnection;", "flags", "", "getApplicationInfo", "Landroid/content/pm/ApplicationInfo;", "packageName", "unbindService", "", "token", "ServiceBinder", "ServiceToken", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmServiceManager {
    private static final String MDM_STU = "com.xuehai.launcher";
    private static final String MDM_TEA = "com.xuehai.response_launcher_teacher";
    private static final String TAG = "MdmServiceManager";
    public static final MdmServiceManager INSTANCE = new MdmServiceManager();
    private static final WeakHashMap<Context, ServiceBinder> mConnectionMap = new WeakHashMap<>();

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/xuehai/system/mdm/MdmServiceManager$ServiceBinder;", "Landroid/content/ServiceConnection;", "mCallback", "(Landroid/content/ServiceConnection;)V", "sdkServer", "Lcom/xuehai/system/mdm/MdmAidlInterface;", "getSdkServer", "()Lcom/xuehai/system/mdm/MdmAidlInterface;", "setSdkServer", "(Lcom/xuehai/system/mdm/MdmAidlInterface;)V", "onServiceConnected", "", "className", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ServiceBinder implements ServiceConnection {
        private final ServiceConnection mCallback;
        private MdmAidlInterface sdkServer;

        public ServiceBinder(ServiceConnection serviceConnection) {
            this.mCallback = serviceConnection;
        }

        public final MdmAidlInterface getSdkServer() {
            return this.sdkServer;
        }

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(service, "service");
            this.sdkServer = MdmAidlInterface.Stub.asInterface(service);
            ServiceConnection serviceConnection = this.mCallback;
            if (serviceConnection != null) {
                serviceConnection.onServiceConnected(className, service);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            ServiceConnection serviceConnection = this.mCallback;
            if (serviceConnection != null) {
                serviceConnection.onServiceDisconnected(className);
            }
            this.sdkServer = null;
        }

        public final void setSdkServer(MdmAidlInterface mdmAidlInterface) {
            this.sdkServer = mdmAidlInterface;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/xuehai/system/mdm/MdmServiceManager$ServiceToken;", "", "mWrappedContext", "Landroid/content/ContextWrapper;", "serviceBinder", "Lcom/xuehai/system/mdm/MdmServiceManager$ServiceBinder;", "(Landroid/content/ContextWrapper;Lcom/xuehai/system/mdm/MdmServiceManager$ServiceBinder;)V", "getMWrappedContext", "()Landroid/content/ContextWrapper;", "getServiceBinder", "()Lcom/xuehai/system/mdm/MdmServiceManager$ServiceBinder;", "isValid", "", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ServiceToken {
        private final ContextWrapper mWrappedContext;
        private final ServiceBinder serviceBinder;

        public ServiceToken() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public ServiceToken(ContextWrapper contextWrapper, ServiceBinder serviceBinder) {
            this.mWrappedContext = contextWrapper;
            this.serviceBinder = serviceBinder;
        }

        public ServiceToken(ContextWrapper contextWrapper, ServiceBinder serviceBinder, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : contextWrapper, (i & 2) != 0 ? null : serviceBinder);
        }

        public final ContextWrapper getMWrappedContext() {
            return this.mWrappedContext;
        }

        public final ServiceBinder getServiceBinder() {
            return this.serviceBinder;
        }

        public final boolean isValid() {
            return (this.mWrappedContext == null || this.serviceBinder == null) ? false : true;
        }
    }

    private MdmServiceManager() {
    }

    @JvmStatic
    public static final ServiceToken bindService(Context context, Intent intent, ServiceConnection conn, int flags) {
        ContextWrapper contextWrapper;
        ServiceToken serviceToken;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(conn, "conn");
        intent.setAction("com.xuehai.system.mdm.action_aidl");
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            contextWrapper = activity.getParent() != null ? new ContextWrapper(activity.getParent()) : new ContextWrapper(context);
        }
        ContextWrapper contextWrapper2 = contextWrapper;
        int i = 3;
        ContextWrapper contextWrapper3 = null;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte b5 = 0;
        if (INSTANCE.getApplicationInfo(contextWrapper2, "com.xuehai.launcher") != null) {
            intent.setPackage("com.xuehai.launcher");
            Log.i("MdmServiceManager", "绑定学生mdm service！");
        } else {
            if (INSTANCE.getApplicationInfo(contextWrapper2, "com.xuehai.response_launcher_teacher") == null) {
                Log.e("MdmServiceManager", "mdm service 不存在！");
                return new ServiceToken(b3 == true ? 1 : 0, b2 == true ? 1 : 0, i, b == true ? 1 : 0);
            }
            intent.setPackage("com.xuehai.response_launcher_teacher");
            Log.i("MdmServiceManager", "绑定教师mdm service！");
        }
        try {
            contextWrapper.startService(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        ServiceBinder serviceBinder = new ServiceBinder(conn);
        if (!contextWrapper.bindService(intent, serviceBinder, flags)) {
            Log.e("MdmServiceManager", "mdm service 绑定失败！");
            return new ServiceToken(contextWrapper3, b5 == true ? 1 : 0, i, b4 == true ? 1 : 0);
        }
        synchronized (mConnectionMap) {
            Log.i("MdmServiceManager", "mdm service 绑定成功！");
            mConnectionMap.put(contextWrapper, serviceBinder);
            serviceToken = new ServiceToken(contextWrapper, serviceBinder);
        }
        return serviceToken;
    }

    private final ApplicationInfo getApplicationInfo(Context context, String packageName) {
        try {
            return context.getPackageManager().getApplicationInfo(packageName, 0);
        } catch (Exception unused) {
            return (ApplicationInfo) null;
        }
    }

    @JvmStatic
    public static final void unbindService(ServiceToken token) {
        ServiceBinder serviceBinderRemove;
        synchronized (mConnectionMap) {
            if (token != null) {
                ContextWrapper mWrappedContext = token.getMWrappedContext();
                if (mWrappedContext != null && (serviceBinderRemove = mConnectionMap.remove(mWrappedContext)) != null) {
                    try {
                        mWrappedContext.unbindService(serviceBinderRemove);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }
}

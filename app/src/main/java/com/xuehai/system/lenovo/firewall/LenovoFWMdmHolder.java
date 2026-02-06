package com.xuehai.system.lenovo.firewall;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.com.microtrust.firewall.aidl.IAFWService;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.lenovo.firewall.LenovoFirewallServiceManager;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0007J\u0006\u0010\u001b\u001a\u00020\u0017J\b\u0010\u001c\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/xuehai/system/lenovo/firewall/LenovoFWMdmHolder;", "", "()V", "TAG", "", "mAidlConnectionStatus", "Lcom/xuehai/system/lenovo/firewall/AidlConnectionStatus;", "getMAidlConnectionStatus", "()Lcom/xuehai/system/lenovo/firewall/AidlConnectionStatus;", "mServiceToken", "Lcom/xuehai/system/lenovo/firewall/LenovoFirewallServiceManager$ServiceToken;", "serviceConnection", "Landroid/content/ServiceConnection;", "weakReference", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "getWeakReference", "()Ljava/lang/ref/WeakReference;", "setWeakReference", "(Ljava/lang/ref/WeakReference;)V", "getMdmService", "Lcn/com/microtrust/firewall/aidl/IAFWService;", "isMdmServiceAlive", "", "registerMdm", "", "context", "testBinderLink", "unBindMdm", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LenovoFWMdmHolder {
    private static LenovoFirewallServiceManager.ServiceToken mServiceToken;
    private static WeakReference<Context> weakReference;
    public static final LenovoFWMdmHolder INSTANCE = new LenovoFWMdmHolder();
    private static final AidlConnectionStatus mAidlConnectionStatus = new AidlConnectionStatus(5000);
    private static final String TAG = "LenovoFWMdmHolder";
    private static final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MdmLog.d(LenovoFWMdmHolder.TAG, "MDM连接成功：" + name);
            LenovoFWMdmHolder.INSTANCE.getMAidlConnectionStatus().connectionSuccess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Context context;
            MdmLog.e(LenovoFWMdmHolder.TAG, "MDM断开：" + name);
            WeakReference<Context> weakReference2 = LenovoFWMdmHolder.INSTANCE.getWeakReference();
            if (weakReference2 == null || (context = weakReference2.get()) == null) {
                return;
            }
            LenovoFWMdmHolder.registerMdm(context);
        }
    };

    private LenovoFWMdmHolder() {
    }

    @JvmStatic
    public static final void registerMdm(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        weakReference = new WeakReference<>(context);
        if (INSTANCE.testBinderLink()) {
            MdmLog.d(TAG, "测试MDM正常，无需重新绑定");
            return;
        }
        if (mAidlConnectionStatus.isConnecting()) {
            return;
        }
        INSTANCE.unBindMdm();
        MdmLog.i(TAG, "MDM绑定");
        LenovoFirewallServiceManager.ServiceToken serviceTokenBindService = LenovoFirewallServiceManager.bindService(context, new Intent(), serviceConnection, 1);
        mServiceToken = serviceTokenBindService;
        if (serviceTokenBindService.isValid()) {
            MdmLog.i(TAG, "MDM AIDL 连接成功 " + mServiceToken);
            mAidlConnectionStatus.connectionStart();
            return;
        }
        mAidlConnectionStatus.setMCurrentStatus(3);
        MdmLog.w(TAG, "MDM AIDL 连接失败 " + mServiceToken);
    }

    private final void unBindMdm() {
        if (mServiceToken != null) {
            MdmLog.i(TAG, "MDM解绑 : " + mServiceToken);
            LenovoFirewallServiceManager.unbindService(mServiceToken);
            mServiceToken = null;
        }
        mAidlConnectionStatus.setMCurrentStatus(0);
    }

    public final AidlConnectionStatus getMAidlConnectionStatus() {
        return mAidlConnectionStatus;
    }

    public final IAFWService getMdmService() {
        LenovoFirewallServiceManager.ServiceBinder serviceBinder;
        LenovoFirewallServiceManager.ServiceToken serviceToken = mServiceToken;
        if (serviceToken == null || (serviceBinder = serviceToken.getServiceBinder()) == null) {
            return null;
        }
        return serviceBinder.getSdkServer();
    }

    public final WeakReference<Context> getWeakReference() {
        return weakReference;
    }

    public final boolean isMdmServiceAlive() {
        return getMdmService() != null;
    }

    public final void setWeakReference(WeakReference<Context> weakReference2) {
        weakReference = weakReference2;
    }

    public final boolean testBinderLink() {
        try {
            IAFWService mdmService = getMdmService();
            return (mdmService != null ? mdmService.isEnable() : null) != null;
        } catch (Throwable unused) {
            return false;
        }
    }
}

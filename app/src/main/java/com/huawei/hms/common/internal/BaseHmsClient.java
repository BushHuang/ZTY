package com.huawei.hms.common.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.adapter.BinderAdapter;
import com.huawei.hms.adapter.InnerBinderAdapter;
import com.huawei.hms.adapter.OuterBinderAdapter;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.FailedBinderCallBack;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.IPCTransport;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.support.api.client.AidlApiClient;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseHmsClient implements AidlApiClient {
    private static final int BINDING = 5;
    private static final int CONNECTED = 3;
    private static final int DISCONNECTED = 1;
    private static final int MSG_CONN_TIMEOUT = 2;
    private static final String TAG = "BaseHmsClient";
    protected static final int TIMEOUT_DISCONNECTED = 6;
    private static BinderAdapter mInnerBinderAdapter;
    private static BinderAdapter mOuterBinderAdapter;
    private String mAppID;
    private final ClientSettings mClientSettings;
    private Handler mConnectTimeoutHandler = null;
    private final Context mContext;
    private volatile d mService;
    protected String sessionId;
    private static final Object LOCK_CONNECT_TIMEOUT_HANDLER = new Object();
    private static final AtomicInteger CONN_STATUS = new AtomicInteger(1);
    private static final AtomicInteger INNER_CONN_STATUS = new AtomicInteger(1);

    public interface ConnectionCallbacks {
        public static final int CAUSE_API_CLIENT_EXPIRED = 3;
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected();

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public BaseHmsClient(Context context, ClientSettings clientSettings, OnConnectionFailedListener onConnectionFailedListener, ConnectionCallbacks connectionCallbacks) {
        this.mContext = context;
        this.mClientSettings = clientSettings;
        this.mAppID = clientSettings.getAppID();
    }

    private void bindCoreInternal(String str, String str2) {
        if (this.mClientSettings.isUseInnerHms()) {
            mInnerBinderAdapter = InnerBinderAdapter.getInstance(this.mContext, str2, str);
            if (!isConnected()) {
                setConnectStatus(5);
                mInnerBinderAdapter.binder(newBinderCallBack());
                return;
            } else {
                HMSLog.i("BaseHmsClient", "The binder is already connected.");
                getAdapter().updateDelayTask();
                connectedInternal(getAdapter().getServiceBinder());
                return;
            }
        }
        mOuterBinderAdapter = OuterBinderAdapter.getInstance(this.mContext, str2, str);
        if (!isConnected()) {
            setConnectStatus(5);
            mOuterBinderAdapter.binder(newBinderCallBack());
        } else {
            HMSLog.i("BaseHmsClient", "The binder is already connected.");
            getAdapter().updateDelayTask();
            connectedInternal(getAdapter().getServiceBinder());
        }
    }

    private void cancelConnDelayHandle() {
        synchronized (LOCK_CONNECT_TIMEOUT_HANDLER) {
            if (this.mConnectTimeoutHandler != null) {
                this.mConnectTimeoutHandler.removeMessages(2);
                this.mConnectTimeoutHandler = null;
            }
        }
    }

    private void checkAvailabilityAndConnect(int i, boolean z) {
        HMSLog.i("BaseHmsClient", "====== HMSSDK version: 60400302 ======");
        int i2 = (this.mClientSettings.isUseInnerHms() ? INNER_CONN_STATUS : CONN_STATUS).get();
        HMSLog.i("BaseHmsClient", "Enter connect, Connection Status: " + i2);
        if (z || !(i2 == 3 || i2 == 5)) {
            if (getMinApkVersion() > i) {
                i = getMinApkVersion();
            }
            HMSLog.i("BaseHmsClient", "connect minVersion:" + i + " packageName:" + this.mClientSettings.getInnerHmsPkg());
            if (this.mContext.getPackageName().equals(this.mClientSettings.getInnerHmsPkg())) {
                HMSLog.i("BaseHmsClient", "service packageName is same, bind core service return");
                bindCoreService();
                return;
            }
            if (!Util.isAvailableLibExist(this.mContext)) {
                int iIsHuaweiMobileServicesAvailable = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this.mContext, i);
                HMSLog.i("BaseHmsClient", "HuaweiApiAvailability check available result: " + iIsHuaweiMobileServicesAvailable);
                if (iIsHuaweiMobileServicesAvailable == 0) {
                    bindCoreService();
                    return;
                } else {
                    notifyFailed(iIsHuaweiMobileServicesAvailable);
                    return;
                }
            }
            AvailableAdapter availableAdapter = new AvailableAdapter(i);
            int iIsHuaweiMobileServicesAvailable2 = availableAdapter.isHuaweiMobileServicesAvailable(this.mContext);
            HMSLog.i("BaseHmsClient", "check available result: " + iIsHuaweiMobileServicesAvailable2);
            if (iIsHuaweiMobileServicesAvailable2 == 0) {
                bindCoreService();
                return;
            }
            if (availableAdapter.isUserResolvableError(iIsHuaweiMobileServicesAvailable2)) {
                HMSLog.i("BaseHmsClient", "bindCoreService3.0 fail, start resolution now.");
                resolution(availableAdapter, iIsHuaweiMobileServicesAvailable2);
            } else {
                if (availableAdapter.isUserNoticeError(iIsHuaweiMobileServicesAvailable2)) {
                    HMSLog.i("BaseHmsClient", "bindCoreService3.0 fail, start notice now.");
                    notice(availableAdapter, iIsHuaweiMobileServicesAvailable2);
                    return;
                }
                HMSLog.i("BaseHmsClient", "bindCoreService3.0 fail: " + iIsHuaweiMobileServicesAvailable2 + " is not resolvable.");
                notifyFailed(iIsHuaweiMobileServicesAvailable2);
            }
        }
    }

    private BinderAdapter.BinderCallBack newBinderCallBack() {
        return new BinderAdapter.BinderCallBack() {
            @Override
            public void onBinderFailed(int i) {
                onBinderFailed(i, null);
            }

            @Override
            public void onBinderFailed(int i, Intent intent) {
                if (intent == null) {
                    HMSLog.i("BaseHmsClient", "onBinderFailed: intent is null!");
                    BaseHmsClient.this.notifyFailed(new ConnectionResult(10, (PendingIntent) null));
                    BaseHmsClient.this.mService = null;
                    return;
                }
                Activity activeActivity = Util.getActiveActivity(BaseHmsClient.this.getClientSettings().getCpActivity(), BaseHmsClient.this.getContext());
                if (activeActivity == null) {
                    HMSLog.i("BaseHmsClient", "onBinderFailed: return pendingIntent to kit and cp");
                    BaseHmsClient.this.notifyFailed(new ConnectionResult(10, PendingIntent.getActivity(BaseHmsClient.this.mContext, 11, intent, 67108864)));
                    BaseHmsClient.this.mService = null;
                    return;
                }
                HMSLog.i("BaseHmsClient", "onBinderFailed: SDK try to resolve and reConnect!");
                long time = new Timestamp(System.currentTimeMillis()).getTime();
                FailedBinderCallBack.getInstance().setCallBack(Long.valueOf(time), new FailedBinderCallBack.BinderCallBack() {
                    @Override
                    public void binderCallBack(int i2) {
                        if (i2 != 0) {
                            BaseHmsClient.this.notifyFailed(new ConnectionResult(10, (PendingIntent) null));
                            BaseHmsClient.this.mService = null;
                        }
                    }
                });
                intent.putExtra("callId", time);
                activeActivity.startActivity(intent);
            }

            @Override
            public void onNullBinding(ComponentName componentName) {
                BaseHmsClient.this.setConnectStatus(1);
                BaseHmsClient.this.notifyFailed(10);
            }

            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                HMSLog.i("BaseHmsClient", "Enter onServiceConnected.");
                BaseHmsClient.this.connectedInternal(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                HMSLog.i("BaseHmsClient", "Enter onServiceDisconnected.");
                BaseHmsClient.this.setConnectStatus(1);
                RequestManager.getHandler().sendEmptyMessage(10013);
            }

            @Override
            public void onTimedDisconnected() {
                BaseHmsClient.this.setConnectStatus(6);
            }
        };
    }

    private void notice(AvailableAdapter availableAdapter, int i) {
        HMSLog.i("BaseHmsClient", "enter notice");
        if (!getClientSettings().isHasActivity()) {
            if (i == 29) {
                i = 9;
            }
            notifyFailed(new ConnectionResult(26, HuaweiApiAvailability.getInstance().getErrPendingIntent(this.mContext, i, 0)));
        } else {
            Activity activeActivity = Util.getActiveActivity(getClientSettings().getCpActivity(), getContext());
            if (activeActivity != null) {
                availableAdapter.startNotice(activeActivity, new AvailableAdapter.AvailableCallBack() {
                    @Override
                    public void onComplete(int i2) {
                        BaseHmsClient.this.notifyFailed(i2);
                    }
                });
            } else {
                notifyFailed(26);
            }
        }
    }

    private void notifyFailed(int i) {
        HMSLog.i("BaseHmsClient", "notifyFailed result: " + i);
        Message message = new Message();
        message.what = 10012;
        message.arg1 = i;
        RequestManager.getHandler().sendMessage(message);
    }

    private void notifyFailed(ConnectionResult connectionResult) {
        HMSLog.i("BaseHmsClient", "notifyFailed result: " + connectionResult.getErrorCode());
        Message message = new Message();
        message.what = 10012;
        message.obj = connectionResult;
        RequestManager.getHandler().sendMessage(message);
    }

    private void resolution(AvailableAdapter availableAdapter, int i) {
        HMSLog.i("BaseHmsClient", "enter HmsCore resolution");
        if (!getClientSettings().isHasActivity()) {
            notifyFailed(new ConnectionResult(26, HuaweiApiAvailability.getInstance().getErrPendingIntent(this.mContext, i, 0)));
            return;
        }
        Activity activeActivity = Util.getActiveActivity(getClientSettings().getCpActivity(), getContext());
        if (activeActivity != null) {
            availableAdapter.startResolution(activeActivity, new AvailableAdapter.AvailableCallBack() {
                @Override
                public void onComplete(int i2) {
                    if (i2 == 0) {
                        BaseHmsClient.this.bindCoreService();
                    } else {
                        BaseHmsClient.this.notifyFailed(i2);
                    }
                }
            });
        } else {
            notifyFailed(26);
        }
    }

    private void tryUnBind() {
        HMSLog.w("BaseHmsClient", "Failed to get service as interface, trying to unbind.");
        if (this.mClientSettings.isUseInnerHms()) {
            BinderAdapter binderAdapter = mInnerBinderAdapter;
            if (binderAdapter == null) {
                HMSLog.w("BaseHmsClient", "mInnerBinderAdapter is null.");
                return;
            }
            binderAdapter.unBind();
        } else {
            BinderAdapter binderAdapter2 = mOuterBinderAdapter;
            if (binderAdapter2 == null) {
                HMSLog.w("BaseHmsClient", "mOuterBinderAdapter is null.");
                return;
            }
            binderAdapter2.unBind();
        }
        setConnectStatus(1);
        notifyFailed(10);
    }

    private void unBindAdapter() {
        if (this.mClientSettings.isUseInnerHms()) {
            BinderAdapter binderAdapter = mInnerBinderAdapter;
            if (binderAdapter != null) {
                binderAdapter.unBind();
                return;
            }
            return;
        }
        BinderAdapter binderAdapter2 = mOuterBinderAdapter;
        if (binderAdapter2 != null) {
            binderAdapter2.unBind();
        }
    }

    void bindCoreService() {
        String innerHmsPkg = this.mClientSettings.getInnerHmsPkg();
        String serviceAction = getServiceAction();
        HMSLog.i("BaseHmsClient", "enter bindCoreService, packageName is " + innerHmsPkg + ", serviceAction is " + serviceAction);
        bindCoreInternal(innerHmsPkg, serviceAction);
    }

    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void connect(int i) {
        checkAvailabilityAndConnect(i, false);
    }

    public void connect(int i, boolean z) {
        checkAvailabilityAndConnect(i, z);
    }

    public void connectedInternal(IBinder iBinder) {
        this.mService = d.a.a(iBinder);
        if (this.mService != null) {
            onConnecting();
        } else {
            HMSLog.e("BaseHmsClient", "mService is null, try to unBind.");
            tryUnBind();
        }
    }

    protected final void connectionConnected() {
        setConnectStatus(3);
        RequestManager.getHandler().sendEmptyMessage(10011);
    }

    public void disconnect() {
        int i = (this.mClientSettings.isUseInnerHms() ? INNER_CONN_STATUS : CONN_STATUS).get();
        HMSLog.i("BaseHmsClient", "Enter disconnect, Connection Status: " + i);
        if (i != 1) {
            if (i == 3) {
                unBindAdapter();
                setConnectStatus(1);
            } else {
                if (i != 5) {
                    return;
                }
                cancelConnDelayHandle();
                setConnectStatus(1);
            }
        }
    }

    public BinderAdapter getAdapter() {
        HMSLog.i("BaseHmsClient", "getAdapter:isInner:" + this.mClientSettings.isUseInnerHms() + ", mInnerBinderAdapter:" + mInnerBinderAdapter + ", mOuterBinderAdapter:" + mOuterBinderAdapter);
        return this.mClientSettings.isUseInnerHms() ? mInnerBinderAdapter : mOuterBinderAdapter;
    }

    @Override
    public List<String> getApiNameList() {
        return this.mClientSettings.getApiName();
    }

    @Override
    public String getAppID() {
        return this.mAppID;
    }

    protected ClientSettings getClientSettings() {
        return this.mClientSettings;
    }

    public int getConnectionStatus() {
        return (this.mClientSettings.isUseInnerHms() ? INNER_CONN_STATUS : CONN_STATUS).get();
    }

    @Override
    public Context getContext() {
        return this.mContext;
    }

    @Override
    public String getCpID() {
        return this.mClientSettings.getCpID();
    }

    @Deprecated
    public int getMinApkVersion() {
        return 30000000;
    }

    @Override
    public String getPackageName() {
        return this.mClientSettings.getClientPackageName();
    }

    @Override
    public d getService() {
        return this.mService;
    }

    public String getServiceAction() {
        HMSPackageManager hMSPackageManager = HMSPackageManager.getInstance(this.mContext);
        return this.mClientSettings.isUseInnerHms() ? hMSPackageManager.getInnerServiceAction() : hMSPackageManager.getServiceAction();
    }

    @Override
    public String getSessionId() {
        return this.sessionId;
    }

    @Override
    public SubAppInfo getSubAppInfo() {
        return this.mClientSettings.getSubAppID();
    }

    @Override
    public String getTransportName() {
        return IPCTransport.class.getName();
    }

    @Override
    public boolean isConnected() {
        return !this.mClientSettings.isUseInnerHms() ? CONN_STATUS.get() != 3 : INNER_CONN_STATUS.get() != 3;
    }

    public boolean isConnecting() {
        return (this.mClientSettings.isUseInnerHms() ? INNER_CONN_STATUS : CONN_STATUS).get() == 5;
    }

    public void onConnecting() {
        connectionConnected();
    }

    void setConnectStatus(int i) {
        if (this.mClientSettings.isUseInnerHms()) {
            INNER_CONN_STATUS.set(i);
        } else {
            CONN_STATUS.set(i);
        }
    }

    public void setService(d dVar) {
        this.mService = dVar;
    }
}

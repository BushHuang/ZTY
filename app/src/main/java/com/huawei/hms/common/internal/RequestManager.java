package com.huawei.hms.common.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.support.log.HMSLog;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestManager implements Handler.Callback {
    public static final int NOTIFY_CONNECT_FAILED = 10012;
    public static final int NOTIFY_CONNECT_SUCCESS = 10011;
    public static final int NOTIFY_CONNECT_SUSPENDED = 10013;
    private static final String TAG = "RequestManager";
    private static Handler mHandler;
    private static volatile RequestManager mInstance;
    private static final Object LOCK_OBJECT = new Object();
    private static Queue<HuaweiApi.RequestHandler> requestQueue = new ConcurrentLinkedQueue();
    private static Map<String, HuaweiApi.RequestHandler> connectedReqMap = new LinkedHashMap();

    private RequestManager(Looper looper) {
        mHandler = new Handler(looper, this);
    }

    public static void addRequestToQueue(HuaweiApi.RequestHandler requestHandler) {
        requestQueue.add(requestHandler);
    }

    public static void addToConnectedReqMap(final String str, final HuaweiApi.RequestHandler requestHandler) {
        if (mHandler == null) {
            return;
        }
        HMSLog.i("RequestManager", "addToConnectedReqMap");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                RequestManager.connectedReqMap.put(str, requestHandler);
            }
        });
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static RequestManager getInstance() {
        synchronized (LOCK_OBJECT) {
            if (mInstance == null) {
                HandlerThread handlerThread = new HandlerThread("RequestManager");
                handlerThread.start();
                mInstance = new RequestManager(handlerThread.getLooper());
            }
        }
        return mInstance;
    }

    private void handleConnectFailed(Message message) {
        HMSLog.i("RequestManager", "NOTIFY_CONNECT_FAILED.");
        while (!requestQueue.isEmpty()) {
            HuaweiApi.RequestHandler requestHandlerPoll = requestQueue.poll();
            Object obj = message.obj;
            if (obj == null) {
                requestHandlerPoll.onConnectionFailed(new ConnectionResult(message.arg1));
            } else if (obj instanceof ConnectionResult) {
                requestHandlerPoll.onConnectionFailed((ConnectionResult) obj);
            } else {
                requestHandlerPoll.onConnectionFailed(new ConnectionResult(8));
            }
        }
    }

    private void handleConnectSuccess() {
        while (!requestQueue.isEmpty()) {
            HuaweiApi.RequestHandler requestHandlerPoll = requestQueue.poll();
            if (requestHandlerPoll != null) {
                Object client = requestHandlerPoll.getClient();
                if (client instanceof BaseHmsClient) {
                    BaseHmsClient baseHmsClient = (BaseHmsClient) client;
                    baseHmsClient.setService(d.a.a(baseHmsClient.getAdapter().getServiceBinder()));
                    requestHandlerPoll.onConnected();
                }
            }
        }
    }

    private void handleConnectSuspend() {
        HMSLog.i("RequestManager", "NOTIFY_CONNECT_SUSPENDED.");
        while (!requestQueue.isEmpty()) {
            requestQueue.poll().onConnectionSuspended(1);
        }
        notifyRunningRequestConnectSuspend();
    }

    private void notifyRunningRequestConnectSuspend() {
        HMSLog.i("RequestManager", "notifyRunningRequestConnectSuspend, connectedReqMap.size(): " + connectedReqMap.size());
        Iterator<Map.Entry<String, HuaweiApi.RequestHandler>> it = connectedReqMap.entrySet().iterator();
        while (it.hasNext()) {
            try {
                it.next().getValue().onConnectionSuspended(1);
            } catch (RuntimeException e) {
                HMSLog.e("RequestManager", "NOTIFY_CONNECT_SUSPENDED Exception: " + e.getMessage());
            }
            it.remove();
        }
    }

    public static void removeReqByTransId(final String str) {
        if (mHandler == null) {
            return;
        }
        HMSLog.i("RequestManager", "removeReqByTransId");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                RequestManager.connectedReqMap.remove(str);
            }
        });
    }

    @Override
    public boolean handleMessage(Message message) {
        if (message == null) {
            return false;
        }
        HMSLog.i("RequestManager", "RequestManager handleMessage.");
        switch (message.what) {
            case 10011:
                handleConnectSuccess();
                break;
            case 10012:
                handleConnectFailed(message);
                break;
            case 10013:
                handleConnectSuspend();
                break;
            default:
                HMSLog.i("RequestManager", "handleMessage unknown msg:" + message.what);
                break;
        }
        return false;
    }
}

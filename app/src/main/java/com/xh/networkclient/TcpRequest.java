package com.xh.networkclient;

import com.xh.logutils.Log;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.Task;
import com.xh.networkclient.common.TaskType;
import com.xh.xhcore.common.util.XHLog;

public class TcpRequest {

    private static class Singleton {
        private static final TcpRequest INSTANCE = new TcpRequest(null);

        private Singleton() {
        }
    }

    static {
        System.loadLibrary("xh_curl");
        System.loadLibrary("xh_common");
        System.loadLibrary("xh_network_client");
    }

    private TcpRequest() {
    }

    TcpRequest(TcpRequest tcpRequest) {
        this();
    }

    private native int JNIConnect(int i, String str, int i2, String str2);

    private native int JNIDisConnect(int i);

    private native int JNISend(int i, byte[] bArr, int i2, String str);

    public static final TcpRequest getInstance() {
        return Singleton.INSTANCE;
    }

    public int connect(String str, int i, String str2, NetworkCallback networkCallback) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " ip:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " port:" + i, new Object[0]);
        int id = CommonUtils.getId();
        CommonUtils.addTask(Integer.valueOf(id), new Task(id, null, TaskType.TASK_TCP_CONNECT, networkCallback));
        if (JNIConnect(id, str, i, str2) == 0) {
            return id;
        }
        Log.e(Log.XH_NETWORK_CLIENT_TAG, "JNIConnect fail");
        CommonUtils.removeTask(Integer.valueOf(id));
        return 0;
    }

    public int disConnect(int i) {
        JNIDisConnect(i);
        CommonUtils.removeTask(Integer.valueOf(i));
        return 0;
    }

    public int send(int i, byte[] bArr, int i2, String str) {
        return JNISend(i, bArr, i2, str);
    }
}

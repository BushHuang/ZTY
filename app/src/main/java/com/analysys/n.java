package com.analysys;

import android.os.IBinder;
import com.analysys.ipc.IAnalysysClient;
import com.analysys.ipc.IAnalysysMain;
import com.analysys.ipc.IIpcProxy;
import com.analysys.ipc.IpcManager;
import com.analysys.utils.ANSLog;
import java.util.Map;

public class n extends IAnalysysMain.a {

    private IIpcProxy f65a;

    public void a(IIpcProxy iIpcProxy) {
        if (this.f65a == iIpcProxy) {
            return;
        }
        ANSLog.i("analysys.ipc", "set main proxy");
        this.f65a = iIpcProxy;
    }

    public void a(String str) {
        IIpcProxy iIpcProxy = this.f65a;
        if (iIpcProxy != null) {
            iIpcProxy.sendVisualEditEvent2Client(str);
        }
    }

    @Override
    public void reportVisualEvent(String str, String str2, Map map) {
        IIpcProxy iIpcProxy = this.f65a;
        if (iIpcProxy != null) {
            iIpcProxy.reportVisualEvent(str, str2, map);
        }
    }

    @Override
    public void setClientBinder(String str, IBinder iBinder) {
        IpcManager.getInstance().addClientBinder(str, IAnalysysClient.a.a(iBinder));
    }
}

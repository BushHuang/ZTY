package com.huawei.hms.api;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.ResponseHeader;
import com.huawei.hms.core.aidl.c;
import com.huawei.hms.core.aidl.e;
import com.huawei.hms.support.api.transport.DatagramTransport;
import com.huawei.hms.support.log.HMSLog;

public class IPCCallback extends c.a {

    private final Class<? extends IMessageEntity> f234a;
    private final DatagramTransport.a b;

    public IPCCallback(Class<? extends IMessageEntity> cls, DatagramTransport.a aVar) {
        this.f234a = cls;
        this.b = aVar;
    }

    @Override
    public void call(com.huawei.hms.core.aidl.b bVar) throws RemoteException {
        if (bVar == null || TextUtils.isEmpty(bVar.f250a)) {
            HMSLog.e("IPCCallback", "In call, URI cannot be empty.");
            throw new RemoteException();
        }
        e eVarA = com.huawei.hms.core.aidl.a.a(bVar.c());
        IMessageEntity iMessageEntityNewResponseInstance = null;
        if (bVar.b() > 0 && (iMessageEntityNewResponseInstance = newResponseInstance()) != null) {
            eVarA.a(bVar.a(), iMessageEntityNewResponseInstance);
        }
        if (bVar.b == null) {
            this.b.a(0, iMessageEntityNewResponseInstance);
            return;
        }
        ResponseHeader responseHeader = new ResponseHeader();
        eVarA.a(bVar.b, responseHeader);
        this.b.a(responseHeader.getStatusCode(), iMessageEntityNewResponseInstance);
    }

    protected IMessageEntity newResponseInstance() {
        Class<? extends IMessageEntity> cls = this.f234a;
        if (cls == null) {
            return null;
        }
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            HMSLog.e("IPCCallback", "In newResponseInstance, instancing exception." + e.getMessage());
            return null;
        }
    }
}

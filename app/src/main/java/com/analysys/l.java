package com.analysys;

import com.analysys.ipc.BytesParcelable;
import com.analysys.ipc.IAnalysysClient;
import com.analysys.ipc.IIpcProxy;
import com.analysys.utils.ANSLog;
import com.analysys.utils.ActivityLifecycleUtils;

public class l extends IAnalysysClient.a {

    private IIpcProxy f63a;

    public void a(IIpcProxy iIpcProxy) {
        if (this.f63a == iIpcProxy) {
            return;
        }
        ANSLog.i("analysys.ipc", "set client proxy");
        this.f63a = iIpcProxy;
    }

    @Override
    public void clearVisualSnapshot() {
        IIpcProxy iIpcProxy = this.f63a;
        if (iIpcProxy != null) {
            iIpcProxy.clearVisualSnapshot();
        }
    }

    @Override
    public BytesParcelable getVisualSnapshotData(String str, boolean z) {
        IIpcProxy iIpcProxy = this.f63a;
        if (iIpcProxy != null) {
            return iIpcProxy.getVisualSnapshotData(str, z);
        }
        return null;
    }

    @Override
    public boolean isInFront() {
        return ActivityLifecycleUtils.isActivityResumed();
    }

    @Override
    public void onVisualEditEvent(String str) {
        IIpcProxy iIpcProxy = this.f63a;
        if (iIpcProxy != null) {
            iIpcProxy.onVisualEditEvent(str);
        }
    }

    @Override
    public void reloadVisualEventLocal() {
        IIpcProxy iIpcProxy = this.f63a;
        if (iIpcProxy != null) {
            iIpcProxy.reloadVisualEventLocal();
        }
    }

    @Override
    public void setVisualEditing(boolean z) {
        IIpcProxy iIpcProxy = this.f63a;
        if (iIpcProxy != null) {
            iIpcProxy.setVisualEditing(z);
        }
    }
}

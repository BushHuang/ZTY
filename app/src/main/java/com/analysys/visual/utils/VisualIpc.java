package com.analysys.visual.utils;

import com.analysys.AnalysysAgent;
import com.analysys.ipc.BytesParcelable;
import com.analysys.ipc.IAnalysysClient;
import com.analysys.ipc.IAnalysysMain;
import com.analysys.ipc.IIpcProxy;
import com.analysys.ipc.IpcManager;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import com.analysys.visual.a;
import com.analysys.visual.ag;
import com.analysys.visual.b;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class VisualIpc implements IIpcProxy {
    private static final int MAX_BINDER_SIZE = 102400;
    private static VisualIpc sInstance = new VisualIpc();
    private byte[] mSnapshotTmpData;
    private int mSnapshotTmpDataIdx;
    private ag mSnapshotWrapper = new ag();

    private VisualIpc() {
    }

    public static VisualIpc getInstance() {
        return sInstance;
    }

    @Override
    public void clearVisualSnapshot() {
        List<IAnalysysClient> allClientBinder;
        this.mSnapshotWrapper.a();
        if (!CommonUtils.isMainProcess(AnalysysUtil.getContext()) || (allClientBinder = IpcManager.getInstance().getAllClientBinder()) == null) {
            return;
        }
        try {
            Iterator<IAnalysysClient> it = allClientBinder.iterator();
            while (it.hasNext()) {
                it.next().clearVisualSnapshot();
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public BytesParcelable getVisualSnapshotData(String str, boolean z) {
        BytesParcelable visualSnapshotData;
        boolean z2 = true;
        if (CommonUtils.isMainProcess(AnalysysUtil.getContext())) {
            if (!b.a().b()) {
                setVisualEditing(true);
            }
            if (IpcManager.getInstance().isCurrentProcessInFront()) {
                this.mSnapshotWrapper.a(str);
                return new BytesParcelable(this.mSnapshotWrapper.a(z), true);
            }
            IAnalysysClient frontClientBinder = IpcManager.getInstance().getFrontClientBinder();
            if (frontClientBinder != null) {
                BytesParcelable bytesParcelable = new BytesParcelable();
                do {
                    try {
                        visualSnapshotData = frontClientBinder.getVisualSnapshotData(str, z);
                        if (visualSnapshotData != null && visualSnapshotData.data != null) {
                            bytesParcelable.appendData(visualSnapshotData);
                        }
                        return null;
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                        return null;
                    }
                } while (!visualSnapshotData.finish);
                return bytesParcelable;
            }
        } else {
            this.mSnapshotWrapper.a(str);
            if (this.mSnapshotTmpData == null) {
                this.mSnapshotTmpData = this.mSnapshotWrapper.a(z);
            }
            byte[] bArr = this.mSnapshotTmpData;
            if (bArr != null) {
                int length = this.mSnapshotTmpDataIdx + 102400;
                if (length >= bArr.length) {
                    length = bArr.length;
                }
                byte[] bArrCopyOfRange = Arrays.copyOfRange(this.mSnapshotTmpData, this.mSnapshotTmpDataIdx, length);
                if (length == this.mSnapshotTmpData.length) {
                    this.mSnapshotTmpDataIdx = 0;
                    this.mSnapshotTmpData = null;
                } else {
                    this.mSnapshotTmpDataIdx = length;
                    z2 = false;
                }
                return new BytesParcelable(bArrCopyOfRange, z2);
            }
        }
        return null;
    }

    @Override
    public void onVisualEditEvent(String str) {
        List<IAnalysysClient> allClientBinder;
        b.a().c(str);
        if (!CommonUtils.isMainProcess(AnalysysUtil.getContext()) || (allClientBinder = IpcManager.getInstance().getAllClientBinder()) == null) {
            return;
        }
        try {
            Iterator<IAnalysysClient> it = allClientBinder.iterator();
            while (it.hasNext()) {
                it.next().onVisualEditEvent(str);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void reloadVisualEventLocal() {
        if (!CommonUtils.isMainProcess(AnalysysUtil.getContext())) {
            b.a().c();
            return;
        }
        List<IAnalysysClient> allClientBinder = IpcManager.getInstance().getAllClientBinder();
        if (allClientBinder != null) {
            try {
                Iterator<IAnalysysClient> it = allClientBinder.iterator();
                while (it.hasNext()) {
                    it.next().reloadVisualEventLocal();
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
    }

    @Override
    public void reportVisualEvent(String str, String str2, Map map) {
        if (!CommonUtils.isMainProcess(AnalysysUtil.getContext())) {
            IAnalysysMain mainBinder = IpcManager.getInstance().getMainBinder();
            if (mainBinder != null) {
                try {
                    mainBinder.reportVisualEvent(str, str2, map);
                    return;
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                    return;
                }
            }
            return;
        }
        ANSLog.i("analysys.visual", "Report event: " + str + ", properties: " + map);
        if (b.a().b()) {
            a.a().a(str, str2, map);
        } else {
            AnalysysAgent.track(AnalysysUtil.getContext(), str, map);
        }
    }

    @Override
    public void sendVisualEditEvent2Client(String str) {
        IAnalysysClient clientBinder;
        if (b.a().b() && (clientBinder = IpcManager.getInstance().getClientBinder(str)) != null) {
            try {
                clientBinder.setVisualEditing(true);
                JSONArray jSONArrayD = b.a().d();
                if (jSONArrayD.length() == 0) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("recordtype", "add");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("events", jSONArrayD);
                jSONObject.put("payload", jSONObject2);
                clientBinder.onVisualEditEvent(jSONObject.toString());
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
    }

    @Override
    public void setVisualEditing(boolean z) {
        List<IAnalysysClient> allClientBinder;
        b.a().a(z);
        if (!CommonUtils.isMainProcess(AnalysysUtil.getContext()) || (allClientBinder = IpcManager.getInstance().getAllClientBinder()) == null) {
            return;
        }
        try {
            Iterator<IAnalysysClient> it = allClientBinder.iterator();
            while (it.hasNext()) {
                it.next().setVisualEditing(z);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}

package com.analysys.visual;

import android.text.TextUtils;
import com.analysys.ipc.BytesParcelable;
import com.analysys.ipc.IpcManager;
import com.analysys.utils.ANSLog;
import com.analysys.utils.ExceptionUtil;
import com.analysys.visual.utils.VisualIpc;
import java.io.OutputStream;
import org.json.JSONObject;

public class ac implements ad {

    private String f87a;
    private String b;

    public void a() {
        VisualIpc.getInstance().clearVisualSnapshot();
    }

    @Override
    public void a(Object obj, OutputStream outputStream) {
        try {
            JSONObject jSONObject = ((JSONObject) obj).getJSONObject("payload");
            boolean z = true;
            if (jSONObject.has("config")) {
                ANSLog.i("analysys.visual", "snapshot init config");
                this.f87a = jSONObject.toString();
            }
            String frontProcessName = IpcManager.getInstance().getFrontProcessName();
            if (!TextUtils.isEmpty(frontProcessName) && !TextUtils.isEmpty(this.b) && !TextUtils.equals(this.b, frontProcessName)) {
                z = false;
            }
            this.b = frontProcessName;
            BytesParcelable visualSnapshotData = VisualIpc.getInstance().getVisualSnapshotData(this.f87a, z);
            if (visualSnapshotData != null) {
                try {
                    try {
                        if (visualSnapshotData.data != null && visualSnapshotData.data.length != 0) {
                            outputStream.write(visualSnapshotData.data);
                        }
                    } catch (Throwable th) {
                        try {
                            ExceptionUtil.exceptionPrint(th);
                            outputStream.close();
                            return;
                        } catch (Throwable th2) {
                            try {
                                outputStream.close();
                            } catch (Throwable th3) {
                                ExceptionUtil.exceptionPrint(th3);
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable th4) {
                    ExceptionUtil.exceptionPrint(th4);
                    return;
                }
            }
            outputStream.close();
        } catch (Throwable th5) {
            ExceptionUtil.exceptionThrow(th5);
        }
    }
}

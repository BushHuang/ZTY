package com.analysys.ipc;

import java.util.Map;

public interface IIpcProxy {
    void clearVisualSnapshot();

    BytesParcelable getVisualSnapshotData(String str, boolean z);

    void onVisualEditEvent(String str);

    void reloadVisualEventLocal();

    void reportVisualEvent(String str, String str2, Map map);

    void sendVisualEditEvent2Client(String str);

    void setVisualEditing(boolean z);
}

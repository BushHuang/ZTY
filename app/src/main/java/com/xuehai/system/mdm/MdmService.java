package com.xuehai.system.mdm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\"\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/xuehai/system/mdm/MdmService;", "Landroid/app/Service;", "()V", "keepLiveService", "Lcom/xuehai/system/mdm/KeepLiveService;", "sdkBinder", "Lcom/xuehai/system/mdm/MdmBinder;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "", "onRebind", "onStartCommand", "", "flags", "startId", "onUnbind", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmService extends Service {
    private final MdmBinder sdkBinder = new MdmBinder();
    private final KeepLiveService keepLiveService = new KeepLiveService();

    @Override
    public IBinder onBind(Intent intent) {
        MdmLog.i("[MDM]", "MdmService onBind");
        return this.sdkBinder;
    }

    @Override
    public void onDestroy() {
        MdmLog.i("[MDM]", "MdmService onDestroy");
        this.keepLiveService.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        MdmLog.i("[MDM]", "MdmService onRebind");
        super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MdmLog.i("[MDM]", "MdmService onStartCommand");
        this.keepLiveService.onStartCommand(intent, flags, startId);
        return 1;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        MdmLog.i("[MDM]", "MdmService onUnbind");
        return super.onUnbind(intent);
    }
}

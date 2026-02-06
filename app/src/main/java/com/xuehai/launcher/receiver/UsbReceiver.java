package com.xuehai.launcher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.log.LogHandler;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.zaze.utils.FileUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/receiver/UsbReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UsbReceiver extends BroadcastReceiver {

    public static final Companion INSTANCE = new Companion(null);
    private static final File logFile = new File(FilePath.getLogDir() + "/usb_device.log");

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/receiver/UsbReceiver$Companion;", "", "()V", "logFile", "Ljava/io/File;", "getLogFile", "()Ljava/io/File;", "createIntentFilter", "Landroid/content/IntentFilter;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IntentFilter createIntentFilter() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            return intentFilter;
        }

        public final File getLogFile() {
            return UsbReceiver.logFile;
        }
    }

    private static final void m170onReceive$lambda1$lambda0(String str, Map.Entry entry) {
        Intrinsics.checkNotNullParameter(str, "$log");
        Intrinsics.checkNotNullParameter(entry, "$it");
        FileUtil.writeToFile(logFile, str, 10485760L);
        MyLog.saveAppErrorLog("usb device attached: " + entry.getValue());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action;
        UsbManager usbManager;
        HashMap<String, UsbDevice> deviceList;
        Intrinsics.checkNotNullParameter(context, "context");
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        int iHashCode = action.hashCode();
        if (iHashCode != -2114103349) {
            if (iHashCode == -1608292967 && action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                MyLog.i("[MDM]", "UsbReceiver USB_DEVICE_DETACHED");
                return;
            }
            return;
        }
        if (!action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED") || (usbManager = (UsbManager) context.getSystemService("usb")) == null || (deviceList = usbManager.getDeviceList()) == null) {
            return;
        }
        for (final Map.Entry<String, UsbDevice> entry : deviceList.entrySet()) {
            LogHandler logHandler = LogHandler.INSTANCE;
            String string = entry.getValue().toString();
            Intrinsics.checkNotNullExpressionValue(string, "it.value.toString()");
            final String log = logHandler.formatLog("usb device attached", string);
            MyLog.i("[MDM]", "UsbReceiver : " + log);
            ThreadPlugins.runInLogThread(new Runnable() {
                @Override
                public final void run() {
                    UsbReceiver.m170onReceive$lambda1$lambda0(log, entry);
                }
            });
        }
    }
}

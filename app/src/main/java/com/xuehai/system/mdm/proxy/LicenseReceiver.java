package com.xuehai.system.mdm.proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xuehai.system.common.log.MdmLog;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \t2\u00020\u0001:\u0002\t\nB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/mdm/proxy/LicenseReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "LicenseListener", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LicenseReceiver extends BroadcastReceiver {

    public static final Companion INSTANCE = new Companion(null);
    private static final HashSet<LicenseListener> licenseListeners = new HashSet<>();

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/xuehai/system/mdm/proxy/LicenseReceiver$Companion;", "", "()V", "licenseListeners", "Ljava/util/HashSet;", "Lcom/xuehai/system/mdm/proxy/LicenseReceiver$LicenseListener;", "Lkotlin/collections/HashSet;", "addListener", "", "listener", "createIntentFilter", "Landroid/content/IntentFilter;", "removeListener", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void addListener(LicenseListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            LicenseReceiver.licenseListeners.add(listener);
        }

        public final IntentFilter createIntentFilter() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xuehai.mdm.knox.free");
            intentFilter.addAction("com.xuehai.mdm.knox.pay");
            intentFilter.addAction("com.xuehai.mdm.Knox.v3");
            intentFilter.addAction("com.xuehai.mdm.huawei.free");
            return intentFilter;
        }

        public final void removeListener(LicenseListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            LicenseReceiver.licenseListeners.remove(listener);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/xuehai/system/mdm/proxy/LicenseReceiver$LicenseListener;", "", "onLicenseStateChanged", "", "desc", "", "success", "", "mdm_proxy_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface LicenseListener {
        void onLicenseStateChanged(String desc, boolean success);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String stringExtra = null;
            boolean booleanExtra = false;
            if (intent.hasExtra("license_state")) {
                booleanExtra = intent.getBooleanExtra("license_state", false);
                stringExtra = intent.getStringExtra("license_state_desc");
            } else if (intent.hasExtra("license_state") || intent.hasExtra("com.xuehai.mdm.huawei.free")) {
                booleanExtra = intent.getBooleanExtra("license_state", false);
                stringExtra = intent.getStringExtra("license_state");
            }
            String action = intent.getAction();
            MdmLog.i("MdmLog", "LicenseReceiver " + action + ": " + booleanExtra);
            Iterator<T> it = licenseListeners.iterator();
            while (it.hasNext()) {
                ((LicenseListener) it.next()).onLicenseStateChanged(stringExtra == null ? "" : stringExtra, booleanExtra);
            }
            if (!Intrinsics.areEqual(action, "com.xuehai.mdm.knox.pay") || context == null) {
                return;
            }
            Intent intent2 = new Intent(intent);
            intent2.setAction("com.xuehai.mdm.Knox");
            context.sendBroadcast(intent2);
        }
    }
}

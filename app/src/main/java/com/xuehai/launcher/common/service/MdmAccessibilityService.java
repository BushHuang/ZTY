package com.xuehai.launcher.common.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import com.xuehai.system.common.log.MdmLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0014¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/common/service/MdmAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "onAccessibilityEvent", "", "event", "Landroid/view/accessibility/AccessibilityEvent;", "onInterrupt", "onServiceConnected", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmAccessibilityService extends AccessibilityService {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final String TAG = "MdmAccessibilityService";

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/xuehai/launcher/common/service/MdmAccessibilityService$Companion;", "", "()V", "TAG", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) {
            return;
        }
        CharSequence packageName = event.getPackageName();
        String string = packageName != null ? packageName.toString() : null;
        MdmLog.i("MdmAccessibilityService", "onAccessibilityEvent，pkgName -> " + string + " , eventType -> " + event.getEventType());
    }

    @Override
    public void onInterrupt() {
        MdmLog.i("MdmAccessibilityService", "onInterrupt");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        MdmLog.i("MdmAccessibilityService", "onServiceConnected");
    }
}

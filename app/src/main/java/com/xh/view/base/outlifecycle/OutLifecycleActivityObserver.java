package com.xh.view.base.outlifecycle;

import android.content.Intent;
import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lcom/xh/view/base/outlifecycle/OutLifecycleActivityObserver;", "Lcom/xh/view/base/outlifecycle/OutLifecycleObserver;", "_dispatchTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface OutLifecycleActivityObserver extends OutLifecycleObserver {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean _dispatchTouchEvent(OutLifecycleActivityObserver outLifecycleActivityObserver, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(outLifecycleActivityObserver, "this");
            Intrinsics.checkNotNullParameter(motionEvent, "ev");
            return false;
        }

        public static void onActivityResult(OutLifecycleActivityObserver outLifecycleActivityObserver, int i, int i2, Intent intent) {
            Intrinsics.checkNotNullParameter(outLifecycleActivityObserver, "this");
        }
    }

    boolean _dispatchTouchEvent(MotionEvent ev);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}

package com.analysys.visual.bind;

import android.view.View;
import com.analysys.allgro.plugin.ASMHookAdapter;
import com.analysys.utils.ExceptionUtil;
import com.analysys.visual.b;

public class VisualASMListener extends ASMHookAdapter {
    @Override
    public void trackSendAccessibilityEvent(final View view, final int i, boolean z) {
        b.a().a(new Runnable() {
            @Override
            public void run() {
                try {
                    b.a().a(view, i);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }
}

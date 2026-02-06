package com.xh.view.base.ext;

import android.view.Window;
import android.view.WindowManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001aK\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u0004¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"applyGravityStyle", "", "Landroid/view/Window;", "gravity", "", "resId", "width", "height", "x", "y", "(Landroid/view/Window;ILjava/lang/Integer;IIII)V", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class WindowExtentionKt {
    public static final void applyGravityStyle(Window window, int i, Integer num, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = i;
        attributes.width = i2;
        attributes.height = i3;
        attributes.x = i4;
        attributes.y = i5;
        window.setAttributes(attributes);
        if (num == null) {
            return;
        }
        window.setWindowAnimations(num.intValue());
    }
}

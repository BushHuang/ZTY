package com.xh.xhcore.common.util;

import android.os.Build;
import android.os.Looper;
import android.os.MessageQueue;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/util/LooperUtil;", "", "()V", "addIdleHandlerToMain", "", "idleHandler", "Landroid/os/MessageQueue$IdleHandler;", "getMainMessageQueue", "Landroid/os/MessageQueue;", "removeIdleHandlerFromMain", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LooperUtil {
    public static final LooperUtil INSTANCE = new LooperUtil();

    private LooperUtil() {
    }

    @JvmStatic
    public static final void addIdleHandlerToMain(MessageQueue.IdleHandler idleHandler) {
        Intrinsics.checkNotNullParameter(idleHandler, "idleHandler");
        MessageQueue mainMessageQueue = INSTANCE.getMainMessageQueue();
        if (mainMessageQueue == null) {
            return;
        }
        mainMessageQueue.addIdleHandler(idleHandler);
    }

    private final MessageQueue getMainMessageQueue() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Looper.getMainLooper().getQueue();
        }
        try {
            Object field = ReflectUtil.getField(Looper.getMainLooper(), "mQueue");
            if (field != null) {
                return (MessageQueue) field;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.os.MessageQueue");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final void removeIdleHandlerFromMain(MessageQueue.IdleHandler idleHandler) {
        Intrinsics.checkNotNullParameter(idleHandler, "idleHandler");
        MessageQueue mainMessageQueue = INSTANCE.getMainMessageQueue();
        if (mainMessageQueue == null) {
            return;
        }
        mainMessageQueue.removeIdleHandler(idleHandler);
    }
}

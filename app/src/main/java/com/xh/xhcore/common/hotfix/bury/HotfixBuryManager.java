package com.xh.xhcore.common.hotfix.bury;

import com.xh.xhcore.common.hotfix.bury.HotfixBuryStrategy;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryManager;", "", "()V", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HotfixBuryManager {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0004H\u0007J\b\u0010\u0006\u001a\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryManager$Companion;", "", "()V", "onApplySuccess", "", "onDownloadSuccess", "onPatchSuccess", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void onApplySuccess() {
            new HotfixBuryStrategy.ApplyHotfixBury().onSuccess();
        }

        @JvmStatic
        public final void onDownloadSuccess() {
            new HotfixBuryStrategy.DownloadHotfixBury().onSuccess();
        }

        @JvmStatic
        public final void onPatchSuccess() {
            new HotfixBuryStrategy.PatchHotfixBury().onSuccess();
        }
    }

    @JvmStatic
    public static final void onApplySuccess() {
        INSTANCE.onApplySuccess();
    }

    @JvmStatic
    public static final void onDownloadSuccess() {
        INSTANCE.onDownloadSuccess();
    }

    @JvmStatic
    public static final void onPatchSuccess() {
        INSTANCE.onPatchSuccess();
    }
}

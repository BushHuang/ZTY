package com.xh.xhcore.common.hotfix.bury;

import com.xh.xhcore.common.hotfix.tinker.util.TinkerManager;
import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.DataCollectionUtil;
import com.xh.xhcore.common.util.JsonUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryUtil;", "", "()V", "NAME", "", "TYPE", "", "bury", "", "event", "HotfixBuryModel", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HotfixBuryUtil {
    public static final HotfixBuryUtil INSTANCE = new HotfixBuryUtil();
    private static final int TYPE = 123;
    private static final String NAME = "10272";

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryUtil$HotfixBuryModel;", "", "event", "", "patchVersion", "(Ljava/lang/String;Ljava/lang/String;)V", "getEvent", "()Ljava/lang/String;", "getPatchVersion", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class HotfixBuryModel {
        private final String event;
        private final String patchVersion;

        public HotfixBuryModel(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "event");
            Intrinsics.checkNotNullParameter(str2, "patchVersion");
            this.event = str;
            this.patchVersion = str2;
        }

        public static HotfixBuryModel copy$default(HotfixBuryModel hotfixBuryModel, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = hotfixBuryModel.event;
            }
            if ((i & 2) != 0) {
                str2 = hotfixBuryModel.patchVersion;
            }
            return hotfixBuryModel.copy(str, str2);
        }

        public final String getEvent() {
            return this.event;
        }

        public final String getPatchVersion() {
            return this.patchVersion;
        }

        public final HotfixBuryModel copy(String event, String patchVersion) {
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(patchVersion, "patchVersion");
            return new HotfixBuryModel(event, patchVersion);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HotfixBuryModel)) {
                return false;
            }
            HotfixBuryModel hotfixBuryModel = (HotfixBuryModel) other;
            return Intrinsics.areEqual(this.event, hotfixBuryModel.event) && Intrinsics.areEqual(this.patchVersion, hotfixBuryModel.patchVersion);
        }

        public final String getEvent() {
            return this.event;
        }

        public final String getPatchVersion() {
            return this.patchVersion;
        }

        public int hashCode() {
            return (this.event.hashCode() * 31) + this.patchVersion.hashCode();
        }

        public String toString() {
            return "HotfixBuryModel(event=" + this.event + ", patchVersion=" + this.patchVersion + ')';
        }
    }

    private HotfixBuryUtil() {
    }

    public final void bury(String event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int i = TYPE;
        String str = NAME;
        String strLoadPatchVersion = TinkerManager.loadPatchVersion();
        Intrinsics.checkNotNullExpressionValue(strLoadPatchVersion, "loadPatchVersion()");
        DataCollectionUtil.setBury(new BuryEvent(i, str, JsonUtil.toJsonString(new HotfixBuryModel(event, strLoadPatchVersion))));
    }
}

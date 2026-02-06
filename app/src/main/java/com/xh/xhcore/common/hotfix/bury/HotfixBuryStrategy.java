package com.xh.xhcore.common.hotfix.bury;

import android.content.SharedPreferences;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.hotfix.tinker.util.TinkerManager;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.DataCollectionUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u0000 \u00052\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy;", "", "()V", "ApplyHotfixBury", "BaseHotfixBury", "Companion", "DownloadHotfixBury", "IHotfixBury", "PatchHotfixBury", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HotfixBuryStrategy {
    public static final String HOTFIX_BURY_EVENT_APPLY_SUCCESS = "applySuccess";
    public static final String HOTFIX_BURY_EVENT_DOWNLOAD_SUCCESS = "downloadSuccess";
    public static final String HOTFIX_BURY_EVENT_PATCH_SUCCESS = "patchSuccess";
    private static final String HOTFIX_BURY_SP_FILE_NAME = "hotfix_bury";
    private static final String TAG = "HotfixBuryStrategy";

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014¨\u0006\u0005"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$ApplyHotfixBury;", "Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$BaseHotfixBury;", "()V", "getEvent", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ApplyHotfixBury extends BaseHotfixBury {
        @Override
        protected String getEvent() {
            return "applySuccess";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0006H$J\b\u0010\t\u001a\u00020\nH\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\r\u001a\u00020\u0004H\u0016¨\u0006\u000e"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$BaseHotfixBury;", "Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$IHotfixBury;", "()V", "bury", "", "generatePatchBuryKey", "", "specifySuccess", "getEvent", "getPatchBuryConfig", "Landroid/content/SharedPreferences;", "hasSuchSuccess", "", "onSuccess", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static abstract class BaseHotfixBury implements IHotfixBury {
        private final String generatePatchBuryKey(String specifySuccess) {
            return XHAppUtil.getVersionName() + '_' + ((Object) TinkerManager.loadPatchVersion()) + '_' + specifySuccess;
        }

        private final boolean hasSuchSuccess(String specifySuccess) {
            boolean z = getPatchBuryConfig().getBoolean(generatePatchBuryKey(specifySuccess), false);
            LogUtils.Companion.d$default(LogUtils.INSTANCE, "HotfixBuryStrategy", "specifySuccess = " + specifySuccess + ", hasSuchSuccess = " + z, null, 4, null);
            return z;
        }

        protected void bury() {
            HotfixBuryUtil.INSTANCE.bury(getEvent());
        }

        protected abstract String getEvent();

        protected final SharedPreferences getPatchBuryConfig() {
            SharedPreferences sharedPreferences = XhBaseApplication.getXhBaseApplication().getSharedPreferences("hotfix_bury", 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getXhBaseApplication().g…ME, Context.MODE_PRIVATE)");
            return sharedPreferences;
        }

        @Override
        public void onSuccess() {
            LogUtils.Companion.d$default(LogUtils.INSTANCE, "HotfixBuryStrategy", Intrinsics.stringPlus("[onSuccess] this = ", this), null, 4, null);
            if (hasSuchSuccess(getEvent())) {
                return;
            }
            bury();
            getPatchBuryConfig().edit().putBoolean(generatePatchBuryKey(getEvent()), true).apply();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$DownloadHotfixBury;", "Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$BaseHotfixBury;", "()V", "cleanOlderVersionRecordInSP", "", "getEvent", "", "onSuccess", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DownloadHotfixBury extends BaseHotfixBury {
        private final void cleanOlderVersionRecordInSP() {
            Map<String, ?> all = getPatchBuryConfig().getAll();
            Intrinsics.checkNotNullExpressionValue(all, "getPatchBuryConfig().all");
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                String key = entry.getKey();
                String versionName = XHAppUtil.getVersionName();
                Intrinsics.checkNotNullExpressionValue(versionName, "getVersionName()");
                if (!StringsKt.startsWith$default(key, versionName, false, 2, (Object) null)) {
                    getPatchBuryConfig().edit().remove(entry.getKey()).apply();
                }
            }
        }

        @Override
        protected String getEvent() {
            return "downloadSuccess";
        }

        @Override
        public void onSuccess() {
            super.onSuccess();
            cleanOlderVersionRecordInSP();
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$IHotfixBury;", "", "onSuccess", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface IHotfixBury {
        void onSuccess();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\b\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$PatchHotfixBury;", "Lcom/xh/xhcore/common/hotfix/bury/HotfixBuryStrategy$BaseHotfixBury;", "()V", "bury", "", "getEvent", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PatchHotfixBury extends BaseHotfixBury {
        @Override
        protected void bury() {
            super.bury();
            DataCollectionUtil.dumpBuryDirectly();
        }

        @Override
        protected String getEvent() {
            return "patchSuccess";
        }
    }
}

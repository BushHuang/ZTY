package com.xh.xhcore.common.hotfix;

import android.util.Log;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.hotfix.tinker.util.TinkerManager;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.okhttp.callback.DownloadCallback;
import com.xh.xhcore.common.http.strategy.okhttp.download.DownloadOkHttp;
import com.xh.xhcore.common.util.XHAppUtil;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007J\b\u0010\u000b\u001a\u00020\u0004H\u0002J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/hotfix/TinkerAliyunPatchUtil;", "", "()V", "PATCH_FILE_NAME", "", "TAG", "downloadPatchWithAliyunObjectKey", "", "patchVersionToBeDownload", "aliyunObjectKey", "generateAliyunObjectKey", "generateAliyunPrefix", "logOssObjectSummary", "ossObjectSummary", "Lcom/alibaba/sdk/android/oss/model/OSSObjectSummary;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TinkerAliyunPatchUtil {
    public static final TinkerAliyunPatchUtil INSTANCE = new TinkerAliyunPatchUtil();
    private static final String PATCH_FILE_NAME = "patch_signed.apk";
    private static final String TAG = "TinkerAliyunPatchUtil";

    private TinkerAliyunPatchUtil() {
    }

    @JvmStatic
    public static final void downloadPatchWithAliyunObjectKey(String patchVersionToBeDownload, String aliyunObjectKey) {
        Intrinsics.checkNotNullParameter(aliyunObjectKey, "aliyunObjectKey");
        String str = "http://xhzty.oss-cn-hangzhou.aliyuncs.com/" + aliyunObjectKey;
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("aliyun downloadUrl = ", str));
        String str2 = TinkerPatchApplicationLike.getTinkerPatchApplicationLike().getApplication().getFilesDir().getAbsolutePath() + "/download/patch/patch_signed.apk";
        ((DownloadOkHttp) ((DownloadOkHttp) new DownloadOkHttp().setUrl(str)).setLocalPath(str2).setCallback(new DownloadCallback(str2, patchVersionToBeDownload) {
            final String $localPath;
            final String $patchVersionToBeDownload;

            {
                super(str2);
                this.$localPath = str2;
                this.$patchVersionToBeDownload = patchVersionToBeDownload;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                super.onResponse(call, response);
                File file = new File(this.$localPath);
                if (!file.exists() || file.length() == 0) {
                    LogUtils.Companion.d$default(LogUtils.INSTANCE, "TinkerAliyunPatchUtil", "download complete but target file does not exist or file is empty", null, 4, null);
                    return;
                }
                LogUtils.Companion.d$default(LogUtils.INSTANCE, "TinkerAliyunPatchUtil", "download patch success", null, 4, null);
                TinkerManager.savePatchVersion(this.$patchVersionToBeDownload);
                TinkerInstaller.onReceiveUpgradePatch(TinkerManager.getTinkerApplicationLike().getApplication(), this.$localPath);
            }
        })).request();
    }

    @JvmStatic
    public static final String generateAliyunObjectKey(String patchVersionToBeDownload) {
        Intrinsics.checkNotNullParameter(patchVersionToBeDownload, "patchVersionToBeDownload");
        return INSTANCE.generateAliyunPrefix() + ((Object) File.separator) + patchVersionToBeDownload + ((Object) File.separator) + "patch_signed.apk";
    }

    private final String generateAliyunPrefix() {
        return "patch" + ((Object) File.separator) + (XHAppConfigProxy.getInstance().getAppId() + '/' + XHAppUtil.getTinkerId() + '/' + (XHAppConfigProxy.getInstance().isAppBuildTypeDebug() ? "debug" : "release"));
    }

    public final void logOssObjectSummary(OSSObjectSummary ossObjectSummary) {
        Intrinsics.checkNotNullParameter(ossObjectSummary, "ossObjectSummary");
        Log.d("TinkerAliyunPatchUtil", "object: " + ((Object) ossObjectSummary.getKey()) + ' ' + ossObjectSummary.getETag() + ' ' + ossObjectSummary.getLastModified());
    }
}

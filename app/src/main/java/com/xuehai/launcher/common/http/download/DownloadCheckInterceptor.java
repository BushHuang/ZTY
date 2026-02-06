package com.xuehai.launcher.common.http.download;

import android.text.TextUtils;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xuehai.launcher.common.R;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.http.DownloadCallback;
import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.http.download.DownloadTaskManager;
import com.xuehai.launcher.common.interceptor.Interceptor;
import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.FileUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002J\u001c\u0010\n\u001a\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/xuehai/launcher/common/http/download/DownloadCheckInterceptor;", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "Lcom/xuehai/launcher/common/http/LRequest;", "", "()V", "checkDownloadFile", "", "savePath", "", "md5", "intercept", "chain", "Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadCheckInterceptor implements Interceptor<LRequest, Unit> {
    public static final int EXECUTE_ERROR = -1;

    private final boolean checkDownloadFile(String savePath, String md5) {
        if (TextUtils.isEmpty(md5)) {
            MyLog.i("Download[MDM]", "没有给定md5, 不校验下载文件 : " + savePath);
            return true;
        }
        if (TextUtils.isEmpty(savePath)) {
            MyLog.w("Download[MDM]", "保存路径记录为空，校验失败");
            return false;
        }
        File file = new File(savePath);
        if (file.exists()) {
            String fileMD5ToString = XHFileUtil.getFileMD5ToString(file);
            if (StringsKt.equals(md5, fileMD5ToString, true)) {
                MyLog.i("Download[MDM]", "下载文件md5校验成功(" + savePath + ')');
                return true;
            }
            MyLog.w("Download[MDM]", "下载文件md5校验失败(" + savePath + ") : " + fileMD5ToString + '/' + md5);
        } else {
            MyLog.w("Download[MDM]", "找不到下载文件(" + savePath + ")，更新失败");
        }
        return false;
    }

    @Override
    public Unit intercept(Interceptor.Chain<LRequest, Unit> chain) {
        intercept2(chain);
        return Unit.INSTANCE;
    }

    public void intercept2(Interceptor.Chain<LRequest, Unit> chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        LRequest lRequestInput = chain.input();
        final String savePath = DownloadExtKt.getSavePath(lRequestInput);
        if (savePath == null) {
            savePath = "";
        }
        final String url = lRequestInput.getUrl();
        MyLog.i("Download[MDM]", "------------ start pre check 下载请求: " + url + " >> " + savePath);
        DownloadCallback downloadCallback = DownloadExtKt.getDownloadCallback(lRequestInput);
        if (TextUtils.isEmpty(url)) {
            MyLog.w("Download[MDM]", "下载的URL不可为空");
            if (downloadCallback != null) {
                downloadCallback.onFailure(-1, "下载的URL不可为空");
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(savePath)) {
            MyLog.w("Download[MDM]", "下载文件的保存路径不可为空");
            if (downloadCallback != null) {
                downloadCallback.onFailure(-1, "下载文件的保存路径不可为空");
                return;
            }
            return;
        }
        MyLog.i("Download[MDM]", "准备下载 url=" + url + "; savePath=" + savePath);
        final String md5 = DownloadExtKt.getMd5(lRequestInput);
        LRequest.Builder builderNewBuilder = lRequestInput.newBuilder();
        DownloadTaskManager.DownloadCallbackBus downloadCallbackBus = DownloadTaskManager.INSTANCE.getDownloadCallbackBus(url);
        if (downloadCallbackBus == null) {
            downloadCallbackBus = new DownloadTaskManager.DownloadCallbackBus() {
                @Override
                public void onFailure(int errorCode, String errorMessage) {
                    DownloadTaskManager.INSTANCE.removeDownloadCallbackBus(url);
                    MyLog.w("Download[MDM]", "下载失败(" + errorMessage + ") url=" + url + ";\n savePath=" + savePath + ';');
                    super.onFailure(errorCode, errorMessage);
                }

                @Override
                public void onSuccess(String message, String savePath2) {
                    Intrinsics.checkNotNullParameter(savePath2, "savePath");
                    DownloadTaskManager.INSTANCE.removeDownloadCallbackBus(url);
                    if (this.checkDownloadFile(savePath2, md5)) {
                        MyLog.i("Download[MDM]", "下载完成 url=" + url + ";\n savePath=" + savePath2 + ';');
                        super.onSuccess(message, savePath2);
                        return;
                    }
                    MyLog.w("Download[MDM]", "md5校验失败(" + message + ") url=" + url + ";\n savePath=" + savePath2 + ';');
                    super.onFailure(400, BaseApplication.INSTANCE.getInstance().getString(R.string.tip_md5_error));
                }
            };
        }
        downloadCallbackBus.add(downloadCallback);
        if (DownloadTaskManager.INSTANCE.isDownloading(url)) {
            MyLog.w("Download[MDM]", "已经在下载中: " + url + "， 加入到callback bus 中等待回掉");
            return;
        }
        MyLog.i("Download[MDM]", "检测是否已下载 url=" + url + "; savePath=" + savePath + "; md5=" + md5);
        if (FileUtil.exists(savePath)) {
            MyLog.i("Download[MDM]", "下载文件已存在,检测文件md5");
            if (checkDownloadFile(savePath, md5)) {
                if (downloadCallback != null) {
                    downloadCallback.onSuccess("下载文件已存在,不必重新下载: " + savePath, savePath);
                    return;
                }
                return;
            }
            FileUtil.deleteFile(savePath);
            MyLog.w("Download[MDM]", "已下载文件md5校验失败, 下载前先删除!!");
        }
        MyLog.i("Download[MDM]", "------------ end pre check 下载请求: " + url + " >> " + savePath);
        DownloadTaskManager.INSTANCE.addDownloadCallbackBus(url, downloadCallbackBus);
        DownloadExtKt.setDownloadCallback(builderNewBuilder, downloadCallbackBus);
        chain.process(builderNewBuilder.build());
    }
}

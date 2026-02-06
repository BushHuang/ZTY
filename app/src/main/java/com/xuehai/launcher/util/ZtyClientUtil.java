package com.xuehai.launcher.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.config.DynamicConfig;
import com.xuehai.launcher.common.config.KeepConfig;
import com.xuehai.launcher.common.config.StaticConfig;
import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.constants.error.ErrorCode;
import com.xuehai.launcher.common.http.DownloadCallback;
import com.xuehai.launcher.common.http.LRequest;
import com.xuehai.launcher.common.http.LResponse;
import com.xuehai.launcher.common.http.NetStore;
import com.xuehai.launcher.common.http.download.DownloadExtKt;
import com.xuehai.launcher.common.http.download.DownloadManager;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.IntentUtil;
import com.xuehai.launcher.common.util.PermissionManager;
import com.xuehai.launcher.common.util.RequestCodeBuilder;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.mdm.KeepLiveService;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.AppUtil;
import com.zaze.utils.FileUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\tH\u0007J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010\r\u001a\u00020\u000eJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u0006\u0010\u0013\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0004J\u0006\u0010\u0015\u001a\u00020\u0004J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u001b\u001a\u00020\u0019J\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00190\u001dJ\u0006\u0010\u001f\u001a\u00020\u0019J\u0014\u0010 \u001a\u00020\u00192\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\"J\u000e\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u001eJ\u000e\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/xuehai/launcher/util/ZtyClientUtil;", "", "()V", "MDM_STU", "", "MDM_TCH", "ZTY_STU", "ZTY_TCH", "backupZtyHistoryData", "", "clearData", "downloadOffline", "Lio/reactivex/Observable;", "callback", "Lcom/xuehai/launcher/common/http/DownloadCallback;", "downloadOnline", "getHistoryData", "", "Ljava/io/File;", "getMatchedZtyClient", "getSavePath", "getUnMatchedZtyClient", "getZtyClientIntent", "Landroid/content/Intent;", "isZtyClient", "", "packageName", "isZtyClientInstalled", "isZtyUpgraded", "Lkotlin/Pair;", "", "needUseInnerApk", "openClient", "doAction", "Lkotlin/Function0;", "saveZtyUpgradeVersion", "currentVersionCode", "startZtyClient", "context", "Landroid/content/Context;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ZtyClientUtil {
    public static final ZtyClientUtil INSTANCE = new ZtyClientUtil();
    private static final String MDM_STU = "com.xuehai.launcher";
    private static final String MDM_TCH = "com.xuehai.response_launcher_teacher";
    public static final String ZTY_STU = "com.xh.zhitongyunstu";
    private static final String ZTY_TCH = "com.xh.zhitongyuntch";

    private ZtyClientUtil() {
    }

    @JvmStatic
    public static final void clearData() {
        MyLog.i("[MDM]", "清理mdm数据(pid = " + Process.myPid() + ",uid = " + Process.myUid() + ')');
        String bakPath = FilePath.getBakPath();
        Intrinsics.checkNotNullExpressionValue(bakPath, "getBakPath()");
        FileUtil.deleteFile(bakPath);
        DynamicConfig.clearAll();
        StaticConfig.INSTANCE.clearAll();
        FileUtil.deleteFile("/data/data/" + BaseApplication.INSTANCE.getInstance().getPackageName());
        Object systemService = BaseApplication.INSTANCE.getInstance().getSystemService("activity");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager activityManager = (ActivityManager) systemService;
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                activityManager.clearApplicationUserData();
            } catch (Exception e) {
                e.printStackTrace();
                ApplicationUtil.INSTANCE.killProcess();
            }
        }
    }

    private static final Boolean m177downloadOffline$lambda2(String str) {
        Intrinsics.checkNotNullParameter(str, "$savePath");
        MdmLog.i("Application[MDM]", "读取内置智通云...");
        ApplicationUtil applicationUtil = ApplicationUtil.INSTANCE;
        Context deviceProtectedStorageContext = BaseApplication.INSTANCE.getInstance().getDeviceProtectedStorageContext();
        StringBuilder sb = new StringBuilder();
        sb.append("apk/com.xh.zhitongyun");
        sb.append(ClientConfig.INSTANCE.isStudentClient() ? "stu" : "tch");
        sb.append(".apk");
        return Boolean.valueOf(applicationUtil.readAssetsApkToFile(deviceProtectedStorageContext, sb.toString(), str));
    }

    private static final ObservableSource m178downloadOffline$lambda4(final DownloadCallback downloadCallback, final String str, Boolean bool) {
        Intrinsics.checkNotNullParameter(downloadCallback, "$callback");
        Intrinsics.checkNotNullParameter(str, "$savePath");
        Intrinsics.checkNotNullParameter(bool, "success");
        return bool.booleanValue() ? Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return ZtyClientUtil.m179downloadOffline$lambda4$lambda3(downloadCallback, str);
            }
        }) : INSTANCE.downloadOnline(downloadCallback);
    }

    private static final String m179downloadOffline$lambda4$lambda3(DownloadCallback downloadCallback, String str) {
        Intrinsics.checkNotNullParameter(downloadCallback, "$callback");
        Intrinsics.checkNotNullParameter(str, "$savePath");
        downloadCallback.onSuccess("拷贝内置apk完成", str);
        return str;
    }

    private static final Unit m180downloadOnline$lambda6(final DownloadCallback downloadCallback, LResponse lResponse) {
        Intrinsics.checkNotNullParameter(downloadCallback, "$callback");
        Intrinsics.checkNotNullParameter(lResponse, "it");
        if (lResponse.isFailure()) {
            downloadCallback.onFailure(lResponse.getCode(), lResponse.getResponseBody());
            return Unit.INSTANCE;
        }
        try {
            JSONObject jSONObject = new JSONObject(lResponse.getResponseBody());
            String strOptString = jSONObject.optString("url");
            String strOptString2 = jSONObject.optString("checksum");
            DownloadManager downloadManager = DownloadManager.INSTANCE;
            LRequest.Builder builder = new LRequest.Builder();
            Intrinsics.checkNotNullExpressionValue(strOptString, "url");
            LRequest.Builder builderUrl$default = LRequest.Builder.url$default(builder, strOptString, null, 2, null);
            DownloadExtKt.setSavePath(builderUrl$default, INSTANCE.getSavePath());
            DownloadExtKt.setMd5(builderUrl$default, strOptString2);
            DownloadExtKt.setDownloadCallback(builderUrl$default, new DownloadCallback() {
                @Override
                public void onFailure(int errorCode, String errorMessage) {
                    MdmLog.w("Application[MDM]", "智通云下载失败 " + errorMessage);
                    FileUtil.deleteFile(ZtyClientUtil.INSTANCE.getSavePath());
                    downloadCallback.onFailure(errorCode, errorMessage);
                }

                @Override
                public void onProgress(double fileTotalSize, double fileDownSize, double speed) {
                    downloadCallback.onProgress(fileTotalSize, fileDownSize, speed);
                }

                @Override
                public void onStart(double total) {
                    downloadCallback.onStart(total);
                }

                @Override
                public void onSuccess(String message, String savePath) {
                    Intrinsics.checkNotNullParameter(savePath, "savePath");
                    downloadCallback.onSuccess(message, savePath);
                    MdmLog.i("Application[MDM]", "智通云下载成功!!");
                }
            });
            DownloadManager.download$default(downloadManager, builderUrl$default.build(), null, 2, null);
        } catch (Exception unused) {
            downloadCallback.onFailure(ErrorCode.ERROR_DEFAULT.getCode(), "获取智通云版本信息发生异常!");
        }
        return Unit.INSTANCE;
    }

    private static final String m181downloadOnline$lambda7(Unit unit) {
        Intrinsics.checkNotNullParameter(unit, "it");
        return INSTANCE.getSavePath();
    }

    private final Intent getZtyClientIntent() {
        String matchedZtyClient = getMatchedZtyClient();
        Intent intent = new Intent("com.xh.zhitongyun.launcher");
        intent.setPackage(matchedZtyClient);
        List listQuery$default = IntentUtil.query$default(IntentUtil.INSTANCE, intent, 0, 2, null);
        List list = listQuery$default;
        if (list == null || list.isEmpty()) {
            MyLog.i("[MDM]", "应用(" + matchedZtyClient + ")未找到可启动intent，尝试启用应用");
            PolicyManager.getApplicationPolicyProxy().setEnableApplication(matchedZtyClient);
            listQuery$default = IntentUtil.query$default(IntentUtil.INSTANCE, intent, 0, 2, null);
        }
        List list2 = listQuery$default;
        if (!(list2 == null || list2.isEmpty())) {
            intent.addFlags(270532608);
            return intent;
        }
        MyLog.i("[MDM]", "应用(" + matchedZtyClient + ")未找到可启动intent,return null");
        return null;
    }

    public final void backupZtyHistoryData() throws Throwable {
        MdmLog.i("Application[MDM]", "检查智通云历史数据");
        for (File file : getHistoryData()) {
            file.renameTo(new File(file.getAbsoluteFile() + ".bak"));
            MyLog.i("[MDM]", "备份智通云历史数据 " + file.getAbsolutePath());
            DynamicConfig.updateUserInnerApkState(true);
        }
    }

    public final Observable<String> downloadOffline(final DownloadCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        final String savePath = getSavePath();
        Observable<String> observableConcatMap = Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return ZtyClientUtil.m177downloadOffline$lambda2(savePath);
            }
        }).subscribeOn(ThreadPlugins.ioScheduler()).concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return ZtyClientUtil.m178downloadOffline$lambda4(callback, savePath, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableConcatMap, "fromCallable {\n         …          }\n            }");
        return observableConcatMap;
    }

    public final Observable<String> downloadOnline(final DownloadCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Observable<String> map = NetStore.INSTANCE.requestPlatformApk(DynamicConfig.getDownloadConfigUrl(), ClientConfig.INSTANCE.getAppId(), ClientConfig.INSTANCE.getAppVersionCode()).observeOn(ThreadPlugins.ioScheduler()).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return ZtyClientUtil.m180downloadOnline$lambda6(callback, (LResponse) obj);
            }
        }).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return ZtyClientUtil.m181downloadOnline$lambda7((Unit) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "NetStore.requestPlatform…tSavePath()\n            }");
        return map;
    }

    public final List<File> getHistoryData() {
        String[] strArr = {"files/launcher.ini", "files/launcher_pre.ini", "files/launcher_test.ini", "databases/zaze_provider.db"};
        ArrayList arrayList = new ArrayList(4);
        for (int i = 0; i < 4; i++) {
            arrayList.add(new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + App.INSTANCE.getInstance().getPackageName() + '/' + strArr[i]));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((File) obj).exists()) {
                arrayList2.add(obj);
            }
        }
        return CollectionsKt.toList(arrayList2);
    }

    public final String getMatchedZtyClient() {
        return ClientConfig.INSTANCE.isStudentClient() ? "com.xh.zhitongyunstu" : "com.xh.zhitongyuntch";
    }

    public final String getSavePath() {
        return FilePath.getBakPath() + "/zhitongyun.apk";
    }

    public final String getUnMatchedZtyClient() {
        return ClientConfig.INSTANCE.isStudentClient() ? "com.xh.zhitongyuntch" : "com.xh.zhitongyunstu";
    }

    public final boolean isZtyClient(String packageName) {
        return Intrinsics.areEqual(getMatchedZtyClient(), packageName);
    }

    public final boolean isZtyClientInstalled() {
        boolean zIsAppInstalled = ApplicationUtil.isAppInstalled(getMatchedZtyClient());
        MyLog.i("[MDM]", "智通云安装状态: " + zIsAppInstalled);
        return zIsAppInstalled;
    }

    public final Pair<Integer, Boolean> isZtyUpgraded() {
        int appVersionCode = AppUtil.getAppVersionCode(BaseApplication.INSTANCE.getInstance().getDeviceProtectedStorageContext(), ClientConfig.INSTANCE.getPackageName());
        String property = KeepConfig.getProperty("last_version");
        if (property == null) {
            property = "-1";
        }
        return new Pair<>(Integer.valueOf(appVersionCode), Boolean.valueOf(appVersionCode > Integer.parseInt(property)));
    }

    public final boolean needUseInnerApk() {
        return false;
    }

    public final boolean openClient(Function0<Boolean> doAction) {
        Intrinsics.checkNotNullParameter(doAction, "doAction");
        if (!ZtyFlagManager.INSTANCE.startZtyEnable()) {
            return false;
        }
        if (doAction.invoke().booleanValue()) {
            return true;
        }
        ZtyFlagManager.INSTANCE.markStartZtyFailed();
        return false;
    }

    public final void saveZtyUpgradeVersion(int currentVersionCode) throws Throwable {
        KeepConfig.setProperty("last_version", String.valueOf(currentVersionCode));
    }

    public final boolean startZtyClient(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MdmLog.i("Application[MDM]", "启动智通云客户端...");
        boolean zOpenClient = openClient(new Function0<Boolean>() {
            {
                super(0);
            }

            @Override
            public final Boolean invoke() {
                Intent ztyClientIntent = ZtyClientUtil.INSTANCE.getZtyClientIntent();
                boolean zStartApp = false;
                if (ztyClientIntent != null) {
                    Context context2 = context;
                    if (!KeepLiveService.INSTANCE.isAlive()) {
                        ZtyFlagManager.INSTANCE.increase();
                        ZtyFlagManager.postMarkError$default(ZtyFlagManager.INSTANCE, false, 1, null);
                    }
                    PermissionManager.INSTANCE.applyRuntimePermissions(context2, ZtyClientUtil.INSTANCE.getMatchedZtyClient());
                    zStartApp = ApplicationUtil.INSTANCE.startApp(context2, ztyClientIntent, RequestCodeBuilder.INSTANCE.get(ZtyClientUtil.class));
                }
                return Boolean.valueOf(zStartApp);
            }
        });
        if (!zOpenClient) {
            ZtyFlagManager.INSTANCE.printAllFlag();
        }
        return zOpenClient;
    }
}

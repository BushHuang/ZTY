package com.xh.xhcore.common.statistic.user.report;

import android.os.Parcelable;
import com.xh.view.ToastUtil;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.constant.ConstServerBaseUrlKt;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.archi.HttpUtil;
import com.xh.xhcore.common.http.strategy.xh.callback.BaseXHHttpCallback;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadProxy;
import com.xh.xhcore.common.statistic.XHEnvironment;
import com.xh.xhcore.common.statistic.user.report.model.UserReportResponseModel;
import com.xh.xhcore.common.statistic.user.report.model.UserReportSaveTrackRequestBodyModel;
import com.xh.xhcore.common.util.ActivityListAdapter;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u000f\b&\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\b\u00106\u001a\u000207H\u0002J\b\u00108\u001a\u000207H\u0002J\b\u00109\u001a\u000207H\u0002J\b\u0010:\u001a\u000207H\u0002J\b\u0010;\u001a\u00020\u0005H\u0002J\b\u0010<\u001a\u00020\u0005H$J\u0012\u0010=\u001a\u00020\u00052\b\u0010>\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010?\u001a\u00020\u0005H\u0002J\u0010\u0010?\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0005H\u0002J\b\u0010A\u001a\u00020\u0005H\u0002J\u0012\u0010B\u001a\u0002072\b\u0010>\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010C\u001a\u000207H\u0014J\u0006\u0010D\u001a\u000207J\b\u0010E\u001a\u000207H\u0002R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u000bR\u001a\u00100\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000b\"\u0004\b2\u0010\u001dR\u001a\u00103\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000b\"\u0004\b5\u0010\u001d¨\u0006F"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/BaseUserReportUploader;", "Landroid/os/Parcelable;", "code", "", "message", "", "clientTraceId", "customErrorMessage", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "APP_FULL_LOG_BACKUP_FILE_NAME", "getAPP_FULL_LOG_BACKUP_FILE_NAME", "()Ljava/lang/String;", "APP_FULL_LOG_FILE_NAME", "getAPP_FULL_LOG_FILE_NAME", "APP_FULL_LOG_FULL_PATH", "getAPP_FULL_LOG_FULL_PATH", "APP_FULL_LOG_FULL_PATH_BACKUP", "getAPP_FULL_LOG_FULL_PATH_BACKUP", "APP_FULL_LOG_PATH", "getAPP_FULL_LOG_PATH", "IS_DEBUG", "", "USER_REPORT_DATA_JSON_FILE_FULL_PATH", "getUSER_REPORT_DATA_JSON_FILE_FULL_PATH", "USER_REPORT_DIR", "getUSER_REPORT_DIR", "activityStack", "getActivityStack", "setActivityStack", "(Ljava/lang/String;)V", "getClientTraceId", "getCode", "()I", "cpvdUser", "Lcom/xuehai/provider/core/dto/CPVDUser;", "getCpvdUser", "()Lcom/xuehai/provider/core/dto/CPVDUser;", "setCpvdUser", "(Lcom/xuehai/provider/core/dto/CPVDUser;)V", "currentTimeMillis", "", "getCurrentTimeMillis", "()Ljava/lang/Long;", "setCurrentTimeMillis", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getCustomErrorMessage", "getMessage", "recentActivityList", "getRecentActivityList", "setRecentActivityList", "userFillFeedback", "getUserFillFeedback", "setUserFillFeedback", "copyFullLogToUserReportDir", "", "createDataJsonFile", "deleteFiles", "doZip", "getDate", "getErrorType", "getTaskContent", "filePath", "getTime", "format", "getUploadZipFileName", "postToTrack", "prepareFilesForZip", "upload", "uploadZipAndPostToTrack", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseUserReportUploader implements Parcelable {
    private final transient String APP_FULL_LOG_BACKUP_FILE_NAME;
    private final transient String APP_FULL_LOG_FILE_NAME;
    private final transient String APP_FULL_LOG_FULL_PATH;
    private final transient String APP_FULL_LOG_FULL_PATH_BACKUP;
    private final transient String APP_FULL_LOG_PATH;
    private final transient boolean IS_DEBUG;
    private final transient String USER_REPORT_DATA_JSON_FILE_FULL_PATH;
    private final transient String USER_REPORT_DIR;
    private String activityStack;
    private final String clientTraceId;
    private final int code;
    private CPVDUser cpvdUser;
    private Long currentTimeMillis;
    private final String customErrorMessage;
    private final String message;
    private String recentActivityList;
    private String userFillFeedback;

    public BaseUserReportUploader(int i, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(str2, "clientTraceId");
        Intrinsics.checkNotNullParameter(str3, "customErrorMessage");
        this.code = i;
        this.message = str;
        this.clientTraceId = str2;
        this.customErrorMessage = str3;
        this.USER_REPORT_DIR = ((Object) XHEnvironment.getExternalStorageDirectory().getAbsolutePath()) + "/xuehai/log/userReport/" + ((Object) XHAppUtil.getPackageName()) + '/';
        this.APP_FULL_LOG_PATH = Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().getAbsolutePath(), "/xuehai/log/");
        String str4 = "catch#" + ((Object) XHAppUtil.getPackageName()) + "#.log";
        this.APP_FULL_LOG_FILE_NAME = str4;
        this.APP_FULL_LOG_FULL_PATH = Intrinsics.stringPlus(this.APP_FULL_LOG_PATH, str4);
        String strStringPlus = Intrinsics.stringPlus(this.APP_FULL_LOG_FILE_NAME, ".1");
        this.APP_FULL_LOG_BACKUP_FILE_NAME = strStringPlus;
        this.APP_FULL_LOG_FULL_PATH_BACKUP = Intrinsics.stringPlus(this.APP_FULL_LOG_PATH, strStringPlus);
        this.USER_REPORT_DATA_JSON_FILE_FULL_PATH = Intrinsics.stringPlus(this.USER_REPORT_DIR, "data.json");
        this.userFillFeedback = "";
        this.activityStack = "";
        this.recentActivityList = "";
    }

    public BaseUserReportUploader(int i, String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? "" : str3);
    }

    private final void copyFullLogToUserReportDir() {
        XHFileUtil.copyFile(new File(this.APP_FULL_LOG_FULL_PATH), new File(Intrinsics.stringPlus(this.USER_REPORT_DIR, this.APP_FULL_LOG_FILE_NAME)));
        if (XHFileUtil.getFileLength(new File(this.APP_FULL_LOG_FULL_PATH)) < 524288) {
            XHFileUtil.copyFile(new File(this.APP_FULL_LOG_FULL_PATH_BACKUP), new File(Intrinsics.stringPlus(this.USER_REPORT_DIR, this.APP_FULL_LOG_BACKUP_FILE_NAME)));
        }
    }

    private final void createDataJsonFile() {
        if (XHFileUtil.deleteFile(this.USER_REPORT_DATA_JSON_FILE_FULL_PATH)) {
            XHFileUtil.writeFileFromString(this.USER_REPORT_DATA_JSON_FILE_FULL_PATH, JsonUtil.toJsonString(this), false);
        }
    }

    private final void deleteFiles() {
        XHFileUtil.deleteFilesInDir(this.USER_REPORT_DIR);
    }

    private final void doZip() throws Throwable {
        HttpUtil.zip(new File(this.USER_REPORT_DIR).listFiles(), this.USER_REPORT_DIR, getUploadZipFileName());
    }

    private final String getDate() {
        return getTime("yyyyMMdd");
    }

    private final String getTaskContent(String filePath) {
        return "<a href=\"" + ((Object) filePath) + "\">" + this.userFillFeedback + "</a>";
    }

    private final String getTime() {
        return getTime("yyyy-MM-dd-HH-mm-ss");
    }

    private final String getTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Long l = this.currentTimeMillis;
        Intrinsics.checkNotNull(l);
        String str = simpleDateFormat.format(new Date(l.longValue()));
        Intrinsics.checkNotNullExpressionValue(str, "simpleDateFormat.format(date)");
        return str;
    }

    private final String getUploadZipFileName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getErrorType());
        sb.append('_');
        sb.append(this.code);
        sb.append('_');
        sb.append(StringsKt.trim((CharSequence) this.message).toString());
        sb.append('_');
        CPVDUser cPVDUser = this.cpvdUser;
        sb.append(cPVDUser == null ? null : Long.valueOf(cPVDUser.getUserId()));
        sb.append('_');
        sb.append(getTime());
        sb.append('_');
        sb.append(this.clientTraceId);
        sb.append(".zip");
        return sb.toString();
    }

    private final void postToTrack(String filePath) {
        String appId;
        if (this.IS_DEBUG) {
            appId = "SA1000";
        } else {
            appId = XHAppConfigProxy.getInstance().getAppId();
            Intrinsics.checkNotNullExpressionValue(appId, "getInstance().appId");
        }
        String str = appId;
        XHRequestOkHttpProxy xHRequestOkHttpProxy = (XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setMethod("POST")).setUrl(Intrinsics.stringPlus(this.IS_DEBUG ? "http://192.168.5.5:8080" : "https://admin.yunzuoye.net", "/api/track/saveTrack"));
        int appRoleType = XHAppConfigProxy.getInstance().getAppRoleType();
        String versionName = XHAppUtil.getVersionName();
        Intrinsics.checkNotNullExpressionValue(versionName, "getVersionName()");
        String str2 = this.userFillFeedback;
        CPVDUser cPVDUser = this.cpvdUser;
        List listListOf = CollectionsKt.listOf(Long.valueOf(cPVDUser == null ? 0L : cPVDUser.getUserId()));
        CPVDUser cPVDUser2 = this.cpvdUser;
        ((XHRequestOkHttpProxy) xHRequestOkHttpProxy.setRequestBody((Object) new UserReportSaveTrackRequestBodyModel(appRoleType, versionName, str, str2, listListOf, CollectionsKt.listOf(Integer.valueOf(cPVDUser2 == null ? 0 : cPVDUser2.getSchoolId())), getTaskContent(filePath))).setCallback(new BaseXHHttpCallback<UserReportResponseModel>() {
            @Override
            public void failed(int code, String msg) {
                super.failed(code, msg);
                ToastUtil.showLong(XhBaseApplication.mContext, "上报失败 code = " + code + " msg = " + ((Object) msg));
            }

            @Override
            public void success(UserReportResponseModel t) {
                super.success((AnonymousClass1) t);
                ToastUtil.showLong(XhBaseApplication.mContext, "上报成功");
            }
        })).request();
    }

    private final void uploadZipAndPostToTrack() {
        XHUploadOkHttpProxy xHUploadOkHttpProxy = (XHUploadOkHttpProxy) XHUploadProxy.createOkHttp().setUrl(ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/upload/" + XHAppConfigProxy.getInstance().getAppId() + '/');
        String packageName = XHAppUtil.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName()");
        ((XHUploadOkHttpProxy) ((XHUploadOkHttpProxy) xHUploadOkHttpProxy.setAliyunExtraPath(CollectionsKt.listOf((Object[]) new String[]{packageName, getDate()})).addHeader("Rename", (Object) false)).setLocalPath(Intrinsics.stringPlus(this.USER_REPORT_DIR, getUploadZipFileName())).setCallback(new XHRequestCallBack.XHUploadCallBack() {
            @Override
            public void failed(int code, String msg) {
                ToastUtil.showLong(XhBaseApplication.mContext, "上传文件失败 code = " + code + " msg = " + ((Object) msg));
                BaseUserReportUploader.this.deleteFiles();
            }

            @Override
            public void success(int reqId, String filePath) {
                BaseUserReportUploader.this.postToTrack(filePath);
                BaseUserReportUploader.this.deleteFiles();
            }
        })).request();
    }

    public final String getAPP_FULL_LOG_BACKUP_FILE_NAME() {
        return this.APP_FULL_LOG_BACKUP_FILE_NAME;
    }

    public final String getAPP_FULL_LOG_FILE_NAME() {
        return this.APP_FULL_LOG_FILE_NAME;
    }

    public final String getAPP_FULL_LOG_FULL_PATH() {
        return this.APP_FULL_LOG_FULL_PATH;
    }

    public final String getAPP_FULL_LOG_FULL_PATH_BACKUP() {
        return this.APP_FULL_LOG_FULL_PATH_BACKUP;
    }

    public final String getAPP_FULL_LOG_PATH() {
        return this.APP_FULL_LOG_PATH;
    }

    public final String getActivityStack() {
        return this.activityStack;
    }

    public final String getClientTraceId() {
        return this.clientTraceId;
    }

    public final int getCode() {
        return this.code;
    }

    public final CPVDUser getCpvdUser() {
        return this.cpvdUser;
    }

    public final Long getCurrentTimeMillis() {
        return this.currentTimeMillis;
    }

    public final String getCustomErrorMessage() {
        return this.customErrorMessage;
    }

    protected abstract String getErrorType();

    public final String getMessage() {
        return this.message;
    }

    public final String getRecentActivityList() {
        return this.recentActivityList;
    }

    public final String getUSER_REPORT_DATA_JSON_FILE_FULL_PATH() {
        return this.USER_REPORT_DATA_JSON_FILE_FULL_PATH;
    }

    public final String getUSER_REPORT_DIR() {
        return this.USER_REPORT_DIR;
    }

    public final String getUserFillFeedback() {
        return this.userFillFeedback;
    }

    protected void prepareFilesForZip() {
        this.cpvdUser = CPVDUserData.getUser(XhBaseApplication.mContext);
        this.currentTimeMillis = Long.valueOf(System.currentTimeMillis());
        this.activityStack = ActivityListAdapter.getActivityStackString();
        this.recentActivityList = ActivityListAdapter.getRecentActivityListString();
        createDataJsonFile();
        copyFullLogToUserReportDir();
    }

    public final void setActivityStack(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.activityStack = str;
    }

    public final void setCpvdUser(CPVDUser cPVDUser) {
        this.cpvdUser = cPVDUser;
    }

    public final void setCurrentTimeMillis(Long l) {
        this.currentTimeMillis = l;
    }

    public final void setRecentActivityList(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.recentActivityList = str;
    }

    public final void setUserFillFeedback(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userFillFeedback = str;
    }

    public final void upload() throws Throwable {
        deleteFiles();
        prepareFilesForZip();
        doZip();
        uploadZipAndPostToTrack();
    }
}

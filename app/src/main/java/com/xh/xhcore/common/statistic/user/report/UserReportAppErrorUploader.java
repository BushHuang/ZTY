package com.xh.xhcore.common.statistic.user.report;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.ErrorLogPath;
import com.xh.xhcore.common.util.XHFileUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B1\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\b\u0010\u0014\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\tH\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\u0018\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0007H\u0016R\u0014\u0010\u000e\u001a\u00020\u000fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/UserReportAppErrorUploader;", "Lcom/xh/xhcore/common/statistic/user/report/BaseUserReportUploader;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "code", "", "message", "", "clientTraceId", "customErrorMessage", "appUserUploadFolder", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MAX_APP_USER_REPORT_DIR_LENGTH", "", "getMAX_APP_USER_REPORT_DIR_LENGTH", "()J", "getAppUserUploadFolder", "()Ljava/lang/String;", "describeContents", "getErrorType", "prepareFilesForZip", "", "writeToParcel", "flags", "CREATOR", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserReportAppErrorUploader extends BaseUserReportUploader implements Parcelable {

    public static final Companion INSTANCE = new Companion(null);
    private final transient long MAX_APP_USER_REPORT_DIR_LENGTH;
    private final String appUserUploadFolder;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/UserReportAppErrorUploader$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/xh/xhcore/common/statistic/user/report/UserReportAppErrorUploader;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/xh/xhcore/common/statistic/user/report/UserReportAppErrorUploader;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<UserReportAppErrorUploader> {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override
        public UserReportAppErrorUploader createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new UserReportAppErrorUploader(parcel);
        }

        @Override
        public UserReportAppErrorUploader[] newArray(int size) {
            return new UserReportAppErrorUploader[size];
        }
    }

    public UserReportAppErrorUploader(int i, String str, String str2, String str3, String str4) {
        super(i, str, str2, str3);
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(str2, "clientTraceId");
        Intrinsics.checkNotNullParameter(str3, "customErrorMessage");
        Intrinsics.checkNotNullParameter(str4, "appUserUploadFolder");
        this.appUserUploadFolder = str4;
        this.MAX_APP_USER_REPORT_DIR_LENGTH = 10485760L;
    }

    public UserReportAppErrorUploader(int i, String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4);
    }

    public UserReportAppErrorUploader(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int i = parcel.readInt();
        String string = parcel.readString();
        String str = string == null ? "" : string;
        String string2 = parcel.readString();
        String str2 = string2 == null ? "" : string2;
        String string3 = parcel.readString();
        String str3 = string3 == null ? "" : string3;
        String string4 = parcel.readString();
        this(i, str, str2, str3, string4 == null ? "" : string4);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public final String getAppUserUploadFolder() {
        return this.appUserUploadFolder;
    }

    @Override
    protected String getErrorType() {
        return ErrorLogPath.appUpload.name();
    }

    public final long getMAX_APP_USER_REPORT_DIR_LENGTH() {
        return this.MAX_APP_USER_REPORT_DIR_LENGTH;
    }

    @Override
    protected void prepareFilesForZip() {
        if (!TextUtils.isEmpty(this.appUserUploadFolder)) {
            long dirLength = XHFileUtil.getDirLength(this.appUserUploadFolder);
            if (dirLength <= 0 || dirLength > this.MAX_APP_USER_REPORT_DIR_LENGTH) {
                LogUtils.INSTANCE.w(Intrinsics.stringPlus("appUserReportDirLength = ", Long.valueOf(dirLength)));
            } else {
                XHFileUtil.copyDir(this.appUserUploadFolder, getUSER_REPORT_DIR());
            }
        }
        super.prepareFilesForZip();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(getCode());
        parcel.writeString(getMessage());
        parcel.writeString(getClientTraceId());
        parcel.writeString(getCustomErrorMessage());
        parcel.writeString(this.appUserUploadFolder);
    }
}

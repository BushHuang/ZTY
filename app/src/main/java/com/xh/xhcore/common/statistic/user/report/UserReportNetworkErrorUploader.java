package com.xh.xhcore.common.statistic.user.report;

import android.os.Parcel;
import android.os.Parcelable;
import com.xh.xhcore.common.statistic.ErrorLogPath;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B'\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\tH\u0014J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0007H\u0016¨\u0006\u0013"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/UserReportNetworkErrorUploader;", "Lcom/xh/xhcore/common/statistic/user/report/BaseUserReportUploader;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "code", "", "message", "", "clientTraceId", "customErrorMessage", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "describeContents", "getErrorType", "writeToParcel", "", "flags", "CREATOR", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserReportNetworkErrorUploader extends BaseUserReportUploader implements Parcelable {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/UserReportNetworkErrorUploader$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/xh/xhcore/common/statistic/user/report/UserReportNetworkErrorUploader;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/xh/xhcore/common/statistic/user/report/UserReportNetworkErrorUploader;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<UserReportNetworkErrorUploader> {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override
        public UserReportNetworkErrorUploader createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new UserReportNetworkErrorUploader(parcel);
        }

        @Override
        public UserReportNetworkErrorUploader[] newArray(int size) {
            return new UserReportNetworkErrorUploader[size];
        }
    }

    public UserReportNetworkErrorUploader(int i, String str, String str2, String str3) {
        super(i, str, str2, str3);
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(str2, "clientTraceId");
        Intrinsics.checkNotNullParameter(str3, "customErrorMessage");
    }

    public UserReportNetworkErrorUploader(int i, String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? "" : str3);
    }

    public UserReportNetworkErrorUploader(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int i = parcel.readInt();
        String string = parcel.readString();
        string = string == null ? "" : string;
        String string2 = parcel.readString();
        string2 = string2 == null ? "" : string2;
        String string3 = parcel.readString();
        this(i, string, string2, string3 != null ? string3 : "");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    protected String getErrorType() {
        return ErrorLogPath.networkError.name();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(getCode());
        parcel.writeString(getMessage());
        parcel.writeString(getClientTraceId());
        parcel.writeString(getCustomErrorMessage());
    }
}

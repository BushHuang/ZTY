package com.xh.xhcore.common.statistic.user.report.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/model/UserReportResponseModel;", "", "code", "", "error", "", "msg", "(ILjava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "getError", "()Ljava/lang/String;", "getMsg", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserReportResponseModel {
    private final int code;
    private final String error;
    private final String msg;

    public UserReportResponseModel(int i, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "error");
        Intrinsics.checkNotNullParameter(str2, "msg");
        this.code = i;
        this.error = str;
        this.msg = str2;
    }

    public static UserReportResponseModel copy$default(UserReportResponseModel userReportResponseModel, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = userReportResponseModel.code;
        }
        if ((i2 & 2) != 0) {
            str = userReportResponseModel.error;
        }
        if ((i2 & 4) != 0) {
            str2 = userReportResponseModel.msg;
        }
        return userReportResponseModel.copy(i, str, str2);
    }

    public final int getCode() {
        return this.code;
    }

    public final String getError() {
        return this.error;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final UserReportResponseModel copy(int code, String error, String msg) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return new UserReportResponseModel(code, error, msg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserReportResponseModel)) {
            return false;
        }
        UserReportResponseModel userReportResponseModel = (UserReportResponseModel) other;
        return this.code == userReportResponseModel.code && Intrinsics.areEqual(this.error, userReportResponseModel.error) && Intrinsics.areEqual(this.msg, userReportResponseModel.msg);
    }

    public final int getCode() {
        return this.code;
    }

    public final String getError() {
        return this.error;
    }

    public final String getMsg() {
        return this.msg;
    }

    public int hashCode() {
        return (((this.code * 31) + this.error.hashCode()) * 31) + this.msg.hashCode();
    }

    public String toString() {
        return "UserReportResponseModel(code=" + this.code + ", error=" + this.error + ", msg=" + this.msg + ')';
    }
}

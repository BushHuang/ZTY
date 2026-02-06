package com.xh.xhcore.common.statistic.user.report.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J[\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011¨\u0006%"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/model/UserReportSaveTrackRequestBodyModel;", "", "iType", "", "sAppVersion", "", "sCode", "sName", "sOfferIds", "", "", "sSchoolId", "sTaskContent", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getIType", "()I", "getSAppVersion", "()Ljava/lang/String;", "getSCode", "getSName", "getSOfferIds", "()Ljava/util/List;", "getSSchoolId", "getSTaskContent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserReportSaveTrackRequestBodyModel {
    private final int iType;
    private final String sAppVersion;
    private final String sCode;
    private final String sName;
    private final List<Long> sOfferIds;
    private final List<Integer> sSchoolId;
    private final String sTaskContent;

    public UserReportSaveTrackRequestBodyModel(int i, String str, String str2, String str3, List<Long> list, List<Integer> list2, String str4) {
        Intrinsics.checkNotNullParameter(str, "sAppVersion");
        Intrinsics.checkNotNullParameter(str2, "sCode");
        Intrinsics.checkNotNullParameter(str3, "sName");
        Intrinsics.checkNotNullParameter(list, "sOfferIds");
        Intrinsics.checkNotNullParameter(list2, "sSchoolId");
        Intrinsics.checkNotNullParameter(str4, "sTaskContent");
        this.iType = i;
        this.sAppVersion = str;
        this.sCode = str2;
        this.sName = str3;
        this.sOfferIds = list;
        this.sSchoolId = list2;
        this.sTaskContent = str4;
    }

    public static UserReportSaveTrackRequestBodyModel copy$default(UserReportSaveTrackRequestBodyModel userReportSaveTrackRequestBodyModel, int i, String str, String str2, String str3, List list, List list2, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = userReportSaveTrackRequestBodyModel.iType;
        }
        if ((i2 & 2) != 0) {
            str = userReportSaveTrackRequestBodyModel.sAppVersion;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = userReportSaveTrackRequestBodyModel.sCode;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = userReportSaveTrackRequestBodyModel.sName;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            list = userReportSaveTrackRequestBodyModel.sOfferIds;
        }
        List list3 = list;
        if ((i2 & 32) != 0) {
            list2 = userReportSaveTrackRequestBodyModel.sSchoolId;
        }
        List list4 = list2;
        if ((i2 & 64) != 0) {
            str4 = userReportSaveTrackRequestBodyModel.sTaskContent;
        }
        return userReportSaveTrackRequestBodyModel.copy(i, str5, str6, str7, list3, list4, str4);
    }

    public final int getIType() {
        return this.iType;
    }

    public final String getSAppVersion() {
        return this.sAppVersion;
    }

    public final String getSCode() {
        return this.sCode;
    }

    public final String getSName() {
        return this.sName;
    }

    public final List<Long> component5() {
        return this.sOfferIds;
    }

    public final List<Integer> component6() {
        return this.sSchoolId;
    }

    public final String getSTaskContent() {
        return this.sTaskContent;
    }

    public final UserReportSaveTrackRequestBodyModel copy(int iType, String sAppVersion, String sCode, String sName, List<Long> sOfferIds, List<Integer> sSchoolId, String sTaskContent) {
        Intrinsics.checkNotNullParameter(sAppVersion, "sAppVersion");
        Intrinsics.checkNotNullParameter(sCode, "sCode");
        Intrinsics.checkNotNullParameter(sName, "sName");
        Intrinsics.checkNotNullParameter(sOfferIds, "sOfferIds");
        Intrinsics.checkNotNullParameter(sSchoolId, "sSchoolId");
        Intrinsics.checkNotNullParameter(sTaskContent, "sTaskContent");
        return new UserReportSaveTrackRequestBodyModel(iType, sAppVersion, sCode, sName, sOfferIds, sSchoolId, sTaskContent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserReportSaveTrackRequestBodyModel)) {
            return false;
        }
        UserReportSaveTrackRequestBodyModel userReportSaveTrackRequestBodyModel = (UserReportSaveTrackRequestBodyModel) other;
        return this.iType == userReportSaveTrackRequestBodyModel.iType && Intrinsics.areEqual(this.sAppVersion, userReportSaveTrackRequestBodyModel.sAppVersion) && Intrinsics.areEqual(this.sCode, userReportSaveTrackRequestBodyModel.sCode) && Intrinsics.areEqual(this.sName, userReportSaveTrackRequestBodyModel.sName) && Intrinsics.areEqual(this.sOfferIds, userReportSaveTrackRequestBodyModel.sOfferIds) && Intrinsics.areEqual(this.sSchoolId, userReportSaveTrackRequestBodyModel.sSchoolId) && Intrinsics.areEqual(this.sTaskContent, userReportSaveTrackRequestBodyModel.sTaskContent);
    }

    public final int getIType() {
        return this.iType;
    }

    public final String getSAppVersion() {
        return this.sAppVersion;
    }

    public final String getSCode() {
        return this.sCode;
    }

    public final String getSName() {
        return this.sName;
    }

    public final List<Long> getSOfferIds() {
        return this.sOfferIds;
    }

    public final List<Integer> getSSchoolId() {
        return this.sSchoolId;
    }

    public final String getSTaskContent() {
        return this.sTaskContent;
    }

    public int hashCode() {
        return (((((((((((this.iType * 31) + this.sAppVersion.hashCode()) * 31) + this.sCode.hashCode()) * 31) + this.sName.hashCode()) * 31) + this.sOfferIds.hashCode()) * 31) + this.sSchoolId.hashCode()) * 31) + this.sTaskContent.hashCode();
    }

    public String toString() {
        return "UserReportSaveTrackRequestBodyModel(iType=" + this.iType + ", sAppVersion=" + this.sAppVersion + ", sCode=" + this.sCode + ", sName=" + this.sName + ", sOfferIds=" + this.sOfferIds + ", sSchoolId=" + this.sSchoolId + ", sTaskContent=" + this.sTaskContent + ')';
    }
}

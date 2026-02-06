package com.xh.xhcore.common.hotfix;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/hotfix/PatchConditionModel;", "", "userId", "", "schoolId", "(Ljava/lang/String;Ljava/lang/String;)V", "getSchoolId", "()Ljava/lang/String;", "getUserId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PatchConditionModel {
    private final String schoolId;
    private final String userId;

    public PatchConditionModel() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public PatchConditionModel(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "userId");
        Intrinsics.checkNotNullParameter(str2, "schoolId");
        this.userId = str;
        this.schoolId = str2;
    }

    public PatchConditionModel(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2);
    }

    public static PatchConditionModel copy$default(PatchConditionModel patchConditionModel, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = patchConditionModel.userId;
        }
        if ((i & 2) != 0) {
            str2 = patchConditionModel.schoolId;
        }
        return patchConditionModel.copy(str, str2);
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getSchoolId() {
        return this.schoolId;
    }

    public final PatchConditionModel copy(String userId, String schoolId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(schoolId, "schoolId");
        return new PatchConditionModel(userId, schoolId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PatchConditionModel)) {
            return false;
        }
        PatchConditionModel patchConditionModel = (PatchConditionModel) other;
        return Intrinsics.areEqual(this.userId, patchConditionModel.userId) && Intrinsics.areEqual(this.schoolId, patchConditionModel.schoolId);
    }

    public final String getSchoolId() {
        return this.schoolId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        return (this.userId.hashCode() * 31) + this.schoolId.hashCode();
    }

    public String toString() {
        return "PatchConditionModel(userId=" + this.userId + ", schoolId=" + this.schoolId + ')';
    }
}

package com.xuehai.launcher.common.http.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\bHÖ\u0001R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006\""}, d2 = {"Lcom/xuehai/launcher/common/http/response/CheckDeviceResponse;", "", "purpose", "", "isValid", "", "systemIsValid", "serviceVersionCode", "", "additionalLogonAuthorizations", "", "(IZZLjava/lang/String;Ljava/util/List;)V", "getAdditionalLogonAuthorizations", "()Ljava/util/List;", "()Z", "setValid", "(Z)V", "getPurpose", "()I", "getServiceVersionCode", "()Ljava/lang/String;", "setServiceVersionCode", "(Ljava/lang/String;)V", "getSystemIsValid", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CheckDeviceResponse {
    private final List<Integer> additionalLogonAuthorizations;
    private boolean isValid;
    private final int purpose;
    private String serviceVersionCode;
    private final boolean systemIsValid;

    public CheckDeviceResponse(int i, boolean z, boolean z2, String str, List<Integer> list) {
        this.purpose = i;
        this.isValid = z;
        this.systemIsValid = z2;
        this.serviceVersionCode = str;
        this.additionalLogonAuthorizations = list;
    }

    public CheckDeviceResponse(int i, boolean z, boolean z2, String str, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? -1 : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? null : str, list);
    }

    public static CheckDeviceResponse copy$default(CheckDeviceResponse checkDeviceResponse, int i, boolean z, boolean z2, String str, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = checkDeviceResponse.purpose;
        }
        if ((i2 & 2) != 0) {
            z = checkDeviceResponse.isValid;
        }
        boolean z3 = z;
        if ((i2 & 4) != 0) {
            z2 = checkDeviceResponse.systemIsValid;
        }
        boolean z4 = z2;
        if ((i2 & 8) != 0) {
            str = checkDeviceResponse.serviceVersionCode;
        }
        String str2 = str;
        if ((i2 & 16) != 0) {
            list = checkDeviceResponse.additionalLogonAuthorizations;
        }
        return checkDeviceResponse.copy(i, z3, z4, str2, list);
    }

    public final int getPurpose() {
        return this.purpose;
    }

    public final boolean getIsValid() {
        return this.isValid;
    }

    public final boolean getSystemIsValid() {
        return this.systemIsValid;
    }

    public final String getServiceVersionCode() {
        return this.serviceVersionCode;
    }

    public final List<Integer> component5() {
        return this.additionalLogonAuthorizations;
    }

    public final CheckDeviceResponse copy(int purpose, boolean isValid, boolean systemIsValid, String serviceVersionCode, List<Integer> additionalLogonAuthorizations) {
        return new CheckDeviceResponse(purpose, isValid, systemIsValid, serviceVersionCode, additionalLogonAuthorizations);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckDeviceResponse)) {
            return false;
        }
        CheckDeviceResponse checkDeviceResponse = (CheckDeviceResponse) other;
        return this.purpose == checkDeviceResponse.purpose && this.isValid == checkDeviceResponse.isValid && this.systemIsValid == checkDeviceResponse.systemIsValid && Intrinsics.areEqual(this.serviceVersionCode, checkDeviceResponse.serviceVersionCode) && Intrinsics.areEqual(this.additionalLogonAuthorizations, checkDeviceResponse.additionalLogonAuthorizations);
    }

    public final List<Integer> getAdditionalLogonAuthorizations() {
        return this.additionalLogonAuthorizations;
    }

    public final int getPurpose() {
        return this.purpose;
    }

    public final String getServiceVersionCode() {
        return this.serviceVersionCode;
    }

    public final boolean getSystemIsValid() {
        return this.systemIsValid;
    }

    public int hashCode() {
        int i = this.purpose * 31;
        boolean z = this.isValid;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (i + i2) * 31;
        boolean z2 = this.systemIsValid;
        int i4 = (i3 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        String str = this.serviceVersionCode;
        int iHashCode = (i4 + (str == null ? 0 : str.hashCode())) * 31;
        List<Integer> list = this.additionalLogonAuthorizations;
        return iHashCode + (list != null ? list.hashCode() : 0);
    }

    public final boolean isValid() {
        return this.isValid;
    }

    public final void setServiceVersionCode(String str) {
        this.serviceVersionCode = str;
    }

    public final void setValid(boolean z) {
        this.isValid = z;
    }

    public String toString() {
        return "CheckDeviceResponse(purpose=" + this.purpose + ", isValid=" + this.isValid + ", systemIsValid=" + this.systemIsValid + ", serviceVersionCode=" + this.serviceVersionCode + ", additionalLogonAuthorizations=" + this.additionalLogonAuthorizations + ')';
    }
}

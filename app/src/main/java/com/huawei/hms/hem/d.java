package com.huawei.hms.hem;

import com.huawei.hem.license.HemLicenseStatusListener;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;

public final class d extends TaskApiCall<a, Integer> {

    public HemLicenseStatusListener f336a;

    public d(String str, String str2) {
        super(str, str2);
        this.f336a = null;
    }

    @Override
    protected final void doExecute(AnyClient anyClient, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<Integer> taskCompletionSource) throws NumberFormatException {
        if (responseErrorCode.getErrorCode() != 0) {
            this.f336a.onStatus(1000, e.a(1000));
        } else {
            int i = Integer.parseInt(str);
            this.f336a.onStatus(i, e.a(i));
        }
    }
}

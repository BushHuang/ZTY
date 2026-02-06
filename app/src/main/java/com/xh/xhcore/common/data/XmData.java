package com.xh.xhcore.common.data;

import android.text.TextUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.IXHAppConfig;
import com.xh.xhcore.common.http.XHMicroServerV3;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;

public class XmData {
    public static String[] getMicroServerUrl(IXHAppConfig.MicroServerVersion microServerVersion) {
        CPVDUser user = getUser();
        if (user == null) {
            return null;
        }
        String[] microRestfulArray = user.getMicroRestfulArray();
        if (microRestfulArray != null && microRestfulArray.length != 0) {
            return replaceSuffix(microServerVersion, microRestfulArray);
        }
        String[] microServerArray = user.getMicroServerArray();
        if (microServerArray != null && microServerArray.length != 0) {
            return replaceSuffix(microServerVersion, microServerArray);
        }
        String microService = user.getMicroService();
        if (TextUtils.isEmpty(microService)) {
            return null;
        }
        return replaceSuffix(microServerVersion, new String[]{microService});
    }

    private static CPVDUser getSourceUser() {
        return CPVDUserData.getUser(XhBaseApplication.mContext);
    }

    public static CPVDUser getUser() {
        return getSourceUser();
    }

    private static String[] replaceSuffix(IXHAppConfig.MicroServerVersion microServerVersion, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (microServerVersion == IXHAppConfig.MicroServerVersion.VERSION1) {
                strArr2[i] = strArr[i].replace("/XhServerMg/GetXsList", "/api/v1/pub/microServer/");
            } else if (microServerVersion == IXHAppConfig.MicroServerVersion.VERSION2) {
                strArr2[i] = strArr[i].replace("/XhServerMg/GetXsList", "/api/v2/pub/microServer/list/").replace("/api/v1/pub/microServer/", "/api/v2/pub/microServer/list/");
            } else if (microServerVersion == IXHAppConfig.MicroServerVersion.VERSION3) {
                strArr2[i] = strArr[i].replace("/XhServerMg/GetXsList", XHMicroServerV3.PATH_RESTFUL_V3).replace("/api/v1/pub/microServer/", XHMicroServerV3.PATH_RESTFUL_V3).replace("/api/v2/pub/microServer/list/", XHMicroServerV3.PATH_RESTFUL_V3);
            }
        }
        return strArr2;
    }

    public static void setUser(CPVDUser cPVDUser) {
    }
}

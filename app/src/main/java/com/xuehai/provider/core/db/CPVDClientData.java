package com.xuehai.provider.core.db;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.xuehai.provider.constants.AuthorityUris;
import com.xuehai.provider.core.dto.CPVDClient;
import com.xuehai.provider.utils.CPVDUtil;

public class CPVDClientData {
    public static final String CLIENT_TYPE = "clientType";
    public static final String PACKAGE_NAME = "packageName";
    private static final String TAG = "ClientData";
    public static final String VERSION_CODE = "versionCode";
    public static final String VERSION_NAME = "versionName";
    private static CPVDClient clientCache;

    public static CPVDClient getClientInfo(Context context) {
        CPVDClient cPVDClient = clientCache;
        try {
            Cursor cursorQuery = context.getContentResolver().query(getUri(), null, null, null, null);
            if (cursorQuery != null) {
                if (cursorQuery.moveToNext()) {
                    CPVDClient cPVDClient2 = new CPVDClient();
                    try {
                        cPVDClient2.setPackageName(DaoUtil.getString(cursorQuery, "packageName"));
                        cPVDClient2.setVersionCode(DaoUtil.getLong(cursorQuery, "versionCode"));
                        cPVDClient2.setVersionName(DaoUtil.getString(cursorQuery, "versionName"));
                        cPVDClient2.setClientType(DaoUtil.getString(cursorQuery, "clientType"));
                        clientCache = cPVDClient2;
                        cPVDClient = cPVDClient2;
                    } catch (Exception e) {
                        e = e;
                        cPVDClient = cPVDClient2;
                        e.printStackTrace();
                        return cPVDClient;
                    }
                }
                cursorQuery.close();
            }
        } catch (Exception e2) {
            e = e2;
        }
        return cPVDClient;
    }

    private static Uri getUri() {
        return AuthorityUris.getAuthorityUris().getClientUri();
    }

    public static boolean isTV(Context context) {
        CPVDClient clientInfo = getClientInfo(context);
        return (clientInfo == null || TextUtils.isEmpty(clientInfo.getClientType())) ? CPVDUtil.isAppInstalled(context, "com.xh.zhitongyuntv") : "tv".equals(clientInfo.getClientType());
    }
}

package com.xh.networkclient;

import android.util.Base64;
import com.google.gson.Gson;
import com.xh.logutils.Log;
import com.xh.networkclient.bean.FileRes;
import com.xh.networkclient.bean.UploadFileRes;
import com.xh.networkclient.common.Code;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.FileMD5;
import com.xh.networkclient.common.Task;
import com.xh.networkclient.common.TaskType;
import com.xh.xhcore.common.util.XHLog;
import java.io.File;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeCallback {
    private void eventDeal(int i, int i2, String str, byte[] bArr) throws JSONException {
        String str2;
        Task task;
        int value;
        byte[] bArrCalcMD5;
        String desByCode = str;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "id:" + i, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "code:" + i2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "description:" + desByCode, new Object[0]);
        String string = null;
        if (bArr != null) {
            try {
                str2 = new String(bArr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e = e;
                str2 = null;
                e.printStackTrace();
                task = CommonUtils.getTask(Integer.valueOf(i));
                if (task != null) {
                }
                Log.e(Log.XH_NETWORK_CLIENT_TAG, "id:" + i + ", task or task.callbacks is null " + task);
                return;
            }
        } else {
            str2 = null;
        }
        try {
            XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "jsonParam:" + str2, new Object[0]);
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            e.printStackTrace();
            task = CommonUtils.getTask(Integer.valueOf(i));
            if (task != null) {
            }
            Log.e(Log.XH_NETWORK_CLIENT_TAG, "id:" + i + ", task or task.callbacks is null " + task);
            return;
        }
        task = CommonUtils.getTask(Integer.valueOf(i));
        if (task != null || task.callbacks == null) {
            Log.e(Log.XH_NETWORK_CLIENT_TAG, "id:" + i + ", task or task.callbacks is null " + task);
            return;
        }
        if (i2 != 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onEvent callback ", new Object[0]);
            task.callbacks.onEvent(i, i2 + CommonUtils.baseCode, desByCode, str2);
            XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onEvent callback ", new Object[0]);
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onEvent callback cost " + jCurrentTimeMillis2 + "ms, id = " + i, new Object[0]);
            CommonUtils.removeTask(Integer.valueOf(i));
            return;
        }
        int status = CommonUtils.fsSuccessCode;
        if (task.type == TaskType.TASK_HTTP_DOWNLOAD && str2 != null) {
            if (task.isBatch == 0) {
                try {
                    string = new JSONObject(str2).getString("md5");
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                if (string != null && (bArrCalcMD5 = FileMD5.calcMD5(new File(task.localPath))) != null) {
                    String strEncodeToString = Base64.encodeToString(bArrCalcMD5, 2);
                    XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "md5Str=" + strEncodeToString.trim() + ", md5Strlen=" + strEncodeToString.length(), new Object[0]);
                    XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "md5=" + string.trim() + ", len=" + string.length(), new Object[0]);
                    if (!strEncodeToString.equals(string)) {
                        value = Code.CODE_FILE_NOT_COMPLETE.getValue();
                        String desByCode2 = CommonUtils.getDesByCode(Code.CODE_FILE_NOT_COMPLETE);
                        Log.e(Log.XH_NETWORK_CLIENT_TAG, "download file md5 is not equal ");
                        desByCode = desByCode2;
                    }
                }
            }
            if (status != CommonUtils.fsSuccessCode) {
                value = Code.CODE_FS_FAIL.getValue();
                desByCode = CommonUtils.getDesByCode(Code.CODE_FS_FAIL);
                Log.e(Log.XH_NETWORK_CLIENT_TAG, "file server return fail ");
            }
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onEvent callback ", new Object[0]);
            task.callbacks.onEvent(i, value + CommonUtils.baseCode, desByCode, str2);
            XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onEvent callback ", new Object[0]);
            long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis3;
            XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onEvent callback cost " + jCurrentTimeMillis4 + "ms, id = " + i, new Object[0]);
            CommonUtils.removeTask(Integer.valueOf(i));
        }
        if (task.type != TaskType.TASK_HTTP_UPLOAD || str2 == null) {
            if (task.type == TaskType.TASK_HTTP_REQUEST && str2 != null && task.url != null && CommonUtils.fsHostPrefix != null && task.url.indexOf(CommonUtils.fsHostPrefix) != -1) {
                try {
                    status = ((FileRes) new Gson().fromJson(new JSONObject(str2).getString("httpResponse"), FileRes.class)).getStatus();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        } else if (task.isBatch == 0) {
            try {
                status = ((UploadFileRes) new Gson().fromJson(new JSONObject(str2).getString("httpResponse"), UploadFileRes.class)).getStatus();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        value = i2;
        if (status != CommonUtils.fsSuccessCode) {
        }
        long jCurrentTimeMillis32 = System.currentTimeMillis();
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onEvent callback ", new Object[0]);
        task.callbacks.onEvent(i, value + CommonUtils.baseCode, desByCode, str2);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onEvent callback ", new Object[0]);
        long jCurrentTimeMillis42 = System.currentTimeMillis() - jCurrentTimeMillis32;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onEvent callback cost " + jCurrentTimeMillis42 + "ms, id = " + i, new Object[0]);
        CommonUtils.removeTask(Integer.valueOf(i));
    }

    private void progressDeal(int i, double d, double d2, String str) {
        Task task = CommonUtils.getTask(Integer.valueOf(i));
        if (task == null || task.callbacks == null) {
            return;
        }
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "id:" + i, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "total:" + d, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "now:" + d2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "jsonParam:" + str, new Object[0]);
        long jCurrentTimeMillis = System.currentTimeMillis();
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onProgress callback ", new Object[0]);
        task.callbacks.onProgress(i, d, d2, str);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onProgress callback ", new Object[0]);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onProgress callback cost " + jCurrentTimeMillis2 + "ms, id = " + i, new Object[0]);
    }

    private void recvDataDeal(int i, byte[] bArr, int i2, String str) {
        Task task = CommonUtils.getTask(Integer.valueOf(i));
        if (task == null || task.callbacks == null) {
            Log.e(Log.XH_NETWORK_CLIENT_TAG, "task or task.callbacks is null");
            return;
        }
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "id:" + i, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "datalen:" + i2, new Object[0]);
        long jCurrentTimeMillis = System.currentTimeMillis();
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "befor onRecvDate callback ", new Object[0]);
        task.callbacks.onRecvDate(i, bArr, i2, str);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "after onRecvDate callback ", new Object[0]);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, "onRecvDate callback cost " + jCurrentTimeMillis2 + "ms, id = " + i, new Object[0]);
    }
}

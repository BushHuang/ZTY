package com.xh.networkclient;

import com.google.gson.Gson;
import com.xh.logutils.Log;
import com.xh.nativeUtils;
import com.xh.networkclient.bean.BatchFileParam;
import com.xh.networkclient.bean.FileRes;
import com.xh.networkclient.bean.UploadFileRes;
import com.xh.networkclient.bean.UrlDTO;
import com.xh.networkclient.common.Code;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.FileMD5;
import com.xh.networkclient.common.Task;
import com.xh.networkclient.common.TaskType;
import com.xh.xhcore.common.util.XHLog;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequest {
    private static final HttpRequest INSTANCE = new HttpRequest();
    private HashMap<Integer, AsynParam> asynParams = new HashMap<>();
    private int earlyBaseInfoSize = 524288;
    private String JSON_HTTP_RESPONSE_KEY = "httpResponse";
    private String JSON_POST_DATA_KEY = "postData";

    private class AsynParam {
        public String updateUrl;

        private AsynParam() {
        }

        AsynParam(HttpRequest httpRequest, AsynParam asynParam) {
            this();
        }
    }

    public enum RequestMethod {
        POST,
        DELETE,
        PUT,
        PATCH,
        GET;

        public static RequestMethod[] valuesCustom() {
            RequestMethod[] requestMethodArrValuesCustom = values();
            int length = requestMethodArrValuesCustom.length;
            RequestMethod[] requestMethodArr = new RequestMethod[length];
            System.arraycopy(requestMethodArrValuesCustom, 0, requestMethodArr, 0, length);
            return requestMethodArr;
        }
    }

    static {
        System.loadLibrary("crypto");
        System.loadLibrary("ssl");
        System.loadLibrary("xh_curl");
        System.loadLibrary("xh_common");
        System.loadLibrary("xh_network_client");
        checkProxy();
    }

    private HttpRequest() {
    }

    private static native String JNIDomainToIp(String str);

    private native int JNIDownload(int i, String str, String str2, String str3);

    private native int JNIRequest(int i, String str, byte[] bArr);

    private static native int JNISetProxy(String str);

    private static native int JNISetUpDownMaxThreads(int i);

    private native int JNIStop(int i);

    private static native int JNITestConnect(String str, int i);

    private native int JNIUpload(int i, String str, String str2, String str3);

    private static void checkProxy() {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " enter ", new Object[0]);
        File[] fileArrListFiles = new File("/mnt/sdcard/xhproxy").listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            JNISetProxy(decryption(fileArrListFiles[0].getName()));
        }
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " out ", new Object[0]);
    }

    private static String decryption(String str) {
        String[] strArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] strArr2 = {"Q", "W", "E", "R", "A", "S", "D", "F", "Z", "X"};
        String str2 = "";
        if (str != null && str.length() > 0) {
            for (char c : str.toCharArray()) {
                if ("O".equals(String.valueOf(c))) {
                    str2 = String.valueOf(str2) + ".";
                } else if ("P".equals(String.valueOf(c))) {
                    str2 = String.valueOf(str2) + ":";
                } else if (Arrays.asList(strArr2).contains(String.valueOf(c))) {
                    str2 = String.valueOf(str2) + strArr[Arrays.asList(strArr2).indexOf(String.valueOf(c))];
                }
            }
        }
        return str2;
    }

    private int downloadByFileId(String str, final String str2, String str3, final NetworkCallback networkCallback) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " fileId:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str3, new Object[0]);
        final int id = CommonUtils.getId();
        if (getUrlByFileId(str, str3, new NetworkCallback() {
            @Override
            public void onEvent(int i, int i2, String str4, String str5) {
                String str6;
                if (i2 != CommonUtils.baseCode) {
                    networkCallback.onEvent(id, i2, str4, str5);
                    return;
                }
                JSONObject jSONObject = null;
                if (str5 != null) {
                    try {
                        str6 = ((FileRes) new Gson().fromJson(new JSONObject(str5).getString(HttpRequest.this.JSON_HTTP_RESPONSE_KEY), FileRes.class)).getFileDTO().getUrl()[0];
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    str6 = null;
                }
                try {
                    jSONObject = str5 == null ? new JSONObject() : new JSONObject(str5);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                HttpRequest httpRequest = HttpRequest.this;
                String str7 = str2;
                String string = jSONObject.toString();
                final NetworkCallback networkCallback2 = networkCallback;
                final int i3 = id;
                if (httpRequest.download(str6, str7, string, new NetworkCallback() {
                    @Override
                    public void onEvent(int i4, int i5, String str8, String str9) {
                        networkCallback2.onEvent(i3, i5, str8, str9);
                    }

                    @Override
                    public void onProgress(int i4, double d, double d2, String str8) {
                        networkCallback2.onProgress(i3, d, d2, str8);
                    }

                    @Override
                    public void onRecvDate(int i4, byte[] bArr, int i5, String str8) {
                    }
                }) <= 0) {
                    networkCallback.onEvent(id, Code.CODE_ADD_DOWNLOAD_TASK_FAIL.getValue(), CommonUtils.getDesByCode(Code.CODE_ADD_DOWNLOAD_TASK_FAIL), str5);
                }
            }

            @Override
            public void onProgress(int i, double d, double d2, String str4) {
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str4) {
            }
        }) <= 0) {
            return 0;
        }
        return id;
    }

    public static final HttpRequest getInstance() {
        return INSTANCE;
    }

    private int getUrlByFileId(String str, String str2, NetworkCallback networkCallback) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " fileId:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str2, new Object[0]);
        return request(String.valueOf(CommonUtils.fsDownloadUrl) + "?fileId=" + str, str2, networkCallback);
    }

    private static String stringToMD5(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private int upload2(String str, String[] strArr, String str2, NetworkCallback networkCallback) throws JSONException {
        String string;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.length:" + strArr.length, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.toString():" + strArr.toString(), new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str2, new Object[0]);
        if (str == null) {
            str = "http://files.yunzuoye.net/XHFileServer/file/zip/upload/tmp";
        }
        if (strArr != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < strArr.length; i++) {
                jSONArray.put(strArr[i]);
                if (!new File(strArr[i]).exists()) {
                    XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!", new Object[0]);
                    return 0;
                }
            }
            string = jSONArray.toString();
        } else {
            string = null;
        }
        String str3 = "/sdcard/xuehai/" + stringToMD5(string) + ".zip";
        nativeUtils.JNIZipFiles(string, str3);
        try {
            JSONObject jSONObject = str2 == null ? new JSONObject() : new JSONObject(str2);
            jSONObject.put(CommonUtils.jsonUserDataKey, "isDelete");
            return upload(str, str3, jSONObject.toString(), networkCallback);
        } catch (JSONException e) {
            e.printStackTrace();
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!", new Object[0]);
            return 0;
        }
    }

    private int uploadWithBaseInfo(final String str, final String str2, final String str3, final NetworkCallback networkCallback) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str3, new Object[0]);
        final String str4 = CommonUtils.fsUploadUrl;
        final int id = CommonUtils.getId();
        final NetworkCallback networkCallback2 = new NetworkCallback() {
            @Override
            public void onEvent(int i, int i2, String str5, String str6) {
                networkCallback.onEvent(id, i2, str5, str6);
            }

            @Override
            public void onProgress(int i, double d, double d2, String str5) {
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str5) {
            }
        };
        final NetworkCallback networkCallback3 = new NetworkCallback() {
            String fileUrl = null;

            @Override
            public void onEvent(int i, int i2, String str5, String str6) throws JSONException {
                JSONObject jSONObject;
                JSONException e;
                JSONObject jSONObject2;
                int iRequest;
                String string;
                JSONObject jSONObject3;
                String string2;
                if (i2 != CommonUtils.baseCode) {
                    networkCallback.onEvent(id, i2, str5, str6);
                    return;
                }
                if (str6 != null) {
                    try {
                        this.fileUrl = ((UploadFileRes) new Gson().fromJson(new JSONObject(str6).getString(HttpRequest.this.JSON_HTTP_RESPONSE_KEY), UploadFileRes.class)).getUploadFileDTO().getFileId();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                AsynParam asynParam = (AsynParam) HttpRequest.this.asynParams.get(Integer.valueOf(id));
                if (asynParam == null || asynParam.updateUrl == null) {
                    try {
                        jSONObject = new JSONObject(str3);
                        try {
                            JSONObject jSONObject4 = new JSONObject(jSONObject.getString(HttpRequest.this.JSON_POST_DATA_KEY));
                            try {
                                jSONObject4.put("fileUrl", this.fileUrl);
                                string = jSONObject4.toString();
                                jSONObject2 = new JSONObject();
                            } catch (JSONException e3) {
                                e = e3;
                                jSONObject = jSONObject4;
                            }
                        } catch (JSONException e4) {
                            e = e4;
                        }
                    } catch (JSONException e5) {
                        jSONObject = null;
                        e = e5;
                    }
                    try {
                        jSONObject2.put(HttpRequest.this.JSON_POST_DATA_KEY, string);
                    } catch (JSONException e6) {
                        e = e6;
                        jSONObject = jSONObject2;
                        e.printStackTrace();
                        jSONObject2 = jSONObject;
                        iRequest = HttpRequest.this.request(str, jSONObject2.toString(), networkCallback2);
                        if (iRequest <= 0) {
                        }
                    }
                    iRequest = HttpRequest.this.request(str, jSONObject2.toString(), networkCallback2);
                } else {
                    JSONObject jSONObject5 = new JSONObject();
                    try {
                        jSONObject5.put("fileUrl", this.fileUrl);
                        string2 = jSONObject5.toString();
                        jSONObject3 = new JSONObject();
                    } catch (JSONException e7) {
                        e = e7;
                    }
                    try {
                        jSONObject3.put("postData", string2);
                    } catch (JSONException e8) {
                        e = e8;
                        jSONObject5 = jSONObject3;
                        e.printStackTrace();
                        jSONObject3 = jSONObject5;
                        iRequest = HttpRequest.this.request(asynParam.updateUrl, jSONObject3.toString(), networkCallback2);
                        if (iRequest <= 0) {
                        }
                    }
                    iRequest = HttpRequest.this.request(asynParam.updateUrl, jSONObject3.toString(), networkCallback2);
                }
                if (iRequest <= 0) {
                    networkCallback.onEvent(id, CommonUtils.baseCode + Code.CODE_ADD_UPDATE_FILEURL_TASK_FAIL.getValue(), CommonUtils.getDesByCode(Code.CODE_ADD_UPDATE_FILEURL_TASK_FAIL), str3);
                }
            }

            @Override
            public void onProgress(int i, double d, double d2, String str5) {
                networkCallback.onProgress(id, d, d2, str5);
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str5) {
            }
        };
        NetworkCallback networkCallback4 = new NetworkCallback() {
            @Override
            public void onEvent(int i, int i2, String str5, String str6) throws JSONException {
                if (i2 != CommonUtils.baseCode) {
                    networkCallback.onEvent(id, i2, str5, str6);
                    return;
                }
                networkCallback.onEvent(id, CommonUtils.baseCode + Code.CODE_BASEINFO_UPLOAD_SUCCESS.getValue(), str5, str6);
                if (str6 != null) {
                    try {
                        String string = new JSONObject(new JSONObject(str6).getString(HttpRequest.this.JSON_HTTP_RESPONSE_KEY)).getString("updateUrl");
                        AsynParam asynParam = new AsynParam(HttpRequest.this, null);
                        asynParam.updateUrl = string;
                        HttpRequest.this.asynParams.put(Integer.valueOf(id), asynParam);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (HttpRequest.this.upload(str4, str2, str3, networkCallback3) <= 0) {
                    networkCallback.onEvent(id, CommonUtils.baseCode + Code.CODE_ADD_UPLOAD_FILE_TASK_FAIL.getValue(), CommonUtils.getDesByCode(Code.CODE_ADD_UPLOAD_FILE_TASK_FAIL), str3);
                }
            }

            @Override
            public void onProgress(int i, double d, double d2, String str5) {
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str5) {
            }
        };
        File file = new File(str2);
        if (((!file.exists() || file.length() < ((long) this.earlyBaseInfoSize)) ? upload(str4, str2, str3, networkCallback3) : request(str, str3, networkCallback4)) <= 0) {
            return 0;
        }
        return id;
    }

    private int uploadWithoutZip(String str, String[] strArr, String str2, NetworkCallback networkCallback) throws JSONException {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.length:" + strArr.length, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.toString():" + strArr.toString(), new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str2, new Object[0]);
        if (str == null) {
            str = "http://files.yunzuoye.net/XHFileServer/file/batch/upload/tmp";
        }
        int id = CommonUtils.getId();
        CommonUtils.addTask(Integer.valueOf(id), new Task(id, str, TaskType.TASK_HTTP_UPLOAD, networkCallback));
        ArrayList arrayList = new ArrayList();
        String string = null;
        if (strArr != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < strArr.length; i++) {
                jSONArray.put(strArr[i]);
                File file = new File(strArr[i]);
                if (!file.exists()) {
                    XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!", new Object[0]);
                    CommonUtils.removeTask(Integer.valueOf(id));
                    return 0;
                }
                arrayList.add(FileMD5.getMD5(file));
            }
            string = jSONArray.toString();
        }
        try {
            JSONObject jSONObject = str2 == null ? new JSONObject() : new JSONObject(str2);
            jSONObject.put(CommonUtils.jsonMd5Key, arrayList);
            jSONObject.put("isBatch", "2");
            if (JNIUpload(id, str, string, jSONObject.toString()) == 0) {
                return id;
            }
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail", new Object[0]);
            CommonUtils.removeTask(Integer.valueOf(id));
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!", new Object[0]);
            return 0;
        }
    }

    public String domainToIp(String str) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " domain=" + str, new Object[0]);
        return JNIDomainToIp(str);
    }

    public int download(String str, String str2, String str3, NetworkCallback networkCallback) throws JSONException {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str3, new Object[0]);
        if (str == null) {
            str = CommonUtils.fsDownloadUrl;
        }
        int id = CommonUtils.getId();
        if (str3 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str3);
                if (jSONObject.has("md5")) {
                    jSONObject.getString("md5");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Task task = new Task(id, str, TaskType.TASK_HTTP_DOWNLOAD, networkCallback);
        task.localPath = str2;
        task.isBatch = 0;
        CommonUtils.addTask(Integer.valueOf(id), task);
        if (JNIDownload(id, str, str2, str3) == 0) {
            return id;
        }
        XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIDownload fail", new Object[0]);
        CommonUtils.removeTask(Integer.valueOf(id));
        return 0;
    }

    public int download(String str, String[] strArr, String str2, String str3, NetworkCallback networkCallback) throws JSONException {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " urls.length:" + strArr.length, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " batchDownoadUrl:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " downloadDir:" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str3, new Object[0]);
        if (str == null) {
            str = "http://files.yunzuoye.net/XHFileServer/file/batch/download/tmp";
        }
        int id = CommonUtils.getId();
        Task task = new Task(id, str, TaskType.TASK_HTTP_DOWNLOAD, networkCallback);
        task.isBatch = 1;
        CommonUtils.addTask(Integer.valueOf(id), task);
        String json = null;
        BatchFileParam batchFileParam = new BatchFileParam();
        ArrayList arrayList = new ArrayList();
        if (strArr != null) {
            for (String str4 : strArr) {
                arrayList.add(new UrlDTO(str4));
            }
            batchFileParam.setUrls(arrayList);
            json = new Gson().toJson(batchFileParam);
        }
        try {
            JSONObject jSONObject = str3 == null ? new JSONObject() : new JSONObject(str3);
            jSONObject.put("isBatch", "1");
            jSONObject.put("batchUrls", json);
            jSONObject.put("contentType", "application/json");
            if (JNIDownload(id, str, str2, jSONObject.toString()) == 0) {
                return id;
            }
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail", new Object[0]);
            CommonUtils.removeTask(Integer.valueOf(id));
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!", new Object[0]);
            return 0;
        }
    }

    public int request(String str, String str2, NetworkCallback networkCallback) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str2, new Object[0]);
        return request(str, null, str2, networkCallback);
    }

    public int request(String str, String str2, String str3, NetworkCallback networkCallback) throws JSONException {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str3, new Object[0]);
        if (networkCallback == null) {
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, " networkCallbacks is null", new Object[0]);
        }
        JSONObject jSONObject = null;
        if (str3 == null) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(str3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (str2 != null) {
            try {
                jSONObject.put("headList", str2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        int id = CommonUtils.getId();
        CommonUtils.addTask(Integer.valueOf(id), new Task(id, str, TaskType.TASK_HTTP_REQUEST, networkCallback));
        jSONObject.toString();
        int length = jSONObject.toString().getBytes().length;
        XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "len=" + length, new Object[0]);
        if (JNIRequest(id, str, jSONObject.toString().getBytes()) == 0) {
            return id;
        }
        XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIRequest fail", new Object[0]);
        CommonUtils.removeTask(Integer.valueOf(id));
        return 0;
    }

    public int requestWithKZ(String str, String str2, int i, NetworkCallback networkCallback) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " K=" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " Z=" + i, new Object[0]);
        return requestWithKZ(str, null, str2, i, null, networkCallback);
    }

    public int requestWithKZ(String str, String str2, String str3, int i, String str4, NetworkCallback networkCallback) throws JSONException {
        JSONObject jSONObject;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url=" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonHeadLines=" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " K=" + str3, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " Z=" + i, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam=" + str4, new Object[0]);
        if (str4 == null) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(str4);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
        }
        if (str2 != null) {
            try {
                jSONObject.put("headList", str2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("K", str3);
            jSONObject2.put("Z", i);
            jSONObject.put("postData", jSONObject2.toString());
            jSONObject.put("contentType", "application/x-www-form-urlencoded; charset=UTF-8");
            jSONObject.put("isKZ", "1");
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return request(str, jSONObject.toString(), networkCallback);
    }

    public int requestWithRESTful(String str, RequestMethod requestMethod, String str2, String str3, NetworkCallback networkCallback) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url=" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " method=" + requestMethod, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonHeadLines=" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " bodyData=" + str3, new Object[0]);
        return requestWithRESTful(str, requestMethod, str2, str3, null, networkCallback);
    }

    public int requestWithRESTful(String str, RequestMethod requestMethod, String str2, String str3, String str4, NetworkCallback networkCallback) throws JSONException {
        JSONObject jSONObject;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url=" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " method=" + requestMethod, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonHeadLines=" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " bodyData=" + str3, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam=" + str4, new Object[0]);
        if (str4 == null) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(str4);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
        }
        try {
            jSONObject.put("requestMethod", requestMethod.toString());
            if (str3 != null && !requestMethod.equals(RequestMethod.DELETE) && !requestMethod.equals(RequestMethod.GET)) {
                jSONObject.put("postData", str3);
            }
            if (str2 != null) {
                jSONObject.put("headList", str2);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("contentType", "application/json");
                jSONObject.put("headList", jSONObject2.toString());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return request(str, jSONObject.toString(), networkCallback);
    }

    public void setProxy(String str) {
        JNISetProxy(str);
    }

    public int setUpDownMaxThreads(int i) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " maxThreads=" + i, new Object[0]);
        return JNISetUpDownMaxThreads(i);
    }

    public int stop(int i) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " id=" + i, new Object[0]);
        JNIStop(i);
        CommonUtils.removeTask(Integer.valueOf(i));
        return 0;
    }

    public boolean testConnect(String str, int i) {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " ip=" + str + ", port=" + i, new Object[0]);
        return JNITestConnect(str, i) == 0;
    }

    public int upload(String str, String str2, String str3, NetworkCallback networkCallback) throws JSONException {
        String md5;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath:" + str2, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str3, new Object[0]);
        if (str == null) {
            str = CommonUtils.fsUploadUrl;
        }
        int id = CommonUtils.getId();
        Task task = new Task(id, str, TaskType.TASK_HTTP_UPLOAD, networkCallback);
        task.localPath = str2;
        task.isBatch = 0;
        CommonUtils.addTask(Integer.valueOf(id), task);
        if (str2 != null) {
            File file = new File(str2);
            if (!file.exists()) {
                XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!", new Object[0]);
                CommonUtils.removeTask(Integer.valueOf(id));
                return 0;
            }
            md5 = FileMD5.getMD5(file);
        } else {
            md5 = null;
        }
        if (str.contains("/file/upload")) {
            if (str.endsWith("/")) {
                str = String.valueOf(str) + md5;
            } else {
                str = String.valueOf(str) + "/" + md5;
            }
        }
        try {
            JSONObject jSONObject = str3 == null ? new JSONObject() : new JSONObject(str3);
            jSONObject.put(CommonUtils.jsonMd5Key, md5);
            if (JNIUpload(id, str, str2, jSONObject.toString()) == 0) {
                return id;
            }
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail", new Object[0]);
            CommonUtils.removeTask(Integer.valueOf(id));
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!", new Object[0]);
            return 0;
        }
    }

    public int upload(String str, String[] strArr, String str2, NetworkCallback networkCallback) throws JSONException {
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " url:" + str, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.length:" + strArr.length, new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " localPath.toString():" + strArr.toString(), new Object[0]);
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " jsonParam:" + str2, new Object[0]);
        if (str == null) {
            str = "http://files.yunzuoye.net/XHFileServer/file/zip/upload/tmp";
        }
        int id = CommonUtils.getId();
        Task task = new Task(id, str, TaskType.TASK_HTTP_UPLOAD, networkCallback);
        task.isBatch = 1;
        CommonUtils.addTask(Integer.valueOf(id), task);
        String string = null;
        if (strArr != null) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < strArr.length; i++) {
                jSONArray.put(strArr[i]);
                if (!new File(strArr[i]).exists()) {
                    XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "file is not exist!", new Object[0]);
                    CommonUtils.removeTask(Integer.valueOf(id));
                    return 0;
                }
            }
            string = jSONArray.toString();
        }
        try {
            JSONObject jSONObject = str2 == null ? new JSONObject() : new JSONObject(str2);
            jSONObject.put("isBatch", "1");
            if (JNIUpload(id, str, string, jSONObject.toString()) == 0) {
                return id;
            }
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "JNIUpload fail", new Object[0]);
            CommonUtils.removeTask(Integer.valueOf(id));
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            XHLog.e(Log.XH_NETWORK_CLIENT_TAG, "jsonParam is invalid!", new Object[0]);
            return 0;
        }
    }
}

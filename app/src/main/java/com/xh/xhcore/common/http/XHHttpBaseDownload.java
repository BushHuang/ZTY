package com.xh.xhcore.common.http;

import android.text.TextUtils;
import com.xh.networkclient.common.CommonUtils;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.HttpReq;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.HttpConst;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.download.XHDownloadOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.download.XHDownloadProxy;
import com.xh.xhcore.common.oss.OssConfig;
import com.xh.xhcore.common.upload.XHTask;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xh.xhcore.common.util.XHLog;
import com.xh.xhcore.common.util.XHNetworkUtil;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public class XHHttpBaseDownload {
    private static final String DOWNLOAD_BASE_URL = "http://filesoss.yunzuoye.net/XHFileServer/file/stream?url=";
    private static final String DOWNLOAD_FILES_BASE_URL = "http://filesoss.yunzuoye.net/XHFileServer/file/batch/raw/download/";
    private static final String TAG = "XHHttpBaseDownload";
    private static ConcurrentHashMap<XHTask, XHTask> mXHTaskConcurrentHashMap = new ConcurrentHashMap<>();

    private static boolean checkNetwork(final XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack, boolean z) {
        if (XHNetworkUtil.isNetworkAvailable(XhBaseApplication.mContext)) {
            return true;
        }
        if (xHDownloadCallBack == null) {
            return false;
        }
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    xHDownloadCallBack.failed(-108000001, XHHttpBaseReq.getErrorMsg(-108000001, "没有网络连接，请连接网络！"));
                }
            });
            return false;
        }
        xHDownloadCallBack.failed(-108000001, XHHttpBaseReq.getErrorMsg(-108000001, "没有网络连接，请连接网络！"));
        return false;
    }

    private static void downloadOnEvent(int i, final int i2, final String str, String str2, boolean z, final XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack, final String str3) {
        XHLog.i("XHHttpBaseDownload", "id=" + i + ",code=" + i2 + ",description=" + str + ",jsonParam=" + str2);
        if (xHDownloadCallBack == null) {
            return;
        }
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (i2 == CommonUtils.baseCode) {
                        xHDownloadCallBack.success(str3);
                        return;
                    }
                    XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack2 = xHDownloadCallBack;
                    int i3 = i2;
                    xHDownloadCallBack2.failed(i3, XHHttpBaseReq.getErrorMsg(i3, XHErrorCodeUtil.getErrorMsgInfo(i3, str)));
                }
            });
        } else if (i2 == CommonUtils.baseCode) {
            xHDownloadCallBack.success(str3);
        } else {
            xHDownloadCallBack.failed(i2, XHHttpBaseReq.getErrorMsg(i2, XHErrorCodeUtil.getErrorMsgInfo(i2, str)));
        }
    }

    private static void downloadOnProgress(final int i, final double d, final double d2, final String str, boolean z, final XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack) {
        if (xHDownloadCallBack == null || d == 0.0d) {
            return;
        }
        if (z) {
            XhBaseApplication.mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    xHDownloadCallBack.onProgress(i, d, d2, str);
                }
            });
        } else {
            xHDownloadCallBack.onProgress(i, d, d2, str);
        }
    }

    private static String exchangeUrl(String str) {
        String str2;
        if (str.contains("xuehaifile")) {
            XHLog.d("XHHttpBaseDownload", "是老的bucketName，可以直接下载：" + str);
        } else {
            int i = 0;
            while (true) {
                if (i >= OssConfig.bucketNames.length) {
                    str2 = "";
                    break;
                }
                if (!str.contains(OssConfig.bucketNames[i])) {
                    i++;
                } else {
                    if (String.valueOf(str.charAt(str.indexOf(OssConfig.bucketNames[i]) + OssConfig.bucketNames[i].length())).equals(".")) {
                        XHLog.d("XHHttpBaseDownload", "已经是新的url，不需要调整：" + str);
                        return str;
                    }
                    str2 = OssConfig.bucketNames[i];
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                str = str.replace(str2 + "/", "").replace("http://", "http://" + str2 + ".").replace("https://", "https://" + str2 + ".");
            }
            XHLog.d("XHHttpBaseDownload", "调整之后的url：" + str);
        }
        return str;
    }

    private static String[] exchangeUrls(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = exchangeUrl(strArr[i]);
        }
        return strArr;
    }

    public static XHTask httpDownload(String str, String str2, String str3, XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack) {
        return httpDownload(exchangeUrl(str), str2, str3, true, xHDownloadCallBack);
    }

    public static XHTask httpDownload(String str, final String str2, final String str3, final boolean z, final XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack) {
        if (XHAppConfigProxy.getInstance().getRequestStrategy() != HttpConst.RequestStrategy.STRATEGY_LIB_CURL) {
            return new XHTask(((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) XHDownloadProxy.createOkHttp().setUrl(str)).setSavePath(str2).setRequestJsonParams(str3)).setExchangeToMainThread(z)).setCallback(xHDownloadCallBack)).request()).getId());
        }
        final String strExchangeUrl = exchangeUrl(str);
        final XHTask xHTask = new XHTask();
        if (!checkNetwork(xHDownloadCallBack, z)) {
            xHTask.setSuccess(false);
            xHTask.setXhTaskId(-1);
            return xHTask;
        }
        final int iDownload = HttpReq.download(strExchangeUrl, str2, str3, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(int i, int i2, String str4, String str5) {
                XHLog.i("XHHttpBaseDownload", "id=" + i + ",code=" + i2 + ",description=" + str4 + ",jsonParam=" + str5);
                if (i2 != CommonUtils.baseCode) {
                    if (i2 == 107000509) {
                        XHHttpBaseDownload.downloadOnEvent(i, i2, "下载人数太多,排队中,请稍后", str5, z, xHDownloadCallBack, str2);
                        return;
                    }
                    XHTask xHTaskHttpDownloadAgain = XHHttpBaseDownload.httpDownloadAgain("http://filesoss.yunzuoye.net/XHFileServer/file/stream?url=" + strExchangeUrl, str2, str3, z, xHDownloadCallBack);
                    if (xHTaskHttpDownloadAgain.getXhTaskId() > 0) {
                        xHTask.setXhTaskId(i);
                        xHTaskHttpDownloadAgain.setKeyTask(xHTask);
                        XHHttpBaseDownload.mXHTaskConcurrentHashMap.put(xHTask, xHTaskHttpDownloadAgain);
                        return;
                    }
                    return;
                }
                if (!XHHttpBaseDownload.isHtmlFile(str2)) {
                    XHHttpBaseDownload.downloadOnEvent(i, i2, str4, str5, z, xHDownloadCallBack, str2);
                    return;
                }
                XHTask xHTaskHttpDownloadAgain2 = XHHttpBaseDownload.httpDownloadAgain("http://filesoss.yunzuoye.net/XHFileServer/file/stream?url=" + strExchangeUrl, str2, str3, z, xHDownloadCallBack);
                if (xHTaskHttpDownloadAgain2.getXhTaskId() > 0) {
                    xHTask.setXhTaskId(i);
                    xHTaskHttpDownloadAgain2.setKeyTask(xHTask);
                    XHHttpBaseDownload.mXHTaskConcurrentHashMap.put(xHTask, xHTaskHttpDownloadAgain2);
                }
            }

            @Override
            public void onProgress(int i, double d, double d2, String str4) {
                XHHttpBaseDownload.downloadOnProgress(i, d, d2, str4, z, xHDownloadCallBack);
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str4) {
                XHLog.i("XHHttpBaseDownload", "i=" + i + ",bytes=" + bArr.length + ",i1=" + i2 + ",s=" + str4);
                XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack2 = xHDownloadCallBack;
                if (xHDownloadCallBack2 instanceof XHRequestCallBack.XHDownloadCallBack) {
                    xHDownloadCallBack2.onRecvDate(i, bArr, i2, str4);
                }
            }
        });
        xHTask.setXhTaskId(iDownload);
        xHTask.setSuccess(true);
        if (iDownload <= 0 && xHDownloadCallBack != null) {
            xHTask.setSuccess(false);
            if (z) {
                XhBaseApplication.mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
                    }
                });
            } else {
                xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
            }
        }
        return xHTask;
    }

    private static XHTask httpDownloadAgain(String str, final String str2, String str3, final boolean z, final XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack) {
        XHLog.i("XHHttpBaseDownload", "单文件下载失败，重新下载；url = " + str);
        XHTask xHTask = new XHTask();
        if (!checkNetwork(xHDownloadCallBack, z)) {
            xHTask.setSuccess(false);
            xHTask.setXhTaskId(-1);
            return xHTask;
        }
        final int iDownload = HttpReq.download(str, str2, str3, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(int i, int i2, String str4, String str5) {
                for (XHTask xHTask2 : XHHttpBaseDownload.mXHTaskConcurrentHashMap.values()) {
                    XHLog.d("XHHttpBaseDownload", "缓存列表" + xHTask2.getXhTaskId() + ";" + xHTask2.getKeyTask().getXhTaskId());
                    if (xHTask2.getXhTaskId() == i) {
                        XHHttpBaseDownload.mXHTaskConcurrentHashMap.remove(xHTask2.getKeyTask());
                    }
                }
                XHLog.d("XHHttpBaseDownload", "缓存列表==========");
                XHHttpBaseDownload.downloadOnEvent(i, i2, str4, str5, z, xHDownloadCallBack, str2);
            }

            @Override
            public void onProgress(int i, double d, double d2, String str4) {
                XHHttpBaseDownload.downloadOnProgress(i, d, d2, str4, z, xHDownloadCallBack);
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str4) {
                XHLog.i("XHHttpBaseDownload", "i=" + i + ",bytes=" + bArr.length + ",i1=" + i2 + ",s=" + str4);
                XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack2 = xHDownloadCallBack;
                if (xHDownloadCallBack2 instanceof XHRequestCallBack.XHDownloadCallBack) {
                    xHDownloadCallBack2.onRecvDate(i, bArr, i2, str4);
                }
            }
        });
        xHTask.setXhTaskId(iDownload);
        xHTask.setSuccess(true);
        if (iDownload <= 0 && xHDownloadCallBack != null) {
            xHTask.setSuccess(false);
            if (z) {
                XhBaseApplication.mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
                    }
                });
            } else {
                xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
            }
        }
        return xHTask;
    }

    public static XHTask httpDownloadFiles(String str, String[] strArr, String str2, String str3, XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack) {
        return httpDownloadFiles(str, exchangeUrls(strArr), str2, str3, true, xHDownloadCallBack);
    }

    public static XHTask httpDownloadFiles(final String str, final String[] strArr, final String str2, final String str3, final boolean z, final XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack) {
        if (XHAppConfigProxy.getInstance().getRequestStrategy() != HttpConst.RequestStrategy.STRATEGY_LIB_CURL) {
            return new XHTask(((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) ((XHDownloadOkHttpProxy) XHDownloadProxy.createOkHttp().setBatchDownloadUrl(str).setUrls(strArr).setSavePath(str2).setRequestJsonParams(str3)).setExchangeToMainThread(z)).setCallback(xHDownloadCallBack)).request()).getId());
        }
        final XHTask xHTask = new XHTask();
        if (!checkNetwork(xHDownloadCallBack, z)) {
            xHTask.setSuccess(false);
            xHTask.setXhTaskId(-1);
            return xHTask;
        }
        final int iDownload = HttpReq.download(str, strArr, str2, str3, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(int i, int i2, String str4, String str5) {
                if (i2 == CommonUtils.baseCode) {
                    XHHttpBaseDownload.downloadOnEvent(i, i2, str4, str5, z, xHDownloadCallBack, str2);
                    return;
                }
                XHLog.i("XHHttpBaseDownload", "批量文件下载失败重试");
                XHTask xHTaskHttpDownloadFilesAgain = XHHttpBaseDownload.httpDownloadFilesAgain("http://filesoss.yunzuoye.net/XHFileServer/file/batch/raw/download/" + XHHttpBaseUpload.getAppKeyFromUrl(str), strArr, str2, str3, z, xHDownloadCallBack);
                xHTaskHttpDownloadFilesAgain.setKeyTask(xHTask);
                XHHttpBaseDownload.mXHTaskConcurrentHashMap.put(xHTask, xHTaskHttpDownloadFilesAgain);
            }

            @Override
            public void onProgress(int i, double d, double d2, String str4) {
                XHHttpBaseDownload.downloadOnProgress(i, d, d2, str4, z, xHDownloadCallBack);
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str4) {
                XHLog.i("XHHttpBaseDownload", "i=" + i + ",bytes=" + bArr.length + ",i1=" + i2 + ",s=" + str4);
                XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack2 = xHDownloadCallBack;
                if (xHDownloadCallBack2 instanceof XHRequestCallBack.XHDownloadCallBack) {
                    xHDownloadCallBack2.onRecvDate(i, bArr, i2, str4);
                }
            }
        });
        xHTask.setXhTaskId(iDownload);
        xHTask.setSuccess(true);
        if (iDownload <= 0 && xHDownloadCallBack != null) {
            xHTask.setSuccess(false);
            if (z) {
                XhBaseApplication.mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
                    }
                });
            } else {
                xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
            }
        }
        return xHTask;
    }

    private static XHTask httpDownloadFilesAgain(String str, String[] strArr, final String str2, String str3, final boolean z, final XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack) {
        XHTask xHTask = new XHTask();
        if (!checkNetwork(xHDownloadCallBack, z)) {
            xHTask.setSuccess(false);
            xHTask.setXhTaskId(-1);
            return xHTask;
        }
        final int iDownload = HttpReq.download(str, strArr, str2, str3, new HttpReq.IRequestCallBack() {
            @Override
            public void onEvent(int i, int i2, String str4, String str5) {
                for (XHTask xHTask2 : XHHttpBaseDownload.mXHTaskConcurrentHashMap.values()) {
                    if (xHTask2.getXhTaskId() == i) {
                        XHHttpBaseDownload.mXHTaskConcurrentHashMap.remove(xHTask2.getKeyTask());
                    }
                }
                XHHttpBaseDownload.downloadOnEvent(i, i2, str4, str5, z, xHDownloadCallBack, str2);
            }

            @Override
            public void onProgress(int i, double d, double d2, String str4) {
                XHHttpBaseDownload.downloadOnProgress(i, d, d2, str4, z, xHDownloadCallBack);
            }

            @Override
            public void onRecvDate(int i, byte[] bArr, int i2, String str4) {
                XHLog.i("XHHttpBaseDownload", "i=" + i + ",bytes=" + bArr.length + ",i1=" + i2 + ",s=" + str4);
                XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack2 = xHDownloadCallBack;
                if (xHDownloadCallBack2 instanceof XHRequestCallBack.XHDownloadCallBack) {
                    xHDownloadCallBack2.onRecvDate(i, bArr, i2, str4);
                }
            }
        });
        xHTask.setXhTaskId(iDownload);
        xHTask.setSuccess(true);
        if (iDownload <= 0 && xHDownloadCallBack != null) {
            xHTask.setSuccess(false);
            if (z) {
                XhBaseApplication.mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
                    }
                });
            } else {
                xHDownloadCallBack.failed(iDownload, XHHttpBaseReq.getErrorMsg(-1, "请求下发失败"));
            }
        }
        return xHTask;
    }

    private static boolean isHtmlFile(String str) throws Throwable {
        if (XHFileUtil.getFileLength(str) > 10240) {
            return false;
        }
        String file2String = XHFileUtil.readFile2String(new File(str), "UTF-8");
        if (TextUtils.isEmpty(file2String) || !file2String.toLowerCase().contains("<html")) {
            return false;
        }
        XHLog.i("XHHttpBaseDownload", "文件内容包含html，需要重新下载");
        return true;
    }

    public static int stop(XHTask xHTask) {
        if (XHAppConfigProxy.getInstance().getRequestStrategy() != HttpConst.RequestStrategy.STRATEGY_LIB_CURL) {
            LogUtils.e("OkHttp 停止下载功能需要持有请求对象进行调用");
            return 0;
        }
        if (!mXHTaskConcurrentHashMap.containsKey(xHTask)) {
            return HttpReq.stop(xHTask);
        }
        int iStop = HttpReq.stop(mXHTaskConcurrentHashMap.get(xHTask));
        mXHTaskConcurrentHashMap.remove(xHTask);
        return iStop;
    }
}

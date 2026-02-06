package com.xh.xhcore.common.http.strategy.xh.download;

import android.text.TextUtils;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.archi.HttpUtil;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.security.MultiDownloadSecurityIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

public class XHMultiDownloadUnZipListener implements HttpUtil.UnZipListener {
    private List<Integer> multiDownloadStatusCodeList = new ArrayList();
    private String[] urls;
    private XHRequestCallBack.XHDownloadCallBack xhDownloadCallBack;

    public XHMultiDownloadUnZipListener(XHRequestCallBack.XHDownloadCallBack xHDownloadCallBack, String[] strArr) {
        this.xhDownloadCallBack = xHDownloadCallBack;
        this.urls = strArr;
    }

    @Override
    public String onUnZipEntry(ZipEntry zipEntry) throws MultiDownloadSecurityIOException, NumberFormatException {
        String name = zipEntry.getName();
        if (TextUtils.isEmpty(name)) {
            throw new MultiDownloadSecurityIOException("multi download , file name is empty");
        }
        int iLastIndexOf = name.lastIndexOf("-");
        if (iLastIndexOf == -1) {
            LogUtils.e("multi download, file name don't have -, use success status code 200");
            this.multiDownloadStatusCodeList.add(200);
            return name;
        }
        if (iLastIndexOf == name.length() - 1) {
            throw new MultiDownloadSecurityIOException("multi download, - is the last char, fileName = " + name);
        }
        try {
            int i = Integer.parseInt(name.substring(iLastIndexOf + 1));
            if (i >= 0) {
                this.multiDownloadStatusCodeList.add(Integer.valueOf(i));
                return name.substring(0, iLastIndexOf);
            }
            throw new MultiDownloadSecurityIOException("multi download, status code =" + i + " , fileName = " + name);
        } catch (NumberFormatException e) {
            throw new MultiDownloadSecurityIOException("multi download , status code is not int, fileName = " + name, e);
        }
    }

    @Override
    public void onUnZipSuccess() {
        this.xhDownloadCallBack.setMultiDownloadStatusCodeListInner(this.multiDownloadStatusCodeList);
    }
}

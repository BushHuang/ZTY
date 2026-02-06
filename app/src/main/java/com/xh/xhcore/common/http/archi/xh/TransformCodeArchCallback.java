package com.xh.xhcore.common.http.archi.xh;

import android.text.TextUtils;
import com.xh.networkclient.HttpRequest;
import com.xh.xhcore.common.http.HttpReq;
import com.xh.xhcore.common.util.XHLog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class TransformCodeArchCallback implements HttpReq.IRequestCallBack {
    HttpReq.IRequestCallBack callback;
    String url;

    public TransformCodeArchCallback(String str, HttpReq.IRequestCallBack iRequestCallBack) {
        this.url = str;
        this.callback = iRequestCallBack;
    }

    private static int reconnectServer(String str, int i) {
        if (i != 107001015 && i != 107001012) {
            return i;
        }
        Matcher matcher = Pattern.compile("(http[s]?://)?([\\w\\d.]*)(:\\d+)?(?=/)").matcher(str);
        String strGroup = null;
        if (matcher.find()) {
            str = matcher.group(2);
            strGroup = matcher.group(3);
        }
        String strReplace = TextUtils.isEmpty(strGroup) ? "80" : strGroup.replace(":", "");
        String strDomainToIp = HttpRequest.getInstance().domainToIp(str);
        if (strDomainToIp == null) {
            return i;
        }
        boolean zTestConnect = HttpRequest.getInstance().testConnect(strDomainToIp, Integer.parseInt(strReplace));
        XHLog.d("HttpReq", "result = " + zTestConnect);
        return zTestConnect ? 107004000 : 107004001;
    }

    @Override
    public void onEvent(int i, int i2, String str, String str2) {
        HttpReq.IRequestCallBack iRequestCallBack = this.callback;
        if (iRequestCallBack != null) {
            iRequestCallBack.onEvent(i, reconnectServer(this.url, i2), str, str2);
        }
    }

    @Override
    public void onProgress(int i, double d, double d2, String str) {
        HttpReq.IRequestCallBack iRequestCallBack = this.callback;
        if (iRequestCallBack != null) {
            iRequestCallBack.onProgress(i, d, d2, str);
        }
    }

    @Override
    public void onRecvDate(int i, byte[] bArr, int i2, String str) {
        HttpReq.IRequestCallBack iRequestCallBack = this.callback;
        if (iRequestCallBack != null) {
            iRequestCallBack.onRecvDate(i, bArr, i2, str);
        }
    }
}

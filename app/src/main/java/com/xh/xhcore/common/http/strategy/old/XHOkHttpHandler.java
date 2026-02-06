package com.xh.xhcore.common.http.strategy.old;

import android.text.TextUtils;
import com.xh.networkclient.NetworkCallback;
import com.xh.networkclient.common.CommonUtils;
import com.xh.networkclient.common.Task;
import com.xh.networkclient.common.TaskType;
import com.xh.xhcore.common.http.archi.HttpUtil;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class XHOkHttpHandler implements IHttpHandler {
    private static void requestGet(int i, String str, NetworkCallback networkCallback) {
    }

    @Override
    public int download(int i, String str, String str2, JSONObject jSONObject) {
        NetworkCallback networkCallback = CommonUtils.getTask(Integer.valueOf(i)).callbacks;
        return 0;
    }

    @Override
    public int request(int i, String str, JSONObject jSONObject) throws JSONException {
        Task task = CommonUtils.getTask(Integer.valueOf(i));
        NetworkCallback networkCallback = task.callbacks;
        String strOptString = jSONObject.optString("requestMethod");
        if (task.type == TaskType.TASK_HTTP_REQUEST) {
            if (strOptString == null || "".equals(strOptString)) {
                requestGet(i, str, networkCallback);
            } else {
                char c = 65535;
                int iHashCode = strOptString.hashCode();
                if (iHashCode != 70454) {
                    if (iHashCode == 2461856 && strOptString.equals("POST")) {
                        c = 1;
                    }
                } else if (strOptString.equals("GET")) {
                    c = 0;
                }
                if (c == 0) {
                    requestGet(i, str, networkCallback);
                } else if (c == 1) {
                    String strOptString2 = jSONObject.optString("postData");
                    if (!TextUtils.isEmpty(strOptString2)) {
                        JSONObject jSONObject2 = HttpUtil.toJSONObject(strOptString2);
                        Iterator<String> itKeys = jSONObject2.keys();
                        while (itKeys.hasNext()) {
                            try {
                                jSONObject2.getString(itKeys.next());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int upload(int i, String str, String str2, JSONObject jSONObject) {
        NetworkCallback networkCallback = CommonUtils.getTask(Integer.valueOf(i)).callbacks;
        return 0;
    }
}

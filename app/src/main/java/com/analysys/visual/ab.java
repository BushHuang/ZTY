package com.analysys.visual;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import com.analysys.network.NetworkUtils;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AnalysysUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import org.json.JSONObject;

public class ab implements ad {
    @Override
    public void a(Object obj, OutputStream outputStream) throws IOException {
        Map map = (Map) obj;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        int i = 3;
        i = 3;
        i = 3;
        try {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("$event_id", map.get("event_id"));
                jSONObject2.put("$manufacturer", Build.MANUFACTURER);
                jSONObject2.put("$model", Build.MODEL);
                jSONObject2.put("$os_version", Build.VERSION.RELEASE);
                jSONObject2.put("$lib_version", "4.5.2");
                jSONObject2.put("$network", NetworkUtils.networkType(AnalysysUtil.getContext(), true));
                DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
                jSONObject2.put("$screen_width", displayMetrics.widthPixels);
                jSONObject2.put("$screen_height", displayMetrics.heightPixels);
                Map map2 = (Map) map.get("event_properties");
                if (map2 != null) {
                    for (String str : map2.keySet()) {
                        jSONObject2.put(str, map2.get(str));
                    }
                }
                jSONObject.put("event_info", jSONObject2);
                jSONObject.put("target_page", map.get("event_page_name"));
                jSONObject.put("type", "eventinfo_request");
                outputStreamWriter.write(jSONObject.toString());
                outputStreamWriter.flush();
                ANSLog.i("analysys.visual", "Send ws command: eventinfo_request");
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    ANSLog.e("analysys.visual", "close websocket writer fail", e);
                }
            } catch (IOException e2) {
                ANSLog.e("analysys.visual", "send event_info to server fail", e2);
                try {
                    outputStreamWriter.close();
                } catch (IOException e3) {
                    Object[] objArr = {"analysys.visual", "close websocket writer fail", e3};
                    ANSLog.e(objArr);
                    i = objArr;
                }
            } catch (Throwable th) {
                ANSLog.e(th);
                try {
                    outputStreamWriter.close();
                } catch (IOException e4) {
                    Object[] objArr2 = {"analysys.visual", "close websocket writer fail", e4};
                    ANSLog.e(objArr2);
                    i = objArr2;
                }
            }
        } catch (Throwable th2) {
            try {
                outputStreamWriter.close();
            } catch (IOException e5) {
                Object[] objArr3 = new Object[i];
                objArr3[0] = "analysys.visual";
                objArr3[1] = "close websocket writer fail";
                objArr3[2] = e5;
                ANSLog.e(objArr3);
            }
            throw th2;
        }
    }
}

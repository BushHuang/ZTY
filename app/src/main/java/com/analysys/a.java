package com.analysys;

import android.content.Context;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.Constants;
import com.analysys.utils.SharedUtil;

public class a {
    public static void a() {
        if (CommonUtils.isMainProcess(AnalysysUtil.getContext())) {
            SharedUtil.setInt(AnalysysUtil.getContext(), "page_count", 0);
            SharedUtil.setString(AnalysysUtil.getContext(), Constants.SP_REFER, "");
            SharedUtil.setBoolean(AnalysysUtil.getContext(), "sendSuccess", false);
        }
    }

    public static void a(Context context) {
        SharedUtil.remove(context, "firstStartTime");
        SharedUtil.remove(context, "$is_first_day");
        SharedUtil.remove(context, "superProperty");
        SharedUtil.remove(context, "js_superProperty");
        ab.e();
        g.a(context).d();
    }
}

package com.analysys.push;

import android.content.Context;
import com.analysys.pushs.a;

public class PushAgent {
    public void enablePush(Context context, String str, String str2) {
        a.a(context).a(str, str2);
    }

    public void trackCampaign(Context context, String str, boolean z, PushListener pushListener) {
        a.a(context).a(str, z, pushListener);
    }
}

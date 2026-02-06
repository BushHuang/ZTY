package com.xh.xhcore.common.statistic.strategy;

import android.content.Context;
import android.text.TextUtils;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.AppBury;
import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.strategy.IBuryStrategy;
import com.xh.xhcore.common.util.JsonUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016¨\u0006\u0011"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/XHBuryStrategy;", "Lcom/xh/xhcore/common/statistic/strategy/IBuryStrategy;", "Lcom/xh/xhcore/common/statistic/strategy/XHBuryConfig;", "()V", "bury", "", "buryEvent", "Lcom/xh/xhcore/common/statistic/BuryEvent;", "eventName", "", "properties", "", "", "initInner", "context", "Landroid/content/Context;", "buryConfig", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHBuryStrategy implements IBuryStrategy<XHBuryConfig> {
    @Override
    public void bury(BuryEvent buryEvent) {
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("buryEvent = ", buryEvent));
        if (buryEvent == null || TextUtils.isEmpty(buryEvent.getName())) {
            return;
        }
        AppBury.getInstance().addEvent(buryEvent);
    }

    @Override
    public void bury(String eventName, Map<String, Object> properties) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(properties, "properties");
        bury(new BuryEvent(eventName, JsonUtil.toJsonString(new JSONObject(properties))));
    }

    @Override
    public void init(Context context, IBuryConfig iBuryConfig) {
        IBuryStrategy.DefaultImpls.init(this, context, iBuryConfig);
    }

    @Override
    public void initInner(Context context, XHBuryConfig buryConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(buryConfig, "buryConfig");
    }
}

package com.xh.xhcore.common.statistic.strategy;

import android.content.Context;
import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.strategy.IBuryConfig;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H'J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bH&J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u001d\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/IBuryStrategy;", "T", "Lcom/xh/xhcore/common/statistic/strategy/IBuryConfig;", "", "bury", "", "buryEvent", "Lcom/xh/xhcore/common/statistic/BuryEvent;", "eventName", "", "properties", "", "init", "context", "Landroid/content/Context;", "buryConfig", "initInner", "(Landroid/content/Context;Lcom/xh/xhcore/common/statistic/strategy/IBuryConfig;)V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IBuryStrategy<T extends IBuryConfig> {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <T extends IBuryConfig> void init(IBuryStrategy<T> iBuryStrategy, Context context, IBuryConfig iBuryConfig) {
            Intrinsics.checkNotNullParameter(iBuryStrategy, "this");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(iBuryConfig, "buryConfig");
            if (iBuryConfig != null) {
                iBuryStrategy.initInner(context, iBuryConfig);
                return;
            }
            throw new IllegalArgumentException("Invalid type " + iBuryConfig.getClass().getName() + " passed to this buryStrategy");
        }
    }

    @Deprecated(message = "旧版本方法兼容")
    void bury(BuryEvent buryEvent);

    void bury(String eventName, Map<String, Object> properties);

    void init(Context context, IBuryConfig buryConfig);

    void initInner(Context context, T buryConfig);
}

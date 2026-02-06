package com.xh.xhcore.common.statistic.strategy.test;

import com.xh.xhcore.common.statistic.BuryEvent;
import com.xh.xhcore.common.statistic.DataCollectionUtil;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/xh/xhcore/common/statistic/strategy/test/BuryTest;", "", "()V", "testBuryNewMethod", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BuryTest {
    public static final BuryTest INSTANCE = new BuryTest();

    private BuryTest() {
    }

    @JvmStatic
    public static final void testBuryNewMethod() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("key1", new BuryEvent("eventName", "data"));
        DataCollectionUtil.setBury("testBuryNewMethod", linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("key1", "SimpleObject");
        DataCollectionUtil.setBury("testBuryNewMethod2", linkedHashMap2);
    }
}

package com.xh.xhcore.common.statistic.strategy.test;

import com.analysys.AnalysysAgent;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.statistic.BuryEvent;
import java.util.HashMap;

public class BuryTestJava {
    public static void testBuryNewMethod() {
        HashMap map = new HashMap();
        map.put("key1", new BuryEvent("eventName", "data"));
        AnalysysAgent.track(XhBaseApplication.mContext, "testBuryNewMethod3", map);
    }
}

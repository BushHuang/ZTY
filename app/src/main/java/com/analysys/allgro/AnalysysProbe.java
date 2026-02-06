package com.analysys.allgro;

import com.analysys.allgro.plugin.ASMProbeHelp;
import com.analysys.allgro.plugin.PageViewProbe;
import com.analysys.allgro.plugin.ViewClickProbe;

public class AnalysysProbe {
    public static void init() {
        ASMProbeHelp.getInstance().registerHookObserver(new PageViewProbe());
        ASMProbeHelp.getInstance().registerHookObserver(new ViewClickProbe());
    }
}

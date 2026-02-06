package com.kwai.koom.javaoom.common;

import java.util.Map;

public interface RunningInfoFetcher {
    String appVersion();

    String currentPage();

    Map<String, String> ext();

    Integer usageSeconds();
}

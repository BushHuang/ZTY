package com.analysys;

import java.util.Map;

public interface ANSAutoPageTracker {
    Map<String, Object> registerPageProperties();

    String registerPageUrl();
}

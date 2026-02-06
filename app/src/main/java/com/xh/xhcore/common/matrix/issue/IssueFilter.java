package com.xh.xhcore.common.matrix.issue;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class IssueFilter {
    private static String CURRENT_FILTER = "ISSUE_IO";
    public static final String ISSUE_IO = "ISSUE_IO";
    public static final String ISSUE_LEAK = "ISSUR_LEAK";
    public static final String ISSUE_SQLITELINT = "ISSUR_SQLITELINT";
    public static final String ISSUE_TRACE = "ISSUR_TRACE";

    @Retention(RetentionPolicy.SOURCE)
    public @interface FILTER {
    }

    public static String getCurrentFilter() {
        return "ISSUR_TRACE";
    }

    public static void setCurrentFilter(String str) {
        CURRENT_FILTER = str;
    }
}

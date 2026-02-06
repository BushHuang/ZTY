package com.xh.xhcore.common.matrix.issue;

import com.tencent.matrix.report.Issue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class IssuesMap {
    private static final ConcurrentHashMap<String, List<Issue>> issues = new ConcurrentHashMap<>();

    public static void clear() {
        issues.clear();
    }

    public static List<Issue> get(String str) {
        return issues.get(str);
    }

    public static int getCount() {
        List<Issue> list = issues.get(IssueFilter.getCurrentFilter());
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static void put(String str, Issue issue) {
        List<Issue> arrayList = issues.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(issue);
        issues.put(str, arrayList);
    }
}

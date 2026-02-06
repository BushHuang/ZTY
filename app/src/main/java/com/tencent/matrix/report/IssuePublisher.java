package com.tencent.matrix.report;

import java.util.HashSet;

public class IssuePublisher {
    private final OnIssueDetectListener mIssueListener;
    private final HashSet<String> mPublishedMap = new HashSet<>();

    public interface OnIssueDetectListener {
        void onDetectIssue(Issue issue);
    }

    public IssuePublisher(OnIssueDetectListener onIssueDetectListener) {
        this.mIssueListener = onIssueDetectListener;
    }

    protected boolean isPublished(String str) {
        if (str == null) {
            return false;
        }
        return this.mPublishedMap.contains(str);
    }

    protected void markPublished(String str) {
        if (str == null) {
            return;
        }
        this.mPublishedMap.add(str);
    }

    protected void publishIssue(Issue issue) {
        OnIssueDetectListener onIssueDetectListener = this.mIssueListener;
        if (onIssueDetectListener == null) {
            throw new RuntimeException("publish issue, but issue listener is null");
        }
        if (issue != null) {
            onIssueDetectListener.onDetectIssue(issue);
        }
    }

    protected void unMarkPublished(String str) {
        if (str == null) {
            return;
        }
        this.mPublishedMap.remove(str);
    }
}

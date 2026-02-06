package com.obs.services.model;

import java.util.concurrent.ConcurrentHashMap;

public interface UploadProgressStatus extends TaskProgressStatus {
    double getAverageSpeed();

    double getInstantaneousSpeed();

    ProgressStatus getTaskStatus(String str);

    ConcurrentHashMap<String, ProgressStatus> getTaskTable();

    long getTotalSize();

    long getTransferredSize();
}

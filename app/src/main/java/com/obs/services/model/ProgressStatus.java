package com.obs.services.model;

public interface ProgressStatus {
    double getAverageSpeed();

    double getInstantaneousSpeed();

    long getNewlyTransferredBytes();

    long getTotalBytes();

    int getTransferPercentage();

    long getTransferredBytes();
}

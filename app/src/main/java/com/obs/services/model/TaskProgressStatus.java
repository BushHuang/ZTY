package com.obs.services.model;

public interface TaskProgressStatus {
    int getExecPercentage();

    int getExecTaskNum();

    int getFailTaskNum();

    int getSucceedTaskNum();

    int getTotalTaskNum();
}

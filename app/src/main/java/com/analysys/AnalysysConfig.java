package com.analysys;

import com.analysys.utils.Constants;

public class AnalysysConfig {
    private String appKey;
    private String baseUrl;
    private String channel;
    private boolean autoProfile = true;
    private EncryptEnum encryptType = EncryptEnum.EMPTY;
    private boolean autoInstallation = false;
    private boolean calibration = Constants.isTimeCheck;
    private long diffTime = Constants.ignoreDiffTime;
    private boolean autoHeatMap = false;
    private boolean autoTrackPageView = true;
    private boolean autoTrackFragmentPageView = false;
    private boolean autoTrackClick = false;
    private boolean autoTrackDeviceId = false;

    public String getAppKey() {
        return this.appKey;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getChannel() {
        return this.channel;
    }

    public EncryptEnum getEncryptType() {
        return this.encryptType;
    }

    public long getMaxDiffTimeInterval() {
        return this.diffTime;
    }

    public boolean isAutoHeatMap() {
        return this.autoHeatMap;
    }

    public boolean isAutoInstallation() {
        return this.autoInstallation;
    }

    public boolean isAutoProfile() {
        return this.autoProfile;
    }

    public boolean isAutoTrackClick() {
        return this.autoTrackClick;
    }

    public boolean isAutoTrackCrash() {
        return af.a().b();
    }

    public boolean isAutoTrackDeviceId() {
        return this.autoTrackDeviceId;
    }

    public boolean isAutoTrackFragmentPageView() {
        return this.autoTrackFragmentPageView;
    }

    public boolean isAutoTrackPageView() {
        return this.autoTrackPageView;
    }

    public boolean isTimeCheck() {
        return this.calibration;
    }

    public void setAllowTimeCheck(boolean z) {
        this.calibration = z;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setAutoHeatMap(boolean z) {
        this.autoHeatMap = z;
    }

    public void setAutoInstallation(boolean z) {
        this.autoInstallation = z;
    }

    public void setAutoProfile(boolean z) {
        this.autoProfile = z;
    }

    public void setAutoTrackClick(boolean z) {
        this.autoTrackClick = z;
    }

    public void setAutoTrackCrash(boolean z) {
        af.a().a(z);
    }

    public void setAutoTrackDeviceId(boolean z) {
        this.autoTrackDeviceId = z;
    }

    public void setAutoTrackFragmentPageView(boolean z) {
        this.autoTrackFragmentPageView = z;
    }

    public void setAutoTrackPageView(boolean z) {
        this.autoTrackPageView = z;
    }

    @Deprecated
    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public void setEncryptType(EncryptEnum encryptEnum) {
        this.encryptType = encryptEnum;
    }

    public void setMaxDiffTimeInterval(long j) {
        this.diffTime = j * 1000;
    }
}

package com.xuehai.platform.notification;

import android.util.Pair;

public class XhNotificationConfig {
    private String packageName;
    private boolean forbidden = false;
    private Pair<Long, Boolean> clientJudge = new Pair<>(0L, false);
    private int defaults = -4;

    public XhNotificationConfig() {
    }

    public XhNotificationConfig(String str) {
        this.packageName = str;
    }

    public Pair<Long, Boolean> getClientJudge() {
        return this.clientJudge;
    }

    public int getDefaults() {
        return this.defaults;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public boolean isForbidden() {
        return this.forbidden;
    }

    public void setClientJudge(Pair<Long, Boolean> pair) {
        this.clientJudge = pair;
    }

    public void setDefaults(int i) {
        this.defaults = i;
    }

    public void setForbidden(boolean z) {
        this.forbidden = z;
    }

    public void update(XhNotificationConfig xhNotificationConfig) {
        this.forbidden = xhNotificationConfig.forbidden;
        this.defaults = xhNotificationConfig.defaults;
    }
}

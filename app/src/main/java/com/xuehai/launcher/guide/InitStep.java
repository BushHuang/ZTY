package com.xuehai.launcher.guide;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/xuehai/launcher/guide/InitStep;", "", "step", "", "desc", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getStep", "()I", "ACTIVATE_DEVICE_MANAGER", "ACTIVATE_LICENSE", "ACTIVATE_FINISH", "ZTY_DOWNLOADING", "ZTY_INSTALLING", "ZTY_FINISH", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum InitStep {
    ACTIVATE_DEVICE_MANAGER(0, "设备激活"),
    ACTIVATE_LICENSE(1, "证书激活"),
    ACTIVATE_FINISH(2, "完成"),
    ZTY_DOWNLOADING(3, "下载智通云"),
    ZTY_INSTALLING(4, "安装智通云"),
    ZTY_FINISH(5, "完成");

    private final String desc;
    private final int step;

    InitStep(int i, String str) {
        this.step = i;
        this.desc = str;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final int getStep() {
        return this.step;
    }
}

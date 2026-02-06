package com.xh.xhcore.common.statistic.connect.times.download;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/statistic/connect/times/download/DownloadTimesModel;", "", "downloadTimes", "", "downloadType", "(II)V", "getDownloadTimes", "()I", "getDownloadType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadTimesModel {
    private final int downloadTimes;
    private final int downloadType;

    public DownloadTimesModel(int i, int i2) {
        this.downloadTimes = i;
        this.downloadType = i2;
    }

    public static DownloadTimesModel copy$default(DownloadTimesModel downloadTimesModel, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = downloadTimesModel.downloadTimes;
        }
        if ((i3 & 2) != 0) {
            i2 = downloadTimesModel.downloadType;
        }
        return downloadTimesModel.copy(i, i2);
    }

    public final int getDownloadTimes() {
        return this.downloadTimes;
    }

    public final int getDownloadType() {
        return this.downloadType;
    }

    public final DownloadTimesModel copy(int downloadTimes, int downloadType) {
        return new DownloadTimesModel(downloadTimes, downloadType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadTimesModel)) {
            return false;
        }
        DownloadTimesModel downloadTimesModel = (DownloadTimesModel) other;
        return this.downloadTimes == downloadTimesModel.downloadTimes && this.downloadType == downloadTimesModel.downloadType;
    }

    public final int getDownloadTimes() {
        return this.downloadTimes;
    }

    public final int getDownloadType() {
        return this.downloadType;
    }

    public int hashCode() {
        return (this.downloadTimes * 31) + this.downloadType;
    }

    public String toString() {
        return "DownloadTimesModel(downloadTimes=" + this.downloadTimes + ", downloadType=" + this.downloadType + ')';
    }
}

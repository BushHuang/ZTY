package com.zaze.utils.date;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001e"}, d2 = {"Lcom/zaze/utils/date/Month;", "", "cn", "", "en", "number", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;I)V", "getCn", "()Ljava/lang/String;", "setCn", "(Ljava/lang/String;)V", "getEn", "setEn", "getNumber", "()I", "setNumber", "(I)V", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER", "util_release"}, k = 1, mv = {1, 4, 1})
public enum Month {
    JANUARY("一月", "January", 1),
    FEBRUARY("二月", "February", 2),
    MARCH("三月", "March", 3),
    APRIL("四月", "April", 4),
    MAY("五月", "May", 5),
    JUNE("六月", "June", 6),
    JULY("七月", "July", 7),
    AUGUST("八月", "AUGUST", 8),
    SEPTEMBER("九月", "September", 9),
    OCTOBER("十月", "October", 10),
    NOVEMBER("十一月", "November", 11),
    DECEMBER("十二月", "December", 12);

    private String cn;
    private String en;
    private int number;

    Month(String str, String str2, int i) {
        this.cn = str;
        this.en = str2;
        this.number = i;
    }

    public final String getCn() {
        return this.cn;
    }

    public final String getEn() {
        return this.en;
    }

    public final int getNumber() {
        return this.number;
    }

    public final void setCn(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cn = str;
    }

    public final void setEn(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.en = str;
    }

    public final void setNumber(int i) {
        this.number = i;
    }
}

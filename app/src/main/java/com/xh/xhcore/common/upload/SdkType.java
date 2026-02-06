package com.xh.xhcore.common.upload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface SdkType {
    public static final int OK_HTTP = 1;
    public static final int XUE_HAI = 0;
}

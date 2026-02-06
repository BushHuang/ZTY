package com.xh.xhcore.common.statistic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;

@Retention(RetentionPolicy.SOURCE)
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/xh/xhcore/common/statistic/LogType;", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
public @interface LogType {
    public static final String APM = "apm";
    public static final String APP_UPLOAD = "appUpload";
    public static final String CRASH = "crash";

    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final String NETWORK_ERROR = "networkError";

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/xh/xhcore/common/statistic/LogType$Companion;", "", "()V", "APM", "", "APP_UPLOAD", "CRASH", "NETWORK_ERROR", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();
        public static final String APM = "apm";
        public static final String APP_UPLOAD = "appUpload";
        public static final String CRASH = "crash";
        public static final String NETWORK_ERROR = "networkError";

        private Companion() {
        }
    }
}

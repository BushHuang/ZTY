package com.xh.xhcore.common.http.dns;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;

@Retention(RetentionPolicy.SOURCE)
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSBuryType;", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
public @interface DNSBuryType {

    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int LOCALDNS_FAIL = 1;
    public static final int LOCALDNS_FAIL_AND_HTTPDNS_FAIL_FUNCTION_FAIL = 2;
    public static final int LOCALDNS_FAIL_AND_HTTPDNS_FAIL_NETWORK_FAIL = 4;
    public static final int RETRY_HTTPDNS = 5;
    public static final int RETRY_HTTPDNS_AND_HTTPDNS_FAIL_FUNCTION_FAIL = 3;
    public static final int RETRY_HTTPDNS_AND_HTTPDNS_FAIL_NETWORK_FAIL = 7;
    public static final int RETRY_HTTPDNS_IPS_NOT_EQUAL_TO_LOCALDNS = 8;
    public static final int RETRY_HTTPDNS_SUCCESS = 6;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xh/xhcore/common/http/dns/DNSBuryType$Companion;", "", "()V", "LOCALDNS_FAIL", "", "LOCALDNS_FAIL_AND_HTTPDNS_FAIL_FUNCTION_FAIL", "LOCALDNS_FAIL_AND_HTTPDNS_FAIL_NETWORK_FAIL", "RETRY_HTTPDNS", "RETRY_HTTPDNS_AND_HTTPDNS_FAIL_FUNCTION_FAIL", "RETRY_HTTPDNS_AND_HTTPDNS_FAIL_NETWORK_FAIL", "RETRY_HTTPDNS_IPS_NOT_EQUAL_TO_LOCALDNS", "RETRY_HTTPDNS_SUCCESS", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();
        public static final int LOCALDNS_FAIL = 1;
        public static final int LOCALDNS_FAIL_AND_HTTPDNS_FAIL_FUNCTION_FAIL = 2;
        public static final int LOCALDNS_FAIL_AND_HTTPDNS_FAIL_NETWORK_FAIL = 4;
        public static final int RETRY_HTTPDNS = 5;
        public static final int RETRY_HTTPDNS_AND_HTTPDNS_FAIL_FUNCTION_FAIL = 3;
        public static final int RETRY_HTTPDNS_AND_HTTPDNS_FAIL_NETWORK_FAIL = 7;
        public static final int RETRY_HTTPDNS_IPS_NOT_EQUAL_TO_LOCALDNS = 8;
        public static final int RETRY_HTTPDNS_SUCCESS = 6;

        private Companion() {
        }
    }
}

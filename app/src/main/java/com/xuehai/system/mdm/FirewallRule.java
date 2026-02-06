package com.xuehai.system.mdm;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/mdm/FirewallRule;", "", "builder", "Lcom/xuehai/system/mdm/FirewallRule$Builder;", "(Lcom/xuehai/system/mdm/FirewallRule$Builder;)V", "application", "", "ipAddress", "portNumber", "rules", "Builder", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FirewallRule {
    private final String application;
    private final String ipAddress;
    private final String portNumber;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/mdm/FirewallRule$Builder;", "", "()V", "application", "", "getApplication$aidl_release", "()Ljava/lang/String;", "setApplication$aidl_release", "(Ljava/lang/String;)V", "ipAddress", "getIpAddress$aidl_release", "setIpAddress$aidl_release", "portNumber", "getPortNumber$aidl_release", "setPortNumber$aidl_release", "build", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private String ipAddress = "*";
        private String portNumber = "*";
        private String application = "*";

        public final Builder application(String application) {
            Intrinsics.checkNotNullParameter(application, "application");
            this.application = application;
            return this;
        }

        public final String build() {
            return new FirewallRule(this, null).rules();
        }

        public final String getApplication() {
            return this.application;
        }

        public final String getIpAddress() {
            return this.ipAddress;
        }

        public final String getPortNumber() {
            return this.portNumber;
        }

        public final Builder ipAddress(String ipAddress) {
            Intrinsics.checkNotNullParameter(ipAddress, "ipAddress");
            this.ipAddress = ipAddress;
            return this;
        }

        public final Builder portNumber(String portNumber) {
            Intrinsics.checkNotNullParameter(portNumber, "portNumber");
            this.portNumber = portNumber;
            return this;
        }

        public final void setApplication$aidl_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.application = str;
        }

        public final void setIpAddress$aidl_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.ipAddress = str;
        }

        public final void setPortNumber$aidl_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.portNumber = str;
        }
    }

    private FirewallRule(Builder builder) {
        this.ipAddress = builder.getIpAddress();
        this.portNumber = builder.getPortNumber();
        this.application = builder.getApplication();
    }

    public FirewallRule(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public final String rules() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%s:%s;*;%s;*", Arrays.copyOf(new Object[]{this.ipAddress, this.portNumber, this.application}, 3));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }
}

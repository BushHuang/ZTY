package com.xh.xhcore.common.util;

import com.xh.xhcore.common.util.DNSLookUpUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xh/xhcore/common/util/DNSLookUpUtil;", "", "()V", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DNSLookUpUtil {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/util/DNSLookUpUtil$Companion;", "", "()V", "checkIp", "", "ip", "", "loadLocalDNS", "", "Ljava/net/InetAddress;", "hostname", "timeout", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static List loadLocalDNS$default(Companion companion, String str, long j, int i, Object obj) {
            if ((i & 2) != 0) {
                j = 10;
            }
            return companion.loadLocalDNS(str, j);
        }

        private static final List m40loadLocalDNS$lambda0(String str) throws UnknownHostException {
            Intrinsics.checkNotNullParameter(str, "$hostname");
            InetAddress[] allByName = InetAddress.getAllByName(str);
            Intrinsics.checkNotNullExpressionValue(allByName, "getAllByName(hostname)");
            return CollectionsKt.distinct(ArraysKt.toList(allByName));
        }

        @JvmStatic
        public final boolean checkIp(String ip) {
            Intrinsics.checkNotNullParameter(ip, "ip");
            if (XHStringUtil.isEmpty(ip)) {
                return true;
            }
            return Util.verifyAsIpAddress(ip);
        }

        @JvmStatic
        public final List<InetAddress> loadLocalDNS(String str) {
            Intrinsics.checkNotNullParameter(str, "hostname");
            return loadLocalDNS$default(this, str, 0L, 2, null);
        }

        @JvmStatic
        public final List<InetAddress> loadLocalDNS(final String hostname, long timeout) throws ExecutionException, InterruptedException, TimeoutException {
            Intrinsics.checkNotNullParameter(hostname, "hostname");
            try {
                FutureTask futureTask = new FutureTask(new Callable() {
                    @Override
                    public final Object call() {
                        return DNSLookUpUtil.Companion.m40loadLocalDNS$lambda0(hostname);
                    }
                });
                new Thread(futureTask).start();
                Object obj = futureTask.get(timeout, TimeUnit.SECONDS);
                Intrinsics.checkNotNullExpressionValue(obj, "task.get(timeout, TimeUnit.SECONDS)");
                return (List) obj;
            } catch (Exception unused) {
                return CollectionsKt.emptyList();
            }
        }
    }

    @JvmStatic
    public static final boolean checkIp(String str) {
        return INSTANCE.checkIp(str);
    }

    @JvmStatic
    public static final List<InetAddress> loadLocalDNS(String str) {
        return INSTANCE.loadLocalDNS(str);
    }

    @JvmStatic
    public static final List<InetAddress> loadLocalDNS(String str, long j) {
        return INSTANCE.loadLocalDNS(str, j);
    }
}

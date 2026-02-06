package com.xh.xhcore.jni;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0086 J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0086 ¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0086 ¢\u0006\u0002\u0010\u0007J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0086 ¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/jni/XHCoreJni;", "", "()V", "getSignatureKey", "", "getSignatureKeyV2", "", "()[Ljava/lang/String;", "getStaticSignatureKey", "isStaticKey", "", "version", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHCoreJni {

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<XHCoreJni> instance$delegate;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/jni/XHCoreJni$Companion;", "", "()V", "instance", "Lcom/xh/xhcore/jni/XHCoreJni;", "getInstance$annotations", "getInstance", "()Lcom/xh/xhcore/jni/XHCoreJni;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final XHCoreJni getInstance() {
            return (XHCoreJni) XHCoreJni.instance$delegate.getValue();
        }
    }

    static {
        System.loadLibrary("xhcore");
        instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<XHCoreJni>() {
            @Override
            public final XHCoreJni invoke() {
                return new XHCoreJni();
            }
        });
    }

    public static final XHCoreJni getInstance() {
        return INSTANCE.getInstance();
    }

    public final native String getSignatureKey();

    public final native String[] getSignatureKeyV2();

    public final native String[] getStaticSignatureKey();

    public final native boolean isStaticKey(String version);
}

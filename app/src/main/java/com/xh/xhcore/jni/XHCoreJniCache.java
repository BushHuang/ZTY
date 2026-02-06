package com.xh.xhcore.jni;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0007¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR'\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\n\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcom/xh/xhcore/jni/XHCoreJniCache;", "", "()V", "TAG", "", "xhSignatureKey", "getXhSignatureKey$annotations", "getXhSignatureKey", "()Ljava/lang/String;", "xhSignatureKey$delegate", "Lkotlin/Lazy;", "xhStaticSignatureKey", "", "getXhStaticSignatureKey$annotations", "getXhStaticSignatureKey", "()[Ljava/lang/String;", "xhStaticSignatureKey$delegate", "getSignKey", "isStaticKey", "", "kv", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHCoreJniCache {
    private static final String TAG = "XHCoreJniCache";
    public static final XHCoreJniCache INSTANCE = new XHCoreJniCache();

    private static final Lazy xhSignatureKey = LazyKt.lazy(new Function0<String>() {
        @Override
        public final String invoke() {
            return XHCoreJni.INSTANCE.getInstance().getSignatureKey();
        }
    });

    private static final Lazy xhStaticSignatureKey = LazyKt.lazy(new Function0<String[]>() {
        @Override
        public final String[] invoke() {
            Object objM228constructorimpl;
            try {
                Result.Companion companion = Result.Companion;
                String[] staticSignatureKey = XHCoreJni.INSTANCE.getInstance().getStaticSignatureKey();
                if (staticSignatureKey.length < 2) {
                    LogUtils.Companion companion2 = LogUtils.INSTANCE;
                    String string = Arrays.toString(staticSignatureKey);
                    Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
                    LogUtils.Companion.i$default(companion2, "XHCoreJniCache", Intrinsics.stringPlus("xhStaticSignatureKey--->signatureKey = ", string), null, 4, null);
                    staticSignatureKey = new String[]{"-1", "-1"};
                }
                objM228constructorimpl = Result.m228constructorimpl(staticSignatureKey);
            } catch (Throwable th) {
                Result.Companion companion3 = Result.Companion;
                objM228constructorimpl = Result.m228constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m231exceptionOrNullimpl(objM228constructorimpl) != null) {
                objM228constructorimpl = new String[]{"-1", "-1"};
            }
            return (String[]) objM228constructorimpl;
        }
    });

    private XHCoreJniCache() {
    }

    @JvmStatic
    public static final String[] getSignKey() {
        try {
            String[] signatureKeyV2 = XHCoreJni.INSTANCE.getInstance().getSignatureKeyV2();
            if (signatureKeyV2.length >= 2) {
                return signatureKeyV2;
            }
            LogUtils.Companion companion = LogUtils.INSTANCE;
            String string = Arrays.toString(signatureKeyV2);
            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
            LogUtils.Companion.i$default(companion, "XHCoreJniCache", Intrinsics.stringPlus("getSignKey--->key = ", string), null, 4, null);
            return new String[]{"-1", "-1"};
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"-1", "-1"};
        }
    }

    public static final String getXhSignatureKey() {
        return (String) xhSignatureKey.getValue();
    }

    @JvmStatic
    public static void getXhSignatureKey$annotations() {
    }

    public static final String[] getXhStaticSignatureKey() {
        return (String[]) xhStaticSignatureKey.getValue();
    }

    @JvmStatic
    public static void getXhStaticSignatureKey$annotations() {
    }

    @JvmStatic
    public static final boolean isStaticKey(String kv) {
        Intrinsics.checkNotNullParameter(kv, "kv");
        try {
            return XHCoreJni.INSTANCE.getInstance().isStaticKey(kv);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.xuehai.launcher.other;

import androidx.lifecycle.ViewModelKt;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.base.AbsViewModel;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.ext.SingleLiveEvent;
import com.xuehai.launcher.common.http.LResponse;
import com.xuehai.launcher.common.http.NetStore;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.network.NetworkUtil;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.KeepCode;
import com.xuehai.launcher.common.util.StringEncryptUtils;
import com.xuehai.launcher.common.util.TotpUtils;
import com.xuehai.launcher.natives.SecretJni;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.codec.binary.Base32;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\tH\u0002J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\tH\u0002J\u0006\u0010\"\u001a\u00020\u001dJ\u0006\u0010#\u001a\u00020\u001dJ\u000e\u0010$\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/xuehai/launcher/other/InputViewModel;", "Lcom/xuehai/launcher/common/base/AbsViewModel;", "()V", "closeDialogEvent", "Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "Ljava/lang/Void;", "getCloseDialogEvent", "()Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "curSecretKey", "", "hasHistorySecretKey", "", "inputMode", "Lcom/xuehai/launcher/other/InputMode;", "secretKeyLocalSpecial", "Lcom/xuehai/launcher/common/util/KeepCode;", "getSecretKeyLocalSpecial", "()Lcom/xuehai/launcher/common/util/KeepCode;", "secretKeyLocalSpecial$delegate", "Lkotlin/Lazy;", "showDeviceResetView", "getShowDeviceResetView", "showLocalKey", "getShowLocalKey", "showRedPoint", "getShowRedPoint", "updateCheckModeTask", "Ljava/lang/Runnable;", "a", "", "b", "checkOffline", "code", "checkOnline", "load", "onCancel", "onSure", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InputViewModel extends AbsViewModel {
    private String curSecretKey;
    private boolean hasHistorySecretKey;
    private final InputMode inputMode = new InputMode();
    private final SingleLiveEvent<Boolean> showRedPoint = new SingleLiveEvent<>();
    private final SingleLiveEvent<String> showLocalKey = new SingleLiveEvent<>();
    private final SingleLiveEvent<Void> showDeviceResetView = new SingleLiveEvent<>();

    private final Lazy secretKeyLocalSpecial = LazyKt.lazy(new Function0<KeepCode>() {
        @Override
        public final KeepCode invoke() {
            return new KeepCode(FilePath.getKeepDir() + "/device", "ls");
        }
    });
    private final SingleLiveEvent<Void> closeDialogEvent = new SingleLiveEvent<>();
    private final Runnable updateCheckModeTask = new Runnable() {
        @Override
        public final void run() {
            InputViewModel.m161updateCheckModeTask$lambda0(this.f$0);
        }
    };

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.xuehai.launcher.other.InputViewModel$onSure$1", f = "InputViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final String $code;
        int label;
        final InputViewModel this$0;

        AnonymousClass1(String str, InputViewModel inputViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$code = str;
            this.this$0 = inputViewModel;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$code, this.this$0, continuation);
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MyLog.i("[MDM]", "验证动态码: " + this.$code);
            if (this.$code.length() == 0) {
                if (App.INSTANCE.getInstance().isDebug()) {
                    MyLog.i("Debug[MDM]", "当前为测试版本，输入空直接跳转解锁！");
                    LiveDataExtKt.action(this.this$0.getShowDeviceResetView());
                } else {
                    this.this$0.toastMessage("输入内容不可为空！");
                }
                return Unit.INSTANCE;
            }
            if (NetworkUtil.INSTANCE.getNetworkType(App.INSTANCE.getInstance()).getIsAvailable() ? this.this$0.checkOnline(this.$code) : this.this$0.checkOffline(this.$code)) {
                LiveDataExtKt.action(this.this$0.getShowDeviceResetView());
            } else {
                this.this$0.toastMessage("动态码错误！");
            }
            LiveDataExtKt.action(this.this$0.getCloseDialogEvent());
            return Unit.INSTANCE;
        }
    }

    private final boolean checkOffline(String code) {
        String strReplace$default;
        int i;
        String str = this.curSecretKey;
        int i2 = 6;
        if (this.inputMode.isDefault()) {
            if (StringsKt.startsWith$default(code, "@", false, 2, (Object) null) && StringsKt.endsWith$default(code, "@", false, 2, (Object) null)) {
                i = 3600;
                strReplace$default = StringsKt.replace$default(code, "@", "", false, 4, (Object) null);
            } else {
                i = 60;
                strReplace$default = code;
            }
        } else if (this.inputMode.isDev() && !this.hasHistorySecretKey) {
            Base32 base32 = new Base32();
            if (str == null) {
                str = "1";
            }
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] bArrEncode = base32.encode(bytes);
            Intrinsics.checkNotNullExpressionValue(bArrEncode, "Base32().encode((secretKey ?: \"1\").toByteArray())");
            i2 = 8;
            strReplace$default = code;
            str = new String(bArrEncode, Charsets.UTF_8);
            i = 900;
        }
        SecretJni.getInstance().verifyDevelopToken(BaseApplication.INSTANCE.getInstance(), code, null);
        try {
            return TotpUtils.verify(str, strReplace$default, i, i2);
        } catch (Exception unused) {
            return false;
        }
    }

    private final boolean checkOnline(String code) throws JSONException {
        LResponse lResponseCheckBrushCode = NetStore.INSTANCE.checkBrushCode(code);
        if (!lResponseCheckBrushCode.isSuccessful()) {
            return false;
        }
        try {
            String strDecrypt = StringEncryptUtils.decrypt(new JSONObject(lResponseCheckBrushCode.getResponseBody()).optString("status"));
            MyLog.i("[MDM]", "checkBrushCode status: " + strDecrypt);
            Intrinsics.checkNotNullExpressionValue(strDecrypt, "status");
            return Integer.parseInt(strDecrypt) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    private final KeepCode getSecretKeyLocalSpecial() {
        return (KeepCode) this.secretKeyLocalSpecial.getValue();
    }

    private static final void m161updateCheckModeTask$lambda0(InputViewModel inputViewModel) {
        Intrinsics.checkNotNullParameter(inputViewModel, "this$0");
        if (inputViewModel.inputMode.isDefault()) {
            LiveDataExtKt.set(inputViewModel.showRedPoint, true);
            LiveDataExtKt.set(inputViewModel.showLocalKey, null);
        } else if (!inputViewModel.inputMode.isDev() || inputViewModel.hasHistorySecretKey) {
            LiveDataExtKt.set(inputViewModel.showRedPoint, false);
            LiveDataExtKt.set(inputViewModel.showLocalKey, null);
        } else {
            LiveDataExtKt.set(inputViewModel.showRedPoint, false);
            String strCreateDeviceRandomText = TotpUtils.createDeviceRandomText(6);
            inputViewModel.curSecretKey = strCreateDeviceRandomText;
            LiveDataExtKt.set(inputViewModel.showLocalKey, strCreateDeviceRandomText);
        }
    }

    public final void a() {
        if (NetworkUtil.INSTANCE.getNetworkType(App.INSTANCE.getInstance()).getIsAvailable()) {
            this.inputMode.clear();
        } else {
            this.inputMode.a();
            ThreadPlugins.runInWorkThread(this.updateCheckModeTask, 1000L);
        }
    }

    public final void b() {
        if (NetworkUtil.INSTANCE.getNetworkType(App.INSTANCE.getInstance()).getIsAvailable()) {
            this.inputMode.clear();
        } else {
            this.inputMode.b();
            ThreadPlugins.runInWorkThread(this.updateCheckModeTask, 1000L);
        }
    }

    public final SingleLiveEvent<Void> getCloseDialogEvent() {
        return this.closeDialogEvent;
    }

    public final SingleLiveEvent<Void> getShowDeviceResetView() {
        return this.showDeviceResetView;
    }

    public final SingleLiveEvent<String> getShowLocalKey() {
        return this.showLocalKey;
    }

    public final SingleLiveEvent<Boolean> getShowRedPoint() {
        return this.showRedPoint;
    }

    public final void load() {
        String code;
        try {
            code = getSecretKeyLocalSpecial().getCode();
        } catch (Throwable unused) {
            code = (String) null;
        }
        this.curSecretKey = code;
        String str = code;
        this.hasHistorySecretKey = !(str == null || str.length() == 0);
    }

    public final void onCancel() {
        this.inputMode.clear();
        LiveDataExtKt.set(this.showRedPoint, false);
        LiveDataExtKt.set(this.showLocalKey, null);
        LiveDataExtKt.action(this.closeDialogEvent);
    }

    public final void onSure(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), ThreadPlugins.INSTANCE.getFileExecutorStub().getCoroutineDispatcher(), null, new AnonymousClass1(code, this, null), 2, null);
    }
}

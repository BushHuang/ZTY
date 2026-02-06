package com.xuehai.launcher.device;

import android.graphics.Bitmap;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.xh.view.util.DisplayUtil;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.base.AbsViewModel;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.network.NetworkUtil;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.util.QrUtil;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0006\u0010\u0011\u001a\u00020\u0012R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/device/DeviceInfoViewModel;", "Lcom/xuehai/launcher/common/base/AbsViewModel;", "()V", "deviceIdMsg", "Landroidx/lifecycle/MutableLiveData;", "", "getDeviceIdMsg", "()Landroidx/lifecycle/MutableLiveData;", "deviceIdQrBmp", "Landroid/graphics/Bitmap;", "getDeviceIdQrBmp", "macAddressMsg", "getMacAddressMsg", "macAddressQrBmp", "getMacAddressQrBmp", "createBitmapByString", "string", "loadDeviceInfo", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceInfoViewModel extends AbsViewModel {
    private final MutableLiveData<String> deviceIdMsg = new MutableLiveData<>();
    private final MutableLiveData<Bitmap> deviceIdQrBmp = new MutableLiveData<>();
    private final MutableLiveData<String> macAddressMsg = new MutableLiveData<>();
    private final MutableLiveData<Bitmap> macAddressQrBmp = new MutableLiveData<>();

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.xuehai.launcher.device.DeviceInfoViewModel$loadDeviceInfo$1", f = "DeviceInfoViewModel.kt", i = {0}, l = {38, 41}, m = "invokeSuspend", n = {"mac"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.xuehai.launcher.device.DeviceInfoViewModel$loadDeviceInfo$1$1", f = "DeviceInfoViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
            final String $deviceId;
            int label;
            final DeviceInfoViewModel this$0;

            C00231(DeviceInfoViewModel deviceInfoViewModel, String str, Continuation<? super C00231> continuation) {
                super(2, continuation);
                this.this$0 = deviceInfoViewModel;
                this.$deviceId = str;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00231(this.this$0, this.$deviceId, continuation);
            }

            @Override
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
                return ((C00231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return this.this$0.createBitmapByString(this.$deviceId);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.xuehai.launcher.device.DeviceInfoViewModel$loadDeviceInfo$1$2", f = "DeviceInfoViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
            final String $mac;
            int label;
            final DeviceInfoViewModel this$0;

            AnonymousClass2(DeviceInfoViewModel deviceInfoViewModel, String str, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = deviceInfoViewModel;
                this.$mac = str;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, this.$mac, continuation);
            }

            @Override
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return this.this$0.createBitmapByString(this.$mac);
            }
        }

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceInfoViewModel.this.new AnonymousClass1(continuation);
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            String str;
            MutableLiveData<Bitmap> mutableLiveData;
            MutableLiveData<Bitmap> mutableLiveData2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String deviceId = App.INSTANCE.getInstance().getDeviceId();
                LiveDataExtKt.set(DeviceInfoViewModel.this.getDeviceIdMsg(), "设备号: " + deviceId);
                String macAddress = NetworkUtil.INSTANCE.getMacAddress();
                MutableLiveData<String> macAddressMsg = DeviceInfoViewModel.this.getMacAddressMsg();
                StringBuilder sb = new StringBuilder();
                sb.append("MAC地址: ");
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                String upperCase = macAddress.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
                sb.append(upperCase);
                LiveDataExtKt.set(macAddressMsg, sb.toString());
                MutableLiveData<Bitmap> deviceIdQrBmp = DeviceInfoViewModel.this.getDeviceIdQrBmp();
                this.L$0 = macAddress;
                this.L$1 = deviceIdQrBmp;
                this.label = 1;
                obj = BuildersKt.withContext(ThreadPlugins.INSTANCE.getMultiDispatcher(), new C00231(DeviceInfoViewModel.this, deviceId, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = macAddress;
                mutableLiveData = deviceIdQrBmp;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    mutableLiveData2 = (MutableLiveData) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    LiveDataExtKt.set(mutableLiveData2, obj);
                    return Unit.INSTANCE;
                }
                mutableLiveData = (MutableLiveData) this.L$1;
                str = (String) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            LiveDataExtKt.set(mutableLiveData, obj);
            MutableLiveData<Bitmap> macAddressQrBmp = DeviceInfoViewModel.this.getMacAddressQrBmp();
            this.L$0 = macAddressQrBmp;
            this.L$1 = null;
            this.label = 2;
            Object objWithContext = BuildersKt.withContext(ThreadPlugins.INSTANCE.getMultiDispatcher(), new AnonymousClass2(DeviceInfoViewModel.this, str, null), this);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutableLiveData2 = macAddressQrBmp;
            obj = objWithContext;
            LiveDataExtKt.set(mutableLiveData2, obj);
            return Unit.INSTANCE;
        }
    }

    private final Bitmap createBitmapByString(String string) {
        int iDp2px = DisplayUtil.dp2px(BaseApplication.INSTANCE.getInstance(), 320.0f);
        return QrUtil.createBitmap(string, iDp2px, iDp2px);
    }

    public final MutableLiveData<String> getDeviceIdMsg() {
        return this.deviceIdMsg;
    }

    public final MutableLiveData<Bitmap> getDeviceIdQrBmp() {
        return this.deviceIdQrBmp;
    }

    public final MutableLiveData<String> getMacAddressMsg() {
        return this.macAddressMsg;
    }

    public final MutableLiveData<Bitmap> getMacAddressQrBmp() {
        return this.macAddressQrBmp;
    }

    public final void loadDeviceInfo() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }
}

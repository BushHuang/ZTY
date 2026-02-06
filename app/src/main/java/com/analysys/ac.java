package com.analysys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.text.TextUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;

public class ac {

    static final class a implements ServiceConnection {
        private a() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Context context = AnalysysUtil.getContext();
            try {
                if (context == null) {
                    return;
                }
                try {
                    String strA = new b(iBinder).a();
                    if (!TextUtils.isEmpty(strA)) {
                        ab.c(strA);
                    }
                    context.unbindService(this);
                } catch (Throwable th) {
                    try {
                        ExceptionUtil.exceptionThrow(th);
                        context.unbindService(this);
                    } catch (Throwable th2) {
                        try {
                            context.unbindService(this);
                        } catch (Throwable th3) {
                            ExceptionUtil.exceptionThrow(th3);
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th4) {
                ExceptionUtil.exceptionThrow(th4);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    static final class b implements IInterface {

        private IBinder f11a;

        public b(IBinder iBinder) {
            this.f11a = iBinder;
        }

        public String a() {
            Parcel parcelObtain;
            Parcel parcelObtain2;
            String string = null;
            try {
                try {
                    parcelObtain = Parcel.obtain();
                    try {
                        parcelObtain2 = Parcel.obtain();
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2 = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain = null;
                    parcelObtain2 = null;
                }
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    this.f11a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    string = parcelObtain2.readString();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        ExceptionUtil.exceptionThrow(th);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return string;
                    } catch (Throwable th4) {
                        try {
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } catch (Throwable th5) {
                            ExceptionUtil.exceptionThrow(th5);
                        }
                        throw th4;
                    }
                }
            } catch (Throwable th6) {
                ExceptionUtil.exceptionThrow(th6);
            }
            return string;
        }

        @Override
        public IBinder asBinder() {
            return this.f11a;
        }
    }

    public static void a() {
        Context context = AnalysysUtil.getContext();
        if (CommonUtils.isMainProcess(context) && TextUtils.isEmpty(ab.f())) {
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                a aVar = new a();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                try {
                    context.bindService(intent, aVar, 1);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            } catch (Throwable unused) {
            }
        }
    }
}

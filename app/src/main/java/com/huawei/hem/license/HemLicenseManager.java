package com.huawei.hem.license;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.hem.b;
import com.huawei.hms.hem.c;
import com.huawei.hms.hem.d;
import com.huawei.hms.hem.e;
import com.huawei.hms.hem.f;
import org.json.JSONObject;

public class HemLicenseManager extends HuaweiApi<c> {

    private static final byte[] f179a = new byte[0];
    private static final Api<c> b = new Api<>("HuaweiHem.API");
    private static final b c = new b();
    private static HemLicenseManager d;
    private Context e;
    private HemLicenseStatusListener f;

    class a extends BroadcastReceiver {
        private a() {
        }

        a(HemLicenseManager hemLicenseManager, byte b) {
            this();
        }

        @Override
        public final void onReceive(Context context, Intent intent) {
            if (intent != null) {
                try {
                    int intExtra = intent.getIntExtra("licenseOperationResult", 1000);
                    if (HemLicenseManager.this.f != null) {
                        HemLicenseManager.this.f.onStatus(intExtra, e.a(intExtra));
                    }
                } catch (Throwable th) {
                    f.a("HemLicenseManager", "rec: " + th.getClass().getSimpleName());
                }
            }
        }
    }

    private HemLicenseManager(Context context) {
        super(context, b, new c(), c);
        this.e = context;
        try {
            this.e.registerReceiver(new a(this, (byte) 0), new IntentFilter("com.huawei.hem.license.operation"), "com.huawei.hms.permission.signatureOrSystem", null);
        } catch (Throwable unused) {
            f.b("HemLicenseManager", "hem register broadcast error");
        }
    }

    public static HemLicenseManager getInstance(Context context) {
        HemLicenseManager hemLicenseManager;
        synchronized (f179a) {
            if (d == null) {
                HemLicenseManager hemLicenseManager2 = new HemLicenseManager(context);
                d = hemLicenseManager2;
                hemLicenseManager2.setApiLevel(1);
                d.setKitSdkVersion(10000102);
            }
            hemLicenseManager = d;
        }
        return hemLicenseManager;
    }

    public Task<Integer> activeLicense() {
        try {
            d dVar = new d("hem.licenseActivate", new JSONObject().toString());
            dVar.f336a = this.f;
            return doWrite(dVar);
        } catch (Throwable th) {
            f.a("HemLicenseManager", "licenseActivate failed: " + th.getClass().getSimpleName());
            return null;
        }
    }

    public Task<Integer> deActiveLicense() {
        try {
            d dVar = new d("hem.licenseDeActivate", new JSONObject().toString());
            dVar.f336a = this.f;
            return doWrite(dVar);
        } catch (Throwable th) {
            f.a("HemLicenseManager", "licenseDeActivate failed: " + th.getClass().getSimpleName());
            return null;
        }
    }

    public void setStatusListener(HemLicenseStatusListener hemLicenseStatusListener) {
        this.f = hemLicenseStatusListener;
    }
}

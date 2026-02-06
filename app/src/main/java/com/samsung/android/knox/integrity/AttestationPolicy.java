package com.samsung.android.knox.integrity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.sec.enterprise.knox.IAttestation;

public class AttestationPolicy {
    public static final String ACTION_KNOX_ATTESTATION_RESULT = "com.samsung.android.knox.intent.action.KNOX_ATTESTATION_RESULT";
    public static final String EXTRA_ATTESTATION_DATA = "com.samsung.android.knox.intent.extra.ATTESTATION_DATA";
    public static final String EXTRA_ERROR_MSG = "com.samsung.android.knox.intent.extra.ERROR_MSG";
    public static final String EXTRA_RESULT = "com.samsung.android.knox.intent.extra.RESULT";
    private Context mContext;

    private class AttestationThread implements Runnable {
        private static final String ATTESTATION_ACTION = "com.sec.enterprise.knox.intent.action.BIND_KNOX_ATTESTATION_SERVICE";
        private static final String ATTESTATION_PKG = "com.sec.enterprise.knox.attestation";
        private static final String TAG = "AttestationPolicy";
        private Context mContext;
        private IAttestation mIAttestation;
        private String mNonce;
        private ServiceConnection mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                AttestationThread.this.mIAttestation = IAttestation.Stub.asInterface(iBinder);
                try {
                    AttestationThread.this.mIAttestation.startAttestation_nonce(AttestationThread.this.mNonce);
                } catch (RemoteException e) {
                    Log.d("AttestationPolicy", "Could not bind to Attestation service: " + e.getMessage());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                AttestationThread.this.mIAttestation = null;
                AttestationThread.this.mNonce = null;
            }
        };

        AttestationThread(Context context, String str) {
            this.mContext = context;
            this.mNonce = str;
        }

        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setPackage("com.sec.enterprise.knox.attestation");
            intent.setAction("com.sec.enterprise.knox.intent.action.BIND_KNOX_ATTESTATION_SERVICE");
            this.mContext.bindService(intent, this.mServiceConnection, 1);
        }
    }

    public AttestationPolicy(Context context) {
        this.mContext = context;
    }

    public void startAttestation(String str) {
        new Thread(new AttestationThread(this.mContext, str)).start();
    }
}

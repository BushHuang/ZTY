package com.analysys.visual;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import com.analysys.ipc.IpcManager;

public class aj implements SensorEventListener {

    private static long f102a;
    private final a b;
    private float[] c = new float[10];
    private float[] d = new float[10];
    private float[] e = new float[10];
    private int f;
    private int g;
    private boolean h;

    public interface a {
        void a();
    }

    public aj(a aVar) {
        this.b = aVar;
    }

    private boolean a(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float[] fArr2 = this.c;
        int i = this.g;
        fArr2[i] = f;
        this.d[i] = f2;
        this.e[i] = f3;
        while (true) {
            if (i < 0) {
                if (!this.h) {
                    break;
                }
                i = 9;
                if (i == this.f) {
                }
            } else if (i == this.f) {
                float fAbs = Math.abs(this.c[i] - f);
                float fAbs2 = Math.abs(this.d[i] - f2);
                float fAbs3 = Math.abs(this.e[i] - f3);
                if (fAbs > 30.0f && (fAbs / fAbs2 > 10.0f || fAbs / fAbs3 > 10.0f)) {
                    break;
                }
                if (fAbs2 > 30.0f && (fAbs2 / fAbs > 10.0f || fAbs2 / fAbs3 > 10.0f)) {
                    break;
                }
                if (fAbs3 > 30.0f && (fAbs3 / fAbs > 10.0f || fAbs3 / fAbs2 > 10.0f)) {
                    break;
                }
                i--;
            } else {
                break;
            }
        }
        a();
        return true;
    }

    public void a() {
        this.g = 0;
        this.f = 0;
        this.h = false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (IpcManager.getInstance().isAppInFront()) {
            int type = sensorEvent.sensor.getType();
            float[] fArr = sensorEvent.values;
            if (type != 1 || !a(fArr) || this.b == null || System.currentTimeMillis() - f102a <= 5000) {
                return;
            }
            f102a = System.currentTimeMillis();
            this.b.a();
        }
    }
}

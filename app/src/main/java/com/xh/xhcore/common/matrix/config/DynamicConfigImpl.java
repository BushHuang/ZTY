package com.xh.xhcore.common.matrix.config;

import com.tencent.matrix.util.MatrixLog;
import com.tencent.mrs.plugin.IDynamicConfig;

public class DynamicConfigImpl implements IDynamicConfig {
    private static final String TAG = "Matrix.DynamicConfigImpl";

    @Override
    public float get(String str, float f) {
        if (IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_frames_threshold_normal.name().equals(str)) {
            return 0.03f;
        }
        if (IDynamicConfig.ExptEnum.clicfg_matrix_fps_dropped_frames_threshold_middle.name().equals(str)) {
            return 0.003f;
        }
        return f;
    }

    @Override
    public int get(String str, int i) {
        if (IDynamicConfig.ExptEnum.clicfg_matrix_trace_fps_time_slice.name().equals(str)) {
            return 7000;
        }
        if (!IDynamicConfig.ExptEnum.clicfg_matrix_resource_max_detect_times.name().equals(str)) {
            return i;
        }
        MatrixLog.i("Matrix.DynamicConfigImpl", "key:" + str + ", before change:" + i + ", after change, value:2", new Object[0]);
        return 2;
    }

    @Override
    public long get(String str, long j) {
        if (!IDynamicConfig.ExptEnum.clicfg_matrix_resource_detect_interval_millis.name().equals(str)) {
            return j;
        }
        MatrixLog.i("Matrix.DynamicConfigImpl", str + ", before change:" + j + ", after change, value:2000", new Object[0]);
        return 2000L;
    }

    @Override
    public String get(String str, String str2) {
        return str2;
    }

    @Override
    public boolean get(String str, boolean z) {
        return z;
    }

    public boolean isFPSEnable() {
        return true;
    }

    public boolean isMatrixEnable() {
        return true;
    }

    public boolean isTraceEnable() {
        return true;
    }
}

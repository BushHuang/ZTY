package com.xuehai.provider.utils;

import com.xuehai.provider.core.dto.CPVDResource;

public interface HttpErrorFrequencyStrategy {
    boolean isNeedShield(CPVDResource cPVDResource);
}

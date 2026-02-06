package com.xuehai.launcher.common.config;

import com.xuehai.launcher.common.constants.FilePath;
import com.zaze.utils.FileUtil;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/xuehai/launcher/common/config/StaticConfig;", "", "()V", "clearAll", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StaticConfig {
    public static final StaticConfig INSTANCE = new StaticConfig();

    private StaticConfig() {
    }

    public final void clearAll() {
        FileUtil.deleteFile(FilePath.getKeepDir() + "/launcher_static_config");
    }
}

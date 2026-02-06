package com.xuehai.system.mdm;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xuehai/system/mdm/MdmFilePath;", "", "()V", "databasePath", "", "filesDir", "launcherIni", "providerDB", "sharedPrefs", "aidl_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmFilePath {
    public static final MdmFilePath INSTANCE = new MdmFilePath();
    public static final String databasePath = "/databases";
    public static final String filesDir = "/files";
    public static final String launcherIni = "/files/launcher.ini";
    public static final String providerDB = "/databases/zaze_provider.db";
    public static final String sharedPrefs = "/shared_prefs/share_data.xml";

    private MdmFilePath() {
    }
}

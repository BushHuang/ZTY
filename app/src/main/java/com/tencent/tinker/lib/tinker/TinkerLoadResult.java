package com.tencent.tinker.lib.tinker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.util.HashMap;

public class TinkerLoadResult {
    private static final String TAG = "Tinker.TinkerLoadResult";
    public long costTime;
    public String currentVersion;
    public File dexDirectory;
    public HashMap<String, String> dexes;
    public File libraryDirectory;
    public HashMap<String, String> libs;
    public int loadCode;
    public String oatDir;
    public HashMap<String, String> packageConfig;
    public SharePatchInfo patchInfo;
    public File patchVersionDirectory;
    public File patchVersionFile;
    public File resourceDirectory;
    public File resourceFile;
    public boolean systemOTA;
    public boolean useInterpretMode;
    public boolean versionChanged;

    public String getPackageConfigByName(String str) {
        HashMap<String, String> map = this.packageConfig;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public boolean parseTinkerResult(Context context, Intent intent) {
        File file;
        File file2;
        String str;
        Tinker tinkerWith = Tinker.with(context);
        this.loadCode = ShareIntentUtil.getIntentReturnCode(intent);
        this.costTime = ShareIntentUtil.getIntentPatchCostTime(intent);
        this.systemOTA = ShareIntentUtil.getBooleanExtra(intent, "intent_patch_system_ota", false);
        String stringExtra = ShareIntentUtil.getStringExtra(intent, "intent_patch_oat_dir");
        this.oatDir = stringExtra;
        this.useInterpretMode = "interpet".equals(stringExtra);
        boolean zIsMainProcess = tinkerWith.isMainProcess();
        TinkerLog.i("Tinker.TinkerLoadResult", "parseTinkerResult loadCode:%d, process name:%s, main process:%b, systemOTA:%b, fingerPrint:%s, oatDir:%s, useInterpretMode:%b", Integer.valueOf(this.loadCode), ShareTinkerInternals.getProcessName(context), Boolean.valueOf(zIsMainProcess), Boolean.valueOf(this.systemOTA), Build.FINGERPRINT, this.oatDir, Boolean.valueOf(this.useInterpretMode));
        String stringExtra2 = ShareIntentUtil.getStringExtra(intent, "intent_patch_old_version");
        String stringExtra3 = ShareIntentUtil.getStringExtra(intent, "intent_patch_new_version");
        File patchDirectory = tinkerWith.getPatchDirectory();
        File patchInfoFile = tinkerWith.getPatchInfoFile();
        if (stringExtra2 == null || stringExtra3 == null) {
            file = patchInfoFile;
            file2 = patchDirectory;
            str = stringExtra3;
        } else {
            if (zIsMainProcess) {
                this.currentVersion = stringExtra3;
            } else {
                this.currentVersion = stringExtra2;
            }
            TinkerLog.i("Tinker.TinkerLoadResult", "parseTinkerResult oldVersion:%s, newVersion:%s, current:%s", stringExtra2, stringExtra3, this.currentVersion);
            String patchVersionDirectory = SharePatchFileUtil.getPatchVersionDirectory(this.currentVersion);
            if (!ShareTinkerInternals.isNullOrNil(patchVersionDirectory)) {
                this.patchVersionDirectory = new File(patchDirectory.getAbsolutePath() + "/" + patchVersionDirectory);
                this.patchVersionFile = new File(this.patchVersionDirectory.getAbsolutePath(), SharePatchFileUtil.getPatchVersionFile(this.currentVersion));
                this.dexDirectory = new File(this.patchVersionDirectory, "dex");
                this.libraryDirectory = new File(this.patchVersionDirectory, "lib");
                this.resourceDirectory = new File(this.patchVersionDirectory, "res");
                this.resourceFile = new File(this.resourceDirectory, "resources.apk");
            }
            file = patchInfoFile;
            file2 = patchDirectory;
            this.patchInfo = new SharePatchInfo(stringExtra2, stringExtra3, ShareIntentUtil.getBooleanExtra(intent, "intent_is_protected_app", false), false, Build.FINGERPRINT, this.oatDir, false);
            str = stringExtra3;
            this.versionChanged = !stringExtra2.equals(str);
        }
        Throwable intentPatchException = ShareIntentUtil.getIntentPatchException(intent);
        if (intentPatchException != null) {
            TinkerLog.i("Tinker.TinkerLoadResult", "Tinker load have exception loadCode:%d", Integer.valueOf(this.loadCode));
            int i = this.loadCode;
            int i2 = -1;
            if (i == -25) {
                i2 = -4;
            } else if (i == -23) {
                i2 = -3;
            } else if (i != -20 && i == -14) {
                i2 = -2;
            }
            tinkerWith.getLoadReporter().onLoadException(intentPatchException, i2);
            return false;
        }
        int i3 = this.loadCode;
        if (i3 == -10000) {
            TinkerLog.e("Tinker.TinkerLoadResult", "can't get the right intent return code", new Object[0]);
            throw new TinkerRuntimeException("can't get the right intent return code");
        }
        if (i3 == -24) {
            File file3 = this.resourceFile;
            if (file3 == null) {
                TinkerLog.e("Tinker.TinkerLoadResult", "resource file md5 mismatch, but patch resource file not found!", new Object[0]);
                throw new TinkerRuntimeException("resource file md5 mismatch, but patch resource file not found!");
            }
            TinkerLog.e("Tinker.TinkerLoadResult", "patch resource file md5 is mismatch: %s", file3.getAbsolutePath());
            tinkerWith.getLoadReporter().onLoadFileMd5Mismatch(this.resourceFile, 6);
        } else if (i3 != -22) {
            if (i3 != -21) {
                switch (i3) {
                    case -19:
                        TinkerLog.i("Tinker.TinkerLoadResult", "rewrite patch info file corrupted", new Object[0]);
                        tinkerWith.getLoadReporter().onLoadPatchInfoCorrupted(stringExtra2, str, file);
                        break;
                    case -18:
                        String stringExtra4 = ShareIntentUtil.getStringExtra(intent, "intent_patch_missing_lib_path");
                        if (stringExtra4 == null) {
                            TinkerLog.e("Tinker.TinkerLoadResult", "patch lib file not found, but path is null!!!!", new Object[0]);
                            throw new TinkerRuntimeException("patch lib file not found, but path is null!!!!");
                        }
                        TinkerLog.e("Tinker.TinkerLoadResult", "patch lib file not found:%s", stringExtra4);
                        tinkerWith.getLoadReporter().onLoadFileNotFound(new File(stringExtra4), 5, false);
                        break;
                    case -17:
                        if (this.patchVersionDirectory == null) {
                            TinkerLog.e("Tinker.TinkerLoadResult", "patch lib file directory not found, warning why the path is null!!!!", new Object[0]);
                            throw new TinkerRuntimeException("patch lib file directory not found, warning why the path is null!!!!");
                        }
                        TinkerLog.e("Tinker.TinkerLoadResult", "patch lib file directory not found:%s", this.libraryDirectory.getAbsolutePath());
                        tinkerWith.getLoadReporter().onLoadFileNotFound(this.libraryDirectory, 5, true);
                        break;
                    case -16:
                        tinkerWith.getLoadReporter().onLoadInterpret(2, ShareIntentUtil.getIntentInterpretException(intent));
                        break;
                    case -15:
                        tinkerWith.getLoadReporter().onLoadInterpret(1, ShareIntentUtil.getIntentInterpretException(intent));
                        break;
                    default:
                        switch (i3) {
                            case -13:
                                String stringExtra5 = ShareIntentUtil.getStringExtra(intent, "intent_patch_mismatch_dex_path");
                                if (stringExtra5 == null) {
                                    TinkerLog.e("Tinker.TinkerLoadResult", "patch dex file md5 is mismatch, but path is null!!!!", new Object[0]);
                                    throw new TinkerRuntimeException("patch dex file md5 is mismatch, but path is null!!!!");
                                }
                                TinkerLog.e("Tinker.TinkerLoadResult", "patch dex file md5 is mismatch: %s", stringExtra5);
                                tinkerWith.getLoadReporter().onLoadFileMd5Mismatch(new File(stringExtra5), 3);
                                break;
                            case -12:
                                TinkerLog.e("Tinker.TinkerLoadResult", "patch dex load fail, classloader is null", new Object[0]);
                                break;
                            case -11:
                                String stringExtra6 = ShareIntentUtil.getStringExtra(intent, "intent_patch_missing_dex_path");
                                if (stringExtra6 == null) {
                                    TinkerLog.e("Tinker.TinkerLoadResult", "patch dex opt file not found, but path is null!!!!", new Object[0]);
                                    throw new TinkerRuntimeException("patch dex opt file not found, but path is null!!!!");
                                }
                                TinkerLog.e("Tinker.TinkerLoadResult", "patch dex opt file not found:%s", stringExtra6);
                                tinkerWith.getLoadReporter().onLoadFileNotFound(new File(stringExtra6), 4, false);
                                break;
                            case -10:
                                String stringExtra7 = ShareIntentUtil.getStringExtra(intent, "intent_patch_missing_dex_path");
                                if (stringExtra7 == null) {
                                    TinkerLog.e("Tinker.TinkerLoadResult", "patch dex file not found, but path is null!!!!", new Object[0]);
                                    throw new TinkerRuntimeException("patch dex file not found, but path is null!!!!");
                                }
                                TinkerLog.e("Tinker.TinkerLoadResult", "patch dex file not found:%s", stringExtra7);
                                tinkerWith.getLoadReporter().onLoadFileNotFound(new File(stringExtra7), 3, false);
                                break;
                            case -9:
                                File file4 = this.dexDirectory;
                                if (file4 == null) {
                                    TinkerLog.e("Tinker.TinkerLoadResult", "patch dex file directory not found, warning why the path is null!!!!", new Object[0]);
                                    throw new TinkerRuntimeException("patch dex file directory not found, warning why the path is null!!!!");
                                }
                                TinkerLog.e("Tinker.TinkerLoadResult", "patch dex file directory not found:%s", file4.getAbsolutePath());
                                tinkerWith.getLoadReporter().onLoadFileNotFound(this.dexDirectory, 3, true);
                                break;
                            case -8:
                                TinkerLog.i("Tinker.TinkerLoadResult", "patch package check fail", new Object[0]);
                                if (this.patchVersionFile == null) {
                                    throw new TinkerRuntimeException("error patch package check fail , but file is null");
                                }
                                tinkerWith.getLoadReporter().onLoadPackageCheckFail(this.patchVersionFile, intent.getIntExtra("intent_patch_package_patch_check", -10000));
                                break;
                            case -7:
                                TinkerLog.e("Tinker.TinkerLoadResult", "patch version file not found, current version:%s", this.currentVersion);
                                if (this.patchVersionFile == null) {
                                    throw new TinkerRuntimeException("error load patch version file not exist, but file is null");
                                }
                                tinkerWith.getLoadReporter().onLoadFileNotFound(this.patchVersionFile, 1, false);
                                break;
                            case -6:
                                TinkerLog.e("Tinker.TinkerLoadResult", "patch version directory not found, current version:%s", this.currentVersion);
                                tinkerWith.getLoadReporter().onLoadFileNotFound(this.patchVersionDirectory, 1, true);
                                break;
                            case -5:
                                TinkerLog.e("Tinker.TinkerLoadResult", "path info blank, wait main process to restart", new Object[0]);
                                break;
                            case -4:
                                TinkerLog.e("Tinker.TinkerLoadResult", "path info corrupted", new Object[0]);
                                tinkerWith.getLoadReporter().onLoadPatchInfoCorrupted(stringExtra2, str, file);
                                break;
                            case -3:
                            case -2:
                                TinkerLog.w("Tinker.TinkerLoadResult", "can't find patch file, is ok, just return", new Object[0]);
                                break;
                            case -1:
                                TinkerLog.w("Tinker.TinkerLoadResult", "tinker is disable, just return", new Object[0]);
                                break;
                            case 0:
                                TinkerLog.i("Tinker.TinkerLoadResult", "oh yeah, tinker load all success", new Object[0]);
                                tinkerWith.setTinkerLoaded(true);
                                this.dexes = ShareIntentUtil.getIntentPatchDexPaths(intent);
                                this.libs = ShareIntentUtil.getIntentPatchLibsPaths(intent);
                                this.packageConfig = ShareIntentUtil.getIntentPackageConfig(intent);
                                if (this.useInterpretMode) {
                                    tinkerWith.getLoadReporter().onLoadInterpret(0, null);
                                }
                                if (zIsMainProcess && this.versionChanged) {
                                    tinkerWith.getLoadReporter().onLoadPatchVersionChanged(stringExtra2, str, file2, this.patchVersionDirectory.getName());
                                }
                                return true;
                        }
                }
            } else {
                if (this.patchVersionDirectory == null) {
                    TinkerLog.e("Tinker.TinkerLoadResult", "patch resource file directory not found, warning why the path is null!!!!", new Object[0]);
                    throw new TinkerRuntimeException("patch resource file directory not found, warning why the path is null!!!!");
                }
                TinkerLog.e("Tinker.TinkerLoadResult", "patch resource file directory not found:%s", this.resourceDirectory.getAbsolutePath());
                tinkerWith.getLoadReporter().onLoadFileNotFound(this.resourceDirectory, 6, true);
            }
        } else {
            if (this.patchVersionDirectory == null) {
                TinkerLog.e("Tinker.TinkerLoadResult", "patch resource file not found, warning why the path is null!!!!", new Object[0]);
                throw new TinkerRuntimeException("patch resource file not found, warning why the path is null!!!!");
            }
            TinkerLog.e("Tinker.TinkerLoadResult", "patch resource file not found:%s", this.resourceFile.getAbsolutePath());
            tinkerWith.getLoadReporter().onLoadFileNotFound(this.resourceFile, 6, false);
        }
        return false;
    }
}

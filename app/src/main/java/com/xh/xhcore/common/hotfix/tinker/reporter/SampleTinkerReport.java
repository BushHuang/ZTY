package com.xh.xhcore.common.hotfix.tinker.reporter;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.xh.xhcore.common.hotfix.tinker.util.Utils;

public class SampleTinkerReport {
    public static final int KEY_APPLIED = 5;
    public static final int KEY_APPLIED_DEXOPT_EXIST = 122;
    public static final int KEY_APPLIED_DEXOPT_FORMAT = 123;
    public static final int KEY_APPLIED_DEXOPT_OTHER = 121;
    public static final int KEY_APPLIED_DEX_EXTRACT = 182;
    public static final int KEY_APPLIED_EXCEPTION = 120;
    public static final int KEY_APPLIED_FAIL_COST_10S_LESS = 206;
    public static final int KEY_APPLIED_FAIL_COST_30S_LESS = 207;
    public static final int KEY_APPLIED_FAIL_COST_5S_LESS = 205;
    public static final int KEY_APPLIED_FAIL_COST_60S_LESS = 208;
    public static final int KEY_APPLIED_FAIL_COST_OTHER = 209;
    public static final int KEY_APPLIED_INFO_CORRUPTED = 124;
    public static final int KEY_APPLIED_LIB_EXTRACT = 183;
    public static final int KEY_APPLIED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND = 153;
    public static final int KEY_APPLIED_PACKAGE_CHECK_DEX_META = 151;
    public static final int KEY_APPLIED_PACKAGE_CHECK_LIB_META = 152;
    public static final int KEY_APPLIED_PACKAGE_CHECK_META_NOT_FOUND = 155;
    public static final int KEY_APPLIED_PACKAGE_CHECK_PATCH_TINKER_ID_NOT_FOUND = 154;
    public static final int KEY_APPLIED_PACKAGE_CHECK_RES_META = 157;
    public static final int KEY_APPLIED_PACKAGE_CHECK_SIGNATURE = 150;
    public static final int KEY_APPLIED_PACKAGE_CHECK_TINKERFLAG_NOT_SUPPORT = 158;
    public static final int KEY_APPLIED_PACKAGE_CHECK_TINKER_ID_NOT_EQUAL = 156;
    public static final int KEY_APPLIED_PATCH_FILE_EXTRACT = 181;
    public static final int KEY_APPLIED_RESOURCE_EXTRACT = 184;
    public static final int KEY_APPLIED_START = 4;
    public static final int KEY_APPLIED_SUCC_COST_10S_LESS = 201;
    public static final int KEY_APPLIED_SUCC_COST_30S_LESS = 202;
    public static final int KEY_APPLIED_SUCC_COST_5S_LESS = 200;
    public static final int KEY_APPLIED_SUCC_COST_60S_LESS = 203;
    public static final int KEY_APPLIED_SUCC_COST_OTHER = 204;
    public static final int KEY_APPLIED_UPGRADE = 100;
    public static final int KEY_APPLIED_UPGRADE_FAIL = 101;
    public static final int KEY_APPLIED_VERSION_CHECK = 180;
    public static final int KEY_APPLY_WITH_RETRY = 10;
    public static final int KEY_CRASH_CAUSE_XPOSED_ART = 9;
    public static final int KEY_CRASH_CAUSE_XPOSED_DALVIK = 8;
    public static final int KEY_CRASH_FAST_PROTECT = 7;
    public static final int KEY_DOWNLOAD = 1;
    public static final int KEY_LOADED = 6;
    public static final int KEY_LOADED_EXCEPTION_DEX = 252;
    public static final int KEY_LOADED_EXCEPTION_DEX_CHECK = 253;
    public static final int KEY_LOADED_EXCEPTION_RESOURCE = 254;
    public static final int KEY_LOADED_EXCEPTION_RESOURCE_CHECK = 255;
    public static final int KEY_LOADED_INFO_CORRUPTED = 309;
    public static final int KEY_LOADED_INTERPRET_GET_INSTRUCTION_SET_ERROR = 450;
    public static final int KEY_LOADED_INTERPRET_INTERPRET_COMMAND_ERROR = 451;
    public static final int KEY_LOADED_INTERPRET_TYPE_INTERPRET_OK = 452;
    public static final int KEY_LOADED_MISMATCH_DEX = 300;
    public static final int KEY_LOADED_MISMATCH_LIB = 301;
    public static final int KEY_LOADED_MISMATCH_RESOURCE = 302;
    public static final int KEY_LOADED_MISSING_DEX = 303;
    public static final int KEY_LOADED_MISSING_DEX_OPT = 307;
    public static final int KEY_LOADED_MISSING_LIB = 304;
    public static final int KEY_LOADED_MISSING_PATCH_FILE = 305;
    public static final int KEY_LOADED_MISSING_PATCH_INFO = 306;
    public static final int KEY_LOADED_MISSING_RES = 308;
    public static final int KEY_LOADED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND = 353;
    public static final int KEY_LOADED_PACKAGE_CHECK_DEX_META = 351;
    public static final int KEY_LOADED_PACKAGE_CHECK_LIB_META = 352;
    public static final int KEY_LOADED_PACKAGE_CHECK_PACKAGE_META_NOT_FOUND = 356;
    public static final int KEY_LOADED_PACKAGE_CHECK_PATCH_TINKER_ID_NOT_FOUND = 354;
    public static final int KEY_LOADED_PACKAGE_CHECK_RES_META = 357;
    public static final int KEY_LOADED_PACKAGE_CHECK_SIGNATURE = 350;
    public static final int KEY_LOADED_PACKAGE_CHECK_TINKERFLAG_NOT_SUPPORT = 358;
    public static final int KEY_LOADED_PACKAGE_CHECK_TINKER_ID_NOT_EQUAL = 355;
    public static final int KEY_LOADED_SUCC_COST_1000_LESS = 401;
    public static final int KEY_LOADED_SUCC_COST_3000_LESS = 402;
    public static final int KEY_LOADED_SUCC_COST_5000_LESS = 403;
    public static final int KEY_LOADED_SUCC_COST_500_LESS = 400;
    public static final int KEY_LOADED_SUCC_COST_OTHER = 404;
    public static final int KEY_LOADED_UNCAUGHT_EXCEPTION = 251;
    public static final int KEY_LOADED_UNKNOWN_EXCEPTION = 250;
    public static final int KEY_REQUEST = 0;
    public static final int KEY_TRY_APPLY = 2;
    public static final int KEY_TRY_APPLY_ALREADY_APPLY = 77;
    public static final int KEY_TRY_APPLY_CONDITION_NOT_SATISFIED = 80;
    public static final int KEY_TRY_APPLY_CRASH_LIMIT = 79;
    public static final int KEY_TRY_APPLY_DISABLE = 71;
    public static final int KEY_TRY_APPLY_GOOGLEPLAY = 75;
    public static final int KEY_TRY_APPLY_INSERVICE = 73;
    public static final int KEY_TRY_APPLY_JIT = 81;
    public static final int KEY_TRY_APPLY_MEMORY_LIMIT = 78;
    public static final int KEY_TRY_APPLY_NOT_EXIST = 74;
    public static final int KEY_TRY_APPLY_ROM_SPACE = 76;
    public static final int KEY_TRY_APPLY_RUNNING = 72;
    public static final int KEY_TRY_APPLY_SUCCESS = 3;
    public static final int KEY_TRY_APPLY_UPGRADE = 70;
    private static final String TAG = "Tinker.SampleTinkerReport";
    private static Reporter reporter;

    public interface Reporter {
        void onReport(int i);

        void onReport(String str);
    }

    public static void onApplied(long j, boolean z) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        if (z) {
            reporter2.onReport(5);
        }
        if (z) {
            reporter.onReport(100);
        } else {
            reporter.onReport(101);
        }
        TinkerLog.i("Tinker.SampleTinkerReport", "hp_report report apply cost = %d", Long.valueOf(j));
        if (j < 0) {
            TinkerLog.e("Tinker.SampleTinkerReport", "hp_report report apply cost failed, invalid cost", new Object[0]);
            return;
        }
        if (j <= 5000) {
            if (z) {
                reporter.onReport(200);
                return;
            } else {
                reporter.onReport(205);
                return;
            }
        }
        if (j <= 10000) {
            if (z) {
                reporter.onReport(201);
                return;
            } else {
                reporter.onReport(206);
                return;
            }
        }
        if (j <= 30000) {
            if (z) {
                reporter.onReport(202);
                return;
            } else {
                reporter.onReport(207);
                return;
            }
        }
        if (j <= 60000) {
            if (z) {
                reporter.onReport(203);
                return;
            } else {
                reporter.onReport(208);
                return;
            }
        }
        if (z) {
            reporter.onReport(204);
        } else {
            reporter.onReport(209);
        }
    }

    public static void onApplyCrash(Throwable th) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(120);
        reporter.onReport("Tinker Exception:apply tinker occur exception " + Utils.getExceptionCauseString(th));
    }

    public static void onApplyDexOptFail(Throwable th) {
        if (reporter == null) {
            return;
        }
        if (th.getMessage().contains("checkDexOptExist failed")) {
            reporter.onReport(122);
            return;
        }
        if (th.getMessage().contains("checkDexOptFormat failed")) {
            reporter.onReport(123);
            return;
        }
        reporter.onReport(121);
        reporter.onReport("Tinker Exception:apply tinker occur exception " + Utils.getExceptionCauseString(th));
    }

    public static void onApplyExtractFail(int i) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        if (i == 1) {
            reporter2.onReport(181);
            return;
        }
        if (i == 3) {
            reporter2.onReport(182);
        } else if (i == 5) {
            reporter2.onReport(183);
        } else {
            if (i != 6) {
                return;
            }
            reporter2.onReport(184);
        }
    }

    public static void onApplyInfoCorrupted() {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(124);
    }

    public static void onApplyPackageCheckFail(int i) {
        if (reporter == null) {
        }
        TinkerLog.i("Tinker.SampleTinkerReport", "hp_report package check failed, error = %d", Integer.valueOf(i));
        switch (i) {
            case -9:
                reporter.onReport(158);
                break;
            case -8:
                reporter.onReport(157);
                break;
            case -7:
                reporter.onReport(156);
                break;
            case -6:
                reporter.onReport(154);
                break;
            case -5:
                reporter.onReport(153);
                break;
            case -4:
                reporter.onReport(152);
                break;
            case -3:
                reporter.onReport(151);
                break;
            case -2:
                reporter.onReport(155);
                break;
            case -1:
                reporter.onReport(150);
                break;
        }
    }

    public static void onApplyPatchServiceStart() {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(4);
    }

    public static void onApplyVersionCheckFail() {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(180);
    }

    public static void onFastCrashProtect() {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(7);
    }

    public static void onLoadException(Throwable th, int i) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        boolean z = true;
        if (i != -4) {
            if (i != -3) {
                if (i != -2) {
                    if (i == -1) {
                        reporter2.onReport(250);
                    }
                } else if (th.getMessage().contains("checkDexInstall failed")) {
                    reporter.onReport(253);
                    TinkerLog.e("Tinker.SampleTinkerReport", "tinker dex check fail:" + th.getMessage(), new Object[0]);
                } else {
                    reporter.onReport(252);
                    TinkerLog.e("Tinker.SampleTinkerReport", "tinker dex reflect fail:" + th.getMessage(), new Object[0]);
                }
            } else if (th.getMessage().contains("checkResInstall failed")) {
                reporter.onReport(255);
                TinkerLog.e("Tinker.SampleTinkerReport", "tinker res check fail:" + th.getMessage(), new Object[0]);
            } else {
                reporter.onReport(254);
                TinkerLog.e("Tinker.SampleTinkerReport", "tinker res reflect fail:" + th.getMessage(), new Object[0]);
            }
            if (z) {
                reporter.onReport("Tinker Exception:load tinker occur exception " + Utils.getExceptionCauseString(th));
                return;
            }
            return;
        }
        reporter2.onReport(251);
        z = false;
        if (z) {
        }
    }

    public static void onLoadFileMisMatch(int i) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        if (i == 3) {
            reporter2.onReport(300);
        } else if (i == 5) {
            reporter2.onReport(301);
        } else {
            if (i != 6) {
                return;
            }
            reporter2.onReport(302);
        }
    }

    public static void onLoadFileNotFound(int i) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
        }
        switch (i) {
            case 1:
                reporter2.onReport(305);
                break;
            case 2:
                reporter2.onReport(306);
                break;
            case 3:
                reporter2.onReport(303);
                break;
            case 4:
                reporter2.onReport(307);
                break;
            case 5:
                reporter2.onReport(304);
                break;
            case 6:
                reporter2.onReport(308);
                break;
        }
    }

    public static void onLoadInfoCorrupted() {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(309);
    }

    public static void onLoadInterpretReport(int i, Throwable th) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        if (i == 0) {
            reporter2.onReport(452);
            return;
        }
        if (i == 1) {
            reporter2.onReport(450);
            reporter.onReport("Tinker Exception:interpret occur exception " + Utils.getExceptionCauseString(th));
            return;
        }
        if (i != 2) {
            return;
        }
        reporter2.onReport(451);
        reporter.onReport("Tinker Exception:interpret occur exception " + Utils.getExceptionCauseString(th));
    }

    public static void onLoadPackageCheckFail(int i) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
        }
        switch (i) {
            case -9:
                reporter2.onReport(358);
                break;
            case -8:
                reporter2.onReport(357);
                break;
            case -7:
                reporter2.onReport(355);
                break;
            case -6:
                reporter2.onReport(354);
                break;
            case -5:
                reporter2.onReport(353);
                break;
            case -4:
                reporter2.onReport(352);
                break;
            case -3:
                reporter2.onReport(351);
                break;
            case -2:
                reporter2.onReport(356);
                break;
            case -1:
                reporter2.onReport(350);
                break;
        }
    }

    public static void onLoaded(long j) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(6);
        if (j < 0) {
            TinkerLog.e("Tinker.SampleTinkerReport", "hp_report report load cost failed, invalid cost", new Object[0]);
            return;
        }
        if (j <= 500) {
            reporter.onReport(400);
            return;
        }
        if (j <= 1000) {
            reporter.onReport(401);
            return;
        }
        if (j <= 3000) {
            reporter.onReport(402);
        } else if (j <= 5000) {
            reporter.onReport(403);
        } else {
            reporter.onReport(404);
        }
    }

    public static void onReportRetryPatch() {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(10);
    }

    public static void onTryApply(boolean z) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
            return;
        }
        reporter2.onReport(2);
        reporter.onReport(70);
        if (z) {
            reporter.onReport(3);
        }
    }

    public static void onTryApplyFail(int i) {
        Reporter reporter2 = reporter;
        if (reporter2 == null) {
        }
        switch (i) {
            case -24:
                reporter2.onReport(80);
                break;
            case -23:
                reporter2.onReport(79);
                break;
            case -22:
                reporter2.onReport(78);
                break;
            case -21:
                reporter2.onReport(76);
                break;
            case -20:
                reporter2.onReport(75);
                break;
            default:
                switch (i) {
                    case -6:
                        reporter2.onReport(77);
                        break;
                    case -5:
                        reporter2.onReport(81);
                        break;
                    case -4:
                        reporter2.onReport(73);
                        break;
                    case -3:
                        reporter2.onReport(72);
                        break;
                    case -2:
                        reporter2.onReport(74);
                        break;
                    case -1:
                        reporter2.onReport(71);
                        break;
                }
        }
    }

    public static void onXposedCrash() {
        if (reporter == null) {
            return;
        }
        if (ShareTinkerInternals.isVmArt()) {
            reporter.onReport(9);
        } else {
            reporter.onReport(8);
        }
    }

    public static void setReporter(Reporter reporter2) {
        reporter = reporter2;
    }
}

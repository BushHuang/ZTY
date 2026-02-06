package com.tencent.tinker.lib.util;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TinkerLog {
    private static final String TAG = "Tinker.TinkerLog";
    private static final TinkerLogImp debugLog;
    private static final TinkerLogImp[] tinkerLogImpRef;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
    private static final List<Object[]> sPendingLogs = Collections.synchronizedList(new ArrayList());

    public interface TinkerLogImp {
        void d(String str, String str2, Object... objArr);

        void e(String str, String str2, Object... objArr);

        void i(String str, String str2, Object... objArr);

        void printErrStackTrace(String str, Throwable th, String str2, Object... objArr);

        void v(String str, String str2, Object... objArr);

        void w(String str, String str2, Object... objArr);
    }

    static {
        TinkerLogImp tinkerLogImp = new TinkerLogImp() {
            @Override
            public void d(String str, String str2, Object... objArr) {
                if (objArr != null && objArr.length != 0) {
                    str2 = String.format(str2, objArr);
                }
                Log.d(str, str2);
            }

            @Override
            public void e(String str, String str2, Object... objArr) {
                if (objArr != null && objArr.length != 0) {
                    str2 = String.format(str2, objArr);
                }
                Log.e(str, str2);
            }

            @Override
            public void i(String str, String str2, Object... objArr) {
                if (objArr != null && objArr.length != 0) {
                    str2 = String.format(str2, objArr);
                }
                Log.i(str, str2);
            }

            @Override
            public void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
                if (objArr != null && objArr.length != 0) {
                    str2 = String.format(str2, objArr);
                }
                if (str2 == null) {
                    str2 = "";
                }
                Log.e(str, str2 + "  " + Log.getStackTraceString(th));
            }

            @Override
            public void v(String str, String str2, Object... objArr) {
                if (objArr != null && objArr.length != 0) {
                    str2 = String.format(str2, objArr);
                }
                Log.v(str, str2);
            }

            @Override
            public void w(String str, String str2, Object... objArr) {
                if (objArr != null && objArr.length != 0) {
                    str2 = String.format(str2, objArr);
                }
                Log.w(str, str2);
            }
        };
        debugLog = tinkerLogImp;
        tinkerLogImpRef = new TinkerLogImp[]{tinkerLogImp};
    }

    public static void d(String str, String str2, Object... objArr) {
        TinkerLogImp impl = getImpl();
        if (impl != null) {
            impl.d(str, str2, objArr);
            return;
        }
        if (objArr != null && objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        String str3 = "[PendingLog@" + SIMPLE_DATE_FORMAT.format(new Date()) + "] ";
        sPendingLogs.add(new Object[]{3, str, str3 + str2});
    }

    public static void e(String str, String str2, Object... objArr) {
        TinkerLogImp impl = getImpl();
        if (impl != null) {
            impl.e(str, str2, objArr);
            return;
        }
        if (objArr != null && objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        String str3 = "[PendingLog@" + SIMPLE_DATE_FORMAT.format(new Date()) + "] ";
        sPendingLogs.add(new Object[]{6, str, str3 + str2});
    }

    public static TinkerLogImp getImpl() {
        TinkerLogImp tinkerLogImp;
        synchronized (tinkerLogImpRef) {
            tinkerLogImp = tinkerLogImpRef[0];
        }
        return tinkerLogImp;
    }

    public static void i(String str, String str2, Object... objArr) {
        TinkerLogImp impl = getImpl();
        if (impl != null) {
            impl.i(str, str2, objArr);
            return;
        }
        if (objArr != null && objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        String str3 = "[PendingLog@" + SIMPLE_DATE_FORMAT.format(new Date()) + "] ";
        sPendingLogs.add(new Object[]{4, str, str3 + str2});
    }

    public static void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        TinkerLogImp impl = getImpl();
        if (impl != null) {
            impl.printErrStackTrace(str, th, str2, objArr);
            return;
        }
        if (objArr != null && objArr.length != 0) {
            str2 = String.format(str2, objArr);
        }
        String str3 = str2 + "  " + Log.getStackTraceString(th);
        String str4 = "[PendingLog@" + SIMPLE_DATE_FORMAT.format(new Date()) + "] ";
        sPendingLogs.add(new Object[]{6, str, str4 + str3});
    }

    public static void printPendingLogs() {
        TinkerLogImp tinkerLogImp;
        synchronized (tinkerLogImpRef) {
            tinkerLogImp = tinkerLogImpRef[0];
        }
        if (sPendingLogs.isEmpty() || tinkerLogImp == null) {
            return;
        }
        for (Object[] objArr : sPendingLogs) {
            int iIntValue = ((Integer) objArr[0]).intValue();
            String str = (String) objArr[1];
            String str2 = (String) objArr[2];
            if (iIntValue == 2) {
                tinkerLogImp.v(str, str2, new Object[0]);
            } else if (iIntValue == 3) {
                tinkerLogImp.d(str, str2, new Object[0]);
            } else if (iIntValue == 4) {
                tinkerLogImp.i(str, str2, new Object[0]);
            } else if (iIntValue == 5) {
                tinkerLogImp.w(str, str2, new Object[0]);
            } else if (iIntValue == 6) {
                tinkerLogImp.e(str, str2, new Object[0]);
            }
        }
        sPendingLogs.clear();
    }

    public static void setTinkerLogImp(TinkerLogImp tinkerLogImp) {
        synchronized (tinkerLogImpRef) {
            tinkerLogImpRef[0] = tinkerLogImp;
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        TinkerLogImp impl = getImpl();
        if (impl != null) {
            impl.v(str, str2, objArr);
            return;
        }
        if (objArr != null && objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        String str3 = "[PendingLog@" + SIMPLE_DATE_FORMAT.format(new Date()) + "] ";
        sPendingLogs.add(new Object[]{2, str, str3 + str2});
    }

    public static void w(String str, String str2, Object... objArr) {
        TinkerLogImp impl = getImpl();
        if (impl != null) {
            impl.w(str, str2, objArr);
            return;
        }
        if (objArr != null && objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        String str3 = "[PendingLog@" + SIMPLE_DATE_FORMAT.format(new Date()) + "] ";
        sPendingLogs.add(new Object[]{5, str, str3 + str2});
    }
}

package com.xuehai.system.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Process;
import android.util.SparseArray;
import com.xuehai.system.common.R;
import com.xuehai.system.common.log.MdmLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public final class IptableApi {
    public static final String CUSTOM_SCRIPT_MSG = "com.googlecode.xuehaifirewall.intent.action.CUSTOM_SCRIPT";
    public static final String MODE_BLACKLIST = "blacklist";
    public static final String MODE_WHITELIST = "whitelist";
    public static final String PREFS_NAME = "XueHaiFireWallPrefs";
    public static final String PREF_3G_UIDS = "AllowedUids3G";
    public static final String PREF_CUSTOMSCRIPT = "CustomScript";
    public static final String PREF_CUSTOMSCRIPT2 = "CustomScript2";
    public static final String PREF_ENABLED = "Enabled";
    public static final String PREF_LOGENABLED = "LogEnabled";
    public static final String PREF_MODE = "BlockMode";
    public static final String PREF_WIFI_UIDS = "AllowedUidsWifi";
    public static final String SCRIPT2_EXTRA = "com.googlecode.xuehaifirewall.intent.extra.SCRIPT2";
    public static final String SCRIPT_EXTRA = "com.googlecode.xuehaifirewall.intent.extra.SCRIPT";
    private static final String SCRIPT_FILE = "xuehaifirewall.sh";
    public static final int SPECIAL_UID_ANY = -10;
    public static final int SPECIAL_UID_KERNEL = -11;
    public static final String STATUS_CHANGED_MSG = "com.googlecode.xuehaifirewall.intent.action.STATUS_CHANGED";
    public static final String STATUS_EXTRA = "com.googlecode.xuehaifirewall.intent.extra.STATUS";
    private static String TAG_FIRE_WALL = "FireWall";
    public static final String TOGGLE_REQUEST_MSG = "com.googlecode.xuehaifirewall.intent.action.TOGGLE_REQUEST";
    public static final String VERSION = "1.5.7";
    private static boolean hasroot = false;
    private static boolean needLog = false;

    public static final class DroidApp {
        String appPackageName;
        ApplicationInfo appinfo;
        boolean selected3g;
        boolean selectedWifi;
        int uid;

        public DroidApp() {
            this.selectedWifi = false;
            this.selected3g = false;
        }

        public DroidApp(int i, String str, boolean z, boolean z2) {
            this.selectedWifi = false;
            this.selected3g = false;
            this.uid = i;
            this.appPackageName = str;
            this.selectedWifi = z;
            this.selected3g = z2;
        }

        public static SparseArray<DroidApp> build(Context context, List<String> list, boolean z) {
            try {
                HashSet hashSet = new HashSet(list);
                PackageManager packageManager = context.getPackageManager();
                List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
                SparseArray<DroidApp> sparseArray = new SparseArray<>();
                for (ApplicationInfo applicationInfo : installedApplications) {
                    String str = applicationInfo.packageName;
                    if (packageManager.checkPermission("android.permission.INTERNET", str) == 0 && hashSet.contains(applicationInfo.packageName)) {
                        DroidApp droidApp = sparseArray.get(applicationInfo.uid);
                        if (droidApp == null) {
                            droidApp = new DroidApp();
                            droidApp.uid = applicationInfo.uid;
                        }
                        droidApp.appPackageName = str;
                        droidApp.appinfo = applicationInfo;
                        if (z) {
                            droidApp.selected3g = true;
                            droidApp.selectedWifi = true;
                        } else {
                            droidApp.selected3g = false;
                            droidApp.selectedWifi = false;
                        }
                        sparseArray.put(applicationInfo.uid, droidApp);
                    }
                }
                return sparseArray;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public String getAppPackageName() {
            return this.appPackageName;
        }
    }

    private static final class ScriptRunner extends Thread {
        private final boolean asroot;
        private Process exec;
        public int exitcode = -1;
        private final File file;
        private final StringBuilder res;
        private final String script;

        public ScriptRunner(File file, String str, StringBuilder sb, boolean z) {
            this.file = file;
            this.script = str;
            this.res = sb;
            this.asroot = z;
        }

        @Override
        public synchronized void destroy() {
            if (this.exec != null) {
                this.exec.destroy();
            }
            this.exec = null;
        }

        @Override
        public void run() {
            try {
                try {
                    this.file.createNewFile();
                    String absolutePath = this.file.getAbsolutePath();
                    Runtime.getRuntime().exec("chmod 777 " + absolutePath).waitFor();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.file));
                    if (new File("/system/bin/sh").exists()) {
                        outputStreamWriter.write("#!/system/bin/sh\n");
                    }
                    outputStreamWriter.write(this.script);
                    if (!this.script.endsWith("\n")) {
                        outputStreamWriter.write("\n");
                    }
                    outputStreamWriter.write("exit\n");
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    if (this.asroot) {
                        this.exec = Runtime.getRuntime().exec("su -c " + absolutePath);
                    } else {
                        this.exec = Runtime.getRuntime().exec("sh " + absolutePath);
                    }
                    InputStream inputStream = this.exec.getInputStream();
                    InputStream errorStream = this.exec.getErrorStream();
                    byte[] bArr = new byte[8192];
                    while (true) {
                        Process process = this.exec;
                        if (process == null) {
                            break;
                        }
                        try {
                            this.exitcode = process.exitValue();
                        } catch (IllegalThreadStateException e) {
                            MdmLog.w("Api", "The process is still running", e);
                        }
                        if (inputStream.available() > 0) {
                            int i = inputStream.read(bArr);
                            if (this.res != null) {
                                this.res.append(new String(bArr, 0, i));
                            }
                        }
                        if (errorStream.available() > 0) {
                            int i2 = errorStream.read(bArr);
                            if (this.res != null) {
                                this.res.append(new String(bArr, 0, i2));
                            }
                        }
                        if (this.exitcode != -1) {
                            break;
                        } else {
                            Thread.sleep(50L);
                        }
                    }
                } catch (InterruptedException unused) {
                    if (this.res != null) {
                        this.res.append("\nOperation timed-out");
                    }
                } catch (Exception e2) {
                    if (this.res != null) {
                        this.res.append("\n" + e2);
                    }
                }
            } finally {
                destroy();
            }
        }
    }

    public static void alert(Context context, CharSequence charSequence) {
    }

    public static void applicationRemoved(Context context, int i) {
        boolean z;
        SharedPreferences sharedPreferences = context.getSharedPreferences("XueHaiFireWallPrefs", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = sharedPreferences.getString("AllowedUidsWifi", "");
        String string2 = sharedPreferences.getString("AllowedUids3G", "");
        String str = i + "";
        if (string.length() > 0) {
            StringBuilder sb = new StringBuilder();
            StringTokenizer stringTokenizer = new StringTokenizer(string, "|");
            z = false;
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                if (str.equals(strNextToken)) {
                    if (needLog) {
                        MdmLog.d(TAG_FIRE_WALL, "Removing UID " + strNextToken + " from the wi-fi list (package removed)!");
                    }
                    z = true;
                } else {
                    if (sb.length() > 0) {
                        sb.append('|');
                    }
                    sb.append(strNextToken);
                }
            }
            if (z) {
                editorEdit.putString("AllowedUidsWifi", sb.toString());
            }
        } else {
            z = false;
        }
        if (string2.length() > 0) {
            StringBuilder sb2 = new StringBuilder();
            StringTokenizer stringTokenizer2 = new StringTokenizer(string2, "|");
            while (stringTokenizer2.hasMoreTokens()) {
                String strNextToken2 = stringTokenizer2.nextToken();
                if (str.equals(strNextToken2)) {
                    if (needLog) {
                        MdmLog.d(TAG_FIRE_WALL, "Removing UID " + strNextToken2 + "from the 3G list (package removed)!");
                    }
                    z = true;
                } else {
                    if (sb2.length() > 0) {
                        sb2.append('|');
                    }
                    sb2.append(strNextToken2);
                }
            }
            if (z) {
                editorEdit.putString("AllowedUids3G", sb2.toString());
            }
        }
        if (z) {
            editorEdit.apply();
            if (isEnabled(context)) {
                applySavedIptablesRules(context, false);
            }
        }
    }

    public static boolean applyIptablesRules(Context context, SparseArray<DroidApp> sparseArray, boolean z) {
        if (context == null) {
            return false;
        }
        saveRules(context, sparseArray);
        return applySavedIptablesRules(context, z);
    }

    private static boolean applyIptablesRulesImpl(Context context, List<Integer> list, List<Integer> list2, boolean z) {
        String str;
        if (context == null) {
            return false;
        }
        assertBinaries(context, z);
        String[] strArr = {"tiwlan+", "wlan+", "eth+", "ra+"};
        String[] strArr2 = {"rmnet+", "pdp+", "ppp+", "uwbr+", "wimax+", "vsnet+", "ccmni+", "usb+"};
        boolean zEquals = context.getSharedPreferences("XueHaiFireWallPrefs", 0).getString("BlockMode", "whitelist").equals("whitelist");
        boolean z2 = !zEquals;
        boolean z3 = context.getSharedPreferences("XueHaiFireWallPrefs", 0).getBoolean("LogEnabled", false);
        String str2 = "";
        String string = context.getSharedPreferences("XueHaiFireWallPrefs", 0).getString("CustomScript", "");
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(scriptHeader(context));
            sb.append("$IPTABLES --version || exit 1\n# Create the xuehaifirewall chains if necessary\n$IPTABLES -L xuehaifirewall >/dev/null 2>/dev/null || $IPTABLES --new xuehaifirewall || exit 2\n$IPTABLES -L xuehaifirewall-3g >/dev/null 2>/dev/null || $IPTABLES --new xuehaifirewall-3g || exit 3\n$IPTABLES -L xuehaifirewall-wifi >/dev/null 2>/dev/null || $IPTABLES --new xuehaifirewall-wifi || exit 4\n$IPTABLES -L xuehaifirewall-reject >/dev/null 2>/dev/null || $IPTABLES --new xuehaifirewall-reject || exit 5\n# Add xuehaifirewall chain to OUTPUT chain if necessary\n$IPTABLES -L OUTPUT | $GREP -q xuehaifirewall || $IPTABLES -A OUTPUT -j xuehaifirewall || exit 6\n# Flush existing rules\n$IPTABLES -F xuehaifirewall || exit 7\n$IPTABLES -F xuehaifirewall-3g || exit 8\n$IPTABLES -F xuehaifirewall-wifi || exit 9\n$IPTABLES -F xuehaifirewall-reject || exit 10\n");
            if (z3) {
                sb.append("# Create the log and reject rules (ignore errors on the LOG target just in case it is not available)\n$IPTABLES -A xuehaifirewall-reject -j LOG --log-prefix \"[DROIDWALL] \" --log-uid\n$IPTABLES -A xuehaifirewall-reject -j REJECT || exit 11\n");
            } else {
                sb.append("# Create the reject rule (log disabled)\n$IPTABLES -A xuehaifirewall-reject -j REJECT || exit 11\n");
            }
            if (string.length() > 0) {
                sb.append("\n# BEGIN OF CUSTOM SCRIPT (user-defined)\n");
                sb.append(string);
                sb.append("\n# END OF CUSTOM SCRIPT (user-defined)\n\n");
            }
            if (needLog) {
                MdmLog.d(TAG_FIRE_WALL, "whitelist : " + zEquals + "; logenabled : " + z3);
            }
            if (zEquals) {
                sb.append("# Allow DNS lookups on white-list for a better logging (ignore errors)\n");
                sb.append("$IPTABLES -A xuehaifirewall -p udp --dport 53 -j RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-wifi -p tcp -m state —-state NEW -m tcp —-dport 53 -j ACCEPT RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-wifi -p udp -m state --state NEW -m udp --dport 53 -j ACCEPT RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-wifi -p udp -m udp --dport 53 -j ACCEPT RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-wifi -p tcp -m tcp --dport 53 -j ACCEPT RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-3g -p tcp -m state --state NEW -m tcp --dport 53 -j ACCEPT RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-3g -p udp -m state --state NEW -m udp --dport 53 -j ACCEPT RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-3g -p udp -m udp --dport 53 -j ACCEPT RETURN\n");
                sb.append("$IPTABLES -A xuehaifirewall-3g -p tcp -m tcp --dport 53 -j ACCEPT RETURN\n");
            }
            sb.append("# Main rules (per interface)\n");
            for (int i = 0; i < 8; i++) {
                String str3 = strArr2[i];
                sb.append("$IPTABLES -A xuehaifirewall -o ");
                sb.append(str3);
                sb.append(" -j xuehaifirewall-3g || exit\n");
            }
            for (int i2 = 0; i2 < 4; i2++) {
                String str4 = strArr[i2];
                sb.append("$IPTABLES -A xuehaifirewall -o ");
                sb.append(str4);
                sb.append(" -j xuehaifirewall-wifi || exit\n");
            }
            sb.append("# Filtering rules\n");
            String str5 = zEquals ? "RETURN" : "xuehaifirewall-reject";
            boolean z4 = list2.indexOf(-10) >= 0;
            boolean z5 = list.indexOf(-10) >= 0;
            if (zEquals && !z5) {
                int uidForName = Process.getUidForName("dhcp");
                if (uidForName != -1) {
                    sb.append("# dhcp user\n");
                    sb.append("$IPTABLES -A xuehaifirewall-wifi -m owner --uid-owner ");
                    sb.append(uidForName);
                    sb.append(" -j RETURN || exit\n");
                }
                int uidForName2 = Process.getUidForName("wifi");
                if (uidForName2 != -1) {
                    sb.append("# wifi user\n");
                    sb.append("$IPTABLES -A xuehaifirewall-wifi -m owner --uid-owner ");
                    sb.append(uidForName2);
                    sb.append(" -j RETURN || exit\n");
                }
            }
            if (!z4) {
                Iterator<Integer> it = list2.iterator();
                while (it.hasNext()) {
                    Iterator<Integer> it2 = it;
                    Integer next = it.next();
                    if (next.intValue() >= 0) {
                        str = str2;
                        sb.append("$IPTABLES -A xuehaifirewall-3g -m owner --uid-owner ");
                        sb.append(next);
                        sb.append(" -j ");
                        sb.append(str5);
                        sb.append(" || exit\n");
                    } else {
                        str = str2;
                    }
                    str2 = str;
                    it = it2;
                }
            } else if (z2) {
                sb.append("$IPTABLES -A xuehaifirewall-3g -j ");
                sb.append(str5);
                sb.append(" || exit\n");
            }
            String str6 = str2;
            if (!z5) {
                for (Integer num : list) {
                    if (num.intValue() >= 0) {
                        sb.append("$IPTABLES -A xuehaifirewall-wifi -m owner --uid-owner ");
                        sb.append(num);
                        sb.append(" -j ");
                        sb.append(str5);
                        sb.append(" || exit\n");
                    }
                }
            } else if (z2) {
                sb.append("$IPTABLES -A xuehaifirewall-wifi -j ");
                sb.append(str5);
                sb.append(" || exit\n");
            }
            if (zEquals) {
                if (!z4) {
                    if (list2.indexOf(-11) >= 0) {
                        sb.append("# hack to allow kernel packets on white-list\n");
                        sb.append("$IPTABLES -A xuehaifirewall-3g -m owner --uid-owner 0:999999999 -j xuehaifirewall-reject || exit\n");
                    } else {
                        sb.append("$IPTABLES -A xuehaifirewall-3g -j xuehaifirewall-reject || exit\n");
                    }
                }
                if (!z5) {
                    if (list.indexOf(-11) >= 0) {
                        sb.append("# hack to allow kernel packets on white-list\n");
                        sb.append("$IPTABLES -A xuehaifirewall-wifi -m owner --uid-owner 0:999999999 -j xuehaifirewall-reject || exit\n");
                    } else {
                        sb.append("$IPTABLES -A xuehaifirewall-wifi -j xuehaifirewall-reject || exit\n");
                    }
                }
            } else {
                if (list2.indexOf(-11) >= 0) {
                    sb.append("# hack to BLOCK kernel packets on black-list\n");
                    sb.append("$IPTABLES -A xuehaifirewall-3g -m owner --uid-owner 0:999999999 -j RETURN || exit\n");
                    sb.append("$IPTABLES -A xuehaifirewall-3g -j xuehaifirewall-reject || exit\n");
                }
                if (list.indexOf(-11) >= 0) {
                    sb.append("# hack to BLOCK kernel packets on black-list\n");
                    sb.append("$IPTABLES -A xuehaifirewall-wifi -m owner --uid-owner 0:999999999 -j RETURN || exit\n");
                    sb.append("$IPTABLES -A xuehaifirewall-wifi -j xuehaifirewall-reject || exit\n");
                }
            }
            StringBuilder sb2 = new StringBuilder();
            int iRunScriptAsRoot = runScriptAsRoot(context, sb.toString(), sb2);
            if (!z || iRunScriptAsRoot == 0) {
                return true;
            }
            String string2 = sb2.toString();
            if (needLog) {
                MdmLog.d(TAG_FIRE_WALL, "XueHaiFireWall" + string2);
            }
            if (string2.indexOf("\nTry `iptables -h' or 'iptables --help' for more information.") != -1) {
                string2 = string2.replace("\nTry `iptables -h' or 'iptables --help' for more information.", str6);
            }
            alert(context, "Error applying iptables rules. Exit code: " + iRunScriptAsRoot + "\n\n" + string2.trim());
            return false;
        } catch (Exception e) {
            if (!z) {
                return false;
            }
            alert(context, "error refreshing iptables: " + e);
            return false;
        }
    }

    public static boolean applyRules(Context context, List<String> list, boolean z) {
        if (context == null) {
            return false;
        }
        saveRules(context, DroidApp.build(context, list, true));
        return applySavedIptablesRules(context, z);
    }

    public static boolean applySavedIptablesRules(Context context, boolean z) {
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("XueHaiFireWallPrefs", 0);
        String string = sharedPreferences.getString("AllowedUidsWifi", "");
        String string2 = sharedPreferences.getString("AllowedUids3G", "");
        LinkedList linkedList = new LinkedList();
        if (string.length() > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(string, "|");
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                if (!"".equals(strNextToken)) {
                    try {
                        linkedList.add(Integer.valueOf(Integer.parseInt(strNextToken)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        LinkedList linkedList2 = new LinkedList();
        if (string2.length() > 0) {
            StringTokenizer stringTokenizer2 = new StringTokenizer(string2, "|");
            while (stringTokenizer2.hasMoreTokens()) {
                String strNextToken2 = stringTokenizer2.nextToken();
                if (!"".equals(strNextToken2)) {
                    try {
                        linkedList2.add(Integer.valueOf(Integer.parseInt(strNextToken2)));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return applyIptablesRulesImpl(context, linkedList, linkedList2, z);
    }

    public static boolean assertBinaries(Context context, boolean z) {
        try {
            File file = new File(context.getDir("bin", 0), "raw/iptables_armv5");
            if (!file.exists() || file.length() != 198652) {
                copyRawFile(context, R.raw.iptables_armv5, file, "755");
            }
            File file2 = new File(context.getDir("bin", 0), "raw/busybox_g1");
            if (file2.exists()) {
                return true;
            }
            copyRawFile(context, R.raw.busybox_g1, file2, "755");
            return true;
        } catch (Exception e) {
            if (z) {
                alert(context, "Error installing binary files: " + e);
            }
            return false;
        }
    }

    public static boolean clearLog(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            if (runScriptAsRoot(context, "dmesg -c >/dev/null || exit\n", sb) == 0) {
                return true;
            }
            alert(context, sb);
            return false;
        } catch (Exception e) {
            alert(context, "error: " + e);
            return false;
        }
    }

    private static void copyRawFile(Context context, int i, File file, String str) throws InterruptedException, Resources.NotFoundException, IOException {
        String absolutePath = file.getAbsolutePath();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(i);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i2 = inputStreamOpenRawResource.read(bArr);
                if (i2 <= 0) {
                    fileOutputStream.close();
                    inputStreamOpenRawResource.close();
                    Runtime.getRuntime().exec("chmod " + str + " " + absolutePath).waitFor();
                    return;
                }
                fileOutputStream.write(bArr, 0, i2);
            }
        } catch (Throwable th) {
            fileOutputStream.close();
            inputStreamOpenRawResource.close();
            throw th;
        }
    }

    public static boolean hasRootAccess(Context context, boolean z) {
        if (hasroot) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (runScriptAsRoot(context, "exit 0", sb) == 0) {
                hasroot = true;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!z) {
            return false;
        }
        alert(context, "Could not acquire root access.\nYou need a rooted phone to run XueHaiFireWall.\n\nIf this phone is already rooted, please make sure XueHaiFireWall has enough permissions to execute the \"su\" command.\nError message: " + sb.toString());
        return false;
    }

    public static boolean isEnabled(Context context) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences("XueHaiFireWallPrefs", 0).getBoolean("Enabled", false);
    }

    public static boolean purgeIptables(Context context, boolean z) {
        if (needLog) {
            MdmLog.i(TAG_FIRE_WALL, "清除规则 ...");
        }
        StringBuilder sb = new StringBuilder();
        try {
            assertBinaries(context, z);
            String string = context.getSharedPreferences("XueHaiFireWallPrefs", 0).getString("CustomScript2", "");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(scriptHeader(context));
            sb2.append("$IPTABLES -F xuehaifirewall\n$IPTABLES -F xuehaifirewall-reject\n$IPTABLES -F xuehaifirewall-3g\n$IPTABLES -F xuehaifirewall-wifi\n");
            if (string.length() > 0) {
                sb2.append("\n# BEGIN OF CUSTOM SCRIPT (user-defined)\n");
                sb2.append(string);
                sb2.append("\n# END OF CUSTOM SCRIPT (user-defined)\n\n");
            }
            int iRunScriptAsRoot = runScriptAsRoot(context, sb2.toString(), sb);
            if (iRunScriptAsRoot != -1) {
                return true;
            }
            if (z) {
                alert(context, "Error purging iptables. exit code: " + iRunScriptAsRoot + "\n" + ((Object) sb));
            }
            return false;
        } catch (Exception e) {
            if (z) {
                alert(context, "Error purging iptables: " + e);
            }
            return false;
        }
    }

    public static int runScript(Context context, String str, StringBuilder sb) throws IOException {
        return runScript(context, str, sb, 40000L, false);
    }

    public static int runScript(Context context, String str, StringBuilder sb, long j, boolean z) {
        ScriptRunner scriptRunner = new ScriptRunner(new File(context.getDir("bin", 0), "xuehaifirewall.sh"), str, sb, z);
        scriptRunner.start();
        try {
            if (j > 0) {
                scriptRunner.join(j);
            } else {
                scriptRunner.join();
            }
            if (scriptRunner.isAlive()) {
                scriptRunner.interrupt();
                scriptRunner.join(150L);
                scriptRunner.destroy();
                scriptRunner.join(50L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return scriptRunner.exitcode;
    }

    public static int runScriptAsRoot(Context context, String str, StringBuilder sb) throws IOException {
        return runScriptAsRoot(context, str, sb, 40000L);
    }

    public static int runScriptAsRoot(Context context, String str, StringBuilder sb, long j) {
        return runScript(context, str, sb, j, true);
    }

    public static void saveRules(Context context, SparseArray<DroidApp> sparseArray) {
        if (needLog) {
            MdmLog.i(TAG_FIRE_WALL, "保存规则...");
        }
        if (sparseArray == null || sparseArray.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < sparseArray.size(); i++) {
            DroidApp droidAppValueAt = sparseArray.valueAt(i);
            if (needLog) {
                MdmLog.d(TAG_FIRE_WALL, String.format("app = %s(%s); wifi:%s; 3g:%s", droidAppValueAt.getAppPackageName(), Integer.valueOf(droidAppValueAt.uid), Boolean.valueOf(droidAppValueAt.selectedWifi), Boolean.valueOf(droidAppValueAt.selected3g)));
            }
            if (droidAppValueAt.selectedWifi) {
                if (sb.length() != 0) {
                    sb.append('|');
                }
                sb.append(droidAppValueAt.uid);
            }
            if (droidAppValueAt.selected3g) {
                if (sb2.length() != 0) {
                    sb2.append('|');
                }
                sb2.append(droidAppValueAt.uid);
            }
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("XueHaiFireWallPrefs", 0).edit();
        editorEdit.putString("AllowedUidsWifi", sb.toString());
        editorEdit.putString("AllowedUids3G", sb2.toString());
        editorEdit.apply();
    }

    private static String scriptHeader(Context context) {
        String absolutePath = context.getDir("bin", 0).getAbsolutePath();
        String str = absolutePath + "/raw/iptables_armv5";
        return "IPTABLES=iptables\nBUSYBOX=busybox\nGREP=grep\nECHO=echo\n# Try to find busybox\nif " + absolutePath + "/busybox_g1 --help >/dev/null 2>/dev/null ; then\n\tBUSYBOX=" + absolutePath + "/raw/busybox_g1\n\tGREP=\"$BUSYBOX grep\"\n\tECHO=\"$BUSYBOX echo\"\nelif busybox --help >/dev/null 2>/dev/null ; then\n\tBUSYBOX=busybox\nelif /system/xbin/busybox --help >/dev/null 2>/dev/null ; then\n\tBUSYBOX=/system/xbin/busybox\nelif /system/bin/busybox --help >/dev/null 2>/dev/null ; then\n\tBUSYBOX=/system/bin/busybox\nfi\n# Try to find grep\nif ! $ECHO 1 | $GREP -q 1 >/dev/null 2>/dev/null ; then\n\tif $ECHO 1 | $BUSYBOX grep -q 1 >/dev/null 2>/dev/null ; then\n\t\tGREP=\"$BUSYBOX grep\"\n\tfi\n\t# Grep is absolutely required\n\tif ! $ECHO 1 | $GREP -q 1 >/dev/null 2>/dev/null ; then\n\t\t$ECHO The grep command is required. XueHaiFireWall will not work.\n\t\texit 1\n\tfi\nfi\n# Try to find iptables\n# Added if iptables binary already in system then use it, if not use implemented one\nif ! command -v iptables &> /dev/null; then\nif " + str + " --version >/dev/null 2>/dev/null ; then\n\tIPTABLES=" + str + "\nfi\nfi\n";
    }

    public static void setEnabled(Context context, boolean z) {
        if (needLog) {
            MdmLog.i(TAG_FIRE_WALL, "设置防火墙可用...");
        }
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("XueHaiFireWallPrefs", 0);
        if (sharedPreferences.getBoolean("Enabled", false) == z) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean("Enabled", z);
        if (!editorEdit.commit()) {
            alert(context, "Error writing to preferences");
            return;
        }
        Intent intent = new Intent("com.googlecode.xuehaifirewall.intent.action.STATUS_CHANGED");
        intent.putExtra("com.googlecode.xuehaifirewall.intent.extra.STATUS", z);
        context.sendBroadcast(intent);
    }

    public static void setNeedLog(boolean z) {
        needLog = z;
    }

    public static void showIptablesRules(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            runScriptAsRoot(context, scriptHeader(context) + "$ECHO $IPTABLES\n$IPTABLES -L -v -n\n", sb);
            alert(context, sb);
        } catch (Exception e) {
            alert(context, "error: " + e);
        }
    }
}

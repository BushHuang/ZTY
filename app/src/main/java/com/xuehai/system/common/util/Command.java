package com.xuehai.system.common.util;

import com.xuehai.system.common.log.MdmLog;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Command {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";
    private static final String TAG_CMD = "Command:";
    private static Boolean isRoot;
    private static final Object object = new Object();
    private static boolean showLog = false;

    public static class CommandResult {
        private static int SUCCESS;
        public int code;
        public String errorMsg;
        public List<String> successList;
        public String successMsg;

        public CommandResult(int i) {
            this.successMsg = "";
            this.code = i;
        }

        public CommandResult(int i, String str, List<String> list) {
            this.successMsg = "";
            this.code = i;
            this.successList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            if (list != null && !list.isEmpty()) {
                this.successList.addAll(list);
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                }
            }
            this.successMsg = sb.toString();
            this.errorMsg = str;
        }

        public boolean isSuccess() {
            return this.code == SUCCESS;
        }

        public String toString() {
            return "CommandResult{code=" + this.code + ", successMsg='" + this.successMsg + "', successList=" + this.successList + ", errorMsg='" + this.errorMsg + "'}";
        }
    }

    public static int execCmd(String str) {
        return execCmd(new String[]{str});
    }

    public static int execCmd(String[] strArr) {
        return execCommand(strArr, false, false).code;
    }

    public static CommandResult execCmdForRes(String str) {
        return execCmdForRes(new String[]{str});
    }

    public static CommandResult execCmdForRes(String[] strArr) {
        return execCommand(strArr, false, true);
    }

    public static CommandResult execCommand(String[] strArr, boolean z, boolean z2) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        ArrayList arrayList;
        StringBuilder sb;
        DataOutputStream dataOutputStream;
        ArrayList arrayList2;
        Process processExec;
        Exception e;
        DataOutputStream dataOutputStream2 = null;
        int iWaitFor = -1;
        if (strArr == null || strArr.length == 0) {
            return new CommandResult(-1, null, null);
        }
        try {
            processExec = Runtime.getRuntime().exec(z ? "su" : "sh");
            dataOutputStream = new DataOutputStream(processExec.getOutputStream());
        } catch (Exception e2) {
            e = e2;
            arrayList = null;
            sb = null;
            dataOutputStream = null;
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
            bufferedReader2 = null;
            if (dataOutputStream2 != null) {
            }
            if (bufferedReader != null) {
            }
            if (bufferedReader2 != null) {
            }
            throw th;
        }
        try {
            try {
                for (String str : strArr) {
                    if (str != null) {
                        if (showLog) {
                            MdmLog.i("Command:", "command ： " + str + "\n");
                        }
                        dataOutputStream.write(str.getBytes());
                        dataOutputStream.writeBytes("\n");
                        dataOutputStream.flush();
                    }
                }
                dataOutputStream.writeBytes("exit\n");
                dataOutputStream.flush();
                iWaitFor = processExec.waitFor();
                if (z2) {
                    arrayList2 = new ArrayList();
                    try {
                        sb = new StringBuilder();
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                            try {
                                bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
                                while (true) {
                                    try {
                                        try {
                                            String line = bufferedReader.readLine();
                                            if (line == null) {
                                                break;
                                            }
                                            arrayList2.add(line);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            dataOutputStream2 = dataOutputStream;
                                            if (dataOutputStream2 != null) {
                                                try {
                                                    dataOutputStream2.close();
                                                } catch (IOException e3) {
                                                    e3.printStackTrace();
                                                    throw th;
                                                }
                                            }
                                            if (bufferedReader != null) {
                                                bufferedReader.close();
                                            }
                                            if (bufferedReader2 != null) {
                                                bufferedReader2.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Exception e4) {
                                        e = e4;
                                        Exception exc = e;
                                        arrayList = arrayList2;
                                        e = exc;
                                        e.printStackTrace();
                                        if (dataOutputStream != null) {
                                            try {
                                                dataOutputStream.close();
                                            } catch (IOException e5) {
                                                e5.printStackTrace();
                                                arrayList2 = arrayList;
                                                return new CommandResult(iWaitFor, sb != null ? sb.toString() : null, arrayList2);
                                            }
                                        }
                                        if (bufferedReader != null) {
                                            bufferedReader.close();
                                        }
                                        if (bufferedReader2 != null) {
                                            bufferedReader2.close();
                                        }
                                        arrayList2 = arrayList;
                                        return new CommandResult(iWaitFor, sb != null ? sb.toString() : null, arrayList2);
                                    }
                                }
                                while (true) {
                                    String line2 = bufferedReader2.readLine();
                                    if (line2 == null) {
                                        break;
                                    }
                                    sb.append(line2);
                                }
                            } catch (Exception e6) {
                                e = e6;
                                bufferedReader2 = null;
                                Exception exc2 = e;
                                arrayList = arrayList2;
                                e = exc2;
                                e.printStackTrace();
                                if (dataOutputStream != null) {
                                }
                                if (bufferedReader != null) {
                                }
                                if (bufferedReader2 != null) {
                                }
                                arrayList2 = arrayList;
                                return new CommandResult(iWaitFor, sb != null ? sb.toString() : null, arrayList2);
                            } catch (Throwable th3) {
                                th = th3;
                                bufferedReader2 = null;
                                dataOutputStream2 = dataOutputStream;
                                if (dataOutputStream2 != null) {
                                }
                                if (bufferedReader != null) {
                                }
                                if (bufferedReader2 != null) {
                                }
                                throw th;
                            }
                        } catch (Exception e7) {
                            e = e7;
                            bufferedReader = null;
                            bufferedReader2 = bufferedReader;
                            Exception exc22 = e;
                            arrayList = arrayList2;
                            e = exc22;
                            e.printStackTrace();
                            if (dataOutputStream != null) {
                            }
                            if (bufferedReader != null) {
                            }
                            if (bufferedReader2 != null) {
                            }
                            arrayList2 = arrayList;
                            return new CommandResult(iWaitFor, sb != null ? sb.toString() : null, arrayList2);
                        }
                    } catch (Exception e8) {
                        e = e8;
                        sb = null;
                        bufferedReader = null;
                    }
                } else {
                    arrayList2 = null;
                    sb = null;
                    bufferedReader = null;
                    bufferedReader2 = null;
                }
                processExec.destroy();
                try {
                    dataOutputStream.close();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
                bufferedReader2 = null;
            }
        } catch (Exception e10) {
            e = e10;
            arrayList = null;
            sb = null;
            bufferedReader = null;
            bufferedReader2 = bufferedReader;
            e.printStackTrace();
            if (dataOutputStream != null) {
            }
            if (bufferedReader != null) {
            }
            if (bufferedReader2 != null) {
            }
            arrayList2 = arrayList;
            return new CommandResult(iWaitFor, sb != null ? sb.toString() : null, arrayList2);
        }
        return new CommandResult(iWaitFor, sb != null ? sb.toString() : null, arrayList2);
    }

    public static int execRootCmd(String str) {
        return execRootCmd(new String[]{str});
    }

    public static int execRootCmd(String[] strArr) {
        return execCommand(strArr, true, false).code;
    }

    public static CommandResult execRootCmdForRes(String str) {
        return execRootCmdForRes(new String[]{str});
    }

    public static CommandResult execRootCmdForRes(String[] strArr) {
        return execCommand(strArr, true, true);
    }

    public static boolean isCommandExists(String str) {
        return execCmdForRes("command -v " + str).isSuccess();
    }

    public static boolean isRoot() {
        DataOutputStream dataOutputStream;
        Throwable th;
        Process processExec;
        DataOutputStream dataOutputStream2;
        Exception e;
        synchronized (object) {
            if (isRoot == null) {
                try {
                    try {
                        processExec = Runtime.getRuntime().exec("su");
                        try {
                            dataOutputStream2 = new DataOutputStream(processExec.getOutputStream());
                        } catch (Exception e2) {
                            dataOutputStream2 = null;
                            e = e2;
                        } catch (Throwable th2) {
                            dataOutputStream = null;
                            th = th2;
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (processExec == null) {
                                throw th;
                            }
                            processExec.destroy();
                            throw th;
                        }
                    } catch (Exception e4) {
                        dataOutputStream2 = null;
                        e = e4;
                        processExec = null;
                    } catch (Throwable th3) {
                        dataOutputStream = null;
                        th = th3;
                        processExec = null;
                    }
                    try {
                        dataOutputStream2.writeBytes("exit\n");
                        dataOutputStream2.flush();
                        if (isSuccess(processExec.waitFor())) {
                            if (showLog) {
                                MdmLog.i("Command:", "设备已Root");
                            }
                            isRoot = true;
                        }
                        try {
                            dataOutputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    } catch (Exception e6) {
                        e = e6;
                        MdmLog.w("Command:", "设备未Root", e);
                        if (dataOutputStream2 != null) {
                            try {
                                dataOutputStream2.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        if (processExec != null) {
                            processExec.destroy();
                        }
                        if (isRoot == null) {
                        }
                        return isRoot.booleanValue();
                    }
                    if (processExec != null) {
                        processExec.destroy();
                    }
                    if (isRoot == null) {
                        isRoot = false;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
        return isRoot.booleanValue();
    }

    public static boolean isSuccess(int i) {
        return i == CommandResult.SUCCESS;
    }

    public static void reboot() {
        if (isRoot()) {
            execRootCmd("reboot");
        } else {
            execCmd("reboot");
        }
    }

    public static void setShowLog(boolean z) {
        showLog = z;
    }
}

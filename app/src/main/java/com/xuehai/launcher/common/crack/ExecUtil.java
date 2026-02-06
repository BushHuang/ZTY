package com.xuehai.launcher.common.crack;

import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u00060\tj\u0002`\n0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/xuehai/launcher/common/crack/ExecUtil;", "", "()V", "SUCCESS", "", "getSUCCESS", "()I", "exec", "Lkotlin/Pair;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "commands", "", "", "([Ljava/lang/String;)Lkotlin/Pair;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecUtil {
    public static final ExecUtil INSTANCE = new ExecUtil();
    private static final int SUCCESS = 0;

    private ExecUtil() {
    }

    public final Pair<Integer, StringBuilder> exec(String[] commands) {
        Process processExec;
        DataOutputStream dataOutputStream;
        Intrinsics.checkNotNullParameter(commands, "commands");
        StringBuilder sb = new StringBuilder();
        DataOutputStream dataOutputStream2 = null;
        int iWaitFor = -1;
        try {
            processExec = Runtime.getRuntime().exec(commands[0]);
            try {
                dataOutputStream = new DataOutputStream(processExec.getOutputStream());
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            processExec = null;
        }
        try {
            int length = commands.length;
            for (int i = 0; i < length; i++) {
                String str = commands[i];
                if (i != 0) {
                    if ((str.length() > 0) && (!StringsKt.isBlank(str))) {
                        byte[] bytes = str.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                        dataOutputStream.write(bytes);
                        dataOutputStream.writeBytes("\n");
                    }
                }
            }
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            iWaitFor = processExec.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iWaitFor == SUCCESS ? processExec.getInputStream() : processExec.getErrorStream()));
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                sb.append(line + '\n');
            }
            try {
                dataOutputStream.close();
            } catch (Exception unused) {
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream2 = dataOutputStream;
            try {
                Log.w("CheckQemu", "exec error", th);
                try {
                    DataOutputStream dataOutputStream3 = dataOutputStream2;
                    if (dataOutputStream3 != null) {
                        dataOutputStream3.close();
                    }
                } catch (Exception unused2) {
                }
                if (processExec != null) {
                }
                return new Pair<>(Integer.valueOf(iWaitFor), sb);
            } finally {
            }
        }
        if (processExec != null) {
            processExec.destroy();
        }
        return new Pair<>(Integer.valueOf(iWaitFor), sb);
    }

    public final int getSUCCESS() {
        return SUCCESS;
    }
}

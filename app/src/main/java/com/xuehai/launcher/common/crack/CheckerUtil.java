package com.xuehai.launcher.common.crack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007J\u0014\u0010\n\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Lcom/xuehai/launcher/common/crack/CheckerUtil;", "", "()V", "readByBytes", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "inputStream", "Ljava/io/InputStream;", "bufferSize", "", "readLine", "reader", "Ljava/io/Reader;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CheckerUtil {
    public static final CheckerUtil INSTANCE = new CheckerUtil();

    private CheckerUtil() {
    }

    @JvmStatic
    public static final StringBuilder readByBytes(InputStream inputStream, int bufferSize) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        StringBuilder sb = new StringBuilder();
        try {
            try {
                try {
                    byte[] bArr = new byte[bufferSize];
                    int i = inputStream.read(bArr);
                    while (i != -1) {
                        sb.append(new String(bArr, 0, i, Charsets.UTF_8));
                        i = inputStream.read(bArr);
                    }
                    inputStream.close();
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                inputStream.close();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return sb;
    }

    public static StringBuilder readByBytes$default(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1024;
        }
        return readByBytes(inputStream, i);
    }

    @JvmStatic
    public static final StringBuilder readLine(Reader reader) throws Throwable {
        Intrinsics.checkNotNullParameter(reader, "reader");
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(reader);
                    try {
                        for (String line = bufferedReader2.readLine(); line != null; line = bufferedReader2.readLine()) {
                            sb.append(line);
                        }
                        bufferedReader2.close();
                    } catch (Exception e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return sb;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return sb;
    }
}

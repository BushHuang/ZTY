package com.analysys;

import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class aj {
    public static String a(byte[] bArr) {
        GZIPInputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byteArrayInputStream = new ByteArrayInputStream(bArr);
                        try {
                            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                        } catch (Throwable th) {
                            th = th;
                            gZIPInputStream = null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        gZIPInputStream = null;
                        byteArrayInputStream = null;
                    }
                    try {
                        byte[] bArr2 = new byte[256];
                        while (true) {
                            int i = gZIPInputStream.read(bArr2, 0, 256);
                            if (i <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, i);
                        }
                        String string = byteArrayOutputStream.toString();
                        try {
                            gZIPInputStream.close();
                        } catch (Throwable th3) {
                            ExceptionUtil.exceptionThrow(th3);
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (Throwable th4) {
                            ExceptionUtil.exceptionThrow(th4);
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th5) {
                            ExceptionUtil.exceptionThrow(th5);
                        }
                        return string;
                    } catch (Throwable th6) {
                        th = th6;
                        try {
                            ExceptionUtil.exceptionThrow(th);
                            if (gZIPInputStream != null) {
                                try {
                                    gZIPInputStream.close();
                                } catch (Throwable th7) {
                                    ExceptionUtil.exceptionThrow(th7);
                                }
                            }
                            if (byteArrayInputStream != null) {
                                try {
                                    byteArrayInputStream.close();
                                } catch (Throwable th8) {
                                    ExceptionUtil.exceptionThrow(th8);
                                }
                            }
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Throwable th9) {
                                    ExceptionUtil.exceptionThrow(th9);
                                }
                            }
                            return null;
                        } finally {
                        }
                    }
                }
            } catch (Throwable th10) {
                th = th10;
                gZIPInputStream = null;
                byteArrayOutputStream = null;
                byteArrayInputStream = null;
            }
        }
        return null;
    }

    public static byte[] a(String str) throws IOException {
        if (CommonUtils.isEmpty(str)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes());
        gZIPOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        return byteArray;
    }
}

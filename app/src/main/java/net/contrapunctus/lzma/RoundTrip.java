package net.contrapunctus.lzma;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RoundTrip {
    static final boolean $assertionsDisabled = false;

    public static void main(String[] strArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(new LzmaOutputStream(byteArrayOutputStream));
        printStream.print("Yes yes yes test test test.");
        printStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        for (byte b : byteArray) {
            System.out.printf("%02x ", Byte.valueOf(b));
        }
        System.out.println();
        System.out.println(new BufferedReader(new InputStreamReader(new LzmaInputStream(new ByteArrayInputStream(byteArray)))).readLine());
        System.out.println("Yes yes yes test test test.");
    }
}

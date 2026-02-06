package com.zaze.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\t"}, d2 = {"Lcom/zaze/utils/ConvertUtil;", "", "()V", "byteToObject", "bytes", "", "objectToByte", "obj", "Ljava/io/Serializable;", "util_release"}, k = 1, mv = {1, 4, 1})
public final class ConvertUtil {
    public static final ConvertUtil INSTANCE = new ConvertUtil();

    private ConvertUtil() {
    }

    @JvmStatic
    public static final Object byteToObject(byte[] bytes) throws ClassNotFoundException, IOException {
        Object object = null;
        if (bytes == null) {
            return null;
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
            byteArrayInputStream.close();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return object;
        }
    }

    @JvmStatic
    public static final byte[] objectToByte(Serializable obj) throws IOException {
        byte[] byteArray = (byte[]) null;
        if (obj == null) {
            return byteArray;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            objectOutputStream.close();
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
            return byteArray;
        }
    }
}

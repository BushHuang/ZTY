package com.xh.xhcore.common.http.archi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CloneUtils {
    public static <T extends Serializable> T clone(T t) throws IOException {
        Exception e;
        T t2;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            t2 = (T) objectInputStream.readObject();
            try {
                objectInputStream.close();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return t2;
            }
        } catch (Exception e3) {
            e = e3;
            t2 = null;
        }
        return t2;
    }
}

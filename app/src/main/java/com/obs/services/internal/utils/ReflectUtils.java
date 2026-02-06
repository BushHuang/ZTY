package com.obs.services.internal.utils;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.ServiceException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectUtils {
    private static Class<?> androidBase64Class;
    private static Map<String, Field> fields = new ConcurrentHashMap();
    private static Class<?> jdkBase64DecoderClass;
    private static Class<?> jdkBase64EncoderClass;
    private static Object jdkNewDecoder;
    private static Object jdkNewEncoder;

    static {
        try {
            androidBase64Class = Class.forName("android.util.Base64");
        } catch (ClassNotFoundException unused) {
        }
        try {
            Class<?> cls = Class.forName("java.util.Base64");
            jdkNewEncoder = cls.getMethod("getEncoder", new Class[0]).invoke(null, new Object[0]);
            jdkNewDecoder = cls.getMethod("getDecoder", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused2) {
        }
        try {
            jdkBase64EncoderClass = Class.forName("sun.misc.BASE64Encoder");
        } catch (ClassNotFoundException unused3) {
        }
        try {
            jdkBase64DecoderClass = Class.forName("sun.misc.BASE64Decoder");
        } catch (ClassNotFoundException unused4) {
        }
    }

    public static byte[] fromBase64(String str) throws UnsupportedEncodingException {
        Class<?> cls = androidBase64Class;
        if (cls != null) {
            try {
                return (byte[]) cls.getMethod("decode", byte[].class, Integer.TYPE).invoke(null, str.getBytes("UTF-8"), 2);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
        Object obj = jdkNewDecoder;
        if (obj != null) {
            try {
                return (byte[]) obj.getClass().getMethod("decode", byte[].class).invoke(jdkNewDecoder, str.getBytes("UTF-8"));
            } catch (Exception e2) {
                throw new ServiceException(e2);
            }
        }
        Class<?> cls2 = jdkBase64DecoderClass;
        if (cls2 == null) {
            throw new ServiceException("Failed to find a base64 decoder");
        }
        try {
            return (byte[]) cls2.getMethod("decodeBuffer", String.class).invoke(jdkBase64DecoderClass.getConstructor(new Class[0]).newInstance(new Object[0]), str);
        } catch (Exception e3) {
            throw new ServiceException(e3);
        }
    }

    private static Field getFieldFromClass(Class<?> cls, String str) {
        do {
            try {
                return cls.getDeclaredField(str);
            } catch (NoSuchFieldException unused) {
                cls = cls.getSuperclass();
            }
        } while (cls != null);
        return null;
    }

    public static void setInnerClient(Object obj, ObsClient obsClient) throws IllegalAccessException, IllegalArgumentException {
        if (obj == null || obsClient == null) {
            return;
        }
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        Field fieldFromClass = fields.get(name);
        if (fieldFromClass == null) {
            try {
                fieldFromClass = getFieldFromClass(cls, "innerClient");
                fieldFromClass.setAccessible(true);
                fields.put(name, fieldFromClass);
            } catch (Exception e) {
                throw new ObsException(e.getMessage(), e);
            }
        }
        fieldFromClass.set(obj, obsClient);
    }

    public static String toBase64(byte[] bArr) {
        Class<?> cls = androidBase64Class;
        if (cls != null) {
            try {
                return new String((byte[]) cls.getMethod("encode", byte[].class, Integer.TYPE).invoke(null, bArr, 2));
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
        Object obj = jdkNewEncoder;
        if (obj != null) {
            try {
                return new String((byte[]) obj.getClass().getMethod("encode", byte[].class).invoke(jdkNewEncoder, bArr), "UTF-8").replaceAll("\\s", "");
            } catch (Exception e2) {
                throw new ServiceException(e2);
            }
        }
        Class<?> cls2 = jdkBase64EncoderClass;
        if (cls2 == null) {
            throw new ServiceException("Failed to find a base64 encoder");
        }
        try {
            return ((String) cls2.getMethod("encode", byte[].class).invoke(jdkBase64EncoderClass.getConstructor(new Class[0]).newInstance(new Object[0]), bArr)).replaceAll("\\s", "");
        } catch (Exception e3) {
            throw new ServiceException(e3);
        }
    }
}

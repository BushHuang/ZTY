package com.huawei.agconnect.config.a;

import android.content.Context;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

class i extends h {

    private final Map<String, String> f171a;
    private final Object b;
    private SecretKey c;
    private boolean d;

    i(Context context, String str) {
        super(context, str);
        this.f171a = new HashMap();
        this.b = new Object();
        this.d = true;
        try {
            String strA = a("/AD91D45E3E72DB6989DDCB13287E75061FABCB933D886E6C6ABEF0939B577138");
            String strA2 = a("/B314B3BF013DF5AC4134E880AF3D2B7C9FFBE8F0305EAC1C898145E2BCF1F21C");
            String strA3 = a("/C767BD8FDF53E53D059BE95B09E2A71056F5F180AECC62836B287ACA5793421B");
            String strA4 = a("/DCB3E6D4C2CF80F30D89CDBC412C964DA8381BB84668769391FBCC3E329AD0FD");
            if (strA == null || strA2 == null || strA3 == null || strA4 == null) {
                this.d = false;
            } else {
                this.c = g.a(e.a(strA), e.a(strA2), e.a(strA3), e.a(strA4));
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException unused) {
            Log.e("SecurityResourcesReader", "Exception when reading the 'K&I' for 'Config'.");
            this.c = null;
        }
    }

    private String a(String str) {
        return super.a(str, (String) null);
    }

    private static byte[] a(SecretKey secretKey, byte[] bArr) throws GeneralSecurityException {
        if (secretKey == null || bArr == null) {
            throw new NullPointerException("key or cipherText must not be null.");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 1, 17);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKey, new IvParameterSpec(bArrCopyOfRange));
        return cipher.doFinal(bArr, bArrCopyOfRange.length + 1, (bArr.length - bArrCopyOfRange.length) - 1);
    }

    @Override
    public String a(String str, String str2) {
        if (!this.d) {
            String strA = a(str);
            return strA != null ? strA : str2;
        }
        if (this.c == null) {
            Log.e("SecurityResourcesReader", "KEY is null return def directly");
            return str2;
        }
        synchronized (this.b) {
            String str3 = this.f171a.get(str);
            if (str3 != null) {
                return str3;
            }
            String strA2 = a(str);
            if (strA2 == null) {
                return str2;
            }
            try {
                String str4 = new String(a(this.c, e.a(strA2)), "UTF-8");
                this.f171a.put(str, str4);
                return str4;
            } catch (UnsupportedEncodingException | GeneralSecurityException unused) {
                Log.e("SecurityResourcesReader", "Exception when reading the 'V' for 'Config'.");
                return str2;
            }
        }
    }

    public String toString() {
        return "SecurityResourcesReader{mKey=" + this.c.hashCode() + ", encrypt=" + this.d + '}';
    }
}

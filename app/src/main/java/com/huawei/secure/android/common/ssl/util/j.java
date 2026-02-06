package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class j {
    private static final String b = "X509CertificateUtil";
    public static final String c = "hmsrootcas.bks";
    public static final String d = "";
    public static final String e = "bks";
    public static final String f = "052root";
    private static final String g = "hmsincas.bks";
    private static final String h = "huawei cbg application integration ca";

    private Context f431a;

    public j(Context context) {
        this.f431a = context;
    }

    public X509Certificate a() {
        return a("hmsincas.bks", "huawei cbg application integration ca");
    }

    public X509Certificate a(String str, String str2) throws Throwable {
        InputStream inputStreamOpen;
        KeyStore keyStore;
        InputStream inputStream = null;
        X509Certificate x509Certificate = null;
        try {
            try {
                keyStore = KeyStore.getInstance("bks");
                inputStreamOpen = this.f431a.getAssets().open(str);
            } catch (IOException e2) {
                e = e2;
                inputStreamOpen = null;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            } catch (KeyStoreException e3) {
                e = e3;
                inputStreamOpen = null;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            } catch (NoSuchAlgorithmException e4) {
                e = e4;
                inputStreamOpen = null;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            } catch (CertificateException e5) {
                e = e5;
                inputStreamOpen = null;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            } catch (Throwable th) {
                th = th;
                f.a(inputStream);
                throw th;
            }
            try {
                inputStreamOpen.reset();
                keyStore.load(inputStreamOpen, "".toCharArray());
                x509Certificate = (X509Certificate) keyStore.getCertificate(str2);
                str = inputStreamOpen;
            } catch (IOException e6) {
                e = e6;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            } catch (KeyStoreException e7) {
                e = e7;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            } catch (NoSuchAlgorithmException e8) {
                e = e8;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            } catch (CertificateException e9) {
                e = e9;
                g.b("X509CertificateUtil", "loadBksCA: exception : " + e.getMessage());
                str = inputStreamOpen;
                f.a((InputStream) str);
                return x509Certificate;
            }
            f.a((InputStream) str);
            return x509Certificate;
        } catch (Throwable th2) {
            th = th2;
            inputStream = str;
            f.a(inputStream);
            throw th;
        }
    }

    public X509Certificate b() {
        return a("hmsrootcas.bks", "052root");
    }
}

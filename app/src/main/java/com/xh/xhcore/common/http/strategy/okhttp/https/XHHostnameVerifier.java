package com.xh.xhcore.common.http.strategy.okhttp.https;

import android.text.TextUtils;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000bJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u0016\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bJ\u0018\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\u001a\u0010\u0016\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\tJ\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/https/XHHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "customVerifyHostName", "", "hostname", "", "firstCertificate", "Ljava/security/cert/X509Certificate;", "getCNString", "x509Certificate", "getSubjectAltNames", "", "certificate", "type", "verify", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "pattern", "verifyIpAddress", "ipAddress", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHHostnameVerifier implements HostnameVerifier {

    public static final Companion INSTANCE = new Companion(null);
    private static final XHHostnameVerifier INSTANCE = new XHHostnameVerifier();
    private final int ALT_DNS_NAME = 2;
    private final int ALT_IPA_NAME = 7;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/https/XHHostnameVerifier$Companion;", "", "()V", "INSTANCE", "Lcom/xh/xhcore/common/http/strategy/okhttp/https/XHHostnameVerifier;", "getINSTANCE", "()Lcom/xh/xhcore/common/http/strategy/okhttp/https/XHHostnameVerifier;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final XHHostnameVerifier getINSTANCE() {
            return XHHostnameVerifier.INSTANCE;
        }
    }

    private XHHostnameVerifier() {
    }

    private final boolean customVerifyHostName(String hostname, X509Certificate firstCertificate) {
        String strReplace$default;
        try {
            String cNString = getCNString(firstCertificate);
            if (TextUtils.isEmpty(hostname) || TextUtils.isEmpty(cNString)) {
                return false;
            }
            String strReplace$default2 = null;
            if (cNString != null && (strReplace$default = StringsKt.replace$default(cNString, ".", "\\.", false, 4, (Object) null)) != null) {
                strReplace$default2 = StringsKt.replace$default(strReplace$default, "*", ".*", false, 4, (Object) null);
            }
            return Pattern.matches(strReplace$default2, hostname);
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private final List<String> getSubjectAltNames(X509Certificate certificate, int type) throws CertificateParsingException {
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return CollectionsKt.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
                }
                if (list.size() >= 2) {
                    Object obj = list.get(0);
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                    }
                    if (((Integer) obj).intValue() != type) {
                        continue;
                    } else {
                        Object obj2 = list.get(1);
                        if (obj2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        arrayList.add((String) obj2);
                    }
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return CollectionsKt.emptyList();
        }
    }

    private final boolean verifyHostname(String hostname, X509Certificate certificate) {
        Locale locale = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale, "US");
        String lowerCase = hostname.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        Iterator<String> it = getSubjectAltNames(certificate, this.ALT_DNS_NAME).iterator();
        while (it.hasNext()) {
            if (verifyHostname(lowerCase, it.next())) {
                return true;
            }
        }
        return false;
    }

    private final boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
        return true;
    }

    public final String getCNString(X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(x509Certificate, "x509Certificate");
        String name = x509Certificate.getSubjectX500Principal().getName();
        Intrinsics.checkNotNullExpressionValue(name, "principalName");
        for (String str : StringsKt.split$default((CharSequence) name, new String[]{","}, false, 0, 6, (Object) null)) {
            String str2 = str;
            if (!TextUtils.isEmpty(str2) && StringsKt.startsWith$default(StringsKt.trim((CharSequence) str2).toString(), "CN=", false, 2, (Object) null)) {
                String strSubstring = str.substring(3);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                return strSubstring;
            }
        }
        return null;
    }

    public final boolean verify(String host, X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return Util.verifyAsIpAddress(host) ? verifyIpAddress(host, certificate) : verifyHostname(host, certificate);
    }

    @Override
    public boolean verify(String host, SSLSession session) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(session, "session");
        try {
            Certificate certificate = session.getPeerCertificates()[0];
            if (certificate != null) {
                return verify(host, (X509Certificate) certificate);
            }
            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        } catch (SSLException unused) {
            return false;
        }
    }

    public final boolean verifyHostname(String hostname, String pattern) {
        if (hostname == null || hostname.length() == 0 || StringsKt.startsWith$default(hostname, ".", false, 2, (Object) null) || StringsKt.endsWith$default(hostname, "..", false, 2, (Object) null) || pattern == null || pattern.length() == 0 || StringsKt.startsWith$default(pattern, ".", false, 2, (Object) null) || StringsKt.endsWith$default(pattern, "..", false, 2, (Object) null)) {
            return false;
        }
        if (!StringsKt.endsWith$default(hostname, ".", false, 2, (Object) null)) {
            hostname = Intrinsics.stringPlus(hostname, ".");
        }
        if (!StringsKt.endsWith$default(pattern, ".", false, 2, (Object) null)) {
            pattern = Intrinsics.stringPlus(pattern, ".");
        }
        Locale locale = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale, "US");
        String lowerCase = pattern.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        String str = lowerCase;
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "*", false, 2, (Object) null)) {
            return Intrinsics.areEqual(hostname, lowerCase);
        }
        if (!StringsKt.startsWith$default(lowerCase, "*.", false, 2, (Object) null) || StringsKt.indexOf$default((CharSequence) str, '*', 1, false, 4, (Object) null) != -1 || hostname.length() < lowerCase.length() || Intrinsics.areEqual("*.", lowerCase)) {
            return false;
        }
        String strSubstring = lowerCase.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        if (!StringsKt.endsWith$default(hostname, strSubstring, false, 2, (Object) null)) {
            return false;
        }
        int length = hostname.length() - strSubstring.length();
        return length <= 0 || StringsKt.lastIndexOf$default((CharSequence) hostname, '.', length + (-1), false, 4, (Object) null) == -1;
    }
}

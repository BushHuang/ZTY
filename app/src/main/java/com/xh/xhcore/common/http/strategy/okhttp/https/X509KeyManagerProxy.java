package com.xh.xhcore.common.http.strategy.okhttp.https;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509KeyManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J1\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ+\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\u0006\u0010\u000e\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u0012J)\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u000e\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007H\u0016¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J)\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u000e\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007H\u0016¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/okhttp/https/X509KeyManagerProxy;", "Ljavax/net/ssl/X509KeyManager;", "keyManager", "(Ljavax/net/ssl/X509KeyManager;)V", "chooseClientAlias", "", "strings", "", "principals", "Ljava/security/Principal;", "socket", "Ljava/net/Socket;", "([Ljava/lang/String;[Ljava/security/Principal;Ljava/net/Socket;)Ljava/lang/String;", "chooseServerAlias", "s", "(Ljava/lang/String;[Ljava/security/Principal;Ljava/net/Socket;)Ljava/lang/String;", "getCertificateChain", "Ljava/security/cert/X509Certificate;", "(Ljava/lang/String;)[Ljava/security/cert/X509Certificate;", "getClientAliases", "(Ljava/lang/String;[Ljava/security/Principal;)[Ljava/lang/String;", "getPrivateKey", "Ljava/security/PrivateKey;", "getServerAliases", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class X509KeyManagerProxy implements X509KeyManager {
    private final X509KeyManager keyManager;

    public X509KeyManagerProxy(X509KeyManager x509KeyManager) {
        Intrinsics.checkNotNullParameter(x509KeyManager, "keyManager");
        this.keyManager = x509KeyManager;
    }

    @Override
    public String chooseClientAlias(String[] strings, Principal[] principals, Socket socket) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        Intrinsics.checkNotNullParameter(principals, "principals");
        Intrinsics.checkNotNullParameter(socket, "socket");
        LogUtils.INSTANCE.d("strings = " + strings + " principals = " + principals + " Socket = " + socket);
        String strChooseClientAlias = this.keyManager.chooseClientAlias(strings, principals, socket);
        Intrinsics.checkNotNullExpressionValue(strChooseClientAlias, "this.keyManager.chooseCl…ings, principals, socket)");
        return strChooseClientAlias;
    }

    @Override
    public String chooseServerAlias(String s, Principal[] principals, Socket socket) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(principals, "principals");
        Intrinsics.checkNotNullParameter(socket, "socket");
        LogUtils.INSTANCE.d("strings = " + s + " principals = " + principals + " Socket = " + socket);
        String strChooseServerAlias = this.keyManager.chooseServerAlias(s, principals, socket);
        Intrinsics.checkNotNullExpressionValue(strChooseServerAlias, "keyManager.chooseServerA…as(s, principals, socket)");
        return strChooseServerAlias;
    }

    @Override
    public X509Certificate[] getCertificateChain(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("strings = ", s));
        X509Certificate[] certificateChain = this.keyManager.getCertificateChain(s);
        Intrinsics.checkNotNullExpressionValue(certificateChain, "keyManager.getCertificateChain(s)");
        return certificateChain;
    }

    @Override
    public String[] getClientAliases(String s, Principal[] principals) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(principals, "principals");
        LogUtils.INSTANCE.d("strings = " + s + " principals = " + principals);
        String[] clientAliases = this.keyManager.getClientAliases(s, principals);
        Intrinsics.checkNotNullExpressionValue(clientAliases, "keyManager.getClientAliases(s, principals)");
        return clientAliases;
    }

    @Override
    public PrivateKey getPrivateKey(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("strings = ", s));
        PrivateKey privateKey = this.keyManager.getPrivateKey(s);
        Intrinsics.checkNotNullExpressionValue(privateKey, "keyManager.getPrivateKey(s)");
        return privateKey;
    }

    @Override
    public String[] getServerAliases(String s, Principal[] principals) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(principals, "principals");
        LogUtils.INSTANCE.d("strings = " + s + " principals = " + principals);
        String[] serverAliases = this.keyManager.getServerAliases(s, principals);
        Intrinsics.checkNotNullExpressionValue(serverAliases, "keyManager.getServerAliases(s, principals)");
        return serverAliases;
    }
}

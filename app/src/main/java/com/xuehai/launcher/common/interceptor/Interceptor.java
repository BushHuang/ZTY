package com.xuehai.launcher.common.interceptor;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001\bJ!\u0010\u0004\u001a\u00028\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006H&¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/common/interceptor/Interceptor;", "INPUT", "OUTPUT", "", "intercept", "chain", "Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "(Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;)Ljava/lang/Object;", "Chain", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface Interceptor<INPUT, OUTPUT> {

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003J\r\u0010\u0004\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00032\u0006\u0010\u0004\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "INPUT", "OUTPUT", "", "input", "()Ljava/lang/Object;", "process", "(Ljava/lang/Object;)Ljava/lang/Object;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Chain<INPUT, OUTPUT> {
        INPUT input();

        OUTPUT process(INPUT input);
    }

    OUTPUT intercept(Chain<INPUT, OUTPUT> chain);
}

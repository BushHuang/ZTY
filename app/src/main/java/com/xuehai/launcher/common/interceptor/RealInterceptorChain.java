package com.xuehai.launcher.common.interceptor;

import com.xuehai.launcher.common.interceptor.Interceptor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B1\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\r\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR \u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xuehai/launcher/common/interceptor/RealInterceptorChain;", "INPUT", "OUTPUT", "Lcom/xuehai/launcher/common/interceptor/Interceptor$Chain;", "interceptors", "", "Lcom/xuehai/launcher/common/interceptor/Interceptor;", "input", "index", "", "(Ljava/util/List;Ljava/lang/Object;I)V", "Ljava/lang/Object;", "()Ljava/lang/Object;", "process", "(Ljava/lang/Object;)Ljava/lang/Object;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class RealInterceptorChain<INPUT, OUTPUT> implements Interceptor.Chain<INPUT, OUTPUT> {
    private final int index;
    private final INPUT input;
    private final List<Interceptor<INPUT, OUTPUT>> interceptors;

    public RealInterceptorChain(List<? extends Interceptor<INPUT, OUTPUT>> list, INPUT input, int i) {
        Intrinsics.checkNotNullParameter(list, "interceptors");
        this.interceptors = list;
        this.input = input;
        this.index = i;
    }

    public RealInterceptorChain(List list, Object obj, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, obj, (i2 & 4) != 0 ? 0 : i);
    }

    @Override
    public INPUT input() {
        return this.input;
    }

    @Override
    public OUTPUT process(INPUT input) {
        if (this.index < this.interceptors.size()) {
            return this.interceptors.get(this.index).intercept(new RealInterceptorChain(this.interceptors, input, this.index + 1));
        }
        throw new AssertionError("index" + this.index + " >= " + this.interceptors.size());
    }
}

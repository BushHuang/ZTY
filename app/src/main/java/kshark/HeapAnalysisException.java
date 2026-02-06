package kshark;

import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \b2\u00060\u0001j\u0002`\u0002:\u0001\bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lkshark/HeapAnalysisException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "cause", "", "(Ljava/lang/Throwable;)V", "toString", "", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class HeapAnalysisException extends RuntimeException {
    private static final long serialVersionUID = -2522323377375290608L;

    public HeapAnalysisException(Throwable th) {
        super(th);
        Intrinsics.checkParameterIsNotNull(th, "cause");
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        Throwable cause = getCause();
        if (cause == null) {
            Intrinsics.throwNpe();
        }
        cause.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "stringWriter.toString()");
        return string;
    }
}

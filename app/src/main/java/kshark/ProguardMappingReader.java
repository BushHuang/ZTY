package kshark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0006\u0010\r\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkshark/ProguardMappingReader;", "", "proguardMappingInputStream", "Ljava/io/InputStream;", "(Ljava/io/InputStream;)V", "parseClassField", "", "line", "", "currentClassName", "proguardMapping", "Lkshark/ProguardMapping;", "parseClassMapping", "readProguardMapping", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class ProguardMappingReader {
    private static final String ARROW_SYMBOL = "->";
    private static final String COLON_SYMBOL = ":";
    private static final String HASH_SYMBOL = "#";
    private static final String OPENING_PAREN_SYMBOL = "(";
    private static final String SPACE_SYMBOL = " ";
    private final InputStream proguardMappingInputStream;

    public ProguardMappingReader(InputStream inputStream) {
        Intrinsics.checkParameterIsNotNull(inputStream, "proguardMappingInputStream");
        this.proguardMappingInputStream = inputStream;
    }

    private final void parseClassField(String line, String currentClassName, ProguardMapping proguardMapping) {
        String str = line;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, " ", 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return;
        }
        int i = iIndexOf$default + 1;
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, "->", i, false, 4, (Object) null);
        if (iIndexOf$default2 == -1) {
            return;
        }
        if (line == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = line.substring(i, iIndexOf$default2);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String string = StringsKt.trim((CharSequence) strSubstring).toString();
        int i2 = iIndexOf$default2 + 2;
        if (line == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring2 = line.substring(i2);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.String).substring(startIndex)");
        if (strSubstring2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        proguardMapping.addMapping(currentClassName + '.' + StringsKt.trim((CharSequence) strSubstring2).toString(), string);
    }

    private final String parseClassMapping(String line, ProguardMapping proguardMapping) {
        String str = line;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "->", 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            return null;
        }
        int i = iIndexOf$default + 2;
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, ":", i, false, 4, (Object) null);
        if (iIndexOf$default2 == -1) {
            return null;
        }
        if (line == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = line.substring(0, iIndexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String string = StringsKt.trim((CharSequence) strSubstring).toString();
        if (line == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring2 = line.substring(i, iIndexOf$default2);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String string2 = StringsKt.trim((CharSequence) strSubstring2).toString();
        proguardMapping.addMapping(string2, string);
        return string2;
    }

    public final ProguardMapping readProguardMapping() throws IOException, ParseException {
        ProguardMapping proguardMapping = new ProguardMapping();
        Reader inputStreamReader = new InputStreamReader(this.proguardMappingInputStream, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        Throwable th = (Throwable) null;
        try {
            BufferedReader bufferedReader2 = bufferedReader;
            String classMapping = (String) null;
            while (true) {
                String line = bufferedReader2.readLine();
                if (line == null) {
                    break;
                }
                if (line == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String string = StringsKt.trim((CharSequence) line).toString();
                if (string == null) {
                    break;
                }
                if (!(string.length() == 0) && !StringsKt.startsWith$default(string, "#", false, 2, (Object) null)) {
                    if (StringsKt.endsWith$default(string, ":", false, 2, (Object) null)) {
                        classMapping = parseClassMapping(string, proguardMapping);
                    } else if (classMapping != null && !StringsKt.contains$default((CharSequence) string, (CharSequence) "(", false, 2, (Object) null)) {
                        parseClassField(string, classMapping, proguardMapping);
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader, th);
            return proguardMapping;
        } finally {
        }
    }
}

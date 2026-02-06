package com.xh.xhcore.common.http.strategy.xh.upload.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0001¨\u0006\u0002"}, d2 = {"trimAllBlank", "", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class UploadFileEntityKt {
    public static final String trimAllBlank(String str) {
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return "";
        }
        String string = StringsKt.trim((CharSequence) str2).toString();
        Pattern patternCompile = Pattern.compile("\\s+");
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(\"\\\\s+\")");
        Matcher matcher = patternCompile.matcher(string);
        Intrinsics.checkNotNullExpressionValue(matcher, "pattern.matcher(text)");
        String strReplaceAll = matcher.replaceAll("");
        Intrinsics.checkNotNullExpressionValue(strReplaceAll, "matcher.replaceAll(\"\")");
        return strReplaceAll;
    }
}

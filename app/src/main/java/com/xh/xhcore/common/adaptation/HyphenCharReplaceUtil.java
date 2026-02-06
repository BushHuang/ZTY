package com.xh.xhcore.common.adaptation;

import android.text.SpannableStringBuilder;
import android.text.style.ScaleXSpan;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0003J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0003¨\u0006\u000b"}, d2 = {"Lcom/xh/xhcore/common/adaptation/HyphenCharReplaceUtil;", "", "()V", "handleTextScaleXSpan", "", "ssb", "Landroid/text/SpannableStringBuilder;", "replaceHyphenChar", "text", "", "replaceHyphenCharInner", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HyphenCharReplaceUtil {
    public static final HyphenCharReplaceUtil INSTANCE = new HyphenCharReplaceUtil();

    private HyphenCharReplaceUtil() {
    }

    @JvmStatic
    private static final void handleTextScaleXSpan(SpannableStringBuilder ssb) {
        int length = ssb.length();
        ssb.append(" ");
        ssb.setSpan(new ScaleXSpan(0.0909091f), length, ssb.length(), 33);
    }

    @JvmStatic
    public static final SpannableStringBuilder replaceHyphenChar(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return TextViewAdaptationUtil.isNoteCreatedNewVersion() ? replaceHyphenCharInner(text) : new SpannableStringBuilder(text);
    }

    @JvmStatic
    private static final SpannableStringBuilder replaceHyphenCharInner(String text) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int length = text.length();
        int i = 0;
        while (i < length) {
            char cCharAt = text.charAt(i);
            i++;
            if (cCharAt == '+' || cCharAt == '-') {
                handleTextScaleXSpan(spannableStringBuilder);
                spannableStringBuilder.append(cCharAt);
                handleTextScaleXSpan(spannableStringBuilder);
            } else {
                spannableStringBuilder.append(cCharAt);
            }
        }
        return spannableStringBuilder;
    }
}

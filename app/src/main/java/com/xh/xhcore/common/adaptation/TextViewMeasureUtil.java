package com.xh.xhcore.common.adaptation;

import android.graphics.Rect;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.StaticLayout;
import android.widget.TextView;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.HashMap;

public class TextViewMeasureUtil {
    public static HashMap<String, Float> wordWidthMap = new HashMap<>();

    private static void alertIfSameWordWithDifferentWidth(String str, float f) {
        if (wordWidthMap.containsKey(str)) {
            Float f2 = wordWidthMap.get(str);
            if (f2.floatValue() != f) {
                LogUtils.e("different width! word = " + str + " wordWidth using word =" + f2 + " wordWidth using character = " + f + " character method - word method diff = " + (f - f2.floatValue()));
            }
        }
    }

    public static int getFontHeight(TextView textView) {
        return textView.getPaint().getFontMetricsInt(null);
    }

    public static void getTextViewHeight(TextView textView, String str) {
        Rect rect = new Rect();
        textView.getPaint().getTextBounds(str, 0, str.length(), rect);
        int iHeight = rect.height();
        int iWidth = rect.width();
        LogUtils.i("TextBounds getLineHeight = " + textView.getLineHeight() + " fontHeight = " + getFontHeight(textView) + " lineSpacingExtra = " + textView.getLineSpacingExtra() + " height = " + iHeight + " width = " + iWidth + " text = " + str);
    }

    public static void getWidthMethod1(TextView textView, String str) {
        LogUtils.i("宽度：" + textView.getPaint().measureText(str));
        Rect rect = new Rect();
        textView.getPaint().getTextBounds(str, 0, str.length(), rect);
        LogUtils.i("TextBounds = " + rect.toShortString());
        textView.getPaint().getTextWidths(str, 0, str.length(), new float[str.length()]);
        logWordsWidthAndTotalWidth(textView, str);
        logWidthUsingCharacter(textView, str);
    }

    public static void getWidthMethod2(TextView textView, String str) {
        Layout layout = textView.getLayout();
        for (int i = 0; i < layout.getLineCount(); i++) {
            layout.getLineBaseline(i);
            textView.getPaddingTop();
            int lineStart = layout.getLineStart(i);
            int lineEnd = layout.getLineEnd(i);
            StaticLayout.getDesiredWidth(str, lineStart, lineEnd, textView.getPaint());
            String strSubstring = str.substring(lineStart, lineEnd);
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < strSubstring.length(); i2++) {
                strSubstring.charAt(i2);
                sb.append("$currentChar-${hashSet.get(i)!!}=");
            }
            LogUtils.i("width:$_width- need: ${width - _width}- $substring - sum:$sum ");
        }
    }

    public static boolean isTheLastCharacterInText(String str, int i) {
        return i == str.length() - 1;
    }

    public static float logSpaceWidth(TextView textView) {
        float desiredWidth = DynamicLayout.getDesiredWidth(" ", textView.getPaint());
        LogUtils.d("空格的宽度为:" + desiredWidth);
        return desiredWidth;
    }

    private static void logWidthUsingCharacter(TextView textView, String str) {
        LogUtils.i("宽度2：" + textView.getMeasuredWidth());
        StringBuilder sb = new StringBuilder();
        float fLogSpaceWidth = logSpaceWidth(textView);
        int i = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            int i2 = i + 1;
            float desiredWidth = DynamicLayout.getDesiredWidth(str.substring(i, i2), textView.getPaint());
            LogUtils.d("字符:" + cCharAt + "的宽度为:" + desiredWidth);
            if (cCharAt != ' ') {
                sb.append(cCharAt);
                f += desiredWidth;
                if (nextCharacterIsSpace(str, i)) {
                    String string = sb.toString();
                    logWordAndItsWidth(f, string);
                    alertIfSameWordWithDifferentWidth(string, f);
                    f2 += f + fLogSpaceWidth;
                    sb = new StringBuilder();
                    f = 0.0f;
                } else if (isTheLastCharacterInText(str, i)) {
                    String string2 = sb.toString();
                    logWordAndItsWidth(f, string2);
                    alertIfSameWordWithDifferentWidth(string2, f);
                    f2 += f;
                    LogUtils.d("通过字符计算的总宽度为:" + f2);
                }
            }
            i = i2;
        }
    }

    private static void logWordAndItsWidth(float f, String str) {
        LogUtils.d("单词:" + str + "的宽度为:" + f);
    }

    public static void logWordsWidthAndTotalWidth(TextView textView, String str) {
        String[] strArrSplit = str.split(" ");
        float fLogSpaceWidth = logSpaceWidth(textView);
        float f = 0.0f;
        for (String str2 : strArrSplit) {
            float desiredWidth = DynamicLayout.getDesiredWidth(str2, textView.getPaint());
            wordWidthMap.put(str2, Float.valueOf(desiredWidth));
            logWordAndItsWidth(desiredWidth, str2);
            f += desiredWidth + fLogSpaceWidth;
        }
        if (!str.endsWith(" ")) {
            f -= fLogSpaceWidth;
        }
        LogUtils.d("通过单词计算的总宽度为:" + f);
        for (String str3 : str.split("\n")) {
            LogUtils.d("段落:" + str3 + "的宽度为:" + DynamicLayout.getDesiredWidth(str3, textView.getPaint()));
        }
    }

    public static boolean nextCharacterIsSpace(String str, int i) {
        int i2 = i + 1;
        return i2 <= str.length() - 1 && str.substring(i2, i + 2).equals(" ");
    }
}

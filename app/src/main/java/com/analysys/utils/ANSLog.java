package com.analysys.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.BaseBundle;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.analysys.LogObserverListener;
import com.analysys.process.AgentProcess;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ANSLog {
    private static final int JSON_INDENT = 2;
    public static final int MAX_CHILD_LEVEL = 3;
    public static final String BR = System.getProperty("line.separator");
    public static boolean isShowLog = false;
    public static String DEFAULT_TAG = "analysys";
    static Pattern p = Pattern.compile("%", 2);
    private static boolean isShellControl = false;
    private static boolean isNeedCallstackInfo = false;
    private static boolean isNeedWrapper = false;
    private static boolean isFormat = false;
    private static String TEMP_TAG = "";
    private static int LOG_MAXLENGTH = 2900;
    private static String content_simple_callstack = "简易调用堆栈: %s.%s[%d]";
    private static String CONTENT_LINE = "║ ";
    private static String CONTENT_SPACE = "  ";
    private static String CONTENT_LOG_INFO = "log info:";
    private static String CONTENT_LOG_EMPTY = "";
    private static String content_title_begin = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private static String content_title_info_callstack = "╔══════════════════════════════════════════════════════════════调用详情══════════════════════════════════════════════════════════════";
    private static String content_title_info_log = "╔══════════════════════════════════════════════════════════════日志详情══════════════════════════════════════════════════════════════";
    private static String content_title_info_error = "╔══════════════════════════════════════════════════════════════异常详情══════════════════════════════════════════════════════════════";
    private static String content_title_info_type = "╔════════════════════════════════════════════════════「%s」════════════════════════════════════════════════════";
    private static String content_title_end = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private static String CONTENT_A = "║ ";
    private static String CONTENT_B = "╔";
    private static String CONTENT_C = "╚";
    private static String CONTENT_D = " ╔";
    private static String CONTENT_E = " ╚";
    private static String CONTENT_WARNNING_SHELL = "Wranning....不够打印级别,请在命令行设置指令后重新尝试打印,命令行指令: adb shell setprop log.tag." + DEFAULT_TAG + " ";
    private static Character FORMATER = '%';

    private ANSLog() {
    }

    public static void d(Object... objArr) {
        if (!isShellControl || Log.isLoggable(DEFAULT_TAG, 3)) {
            parserArgsMain(2, objArr);
            return;
        }
        Log.d(DEFAULT_TAG, CONTENT_WARNNING_SHELL + "DEBUG");
    }

    public static void e(Object... objArr) {
        if (!isShellControl || Log.isLoggable(DEFAULT_TAG, 6)) {
            parserArgsMain(5, objArr);
            return;
        }
        Log.e(DEFAULT_TAG, CONTENT_WARNNING_SHELL + "ERROR");
    }

    private static String format(JSONArray jSONArray) {
        if (jSONArray == null) {
            return "";
        }
        try {
            return isFormat ? jSONArray.toString(2) : jSONArray.toString();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return "";
        }
    }

    private static String format(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            return isFormat ? jSONObject.toString(2) : jSONObject.toString();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return "";
        }
    }

    private static int getArrayDimension(Object obj) {
        int i = 0;
        for (int i2 = 0; i2 < obj.toString().length() && obj.toString().charAt(i2) == '['; i2++) {
            i++;
        }
        return i;
    }

    private static String getCallStaceInfo() {
        Exception exc = new Exception("debug_info call stack.");
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (i >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            if (z && !z2) {
                break;
            }
            if (stackTraceElement.getClassName().equals(ANSLog.class.getName())) {
                if (!z) {
                    z = true;
                }
                z2 = true;
            } else if (z) {
                String string = null;
                if (isNeedCallstackInfo) {
                    String[] strArrSplit = parseString(exc).split("\n");
                    StringBuilder sb2 = new StringBuilder();
                    for (int i2 = 1; i2 < strArrSplit.length; i2++) {
                        sb2.append(CONTENT_SPACE);
                        sb2.append(CONTENT_SPACE);
                        sb2.append(CONTENT_SPACE);
                        sb2.append(strArrSplit[i2]);
                        if (i2 != strArrSplit.length - 1) {
                            sb2.append("\n");
                        }
                    }
                    string = sb2.toString();
                }
                if (isNeedWrapper) {
                    if (isNeedCallstackInfo) {
                        sb.append("\n");
                        sb.append(content_title_info_callstack);
                        sb.append("\n");
                        sb.append(CONTENT_LINE);
                        sb.append(CONTENT_SPACE);
                        sb.append("文件名:     " + stackTraceElement.getFileName());
                        sb.append("\n");
                        sb.append(CONTENT_LINE);
                        sb.append(CONTENT_SPACE);
                        sb.append("类名:      " + stackTraceElement.getClassName());
                        sb.append("\n");
                        sb.append(CONTENT_LINE);
                        sb.append(CONTENT_SPACE);
                        sb.append("方法名:     " + stackTraceElement.getMethodName());
                        sb.append("\n");
                        sb.append(CONTENT_LINE);
                        sb.append(CONTENT_SPACE);
                        sb.append("行号:      " + stackTraceElement.getLineNumber());
                        sb.append("\n");
                        sb.append(CONTENT_LINE);
                        sb.append(CONTENT_SPACE);
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Native方法:");
                        sb3.append(stackTraceElement.isNativeMethod() ? "是" : "不是");
                        sb.append(sb3.toString());
                        sb.append("\n");
                        sb.append(CONTENT_LINE);
                        sb.append(CONTENT_SPACE);
                        sb.append("调用堆栈详情:");
                        sb.append("\n");
                        sb.append(wrapperString(string));
                    } else {
                        sb.append("\n");
                        sb.append(content_title_begin);
                        sb.append("\n");
                        sb.append(CONTENT_LINE);
                        sb.append(String.format(content_simple_callstack, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())));
                    }
                } else if (isNeedCallstackInfo) {
                    sb.append("文件名:    " + stackTraceElement.getFileName());
                    sb.append("\n");
                    sb.append("类名:      " + stackTraceElement.getClassName());
                    sb.append("\n");
                    sb.append("方法名:    " + stackTraceElement.getMethodName());
                    sb.append("\n");
                    sb.append("行号:      " + stackTraceElement.getLineNumber());
                    sb.append("\n");
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Native方法:");
                    sb4.append(stackTraceElement.isNativeMethod() ? "是" : "不是");
                    sb.append(sb4.toString());
                    sb.append("\n");
                    sb.append("调用堆栈详情:");
                    sb.append("\n");
                    sb.append(wrapperString(string));
                } else if (isFormat) {
                    sb.append(String.format(content_simple_callstack, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())));
                }
            }
            i++;
        }
        return sb.toString();
    }

    private static void getClassFields(Class<?> cls, JSONObject jSONObject, Object obj, int i) {
        String name;
        Object string;
        String name2;
        String strReplaceAll;
        String name3;
        Object obj2;
        Object obj3;
        try {
            if (cls.equals(Object.class)) {
                return;
            }
            Field[] declaredFields = cls.getDeclaredFields();
            for (int i2 = 0; i2 < declaredFields.length; i2++) {
                Field field = declaredFields[i2];
                field.setAccessible(true);
                if ((!cls.isMemberClass() || isStaticInnerClass(cls) || i2 != 0) && !"$change".equals(field.getName()) && (isStaticInnerClass(cls) || (!"$change".equals(field.getName()) && !"this$0".equalsIgnoreCase(field.getName())))) {
                    try {
                        obj3 = field.get(obj);
                    } catch (Throwable th) {
                        try {
                            ExceptionUtil.exceptionThrow(th);
                            if (i >= 3) {
                                name = field.getName();
                                string = th.toString();
                            } else if (Number.class.isAssignableFrom(th.getClass())) {
                                name2 = field.getName();
                                obj2 = th;
                            } else {
                                strReplaceAll = objectToString(th, i + 1).replaceAll("\n", "").replaceAll("\r", "").replaceAll("\r\n", "");
                                try {
                                    jSONObject.put(field.getName(), new JSONObject(strReplaceAll));
                                } catch (Throwable th2) {
                                    ExceptionUtil.exceptionThrow(th2);
                                    try {
                                        jSONObject.put(field.getName(), new JSONArray(strReplaceAll));
                                    } catch (Throwable th3) {
                                        ExceptionUtil.exceptionThrow(th3);
                                        name3 = field.getName();
                                        jSONObject.put(name3, strReplaceAll);
                                    }
                                }
                            }
                        } catch (Throwable th4) {
                            jSONObject.put(field.getName(), "null");
                            throw th4;
                        }
                    }
                    if (obj3 == null) {
                        jSONObject.put(field.getName(), "null");
                    } else if (i >= 3) {
                        name = field.getName();
                        string = obj3.toString();
                        jSONObject.put(name, string);
                    } else if (Number.class.isAssignableFrom(obj3.getClass())) {
                        name2 = field.getName();
                        obj2 = obj3;
                        jSONObject.put(name2, obj2);
                    } else {
                        strReplaceAll = objectToString(obj3, i + 1).replaceAll("\n", "").replaceAll("\r", "").replaceAll("\r\n", "");
                        try {
                            jSONObject.put(field.getName(), new JSONObject(strReplaceAll));
                        } catch (Throwable th5) {
                            ExceptionUtil.exceptionThrow(th5);
                            try {
                                jSONObject.put(field.getName(), new JSONArray(strReplaceAll));
                            } catch (Throwable th6) {
                                ExceptionUtil.exceptionThrow(th6);
                                name3 = field.getName();
                                jSONObject.put(name3, strReplaceAll);
                            }
                        }
                    }
                }
            }
        } catch (Throwable th7) {
            ExceptionUtil.exceptionThrow(th7);
        }
    }

    private static String getFlags(int i) {
        Object obj;
        HashMap map = new HashMap();
        for (Field field : Intent.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().startsWith("FLAG_")) {
                try {
                    obj = field.get(Intent.class);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
                int iIntValue = ((obj instanceof Integer) || "int".equals(obj.getClass().getSimpleName())) ? ((Integer) obj).intValue() : 0;
                if (map.get(Integer.valueOf(iIntValue)) == null) {
                    map.put(Integer.valueOf(iIntValue), field.getName());
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue2 = ((Integer) it.next()).intValue();
            if ((iIntValue2 & i) == iIntValue2) {
                sb.append((String) map.get(Integer.valueOf(iIntValue2)));
                sb.append(" | ");
            }
        }
        if (TextUtils.isEmpty(sb.toString())) {
            sb.append(i);
        } else if (sb.indexOf("|") != -1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    private static List<String> getStringBysplitLine(String str, int i) {
        ArrayList arrayList = new ArrayList();
        String[] strArrSplit = str.split("\n");
        if (strArrSplit.length == 1) {
            strArrSplit = str.split("\r");
            if (strArrSplit.length == 1) {
                strArrSplit = str.split("\r\n");
                if (strArrSplit.length == 1) {
                    strArrSplit = str.split("\n\r");
                }
            }
        }
        if (strArrSplit.length > 1) {
            for (String str2 : strArrSplit) {
                processLine(i, arrayList, str2);
            }
        } else {
            processLine(i, arrayList, str);
        }
        return arrayList;
    }

    private static char getType(Object obj) {
        if (!isArray(obj)) {
            return (char) 0;
        }
        String string = obj.toString();
        return string.substring(string.lastIndexOf("[") + 1, string.lastIndexOf("[") + 2).charAt(0);
    }

    private static void header(Object obj, StringBuilder sb) {
        if (isNeedWrapper) {
            if (obj instanceof String) {
                sb.append(content_title_info_log);
                sb.append("\n");
            } else if (obj instanceof Throwable) {
                sb.append(content_title_info_error);
                sb.append("\n");
            } else {
                sb.append(String.format(content_title_info_type, obj.getClass().getName()));
                sb.append("\n");
            }
        }
    }

    public static void i(Object... objArr) {
        if (!isShellControl || Log.isLoggable(DEFAULT_TAG, 4)) {
            parserArgsMain(3, objArr);
            return;
        }
        Log.i(DEFAULT_TAG, CONTENT_WARNNING_SHELL + "INFO");
    }

    public static void init(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str) {
        isShowLog = z;
        isShellControl = z2;
        isNeedWrapper = z3;
        isNeedCallstackInfo = z4;
        isFormat = z5;
        if (str == null || str.length() <= 0) {
            return;
        }
        DEFAULT_TAG = str;
    }

    private static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    private static boolean isStaticInnerClass(Class<?> cls) {
        return cls != null && cls.isMemberClass() && (cls.getModifiers() & 8) == 8;
    }

    private static String objectToString(Object obj) {
        return objectToString(obj, 0);
    }

    private static String objectToString(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (i > 3) {
            return obj.toString();
        }
        Class<?> cls = obj.getClass();
        if (Build.VERSION.SDK_INT > 20) {
            if (BaseBundle.class.isAssignableFrom(cls)) {
                return parseString((BaseBundle) obj);
            }
        } else if (Bundle.class.isAssignableFrom(cls)) {
            return parseString((Bundle) obj);
        }
        if (String.class.isAssignableFrom(cls)) {
            return parseString((String) obj);
        }
        if (Number.class.isAssignableFrom(cls)) {
            return String.valueOf((Number) obj);
        }
        if (Intent.class.isAssignableFrom(cls)) {
            return parseString((Intent) obj);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            return parseString((Collection<?>) obj);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return parseString((Map<?, ?>) obj);
        }
        if (Throwable.class.isAssignableFrom(cls)) {
            return parseString((Throwable) obj);
        }
        if (Reference.class.isAssignableFrom(cls)) {
            return parseString((Reference<?>) obj);
        }
        if (Message.class.isAssignableFrom(cls)) {
            return parseString((Message) obj);
        }
        if (Activity.class.isAssignableFrom(cls)) {
            return parseString((Activity) obj);
        }
        if (JSONArray.class.isAssignableFrom(cls)) {
            return format((JSONArray) obj);
        }
        if (JSONObject.class.isAssignableFrom(cls)) {
            return format((JSONObject) obj);
        }
        if (StringBuilder.class.isAssignableFrom(cls)) {
            return ((StringBuilder) obj).toString();
        }
        if (StringBuffer.class.isAssignableFrom(cls)) {
            return ((StringBuffer) obj).toString();
        }
        if (Class.class.isAssignableFrom(cls)) {
            return parseStringByObject(obj, i);
        }
        if (isArray(obj)) {
            StringBuilder sb = new StringBuilder();
            traverseArray(sb, obj);
            return sb.toString();
        }
        String string = obj.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(obj.getClass().getName());
        sb2.append("@");
        return string.startsWith(sb2.toString()) ? parseStringByObject(obj, i) : obj.toString();
    }

    private static String parseString(Activity activity) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        JSONObject jSONObject = new JSONObject();
        Field[] fields = activity.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!"org.aspectj.lang.JoinPoint$StaticPart".equals(field.getType().getName()) && !"$change".equals(field.getName()) && !"this$0".equalsIgnoreCase(field.getName())) {
                try {
                    jSONObject.put(field.getName(), objectToString(field.get(activity)));
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        }
        StringBuilder sb = new StringBuilder(activity.getClass().getName());
        sb.append(" {");
        sb.append(BR);
        for (Field field2 : fields) {
            field2.setAccessible(true);
            if (!"org.aspectj.lang.JoinPoint$StaticPart".equals(field2.getType().getName()) && !"$change".equals(field2.getName()) && !"this$0".equalsIgnoreCase(field2.getName())) {
                try {
                    Object obj = field2.get(activity);
                    sb.append(field2.getName());
                    sb.append("=>");
                    sb.append(objectToString(obj));
                    sb.append(BR);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        sb.append("}");
        return format(jSONObject);
    }

    private static String parseString(Intent intent) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(intent.getScheme())) {
                jSONObject.put("Scheme", intent.getScheme());
            }
            if (!TextUtils.isEmpty(intent.getAction())) {
                jSONObject.put("Action", intent.getAction());
            }
            if (!TextUtils.isEmpty(intent.getDataString())) {
                jSONObject.put("DataString", intent.getDataString());
            }
            if (!TextUtils.isEmpty(intent.getType())) {
                jSONObject.put("Type", intent.getType());
            }
            if (!TextUtils.isEmpty(intent.getPackage())) {
                jSONObject.put("Package", intent.getPackage());
            }
            if (!TextUtils.isEmpty(intent.getComponent().toString())) {
                jSONObject.put("ComponentInfo", intent.getComponent().toString());
            }
            if (!TextUtils.isEmpty(intent.getCategories().toString())) {
                jSONObject.put("Categories", intent.getCategories().toString());
            }
            String string = parseString(intent.getExtras());
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put("Extras", string);
            }
            if (!TextUtils.isEmpty(getFlags(intent.getFlags()))) {
                jSONObject.put("Flags", intent.getType());
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return format(jSONObject);
    }

    private static String parseString(BaseBundle baseBundle) {
        if (baseBundle == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : baseBundle.keySet()) {
            try {
                jSONObject.put(str, objectToString(baseBundle.get(str)));
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return format(jSONObject);
    }

    private static String parseString(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, objectToString(bundle.get(str)));
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return format(jSONObject);
    }

    private static String parseString(Message message) {
        if (message == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("what", message.what);
            jSONObject.put("when", message.getWhen());
            jSONObject.put("arg1", message.arg1);
            jSONObject.put("arg2", message.arg2);
            jSONObject.put("data", parseString(message.getData()));
            jSONObject.put("obj", objectToString(message.obj));
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return format(jSONObject);
    }

    private static String parseString(String str) {
        StringReader stringReader;
        try {
            try {
                return format(new JSONObject(str));
            } catch (JSONException unused) {
                return format(new JSONArray(str));
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                return str;
            }
        } catch (JSONException unused2) {
            StringReader stringReader2 = null;
            try {
                stringReader = new StringReader(str);
            } catch (Throwable unused3) {
            }
            try {
                StreamSource streamSource = new StreamSource(stringReader);
                StreamResult streamResult = new StreamResult(new StringWriter());
                Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
                transformerNewTransformer.setOutputProperty("indent", "yes");
                transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformerNewTransformer.transform(streamSource, streamResult);
                String strReplaceFirst = streamResult.getWriter().toString().replaceFirst(">", ">\n");
                stringReader.close();
                return strReplaceFirst;
            } catch (Throwable unused4) {
                stringReader2 = stringReader;
                if (stringReader2 != null) {
                    stringReader2.close();
                }
                return str;
            }
        }
    }

    private static String parseString(Throwable th) {
        PrintWriter printWriter;
        StringWriter stringWriter;
        StringBuilder sb = new StringBuilder();
        StringWriter stringWriter2 = null;
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
            } catch (Throwable th2) {
                th = th2;
                printWriter = null;
            }
        } catch (Throwable th3) {
            th = th3;
            printWriter = null;
        }
        try {
            th.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
            String[] strArrSplit = stringWriter.toString().split("\n");
            for (int i = 0; i < strArrSplit.length; i++) {
                String str = strArrSplit[i];
                if ("at".equalsIgnoreCase(str.substring(1, 3))) {
                    sb.append(CONTENT_SPACE);
                    sb.append(str);
                } else {
                    sb.append(str);
                }
                if (i != strArrSplit.length - 1) {
                    sb.append("\n");
                }
            }
            try {
                stringWriter.close();
            } catch (Throwable th4) {
                ExceptionUtil.exceptionThrow(th4);
            }
        } catch (Throwable th5) {
            th = th5;
            stringWriter2 = stringWriter;
            try {
                ExceptionUtil.exceptionThrow(th);
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Throwable th6) {
                        ExceptionUtil.exceptionThrow(th6);
                    }
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                return sb.toString();
            } finally {
            }
        }
        printWriter.close();
        return sb.toString();
    }

    private static String parseString(Reference<?> reference) {
        return objectToString(reference.get());
    }

    private static String parseString(Collection<?> collection) {
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            jSONArray.put(objectToString(it.next()));
        }
        return format(jSONArray);
    }

    private static String parseString(Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        for (Object obj : map.keySet()) {
            try {
                Object obj2 = map.get(obj);
                if (obj == null) {
                    obj = "null";
                }
                if (obj2 != null) {
                    jSONObject.put(objectToString(obj), objectToString(obj2));
                } else {
                    jSONObject.put(objectToString(obj), "null");
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return format(jSONObject);
    }

    private static String parseStringByObject(Object obj, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            getClassFields(obj.getClass(), jSONObject, obj, i);
            Class<? super Object> superclass = obj.getClass().getSuperclass();
            if (superclass != null) {
                while (!superclass.equals(Object.class)) {
                    getClassFields(superclass, jSONObject, obj, i);
                    superclass = superclass.getSuperclass();
                }
            } else {
                jSONObject.put("toString", obj.toString());
            }
            return format(jSONObject);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return obj.toString();
        }
    }

    private static void parserArgsMain(int i, Object[] objArr) {
        if (isShowLog) {
            StringBuilder sb = new StringBuilder();
            if (isFormat) {
                sb.append(CONTENT_LOG_INFO);
                sb.append("\n");
            }
            String callStaceInfo = getCallStaceInfo();
            if (!TextUtils.isEmpty(callStaceInfo)) {
                sb.append(callStaceInfo);
                sb.append("\n");
            }
            int i2 = 0;
            if (objArr[0] instanceof String) {
                String str = (String) objArr[0];
                if (str.contains(String.valueOf(FORMATER)) && objArr.length > 1) {
                    int length = objArr.length - 1;
                    Object[] objArr2 = new Object[length];
                    for (int i3 = 1; i3 < objArr.length; i3++) {
                        objArr2[i3 - 1] = objArr[i3];
                    }
                    int i4 = 0;
                    while (p.matcher(str).find()) {
                        i4++;
                    }
                    if (i4 == length) {
                        String str2 = String.format(Locale.getDefault(), str, objArr2);
                        if (isNeedWrapper) {
                            sb.append(content_title_info_log);
                            sb.append("\n");
                        }
                        sb.append(wrapperString(str2));
                        sb.append("\n");
                    } else {
                        if (isNeedWrapper) {
                            sb.append(content_title_info_log);
                            sb.append("\n");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        int length2 = objArr.length;
                        while (i2 < length2) {
                            String strObjectToString = objectToString(objArr[i2]);
                            if (!TextUtils.isEmpty(strObjectToString)) {
                                sb2.append(strObjectToString);
                                sb2.append("\t");
                            }
                            i2++;
                        }
                        sb.append(wrapperString(sb2.toString()));
                        sb.append("\n");
                    }
                } else if (objArr.length > 1) {
                    String strProcessTagCase = processTagCase(objArr);
                    if (TextUtils.isEmpty(strProcessTagCase)) {
                        sb.append(wrapperString(""));
                        sb.append("\n");
                    } else {
                        sb.append(wrapperString(strProcessTagCase));
                        sb.append("\n");
                    }
                } else {
                    if (isNeedWrapper) {
                        sb.append(content_title_info_log);
                        sb.append("\n");
                    }
                    sb.append(wrapperString(str));
                    sb.append("\n");
                }
            } else {
                int length3 = objArr.length;
                while (i2 < length3) {
                    String strProcessObjectCase = processObjectCase(objArr[i2]);
                    if (!TextUtils.isEmpty(strProcessObjectCase)) {
                        sb.append(strProcessObjectCase);
                        sb.append("\n");
                    }
                    i2++;
                }
            }
            if (isNeedWrapper) {
                sb.append(content_title_end);
            }
            preparePrint(i, sb.toString());
        }
    }

    private static void preparePrint(int i, String str) {
        String str2 = DEFAULT_TAG;
        if (!TextUtils.isEmpty(TEMP_TAG)) {
            str2 = TEMP_TAG;
        }
        int length = str.length();
        int i2 = LOG_MAXLENGTH;
        if (length > i2) {
            List<String> stringBysplitLine = getStringBysplitLine(str, i2);
            StringBuilder sb = null;
            for (int i3 = 0; i3 < stringBysplitLine.size(); i3++) {
                String str3 = stringBysplitLine.get(i3);
                if (sb == null) {
                    sb = new StringBuilder();
                }
                if (sb.length() + str3.length() >= LOG_MAXLENGTH) {
                    realPrint(i, str2, wrapperString(sb.toString()));
                    sb = new StringBuilder();
                    if (str3.length() >= LOG_MAXLENGTH) {
                        realPrint(i, str2, wrapperString(str3));
                    } else {
                        sb.append(str3);
                    }
                    if (i3 != stringBysplitLine.size() - 1) {
                        sb.append("\n");
                    }
                } else {
                    sb.append(str3);
                    if (i3 != stringBysplitLine.size() - 1) {
                        sb.append("\n");
                    }
                }
            }
            if (sb != null) {
                realPrint(i, str2, wrapperString(sb.toString()));
            }
        } else {
            realPrint(i, str2, wrapperString(str));
        }
        TEMP_TAG = "";
    }

    private static void processLine(int i, List<String> list, String str) {
        if (str.length() <= i) {
            list.add(str);
            return;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2 + i;
            try {
                list.add(str.substring(i2, i3));
                i2 = i3;
            } catch (StringIndexOutOfBoundsException unused) {
                list.add(str.substring(i2, str.length()));
                return;
            }
        }
    }

    private static String processObjectCase(Object obj) {
        StringBuilder sb = new StringBuilder();
        try {
            String strObjectToString = objectToString(obj);
            if (TextUtils.isEmpty(strObjectToString)) {
                if (isNeedWrapper) {
                    sb.append(content_title_info_log);
                    sb.append("\n");
                }
                sb.append(wrapperString(""));
            } else {
                header(obj, sb);
                sb.append(wrapperString(strObjectToString));
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return sb.toString();
    }

    private static String processTagCase(Object[] objArr) {
        String str = (String) objArr[0];
        StringBuilder sb = new StringBuilder();
        TEMP_TAG = str;
        for (int i = 1; i < objArr.length; i++) {
            sb.append(processObjectCase(objArr[i]));
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void realPrint(int i, String str, String str2) {
        LogObserverListener logObserverListener = AgentProcess.getInstance().getLogObserverListener();
        if (logObserverListener != null) {
            logObserverListener.onLogMessage(str + ": " + str2);
        }
        switch (i) {
            case 1:
                Log.v(str, str2);
                break;
            case 2:
                Log.d(str, str2);
                break;
            case 3:
                Log.i(str, str2);
                break;
            case 4:
                Log.w(str, str2);
                break;
            case 5:
                Log.e(str, str2);
                break;
            case 6:
                Log.wtf(str, str2);
                break;
        }
    }

    private static void traverseArray(StringBuilder sb, Object obj) {
        if (!isArray(obj)) {
            sb.append("not a array!!");
            return;
        }
        int i = 0;
        if (getArrayDimension(obj) == 1) {
            char type = getType(obj);
            if (type == 'F') {
                sb.append(Arrays.toString((float[]) obj));
                return;
            }
            if (type == 'L') {
                Object[] objArr = (Object[]) obj;
                sb.append("[");
                while (i < objArr.length) {
                    sb.append(objectToString(objArr[i]));
                    if (i != objArr.length - 1) {
                        sb.append(",");
                    }
                    i++;
                }
                sb.append("]");
                return;
            }
            if (type == 'S') {
                sb.append(Arrays.toString((short[]) obj));
                return;
            }
            if (type == 'Z') {
                sb.append(Arrays.toString((boolean[]) obj));
                return;
            }
            if (type == 'I') {
                sb.append(Arrays.toString((int[]) obj));
                return;
            }
            if (type == 'J') {
                sb.append(Arrays.toString((long[]) obj));
                return;
            }
            switch (type) {
                case 'B':
                    sb.append(Arrays.toString((byte[]) obj));
                    break;
                case 'C':
                    sb.append(Arrays.toString((char[]) obj));
                    break;
                case 'D':
                    sb.append(Arrays.toString((double[]) obj));
                    break;
                default:
                    sb.append(Arrays.toString((Object[]) obj));
                    break;
            }
            return;
        }
        sb.append("[");
        while (true) {
            Object[] objArr2 = (Object[]) obj;
            if (i >= objArr2.length) {
                sb.append("]");
                return;
            }
            traverseArray(sb, objArr2[i]);
            if (i != objArr2.length - 1) {
                sb.append(",");
            }
            i++;
        }
    }

    public static void v(Object... objArr) {
        if (!isShellControl || Log.isLoggable(DEFAULT_TAG, 2)) {
            parserArgsMain(1, objArr);
            return;
        }
        Log.v(DEFAULT_TAG, CONTENT_WARNNING_SHELL + "VERBOSE");
    }

    public static void w(Object... objArr) {
        if (!isShellControl || Log.isLoggable(DEFAULT_TAG, 5)) {
            parserArgsMain(4, objArr);
            return;
        }
        Log.w(DEFAULT_TAG, CONTENT_WARNNING_SHELL + "WARN");
    }

    private static String wrapperString(String str) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            if (isNeedWrapper) {
                sb.append(CONTENT_LINE);
            }
            sb.append(CONTENT_LOG_EMPTY);
            return sb.toString();
        }
        int i = 0;
        if (str.contains("\n")) {
            String[] strArrSplit = str.split("\n");
            if (strArrSplit.length > 0) {
                sb = new StringBuilder();
                while (i < strArrSplit.length) {
                    String str2 = strArrSplit[i];
                    if (isNeedWrapper && !str2.startsWith(CONTENT_A) && !str2.startsWith(CONTENT_B) && !str2.startsWith(CONTENT_C) && !str2.startsWith(CONTENT_D) && !str2.startsWith(CONTENT_LOG_INFO) && !TextUtils.isEmpty(str2) && !str2.startsWith(CONTENT_E)) {
                        sb.append(CONTENT_LINE);
                    }
                    sb.append(str2);
                    if (i != strArrSplit.length - 1) {
                        sb.append("\n");
                    }
                    i++;
                }
            }
        } else if (str.contains("\r")) {
            String[] strArrSplit2 = str.split("\r");
            if (strArrSplit2.length > 0) {
                sb = new StringBuilder();
                while (i < strArrSplit2.length) {
                    String str3 = strArrSplit2[i];
                    if (isNeedWrapper && !str3.startsWith(CONTENT_A) && !str3.startsWith(CONTENT_B) && !str3.startsWith(CONTENT_D) && !str3.startsWith(CONTENT_E) && !str3.startsWith(CONTENT_LOG_INFO) && !TextUtils.isEmpty(str3) && !str3.startsWith(CONTENT_C)) {
                        sb.append(CONTENT_LINE);
                    }
                    sb.append(str3);
                    if (i != strArrSplit2.length - 1) {
                        sb.append("\r");
                    }
                    i++;
                }
            }
        } else if (str.contains("\r\n")) {
            String[] strArrSplit3 = str.split("\r\n");
            if (strArrSplit3.length > 0) {
                sb = new StringBuilder();
                while (i < strArrSplit3.length) {
                    String str4 = strArrSplit3[i];
                    if (isNeedWrapper && !str4.startsWith(CONTENT_A) && !str4.startsWith(CONTENT_B) && !str4.startsWith(CONTENT_D) && !str4.startsWith(CONTENT_E) && !str4.startsWith(CONTENT_LOG_INFO) && !TextUtils.isEmpty(str4) && !str4.startsWith(CONTENT_C)) {
                        sb.append(CONTENT_LINE);
                    }
                    sb.append(str4);
                    if (i != strArrSplit3.length - 1) {
                        sb.append("\r\n");
                    }
                    i++;
                }
            }
        } else if (str.contains("\n\r")) {
            String[] strArrSplit4 = str.split("\n\r");
            if (strArrSplit4.length > 0) {
                sb = new StringBuilder();
                while (i < strArrSplit4.length) {
                    String str5 = strArrSplit4[i];
                    if (isNeedWrapper && !str5.startsWith(CONTENT_A) && !str5.startsWith(CONTENT_B) && !str5.startsWith(CONTENT_D) && !str5.startsWith(CONTENT_E) && !str5.startsWith(CONTENT_LOG_INFO) && !TextUtils.isEmpty(str5) && !str5.startsWith(CONTENT_C)) {
                        sb.append(CONTENT_LINE);
                    }
                    sb.append(str5);
                    if (i != strArrSplit4.length - 1) {
                        sb.append("\n\r");
                    }
                    i++;
                }
            }
        } else {
            if (isNeedWrapper && !str.startsWith(CONTENT_A) && !str.startsWith(CONTENT_B) && !str.startsWith(CONTENT_D) && !str.startsWith(CONTENT_LOG_INFO) && !TextUtils.isEmpty(str) && !str.startsWith(CONTENT_E) && !str.startsWith(CONTENT_C)) {
                sb.append(CONTENT_LINE);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static void wtf(Object... objArr) {
        if (!isShellControl || Log.isLoggable(DEFAULT_TAG, 7)) {
            parserArgsMain(6, objArr);
            return;
        }
        Log.wtf(DEFAULT_TAG, CONTENT_WARNNING_SHELL + "ASSERT");
    }
}

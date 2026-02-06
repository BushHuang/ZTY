package com.tencent.tinker.loader.hotplug;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class IncrementComponentManager {
    private static final String TAG = "Tinker.IncrementCompMgr";
    private static final int TAG_ACTIVITY = 0;
    private static final int TAG_PROVIDER = 2;
    private static final int TAG_RECEIVER = 3;
    private static final int TAG_SERVICE = 1;
    private static Context sContext = null;
    private static volatile boolean sInitialized = false;
    private static String sPackageName;
    private static final Map<String, ActivityInfo> CLASS_NAME_TO_ACTIVITY_INFO_MAP = new HashMap();
    private static final Map<String, IntentFilter> CLASS_NAME_TO_INTENT_FILTER_MAP = new HashMap();
    private static final AttrTranslator<ActivityInfo> ACTIVITY_INFO_ATTR_TRANSLATOR = new AttrTranslator<ActivityInfo>() {
        private int parseLaunchMode(String str) {
            if ("standard".equalsIgnoreCase(str)) {
                return 0;
            }
            if ("singleTop".equalsIgnoreCase(str)) {
                return 1;
            }
            if ("singleTask".equalsIgnoreCase(str)) {
                return 2;
            }
            if ("singleInstance".equalsIgnoreCase(str)) {
                return 3;
            }
            Log.w("Tinker.IncrementCompMgr", "Unknown launchMode: " + str);
            return 0;
        }

        private int parseScreenOrientation(String str) {
            if ("unspecified".equalsIgnoreCase(str)) {
                return -1;
            }
            if ("behind".equalsIgnoreCase(str)) {
                return 3;
            }
            if ("landscape".equalsIgnoreCase(str)) {
                return 0;
            }
            if ("portrait".equalsIgnoreCase(str)) {
                return 1;
            }
            if ("reverseLandscape".equalsIgnoreCase(str)) {
                return 8;
            }
            if ("reversePortrait".equalsIgnoreCase(str)) {
                return 9;
            }
            if ("sensorLandscape".equalsIgnoreCase(str)) {
                return 6;
            }
            if ("sensorPortrait".equalsIgnoreCase(str)) {
                return 7;
            }
            if ("sensor".equalsIgnoreCase(str)) {
                return 4;
            }
            if ("fullSensor".equalsIgnoreCase(str)) {
                return 10;
            }
            if ("nosensor".equalsIgnoreCase(str)) {
                return 5;
            }
            if ("user".equalsIgnoreCase(str)) {
                return 2;
            }
            if (Build.VERSION.SDK_INT >= 18 && "fullUser".equalsIgnoreCase(str)) {
                return 13;
            }
            if (Build.VERSION.SDK_INT >= 18 && "locked".equalsIgnoreCase(str)) {
                return 14;
            }
            if (Build.VERSION.SDK_INT < 18 || !"userLandscape".equalsIgnoreCase(str)) {
                return (Build.VERSION.SDK_INT < 18 || !"userPortrait".equalsIgnoreCase(str)) ? -1 : 12;
            }
            return 11;
        }

        @Override
        void onInit(Context context, int i, XmlPullParser xmlPullParser) {
            if (i == 0) {
                try {
                    if (xmlPullParser.getEventType() == 2 && "activity".equals(xmlPullParser.getName())) {
                    } else {
                        throw new IllegalStateException("unexpected xml parser state when parsing incremental component manifest.");
                    }
                } catch (XmlPullParserException e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        @Override
        void onTranslate(Context context, int i, String str, String str2, ActivityInfo activityInfo) {
            int identifier = 0;
            if ("name".equals(str)) {
                if (str2.charAt(0) != '.') {
                    activityInfo.name = str2;
                    return;
                }
                activityInfo.name = context.getPackageName() + str2;
                return;
            }
            if ("parentActivityName".equals(str)) {
                if (Build.VERSION.SDK_INT >= 16) {
                    if (str2.charAt(0) != '.') {
                        activityInfo.parentActivityName = str2;
                        return;
                    }
                    activityInfo.parentActivityName = context.getPackageName() + str2;
                    return;
                }
                return;
            }
            if ("exported".equals(str)) {
                activityInfo.exported = "true".equalsIgnoreCase(str2);
                return;
            }
            if ("launchMode".equals(str)) {
                activityInfo.launchMode = parseLaunchMode(str2);
                return;
            }
            if ("theme".equals(str)) {
                activityInfo.theme = context.getResources().getIdentifier(str2, "style", context.getPackageName());
                return;
            }
            if ("uiOptions".equals(str)) {
                if (Build.VERSION.SDK_INT >= 14) {
                    activityInfo.uiOptions = Integer.decode(str2).intValue();
                    return;
                }
                return;
            }
            if ("permission".equals(str)) {
                activityInfo.permission = str2;
                return;
            }
            if ("taskAffinity".equals(str)) {
                activityInfo.taskAffinity = str2;
                return;
            }
            if ("multiprocess".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 1;
                    return;
                } else {
                    activityInfo.flags &= -2;
                    return;
                }
            }
            if ("finishOnTaskLaunch".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 2;
                    return;
                } else {
                    activityInfo.flags &= -3;
                    return;
                }
            }
            if ("clearTaskOnLaunch".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 4;
                    return;
                } else {
                    activityInfo.flags &= -5;
                    return;
                }
            }
            if ("noHistory".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 128;
                    return;
                } else {
                    activityInfo.flags &= -129;
                    return;
                }
            }
            if ("alwaysRetainTaskState".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 8;
                    return;
                } else {
                    activityInfo.flags &= -9;
                    return;
                }
            }
            if ("stateNotNeeded".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 16;
                    return;
                } else {
                    activityInfo.flags &= -17;
                    return;
                }
            }
            if ("excludeFromRecents".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 32;
                    return;
                } else {
                    activityInfo.flags &= -33;
                    return;
                }
            }
            if ("allowTaskReparenting".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 64;
                    return;
                } else {
                    activityInfo.flags &= -65;
                    return;
                }
            }
            if ("finishOnCloseSystemDialogs".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 256;
                    return;
                } else {
                    activityInfo.flags &= -257;
                    return;
                }
            }
            if ("showOnLockScreen".equals(str) || "showForAllUsers".equals(str)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int valueOfStaticIntField = ShareReflectUtil.getValueOfStaticIntField(ActivityInfo.class, "FLAG_SHOW_FOR_ALL_USERS", 0);
                    if ("true".equalsIgnoreCase(str2)) {
                        activityInfo.flags = valueOfStaticIntField | activityInfo.flags;
                        return;
                    } else {
                        activityInfo.flags = (valueOfStaticIntField ^ (-1)) & activityInfo.flags;
                        return;
                    }
                }
                return;
            }
            if ("immersive".equals(str)) {
                if (Build.VERSION.SDK_INT >= 18) {
                    if ("true".equalsIgnoreCase(str2)) {
                        activityInfo.flags |= 2048;
                        return;
                    } else {
                        activityInfo.flags &= -2049;
                        return;
                    }
                }
                return;
            }
            if ("hardwareAccelerated".equals(str)) {
                if (Build.VERSION.SDK_INT >= 11) {
                    if ("true".equalsIgnoreCase(str2)) {
                        activityInfo.flags |= 512;
                        return;
                    } else {
                        activityInfo.flags &= -513;
                        return;
                    }
                }
                return;
            }
            if ("documentLaunchMode".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    activityInfo.documentLaunchMode = Integer.decode(str2).intValue();
                    return;
                }
                return;
            }
            if ("maxRecents".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    activityInfo.maxRecents = Integer.decode(str2).intValue();
                    return;
                }
                return;
            }
            if ("configChanges".equals(str)) {
                activityInfo.configChanges = Integer.decode(str2).intValue();
                return;
            }
            if ("windowSoftInputMode".equals(str)) {
                activityInfo.softInputMode = Integer.decode(str2).intValue();
                return;
            }
            if ("persistableMode".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    activityInfo.persistableMode = Integer.decode(str2).intValue();
                    return;
                }
                return;
            }
            if ("allowEmbedded".equals(str)) {
                int valueOfStaticIntField2 = ShareReflectUtil.getValueOfStaticIntField(ActivityInfo.class, "FLAG_ALLOW_EMBEDDED", 0);
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags = valueOfStaticIntField2 | activityInfo.flags;
                    return;
                } else {
                    activityInfo.flags = (valueOfStaticIntField2 ^ (-1)) & activityInfo.flags;
                    return;
                }
            }
            if ("autoRemoveFromRecents".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if ("true".equalsIgnoreCase(str2)) {
                        activityInfo.flags |= 8192;
                        return;
                    } else {
                        activityInfo.flags &= -8193;
                        return;
                    }
                }
                return;
            }
            if ("relinquishTaskIdentity".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if ("true".equalsIgnoreCase(str2)) {
                        activityInfo.flags |= 4096;
                        return;
                    } else {
                        activityInfo.flags &= -4097;
                        return;
                    }
                }
                return;
            }
            if ("resumeWhilePausing".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    if ("true".equalsIgnoreCase(str2)) {
                        activityInfo.flags |= 16384;
                        return;
                    } else {
                        activityInfo.flags &= -16385;
                        return;
                    }
                }
                return;
            }
            if ("screenOrientation".equals(str)) {
                activityInfo.screenOrientation = parseScreenOrientation(str2);
                return;
            }
            if ("label".equals(str)) {
                try {
                    identifier = context.getResources().getIdentifier(str2, "string", IncrementComponentManager.sPackageName);
                } catch (Throwable unused) {
                }
                if (identifier != 0) {
                    activityInfo.labelRes = identifier;
                    return;
                } else {
                    activityInfo.nonLocalizedLabel = str2;
                    return;
                }
            }
            try {
                if ("icon".equals(str)) {
                    activityInfo.icon = context.getResources().getIdentifier(str2, null, IncrementComponentManager.sPackageName);
                } else if ("banner".equals(str)) {
                    if (Build.VERSION.SDK_INT < 20) {
                    } else {
                        activityInfo.banner = context.getResources().getIdentifier(str2, null, IncrementComponentManager.sPackageName);
                    }
                } else if (!"logo".equals(str)) {
                } else {
                    activityInfo.logo = context.getResources().getIdentifier(str2, null, IncrementComponentManager.sPackageName);
                }
            } catch (Throwable unused2) {
            }
        }
    };

    private static abstract class AttrTranslator<T_RESULT> {
        private AttrTranslator() {
        }

        void onInit(Context context, int i, XmlPullParser xmlPullParser) {
        }

        abstract void onTranslate(Context context, int i, String str, String str2, T_RESULT t_result);

        final void translate(Context context, int i, XmlPullParser xmlPullParser, T_RESULT t_result) {
            onInit(context, i, xmlPullParser);
            int attributeCount = xmlPullParser.getAttributeCount();
            for (int i2 = 0; i2 < attributeCount; i2++) {
                if ("android".equals(xmlPullParser.getAttributePrefix(i2))) {
                    onTranslate(context, i, xmlPullParser.getAttributeName(i2), xmlPullParser.getAttributeValue(i2), t_result);
                }
            }
        }
    }

    private IncrementComponentManager() {
        throw new UnsupportedOperationException();
    }

    private static synchronized void ensureInitialized() {
        if (!sInitialized) {
            throw new IllegalStateException("Not initialized!!");
        }
    }

    public static synchronized boolean init(Context context, ShareSecurityCheck shareSecurityCheck) throws IOException {
        XmlPullParser xmlPullParserNewPullParser;
        Context baseContext;
        ?? r2 = "assets/inc_component_meta.txt";
        if (!shareSecurityCheck.getMetaContentMap().containsKey("assets/inc_component_meta.txt")) {
            Log.i("Tinker.IncrementCompMgr", "package has no incremental component meta, skip init.");
            return false;
        }
        while ((context instanceof ContextWrapper) && (baseContext = ((ContextWrapper) context).getBaseContext()) != null) {
            context = baseContext;
        }
        sContext = context;
        sPackageName = context.getPackageName();
        StringReader stringReader = new StringReader(shareSecurityCheck.getMetaContentMap().get("assets/inc_component_meta.txt"));
        try {
            try {
                xmlPullParserNewPullParser = Xml.newPullParser();
            } catch (XmlPullParserException e) {
                e = e;
            } catch (Throwable th) {
                th = th;
                r2 = 0;
                if (r2 != 0) {
                }
                SharePatchFileUtil.closeQuietly(stringReader);
                throw th;
            }
            try {
                xmlPullParserNewPullParser.setInput(stringReader);
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if ("activity".equalsIgnoreCase(name)) {
                            ActivityInfo activity = parseActivity(context, xmlPullParserNewPullParser);
                            CLASS_NAME_TO_ACTIVITY_INFO_MAP.put(activity.name, activity);
                        } else if (!"service".equalsIgnoreCase(name) && !"receiver".equalsIgnoreCase(name)) {
                            "provider".equalsIgnoreCase(name);
                        }
                    }
                }
                sInitialized = true;
                if (xmlPullParserNewPullParser != null) {
                    try {
                        xmlPullParserNewPullParser.setInput(null);
                    } catch (Throwable unused) {
                    }
                }
                SharePatchFileUtil.closeQuietly(stringReader);
                return true;
            } catch (XmlPullParserException e2) {
                e = e2;
                throw new IOException(e);
            }
        } catch (Throwable th2) {
            th = th2;
            if (r2 != 0) {
                try {
                    r2.setInput(null);
                } catch (Throwable unused2) {
                }
            }
            SharePatchFileUtil.closeQuietly(stringReader);
            throw th;
        }
    }

    public static boolean isIncrementActivity(String str) {
        ensureInitialized();
        return str != null && CLASS_NAME_TO_ACTIVITY_INFO_MAP.containsKey(str);
    }

    private static synchronized ActivityInfo parseActivity(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ActivityInfo activityInfo;
        activityInfo = new ActivityInfo();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        activityInfo.applicationInfo = applicationInfo;
        activityInfo.packageName = sPackageName;
        activityInfo.processName = applicationInfo.processName;
        activityInfo.launchMode = 0;
        activityInfo.permission = applicationInfo.permission;
        activityInfo.screenOrientation = -1;
        activityInfo.taskAffinity = applicationInfo.taskAffinity;
        if (Build.VERSION.SDK_INT >= 11 && (applicationInfo.flags & 536870912) != 0) {
            activityInfo.flags |= 512;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            activityInfo.documentLaunchMode = 0;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            activityInfo.uiOptions = applicationInfo.uiOptions;
        }
        ACTIVITY_INFO_ATTR_TRANSLATOR.translate(context, 0, xmlPullParser, activityInfo);
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= depth)) {
                break;
            }
            if (next != 3 && next != 4) {
                String name = xmlPullParser.getName();
                if ("intent-filter".equalsIgnoreCase(name)) {
                    parseIntentFilter(context, activityInfo.name, xmlPullParser);
                } else if ("meta-data".equalsIgnoreCase(name)) {
                    parseMetaData(context, activityInfo, xmlPullParser);
                }
            }
        }
        return activityInfo;
    }

    private static synchronized void parseIntentFilter(Context context, String str, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        IntentFilter intentFilter = new IntentFilter();
        String attributeValue = xmlPullParser.getAttributeValue(null, "priority");
        if (!TextUtils.isEmpty(attributeValue)) {
            intentFilter.setPriority(Integer.decode(attributeValue).intValue());
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "autoVerify");
        if (!TextUtils.isEmpty(attributeValue2)) {
            try {
                ShareReflectUtil.findMethod((Class<?>) IntentFilter.class, "setAutoVerify", (Class<?>[]) new Class[]{Boolean.TYPE}).invoke(intentFilter, Boolean.valueOf("true".equalsIgnoreCase(attributeValue2)));
            } catch (Throwable unused) {
            }
        }
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= depth)) {
                break;
            }
            if (next != 3 && next != 4) {
                String name = xmlPullParser.getName();
                if ("action".equals(name)) {
                    String attributeValue3 = xmlPullParser.getAttributeValue(null, "name");
                    if (attributeValue3 != null) {
                        intentFilter.addAction(attributeValue3);
                    }
                } else if ("category".equals(name)) {
                    String attributeValue4 = xmlPullParser.getAttributeValue(null, "name");
                    if (attributeValue4 != null) {
                        intentFilter.addCategory(attributeValue4);
                    }
                } else if ("data".equals(name)) {
                    String attributeValue5 = xmlPullParser.getAttributeValue(null, "mimeType");
                    if (attributeValue5 != null) {
                        try {
                            intentFilter.addDataType(attributeValue5);
                        } catch (IntentFilter.MalformedMimeTypeException e) {
                            throw new XmlPullParserException("bad mimeType", xmlPullParser, e);
                        }
                    }
                    String attributeValue6 = xmlPullParser.getAttributeValue(null, "scheme");
                    if (attributeValue6 != null) {
                        intentFilter.addDataScheme(attributeValue6);
                    }
                    if (Build.VERSION.SDK_INT >= 19) {
                        String attributeValue7 = xmlPullParser.getAttributeValue(null, "ssp");
                        if (attributeValue7 != null) {
                            intentFilter.addDataSchemeSpecificPart(attributeValue7, 0);
                        }
                        String attributeValue8 = xmlPullParser.getAttributeValue(null, "sspPrefix");
                        if (attributeValue8 != null) {
                            intentFilter.addDataSchemeSpecificPart(attributeValue8, 1);
                        }
                        String attributeValue9 = xmlPullParser.getAttributeValue(null, "sspPattern");
                        if (attributeValue9 != null) {
                            intentFilter.addDataSchemeSpecificPart(attributeValue9, 2);
                        }
                    }
                    String attributeValue10 = xmlPullParser.getAttributeValue(null, "host");
                    String attributeValue11 = xmlPullParser.getAttributeValue(null, "port");
                    if (attributeValue10 != null) {
                        intentFilter.addDataAuthority(attributeValue10, attributeValue11);
                    }
                    String attributeValue12 = xmlPullParser.getAttributeValue(null, "path");
                    if (attributeValue12 != null) {
                        intentFilter.addDataPath(attributeValue12, 0);
                    }
                    String attributeValue13 = xmlPullParser.getAttributeValue(null, "pathPrefix");
                    if (attributeValue13 != null) {
                        intentFilter.addDataPath(attributeValue13, 1);
                    }
                    String attributeValue14 = xmlPullParser.getAttributeValue(null, "pathPattern");
                    if (attributeValue14 != null) {
                        intentFilter.addDataPath(attributeValue14, 2);
                    }
                }
                skipCurrentTag(xmlPullParser);
            }
        }
        CLASS_NAME_TO_INTENT_FILTER_MAP.put(str, intentFilter);
    }

    private static synchronized void parseMetaData(Context context, ActivityInfo activityInfo, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ClassLoader classLoader = IncrementComponentManager.class.getClassLoader();
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "value");
        if (!TextUtils.isEmpty(attributeValue)) {
            if (activityInfo.metaData == null) {
                activityInfo.metaData = new Bundle(classLoader);
            }
            activityInfo.metaData.putString(attributeValue, attributeValue2);
        }
    }

    public static ActivityInfo queryActivityInfo(String str) {
        ensureInitialized();
        if (str != null) {
            return CLASS_NAME_TO_ACTIVITY_INFO_MAP.get(str);
        }
        return null;
    }

    public static ResolveInfo resolveIntent(Intent intent) {
        IntentFilter intentFilter;
        String className;
        ensureInitialized();
        ComponentName component = intent.getComponent();
        int i = -1;
        int i2 = 0;
        if (component != null) {
            className = component.getClassName();
            if (CLASS_NAME_TO_ACTIVITY_INFO_MAP.containsKey(className)) {
                i = 0;
            } else {
                className = null;
            }
            intentFilter = null;
        } else {
            String str = null;
            intentFilter = null;
            int i3 = -1;
            int i4 = 0;
            for (Map.Entry<String, IntentFilter> entry : CLASS_NAME_TO_INTENT_FILTER_MAP.entrySet()) {
                String key = entry.getKey();
                IntentFilter value = entry.getValue();
                int iMatch = value.match(intent.getAction(), intent.getType(), intent.getScheme(), intent.getData(), intent.getCategories(), "Tinker.IncrementCompMgr");
                boolean z = (iMatch == -3 || iMatch == -4 || iMatch == -2 || iMatch == -1) ? false : true;
                int priority = value.getPriority();
                if (z && priority > i3) {
                    intentFilter = value;
                    str = key;
                    i4 = iMatch;
                    i3 = priority;
                }
            }
            className = str;
            i = i3;
            i2 = i4;
        }
        if (className == null) {
            return null;
        }
        ResolveInfo resolveInfo = new ResolveInfo();
        resolveInfo.activityInfo = CLASS_NAME_TO_ACTIVITY_INFO_MAP.get(className);
        resolveInfo.filter = intentFilter;
        resolveInfo.match = i2;
        resolveInfo.priority = i;
        resolveInfo.resolvePackageName = sPackageName;
        resolveInfo.icon = resolveInfo.activityInfo.icon;
        resolveInfo.labelRes = resolveInfo.activityInfo.labelRes;
        return resolveInfo;
    }

    private static void skipCurrentTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }
}

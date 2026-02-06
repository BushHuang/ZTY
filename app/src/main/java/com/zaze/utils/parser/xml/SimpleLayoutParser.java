package com.zaze.utils.parser.xml;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.zaze.utils.log.ZLog;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

public class SimpleLayoutParser {
    private static final String ATTR_CONTAINER = "container";
    private static final String ATTR_WORKSPACE = "workspace";
    private static final String FORMATTED_LAYOUT_RES = "default_layout_%dx%d";
    private static final String FORMATTED_LAYOUT_RES_WITH_HOSTEAT = "default_layout_%dx%d_h%s";
    private static final String LAYOUT_RES = "default_layout";
    private static final String TAG_INCLUDE = "include";
    private static final String TAG_NAME_SPACE = "http://schemas.android.com/apk/res-auto/com.android.launcher3";
    private static final String TAG_RESOLVE = "resolve";
    private ContentValues mValues = new ContentValues();
    private Resources resources;

    public SimpleLayoutParser(Context context) {
        this.resources = context.getResources();
    }

    private void endDocument() {
        ZLog.i("XML[ZZ]", "endDocument", new Object[0]);
    }

    private void endElement(XmlResourceParser xmlResourceParser) {
        ZLog.i("XML[ZZ]", "结束解析 : " + xmlResourceParser.getName(), new Object[0]);
        ZLog.i("XML[ZZ]", "-----------------------------------", new Object[0]);
    }

    protected static int getAttributeResourceValue(XmlResourceParser xmlResourceParser, String str, int i) {
        int attributeResourceValue = xmlResourceParser.getAttributeResourceValue("http://schemas.android.com/apk/res-auto/com.android.launcher3", str, i);
        return attributeResourceValue == i ? xmlResourceParser.getAttributeResourceValue(null, str, i) : attributeResourceValue;
    }

    private void startDocument() {
        ZLog.i("XML[ZZ]", "startDocument", new Object[0]);
    }

    private void startElement(XmlResourceParser xmlResourceParser, ArrayList<Long> arrayList) throws Resources.NotFoundException {
        ZLog.i("XML[ZZ]", "-----------------------------------", new Object[0]);
        String name = xmlResourceParser.getName();
        ZLog.i("XML[ZZ]", "开始解析 : " + name, new Object[0]);
        if ("include".equals(name)) {
            int attributeResourceValue = getAttributeResourceValue(xmlResourceParser, "workspace", 0);
            if (attributeResourceValue != 0) {
                parser(attributeResourceValue, arrayList);
                return;
            }
            return;
        }
        if ("resolve".equals(name)) {
            this.mValues.clear();
            for (int i = 0; i < xmlResourceParser.getAttributeCount(); i++) {
                ZLog.i("XML[ZZ]", xmlResourceParser.getAttributeName(i) + " = " + xmlResourceParser.getAttributeValue(i), new Object[0]);
            }
        }
    }

    protected void characters(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        ZLog.i("XML[ZZ]", "characters = " + xmlResourceParser.nextText(), new Object[0]);
    }

    public void parser(int i, ArrayList<Long> arrayList) throws Resources.NotFoundException {
        try {
            XmlResourceParser xml = this.resources.getXml(i);
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    startDocument();
                } else if (eventType == 2) {
                    startElement(xml, arrayList);
                } else if (eventType == 3) {
                    endElement(xml);
                } else if (eventType == 4) {
                    characters(xml);
                }
            }
            endDocument();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}

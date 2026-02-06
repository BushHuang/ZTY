package com.zaze.utils.parser.xml;

import org.xml.sax.Attributes;

public interface XmlParserCallback {
    void characters(char[] cArr, int i, int i2);

    void endDocument();

    void endElement(String str, String str2);

    void startDocument();

    void startElement(String str, Attributes attributes);
}

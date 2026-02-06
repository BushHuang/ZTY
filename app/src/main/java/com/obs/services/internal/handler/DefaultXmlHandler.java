package com.obs.services.internal.handler;

import java.util.LinkedList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public abstract class DefaultXmlHandler extends DefaultHandler {
    private StringBuilder currText = null;
    private final LinkedList<String> context = new LinkedList<>();

    @Override
    public void characters(char[] cArr, int i, int i2) {
        this.currText.append(cArr, i, i2);
    }

    @Override
    public void endDocument() {
    }

    public void endElement(String str, String str2) {
        this.context.removeLast();
    }

    @Override
    public void endElement(String str, String str2, String str3) {
        endElement(str2, this.currText.toString());
    }

    @Override
    public void startDocument() {
    }

    public void startElement(String str) {
        this.context.add(str);
    }

    @Override
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        this.currText = new StringBuilder();
        startElement(str2, attributes);
    }

    public void startElement(String str, Attributes attributes) {
        startElement(str);
    }
}

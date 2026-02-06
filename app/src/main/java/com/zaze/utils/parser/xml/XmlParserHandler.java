package com.zaze.utils.parser.xml;

import android.text.TextUtils;
import com.zaze.utils.log.ZLog;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParserHandler {
    private XmlParserCallback parser;
    private ContentParserHandler parserHandler = new ContentParserHandler();

    private class ContentParserHandler extends DefaultHandler {
        private String value;

        private ContentParserHandler() {
            this.value = "";
        }

        @Override
        public void characters(char[] cArr, int i, int i2) throws SAXException {
            super.characters(cArr, i, i2);
            this.value = new String(cArr, i, i2);
            XmlParserHandler.this.parser.characters(cArr, i, i2);
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            XmlParserHandler.this.parser.endDocument();
        }

        @Override
        public void endElement(String str, String str2, String str3) throws SAXException {
            super.endElement(str, str2, str3);
            ZLog.i("XML[ZZ]", "qName : %s; value : %s", str3, this.value);
            XmlParserHandler.this.parser.endElement(str3, this.value);
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            XmlParserHandler.this.parser.startDocument();
        }

        @Override
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            super.startElement(str, str2, str3, attributes);
            XmlParserHandler.this.parser.startElement(str3, attributes);
        }
    }

    public XmlParserHandler(XmlParserCallback xmlParserCallback) {
        this.parser = xmlParserCallback;
    }

    public void parser(String str) throws SAXException, IOException {
        try {
            XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xMLReader.setContentHandler(this.parserHandler);
            ZLog.i("XML[ZZ]", "parser value : " + str, new Object[0]);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            xMLReader.parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

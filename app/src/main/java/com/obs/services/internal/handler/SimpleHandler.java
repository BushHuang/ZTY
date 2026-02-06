package com.obs.services.internal.handler;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public abstract class SimpleHandler extends DefaultHandler {
    private static final ILogger log = LoggerBuilder.getLogger((Class<?>) SimpleHandler.class);
    protected SimpleHandler currentHandler;
    protected SimpleHandler parentHandler = null;
    private StringBuffer textContent;
    protected XMLReader xr;

    public SimpleHandler(XMLReader xMLReader) {
        this.xr = null;
        this.textContent = null;
        this.currentHandler = null;
        this.xr = xMLReader;
        this.textContent = new StringBuffer();
        this.currentHandler = this;
    }

    @Override
    public void characters(char[] cArr, int i, int i2) {
        this.textContent.append(cArr, i, i2);
    }

    public void controlReturned(SimpleHandler simpleHandler) {
    }

    @Override
    public void endElement(String str, String str2, String str3) {
        String strTrim = this.textContent.toString().trim();
        try {
            this.currentHandler.getClass().getMethod("end" + str2, String.class).invoke(this.currentHandler, strTrim);
        } catch (NoSuchMethodException unused) {
            if (log.isDebugEnabled()) {
                log.debug((CharSequence) ("Skipped non-existent SimpleHandler subclass's endElement method for '" + str2 + "' in " + getClass().getName()));
            }
        } catch (Throwable th) {
            if (log.isErrorEnabled()) {
                log.error("Unable to invoke SimpleHandler subclass's endElement method for '" + str2 + "' in " + getClass().getName(), th);
            }
        }
        this.textContent = new StringBuffer();
    }

    public boolean isChildHandler() {
        return this.parentHandler != null;
    }

    public void returnControlToParentHandler() {
        if (isChildHandler()) {
            SimpleHandler simpleHandler = this.parentHandler;
            simpleHandler.currentHandler = simpleHandler;
            simpleHandler.controlReturned(this);
            SimpleHandler simpleHandler2 = this.parentHandler;
            this.currentHandler = simpleHandler2;
            this.xr.setContentHandler(simpleHandler2);
            this.xr.setErrorHandler(this.currentHandler);
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) ("Ignoring call to return control to parent handler, as this class has no parent: " + getClass().getName()));
        }
    }

    @Override
    public void startElement(String str, String str2, String str3, Attributes attributes) {
        try {
            this.currentHandler.getClass().getMethod("start" + str2, new Class[0]).invoke(this.currentHandler, new Object[0]);
        } catch (NoSuchMethodException unused) {
            if (log.isDebugEnabled()) {
                log.debug((CharSequence) ("Skipped non-existent SimpleHandler subclass's startElement method for '" + str2 + "' in " + getClass().getName()));
            }
        } catch (Throwable th) {
            if (log.isErrorEnabled()) {
                log.error("Unable to invoke SimpleHandler subclass's startElement method for '" + str2 + "' in " + getClass().getName(), th);
            }
        }
    }

    public void transferControlToHandler(SimpleHandler simpleHandler) {
        this.currentHandler = simpleHandler;
        simpleHandler.parentHandler = this;
        this.xr.setContentHandler(simpleHandler);
        this.xr.setErrorHandler(this.currentHandler);
    }
}

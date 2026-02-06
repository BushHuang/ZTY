package com.obs.services.internal;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import java.io.Serializable;
import java.util.Properties;

public class ObsProperties implements Serializable {
    private static final ILogger log = LoggerBuilder.getLogger((Class<?>) ObsProperties.class);
    private static final long serialVersionUID = -822234326095333142L;
    private final Properties properties = new Properties();

    private static String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public void clearAllProperties() {
        this.properties.clear();
    }

    public void clearProperty(String str) {
        this.properties.remove(str);
    }

    public boolean containsKey(String str) {
        return this.properties.containsKey(str);
    }

    public boolean getBoolProperty(String str, boolean z) throws IllegalArgumentException {
        String strTrim = trim(this.properties.getProperty(str, String.valueOf(z)));
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) (str + "=" + strTrim));
        }
        if ("true".equalsIgnoreCase(strTrim)) {
            return true;
        }
        if ("false".equalsIgnoreCase(strTrim)) {
            return false;
        }
        throw new IllegalArgumentException("Boolean value '" + strTrim + "' for obs property '" + str + "' must be 'true' or 'false' (case-insensitive)");
    }

    public int getIntProperty(String str, int i) throws NumberFormatException {
        String strTrim = trim(this.properties.getProperty(str, String.valueOf(i)));
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) (str + "=" + strTrim));
        }
        return Integer.parseInt(strTrim);
    }

    public long getLongProperty(String str, long j) throws NumberFormatException {
        String strTrim = trim(this.properties.getProperty(str, String.valueOf(j)));
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) (str + "=" + strTrim));
        }
        return Long.parseLong(strTrim);
    }

    public String getStringProperty(String str, String str2) {
        String strTrim = trim(this.properties.getProperty(str, str2));
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) (str + "=" + strTrim));
        }
        return strTrim;
    }

    public void setProperty(String str, String str2) {
        if (str2 == null) {
            clearProperty(str);
        } else {
            this.properties.put(str, trim(str2));
        }
    }
}

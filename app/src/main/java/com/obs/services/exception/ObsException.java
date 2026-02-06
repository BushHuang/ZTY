package com.obs.services.exception;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObsException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private String errorCode;
    private String errorHostId;
    private String errorIndicator;
    private String errorMessage;
    private String errorRequestId;
    private int responseCode;
    private Map<String, String> responseHeaders;
    private String responseStatus;
    private String xmlMessage;

    public ObsException(String str) {
        this(str, null, null);
    }

    public ObsException(String str, String str2) {
        this(str, str2, null);
    }

    public ObsException(String str, String str2, Throwable th) {
        super(str, th);
        this.xmlMessage = null;
        this.errorCode = null;
        this.errorMessage = null;
        this.errorRequestId = null;
        this.errorHostId = null;
        this.responseHeaders = null;
        this.responseCode = -1;
        this.responseStatus = null;
        this.errorIndicator = null;
        if (str2 != null) {
            parseXmlMessage(str2);
        }
    }

    public ObsException(String str, Throwable th) {
        this(str, null, th);
    }

    private String findXmlElementText(String str, String str2) {
        Matcher matcher = Pattern.compile(".*<" + str2 + ">(.*)</" + str2 + ">.*").matcher(str);
        if (matcher.matches() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        return null;
    }

    private boolean isParsedFromXmlMessage() {
        return this.xmlMessage != null;
    }

    private void parseXmlMessage(String str) {
        String strReplaceAll = str.replaceAll("\n", "");
        this.xmlMessage = strReplaceAll;
        this.errorCode = findXmlElementText(strReplaceAll, "Code");
        this.errorMessage = findXmlElementText(strReplaceAll, "Message");
        this.errorRequestId = findXmlElementText(strReplaceAll, "RequestId");
        this.errorHostId = findXmlElementText(strReplaceAll, "HostId");
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorHostId() {
        return this.errorHostId;
    }

    public String getErrorIndicator() {
        return this.errorIndicator;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getErrorRequestId() {
        return this.errorRequestId;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public Map<String, String> getResponseHeaders() {
        return this.responseHeaders;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public String getXmlMessage() {
        return this.xmlMessage;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setErrorHostId(String str) {
        this.errorHostId = str;
    }

    public void setErrorIndicator(String str) {
        this.errorIndicator = str;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setErrorRequestId(String str) {
        this.errorRequestId = str;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public void setResponseHeaders(Map<String, String> map) {
        this.responseHeaders = map;
    }

    public void setResponseStatus(String str) {
        this.responseStatus = str;
    }

    public void setXmlMessage(String str) {
        this.xmlMessage = str;
    }

    @Override
    public String toString() {
        String string = super.toString();
        if (this.responseCode != -1) {
            string = string + " -- ResponseCode: " + this.responseCode + ", ResponseStatus: " + this.responseStatus;
        }
        if (isParsedFromXmlMessage()) {
            return string + ", XML Error Message: " + this.xmlMessage;
        }
        if (this.errorRequestId == null) {
            return string;
        }
        return string + ", RequestId: " + this.errorRequestId + ", HostId: " + this.errorHostId;
    }
}

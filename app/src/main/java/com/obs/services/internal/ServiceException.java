package com.obs.services.internal;

import com.jamesmurty.utils.XMLBuilder;
import com.obs.services.internal.utils.ServiceUtils;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -757626557833455141L;
    private String errorCode;
    private String errorHostId;
    private String errorIndicator;
    private String errorMessage;
    private String errorRequestId;
    private String requestHost;
    private String requestPath;
    private String requestVerb;
    private int responseCode;
    private String responseDate;
    private Map<String, String> responseHeaders;
    private String responseStatus;
    private String xmlMessage;

    public ServiceException() {
        this.responseCode = -1;
    }

    public ServiceException(String str) {
        super(str);
        this.responseCode = -1;
    }

    public ServiceException(String str, String str2) {
        this(str, str2, null);
    }

    public ServiceException(String str, String str2, Throwable th) {
        super(str, th);
        this.responseCode = -1;
        if (ServiceUtils.isValid(str2)) {
            parseXmlMessage(str2);
        }
    }

    public ServiceException(String str, Throwable th) {
        super(str, th);
        this.responseCode = -1;
    }

    public ServiceException(Throwable th) {
        super(th);
        this.responseCode = -1;
    }

    private String findXmlElementText(String str, String str2) {
        Matcher matcher = Pattern.compile(".*<" + str2 + ">(.*)</" + str2 + ">.*").matcher(str);
        if (matcher.matches() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        return null;
    }

    private void parseXmlMessage(String str) {
        String strReplaceAll = str.replaceAll("\n", "");
        this.xmlMessage = strReplaceAll;
        this.errorCode = findXmlElementText(strReplaceAll, "Code");
        this.errorMessage = findXmlElementText(strReplaceAll, "Message");
        this.errorRequestId = findXmlElementText(strReplaceAll, "RequestId");
        this.errorHostId = findXmlElementText(strReplaceAll, "HostId");
        String strFindXmlElementText = findXmlElementText(strReplaceAll, "Details");
        if (strFindXmlElementText == null || strFindXmlElementText.length() <= 0) {
            return;
        }
        this.errorMessage += " " + strFindXmlElementText;
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

    public String getRequestHost() {
        return this.requestHost;
    }

    public String getRequestPath() {
        return this.requestPath;
    }

    public String getRequestVerb() {
        return this.requestVerb;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getResponseDate() {
        return this.responseDate;
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

    public XMLBuilder getXmlMessageAsBuilder() throws ParserConfigurationException, SAXException, IOException {
        if (this.xmlMessage == null) {
            return null;
        }
        return XMLBuilder.parse(new InputSource(new StringReader(this.xmlMessage)));
    }

    public boolean isParsedFromXmlMessage() {
        return this.xmlMessage != null;
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

    public void setRequestAndHostIds(String str, String str2) {
        this.errorRequestId = str;
        this.errorHostId = str2;
    }

    public void setRequestHost(String str) {
        this.requestHost = str;
    }

    public void setRequestPath(String str) {
        this.requestPath = str;
    }

    public void setRequestVerb(String str) {
        this.requestVerb = str;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public void setResponseDate(String str) {
        this.responseDate = str;
    }

    public void setResponseHeaders(Map<String, String> map) {
        this.responseHeaders = map;
    }

    public void setResponseStatus(String str) {
        this.responseStatus = str;
    }

    @Override
    public String toString() {
        String str;
        String string = super.toString();
        if (this.requestVerb != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(" ");
            sb.append(this.requestVerb);
            sb.append(" '");
            sb.append(this.requestPath);
            sb.append("'");
            String str2 = "";
            if (this.requestHost != null) {
                str = " on Host '" + this.requestHost + "'";
            } else {
                str = "";
            }
            sb.append(str);
            if (this.responseDate != null) {
                str2 = " @ '" + this.responseDate + "'";
            }
            sb.append(str2);
            string = sb.toString();
        }
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

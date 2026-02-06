package com.xh.xhcore.common.http.strategy.xh.security;

import com.xh.xhcore.common.base.XhBaseApplication;
import java.util.HashMap;
import java.util.Map;

public class DownloadSecurityConfig {
    private Map<String, String> metadata;

    public DownloadSecurityConfig() {
        this.metadata = new HashMap();
    }

    public DownloadSecurityConfig(Map<String, String> map) {
        this.metadata = new HashMap();
        this.metadata = map;
    }

    private Map<String, String> getMetadata() {
        int[] classIds;
        Map<String, String> map = this.metadata;
        if (!map.containsKey(ConstSecurity.SECURITY_SCHOOL_ID)) {
            map.put(ConstSecurity.SECURITY_SCHOOL_ID, XhBaseApplication.getSchoolId());
        }
        if (!map.containsKey(ConstSecurity.SECURITY_CLASS_ID) && (classIds = XhBaseApplication.getClassIds()) != null && classIds.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i : classIds) {
                sb.append(i);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            map.put(ConstSecurity.SECURITY_CLASS_ID, sb.toString());
        }
        if (!map.containsKey(ConstSecurity.SECURITY_USER_ID)) {
            map.put(ConstSecurity.SECURITY_USER_ID, XhBaseApplication.getXHUserId());
        }
        if (!map.containsKey(ConstSecurity.SECURITY_IS_TEACHER)) {
            map.put(ConstSecurity.SECURITY_IS_TEACHER, String.valueOf(XhBaseApplication.getUserType() == 4));
        }
        return map;
    }

    public String getConditions() {
        Map<String, String> metadata = getMetadata();
        return metadata.isEmpty() ? "" : ConstSecurity.mapToJsonStr(metadata);
    }
}

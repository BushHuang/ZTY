package com.xuehai.provider.core.font;

import java.io.Serializable;

public class FontEntity implements Serializable {
    private String fontCode;
    private String fontName;
    private String fontType;
    private String fontUri;
    private String md5;
    private int operationType;
    private long uploadTime;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FontEntity fontEntity = (FontEntity) obj;
        if (this.operationType != fontEntity.operationType || this.uploadTime != fontEntity.uploadTime) {
            return false;
        }
        String str = this.fontCode;
        if (str == null ? fontEntity.fontCode != null : !str.equals(fontEntity.fontCode)) {
            return false;
        }
        String str2 = this.fontType;
        if (str2 == null ? fontEntity.fontType != null : !str2.equals(fontEntity.fontType)) {
            return false;
        }
        String str3 = this.fontName;
        if (str3 == null ? fontEntity.fontName != null : !str3.equals(fontEntity.fontName)) {
            return false;
        }
        String str4 = this.md5;
        if (str4 == null ? fontEntity.md5 != null : !str4.equals(fontEntity.md5)) {
            return false;
        }
        String str5 = this.fontUri;
        String str6 = fontEntity.fontUri;
        return str5 != null ? str5.equals(str6) : str6 == null;
    }

    public String getFontCode() {
        return this.fontCode;
    }

    public String getFontName() {
        return this.fontName;
    }

    public String getFontType() {
        return this.fontType;
    }

    public String getFontUri() {
        return this.fontUri;
    }

    public String getMd5() {
        return this.md5;
    }

    public int getOperationType() {
        return this.operationType;
    }

    public long getUploadTime() {
        return this.uploadTime;
    }

    public int hashCode() {
        String str = this.fontCode;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.fontType;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.fontName;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.md5;
        int iHashCode4 = (((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.operationType) * 31;
        String str5 = this.fontUri;
        int iHashCode5 = (iHashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        long j = this.uploadTime;
        return iHashCode5 + ((int) (j ^ (j >>> 32)));
    }

    public void setFontCode(String str) {
        this.fontCode = str;
    }

    public void setFontName(String str) {
        this.fontName = str;
    }

    public void setFontType(String str) {
        this.fontType = str;
    }

    public void setFontUri(String str) {
        this.fontUri = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setOperationType(int i) {
        this.operationType = i;
    }

    public void setUploadTime(long j) {
        this.uploadTime = j;
    }

    public String toString() {
        return "FontEntity{fontCode='" + this.fontCode + "', fontType='" + this.fontType + "', fontName='" + this.fontName + "', md5='" + this.md5 + "', operationType=" + this.operationType + ", fontUri='" + this.fontUri + "', uploadTime=" + this.uploadTime + '}';
    }
}

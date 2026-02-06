package com.xuehai.provider.core.font;

public enum FontType {
    SANS_REGULAR("1"),
    SANS_BOLD("2"),
    SANS_MEDIUM("3"),
    SERIF_REGULAR("4"),
    SERIF_BOLD("5"),
    DIN_REGULAR("6"),
    DIN_BOLD("7"),
    DIN_MEDIUM("8"),
    BRUSH_SCRIPT_MT_ITALIC("9"),
    DIN_BLACK_ITALIC("10");

    private String fontCode;

    FontType(String str) {
        this.fontCode = str;
    }

    public String getFontCode() {
        return this.fontCode;
    }
}

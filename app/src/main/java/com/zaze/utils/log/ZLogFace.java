package com.zaze.utils.log;

public interface ZLogFace {
    void d(String str, String str2);

    void e(String str, String str2);

    String getLogFromFile();

    void i(String str, String str2);

    void v(String str, String str2);

    void w(String str, String str2);

    void writeLogToFile(String str, String str2);
}

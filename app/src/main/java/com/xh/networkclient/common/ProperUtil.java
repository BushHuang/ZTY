package com.xh.networkclient.common;

import java.io.IOException;
import java.util.Properties;

public class ProperUtil {
    private static Properties props;

    public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(ProperUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        props = properties;
        return properties;
    }
}

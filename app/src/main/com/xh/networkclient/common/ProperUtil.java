package com.xh.networkclient.common;

import java.io.InputStream;
import java.util.Properties;

public class ProperUtil {
	private static Properties props;

	public static Properties getProperties() {
		Properties propsTmp = new Properties();
		try {
			InputStream in = ProperUtil.class.getClassLoader()
					.getResourceAsStream("config.properties");
			propsTmp.load(in);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		props = propsTmp;
		return props;
	}
}

package com.xh;

public class nativeUtils {
	
	public static native void JNIZipFiles(String files, String ZipPath);

	static {
		System.loadLibrary("xh_common");
	}
}

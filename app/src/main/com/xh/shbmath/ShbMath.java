package com.xh.shbmath;

import android.util.Log;

public class ShbMath {
	static public int CreateGifFromEq(String expression, String gifFilePath){
		Log.v("SHB_MATH", "expression=" + expression + " gifFilePath=" + gifFilePath);
		return JNICreateGifFromEq(expression, gifFilePath);
	}
	
	static native private int JNICreateGifFromEq(String expression, String gifFilePath);
	
	static {
		System.loadLibrary("shbmath");
	}
}

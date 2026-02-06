package com.xh.networkclient.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.xh.logutils.Log;
import com.xh.xhcore.common.util.XHLog;

public class FileMD5 {

	/**
	 * 对一个文件获取md5值
	 * 
	 * @return md5串
	 */
	public static String getMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest MD5 =MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}

			return new String(Hex.encodeHex(MD5.digest())).toUpperCase();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 对一个文件获取md5值
	 * 
	 * @return md5串
	 */
	public static byte[] calcMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest MD5 =MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return MD5.digest();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 对一个文件获取md5值
	 * 
	 * @return md5串
	 */
	public static String getMD5(byte[] flows) {
		try {
			MessageDigest MD5 =MessageDigest.getInstance("MD5");
			MD5.update(flows);
			return new String(Hex.encodeHex(MD5.digest())).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 求一个字符串的md5值
	 * 
	 * @param target
	 *            字符串
	 * @return md5 value
	 */
	public static String MD5(String target) {
		return DigestUtils.md5Hex(target);
	}

//	public static void main(String[] args) {
//		String path = "F:\\开发工具\\JAVA类开发工具\\Android\\android-studio-bundle-141.1980579-windows.exe";
//
//		for (;;) {
//			long beginTime = System.currentTimeMillis();
//			File fileZIP = new File(path);
//			String md5 = getMD5(fileZIP);
//			long endTime = System.currentTimeMillis();
//			System.out.println("MD5:" + md5 + "\n time:" + ((endTime - beginTime) / 1000) + "s");
//		}
//	}
}

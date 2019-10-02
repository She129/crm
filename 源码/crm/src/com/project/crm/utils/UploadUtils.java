package com.project.crm.utils;

import java.util.UUID;

public class UploadUtils {
	public static String getUUidFileName(String fileName) {
		int index = fileName.lastIndexOf(".");
		String extionds = fileName.substring(index);
		return UUID.randomUUID().toString().replace("-", "") + extionds;
	}

	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf;
		return "/" + d1 + "/" + d2;

	}

	public static void main(String[] args) {
		System.out.println(getUUidFileName("aa.txt"));
		System.out.println(getPath("aa2dasd.txt"));
	}
}

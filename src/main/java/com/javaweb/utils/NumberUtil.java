package com.javaweb.utils;

public class NumberUtil {
	public static Boolean checkNumber(String data) {
		try {
			Long number=Long.parseLong(data);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
}

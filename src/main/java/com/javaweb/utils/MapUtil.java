package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
	public static <T> T getBuild(Map<String, Object> params, String name, Class<T> tClass) {
		Object object = params.getOrDefault(name, null);
		if (object != null) {
			if (
			/* tClass.getName().equals("java.lang.Long" */
			tClass == Long.class) {
				object = object != "" ? Long.valueOf(object.toString()) : null;
			} else if (tClass.getName().equals("java.lang.Interger")) {
				object = object != "" ? Integer.valueOf(object.toString()) : null;
			} else if (tClass.getName().equals("java.lang.String")) {
				object = String.valueOf(object.toString());
			}
		}
		return tClass.cast(object);
	}

}

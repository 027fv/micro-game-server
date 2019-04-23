package com.sy.utils;

public class ClassUtil {

	public static Class<?> getSuperclass(Object obj) {
		return obj.getClass().getSuperclass();
	}

	public static Class<?>[] getInterfaces(Object obj) {
		return obj.getClass().getInterfaces();
	}

}

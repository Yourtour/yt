package com.yt.core.utils;

import java.lang.reflect.Field;

public class EnumUtils {

	public static String valueOf(Enum<?> value) throws Exception {
		Field field = value.getClass().getField("code");
		if (field == null) {
			throw new Exception(String.format(
					"The Enum[%s] hasn't a field named 'code'.", value
							.getClass().getName()));
		}
		return (String) field.get(value);
	}

	public static Enum<?> valueOf(String code,
			@SuppressWarnings("rawtypes") Class<? extends Enum> clazz)
			throws Exception {
		Enum<?>[] ecs = clazz.getEnumConstants();
		for (Enum<?> ec : ecs) {
			Field field = ec.getClass().getField("code");
			if (field == null) {
				throw new Exception(String.format(
						"The Enum[%s] hasn't a field named 'code'.",
						clazz.getName()));
			}
			Object value = field.get(ec);
			if (value.equals(code)) {
				return ec;
			}
		}
		throw new Exception(String.format(
				"The Enum[%s] not contained the Enum Constants named '%s'.",
				clazz.getName(), code));
	}

	public static Enum<?> createDefault(
			@SuppressWarnings("rawtypes") Class<? extends Enum> clazz) {
		return clazz.getEnumConstants()[0];
	}

}

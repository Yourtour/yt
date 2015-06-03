package com.yt.core.utils;

import java.lang.reflect.Field;

/**
 * 针对Enum枚举类型的通用操作工具类
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月3日</td>
 * <td>john</td>
 * <td>Create</td>
 * </tr>
 * </table>
 * 
 * @author john
 * 
 * @version 1.0
 * @since 1.0
 */
public class EnumUtils {

	/**
	 * 获取枚举值中的code字段内容
	 * 
	 * @param value
	 *            枚举值
	 * @return 枚举值的code内容，如果枚举类没有实现public类型的code字段，则抛出异常。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public static String valueOf(Enum<?> value) throws Exception {
		Field field = value.getClass().getField("code");
		if (field == null) {
			throw new Exception(String.format(
					"The Enum[%s] hasn't a field named 'code'.", value
							.getClass().getName()));
		}
		return (String) field.get(value);
	}

	/**
	 * 根据传入的枚举类和代码，创建一个指定代码内容的枚举值
	 * 
	 * @param code
	 *            指定的枚举值内容
	 * @param clazz
	 *            枚举类
	 * @return 指定内容的枚举值，如果该枚举类没有实现public类型的code字段，或者指定内容的枚举值不存在，均抛出异常
	 * @throws Exception
	 *             创建过程中发生的异常
	 */
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

	/**
	 * 根据传入的枚举类，创建一个默认的枚举值，默认为第一个枚举值。
	 * 
	 * @param clazz
	 *            枚举类
	 * @return 枚举值
	 */
	public static Enum<?> createDefault(
			@SuppressWarnings("rawtypes") Class<? extends Enum> clazz) {
		return clazz.getEnumConstants()[0];
	}

}

package com.yt.dal.hbase.utils;

import java.lang.reflect.Field;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.BeanDescriptor.Family;
import com.yt.dal.hbase.cache.BeanDescriptor.Qualifier;

public class HBaseUtils {

	/**
	 * 从指定的BeanDescriptor中获取指定列族和限定词（列）对应的bean字段名。
	 * 
	 * @param bd
	 *            BeanDescripor
	 * @param family
	 *            列族
	 * @param qualifier
	 *            限定词（列）
	 * @return 对应的bean字段名，如果没有，则抛出异常。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public static String getFieldName(BeanDescriptor bd, String family,
			String qualifier) throws Exception {
		if (bd == null) {
			throw new Exception("The BeanDescriptor is null.");
		}
		Family f = bd.getFamily(family);
		if (f == null) {
			throw new Exception(String.format(
					"The family[%s] is not exist in the BeanDescriptor.",
					family));
		}
		for (Qualifier q : f.getQualifiers().values()) {
			if (qualifier.equals(q.getQualifier())) {
				return q.getName();
			}
		}
		throw new Exception(
				String.format(
						"The Qualifier[%] of Family[%s] is not exist in the BeanDescriptor.",
						qualifier, family));
	}
 
	public static void set(BaseBean bean, String fieldName, byte[] value)
			throws Exception {
		// TODO
	}

	public static byte[] get(BaseBean bean, String fieldName) throws Exception {
		if (bean == null) {
			throw new Exception("The bean is null.");
		}
		if (fieldName == null || fieldName.length() <= 0) {
			throw new Exception("The field's is null.");
		}
		Field field = bean.getClass().getDeclaredField(fieldName);
		Object value = field.get(bean);
		// TODO
		return null;
	}
	
	private static byte[] toBytes(Object value) throws Exception {
		throw new Exception(String.format("The Object[%s] ", value.getClass().getName()));
	}
	
}

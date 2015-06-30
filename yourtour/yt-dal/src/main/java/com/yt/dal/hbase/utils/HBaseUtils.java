package com.yt.dal.hbase.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.util.Bytes;

import com.yt.core.utils.EnumUtils;
import com.yt.dal.hbase.IBaseBean;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.BeanDescriptor.Family;
import com.yt.dal.hbase.cache.BeanDescriptor.Qualifier;

/**
 * 操作hbase数据中常用的方法
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
 * <td>2015年5月20日</td>
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
public class HBaseUtils {
	private static final Log LOG = LogFactory.getLog(HBaseUtils.class);

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

	/**
	 * 以字节数组方式设置hbase实体对象中指定字段的值
	 * 
	 * @param bean
	 *            hbase实体对象
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            字节数组形式的数据
	 * @throws Exception
	 *             设置过程中发生的异常
	 */
	public static void set(IBaseBean bean, String fieldName, byte[] value)
			throws Exception {
		if (bean == null) {
			throw new Exception("The bean is null.");
		}
		if (fieldName == null || fieldName.length() <= 0) {
			throw new Exception("The field's is null.");
		}
		Field field = getField(bean.getClass(), fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		field.set(bean, fromBytes(value, field.getType()));
	}

	/**
	 * 以字节数组方式获取hbase实体对象中对应字段的数据
	 * 
	 * @param bean
	 *            hbase实体对象
	 * @param fieldName
	 *            字段名称
	 * @return 对应字段数据的字节数组
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public static byte[] get(IBaseBean bean, String fieldName) throws Exception {
		if (bean == null) {
			throw new Exception("The bean is null.");
		}
		if (fieldName == null || fieldName.length() <= 0) {
			throw new Exception("The field's is null.");
		}
		Field field = getField(bean.getClass(), fieldName);
		field.setAccessible(true);
		Object value = field.get(bean);
		Class<?> clazz = field.getType();
		return toBytes(value, clazz);
	}

	private static Field getField(Class<?> clazz, String fieldName) {
		if (clazz == null) {
			return null;
		}
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (Exception ex) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Get the field[%s] from Class[%s] fail.", fieldName,
						clazz.getName()), ex);
			}
			return getField(clazz.getSuperclass(), fieldName);
		}
	}

	// 根据数据字段类型，将字节数组转换为对应数据类型的数据
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object fromBytes(byte[] value, Class<?> clazz)
			throws Exception {
		if (clazz.isAssignableFrom(byte[].class)) {
			return value;
		} else if (clazz.isAssignableFrom(Byte.class)
				|| clazz.isAssignableFrom(byte.class)) {
			return (value == null ? null : value[0]);
		} else if (clazz.isAssignableFrom(BigDecimal.class)) {
			return (value == null ? null : Bytes.toBigDecimal(value));
		} else if (clazz.isAssignableFrom(Boolean.class)
				|| clazz.isAssignableFrom(boolean.class)) {
			return (value == null ? false : Bytes.toBoolean(value));
		} else if (clazz.isAssignableFrom(ByteBuffer.class)) {
			return (value == null ? null : ByteBuffer.wrap(value));
		} else if (clazz.isAssignableFrom(Double.class)
				|| clazz.isAssignableFrom(double.class)) {
			return (value == null ? 0.0 : Bytes.toDouble(value));
		} else if (clazz.isAssignableFrom(Float.class)
				|| clazz.isAssignableFrom(float.class)) {
			return (value == null ? 0.0 : Bytes.toFloat(value));
		} else if (clazz.isAssignableFrom(Integer.class)
				|| clazz.isAssignableFrom(int.class)) {
			return (value == null ? 0 : Bytes.toInt(value));
		} else if (clazz.isAssignableFrom(Long.class)
				|| clazz.isAssignableFrom(long.class)) {
			return (value == null ? 0l : Bytes.toLong(value));
		} else if (clazz.isAssignableFrom(Short.class)
				|| clazz.isAssignableFrom(short.class)) {
			return (value == null ? 0 : Bytes.toShort(value));
		} else if (clazz.isAssignableFrom(String.class)) {
			return (value == null ? "" : Bytes.toString(value));
		} else if (clazz.isEnum()) {
			Class<? extends Enum> enumClass = (Class<Enum>) clazz;
			if (value == null) {
				return EnumUtils.createDefault(enumClass);
			}
			String code = Bytes.toString(value);
			return EnumUtils.valueOf(code, enumClass);
		} else if (clazz.isAssignableFrom(IBaseBean.class)) {
			if (value == null) {
				return null;
			}
			String associatedString = Bytes.toString(value);
			String rowKey = associatedString.substring(associatedString
					.indexOf('@'));
			IBaseBean bean = (IBaseBean) clazz.newInstance();
			bean.setRowKey(rowKey);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Instance a BaseBean[%s] Object, rowkey: %s.",
						clazz.getName(), rowKey));
			}
			return bean;
		}
		throw new Exception(String.format(
				"The Type[%s] can not be supported to transform to bytes.",
				clazz.getName()));
	}

	// 根据数据字段类型，将数据内容转换为字节数组
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static byte[] toBytes(Object value, Class<?> clazz)
			throws Exception {
		if (clazz.isAssignableFrom(byte[].class)) {
			return (value == null ? new byte[0] : (byte[]) value);
		} else if (clazz.isAssignableFrom(Byte.class)
				|| clazz.isAssignableFrom(byte.class)) {
			value = (value == null ? 0x0 : value);
			byte[] result = new byte[0];
			result[0] = (byte) value;
			return result;
		} else if (clazz.isAssignableFrom(BigDecimal.class)) {
			value = (value == null ? 0 : value);
			return Bytes.toBytes((BigDecimal) value);
		} else if (clazz.isAssignableFrom(Boolean.class)
				|| clazz.isAssignableFrom(boolean.class)) {
			value = (value == null ? false : value);
			return Bytes.toBytes((Boolean) value);
		} else if (clazz.isAssignableFrom(ByteBuffer.class)) {
			value = (value == null ? ByteBuffer.allocate(0) : value);
			return Bytes.toBytes((ByteBuffer) value);
		} else if (clazz.isAssignableFrom(Double.class)
				|| clazz.isAssignableFrom(double.class)) {
			value = (value == null ? 0.0 : value);
			return Bytes.toBytes((Double) value);
		} else if (clazz.isAssignableFrom(Float.class)
				|| clazz.isAssignableFrom(float.class)) {
			value = (value == null ? 0.0 : value);
			return Bytes.toBytes((Float) value);
		} else if (clazz.isAssignableFrom(Integer.class)
				|| clazz.isAssignableFrom(int.class)) {
			value = (value == null ? 0 : value);
			return Bytes.toBytes((Integer) value);
		} else if (clazz.isAssignableFrom(Long.class)
				|| clazz.isAssignableFrom(long.class)) {
			value = (value == null ? 0l : value);
			return Bytes.toBytes((Long) value);
		} else if (clazz.isAssignableFrom(Short.class)
				|| clazz.isAssignableFrom(short.class)) {
			value = (value == null ? 0 : value);
			return Bytes.toBytes((Short) value);
		} else if (clazz.isAssignableFrom(String.class)) {
			value = (value == null ? "" : value);
			return Bytes.toBytes((String) value);
		} else if (clazz.isEnum()) {
			if (value == null) {
				value = EnumUtils.createDefault((Class<Enum>) clazz);
			}
			return Bytes.toBytes(EnumUtils.valueOf((Enum) value));
		} else if (clazz.isAssignableFrom(IBaseBean.class)) {
			if (value == null) {
				throw new Exception(
						String.format(
								"The Type[%] is assigned from BaseBean, but the object is null.",
								clazz.getName()));
			}
			return Bytes.toBytes(getAssociatedString((IBaseBean) value));
		}
		throw new Exception(String.format(
				"The Type[%s] can not be supported to transform to bytes.",
				clazz.getName()));
	}

	/**
	 * 从关联的 BaseBean中获取关联字段描述
	 * 
	 * @param bean
	 *            hbase实体
	 * @return 唯一标示该hbase实体的关联字符串，形如：“行键@命名空间:表名”。
	 * @throws Exception
	 */
	public static String getAssociatedString(IBaseBean bean) throws Exception {
		Class<? extends IBaseBean> clazz = bean.getClass();
		if (!clazz.isAnnotationPresent(HbaseTable.class)) {
			throw new Exception(String.format(
					"The Class[%s] is not annotated from HbaseTable.",
					clazz.getName()));
		}
		HbaseTable table = clazz.getAnnotation(HbaseTable.class);
		String ns = table.namespace();
		if ("".equals(ns)) {
			ns = "default";
		}
		return String.format("%s@%s:%s", bean.getRowKey(), ns, table.name());
	}

}

package com.yt.dal.hbase.cache;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.util.Bytes;

import com.yt.dal.hbase.IBaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

/**
 * 描述hbase表的相关信息的对象
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
 * <td>2015年5月18日</td>
 * <td>john</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年5月23日</td>
 * <td>john</td>
 * <td>添加了表的namespace字段</td>
 * </tr>
 * </table>
 * 
 * @author john
 * 
 * @version 1.0
 * @since 1.0
 */
public class BeanDescriptor implements Serializable {

	private static final Log LOG = LogFactory.getLog(BeanDescriptor.class);

	private static final long serialVersionUID = -1774104157037112048L;

	private String namespace, key, tableName, startKey, endKey, version;

	private int regionNum = 1;

	private Map<String, Family> families;

	/**
	 * 描述hbase表中列族信息的对象
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
	 * <td>2015年5月18日</td>
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
	public class Family implements Serializable {

		private static final long serialVersionUID = 3302112517456411913L;

		private String name;

		private byte[] byteFamily;

		private Map<String, Qualifier> qualifiers;

		/**
		 * 默认构造方法
		 */
		public Family() {
			super();
			this.qualifiers = new HashMap<String, Qualifier>();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#clone()
		 */
		@Override
		protected Family clone() {
			Family f = new Family();
			f.name = name;
			f.byteFamily = Arrays.copyOf(byteFamily, byteFamily.length);
			f.qualifiers = new HashMap<String, Qualifier>();
			for (Qualifier q : qualifiers.values()) {
				f.qualifiers.put(q.getName(), q.clone());
			}
			return f;
		}

		/**
		 * 获取列族名称
		 * 
		 * @return 列族名称
		 */
		public String getName() {
			return name;
		}

		/**
		 * 设置列族名称
		 * 
		 * @param family
		 *            设置列族
		 */
		protected void setFamily(String family) {
			this.name = family;
			this.byteFamily = Bytes.toBytes(family);
		}

		/**
		 * 获取列族字节序数据，便于提升性能
		 * 
		 * @return 列族字节序数据
		 */
		public byte[] getByteFamily() {
			return byteFamily;
		}

		/**
		 * 获取本表中所有的限定词（列）对象集合
		 * 
		 * @return 限定词（列）对象集合
		 */
		public Map<String, Qualifier> getQualifiers() {
			return qualifiers;
		}

		/**
		 * 获取指定限定词字段名的限定词（列）对象
		 * 
		 * @param name
		 *            限定词（列）对应的字段（hbase实体类中的字段）名
		 * @return 限定词（列）对象
		 */
		public Qualifier getQualifier(String name) {
			return qualifiers.get(name);
		}

	}

	/**
	 * 描述hbase表中限定词相关信息的对象
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
	 * <td>2015年5月18日</td>
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
	public class Qualifier implements Serializable {

		private static final long serialVersionUID = -6999447702862612932L;
		private String name, qualifier, version = "1.0";
		private byte[] byteQualifier;

		/**
		 * 默认构造方法
		 */
		public Qualifier() {
			super();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#clone()
		 */
		@Override
		protected Qualifier clone() {
			Qualifier q = new Qualifier();
			q.name = name;
			q.qualifier = qualifier;
			q.version = version;
			q.byteQualifier = Arrays
					.copyOf(byteQualifier, byteQualifier.length);
			return q;
		}

		/**
		 * 获取限定词对应的字段名称（在hbase实体类中的字段）
		 * 
		 * @return hbase实体类的字段名
		 */
		public String getName() {
			return name;
		}

		/**
		 * 获取限定词（列）名称
		 * 
		 * @return 限定词（列）
		 */
		public String getQualifier() {
			return qualifier;
		}

		/**
		 * 获取限定词（列）的版本
		 * 
		 * @return 限定词（列）的版本号
		 */
		public String getVersion() {
			return version;
		}

		/**
		 * 设置hbase表中的限定词（列）和hbase实体类中的字段映射关系
		 * 
		 * @param name
		 *            列属性名称，对应hbase实体类中的字段名
		 * @param qualifier
		 *            限定词（列）
		 */
		protected void setQualifier(String name, String qualifier) {
			this.name = name;
			if ("".equals(qualifier) || qualifier == null) {
				// 如果没有设置qualifier，则默认使用字段名称替代。
				this.qualifier = name;
			} else {
				this.qualifier = qualifier;
			}
			this.byteQualifier = Bytes.toBytes(this.qualifier);
		}

		/**
		 * 获取限定词的字节序数据，便于提升性能
		 * 
		 * @return 限定词的字节序数据
		 */
		public byte[] getByteQualifier() {
			return byteQualifier;
		}
	}

	/**
	 * 默认构造方法
	 */
	public BeanDescriptor() {
		super();
		this.families = new HashMap<String, Family>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected BeanDescriptor clone() {
		BeanDescriptor bd = new BeanDescriptor();
		bd.key = key;
		bd.namespace = namespace;
		bd.tableName = tableName;
		bd.startKey = startKey;
		bd.endKey = endKey;
		bd.version = version;
		bd.regionNum = regionNum;
		bd.families = new HashMap<String, Family>();
		for (Family f : families.values()) {
			bd.families.put(f.getName(), f.clone());
		}
		return bd;
	}

	/**
	 * 工厂方法，根据指定的hbase实体类，创建一个对应的hbase实体描述对象，便于后续的DDL和CRUD操作。<br>
	 * <b>注意：</b>hbase实体类必须从BaseBean继承而来，且必须使用HbaseTable和HbaseColumn进行注解。
	 * 
	 * @param clazz
	 *            hbase实体类
	 * @return 如果成功创建，返回hbase实体类描述对象；否则返回null。
	 * @see BaseBean
	 * @see HbaseTable
	 * @see HbaseColumn
	 */
	public static BeanDescriptor valueOf(Class<? extends IBaseBean> clazz) {
		if (!clazz.isAnnotationPresent(HbaseTable.class)) {
			// 如果没有使用HbaseTable进行注解，则返回null。
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format(
						"The Class[%s] not be annotation the Table.",
						clazz.getName()));
			}
			return null;
		}
		BeanDescriptor bd = new BeanDescriptor();
		bd.key = clazz.getName();
		HbaseTable table = clazz.getAnnotation(HbaseTable.class);
		bd.namespace = table.namespace();
		bd.tableName = table.name();
		bd.startKey = table.startKey();
		bd.endKey = table.endKey();
		bd.regionNum = table.regionNum();
		scanFields(clazz, bd);
		Class<?> supClass = clazz.getSuperclass();
		if (supClass != null) {
			scanFields(supClass, bd);
		}
		return bd;
	}

	// 扫描IBaseBean实现类中的hbase标记属性
	private static void scanFields(Class<?> clazz,
			BeanDescriptor bd) {
		Field[] fields = clazz.getDeclaredFields();
		String version = "0.0";
		// 循环处理每一列，并获取列族信息和版本信息。
		for (Field field : fields) {
			if (!field.isAnnotationPresent(HbaseColumn.class)) {
				continue;
			}
			HbaseColumn column = field.getAnnotation(HbaseColumn.class);
			String familyName = column.family(), qualifierName = column.name();
			if ("".equals(familyName)) {
				familyName = "d";
			}
			Family family = null;
			if (bd.families.containsKey(familyName)) {
				family = bd.families.get(familyName);
			} else {
				family = bd.createFamily(familyName);
				bd.families.put(familyName, family);
			}

			Qualifier qualifier = null;
			if (family.getQualifiers().containsKey(qualifierName)) {
				qualifier = family.getQualifiers().get(qualifierName);
			} else {
				qualifier = bd.createQualifier(field.getName(), qualifierName);
				family.getQualifiers().put(qualifier.getName(), qualifier);
				qualifier.version = column.version();
			}
			if (bd.compareVersion(version, qualifier.version) < 0) {
				version = qualifier.version;
			}
		}
		bd.version = version;
	}

	// 创建一个列族描述对象
	private Family createFamily(String name) {
		Family family = new Family();
		family.setFamily(name);
		return family;
	}

	// 创建一个限定词描述对象
	private Qualifier createQualifier(String name, String qualifier) {
		Qualifier q = new Qualifier();
		q.setQualifier(name, qualifier);
		return q;
	}

	// 比较两个版本号的大小，如果version1 > version2，则返回大于0的整数。
	private int compareVersion(String version1, String version2) {
		// 根据版本号的规则比较大小
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");
		for (int index = 0, minNum = Math.min(v1.length, v2.length); index < minNum; index++) {
			int vi1 = Integer.valueOf(v1[index]), vi2 = Integer
					.valueOf(v2[index]);
			if (vi1 == vi2) {
				continue;
			} else {
				return vi1 - vi2;
			}
		}
		return v1.length - v2.length;
	}

	/**
	 * 获取本表对应的键值，直接映射到该实体的类名
	 * 
	 * @return 键，唯一标识一个hbase实体对象
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 获取本表的命名空间名称
	 * 
	 * @return 命名空间
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * 获取本表的名称
	 * 
	 * @return 表名
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 获取完整的hbase表名，包括命名空间
	 * 
	 * @return 带有namespace的表名
	 */
	public String getFullTableName() {
		if (namespace != null && !"".equals(namespace)) {
			return String.format("%s:%s", namespace, tableName);
		} else {
			return tableName;
		}
	}

	/**
	 * 获取本表分区的开始键值
	 * 
	 * @return 分区的开始键值
	 */
	public String getStartKey() {
		return startKey;
	}

	/**
	 * 获取本表分区的结束键值
	 * 
	 * @return 分区的结束键值
	 */
	public String getEndKey() {
		return endKey;
	}

	/**
	 * 获取本表的当前版本号
	 * 
	 * @return 表的当前版本号，为所有字段的最大版本号
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 获取本表中的分区个数
	 * 
	 * @return 分区个数，返回1表示不分区
	 */
	public int getRegionNum() {
		return regionNum;
	}

	/**
	 * 获取本表中所有的列族对象集合
	 * 
	 * @return 列族集合
	 */
	public Map<String, Family> getFamilies() {
		return families;
	}

	/**
	 * 根据列族名字获取本表中指定的类族对象
	 * 
	 * @param key
	 *            列族名
	 * @return 列族
	 */
	public Family getFamily(String key) {
		return families.get(key);
	}

}

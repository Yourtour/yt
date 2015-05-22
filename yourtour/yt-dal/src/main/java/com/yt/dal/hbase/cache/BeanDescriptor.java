package com.yt.dal.hbase.cache;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.util.Bytes;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

public class BeanDescriptor implements Serializable {

	private static final Log LOG = LogFactory.getLog(BeanDescriptor.class);

	private static final long serialVersionUID = -1774104157037112048L;

	private String key, tableName, startKey, endKey, version;

	private int regionNum = 1;

	private Map<String, Family> families;

	public class Family implements Serializable {

		private static final long serialVersionUID = 3302112517456411913L;

		private String name;

		private byte[] byteFamily;

		private Map<String, Qualifier> qualifiers;

		public Family() {
			super();
			this.qualifiers = new HashMap<String, Qualifier>();
		}

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

		public String getName() {
			return name;
		}

		protected void setFamily(String family) {
			this.name = family;
			this.byteFamily = Bytes.toBytes(family);
		}

		public byte[] getByteFamily() {
			return byteFamily;
		}

		public Map<String, Qualifier> getQualifiers() {
			return qualifiers;
		}

		public Qualifier getQualifier(String name) {
			return qualifiers.get(name);
		}

	}

	public class Qualifier implements Serializable {

		private static final long serialVersionUID = -6999447702862612932L;
		private String name, qualifier, version = "1.0";
		private byte[] byteQualifier;

		public Qualifier() {
			super();
		}

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

		public String getName() {
			return name;
		}

		public String getQualifier() {
			return qualifier;
		}

		public String getVersion() {
			return version;
		}

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

		public byte[] getByteQualifier() {
			return byteQualifier;
		}
	}

	public BeanDescriptor() {
		super();
		this.families = new HashMap<String, Family>();
	}

	@Override
	protected BeanDescriptor clone() {
		BeanDescriptor bd = new BeanDescriptor();
		bd.key = key;
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

	public static BeanDescriptor valueOf(Class<? extends BaseBean> clazz) {
		if (!clazz.isAnnotationPresent(HbaseTable.class)) {
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
		bd.tableName = table.name();
		bd.startKey = table.startKey();
		bd.endKey = table.endKey();
		bd.regionNum = table.regionNum();
		Field[] fields = clazz.getDeclaredFields();
		String version = "0.0";
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
			}
			qualifier.version = column.version();
			if (bd.compareVersion(version, qualifier.version) < 0) {
				version = qualifier.version;
			}
		}
		bd.version = version;
		return bd;
	}

	private Family createFamily(String name) {
		Family family = new Family();
		family.setFamily(name);
		return family;
	}

	private Qualifier createQualifier(String name, String qualifier) {
		Qualifier q = new Qualifier();
		q.setQualifier(name, qualifier);
		return q;
	}

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

	public String getKey() {
		return key;
	}

	public String getTableName() {
		return tableName;
	}

	public String getStartKey() {
		return startKey;
	}

	public String getEndKey() {
		return endKey;
	}

	public String getVersion() {
		return version;
	}

	public int getRegionNum() {
		return regionNum;
	}

	public Map<String, Family> getFamilies() {
		return families;
	}
	
	public Family getFamily(String key) {
		return families.get(key);
	}

}

package com.yt.dal.hbase;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;

import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.BeanDescriptor.Family;
import com.yt.dal.hbase.cache.IBeanDescriptorCache;

/**
 * 进行DDL操作（建表、删表、修改表）的接口实现类。
 * 
 * <p>
 * <b>修改历史:</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年5月13日</td>
 * <td>John Peng</td>
 * <td>初始创建</td>
 * </tr>
 * </table>
 * 
 * @author John Peng
 * 
 * @version 1。0
 * @since 1.0
 */
public class DdlGeneralOperate implements IDdlOperate {
	private static final Log LOG = LogFactory.getLog(DdlGeneralOperate.class);
	/** 成员变量：Hbase操作管理器 */
	@Autowired
	private HbaseManager manager;

	/** 成员变量：BeanDescriptor缓存操作接口 */
	@Autowired
	private IBeanDescriptorCache cache;

	/**
	 * 默认构造方法
	 */
	public DdlGeneralOperate() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#createTable(java.lang.String)
	 */
	@Override
	public void createTable(String tableName) throws Exception {
		if (tableExist(tableName)) {
			throw new Exception(String.format("The table[%s] has existed.",
					tableName));
		}
		HTableDescriptor htd = new HTableDescriptor(
				TableName.valueOf(tableName));
		manager.getAdmin().createTable(htd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#createTable(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public void createTable(String tableName, String[] families)
			throws Exception {
		createTable(tableName, families, null);
	}

	private void createTable(String tableName, String[] families,
			Map<String, byte[]> properties) throws Exception {
		if (tableExist(tableName)) {
			throw new Exception(String.format("The table[%s] has existed.",
					tableName));
		}
		HTableDescriptor htd = new HTableDescriptor(
				TableName.valueOf(tableName));
		for (String family : families) {
			HColumnDescriptor hcd = new HColumnDescriptor(family);
			htd.addFamily(hcd);
		}
		if (properties != null) {
			for (String key : properties.keySet()) {
				htd.setValue(Bytes.toBytes(key), properties.get(key));
			}
		}
		manager.getAdmin().createTable(htd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#createTable(java.lang.Class)
	 */
	@Override
	public void createTable(Class<? extends BaseBean> clazz) throws Exception {
		BeanDescriptor bd = cache.get(clazz);
		if (bd == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The BeanDescriptor[%s] not exist.",
						clazz.getName()));
			}
		}
		String tableName = bd.getTableName();
		if (tableExist(tableName)) {
			throw new Exception(String.format("The table[%s] has existed.",
					tableName));
		}
		String[] families = new String[bd.getFamilies().size()];
		int index = 0;
		for (Family family : bd.getFamilies().values()) {
			families[index++] = family.getName();
		}
		Map<String, byte[]> properties = new HashMap<String, byte[]>(4);
		properties.put("startKey", Bytes.toBytes(bd.getStartKey()));
		properties.put("endKey", Bytes.toBytes(bd.getEndKey()));
		properties.put("regionNum", Bytes.toBytes(bd.getRegionNum()));
		properties.put("version", Bytes.toBytes(bd.getVersion()));
		createTable(tableName, families, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#dropTable(java.lang.String)
	 */
	@Override
	public void dropTable(String tableName) throws Exception {
		try {
			if (tableExist(tableName)) {
				Admin admin = manager.getAdmin();
				TableName tn = TableName.valueOf(tableName);
				admin.disableTable(tn);
				admin.deleteTable(tn);
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format("The table[%s] is not exist.",
							tableName));
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#dropTable(java.lang.Class)
	 */
	@Override
	public void dropTable(Class<? extends BaseBean> clazz) throws Exception {
		BeanDescriptor bd = cache.get(clazz);
		if (bd == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The BeanDescriptor[%s] not exist.",
						clazz.getName()));
			}
		}
		String tableName = bd.getTableName();
		dropTable(tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#tableExist(java.lang.String)
	 */
	@Override
	public boolean tableExist(String tableName) throws Exception {
		return manager.getAdmin().tableExists(TableName.valueOf(tableName));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#tableExist(java.lang.Class)
	 */
	@Override
	public boolean tableExist(Class<? extends BaseBean> clazz) throws Exception {
		BeanDescriptor bd = cache.get(clazz);
		if (bd == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The BeanDescriptor[%s] not exist.",
						clazz.getName()));
			}
		}
		String tableName = bd.getTableName();
		return tableExist(tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#alterTable(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void alterTable(String tableName, String family) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IDdlOperate#alterTable(java.lang.Class)
	 */
	@Override
	public void alterTable(Class<? extends BaseBean> clazz) {
		// TODO Auto-generated method stub

	}
}

package com.yt.dal.hbase;

import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;

import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.IBeanDescriptorCache;

public class CrudGeneralOperate implements ICrudOperate {
	private static final Log LOG = LogFactory.getLog(CrudGeneralOperate.class);

	@Autowired
	private HbaseManager manager;

	@Autowired
	private IDdlOperate ddlOperate;

	@Autowired
	private IBeanDescriptorCache cache;

	@Override
	public BaseBean save(BaseBean bean) throws Exception {
		return save(bean, true);
	}

	@SuppressWarnings("unchecked")
	private BaseBean save(BaseBean bean, boolean saveTimestamp)
			throws Exception {
		if (bean == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The bean is null.");
			}
			return null;
		}
		Class<BaseBean> clazz = (Class<BaseBean>)bean.getClass();
		BeanDescriptor bd = cache.get(clazz);
		if (bd == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The BeanDescriptor[%s] not exist.",
						clazz.getName()));
			}
		}
		String tableName = bd.getTableName();
		if (!manager.tableExist(tableName)) {
			ddlOperate.createTable(tableName);
		}
		Table table = manager.getConnection().getTable(
				TableName.valueOf(tableName));
		byte[] rowkey = Bytes.toBytes(bean.getRowKey());
		Vector<Put> batch = new Vector<Put>();
		for (BeanDescriptor.Family family : bd.getFamilies().values()) {
			byte[] byteFamily = family.getByteFamily();
			for (BeanDescriptor.Qualifier qualifier : family.getQualifiers()
					.values()) {
				Put put = new Put(rowkey);
				byte[] byteQualifier = qualifier.getByteQualifier();
				byte[] byteValue = Bytes.toBytes(BeanUtils.getProperty(bean,
						qualifier.getName()));
				if (saveTimestamp) {
					put.addColumn(byteFamily, byteQualifier,
							System.currentTimeMillis(), byteValue);
				} else {
					put.addColumn(byteFamily, byteQualifier, byteValue);
				}
				batch.add(put);
			}
		}
		Object[] result = new Object[batch.size()];
		table.batch(batch, result);
		// setBeanValue(bean, result);
		return bean;
	}

	@Override
	public List<BaseBean> save(List<BaseBean> beans) throws Exception {
		List<BaseBean> result = new Vector<BaseBean>(beans.size());
		for (BaseBean bean : beans) {
			result.add(save(bean));
		}
		return result;
	}

	@Override
	public BaseBean saveOne(BaseBean bean) throws Exception {
		return save(bean, false);
	}

	@Override
	public List<BaseBean> saveOne(List<BaseBean> beans) throws Exception {
		List<BaseBean> result = new Vector<BaseBean>(beans.size());
		for (BaseBean bean : beans) {
			result.add(saveOne(bean));
		}
		return result;
	}

	@Override
	public BaseBean getNewest(String tableName, String rowKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseBean> get(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseBean> get(String tableName, String rowKey) {
		// TODO Auto-generated method stub
		return null;
	}

}

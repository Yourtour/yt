package com.yt.dal.hbase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#save(com.yt.dal.hbase.BaseBean)
	 */
	@Override
	public void save(BaseBean bean) throws Exception {
		if (bean == null) {
			return;
		}
		List<BaseBean> beans = new Vector<BaseBean>(1);
		beans.add(bean);
		saveBatch(beans, true);
	}

	// 以Batch的方式批量保存数据
	private void saveBatch(List<BaseBean> beans, boolean saveTimestamp)
			throws Exception {
		if (beans == null || beans.isEmpty()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The bean is null or em.");
			}
			return;
		}
		Map<String, List<Put>> batch = new HashMap<String, List<Put>>();
		for (BaseBean bean : beans) {
			Class<? extends BaseBean> clazz = bean.getClass();
			BeanDescriptor bd = cache.get(clazz);
			if (bd == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format("The BeanDescriptor[%s] not exist.",
							clazz.getName()));
				}
			}
			List<Put> puts = getBatchPuts(bd, bean, saveTimestamp);
			String ftn = bd.getFullTableName();
			if (batch.containsKey(ftn)) {
				batch.get(ftn).addAll(puts);
			} else {
				batch.put(ftn, puts);
			}
		}
		// batch save a table
		int num = 0;
		for (String ftn : batch.keySet()) {
			Table table = manager.getConnection().getTable(
					TableName.valueOf(ftn));
			List<Put> list = batch.get(ftn);
			Object[] putResult = new Object[list.size()];
			table.batch(list, putResult);
			num += putResult.length;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Save data successfully, total put %d operate.", num));
		}
	}

	// 转换并获取一个实体对应的Put批处理列表，便于后续高效存储数据。
	private List<Put> getBatchPuts(BeanDescriptor bd, BaseBean bean,
			boolean saveTimestamp) throws Exception {
		Vector<Put> puts = new Vector<Put>();
		byte[] rowkey = Bytes.toBytes(bean.getRowKey());
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
				puts.add(put);
			}
		}
		return puts;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#save(java.util.List)
	 */
	@Override
	public void save(List<BaseBean> beans) throws Exception {
		saveBatch(beans, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#saveOne(com.yt.dal.hbase.BaseBean)
	 */
	@Override
	public void saveOne(BaseBean bean) throws Exception {
		if (bean == null) {
			return;
		}
		List<BaseBean> beans = new Vector<BaseBean>(1);
		beans.add(bean);
		saveBatch(beans, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#saveOne(java.util.List)
	 */
	@Override
	public void saveOne(List<BaseBean> beans) throws Exception {
		saveBatch(beans, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#deleteRow(com.yt.dal.hbase.BaseBean)
	 */
	@Override
	public void deleteRow(BaseBean bean) throws Exception {
		if (bean == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The bean is null, the delete operate be ignored.");
			}
			return;
		}
		List<BaseBean> beans = new Vector<BaseBean>(1);
		beans.add(bean);
		deleteRows(beans);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#deleteRows(java.util.List)
	 */
	@Override
	public void deleteRows(List<BaseBean> beans) throws Exception {
		if (beans == null || beans.isEmpty()) {
			return;
		}
		Map<String, List<Delete>> batch = new HashMap<String, List<Delete>>();
		for (BaseBean bean : beans) {
			Class<? extends BaseBean> clazz = bean.getClass();
			BeanDescriptor bd = cache.get(clazz);
			if (bd == null) {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format("The BeanDescriptor[%s] not exist.",
							clazz.getName()));
				}
			}
			String ftn = bd.getFullTableName();
			Delete del = new Delete(Bytes.toBytes(bean.getRowKey()));
			if (batch.containsKey(ftn)) {
				batch.get(ftn).add(del);
			} else {
				List<Delete> list = new Vector<Delete>();
				list.add(del);
				batch.put(ftn, list);
			}
		}
		// batch save a table
		int rows = 0;
		for (String ftn : batch.keySet()) {
			Table table = manager.getConnection().getTable(
					TableName.valueOf(ftn));
			List<Delete> list = batch.get(ftn);
			Object[] delResult = new Object[list.size()];
			table.batch(list, delResult);
			rows += delResult.length;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("delete data[%d rows] successfully.", rows));
		}
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

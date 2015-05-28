package com.yt.dal.hbase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;

import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.BeanDescriptor.Family;
import com.yt.dal.hbase.cache.BeanDescriptor.Qualifier;
import com.yt.dal.hbase.cache.IBeanDescriptorCache;
import com.yt.dal.hbase.utils.HBaseUtils;

/**
 * hbase中进行CRUD操作的实现类。
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#save(java.util.List)
	 */
	@Override
	public void save(List<? extends BaseBean> beans) throws Exception {
		saveBatch(beans, true);
	}

	// 以Batch的方式批量保存数据
	private void saveBatch(List<? extends BaseBean> beans, boolean saveTimestamp)
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
			//table.batch(list, putResult);
			 table.put(list);
			table.close();
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
				Put put = null;
				if (saveTimestamp) {
					put = new Put(rowkey, System.currentTimeMillis());
				} else {
					put = new Put(rowkey);
				}
				byte[] byteQualifier = qualifier.getByteQualifier();
				byte[] byteValue = Bytes.toBytes(BeanUtils.getProperty(bean,
						qualifier.getName()));
				if (saveTimestamp) {
					put.addColumn(byteFamily, byteQualifier,
							System.currentTimeMillis(), byteValue);
				} else {
					put.addColumn(byteFamily, byteQualifier, byteValue);
				}
				put.setDurability(Durability.SYNC_WAL);
				puts.add(put);
			}
		}
		return puts;
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
	public void deleteRows(List<? extends BaseBean> beans) throws Exception {
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
			for (Family family : bd.getFamilies().values()) {
				for (Qualifier qualifier : family.getQualifiers().values()) {
					del.addColumn(family.getByteFamily(),
							qualifier.getByteQualifier());
				}
			}
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
			//table.batch(list, delResult);
			table.delete(list);
			table.close();
			rows += delResult.length;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("delete data[%d rows] successfully.", rows));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#getNewest(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public BaseBean getNewest(Class<? extends BaseBean> clazz, String rowKey)
			throws Exception {
		BeanDescriptor bd = cache.get(clazz);
		if (bd == null) {
			throw new Exception(String.format(
					"The BeanDescriptor[%s] is not exist.", clazz.getName()));
		}
		BaseBean bean = clazz.newInstance();
		try (Table table = manager.getConnection().getTable(
				TableName.valueOf(bd.getFullTableName()))) {
			Get get = new Get(Bytes.toBytes(rowKey));
			bean.setRowKey(rowKey);
			Result rs = table.get(get);
			if (rs.isEmpty()) {
				return null;
			}
			for (Cell cell : rs.rawCells()) {
				String family = Bytes.toString(CellUtil.cloneFamily(cell));
				String qualifier = Bytes
						.toString(CellUtil.cloneQualifier(cell));
				byte[] value = CellUtil.cloneValue(cell);
				HBaseUtils.set(bean,
						HBaseUtils.getFieldName(bd, family, qualifier), value);
			}
			return bean;
		} catch (IOException ex) {
			throw new Exception(String.format(
					"Get the data[class=%s, rowkey=%s] fail.", clazz.getName(),
					rowKey));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#get(java.lang.Class)
	 */
	@Override
	public List<? extends BaseBean> get(Class<? extends BaseBean> clazz)
			throws Exception {
		BeanDescriptor bd = cache.get(clazz);
		if (bd == null) {
			throw new Exception(String.format(
					"The BeanDescriptor[%s] is not exist.", clazz.getName()));
		}
		List<BaseBean> result = new Vector<BaseBean>();
		try (Table table = manager.getConnection().getTable(
				TableName.valueOf(bd.getFullTableName()))) {
			Scan scan = new Scan();
			for (Family f : bd.getFamilies().values()) {
				scan.addFamily(f.getByteFamily());
			}
			ResultScanner rss = table.getScanner(scan);
			for (Result rs = rss.next(); rs != null; rs = rss.next()) {
				if (rs.isEmpty()) {
					continue;
				}
				BaseBean bean = clazz.newInstance();
				bean.setRowKey(Bytes.toString(rs.getRow()));
				for (Cell cell : rs.rawCells()) {
					String family = Bytes.toString(CellUtil.cloneFamily(cell));
					String qualifier = Bytes.toString(CellUtil
							.cloneQualifier(cell));
					byte[] value = CellUtil.cloneValue(cell);
					HBaseUtils.set(bean,
							HBaseUtils.getFieldName(bd, family, qualifier),
							value);
				}
				result.add(bean);
			}
		} catch (IOException ex) {
			throw new Exception(String.format("Scan the data[class=%s] fail.",
					clazz.getName()));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.ICrudOperate#get(java.lang.Class, long, long)
	 */
	@Override
	public List<? extends BaseBean> get(Class<? extends BaseBean> clazz,
			long ts1, long ts2) throws Exception {
		BeanDescriptor bd = cache.get(clazz);
		if (bd == null) {
			throw new Exception(String.format(
					"The BeanDescriptor[%s] is not exist.", clazz.getName()));
		}
		List<BaseBean> result = new Vector<BaseBean>();
		try (Table table = manager.getConnection().getTable(
				TableName.valueOf(bd.getFullTableName()))) {
			Scan scan = new Scan();
			for (Family f : bd.getFamilies().values()) {
				scan.addFamily(f.getByteFamily());
			}
			if (ts1 < 0) {
				ts1 = 0;
			}
			if (ts2 > 0) {
				ts2 = Long.MAX_VALUE;
			}
			scan.setTimeRange(ts1, ts2);
			ResultScanner rss = table.getScanner(scan);
			for (Result rs = rss.next(); rs != null; rs = rss.next()) {
				if (rs.isEmpty()) {
					continue;
				}
				BaseBean bean = clazz.newInstance();
				bean.setRowKey(Bytes.toString(rs.getRow()));
				for (Cell cell : rs.rawCells()) {
					String family = Bytes.toString(CellUtil.cloneFamily(cell));
					String qualifier = Bytes.toString(CellUtil
							.cloneQualifier(cell));
					byte[] value = CellUtil.cloneValue(cell);
					HBaseUtils.set(bean,
							HBaseUtils.getFieldName(bd, family, qualifier),
							value);
				}
				result.add(bean);
			}
		} catch (IOException ex) {
			throw new Exception(String.format("Scan the data[class=%s] fail.",
					clazz.getName()));
		}
		return result;
	}

}

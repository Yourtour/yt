/* 
 * @(#)HbaseStructureSync.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.
 */
package com.yt.dal.hbase.generator;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.yt.dal.hbase.IDdlOperate;
import com.yt.dal.hbase.cache.BeanDescriptor;
import com.yt.dal.hbase.cache.IBeanDescriptorCache;

/**
 * 根据DDL同步Hbase数据库结构的实现类，能够实现根据最新配置Bean实现新建、修改相关的表结构，不支持删除表结构。<br>
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
 * <td>2015年6月8日</td>
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
public class HbaseStructureSync implements InitializingBean {

	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory.getLog(HbaseStructureSync.class);

	@Autowired
	private IBeanDescriptorCache cache;

	@Autowired
	private IDdlOperate ddlOperate;

	private boolean ignoreError = false;

	/**
	 * 默认构造方法
	 */
	public HbaseStructureSync() {
		super();
	}

	/**
	 * 默认构造方法
	 * 
	 * @param ignoreError
	 *            如果设置为true，则忽略所有异常（记录异常日志），继续后续处理；否则抛出异常，中断后续处理。
	 * @see #setIgnoreError(boolean)
	 */
	public HbaseStructureSync(boolean ignoreError) {
		super();
		this.setIgnoreError(ignoreError);
	}

	/**
	 * 设置在同步hbase表结构过程中是否需要忽略异常和错误。
	 * 
	 * @param ignoreError
	 *            如果设置为true，则忽略所有异常（记录异常日志），继续后续处理；否则抛出异常，中断后续处理。
	 */
	public void setIgnoreError(boolean ignoreError) {
		this.ignoreError = ignoreError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("ignoreError = %b", ignoreError));
		}
		if (cache == null) {
			throw new Exception("The BeanDescriptorCache not be loaded.");
		}
		List<BeanDescriptor> bds = cache.get();
		if (bds.isEmpty()) {
			if (LOG.isInfoEnabled()) {
				LOG.info("There is not any BeanDescriptor be defined.");
			}
		}
		for (BeanDescriptor beanDescriptor : bds) {
			try {
				hbaseTableGenerate(beanDescriptor);
			} catch (Exception ex) {
				if (ignoreError) {
					if (LOG.isErrorEnabled()) {
						LOG.error(
								String.format(
										"Synchorizate the table[%s] fail, but the ignoreError = [%b].",
										beanDescriptor.getTableName(),
										ignoreError), ex);
					}
					continue;
				} else {
					throw ex;
				}
			}
		}
	}

	// 同步一张hbase表
	private void hbaseTableGenerate(BeanDescriptor bd) throws Exception {
		if (bd == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("The BeanDescriptor is null.");
			}
			return;
		}
		if (ddlOperate == null) {
			throw new Exception("The DdlOperate not be loaded.");
		}
		String tableName = bd.getFullTableName();
		if (!ddlOperate.tableExist(tableName)) {
			// 表不存在，则创建
			ddlOperate.createTable(bd);
		} else {
			// 表存在，则同步
			ddlOperate.alterTable(bd);
		}
	}

}

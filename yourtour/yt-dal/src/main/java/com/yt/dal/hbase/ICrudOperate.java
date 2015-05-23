package com.yt.dal.hbase;

import java.util.List;

public interface ICrudOperate {
	/**
	 * 保存指定的bean对象数据到Hbase，同时将显式保存当前的时间戳。
	 * 
	 * @param bean
	 *            待保存的bean对象，必须使用@Table进行注解。
	 */
	public void save(BaseBean bean) throws Exception;

	/**
	 * 保存指定的bean对象列表数据到Hbase，同时将显式保存当前的时间戳。
	 * 
	 * @param beans
	 *            待保持的bean对象列表，必须使用@Table进行注解。
	 */
	public void save(List<BaseBean> beans) throws Exception;

	/**
	 * 保存指定的bean对象数据到Hbase，不显式保存当前的时间戳。
	 * 
	 * @param bean
	 *            待保存的bean对象，必须使用@Table进行注解。
	 */
	public void saveOne(BaseBean bean) throws Exception;

	/**
	 * 保存指定的bean对象列表数据到Hbase，不显式保存当前的时间戳。
	 * 
	 * @param beans
	 *            待保持的bean对象列表，必须使用@Table进行注解。
	 */
	public void saveOne(List<BaseBean> beans) throws Exception;

	public void deleteRow(BaseBean bean) throws Exception;

	public void deleteRows(List<BaseBean> beans) throws Exception;

	public BaseBean getNewest(String tableName, String rowKey) throws Exception;

	public List<BaseBean> get(String tableName) throws Exception;

	public List<BaseBean> get(String tableName, String rowKey) throws Exception;
}

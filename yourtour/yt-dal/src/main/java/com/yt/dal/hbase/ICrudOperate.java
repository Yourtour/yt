package com.yt.dal.hbase;

import java.util.List;

/**
 * hbase中对实体进行CRUD操作的接口定义。
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
	public void save(List<? extends BaseBean> beans) throws Exception;

	/**
	 * 逻辑删除指定的bean对象数据
	 * 
	 * @param bean
	 *            待删除的hbase实体数据对象
	 * @throws Exception
	 *             删除数据过程中发生的异常
	 */
	public void deleteRow(BaseBean bean) throws Exception;

	/**
	 * 逻辑删除指定的bean对象列表数据
	 * 
	 * @param beans
	 *            待删除的hbase实体数据对象列表
	 * @throws Exception
	 *             删除数据过程中发生的异常
	 */
	public void deleteRows(List<? extends BaseBean> beans) throws Exception;

	/**
	 * 获取指定表和行键的bean数据对象，仅仅返回最新版本数据。
	 * 
	 * @param class
	 *            hbase实体类
	 * @param rowKey
	 *            行键
	 * @return bean数据对象，如果指定对象不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public BaseBean get(Class<? extends BaseBean> clazz, String rowKey) throws Exception;

	/**
	 * 获取指定hbase实体类的所有bean数据对象
	 * 
	 * @param clazz
	 *            hbase实体类
	 * @return bean数据对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<? extends BaseBean> get(Class<? extends BaseBean> clazz)
			throws Exception;

	/**
	 * 获取指定表中介于指定时间戳之间（大于等于ts1且小于等于ts2）的实体数据对象
	 * 
	 * @param clazz
	 *            hbase实体类
	 * @param ts1
	 *            开始时间戳
	 * @param ts2
	 *            结束时间戳
	 * @return bean数据对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<? extends BaseBean> get(Class<? extends BaseBean> clazz,
			long ts1, long ts2) throws Exception;

}

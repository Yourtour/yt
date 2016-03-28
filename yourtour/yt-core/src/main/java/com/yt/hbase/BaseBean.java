package com.yt.hbase;

/**
 * hbase实体类的接口定义。
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
 * <td>2015年9月26日</td>
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
public interface BaseBean {

	/**
	 * 获取hbase实体对象的行键
	 * 
	 * @return hbase实体对象的行键
	 */
	public String getRowKey();

	/**
	 * 设置hbase实体对象的行键
	 * 
	 * @param rowKey
	 *            hbase实体对象的行键
	 */
	public void setRowKey(String rowKey);

	/**
	 * 获取创建人员ID
	 * 
	 * @return 创建人员ID
	 */
	public Long getCreatedUserId();

	/**
	 * 设置创建人员ID
	 * 
	 * @param createdUserId
	 *            创建人员ID
	 */
	public void setCreatedUserId(Long createdUserId);

	/**
	 * 获取更新人员ID
	 * 
	 * @return 更新人员ID
	 */
	public long getCreatedTime();

	/**
	 * 设置更新人员ID
	 * 
	 * @param updatedUserId
	 *            更新人员ID
	 */
	public void setCreatedTime(long createdTime);

	/**
	 * 获取更新人员ID
	 * 
	 * @return 更新人员ID
	 */
	public Long getUpdatedUserId();

	/**
	 * 设置更新人员ID
	 * 
	 * @param updatedUserId
	 *            更新人员ID
	 */
	public void setUpdatedUserId(Long updatedUserId);

	/**
	 * 获取更新时间
	 * 
	 * @return 更新时间
	 */
	public long getUpdatedTime();

	/**
	 * 设置更新时间
	 * 
	 * @param updatedTime
	 *            更新时间
	 */
	public void setUpdatedTime(long updatedTime);

}
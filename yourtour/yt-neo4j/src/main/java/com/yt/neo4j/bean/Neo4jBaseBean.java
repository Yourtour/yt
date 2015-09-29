package com.yt.neo4j.bean;

/**
 * Neo4J中定义实体的接口定义
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
 * <td>2015年９月2８日</td>
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
public interface Neo4jBaseBean {

	/**
	 * 获取Neo4J实体在图中的唯一ID，该ID被Neo4J在内部自动维护。
	 * 
	 * @return 实体对应的ID，如果ID为null，则表示该实体没有被Attach。
	 */
	public Long getGraphId();

	/**
	 * 设置Neo4J实体在图中的唯一ID，只能由内部进行设置，外部只能获取。
	 * 
	 * @param id
	 *            设置的ID
	 */
	public void setGraphId(Long id);

	/**
	 * 获取创建人员ID
	 * 
	 * @return 创建人员ID
	 */
	public String getCreatedUserId();

	/**
	 * 设置创建人员ID
	 * 
	 * @param createdUserId
	 *            创建人员ID
	 */
	public void setCreatedUserId(String createdUserId);

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
	public String getUpdatedUserId();

	/**
	 * 设置更新人员ID
	 * 
	 * @param updatedUserId
	 *            更新人员ID
	 */
	public void setUpdatedUserId(String updatedUserId);

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
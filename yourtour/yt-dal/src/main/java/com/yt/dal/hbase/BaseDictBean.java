package com.yt.dal.hbase;


/**
 * hbase中字典类实体的抽象类，定义了常规字典类实体必须包含的数据内容。
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
 * <td>2015年5月28日</td>
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
public abstract class BaseDictBean extends BaseBean {
	private static final long serialVersionUID = 9059455163441467114L;
				
	/**
	 * 默认构造方法
	 */
	public BaseDictBean() {
		super();
	}

	/**
	 * 获取代码
	 * @return 代码
	 */
	public abstract String getCode();
	
	/**
	 * 设置代码
	 * @param code 代码
	 */
	public abstract void setCode(String code);
	
	/**
	 * 获取名称
	 * @return 名称
	 */
	public abstract String getName();
	
	/**
	 * 设置名称
	 * @param name 名称
	 */
	public abstract void setName(String name);
	
	/**
	 * 获取状态
	 * @return 状态
	 */
	public abstract Enum<?> getStatus();
	
	/**
	 * 设置状态，状态是一个枚举量
	 * @param status 状态
	 */
	public abstract void setStatus(Enum<?> status);
	
	/**
	 * 获取创建人员ID
	 * @return 创建人员ID
	 */
	public abstract String getCreatedUserId();
	
	/**
	 * 设置创建人员ID
	 * @param createdUserId 创建人员ID
	 */
	public abstract void setCreatedUserId(String createdUserId);
	
	/**
	 * 获取更新人员ID
	 * @return 更新人员ID
	 */
	public abstract String getUpdatedUserId();
	
	/**
	 * 设置更新人员ID
	 * @param updatedUserId 更新人员ID
	 */
	public abstract void setUpdatedUserId(String updatedUserId);
	
	/**
	 * 获取更新时间
	 * @return 更新时间
	 */
	public abstract long getUpdatedTime();
	
	/**
	 * 设置更新时间
	 * @param updatedTime 更新时间
	 */
	public abstract void setUpdatedTime(long updatedTime);

}

package com.yt.dal.hbase.cache;

import com.yt.dal.hbase.BaseBean;

/**
 * 定义hbase表描述对象的缓存接口。<br>
 * <b>注意：</b>缓存中返回的对象均是clone对象，因此后续所有修改将不会影响到缓存中的数据。如果需要修改缓存中的数据，必须使用put方法。
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
public interface IBeanDescriptorCache {
	/**
	 * 设置并返回hbase实体类的表描述对象，用于缓存。
	 * 
	 * @param clazz
	 *            hbase实体对象类
	 * @return hbase表描述对象，如果设置不成功，则返回null。
	 * @see #get(Class)
	 */
	public BeanDescriptor put(Class<? extends BaseBean> clazz);

	/**
	 * 获取指定hbase实体类的表描述对象
	 * 
	 * @param clazz
	 *            hbase实体对象类
	 * @return hbase表描述对象，如果不存在，则返回null。
	 * @see #get(String)
	 */
	public BeanDescriptor get(Class<? extends BaseBean> clazz);

	/**
	 * 获取指定hbase实体对象名的表描述对象
	 * 
	 * @param beanClassName
	 *            hbase实体对象名称
	 * @return hbase表描述对象，如果不存在，则返回null。
	 */
	public BeanDescriptor get(String beanClassName);
}

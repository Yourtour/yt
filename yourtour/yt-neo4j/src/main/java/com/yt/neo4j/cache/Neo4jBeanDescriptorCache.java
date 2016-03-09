package com.yt.neo4j.cache;

import java.util.List;

import com.yt.neo4j.bean.Neo4jBaseBean;

/**
 * 定义neo4j表描述对象的缓存接口。<br>
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
 * <td>2015年10月19日</td>
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
public interface Neo4jBeanDescriptorCache<T extends Neo4jBaseBean> {
	/**
	 * 设置并返回neo4j实体类的表描述对象，用于缓存。
	 * 
	 * @param clazz
	 *            neo4j实体对象类
	 * @return neo4j表描述对象，如果设置不成功，则返回null。
	 * @see #get(Class)
	 */
	public Neo4jBeanDescriptor put(Class<T> clazz);

	/**
	 * 获取指定neo4j实体类的表描述对象
	 * 
	 * @param clazz
	 *            neo4j实体对象类
	 * @return neo4j表描述对象，如果不存在，则返回null。
	 * @see #get(String)
	 */
	public Neo4jBeanDescriptor get(Class<T> clazz);

	/**
	 * 获取指定neo4j实体对象名的表描述对象
	 * 
	 * @param beanClassName
	 *            neo4j实体对象名称
	 * @return neo4j表描述对象，如果不存在，则返回null。
	 */
	public Neo4jBeanDescriptor get(String beanClassName);

	/**
	 * 获取缓存中所有的neo4j实体对象
	 * 
	 * @return neo4j实体对象列表
	 */
	public List<Neo4jBeanDescriptor> get();
}

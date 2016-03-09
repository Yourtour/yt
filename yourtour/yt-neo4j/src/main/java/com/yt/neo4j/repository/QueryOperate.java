/** 
 * @(#)ICrudOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.neo4j.repository;

import com.yt.neo4j.bean.Neo4jBaseBean;

import java.util.List;

/**
 * 定义了针对Neo4J的CRUD操作的接口。
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
 * <td>2015年6月21日</td>
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
public interface QueryOperate<T extends Neo4jBaseBean> {

	/**
	 * 获取指定Neo4J实体个数
	 * 
	 * @param clazz
	 *            Neo4J实体类
	 * @return 实体个数
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public long count() throws Exception;

	/**
	 * 获取指定图ID的Neo4J实体对象
	 * 
	 * @param graphId
	 *            图节点ID
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public T get(Long graphId) throws Exception;

	/**
	 * 获取指定图ID的Neo4J实体对象
	 * 
	 * @param graphId
	 *            图节点ID
	 * @param loadRelations
	 *            是否加载关系
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public T get(Long graphId, boolean loadRelations) throws Exception;

	/**
	 * 根据指定的索引字段获取指定内容的Neo4J实体对象
	 * 
	 * @param indexedPropertyName
	 *            索引字段名称
	 * @param value
	 *            指定的搜索内容
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public T get(String indexedPropertyName, String value) throws Exception;

	/**
	 * 根据指定的索引字段获取指定内容的Neo4J实体对象
	 * 
	 * @param indexedPropertyName
	 *            索引字段名称
	 * @param value
	 *            指定的搜索内容
	 * @param loadRelations
	 *            是否加载关系
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public T get(String indexedPropertyName, String value, boolean loadRelations) throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象
	 * 
	 * @return Neo4J实体对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<T> get() throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象
	 * 
	 * @param loadRelations
	 *            是否加载关系
	 * @return Neo4J实体对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<T> get(boolean loadRelations) throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象，按页方式。
	 * 
	 * @param start
	 *            起始页数
	 * @param limit
	 *            每页记录数
	 * @return Neo4J实体对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<T> getByPage(int start, int limit)	throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象，按页方式。
	 * 
	 * @param start
	 *            起始页数
	 * @param limit
	 *            每页记录数
	 * @param loadRelations
	 *            是否加载关系
	 * @return Neo4J实体对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<T> getByPage(int start, int limit, boolean loadRelations) throws Exception;

	/**
	 * 删除指定的Neo4J实体对象
	 * 
	 * @param bean
	 *            指定的Neo4J实体对象
	 * @throws Exception
	 *             删除过程中发生的异常
	 */
	public void delete(T bean) throws Exception;

	/**
	 * 删除所有对应的Neo4J实体对象。
	 * 
	 * @throws Exception
	 *             删除过程中发生的异常
	 */
	public void delete() throws Exception;

	/**
	 * 保存一个指定的Neo4J实体对象
	 * 
	 * @param neo4jBean
	 *            Neo4J实体对象
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public void save(T neo4jBean) throws Exception;

	/**
	 * 保存一个指定的Neo4J实体对象
	 *
	 * @param neo4jBean
	 *            Neo4J实体对象
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public void save(T neo4jBean, boolean saveRelation) throws Exception;

	/**
	 * 仅仅保存指定的Neo4J实体对象中的所有关系，本方法不会保存该实体对象中的其他属性。
	 * 
	 * @param neo4jBean
	 *            Neo4J实体对象
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public void saveRelationsOnly(T neo4jBean) throws Exception;
}

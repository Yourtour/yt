/** 
 * @(#)ICrudOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.neo4j.repository;

import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Direction;

import com.yt.neo4j.bean.Neo4jBaseBean;

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
public interface CrudOperate {

	/**
	 * 获取指定Neo4J实体个数
	 * 
	 * @param clazz
	 *            Neo4J实体类
	 * @return 实体个数
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public long count(Class<? extends Neo4jBaseBean> clazz) throws Exception;

	/**
	 * 获取指定图ID的Neo4J实体对象
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @param graphId
	 *            图节点ID
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz, Long graphId)
			throws Exception;

	/**
	 * 获取指定图ID的Neo4J实体对象
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @param graphId
	 *            图节点ID
	 * @param loadRelations
	 *            是否加载关系
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz,
			Long graphId, boolean loadRelations) throws Exception;

	/**
	 * 根据指定的索引字段获取指定内容的Neo4J实体对象
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @param indexedPropertyName
	 *            索引字段名称
	 * @param value
	 *            指定的搜索内容
	 * @return Neo4J实体对象，如果不存在，则返回null。
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz,
			String indexedPropertyName, String value) throws Exception;

	/**
	 * 根据指定的索引字段获取指定内容的Neo4J实体对象
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
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
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz,
			String indexedPropertyName, String value, boolean loadRelations)
			throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @return Neo4J实体对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<? extends Neo4jBaseBean> get(
			Class<? extends Neo4jBaseBean> clazz) throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @param loadRelations
	 *            是否加载关系
	 * @return Neo4J实体对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<? extends Neo4jBaseBean> get(
			Class<? extends Neo4jBaseBean> clazz, boolean loadRelations)
			throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象，按页方式。
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @param start
	 *            起始页数
	 * @param limit
	 *            每页记录数
	 * @return Neo4J实体对象列表
	 * @throws Exception
	 *             获取过程中发生的异常
	 */
	public List<? extends Neo4jBaseBean> getByPage(
			Class<? extends Neo4jBaseBean> clazz, int start, int limit)
			throws Exception;

	/**
	 * 获取对应实体类的Neo4J实体对象，按页方式。
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
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
	public List<? extends Neo4jBaseBean> getByPage(
			Class<? extends Neo4jBaseBean> clazz, int start, int limit,
			boolean loadRelations) throws Exception;

	/**
	 * 删除指定的Neo4J实体对象
	 * 
	 * @param bean
	 *            指定的Neo4J实体对象
	 * @throws Exception
	 *             删除过程中发生的异常
	 */
	public void delete(Neo4jBaseBean bean) throws Exception;

	/**
	 * 删除所有对应的Neo4J实体对象。
	 * 
	 * @param clazz
	 *            对应的Neo4J实体类
	 * @throws Exception
	 *             删除过程中发生的异常
	 */
	public void delete(Class<? extends Neo4jBaseBean> clazz) throws Exception;

	/**
	 * 保存一个指定的Neo4J实体对象
	 * 
	 * @param neo4jBean
	 *            Neo4J实体对象
	 * @param operator
	 *            当前操作者
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public void save(Neo4jBaseBean neo4jBean, String operator) throws Exception;

	/**
	 * 仅仅保存指定的Neo4J实体对象中的所有关系，本方法不会保存该实体对象中的其他属性。
	 * 
	 * @param neo4jBean
	 *            Neo4J实体对象
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public void saveRelationsOnly(Neo4jBaseBean neo4jBean) throws Exception;

	/**
	 * 仅仅保存指定的Neo4J实体对象中的指定的关系，本方法不会保存该实体对象中的其他属性。
	 * 
	 * @param neo4jBean
	 *            Neo4j实体对象
	 * @param relationshipFieldNames
	 *            指定要保存的关系字段列表
	 * @throws Exception
	 *             保存过程中发生的异常
	 */
	public void saveRelationsOnly(Neo4jBaseBean neo4jBean,
			String[] relationshipFieldNames) throws Exception;

	/**
	 * 仅仅保存最简单的Neo4J实体对象之间的关系，本方法建立的关系不包括属性。<br>
	 * 如果该关系原来存在，则不会重新建立该关系。
	 * 
	 * @param src
	 *            关系的源节点
	 * @param tar
	 *            关系的目标节点
	 * @param relationship
	 *            关系
	 * @param direction
	 *            关系的方向
	 * @throws Exception
	 *             建立关系过程中发生的异常
	 */
	public void createRelation(Neo4jBaseBean src, Neo4jBaseBean tar,
			String relationship, Direction direction) throws Exception;

	/**
	 * 保存Neo4J实体对象之间的关系，本方法建立的关系包括属性。<br>
	 * 如果该关系原来存在，则删除该关系后重新建立该关系。
	 * 
	 * @param src
	 *            关系的源节点
	 * @param tar
	 *            关系的目标节点
	 * @param relationship
	 *            关系
	 * @param direction
	 *            关系的方向
	 * @param propertes
	 *            关系的附加属性
	 * @throws Exception
	 *             建立关系过程中发生的异常
	 */
	public void createRelation(Neo4jBaseBean src, Neo4jBaseBean tar,
			String relationship, Direction direction,
			Map<String, Object> propertes) throws Exception;

	/**
	 * 删除指定两个节点之间指定名称的关系，包括关系的属性。
	 * 
	 * @param src
	 *            关系开始节点对象
	 * @param tar
	 *            关系结束节点对象
	 * @param relationship
	 *            关系名称
	 * @throws Exception
	 *             删除关系过程中发生的异常
	 */
	public void deleteRelation(Neo4jBaseBean src, Neo4jBaseBean tar,
			String relationship) throws Exception;

}

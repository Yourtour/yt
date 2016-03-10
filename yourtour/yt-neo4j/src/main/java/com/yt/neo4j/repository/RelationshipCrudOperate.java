/** 
 * @(#)ICrudOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.neo4j.repository;

import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.springframework.stereotype.Repository;

import com.yt.neo4j.Neo4jBaseBean;

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
@Repository
public interface RelationshipCrudOperate<S extends Neo4jBaseBean, T extends Neo4jBaseBean> {
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
	public void createRelation(S src, T tar,  String relationship, Direction direction) throws Exception;

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
	public void createRelation(S src, T tar,  String relationship, Direction direction,  Map<String, Object> propertes) throws Exception;
	
	/**
	 * 保存Neo4J实体对象之间的关系，本方法建立的关系包括属性。<br>
	 * 如果该关系原来存在，则删除该关系后重新建立该关系。
	 * 
	 * @param src
	 *            关系的源节点
	 * @param tar
	 *            关系的目标节点
	 * @param relationshipType
	 *            关系
	 * @throws Exception
	 *             建立关系过程中发生的异常
	 */
	public Map<String, Object> getRelation(S src, T tar,  String relationshipType) throws Exception;

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
	public void deleteRelation(S src, T tar,  String relationship) throws Exception;
}

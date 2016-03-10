/** 
 * @(#)CrudGeneralOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.neo4j.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.yt.neo4j.Neo4jBaseBean;
import com.yt.neo4j.cache.Neo4jBeanDescriptorCache;

/**
 * 面向Neo4j实体对象之间的关系进行常规CRUD操作的操作类。
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
/**
 * @author john
 *
 */

/**
 * @author john
 * 
 */
public class RelationshipCrudGeneralOperate<S extends Neo4jBaseBean,T extends Neo4jBaseBean> implements RelationshipCrudOperate<S, T>{
	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory.getLog(RelationshipCrudGeneralOperate.class);

	@Autowired
	protected Neo4jTemplate template;

	@Autowired
	protected Neo4jBeanDescriptorCache cache;

	/**
	 * 默认构造方法
	 */
	public RelationshipCrudGeneralOperate() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#createRelation(com.yt.neo4j.bean.
	 * Neo4jBaseBean, com.yt.neo4j.bean.Neo4jBaseBean, java.lang.String,
	 * org.neo4j.graphdb.Direction)
	 */
	public void createRelation(S src, T tar, String relationship, Direction direction) throws Exception {
		this.doCreateRelation(src, tar, relationship, direction);
	}

	/**
	 * 构建两个实体之间的关系
	 * @param src
	 * @param tar
	 * @param relationship
	 * @param direction
	 * @throws Exception
	 */
	private void doCreateRelation(Neo4jBaseBean src, Neo4jBaseBean tar, String relationship, Direction direction) throws Exception {
		// 注意：CREATE语句创建关系只能有一个方向，因此如果direction = Direction.BOTH，那么需要执行两次语句
		if (direction == Direction.BOTH || direction == Direction.OUTGOING) {
			// 创建outgoing方向的关系
			Relationship r = template.getRelationshipBetween(src, tar,
					relationship);
			if (r == null) {
				// 只有该关系不存在，则建立新的关系。
				String createRelationQuery = String
						.format("START src=node(%d), tar=node(%d) CREATE src-[:%s]->tar",
								src.getId(), tar.getId(),
								relationship);
				template.query(createRelationQuery, null);
			}
		}

		if (direction == Direction.BOTH || direction == Direction.INCOMING) {
			// 创建incoming方向的关系
			Relationship r = template.getRelationshipBetween(tar, src,
					relationship);
			if (r == null) {
				String createRelationQuery = String
						.format("START src=node(%d), tar=node(%d) CREATE src<-[:%s]-tar",
								src.getId(), tar.getId(),
								relationship);
				template.query(createRelationQuery, null);
			}
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create the relationship success: %s(%d)%s-[r:%s]-%s%s(%d)",
							src.getClass().getSimpleName(), src.getId(),
							direction == Direction.BOTH
									|| direction == Direction.INCOMING ? "<"
									: "", relationship,
							direction == Direction.BOTH
									|| direction == Direction.OUTGOING ? ">"
									: "", tar.getClass().getSimpleName(), tar
									.getId()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#createRelation(com.yt.neo4j.bean.
	 * Neo4jBaseBean, com.yt.neo4j.bean.Neo4jBaseBean, java.lang.String,
	 * org.neo4j.graphdb.Direction, java.util.Map)
	 */
	public void createRelation(S src, T tar,String relationship, Direction direction,
			Map<String, Object> properties) throws Exception {
		if (src == null || tar == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Any Neo4jBaseBean is null, can not create the relationship.");
			}
			return;
		}
		// 取出原始的节点
		Node oriSrc = template.getNode(src.getId());
		Node oriTar = template.getNode(tar.getId());
		if (oriSrc == null || oriTar == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Any Node is null, can not create the relationship.");
			}
			return;
		}
		// 删除原有关系
		this.deleteRelation(src, tar, relationship);

		// 建立新的关系
		if (direction == Direction.OUTGOING || direction == Direction.BOTH) {
			template.createRelationshipBetween(oriSrc, oriTar, relationship,
					properties);
		}
		if (direction == Direction.INCOMING || direction == Direction.BOTH) {
			template.createRelationshipBetween(oriTar, oriSrc, relationship,
					properties);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String
					.format("Create the relationship success: %s(%d)%s-[r:%s]-%s%s(%d)",
							src.getClass().getSimpleName(), src.getId(),
							direction == Direction.BOTH
									|| direction == Direction.INCOMING ? "<"
									: "", relationship,
							direction == Direction.BOTH
									|| direction == Direction.OUTGOING ? ">"
									: "", tar.getClass().getSimpleName(), tar
									.getId()));
		}
	}

	/*
         * (non-Javadoc)
         *
         * @see
         * com.yt.neo4j.repository.CrudOperate#deleteRelation(com.yt.neo4j.bean.
         * Neo4jBaseBean, com.yt.neo4j.bean.Neo4jBaseBean, java.lang.String)
         */
	@Override
	public void deleteRelation(S src, T tar,
			String relationship) throws Exception {
		if (src == null || tar == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Any Neo4jBaseBean is null, can not delete the relationship.");
			}
			return;
		}
		template.deleteRelationshipBetween(src, tar, relationship);
		// TODO 需要验证一下，删除时是否有方向性，如果没有，则可以删除掉一句语句。
		template.deleteRelationshipBetween(tar, src, relationship);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Delete relationship success: %s(%d)-[r:%s]-%s(%d).", src
							.getClass().getSimpleName(), src.getId(),
					relationship, tar.getClass().getSimpleName(), tar
							.getId()));
		}
	}

	@Override
	public Map<String, Object> getRelation(S src,
			T tar, String relationshipType) throws Exception {
		Relationship relationship = this.template.getRelationshipBetween(src, tar, relationshipType);
		if(relationship != null){
			Map<String, Object> props = new HashMap<>();
			
			Iterable<String> keys = relationship.getPropertyKeys();
			if(keys != null){
				Iterator<String> itKeys = keys.iterator();
				String key = null;
				while(itKeys.hasNext()){
					key = itKeys.next().toString();
					props.put(key, relationship.getProperty(key));
				}
			}
			
			return props;
		}
		return null;
	}
}

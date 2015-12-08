/** 
 * @(#)CrudGeneralOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.neo4j.repository;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.yt.neo4j.bean.Neo4jBaseBean;
import com.yt.neo4j.bean.Neo4jBaseDictBean;
import com.yt.neo4j.cache.Neo4jBeanDescriptor;
import com.yt.neo4j.cache.Neo4jBeanDescriptor.RelationDescriptor;
import com.yt.neo4j.cache.Neo4jBeanDescriptorCache;

/**
 * 面向Neo4j实体对象进行常规CRUD操作的操作类。
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
public class CrudGeneralOperate implements CrudOperate {
	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory.getLog(CrudGeneralOperate.class);

	@Autowired
	protected Neo4jTemplate template;

	@Autowired
	protected Neo4jBeanDescriptorCache cache;

	/**
	 * 默认构造方法
	 */
	public CrudGeneralOperate() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#count(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long count(Class<? extends Neo4jBaseBean> clazz) throws Exception {
		return template.count((Class<Neo4jBaseBean>) clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class,
	 * java.lang.Long)
	 */
	@Override
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz, Long graphId)
			throws Exception {
		return get(clazz, graphId, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class,
	 * java.lang.Long, boolean)
	 */
	@Override
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz,
			Long graphId, boolean loadRelations) throws Exception {
		Neo4jBaseBean bean = template.findOne(graphId, clazz);
		if (bean != null && loadRelations) {
			bean = loadRelations(bean);
		}
		return bean;
	}

	// 加载关系数据
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Neo4jBaseBean loadRelations(Neo4jBaseBean bean) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Load the relations node for %s[%d].", bean
					.getClass().getSimpleName(), bean.getGraphId()));
		}
		Neo4jBeanDescriptor nbd = cache.get(bean.getClass());
		if (nbd != null) {
			for (RelationDescriptor rd : nbd.getRelations().values()) {
				String relationClause = "";
				if (rd.getDirection() == Direction.INCOMING) {
					// incoming
					relationClause = String.format("<-[:%s]-",
							rd.getRelationship());
				} else if (rd.getDirection() == Direction.OUTGOING) {
					// outgoing
					relationClause = String.format("-[:%s]->",
							rd.getRelationship());
				} else {
					// incoming & outgoing = both
					relationClause = String.format("<-[:%s]->",
							rd.getRelationship());
				}
				String query = String.format(
						"START n=node(%d) MATCH n%s(m:%s) RETURN DISTINCT m",
						bean.getGraphId(), relationClause, rd.getClazz()
								.getSimpleName());
				Result<Map<String, Object>> result = template
						.query(query, null);
				Object value = null;
				if (rd.isSet()) {
					// 是集合数据，封装到List对象中
					List list = new Vector();
					for (Map<String, Object> row : result) {
						Object tar = template.convert(row, rd.getClazz());
						list.add(tar);
					}
					// 按照ID或者Code或者RowKey排序
					Collections.sort(list, null);
					value = list;
				} else {
					// 不是集合数据，返回第一条数据
					for (Map<String, Object> row : result) {
						Object tar = template.convert(row, rd.getClazz());
						value = tar;
						break;
					}
				}
				// 将数据设置到bean中
				Field field = rd.getField();
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				field.set(bean, value);
			}
		} else {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The %s has not any relations.", bean
						.getClass().getSimpleName()));
			}
		}
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz,
			String indexedPropertyName, String value) throws Exception {
		return get(clazz, indexedPropertyName, value, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class,
	 * java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public Neo4jBaseBean get(Class<? extends Neo4jBaseBean> clazz,
			String indexedPropertyName, String value, boolean loadRelations)
			throws Exception {
		Result<Neo4jBaseBean> result = template.findByIndexedValue(clazz,
				indexedPropertyName, value);
		Neo4jBaseBean bean = result.singleOrNull();
		if (bean == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("The Neo4jBean[%s='%s'] not exist.",
						indexedPropertyName, value));
			}
		} else {
			if (loadRelations) {
				bean = loadRelations(bean);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Found a Neo4jBean, %s: %s, graphId: %d.",
						indexedPropertyName, value,
						((Neo4jBaseBean) bean).getGraphId()));
			}
		}
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class)
	 */
	@Override
	public List<? extends Neo4jBaseBean> get(
			Class<? extends Neo4jBaseBean> clazz) throws Exception {
		return get(clazz, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class, boolean)
	 */
	@Override
	public List<? extends Neo4jBaseBean> get(
			Class<? extends Neo4jBaseBean> clazz, boolean loadRelations)
			throws Exception {
		@SuppressWarnings("unchecked")
		Result<Neo4jBaseBean> result = template
				.findAll((Class<Neo4jBaseBean>) clazz);
		List<Neo4jBaseBean> list = new Vector<Neo4jBaseBean>();
		for (Neo4jBaseBean bean : result) {
			if (bean != null && loadRelations) {
				bean = loadRelations(bean);
			}
			list.add(bean);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Fetch Neo4j[class='%s'] success, total: %d.",
					clazz.getSimpleName(), list.size()));
		}
		Collections.sort(list, null);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#getByPage(java.lang.Class, int,
	 * int)
	 */
	@Override
	public List<? extends Neo4jBaseBean> getByPage(
			Class<? extends Neo4jBaseBean> clazz, int start, int limit)
			throws Exception {
		return getByPage(clazz, start, limit, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#getByPage(java.lang.Class, int,
	 * int, boolean)
	 */
	@Override
	public List<? extends Neo4jBaseBean> getByPage(
			Class<? extends Neo4jBaseBean> clazz, int start, int limit,
			boolean loadRelations) throws Exception {
		String query = String.format("MATCH (n:%s) RETURN n SKIP %d LIMIT %d",
				clazz.getSimpleName(), start, limit);
		Result<Map<String, Object>> result = template.query(query, null);
		List<Neo4jBaseBean> list = new Vector<Neo4jBaseBean>();
		for (Map<String, Object> row : result) {
			Neo4jBaseBean bean = template.convert(row, clazz);
			bean = loadRelations(bean);
			list.add(bean);
		}
		Collections.sort(list, null);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#delete(com.yt.neo4j.bean.Neo4jBaseBean
	 * )
	 */
	@Override
	public void delete(Neo4jBaseBean bean) throws Exception {
		Long graphId = bean.getGraphId();
		if (graphId == null) {
			throw new Exception(
					"The Neo4J bean's id is null, the operate is ignored.");
		}
		Neo4jBaseBean d = this.get(bean.getClass(), graphId);
		if (d != null) {
			// 先删除存在的关系
			String deleteRelationQuery = String.format(
					"START n=node(%d) MATCH n-[r]-m DELETE r", d.getGraphId());
			template.query(deleteRelationQuery, null);
			// 再删除指定的节点
			template.delete(d);
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format(
						"Delete the Neo4j[class='%s', id='%d'] success.", bean
								.getClass().getSimpleName(), graphId));
			}
		} else {
			// 指定的节点不存在。
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format(
						"The Neo4j[class='%s', id='%d'] not exist.", bean
								.getClass().getSimpleName(), graphId));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#delete(java.lang.Class)
	 */
	@Override
	public void delete(Class<? extends Neo4jBaseBean> clazz) throws Exception {
		@SuppressWarnings("unchecked")
		Result<Neo4jBaseBean> result = template
				.findAll((Class<Neo4jBaseBean>) clazz);
		long count = 0;
		for (Neo4jBaseBean bean : result) {
			// 先删除该节点为起点的所有关系
			String deleteRelationQuery = String.format(
					"START n=node(%d) MATCH n-[r]-m DELETE r",
					bean.getGraphId());
			template.query(deleteRelationQuery, null);
			// 再删除指定的节点
			template.delete(bean);
			count++;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Delete the Neo4j[class='%s'] success, total: %d.",
					clazz.getSimpleName(), count));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#save(com.yt.neo4j.bean.Neo4jBaseBean,
	 * java.lang.String)
	 */
	@Override
	public void save(Neo4jBaseBean neo4jBean, String operator) throws Exception {
		if (neo4jBean == null) {
			String msg = "The Neo4jBean is null.";
			if (LOG.isWarnEnabled()) {
				LOG.warn(msg);
			}
			throw new NullPointerException("The Neo4jBean is null.");
		}
		Class<? extends Neo4jBaseBean> clazz = neo4jBean.getClass();
		// 判断该图节点是否存在
		Neo4jBaseBean bean = null;
		if (neo4jBean.getGraphId() != null) {
			// 以ID是否存在为最优先
			bean = get(clazz, neo4jBean.getGraphId());
		}
		if (bean == null && neo4jBean instanceof Neo4jBaseDictBean) {
			// 如果是字典类型的节点，则通过代码来判断该bean是否已经存在
			bean = get(clazz, "code", ((Neo4jBaseDictBean) neo4jBean).getCode());
		}
		if (bean == null) {
			// 该记录不存在，更新Create时间
			neo4jBean.setCreatedTime(System.currentTimeMillis());
			neo4jBean.setGraphId(null);
			neo4jBean.setCreatedUserId(operator);
		} else {
			// 该记录已经存在，更新Update时间
			neo4jBean.setGraphId(bean.getGraphId());
			neo4jBean.setUpdatedTime(System.currentTimeMillis());
			neo4jBean.setUpdatedUserId(operator);
		}

		// 先保存指定的节点
		Neo4jBaseBean tar = template.save(neo4jBean);
		// 再保存关系
		saveRelations(neo4jBean);
		if (LOG.isDebugEnabled()) {
			if (neo4jBean instanceof Neo4jBaseDictBean) {
				LOG.debug(String.format(
						"Save Neo4jBean(Dict) success, code: %s, graphId: %d.",
						((Neo4jBaseDictBean) tar).getCode(), tar.getGraphId()));
			} else {
				LOG.debug(String.format("Save Neo4jBean success, graphId: %d.",
						tar.getGraphId()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#saveRelationsOnly(com.yt.neo4j.bean
	 * .Neo4jBaseBean)
	 */
	@Override
	public void saveRelationsOnly(Neo4jBaseBean neo4jBean) throws Exception {
		if (neo4jBean == null) {
			String msg = "The Neo4jBean is null.";
			if (LOG.isWarnEnabled()) {
				LOG.warn(msg);
			}
			throw new NullPointerException("The Neo4jBean is null.");
		}
		saveRelations(neo4jBean);
	}

	// 保存关系数据
	private void saveRelations(Neo4jBaseBean bean) throws Exception {
		saveRelationsOnly(bean, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#saveRelationsOnly(com.yt.neo4j.bean
	 * .Neo4jBaseBean, java.lang.String[])
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void saveRelationsOnly(Neo4jBaseBean neo4jBean,
			String[] relationshipFieldNames) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Save the relations node for %s[%d].",
					neo4jBean.getClass().getSimpleName(),
					neo4jBean.getGraphId()));
		}
		// 取出现有的关系
		Neo4jBaseBean ori = this.get(neo4jBean.getClass(),
				neo4jBean.getGraphId());
		Neo4jBeanDescriptor nbd = cache.get(neo4jBean.getClass());
		if (nbd != null) {
			// 找出需要维护的关系字段列表
			if (relationshipFieldNames == null) {
				Set<String> keys = nbd.getRelations().keySet();
				relationshipFieldNames = keys.toArray(new String[0]);
			}
			for (String fieldName : relationshipFieldNames) {
				RelationDescriptor rd = nbd.getRelation(fieldName);
				Field field = rd.getField();
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				Object tar = field.get(neo4jBean);
				if (tar == null) {
					// 关系指定的字段内容为空，则不创建任何关系。
					continue;
				}
				if (tar instanceof List) {
					// 如果关系指定的字段内容为列表，则需要循环创建关系
					List tarList = (List) tar;
					List oriList = (List) field.get(ori);
					// 先做预处理，分离出需要新建和删除的关系，由于目前关系没有属性，因此不存在修改关系的内容
					List insert = new Vector();
					for (int i = tarList.size() - 1; i >= 0; i--) {
						Neo4jBaseBean tarBean = (Neo4jBaseBean) tarList.get(i);
						int found = -1;
						for (int j = oriList.size() - 1; j >= 0; j--) {
							Neo4jBaseBean oriBean = (Neo4jBaseBean) oriList
									.get(j);
							if (tarBean.getGraphId().longValue() == oriBean
									.getGraphId().longValue()) {
								found = j;
								break;
							}
						}
						if (found != -1) {
							// 关系已经存在，在目标和源列表中去除
							tarList.remove(i);
							oriList.remove(found);
						} else {
							// 关系不存在，在插入列表中添加
							insert.add(tarList.get(i));
						}
					}
					for (Object obj : insert) {
						// 建立新的关系
						createRelation(neo4jBean, (Neo4jBaseBean) obj,
								rd.getRelationship(), rd.getDirection());
					}
					for (Object obj : oriList) {
						// 删除需要删除的关系，源列表中剩余的就是需要删除的关系
						if (rd.getDirection() == Direction.INCOMING
								|| rd.getDirection() == Direction.BOTH) {
							template.deleteRelationshipBetween(obj, neo4jBean,
									rd.getRelationship());
						}
						if (rd.getDirection() == Direction.OUTGOING
								|| rd.getDirection() == Direction.BOTH) {
							template.deleteRelationshipBetween(neo4jBean, obj,
									rd.getRelationship());
						}
					}
				} else {
					Neo4jBaseBean oriBean = (Neo4jBaseBean) field.get(ori);
					if (oriBean != null) {
						if (oriBean.getGraphId().longValue() != ((Neo4jBaseBean) tar)
								.getGraphId().longValue()) {
							// 关系改变了，要先删除原来的关系
							if (rd.getDirection() == Direction.INCOMING
									|| rd.getDirection() == Direction.BOTH) {
								template.deleteRelationshipBetween(oriBean,
										neo4jBean, rd.getRelationship());
							}
							if (rd.getDirection() == Direction.OUTGOING
									|| rd.getDirection() == Direction.BOTH) {
								template.deleteRelationshipBetween(neo4jBean,
										oriBean, rd.getRelationship());
							}
							// 创建关系
							createRelation(neo4jBean, (Neo4jBaseBean) tar,
									rd.getRelationship(), rd.getDirection());
						}
					} else {
						// 否则只要创建一个关系即可。
						createRelation(neo4jBean, (Neo4jBaseBean) tar,
								rd.getRelationship(), rd.getDirection());
					}
				}
			}
		} else {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("The %s has not any relations.",
						neo4jBean.getClass().getSimpleName()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#createRelation(com.yt.neo4j.bean.
	 * Neo4jBaseBean, com.yt.neo4j.bean.Neo4jBaseBean, java.lang.String,
	 * org.neo4j.graphdb.Direction)
	 */
	public void createRelation(Neo4jBaseBean src, Neo4jBaseBean tar,
			String relationship, Direction direction) throws Exception {
		// 注意：CREATE语句创建关系只能有一个方向，因此如果direction = Direction.BOTH，那么需要执行两次语句
		if (direction == Direction.BOTH || direction == Direction.OUTGOING) {
			// 创建outgoing方向的关系
			Relationship r = template.getRelationshipBetween(src, tar,
					relationship);
			if (r == null) {
				// 只有该关系不存在，则建立新的关系。
				String createRelationQuery = String
						.format("START src=node(%d), tar=node(%d) CREATE src-[:%s]->tar",
								src.getGraphId(), tar.getGraphId(),
								relationship);
				template.query(createRelationQuery, null);
				if (LOG.isDebugEnabled()) {
					LOG.debug(String
							.format("Create relationship successed: %s(%d)-[:%s]->%s(%d).",
									src.getClass().getSimpleName(), src
											.getGraphId(), relationship, tar
											.getClass().getSimpleName(), tar
											.getGraphId()));
				}
			}
		}

		if (direction == Direction.BOTH || direction == Direction.INCOMING) {
			// 创建incoming方向的关系
			Relationship r = template.getRelationshipBetween(tar, src,
					relationship);
			if (r == null) {
				String createRelationQuery = String
						.format("START src=node(%d), tar=node(%d) CREATE src<-[:%s]-tar",
								src.getGraphId(), tar.getGraphId(),
								relationship);
				template.query(createRelationQuery, null);
				if (LOG.isDebugEnabled()) {
					LOG.debug(String
							.format("Create relationship successed: %s(%d)<-[:%s]-%s(%d).",
									src.getClass().getSimpleName(), src
											.getGraphId(), relationship, tar
											.getClass().getSimpleName(), tar
											.getGraphId()));
				}
			}
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
	public void createRelation(Neo4jBaseBean src, Neo4jBaseBean tar,
			String relationship, Direction direction,
			Map<String, Object> properties) throws Exception {
		if (src == null || tar == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Any Neo4jBaseBean is null, can not create the relationship.");
			}
			return;
		}
		// 取出原始的节点
		Node oriSrc = template.getNode(src.getGraphId());
		Node oriTar = template.getNode(tar.getGraphId());
		if (oriSrc == null || oriTar == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Any Node is null, can not create the relationship.");
			}
			return;
		}
		// 删除原有关系
		template.deleteRelationshipBetween(src, tar, relationship);
		template.deleteRelationshipBetween(tar, src, relationship);
		// 建立新的关系
		if (direction == Direction.OUTGOING || direction == Direction.BOTH) {
			template.createRelationshipBetween(oriSrc, oriTar, relationship,
					properties);
		}
		if (direction == Direction.INCOMING || direction == Direction.BOTH) {
			template.createRelationshipBetween(oriTar, oriSrc, relationship,
					properties);
		}
	}

}

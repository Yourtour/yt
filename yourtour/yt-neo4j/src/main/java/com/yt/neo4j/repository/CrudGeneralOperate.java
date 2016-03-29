/** 
 * @(#)CrudGeneralOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.neo4j.repository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.yt.neo4j.Neo4jBaseBean;
import com.yt.neo4j.Neo4jBaseDictBean;
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
public class CrudGeneralOperate<T extends Neo4jBaseBean> implements
		CrudOperate<T> {
	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory.getLog(CrudGeneralOperate.class);

	@Autowired
	protected Neo4jTemplate template;

	@SuppressWarnings("rawtypes")
	@Autowired
	protected Neo4jBeanDescriptorCache cache;

	/**
	 * 默认构造方法
	 */
	public CrudGeneralOperate() {
		super();
	}

	/**
	 * 获取泛型类型
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getClazz() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		return (Class<T>) params[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#count(java.lang.Class)
	 */
	@Override
	public long count() throws Exception {
		return template.count(getClazz());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class,
	 * java.lang.Long)
	 */
	@Override
	public T get(Long graphId) throws Exception {
		return get(graphId, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class,
	 * java.lang.Long, boolean)
	 */
	@Override
	public T get(Long graphId, boolean loadRelations) throws Exception {
		Class<T> clazz = this.getClazz();
		T bean = template.findOne(graphId, clazz);
		if (bean != null && loadRelations) {
			bean = loadRelations(bean);
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	public List<T> query(String queryStr, Map<String, Object> params)
			throws Exception {
		Class<T> clazz = this.getClazz();

		List<T> list = new ArrayList<>();
		Result<Map<String, Object>> result = template.query(queryStr, params);
		Object tar = null;
		for (Map<String, Object> row : result) {
			if (clazz.isAnnotationPresent(QueryResult.class)) {
				tar = clazz.newInstance();
				BeanWrapper bw = new BeanWrapperImpl(tar);
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					if (row.containsKey(field.getName())) {
						bw.setPropertyValue(field.getName(), template.convert(
								row.get(field.getName()), field.getType()));
					}
				}

			} else {
				tar = template.convert(row, clazz.getClass());
			}
			list.add((T) tar);
		}

		return list;
	}

	// 加载关系数据
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected T loadRelations(T bean) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Load the relations node for %s[%d].", bean
					.getClass().getSimpleName(), bean.getId()));
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
						bean.getId(), relationClause, rd.getClazz()
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
	public T get(String indexedPropertyName, String value) throws Exception {
		return get(indexedPropertyName, value, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class,
	 * java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public T get(String indexedPropertyName, String value, boolean loadRelations)
			throws Exception {
		Class<T> clazz = this.getClazz();

		Result<T> result = template.findByIndexedValue(clazz,
				indexedPropertyName, value);
		T bean = result.singleOrNull();
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
						indexedPropertyName, value, bean.getId()));
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
	public List<T> get() throws Exception {
		return get(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#get(java.lang.Class, boolean)
	 */
	@Override
	public List<T> get(boolean loadRelations) throws Exception {
		Class<T> clazz = this.getClazz();

		Result<T> result = template.findAll((Class<T>) clazz);
		List<T> list = new Vector<T>();
		for (T bean : result) {
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
	public List<T> getByPage(int start, int limit) throws Exception {
		return getByPage(start, limit, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.repository.CrudOperate#getByPage(java.lang.Class, int,
	 * int, boolean)
	 */
	@Override
	public List<T> getByPage(int start, int limit, boolean loadRelations)
			throws Exception {
		Class<T> clazz = this.getClazz();

		String query = String.format("MATCH (n:%s) RETURN n SKIP %d LIMIT %d",
				clazz.getSimpleName(), start, limit);
		Result<Map<String, Object>> result = template.query(query, null);
		List<T> list = new Vector<T>();
		for (Map<String, Object> row : result) {
			T bean = template.convert(row, clazz);
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
	public void delete(T bean) throws Exception {
		Long graphId = bean.getId();
		if (graphId == null) {
			throw new Exception(
					"The Neo4J bean's id is null, the operate is ignored.");
		}
		T d = this.get(graphId);
		if (d != null) {
			// 先删除存在的关系
			String deleteRelationQuery = String.format(
					"START n=node(%d) MATCH n-[r]-m DELETE r", d.getId());
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
	public void delete() throws Exception {
		Class<T> clazz = this.getClazz();
		Result<T> result = template.findAll(clazz);
		long count = 0;
		for (T bean : result) {
			// 先删除该节点为起点的所有关系
			String deleteRelationQuery = String.format(
					"START n=node(%d) MATCH n-[r]-m DELETE r", bean.getId());
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
	public void save(T neo4jBean) throws Exception {
		this.save(neo4jBean, true);
	}

	@Override
	public void save(T neo4jBean, boolean saveRelation) throws Exception {
		if (neo4jBean == null) {
			String msg = "The Neo4jBean is null.";
			if (LOG.isWarnEnabled()) {
				LOG.warn(msg);
			}
			throw new NullPointerException("The Neo4jBean is null.");
		}
		// 判断该图节点是否存在
		Neo4jBaseBean bean = null;
		Long id = neo4jBean.getId();
		if (id != null && id != -1l) {
			// 以ID是否存在为最优先
			bean = get(neo4jBean.getId());
		} else  {
			bean = null;
			neo4jBean.setId(null);
		}
		if (bean == null && neo4jBean instanceof Neo4jBaseDictBean) {
			// 如果是字典类型的节点，则通过代码来判断该bean是否已经存在
			bean = get("code", ((Neo4jBaseDictBean) neo4jBean).getCode());
		}
		if (bean != null) {
			neo4jBean.setId(bean.getId());
		}
		// 先保存指定的节点
		T tar = template.save(neo4jBean);

		if (saveRelation) {
			// 再保存关系
			saveRelations(neo4jBean);
		}

		if (LOG.isDebugEnabled()) {
			if (neo4jBean instanceof Neo4jBaseDictBean) {
				LOG.debug(String.format(
						"Save Neo4jBean(Dict) success, code: %s, graphId: %d.",
						((Neo4jBaseDictBean) tar).getCode(), tar.getId()));
			} else {
				LOG.debug(String.format("Save Neo4jBean success, graphId: %d.",
						tar.getId()));
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
	public void saveRelationsOnly(T neo4jBean) throws Exception {
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
	private void saveRelations(T bean) throws Exception {
		saveRelationsOnly(bean, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.neo4j.repository.CrudOperate#saveRelationsOnly(com.yt.neo4j.bean
	 * .Neo4jBaseBean, java.lang.String[])
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void saveRelationsOnly(T neo4jBean, String[] relationshipFieldNames)
			throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("Save the relations node for %s[%d].",
					neo4jBean.getClass().getSimpleName(), neo4jBean.getId()));
		}
		// 取出现有的关系
		T ori = this.get(neo4jBean.getId());
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
						T tarBean = (T) tarList.get(i);
						int found = -1;
						for (int j = oriList.size() - 1; j >= 0; j--) {
							T oriBean = (T) oriList.get(j);
							if (tarBean.getId().longValue() == oriBean.getId()
									.longValue()) {
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
						this.doCreateRelation(neo4jBean, (Neo4jBaseBean) obj,
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
					T oriBean = (T) field.get(ori);
					if (oriBean != null) {
						if (oriBean.getId().longValue() != ((T) tar).getId()
								.longValue()) {
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
							this.doCreateRelation(neo4jBean,
									(Neo4jBaseBean) tar, rd.getRelationship(),
									rd.getDirection());
						}
					} else {
						// 否则只要创建一个关系即可。
						this.doCreateRelation(neo4jBean, (Neo4jBaseBean) tar,
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

	/**
	 * 构建两个实体之间的关系
	 * 
	 * @param src
	 * @param tar
	 * @param relationship
	 * @param direction
	 * @throws Exception
	 */
	private void doCreateRelation(Neo4jBaseBean src, Neo4jBaseBean tar,
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
								src.getId(), tar.getId(), relationship);
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
								src.getId(), tar.getId(), relationship);
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
}

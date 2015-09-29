/** 
 * @(#)CrudGeneralOperate.java
 *
 * Copyright 2015, 迪爱斯通信设备有限公司保留.<br>
 *
 * @author John Peng
 */
package com.yt.neo4j.repository;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.yt.neo4j.bean.Neo4jBaseBean;
import com.yt.neo4j.bean.Neo4jBaseDictBean;

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
@Component
public class CrudGeneralOperate implements CrudOperate {
	/** 静态变量：系统日志 */
	private static final Log LOG = LogFactory.getLog(CrudGeneralOperate.class);

	@Autowired
	protected Neo4jTemplate template;

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
		Neo4jBaseBean bean = template.findOne(graphId, clazz);
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
		Result<Neo4jBaseBean> result = template.findByIndexedValue(clazz,
				indexedPropertyName, value);
		Neo4jBaseBean bean = result.singleOrNull();
		if (bean == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(String.format("The Neo4jBean[%s='%s'] not exist.",
						indexedPropertyName, value));
			}
		} else {
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
		@SuppressWarnings("unchecked")
		Result<Neo4jBaseBean> result = template
				.findAll((Class<Neo4jBaseBean>) clazz);
		List<Neo4jBaseBean> list = new Vector<Neo4jBaseBean>();
		for (Neo4jBaseBean bean : result) {
			list.add(bean);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Fetch Neo4j[class='%s'] success, total: %d.",
					clazz.getName(), list.size()));
		}
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
		template.delete(d);
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Delete the Neo4j[class='%s', id='%d'] success.", bean
							.getClass().getName(), graphId));
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
			template.delete(bean);
			count++;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"Delete the Neo4j[class='%s'] success, total: %d.",
					clazz.getName(), count));
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
	public void save(Neo4jBaseBean Neo4jBean, String operator) throws Exception {
		if (Neo4jBean == null) {
			String msg = "The Neo4jBean is null.";
			if (LOG.isWarnEnabled()) {
				LOG.warn(msg);
			}
			throw new NullPointerException("The Neo4jBean is null.");
		}
		Class<? extends Neo4jBaseBean> clazz = Neo4jBean.getClass();
		// 如果是字典类型的节点，则通过代码来判断该bean是否已经存在
		if (Neo4jBean instanceof Neo4jBaseDictBean) {
			Neo4jBaseBean bean = get(clazz, "code",
					((Neo4jBaseDictBean) Neo4jBean).getCode());
			if (bean == null) {
				// 该记录不存在，更新Create时间
				Neo4jBean.setCreatedTime(System.currentTimeMillis());
				Neo4jBean.setGraphId(null);
				Neo4jBean.setCreatedUserId(operator);
			} else {
				// 该记录已经存在，更新Update时间
				Neo4jBean.setGraphId(bean.getGraphId());
				Neo4jBean.setUpdatedTime(System.currentTimeMillis());
				Neo4jBean.setUpdatedUserId(operator);
			}
		}

		Neo4jBaseBean tar = template.save(Neo4jBean);
		if (LOG.isDebugEnabled()) {
			if (Neo4jBean instanceof Neo4jBaseDictBean) {
				LOG.debug(String.format(
						"Save Neo4jBean(Dict) success, code: %s, graphId: %d.",
						((Neo4jBaseDictBean) tar).getCode(), tar.getGraphId()));
			} else {
				LOG.debug(String.format("Save Neo4jBean success, graphId: %d.",
						tar.getGraphId()));
			}
		}
	}

}

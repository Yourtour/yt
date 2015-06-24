package com.yt.rsal.neo4j.bean;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.dal.hbase.IBaseBean;

/**
 * Neo4J中定义实体的基类，定义所有实体类中都至少包括的GraphId字段，并进行了注解。<br>
 * 同时由于BaseBean中定义的rowKey没有使用transient限定符，因此被默认定义为Neo4J的节点属性，并定义为唯一索引。
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
 * <td>2015年6月20日</td>
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
@NodeEntity
public class Neo4JBaseBean implements Serializable, IBaseBean, INeo4JBaseBean,
		Comparable<Neo4JBaseBean> {
	private static final long serialVersionUID = -1255893864343425738L;
	@GraphId
	private Long graphid;

	@GraphProperty
	@Indexed(unique = true)
	private String rowKey;
	
	/**
	 * 默认构造方法
	 */
	public Neo4JBaseBean() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.rsal.hbase.bean.INeo4jBaseBean#getGraphId()
	 */
	@Override
	public Long getGraphId() {
		return graphid;
	}

	/**
	 * 设置Neo4J实体在图中的唯一ID，只能由内部进行设置，外部只能获取。
	 * 
	 * @param id
	 *            设置的ID
	 */
	protected void setGraphId(Long id) {
		this.graphid = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IBaseBean#getRowKey()
	 */
	@Override
	public String getRowKey() {
		return rowKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.dal.hbase.IBaseBean#setRowKey(java.lang.String)
	 */
	@Override
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Neo4JBaseBean o) {
		if (o == null) {
			return 1;
		}
		String src = this.getRowKey();
		String tar = o.getRowKey();
		if (src == null && tar == null) {
			return 0;
		} else if (src != null) {
			return src.compareTo(tar);
		} else {
			return - tar.compareTo(src);
		}
	}

}
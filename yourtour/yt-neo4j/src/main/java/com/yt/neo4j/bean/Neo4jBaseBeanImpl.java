package com.yt.neo4j.bean;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

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
public class Neo4jBaseBeanImpl implements Serializable,
		Comparable<Neo4jBaseBeanImpl>, Neo4jBaseBean {
	private static final long serialVersionUID = -1255893864343425738L;
	
	@GraphId
	private Long graphid = null;

	private String createdUserId = "";

	private long createdTime;

	private String updatedUserId = "";

	private long updatedTime;

	/**
	 * 默认构造方法
	 */
	public Neo4jBaseBeanImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#getGraphId()
	 */
	@Override
	public Long getGraphId() {
		return graphid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#setGraphId(java.lang.Long)
	 */
	@Override
	public void setGraphId(Long id) {
		this.graphid = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#getCreatedUserId()
	 */
	@Override
	public String getCreatedUserId() {
		return createdUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#setCreatedUserId(java.lang.String)
	 */
	@Override
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#getCreatedTime()
	 */
	@Override
	public long getCreatedTime() {
		return createdTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#setCreatedTime(long)
	 */
	@Override
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#getUpdatedUserId()
	 */
	@Override
	public String getUpdatedUserId() {
		return updatedUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#setUpdatedUserId(java.lang.String)
	 */
	@Override
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#getUpdatedTime()
	 */
	@Override
	public long getUpdatedTime() {
		return updatedTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#setUpdatedTime(long)
	 */
	@Override
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Neo4jBaseBeanImpl o) {
		if (o == null) {
			return 1;
		}
		Long src = this.getGraphId();
		Long tar = o.getGraphId();
		if (src == null && tar == null) {
			return 0;
		} else if (src != null) {
			return src.compareTo(tar);
		} else {
			return -tar.compareTo(src);
		}
	}

}
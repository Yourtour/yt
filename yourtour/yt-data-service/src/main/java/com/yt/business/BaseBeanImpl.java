package com.yt.business;

import java.io.Serializable;
import java.util.UUID;

import com.yt.business.common.Constants;
import com.yt.core.utils.DateUtils;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.hbase.BaseBean;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.neo4j.bean.Neo4jBaseBean;

@NodeEntity
public class BaseBeanImpl implements Serializable, BaseBean, Neo4jBaseBean,
		Comparable<BaseBeanImpl> {
	private static final long serialVersionUID = -916424014919620404L;

	@Indexed(unique = true)
	private String rowKey = UUID.randomUUID().toString();

	@GraphId
	private Long graphId = null;

	@HbaseColumn(name = "cuid")
	private String createdUserId = "";

	@HbaseColumn(name = "ct")
	private long createdTime = 0l;

	@HbaseColumn(name = "uuid")
	private String updatedUserId = "";

	@HbaseColumn(name = "ut")
	private long updatedTime = 0l;

	@HbaseColumn(name = "stat")
	@Indexed
	private Constants.Status status = Constants.Status.VALIDATED;

	/**
	 * 默认的构造函数
	 */
	public BaseBeanImpl() {
		super();

		this.rowKey = UUID.randomUUID().toString();
	}

	/**
	 * 默认的构造函数
	 */
	public BaseBeanImpl(Long id) {
		super();

		this.graphId = id;

		this.rowKey = UUID.randomUUID().toString();
	}

	/**
	 * 默认的构造函数
	 */
	public BaseBeanImpl(String userId)
	{
		super();

		this.createdTime = DateUtils.getCurrentTimeMillis();
		this.updatedTime = DateUtils.getCurrentTimeMillis();

		this.createdUserId = userId;
		this.updatedUserId = userId;

		this.rowKey = UUID.randomUUID().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BaseBeanImpl o) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#getGraphId()
	 */
	@Override
	public Long getGraphId() {
		return graphId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.neo4j.bean.Neo4jBaseBean#setGraphId(java.lang.Long)
	 */
	@Override
	public void setGraphId(Long id) {
		this.graphId = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getRowKey()
	 */
	@Override
	public String getRowKey() {
		this.rowKey = UUID.randomUUID().toString();
		System.out.println(this.rowKey);
		return this.rowKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setRowKey(java.lang.String)
	 */
	@Override
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getCreatedUserId()
	 */
	@Override
	public String getCreatedUserId() {
		return createdUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setCreatedUserId(java.lang.String)
	 */
	@Override
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getCreatedTime()
	 */
	@Override
	public long getCreatedTime() {
		return createdTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setCreatedTime(long)
	 */
	@Override
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getUpdatedUserId()
	 */
	@Override
	public String getUpdatedUserId() {
		return updatedUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setUpdatedUserId(java.lang.String)
	 */
	@Override
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#getUpdatedTime()
	 */
	@Override
	public long getUpdatedTime() {
		return updatedTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.hbase.BaseBean#setUpdatedTime(long)
	 */
	@Override
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Constants.Status getStatus() {
		return status;
	}

	public void setStatus(Constants.Status status) {
		this.status = status;
	}

	public boolean isNew(){
		return this.graphId == null || this.graphId.longValue() == 0;
	}
}

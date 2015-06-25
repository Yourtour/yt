package com.yt.test.neo4j.bean;

import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;
import com.yt.test.neo4j.bean.Constants.Status;

/**
 * @author Tony.Zhang
 * 
 *         行程bean，定义了行程基本信息
 * 
 */
@HbaseTable(name = "T_ROUTE_INFO")
public class RouteBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -8980153602025087935L;
	private static final String INDEX_NAME = "route";

	private @HbaseColumn(name = "name")
	@GraphProperty
	@Indexed
	String name; // 安排名称
	private @HbaseColumn(name = "intr")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String intro; // 概述， 可以针对行程安排中具体某天或者某个景点进行描述
	private @HbaseColumn(name = "feat")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String feature; // 特点， 可以针对行程安排中具体某天或者某个景点进行特点描述
	private @HbaseColumn(name = "reas")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String reason; // 推荐理由，， 可以针对行程安排中具体某天或者某个景点进行推荐描述
	private @HbaseColumn(name = "plac")
	@GraphProperty
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String place; // 目的地
	private @HbaseColumn(name = "peri")
	@Indexed(numeric = true)
	long period; // 安排持续时间， 以秒为单位
	private @HbaseColumn(name = "stat")
	@GraphProperty
	Status status;

	private @HbaseColumn(name = "iu")
	transient String imageUrl; // 行程图片
	private @HbaseColumn(name = "st")
	transient long startTime; // 安排开始时间，以秒为单位
	private @HbaseColumn(name = "et")
	transient long endTime; // 安排结束时间，以秒为单位
	private @HbaseColumn(name = "cuid")
	transient String createdUserId = "";
	private @HbaseColumn(name = "ct")
	transient long createdTime;
	private @HbaseColumn(name = "uuid")
	transient String updatedUserId = "";
	private @HbaseColumn(name = "ut")
	transient long updatedTime;

	public RouteBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}

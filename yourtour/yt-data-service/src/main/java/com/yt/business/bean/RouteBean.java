package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseDictBeanImpl;
import com.yt.business.common.Constants.Status;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;

/**
 * 行程bean，定义了行程基本信息
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
 * <td>2015年6月2日</td>
 * <td>Tony.Zhang</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年6月27日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和neo4j的操作模式进行修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_ROUTE_INFO")
@NodeEntity
public class RouteBean extends BaseDictBeanImpl {
	private static final long serialVersionUID = -8980153602025087935L;
	private static final String INDEX_NAME = "route";

	@HbaseColumn(name = "iu")
	private String imageUrl; // 行程图片

	@HbaseColumn(name = "line")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String lineName; // 线路名称

	@HbaseColumn(name = "step")
	private int step; // 规划步骤

	@HbaseColumn(name = "intr")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String intro; // 概述， 可以针对行程安排中具体某天或者某个景点进行描述

	@HbaseColumn(name = "feat")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String feature; // 特点， 可以针对行程安排中具体某天或者某个景点进行特点描述

	@HbaseColumn(name = "reas")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String reason; // 推荐理由，， 可以针对行程安排中具体某天或者某个景点进行推荐描述

	@HbaseColumn(name = "plac")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String place; // 目的地

	@HbaseColumn(name = "st")
	private long startTime; // 安排开始时间，以秒为单位

	@HbaseColumn(name = "et")
	private long endTime; // 安排结束时间，以秒为单位

	@HbaseColumn(name = "peri")
	private long period; // 安排持续时间， 以秒为单位

	@HbaseColumn(name = "stat")
	@Indexed
	private Status status;

	public RouteBean() {
		super();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
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

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}

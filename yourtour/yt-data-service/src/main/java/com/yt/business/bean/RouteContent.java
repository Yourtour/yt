package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants.ScheduleType;
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
@HbaseTable(name = "T_ROUTE_CONTENT_INFO")
@NodeEntity
public class RouteContent extends BaseBeanImpl {
	private static final long serialVersionUID = -8980153602025087935L;
	
	private ScheduleType type;
	
	private String name;

	private String startTime;
	
	private String endTime;
	
	private double period;
	
	private String memo;
	
	private Integer seq;
	
	private ResourceBean resource;
	
	public RouteContent() {
		super();
	}
	
	public ScheduleType getType() {
		return type;
	}

	public void setType(ScheduleType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public ResourceBean getResource() {
		return resource;
	}

	public void setResource(ResourceBean resource) {
		this.resource = resource;
	}

	
}

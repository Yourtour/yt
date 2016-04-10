package com.yt.business.bean;

import java.util.List;

import com.yt.core.utils.StringUtils;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

@HbaseTable(name = "T_ROUTE_SCHEDULE")
@NodeEntity
public class RouteScheduleBean extends BaseBeanImpl implements Cloneable{
	private static final long serialVersionUID = 8074543232974381934L;
	private static final String INDEX_NAME = "routeSchedule";

	// 资源枚举
	public static enum ScheduleType {
		DAY("DAY","日程"),SCENE("SCENE", "游玩"), FOOD("FOOD", "餐饮"), HOTEL("HOTEL", "住宿"), TRAFFIC(
				"TRAFFIC", "交通"), FREE("FREE", "自行安排"), MATTER("MATTER", "事项");

		public String code;
		public String name;

		private ScheduleType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public static ScheduleType getType(String code){
			if(StringUtils.isNull(code)) return null;

			ScheduleType[] types = ScheduleType.values();
			for(ScheduleType type : types){
				if(type.code.equalsIgnoreCase(code)){
					return type;
				}
			}

			return null;
		}
	}

	private String 	name;

	private Long	parentId;

	private ScheduleType type;

	@HbaseColumn(name = "idx")
	private int index = 1; // 行程日程排序号

	@HbaseColumn(name = "dt")
	private Long date = 0l; // 行程日程日期

	@HbaseColumn(name = "stm")
	private String startTime = "00:00"; // 行程活动始时间

	@HbaseColumn(name = "etm")
	private String endTime = "00:00"; // 行程活动结束时间

	private float	duration; //持续时间

	private String price; //价格信息
	private String currency; //币种

	@HbaseColumn(name = "desc")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String memo; // 日程描述

	private String reason; //推荐理由

	private String places; //目的地

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_RELATED, type = ResourceBean.class, direction = Direction.OUTGOING)
	private transient ResourceBean resource = null; // 行程活动关联的资源

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteScheduleItemBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<RouteScheduleItemBean> items = null;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_HAS, type = RouteMainBean.class, direction = Direction.INCOMING)
	private transient RouteMainBean routeMain = null; // 行程日程关联的行程

	public RouteScheduleBean() {
		super();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScheduleType getType() {
		return type;
	}

	public void setType(ScheduleType type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public RouteMainBean getRouteMain() {
		return routeMain;
	}

	public void setRouteMain(RouteMainBean routeMain) {
		this.routeMain = routeMain;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public ResourceBean getResource() {
		return resource;
	}

	public void setResource(ResourceBean resource) {
		this.resource = resource;
	}

	public List<RouteScheduleItemBean> getItems() {
		return items;
	}

	public void setItems(List<RouteScheduleItemBean> items) {
		this.items = items;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		RouteScheduleBean clone = (RouteScheduleBean) super.clone();

		clone.setId(-1l);
		clone.setRowKey("");

		if(this.resource != null){
			clone.resource = (ResourceBean) this.resource.clone();
		}

		return clone;
	}
}
